  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir();

    if (form.hdBoton.value == 'Borrar' || form.hdBoton.value == 'BorrarB') {
      if(!confirm("¿Está Seguro de deshabilitar el Registro?"))
        return false;
    }

    if ((form.hdBoton.value == 'Guardar') || (form.hdBoton.value == 'GuardarA')){
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cDscMarcaSust)
        cVMsg = cVMsg + fSinValor(form.cDscMarcaSust,2,'Descripción', true);
      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
    }
     return true;
   }
