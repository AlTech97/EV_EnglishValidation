$( document ).ready(function() {		

	$( document ).on( "submit", "#forwardButton", function() {
		var idUser = '';
		var serial = '';
		var nome = '';
		var cognome = '';
		var codCertificato = '';
		var forwardButton = '<input type="checkbox" name="" value="">';
		var footer = '';
		
		if(idUser != undefined && idUser.length > 0 ){
			$(".preloader").show();
			
			$.ajax({
				url: absolutePath+"/ServletSecretary",
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
		else{
			showAlert(1, "Errore parametri.");
		}
	});
});

	
function showData() {
	$(".preloader").show();

	$.ajax({
		url: absolutePath+"/ServletSecretary",
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
				$("#bodySegretaryBody").html(msg.content);				
			}
		},
		error: function(msg){
			showAlert(1, "Impossibile Recuperare i dati.");
		}
	});
	
	$(".preloader").hide();		
}


	