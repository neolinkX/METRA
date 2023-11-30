  var wExp;
  var theTable;
  var theTableBody;
  var newCell;
  var newRow;
  var ObjetoDiag;

  function fSelDiag(cObj){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       ObjetoDiag = cObj;
       cPagina = '\n<HTML><title>Selector de Diagnósticos</title>';
       cPagina = cPagina + '\n' + cStyle;
       cPagina = cPagina + '\n<body bgcolor="" topmargin="0" leftmargin="0">';
       cPagina = cPagina + '\n<form method="POST" action="_self">&nbsp;';
       cPagina = cPagina + '\n<table width="100%" valign="top"><tr><td><table border="1" align="center" cellspacing="0" cellpadding="3" class="ETablaInfo">';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td colspan="2" class="ETablaT">Selector de Diagnósticos</td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Especialidad</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><select name="iCveEspecialidad" size="1" onChange="javascript:if(!window.opener.closed)window.opener.fLoadDiagCIE();"></select></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Búsqueda Rápida por:</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><select name="lCIE" size="1" onChange="javascript:if(!window.opener.closed)window.opener.fLoadDiagCIE();"></select></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Introduzca el inicio de la búsqueda:</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><input type="text" name="cCIE" value="" size="30" onkeyup="javascript:if(!window.opener.closed)window.opener.fLoadDiagCIE();"></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    </table></td></tr><tr><td>&nbsp;</td></tr><tr><td>';
       cPagina = cPagina + '\n       <table ID="myTABLE" border="1" align="center" cellspacing="0" cellpadding="3" class="ETablaInfo">';
       cPagina = cPagina + '\n           <tr><td>&nbsp;</td></tr>';
       cPagina = cPagina + '\n       </table>';
       cPagina = cPagina + '\n </td></tr></table>';
       cPagina = cPagina + '\n</form>';
       cPagina = cPagina + '\n</body>';
       cPagina = cPagina + '\n</html>';
       wExp = window.open("", "Selector","width=750,height=350,status=no,resizable=no,top=200,left=200,scrollbars=yes");
       wExp.opener = window;
       wExp.gBGColor="white";
       wExp.gLinkColor="black";
       wExp.gTextColor="black";
       this.wExp.document.write(cPagina);
       wExp.moveTo(50, 50);
       fLoadDiag();
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }
  
  

  function fSelDiagCif(cObj){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       ObjetoDiag = cObj;
       cPagina = '\n<HTML><title>Selector de Diagnósticos CIF</title>';
       cPagina = cPagina + '\n' + cStyle;
       cPagina = cPagina + '\n<body bgcolor="" topmargin="0" leftmargin="0">';
       cPagina = cPagina + '\n<form method="POST" action="_self">&nbsp;';
       cPagina = cPagina + '\n<table width="100%" valign="top"><tr><td><table border="1" align="center" cellspacing="0" cellpadding="3" class="ETablaInfo">';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td colspan="2" class="ETablaT">Selector de Diagnósticos CIF</td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Rama</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><select name="iCveEspecialidad3" size="1" onChange="javascript:if(!window.opener.closed)window.opener.fLoadDiagCIF();"></select></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Búsqueda Rápida por:</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><select name="lCIF" size="1" onChange="javascript:if(!window.opener.closed)window.opener.fLoadDiagCIF();"></select></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Introduzca el inicio de la búsqueda:</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><input type="text" name="cCIF" value="" size="30" onkeyup="javascript:if(!window.opener.closed)window.opener.fLoadDiagCIF();"></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    </table></td></tr><tr><td>&nbsp;</td></tr><tr><td>';
       cPagina = cPagina + '\n       <table ID="myTABLE" border="1" align="center" cellspacing="0" cellpadding="3" class="ETablaInfo">';
       cPagina = cPagina + '\n           <tr><td>&nbsp;</td></tr>';
       cPagina = cPagina + '\n       </table>';
       cPagina = cPagina + '\n </td></tr></table>';
       cPagina = cPagina + '\n</form>';
       cPagina = cPagina + '\n</body>';
       cPagina = cPagina + '\n</html>';
       wExp = window.open("", "Selector","width=750,height=350,status=no,resizable=no,top=200,left=200,scrollbars=yes");
       wExp.opener = window;
       wExp.gBGColor="white";
       wExp.gLinkColor="black";
       wExp.gTextColor="black";
       this.wExp.document.write(cPagina);
       wExp.moveTo(50, 50);
       fLoadDiagCif();
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }  
  

  function fLoadDiag(){
    fInitDiag();
    fFillEspecialidad();
    fLoadDiagCIE();
  }
  
  function fLoadDiagCif(){
	    fInitDiagCif();
	    fFillEspecialidadCif();
	    fLoadDiagCIF();
	  }

  function fInitDiag() {
    theTable = (this.wExp.document.all) ? this.wExp.document.all.myTABLE :
    this.wExp.document.getElementById("myTABLE")
    theTableBody = theTable.tBodies[0]
  }
  
  function fInitDiagCif() {
	    theTable = (this.wExp.document.all) ? this.wExp.document.all.myTABLE :
	    this.wExp.document.getElementById("myTABLE")
	    theTableBody = theTable.tBodies[0]
	  }

  function fDelTableDiag(){
    for(i=0;theTableBody.rows.length;i++){
      theTableBody.deleteRow(0);
    }
  }

  function fFillEspecialidad(){
    form=this.wExp.document.forms[0];
    iLength = aMEDEsp.length;
    var meEsp;
    if(iLength > 0){
      form.iCveEspecialidad.length = iLength;
      for(i=0;i < iLength;i++){
        meEsp = aMEDEsp[i];
        form.iCveEspecialidad[i].text = meEsp[1];
        form.iCveEspecialidad[i].value = meEsp[0];
      }
      form.lCIE.length = 2;
      form.lCIE[0].text = 'CIE';
      form.lCIE[0].value = '0';
      form.lCIE[1].text = 'Dsc. Breve';
      form.lCIE[1].value = '1';
    }
  }
  

  function fFillEspecialidadCif(){
    form=this.wExp.document.forms[0];
    iLength = aMEDCifR.length;
    var meEsp;
    if(iLength > 0){
      form.iCveEspecialidad3.length = iLength;
      for(i=0;i < iLength;i++){
        meEsp = aMEDCifR[i];
        form.iCveEspecialidad3[i].text = meEsp[1];
        form.iCveEspecialidad3[i].value = meEsp[0];
      }
      form.lCIF.length = 2;
      form.lCIF[0].text = 'CIF';
      form.lCIF[0].value = '0';
      /*form.lCIF[1].text = 'Dsc. Breve';
      form.lCIF[1].value = '1';*/
    }
  }  

  function fLoadDiagCIE(){

    form=this.wExp.document.forms[0];
    var aDiag;
    cBus = form.cCIE.value.toUpperCase();
    cSubs = '';
    fDelTableDiag();
    if(form.lCIE.value == 0){
      aDiag = aMEDDiagCIE;
      k=4;
    }else{
      aDiag = aMEDDiagDsc;
      k=2;
    }
    iLength = aDiag.length;
    var meEsp;
    j = 1;
    if(iLength > 0){

      fInsRowDiag(0);
      fInsCellDiag(0,'CIE','ETablaT');
      fInsCellDiag(1,'Dsc. Breve','ETablaT');
      fInsCellDiag(2,'Observaciones','ETablaT',2);      

      for(i=0;i < iLength;i++){
        lIns = false;
        meEsp = aDiag[i];
        if(meEsp[0] == form.iCveEspecialidad.value){
          if(cBus != ''){
            cSubs = meEsp[k];
            if(cSubs.substring(0,cBus.length).toUpperCase() == cBus)
              lIns = true;
          }else{
            lIns = true;
          }
          if(lIns){
            iRows = parseInt(meEsp[3].length/80);
            if(iRows<1) iRows = 1;
            if(iRows>3) iRows = 3;
            
            fInsRowDiag(j);
            fInsCellDiag(0,meEsp[4],'EEtiqueta');
            fInsCellDiag(1,'<textarea cols="40" rows="'+iRows+'" name="cBre" readonly>'+meEsp[2]+'</textarea>','ECampo');
            fInsCellDiag(2,'<textarea cols="40" rows="'+iRows+'" name="cObs" readonly>'+meEsp[3]+'</textarea>','ECampo');
            fInsCellDiag(3,'<A HREF="javascript:if(!window.opener.closed)window.opener.fSetDiag('+"'"+meEsp[4]+"'"+');else{window.close();}">Selección...</A>');
            j++;
          }
        }
      }
    }
  }


  function fLoadDiagCIF(){

    form=this.wExp.document.forms[0];
    var aDiag;
    cBus = form.cCIF.value.toUpperCase();
    cSubs = '';
    fDelTableDiag();
    if(form.lCIF.value == 0){
      aDiag = aMEDCifCIE;
      k=4;
    }else{
      aDiag = aMEDCifDsc;
      k=2;
    }
    iLength = aDiag.length;
    var meEsp;
    j = 1;
    if(iLength > 0){

      fInsRowDiag(0);
      fInsCellDiag(0,'CIF','ETablaT',2);
      //fInsCellDiag(1,'Dsc. Breve','ETablaT');
      //fInsCellDiag(2,'Observaciones','ETablaT',2);

      for(i=0;i < iLength;i++){
        lIns = false;
        meEsp = aDiag[i];
        if(meEsp[0] == form.iCveEspecialidad3.value){
          if(cBus != ''){
            cSubs = meEsp[k];
            if(cSubs.substring(0,cBus.length).toUpperCase() == cBus)
              lIns = true;
          }else{
            lIns = true;
          }
          if(lIns){
            iRows = parseInt(meEsp[3].length/80);
            if(iRows<1) iRows = 1;
            if(iRows>3) iRows = 3;
            
            fInsRowDiag(j);
            fInsCellDiag(0,meEsp[4],'EEtiqueta');
            //fInsCellDiag(1,'<textarea cols="40" rows="'+iRows+'" name="cBre" readonly>'+meEsp[2]+'</textarea>','ECampo');
            //fInsCellDiag(2,'<textarea cols="40" rows="'+iRows+'" name="cObs" readonly>'+meEsp[3]+'</textarea>','ECampo');
            fInsCellDiag(1,'<A HREF="javascript:if(!window.opener.closed)window.opener.fSetCif('+"'"+meEsp[4]+"'"+');else{window.close();}">Selección...</A>');
            j++;
          }
        }
      }
    }
  }  
  
  
  function fInsRowDiag(iR){
     newRow = theTableBody.insertRow(iR);
     newRow.className="ETablaInfo";
  }

  function fInsCellDiag(iC, cVal, cClass, iCol){
      newCell = newRow.insertCell(iC);
      newCell.innerHTML = cVal;
      if(cClass)
        newCell.className = cClass;
      else
        newCell.align = "center";
      if(iCol)
        newCell.colSpan=iCol;
  }

  function fSetDiag(cValor){
    if(ObjetoDiag)
      ObjetoDiag.value = cValor;

    if((wExp != null) && (!wExp.closed))
      wExp.close();
  }
  
  function fSetCif(cValor){
	    if(ObjetoDiag)
	      ObjetoDiag.value = cValor;

	    if((wExp != null) && (!wExp.closed))
	      wExp.close();
	  }
