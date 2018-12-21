<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>	

		                    <!-- Main Menu -->
		                    <nav class="main-menu">                        
		                        <div class="navbar-collapse collapse clearfix" id="bs-example-navbar-collapse-1">
		                            <ul class="navigation clearfix">
										<%
											String pageName = request.getParameter("pageName");
											String pageFolder = request.getParameter("pageFolder");
											if(pageName.equals("blank.jsp")){
											  %>
												<li class="current"><a href="/EnglishValidation/blank.jsp">Blank</a></li>											  
											  <%
											}
										%>
		                            </ul>
		                        </div>
		                    </nav>