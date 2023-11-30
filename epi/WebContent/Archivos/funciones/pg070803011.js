  function Select(Index){
     form = document.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,8)=="iCveMeta")
           form.elements[i].selectedIndex = Index;
     }
  }

  function Check(Checked){
     form = document.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,8)=="lAsignar")
           form.elements[i].checked = Checked;
     }
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      var iArticulo = 0;
      cVMsg = '';
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
      else{
         for (i = 0; i < form.length; i++){
            str = form.elements[i].name;
            if (str.substring(0,8)=="lAsignar"){
               if (form.elements[i].checked){
                  iArticulo = str.substring(8,form.elements[i].length);
                  if (form.elements[i-1].selectedIndex==0)
                     cVMsg = cVMsg + "\n - Debe seleccionar una Meta para el Articulo " + iArticulo;
                  if (form.elements[i-2]){
                     if (parseFloat(form.elements[i-2].value) < 0){
                        cVMsg = cVMsg + "\n - La Cantidad del Articulo " + iArticulo + " debe ser positiva";
                     }
                     else{
                       if (parseFloat(form.elements[i-2].value) == 0)
                         form.elements[i].checked = false;
                       else
                         cVMsg = cVMsg + fSinValor(form.elements[i-2],4,'Cantidad del Articulo ' + iArticulo, true);
                     }
                  }
               }
               if (cVMsg != ''){
                  alert("Datos no Válidos: \n" + cVMsg);
                  return false;
               }
            }
         }
      }
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      form = document.forms[0];
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           objDes[0].value="-1";
           window.parent.FRMOtroPanel.location="pg070803011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070803011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
