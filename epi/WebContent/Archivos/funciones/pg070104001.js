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
    if ((form.iCveExpediente.value != null && form.iCveExpediente.value.length > 0) &&
        (form.iCveModulo.value != 0) && (form.iCveProceso.value != 0)){
       if (form.iCveExpediente)
         cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
         if (cVMsg != ''){
            alert("Datos no Validos: \n" + cVMsg);
         }else{
            form.iNumExamen.value = "";
            form.hdType.value = "E";
            form.submit();
         }
    }else{

       if (form.iCveExpediente.value == null || form.iCveExpediente.value.length == 0){
           cVMsg = cVMsg + "- Ingrese un Numero de Expediente \n";
       }
       if (form.iCveModulo.value == 0){
           cVMsg = cVMsg + "- Ingrese la Clave del Modulo \n";
       }
       if (form.iCveProceso.value == 0){
           cVMsg = cVMsg + "- Ingrese la Clave del Proceso \n";
       }
       if (cVMsg != ''){
         alert("Datos no Validos: \n" + cVMsg);
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
       if (form.iCveUniMed.value < 1){
           cVMsg = cVMsg + "- Seleccione una Unidad Medica \n";
       }
       if (form.iCveModulo.value < 1){
           cVMsg = cVMsg + "- Seleccione un Modulo \n";
       }
       if (form.iCveProceso.value < 1){
           cVMsg = cVMsg + "- Seleccione un Proceso \n";
       }
       if (form.iCveExpediente.value == null || form.iCveExpediente.value.length == 0){
           cVMsg = cVMsg + "- Ingrese un Numero de Expediente \n";
       }
       else if (form.hdBuscado.value != 1){
           cVMsg = cVMsg + "- Antes de Guardar, busque el Expediente \n";
       }
       if (form.hdBuscado.value == 1 && form.iFolioEs.value==""){
         cVMsg = cVMsg + "- Escriba la encuesta de salida \n";
       }

       if (cVMsg != ''){
         alert("Datos no Validos: \n" + cVMsg);
       }else{
          if (form.hdBoton.value == 'Guardar'){
            if(confirm("¿Esta seguro de guardar la información?")){
              return true;
              form.lIniciado.value = "1";
            }
            else
              return false;
          }
       }
    }
   }