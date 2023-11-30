
 function fBusca(){
  form = document.forms[0];
  cTexto = "";

     if (form.iCveUniMed.value == "-1"){
        cTexto += "\n- Seleccione una Unidad Médica Valida \n";
     }

     if (form.iCveMdoTrans.value == "0"){
        cTexto += "\n- Seleccione un Medio de Transporte Valido";
     }

     if (form.dtAsigna)
        cTexto = cTexto + fSinValor(form.dtAsigna,5,'Fecha de Asignación:', true);

     if (cTexto != "")
       alert(cTexto);

     form.hdBoton.value = 'Buscar';
     form.target = "_self";
     form.submit();

}

function fInvestigacion(iIDDGPMPT,iIDMdoTrans,dtAccidente,cDscBreve,iCveMotivo){
       form = document.forms[0];
       form.target =  "FRMDatos";
       form.iIDDGPMPT.value=iIDDGPMPT;
       form.iIDMdoTrans.value=iIDMdoTrans;
       form.dtAccidente.value=dtAccidente;
       form.cDscBreve.value=cDscBreve;
       form.iCveMotivo.value=iCveMotivo;
       form.cDscMdoTrans.value = form.iCveMdoTrans.options[form.iCveMdoTrans.selectedIndex].text;
       form.action = "pg070402031.jsp";
       form.submit();
}

function fRecomendacion(iIDDGPMPT,iIDMdoTrans,dtAccidente,cDscBreve,iCveMotivo){
       form = document.forms[0];
       form.target =  "FRMDatos";
       form.iIDDGPMPT.value=iIDDGPMPT;
       form.iIDMdoTrans.value=iIDMdoTrans;
       form.dtAccidente.value=dtAccidente;
       form.cDscBreve.value=cDscBreve;
       form.iCveMotivo.value=iCveMotivo;
       form.cDscMdoTrans.value = form.iCveMdoTrans.options[form.iCveMdoTrans.selectedIndex].text;
       form.action = "pg070402032.jsp";
       form.submit();
}
