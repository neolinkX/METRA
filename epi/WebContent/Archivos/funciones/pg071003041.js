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

      fChkValues();

      if(form.iCveProceso.value < 1)
        cVMsg = cVMsg + "\n - Seleccione un Proceso.";

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
     alert("changeallcheck");
     form = doc.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,cSubNombre.length) == cSubNombre)
           form.elements[i].checked = Checked;
     }
  }

  function fChkValues(){
    form = document.forms[0];
    for (i = 0; i < form.length-2; i++){
       str = form.elements[i].name;
       str2 = form.elements[i+2].name;
       if (str.substring(0,'chkl'.length) == 'chkl' && str2.substring(0,'chkl2'.length) == 'chkl2')
         if(form.elements[i].checked)   
           form.elements[i+2].value = 1;
         else if(form.elements[i].checked==false)   
           form.elements[i+2].value = 0;
    }
  }
