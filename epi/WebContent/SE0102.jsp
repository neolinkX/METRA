<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%
TEntorno    vEntorno      = new TEntorno();
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
%>
<html>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cie.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript">
  var theTable;
  var theTableBody;
  var newCell;
  var newRow;

  function fOnLoad(){
    fInit();
    fFillEspecialidad();
    fLoadCIE();
  }

  function fInit() {
    theTable = (document.all) ? document.all.myTABLE :
    document.getElementById("myTABLE")
    theTableBody = theTable.tBodies[0]
  }

  function fDelTable(){
    for(i=0;theTableBody.rows.length;i++){
      theTableBody.deleteRow(0);
    }
  }

  function fFillEspecialidad(){
    form=document.forms[0];
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

  function fLoadCIE(){
    form=document.forms[0];
    var aDiag;
    cBus = form.cCIE.value.toUpperCase();
    cSubs = '';
    fDelTable();
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
      fInsRow(0);
      fInsCell(0,'CIE');
      fInsCell(1,'Descripción Breve');
      fInsCell(2,'Observación');
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
            fInsRow(j);
            fInsCell(0,meEsp[4]);
            fInsCell(1,meEsp[2]);
            fInsCell(2,meEsp[3]);
            j++;
          }
        }
      }
    }
  }

  function fInsRow(iR){
     newRow = theTableBody.insertRow(iR);
  }

  function fInsCell(iC, cVal){
      newCell = newRow.insertCell(iC);
      newCell.innerHTML = cVal;
      newCell.align = "center";
  }

</script>
<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
<form method="POST" action="_self">&nbsp;
  <table background="" width="80%" border="1" align="center" cellspacing="0" cellpadding="3">
    <tr>
      <td colspan="2">Selector de Diagnósticos</td>
    </tr>
    <tr>
      <td>Especialidad</td>
      <td><select name="iCveEspecialidad" size="1" onChange="fLoadCIE();"></select></td>
    </tr>
    <tr>
      <td>Búsqueda Rápida por:</td>
      <td><select name="lCIE" size="1" onChange="fLoadCIE();"></select></td>
    </tr>
    <tr>
      <td colspan="2" align"center">Introduzca el inicio de la búsqueda:&nbsp;<input type="text" name="cCIE" value="" size="30" onkeyup="fLoadCIE();"></td>
    </tr>
    <tr>
<td colspan="2">
       <table ID="myTABLE" border="1" align="center" cellspacing="0" cellpadding="3">
           <tr><td>&nbsp;</td></tr>
       </table>
</td>
    </tr>
 </table>
</form>
</body>
</html>