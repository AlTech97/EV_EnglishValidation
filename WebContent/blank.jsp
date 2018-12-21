<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<%! String pageName = "blank.jsp"; %>
<%! String pageFolder = ""; %>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/partials/head.jsp" />
	</head>

	<body onLoad="">
		<div class="page-wrapper">
		 	
		    <!-- Preloader -->
		    <div class="preloader"></div>
		 	
		    <!-- Main Header -->
		    <header class="main-header">    	
		    	<!--Header-Upper-->
		        <div class="header-upper">
		        	<div class="auto-container">
		            	<div class="clearfix">
		                	
		                	<div class="logo-outer">
		                    	<div class="logo">
		                    		<a href="#"></a>
		                    	</div>
		                    </div>                    
		                </div>
		            </div>
		        </div>
		        <!--End Header Upper-->
		        
		        <!--Header Lower-->
		        <div class="header-lower">
		        	<div class="auto-container">
		            	<div class="nav-outer clearfix">
		            			                    
							<jsp:include page="/partials/mainMenu.jsp">
								<jsp:param name="pageName" value="<%= pageName %>" />
								<jsp:param name="pageFolder" value="<%= pageFolder %>" />
							</jsp:include>
		                    
		                    
		                     <!-- Hidden Nav Toggler -->
		                     <div class="nav-toggler">
		                         <button class="hidden-bar-opener"><span class="icon qb-menu1"></span></button>
		                     </div>
		                    
		                </div>
		            </div>
		        </div>
		        <!--End Header Lower-->
		        	
		    </header>
		    <!--End Header Style Two -->
		    
		    <!-- Hidden Navigation Bar -->
		    <section class="hidden-bar left-align">
		        
		        <div class="hidden-bar-closer">
		            <button><span class="qb-close-button"></span></button>
		        </div>
		        
				<jsp:include page="/partials/hiddenMenu.jsp">
					<jsp:param name="pageName" value="<%= pageName %>" />
					<jsp:param name="pageFolder" value="<%= pageFolder %>" />					
				</jsp:include>

		        
		    </section>
		    <!-- End / Hidden Bar -->		    
            
            <div class="sidebar-page-container basePage viewRequestBlank"> <!-- viewRequestBlank da sostituire con quello giusto -->
                <div class="auto-container">
                    <div class="row clearfix">
                        <div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="content">
                                    <div class="news-block-seven">
                                        <table id="blankTable">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">ID</th>
                                                    <th class="text-center">Campo</th>
                                                    <th class="text-center">Campo</th>
                                                    <th class="text-center">Azioni</th>
                                                </tr>	
                                            </thead>
                                            <tbody id="bodyBlankTable">
                                            	<tr>
                                            		<td class="text-center">Ciao</td>
                                            		<td class="text-center">Ciao</td>
                                            		<td class="text-center">Ciao</td>
                                            		<td class="text-center">Ciao</td>
                                            	</tr>  
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
