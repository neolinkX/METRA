  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 249)
      fArea.value = cText = cText.substring(0,249);
    form.iNoLetras.value = 249 - cText.length;
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Nuevo') {
      cVMsg = '';
      if(form.iAnio.value<1)
        cVMsg = cVMsg + "\n - Seleccione el año.";
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if(form.cDscPeriodPla.value=="")
        cVMsg = cVMsg + "\n - Escriba una descripción.";

      if(form.iCveTpoPlantilla.value<1)
        cVMsg = cVMsg + "\n - Seleccione un tipo de plantilla.";

      if (document.forms[0].dtGeneracion)
        cVMsg = cVMsg + fSinValor(document.forms[0].dtGeneracion,5,'Fecha de Generación:', true);

      if (document.forms[0].dtVencimiento)
        cVMsg = cVMsg + fSinValor(document.forms[0].dtVencimiento,5,'Fecha de Vencimiento:', true);

      if(form.dtGeneracion.value>form.dtVencimiento.value)
        cVMsg = cVMsg + "\n - La fecha de Vencimiento debe ser posterior a la fecha de generación.";

      if(cVMsg!=''){
        alert('Datos no Válidos: \n' + cVMsg);
        return false;
      }
      else if(!confirm("¿Está Seguro de Guardar la Información?"))
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

