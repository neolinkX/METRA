  function fValidaTodo(){

    var form=document.forms[0];
    var lVehiculo = 0;
    if (form.RadioVEH.length>=2){
       for (var i=0; i < form.RadioVEH.length; i++) {
          if (form.RadioVEH[i].checked)
             lVehiculo = form.RadioVEH[i].value;
       }
    }else{
       if (form.RadioVEH && form.RadioVEH.checked)
          lVehiculo = form.RadioVEH.value;
    }


    form = document.forms[0];
    if (form.hdBoton.value == 'Borrar') {
       if(!confirm("¿Desea usted eliminar el registro del vehículo?"))
         return false;
    }

    var lGuarda = false;
    if (form.hdBoton.value == 'Guardar') {
      if(!confirm("¿Desea usted guardar la información correspondiente?"))
        return false;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }else
        return true;
      }
   }


  function fValidar(){
    var form=document.forms[0];
    var lValida = false;
    if (form.RadioVEH.length>=2){
       for (var i=0; i < form.RadioVEH.length; i++) {
          if (form.RadioVEH[i].checked)
             lValida = true;
       }
    }else{
       if (form.RadioVEH && form.RadioVEH.checked)
          lValida = true;
    }

    if (lValida){
       form.target="_self";
       form.hdBoton.value="Validar";
       form.submit();
    }else
       alert('Debe tener seleccionado un Vehículo para Realizar la Validación!');
  }



  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070703021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070703021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
    }
  }
