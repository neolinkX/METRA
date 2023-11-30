
  function fValidaTodo(){
    var form = document.forms[0];
        cVMsg = '';

    if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA" ){

      if (form.cDscTurno)
        cVMsg = cVMsg + fSinValor(form.cDscTurno,0,'Descripción', true);

      if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,0,'Desc. Breve', true);

      if (form.iCveUsuRespon)
        cVMsg = cVMsg + fSinValor(form.iCveUsuRespon,3,'Usuario', true);

      if (form.iCveUsuRespon){
        if (form.iCveUsuRespon.length <= 0)
          cVMsg = cVMsg + "\n - La Lista de Selección para el Usuario No tiene Elementos.";
        if (form.iCveUsuRespon.value <= 0)
          cVMsg = cVMsg + "\n - Debe seleccionar El Usuario.";
      } else
        cVMsg = cVMsg + "\n - La Lista de Selección para el Usuario No tiene Elementos.";

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }

      if (!confirm(" ¿ Desea Guardar el Turno de Validación ? "))
        return false;
    }

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }

    return true;
  }
