  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'BorrarB') {
      if(!confirm("�Est� Seguro de deshabilitar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      if(!confirm("�Est� Seguro de Guardar la Informaci�n?"))
        return false;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

      if (form.cDscSustancia)
         cVMsg = cVMsg + fSinValor(form.cDscSustancia,2,'Descripci�n', true);

      if (form.cTitRepConf)
         cVMsg = cVMsg + fSinValor(form.cTitRepConf,2,'T�tulo de Lote Confirmatorio', true);

      if (form.cPrefLoteConf)
         cVMsg = cVMsg + fSinValor(form.cPrefLoteConf,2,'Identificador de Lote Confirmatorio', true);

      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }
    }

    return true;
  }