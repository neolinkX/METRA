function fillNext(){
    var form = document.forms[0];
    form.target = "FRMDatos";
    form.submit();
  }
  function fSubmite(){
    var form = document.forms[0];
    form.target = 'FRMDatos';
    form.submit();
  }
  function fIrCatalogo(iCveExp,iNumExm){
    var form=document.forms[0];
    form.hdICveExpediente.value=iCveExp;
    form.hdINumExamen.value=iNumExm;
    form.action="pg070105010.jsp";
    form.target="FRMDatos";
    form.submit();
  }