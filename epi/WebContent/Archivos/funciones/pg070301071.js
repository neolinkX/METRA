  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if (form.iCveEquipo){
        cVMsg = cVMsg + fSinValor(form.iCveEquipo,3,'Cve. Campo', true);

        if (form.iCveEquipo.length <= 0)
          cVMsg = cVMsg + "\n - La Lista de Selección para el Equipo No tiene Elementos.";

        if (form.iCveEquipo.value <= 0)
          cVMsg = cVMsg + "\n - Debe seleccionar el Equipo.";
      }

      if (form.iCarruseles)
        cVMsg = cVMsg + fSinValor(form.iCarruseles,3,'No. Discos', true);

      if (form.iPosiciones)
        cVMsg = cVMsg + fSinValor(form.iPosiciones,3,'Posiciones por Disco', true);

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }

      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }
    return true;
  }
