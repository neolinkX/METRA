  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Nuevo'){
      return true;
    }
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = "";

      if(form.iEjercicio.value=='')
        cVMsg += "\n - Escriba un Ejercicio.";
             
      
      cadena = form.iRefNum.value;
      if(fSoloNumeros(cadena)==false)
        cVMsg += "\n - Ref. Num. debe contener solo valores numúricos.";

      if (form.iCveConcepto.value <= 0 || form.iCveConcepto.value == ""){
          cVMsg = cVMsg + " - Debe seleccionar el concepto. \n";
        }
      
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

