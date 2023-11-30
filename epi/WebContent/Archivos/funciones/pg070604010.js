  function llenaSLT(opc,val1,val2,val3,objDes,objDes2){
     if(objDes!=''){
        if(objDes.name!=''){
           if (objDes2!='' && objDes2.name!=''){
               objDes2.length=1;
               objDes2[0].text="Datos no Disponibles...";
           }
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070604010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070604010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
  function fDetalles(iCveVeh,iCveMan, pag) {
      var form = document.forms[0];
      form.iCveMantenimiento.value = iCveMan;
      form.hdCampoClave.value = iCveVeh;
      form.target = 'FRMDatos';
      form.action = pag;
      form.submit();
  }

  function fValidaBuscar(){
     var form = document.forms[0];
     var Msg = "";

     // Rango
     if (form.xMesRango.checked) {
        if (form.dtInicio)
             Msg = Msg + fSinValor(form.dtInicio,5,'Fecha de Inicio', true);
        if (form.dtFin)
             Msg = Msg + fSinValor(form.dtFin,5,'Fecha Final', true);
     }

     // Unidad Medica
     if (form.xUniMed.checked) {
        if (form.iCveUniMed.value == '' || form.iCveUniMed.value == '0')
           Msg = Msg + ' - Seleccione una Unidad Medica.\n';
     }

     // Modulo
     if (form.xModulo.checked) {
        if (form.iCveModulo.value == '' || form.iCveModulo.value == '0')
           Msg = Msg + ' - Seleccione un Modulo.\n';
     }

     // Area
     if (form.xArea.checked) {
        if (form.iCveArea.value == '' || form.iCveArea.value == '0' || form.iCveArea.value == '-1')
           Msg = Msg + ' - Seleccione un Área';
     }

     // Mostrar Mensajes
     if (Msg != '') {
        alert('Error en los datos, verifique:\n' + Msg);
        return false;
     }
     return true;
  }