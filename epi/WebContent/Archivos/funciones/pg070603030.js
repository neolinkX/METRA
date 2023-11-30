  function fIrCatalogo(iCveEquipo, iCveMantenimiento,cPagina){
    form = document.forms[0];
    form.iCveEquipo.value = iCveEquipo;
    form.iCveMantenimiento.value = iCveMantenimiento;
    form.hdRowNum.value = iCveEquipo;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070603031.jsp';
    form.submit();
  }

  function fIrSeguimiento(iCveEquipo, iCveMantenimiento){
    form = document.forms[0];
    form.hdIni.value = iCveEquipo;
    form.hdIni2.value = iCveMantenimiento;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070603032.jsp';
    form.submit();
  }

  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070603031P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070603031P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion=" + document.forms[0].action;
    }
  }

   function Activar1(lActivar){
      form = document.forms[0];
      form.dtDeSol.disabled = lActivar;
      form.dtASol.disabled = lActivar;
   }

   function Activar2(lActivar){
      form = document.forms[0];
      form.dtDePro.disabled = lActivar;
      form.dtAPro.disabled = lActivar;
   }


   function fValidaBuscar(){
      cVMsg = '';
      if (form.lSolicitados && form.lSolicitados.checked){
         if (form.dtASol)
            cVMsg = cVMsg + fSinValor(form.dtASol,5,'Fecha de Inicio de la Solicitud', true);

         if (form.dtDeSol)
            cVMsg = cVMsg + fSinValor(form.dtDeSol,5,'Fecha de Fin de la Solicitud', true);
      }

      if (form.lProgramados && form.lProgramados.checked){
         if (form.dtAPro)
            cVMsg = cVMsg + fSinValor(form.dtAPro,5,'Fecha de Inicio de la Programación', true);

         if (form.dtDePro)
            cVMsg = cVMsg + fSinValor(form.dtDePro,5,'Fecha de Fin de la Programación', true);
      }


      if (form.iCveUniMed && form.iCveUniMed.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar la Unidad Médica';

      if (form.iCveModulo && form.iCveModulo.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar el Módulo';

      if (form.iCveArea && form.iCveArea.selectedIndex ==0)
         cVMsg = cVMsg + '\n - Debe seleccionar el Área';

      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
      else
         return true;
   }
