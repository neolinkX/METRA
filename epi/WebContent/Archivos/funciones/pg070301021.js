
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';
    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA" ){

      if (form.cDscTipoRecep)
         cVMsg = cVMsg + fSinValor(form.cDscTipoRecep,1,'Tipo de Recepci�n', true);

      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }

      if (!confirm(" � Desea Guardar el Tipo de Recepci�n ? "))
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
