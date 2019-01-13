package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.SystemAttribute;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ServletSecretary.
 */
@WebServlet("/ServletSecretary")
public class ServletSecretary extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ServletSecretary() {
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
   * * Method doPost(). * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   * response)
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Integer result = 0; // indica se la query è riuscita
    String error = ""; // indica il mex di errore
    String content = ""; // indica il contenuto trovato dopo la query
    String redirect = ""; // reindirizza di nuovo alla pagina in caso di errore

    PreparedStatement stmt = null;
    Statement stmtSelect = null;

    int flag = Integer.parseInt(request.getParameter("flag"));
    Connection conn = new DbConnection().getInstance().getConn();
    String sql = "";

    if (conn != null) {
      Integer requestWorkingSecretary = Integer
          .parseInt(new SystemAttribute().getValueByKey("request-working-secretary"));

      if (flag == 1) { // Preleva tutte le richieste
        try {
          stmtSelect = conn.createStatement();
          sql = "SELECT r.id_request, r.certificate_serial, r.level, r.release_date,"
              + " r.expiry_date, r.year, r.requested_cfu, r.serial, r.validated_cfu,"
              + " u.email AS user_email, u.name, u.surname, e.name AS state, e.email AS ente_mail,"
              + " e.site AS ente_site, s.description AS ente, s.id_state " + "FROM request r"
              + "     INNER JOIN ente e ON r.fk_certifier = e.id_ente "
              + "     INNER JOIN state s ON r.fk_state = s.id_state "
              + "     INNER JOIN user u ON r.fk_user = u.email " + "WHERE s.id_state IN("
              + requestWorkingSecretary
              + ")";
          ResultSet r = stmtSelect.executeQuery(sql);
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
            result = 1;
            int count = r.last() ? r.getRow() : 0;
            if (count > 0) {
              r.beforeFirst();
              SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
              while (r.next()) {

                content += "<tr>";
                content += "    <td class='text-center'>" + r.getString("id_request") + "</td>";
                content += "    <td class='text-center'>" + r.getString("serial") + "</td>";
                content += "<td class='text-center'>" + r.getString("name");
                content += "</td>";
                content += "<td class='text-center'>" + r.getString("surname");
                content += "</td>";
                content +=
                    "    <td class='text-center'>" + r.getString("certificate_serial") + "</td>";
                content += "<td>" 
                    + "<input type=\"text\" class=\"form-control cfuToValidate\" "
                    + "value=\"" + r.getString("validated_cfu") + "\" "
                    + "placeholder=\"Inserire i CFU gi&agrave; convalidati\""
                    + " data-idRequest=\"" + r.getInt("id_request") + "\">";

                content += "<button class=\"btn btn-primary btn-action saveCfu"
                    + "\" title=\"Inserisci i CFU Convalidati\" data-idRequest=\"" 
                    + r.getString("id_request")
                    + "\"><i class=\"fa fa-edit\"></i></button>";
                
                content += "</td>";

                content += "<td class='text-center'>";
                content += "<button class=\"btn btn-primary btn-action toWorkingAdmin"
                    + "\" title=\"Inoltra all'admin\" data-idRequest=\"" + r.getString("id_request")
                    + "\" style='background-color: #149414; border-color: #149414;' "
                    + "><i class=\"fa fa-check\"></i></button>";
                
                content += "</td>";
              }              
            } else {
              content += "<tr><td colspan='7' class=\"text-center\""
                  + ">Nessuna Richiesta Presente</td></tr>";
            }
          }
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

      } else if (flag == 2) { //Set cfu        
        Integer idRequest = Integer.parseInt(request.getParameter("idRequest"));
        Integer cfu = Integer.parseInt(request.getParameter("cfu"));
        
        try {
          sql = " UPDATE request SET validated_cfu = ? WHERE id_request = ?; ";
          stmt = conn.prepareStatement(sql);
          stmt.setInt(1, cfu);
          stmt.setInt(2, idRequest);
          if (stmt.executeUpdate() > 0) {
            result = 1;
            content = "CFU aggiornati con successo.";
          } else {
            error += " Impossibile cambiare i CFU nella richiesta.";
            result = 0;
          }

          if (result == 0) {
            conn.rollback();
            result *= 0;
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
          result = 0;
        }        
      } else if (flag == 3) { //Inoltra all'admin        
        Integer idRequest = Integer.parseInt(request.getParameter("idRequest"));
        Integer requestWorkingAdminState = Integer
            .parseInt(new SystemAttribute().getValueByKey("request-working-admin"));
        
        try {
          sql = " UPDATE request SET fk_state = ? WHERE id_request = ?; ";
          stmt = conn.prepareStatement(sql);
          stmt.setInt(1, requestWorkingAdminState);
          stmt.setInt(2, idRequest);
          if (stmt.executeUpdate() > 0) {
            result = 1;
            content = "Richiesta inoltrata all'amministratore con successo.";
          } else {
            error += " Impossibile inoltrare la richiesta.";
            result = 0;
          }

          if (result == 0) {
            conn.rollback();
            result *= 0;
          } else {
            conn.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
          result = 0;
        }        
      }    

    } else {
      result = 0;
      error = "Nessuna connesione al DB";
    }

    JSONObject res = new JSONObject();
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    PrintWriter out = response.getWriter();
    out.println(res);
    response.setContentType("json");
  }
}
