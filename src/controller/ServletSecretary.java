package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ServletSecretary.
 */
@WebServlet("/ServletSecretary")
public class ServletSecretary extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   * @see HttpServlet#HttpServlet()
   */
  public ServletSecretary() {
    super();
  }

  /**
   * Method doGet().
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * * Method doPost().
   * * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Integer result = 0;
    String error = "";
    String content = "";
    PreparedStatement stmt = null;

    int flag = Integer.parseInt(request.getParameter("flag"));
    Connection connDb = new DbConnection().getInstance().getConn();
    String sql = "";

    if (connDb != null) {

      if (flag == 1) {
        try {
          sql = "SELECT * FROM REQUEST;";
          stmt = ((DbConnection) connDb).getConn().prepareStatement(sql);
          if (stmt.executeUpdate() == 1) {
            content = "Success";
            result = 1;
          } else {
            error = "Error";
            result = 0;
          }

          if (result == 0) {
            ((DbConnection) connDb).getConn().rollback();
          } else {
            ((DbConnection) connDb).getConn().commit();
          }
          ((DbConnection) connDb).getConn().close();
        } catch (Exception e) {
          error = "Errore nell'esecuzione Query.";
          result = 0;
        }
      }  else if (flag == 2) { 
          
        Integer idRequest = Integer.valueOf(request.getParameter("idRequest"));
        Integer cfu = Integer.valueOf(request.getParameter("cfu"));
  
        try {
          sql = "SELECT validated_CFU FROM REQUEST WHERE id_request = ?";
          stmt = connDb.prepareStatement(sql);
          stmt.setInt(1, idRequest);
          ResultSet r = stmt.executeQuery();
          if (r.wasNull()) {
            error = "Errore nell'esecuzione della Query";
          } else {
            int count = r.last() ? r.getRow() : 0;
            if (count == 1) {
              sql = "UPDATE REQUEST SET validated_CFU = ? WHERE id_request = ?";
              stmt = connDb.prepareStatement(sql);
              stmt.setInt(1, cfu);
              stmt.setString(1, r.getString("validated_cfu"));
              if (stmt.executeUpdate() > 0) {
                content = "Success";
                result = 1;
              } else {
                error = "Error";
              }
            } else {
              error = "Error";
            }
          }
          
          if (result == 0) {
            connDb.rollback();
          } else {
            connDb.commit();
          }

        } catch (Exception e) {
          error += e.getMessage();
        } 
      }
      
    } else {
      error = "Nessuna connessione al database.";
    }
    
    JSONObject res = new JSONObject();
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    PrintWriter out = response.getWriter();
    out.println(res);
  }
}
