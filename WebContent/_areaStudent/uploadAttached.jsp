<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="controller.CheckSession, model.SystemAttribute, controller.Utils"%>

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

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.5/jspdf.debug.js"></script>

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
								<h2>
									Richiesta N.<%= idRequest %>
									</h1>
									<h2>
										Trascina o premi sull'apposito riquadro per caricare un file
										</h1>
										<div action='<%= request.getContextPath() + "/Uploader" %>'
											class='dropzoneUploader'></div>

										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-submit"
												id='aggiungiAllegati'>Concludi</button>
										</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<button type="button" class="btn btn-primary generatePDF"
				onclick="createPdf()">Genera PDF</button>

		</div>
		<jsp:include page="/partials/footer.jsp" />
	</div>
	<!--End pagewrapper-->

	<jsp:include page="/partials/includes.jsp" />

	<script>
		function createPdf(){
			
			var doc = new jsPDF("landscape","pt","a2")
			
			doc.text('La/Il sottoscritta/o ___________________________________________________________', 10, 20)
			doc.text("immatricolata/o nell'aa _________________al corso di ",10,40)
	/*		doc.text("() Laurea Triennale",100,100)
			doc.text("() Laurea Magistrale",100,100)
			doc.text("In Informatica matricola n° ____________________________________",1000,1000)
			doc.text("																					CHIEDE",100,100)
			doc.text("Che venga valutata la certificazione allegata ",1000,1000)
			doc.text("ENTE CERTIFICATORE :",1000,1000)
			doc.text("GRADE",100,100)
			doc.text("LIVELLO CEFR ",1000,1000)
			doc.text("ai fini del riconoscimento di N° ___ CFU relativi alla prova di Lingua Inglese previsti nel proprio piano di studi",1000,1000)
			doc.text("Si allega certificazione",1000,1000)
			doc.text("Fisciano, _____________																	Firma studente _________________________________",2000,2000)
			doc.text("ATTENZIONE: Il presente modulo va compilato al computer, va quindi stampato, firmato, scannerizzato salvato con il nome che segue questa regola:",2000,2000)
					doc.text("Matricola_CognomeNome_Inglese. Il certificato di lingua Inglese va scannerizzato e salvato con il nome del file che segue questa regola",2000,2000)
					doc.text("Matricola_Cognome_Certificato. I due file vanno inviati a carrierestudenti.di@unisa.iti. L'oggetto della mail deve contenere <Matricola> - Riconoscimento",2000,2000)
					doc.text("Lingua Inglese. Per esempio 0512105081 - Riconoscimento Lingua Inglese. Se viene creato un unico file zip (contenente domanda e certificato) anche questo",2000,2000)
					doc.text("file deve seguire la regola Matricola_CognomeNome_Inglese.",2000,2000)
					doc.text("Gli studenti della triennale immatricolati fino all'aa 2016/17 necessitano di una certificazione di livello almeno A2 per il riconoscimento di 3 CFU. Gli",2000,2000)
					doc.text("immatricolati dall'aa 2017/18 necessitano di una certificazione di livello almeno B1 per il riconoscimento di 6 CFU. Gli studenti della Laurea Magistrale",2000,2000)
					doc.text("necessitano di una certificazione di livello almeno B2 per il riconoscimento di 6 CFU di lingua lnglese. La certificazione deve in ogni caso essere stata rilasciata",2000,2000)
					doc.text("da un ente certificatore riconosciuto dal MIUR.",2000,2000)
					*/
			doc.text('')
			doc.save('a4.pdf')
		}
		</script>


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
	<script
		src="<%= request.getContextPath() %>/js/pages/scripts_uploadAttached.js"></script>

</body>
</html>
