
  function fValidaTodo(){
    var form = document.forms[0];
       cVMsg = '';
    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       if (document.forms[0].dtAutorizacion){
          if (document.forms[0].dtAutorizacion.value != '')
           cVMsg = cVMsg + fSinValor(document.forms[0].dtAutorizacion,5,'Fecha de Autorización:', true);
       }

       if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
       }

       if (!confirm(" ¿ Desea Guardar la Autorización del Inventario de los Artículos del Almacén ? "))
         return false;
    }

    return true;
  }

  function llenaSLT(opc,val1,val2,val3,objDes,objDes2,objDes3){

     if(objDes3!=''){
        if(objDes3.name!=''){
           objDes3.length=1;
           objDes3[0].text="Seleccione...";
        }
     }
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
           window.parent.FRMOtroPanel.location="pg070802051P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&objDes2=" + objDes3.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070802051P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&objDes2=" + objDes3.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fListado(rwId){
     forma = document.forms[0];

     forma.hdIni.value    = rwId;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070802051.jsp';
     forma.submit();
  }
