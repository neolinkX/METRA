  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Eliminar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if (form.iCveEquipo){
        cVMsg = cVMsg + fSinValor(form.iCveEquipo,3,'Equipo', true);

        if (form.iCveEquipo.length <= 0)
          cVMsg = cVMsg + "\n - La Lista de Selecci�n para el Equipo No tiene Elementos.";

        if (form.iCveEquipo.value <= 0)
          cVMsg = cVMsg + "\n - Debe seleccionar el Equipo.";
      }

      if (form.iCveArea){
        cVMsg = cVMsg + fSinValor(form.iCveArea,3,'Area', true);

        if (form.iCveArea.length <= 0)
          cVMsg = cVMsg + "\n - La Lista de Selecci�n para el Area No tiene Elementos.";

        if (form.iCveArea.value <= 0)
          cVMsg = cVMsg + "\n - Debe seleccionar el Area.";
      }

      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }

      if(!confirm("�Est� Seguro de Guardar la Informaci�n?"))
        return false;
    }
    return true;
  }