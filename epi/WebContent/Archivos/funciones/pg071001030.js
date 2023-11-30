  function fIr(iCveUsuario, iCveUniMed, iCveProceso, iCveModulo, iCveServicio, iCveRama){
    form = document.forms[0];
    form.hdCampoClave.value = iCveUsuario;
    form.hdiCveUniMed.value = iCveUniMed;
    form.hdiCveProceso.value = iCveProceso;
    form.hdiCveModulo.value = iCveModulo;
    form.hdiCveServicio.value = iCveServicio;
    form.hdiCveRama.value = iCveRama;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg071001031.jsp';
    form.submit();
  }
  
    
  function fSelectedPer(iCveUsuario){
    var form = document.forms[0];
    form.hdiCveUsuario.value = iCveUsuario;
    form.hdBoton.value = "Nuevo";
    form.target = "_self";
    form.submit();
  }
