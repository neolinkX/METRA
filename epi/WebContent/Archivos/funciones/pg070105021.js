  function fIrCatalogo(page){
    var form=document.forms[0];
    form.action=page;
    form.target="FRMDatos";
    form.submit();
  }