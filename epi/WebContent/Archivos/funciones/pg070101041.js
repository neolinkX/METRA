  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

      if (form.iCveServicio)
        cVMsg = cVMsg + fSinValor(form.iCveServicio,3,'Clave:', false);

      if (form.cDscServicio)
        cVMsg = cVMsg + fSinValor(form.cDscServicio,2,'Descripción:', true);
      if (form.cObservacion)
        cVMsg = cVMsg + fSinValor(form.cObservacion,0,'Observaciones:', false);

      if (form.lVariosMeds)
        cVMsg = cVMsg + fSinValor(form.lVariosMeds,3,'Realizado Por Varios Médicos', false);

      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'lActivo', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1999)
    fArea.value = cText = cText.substring(0,1999);
  form.iNoLetras.value = 1999 - cText.length;
}
