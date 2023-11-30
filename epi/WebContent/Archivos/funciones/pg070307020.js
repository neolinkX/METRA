  var wExp;

  function fShowReport(cPagina,iRep,cTit,cCols,cSQL,iTipo){
      frm = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
          wExp = open(cPagina+'?hdRep='+iRep+'&hdTit='+cTit+'&hdCols='+cCols+'&hdQuery='+cSQL+'&hdTipo='+iTipo,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
      }
  }

  function fBuscar(){
    frm = document.forms[0];
    iAnio = '';
    cDscModulo = '';
    cMeses = '';

    for(i=0; i<frm.iMeses.length; i++){
      if(frm.iMeses[i].value == frm.iMeses.value){
        cMeses = frm.iMeses[i].text;
        break;
      }
    }


    if(frm.iTipo.value == 2){
      if(confirm("El reporte tomará algunos minutos en crearse ¿Desea continuar?"))
        fShowReport('pg070307020R.jsp',2,'TEMPERATURA DE LOS REFRIGERADORES','',frm.iAnio.value + '|' + frm.iMeses.value + '|' + frm.iCveArea.value,1);
//      fShowReport('pg070307020R.jsp',2, , 'hdQuery= + frm.iAnio.value + '|' + frm.iMeses.value,1);
    }

  }

  function fChg(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070106020.jsp';
    form.submit();
  }

  function fTipo(){
    frm = document.forms[0];
    frm.iAnio.disabled = false;
    frm.iMeses.disabled = false;
    frm.dtFecha.disabled = false;

    if(frm.iTipo.value == 1){
      frm.iMeses.disabled = true;
      frm.dtFecha.disabled = true;
    }
    if(frm.iTipo.value == 2){
      frm.dtFecha.disabled = true;
    }
    if(frm.iTipo.value == 3){
      frm.iAnio.disabled = true;
      frm.iMeses.disabled = true;
    }
  }
