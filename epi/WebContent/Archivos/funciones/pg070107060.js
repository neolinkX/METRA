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
	cVMsg = cVMsg + fSinValor(form.iNumExamen,3,'Examen', true);
	if (cVMsg != ''){
	    alert("Datos no Válidos: \n" + cVMsg);
	}  else {
		form.hdBoton.value = 'Actual';
		form.tpoBusqueda.value = 'porExpediente';
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
    var  macAddress = "";
    var ipAddress = "";
    var computerName = "";
    var revision = "C:/Windows/Temp/FingerInfo.jar";
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    var folderExistencia = fso.FileExists(revision);
     
    if (BrowserDetect.browser == 'Explorer' && parseInt(BrowserDetect.version,10) >= 9) {
    //alert("entro a explorer 9 o mayor");
    	if (folderExistencia) {
    		var ts;
    		var ForReading = 1;
    		ts = fso.OpenTextFile("C:\\Windows\\Temp\\FingerPrintInf.txt", ForReading);
    		ipAddress = ts.ReadLine();
    		computerName = ts.ReadLine();
    		macAddress =ts.ReadLine();
    		//Response.Write("Contenido del archivo= '" + s + "'");
    		ts.Close();
    	
    	}

    } else {
    	var wmi = GetObject("winmgmts:{impersonationLevel=impersonate}");
    	e = new Enumerator(wmi.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration WHERE IPEnabled = True"));
    	for(; !e.atEnd(); e.moveNext()) {
    		var s = e.item();
    		macAddress = s.MACAddress;
    		ipAddress = s.IPAddress(0);
    		computerName = s.DNSHostName;
    	}
    }
    form.hdMacAddress.value = macAddress;
    form.hdIpAddress.value = ipAddress;
    form.hdComputerName.value = computerName;
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
      if (form.iNumExamen)
        cVMsg = cVMsg + fSinValor(form.iNumExamen,3,'Expediente', false);        
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

function fValidaSelectores() {
	form = document.forms[0];
	cVMsg1 = "";
	if (form.iCveUniMed)
	{
		if (form.iCveUniMed.value==-1)
		{
            cVMsg1 += "\n - El campo 'Unidad Médica' es Obligatorio, favor de seleccionar.";
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
      alert("Datos no Válidos: \n" + cVMsg1);
      return false;
    }

	return true;
}



  function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

//       wExp = open("pg070104021.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=700,height=500,screenX=800,screenY=600');
       wExp = open("pg070104070.jsp"+cInicio, "Consulta",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=300,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }

   function fIrCatalogo(page,val){
    var form=document.forms[0];
    form.action=page + "?iNumExamen=" + val;
    form.target="FRMDatos";
    form.submit();
  }

