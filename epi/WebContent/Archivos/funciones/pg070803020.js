  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070803020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070803020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fIrDetalle(cIAnio, cICveAlmacen, cICveSolicSum, cDscUniMed, cDscArea, cDtSolicitud, cDtSuministro, cDscPrioridad, cDscSolicitud, iCveUniMed, iCveModulo, iCveArea, iCvePeriodo) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';

    form.hdIAnio.value = cIAnio;
    form.hdICveAlmacen.value = cICveAlmacen;
    form.hdICveSolicSum.value = cICveSolicSum;
    form.hdDscUniMed.value = cDscUniMed;
    form.hdDscArea.value = cDscArea;
    form.hdDtSolicitud.value = cDtSolicitud;
    form.hdDtSuministro.value = cDtSuministro;
    form.hdDscPrioridad.value = cDscPrioridad;
    form.hdDscSolicitud.value = cDscSolicitud;

    form.iCveUniMed.value = iCveUniMed;
    form.iCveModulo.value = iCveModulo;
    form.iCveArea.value = iCveArea;
    form.iAnio.value = cIAnio;
    form.iCvePeriodo.value = iCvePeriodo;
    form.chkProgExt.value = cDscSolicitud;

    form.hdRowNum.value = cICveSolicSum
    form.target = 'FRMDatos';
    form.action = 'pg070803021.jsp';
    form.submit();
  }

