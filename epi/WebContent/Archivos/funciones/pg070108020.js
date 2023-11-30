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

  function fIrCatalogo(iCveUniMed,iCveModulo,iCveServicio,iCveRama,ramaInicial,iCveExpediente,iNumExamen){
    var form=document.forms[0];
	form.iCveUniMed.value = iCveUniMed;
	form.iCveProceso.value = "1";
	form.iCveModulo.value = iCveModulo;
	form.iCveServicio.value = iCveServicio;
	form.iCveRama.value = iCveRama;
	form.ramaInicial.value = ramaInicial;
	form.iCveExpediente.value = iCveExpediente;
	form.iNumExamen.value = iNumExamen;

    form.action="pg070108021.jsp";
    form.target="FRMDatos";
    form.submit();
  } 