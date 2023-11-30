  function fValidaTodo(){
     form = document.forms[0];
     if (form.hdBoton.value == 'Borrar') {
       if(!confirm("¿Está Seguro de Eliminar el Registro?"))
         return false;
     }
     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
        cVMsg = '';
        var tipo = '';
        var checked = false;
        if (form.cTpoPersona[0].checked) {
           checked = true;
           tipo = 1;
        }
        if (form.cTpoPersona[1].checked) {
           checked = true;
           tipo = 2;
        }
        if (checked == false) {
           cVMsg = cVMsg + "\n - Seleccione un tipo de persona.";
        } else {
           if (!fValRFC(form.cRFC.value, tipo))
              cVMsg = cVMsg + '\n - R.F.C. Incorrecto';
        }
        if (form.iIDDGPMPT)
           cVMsg = cVMsg + fSinValor(form.iIDDGPMPT,3,'ID DGPMPT', true);
        if (form.iIDMdoTrans)
           cVMsg = cVMsg + fSinValor(form.iIDMdoTrans,3,'ID Modo de Transporte', false);
        if (form.dtRegistro)
           cVMsg = cVMsg + fSinValor(form.dtRegistro,5,'Fecha de Registro', true);
        if (form.cNombreRS)
           cVMsg = cVMsg + fSinValor(form.cNombreRS,0,'Nombre o Razón Social', true);
        if (form.cDenominacion)
           cVMsg = cVMsg + fSinValor(form.cDenominacion,0,'Denominación', true);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
        return true;
     }
     return true;
  }
  function fCambiaPersona(tipo) {
     form = document.forms[0];
     if (tipo == 'F') {
         form.cApPaterno.disabled = false;
         form.cApMaterno.disabled = false;
         form.cCURP.disabled = false;
     }
     if (tipo == 'M') {
         form.cApPaterno.disabled = true;
         form.cApMaterno.disabled = true;
         form.cCURP.disabled = true;
         form.cApPaterno.value = '';
         form.cApMaterno.value = '';
         form.cCURP.value = '';
     }
  }
  function fCambioFiltro() {
     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.target         = 'FRMDatos';
     form.action         = 'pg070502011.jsp';
     form.submit();
  }
