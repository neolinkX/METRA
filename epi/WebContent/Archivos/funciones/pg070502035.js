  function fSelectedPer(iCvePersonal, iCveExpediente, iNumExamen){
    var form = document.forms[0];
    window.parent.FRMOtroPanel.location="pg070502035P.jsp?opc=1&val1=" + iCvePersonal + "&val2=''&val3=''&objDes=''" + '&hdAccion=' + document.forms[0].action;
  }


function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502035P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502035P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }


  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cRFC){
         cVMsg = cVMsg + fSinValor(form.cRFC,2,'RFC:', true);
         if ( fValRFC(form.cRFC.value,1) == false)
           cVMsg = cVMsg + "\n - El RFC de la Persona Física es Incorrecto. \n";
      }
      if (form.cCURP)
         cVMsg = cVMsg + fSinValor(form.cCURP,2,'CURP:', false);

      if (form.iCveExpediente)
        cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente:', false);

      if (form.dtNacimiento)
        cVMsg = cVMsg + fSinValor(form.dtNacimiento,5,'FECNAC (Fecha de Nacimiento):', false);

      if (form.iCvePaisNac)
         if (form.iCvePaisNac.selectedIndex == 0)
            cVMsg = cVMsg + " \n - Debe seleccionar el Pais de Nacimiento";

      if (form.iCveEstadoNac)
         if (form.iCveEstadoNac.selectedIndex == 0)
            cVMsg = cVMsg + " \n - Debe seleccionar el Estado de Nacimiento";

      if (form.iCveMdoTrans)
         if (form.iCveMdoTrans.selectedIndex == 0)
            cVMsg = cVMsg + " \n - Debe seleccionar el Modo de Transporte";

      if (form.iCvePuesto)
         if (form.iCvePuesto.selectedIndex == 0)
            cVMsg = cVMsg + " \n - Debe seleccionar el Puesto";

      if (form.cLicencia)
         if (form.cLicencia.value == '')
            cVMsg = cVMsg + " \n - Favor de Introducir la Licencia ";

      if (form.dtLicVencimiento)
        cVMsg = cVMsg + fSinValor(form.dtLicVencimiento,5,'Fecha de Vencimiento:', false);

      if (form.iCP)
        cVMsg = cVMsg + fSinValor(form.iCP,3,'CP:', false);

      if (form.iCvePais)
         if (form.iCvePais.selectedIndex == 0)
            cVMsg = cVMsg + " \n - Debe seleccionar el Pais";

      if (form.iCveEstado)
         if (form.iCveEstado.selectedIndex == 0)
            cVMsg = cVMsg + " \n - Debe seleccionar el Estado";

      if (form.iCveMunicipio)
         if (form.iCveMunicipio.selectedIndex == 0)
            cVMsg = cVMsg + " \n - Debe seleccionar el Municipio";

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }
     return true;
   }
