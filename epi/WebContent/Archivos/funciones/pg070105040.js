function fValidaTodo2() {
	var form = document.forms[0];
	cVMsg = '';
	if (form.hdBoton.value == 'Imprimir')
		fImprimir();

	/* Validacion de Servicios */
/*
	if (form.hdServGABOFTA.value == 0) {
		cVMsg = cVMsg + "\n 	- Gabinete de Oftalmología. \n";
	}*/
	
	
	if (form.hdServEVOFTA.value == 1) {
		cVMsg = cVMsg + "\n 	- Evaluación Oftalmológica. \n";
	}


	if (cVMsg != '') {
		alert("No podrá dictaminar el examen hasta que se capturen los siguientes servicios:\n"
				+ cVMsg);
		return false;
	}
	return true;
}

function Genera_Doc() {
	form = document.forms[0];
	form.target = "_self";
	// form.target="_blank";
	form.hdBoton.value = 'Imprime Documentacion';
	form.submit();
}

function fValidaTodo() {
	var form = document.forms[0];
	cVMsg = '';
	if (form.hdBoton.value == 'Imprimir') {
		fImprimir();
	}

	// / VALIDANDO SELECCION DE APTITUDES
	if (form.hdBoton.value == 'Guardar') {
		form.hdBoton.value == '';
		var Cad = form.hdDictamenes.value;
		var ArrCad = Cad.split(",");
		var Tam = ArrCad.length - 1;
		var select = 0;
		var NoApto = 0;
		lRadioCheck = false;
		for ( var Cont = 0; Cont < Tam; Cont++) {
			// alert(ArrCad[Cont]);
			// alert("value
			// ="+document.forms[0]['lDictamen'+ArrCad[Cont]].value);
			// alert("length
			// ="+document.forms[0]['lDictamen'+ArrCad[Cont]].length);
			if (document.forms[0]['lDictamen' + ArrCad[Cont]].value != '0') {
				// alert("Definido");
				for ( var i = 0; i < document.forms[0]['lDictamen'
						+ ArrCad[Cont]].length; i++) {
					// alert(i+"="+document.forms[0]['lDictamen'+ArrCad[Cont]].value);
					if (document.forms[0]['lDictamen' + ArrCad[Cont]][i].checked) {
						select++;
						// alert("seleccionado"+ArrCad[Cont]);
						if (document.forms[0]['lDictamen' + ArrCad[Cont]][i].value == '0') {
							// alert("1seleccionado No apto = "+ArrCad[Cont]);
							NoApto++;
						}
						break;
					}
				}
			} else {
				// alert("op2");
				if (document.forms[0]['lDictamen' + ArrCad[Cont]].value == '0') {
					// alert("seleccionado No apto = "+ArrCad[Cont]);
					NoApto++;
					select++;
				}
				// select++;
			}

		}
		// alert("NoApto="+NoApto);
		// alert("select="+select);
		// alert("Tam="+Tam);

		if (select < Tam) {
			cVMsg = cVMsg + "      -Favor de Dictaminar! \n";
		}

		if (cVMsg != '') {
			alert("Datos no Válidos: \n" + cVMsg);
			return false;
		} else {
			// Genera_Doc();
		}
	}
	// //// VALIDACION DE SELECCION DE APTITUD DE CATEGORIAS ///////

	return true;
}
