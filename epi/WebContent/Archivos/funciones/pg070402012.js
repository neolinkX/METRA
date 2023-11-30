  function fIr(iAnio,iCveMdoTrans,iIDDGPMPT,iCveVehiculo,cDscMdoTrans){
    form = document.forms[0];
    form.hdiAnio.value = iAnio;
    form.hdiCveMdoTrans.value = iCveMdoTrans;
    form.hdiIDDGPMPT.value = iIDDGPMPT;
    form.hdiCveVehiculo.value = iCveVehiculo;
    form.hdcDscMdoTrasn.value = cDscMdoTrans;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070402013.jsp';
    form.submit();
  }