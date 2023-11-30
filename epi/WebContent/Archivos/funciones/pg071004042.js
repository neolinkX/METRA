  function fIrDetalle(cCampoClave1, cCampoClave2, cCampoClave3, cCampoClave4){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdCampoClave4.value = cCampoClave4;
    form.iOrden.value = cCampoClave4;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071004043.jsp';
    form.submit();
  }
  
   function fIrVigencia(cCampoClave1, cCampoClave2, cCampoClave3){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071004042.jsp';
    form.submit();
  }

