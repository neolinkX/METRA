  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
       if(!confirm("�Est� Seguro de Eliminar el Registro?"))
          return false;
       else
          return true;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.dtProgramado)
         cVMsg = cVMsg + fSinValor(form.dtProgramado,5,'Fecha de Programaci�n', true);
      if (form.iCveTpoMantto && form.iCveTpoMantto.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar un Tipo de Mantenimiento';
      if (form.iCveMotivo && form.iCveMotivo.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar un Motivo';
      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }
