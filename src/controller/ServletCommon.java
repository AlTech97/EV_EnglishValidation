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
import java.io.PrintWriter;
import org.json.simple.JSONObject;

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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Integer risultato = 0;
    String errore = "";
    String contenuto = "";
    String redirect = "";


    int flag = Integer.parseInt(request.getParameter("flag"));

    if (flag == 1) { // login
      new DbConnection();
      Connection connDb = DbConnection.getIstance().getConn();
      String email = request.getParameter("email");
      String password = request.getParameter("password");



      try {

        String sql = "";
        sql = ""
            +"SELECT EMAIL,USER_TYPE, PASSWORD" + "FROM user AS us "
            + "WHERE us.USER_TYPE = 1 AND TRIM(us.EMAIL) = TRIM('" + email
            + "') AND TRIM(us.PASSWORD) = TRIM('" + password + "');";
        
        PreparedStatement stmt = connDb.prepareStatement(sql);
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
          connDb.rollback();
        } else {
          connDb.commit();
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
