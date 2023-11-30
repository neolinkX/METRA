  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070602010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070602010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
  function fCambioFiltro() {
     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.target         = 'FRMDatos';
     form.action         = 'pg070602010.jsp';
     form.submit();
  }
  function fDetalle(iCveEq, iPag) {
     var cPag = '';
     if (iPag == 1)
        cPag = 'pg070602011.jsp';
     if (iPag == 2)
        cPag = 'pg070602012.jsp';
     if (iPag == 3)
        cPag = 'pg070602013.jsp';

     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.iCveEquipo.value = iCveEq;
     form.hdRowNum.value = iCveEq;
     form.target         = 'FRMDatos';
     form.action         = cPag;
     form.submit();
  }