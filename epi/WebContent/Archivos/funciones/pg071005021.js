  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Nuevo'){
      return true;
    }
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = "";

      if(form.cDscMotivo.value=='')
        cVMsg += "\n - Escriba una descripci�n.";
             
      if(form.iCveProceso.value<1)
        cVMsg += "\n - Seleccione un proceso.";             

      if(cVMsg!=""){
        alert('Datos no V�lidos: \n' + cVMsg);
        return false; 
      }
        

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

