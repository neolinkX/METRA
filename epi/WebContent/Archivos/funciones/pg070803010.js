  function fIrCatalogo(cCampoClave, cCampoClave2) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.hdCampoClave1.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdRowNum.value = cCampoClave;
    form.target = 'FRMDatos';
    form.action = 'pg070803011.jsp';
    form.submit();
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070803010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070803010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fValidaTodo(){
    var form = document.forms[0];

    if (form.hdBoton.value == 'Modificar'||form.hdBoton.value == 'Borrar'){
       if (form.lAutorizada.value == '1'){
           alert("La Solicitud no puede ser modificada ni borrada, ya que se encuntra Autorizada!");
           return false;
       }
    }

     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
        if(!confirm("¿Está seguro de Guardar la Información?"))
           return false;
     }

     if (form.hdBoton.value == 'Borrar') {
        if(!confirm("¿Está seguro de Borrar la Información?"))
        return false;
     }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.dtSolicitud)
        cVMsg = cVMsg + fSinValor(form.dtSolicitud,5,'Fecha de Solicitud', false);
//      if (form.dtSuministro)
//        cVMsg = cVMsg + fSinValor(form.dtSuministro,5,'Fecha de Suministro', false);
      if (form.iCveUniMed)
         if (form.iCveUniMed.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica";

      if (form.iCveModulo)
         if (form.iCveModulo.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar el Módulo";

      if (form.iCveArea)
         if (form.iCveArea.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar el Área";

      if (form.iAnio)
         if (form.iAnio.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar el Año";

      if (form.iCveAlmacen)
         if (form.iCveAlmacen.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar el Almacen";

      if (form.iCvePeriodo)
         if (form.iCvePeriodo.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar el Periodo";

      if (form.iCvePrioridad)
         if (form.iCvePrioridad.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar la Prioridad";

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
    }
     return true;
   }
