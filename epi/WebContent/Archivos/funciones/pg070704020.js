function fBuscar(){
  if(fValidaBuscar()){
    form = document.forms[0];
    form.hdBoton.value = 'Buscar';
    form.target = "_self";
    form.submit();
  }
}
   function fValidaBuscar(){
      form = document.forms[0];
      var cVMsg = '';
      if (form.dtDesde.value != '')
        cVMsg = cVMsg + fSinValor(form.dtDesde,5,'Fecha Desde', true);

      if (form.dtHasta && form.dtHasta.value != '')
        cVMsg = cVMsg + fSinValor(form.dtHasta,5,'Fecha Hasta', true);

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
      else{
         return true;
      }
   }