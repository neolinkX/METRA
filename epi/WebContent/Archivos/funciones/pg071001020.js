
  function fIr(iCveUsuario, iCveUniMed, iCveProceso){
    form = document.forms[0];
    form.hdCampoClave.value = iCveUsuario;
    form.hdiCveUniMed.value = iCveUniMed;
    form.hdiCveProceso.value = iCveProceso;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071001021.jsp';
    form.submit();
  }
