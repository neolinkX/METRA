  var wExp;

  function fShowReport(cPagina,iRep,cTit,cCols,cSQL,iTipo){
      frm = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
          wExp = open(cPagina+'?hdRep='+iRep+'&hdTit='+cTit+'&hdCols='+cCols+'&hdQuery='+cSQL+'&hdTipo='+iTipo+'&iCveUniMed='+frm.iCveUniMed.value+'&iCveModulo='+frm.iCveModulo.value,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
      }
  }

  function fBuscar(){
    frm = document.forms[0];

    cDscUniMed = '';
    cDscModulo = '';
    cMeses = '';
    for(i=0; i<frm.iCveUniMed.length; i++){
      if(frm.iCveUniMed[i].value == frm.iCveUniMed.value){
        cDscUniMed = frm.iCveUniMed[i].text;
        break;
      }
    }
    for(i=0; i<frm.iCveModulo.length; i++){
      if(frm.iCveModulo[i].value == frm.iCveModulo.value){
        cDscModulo = frm.iCveModulo[i].text;
        break;
      }
    }
    for(i=0; i<frm.iMeses.length; i++){
      if(frm.iMeses[i].value == frm.iMeses.value){
        cMeses = frm.iMeses[i].text;
        break;
      }
    }

    if(frm.iTipo.value == 1){
      if(confirm("El reporte tomará algunos minutos en crearse ¿Desea continuar?"))
        fShowReport('pg070202010R.jsp',1,cDscUniMed + ', ' + cDscModulo + ', EXÁMENES MÉDICOS EN OPERACIÓN DEL AÑO: ' + frm.iAnio.value,'',frm.iAnio.value,1);
    }
    if(frm.iTipo.value == 2){
      if(confirm("El reporte tomará algunos minutos en crearse ¿Desea continuar?"))
        fShowReport('pg070202010R.jsp',2,cDscUniMed + ', ' + cDscModulo + ', EXÁMENES MÉDICOS EN OPERACIÓN DEL MES: ' + cMeses,'',frm.iAnio.value + '|' + frm.iMeses.value,1);
    }
    if(frm.iTipo.value == 3){
      if(fValFecha(frm.dtFecha.value)){
        fShowReport('pg070202010R.jsp',3,cDscUniMed + ', ' + cDscModulo + ', EXÁMENES MÉDICOS EN OPERACIÓN DEL DÍA: ' + frm.dtFecha.value,'',frm.dtFecha.value,1);
      }
      else
        alert('La fecha contiene un dato no válido!');
    }
  }

  function fChg(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070202010.jsp';
    form.submit();
  }

  function fTipo(){
    frm = document.forms[0];
    frm.iMeses.selectedIndex = iMes - 1;
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
