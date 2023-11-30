
  function fListado1(rwId,rwId2){
     forma = document.forms[0];

     forma.hdIni.value    = rwId;
     forma.hdIni2.value   = rwId2;
     forma.hdBoton.value  = 'Actual';
     forma.target         = 'FRMDatos';
     forma.action         = 'pg070502032.jsp';
     forma.submit();
  }

  function fCopiar(rwId,rwId2){

     if(confirm("¿ Está Seguro de Copiar la Plantilla ?")){
       forma = document.forms[0];
       forma.hdIni.value    = rwId;
       forma.hdIni2.value   = rwId2;
       forma.hdBoton.value  = 'Copiar';
       forma.target         = 'FRMDatos';
       forma.action         = 'pg070502031.jsp';
       forma.submit();
     }

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







