  function fValidaTodo(){
     form = document.forms[0];
     cVMsg = '';
     if (form.hdBoton.value == 'Guardar') {
        if(!confirm("¿Está Seguro de Guardar la Información?"))
           return false;
        else {
           if (form.dtSolicitud)
              cVMsg = cVMsg + fSinValor(form.dtSolicitud,5,'Fecha', true);
           if (form.iCveEtapa)
             cVMsg = cVMsg + fSinValor(form.iCveEtapa,3,'Etapa', true);
           if (form.iCveSolicitante)
             cVMsg = cVMsg + fSinValor(form.iCveSolicitante,3,'Solicitante', true);
           if (form.esExterno.value == 'S')
             cVMsg = cVMsg + fSinValor(form.cSolicitante,2,'Nombre Externo', true);
           if (form.iCveUsuReg)
             cVMsg = cVMsg + fSinValor(form.iCveUsuSolicita,3,'Usuario', true);
           if (cVMsg != ''){
             alert("Datos no Válidos: \n" + cVMsg);
             return false;
           }

        }
     }
     return true;
  }

  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 250)
      fArea.value = cText = cText.substring(0,250);
    form.iNoLetras.value = 250 - cText.length;
  }

  function fValidaExterno(aExt, iCve) {
     form = document.forms[0];
     var clave = iCve.value;
     var bExterno = false;
     var i = 0;
     for (i = 0; i < aExt.length; i++) {
//        alert(aExt[i]);
        if (clave == aExt[i]) {
           bExterno = true;
        }
     }
     if (bExterno) {
        form.esExterno.value = 'S';
        form.cSolicitante.disabled = false;
     } else {
        form.esExterno.value = 'N';
        form.cSolicitante.disabled = true;
     }
  }