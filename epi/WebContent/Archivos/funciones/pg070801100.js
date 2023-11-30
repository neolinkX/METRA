function fIrCatalogo(cCampoClave){
    form = document.forms[0];

    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070801101.jsp';
    form.submit();
 }



