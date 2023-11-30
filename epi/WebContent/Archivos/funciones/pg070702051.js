  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
       if(!confirm("¿Está Seguro de Eliminar el Registro?"))
          return false;
       else
          return true;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
       if(!confirm("¿Está Seguro de Guardar la Información?"))
          return false;
    }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.dtProgramado)
         cVMsg = cVMsg + fSinValor(form.dtProgramado,5,'Fecha de Programación', true);

      if (form.iCveTpoMantto && form.iCveTpoMantto.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar un Tipo de Mantenimiento';

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }
