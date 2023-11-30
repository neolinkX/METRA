function llenaSLT(opc, val1, val2, val3, objDes) {
	if (objDes != '') {
		if (objDes.name != '') {
			objDes.length = 1;
			objDes[0].text = "Cargando Datos...";
			window.parent.FRMOtroPanel.location = "pg070106041P.jsp?opc=" + opc
					+ "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3
					+ "&objDes=" + objDes.name + "&hdAccion="
					+ document.forms[0].action;
		} else {
			alert("Debe seleccionar un dato valido!");
			objDes.length = 0;
			objDes.length = 1;
			return false;
		}
	} else {
		window.parent.FRMOtroPanel.location = "pg070106041P.jsp?opc=" + opc
				+ "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3
				+ "&objDes=" + objDes.name + "&hdAccion="
				+ document.forms[0].action;
	}
}

function fSelectedPer(iCvePersonal, iCveExpediente, iNumExamen) {
	var form = document.forms[0];
	form.iCvePersonal.value = iCvePersonal;
	form.lMostrarDatos.value = true;
	form.target = "_self";
	form.submit();
}

function fAgregar() {
	var form = document.forms[0];
	form.lAgregar.value = true;
	form.lAccion.value = 'Agregar';
	form.target = "_self";
	form.submit();
}

function fQuitar(iCveMdoTrans, iCveCategoria, iCveCatalogoNoAp) {
	var form = document.forms[0];
	if (confirm('¿ Desea Bajar el Personal en el Modo de Transporte y Categoria del Catálogo de No Aptos ?')) {
		form.hdiCveMdoTrans.value = iCveMdoTrans;
		form.hdiCveCategoria.value = iCveCategoria;
		form.hdiCveCatalogoNoAp.value = iCveCatalogoNoAp;
		form.lGuardar.value = true;
		form.lQuitar.value = true;
		form.lAccion.value = 'Quitar';
		form.target = "_self";
		form.submit();
	}
}

function fMotivos(iCveUniMed, iCveMdoTrans, iCveCategoria, iCveCatalogoNoAp) {
	var form = document.forms[0];
	form.hdiCveUniMed.value = iCveUniMed;
	form.hdiCveMdoTrans.value = iCveMdoTrans;
	form.hdiCveCategoria.value = iCveCategoria;
	form.hdiCveCatalogoNoAp.value = iCveCatalogoNoAp;
	form.lAgregar.value = "";
	form.lAccion.value = 'Motivos';
	form.target = "_self";
	form.submit();
}

function fAgregarMot(iCveUniMed, iCveMdoTrans, iCveCategoria, iCveCatalogoNoAp) {
	var form = document.forms[0];
	msg = "";

	if (form.iCveMotivoNoAp)
		if (form.iCveMotivoNoAp.value < 1)
			msg += " - Seleccione un Motivo de No Aptitud.\n"

	if (form.iCveRubroNoAp)
		if (form.iCveRubroNoAp.value < 1)
			msg += " - Seleccione un Rubro de No Aptitud.\n"

	if (msg != "") {
		alert("Verifique lo Siguiente: \n" + msg);
	} else {
		if (confirm('¿ Desea Agregar el Motivo y Rubro de No Aptitud ?')) {
			form.hdiCveUniMed.value = iCveUniMed;
			form.hdiCveMdoTrans.value = iCveMdoTrans;
			form.hdiCveCategoria.value = iCveCategoria;
			form.hdiCveCatalogoNoAp.value = iCveCatalogoNoAp;
			form.lAgregar.value = "";
			form.lAccion.value = 'AgregarMot';
			form.target = "_self";
			form.submit();
		}
	}
}

function fGuardar() {
	var form = document.forms[0];
	msg = "";

	if (form.iCveUniMed.value < 1)
		msg += " - Seleccione una unidad médica.\n"

	if (form.iCveMdoTrans.value < 1)
		msg += " - Seleccione un modo de transporte.\n"

	if (form.iCveCategoria.value < 1)
		msg += " - Seleccione una categoría.\n"

	if (form.dtInicio){
		msg +=  fSinValor(form.dtInicio,5,'Fecha:', true) ;
	}

	if (form.dtCaptura){
		msg += "" + fSinValor(form.dtCaptura,5,'Fecha:', true) ;
	}

	if (form.dttomada){
		msg += fSinValor(form.dttomada,5,'Fecha:', true);
	}
	
	if(form.numpba.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numpba.value)){
			msg += " Escriba un n\u00FAmero de prueba correcto \n"
		}
    }
	
	if(form.numeqp.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numeqp.value)){
			msg += " Escriba un n\u00FAmero de equipo correcto \n"
		}
    }			
	
	if (form.toxpendiente){
        if (form.toxpendiente.value <= 0 || form.toxpendiente.value == ""){
        	msg += " - Indique si el Toxicol\u00F3gico est\u00E1 pendiente. \n"
        }
     }
	
	if(form.numffccc.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numffccc.value)){
			msg += " Escriba un n\u00FAmero de FFCCC correcto \n"
		}
    }			
	
	if(form.numenvio.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numenvio.value)){
			msg += " Escriba un n\u00FAmero de envi\u00F3 correcto \n"
		}
    }				
	
	
	
	if (form.iCveMotivoNoAp)
		if (form.iCveMotivoNoAp.value < 1)
			msg += " - Seleccione un motivo.\n"

	if (!fSoloNumeros(form.iPeriodo.value))
		msg += " - Verifique el valor del periodo.\n"

	if (msg == "") {
		msg2 = "";
		//if(form.lAccion.value == "Agregar")
		msg2 = "¿Está seguro que desea agregar el registro al catálogo de no aptos?";
		//else if(form.lAccion.value == "Quitar")
		//  msg2 = "¿Está seguro que desea quitar (desactivar) el registro del catálogo de no aptos?";
		if (confirm(msg2)) {
			form.lGuardar.value = true;
			form.lAccion.value = "GuardarNoAp";
			form.target = "_self";
			form.submit();
		}
	} else
		alert(msg);
}


function fGuardar2() {
	var form = document.forms[0];
	msg = "";
	
	if (form.dttomada){
		msg += fSinValor(form.dttomada,5,'Fecha:', true);
	}
	
	if(form.numpba.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numpba.value)){
			msg += " Escriba un n\u00FAmero de prueba correcto \n"
		}
    }
	
	if(form.numeqp.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numeqp.value)){
			msg += " Escriba un n\u00FAmero de equipo correcto \n"
		}
    }			
	
	if (form.toxpendiente){
        if (form.toxpendiente.value <= 0 || form.toxpendiente.value == ""){
        	msg += " - Indique si el Toxicol\u00F3gico est\u00E1 pendiente. \n"
        }
     }
	
	if(form.numffccc.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numffccc.value)){
			msg += " Escriba un n\u00FAmero de FFCCC correcto \n"
		}
    }			
	
	if(form.numenvio.value.length > 0){		
		if (!/^([0-9])*$/.test(form.numenvio.value)){
			msg += " Escriba un n\u00FAmero de envi\u00F3 correcto \n"
		}
    }				
	

	if (msg == "") {
		msg2 = "";
		//if(form.lAccion.value == "Agregar")
		msg2 = "¿Está seguro que desea actualizar el registro Toxicol\u00F3gico?";
		//else if(form.lAccion.value == "Quitar")
		//  msg2 = "¿Está seguro que desea quitar (desactivar) el registro del catálogo de no aptos?";
		if (confirm(msg2)) {
			form.lGuardar.value = true;
			form.lAccion.value = "ActualizaNoAp";
			form.target = "_self";
			form.submit();
		}
	} else
		alert(msg);
}


function llenaSLTRubros(opc, val1, val2, val3, iCvePersonal, iCveMdoTrans,
		iCveCategoria, iCveCatalogoNoAp, objDes) {
	if (objDes != '') {
		if (objDes.name != '') {
			objDes.length = 1;
			objDes[0].text = "Cargando Datos...";
			window.parent.FRMOtroPanel.location = "pg070106041P.jsp?opc=" + opc
					+ "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3
					+ "&iCvePersonal=" + iCvePersonal + "&iCveMdoTrans="
					+ iCveMdoTrans + "&iCveCategoria=" + iCveCategoria
					+ "&iCveCatalogoNoAp=" + iCveCatalogoNoAp + "&objDes="
					+ objDes.name + "&hdAccion=" + document.forms[0].action;
		} else {
			alert("Debe seleccionar un dato valido!");
			objDes.length = 0;
			objDes.length = 1;
			return false;
		}
	} else {
		window.parent.FRMOtroPanel.location = "pg070106041P.jsp?opc=" + opc
				+ "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3
				+ "&iCvePersonal=" + iCvePersonal + "&iCveMdoTrans="
				+ iCveMdoTrans + "&iCveCategoria=" + iCveCategoria
				+ "&iCveCatalogoNoAp=" + iCveCatalogoNoAp + "&objDes="
				+ objDes.name + "&hdAccion=" + document.forms[0].action;
	}
}

function llenaSLTCategorias(opc, val1, val2, val3, iCvePersonal, iCveMdoTrans,
		iCveCatalogoNoAp, objDes) {
	if (objDes != '') {
		if (objDes.name != '') {
			objDes.length = 1;
			objDes[0].text = "Cargando Datos...";
			window.parent.FRMOtroPanel.location = "pg070106041P.jsp?opc=" + opc
					+ "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3
					+ "&iCvePersonal=" + iCvePersonal + "&iCveMdoTrans="
					+ iCveMdoTrans + "&iCveCatalogoNoAp=" + iCveCatalogoNoAp
					+ "&objDes=" + objDes.name + "&hdAccion="
					+ document.forms[0].action;
		} else {
			alert("Debe seleccionar un dato valido!");
			objDes.length = 0;
			objDes.length = 1;
			return false;
		}
	} else {
		window.parent.FRMOtroPanel.location = "pg070106041P.jsp?opc=" + opc
				+ "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3
				+ "&iCvePersonal=" + iCvePersonal + "&iCveMdoTrans="
				+ iCveMdoTrans + "&iCveCatalogoNoAp=" + iCveCatalogoNoAp
				+ "&objDes=" + objDes.name + "&hdAccion="
				+ document.forms[0].action;
	}
}


function validaNumero(campo){
  if(isNaN(campo.value)) {
    alert("Debe escribir un n\u00FAmero");
    campo.focus();
    return false;
  }
  return true;
}
