  function fIrDetalle(cCampoClave1){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071003021.jsp';
    form.submit();
  }
  function fIrAsignar(cCampoClave1){
    form = document.forms[0];
    form.iCveArea.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071003033.jsp';
    form.submit();
  }
