
  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

      if (form.TBXAutorizacion){
         if (form.TBXAutorizacion.checked == true){
           if (!confirm(" ¿ Desea Procesar la Autorización del Lote Confirmatorio ? "))
           return false;
         } else {
           if (!confirm(" ¿ Desea Procesar la Eliminación del Análisis del Lote Confirmatorio ? "))
           return false;
         }
      }
    }


    return true;
  }
