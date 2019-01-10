$( document ).ready(function() {	
	
	$( document ).on( "click", ".toWorkingAdmin", function() {
		var idRequest = $(this).data("idrequest");		
		if(idRequest != undefined && idRequest > 0){
			$(".preloader").show();
			
			$.ajax({
				url: absolutePath+"/ServletSecretary",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {						
					"flag": 3,
					"idRequest": idRequest
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
	
	$( document ).on( "click", ".saveCfu", function() {
		var idRequest = $(this).data("idrequest");
		var cfu = $(this).closest("td").children(".cfuToValidate").val();	
		if(idRequest != undefined && idRequest > 0 && cfu != undefined && (cfu > 0 || cfu < 13)){
			$(".preloader").show();
			
			$.ajax({
				url: absolutePath+"/ServletSecretary",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {						
					"flag": 2,
					"idRequest": idRequest,
					"cfu": cfu
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


	