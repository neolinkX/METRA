function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070302030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070302030P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }

  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir()
    if (form.hdBoton.value == 'Borrar') {
      if(!confirm("¿Está Seguro de Eliminar el Registro?"))
        return false;
    }
    if ((form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'PbaRapida' ) && form.hdBoton.value != 'Cancelar' ) {
    //alert("Si entro a la vlaidacion");
      cVMsg = '';
      if (form.iCvePbaRapida){
         cVMsg = cVMsg + fSinValor(form.iCvePbaRapida,3,'Cve. Pba. Rápida', true);
         //alert("fSin Valor:" + fSinValor(form.iCvePbaRapida,2,'Cve. Pba. Rápida', true));
      }
      if (form.iCvePersonal)
         cVMsg = cVMsg + fSinValor(form.iCvePersonal,3,'Persona', true);
      if (form.iCveLaboratorio && form.iCveLaboratorio.selectedIndex==0)
         cVMsg += "Seleccione el Laboratorio! ";
      if (cVMsg != ''){
         alert("Datos no Válidos: \n" + cVMsg);
         return false;
      }
    }
    return true;
  }


 function fEnvio(){
  form = document.forms[0];
  form.hdBoton.value = 'Enviar';
  form.target = "_self";
  form.submit();
}


 function fCambiaLab(cValor){
  form = document.forms[0];
  form.hdBoton.value = cValor;
  form.target = "_self";
  form.submit();
}

 

 function fActualizaComboM(iOpc, forma, oComboO, oComboA, filtro1, filtro2, filtro3){
	  while (oComboA.length > 0) oComboA.options[0]=null;
     oComboA.options[0] = new Option("Cargando Datos...","-1");
	  // Submitir al Panel de Llenado
     window.parent.FRMOtroPanel.location="pg07ComboMensajeria.jsp?"+
                                         "iOpc="      + iOpc         + // Opción de llenado
                                         "&cCombo="   + oComboA.name + // Nombre del combo a llenar
               	                      "&hdAccion=" + document.forms[0].action + // Action
                                         "&iFiltro1=" + filtro1 +  // iCveUniMed
										  "&iFiltro2=" + filtro2 +  // iCveModulo
                                         "&iFiltro3=" + filtro3 ;  // iCveProceso

 }

