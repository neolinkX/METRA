  function fIrCatalogo(iCveRecomendacion){
    var form=document.forms[0];
    form.hdRowNum.value=iCveRecomendacion;
    form.hdBoton.value='Actual';
    form.target='FRMDatos';
    form.action='pg070401051.jsp';
    form.submit();
  }
  function fGenArchivo(){
    if(confirm("¿Desea usted generar el archivo JS de las recomendaciones de Inv. de Accidentes?")){
      form = document.forms[0];
      form.hdBoton.value="GenJS";
      form.target="_self";
      form.submit();
    }
  }