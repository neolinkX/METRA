
function fValidaTodo() {
  var form = document.forms[0];
  if (form.hdBoton.value != 'Cancelar') {
        cVMsg = '';
    if (form.iCodigo) {
      cVMsg = cVMsg + fSinValor(form.iCodigo, 0, 'No. de código',true);
    }
    if (form.cDscReactivo) {
      cVMsg = cVMsg + fSinValor(form.cDscReactivo, 0, 'Descripción',true);
    }
    if (form.cDscBreve) {
      cVMsg = cVMsg + fSinValor(form.cDscBreve, 0, 'Breve',true);
    }
    if (form.dtPreparacion) {
      cVMsg = cVMsg + fSinValor(form.dtPreparacion, 5, 'Fecha',true);
    }
    if (form.dVolumen) {
      cVMsg = cVMsg + fSinValor(form.dVolumen, 4, 'Volumen',true);
    }
    if (form.iViales) {
      cVMsg = cVMsg + fSinValor(form.iViales, 3, 'No. de viales',true);
    }
    if (form.dtCaducidad) {
      cVMsg = cVMsg + fSinValor(form.dtCaducidad, 5, 'Caducidad',true);
    }
    if (form.dtAutoriza) {
      cVMsg = cVMsg + fSinValor(form.dtAutoriza, 5, 'Autorizado',true);
    }
    if (cVMsg != '') {
      alert("Datos no válidos: \n" + cVMsg);
      return false;
    }
  }
  return true;
}

function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1999)
    fArea.value = cText = cText.substring(0,1999);
  form.iNoLetras.value = 1999 - cText.length;
}

function fChgTog(){
  form = document.forms[0];
  if(form.lAgotado){
     form.dtAgotado.disabled = !form.lAgotado.checked;
     if(!form.lAgotado.checked){
       form.dtAgotado.value = '';
     }
  }
}

function voidfChgTog(){} 
