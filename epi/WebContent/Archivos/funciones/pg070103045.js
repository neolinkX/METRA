
function fDireccion(Personal){
   var form = document.forms[0];

    form.hdICvePersonal.value = Personal;
    form.hdBoton.value  = "Detalle";

    form.target = 'FRMDatos';
    form.action = 'pg070103042.jsp';
    form.submit();

  }


 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070103041P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deber�n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensi�n js (vg. pg0702061.js)
function fValidaTodo(){
    var form = document.forms[0];

  if (form.hdBoton.value == 'Nuevo' || form.hdBoton.value == 'Borrar') {
     alert("Funci�n deshabilitada !")
     return false;
  }

  
  ///Obteniendo datos de la maquina
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
  ////Se obtuvieron los datos de la maquina
  
  
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
	  if(form.cApPaterno && form.cApMaterno){
		  if(form.cApPaterno.value.length == 0 && form.cApMaterno.value.length == 0){
             cVMsg = cVMsg + fSinValor(form.cApPaterno,1,'Paterno', true);
             cVMsg = cVMsg + fSinValor(form.cApMaterno,1,'Materno', true);
		  }else{
             cVMsg = cVMsg + fSinValor(form.cApPaterno,1,'Paterno', false);
             cVMsg = cVMsg + fSinValor(form.cApMaterno,1,'Materno', false);
		  }
	  }

      if (form.cNombre)
        cVMsg = cVMsg + fSinValor(form.cNombre,1,'Nombre', true);
      
      if (form.dtNacimiento){
        cVMsg = cVMsg + fSinValor(form.dtNacimiento,5,'Fec. Nac.:', true);
    	 //////Comparando fecha de nacimiento ///////////
	     Dia  = form.hdHoy.value.substring(0,2);
	     Mes  = form.hdHoy.value.substring(3,5)-1;
	     Anio = form.hdHoy.value.substring(6,10); 
	     dtFecHoyNoventa = new Date(Anio,Mes,Dia);
	     dtFecHoyQuince = new Date(Anio,Mes,Dia);
		 
	     Dia  = form.dtNacimiento.value.substring(0,2); 
        Mes  = form.dtNacimiento.value.substring(3,5)-1;
        Anio = form.dtNacimiento.value.substring(6,10);
        dtFecNacimiento = new Date(Anio,Mes,Dia);
	     //alert(dtFecNacimiento);
	     dtFecHoyNoventa.setMonth(-1071);
	     //alert(dtFecHoyNoventa);
	     dtFecHoyQuince.setMonth(-171);
	     //alert(dtFecHoyQuince);
	     
	     if (dtFecNacimiento < dtFecHoyNoventa){
	         cVMsg = cVMsg + "\n - La Fecha de Nacimiento es incorrecta, no puede ser mayor a 90 a\u00F1os. \n";
	     }
	     
	     if (dtFecNacimiento > dtFecHoyQuince){
	         cVMsg = cVMsg + "\n - La Fecha de Nacimiento es incorrecta, no puede ser menor a 15 a\u00F1os. \n";
	     }
	     
      }
      
		
	 if (form.cRFC){
         cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
         if ( fValRFC(form.cRFC.value,1) == false)
           cVMsg = cVMsg + "\n - El RFC de la Persona F�sica es Incorrecto. \n";
      }		
     // if (form.cRFC)
     //   cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
     if (form.cHomoclave)
        cVMsg = cVMsg + fSinValor(form.cHomoclave,2,'Homoclave:', false);
      if (form.cCURP)
        cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP:', false);

      var curp=form.cCURP.value;//si se trata de obtener la curp de un formulario.
      if(curp.match(/^([a-z]{4})([0-9]{6})([a-z]{6})([0-9A-Z]{1})([0-9]{1})$/i)){
    	  //cVMsg = cVMsg;
      }else{
    	  cVMsg = cVMsg + "\n - La CURP ingresada es incorrecta. \n";
      }
      

     if (form.iCvePaisNac){
         if (form.iCvePaisNac.value <= 0 || form.iCvePaisNac.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar alg�n pa�s de nacimiento. \n";
         }
      }
      if (form.iCveEstadoNac){
         if (form.iCveEstadoNac.value <= 0 || form.iCveEstadoNac.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar alg�n lugar de nacimiento. \n";
         }
      } 


//      if (form.cExpediente)
//        cVMsg = cVMsg + fSinValor(form.cCURP,2,'Expediente:', true);
      
      if (form.cExpediente){
         cVMsg = cVMsg + fSinValor(form.cExpediente,2,'Expediente:', false);
         if ( fComilla(form.cExpediente.value) == true)
           cVMsg = cVMsg + "\n - El Expediente de la Persona es Incorrecto. \n";
        }
        
      if (form.cCorreoElec)
        cVMsg = cVMsg + fSinValor(form.cCorreoElec,0,'Correo Electr�nico:', false);

      if (form.cCorreoElec){
         if ( fComilla(form.cCorreoElec.value) == true)
           cVMsg = cVMsg + "\n - El Correo Electronico es Incorrecto. \n";
        }

      if (form.cPersonaAviso)
        cVMsg = cVMsg + fSinValor(form.cPersonaAviso,2,'Persona Aviso:', false);
        
  
      if (form.cDirecAviso){
        cVMsg = cVMsg + fSinValor(form.cDirecAviso,0,'Direcci�n Aviso:', false);
        if ( fComilla(form.cDirecAviso.value) == true)
           cVMsg = cVMsg + "\n - Direcci�n Aviso es Incorrecto. \n";
        }

        
     if (form.cTelAviso)
        cVMsg = cVMsg + fSinValor(form.cTelAviso,2,'Tel. Aviso:', false);
      if (form.cCorreoAviso){
        cVMsg = cVMsg + fSinValor(form.cCorreoAviso,0,'Correo Aviso:', false);
                if ( fComilla(form.cCorreoAviso.value) == true)
           cVMsg = cVMsg + "\n - Correo Aviso es Incorrecto. \n";
        }

      if (form.cLicencia){
	      if (form.cLicencia.value.length > 0){
	          if (form.cLicencia.value.match(/^[a-zA-Z0-9]+$/)){
	            cVMsg = cVMsg + "";
	          }else{
	            cVMsg = cVMsg + "\n - La licencia solo puede contener n�meros y letras, y no puede contener caracteres en blanco. \n";
	          } 
	        }
      }




        if (cVMsg != ''){
          alert("Datos no V\u00E1lidos: \n" + cVMsg);
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
 
 function fValidaCURP(curp){	
	 
	 if(!curp.value.match(/^[A-Z]{4}[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[HM]{1}(AS|BC|BS|CC|CH|CL|CS|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)[B-DF-HJ-NP-TV-Z]{3}[0-9A-Z]{1}[0-9]{1}$/i)){//AAAA######AAAAAA##
		 alert('La CURP ingresada no es valida.');	    	
	 }
}

 