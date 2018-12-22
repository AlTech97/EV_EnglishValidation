var absolutePath = "/EnglishValidation";

function showAlert(flag, descrizione){
	toastr.options = {
	  "closeButton": true,
	  "debug": false,
	  "newestOnTop": false,
	  "progressBar": false,
	  "positionClass": "toast-bottom-right",
	  "preventDuplicates": false,
	  "onclick": null,
	  "showDuration": "300",
	  "hideDuration": "1000",
	  "timeOut": "5000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	}
	
	if(flag == 0){ //Tutto OK
		toastr.success(descrizione, "Operazione Effettuata");
	}
	else if(flag == 1){ //Errore		
		toastr.error(descrizione, "Operazione Fallita");
	}			
}