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

      if (form.iCveTpoArticulo)
        cVMsg = cVMsg + fSinValor(form.iCveTpoArticulo,3,'Clave:', false);

      if (form.cDscTpoArticulo)
        cVMsg = cVMsg + fSinValor(form.cDscTpoArticulo,0,'Descripción:', true);

      if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,0,'Descripción Breve:', true);

      if (form.iIDPartida){
        if(form.iIDPartida.value != ''){
          if(!fSoloNumeros(form.iIDPartida.value))
            cVMsg = cVMsg + "\n - El campo Partida solo permite caracteres numéricos.";
        }
      }

      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'lActivo', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

