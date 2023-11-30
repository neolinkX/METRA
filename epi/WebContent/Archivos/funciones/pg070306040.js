function fBuscar() {
     form = document.forms[0];
     form.target = "_self";
     var pasa = true;
     var tienevalor = false;
     var msg = "Error.\n";

     //Validar las fechas
     if (form.chkfprep.checked && !fValFecha(form.dtPreparacion.value)) {
	pasa = false;
        msg += "Fecha de Preparación no valida\n";
     }
     
     if (form.chkfcad.checked && !fValFecha(form.dtCaducidad.value)) {
	pasa = false;
        msg += "Fecha de Caducidad no valida\n";
     }

     if (form.chkSituacion.checked) {
        for(i = 0; i < 3; i++) {
            if (form.situacion[i].checked) {
                tienevalor = true;
             }
        }
        if (!tienevalor) {
            pasa = false;
            msg += "Seleccione una situación de la Sustancia\n";
        }
     }

     if (pasa) {
        form.hdBuscar.value='S';
        form.submit();
     } else {
        alert(msg);
     }
}

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
 }

  function fIrCatalogo(cCampoClave, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070306041.jsp';
    form.submit();
  }
