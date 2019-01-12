package controller;

import interfacce.UserInterface;
import java.io.File;
import java.io.FileOutputStream;
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
import model.Request;
import model.Student;
import model.SystemAttribute;
import org.eclipse.jdt.internal.compiler.env.IModule;
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
  
 
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Method doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @SuppressWarnings("unchecked")
  public void doPost(HttpServletRequest request, HttpServletResponse response)
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
        if (name.length() == 0 || name.length() > 20) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        String surname = request.getParameter("surname");
        if (surname.length() == 0 || surname.length() > 20) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        String email = request.getParameter("email");
        /*l'email è valida se la sua lunghezza è diversa da 0, 
         * se non è presente nel DB e se rispetta il formato
         * se finisce con @studenti.unisa.it
        */
        String prefix = email.substring(0, email.indexOf("@"));
        if (email.length() == 0 
            || !email.endsWith("@studenti.unisa.it") 
            || prefix.length() < 3 || prefix.indexOf(".") == -1) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        
        char sex = request.getParameter("sex").charAt(0);
        if (sex != 'M' && sex != 'F') {
          throw new IllegalArgumentException("Valore non corretto");
        }

        //controlla la password prima di criptarla
        String pass = request.getParameter("password");
        if (pass.length() < 8) {
          throw new IllegalArgumentException("Formato non corretto");
        }
        //quì la password viene criptata per essere poi salvata nel db
        String password = new Utils().generatePwd(pass);
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
              sql = " INSERT INTO user " + " (email, name, surname, sex, password, user_type) "
                  + " VALUES " + " (?, ?, ?, ?, ?, ?) ";
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
            } else {
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
      } else if (flag == 2) { // registrazione primo form in DB
        UserInterface user = (UserInterface) request.getSession().getAttribute("user");
        
        String year = request.getParameter("year");
        if (year.length() == 0) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        /*da decidere se tenere o togliere
        String graduation = request.getParameter("graduation");
        */
        int serial = Integer.parseInt(request.getParameter("serial"));
        if (serial > 999999999) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        int idEnte = Integer.parseInt(request.getParameter("idEnte"));
        if (idEnte == 0) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        String expiryDate = request.getParameter("expiryDate");
        if (expiryDate.length() == 0) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        String releaseDate = request.getParameter("releaseDate");
        if (releaseDate.length() == 0) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        String certificateSerial = request.getParameter("certificateSerial");
        if (certificateSerial.length() == 0) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        String level = request.getParameter("level");
        if (level.length() < 2 || level.length() > 4) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        int requestedCfu = Integer.parseInt(request.getParameter("requestedCfu"));
        if (requestedCfu > 12) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        
        
        
        int validatedCfu = 0;
        String idUser = user.getEmail();
        
        int idState =
            Integer.parseInt(new SystemAttribute().getValueByKey("request-partially-completed"));
        try {
          sql = " SELECT id_request "
              + "FROM request WHERE fk_user = ? AND (fk_state = ? OR fk_state = ?) ";
          stmt = conn.prepareStatement(sql);
          stmt.setString(1, idUser);
          stmt.setString(2, new SystemAttribute().getValueByKey("request-accepted"));
          stmt.setString(3, new SystemAttribute().getValueByKey("request-refused"));
          ResultSet r = stmt.executeQuery();
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
            int count = r.last() ? r.getRow() : 0;
            if (count == 0) {
              sql = " INSERT INTO request "
                  + " (level, release_date, expiry_date, year, requested_cfu,"
                  + " serial, validated_cfu, fk_user, fk_certifier, fk_state, certificate_serial) "
                  + " VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
              stmt = conn.prepareStatement(sql, stmt.RETURN_GENERATED_KEYS);
              stmt.setString(1, level);
              stmt.setString(2, releaseDate);
              stmt.setString(3, expiryDate);
              stmt.setString(4, year);
              stmt.setInt(5, requestedCfu);
              stmt.setInt(6, serial);
              stmt.setInt(7, validatedCfu);
              stmt.setString(8, idUser);
              stmt.setInt(9, idEnte);
              stmt.setInt(10, idState);
              stmt.setString(11, certificateSerial);
              if (stmt.executeUpdate() > 0) {
                content = "Richiesta parziale presentata con successo.";
                redirect = request.getContextPath() + "/_areaStudent/uploadAttached.jsp";

                Integer idRequest = 0;

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                  idRequest = rs.getInt(1);
                }

                request.getSession().setAttribute("idRequest", idRequest);
                request.getSession().setAttribute("idEnte", idEnte);                
                request.getSession().setAttribute("requestedCfu", requestedCfu);
                request.getSession().setAttribute("level", level);
                request.getSession().setAttribute("releaseDate", releaseDate);
                request.getSession().setAttribute("expiryDate", expiryDate);
                request.getSession().setAttribute("validatedCfu", validatedCfu);
                request.getSession().setAttribute("serial", serial);
                request.getSession().setAttribute("year", year);
                request.getSession().setAttribute("certificateSerial", certificateSerial);
                

                result = 1;
                rs.close();
              } else {
                error = "Impossibile presentare la richiesta.";
              }
            } else {
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
      } else if (flag == 3) { // inserimento allegati in DB
        String[] filenames = request.getParameterValues("filenames[]");
        if (filenames.length != 2 
            || !filenames[0].endsWith(".pdf") 
            || !filenames[1].endsWith(".pdf")) {
          throw new IllegalArgumentException("Valore non corretto");
        }
        Integer idRequest = (Integer) request.getSession().getAttribute("idRequest");
        UserInterface user = (UserInterface) request.getSession().getAttribute("user");

        result = 1;
        try {
          for (int i = 0; i < filenames.length; i++) {
            sql = " INSERT INTO attached " + " (filename, fk_request, fk_user) " + " VALUES "
                + " (?, ?, ?) ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, filenames[i]);
            stmt.setInt(2, idRequest);
            stmt.setString(3, user.getEmail());
            if (stmt.executeUpdate() > 0) {
              result *= 1;
            } else {
              error += " Impossibile inserire l'allegato ." + filenames[i];
              result *= 0;
            }
          }

          if (result == 1) {
            Integer newState =
                Integer.parseInt(new SystemAttribute().getValueByKey("request-working-secretary"));
            sql = " UPDATE request SET fk_state = ? WHERE id_request = ?; ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, newState);
            stmt.setInt(2, idRequest);
            if (stmt.executeUpdate() > 0) {
              result = 1;
              redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
              content = "Allegati inseriti con successo.";
            } else {
              error += " Impossibile cambiare stato alla richiesta.";
              result = 0;
            }
          }

          if (result == 0) {
            conn.rollback();
            result *= 0;
          } else {
            conn.commit();
          }
        } catch (Exception e) {
          error += e.getMessage();
          result *= 0;
        }

      } 

    } else {
      error += "Nessuna connessione al database.";
    }


    JSONObject res = new JSONObject();
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    res.put("redirect", redirect);
    PrintWriter out = response.getWriter();
    out.println(res);
    response.setContentType("json");
  }
}
