<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Suàrez Romero
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.TVMEDServicio" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%

  TParametro   vParametros   = new TParametro("07");
  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  String cError = "";

  TEtiCampo    vEti          = new TEtiCampo();
  Vector vcRecords = new Vector();
  TVDinRep vRecord;
  // recuperar los datos.
/*&iCveUniMed='+frm.iCveUniMed.value+'&
iCveModulo='+frm.iCveModulo.value+'&
cDscUniMed='+cDscUniMed+'&
cDscModulo='+cDscModulo+'&
dtFechaI='+frm.dtFechaI.value+'&
dtFechaF='+frm.dtFechaF.value+'&
cUsuario='+cUsuario+'&
iCveUsuario='+frm.iCveUsuario.value+'&
iCveServicio='+frm.iCveServicio.value); */
  int iCveUniMed = 0, iCveModulo = 0, iCveServicio = 0, iCveRama = -1;
  String cFechaI = "", cFechaF = "";
  TFechas Fecha = new TFechas();

  try{
    StringBuffer cSQL = new StringBuffer();
    if(request.getParameter("iCveUniMed") != null)
      iCveUniMed =  Integer.parseInt(request.getParameter("iCveUniMed").toString(),10);
    if(request.getParameter("iCveModulo") != null)
      iCveModulo =  Integer.parseInt(request.getParameter("iCveModulo").toString(),10);
    if(request.getParameter("iCveServicio") != null)
      iCveServicio =  Integer.parseInt(request.getParameter("iCveServicio").toString(),10);
    if(request.getParameter("iCveRama") != null)
      iCveRama =  Integer.parseInt(request.getParameter("iCveRama").toString(),10);
    if(request.getParameter("dtFechaI") != null)
      cFechaI =  request.getParameter("dtFechaI").toString();
    if(request.getParameter("dtFechaF") != null)
      cFechaF =  request.getParameter("dtFechaF").toString();
    if(iCveRama == -1){
      cSQL.append(" select ES.iCveUsuAplica,  U.cApPaterno || ' ' || U.cApMaterno || ' ' ||  U.cNombre as cNombre ,")
          .append("        ES.dtFin, COUNT(*) as Realizados ")
          .append(" from EXPExamAplica EA ")
          .append("  inner join EXPServicio ES on ES.iCveExpediente = EA.iCveExpediente ")
          .append("                           and ES.iNumExamen     = EA.iNumExamen ")
          .append("  inner join SEGUsuario U on U.iCveUsuario = ES.iCveUsuAplica ")
          .append(" where EA.iCveUniMed =  ").append(iCveUniMed)
          .append("   and EA.iCveModulo =  ").append(iCveModulo)
          .append("   and ES.iCveServicio = ").append(iCveServicio)
          .append("   and ES.lConcluido = 1 ")
          .append("   and ES.dtFin between '").append(Fecha.getDateSQL(cFechaI)).append("'")
          .append("   and '").append(Fecha.getDateSQL(cFechaF)).append("'")
          .append(" group by ES.iCveUsuAplica, U.cApPaterno, u.cApMaterno, U.cNombre, ES.dtFin ");
    } else{
      cSQL.append(" select R.cDscRama, ER.iCveUsuAplica,  U.cApPaterno || ' ' || U.cApMaterno || ' ' ||  U.cNombre as cNombre , ")
          .append("        ER.dtFin, COUNT(*) as Realizados ")
          .append(" from EXPExamAplica EA ")
          .append("  inner join EXPRama ER on ER.iCveExpediente = EA.iCveExpediente ")
          .append("                       and ER.iNumExamen = EA.iNumExamen ")
          .append("  inner join MEDRama R on R.iCveServicio = ER.iCveServicio ")
          .append("                      and R.iCveRama     = ER.iCveRama ")
          .append("  inner join SEGUsuario U on U.iCveUsuario = ER.iCveUsuAplica ")
          .append(" where EA.iCveUniMed =  ").append(iCveUniMed)
          .append("   and EA.iCveModulo =  ").append(iCveModulo)
          .append("   and ER.iCveServicio = ").append(iCveServicio)
          .append("   and ER.lConcluido = 1 ");
     if(iCveRama>0)
      cSQL.append("   and ER.iCveRama = ").append(iCveRama);
      cSQL.append("   and ER.dtFin between '").append(Fecha.getDateSQL(cFechaI)).append("'")
          .append("   and '").append(Fecha.getDateSQL(cFechaF)).append("'")
          .append(" group by R.cDscRama, ER.iCveUsuAplica, U.cApPaterno, u.cApMaterno, U.cNombre, ER.dtFin ");
    }
    vcRecords = new TDDinRep().findByAll(cSQL.toString());

  }catch(Exception e){
    cError = e.getMessage();
  }

  String cCols = "RAMA|USUARIO|FECHA|CANTIDAD|POR USUARIO|";
  String cExcel = ""+request.getParameter("hdTipo");

  StringTokenizer stCols = new StringTokenizer(cCols,"|");
  int iCols = stCols.countTokens();

%>
<html>
<SCRIPT LANGUAGE="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  <%if(!vcRecords.isEmpty() && !cExcel.equals("1")){%>
    window.print();
  <%}%>
</SCRIPT>
<title>
Atención Por Servicio
</title>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="">
<form method="POST" action="pgReporteBase.jsp">
  <table width="100%" height="100%">
    <tr>
      <%if(!cExcel.equals("1")){%>
      <td align="center" width="50%" valign="middle">
        <img SRC="<%=vParametros.getPropEspecifica("RutaImgServer")%>LogoRep01.gif">&nbsp;
        <img SRC="<%=vParametros.getPropEspecifica("RutaImgServer")%>LogoRep02.gif">
      </td>
      <%}%>
    </tr>
    <tr>
      <td valign="top">
                          <table border="1" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="<%=iCols%>" class="ERTablaT">Atención Por Servicio</td>
                            </tr>
                            <%
                               if (!vcRecords.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo' colspan='"+iCols+"'>El reporte ha sido generado a través de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
                                     // Descripción del Servicio
                                     xlsObj.comDespliega("A",8,request.getParameter("cDscUniMed")+", "+request.getParameter("cDscModulo")+ " - ATENCIÓN POR SERVICIO DEL PERIODO DE  "+ cFechaI + "  A  " + cFechaF);
                                     xlsObj.comDespliega("A",10,((TVMEDServicio)(((Vector)new TDMEDServicio().FindByAll(" where iCveServicio = " + iCveServicio )).get(0))).getCDscServicio());
                                     char   Cel = 'A', CelF = 'A';
                                     String Celda = "", CeldaF = "";
                                     int bandera = 0, iReng = 11;
                                     while(stCols.hasMoreTokens()){
                                       if(iCveRama == -1 && bandera == 0) {
                                         bandera = 1;
                                         stCols.nextToken();
                                       }
                                       Celda = Celda.valueOf(Cel);
                                       xlsObj.comDespliega(Celda,iReng,stCols.nextToken());
                                       Cel = (char)(Cel + 1);
                                     }
                                     iReng++;
                                     TVDinRep vDinRep;
                                     vDinRep = (TVDinRep) vcRecords.get(0);
                                     Vector vcKeys = vDinRep.getVcKeys();
                                     int i=0, iRengI = 0, iRengF = 0;
                                     int iCveUsuario = 0, iCveUsuIni = 0;
                                     String cRama = "", cRamaI = "";
                                     int iBanRama = 0;
                                     for(i=0; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       Cel = 'A';
                                       iRengF++;
                                       for(int j=0; j<vcKeys.size(); j++){
                                         // Obtener descripción de la rama
                                         if(iCveRama >- 1  && j == 0){
                                           cRama = vDinRep.getString((String) vcKeys.get(j));
                                           if(cRamaI.compareToIgnoreCase(cRama) != 0 && !cRamaI.equalsIgnoreCase("")){
                                              iBanRama = 1;
                                              cRamaI = "";
                                           }
                                           if(cRamaI.equalsIgnoreCase(""))
                                             cRamaI = vDinRep.getString((String) vcKeys.get(j));
                                         }
                                         if( (iCveRama == -1 && j == 0) ||
                                             (iCveRama >- 1  && j == 1) ){
                                           iCveUsuario = vDinRep.getInt((String) vcKeys.get(j));
                                           // Imprimir sumatoria
                                           if( (iCveUsuIni != iCveUsuario && iCveUsuIni != 0) ||
                                                iBanRama == 1 ){
                                             xlsObj.comDespliega(Celda.valueOf(CelF),iReng + i -1, "=sum(" + CeldaF  + iRengI + ":" + CeldaF + (iRengF-1) + ")");
                                             xlsObj.comBordeTotal(Celda.valueOf(CelF),iReng + i -1,Celda.valueOf(CelF),iReng + i -1,1);
                                             iCveUsuIni = 0;
                                             iBanRama = 0;
                                           }
                                           if(iCveUsuIni == 0){
                                             iCveUsuIni = iCveUsuario;
                                             iRengF = iRengI = iReng + i;
                                           }
                                           continue;
                                         }
                                         Celda = Celda.valueOf(Cel);
                                         xlsObj.comDespliega(Celda,iReng + i,""+vDinRep.get((String) vcKeys.get(j)));
                                         Cel = (char)(Cel + 1);
                                       }
                                       CeldaF = Celda;
                                       CelF   = Cel;
                                     }
                                     xlsObj.comDespliega(Celda.valueOf(CelF),iReng + i -1, "=sum(" + CeldaF  + iRengI + ":" + CeldaF + iRengF + ")");
                                     xlsObj.comBordeTotal(Celda.valueOf(CelF),iReng + i -1 ,Celda.valueOf(CelF),iReng + i -1,1);
                                     xlsObj.comBordeTotal("A",iReng,iCveRama==-1?"C":"D",i+iReng-1,1);
                                     if(iCveRama==-1){
                                       xlsObj.comDespliega("B",i+iReng,"TOTAL:");
                                       xlsObj.comCreaFormula("C",i+iReng,"=sum(C" + iReng + ":C"+(i+iReng-1));
                                     }
                                     else{
                                       xlsObj.comDespliega("C",i+iReng,"TOTAL:");
                                       xlsObj.comCreaFormula("D",i+iReng,"=sum(D" + iReng + ":D"+(i+iReng-1));
                                     }
                                     xlsObj.comAlineaRango("A",i+iReng,"D",i+iReng,xlsObj.getAT_HDERECHA());
                                     xlsObj.comFontBold("B",i+iReng,"D",i+iReng);
                                     //out.print(xlsObj.creaActiveX("pg070106030R","Productividad-" + iCveServicio +"-" + Fecha.getFechaMMDDYYYYSinSep(Fecha.TodaySQL()), false, false, true));
                                     out.print(xlsObj.creaActiveX("pg070106030R","Productividad-" + iCveServicio +"-" + Fecha.getFechaDDMMYYYYSinSep(Fecha.TodaySQL()), false, false, true));
                                  }
                               }
                               else{
                                 if(cError.equals(""))
                                   out.print("<td class='ERCampo' colspan='"+iCols+"'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 else
                                   out.print("<td class='ERCampo' colspan='"+iCols+"'>ERROR AL EJECUTAR EL REPORTE: "+cError+"</td>");
                               }
                            %>
                          </table>
  </td></tr>
 </table>
</form>
</body>
<%=vErrores.muestraError()%>
</html>
