  var wExp;
  var theTable;
  var theTableBody;
  var newCell;
  var newRow;
  var ObjetoReco;

  function fSelReco(cObj){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       ObjetoReco = cObj;
       cPagina = '\n<HTML><title>Selector de Recon�sticos</title>';
       cPagina = cPagina + '\n' + cStyle;
       cPagina = cPagina + '\n<body bgcolor="" topmargin="0" leftmargin="0">';
       cPagina = cPagina + '\n<form method="POST" action="_self">&nbsp;';
       cPagina = cPagina + '\n<table width="100%" valign="top"><tr><td><table border="1" align="center" cellspacing="0" cellpadding="3" class="ETablaInfo">';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td colspan="2" class="ETablaT">Selector de Recomendaciones</td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Especialidad</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><select name="iCveEspecialidad2" size="1" onChange="javascript:if(!window.opener.closed)window.opener.fLoadRecoID();"></select></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">B�squeda R�pida por:</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><select name="lID" size="1" onChange="javascript:if(!window.opener.closed)window.opener.fLoadRecoID();"></select></td>';
       cPagina = cPagina + '\n    </tr>';
       cPagina = cPagina + '\n    <tr class="ETablaInfo">';
       cPagina = cPagina + '\n      <td class="EEtiqueta">Introduzca el inicio de la b�squeda:</td>';
       cPagina = cPagina + '\n      <td class="ECampo"><input type="text" name="cID" value="" size="30" onkeyup="javascript:if(!window.opener.closed)window.opener.fLoadRecoID();"></td>';
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
       this.wExp.document.write(cPagina);
       wExp.moveTo(50, 50);
       fLoadReco();
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
      }
  }

  function fLoadReco(){
    fInitReco();
    fFillEspecialidad2();
    fLoadRecoID();
  }

  function fInitReco() {
    theTable = (this.wExp.document.all) ? this.wExp.document.all.myTABLE :
    this.wExp.document.getElementById("myTABLE")
    theTableBody = theTable.tBodies[0]
  }

  function fDelTableReco(){
    for(i=0;theTableBody.rows.length;i++){
      theTableBody.deleteRow(0);
    }
  }

  function fFillEspecialidad2(){
    form=this.wExp.document.forms[0];
    iLength = aMEDEsp2.length;
    var meEsp;
    if(iLength > 0){
      form.iCveEspecialidad2.length = iLength;
      for(i=0;i < iLength;i++){
        meEsp = aMEDEsp2[i];
        form.iCveEspecialidad2[i].text = meEsp[1];
        form.iCveEspecialidad2[i].value = meEsp[0];
      }
      form.lID.length = 2;
      form.lID[0].text = 'ID';
      form.lID[0].value = '0';
      form.lID[1].text = 'Dsc. Breve';
      form.lID[1].value = '1';
    }
  }

  function fLoadRecoID(){

    form=this.wExp.document.forms[0];
    var aReco;
    cBus = form.cID.value.toUpperCase();
    cSubs = '';
    fDelTableReco();
    if(form.lID.value == 0){
      aReco = aMEDRecID;
      k=4;
    }else{
      aReco = aMEDRecDsc;
      k=2;
    }
    iLength = aReco.length;
    var meEsp;
    j = 1;
    if(iLength > 0){

      fInsRowReco(0);
      fInsCellReco(0,'ID','ETablaT');
      fInsCellReco(1,'Dsc. Breve','ETablaT');
      fInsCellReco(2,'Recomendaci�n','ETablaT',2);      

      for(i=0;i < iLength;i++){
        lIns = false;
        meEsp = aReco[i];
        if(meEsp[0] == form.iCveEspecialidad2.value){
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
            
            fInsRowReco(j);
            fInsCellReco(0,meEsp[4],'EEtiqueta');
            fInsCellReco(1,'<textarea cols="40" rows="'+iRows+'" name="cBre" readonly>'+meEsp[2]+'</textarea>','ECampo');
            fInsCellReco(2,'<textarea cols="40" rows="'+iRows+'" name="cObs" readonly>'+meEsp[3]+'</textarea>','ECampo');
            fInsCellReco(3,'<A HREF="javascript:if(!window.opener.closed)window.opener.fSetReco('+"'"+meEsp[4]+"'"+');else{window.close();}">Selecci�n...</A>');
            j++;
          }
        }
      }
    }
  }

  function fInsRowReco(iR){
     newRow = theTableBody.insertRow(iR);
     newRow.className = "ETablaInfo";
  }

  function fInsCellReco(iC, cVal, cClass, iCol){
      newCell = newRow.insertCell(iC);
      newCell.innerHTML = cVal;
      if(cClass)
        newCell.className = cClass;
      else
        newCell.align = "center";
      if(iCol)
        newCell.colSpan=iCol;
  }

  function fSetReco(cValor){
    if(ObjetoReco)
      ObjetoReco.value = cValor;

    if((wExp != null) && (!wExp.closed))
      wExp.close();
  }
