  function fIrDetalle(cCampoClave1){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070701051.jsp';
    form.submit();
  }
