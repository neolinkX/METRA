  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 249)
      fArea.value = cText = cText.substring(0,249);
    form.iNoLetras.value = 249 - cText.length;
  }
  function fChgArea2(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 499)
      fArea.value = cText = cText.substring(0,499);
    form.iNoLetras2.value = 499 - cText.length;
  }
  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cvMsg = '';
      if(form.cDscArticulo.value.length == 0)
        cvMsg += '\nDebe proporcionar la descripci�n del art�culo.';
      if(form.cDscBreve.value.length == 0)
        cvMsg += '\nDebe proporcionar la descripci�n breve del art�culo.';
      if(form.hdBoton.value == 'Guardar'  ){
      if(form.iCveTpoArticulo.selectedIndex <= 0)
        cvMsg += '\nDebe seleccionar el tipo de art�culo.';
      if(form.iCveFamilia.selectedIndex <= 0)
        cvMsg += '\nDebe seleccionar la familia del art�culo.';
      }
      
      if (cvMsg != ''){
         alert("Datos no V�lidos: \n" + cvMsg);
         return false;
      }
      if(!confirm("�Est� Seguro de Guardar la Informaci�n?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
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
           window.parent.FRMOtroPanel.location="pg070802011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        }
        else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    }
    else {
       window.parent.FRMOtroPanel.location="pg070802011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
