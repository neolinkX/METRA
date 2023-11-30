  var wExp;

  function fShowReport(cPagina){
      frm = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
          wExp = open(cPagina,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
      }
  }

  function fSelText(oSel){
    for(i=0; i<oSel.length; i++){
      if(oSel[i].value == oSel.value){
        return oSel[i].text;
      }
    }
    return '';
  }

  function fBuscar1(){
    frm = document.forms[0];
    cMeses = fSelText(frm.iMeses);
    cEmpresa = fSelText(frm.iCveEmpresa);
    cModulo = fSelText(frm.iCveModulo);
    cUniMed = fSelText(frm.iCveUniMed);

    fShowReport('pg070202020R.jsp?iAnio='+frm.iAnio.value+'&iMeses='+frm.iMeses.value+'&cEmpresa='+cEmpresa+'&cModulo='+cModulo+'&cMeses='+cMeses+'&iCveEmpresa='+frm.iCveEmpresa.value+'&cUniMed='+cUniMed+'&hdTipo=1'+'&iCveUniMed='+frm.iCveUniMed.value+'&iCveModulo='+frm.iCveModulo.value);
  }

  function fBuscar2(lTodas){
    frm = document.forms[0];
    cMeses = fSelText(frm.iMeses);
    cEmpresa = fSelText(frm.iCveEmpresa);
    cModulo = fSelText(frm.iCveModulo);
    cUniMed = fSelText(frm.iCveUniMed);

    if(lTodas)
      cEmpresa = "*";

    fShowReport('pg070202021R.jsp?iAnio='+frm.iAnio.value+'&iMeses='+frm.iMeses.value+'&cEmpresa='+cEmpresa+'&cModulo='+cModulo+'&cMeses='+cMeses+'&iCveEmpresa='+frm.iCveEmpresa.value+'&cUniMed='+cUniMed+'&hdTipo=1'+'&iCveUniMed='+frm.iCveUniMed.value+'&iCveModulo='+frm.iCveModulo.value);
  }


  function fChg(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070202020.jsp';
    form.submit();
  }

  function fTipo(){
    frm = document.forms[0];
    frm.iMeses.selectedIndex = iMes - 1;
    frm.iAnio.disabled = false;
    frm.iMeses.disabled = false;
    if (frm.dtFecha)
    frm.dtFecha.disabled = false;

     if (frm.iTipo){

    if(frm.iTipo.value == 1){
      frm.iMeses.disabled = true;
      if (frm.dtFecha)
      frm.dtFecha.disabled = true;
    }

    if(frm.iTipo.value == 2){
      frm.dtFecha.disabled = true;
    }

    if(frm.iTipo.value == 3){
      frm.iAnio.disabled = true;
      if (frm.dtFecha)
      frm.iMeses.disabled = true;
    }
  }

  }
