  

function fIrAsignacion(cCampoClave1, cCampoClave2){
    form = document.forms[0];
    form.iCveUniMed.value = cCampoClave1;
    form.iCveModulo.value = cCampoClave2;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071003032.jsp';
    form.submit();
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
    //alert("valido " + macAddress + ",");
    var valorFinal = form.iCveUniMed.selectedIndex;
    form.hdNameUniMed.value = form.iCveUniMed.options[valorFinal].text;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      var valorFinalVal = form.lValida.selectedIndex;
      form.hdNameValidaBio.value = form.lValida.options[valorFinalVal].text;

      if(form.cDscModulo.value=="")
        cVMsg = cVMsg + "\n - Escriba la descripción del Módulo.";

      if(form.cCalle.value=="")
        cVMsg = cVMsg + "\n - Escriba la calle del domicilio del Módulo.";

      if(form.cColonia.value<1)
        cVMsg = cVMsg + "\n - Escriba la Colonia del domicilio del Módulo.";

      if(form.iCP.value!="" && form.iCP.value!="0"){
        cCP = form.iCP.value;
        if(cCP.length<5)
          cVMsg = cVMsg + "\n - El C.P. debe tener 5 números.";
        else{
          iError = 0;
          for(i=0; i<cCP.length; i++)
            if(cCP.charAt(i)<'0' || cCP.charAt(i)>'9')
              iError = 1;
          if(iError == 1)
            cVMsg = cVMsg + "\n - El C.P. debe tener solamente caracteres numéricos.";
        }
      }


      if(form.cCiudad.value=="")
        cVMsg = cVMsg + "\n - Escriba la ciudad del domicilio de la Unidad Médica.";

      if(form.iCvePais.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Pais.";
      else if(form.iCveEstado.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Estado.";
      else if(form.iCveMunicipio.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Municipio o Delegación.";

      if(cVMsg!=''){
        alert('Datos no Válidos: \n' + cVMsg);
        return false;
      }
      else
        return true;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
    if(objDes!=''){
      if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg071003011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
      }
      else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
      }
    }
    else {
      window.parent.FRMOtroPanel.location="pg071003011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
