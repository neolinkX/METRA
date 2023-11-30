  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCvePerfil)
        cVMsg = cVMsg + fSinValor(form.iCvePerfil,3,'Perfil', false);
      if (form.iCveEspecialidad)
        cVMsg = cVMsg + fSinValor(form.iCveEspecialidad,3,'Especialidad', false);
      if (form.cEspecificacion)
        cVMsg = cVMsg + fSinValor(form.cEspecificacion,0,'Especificacion', true);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
		// limitar el tamaño del campo Especificación
		if(form.cEspecificacion.value.length()>=2000){
			form.cEspecificacion.value=form.cEspecificacion.value.substring(0,1999);
		}
    }
     return true;
   }

  function fIr(cCampoClave, cPropiedad, cPagina){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 1999)
      fArea.value = cText = cText.substring(0,1999);
    form.iNoLetras.value = 1999 - cText.length;
  }
