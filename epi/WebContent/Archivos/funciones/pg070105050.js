function fIrCatalogo(iNumExamen){
    var form=document.forms[0];
    form.hdINumExamTmp.value=iNumExamen;
    form.action="pg070105020.jsp";
    form.target="FRMDatos";
    form.submit();
  }