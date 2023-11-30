  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'BorrarB') {
         if(!confirm("¿Está Seguro de Desactivar el Registro?"))
           return false;
    }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

      if (form.iCveTpoMovimiento)
        cVMsg = cVMsg + fSinValor(form.iCveTpoMovimiento ,3,'Clave:', false);

      if (form.cDscTpoMovimiento)
        cVMsg = cVMsg + fSinValor(form.cDscTpoMovimiento ,2,'Descripción:', true);

      if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,2,'Descripción Breve:', true);

      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'lActivo', false);

        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

