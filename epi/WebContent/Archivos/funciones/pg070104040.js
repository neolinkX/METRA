 function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    var form = document.forms[0];
    form.hdiCvePersonal.value = iCvePersonal;
    form.hdiCveExpediente.value = iCveExpediente;
    form.hdiNumExamen.value = iNumExamen;
   if (form.iCveUniMed){
      form.hdBoton.value = "Nuevo";
   }
   else{
       form.hdBoton.value = "Cancelar";
   }

    form.target = "_self";
    form.submit();

  }


  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deber�n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensi�n js (vg. pg0702061.js)
  function fValidaTodo(){
    var form = document.forms[0];
    var Dia;
    var Mes;
    var Anio;

    /* Comento MGonzalez 
    if(form.hdBoton.value=='Nuevo'){
      if(form.iCveUniMed.value!=null && form.iCveUniMed.value!="null" && form.iCveUniMed.value!="" && (form.hdiCveUniMed.value==null || form.hdiCveUniMed.value=="null" || form.hdiCveUniMed.value==""))
        form.hdiCveUniMed.value = form.iCveUniMed.value;
    }
    */
     
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("�Est� Seguro de Eliminar el Registro?"))
        return false;
    }
    if ((form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'PbaRapida' ) && form.hdBoton.value != 'Cancelar' ) {
      cVMsg = '';
      if (form.iCveUniMed){
         if (form.iCveUniMed.value <= 0){
           cVMsg = cVMsg + " - Debe seleccionar alguna unidad m�dica. \n";
         }else{
           form.xCveUniMed.value = form.iCveUniMed.value;
         }
      }

       if (form.iCveModulo){
         if (form.iCveModulo.value <= 0 || form.iCveModulo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n m�dulo. \n";
         }
      }

       if (form.iCveProceso){
         if (form.iCveProceso.value <= 0 || form.iCveProceso.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n proceso. \n";
         }
      }
       if (form.iCveMotivo){
         if (form.iCveMotivo.value <= 0 || form.iCveMotivo.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n motivo. \n";
         }
      }

       if (form.iCveModTrans){
         if (form.iCveModTrans.value <= 0 || form.iCveModTrans.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar alg�n modo de transporte. \n";
         }
      } 
      if (form.iCveUsuAplica){
         if (form.iCveUsuAplica.value <= 0 || form.iCveUsuAplica.value == ""){
           cVMsg = cVMsg + " - Debe seleccionar M�dico Aplicante.";
         }
      }

      if (form.iCvePbaRapida){
         cVMsg = cVMsg + fSinValor(form.iCvePbaRapida,3,'Cve. Pba. R�pida', true);
      }

      if(form.iNumExamen.value!=null && form.iNumExamen.value!="null" && form.iNumExamen.value!="" && (form.hdiNumExamen.value==null || form.hdiNumExamen.value=="null" || form.hdiNumExamen.value==""))
        form.hdiNumExamen.value = form.iNumExamen.value;

      if(form.iCveExpediente.value!=null && form.iCveExpediente.value!="null" && form.iCveExpediente.value!="" && (form.hdiCveExpediente.value==null || form.hdiCveExpediente.value=="null" || form.hdiCveExpediente.value==""))
        form.hdiCveExpediente.value = form.iCveExpediente.value;

     //Cambio para validaci�n de Persona
      if(form.iCvePersonal.value!=null && form.iCvePersonal.value!="null" && form.iCvePersonal.value!="" && (form.hdiCvePersonal.value==null || form.hdiCvePersonal.value=="null" || form.hdiCvePersonal.value==""))
        form.hdiCvePersonal.value = form.iCvePersonal.value;
      if(form.hdiCvePersonal)
        cVMsg = cVMsg + fSinValor(form.hdiCvePersonal,3,'Persona', true);

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
            cVMsg = cVMsg + "\n - La Fecha de Aplicaci�n no puede ser mayor a la fecha actual. \n";
           }
         }
         cVMsg = cVMsg + fSinValor(form.dtAplicacion,5,'Fecha de Aplicaci�n', true);
      }

      if (cVMsg != ''){
         alert("Datos no V�lidos: \n" + cVMsg);
         return false;
      }

      if (form.hdBoton.value == 'Guardar') {
      msg="�Desea almacenar la informaci�n de la prueba r�pida? ";
        if (!confirm(msg))
            return false;
      }


    }

    return true;
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

     //alert("vals js: " + val1 + "** " + val2);
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070104040P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070104040P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;;
    }
  }

 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

  function fIrPagina(UniMed,Proceso,Motivo,UsuAplica,Modulo,Transporte){
    form = document.forms[0];
    form.target = 'FRMDatos';
    //alert ("vals UM: " + UniMed  + " proc: " + Proceso);
    //alert ("vals mod: " + Modulo  + " trans: " + Transporte);
    form.action = 'pg070104050.jsp?hdBoton=Nuevo&iCveUniMed=' + UniMed + '&iCveProceso=' + Proceso + '&iCveMotivo=' + Motivo + '&iCveUsuRecolec=' + UsuAplica + '&iCveModulo=' + Modulo + '&iCveModTrans=' + Transporte ;
    form.submit();
  }


