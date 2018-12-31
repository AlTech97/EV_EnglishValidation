$( document ).ready(function() {		

	$( document ).on( "click", ".toWorkingEducationAdvice", function() {
		var type = $(this).data("type");
		var idRequest = $(this).data("idrequest");
		
		if(type != undefined && (type == 1 || type == 2) && idRequest != undefined && idRequest > 0){
			if(confirm("Conferma il cambio di stato della richiesta?")){
				$(".preloader").show();
				
				$.ajax({
					url: absolutePath+"/ServletAdmin",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"type": type,
						"idRequest": idRequest,
						"flag": 2
					},
					success:function(msg){
						if(!msg.result){
							showAlert(1, msg.error);
						}
						else{
							showAlert(0, msg.content);
							setTimeout(function(){							
								showData();
							},2000);					
						}
					},
					error: function(msg){
						showAlert(1, "Impossibile Recuperare i dati.");
					}
				});
				
				$(".preloader").hide();				
			}			
		}
		else{
			showAlert(1, "Errore parametri.");
		}
	});
	
	$( document ).on( "click", ".verifyCertificate", function() {
		var mail = $(this).data("mail");
		var certSerial = $(this).data("certserial");		
        var subject= "Certification Validation";
        
        if(mail != undefined && certSerial != undefined && mail.length > 0 && certSerial.length > 0){
	        var body = "Dear, I would know if this certificate is valid: Certificate Serial "+certSerial+".\r\n\rThank you";
	        var uri = "mailto:"+mail+"?subject=";
	        uri += encodeURIComponent(subject);
	        uri += "&body=";
	        uri += encodeURIComponent(body);
	        window.open(uri);
	        return false;
        }        
	});
		
	
});

function showData() {
	$(".preloader").show();

	$.ajax({
		url: absolutePath+"/ServletAdmin",
		type: "POST",
		dataType: 'JSON',
		async: false,
		data: {
			"flag": 1
		},
		success:function(msg){
			if(!msg.result){
				showAlert(1, msg.error);
			}
			else{
				$("#bodyAdminTable").html(msg.content);				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$(".preloader").hide();		
}