function fHabilitafecha(){
   form = document.forms[0];

   if (form.lAgotado.checked){
     form.dtAgotado.disabled = false;
   }
   else{
      form.dtAgotado.value = "";
      form.dtAgotado.disabled = true;
   }
  

}



  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070306021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato válido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070306021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

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
     alert("No es posible borrar los registros.")
     return false;
      }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA' ){
      }

      if (form.iCveCtrolCalibra)
        cVMsg = cVMsg + fSinValor(form.iCveCtrolCalibra,3,'iCveCtrolCalibra', false);

      if (form.iAnio)
        cVMsg = cVMsg + fSinValor(form.iAnio,3,'Año:', false);
      if (form.cLote)
        cVMsg = cVMsg + fSinValor(form.cLote,0,'Lote', true);
      if (form. cDscCtrolCalibra)
        cVMsg = cVMsg + fSinValor(form.cDscCtrolCalibra,0,'La Descripción ', true);
      if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,0,'La Descripción Breve ', true);
      if (form.iCveTpoReact && form.iCveTpoReact.selectedIndex <= 0)
        cVMsg = cVMsg + "\n - Debe seleccionar un Tipo de Reactivo";

      if (form.iCveReactivo && form.iCveReactivo.selectedIndex <= 0)
        cVMsg = cVMsg + "\n - Debe seleccionar un Reactivo";
      if (form.iCveSustancia)
        cVMsg = cVMsg + fSinValor(form.iCveSustancia,2,'La droga ', true);
      if (form.dVolumen)
        cVMsg = cVMsg + fSinValor(form.dVolumen,4,'El Volumen', true);
      if (form.dConcentracion)
        cVMsg = cVMsg + fSinValor(form.dConcentracion,4,'La Concentracion', true);
      if (form.iCveEmpleoCalib)
        cVMsg = cVMsg + fSinValor(form.iCveEmpleoCalib,2,'El empleo ', true);
      if (form.lCuantCual)
        cVMsg = cVMsg + fSinValor(form.lCuantCual,3,'lCuantCual:', false);
      if (form.iViales)
        cVMsg = cVMsg + fSinValor(form.iViales,3,'El número de viales ', true);
      if (form.dtPreparacion)
        cVMsg = cVMsg + fSinValor(form.dtPreparacion,5,'La fecha de preparación ', true);
      if (form.dtCaducidad)
        cVMsg = cVMsg + fSinValor(form.dtCaducidad,5,'La fecha de caducidad', true);
      if (form.dtAutoriza)
        cVMsg = cVMsg + fSinValor(form.dtAutoriza,5,'La fecha de autorización', true);
      if (form.iCveUsuAutoriza)
        cVMsg = cVMsg + fSinValor(form.iCveUsuAutoriza,3,'El usuario que autoriza ', true);
     /* if (form.dtAgotado)
        cVMsg = cVMsg + fSinValor(form.dtAgotado,5,'dtAgotado:', false);*/
      if (form.dtBaja)
        cVMsg = cVMsg + fSinValor(form.dtBaja,5,'dtBaja:', false);
      if (form.iAnioBaja)
        cVMsg = cVMsg + fSinValor(form.iAnioBaja,3,'iAnioBaja:', false);
      if (form.iCveBaja)
        cVMsg = cVMsg + fSinValor(form.iCveBaja,3,'iCveBaja', false);
      if (form.iCveMarcaSust)
        cVMsg = cVMsg + fSinValor(form.iCveMarcaSust,3,'iCveMarcaSust:', true);
      if (form.iCveUsuPrepara)
        cVMsg = cVMsg + fSinValor(form.iCveUsuPrepara,3,'El usuario que prepara ', true);

   if (form.dtAgotado){
      if (form.lAgotado.checked || form.lBaja.checked){
        if (form.dtAgotado){
          cVMsg = cVMsg + fSinValor(form.dtAgotado,5,'La fecha en que se agoto la sustancia', true);
        }
       }
   }


        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }

      if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA' ) {
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }

     return true;
   }