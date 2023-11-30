  function fBuscar(){
    form = document.forms[0];
    lAcceso = true;
    cCondicion = '';

    if(form.iCveUsuario.value != ''){
      if(fSoloNumeros(form.iCveUsuario.value))
        cCondicion = " where segusuario.iCveUsuario = " + form.iCveUsuario.value + " and";
      else
        lAcceso = false;
    }

    if(form.cUSUARIO.value != '' && lAcceso && form.cUSUARIO.value.length > 1)
      cCondicion = cCondicion + " segusuario.cUSUARIO like '" + form.cUSUARIO.value + "%' and";

   // if(form.cCURP.value != ''  && lAcceso && form.cCURP.value.length > 1)
   //   cCondicion = cCondicion + " PERDatos.cCURP like '" + form.cCURP.value + "%' and";

    if(form.cPaterno.value != ''  && lAcceso && form.cPaterno.value.length > 1)
      cCondicion = cCondicion + " segusuario.cApPaterno like '" + form.cPaterno.value + "%' and";

    if(form.cMaterno.value != ''  && lAcceso && form.cMaterno.value.length > 1)
      cCondicion = cCondicion + " segusuario.cApMaterno like '" + form.cMaterno.value + "%' and";

    if(form.cNombre.value != ''  && lAcceso && form.cNombre.value.length > 1)
      cCondicion = cCondicion + " segusuario.cNombre like '" + form.cNombre.value + "%' and";

    if(cCondicion != ''){
      cCondicion = '  ' + cCondicion.substring(0,cCondicion.length - 3);
    }

    if(lAcceso && cCondicion != ''){
      form.hdBuscar.value = 1;
      form.action = "SePerMed.jsp";
      form.hdCondicion.value = cCondicion;
      form.submit();
    }else
      alert('Debe introducir alguna condición de búsqueda válida (mayor a 2 caracteres)');
  }

  //function fSelected(iCvePersonal,iCveExpediente,iNumExamen,cNomCompleto){
  function fSelected(iCveUsuario){
     if(window.opener.fSelectedPer){
       //window.opener.fSelectedPer(iCvePersonal, iCveExpediente, iNumExamen,cNomCompleto);
       window.opener.fSelectedPer(iCveUsuario);
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
        form.action = "SEPerMed.jsp";
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
    form.cUSUARIO.focus();
  }