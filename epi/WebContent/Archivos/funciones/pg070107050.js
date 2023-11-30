function fillNext(){
       form = document.forms[0];
       form.target =  "FRMDatos";
       form.submit();
}
function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    form = document.forms[0];
    form.target =  "FRMDatos";
    form.hdICvePersonal.value = iCvePersonal;
    form.iNumExamen.value = iNumExamen;
    form.hdType.value = "P";
    form.submit();
}

function fSelExp(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    cVMsg = '';
    if ((form.iCveExpediente.value != null && form.iCveExpediente.value.length > 0)){
       if (form.iCveExpediente)
         cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
         if (cVMsg != ''){
            alert("Datos no Válidos: \n" + cVMsg);
         }else{
            form.iNumExamen.value = "";
            form.hdType.value = "E";
            form.submit();
         }
    }else{

       if (form.iCveExpediente.value == null || form.iCveExpediente.value.length == 0){
           cVMsg = cVMsg + "- Ingrese un Número de Expediente \n";
       }
       
       if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
       }
    }
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
    if (form.hdBoton.value != 'Cancelar') {

      cVMsg = '';
      
       
       if (form.hdBuscado.value == 1 && form.iFolioEs.value==""){
         cVMsg = cVMsg + "- Escriba la encuesta de salida \n";
       }

       if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
       }else{
          if (form.hdBoton.value == 'Guardar'){
            if(confirm("¿Está seguro que desea desbloquear el examen?")){
              return true;
              form.lIniciado.value = "1";
            }
            else
              return false;
          }
       }
    }
   }