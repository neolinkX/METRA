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
  Vector vcPrevios = new Vector();

  String cRep = ""+request.getParameter("hdRep");
  String cCond = ""+request.getParameter("hdQuery");

  try{
    if(cRep.equals("1"))
       cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + cCond;

    if(cRep.equals("2")){
       StringTokenizer stAnioMes = new StringTokenizer(cCond,"|");
       cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();
       cRep =  cRep + " and {FN MONTH(expexamaplica.dtsolicitado)} = " + stAnioMes.nextToken();
    }

    if(cRep.equals("3"))
       cRep =  " and expexamaplica.dtsolicitado = '" + cCond + "'";

    String cSQL =
       "select grlempresas.icveempresa, (grlempresas.cnombrers || ' ' || grlempresas.cappaterno || ' ' || grlempresas.capmaterno ) as cnombre, "+
       "cdscmdotrans," +
       "icvemomentoap, count(*) as iNo "+
       "from EMOExamen  "+
       "join expexamaplica on EMOExamen.icveexpediente = expexamaplica.icveexpediente "+
       "and EMOExamen.inumexamen = expexamaplica.inumexamen  "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       cRep +
       " join grlempresas on grlempresas.icveempresa = expexamaplica.icvenumempresa "+
       " join grlmdotrans on grlempresas.icvemdotrans = grlmdotrans.icvemdotrans "+
       "group by grlempresas.icveempresa, grlempresas.cnombrers, grlempresas.cappaterno, grlempresas.capmaterno, cdscmdotrans, icvemomentoap ";

    TDDinRep dDinRep = new TDDinRep();
    vcRecords = dDinRep.findByAll(cSQL);

  }catch(Exception e){
    cError = e.getMessage();
  }

  String cCols = ""+request.getParameter("hdCols");
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
<%=""+request.getParameter("hdTit")%>
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
                              <td colspan="<%=iCols%>" class="ERTablaT"><%=""+request.getParameter("hdTit")%></td>
                            </tr>
                            <%
                               if (!vcRecords.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo' colspan='"+iCols+"'>El reporte ha sido generado a través de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
                                     xlsObj.comDespliega("A",8,request.getParameter("hdTit"));
                                     char   Cel = 'A';
                                     String Celda = "";
                                     while(stCols.hasMoreTokens()){
                                       Celda = Celda.valueOf(Cel);
                                       xlsObj.comDespliega(Celda,10,stCols.nextToken());
                                       Cel = (char)(Cel + 1);
                                     }
                                     TVDinRep vDinRep;
                                     vDinRep = (TVDinRep) vcRecords.get(0);
                                     Vector vcKeys = vDinRep.getVcKeys();
                                     int iAct = -1, iAnt = -1, iRen = -1;
                                     for(int i=0; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       Cel = 'A';
                                       iAct = vDinRep.getInt("ICVEEMPRESA");
                                       if(iAct != iAnt){
                                         iRen++;
                                         iAnt = iAct;
                                       }
                                       xlsObj.comDespliega("A",14 + iRen,""+vDinRep.get("CNOMBRE"));
                                       xlsObj.comDespliega("B",14 + iRen,""+vDinRep.get("ICVEEMPRESA"));
                                       xlsObj.comDespliega("C",14 + iRen,""+vDinRep.get("CDSCMDOTRANS"));
                                       if(vDinRep.getInt("ICVEMOMENTOAP") == 1)
                                          xlsObj.comDespliega("D",14 + iRen,""+vDinRep.get("INO"));
                                       if(vDinRep.getInt("ICVEMOMENTOAP") == 2)
                                          xlsObj.comDespliega("E",14 + iRen,""+vDinRep.get("INO"));
                                       if(vDinRep.getInt("ICVEMOMENTOAP") == 3)
                                          xlsObj.comDespliega("F",14 + iRen,""+vDinRep.get("INO"));
                                     }
                                     xlsObj.comBordeTotal("A",14,"G",14+iRen,1);
                                     xlsObj.comDespliega("D",16+iRen,"RESPONSABLE");
                                     try{
                                       TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                       xlsObj.comDespliega("D",19+iRen,"Dr(a). " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno());
                                     }catch(Exception e){}
                                     out.print(xlsObj.creaActiveX("pg070202010","",false,false,true));

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
