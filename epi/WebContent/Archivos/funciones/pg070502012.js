  function fIrDetalle(cCampoClave1, cCampoClave2){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070502013.jsp';
    form.submit();
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

  function fRegSel(cSel){
    form = document.forms[0];
    form.hdSel.value = cSel;
  }
