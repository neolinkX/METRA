  function fIrCatalogo(cCampoClave1, cCampoClave2){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdRowNum.value = cCampoClave1;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101021.jsp';
    form.submit();
  }

  function fGenArchivo(){
    if(confirm("¿ Desea usted generar el archivo de los diagnósticos CIE de uso mas Frecuente ?")){
      form = document.forms[0];
      form.hdBoton.value="GenJS";
      form.target="_self";
      form.submit();
    }
  }

    function fGenArchivoTodos(){
    if(confirm("¿ Desea usted generar el archivo de los diagnósticos CIE ?")){
      form = document.forms[0];
      form.hdBoton.value="GenJSTodos";
      form.target="_self";
      form.submit();
    }
  }