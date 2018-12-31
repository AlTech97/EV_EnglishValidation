package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import model.Request;
import model.SystemAttribute;

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
      Integer requestWorkingEducationAdvice1 = Integer.parseInt(new SystemAttribute().getValueByKey("request-working-educational-advice-1"));
      Integer requestWorkingEducationAdvice2 = Integer.parseInt(new SystemAttribute().getValueByKey("request-working-educational-advice-2"));

      if(flag == 1) { //Preleva tutte le richieste
        try {
          Integer requestWorkingAdmin = Integer.parseInt(new SystemAttribute().getValueByKey("request-working-admin"));
          Integer requestAccepted = Integer.parseInt(new SystemAttribute().getValueByKey("request-accepted"));
          Integer requestRefused = Integer.parseInt(new SystemAttribute().getValueByKey("request-refused"));
          
          
          stmtSelect = conn.createStatement();
          sql = "SELECT r.id_request, r.certificate_serial, r.level, r.release_date, r.expiry_date, r.year, r.requested_cfu, r.serial, r.validated_cfu, u.name, u.surname, e.name AS state, e.email AS ente_mail, e.site AS ente_site, s.description AS ente, s.id_state "
              + "FROM request r"
              + "     INNER JOIN ente e ON r.fk_certifier = e.id_ente "
              + "     INNER JOIN state s ON r.fk_state = s.id_state "
              + "     INNER JOIN user u ON r.fk_user = u.email "
              + "WHERE s.id_state IN("+requestWorkingAdmin+", "+requestWorkingEducationAdvice1+", "+requestWorkingEducationAdvice2+", "+requestAccepted+", "+requestRefused+")";
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
                content += "    <td class='text-center'>"+r.getString("id_request")+"</td>";
                content += "    <td class='text-center'>"+r.getString("serial")+"</td>";
                content += "    <td class='text-center'>"+r.getString("name")+"</td>";
                content += "    <td class='text-center'>"+r.getString("surname")+"</td>";
                content += "    <td class='text-center'>"+r.getInt("year")+"/"+(r.getInt("year")+1)+"</td>";
                content += "    <td class='text-center'>"+r.getString("certificate_serial")+"</td>";
                content += "    <td class='text-center'>"+r.getString("level")+"</td>";
                content += "    <td class='text-center'>"+sdf.format(r.getDate("release_date"))+"</td>";
                content += "    <td class='text-center'>"+sdf.format(r.getDate("expiry_date"))+"</td>";
                content += "    <td class='text-center'>"+r.getString("requested_cfu")+"</td>";
                if(r.getInt("validated_cfu") == 0) {
                  content += "    <td class='text-center'>NC</td>";                  
                }
                else {
                  content += "    <td class='text-center'>"+r.getInt("validated_cfu")+"</td>";
                }
                
                content += "    <td class='text-center'>"+r.getString("ente")+"</td>";
                content += "    <td class='text-center'>"+r.getString("state")+"</td>";
                content += "    <td class='text-center'>";                
                
                
                
                if(r.getInt("id_state") == requestWorkingAdmin) { //Se è in lavorazione dall'admin
                  if (!r.getString("ente_mail").equals("")) { //Se è settata la mail dell'ente                  
                    content += "<button class=\"btn btn-primary btn-action verifyCertificate\" title=\"Verifica Validit&agrave; Certificato Tramite Mail\" data-mail=\""+r.getString("ente_mail")+"\" data-certserial=\""+r.getString("certificate_serial")+"\"><i class=\"fa fa-question\"></i></button>";
                  } 
                  else if (!r.getString("ente_site").equals("")) { //Se è settato il sito dell'ente                  
                    content += "<a target=\"_blank\" href=\""+r.getString("ente_site")+"\" class=\"btn btn-primary verifyCertificate\" title=\"Verifica Validit&agrave; Certificato Tramite Sito Web\"><i class=\"fa fa-question\"></i></button>";
                  } 
                  
                  //Mettere in requestWorkingEducationAdvice1
                  content += "<button class=\"btn btn-primary btn-action toWorkingEducationAdvice accept\" data-type=\"1\" data-idrequest=\""+r.getInt("id_request")+"\" title=\"Accetta e Inoltra al Consiglio Didattico\"><i class=\"fa fa-check\"></i></button>";
                  
                  //oppure Mettere in requestWorkingEducationAdvice2
                  content += "<button class=\"btn btn-primary btn-action toWorkingEducationAdvice refuse\" data-type=\"2\" data-idrequest=\""+r.getInt("id_request")+"\" title=\"Rifiuta e Inoltra al Consiglio Didattico\"><i class=\"fa fa-times\"></i></button>";
                }
                
                if (r.getInt("id_state") == requestWorkingEducationAdvice1 || r.getInt("id_state") == requestWorkingEducationAdvice2) { //Se è in lavorazione da consiglio didattico
                  //Mettere in requestAccepted
                  //oppure Mettere in requestRefused                                
                }
                
                content += "</td>";
                
                content += "</tr>";
              } 
              result = 1;
            } else {
              result = 1;
              content = "<tr><td colspan='14' class='text-center'>Nessuna Richiesta Presente</td></tr>";
            }
          }
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }           
      } else if(flag == 2) {
        Integer type = Integer.parseInt(request.getParameter("type"));
        Integer idRequest = Integer.parseInt(request.getParameter("idRequest"));
        if(type == 1) { //requestWorkingEducationAdvice1
          type = requestWorkingEducationAdvice1;
        }
        else if(type == 2) { //requestWorkingEducationAdvice2
          type = requestWorkingEducationAdvice2;
        }
        
        try {
          sql = " UPDATE request SET fk_state = ? WHERE id_request = ?; ";
          stmt = conn.prepareStatement(sql);              
          stmt.setInt(1, type);
          stmt.setInt(2, idRequest);
          if (stmt.executeUpdate() > 0) {            
            result = 1;            
            content = "Richiesta aggiornata con successo.";
          } else {
            error += " Impossibile cambiare stato alla richiesta.";
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

    /*
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
    /*
    // della request ci serve mostrare il suo id e il serial
    Connection connDb = null; // serve per aprire la connessione al db
    PreparedStatement stmt = null; // usata per permettere l'exe della query sul db (query"sql")
    ResultSet rs = null; // insieme dei dati che abbiamo ricavato dal db, lo mostreremo nella jsp
    String sql = "SELECT * FROM REQUEST;";
    try {
      ArrayList list = new ArrayList();
      connDb = DbConnection.getInstance().getConn();
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
    */
  }
}



