  function fSubmite(cValor){
    form = document.forms[0];
    form.hdBoton.value = cValor;
    form.target="_self";
    form.submit();
  }