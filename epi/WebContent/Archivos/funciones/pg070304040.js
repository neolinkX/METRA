
  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarP" ){

       if (!confirm(" � Desea Guardar el Resultado de la Calibraci�n del Equipo ? "))
         return false;
    }


    return true;
  }
