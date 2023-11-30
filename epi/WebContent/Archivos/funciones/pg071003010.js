  function fIrDetalle(cCampoClave1){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071003011.jsp';
    form.submit();
  }

  function fIrModulos(cCampoClave1){
    form = document.forms[0];
    form.iCveUniMed.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071003030.jsp';
    form.submit();
  }


