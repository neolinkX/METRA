  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'BorrarB') {
         if(!confirm("�Est� Seguro de Desactivar el Registro?"))
           return false;
    }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

      if (form.iCveTpoArticulo)
        cVMsg = cVMsg + fSinValor(form.iCveTpoArticulo,3,'Clave:', false);

      if (form.cDscTpoArticulo)
        cVMsg = cVMsg + fSinValor(form.cDscTpoArticulo,0,'Descripci�n:', true);

      if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,0,'Descripci�n Breve:', true);

      if (form.iIDPartida){
        if(form.iIDPartida.value != ''){
          if(!fSoloNumeros(form.iIDPartida.value))
            cVMsg = cVMsg + "\n - El campo Partida solo permite caracteres num�ricos.";
        }
      }

      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'lActivo', false);
        if (cVMsg != ''){
          alert("Datos no V�lidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

