/**
 * 
 */

var GRALiCveExp, GRALiNumExm, ENCUESTA, ISOPEN, VALIDO = false;
var MEDICOVALIDADO = false;
var PACIENTEVALIDADO = false;

function procesoDeValidacion(iCveExp, iNumExm) {
	alert('valida '+MEDICOVALIDADO+' '+PACIENTEVALIDADO);
	if (MEDICOVALIDADO && PACIENTEVALIDADO) {
		var form = document.forms[2];
		form.iFolioEs.focus();
		siguientePagina();
	}
	if (MEDICOVALIDADO && !PACIENTEVALIDADO) {
		ENCUESTA = document.forms[2].iFolioEs.value;
		document.forms[2].iFolioEs.value = '';
		var form = document.forms[2];
		form.iCveExpediente.focus();
		GRALiCveExp = iCveExp;
		GRALiNumExm = iNumExm;
		openpopupValidaPaciente();
		return false;
	}
	if (PACIENTEVALIDADO && !MEDICOVALIDADO) {
		ENCUESTA = document.forms[2].iFolioEs.value;
		document.forms[2].iFolioEs.value = '';
		var form = document.forms[2];
		form.iCveExpediente.focus();
		GRALiCveExp = iCveExp;
		GRALiNumExm = iNumExm;
		openpopupValidaMedico();
		return false;
	}
	if (!PACIENTEVALIDADO && !MEDICOVALIDADO) {
		ENCUESTA = document.forms[2].iFolioEs.value;
		document.forms[2].iFolioEs.value = '';
		var form = document.forms[2];
		//form.iCveExpediente.focus();
		GRALiCveExp = iCveExp;
		GRALiNumExm = iNumExm;
		openpopupValidaMedico();
		return false;
	}
}

function closepopup() {
	ISOPEN = false;
}

function openpopupValidaMedico() {
	Loading();
	if (ISOPEN) {
		alert('Tiene una validacion biometrica de medico en ejecución');
	} else {
		//if(confirm('Para capturar la encuesta de salida se solicitara la validacion biometrica del medico y el paciente, \n Desea realizar la validacion biometrica?')){
		var popurl = "validaBiometricaValeDeServicios2.jsp?idPersona=1&iNumExm="
				+ GRALiNumExm + "&iCveExp=" + GRALiCveExp;
		ISOPEN = true;
		window.open(popurl, "", "width=1,height=1,status,menubar,");
		//}else{
		//form.iCveExpediente.focus();
		//return false;
		//}
	}
}
function respuestaopenpopupValidaMedico(esValido) {
	if (esValido) {
		MEDICOVALIDADO = true;
		ISOPEN = false;
		procesoDeValidacion(document.forms[2].iCveExpediente.value,
				document.forms[2].iNumExamen.value);
		//openpopupValidaPaciente();

	}
}
function openpopupValidaPaciente() {
	Loading();
	if (ISOPEN) {
		alert('Tiene una validacion biometrica del paciente en ejecución');
	} else {
		var popurl = "validaBiometricaValeDeServicios2.jsp?idPersona=2&iNumExm="
				+ GRALiNumExm + "&iCveExp=" + GRALiCveExp;
		ISOPEN = true;
		window.open(popurl, "", "width=600,height=200,status,menubar,");

	}
}
function respuestaopenpopupValidaPaciente(esValido) {
	if (esValido) {
		PACIENTEVALIDADO = true;
		ISOPEN = false;
		procesoDeValidacion(document.forms[2].iCveExpediente.value,
				document.forms[2].iNumExamen.value);
		//siguientePagina();
	}
}

function siguientePagina() {
	document.forms[2].iFolioEs.value = ENCUESTA;
	VALIDO = true;
}





var ld=(document.all);  
var ns4=document.layers; 
var ns6=document.getElementById&&!document.all; 
var ie4=document.all;  
if (ns4) 	ld=document.loading; 
else if (ns6) 	ld=document.getElementById("loading").style; 
else if (ie4) 	ld=document.all.loading.style;  
function Loaded() { 
	if(ns4){ld.visibility="collapse";} 
else if (ns6||ie4) ld.display="none"; } 
function Loading() { 
	if(ns4){ld.visibility="visible";} 
else if (ns6||ie4) ld.display="block"; } 
Loaded();