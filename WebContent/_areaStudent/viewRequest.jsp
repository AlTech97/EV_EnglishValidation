<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" import="controller.CheckSession" %>
<%@ page import="java.util.*,model.Request" %>
<%
	String pageName = "viewRequest.jsp";
	String pageFolder = "_areaStudent";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	if(!ck.isAllowed()){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
%>
<%    
    Collection<?> requests = null;  
    if((((String)session.getAttribute("currentSessionStudent")) !=null)){
        if(((session.getAttribute("currentSessionStudent")).equals("Student"))){
          requests=(Collection<?>)request.getAttribute("requests");
           //se i dati non esistono chiamo la servlet che riempie i campi
           if(requests == null){
        	  	response.sendRedirect("./ServletStudent");
        	  return;
           }
          }
      
     }else{    	
     	//response.sendRedirect("viewRequest.jsp");
     }     
%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/partials/head.jsp" />
	</head>

	<body onLoad="">
		<div class="page-wrapper">
		 	
		    <!-- Preloader -->
		    <div class="preloader"></div>
		 	
		    
			<jsp:include page="/partials/header.jsp">
				<jsp:param name="pageName" value="<%= pageName %>" />
				<jsp:param name="pageFolder" value="<%= pageFolder %>" />					
			</jsp:include>
	    
            
            <div class="sidebar-page-container basePage viewRequestStudent">
                <div class="auto-container">
                    <div class="row clearfix">
                        <div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="content">
                                    <div class="news-block-seven">
                                        <table id="blankTable">
                                            <thead>
                                                <tr align="center">
                                                    <th class="text-center" align="center">ID</th>
                                                    <th class="text-center" align="center">Matricola</th>
                                                    <th class="text-center" align="center">Allegati</th>
                                                    <th class="text-center" align="center">Stato</th>
                                                </tr>	
                                            </thead>
                                            <tbody id="bodyBlankTable">
                                            <%
 											 // products.clear();
												if(requests!=null){
   													if(requests.size()>0){
														 Iterator<?> it=requests.iterator();
	 													 	while(it.hasNext()){
		   													Request bean = (Request)it.next();
											%>
                                            	
                                            	<tr align="center">
                                            		<td class="text-center" align="center"><%= bean.getIdRequest()%></td>
                                            		<td class="text-center" align="center"><%= bean.getSerial() %></td>
                                            		<td class="text-center" align="center"><%= bean.getAttached() %></td>
                                            		<td class="text-center" align="center"><%= bean.getState() %></td>
                                            	</tr>  
                                            </tbody>
                                            <%				}  
	 												}
	   											}  %>
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
				$('#blankTable').DataTable( {
			        "order": [[ 0, "desc" ]],
			        "lengthMenu": [[10, -1], [10, "Tutti"]],
			        "language": {
						    "sEmptyTable":     "Nessun Ordine Presente",
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
	</body>
</html>
