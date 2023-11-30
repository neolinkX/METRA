  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
  function fValidaTodo(){
    form = document.forms[0];
    if (form.hdBoton.value == 'Borrar') {
      return confirm("¿Desea usted eliminar el registro del accidente?");
    }else if (form.hdBoton.value != 'Cancelar') {
      if(form.lFinRegistro && form.lFinRegistro.checked && !confirm("El registro ya no podrá ser modificado ¿Desea continuar?")) return false;
      var cVMsg='';
      if(form.iIDMdoTrans      ) cVMsg += fSinValor(form.iIDMdoTrans      ,3,'Identificador Modo de Transporte',true);
      if(form.dtAccidente      ) cVMsg += fSinValor(form.dtAccidente      ,5,'Fecha del accidente',true);
      //if(form.cLugar           ) cVMsg += fSinValor(form.cLugar           ,0,'Lugar',true);
      //if(form.cDscBreve        ) cVMsg += fSinValor(form.cDscBreve        ,0,'Descripción Breve',true);
      //if(form.cDscAccidente    ) cVMsg += fSinValor(form.cDscAccidente    ,0,'Descripción',true);
      if(form.dtNotifica       ) cVMsg += fSinValor(form.dtNotifica       ,5,'Fecha de la Notificación',true);
      if(form.iVehFedInvolucra ) cVMsg += fSinValor(form.iVehFedInvolucra ,3,'Vehículos Federales',true);
      if(form.iVehPartInvolucra) cVMsg += fSinValor(form.iVehPartInvolucra,3,'Vehículos Particulares',true);
      if(form.iVehEdoInvolucra ) cVMsg += fSinValor(form.iVehEdoInvolucra ,3,'Vehículos Estatales',true);
      if(form.iPerFedInvolucra ) cVMsg += fSinValor(form.iPerFedInvolucra ,3,'Personal Federal',true);
      if(form.iPerPartInvolucra) cVMsg += fSinValor(form.iPerPartInvolucra,3,'Personal Particular',true);
      if(form.iPerEdoInvolucra ) cVMsg += fSinValor(form.iPerEdoInvolucra ,3,'Personal Estatal',true);
      if(form.hdBoton.value == 'Guardar'){
         if(form.iCveMotivo.value != 0){
            if(form.iCveMotivo    ) cVMsg += fSinValor(form.iCveMotivo       ,3,'Motivo del Accidente',true);
         }else{
            cVMsg = '\n - Debe Seleccionar un Motivo Valido';
         }
      }
      if (cVMsg != ''){
        alert("Datos no Válidos: \n" + cVMsg);
        return false;
      }
    }
     return true;
   }
  function fRecalSelects(cOpc){
    var form = document.forms[0];
    if(cOpc=='ESTADOS'){
      var iCvePais=form.iCvePais.value;
      fSubOculto('pg070402011P.jsp','ESTADOS',iCvePais);
    }else if(cOpc=='MUNICIPIOS'){
      var iCvePais=form.iCvePais.value;
      var iCveEstado=form.iCveEstado.value;
      fSubOculto('pg070402011P.jsp','MUNICIPIOS',iCvePais+'|'+iCveEstado);
    }else if(cOpc=='CAUSAS'){
      var iCveTpoCausa=form.iCveTpoCausa.value;
      fSubOculto('pg070402011P.jsp','CAUSAS',iCveTpoCausa);
    }
  }
  function fGetOculto(aVar,cOpc){
    var form = document.forms[0];
    if(cOpc=='ESTADOS' && form.iCveEstado){
      fFillSelect(form.iCveEstado,aVar);
      fRecalSelects('MUNICIPIOS');
    }else if(cOpc=='MUNICIPIOS' && form.iCveMunicipio){
      fFillSelect(form.iCveMunicipio,aVar);
    }else if(cOpc=='CAUSAS' && form.iCveCausa){
      fFillSelect(form.iCveCausa,aVar);
    }
  }
  function fVerLength(atObj,size){
    if(atObj.value.length>size){
      alert("La Descripción ("+atObj.value.length+" caracteres) sobrepasa el tamaño máximo permitido ("+size+" caracteres).");
      atObj.focus();
      atObj.select();
    }else{
        atObj.form.cta.value=size-atObj.value.length;
    }
  }
  function fOnKeyDown(atObj,size){
    if(atObj.value.length<size){
      atObj.form.cta.value=size-atObj.value.length-1;
      return true;
    }
    return false;
  }
  function fIrCatalogo(page){
    var form=document.forms[0];


    form.hdiAnio.value=form.iAnio.value;
    form.hdiCveMdoTrans.value=form.iCveMdoTrans.value;
    form.hdiIDDGPMPT.value=form.iIDDGPMPT.value;
    form.hdiIdefMedPrev.value=form.iIDDGPMPT.value;
    form.hdBoton.value='Actual';
    form.target='FRMDatos';
    form.action='pg0704020'+page+'.jsp';
    form.submit();
  }