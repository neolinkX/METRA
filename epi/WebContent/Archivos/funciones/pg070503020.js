

  function fEnviarExcel(cPagina,iTipo,cTit,cCols,cSQL){

     if(confirm("¿ Está Seguro de Enviar a Excel ?")){
        fShowReport(cPagina,iTipo,cTit,cCols,cSQL)

     }
  }

  var wExp;
  function fShowReport(cPagina,iTipo,cTit,cCols,cSQL){
      frm = document.forms[0];
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
          wExp = open(cPagina+'?hdTit='+cTit+'&hdCols='+cCols+'&hdQuery='+cSQL+'&hdTipo='+iTipo,"Selector",'dependent=yes,hotKeys=no,location=no,menubar=yes,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=750,height=550,screenX=800,screenY=600');
          wExp.moveTo(50, 50);
          window.onclick=HandleFocus
          window.onfocus=HandleFocus
          fSetModal();
      }
  }



