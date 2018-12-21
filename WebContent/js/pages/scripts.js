//validaCF('CODICEFISCALE');
function validaCF(cf){
	var validi, i, s, set1, set2, setpari, setdisp;
	if( cf == '' )  return '';
	cf = cf.toUpperCase();
	if( cf.length != 16 )
		return false;
	validi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	for( i = 0; i < 16; i++ ){
		if( validi.indexOf( cf.charAt(i) ) == -1 )
			return false;
	}
	set1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	set2 = "ABCDEFGHIJABCDEFGHIJKLMNOPQRSTUVWXYZ";
	setpari = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	setdisp = "BAKPLCQDREVOSFTGUHMINJWZYX";
	s = 0;
	for( i = 1; i <= 13; i += 2 )
		s += setpari.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
	for( i = 0; i <= 14; i += 2 )
		s += setdisp.indexOf( set2.charAt( set1.indexOf( cf.charAt(i) )));
	if( s%26 != cf.charCodeAt(15)-'A'.charCodeAt(0) )
		return false;
	return true;
}

//checkEmail('prova@email.it');
function checkEmail(email){
	var $email = email;
	var re = /[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}/igm;
	if ($email == '' || !re.test($email)){
		return false;
	}
	else{
		return true;
	}
}

function resizeContent(){
	$("#content").css("min-height", ($(window).height() - $("#header").height() - $("#footer").height())+"px");	
}

function showAlert(flag, descrizione){
	if(flag == 0){ //Tutto OK
		console.log(descrizione);
	}
	else if(flag == 1){ //Errore		
		console.log(descrizione);
	}	
}