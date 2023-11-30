   function Activar1(Obj,lActivar){
      Obj.disabled = lActivar;
   }

   function Activar2(lActivar){
      form = document.forms[0];
      form.iCveTpoMantto2.disabled = lActivar;
      form.Mant1[0].disabled = lActivar;
      form.Mant1[1].disabled = lActivar;
      Activar3(true,true);
   }

   function Activar3(lActivar1,lActivar2){
      form = document.forms[0];
      form.Prov[0].disabled = lActivar1;
      form.Prov[1].disabled = lActivar1;
      form.iAnio.disabled   = lActivar2;
      form.iMes.disabled = lActivar2;

   }

   function fValidaBuscar(){
      form = document.forms[0];
      var cVMsg = '';

      if (form.iCveUniMed && form.iCveUniMed.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar una Unidad Médica';

      if (form.lTpoVeh!=null && form.lTpoVeh.checked){
         if (form.iCveTpoVehiculo.selectedIndex == 0){
            cVMsg = cVMsg + '\n - Debe seleccionar un Tipo de Vehículo'; 
         }
      }
      if (form.lMarca!=null && form.lMarca.checked){
         if (form.iCveMarca.selectedIndex == 0){
            cVMsg = cVMsg + '\n - Debe seleccionar una Marca'; 
         }
      }
      if (form.lTpoMantto!=null && form.lTpoMantto.checked){
         if (form.iCveTpoMantto2.selectedIndex==0)
            cVMsg = cVMsg + '\n - Debe seleccionar un Tipo de Mantenimiento';
         if (!form.Mant1[0].checked && !form.Mant1[1].checked){
            cVMsg = cVMsg + '\n - Debe seleccionar una Especificación'; 
         }
         else{
            if (form.Mant1[0].checked){
               if (!form.Prov[0].checked && !form.Prov[1].checked){
                  cVMsg = cVMsg + '\n - Debe seleccionar una Especificación de Proveedor';
               }
            }
         }
      }


      if (form.dtSolicitud && form.dtSolicitud.value !='')
        cVMsg = cVMsg + fSinValor(form.dtSolicitud,5,'Fecha de Solicitud', true);

      if (form.dtProgramada && form.dtProgramada.value != '')
        cVMsg = cVMsg + fSinValor(form.dtProgramada,5,'Fecha Programada', true);

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }else{
         if (form.dtSolicitud && form.dtProgramada){
            iAnio = form.dtSolicitud.value.substring(6,10);
            iMes  = form.dtSolicitud.value.substring(3,5);
            iDia  = form.dtSolicitud.value.substring(0,2);
            var dtSolicitud = new Date(iAnio,iMes,iDia);
          
            iAnio = form.dtProgramada.value.substring(6,10);
            iMes  = form.dtProgramada.value.substring(3,5);
            iDia  = form.dtProgramada.value.substring(0,2);
            var dtProgramada = new Date(iAnio,iMes,iDia);
            if (dtSolicitud > dtProgramada){
               alert("La Fecha de Solicitud no debe ser mayor a la Fecha Programada!");
               form.dtSolicitud.value = "";
               form.dtProgramada.value = "";
               return false;
            }else
               return true;
         }else
             return true; 
      }
   }


  function fValidaTodo(){
    form = document.forms[0];
    if (form.hdBoton.value == 'Borrar') {
       if(!confirm("¿Desea usted eliminar el registro del vehículo?"))
         return false;
    }
    var lGuarda = false;
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
       for (i = 0;i<form.elements.length;i++){
          if (form.elements[i].name.substring(0,5) == 'check' && form.elements[i].checked)
             lGuarda = true;
       }
       if (lGuarda){
          if(!confirm("¿Desea usted guardar la información correspondiente?"))
            return false;
       }
       else{
          alert('- Debe seleccionar un Vehículo para la generación de Mantenimientos!');
          return false;
       }

    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      var iAnio = 0;
      var iMes  = 0;
      var iDia  = 0;

      form.cDscTpoMantto.value = form.iCveTpoMantto.options[form.iCveTpoMantto.selectedIndex].text;

      if (form.dtSolicitud)
        cVMsg = cVMsg + fSinValor(form.dtSolicitud,5,'Fecha de Solicitud', true);
      if (form.dtProgramada)
        cVMsg = cVMsg + fSinValor(form.dtProgramada,5,'Fecha Programada', true);
      if (form.iCveTpoMantto && form.iCveTpoMantto.selectedIndex == 0)
        cVMsg = cVMsg + "\n - Debe seleccionar el Tipo de Mantenimiento";

      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }else{
         if (form.dtSolicitud && form.dtProgramada){
            iAnio = form.dtSolicitud.value.substring(6,10);
            iMes  = form.dtSolicitud.value.substring(3,5);
            iDia  = form.dtSolicitud.value.substring(0,2);
            var dtSolicitud = new Date(iAnio,iMes,iDia);

            iAnio = form.dtProgramada.value.substring(6,10);
            iMes  = form.dtProgramada.value.substring(3,5);
            iDia  = form.dtProgramada.value.substring(0,2);
            var dtProgramada = new Date(iAnio,iMes,iDia);
            if (dtSolicitud > dtProgramada){
               alert("La Fecha de Solicitud no debe ser mayor a la Fecha Programada!");
               form.dtSolicitud.value = "";
               form.dtProgramada.value = "";
               return false;
            }else
               return true;
         }else
             return true; 
      }
    }
     return true;
   }
