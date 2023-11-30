  function fValidaBuscar(){
    var form = document.forms[0];
        cVMsg = '';
/*
    if (form.iCveUniMed){
      if (form.iCveUniMed.value <= 0){
        cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica.";
      }
    }
    if (form.SLSAlmacen){
      if (form.SLSAlmacen.value <= 0){
        cVMsg = cVMsg + "\n - Debe seleccionar el Almacén.";
      }
    }
    if (form.SLSTipoArticulo){
      if (form.SLSTipoArticulo.value <= 0){
        cVMsg = cVMsg + "\n - Debe seleccionar el Tipo de Artículo.";
      }
    }
*/
    if (cVMsg != ''){
      alert("Datos no Válidos: \n" + cVMsg);
      return false;
    }
    return true;
  }

  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';
    var iOpcion = 0;

    if (window.fValidaBuscar)
      if (!window.fValidaBuscar())
        return false;
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
     if (document.forms[0].FILClave)
       document.forms[0].FILClave.disabled = 1;
     if(objDes!=''){
        if(objDes.name!=''){
           if (opc != '4'){
             objDes.length=1;
             objDes[0].text="Cargando Datos...";
           }
           window.parent.FRMOtroPanel.location="pg070803040P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           objDes2.length=0;
           objDes2.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070803040P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
