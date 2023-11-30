  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070603010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070603010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fActClasificacion(){
    form = document.forms[0];
    if(form.chkClasificacion.checked)
      form.iCveClasificacion.disabled = false;
    else{
      form.iCveClasificacion.disabled = true;
      form.iCveTpoEquipo.disabled = true;
      form.chkTpoEquipo.checked = false;
    }
  }

  function fActTpoEquipo(){
    form = document.forms[0];
    if(form.chkTpoEquipo.checked)
      form.iCveTpoEquipo.disabled = false;
    else
      form.iCveTpoEquipo.disabled = true;
  }

  function fActMarca(){
    form = document.forms[0];
    if(form.chkMarca.checked)
      form.iCveMarca.disabled = false;
    else
      form.iCveMarca.disabled = true;
  }

  function fActUniMed(){
    form = document.forms[0];
    if(form.chkUniMed.checked)
      form.iCveUniMed.disabled = false;
    else{
      form.iCveUniMed.disabled = true;
      form.iCveModulo.disabled = true;
      form.iCveArea.disabled = true;
      form.chkModulo.checked = false;
      form.chkArea.checked = false;
    }
  }

  function fActModulo(){
    form = document.forms[0];
    if(form.chkModulo.checked)
      form.iCveModulo.disabled = false;
    else{
      form.iCveModulo.disabled = true;
      form.iCveArea.disabled = true;
      form.chkArea.checked = false;
    }
  }

  function fActArea(){
    form = document.forms[0];
    if(form.chkArea.checked)
      form.iCveArea.disabled = false;
    else
      form.iCveArea.disabled = true;
  }

  function fValidaBuscar(){
    cVMsg = '';
    form = document.forms[0];
    if(!form.chkClasificacion.checked && !form.chkTpoEquipo.checked && !form.chkMarca.checked && !form.chkUniMed.checked && !form.chkModulo.checked && !form.chkArea.checked){
      cVMsg = cVMsg + "\n - Seleccione al menos un criterio para el filtrado de equipos.";
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else if(form.chkClasificacion.checked && form.iCveClasificacion.value<1){
      cVMsg = cVMsg + "\n - Seleccione una clasificación.";
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else if(form.chkTpoEquipo.checked && form.iCveTpoEquipo.value<1){
      cVMsg = cVMsg + "\n - Seleccione un tipo de equipo.";
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else if(form.chkMarca.checked && form.iCveMarca.value<1){
      cVMsg = cVMsg + "\n - Seleccione una marca.";
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else if(form.chkUniMed.checked && form.iCveUniMed.value<1){
      cVMsg = cVMsg + "\n - Seleccione una unidad médica.";
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else if(form.chkModulo.checked && form.iCveModulo.value<1){
      cVMsg = cVMsg + "\n - Seleccione un módulo.";
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else if(form.chkArea.checked && form.iCveArea.value<1){
      cVMsg = cVMsg + "\n - Seleccione un área.";
      alert('Datos no Válidos: \n' + cVMsg);
      return false;
    }
    else{
      form.lMostrarDatos.value = "verdadero";
      return true;
   }
  }

  function fProgramar(){
    cVMsg = '';
    form = document.forms[0];

    if(form.iCveTpoMantto.value<1)
      cVMsg = cVMsg + "\n - Seleccione un tipo de mantenimiento.";

    if(form.iCveMotivo.value<1)
      cVMsg = cVMsg + "\n - Seleccione un motivo de mantenimiento.";

    if(form.dtProgramado.value=="")
      cVMsg = cVMsg + "\n - Seleccione una fecha de programación de mantenimiento.";
    else{
      if(!fValFecha(form.dtProgramado.value))
        cVMsg = cVMsg + "\n - Verfique el formato de fecha (dd/mm/aaaa).";
    }

    if(cVMsg == ""){
      form.lProgramar.value = "verdadero";
      form.lMostrarDatos.value = "verdadero";
      form.target='FRMDatos';
      //form.hdBoton.value='Primero';
      form.submit();

    }
    else
      alert('Datos no Válidos: \n' + cVMsg); 
  }
