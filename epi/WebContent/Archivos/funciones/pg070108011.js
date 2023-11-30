  function fValidaTodo(){
    var form = document.forms[0];
    lRadioCheck = false;  

    if(form.hdBoton.value == 'Generar Reporte')
       return true;
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