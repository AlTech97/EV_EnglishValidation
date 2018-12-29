<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" import="controller.CheckSession, model.SystemAttribute, controller.Utils" %>

<%
	String pageName = "uploadAttached.jsp";
	String pageFolder = "_areaStudent";
	CheckSession ck = new CheckSession(pageFolder, pageName, request.getSession());
	Integer idRequest = (Integer) request.getSession().getAttribute("idRequest");
	if(idRequest == null){
	  idRequest = new Utils().getLastUserRequestPartiallyCompleted(request.getSession());
	  request.getSession().setAttribute("idRequest", idRequest);
	}
	Integer requestNumberMaxUpload = Integer.parseInt(new SystemAttribute().getValueByKey("request-number-max-upload"));	
	String requestAllowedExtensionUpload = new SystemAttribute().getValueByKey("request-allowed-extension-upload");
	Integer requestState = new Utils().getRequestState(idRequest);
	Integer shouldState = Integer.parseInt(new SystemAttribute().getValueByKey("request-partially-completed"));
	if(!ck.isAllowed() || idRequest == 0 || requestState != shouldState){
	  response.sendRedirect(request.getContextPath()+ck.getUrlRedirect());  
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/partials/head.jsp" />
	</head>

	<body>
		<div class="page-wrapper">
		 	
		    <!-- Preloader -->
		    <div class="preloader"></div>
		 	
		    
			<jsp:include page="/partials/header.jsp">
				<jsp:param name="pageName" value="<%= pageName %>" />
				<jsp:param name="pageFolder" value="<%= pageFolder %>" />					
			</jsp:include>
	    
            
            <div class="sidebar-page-container basePage uploadAttachedPage">
                <div class="auto-container">
                    <div class="row clearfix">
                        <div class="content-side col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="content">
	                           <div class="news-block-seven">
	                           		<h2> Richiesta N.<%= idRequest %> </h1>
	                           		<h2> Trascina o premi sull'apposito riquadro per caricare un file </h1>
							        	<div action='<%= request.getContextPath() + "/Uploader" %>' class='dropzoneUploader'></div>
						        							        	
								    <div class="form-group">	
								    	<button type="submit" class="btn btn-primary btn-submit" id='aggiungiAllegati'>Concludi</button>	
								    </div>
								    
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
			$( document ).ready(function() {	
				$(".dropzoneUploader").dropzone({
					  maxFiles: <%= requestNumberMaxUpload %>,
					  acceptedFiles: "<%= requestAllowedExtensionUpload %>",
					  accept: function(file, done){
					    done();
					  },
					  init: function() {		
					      this.on("maxfilesexceeded", function(file, errorMessage){
					    	  this.removeFile(file);
					    	  showAlert(1, errorMessage);		    	  
					      });
	                      
					      this.on("error", function(file, errorMessage) {
					    	  this.removeFile(file);
					    	  showAlert(1, errorMessage);
	                      });
	                    
						  this.on("success", function(file, response) {
							  var msg = jQuery.parseJSON(response);
						  	  if(!msg.result){
						  		showAlert(1, msg.error);
						  	  }	            		    
						  	  else{
						  		file.previewElement.querySelector("[data-dz-name]").innerHTML = msg.content;
						  	  }
						  });
					  }		  						
				});	
				
			});
		</script>
		<script src="<%= request.getContextPath() %>/js/filesystem_dropzone.js"></script>
		<script src="<%= request.getContextPath() %>/js/pages/scripts_uploadAttached.js"></script>		
			
	</body>
</html>
