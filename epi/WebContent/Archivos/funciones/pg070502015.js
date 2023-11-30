
  function fValidaTodo(){
     var form = document.forms[0];
     if (form.hdBoton.value == 'Borrar') {
       if(!confirm("¿Está Seguro de Eliminar el Registro?"))
         return false;
     }
     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
        cVMsg = '';

        if (!fValRFC(form.cRFC.value, 1))
           cVMsg = cVMsg + '\n - R.F.C. Incorrecto';

        if (form.cApPaterno)
           cVMsg = cVMsg + fSinValor(form.cApPaterno,2,'Apellido Paterno', true);

        if (form.cApMaterno)
           cVMsg = cVMsg + fSinValor(form.cApMaterno,2,'Apellido Materno', true);

        if (form.cNombre)
           cVMsg = cVMsg + fSinValor(form.cNombre,2,'Nombre(s)', true);

        if (form.cPuesto)
           cVMsg = cVMsg + fSinValor(form.cPuesto,2,'Puesto', true);
        //if (form.cCalle)
        //   cVMsg = cVMsg + fSinValor(form.cCalle,2,'Calle', true);
        //if (form.cColonia)
        //   cVMsg = cVMsg + fSinValor(form.cColonia,2,'Colonia', true);

        if (form.iCP)
           cVMsg = cVMsg + fSinValor(form.iCP,3,'C.P.', true);

        if (form.iCvePais.value == 0 || form.iCvePais.value == '')
           cVMsg = cVMsg + '\n - Debe seleccionar un País.';

        if (form.iCveEstado.value == -1 || form.iCveEstado.value == '')
           cVMsg = cVMsg + '\n - Debe seleccionar un Estado.';

        if (form.iCveMunicipio.value == -1 || form.iCveMunicipio.value == '')
           cVMsg = cVMsg + '\n - Debe seleccionar un Municipio.';

        if (cVMsg != ''){
           alert("Datos no Válidos: \n" + cVMsg);
           return false;
        }

        if(!confirm("¿Está Seguro de Guardar la Información?"))
          return false;
     }
     return true;
  }

 function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras.value = 1999 - cText.length;
  }

  function fCambioFiltro() {
     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.target         = 'FRMDatos';
     form.action         = 'pg070502015.jsp';
     form.submit();
  }

  function llenaSLT(opc,val1,val2,val3,objDes,objDes2){
     if(objDes!=''){
        if(objDes.name!=''){
           if (objDes2!='' && objDes2.name!='') {
               objDes2.length=1;
               objDes2[0].text="Datos no Disponibles...";
           }
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502015P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502015P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }


