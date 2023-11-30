  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'BorrarB') {
      if(!confirm("¿Está Seguro de deshabilitar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

      if (form.cDscSustancia)
         cVMsg = cVMsg + fSinValor(form.cDscSustancia,2,'Descripción', true);

      if (form.cTitRepConf)
         cVMsg = cVMsg + fSinValor(form.cTitRepConf,2,'Título de Lote Confirmatorio', true);

      if (form.cPrefLoteConf)
         cVMsg = cVMsg + fSinValor(form.cPrefLoteConf,2,'Identificador de Lote Confirmatorio', true);

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }

    return true;
  }