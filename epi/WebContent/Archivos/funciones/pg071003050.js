 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg071003050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg071003050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {

      cVMsg = '';

      if(form.iCveUniMed.value < 1)
        cVMsg = cVMsg + "\n - Seleccione una Unidad M�dica.";

      if(form.iCveModulo.value < 1)
        cVMsg = cVMsg + "\n - Seleccione un M�dulo.";

      if(cVMsg!=''){
        alert('Datos no V�lidos: \n' + cVMsg);
        return false;
      }
      else if(!confirm("�Est� Seguro de guardar los cambios?"))
        return false;
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

  function ChangeAllCheck(doc, Checked, cSubNombre){
     form = doc.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,cSubNombre.length) == cSubNombre)
           form.elements[i].checked = Checked;
     }
  }

