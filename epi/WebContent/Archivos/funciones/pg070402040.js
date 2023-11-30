  function fIrPagina(iCvePersonal,page,id){
    var form=document.forms[0];
    form.hdiCvePersonal.value=iCvePersonal;
    form.iCvePersonal.value=iCvePersonal;
    form.hdBoton.value='Actual';
    form.target='FRMDatos';
    if (id == 0){
       form.action='pg0704020'+page+'.jsp';
    }else{
       form.action='pg0701050'+page+'.jsp';
    }
    form.submit();
  }
