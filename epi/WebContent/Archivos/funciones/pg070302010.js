 function fSelectedPer(iCvePersonal){
    var form = document.forms[0];
    form.hdiCvePersonal.value = iCvePersonal;
   if (form.iCveUniMed){
      form.hdBoton.value = "Nuevo";
   }
   else{
       form.hdBoton.value = "Cancelar";
   }

    form.target = "_self";
    form.submit();

  }


  function fCancelar(){
    var form = document.forms[0];
    form.hdBoton.value = "Cancelar";
    form.target = "_self";
    form.submit();

  }

  function fPbaRapida(){
    var form = document.forms[0];
    form.hdBoton.value = "PbaRapida";
    form.target = "_self";
    form.submit();

  }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }


  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }
    if ((form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'PbaRapida' ) && form.hdBoton.value != 'Cancelar' ) {
      cVMsg = '';
      if (form.iCveUniMed){
         if (form.iCveUniMed.value <= 0){
           cVMsg = cVMsg + " - Debe seleccionar alguna unidad médica. \n";
         }else{
           form.xCveUniMed.value = form.iCveUniMed.value;
         }
      }

      if (form.iCveModulo){
         if (form.iCveModulo.value <= 0 || form.iCveModulo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún módulo. \n";
         }
      }


       if (form.iCveProceso){
         if (form.iCveProceso.value <= 0 || form.iCveProceso.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún proceso. \n";
         }
      }
       if (form.iCveMotivo){
         if (form.iCveMotivo.value <= 0 || form.iCveMotivo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún motivo. \n";
         }
      }
      if (form.iCveModTrans){
         if (form.iCveModTrans.value <= 0 || form.iCveModTrans.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar algún modo de transporte. \n";
         }
      } 


      if (form.iCveUsuAplica){
         if (form.iCveUsuAplica.value <= 0 || form.iCveUsuAplica.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar Médico Aplicante.";
         }
      }

      if (form.iCvePbaRapida){
         cVMsg = cVMsg + fSinValor(form.iCvePbaRapida,3,'Cve. Pba. Rápida', true);
      }

       //Cambio para validación de Persona
      //if (form.hdiCvePersonal)
      //   cVMsg = cVMsg + fSinValor(form.hdiCvePersonal,3,'Persona', true);

      if (form.iCvePersonal)
         cVMsg = cVMsg + fSinValor(form.iCvePersonal,3,'Persona', true);

       if (form.dtAplicacion){
         if (form.dtAplicacion.value != null){
         Dia  = form.dtAplicacion.value.substring(0, 2);
         Mes  = form.dtAplicacion.value.substring(3, 5)-1;
         Anio = form.dtAplicacion.value.substring(6, 10);
         dtFecApi = new Date(Anio,Mes,Dia);

         Dia  = form.hdHoy.value.substring(0, 2);
         Mes  = form.hdHoy.value.substring(3, 5)-1;
         Anio = form.hdHoy.value.substring(6, 10);
         dtFecHoy = new Date(Anio,Mes,Dia);
          if (dtFecApi > dtFecHoy){
            cVMsg = cVMsg + "\n - La Fecha de Aplicación no puede ser mayor a la fecha actual. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtAplicacion,5,'Fecha de Aplicación', true);
      }

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }

      if (form.hdBoton.value == 'Guardar') {
      msg="¿ Desea Almacenar la información de la Prueba Rápida ? ";
        if (!confirm(msg))
            return false;
      }


    }

    return true;
  }

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

  function fIrPagina(UniMed,Proceso,Motivo){
    form = document.forms[0];
    //alert("UniMed: " + UniMed);
   // form.hdBoton.value = 'Nuevo';
    form.target = 'FRMDatos';
    form.action = 'pg070302020.jsp?hdBoton=Nuevo&iCveUniMed=' + UniMed + '&iCveProceso=' + Proceso + '&iCveMotivo=' + Motivo;
    form.submit();
  }