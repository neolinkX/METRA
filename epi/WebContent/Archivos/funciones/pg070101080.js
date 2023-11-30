  function fIr(cCampoClave, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave1.value = cCampoClave;
	form.iCvePerfil.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
	form.hdLOrdenar.value = '';   // para no arrastrar el parámetro
	form.hdLCondicion.value = '';
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

  function fCambiaMdo(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}
