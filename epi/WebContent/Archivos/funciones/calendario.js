  var iAnio;
  var iMes;
  var iDia;
  var iRef;
  var iDiaUno;

  var theTable;
  var theTableBody;
  var newCell;
  var newRow;
  var aMeses, cMeses;
  var wExp;
  var Objeto;

  function fLoadCal(iD,iM, iA, cObj){
    if(!cObj.disabled){
      if((wExp != null) && (!wExp.closed))
        wExp.focus();
      else{
       cPagina = '\n<HTML><title>Calendario</title>';
       cPagina = cPagina + '\n<style>';
       cPagina = cPagina + '\n  A:Link  {';
       cPagina = cPagina + '\n    COLOR:#FFFFFF; TEXT-DECORATION:none';
       cPagina = cPagina + '\n  }';
       cPagina = cPagina + '\n  A:Visited  {';
       cPagina = cPagina + '\n    COLOR:#FFFFFF; TEXT-DECORATION:none';
       cPagina = cPagina + '\n  }';
       cPagina = cPagina + '\n</style>';
       cPagina = cPagina + '\n<body bgcolor="" topmargin="0" leftmargin="0" >';
       cPagina = cPagina + '\n<table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0">';
       cPagina = cPagina + '\n<tr><td valign="middle" width="100%" height="100%"><table ID="myTABLE" border="1" width="100%" cellspacing="0" cellpadding="1">';
       cPagina = cPagina + '\n<tr><td valign="middle" width="100%" height="100%">&nbsp;</td></tr></table></td></tr></table>';
       cPagina = cPagina + '\n</body>';
       cPagina = cPagina + '\n<HTML>';

       wExp = window.open("", "Calendario","width=230,height=240,status=no,resizable=no,top=200,left=200");
       wExp.opener = window;
       wExp.gBGColor="white";
       wExp.gLinkColor="white";
       wExp.gTextColor="white";
       this.wExp.document.write(cPagina);
       Objeto = cObj;
       wExp.moveTo(265, 175);
       fLoad(iD,iM, iA);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();

      }
    }else{
      alert('Este campo de tipo fecha no se encuentra disponible.');
    }
  }

  function fLoad(iD,iM, iA){
     iDia = iD;
     iRef = iD;
     iMes = iM-1;
     iAnio = iA;
     iDiaUno = fFirstDay(iAnio,iMes);
     init();
     fDelTable();
     fPanel();
     fGenCalendar();
  }

  function init() {
    theTable = (this.wExp.document.all) ? this.wExp.document.all.myTABLE :
    this.wExp.document.getElementById("myTABLE")
    theTableBody = theTable.tBodies[0]
    if((iAnio % 4) == 0)
      aMeses = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    else
      aMeses = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    cMeses = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
  }

  function fDelTable(){
    for(i=0;theTableBody.rows.length;i++){
      theTableBody.deleteRow(0);
    }
  }

  function fPanel(){
    cOp = '';
    for(i=0;i<cMeses.length;i++){
      cSel = '';
      if(i == iMes)
        cSel = 'SELECTED';
      cOp = cOp + '<option ' + cSel + ' value="' + i + '">' + cMeses[i] + '</option>';
    }

    fInsRow(0);
    fInsCell(0,2,'<A HREF="javascript:if(!window.opener.closed)window.opener.fCambio(1);else{window.close();}">|<<</A>',3);
    fInsCell(1,3,'<input type="text" name="FILAnio" value="' + iAnio + '" size="4" maxlength="4" onblur="if(!window.opener.closed)window.opener.fSLS();else{window.close();}" >',3);
    fInsCell(2,2,'<A HREF="javascript:if(!window.opener.closed)window.opener.fCambio(4);else{window.close();}">>>|</A>',3);
    fInsRow(1);
    fInsCell(0,2,'<A HREF="javascript:if(!window.opener.closed)window.opener.fCambio(2);else{window.close();}"><<</A>',4);
    fInsCell(1,3,'<select name="SLSMes"  onChange="if(!window.opener.closed)window.opener.fSLS();else{window.close();}" size="1">'+cOp+'</select>',4);
    fInsCell(2,2,'<A HREF="javascript:if(!window.opener.closed)window.opener.fCambio(3);else{window.close();}">>></A>',4);
    fInsRow(2);
    fInsCell(0,0,"Dom",1);
    fInsCell(1,0,"Lun",1);
    fInsCell(2,0,"Mar",1);
    fInsCell(3,0,"Mie",1);
    fInsCell(4,0,"Jue",1);
    fInsCell(5,0,"Vie",1);
    fInsCell(6,0,"Sab",1);
  }

  function fGenCalendar(){
    var iRow = 3; var iCell = 0; var iDia = 1, iColor;
    if((iAnio % 4) == 0)
      aMeses = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    else
      aMeses = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    fInsRow(iRow);
    for(i=0;i<42;i++){
      if(i<iDiaUno || iDia>aMeses[iMes]){
        fInsCell(iCell,0,"&nbsp;");
      }
      if(i>=iDiaUno && iDia<=aMeses[iMes]){
        iColor = 5;
        if(iRef == iDia)
          iColor = 2;
        fInsCell(iCell,0,'<A HREF="javascript:if(!window.opener.closed)window.opener.fSetDate('+iDia+','+iMes+','+iAnio+');else{window.close();}">'+iDia+'</A>',iColor);
        iDia++;
      }
      iCell++;
      if(iCell == 7){
        iCell = 0;iRow++;
        fInsRow(iRow);
      }
    }
  }

  function fInsRow(iR){
     newRow = theTableBody.insertRow(iR);
  }

  function fInsCell(iC, iCol, cVal,cColor){
      newCell = newRow.insertCell(iC);
      newCell.innerHTML = cVal;
      newCell.align = "center";
      if(iCol!=0)
        newCell.colSpan = iCol;
      if(cColor == 1){
    	  newCell.bgColor = "#1D9961";
    	  newCell.style.color = "#FFFFFF";
      }
        
      if(cColor == 2)
        newCell.bgColor = "#B92025";     
      if(cColor == 3){
    	  newCell.bgColor = "#1D9961";
    	  newCell.style.color = "#FFFFFF";
      }
      if(cColor == 4){
    	  newCell.bgColor = "#1D9961";
    	  newCell.style.color = "#FFFFFF";
      }
      if(cColor == 5)
        newCell.bgColor = "#919294";
  }

  function fFirstDay(iA,iM){
    var firstDate = new Date(iA,iM,1);
    return firstDate.getDay();
  }

  function fCambio(cValor){
    if(cValor == 1){
      iAnio = parseInt(iAnio,10) - 1;
      iDiaUno = fFirstDay(iAnio,iMes);
    }
    if(cValor == 2){
      iMes = iMes - 1;
      if(iMes < 0)
        iMes = 0;
      iDiaUno = fFirstDay(iAnio,iMes);
    }
    if(cValor == 3){
      iMes = iMes + 1;
      if(iMes > 11)
        iMes = 11;
      iDiaUno = fFirstDay(iAnio,iMes);
    }
    if(cValor == 4){
      iAnio = parseInt(iAnio,10) + 1;
      iDiaUno = fFirstDay(iAnio,iMes);
    }
    fDelTable();
    fPanel();
    fGenCalendar();
  }

  function fSLS(cValor){
      form=this.wExp;
      iAux = ''+parseInt(form.FILAnio.value,10);       
      if(iAux != 'NaN')
        iAnio = iAux
      iMes = parseInt(form.SLSMes.value,10);
      iDiaUno = fFirstDay(iAnio,iMes);
      fDelTable();
      fPanel();
      fGenCalendar();
  }

  function fSetDate(dia,mes,anio){
    mes = mes+1;
    cVMes = '' + mes;
    if(mes < 10)
      cVMes = "0" + mes;

    cVDia = '' + dia;
    if(dia < 10)
      cVDia = "0" + dia;
 
    if(Objeto)
      Objeto.value = cVDia + '/' + cVMes + '/' + anio;

    if((wExp != null) && (!wExp.closed))
      wExp.close();
  }
