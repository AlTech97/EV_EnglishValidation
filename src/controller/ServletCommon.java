package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.PrintWriter;
import org.json.simple.JSONObject;



/**
 * Servlet implementation class ServletCommon
 */
@WebServlet("/ServletCommon")
public class ServletCommon extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ServletCommon() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Integer risultato = 0;
    String errore = "";
    String contenuto = "";
    String redirect = "";


    int flag = Integer.parseInt(request.getParameter("flag"));

    if (flag == 1) { // login
      Connection connDB = new DbConnection().getIstance().getConn();
      String email = request.getParameter("email");
      String password = request.getParameter("password");



      try {

        String sql = "";
        sql = "" + "SELECT email, password " + "FROM user AS u "
            + "WHERE TRIM(u.email) = ? AND u.password = ?;";
        PreparedStatement stmt = connDB.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);
        ResultSet result = stmt.executeQuery(sql);
        if (result.wasNull()) {
          errore = "Errore nell'esecuzione della Query";
          risultato = 0;
        } else {

          int count = result.last() ? result.getRow() : 0;
          if (count == 1) {

            request.getSession().setAttribute("email", result.getString("email"));
            // Inserire gli altri set

            HttpSession session = request.getSession();
            if ((int) session.getAttribute("userType") == 0) { // Profilo Student
              redirect = request.getContextPath() + "/_areaStudent/viewRequest.jsp";
            }
            if ((int) session.getAttribute("userType") == 1) { // Profilo Secretary
              redirect = request.getContextPath() + "/_areaSecretary/viewRequest.jsp";
            }
            if ((int) session.getAttribute("userType") == 2) { // Profilo Admin
              redirect = request.getContextPath() + "/_areaAdmin/viewRequest.jsp";
            }

            contenuto = "Utente Trovato";
            risultato = 1;
          } else {
            errore = "Utente NON Trovato";
            risultato = 0;
          }
        }

        if (risultato == 0) {
          connDB.rollback();
        } else {
          connDB.commit();
        }

      } catch (Exception e) {
        errore = "Errore esecuzione Query.";
        risultato = 0;
      }


    }
    
    JSONObject res = new JSONObject();
    PrintWriter out = response.getWriter();
    res.put("risultato", risultato);
    res.put("errore", errore);
    res.put("contenuto", contenuto);
    res.put("redirect", redirect);
    out.println(res);


  }
}
