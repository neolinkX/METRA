function fReactCalib() {
     form = document.forms[0];
     //form.target = 'FRMDatos';
     //form.submit();
}

function fBuscar() {
     form = document.forms[0];
     form.target = 'FRMDatos';
     form.hdBuscar.value = 'S';
     form.submit();
}


 function fIrCatalogo(cCampoClave, cCampoClave2 ,cCampoClave3){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCampoClave3.value = cCampoClave3;
    //form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070306031.jsp';
    form.submit();
  }

