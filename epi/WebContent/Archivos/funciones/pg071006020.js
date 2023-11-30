  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {

      fTxtValues();

      cVMsg = '';

      if(form.iCveMotivo.value < 1)
        cVMsg = cVMsg + "\n - Seleccione un Motivo.";

      if(cVMsg!=''){
        alert('Datos no Válidos: \n' + cVMsg);
        return false;
      }
      else if(!confirm("¿Está Seguro de guardar los cambios?"))
        return false;
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

  function ChangeAllCheck(doc, Checked, cSubNombre){
     form = doc.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,cSubNombre.length) == cSubNombre)
           form.elements[i].checked = Checked;
     }
  }

  function fTxtValues(){
    form = document.forms[0];
    for (i = 0; i < form.length; i++){
       str = form.elements[i].name;
       if (str.substring(0,'txt'.length) == 'txt')
         form.elements[i+1].value = form.elements[i].value;
    }
  }

