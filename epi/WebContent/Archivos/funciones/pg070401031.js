  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value != 'Cancelar'){
      cVMsg = '';
      if(form.cDscCausa){
        cVMsg += fSinValor(form.cDscCausa,2,'Descripción', true);
        fMayus(form.cDscCausa);
      }
      if(cVMsg!=''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
    }
    if (form.hdBoton.value == 'Borrar' || form.hdBoton.value == 'BorrarB')
      return confirm("¿Está Seguro de Desactivar los Registro seleccionados?");
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA')
      return confirm("¿Está Seguro de Guardar la Información?");
    return true;
  }
  function fSubmite(objSel,noHagasSubmit){
    var form=document.forms[0];
    form.hdICveTpoCausa.value=objSel.value;
    form.target='FRMDatos';
    if(noHagasSubmit) return;
    form.hdBoton.value="Primero";
    form.submit();
  }
