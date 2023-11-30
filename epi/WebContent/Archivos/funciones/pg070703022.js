  function fValidaTodo(){
     return fValorDatos();
  }

  function fValidaDatos(tipoDato) {
     form = document.forms[0];
     cVMsg = '';
     mandatorio = false;
     for (i = 0; i < tipoDato.length; i++) {
// Validar Tipo de Dato
        if (tipoDato[i][1] == 3) {
           if (tipoDato[i][3] == 1 && (form.dRango1.value == '' || form.dRango2.value == ''))    // Es Obligatorio
              cVMsg = cVMsg + '\nEl Rango es obligatorio.';
           if (!fDecimal(form.dRango1.value) || !fDecimal(form.dRango2.value))
              cVMsg = cVMsg + '\nEl Rango solo acepta Decimales.';
        } else {
// Barrer la Forma
           for (e = 0; e < form.elements.length; e++) {
              if (tipoDato[i][2] == form.elements[e].name) {  //Nombre Coincide
// Logico
//                 if (tipoDato[i][1] == 1)
//                    if (tipoDato[i][3] == 1){  // Es Obligatorio
//                       if (!form.elements[e].checked) {
//                          cVMsg = cVMsg + '\nEl campo ' + tipoDato[i][4] + " es obligatorio.";
//                       }
//                    }
// Decimal
                 if (tipoDato[i][1] == 2) {
                    if (tipoDato[i][3] == 1 && form.elements[e].value == '')    // Es Obligatorio
                       cVMsg = cVMsg + '\nEl campo ' + tipoDato[i][4] + " es obligatorio.";
                    if (!fDecimal(form.elements[e].value))
                       cVMsg = cVMsg + '\nEl campo ' + tipoDato[i][4] + " solo acepta decimales.";
                 }
// Texto
                 if (tipoDato[i][1] == 4) {
                    if (tipoDato[i][3] == 1 && form.elements[e].value == '')    // Es obligatorio
                       cVMsg = cVMsg + '\nEl campo ' + tipoDato[i][4] + " es obligatorio.";
                 }
              }
           }
        }
     }
// Kilometraje
     if (form.grabaUltimaEtapa.value == 1 || form.grabaPrimeraEtapa.value == 1) {
        if (form.iKmInicial.value == '') {
           cVMsg = cVMsg + '\nEl campo Kilomteraje es Obligatorio.';
        } else if (!fSoloNumeros(form.iKmInicial.value)) {
           cVMsg = cVMsg + '\nEl campo Kilometraje solo acepta enteros.';
        }
     }

// Ver si pasa
     if (cVMsg != '') {
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
     }else
        return true;
  }

  function fChgArea(fArea){
    form = document.forms[0];
    cText = fArea.value;
    if(cText.length > 2000)
      fArea.value = cText = cText.substring(0,2000);
    form.iNoLetras.value = 2000 - cText.length;
  }