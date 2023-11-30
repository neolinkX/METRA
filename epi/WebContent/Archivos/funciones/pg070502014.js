  function fValidaTodo() {
     form = document.forms[0];
     if (form.hdBoton.value == 'Guardar')
	 if (confirm(" ¿ Desea Guardar los Cambios? ")) {
              form = document.forms[0];
              form.hdBoton.value  = 'Actual';
              form.GuardaRepresentante.value  = 'S';
              form.target         = 'FRMDatos';
              form.action         = 'pg070502014.jsp';
              form.submit();
         }
  }
  function fCambioFiltro() {
     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.target         = 'FRMDatos';
     form.action         = 'pg070502014.jsp';
     form.submit();
  }
  function fDetalle(iCveEmp, iCveRepr) {
     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.iCveEmpresa.value = iCveEmp;
     form.iCveRepresentante.value = iCveRepr;
     form.hdRowNum.value = iCveRepr;
     form.target         = 'FRMDatos';
     form.action         = 'pg070502015.jsp';
     form.submit();
  }
  function fGuardarR() {
     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.GuardaRepresentante.value  = 'S';
     form.target         = 'FRMDatos';
     form.action         = 'pg070502014.jsp';
     form.submit();
  }