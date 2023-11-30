  var theTable;
  var theTableBody;
  var fPrueba  = new Array();
  var fPrueba2 = new Array();
  var fTabla = new Array();
  var iPos = 0;

  function fCSS(){
    cCSS = "<style>";
    cCSS = cCSS + "A:hover{";
    cCSS = cCSS + "COLOR:#CCCCCC;";
    cCSS = cCSS + "font-family: Verdana ;";
    cCSS = cCSS + "font-size: 8pt ;";
    cCSS = cCSS + "font-weight: bold;";
    cCSS = cCSS + "TEXT-DECORATION:none";
    cCSS = cCSS + "}";
    cCSS = cCSS + "A.include{";
    cCSS = cCSS + "COLOR:#FFFFFF;";
    cCSS = cCSS + "font-family: Verdana ;";
    cCSS = cCSS + "font-size: 8pt ;";
    cCSS = cCSS + "font-weight: bold;";
    cCSS = cCSS + "TEXT-DECORATION:none";
    cCSS = cCSS + "}";
    cCSS = cCSS + "TD.menu {";
    cCSS = cCSS + "BACKGROUND-COLOR:";
    cCSS = cCSS + "}";
    cCSS = cCSS + "  A:Link  {";
    cCSS = cCSS + "    COLOR:#FFFFFF;";
    cCSS = cCSS + "    font-family: Verdana ;";
    cCSS = cCSS + "    font-size: 8pt ;";
    cCSS = cCSS + "    font-weight: bold;";
    cCSS = cCSS + "    TEXT-DECORATION:none";
    cCSS = cCSS + "  }";
    cCSS = cCSS + "  A:Visited  {";
    cCSS = cCSS + "    COLOR:#FFFFFF;";
    cCSS = cCSS + "    font-family: Verdana ;";
    cCSS = cCSS + "    font-size: 8pt ;";
    cCSS = cCSS + "    font-weight: bold;";
    cCSS = cCSS + "    TEXT-DECORATION:none";
    cCSS = cCSS + "  }";
    cCSS = cCSS + "</style>";
    return cCSS;
  }

  function fPag(){
     cPagina = '</html><SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'valida_nt.js"></SCRIPT>'+
               '<SCRIPT language="JavaScript" src="'+cRutaFuncs+'t07_Tooltips.js"></SCRIPT>'+
               '<SCRIPT LANGUAGE="JavaScript" SRC="'+cRutaFuncs+'buscar_nt.js"></SCRIPT>'+
               '<link rel="stylesheet" href="'+Style+'" TYPE="text/css">'+
               fCSS() +
               '<body topmargin="0" leftmargin="0" onLoad="fOnLoad();">'+
               '<form method="POST" action="pg0700003.jsp">'+
               '  <table class="ETablaST" border="0" width="100%" height="100%" cellspacing="2" cellpadding="0" background="'+bkFondo+'menufondo.gif">'+
               '    <tr height="5%">'+
               '      <td class="EEtiquetaC" valign="middle" background="'+bkFondo+'menufondo.gif">'+
               '         <img SRC="'+bkFondo+'menu.gif">'+
               '      </td>'+
               '    </tr>'+
               '    <tr>'+
               '      <td valign="top">'+
               '        <table><tr><td>&nbsp;</td></tr></table>'+
               '        <table ID="myTABLE" border="0" width="100%" cellspacing="0" cellpadding="0">'+
               '          <tr><td valign="top" width="100%" height="100%">&nbsp;</td></tr>'+
               '        </table>'+
               '      </td>'+
               '    </tr>'+
               '  </table>'+
               '</form>'+
               '</body>'+
               '</html>';
     document.write(cPagina);
  }

  function fOnLoad(){
    top.document.title = 'Menú del Sistema';
    init();
    fShowMenu(document.forms[0]);
  }

  function fM(iPadre,iOrden,cReferencia,cDscMenu,iNivMenu){
    cMouse = '';
    cImg = 'b0' + iOrden + '01.gif';
    if(iNivMenu == 0 || (iNivMenu == 1 && cReferencia == '#')){
      cDscImg = '<img name="b' + iOrden +'" border="0" src="' + cRutaImg + cImg + '">';
      cMouse =
      " onMouseOut=" + '"' + "fCambiaImagen(document, 'b" + iOrden + "','','" + cRutaImg + cImg + "',1);return true;"+ '"' +
      " onMouseOver=" + '"' + "fCambiaImagen(document, 'b" + iOrden + "','','" + cRutaImg + cImg.substring(0,cImg.length-5) + "2.gif',1);return true;"+ '"';
    }
    else{
      if(cReferencia == '#')
        cDscImg = '<img name="menu" border="0" src="' + cRutaImg + 'bFlecha02.gif">&nbsp;' + cDscMenu;
      else
        cDscImg = cDscMenu;
    }
    fPrueba2 = [iPadre,'<A HREF="javascript:fCambio(' + "'" + cReferencia + "','" + iOrden + "','" + "','" + cDscMenu + "'" + ');"' + cMouse  + '>' + cDscImg + " </A>",0,iOrden,iNivMenu];
    fPrueba[iPos] = fPrueba2;
    iPos++;
  }

  function init() {
    theTable = (document.all) ? document.all.myTABLE :
    document.getElementById("myTABLE")
    theTableBody = theTable.tBodies[0]
  }

  function fValidaTodo(){
    return true;
  }

  function fCargarPag(cValor, cTitulo){
    if((window.opener) && (!window.opener.closed) && (window.opener.fCargarPagOpener)){
      window.opener.fCargarPagOpener(cValor, cTitulo);
    }else{
      alert("El Sistema ya no se encuentra disponible, el menú cerrará.");
      window.close();
    }
  }

  function fCambio(cReferencia, iOrden, cBlanco, cDscMenu){
    form = document.forms[0];
    if((window.opener) && (!window.opener.closed)){
      if(cReferencia == '#'){
        for(i=0; i<fPrueba.length; i++){
          fVarAux2 = fPrueba[i];
          if(fVarAux2[0] == iOrden)
            fSelecciona(i, iOrden);
        }
        fShowTable(form);
      }else{
        fCargarPag(cReferencia, cDscMenu);
      }
    }else{
      alert("El Sistema ya no se encuentra disponible, el menú cerrará.");
      window.close();
    }
  }


  function fShowMenu(form){
    for(i=0; i<fPrueba.length; i++){
      fSelecciona(i,0);
    }
    fShowTable(form);
  }

  function fSelecciona(i, iNivel){
    var fVarAux2 = fPrueba[i];
    if (fVarAux2[0] == iNivel){
      if(fVarAux2[2] == 0)
        fVarAux2[2] = 1;
      else{
        fVarAux2[2] = 0;
        fDesSelChild(0,fVarAux2[3]);
      }
    }
    fPrueba[i] = fVarAux2;
  }

  function fDesSelChild(j,iNivel){
    var fVarAux2;
    for(var i=j;i<fPrueba.length;i++){
      fVarAux2 = fPrueba[i];
      if (fVarAux2[0] == iNivel){
         fVarAux2[2] = 0;
         fPrueba[i] = fVarAux2;
         i = fDesSelChild(i,fVarAux2[3]);
      }
    }
    return j;
  }

  function fShowTable(form) {
    var newCell;
    var newRow;
    for(i=0;theTableBody.rows.length;i++){
      theTableBody.deleteRow(0);
    }
    j = 0;
    for(i=0; i<fPrueba.length; i++){
      fVarAux2 = fPrueba[i];
      if(fVarAux2[2] == 1){
        newRow = theTableBody.insertRow(j);
        newCell = newRow.insertCell(0);
        cMenu = fVarAux2[1];
        for(k=0;k < fVarAux2[4]; k++){
          cMenu = "&nbsp;&nbsp;&nbsp;&nbsp;" + cMenu;
        }
        newCell.innerHTML = cMenu;
        j++;
      }
    }
    fAsignaImg(cRutaImgServer,cRutaImgLocal);
  }

  function getIEVersion(){
    var ua = navigator.userAgent
    var IEOffset = ua.indexOf('MSIE ')
    return parseFloat(ua.substring(IEOffset + 5, ua.indexOf(';', IEOffset)))
  }

  function fVerLocalImages(cLocal) {
    try{
      fsObj = new ActiveXObject("Scripting.FileSystemObject")
      return fsObj.FolderExists(cLocal);
    }catch(e){
      return false;
    }
  }

  function fAsignaImg(cValor,cLocal) {
    for(l=0;l<document.images.length;l++){
       img = document.images[l];
       cImg = img.src+'/';
       cImg = fEntry(fNumEntries(cImg,"/"),cImg,"/");
       if(navigator.appName == 'Microsoft Internet Explorer' && parseInt(getIEVersion()) > 4 && fVerLocalImages(cLocal)){
          img.src = cLocal + cImg;
       }else{
          img.src = cValor + cImg;
       }
    }
  }

  function fVerRutaImg(cValor,cLocal) {
    cSuccess = cValor;
    if(navigator.appName == 'Microsoft Internet Explorer' && parseInt(getIEVersion()) > 4 && fVerLocalImages(cLocal)){
      cSuccess = cLocal;
    }else{
      cSuccess = cValor;
    }
    return cSuccess;
  }

  function fEncuentraObjeto(n, d) {
    var p,i,x;
    if(!d)
      d=vDocument;
    if((p=n.indexOf("?"))>0&&parent.frames.length) {
      d=parent.frames[n.substring(p+1)].document;
      n=n.substring(0,p);
    }
    if(!(x=d[n])&&d.all)
      x=d.all[n];
    if (d.forms){
      for (i=0;!x&&i<d.forms.length;i++)
        x=d.forms[i][n];
    }
    for(i=0;!x&&d.layers&&i<d.layers.length;i++)
      x=fEncuentraObjeto(n,d.layers[i].document);
    return x;
  }

  function fCambiaImagen() {
    var l,z=0,x,a=fCambiaImagen.arguments;
    var doc = a[0];
    vDocument = doc;
    doc.MM_sr=new Array;
    for(l=1;l<(a.length-2);l+=3)
    if ((x=fEncuentraObjeto(a[l]))!=null){
        doc.MM_sr[z++]=x;
        if(!x.oSrc)
          x.oSrc=x.src;
        cImg = a[l+2]+'/';
        cImg = fEntry(fNumEntries(cImg,"/"),cImg,"/");
        x.src=fVerRutaImg(cRutaImgServer,cRutaImgLocal) + cImg;
    }
  }
 
