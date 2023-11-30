  function fIrCatalogo(iCveVehiculo,cPagina) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.hdCampoClave.value = iCveVehiculo;
    form.hdRowNum.value = iCveVehiculo;
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }