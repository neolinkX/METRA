  function fChgArea500(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 499)
      fArea.value = cText = cText.substring(0,499);
    form.iNoLetras[0].value = 499 - cText.length;
  }

  function fChgArea2000(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras[1].value = 1999 - cText.length;
  }


  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Nuevo'){
      return true;
    }
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = "";

      if(form.cDscRequisito.value=='')
        cVMsg += "\n - Escriba una descripción.";
             
      if(cVMsg!=""){
        alert('Datos no Válidos: \n' + cVMsg);
        return false; 
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

