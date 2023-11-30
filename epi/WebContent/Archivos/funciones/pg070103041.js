
function fDireccion(Personal){
   var form = document.forms[0];

    form.hdICvePersonal.value = Personal;
    form.hdBoton.value  = "Detalle";

    form.target = 'FRMDatos';
    form.action = 'pg070103046.jsp';
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

  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberï¿½n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensiï¿½n js (vg. pg0702061.js)
function fValidaTodo(){
    var form = document.forms[0];

  if (form.hdBoton.value == 'Nuevo' || form.hdBoton.value == 'Borrar') {
     alert("Función deshabilitada !")
     return false;
  }

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
      if (form.dtNacimiento)
        cVMsg = cVMsg + fSinValor(form.dtNacimiento,5,'Fec. Nac.:', true);
		
	 if (form.cRFC){
         cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
         if ( fValRFC(form.cRFC.value,1) == false)
           cVMsg = cVMsg + "\n - El RFC de la Persona Física es Incorrecto. \n";
      }		
     // if (form.cRFC)
     //   cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
     if (form.cHomoclave)
        cVMsg = cVMsg + fSinValor(form.cHomoclave,2,'Homoclave:', false);
      if (form.cCURP)
        cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP:', false);


     if (form.iCvePaisNac){
         if (form.iCvePaisNac.value <= 0 || form.iCvePaisNac.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún país de nacimiento. \n";
         }
      }
      if (form.iCveEstadoNac){
         if (form.iCveEstadoNac.value <= 0 || form.iCveEstadoNac.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún lugar de nacimiento. \n";
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
        cVMsg = cVMsg + fSinValor(form.cCorreoElec,0,'Correo Electrónico:', false);

      if (form.cCorreoElec){
         if ( fComilla(form.cCorreoElec.value) == true)
           cVMsg = cVMsg + "\n - El Correo Electronico es Incorrecto. \n";
        }

      if (form.cPersonaAviso)
        cVMsg = cVMsg + fSinValor(form.cPersonaAviso,2,'Persona Aviso:', false);
        
  
      if (form.cDirecAviso){
        cVMsg = cVMsg + fSinValor(form.cDirecAviso,0,'Dirección Aviso:', false);
        if ( fComilla(form.cDirecAviso.value) == true)
           cVMsg = cVMsg + "\n - Dirección Aviso es Incorrecto. \n";
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
	            cVMsg = cVMsg + "\n - La licencia solo puede contener números y letras. \n";
	          } 
	        }
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