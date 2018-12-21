<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>	
		        <!-- Hidden Bar Wrapper -->
		        <div class="hidden-bar-wrapper">
		            <div class="logo">
		            	<a href="#"></a>
		            </div>
		            <!-- .Side-menu -->
		            <div class="side-menu">
		            	<!--navigation-->
		                <ul class="navigation clearfix">
		                	<li><a href="#">Blank</a></li>
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
		            <!-- /.Side-menu -->
		            
		        </div><!-- / Hidden Bar Wrapper -->