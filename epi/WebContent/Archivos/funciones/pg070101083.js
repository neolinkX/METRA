function fIr(cPropiedad, cPagina){
    form = document.forms[0];
//    form.hdCampoClave.value = cCampoClave;
//    form.hdCampoClave1.value = cCampoClave;
//	form.iCvePerfil.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
//    form.hdRowNum.value = cCampoClave;
	form.hdLOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

function fSubmit(cAccion){
    form = document.forms[0];
    form.hdBoton.value = cAccion;
    form.target = 'FRMDatos';
    //form.action = cPagina;
    form.submit();
  }

function fChgTog(asignar, alarma){
  // alarma se habilita si asignar se marca
  // alarma se deshabilita si asignar se desmarca
  alarma.disabled = !asignar.checked;
}
function fCambia(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

function fCambiaPerfil(cValor){
  form = document.forms[0];
  form.iCveEspecialidad.value = 0; // reset
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}
