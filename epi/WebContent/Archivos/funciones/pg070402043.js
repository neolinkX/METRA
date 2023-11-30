  function fIrA(iCveServicio){
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.iCveServicio.value = iCveServicio;
    form.target = 'FRMDatos';
    form.action = 'pg070201012.jsp';
    form.submit();
  }