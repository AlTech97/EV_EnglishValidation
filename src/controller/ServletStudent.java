package controller;

import interfacce.UserInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ServletStudent.
 */
@WebServlet("/ServletStudent")
public class ServletStudent extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletStudent() {
    super();
  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @SuppressWarnings("unchecked")
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Integer result = 0;
    String error = "";
    String content = "";
    String redirect = "";
    PreparedStatement stmt = null;

    int flag = Integer.parseInt(request.getParameter("flag"));
    Connection conn = new DbConnection().getIstance().getConn();
    String sql = "";

    if (conn != null) {

      if (flag == 1) { // registrazione nuovo utente        
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        char sex = request.getParameter("sex").charAt(0);      
        String password = new PasswordUtils().generatePwd(request.getParameter("password"));
        int userType = 0;
        UserInterface user = null;
        
        try {
          sql = " SELECT  email FROM user WHERE TRIM(LOWER(email)) = TRIM(?) ";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, email.toLowerCase());
          ResultSet r = stmt.executeQuery();
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
            int count = r.last() ? r.getRow() : 0;
            if (count == 0) {
              sql =
                  " INSERT INTO user "
                + " (email, name, surname, sex, password, user_type) "
                + " VALUES "
                + " (?, ?, ?, ?, ?, ?) ";
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, email.toLowerCase());
              stmt.setString(2, name);
              stmt.setString(3, surname);
              stmt.setString(4, String.valueOf(sex));
              stmt.setString(5, password);
              stmt.setInt(6, userType);
              content = stmt.toString();
              if (stmt.executeUpdate() > 0) {            
                redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
                user = new Student(email, name, surname, sex, password, userType);            
                request.getSession().setAttribute("user", user);
                content = "Registrazione effettuata correttamente.";
                result = 1;
              } else {
                error = "Impossibile effettuare la registrazione.";
              }            
            }
            else {
              error = "Utente gi&agrave; registrato.";
            }
          }

          if (result == 0) {
            conn.rollback();
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
        }
      }
      
      
    } else {
      error = "Nessuna connessione al database.";
    }


    JSONObject res = new JSONObject();
    res.put("risultato", result);
    res.put("errore", error);
    res.put("contenuto", content);
    res.put("redirect", redirect);
    PrintWriter out = response.getWriter();
    out.println(res);
  }
}
