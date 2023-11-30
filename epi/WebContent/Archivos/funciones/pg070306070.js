 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}


  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA' ) {
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iAnio)
        cVMsg = cVMsg + fSinValor(form.iAnio,3,'', false);
      if (form.iCveLaboratorio)
        cVMsg = cVMsg + fSinValor(form.iCveLaboratorio,3,'', false);
      if (form.iCveValPres)
        cVMsg = cVMsg + fSinValor(form.iCveValPres,3,'', false);
      if (form.iCveCtrolCalibra)
        cVMsg = cVMsg + fSinValor(form.iCveCtrolCalibra,3,'', false);
      if (form.iCveEquipo)
        cVMsg = cVMsg + fSinValor(form.iCveEquipo,3,'', false);
      if (form.iCveSustancia)
        cVMsg = cVMsg + fSinValor(form.iCveSustancia,3,'', false);
      if (form.dtEstudio)
        cVMsg = cVMsg + fSinValor(form.dtEstudio,5,'', false);
      if (form.dCorte)
        cVMsg = cVMsg + fSinValor(form.dCorte,4,'', false);
      if (form.dCorteneg)
        cVMsg = cVMsg + fSinValor(form.dCorteneg,4,'', false);
      if (form.dCortePost)
        cVMsg = cVMsg + fSinValor(form.dCortePost,4,'', false);
      if (form.cObservacion)
        cVMsg = cVMsg + fSinValor(form.cObservacion,2,'', false);
      if (form.iCveusuResp)
        cVMsg = cVMsg + fSinValor(form.iCveusuResp,3,'', false);


        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }


  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070306021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070306021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }


