
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';
    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA" ){

      if (form.cDscSituacion)
         cVMsg = cVMsg + fSinValor(form.cDscSituacion,1,'Descripción', true);

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }

      if (!confirm(" ¿ Desea Guardar la Situación ? "))
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
