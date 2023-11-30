  var aGen = new Array();
  var iCount = 0;
  
  function fOnLoad(){
  }

  function fValidaTodo(){
    return true;
  }
  function fSubmite(objImg, cValor){
  }
  function cambiaSource(objImagen, cEstado){
  }
  function fMouseOver(objDoc, cImagen, cEstatus){
  }
  function fMouseOut(objDoc, cImagen){
  }
  function cambiaEstado(cEstado){
  }
  function fTitulo(cAccion){
    form = document.forms[0];
    if (form.hdAccion)
       form.hdAccion.value = cAccion;
    form.submit();
  }

  function fGen(cPagina, cBusca,cLlave){
    form = document.forms[0];
    if (form.hdBusca)
       form.hdBusca.value = cBusca; 
    if (form.hdLlave)
       form.hdLlave.value = cLlave;
    form.action = cPagina;
    form.submit();
  }

  function fSub(aVar,cBusca){
    if(window.parent.FRMDatos){
      farForm = window.parent.FRMDatos.fGetOculto(aVar,cBusca);
    }
  }

  function fa(iCve,cDsc){
    aGen[iCount]= [iCve,cDsc];
    iCount++;
  }

  function fPag(){
     cPagina = '<HTML><SCRIPT LANGUAGE="JavaScript" SRC="'+RutaFuncs+'toolbars.js"></SCRIPT>'+
          '<link rel="stylesheet" href="'+Style+'" TYPE="text/css">'+
          '<body topmargin="0" leftmargin="0" onLoad="fOnLoad();">'+
          '<form method="POST" action="pg0700007.jsp">'+
          '  <input type="hidden" name="hdEstado" value="">'+
          '  <input type="hidden" name="hdAccion" value="'+cAccion+'">'+
          '  <input type="hidden" name="hdTitulo" value="'+cTitulo+'">'+
          '  <input type="hidden" name="hdBusca" value="">'+
          '  <input type="hidden" name="hdLlave" value="">'+
          '  <table border="0" width="100%" height="100%" background="'+bkFondo+'">'+
          '    <tr>'+
          '      <td class="ETituloTPag" align="center" width="100%" valign="middle">'+cTitulo+'</td>'+
          '    </tr>'+
          '  </table>'+
          '</form>'+
          '</body></HTML>';
          document.write(cPagina);
  } 
  