  function Select(Index){
     form = document.forms[0];
     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,8)=="iCveMeta")
           form.elements[i].selectedIndex = Index;
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
      var iArticulo = 0;
      cVMsg = '';
      if(!confirm("�Est� Seguro de Eliminar los articulos seleccionados?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      form = document.forms[0];
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

  function fAccion(cAccion){
    form = document.forms[0];
    if(confirm("�Est� Seguro de Autorizar los Articulos seleccionados?")){
       form.target="_self";
       form.hdBoton.value = cAccion;
       form.submit();
    }
  }
