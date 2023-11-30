function fReactCalib(cValor) {
     form = document.forms[0];
     form.hdBoton.value = cValor;
     form.target = "_self";
     form.submit();
}

  function fValidaTodo(){
    form = document.forms[0]; 


    if (form.hdBoton.value == 'Borrar' || form.hdBoton.value == 'Modificar') {
     alert("No es posible modificar o borrar los registros.")
     return false;
      }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {

    if (form.iAnio)
        cVMsg = cVMsg + fSinValor(form.iAnio,3,'Año:', true);
    if (form.iCveLaboratorio)
        cVMsg = cVMsg + fSinValor(form.iCveLaboratorio,3,'Laboratorio:', true);


    if (form.tipo){
       if (form.tipo[0].checked || form.tipo[1].checked){

          if (form.iCveReactivo)
             cVMsg = cVMsg + fSinValor(form.iCveReactivo,3,'Reactivo:', true);

          if (form.iCveCtrolCalibra) 
             cVMsg = cVMsg + fSinValor(form.iCveCtrolCalibra,3,'Calibrador:', true);
       }
       else
          cVMsg = cVMsg + " - Debe Seleccionar algún reactivo o algún calibrador.";
    }


    if (form.dtDesechado)
        cVMsg = cVMsg + fSinValor(form.dtDesechado,5,'Fecha:', true);
    if (form.iCveMotBaja)
        cVMsg = cVMsg + fSinValor(form.iCveMotBaja,3,'Motivo Baja:', true);

    if (form.cOrganoInterno)
        cVMsg = cVMsg + fSinValor(form.cOrganoInterno,0,'Organo Interno:', true);

    if (form.iCveUsuBaja)
        cVMsg = cVMsg + fSinValor(form.iCveUsuBaja,3,'Usuario Responsable:', true);

    if (form.cInactiva)
        cVMsg = cVMsg + fSinValor(form.cInactiva,0,'Empresa que inactiva:', true);

    if (form.cObservacion)
        cVMsg = cVMsg + fSinValor(form.cObservacion,0,'Observación:', false);


     if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg); 
          return false;
        }


      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
      form.submit();
    }
}

    return true;
  }


function Genera_Doc(){
   form = document.forms[0]; 
   form.target="_self";
   form.hdReporte.value = 'Reporte'; 
   form.hdBoton.value = 'Cancelar'; 
   //alert(" yap value: " + form.iCveLaboratorio.value + "//" + form.hdCampoClave.value);
    form.submit();
}