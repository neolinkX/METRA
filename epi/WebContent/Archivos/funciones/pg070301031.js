
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA" ){

      if (form.iCveTipoRecep){
        if (form.iCveTipoRecep.length <= 0)
          cVMsg = cVMsg + "\n - La Lista de Selecci�n para la Descripci�n del Tipo No tiene Elementos.";
        if (form.iCveTipoRecep.value <= 0)
          cVMsg = cVMsg + "\n - Debe seleccionar la Descripci�n del Tipo.";
      } else
        cVMsg = cVMsg + "\n - La Lista de Selecci�n para la Descripci�n del Tipo No tiene Elementos.";

      if (form.cDscMotRecep)
         cVMsg = cVMsg + fSinValor(form.cDscMotRecep,2,'Descripci�n del Motivo', true);

      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }

      if (!confirm(" � Desea Guardar el Motivo de Recepci�n ? "))
        return false;
    }

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Eliminar el Registro?"))
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