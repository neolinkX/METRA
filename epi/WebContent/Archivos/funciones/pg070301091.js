  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cDscTpoReact)
        cVMsg = cVMsg + fSinValor(form.cDscTpoReact,0,'Descripci�n', true);
      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'Activo', true);
        if (cVMsg != ''){
          alert("Datos no V�lidos: \n" + cVMsg);
          return false;
        }
    }
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro seleccionado?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      if(!confirm("�Est� Seguro de Guardar la Informaci�n?"))
        return false;
    }
     return true;
   }
