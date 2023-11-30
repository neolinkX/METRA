  function fIrProcXUM(cCampoClave1, cCampoClave2, cCampoClave3, cCampoClave4, cCampoClave5){
    form = document.forms[0];
    form.iCveUsuario.value = cCampoClave1;
    form.cApPaterno.value = cCampoClave2;
    form.cApMaterno.value = cCampoClave3;
    form.cNombre.value = cCampoClave4;
    form.cUsuario.value = cCampoClave5;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071003061.jsp';
    form.submit();
  }

  function fIrServRama(cCampoClave1, cCampoClave2, cCampoClave3, cCampoClave4, cCampoClave5){
    form = document.forms[0];
    form.iCveUsuario.value = cCampoClave1;
    form.cApPaterno.value = cCampoClave2;
    form.cApMaterno.value = cCampoClave3;
    form.cNombre.value = cCampoClave4;
    form.cUsuario.value = cCampoClave5;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071003062.jsp';
    form.submit();
  }
