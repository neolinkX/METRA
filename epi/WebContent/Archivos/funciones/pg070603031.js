  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
       if (!form.Borrar){
          alert('Verifique el etatus del Mantenimiento puede ser que este Cancelado o Concluido!');
          return false;
       }
       else{
          if(!confirm("¿Está Seguro de Eliminar el Registro?"))
             return false;
          else
             return true;
       }
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.dtSolicitud)
         cVMsg = cVMsg + fSinValor(form.dtSolicitud,5,'Fecha de Solicitud', true);
      if (form.iCveTpoMantto && form.iCveTpoMantto.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar un Tipo de Mantenimiento';
      if (form.iCveMotivo && form.iCveMotivo.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar un Motivo';


      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }
