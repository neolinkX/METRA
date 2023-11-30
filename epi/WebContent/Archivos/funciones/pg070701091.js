  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if(form.cDscEtapaSolic.value=='')
        cVMsg = cVMsg + "\n - Escriba una descripción.";

      if(form.iOrden.value!='')
        if(!fSoloNumeros(form.iOrden.value))
          cVMsg = cVMsg + "\n - El orden debe ser un número.";         
      
      if(cVMsg!=""){
        alert('Datos no Válidos: \n' + cVMsg);
        return false; 
      }
      else 
        return true; 

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

