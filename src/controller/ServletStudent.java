package controller;

import interfacce.UserInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Student;
import model.SystemAttribute;
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
    Connection conn = new DbConnection().getInstance().getConn();
    String sql = "";

    if (conn != null) {

      if (flag == 1) { // registrazione nuovo utente        
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        char sex = request.getParameter("sex").charAt(0);      
        String password = new Utils().generatePwd(request.getParameter("password"));
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
      else if (flag == 2) { // registrazione primo form in DB
        UserInterface user =  (UserInterface) request.getSession().getAttribute("user");
        
        Date releaseDate = null;
        Date expiryDate = null;
        Date year = null;
        try {
          releaseDate = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("releaseDate"));
          expiryDate = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("expiryDate"));
          year = (Date) new SimpleDateFormat("yyyy").parse(request.getParameter("year"));  
        } catch (ParseException e) {
          error += e.getMessage();
        }           
        String certificateSerial = request.getParameter("certificateSerial");
        String level = request.getParameter("level");                
        int requestedCfu = Integer.parseInt(request.getParameter("requestedCfu"));
        int serial = Integer.parseInt(request.getParameter("serial"));
        int validatedCfu = Integer.parseInt(request.getParameter("validatedCfu"));
        String idUser = user.getEmail();
        int idEnte = Integer.parseInt(request.getParameter("idEnte"));                
        int idState = Integer.parseInt(new SystemAttribute().getValueByKey("partially-completed"));
        try {
          sql = " SELECT id_request FROM request WHERE fk_user = ? AND (fk_state = ? OR fk_state = ?) ";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, idUser);
          stmt.setString(2, new SystemAttribute().getValueByKey("accepted"));
          stmt.setString(3, new SystemAttribute().getValueByKey("refused"));
          ResultSet r = stmt.executeQuery();
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
            int count = r.last() ? r.getRow() : 0;
            if (count == 0) {
              sql =
                  " INSERT INTO request "
                + " (level, realase_date, expiry_date, year, requested_cfu, serial, validated_cfu, fk_user, fk_certifier, fk_state, certificate_serial) "
                + " VALUES "
                + " (?, ?, ?, ?, ?, ?, ?) ";
              stmt = conn.prepareStatement(sql);              
              stmt.setString(1, level);
              stmt.setDate(2, releaseDate);
              stmt.setDate(3, expiryDate);
              stmt.setDate(4, year);
              stmt.setInt(5, requestedCfu);
              stmt.setInt(6, serial);
              stmt.setInt(7, validatedCfu);
              stmt.setString(8, idUser);
              stmt.setInt(9, idEnte);
              stmt.setInt(10, idState);
              stmt.setString(11, certificateSerial);
              if (stmt.executeUpdate() > 0) {            
                content = "Richiesta presentata con successo.";
                redirect = request.getContextPath() + "/_areaStudent/uploadAttached.jsp";
                result = 1;
              } else {
                error = "Impossibile presentare la richiesta.";
              }            
            }
            else {
              error = "Una richiesta gi&agrave; presentata non &egrave; stata ancora conclusa.";
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
