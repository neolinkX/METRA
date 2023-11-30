  function fValidaTodo(){
    var form = document.forms[0];
      if (window.parent.parent.parent.FRMCuerpo.FRMDatos){
        if (window.parent.parent.parent.FRMCuerpo.FRMDatos.fValidaBuscar)
          if (!window.parent.parent.parent.FRMCuerpo.FRMDatos.fValidaBuscar())
            return false;
      }
      if ((form.SLSOperador.selectedIndex != 0) && (form.FILBuscar.value == '')){
        alert('No se ingresó el valor del criterio de búsqueda.');
        form.FILBuscar.focus();
        return false;
      }
      if ((form.SLSOperador.selectedIndex == 0) && (form.FILBuscar.value != '')){
        alert('No se seleccionó un criterio de búsqueda.');
        return false;
      }
      return fValTipoDato();
  }

  function fOculto(cValor){
    if(document.forms[0]){
      form = document.forms[0];
      for(i = 0; i < form.elements.length; i++){
        form.elements[i].disabled = cValor;
      }
    }
  }

  function fSubmite(){
    form = document.forms[0];
    if(fValidaTodo() && fNumReg()){
       vFiltrar = fFiltraOrdena();
       vFRM = window.parent.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];
       vFRM.hdBoton.value='Primero';
       if(vFRM.FILNumReng)
          vFRM.FILNumReng.value=form.FILNumReng.value;
       if(vFRM.hdLCondicion)
          vFRM.hdLCondicion.value=vFiltrar;
       if(vFRM.hdLOrdenar)
          vFRM.hdLOrdenar.value=form.hdOrdenar.value;
       if(vFRM.hdCCondicion)
          vFRM.hdCCondicion.value=vFiltrar;
       if(vFRM.hdCOrdenar)
          vFRM.hdCOrdenar.value=form.hdOrdenar.value;
       vFRM.target='FRMDatos';
       vFRM.submit();
    }
  }

  function fSubmiteInt(){
    form = document.forms[0];
    if(fValidaTodo() && fNumReg()){
       vFiltrar = fFiltraOrdena();
       vFRM = window.parent.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];
//       vFRM.hdBoton.value='Primero';
       if(vFRM.FILNumReng)
          vFRM.FILNumReng.value=form.FILNumReng.value;
       if(vFRM.hdLCondicion)
          vFRM.hdLCondicion.value=vFiltrar;
       if(vFRM.hdLOrdenar)
          vFRM.hdLOrdenar.value=form.hdOrdenar.value;
       if(vFRM.hdCCondicion)
          vFRM.hdCCondicion.value=vFiltrar;
       if(vFRM.hdCOrdenar)
          vFRM.hdCOrdenar.value=form.hdOrdenar.value;
       vFRM.target='FRMDatos';
       vFRM.submit();
    }
  }

  function fFiltraOrdena(){
     var form = document.forms[0];
     var vFiltrar, cFilBuscar,j, cOperador;
     // Ordenar
     form.hdOrdenar.value = " order by " + form.SLSOrden.value;
     // filtro
     if(form.SLSOperador.value == 'Todos'){
       vFiltrar = '';
     }else{
        for(i=0; i < form.SLSFiltro.length; i++){
           if(form.SLSFiltro[i].value == form.SLSFiltro.value){
              j = fEntry(i+1,form.hdTipos.value,'|');
              break;
           }
        }
        cFilBuscar = form.FILBuscar.value;
        cOperador = form.SLSOperador.value;
        if(cOperador == 'like'){
           cFilBuscar = "'" + cFilBuscar + "%'";
        }else{
           if(j == 5 || j == 8 || j == 9)
              cFilBuscar = "'" + cFilBuscar + "'";
        }
        vFiltrar = form.SLSFiltro.value + ' ' + cOperador + ' ' + cFilBuscar;
     }
     return vFiltrar;
  }

  function fNumReg(){
    vFRM = window.parent.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];
    campo = document.forms[0].FILNumReng;
    lSubmite = true;

    if(vFRM.FILNumReng && form.hdNRworks.value == 'true'){
      if (campo.value == ''){
        alert('Debe proporcionar el número de renglones a desplegar');
        campo.value = vpNRT;
        lSubmite = false;
      }
      if (!fSoloNumeros(campo.value)){
        alert('El número de renglones es incorrecto');
        campo.value = vpNRT;
        lSubmite = false;
      }
      if (parseInt(campo.value, 10) < 1){
        alert('El número de renglones debe ser mayor o igual a 1');
        campo.value = vpNRT;
        lSubmite = false;
      }

      if (parseInt(campo.value, 10) > parseInt(vpNMR, 10)){
        alert('El número de renglones debe ser menor o igual a ' + vpNMR);
        campo.value = vpNRT;
        lSubmite = false;
      }
    }
    return lSubmite;
  }

  function fVerOperador(){
     form = document.forms[0];
     j = 0;
     if(form.hdOperador.value == 1 || form.hdOperador.value == 2){
       for(i=0; i < form.SLSFiltro.length; i++){
         if(form.SLSFiltro[i].value == form.SLSFiltro.value){
           j = fEntry(i+1,form.hdTipos.value,'|');
           break;
         }
       }
       if(j == 5 || j == 6 || j == 7){
         if(form.SLSOperador[form.SLSOperador.length - 1].text  == "Inicie")
           form.SLSOperador.length = form.SLSOperador.length - 1;
       }else{
         if(form.SLSOperador[form.SLSOperador.length - 1].value != "like"){
           form.SLSOperador.length = form.SLSOperador.length + 1;
           form.SLSOperador[form.SLSOperador.length - 1].value = "like";
           form.SLSOperador[form.SLSOperador.length - 1].text  = "Inicie";
         }
       }
     }
  }

  function fValTipoDato(){
    form = document.forms[0];
    form.hdTipos.value
    for(i=0; i < form.SLSFiltro.length; i++){
       if(form.SLSFiltro[i].value == form.SLSFiltro.value){
          j = fEntry(i+1,form.hdTipos.value,'|');
          return fValida(form, parseInt(j,10));
          break;
       }
    }
    return false;
  }

// 1. "Todos" 2."Todos = Inicie" 3."="
  function fOperador(cValor, cPagina){
    form = document.forms[0];
    form.hdNewAction.value = cPagina;
    if(form.hdNewAction.value != form.hdOldAction.value){
       form.FILBuscar.value = '';
       form.hdOperador.value = cValor;
       if(cValor == 1){
         form.SLSOperador.length = 8;
         form.SLSOperador[0].value = form.SLSOperador[0].text = "Todos";
         form.SLSOperador[1].value = form.SLSOperador[1].text = "<>";
         form.SLSOperador[2].value = form.SLSOperador[2].text = "=";
         form.SLSOperador[3].value = form.SLSOperador[3].text = "<";
         form.SLSOperador[4].value = form.SLSOperador[4].text ="<=";
         form.SLSOperador[5].value = form.SLSOperador[5].text =">";
         form.SLSOperador[6].value = form.SLSOperador[6].text =">=";
         form.SLSOperador[7].value ="like";
         form.SLSOperador[7].text ="Inicie";
         //form.SLSOperador[2].selected = true;
         form.SLSOperador[0].selected = true;
       }
       if(cValor == 2){
         form.SLSOperador.length = 3;
         form.SLSOperador[0].value = form.SLSOperador[0].text = "Todos";
         form.SLSOperador[1].value = form.SLSOperador[1].text = "=";
         form.SLSOperador[2].value ="like";
         form.SLSOperador[2].text ="Inicie";
         form.SLSOperador[1].selected = true;
       }
       if(cValor == 3){
         form.SLSOperador.length = 1;
         form.SLSOperador[0].value = form.SLSOperador[0].text = "=";
         form.SLSOperador[0].selected = true;
       }
    }
  }

  function fVerTodos(){
     form = document.forms[0];
     if(form.SLSOperador.value == "Todos"){
        form.FILBuscar.value = '';
     }
  }

  function fFiltro(cDscCampos,cCampos,cTipos){
    form = document.forms[0];
    if(form.hdNewAction.value != form.hdOldAction.value){
       form.hdTipos.value = cTipos;
       cNumCampos = fNumEntries(cCampos,"|");
       for(i=1; i <= cNumCampos; i++){
          form.SLSFiltro.length = cNumCampos;
          form.SLSFiltro[i-1].value = fEntry(i,cCampos,"|");
          form.SLSFiltro[i-1].text  = fEntry(i,cDscCampos,"|");
       }
       form.SLSFiltro[0].selected = true;
       fVerOperador();
    }
  }

  function fOrdenar(cDscCampos,cCampos){
    form = document.forms[0];
    if(form.hdNewAction.value != form.hdOldAction.value){
       cNumCampos = fNumEntries(cCampos,"|");
       form.SLSOrden.length = cNumCampos;
       for(i=1; i <= cNumCampos; i++){
          form.SLSOrden[i-1].value = fEntry(i,cCampos,"|");
          form.SLSOrden[i-1].text  = fEntry(i,cDscCampos,"|");
       }
       form.SLSOrden[0].selected = true;
       form.hdOldAction.value = form.hdNewAction.value;
    }
  }

  function fRegNum(lRegNum){
    form = document.forms[0];
    form.hdNRworks.value = lRegNum;
    if(lRegNum){
      if(form.FILNumReng.value == ''){
        form.FILNumReng.value = form.hdNumRengTab.value;
      }
    }else{
      form.FILNumReng.value = '';
    }
    form.FILNumReng.disabled = !lRegNum;
  }

  function fOnLoad(){
    form = document.forms[0];
    form.hdNumRengTab.value = vpNRT;
    form.FILNumReng.value = form.hdNumRengTab.value;
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }
