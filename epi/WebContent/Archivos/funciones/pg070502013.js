  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Nuevo') {
      cVMsg = '';
      if(form.iCveEmpresa.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Transportista.";
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if(form.cCalle.value=="")
        cVMsg = cVMsg + "\n - Escriba la calle del domicilio Fiscal del Transportista.";
      if(form.cColonia.value<1)
        cVMsg = cVMsg + "\n - Escriba la Colonia del domicilio Fiscal del Transportista.";


      if(form.iCP.value=="")
        cVMsg = cVMsg + "\n - Escriba el C.P. del domicilio Fiscal del Transportista.";
      else {
        cCP = form.iCP.value;
        if(cCP.length<5)
          cVMsg = cVMsg + "\n - El C.P. debe tener 5 números.";
        else{
          iError = 0;
          for(i=0; i<cCP.length; i++)
            if(cCP.charAt(i)<'0' || cCP.charAt(i)>'9')
              iError = 1;
          if(iError == 1)
            cVMsg = cVMsg + "\n - El C.P. debe tener solamente caracteres numéricos.";
        }
      }



      if(form.iCvePais.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Pais.";
      else if(form.iCveEstado.value<0)
        cVMsg = cVMsg + "\n - Seleccione un Estado.";
      else if(form.iCveMunicipio.value<0)
        cVMsg = cVMsg + "\n - Seleccione un Municipio.";

      if(cVMsg!=''){
        alert('Datos no Válidos: \n' + cVMsg);
        return false;
      }
      else if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
    if(objDes!=''){
      if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070502013P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
      }
      else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
      }
    }
    else {
      window.parent.FRMOtroPanel.location="pg070502013P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }