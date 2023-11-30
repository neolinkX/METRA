  function fIrCatalogo(cCampoClave1, cCampoClave2,cCampoClave3,cCampoClave4){
    form = document.forms[0];
    form.iNumPersonal.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070502035.jsp';
    form.submit();
  }