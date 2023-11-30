  function llenaSLT(opc,val1,val2,val3,objDes,objDes2){
     if(objDes2!=''){
        if(objDes2.name!=''){
           objDes2.length=1;
           objDes2[0].text="Seleccione...";
        }
     }
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070802060P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070802060P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fListado(rwId){
     forma = document.forms[0];

     forma.hdIni.value    = rwId;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802021.jsp';
     forma.submit();
  }

  function fDetalle(iCveArt) {
     forma = document.forms[0];
     forma.hdBoton.value  = 'Actual';
     forma.iCveArticulo.value = iCveArt;
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802061.jsp';
     forma.submit();
  }

  function fLote(iCveArt) {
     forma = document.forms[0];
     forma.hdBoton.value  = 'Actual';
     forma.iCveArticulo.value = iCveArt;
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802062.jsp';
     forma.submit();
  }

  function fUbicacion(iCveArt) {
     forma = document.forms[0];
     forma.hdBoton.value  = 'Actual';
     forma.iCveArticulo.value = iCveArt;
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802063.jsp';
     forma.submit();
  }

  function fMovimiento(iCveArt) {
     forma = document.forms[0];
     forma.hdBoton.value  = 'Actual';
     forma.iCveArticulo.value = iCveArt;
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802064.jsp';
     forma.submit();
  }
