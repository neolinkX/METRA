function fDireccion(Personal){
   var form = document.forms[0];

    form.hdICvePersonal.value = Personal;
    alert("Direccion Personal :" + Personal);

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
           window.parent.FRMOtroPanel.location="pg070103042P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
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

  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
function fValidaTodo(){
    var form = document.forms[0];

  if (form.hdBoton.value == 'Borrar') {
     alert("Función deshabilitada !")
     return false;
  }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

      if (form.cCalle)
        cVMsg = cVMsg + fSinValor(form.cCalle,0,'Calle:', true);
      if (form.cNumExt)
        cVMsg = cVMsg + fSinValor(form.cNumExt,2,'NumExt:', true);
      if (form.cNumInt)
        cVMsg = cVMsg + fSinValor(form.cNumInt,2,'NumInt:', true);
      if (form.cColonia)
        cVMsg = cVMsg + fSinValor(form.cColonia,0,'Colonia:', true);
      if (form.iCP)
        cVMsg = cVMsg + fSinValor(form.iCP,3,'CP:', true);
      if (form.cCiudad)
        cVMsg = cVMsg + fSinValor(form.cCiudad,0,'Ciudad:', true);
     /* if (form.iCvePais)
        cVMsg = cVMsg + fSinValor(form.iCvePais,3,'Pais:', false);
      if (form.iCveEstado)
        cVMsg = cVMsg + fSinValor(form.iCveEstado,3,'Estado:', false);
      if (form.iCveMunicipio)
        cVMsg = cVMsg + fSinValor(form.iCveMunicipio,3,'Municipio:', false);
*/

     if (form.iCvePais){
         if (form.iCvePais.value <= 0 || form.iCvePais.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún país \n";
         }
      }
      if (form.iCveEstado){
         if (form.iCveEstado.value <= 0 || form.iCveEstado.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún estado. \n";
         }
      }
       if (form.iCveMunicipio){
         if (form.iCveMunicipio.value <= 0 || form.iCveMunicipio.value == ""){
           cVMsg = cVMsg + "\n - Debe seleccionar algún municipio. \n";
         }
      }

      if (form.cTel)
        cVMsg = cVMsg + fSinValor(form.cTel,2,'Tel:', false); 
      
      
      //alert(form.hdBoton.value);
      /*
      if (form.iCveUniMed.value == '107') {
    	  if (form.hdBoton.value == 'Modificar') {
    		  cVMsg = cVMsg + "\n - Los médicos terceros no están autorizados para modificar el domicilio. \n";
    	  }
      }*/

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