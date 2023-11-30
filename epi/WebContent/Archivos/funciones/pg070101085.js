  function fIr(cCampoClave, cCampoClave2, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave2;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

function fCambiaPerfil(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}
