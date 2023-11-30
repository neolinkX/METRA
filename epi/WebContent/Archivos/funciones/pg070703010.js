 function fIrCatalogo(iCveVehiculo,cPagina) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.hdCampoClave.value = iCveVehiculo;
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }

  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }

    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }


    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';

      if (form.dtSolicitud && form.dtVenceLic && form.dtSolicitud.value!='' && form.dtVenceLic.value!=''){
         iAnio = form.dtSolicitud.value.substring(6,10);
         iMes  = form.dtSolicitud.value.substring(3,5);
         iDia  = form.dtSolicitud.value.substring(0,2);
         var dtSolicitud = new Date(iAnio,iMes,iDia);
       
         iAnio = form.dtVenceLic.value.substring(6,10);
         iMes  = form.dtVenceLic.value.substring(3,5);
         iDia  = form.dtVenceLic.value.substring(0,2);
         var dtVenceLic = new Date(iAnio,iMes,iDia);
         if (dtSolicitud > dtVenceLic){
            cVMsg = "La Fecha de Vencimiento de la Licencia no debe ser menor a la Fecha de Solicitud!";
            form.dtVenceLic.value = "";
         }
      }
      
      if (cVMsg == ''){
         if (form.iCveUniMed && form.iCveUniMed.selectedIndex==0)
           cVMsg = cVMsg + " \n - Debe seleccionar una Unidad Médica";

         if (form.iCveModulo && form.iCveModulo.selectedIndex==0)
           cVMsg = cVMsg + "\n - Debe seleccionar un Módulo";

         if (form.iCveArea && form.iCveArea.selectedIndex==0)
           cVMsg = cVMsg + "\n - Debe seleccionar una Área";

         if (form.dtRegistro)
           cVMsg = cVMsg + fSinValor(form.dtRegistro,5,'Fecha de Registro:', true);

         if (form.iCveUsuSolic && form.iCveUsuSolic.selectedIndex==0)
           cVMsg = cVMsg + "\n - Debe seleccionar al Usuario que Solicita";

/*         if (form.iCveUsuConductor && form.iCveUsuConductor.selectedIndex==0)
           cVMsg = cVMsg + "\n - Debe seleccionar al Usuario que Conduce"; */

         if (form.cLicencia)
           cVMsg = cVMsg + fSinValor(form.cLicencia,2,'Licencia:', true);

         if (form.lLicPermanente && !form.lLicPermanente.checked){
           if (form.dtVenceLic)
             cVMsg = cVMsg + fSinValor(form.dtVenceLic,5,'Vencimiento de Licencia:', true);
         }
         if (form.lLicPermanente && form.lLicPermanente.checked)
            form.dtVenceLic.value = '';

         if (form.iCveMotivo && form.iCveMotivo.selectedIndex==0)
           cVMsg = cVMsg + "\n - Debe seleccionar el Motivo";

         if (form.cDestino)
           if (form.cDestino.value == '')
             cVMsg = cVMsg + '\n - El campo Destino es obligatorio';

         if (form.dtSolicitud)
           cVMsg = cVMsg + fSinValor(form.dtSolicitud,5,'Fecha de Solicitud:', true);

         var itotal = 0; 
         if (form.idias && form.ihoras){
             if(form.idias.value!=''){
               cVMsg = cVMsg + fSinValor(form.idias,3,'días del tiempo requerido:', true);
               itotal = parseInt(form.idias.value,10) * 24;
             }
             if (form.ihoras.value!=''){
               cVMsg = cVMsg + fSinValor(form.ihoras,3,'horas del tiempo requerido:', true);
               itotal = itotal + parseInt(form.ihoras.value,10);
             }
             if (itotal==0 || isNaN(itotal))
                cVMsg = cVMsg + "\n - Debe proporcionar al menos los días o las horas del Tiempo Requerido!";
         }

      }
      

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
    }
     return true;
   }


  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070703010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070703010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
    }
  }
