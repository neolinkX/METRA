    function activa(actual, rama) {
        form = document.forms[0];
            for (var i=0; i < form.elements.length; i++) {
                    nombre = form.elements[i].name;
                    sSint = "checkSintoma";
                    sintoma = nombre.substring(12,nombre.indexOf("_")); // 12 es la longitud de 'checkSintoma'
                    if (sintoma==rama) {
                            form.elements[i].checked=actual.checked;
                    }
            }
    }

    function activaX(actual, rama) {
        form = document.forms[0];
            var idx=0;
            var check=0;
            for (var i=0; i < form.elements.length; i++) {
                    nombre = form.elements[i].name;
                    sintoma = nombre.substring(12,nombre.indexOf("_")); // 12 es la longitud de 'checkSintoma'
                    if (sintoma==rama) {
                         rama = eval('form.checkRama'+rama);
                         rama.checked = actual.checked;
                    }
            }
    }

function truncar(campo){
  campo.value=campo.value.substring(0,499);
return true;
}

function validaNumero(campo){
  if(isNaN(campo.value)) {
    alert("Debe escribir un número");
    campo.focus();
    return false;
  }
  return true;
}

function fBuscar(){
  form = document.forms[0];
  var cVMsg = "";
  if (form.iCveServicio) {
        if (form.iCveServicio.value==-1) {
           cVMsg += "\n - El campo 'Servicio' es Obligatorio, favor de seleccionar.";
        }
  }
  if (cVMsg != ''){
      alert("Datos no Válidos: \n" + cVMsg);
  }  else {
          form.hdBoton.value = 'Actual';
          form.tpoBusqueda.value = 'buscarPorServicio';
          form.target = "_self";
          form.submit();
  }
}

  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras.value = 1999 - cText.length;
  }