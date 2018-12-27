package controller;

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
import org.json.simple.JSONObject;
import interfacce.UserInterface;
import model.Admin;
import model.Secretary;
import model.Student;

/**
 * Servlet implementation class ServletCommon.
 */
@WebServlet("/ServletCommon")
public class ServletCommon extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletCommon() {
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

    int flag = Integer.parseInt(request.getParameter("flag"));
    Connection conn = new DbConnection().getIstance().getConn();
    String sql = "";
    
    if (conn != null) {
      
      if (flag == 1) { // login       
        String email = request.getParameter("email");
        String password = new PasswordUtils().generatePwd(request.getParameter("password"));        
        try {  
          sql = " SELECT  name, surname, sex, user_type FROM user WHERE TRIM(email) = TRIM(?) AND TRIM(password) = TRIM(?)";
          PreparedStatement stmt = conn.prepareStatement(sql);
          stmt.setString(1, email);
          stmt.setString(2, password);
          ResultSet r = stmt.executeQuery();
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          }
          else {
            int count = r.last() ? r.getRow() : 0;
            if (count == 1) {
              UserInterface user = null;
              String name = r.getString("name");
              String surname = r.getString("surname");
              char sex = r.getString("sex").charAt(0);
              
              int userType = r.getInt("user_type");
              if (userType == 0) { // Profilo Student
                redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
                user = new Student(email, name, surname, sex, password, userType);
              }
              else if (userType == 1) { // Profilo Secretary
                redirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
                user = new Secretary(email, name, surname, sex, password, userType);
              }
              else if (userType == 2) { // Profilo Admin
                redirect = request.getContextPath() + "/_areaAdmin/viewRequest.jsp";
                user = new Admin(email, name, surname, sex, password, userType);
              }
              
              request.getSession().setAttribute("user", user);
              
              result = 1;
            }  
            else {
              error = "Username o Password errati.";
            }
          }
              
        
          if (result == 0) {
            conn.rollback();
          } else {
            conn.commit();
          }
          
        } 
        catch (Exception e) {
          error = e.getMessage();
        }      
      }
            
    }
    else {
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
