

  function fEnviarExcel(cPagina,iTipo,cTit,cCols,cSQL){
     if(confirm("¿ Está Seguro de Enviar a Excel ?")){
       forma                = document.forms[0];
       forma.hdBoton.value  = 'Enviar';
       forma.target         = 'FRMDatos';
       forma.action         = 'pg070403070.jsp';
       forma.submit();
     }
  }
