  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Nuevo'){
      return true;
    }
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(alert(" - Acción no permitida "))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = "";

      if(form.iCvePais.value<1)
        cVMsg += "\n - Seleccione un País.";

      if(form.cDscNacional.value=='')

        cVMsg += "\n - Escriba la Nacionalidad.";
             
      if(cVMsg!=""){
        alert('Datos no Válidos: \n' + cVMsg);
        return false; 
      }
        

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

