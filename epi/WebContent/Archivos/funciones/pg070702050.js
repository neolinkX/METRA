  function fIrCatalogo(iCveEquipo,cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = iCveEquipo;

    form.hdRowNum.value = iCveEquipo;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }
