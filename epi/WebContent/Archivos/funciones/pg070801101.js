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

      if (form.iCveUnidad)
        cVMsg = cVMsg + fSinValor(form.iCveUnidad,3,'Clave:', false);

      if (form.cDscUnidad)
        cVMsg = cVMsg + fSinValor(form.cDscUnidad,2,'Descripci�n:', true);

      if (form.cAbreviatura)
        cVMsg = cVMsg + fSinValor(form.cAbreviatura,2,'Abreviatura:', true);

      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'lActivo', false);
        if (cVMsg != ''){
          alert("Datos no V�lidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

