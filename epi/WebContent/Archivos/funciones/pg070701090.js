  function fIrDetalle(cCampoClave1){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070701091.jsp';
    form.submit();
  }
  function fIrControles(cCampoClave1){
    form = document.forms[0];
    form.iCveEtapaSolic.value = cCampoClave1;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070701092.jsp';
    form.submit();
  }
  function fValidaIrA(){
    form = document.forms[0];
    form.iCveEtapaSolic.value = 0;
    return true;
  }
