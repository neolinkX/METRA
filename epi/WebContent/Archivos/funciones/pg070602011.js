function fOpcional3(cVCadena){    

    if ( fRaros(cVCadena)      || fPuntuacion(cVCadena) || 

         fSignos(cVCadena) || fArroba(cVCadena)     || 

         fParentesis(cVCadena) ||  

         fDiagonal(cVCadena)   || fComa(cVCadena))

        return false;  

    else 

        return true;

}

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar' && form.hdBoton.value != 'Primero' && form.hdBoton.value != 'Anterior' &&
         form.hdBoton.value != 'Siguiente' && form.hdBoton.value != 'Ultimo' && form.hdBoton.value != 'Buscar' ) {
      cVMsg = '';
      if (form.iCveTpoEquipo.value == '' || form.iCveTpoEquipo.value == '0')
        cVMsg = cVMsg + ' - Seleccione un Tipo de Equipo';
      if (form.cCveEquipo)
        if (fOpcional3(form.cCveEquipo.value))
        cVMsg = cVMsg + ' - El Identificador del Equipo solo acepta letras, números y guión.';
      if (form.cDscEquipo)
        cVMsg = cVMsg + fSinValor(form.cDscEquipo,2,'Nombre', true);
      if (form.cNumSerie)
        cVMsg = cVMsg + fSinValor(form.cNumSerie,2,'No. Serie', true);
      if (form.cInventario){
         if (form.cInventario.value == '')
           cVMsg = cVMsg + "\n - Debe Proporcionar el Inventario.";
      }
      //  cVMsg = cVMsg + fSinValor(form.cInventario,2,'Inventario', true);
      if (form.dtAdquisicion)
        cVMsg = cVMsg + fSinValor(form.dtAdquisicion,5,'Adquisición', true);
      if (form.iPerManttoPrev)
        cVMsg = cVMsg + fSinValor(form.iPerManttoPrev,3,'Periodo de Mantenimiento', true);
      if (form.iLimiteUso)
        cVMsg = cVMsg + fSinValor(form.iLimiteUso,3,'Uso Límite para Mantenimiento', true);
      if (form.iPerCalibracion)
        cVMsg = cVMsg + fSinValor(form.iPerCalibracion,3,'Periodo de Calibración', true);
//  Baja
      if (form.hdBoton.value == 'GuardarA')
        if (form.lBaja.checked) {
           if (form.dtBaja)
              cVMsg = cVMsg + fSinValor(form.dtBaja,5,'Fecha de Baja', true);
           if (form.iCveCausaBaja)
              cVMsg = cVMsg + fSinValor(form.iCveCausaBaja,2,'Causa de Baja', true);
        }

      if (form.hdBoton.value == 'Modificar')
         if (form.dadoBaja.value == 1) {
            alert(" Registro Inactivo, no es posible modificarlo.");
            return false;
         }
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
      return true;
    }
    return true;
  }
  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070602011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070602011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 250)
      fArea.value = cText = cText.substring(0,250);
    form.iNoLetras.value = 250 - cText.length;
  }