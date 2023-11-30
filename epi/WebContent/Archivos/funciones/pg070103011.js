 function fIrCatalogo(){
    fIrA('Guardar','pg070103011.jsp');
  }
  function fIrA(accion,pagina){
    form = document.forms[0];
    form.hdBoton.value = accion;
    form.target = 'FRMDatos';
    form.action = pagina;
    form.submit();
  }