  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir();

    if (form.hdBoton.value == 'Borrar' || form.hdBoton.value == 'BorrarB') {
      if(!confirm("�Est� Seguro de deshabilitar el Registro?"))
        return false;
    }

    if ((form.hdBoton.value == 'Guardar') || (form.hdBoton.value == 'GuardarA')){
      if(!confirm("�Est� Seguro de Guardar la Informaci�n?"))
        return false;
    }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cDscMarcaSust)
        cVMsg = cVMsg + fSinValor(form.cDscMarcaSust,2,'Descripci�n', true);
      if (cVMsg != ''){
        alert("Datos no V�lidos: \n" + cVMsg);
        return false;
      }
    }
     return true;
   }
