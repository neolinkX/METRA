  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras.value = 1999 - cText.length;
  }

  function fChgArea2(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 249)
      fArea.value = cText = cText.substring(0,249);
    form.iNoLetras2.value = 249 - cText.length;
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      
      if(form.cCIE.value=='')
        cVMsg = cVMsg + "\n - Escriba el campo CIE.";
      
      if(form.cDscDiagnostico.value=='')
        cVMsg = cVMsg + "\n - Escriba un diagnóstico.";
      
      if(form.cDscBreve.value=='')
        cVMsg = cVMsg + "\n - Escriba una descripción breve.";
      
      if(form.cObservacion.value=='')
        cVMsg = cVMsg + "\n - Escriba una observación.";      
      
      if(cVMsg!=""){
        alert('Datos no Válidos: \n' + cVMsg);
        return false; 
      }
      else{
        return true
      }
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

