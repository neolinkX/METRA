  function fIrCatalogo(cEquipo, cAsignacion) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.hdCampoClave.value = cEquipo;
    form.hdRowNum.value = cEquipo;
    form.iCveAsignacion.value = cAsignacion;
    form.target = 'FRMDatos';
    form.action = 'pg070602021.jsp';
    form.submit();
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070602020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070602020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
    }
  }

  function fValidaBuscar(){
    var form = document.forms[0];
    var cVMsg = '';
    if (form.RSTMostrar[1].checked){
      if (form.iCveUniMed)
         if (form.iCveUniMed.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica.";
      if (form.iCveModulo)
         if (form.iCveModulo.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar el Módulo.";
      if (form.iCveArea)
         if (form.iCveArea.selectedIndex == 0)
            cVMsg = cVMsg + "\n - Debe seleccionar el Área.";
      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
    }
    return true;
  }

  function fValidaTodo(){
    var form = document.forms[0];
    var cVMsg = '';
     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
        if(!confirm("¿Está seguro de Guardar la Información?"))
           return false;
        if (form.RSTMostrar[0].checked){
          if (form.iCveUniMed)
             if (form.iCveUniMed.selectedIndex == 0)
                cVMsg = cVMsg + "\n - Debe seleccionar la Unidad Médica.";
          if (form.iCveModulo)
             if (form.iCveModulo.selectedIndex == 0)
                cVMsg = cVMsg + "\n - Debe seleccionar el Módulo.";
          if (form.iCveArea)
             if (form.iCveArea.selectedIndex == 0)
                cVMsg = cVMsg + "\n - Debe seleccionar el Área.";
        }
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
     }
     return true;
   }

   function fValidaReg(vObject, vSelect){
     if (vObject.checked){
       if (vSelect){
         var vOpcion = vSelect.options[vSelect.selectedIndex];
         if (vOpcion.value == 0 || vOpcion.value == -1){
           alert('Debe seleccionar un usuario responsable para la asignación de equipo.');
           vObject.checked = false;
           return false;
         }
       }
     }
     return true;
   }

   function fValidaElegido(vSelect, vObject){
     var vOpcion = vSelect.options[vSelect.selectedIndex];
     if (vOpcion.value == 0 || vOpcion.value == -1){
       if (vObject.checked){
         vObject.checked = false;
         return false;
       }
     }
     return true;
   }

   function fCargaListasOnLoad(){
     var form = document.forms[0];
     if (form.RSTMostrar[0].checked){
       if (form.iCveUniMed && form.iCveModulo){
         if (form.iCveUniMed.options[form.iCveUniMed.selectedIndex].value > 0)
           llenaSLT(1,form.iCveUniMed.options[form.iCveUniMed.selectedIndex].value,'','',form.iCveModulo);
       }
     }
   }
