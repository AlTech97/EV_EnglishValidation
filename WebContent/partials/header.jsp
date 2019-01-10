<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1" import="controller.CheckSession , controller.Utils"%>

<%
	String pageName = request.getParameter("pageName");
	String pageFolder = request.getParameter("pageFolder");
	String menu = "";
	String hiddenMenu = "";

	if (pageFolder.equals("_areaAdmin")) { //se stiamo in una pagina dell'area admin
		if (pageName.equals("viewRequest.jsp")) {
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/viewRequest.jsp\">Richieste</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Logout</a></li>";
		} 
	} else if (pageFolder.equals("_areaSecretary")) { //se stiamo in una pagina dell'area segreteria
		if (pageName.equals("viewRequest.jsp")) {
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/viewRequest.jsp\">Richieste</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Logout</a></li>";
		}
	} else if (pageFolder.equals("_areaStudent")) { //se stiamo in una pagina dell'area studente
		
		if (pageName.equals("viewRequest.jsp")) { //se stiamo in viewRequest
				menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "/viewRequest.jsp\">Richieste</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/" + pageFolder
						+ "/firstForm.jsp\">First Form</a></li>";
				menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Logout</a></li>";
		}
		if (pageName.equals("firstForm.jsp")) {
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/firstForm.jsp\">First Form</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Logout</a></li>";
		}
		if (pageName.equals("uploadAttached.jsp")) {
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath() + "/" + pageFolder
					+ "/uploadAttached.jsp\">Upload Attached</a></li>";
			menu += "<li><a href=\"" + request.getContextPath() + "/logout.jsp\">Logout</a></li>";
		}
	} else if (pageFolder.equals("")) { //se non siamo (o siamo) loggati

		if (pageName.equals("login.jsp")) {
			menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
					+ "/login.jsp\">Login</a></li>";
		} else {
			CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
			if (pageName.equals("logout.jsp") && ck.isAllowed()) { 
				menu += "<li class=\"current\"><a href=\"" + request.getContextPath()
						+ "/logout.jsp\">Logout</a></li>";
				menu += "<li><a href=\"" + request.getContextPath()
				+ "/login.jsp\">Login</a></li>";
			}
		}
	}

	hiddenMenu = menu;
%>
<!-- Modal -->
<div id="defaultModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<form action="#">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
				</div>
			</div>
		</form>

	</div>
</div>
<!-- Main Header -->
<header class="main-header">
	<!--Header-Upper-->
	<div class="header-upper">
		<div class="auto-container">
			<div class="clearfix">

				<div class="logo-outer">
					<div class="logo">
						<a href="<%=request.getContextPath()%>/blank.jsp"></a>
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
					<div class="navbar-collapse collapse clearfix"
						id="bs-example-navbar-collapse-1">
						<ul class="navigation clearfix">
							<%=menu%>
						</ul>
					</div>
				</nav>


				<!-- Hidden Nav Toggler -->
				<div class="nav-toggler">
					<button class="hidden-bar-opener">
						<span class="icon qb-menu1"></span>
					</button>
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
		<button>
			<span class="qb-close-button"></span>
		</button>
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
				<%=hiddenMenu%>
			</ul>
		</div>
		<!-- /.Side-menu -->

	</div>
	<!-- / Hidden Bar Wrapper -->


</section>
<!-- End / Hidden Bar -->
