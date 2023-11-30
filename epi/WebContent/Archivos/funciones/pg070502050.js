  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           objDes[0].value="-1";
           window.parent.FRMOtroPanel.location="pg070502050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070502050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fIrCatalogo(iCveEmpresa,iCvePlantilla,cPagina){
    form = document.forms[0];
    form.hdIni.value = iCveEmpresa;
    form.hdIni2.value = iCvePlantilla;
    form.hdRowNum.value = iCveEmpresa;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }


   function Activar1(lActivar){
      form = document.forms[0];
      form.lProgramada.disabled = lActivar;
      form.lExtra.disabled = lActivar;
   }

   function Activar2(lActivar){
      form = document.forms[0];
      form.iAnio.disabled = lActivar;
      form.iCvePeriodPla.disabled = lActivar;
   }

   function Activar3(lActivar){
      form = document.forms[0];
      form.dtSolicitud.disabled = lActivar;
      if (!lActivar)
        form.dtSolicitud.value = form.hdToday.value;
      else
        form.dtSolicitud.value = "";
   }

   function Activar4(lActivar){
      form = document.forms[0];
      form.iCvePais.disabled = lActivar;
      form.iCveEstado.disabled = lActivar;
      form.iCveMunicipio.disabled = lActivar;
   }

   function Activar5(lActivar){
      form = document.forms[0];
      form.cRFC.disabled          = lActivar;
      form.cDenominacion.disabled = lActivar;
      form.cIDDGPMPT.disabled     = lActivar;
   }

   function fValidaBuscar(){
     form = document.forms[0];
     cVMsg = '';

     if (form.iCveUniMed){
        if (form.iCveUniMed.value == '-1'){
           cVMsg = cVMsg + "\n Debe Seleccionar la Unidad Médica";
        }
        if (form.iIDMdoTrans)
           cVMsg = cVMsg + fSinValor(form.iIDMdoTrans,3,'ID Modo de Transporte', false);

     }

     if (form.lUbicacion){
       if (form.lUbicacion.checked){
          if (form.iCvePais){
            if (form.iCvePais.value == '-1'){
              cVMsg = cVMsg + "\n Debe Seleccionar el País";
            }
          }
          if (form.iCveEstado){
            if (form.iCveEstado.value == '-1'){
              cVMsg = cVMsg + "\n Debe Seleccionar el Estado";
            }
          }
          if (form.iCveMunicipio){
            if (form.iCveMunicipio.value == '-1'){
              cVMsg = cVMsg + "\n Debe Seleccionar la Delegación o Municipio";
            }
          }
       }
     }

     if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
     }

     return true;
   }



