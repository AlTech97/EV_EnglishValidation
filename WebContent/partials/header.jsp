<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>	

<%

	String pageName = request.getParameter("pageName");
	String pageFolder = request.getParameter("pageFolder");
	String menu = "";
	String hiddenMenu = "";
	
	if(pageFolder.equals("_areaAdmin")){
		if(pageName.equals("viewRequest.jsp")){
		  menu += "<li class=\"current\"><a href=\""+request.getContextPath()+"/"+pageFolder+"/viewRequest.jsp\">Richieste</a></li>";											  
		}
		else{
		  menu += "<li><a href=\""+request.getContextPath()+"/"+pageFolder+"/viewRequest.jsp\">Richieste</a></li>";
		}
	}
	else if(pageFolder.equals("_areaSecretary")){
		if(pageName.equals("viewRequest.jsp")){
		  menu += "<li class=\"current\"><a href=\""+request.getContextPath()+"/"+pageFolder+"/viewRequest.jsp\">Richieste</a></li>";
		}
		else{
		  menu += "<li><a href=\""+request.getContextPath()+"/"+pageFolder+"/viewRequest.jsp\">Richieste</a></li>";
		}
	}
	else if(pageFolder.equals("_areaStudent")){
		if(pageName.equals("viewRequest.jsp")){
		  menu += "<li class=\"current\"><a href=\""+request.getContextPath()+"/"+pageFolder+"/viewRequest.jsp\">Richieste</a></li>";
		}
		else{
		  menu += "<li><a href=\""+request.getContextPath()+"/"+pageFolder+"/viewRequest.jsp\">Richieste</a></li>";
		}
	}
	else if(pageFolder.equals("")){
		if(pageName.equals("blank.jsp")){
		  menu += "<li class=\"current\"><a href=\""+request.getContextPath()+"/blank.jsp\">Blank</a></li>";
		}
		else{
		  menu += "<li><a href=\""+request.getContextPath()+"/blank.jsp\">Blank</a></li>";
		}
		
		if(pageName.equals("login.jsp")){
		  menu += "<li class=\"current\"><a href=\""+request.getContextPath()+"/login.jsp\">Login</a></li>";		  
		}
		else{
		  menu += "<li><a href=\""+request.getContextPath()+"/login.jsp\">Login</a></li>";
		}
	}
	
	hiddenMenu = menu;

%>

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
		            			                    
		                    <!-- Main Menu -->
		                    <nav class="main-menu">                        
		                        <div class="navbar-collapse collapse clearfix" id="bs-example-navbar-collapse-1">
		                            <ul class="navigation clearfix">
										<%= menu %>
		                            </ul>
		                        </div>
		                    </nav>
		                    
		                    
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
		        
		        <!-- Hidden Bar Wrapper -->
		        <div class="hidden-bar-wrapper">
		            <div class="logo">
		            	<a href="#"></a>
		            </div>
		            <!-- .Side-menu -->
		            <div class="side-menu">
		            	<!--navigation-->
		                <ul class="navigation clearfix">
							<%= hiddenMenu %>
		                 </ul>
		            </div>
		            <!-- /.Side-menu -->
		            
		        </div><!-- / Hidden Bar Wrapper -->

		        
		    </section>
		    <!-- End / Hidden Bar -->			    