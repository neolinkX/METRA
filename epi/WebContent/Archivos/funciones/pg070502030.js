
  function fListado1(rwId){
     forma = document.forms[0];
     forma.iCveEmpresa.value = rwId;
     forma.hdRowNum.value = rwId;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070502011.jsp';
     forma.submit();
  }

  function fListado2(rwId){
     forma = document.forms[0];

     forma.hdIni.value    = rwId;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070502031.jsp';
     forma.submit();
  }

  function fSubmitForm1(theButton){
    var form = document.forms[0];
    form.hdBoton.value = theButton;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    else{
      form.target="_self";
      if (window.fValidaTodo)
        lSubmitir = fValidaTodo();
      else
        lSubmitir = true;
      if (lSubmitir){
        window.parent.parent.FRMCuerpo.FRMFiltro.FRMBusqueda.fSubmiteInt();
      }else
        form.hdBoton.value = "";
    }
  }









