function fIrCatalogo(cCampoClave){
    form = document.forms[0];

    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101041.jsp';
    form.submit();
 }

function fIrRamas(cCampoClave){
    form = document.forms[0];

    form.hdCampoClave.value = cCampoClave;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101050.jsp';
    form.submit();
 }

