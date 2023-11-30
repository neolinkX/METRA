  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070704010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070704010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
    }
  }

  function fValidaBuscar(){
    cVMsg = '';
    form = document.forms[0];    

    if(form.iCveUniMed.value<1)
      cVMsg = cVMsg + "\n - Seleccione una Unidad Médica.";

    if(form.iAnio.value<1)
      cVMsg = cVMsg + "\n - Seleccione un año.";

    if(cVMsg!=""){
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else
      return true;
  }

