function fBuscar(){
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
    form.hdBoton.value = "Cancelar";
    form.target = "_self";
    form.submit();
}

 
  function fSelectedPer(iCvePersonal){
    var form = document.forms[0];
    form.hdiCvePersonal.value = iCvePersonal;
    form.hdBoton.value = "Nuevo";
    form.target = "_self";
    form.submit();
 
  }

function fValidaTodo(){
	//alert("Si entro");
    var form = document.forms[0]; 
    var Dia;
    var Mes;
    var Anio;
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


    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }


    if (form.hdBoton.value != 'Cancelar' && form.hdBoton.value != 'Nuevo') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad Médica:', true);
      if (form.iCveModulo){
         if (form.iCveModulo.value <= 0){
           cVMsg = cVMsg + "\n - Debe seleccionar el módulo. \n";
         }
      }

      if (form.hdBoton.value == 'Guardar'){
        /*if (form.dtFecha){  
         if (form.dtFecha.value != null){
         Dia  = form.dtFecha.value.substring(0,2); 
         Mes  = form.dtFecha.value.substring(3,5)-1;
         Anio = form.dtFecha.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.dtFecha2.value.substring(0,2);
         Mes  = form.dtFecha2.value.substring(3,5)-1;
         Anio = form.dtFecha2.value.substring(6,10); 
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de Inicio de Inhabilitaci�n no puede ser mayor a la de fin. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtFecha,5,'Fecha:', true);
      }
      
      if (form.dtFecha2){  
         if (form.dtFecha2.value != null){
         Dia  = form.dtFecha2.value.substring(0,2); 
         Mes  = form.dtFecha2.value.substring(3,5)-1;
         Anio = form.dtFecha2.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.hdHoy.value.substring(0,2);
         Mes  = form.hdHoy.value.substring(3,5)-1;
         Anio = form.hdHoy.value.substring(6,10); 
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi < dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de Fin de la Inhabilitaci�n no puede ser menor al d�a de hoy. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtFecha2,5,'Fecha:', true);
      }*/
    	  
      
   }



     if (form.iCveMotivo){
         if (form.iCveMotivo.value <= 0 || form.iCveMotivo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar el motivo. \n";
         }
      }

      
     if (form.iNoLetras){
        cVMsg = cVMsg + fSinValor(form.iNoLetras,0,'Observaciones', true);
                   if ( fComilla(form.iNoLetras.value) == true)
           cVMsg = cVMsg + "\n - El formato de Observaciones es Incorrecto. \n";
        }
              

        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

  function fIrPagina(){
    form = document.forms[0];
    form.hdiCvePersonal.value = '1';
    form.target = 'FRMDatos';
    form.action = 'SEDetPer.jsp';
    form.submit();
  }



  var wExp;

  function fGetFile(){
     form = document.forms[0];
     if(fValidaCarga()){

     if((wExp != null) && (!wExp.closed))
        wExp.focus();
     else{
           cArchivo="CIS.txt";
           cPagina = '<HTML><title>Cargar Información</title>'+cStyle+'\n'+
           '<SCRIPT LANGUAGE="JavaScript">\n'+
           '  function fSaveFile(){\n'+
           '    form = document.forms[0];\n'+
           '    cArchivo="'+cArchivo+'" \n'+
           '    if(form.fArchivo.value.substring(form.fArchivo.value.length - 7) != cArchivo) \n'+
           '      alert("El archivo seleccionado no es el correcto! \\n " + form.fArchivo.value + " <> " + cArchivo);\n'+
           '    else \n'+
           '      form.submit();\n'+
           '  }\n'+
           '  function fClose(){\n'+
           '    form = window.opener;\n'+
           '    form.submit(); window.close();\n'+
           '  }\n'+
           '</SCRIPT>\n'+
           '<body bgcolor="white">'+
           '<form METHOD="POST" ACTION="servUpCIS?iCveUniMed='+form.iCveUniMed.value+'&iCveModulo='+form.iCveModulo.value+'" ENCTYPE="multipart/form-data">'+
           '<table border="1" class="ETablaInfo" width="100%" height="100%" cellspacing="0" cellpadding="3">\n'+
           '<tr><td class="ETablaT" align="center">Cargar Información<tr><td>'+
           '<tr><td class="EEtiquetaC" >' + cArchivo + '\n'+
           '<tr><td>'+
           '<tr><td align="center">' +
           '<input type="File" name="fArchivo" size="50" maxlength="80">\n'+
           '</td></tr>'+
           '<tr><td align="center">\n'+
           '<A HREF="javascript:fSaveFile();">Cargar Información</A>\n'+
           '</td></tr>\n'+
           '</table>\n'+
           '</form></body></HTML>\n';
       wExp = window.open("", "","width=600,height=15,status=no,resizable=no,top=200,left=200");
       wExp.opener = window;
       wExp.moveTo(100,100);
       this.wExp.document.write(cPagina);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();

     }
	 }
  }

 function fValidaCarga(){
       form = document.forms[0];
       alert("aca");
       cVMsg = '';
       if (form.iCveUniMed.value < 1){
           cVMsg = cVMsg + "- Seleccione una Unidad Médica \n";
       }
       if (form.iCveModulo.value < 1){
           cVMsg = cVMsg + "- Seleccione un Módulo \n";
       }
	   if(!fValFecha(form.dtFecha.value)){
           cVMsg = cVMsg + "- Verifique la fecha \n";
       }
       if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);		
       }else
         return true;
 }
