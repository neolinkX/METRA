
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
function fValidaTodo(){
    var form = document.forms[0];
    var Dia;
    var Mes;
     if (form.hdBoton.value == 'Guardar' && form.hdBoton.value != 'Cancelar' ) {
      cVMsg = '';

       if (form.iCveUniMed){
         if (form.iCveUniMed.value <= 0){
           cVMsg = cVMsg + " - Debe seleccionar alguna unidad médica. \n";
         }
      }
       if (form.iCveProceso){
         if (form.iCveProceso.value <= 0 || form.iCveProceso.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún proceso. \n";
         }
      }
       if (form.iCveMotivo){
         if (form.iCveMotivo.value <= 0 || form.iCveMotivo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún motivo. \n";
         }
      }
      if (form.iCveUsuRecolec){
         if (form.iCveUsuRecolec.value <= 0 || form.iCveUsuRecolec.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar Médico Recolector. \n";
         }
      }

      if (form.iCveMdoTrans){
         if (form.iCveMdoTrans.value <= 0 || form.iCveMdoTrans.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar el modo de Transporte. \n";
         }
      }

       if (form.hdCvePersonal){
         cVMsg = cVMsg + fSinValor(form.hdCvePersonal,3,'Personal', true);
       }

      if (form.iAnio)
        cVMsg = cVMsg + fSinValor(form.iAnio,3,'Año', true);
      if (form.iCveMuestra)
        cVMsg = cVMsg + fSinValor(form.iCveMuestra,3,'Muestra', true);
      if (form.hdCveEmpresa)
        cVMsg = cVMsg + fSinValor(form.hdCveEmpresa,3,'Empresa', false);
      if (form.iCvePbaRapida)
        cVMsg = cVMsg + fSinValor(form.iCvePbaRapida,3,'Prueba Rápida', false);
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad Médica', false);
      if (form.iCveEnvio)
        cVMsg = cVMsg + fSinValor(form.iCveEnvio,3,'Envío', false);
      if (form.iCveProceso)
        cVMsg = cVMsg + fSinValor(form.iCveProceso,3,'Proceso', false);
      if (form.iCveMotivo)
        cVMsg = cVMsg + fSinValor(form.iCveMotivo,3,'Motivo', false);
      if (form.iCveSituacion)
        cVMsg = cVMsg + fSinValor(form.iCveSituacion,3,'Situacion', false);
      
      /////Maximo 8 caracteres
      if (form.iCveMuestra.value.length < 8){
    	  cVMsg = cVMsg + "\n - La Muestra no puede ser menor a 8 caracteres. \n";
      }

    if (form.dtRecoleccion){
         if (form.dtRecoleccion.value != null){
         Dia  = form.dtRecoleccion.value.substring(0,2);
         Mes  = form.dtRecoleccion.value.substring(3,5)-1;
         Anio = form.dtRecoleccion.value.substring(6,10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.hdHoy.value.substring(0,2);
         Mes  = form.hdHoy.value.substring(3,5)-1;
         Anio = form.hdHoy.value.substring(6,10);
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de recolección no puede ser mayor a la fecha actual. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtRecoleccion,5,'Fecha de recolección', true);
      }

    /*
    if (form.cObsMuestra){
    	cVMsg = cVMsg + fSinValor(form.cObsMuestra,2,'Observaciones:', true);
    }*/
    

    if (form.iNoLetras){
       cVMsg = cVMsg + fSinValor(form.iNoLetras,0,'Observaciones', true);
                  if ( fComilla(form.iNoLetras.value) == true)
          cVMsg = cVMsg + "\n - El formato de Observaciones es Incorrecto. \n";
       }
    
    
    	var i = 0;
    	if(form.lResultado.value == 1){
			  if(form.lAnfetaminas.checked == true){i++;}
			  if(form.lCannabis.checked == true){i++;}
			  if(form.lCocaina.checked == true){i++;}
			  if(form.lOpiaceos.checked == true){i++;}
			  if(form.lFenciclidina.checked == true){i++;}
			  if(form.lMetanfetaminas.checked == true){i++;}
		  
			  if (i == 0){
				  	cVMsg = cVMsg + "\n - Favor de indicar la sustancia a la que resulto positiva la prueba rápida. \n";
			    }
			 
    	}	  
    	
    	 
    	 
    
    
    
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
        msg="¿ Desea Almacenar la información del Formato Federal de Control y Cadena de Custodia? ";
        if (!confirm(msg)) return false;

    }
     return true;
   }

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}


function fSelectedPer(iCvePersonal){
    var form = document.forms[0];
    form.hdiCvePersonal.value = iCvePersonal;

   //if (form.iCveMdoTrans){
      form.hdBoton.value = "Nuevo";
   //}

    form.target = "_self";
    form.submit();

  }


 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070302020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras.value = 1999 - cText.length;
  }

  function fEmpSelected(iCveEmpresa, cNombreRS, cApPaterno, cApMaterno, cRFC, cCURP, cTpoPersona){
      var form = document.forms[0];
     //alert("Valores Empresa: " + iCveEmpresa + cNombreRS );
     form.hdCveEmpresa.value = iCveEmpresa;
     form.hdDscEmpresa.value = cNombreRS;

     //if (form.iCveMdoTrans){
      form.hdBoton.value = "Nuevo";
     //}

    form.target = "_self";
    form.submit();
  }

  function disableUno(){
	  form = document.forms[0];
	  if(form.lResultado.value == 0 || form.lResultado.value == 2){
		  form.lAnfetaminas.checked = false;
		  form.lCannabis.checked = false;
		  form.lCocaina.checked = false;
		  form.lOpiaceos.checked = false;
		  form.lFenciclidina.checked = false;
		  form.lMetanfetaminas.checked = false;
		  alert("Solo es posible seleccionar una sustancia cuando el resultado es positivo");
	  }	  
  }
  
  function disableDos(){
	  form = document.forms[0];
	  if(form.lResultado.value == 0 || form.lResultado.value == 2){
		  form.lAnfetaminas.checked = false;
		  form.lCannabis.checked = false;
		  form.lCocaina.checked = false;
		  form.lOpiaceos.checked = false;
		  form.lFenciclidina.checked = false;
		  form.lMetanfetaminas.checked = false;
	  }	  
  }
