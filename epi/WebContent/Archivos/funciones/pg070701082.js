  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
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

  function fValidaIrA(){   
    var form = document.forms[0];
    form.hdEmpMantto.value = form.iCveEmpMantto.value;
    return true;
  }