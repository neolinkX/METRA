  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070308020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070308020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  function fAccion(cAccion){
    form = document.forms[0];
    form.target="_self";
    form.hdBoton.value = cAccion;
    form.submit();
  }

  function fLiberar(cPagina){
      if(confirm('Desea Realmente Liberar la Muestra ?')){
        form = document.forms[0];
        form.hdBoton.value = 'GuardarA';
        form.target = 'FRMDatos';
        form.action = cPagina;
        form.submit();
      }
  }

  function fBorrar(cPagina){
      if(confirm('Desea Realmente Borrar la Muestra ?')){
        form = document.forms[0];
        form.hdBoton.value = 'Borrar';
        form.target = 'FRMDatos';
        form.action = cPagina;
        form.submit();
      }
  }

  function fEnvio(cPagina){
      if(confirm('Desea Realmente Borrar el Envío ?')){
        form = document.forms[0];
        form.hdBoton.value = 'Guardar';
        form.target = 'FRMDatos';
        form.action = cPagina;
        form.submit();
      }
  }

function fValidaSelectores() {
    form = document.forms[0];
    cVMsg1 = "";

    if (form.iCveUniMed){
       if (form.iCveUniMed.value < 1){
         cVMsg1 += "\n - El campo 'Laboratorio' es Obligatorio, favor de seleccionar valor.";
       }
    }
    if (form.iCveLoteCualita){
       if (form.iCveLoteCualita.value < 1){
         cVMsg1 += "\n - El campo 'Lote' es Obligatorio, favor de seleccionar valor.";
       }
    }
    if (form.iCveExamCualita){
       if (form.iCveExamCualita.value < 1){
         cVMsg1 += "\n - El campo 'Exámenes' es Obligatorio, favor de seleccionar valor.";
       }
    }

    if (cVMsg1 != ''){
      alert("Datos no Válidos: \n" + cVMsg1);
      return false;
    }
    return true;
}
