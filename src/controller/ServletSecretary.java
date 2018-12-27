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

    Integer cfu = Integer.parseInt(request.getParameter("cfu"));
    Integer result = 0;
    String error = "";
    String content = "";

    new DbConnection();
    Connection connDb = DbConnection.getIstance().getConn();
    if (((DbConnection) connDb).getConn() != null) {
      try {
        String sql = "INSERT INTO REQUEST(validated_CFU) VALUES (NOW()) ;";
        PreparedStatement stmt = ((DbConnection) connDb).getConn().prepareStatement(sql);
        stmt.setInt(1, cfu);
        if (stmt.executeUpdate() == 1) {
          content = "CFU inseriti con successo.";
          result = 1;
        } else {
          error = "Errore Inserimento CFU.";
          result = 0;
        }

        if (result == 0) {
          ((DbConnection) connDb).getConn().rollback();
        } else {
          ((DbConnection) connDb).getConn().commit();
        }
        ((DbConnection) connDb).getConn().close();
      } catch (Exception e) {
        error = "Errore esecuzione Query.";
        result = 0;
      }
    } 

  }

}
