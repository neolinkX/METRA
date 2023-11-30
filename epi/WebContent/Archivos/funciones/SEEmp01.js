  function fBuscar(){

    form = document.forms[0];
    cCondicion = '';

    if(form.cRFC.value != '' && form.cRFC.value.length > 1)
      cCondicion = cCondicion + " UCASE(cRFC) like '" + form.cRFC.value + "%' and";

    if(form.cCURP.value != ''  && form.cCURP.value.length > 1)
      cCondicion = cCondicion + " UCASE(cCURP) like '" + form.cCURP.value + "%' and";

    if(form.cNombreRS.value != ''  && form.cNombreRS.value.length > 1)
      cCondicion = cCondicion + " UCASE(cNombreRS) like '" + form.cNombreRS.value + "%' and";

    if(form.cPaterno.value != ''  && form.cPaterno.value.length > 1)
      cCondicion = cCondicion + " UCASE(cApPaterno) like '" + form.cPaterno.value + "%' and";

    if(form.cMaterno.value != ''  && form.cMaterno.value.length > 1)
      cCondicion = cCondicion + " UCASE(cApMaterno) like '" + form.cMaterno.value + "%' and";

    if(form.cDenominacion.value != ''  && form.cDenominacion.value.length > 1)
      cCondicion = cCondicion + " UCASE(cNombre) like '" + form.cDenominacion.value + "%' and";

    if(form.cCveRPA.value != ''  && form.cCveRPA.value.length > 1)
      cCondicion = cCondicion + " UCASE(cCveRPA) like '" + form.cCveRPA.value + "%' and";

    if(cCondicion != ''){
      cCondicion = " where cTpoPersona = '" +  form.cTpoPersona.value + "' and" + cCondicion.substring(0,cCondicion.length - 3);
      form.hdBuscar.value = 1;
      form.action = "SEEmp01.jsp";
      form.hdCondicion.value = cCondicion;
      form.submit();
    }else
      alert('Debe introducir alguna condición de búsqueda válida (mayor a dos caracteres)');
  }

  function fEmpSelected(iCveEmpresa, cNombreRS, cApPaterno, cApMaterno, cRFC, cCURP, cTpoPersona){
     if(window.opener.fEmpSelected){
       window.opener.fEmpSelected(iCveEmpresa, cNombreRS, cApPaterno, cApMaterno, cRFC, cCURP, cTpoPersona);
       window.close();
     }else{
       alert('El módulo ya no se encuentra disponible.');
       window.close();
     }
  }
  
  function fEmpSelecDom(){
	       alert('La empresa que desea seleccionar no cuenta con una dirección, favor de reportarlo al administrador!');
  }
  
   var wPrueba;
  function fDetalle2(iCveEmpresa){
    if((wPrueba != null) && (!wPrueba.closed))
      wPrueba.focus();
    else{
      wPrueba = open("SEDetEmp.jsp?hdiCveEmpresa=" + iCveEmpresa, "Selector1",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=550,height=575,screenX=800,screenY=600');
      wPrueba.moveTo(15, 15);
    }
  }
