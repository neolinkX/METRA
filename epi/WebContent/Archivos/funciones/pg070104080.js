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
    form.iNumExamen.value = cNumExamen;
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
	    alert("Datos no V�lidos: \n" + cVMsg);
	}  else {
		form.hdBoton.value = 'Actual';
		form.tpoBusqueda.value = 'porExpediente';
		form.target = "_self";
		form.submit();
	}
  }
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
            cVMsg += "\n - El campo 'Unidad M�dica' es Obligatorio, favor de seleccionar.";
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
          alert("Datos no V�lidos: \n" + cVMsg);
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
	form.hdLOrdenar.value = '';   // para no arrastrar el par�metro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

function fValidaSelectores() {
	form = document.forms[0];
	cVMsg1 = "";
	if (form.iCveUniMed)
	{
		if (form.iCveUniMed.value==-1)
		{
            cVMsg1 += "\n - El campo 'Unidad M�dica' es Obligatorio, favor de seleccionar.";
		}
	}
	if (form.iCveModulo)
	{
		if (form.iCveModulo.value==-1)
		{
            cVMsg1 += "\n - El campo 'Modulo' es Obligatorio, favor de seleccionar.";
		}
	}
	if (form.iCveServicio)
	{
		if (form.iCveServicio.value==-1)
		{
            cVMsg1 += "\n - El campo 'Servicio' es Obligatorio, favor de seleccionar.";
		}
	}
	if (form.iCveRama)
	{
		if (form.iCveRama.value==-1)
		{
            cVMsg1 += "\n - El campo 'Rama' es Obligatorio, favor de seleccionar.";
		}
	}
    if (cVMsg1 != ''){
      alert("Datos no V�lidos: \n" + cVMsg1);
      return false;
    }

	return true;
}



   function fIrCatalogo(page,val){
    var form=document.forms[0];
    form.action=page + "?iNumExamen=" + val;
    form.target="FRMDatos";
    form.submit();
  }

