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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * * Method doPost(). * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   * response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    /*
     * Integer result = 0; String error = ""; String content = ""; PreparedStatement stmt = null;
     * 
     * int flag = Integer.parseInt(request.getParameter("flag")); Connection connDb = new
     * DbConnection().getInstance().getConn(); String sql = "";
     * 
     * if (connDb != null) {
     * 
     * if (flag == 1) { try { sql = "SELECT * FROM REQUEST;"; stmt = ((DbConnection)
     * connDb).getConn().prepareStatement(sql); if (stmt.executeUpdate() == 1) { content =
     * "Success"; result = 1; } else { error = "Error"; result = 0; }
     * 
     * if (result == 0) { ((DbConnection) connDb).getConn().rollback(); } else { ((DbConnection)
     * connDb).getConn().commit(); } ((DbConnection) connDb).getConn().close(); } catch (Exception
     * e) { error = "Errore nell'esecuzione Query."; result = 0; } } else if (flag == 2) {
     * 
     * Integer idRequest = Integer.valueOf(request.getParameter("idRequest")); Integer cfu =
     * Integer.valueOf(request.getParameter("cfu"));
     * 
     * try { sql = "SELECT validated_CFU FROM REQUEST WHERE id_request = ?"; stmt =
     * connDb.prepareStatement(sql); stmt.setInt(1, idRequest); ResultSet r = stmt.executeQuery();
     * if (r.wasNull()) { error = "Errore nell'esecuzione della Query"; } else { int count =
     * r.last() ? r.getRow() : 0; if (count == 1) { sql =
     * "UPDATE REQUEST SET validated_CFU = ? WHERE id_request = ?"; stmt =
     * connDb.prepareStatement(sql); stmt.setInt(1, cfu); stmt.setString(1,
     * r.getString("validated_cfu")); if (stmt.executeUpdate() > 0) { content = "Success"; result =
     * 1; } else { error = "Error"; } } else { error = "Error"; } }
     * 
     * if (result == 0) { connDb.rollback(); } else { connDb.commit(); }
     * 
     * } catch (Exception e) { error += e.getMessage(); } }
     * 
     * } else { error = "Nessuna connessione al database."; }
     */
    Integer result = 0; // indica se la query è riuscita
    String error = ""; // indica il mex di errore
    String content = ""; // indica il contenuto trovato dopo la query
    String redirect = ""; // reindirizza di nuovo alla pagina in caso di errore

    PreparedStatement stmt = null;
    Statement stmtSelect = null;

    int flag = 1;// Integer.parseInt(request.getParameter("flag"));
    Connection conn = new DbConnection().getInstance().getConn();
    String sql = "";

    if (conn != null) {
      Integer requestWorkingEducationAdvice1 = Integer
          .parseInt(new SystemAttribute().getValueByKey("request-working-educational-advice-1"));
      Integer requestWorkingEducationAdvice2 = Integer
          .parseInt(new SystemAttribute().getValueByKey("request-working-educational-advice-2"));
      Integer requestWorkingAdmin =
          Integer.parseInt(new SystemAttribute().getValueByKey("request-working-admin"));
      Integer requestAccepted =
          Integer.parseInt(new SystemAttribute().getValueByKey("request-accepted"));
      Integer requestRefused =
          Integer.parseInt(new SystemAttribute().getValueByKey("request-refused"));

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
              + requestWorkingAdmin + ", " + requestWorkingEducationAdvice1 + ", "
              + requestWorkingEducationAdvice2 + ", " + requestAccepted + ", " + requestRefused
              + ")";
          ResultSet r = stmtSelect.executeQuery(sql);
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
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
                content += "    <td class='text-center'>" + r.getString("level") + "</td>";
                content += "<td>" + "<input type=\"checkbox\" name=\"\" value=\"\">" + "</td>";
                if (r.getInt("validated_cfu") == 0) {
                  content += "    <td class='text-center'>NC</td>";
                } else {
                  content += "    <td class='text-center'>" + r.getInt("validated_cfu") + "</td>";
                }

                content += "    <td class='text-center'>";
              }
              
              result = 1;
            }
          }
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

        JSONObject res = new JSONObject();
        res.put("result", result);
        res.put("error", error);
        res.put("content", content);
        PrintWriter out = response.getWriter();
        out.println(res);
      }
    }
  }
}
