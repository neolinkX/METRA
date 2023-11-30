  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Desactivar el Registro?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
       var iSolicitud = 0;
       var lGuardar = true;
       cVMsg = '';
       for (i = 0; i < form.length; i++){
          str = form.elements[i].name;
          if (str.substring(0,10)=="dUnidAutor"){
             if (form.elements[i].value != ''){
                 iSolicitud = str.substring(10,form.elements[i].length);
                 if (form.elements[i]){
                     if (parseFloat(form.elements[i].value) < 0){
                        cVMsg = cVMsg + "\n - Las Unidades a Autorizar de la Solicitud " + iSolicitud + " deben ser positivas";
                     }
                     else{
                       var objetoOculto;
                       for (j=0; j<form.length;j++){
                         if (form.elements[j].name == 'dUnidSolicita' + str.substring(10)){
                           objetoOculto = form.elements[j];
                           break;
                         }
                       }
                       if (parseFloat(objetoOculto.value) < parseFloat(form.elements[i].value))
                         cVMsg = cVMsg + "\n - Las Unidades a Autorizar deben ser menores o iguales a las solicitadas";
                       else
                         cVMsg = fSinValor(form.elements[i],4,'Cantidad de la Solicitud ' + iSolicitud, true);
                     }
                 }
             }
             if (cVMsg != ''){
                alert("Datos no Válidos: \n" + cVMsg);
                return false;
             }
          }
       }
       if(!confirm("¿Está Seguro de Guardar las Unidades Establecidas?"))
          return false;
       else{
          form.target="_self";
          form.submit();
       }
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cPropiedad)
         cVMsg = cVMsg + fSinValor(form.cPropiedad,1,'Propiedad', true);
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }

  function fAccion(cAccion){
     form = document.forms[0];
     if(confirm("¿Está Seguro de Autorizar las Unidades Establecidas?")){
        form.target="_self";
        form.hdBoton.value = cAccion;
        form.submit();
     }
  }

  function Suma(dUniAut,dDif,dUnidSol){
     form = document.forms[0];
     dDif.value = dUnidSol.value - dUniAut.value;
     var dTotUniAuto = 0;
     var dTotDif = 0;

     for (i = 0; i < form.length; i++){
        str = form.elements[i].name;
        if (str.substring(0,10)=="dUnidAutor"){
           if (form.elements[i].value!='')
               dTotUniAuto = dTotUniAuto + parseFloat(form.elements[i].value);
        }
        if (str.substring(0,11)=="dDiferencia"){
           if (form.elements[i].value!='')
              dTotDif     = dTotDif + parseFloat(form.elements[i].value);
        }
     }
     document.forms[0].dTotUniAuto.value = dTotUniAuto;
     document.forms[0].dTotDif.value     = dTotDif;

     if (dTotUniAuto > parseFloat(form.iExistencias.value)){
        alert('La asignación de las Unidades Autorizadas excede las Existenias!');
        for (i = 0; i < form.length; i++){
           str = form.elements[i].name;
           if (str.substring(0,10)=="dUnidAutor")
              form.elements[i].value = '';
           if (str.substring(0,11)=="dDiferencia")
              form.elements[i].value = '';
        }
        document.forms[0].dTotUniAuto.value = '';
        document.forms[0].dTotDif.value     = '';
     }
  }
