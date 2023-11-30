  function fValidaTodo(){
    var form = document.forms[0];
    cVMsg = '';

    if (form.hdBoton.value == "Guardar"){
      if (form.modificar.value =='S') {
        if (form.lConcluido.checked) {
           form.hdLectura.value = 1;
           if (!form.lCancelado.checked) {
              //Validar Conclusion
              //alert ("Concluido si Cancelado no");
              cVMsg = cVMsg + fSinValor(form.dtRecepcion,5,'Fecha de Recepción', true);
              if (cVMsg != '') {
                 alert('Error.\n' + cVMsg);
                 return false;
              }
              return true;
           }
           alert('No es posible cancelar y concluir el Mantenimiento.\nSeleccione solo una opcion.');
           return false;
        }
        if (form.lCancelado.checked) {
           form.hdLectura.value = 1;
           if (!form.lConcluido.checked) {
              //Validar Cancelar
              //alert('Cancelar si concluir no');
              return true;
           }
        }
      } else {
         alert('Este Mantenimiento no puede ser Modificado.');
         return false;
      }
    }
    return true;
  }


  function fChgArea(fArea,numText){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 2000)
      fArea.value = cText = cText.substring(0,2000);
    if (numText == 1)
      form.iNoLetrasAcc.value = 2000 - cText.length;
    if (numText == 2)
      form.iNoLetrasRes.value = 2000 - cText.length;
    if (numText == 3)
      form.iNoLetrasObs.value = 2000 - cText.length;
  }
