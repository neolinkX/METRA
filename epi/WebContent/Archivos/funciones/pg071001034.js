function fValidaTodo() {
	var form = document.forms[0];

	if (form.hdBoton.value == 'Nuevo') {
		return true;
	}
	if (form.hdBoton.value == 'Imprimir') {
		fImprimir();
	}

	if (form.hdBoton.value == 'Borrar') {
		alert(" - Acción no permitida ")
		return false;
	}
	if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
		cVMsg = "";

		if (form.iCveMensajeria.value < 1) {
			cVMsg += "\n - Seleccione un Mensaje.";
		}

		if (form.cDscMensajeria.value == '')
			cVMsg += "\n - Escriba el Mensaje.";

		if (form.SitioOficial.value == '')
			cVMsg += "\n - Escriba la URL.";

		if (cVMsg != "") {
			alert('Datos no Válidos: \n' + cVMsg);
			return false;
		}

		if (!isValidUrl(form.SitioOficial)) {
			alert('URL no Valida: \n' + cVMsg);
			return false;
		}

	}
	if (form.hdBoton.value != 'Cancelar') {
		cVMsg = '';
		if (form.cPropiedad)
			cVMsg = cVMsg + fSinValor(form.cPropiedad, 1, 'Propiedad', true);
		if (cVMsg != '') {
			alert("Datos no Válidos: \n" + cVMsg);
			return false;
		}
	}
	return true;
}

function isValidUrl(url) {
	var pattern = /^(http|https|ftp)\:\/\/[a-z0-9\.-]+\.[a-z]{2,4}/gi;
	if (url.value.match(pattern)) {
		return true;
	} else {
		return false;
	}

}

function isValidLength(Str) {
	if (Str.value.length >= 301) {
		alert("Se permite un maximo de 300 caracteres");
		Str.value = Str.value.substring(0, 300);
	}
}