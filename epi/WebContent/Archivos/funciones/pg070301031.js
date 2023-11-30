
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA" ){

      if (form.iCveTipoRecep){
        if (form.iCveTipoRecep.length <= 0)
          cVMsg = cVMsg + "\n - La Lista de Selección para la Descripción del Tipo No tiene Elementos.";
        if (form.iCveTipoRecep.value <= 0)
          cVMsg = cVMsg + "\n - Debe seleccionar la Descripción del Tipo.";
      } else
        cVMsg = cVMsg + "\n - La Lista de Selección para la Descripción del Tipo No tiene Elementos.";

      if (form.cDscMotRecep)
         cVMsg = cVMsg + fSinValor(form.cDscMotRecep,2,'Descripción del Motivo', true);

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }

      if (!confirm(" ¿ Desea Guardar el Motivo de Recepción ? "))
        return false;
    }

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }

    return true;
  }

  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras.value = 1999 - cText.length;
  }