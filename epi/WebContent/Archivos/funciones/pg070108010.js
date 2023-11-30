function fillNext(){
    var form=document.forms[0];
    form.target="FRMDatos";
    form.submit();
  }
  function fSubmite(){
    var form=document.forms[0];
    if(fValFecha(form.dtConcluido.value) && fValFecha(form.dtConcluido2.value)){
      form.target='FRMDatos';
      form.submit();
    }else{
      alert("Verifique la fecha");
      form.dtConcluido.focus();
      form.dtConcluido.select();
    }
  }
  function fIrCatalogo(iCveExp,iNumExm){
    var form=document.forms[0];
    form.hdICveExpediente.value=iCveExp;
    form.hdINumExamen.value=iNumExm;
    form.action="pg070108011.jsp";
    form.target="FRMDatos";
    form.submit();
  } 