 function fValidaTodo(){
    var form = document.forms[0];

     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
        if(!confirm("¿Desea guardar la configuración del examen?")) 
           return false;
     }

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }
    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cDscBreve)
        cVMsg = cVMsg + fSinValor(form.cDscBreve,2,'Descipción Breve:', false);
      if (form.cPregunta)
        cVMsg = cVMsg + fSinValor(form.cPregunta,2,'Pregunta:', false);
      if (form.cEtiqueta)
        cVMsg = cVMsg + fSinValor(form.cEtiqueta,2,'Etiqueta:', false);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
    return true;
  }

function fillNext(){
       form = document.forms[0];
       form.target =  "FRMDatos";
       form.hdBoton.value = "Nuevo";
       form.submit();
}

function fIr(cCampoClave, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.SLSProceso.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070101071.jsp';
    form.submit();
  }


function fillBusca(){
       form = document.forms[0];
       form.hdBoton.value = "Buscar";
       form.target =  "FRMDatos";
       form.submit();
}