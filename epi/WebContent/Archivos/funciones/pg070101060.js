  
  function fillNext(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    form.submit();
  }
  
  function fillBusca(){
    form = document.forms[0];
    form.hdBoton.value = "Buscar";
    form.target =  "FRMDatos";
    form.submit();
  }

  function fIrCatalogo(cCampoClave){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.iCveSintoma.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101061.jsp';
    form.submit();
  }

  function fReordenar(cCampoClave1, cCampoClave2){
    form = document.forms[0];
    form.iCveServicio.value = cCampoClave1;
    form.iCveRama.value = cCampoClave2;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101062.jsp';
    form.submit();
  }
  
 
 form.hdBoton.value = 'Actual';
