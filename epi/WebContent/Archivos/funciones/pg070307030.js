  var wExp;

  function fShowReport(cPagina,iRep,cTit,cCols,cSQL,iTipo){
      frm = document.forms[0];
   
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
          wExp = open(cPagina+'?hdRep='+iRep+'&hdTit='+cTit+'&hdCols='+cCols+'&hdQuery='+cSQL+'&hdTipo='+iTipo+'&iCveUniMed='+frm.iCveUniMed.value+'&cLote='+frm.cLote.value+'&dtFechaI='+frm.dtFechaI.value+'&dtFechaF='+frm.dtFechaF.value+'&lfecha='+ frm.lFecha.checked,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
      }
  }

 function fBuscar(){
    frm = document.forms[0];

    cDscUniMed = '';
    cDscLote = '';
    cMeses = '';
    for(i=0; i<frm.iCveUniMed.length; i++){
      if(frm.iCveUniMed[i].value == frm.iCveUniMed.value){
        cDscUniMed = frm.iCveUniMed[i].text;
        break;
      }
    }
    for(i=0; i<frm.cLote.length; i++){
      if(frm.cLote[i].value == frm.cLote.value){
        cDscLote = frm.cLote[i].text;
        break;
      }
    }


     if (form.dtFechaI && form.dtFechaF && form.lFecha.checked){
         iAnio = form.dtFechaI.value.substring(6,10);
         iMes  = form.dtFechaI.value.substring(3,5);
         iDia  = form.dtFechaI.value.substring(0,2);
         var dtInicio = new Date(iAnio,iMes,iDia);

         iAnio = form.dtFechaF.value.substring(6,10);
         iMes  = form.dtFechaF.value.substring(3,5);
         iDia  = form.dtFechaF.value.substring(0,2);
         var dtFin = new Date(iAnio,iMes,iDia);
         if (dtInicio > dtFin){
            alert("La Fecha de Inicio no debe ser mayor a la Fecha de Fin!");
            return false;
         }
      }


    //if(frm.iTipo.value == 1){
      if(confirm("El reporte tomará algunos minutos en crearse ¿Desea continuar?"))
        //function fShowReport(cPagina,iRep,cTit,cCols,cSQL,iTipo){
        fShowReport('pg070307030R.jsp',1,'COMPORTAMIENTO DE LOS CONTROLES DE CALIDAD ','','',1);
    //}
    return true;
  }

  function fChg(){
    form = document.forms[0];
    form.target = 'FRMDatos';
    form.action = 'pg070307030.jsp';
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
