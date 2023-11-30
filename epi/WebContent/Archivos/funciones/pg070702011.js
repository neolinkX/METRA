  function fIrCatalogo(iCveVehiculo,cPagina) {
    form = document.forms[0];
    form.hdBoton.value = 'Actual';
    form.hdCampoClave.value = iCveVehiculo;
    form.hdRowNum.value = iCveVehiculo;
    form.target = 'FRMDatos';
    form.action = cPagina;
    form.submit();
  }



function fOpcional(cVCadena){    

    if ( fRaros(cVCadena)      || fPuntuacion(cVCadena) || 

         fSignoGuion(cVCadena) || fArroba(cVCadena)     || 

         fParentesis(cVCadena) || fPunto(cVCadena)      || 

         fDiagonal(cVCadena)   || fComa(cVCadena))

        return false;  

    else 

        return true;

}

function fOpcional2(cVCadena){    

    if ( fRaros(cVCadena)      || fPuntuacion(cVCadena) || 

         fSignoGuion(cVCadena) || fArroba(cVCadena)     || 

         fParentesis(cVCadena) || fPunto(cVCadena)      || 

         fComa(cVCadena))

        return false;  

    else 

        return true;

}

function fOpcional3(cVCadena){    

    if ( fRaros(cVCadena)      || fPuntuacion(cVCadena) || 

         fSignos(cVCadena) || fArroba(cVCadena)     || 

         fParentesis(cVCadena) ||  

         fDiagonal(cVCadena)   || fComa(cVCadena))

        return false;  

    else 

        return true;

}

function fSignoGuion(cVCadena){

   if (fEncCaract(cVCadena,"+"))

       return true;

   else

       return false;                                                                

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
      if (form.iCveTpoVehiculo)
        if (form.iCveTpoVehiculo.selectedIndex ==0)   
          cVMsg = cVMsg + "\n - Debe seleccionar el Tipo de Vehiculo";

      if (form.iCveMarca)
        if (form.iCveMarca.selectedIndex ==0)   
          cVMsg = cVMsg + "\n - Debe seleccionar la Marca";

      if (form.iCveModelo)
        if (form.iCveModelo.selectedIndex ==0)   
          cVMsg = cVMsg + "\n - Debe seleccionar el Modelo";

      if (form.cNumSerie)
         if(!fOpcional2(form.cNumSerie.value))
        cVMsg = cVMsg + "\n - El campo No. Serie solo permite valores alfanuméricos, guión y diagonal.";

      if (form.cNumMotor)
         if(!fOpcional2(form.cNumMotor.value))
        cVMsg = cVMsg + "\n - El campo No. Motor solo permite valores alfanuméricos, guión y diagonal.";


      if (form.cInventario)
         if(!fOpcional(form.cInventario.value))
        cVMsg = cVMsg + "\n - El campo Inventario solo permite valores alfanuméricos y el guion.";

      if (form.iCveColor)
        if (form.iCveColor.selectedIndex ==0)   
          cVMsg = cVMsg + "\n - Debe seleccionar el Color";

      if (form.cPlacas)
         if(!fOpcional3(form.cPlacas.value))
        cVMsg = cVMsg + "\n - El campo Placas solo permite valores alfanuméricos y el punto.";

      if (form.iKmMantto)
        cVMsg = cVMsg + fSinValor(form.iKmMantto,3,'Km. Mantenimiento:', true);

      if (form.cPoliza)
        cVMsg = cVMsg + fSinValor(form.cPoliza,2,'Poliza:', true);

      if (form.dtInicioVig)
        cVMsg = cVMsg + fSinValor(form.dtInicioVig,5,'Inicio Vig:', true);

      if (form.dtFinVig)
        cVMsg = cVMsg + fSinValor(form.dtFinVig,5,'Fin Vig:', true);

      if (form.dtAlta)
        cVMsg = cVMsg + fSinValor(form.dtAlta,5,'Fecha de Alta:', true);

      if (form.iCveEstadoVEH)
        if (form.iCveEstadoVEH.selectedIndex ==0)   
          cVMsg = cVMsg + "\n - Debe seleccionar el Estado del Vehículo";

      if (form.lBaja && form.lBaja.checked){
         if (form.dtBaja)
           cVMsg = cVMsg + fSinValor(form.dtBaja,5,'Fecha de Baja:', true);
         if (form.iCveMtvoBaja)
            if (form.iCveMtvoBaja.selectedIndex ==0)   
               cVMsg = cVMsg + "\n - Debe seleccionar el Motivo de Baja";
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
           window.parent.FRMOtroPanel.location="pg070702011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070702011P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
    }
  }
