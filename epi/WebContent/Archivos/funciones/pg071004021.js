  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Nuevo'){
      cVMsg = '';
      if(form.iCveMdoTrans.value < 1){
        cVMsg = cVMsg + "\n - Seleccione una MdoTrans.";
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

      if(form.cDscCategoria.value=='')
        cVMsg = cVMsg + "\n - Escriba una descripci�n.";
      
      cadena = form.iTmpoReexp.value;
      if(fSoloNumeros(cadena)==false)
        cVMsg = cVMsg + "\n - El campo reexpedici�n debe contener solo valores num�ricos.";

      if(cVMsg != ""){
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

