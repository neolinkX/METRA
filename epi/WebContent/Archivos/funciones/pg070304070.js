
  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

      if (!confirm(" � Desea Autorizar el An�lisis de las Muestras ? "))
         return false;

    }
    return true;
  }
