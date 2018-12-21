<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/partials/head.jsp" />
	</head>

	<body onLoad="getBlank()">
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
		            			                    
		                    <jsp:include page="/partials/mainMenu.jsp" />
		                    
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
		        
		        <jsp:include page="/partials/hiddenMenu.jsp" />
		        
		    </section>
		    <!-- End / Hidden Bar -->		    
            
            <div class="sidebar-page-container">
                <div class="auto-container">
                    <div class="row clearfix">
                        <div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="content">
                                    <div class="sec-title">
                                        <h2>Blank</h2>
                                    </div>
                                    <div class="news-block-seven">

                                        <table id="blankTable">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Elimina</th>
                                                </tr>	
                                            </thead>
                                            <tbody id="bodyBlankTable">
                                                
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
        <script src="/Progetto_IS/js/pages/scripts_blank.js"></script>		
	</body>
</html>
