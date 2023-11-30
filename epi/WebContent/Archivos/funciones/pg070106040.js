  function fIrDetalle(cCampoClave1, cCampoClave2, cCampoClave3, cCampoClave4){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdCampoClave4.value = cCampoClave4;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070106041.jsp';
    form.submit();
  }
