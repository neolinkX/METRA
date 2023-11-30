  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()

    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Desactivar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      cVMsg = '';
      if(form.cRFC.value=="")
        cVMsg = cVMsg + "\n - Escriba el R.F.C.";

      //if(form.cCURP.value=="")
      //  cVMsg = cVMsg + "\n - Escriba la C.U.R.P.";

      if((form.cTpoPersona[1].checked && form.cApPaterno.value!="") || (form.cTpoPersona[1].checked && form.cApMaterno.value!=""))
        cVMsg = cVMsg + "\n - Para una persona moral, escriba solamente el campo Nombre.";

      if((form.cTpoPersona[0].checked && form.cApPaterno.value=="") || (form.cTpoPersona[0].checked && form.cApMaterno.value==""))
        cVMsg = cVMsg + "\n - Para una persona f�sica, escriba Nombre, Primer Apellido y Segundo Apellido.";

      if(form.cNombreRS.value=="")
        cVMsg = cVMsg + "\n - Escriba el nombre.";

      if(form.cCalle.value=="")
        cVMsg = cVMsg + "\n - Escriba la calle del domicilio de la empresa.";

      if(form.cColonia.value<1)
        cVMsg = cVMsg + "\n - Escriba la Colonia del domicilio de la empresa.";

      if(form.iCP.value=="")
        cVMsg = cVMsg + "\n - Escriba el C.P. del domicilio de la empresa.";
      else {
        cCP = form.iCP.value;
        if(cCP.length<5)
          cVMsg = cVMsg + "\n - El C.P. debe tener 5 n�meros.";
        else{
          iError = 0;
          for(i=0; i<cCP.length; i++)
            if(cCP.charAt(i)<'0' || cCP.charAt(i)>'9')
              iError = 1;
          if(iError == 1)
            cVMsg = cVMsg + "\n - El C.P. debe tener solamente caracteres num�ricos.";
        }
      }


      if(form.cCiudad.value=="")
        cVMsg = cVMsg + "\n - Escriba la ciudad del domicilio de la empresa.";

      if(form.iCvePais.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Pais.";
      else if(form.iCveEstado.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Estado.";
      else if(form.iCveMunicipio.value<1)
        cVMsg = cVMsg + "\n - Seleccione un Municipio o Delegaci�n.";

      if(cVMsg!=''){
        alert('Datos no V�lidos: \n' + cVMsg);
        return false;
      }
      else if(!confirm("�Est� Seguro de Guardar la Informaci�n?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
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
           window.parent.FRMOtroPanel.location="pg070601071P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
      }
      else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
      }
    }
    else {
      window.parent.FRMOtroPanel.location="pg070601071P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
