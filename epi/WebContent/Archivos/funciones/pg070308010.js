
  function fLiberar(cPagina){
      if(confirm('Desea Realmente Liberar la Muestra ?')){
        form = document.forms[0];
        form.hdBoton.value = 'GuardarA';
        form.target = 'FRMDatos';
        form.action = cPagina;
        form.submit();
      }
  }

  function fBorrar(cPagina){
      if(confirm('Desea Realmente Borrar la Muestra ?')){
        form = document.forms[0];
        form.hdBoton.value = 'Borrar';
        form.target = 'FRMDatos';
        form.action = cPagina;
        form.submit();
      }
  }

  function fEnvio(cPagina){
      if(confirm('Desea Realmente Borrar el Envío ?')){
        form = document.forms[0];
        form.hdBoton.value = 'Guardar';
        form.target = 'FRMDatos';
        form.action = cPagina;
        form.submit();
      }
  }

function fValidaSelectores() {
    form = document.forms[0];
    cVMsg1 = "";
    if (form.iCveMuestra){
       if (form.iCveMuestra.value == ""){
         cVMsg1 += "\n - El campo 'Muestra' es Obligatorio, favor de introducir valor.";
       }
       else{
          if(isNaN(form.iCveMuestra.value)){
             cVMsg1 += "\n - El campo 'Muestra' debe ser Numérico.";
          }
       }
    }

    if (cVMsg1 != ''){
      alert("Datos no Válidos: \n" + cVMsg1);
      return false;
    }
    return true;
}
