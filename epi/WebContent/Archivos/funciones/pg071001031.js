
  function fValidaTodo(){
    form = document.forms[0];
    if(form.hdBoton.value == 'Modificar'){
      alert('Esta opción se encuentra deshabilitada!');
      return false;
    }
    if(form.hdBoton.value == 'Borrar'){
      if(!confirm('¿Está seguro de eliminar el registro?'))
        return false;
    }
    lMsg = true;
    if(form.hdBoton.value == 'Guardar'){
      if(form.iCveUsuario.value == '')
        lMsg = false;
      if(form.iCveUniMed.value == '')
        lMsg = false;
      if(form.iCveProceso.value == '')
        lMsg = false;
      if(form.iCveModulo.value == '')
        lMsg = false;
      /*if(form.iCveServicio.value == '')
        lMsg = false;
      if(form.iCveRama.value == '')
        lMsg = false;*/

      if(lMsg == false)
        alert("No todos los datos se encuentran disponibles!");
 
      return lMsg;
    }
    return true;
  }

  function fChg(){
    form = document.forms[0];
    form.hdBoton.value = 'Nuevo';
    form.target = 'FRMDatos';
    form.action = 'pg071001031.jsp';
    form.submit();
  }
  
    function fSelectedPer(iCveUsuario){
    var form = document.forms[0];
    form.hdiCveUsuario.value = iCveUsuario;
    form.hdBoton.value = "Nuevo";
    form.target = "_self";
    form.submit();
 
  }

