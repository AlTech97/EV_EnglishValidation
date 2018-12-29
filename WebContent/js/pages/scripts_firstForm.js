$(document).ready(function(){
	
	
	$(document).on('submit', '#firstForm', function(e){
		var year = $("#immatricolazione").val();
		var graduation = $(".optradio:checked").val();
		var serial = $("#matricola").val();
		var ente = $("#ente").val();
		var expiryDate = $("#datarilascio").val();
		var certificateSerial = $("#seriale").val();
		var level = $("#lvlcefr").val();;
		var requestedCfu = $("#cfu").val();

		if(year != undefined && graduation != undefined && number != undefined && ente != undefined && 
				expiryDate != undefined && serial != undefined && level != undefined && requestedCfu != undefined){
				$(".preloader").show();

				$.ajax({
					url: absolutePath+"/ServletStudent",
					type: "POST",
					dataType: 'JSON',
					async: false,
					data: {
						"year": year,
						"graduation": graduation,
						"serial": serial,
						"ente": ente,
						"expiryDate": expiryDate,
						"number": number,
						"level": level,
						"requestedCfu": requestedCfu,
						"flag": 2
					},
					success:function(msg){
						if(!msg.risultato){
							showAlert(1, msg.errore);
						}
						else{
							showAlert(0, msg.contenuto);
							
							setTimeout(function (){
								window.location.href = msg.redirect;
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
			showAlert(1, "Errore prelevamento campi.");
		}

		return false;
	});	
	
	
	
	
	
});
