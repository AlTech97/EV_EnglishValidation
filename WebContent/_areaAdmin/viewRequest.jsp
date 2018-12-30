<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" import="controller.CheckSession" %>
<%@ page import="java.util.*,model.Request,controller.DbConnection,controller.ServletAdmin,java.sql.ResultSet,java.sql.Statement" %>

<%
	String pageName = "viewRequest.jsp";
	String pageFolder = "_areaAdmin";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/partials/head.jsp" />
	</head>

	<body onLoad="showData()">
		<div class="page-wrapper">
		 	
		    <!-- Preloader -->
		    <div class="preloader"></div>
		 	
		    
			<jsp:include page="/partials/header.jsp">
				<jsp:param name="pageName" value="<%= pageName %>" />
				<jsp:param name="pageFolder" value="<%= pageFolder %>" />					
			</jsp:include>
	    
            
            <div class="sidebar-page-container basePage viewRequestAdmin">
                <div class="auto-container">
                    <div class="row clearfix">
                        <div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="content ">
                                    <div class="news-block-seven">
                     <%
                     /*
						String outputIdRequest = ""; // output 
						String outputUser = "";
						
				        	DbConnection connDB = new DbConnection();
							if(connDB.getConn() != null) {
								try {
									Statement stmt = connDB.getConn().createStatement();
									String sql = ""; // nome e cognome, idRequest , matricola, codiceCertificato
									sql = ""
										+ "SELECT * " // ID_REQUEST, CERTFICATE_SERIAL, FK_USER, SERIAL
										+ "FROM REQUEST "									
										+ "ORDER BY ID_REQUEST DESC;";										
									ResultSet result = stmt.executeQuery(sql);
									
									// devo creare un'altra query per lo user con il relativo resultSet
									Statement stmt2 = connDB.getConn().createStatement();
									String sql2 = "";
									sql2 = ""
									+ "SELECT NAME,SURNAME  "
									+ "FROM USER "
									+ "WHERE EMAIL LIKE '%studenti%';";
									ResultSet result2 = stmt2.executeQuery(sql2);
									
									int counterIdRequest = 1;
									int counterNome = 1;
									int counterCognome = 1;
									
									if(!result.wasNull()  && !result2.wasNull() ) {										
										int rowCount = result.last() ? result.getRow() : 0;
										int rowCount2 = result2.last() ? result2.getRow() : 0;
										if(rowCount > 0  && rowCount2 > 0 ) {
										//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										//	result.beforeFirst();
											outputIdRequest += result.getInt(1); //"<fieldset><legend>Ordine</legend>";
										//	outputUser += result.getString(10);
											
											outputUser += result2.getString(1);
											outputUser += " ";
											outputUser += result2.getString(2);
										//	output += "<select id='idOrdine' class='campoForm' name='idOrdine'>";
											while(result.next() && result2.next()) {					
												// output += " ";// getElementById e mettere i risultati delle query 
											    // output += "<option value='"+result.getString("id_ordine")+"'>N."+result.getString("id_ordine")+" - "+sdf.format(result.getDate("data_ordine"))+" - &euro;"+result.getString("totale_ordine")+"</option>";
											//	outputIdRequest += result.getInt(counterIdRequest);
											//	counterIdRequest++;
												
											//	outputUser += result2.getString(counterNome);
											//	outputUser += " ";
											//	outputUser += result2.getString(counterCognome);
											//	counterNome++;
											//	counterCognome++;
											}
										//	counterIdRequest++;
										//	counterNome++;
										//	counterCognome++;
										//	output += "</select>";
										//	output += "</fieldset>";
										}
										else {
											// output += "<input type='hidden' id='idOrdine' name='idOrdine' value='0' />"; vedere ...
											outputIdRequest = " rows are < 0";
										}											
									}									
									connDB.getConn().close();
								}
								catch(Exception e) {
									outputIdRequest = e.getMessage();
								}	
							}
							else {
								outputIdRequest = "Errore di connessione, riprovare"; // connDB.getError(); Nella connection non ci sta sto metodo
							}				        				        				        
					//	}
					//	else{
					//		output += "<input type='hidden' id='idOrdine' name='idOrdine' value='0' />";
					//	}
					*/
					%>
				 	
                                        <table id="adminTable">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">ID</th>
                                                    <th class="text-center">Matricola</th>
                                                    <th class="text-center">Nome</th>                                                    
                                                    <th class="text-center">Cognome</th>
                                                    <th class="text-center">A.A.</th>                                                    
                                                    <th class="text-center">Cod. Cert.</th>
                                                    <th class="text-center">Liv. Cert.</th>
                                                    <th class="text-center">Data Ril.</th>
                                                    <th class="text-center">Data Scad.</th>                                                    
                                                    <th class="text-center">CFU Ric.</th>
                                                    <th class="text-center">CFU Conv.</th>
                                                    <th class="text-center">Stato</th>
                                                    <th class="text-center">Ente</th>
                                                    <th class="text-center">Azioni</th>
                                                </tr>	
                                            </thead>
                                            <tbody id="bodyAdminTable">

                                            </tbody>
                                        </table>					
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="/partials/footer.jsp" />
		</div>
		<!--End pagewrapper-->		
		
		<jsp:include page="/partials/includes.jsp" />		
		<script>
			jQuery(document).ready(function($){
				$('#adminTable').DataTable( {
			        "order": [[ 0, "desc" ]],
			        "lengthMenu": [[10, -1], [10, "Tutti"]],
			        "autoWidth": false,
			        "bAutoWidth": false,
			        "language": {
						    "sEmptyTable":     "Nessuna richiesta Presente",
						    "sInfo":           "Vista da _START_ a _END_ di _TOTAL_ elementi",
						    "sInfoEmpty":      "Vista da 0 a 0 di 0 elementi",
						    "sInfoFiltered":   "(filtrati da _MAX_ elementi totali)",
						    "sInfoPostFix":    "",
						    "sInfoThousands":  ".",
						    "sLengthMenu":     "Visualizza _MENU_ elementi",
						    "sLoadingRecords": "Caricamento...",
						    "sProcessing":     "Elaborazione...",
						    "sSearch":         "Cerca:",
						    "sZeroRecords":    "La ricerca non ha portato alcun risultato.",
						    "oPaginate": {
						        "sFirst":      "Inizio",
						        "sPrevious":   '<i class="fa fa-caret-left"></i>',
						        "sNext":       '<i class="fa fa-caret-right"></i>',
						        "sLast":       "Fine"
						    },
						    "oAria": {
						        "sSortAscending":  ": attiva per ordinare la colonna in ordine crescente",
						        "sSortDescending": ": attiva per ordinare la colonna in ordine decrescente"
						    }
			        }        
			    } );
			});
		</script>
		<script src="<%= request.getContextPath() %>/js/pages/scripts_viewRequestAdmin.js"></script>		
	</body>
</html>
