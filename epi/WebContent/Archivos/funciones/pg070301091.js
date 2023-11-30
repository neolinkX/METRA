  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cDscTpoReact)
        cVMsg = cVMsg + fSinValor(form.cDscTpoReact,0,'Descripción', true);
      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'Activo', true);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro seleccionado?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }
     return true;
   }
