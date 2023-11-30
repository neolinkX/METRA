  function fIrCatalogo(iAnio,iCveMdoTrans,iIDDGPMPT,page,iCveModulo){
    var form=document.forms[0];
    form.iAnio.value=iAnio;
    form.iCveMdoTrans.value=iCveMdoTrans;
    form.iIDDGPMPT.value=iIDDGPMPT;
    form.hdiAnio.value=iAnio;
    form.hdiCveMdoTrans.value=iCveMdoTrans;
    form.hdiIDDGPMPT.value=iIDDGPMPT;
    form.hdiIdefMedPrev.value=iIDDGPMPT;
    if(iCveModulo){
      form.iCveModulo.value = iCveModulo;
    }
    form.hdBoton.value='Actual';
    form.target='FRMDatos';
    form.action='pg0704020'+page+'.jsp';
    form.submit();
  }
  function fGuardar(){
    var form=document.forms[0];

    if(confirm("¿ Está Seguro de Guardar la Situación de Ninguno, Investigado o Cancelado ?")){
      form.hdBoton.value='Guardar';
      form.target='FRMDatos';
      form.action='pg070402010.jsp';
      form.submit();
    }
  }
