  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está seguro de desactivar el registro?"))
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
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
        var msg = "Error. \n";
        var pasa = true;
        if (form.cDscRama.value == '') {
            msg += "\tProporcione una Rama.\n";
            pasa = false;
        }
        if (!pasa) {
            alert(msg);
        }
        return pasa;
    }
    return true;
  }
    function fChgArea(fArea){
        form = document.forms[0];
        cText = fArea.value;
        if(cText.length > 1999)
            fArea.value = cText = cText.substring(0,1999);
        form.iNoLetras.value = 1999 - cText.length;
    }
  function fCambiaServicio() {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101051.jsp';
    form.submit();
  }