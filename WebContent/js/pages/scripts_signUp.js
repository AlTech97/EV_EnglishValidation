$(document).ready(function(){
	
	
	$(document).on('submit', '#signUp', function(e){
		var nomeUtente = $("#name").val();
		var cognomeUtente = $("#surname").val();
		var emailUtente = $("#email").val();
		var sessoUtente = $(".sessoUtente:checked").val();
		var passwordUtente = $("#passwordUtente").val();
		var confermaPasswordUtente = $("#confermaPasswordUtente").val();
		var continua = 1;
		
		if(nomeUtente == undefined || nomeUtente == "" || nomeUtente.length < 1){			
			showAlert(1, "Inserire un nome");
			continua *= 0;
		}
		if(cognomeUtente == undefined || cognomeUtente == "" || cognomeUtente.length < 1){			
			showAlert(1, "Inserire un cognome");
			continua *= 0;
		}
		if(emailUtente == undefined || emailUtente == "" || !checkEmail(emailUtente)){			
			showAlert(1, "Inserire un'email valida");
			continua *= 0;
		}
		if(passwordUtente == undefined || passwordUtente == "" || passwordUtente.length < 6){			
			showAlert(1, "Inserire una password valida di almeno 6 caratteri");
			continua *= 0;
		}
		if(confermaPasswordUtente == undefined || passwordUtente != confermaPasswordUtente){			
			showAlert(1, "Controllare che le due password coincidano");
			continua *= 0;
		}

		
		if(continua){
			$(".preloader").show();

			$.ajax({
				url: absolutePath+"/ServletStudent",
				type: "POST",
				dataType: 'JSON',
				async: false,
				data: {
					"nomeUtente": nomeUtente,
					"cognomeUtente": cognomeUtente,
					"emailUtente": emailUtente,
					"passwordUtente": passwordUtente,
					"sessoUtente": sessoUtente
				},
				success:function(msg){
					if(!msg.risultato){
						showAlert(1, msg.errore);
					}
					else{
						showAlert(0, msg.contenuto);
					}
				},
				error: function(msg){
					showAlert(1, "Impossibile Recuperare i dati.");
				}
			});
			
			$(".preloader").hide();
			return false;
		}
		else{			
			return false;
		}					
		return false;
	});	
	
	
	
	
	
});
