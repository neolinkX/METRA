  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {

      cVMsg = '';

      if(form.iCveUniMed.value < 1)
        cVMsg = cVMsg + "\n - Seleccione una Unidad M�dica.";

      if(form.iCveProceso.value < 1)
        cVMsg = cVMsg + "\n - Seleccione un Proceso.";

      if(form.iCveModulo.value < 1)
        cVMsg = cVMsg + "\n - Seleccione un M�dulo.";

      if(form.iCveServicio.value < 1)
        cVMsg = cVMsg + "\n - Seleccione un Servicio.";

      if(cVMsg!=''){
        alert('Datos no V�lidos: \n' + cVMsg);
        return false;
      }
      else if(!confirm("�Est� Seguro de guardar los cambios?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }





