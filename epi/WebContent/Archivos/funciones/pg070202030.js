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
    cModulo = fSelText(frm.iCveModulo);
    cUniMed = fSelText(frm.iCveUniMed);

    fShowReport('pg070202030R.jsp?iAnio='+frm.iAnio.value+'&iMeses='+frm.iMeses.value+'&cModulo='+cModulo+'&cMeses='+cMeses+'&cUniMed='+cUniMed+'&hdTipo=1'+'&iCveUniMed='+frm.iCveUniMed.value+'&iCveModulo='+frm.iCveModulo.value);
  }

  function fChg(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070202030.jsp';
    form.submit();
  }
