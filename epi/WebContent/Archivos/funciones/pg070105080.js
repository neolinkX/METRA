function fillNext(){
    var form=document.forms[0];
    form.target="FRMDatos";
    form.submit();
  }
  function fSubmite(){
    var form=document.forms[0];
    if(fValFecha(form.dtConcluido.value)){
      form.target='FRMDatos';
      form.submit();
    }else{
      alert("Verifique la fecha");
      form.dtConcluido.focus();
      form.dtConcluido.select();
    }
  }
