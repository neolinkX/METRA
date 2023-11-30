
  function llenaSLT(opc,val1,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502040P.jsp?opc=" + opc + "&val1=" + val1 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502040P.jsp?opc=" + opc + "&val1=" + val1 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function llenaFecha(opc,val1,val2,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           //objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502040P.jsp?opc=" + opc + "&val1=" + val1  + "&val2=" + val2 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502040P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fListado(){

     if (confirm(" ¿ Desea Generar Las Plantillas con la Información Proporcionada ? ")){
       forma = document.forms[0];
       //forma.hdIni.value    = rwId;
       forma.hdBoton.value  = 'Generar';
       forma.target         = 'FRMDatos';
       forma.action         = 'pg070502040.jsp';
       forma.submit();
     }
  }




