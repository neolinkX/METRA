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
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Nuevo') {
      cVMsg = '';
      if(form.iAnio.value<1)
        cVMsg = cVMsg + "\n - Seleccione el a�o.";
      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if(form.cDscPeriodPla.value=="")
        cVMsg = cVMsg + "\n - Escriba una descripci�n.";

      if(form.iCveTpoPlantilla.value<1)
        cVMsg = cVMsg + "\n - Seleccione un tipo de plantilla.";

      if (document.forms[0].dtGeneracion)
        cVMsg = cVMsg + fSinValor(document.forms[0].dtGeneracion,5,'Fecha de Generaci�n:', true);

      if (document.forms[0].dtVencimiento)
        cVMsg = cVMsg + fSinValor(document.forms[0].dtVencimiento,5,'Fecha de Vencimiento:', true);

      if(form.dtGeneracion.value>form.dtVencimiento.value)
        cVMsg = cVMsg + "\n - La fecha de Vencimiento debe ser posterior a la fecha de generaci�n.";

      if(cVMsg!=''){
        alert('Datos no V�lidos: \n' + cVMsg);
        return false;
      }
      else if(!confirm("�Est� Seguro de Guardar la Informaci�n?"))
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

