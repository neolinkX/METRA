  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deber�n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensi�n js (vg. pg0702061.js)
  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value != 'Cancelar'){
      cVMsg = '';
      if(form.cDscCausa){
        cVMsg += fSinValor(form.cDscCausa,2,'Descripci�n', true);
        fMayus(form.cDscCausa);
      }
      if(cVMsg!=''){
        alert("Datos no V�lidos: \n" + cVMsg);
        return false;
      }
    }
    if (form.hdBoton.value == 'Borrar' || form.hdBoton.value == 'BorrarB')
      return confirm("�Est� Seguro de Desactivar los Registro seleccionados?");
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA')
      return confirm("�Est� Seguro de Guardar la Informaci�n?");
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
