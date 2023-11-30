
  function ActivarMod(iselect){
    var form = document.forms[0];

    if (iselect == 0){
       form.elements[14].disabled = true;
       form.elements[15].disabled = true;
       form.elements[16].disabled = true;
       form.elements[17].disabled = true;
       form.elements[19].disabled = false;
       form.elements[20].disabled = false;
       form.elements[21].disabled = false;
    }
    else 
       if (iselect == 1){
          form.elements[14].disabled = false;
          form.elements[15].disabled = false;
          form.elements[16].disabled = false;
          form.elements[17].disabled = false;
          form.elements[19].disabled = true;
          form.elements[20].disabled = true;
          form.elements[21].disabled = true;
       }


  }

  function Activar(iselect){
    var form = document.forms[0];
    if (iselect == 0){
       // Cuantitativo
       form.elements[13].disabled = true;
       form.elements[14].disabled = true;
       form.elements[15].disabled = true;
       form.elements[16].disabled = true;
	   form.elements[17].disabled = true;
	   form.elements[18].disabled = true;
	   form.elements[19].disabled = true;
       // Cualitativo
       form.elements[20].disabled = false;
       form.elements[21].disabled = false;
       form.elements[22].disabled = false;
    }
    else 
       if (iselect == 1){
          form.elements[13].disabled = false;
          form.elements[14].disabled = false;
          form.elements[15].disabled = false;
          form.elements[16].disabled = false;
		  form.elements[17].disabled = false;
		  form.elements[18].disabled = false;
          form.elements[19].disabled = false;

          form.elements[20].disabled = false;
          form.elements[21].disabled = false;

          form.elements[22].disabled = true;
		  form.elements[23].disabled = true;		  
       }
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070301221P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070301221P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fValidaTodo(){
    var form = document.forms[0];

    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
/*
    if (form.hdBoton.value == 'Borrar' || form.hdBoton.value == 'BorrarB') {
        alert("No es posible borrar los registros.")
        return false;
    }
*/

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCveSustancia)
        cVMsg = cVMsg + fSinValor(form.iCveSustancia,3,'Sustancia:', false);
      if (form.iCveCorte)
        cVMsg = cVMsg + fSinValor(form.iCveCorte,3,'Corte:', false);
      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'Activo:', true);
      if (form.dtInicio)
        cVMsg = cVMsg + fSinValor(form.dtInicio,5,'Inicio:', true); 
      if (form.dtFin)
        cVMsg = cVMsg + fSinValor(form.dtFin,5,'Fin:', true);
      if (form.iCveUsuAutoriza)
        cVMsg = cVMsg + fSinValor(form.iCveUsuAutoriza,3,'Usuario que Autoriza:', true);


       
     if ((form.hdBoton.value == 'Guardar') || (form.hdBoton.value == 'GuardarA')){


           if (form.dCorte1)
             cVMsg = cVMsg + fSinValor(form.dCorte1,4,'Corte:', true);
              if (form.dMargenError)
            cVMsg = cVMsg + fSinValor(form.dMargenError,4,'Margen de Error:', true);
         


     }


      if (form.dtInicio && form.dtFin){
         iAnio = form.dtInicio.value.substring(6,10);
         iMes  = form.dtInicio.value.substring(3,5);
         iDia  = form.dtInicio.value.substring(0,2);
         var dtInicio = new Date(iAnio,iMes,iDia);
          
         iAnio = form.dtFin.value.substring(6,10);
         iMes  = form.dtFin.value.substring(3,5);
         iDia  = form.dtFin.value.substring(0,2);
         var dtFin = new Date(iAnio,iMes,iDia);
         if (dtInicio > dtFin){
            cVMsg = cVMsg + "\n La Fecha de Inicio no debe ser mayor a la Fecha de Fin!";
         }
      }


      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }

      if ((form.hdBoton.value == 'Guardar') || (form.hdBoton.value == 'GuardarA')){
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }
         

    }
     return true;
   }