  var lOnLoad = false;
  function fOnSetLocal(){
    form = document.forms[0];
    if(form.iCvePaisOr){
      fSubOculto('pg070402013P.jsp','pais','');
      lOnLoad = true;
    }
  }

  function fValidaTodo(){
    form = document.forms[0];
    if (form.hdBoton.value == 'Borrar') {
       if(!confirm("¿Desea usted eliminar el registro del vehículo?"))
         return false;
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.cMatricula)
        cVMsg = cVMsg + fSinValor(form.cMatricula,2,'Matrícula', true);
      if (form.cPropietario)
        cVMsg = cVMsg + fSinValor(form.cPropietario,2,'Propietario', true);
      if (form.iCveEmpresa)
        cVMsg = cVMsg + fSinValor(form.iCveEmpresa,0,'Empresa', true);
      if (form.iCveServPrestado)
        cVMsg = cVMsg + fSinValor(form.iCveServPrestado,3,'Servicio', true);
      if (form.iCveEdoOr)
        cVMsg = cVMsg + fSinValor(form.iCveEdoOr,3,'Origen - Entidad Federativa', true);
      if (form.cOrigen)
        cVMsg = cVMsg + fSinValor(form.cOrigen,0,'Origen', true);
      if (form.iCveEdoDest)
        cVMsg = cVMsg + fSinValor(form.iCveEdoDest,3,'Destino - Entidad Federativa', false);
      if (form.cDestino)
        cVMsg = cVMsg + fSinValor(form.cDestino,0,'Destino', true);
      if (form.iPerFedInvolucra)
        cVMsg = cVMsg + fSinValor(form.iPerFedInvolucra,3,'Federales', true);
      if (form.iPerEdoInvolucra)
        cVMsg = cVMsg + fSinValor(form.iPerEdoInvolucra,3,'Estatales', true);
      if (form.iPerPartInvolucra)
        cVMsg = cVMsg + fSinValor(form.iPerPartInvolucra,3,'Particulares', true);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }

  function fEmpSelected(iCveEmpresa, cNombreRS, cApPaterno, cApMaterno, cRFC, cCURP, cTpoPersona){
     form = document.forms[0];
     form.iCveEmpresa.value = iCveEmpresa;
     form.cEmpresa.value = cTpoPersona + ' - ' + cRFC + ' - ' + cCURP + ' - ' + cNombreRS + ' ' + cApPaterno + ' ' + cApMaterno;
  }

  function fGetOculto(aVar,cBusca){
    form = document.forms[0];
    iCveSel = 0;
    if(lOnLoad){
      if(cBusca == 'pais' && form.iCvePaisOr){
        iCveSel = (form.hdiCvePaisOr) ? form.hdiCvePaisOr.value : 0;
        fFillSelect(form.iCvePaisOr,aVar,iCveSel);
        iCveSel = (form.hdiCvePaisDest) ? form.hdiCvePaisDest.value : 0;
        fFillSelect(form.iCvePaisDest,aVar,iCveSel);
        fSubOculto('pg070402013P.jsp','edoOr',form.iCvePaisOr.value)
      }
      if(cBusca == 'edoOr' && form.iCveEdoOr){
        iCveSel = (form.hdiCveEdoOr) ? form.hdiCveEdoOr.value : 0;
        fFillSelect(form.iCveEdoOr,aVar,iCveSel);
        fSubOculto('pg070402013P.jsp','edoDest',form.iCvePaisDest.value)
      }
      if(cBusca == 'edoDest' && form.iCveEdoDest){
        iCveSel = (form.hdiCveEdoDest) ? form.hdiCveEdoDest.value : 0;
        fFillSelect(form.iCveEdoDest,aVar,iCveSel);
        fSubOculto('pg070402013P.jsp','servpres','');
      }
      if(cBusca == 'servpres' && form.iCveServPrestado){
        iCveSel = (form.hdiCveServPrestado) ? form.hdiCveServPrestado.value : 0;
        fFillSelect(form.iCveServPrestado,aVar,iCveSel);
        lOnLoad = false;
      }
    }else{
      if(cBusca == 'edoOr' && form.iCveEdoOr){
        fFillSelect(form.iCveEdoOr,aVar);
      }
      if(cBusca == 'edoDest' && form.iCveEdoDest){
        fFillSelect(form.iCveEdoDest,aVar);
      }
    }
  }
