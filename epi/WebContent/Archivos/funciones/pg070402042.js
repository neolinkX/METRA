  function fIrCatalogo(iAnio,iCveMdoTrans,iIDDGPMPT,page){
    var form=document.forms[0];
    form.iAnio.value=iAnio;
    form.iCveMdoTrans.value=iCveMdoTrans;
    form.iIDDGPMPT.value=iIDDGPMPT;
    form.hdiAnio.value=iAnio;
    form.hdiCveMdoTrans.value=iCveMdoTrans;
    form.hdiIDDGPMPT.value=iIDDGPMPT;
    form.hdiIdefMedPrev.value=iIDDGPMPT;
    form.hdBoton.value='Actual';
    form.target='FRMDatos';
    form.action='pg0704020'+page+'.jsp';
    form.submit();
  }
  function fGuardar(){
    var form=document.forms[0];
    form.hdBoton.value='Guardar';
    form.target='FRMDatos';
    form.action='pg070402010.jsp';
    form.submit();
  }
