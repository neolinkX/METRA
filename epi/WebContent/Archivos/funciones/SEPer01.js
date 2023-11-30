  function fBuscar(){
    form = document.forms[0];
    lAcceso = true;
    cCondicion = '';

    if(form.iCvePersonal.value != ''){
      if(fSoloNumeros(form.iCvePersonal.value))
        cCondicion = " PERDatos.iCvePersonal = " + form.iCvePersonal.value + " and";
      else
        lAcceso = false;
    }

    if(form.cRFC.value != '' && lAcceso && form.cRFC.value.length > 1)
      cCondicion = cCondicion + " UCASE(PERDatos.cRFC) like '" + form.cRFC.value + "%' and";

    if(form.cCURP.value != ''  && lAcceso && form.cCURP.value.length > 1)
      cCondicion = cCondicion + " UCASE(PERDatos.cCURP) like '" + form.cCURP.value + "%' and";

    if(form.cPaterno.value != ''  && lAcceso && form.cPaterno.value.length > 1)
      cCondicion = cCondicion + " UCASE(PERDatos.cApPaterno) like '" + form.cPaterno.value + "%' and";

    if(form.cMaterno.value != ''  && lAcceso && form.cMaterno.value.length > 1)
      cCondicion = cCondicion + " UCASE(PERDatos.cApMaterno) like '" + form.cMaterno.value + "%' and";

    if(form.cNombre.value != ''  && lAcceso && form.cNombre.value.length > 1)
      cCondicion = cCondicion + " UCASE(PERDatos.cNombre) like '" + form.cNombre.value + "%' and";

    if(cCondicion != ''){
      cCondicion = ' where ' + cCondicion.substring(0,cCondicion.length - 3);
    }

    if(lAcceso && cCondicion != ''){
      form.hdBuscar.value = 1;
      form.action = "SEPer01.jsp";
      form.hdCondicion.value = cCondicion;
      form.submit();
    }else
      alert('Debe introducir alguna condición de búsqueda válida (mayor a 2 caracteres)');
  }

  function fSelected(iCvePersonal,iCveExpediente,iNumExamen,cNomCompleto){
     if(window.opener.fSelectedPer){
       window.opener.fSelectedPer(iCvePersonal, iCveExpediente, iNumExamen,cNomCompleto);
       window.close();       
     }else{
       alert('El módulo ya no se encuentra disponible.'); 
       window.close();
     }
  }

  var wPrueba;
  function fDetalle(iCvePersonal){
    if((wPrueba != null) && (!wPrueba.closed))
      wPrueba.focus();
    else{
      wPrueba = open("SEDetPer.jsp?hdiCvePersonal=" + iCvePersonal, "Selector1",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=550,height=575,screenX=800,screenY=600');
      wPrueba.moveTo(15, 15);
    }
  }

  function fChgUniMed(){
    form = document.forms[0];
    form.action = "SEPer01.jsp";
    form.submit();
  }

  function fPreDet(){
    form = document.forms[0];
    if(form.iCveUniMed.value == '' || form.iCveModulo.value == '')
      alert('Debe seleccionar una Unidad Médica o Módulo Válidos!');
    else{
      if(confirm("¿Desea usted Predeterminar el valor Actual de Unidad Médica y Módulo?")){
        form.action = "SEPer01.jsp";
        form.hdPreDet.value = "1";
        form.submit();
      }  
    } 
  }

  function fVer(evt){
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if(charCode == 13){
      fBuscar();
    }
  }

  function fOnLoad(){
    form = document.forms[0];
    form.cRFC.focus();
  }