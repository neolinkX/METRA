  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Nuevo'){
      cVMsg = '';
      if(form.iCveTpoArticulo.value < 1){
        cVMsg = cVMsg + "\n - Seleccione un tipo de art�culo.";
        alert('Datos no V�lidos: \n' + cVMsg);
        return false;
      }
      else
        return true;
    }
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if(form.cDscFamilia.value==''){
        cVMsg = cVMsg + "\n - Escriba una descripci�n.";
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

