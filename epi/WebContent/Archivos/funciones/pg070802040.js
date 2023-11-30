function fIrCatalogo(cCampoClave, cCampoClave2, cCampoClave3){
    form = document.forms[0];

    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave3;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070802041.jsp';
    form.submit();
 }


function fSuministrar(cCampoClave, cCampoClave2, cCampoClave3){
    form = document.forms[0];

    if (!confirm(" ¿ Desea Suministrar los Artículos del Almacén ? ")){
      //return false;
    }
    else {
      form.hdCampoClave.value = cCampoClave;
      form.hdCampoClave2.value = cCampoClave2;
      form.hdCampoClave3.value = cCampoClave3;
      form.hdRowNum.value = cCampoClave3;
      form.hdBoton.value = 'Suministrar';
      form.target = 'FRMDatos';
      form.action = 'pg070802040.jsp';
      form.submit();
    }
 }

function fComprobante(cCampoClave, cCampoClave2, cCampoClave3){
    form = document.forms[0];

    if (!confirm(" ¿ Desea Generar el Comprobante de Salida del Almacén ? ")){
      //return false;
    }
    else {
      form.hdCampoClave.value = cCampoClave;
      form.hdCampoClave2.value = cCampoClave2;
      form.hdCampoClave3.value = cCampoClave3;
      form.hdRowNum.value = cCampoClave3;
      form.hdBoton.value = 'Comprobante';
      form.target = 'FRMDatos';
      form.action = 'pg070802040.jsp';
      form.submit();
    }
 }

function fVale(cCampoClave, cCampoClave2, cCampoClave3){
    form = document.forms[0];

    if (!confirm(" ¿ Desea Generar el Pase de Salida del Almacén ? ")){
      //return false;
    }
    else {
      form.hdCampoClave.value = cCampoClave;
      form.hdCampoClave2.value = cCampoClave2;
      form.hdCampoClave3.value = cCampoClave3;
      form.hdRowNum.value = cCampoClave3;
      form.hdBoton.value = 'Vale';
      form.target = 'FRMDatos';
      form.action = 'pg070802040.jsp';
      form.submit();
    }
 }






