package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Request;

/**
 * Servlet implementation class ServletAdmin.
 */
@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Servlet implementation class ServletCommon.
   */
  public ServletAdmin() {
    super();

  }

  /**
   * Method doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
  }

  /**
   * Method doPost().
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    /*    Integer result = 0; // indica se la query è riuscita
    String error = ""; // indica il mex di errore
    String content = ""; // indica il contenuto trovato dopo la query
    String redirect = ""; // reindirizza di nuovo alla pagina in caso di errore

    // creiamo la connessione al DB
    new DbConnection();
    Connection connDb = DbConnection.getIstance().getConn();
    // VISUALIZZAZIONE DI TUTTE LE RICHIESTE
    if (((DbConnection) connDb).getConn() != null) {
      try {
        // query per permettere la visualizzazione di una lista con le richieste
        String sql = "SELECT * FROM REQUEST;";
        PreparedStatement statement = ((DbConnection) connDb).getConn().prepareStatement(sql);
        if (statement.executeUpdate() == 1) {
          content = "Success ";
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
    }
    // Permettere la verifica del certificato tramite il suo codice (( INCOMPLETA ))
    int idAttached = Integer.parseInt(request.getParameter("idAttached"));
    new DbConnection();
    Connection connDb2 = DbConnection.getIstance().getConn();
    if (((DbConnection) connDb2).getConn() != null) {
      try {
        String sql = "SELECT * FROM ATTACHED WHERE ID_ATTACHED = " + idAttached + ";";
        PreparedStatement statement = ((DbConnection) connDb).getConn().prepareStatement(sql);
        statement.setInt(1, idAttached);
        ResultSet resultSet = statement.executeQuery(sql);
        if (statement.executeUpdate() == 1) {
          content = "Success ";
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
        error = "Errore nell'esecuzione della query";
        result = 0;
      }
    }
     */
    
    // della request ci serve mostrare il suo id e il serial
    Connection connDb = null; // serve per aprire la connessione al db
    PreparedStatement stmt = null; // usata per permettere l'exe della query sul db (query"sql")
    ResultSet rs = null; // insieme dei dati che abbiamo ricavato dal db, lo mostreremo nella jsp
    String sql = "SELECT * FROM REQUEST;";
    try {
      ArrayList list = new ArrayList();
      connDb = DbConnection.getIstance().getConn();
      stmt = ((DbConnection) connDb).getConn().prepareStatement(sql);
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        Request r = new Request();
        r.setIdRequest(rs.getInt("idRequest"));
        r.setSerial(rs.getInt("serial"));
        list.add(r);
      }
      //set la req del browser (request) con attrib list (Array) ci serve a rich.i dati presi da jsp
      request.setAttribute("list", list);
      // esegue un reindirizzamento sulla pagina in modo che venga visualizzata dopo la query
      RequestDispatcher disp = request.getRequestDispatcher("/viewRequest.jsp"); 
      disp.forward(request, response);
    } catch (SQLException ex) {
      ex.getMessage();
    } finally {
      try {
        rs.close();
        stmt.close();
      } catch (SQLException ex) {
        ex.getMessage();
      }
    }
  }
}



