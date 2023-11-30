 function Habilita(valor1, valor2, valor3){
    var form = document.forms[0];
    for(var k = 0; k < form.length; k++ ){
      if(form.elements[k].name == valor1){
        if(form.elements[k].disabled == false){
          form.elements[k].disabled = true;
        }
        else{
          form.elements[k].disabled = false;
        }
      }
      else if(form.elements[k].name == valor2){
        if(form.elements[k].disabled == false){
          form.elements[k].disabled = true;
        }
        else{
          form.elements[k].disabled = false;
        }
      }
      else if(form.elements[k].name == valor3){
        if(form.elements[k].disabled == false){
          form.elements[k].disabled = true;
        }
        else{
          form.elements[k].disabled = false;
        }
      }
    }
 }

  function fillNext(){
    var form = document.forms[0];
    form.target = "FRMDatos";
    form.submit();
  }

  function fSubmite(){
    var form = document.forms[0];
    form.target = 'FRMDatos';
    form.submit();
  }


  var wExp;
  function fSelPer(iTipo){
      form = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cInicio = "";
       if(iTipo){
         if(iTipo != '')
           cInicio = "?hdTipo=" + iTipo;
       }
       wExp = open("SEPer01.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=550,height=300,screenX=800,screenY=600');
//       wExp.creator = self;
       wExp.moveTo(50, 50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }

  function fSelectedPer(cCvePersonal, cCveExpediente, cNumExamen){
    form = document.forms[0];
    form.iCvePersonal.value = cCvePersonal;
    form.iCveExpediente.value = cCveExpediente;
//    form.iNumExamen.value = cNumExamen;
    form.tpoBusqueda.value = 'porPersonal';
    form.hdBoton.value = 'Actual';
    form.target = "_self";
    form.submit();
  }

function fCambiaSelects(modo){
  form = document.forms[0];
  form.hdBoton.value = 'Actual';
  form.target = "_self";
  switch(modo) {
	case 1:
		form.iCveModulo.value='0'
	case 2:
		form.iCveServicio.value='0'
  }
  form.submit();
}

function fBuscar(){
  if(fValidaTodo()){
    form = document.forms[0];
  if (form.iCveExpediente)
	cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', true);
	if (cVMsg != ''){
	    alert("Datos no Válidos: \n" + cVMsg);
	}  else {
		form.hdBoton.value = 'Buscar';
//		form.tpoBusqueda.value = 'porExpediente';
		form.target = "_self";
		form.submit();
	}
  }
}

  // no está en uso
  function fActualizaModulos(forma){
    var cboMod=forma.iCveModulo;
    while(cboMod.length>0) cboMod.options[0]=null;
    cboMod.options[cboMod.length]=new Option("Cargando datos...","");
    window.parent.FRMOtroPanel.location="pg070104020P.jsp?"+
      "idAxn=BUILD_MODS"+
      "&iCveUniMed="+forma.iCveUniMed.value;
  }

  // no está en uso
  function fActualizaServicios(forma){
    var cboServ=forma.iCveServicio;
    while(cboServ.length>0) cboServ.options[0]=null;
    cboServ.options[cboServ.length]=new Option("Cargando datos...","");
    window.parent.FRMOtroPanel.location="pg070104020P.jsp?"+
      "idAxn=BUILD_SERVS"+
      "&iCveUniMed="+forma.iCveUniMed.value+
      "&iCveModulo="+forma.iCveModulo.value;
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCveUniMed)
		if (form.iCveUniMed.value==-1)
		{
            cVMsg += "\n - El campo 'Unidad Médica' es Obligatorio, favor de seleccionar.";
		}
      if (form.iCveModulo)
		if (form.iCveModulo.value==-1)
		{
            cVMsg += "\n - El campo 'Modulo' es Obligatorio, favor de seleccionar.";
		}
      if (form.iCveServicio)
        cVMsg = cVMsg + fSinValor(form.iCveServicio,0,'Servicio', false);
      if (form.iCveExpediente)
        cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

  function fIr(cPagina, cExpediente, cExamen, cPersonal){
    form = document.forms[0];
    form.iCveExpediente.value = cExpediente;
	form.iNumExamen.value = cExamen;
	form.iCvePersonal.value = cPersonal;
	form.hdLOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

  function fLiberar(cPagina){
    if(confirm('¿Desea realizar la Modificación solicitada al Expediente?')){
      form = document.forms[0];
      form.hdBoton.value = 'GuardarA';
      form.target = 'FRMDatos';
      form.action = cPagina;
      form.submit();
    }
  }

function fValidaSelectores() {
	form = document.forms[0];
	cVMsg1 = "";
	if (form.iCveUniMed){
	  if (form.iCveUniMed.value==-1){
            cVMsg1 += "\n - El campo 'Unidad Médica' es Obligatorio, favor de seleccionar.";
	  }
	}
	if (form.iCveModulo){
  	  if (form.iCveModulo.value==-1){
            cVMsg1 += "\n - El campo 'Modulo' es Obligatorio, favor de seleccionar.";
	  }
	}
	if (form.iCveServicio){
	  if (form.iCveServicio.value==-1){
            cVMsg1 += "\n - El campo 'Servicio' es Obligatorio, favor de seleccionar.";
	  }
	}
	if (form.iCveRama){
	  if (form.iCveRama.value==-1){
            cVMsg1 += "\n - El campo 'Rama' es Obligatorio, favor de seleccionar.";
	  }
	}
    if (cVMsg1 != ''){
      alert("Datos no Válidos: \n" + cVMsg1);
      return false;
    }
    return true;
}

 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070107030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070107030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
    }
  }
