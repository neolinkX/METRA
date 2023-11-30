  function fIrCatalogo(cCampoClave1, cCampoClave2, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave1.value = cCampoClave1;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCPropiedad.value = cPropiedad;
    //form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070306011.jsp';
    form.submit();
  }

function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1999)
    fArea.value = cText = cText.substring(0,1999);
  form.iNoLetras.value = 1999 - cText.length;
}

function fChgTog(){
  form = document.forms[0];
  if(form.lAgotado){
     form.dtAgotado.disabled = !form.lAgotado.checked;
     if(!form.lAgotado.checked){
       form.dtAgotado.value = '';
     }
  }
}
