
  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

      if (form.TBXAutorizacion){
         if (form.TBXAutorizacion.checked == true){
           if (!confirm(" � Desea Procesar la Autorizaci�n del Lote Confirmatorio ? "))
           return false;
         } else {
           if (!confirm(" � Desea Procesar la Eliminaci�n del An�lisis del Lote Confirmatorio ? "))
           return false;
         }
      }
    }


    return true;
  }
