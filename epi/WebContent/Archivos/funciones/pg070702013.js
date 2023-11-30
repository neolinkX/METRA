  function fIrCatalogo(iCveVehiculo,iCveMantenimiento,cPagina) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.hdCampoClave.value = iCveVehiculo;
    form.hdRowNum.value = iCveVehiculo;
    form.iCveVehiculo.value = iCveVehiculo;
    form.iCveMantenimiento.value = iCveMantenimiento;
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }