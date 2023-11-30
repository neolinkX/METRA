  function fIrCatalogo(cCampoClave, cCampoClave2) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.hdCampoClave1.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdRowNum.value = cCampoClave;
    form.target = 'FRMDatos';
    form.action = 'pg070101051.jsp';
    form.submit();
  }
  function fIrReordenar() {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101052.jsp';
    form.submit();
  }
  function fCambiaServicio() {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101050.jsp';
    form.submit();
  }
  function fConfig(rama) {
    form = document.forms[0];
    form.iCveRama.value = rama;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101060.jsp';
    form.submit();
  }