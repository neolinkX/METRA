  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if(form.cDscTpoPlantilla.value==''){
        cVMsg = cVMsg + "\n - Escriba la Descripci�n.";
      }

      if(form.cDscBreve.value==''){
        cVMsg = cVMsg + "\n - Escriba la Descripci�n Breve.";
      }

      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
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
