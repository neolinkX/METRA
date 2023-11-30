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
  String cQuery = "" + request.getParameter("hdQuery");

  if(cQuery.equals("null") || cQuery.equals("")){
     try{
       cQuery = (String) request.getSession(true).getAttribute("cRepSQL");
     }catch(Exception e){
       cQuery = "";
     }
  }

  try{
    TDDinRep dDinRep = new TDDinRep();
    vcRecords = dDinRep.findByAll(cQuery);
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
  <input type="hidden" name="hdQuery" value="">
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
                                     char   Cel = 'A', Cel2 = ' ';
                                     String Celda = "";
                                     while(stCols.hasMoreTokens()){
                                       Celda = Celda.valueOf(Cel) + (Celda.valueOf(Cel2).equals(" ") == true ? "" : Celda.valueOf(Cel2));
                                       xlsObj.comDespliega(Celda,10,stCols.nextToken());

                                       if(Cel2 == 'Z' || Cel2 == ' ')
                                         Cel = (char)(Cel + 1);
                                       if(Cel2 != ' ')
                                         Cel2 = (char)(Cel2 + 1);
                                       if(Cel > 90){
                                         Cel = 'A';
                                         if(Cel2 == ' ')
                                           Cel2 = 'A';
                                       }
                                       if(Cel2 > 90)
                                         Cel2 = 'A';
                                     }
                                     TVDinRep vDinRep;
                                     vDinRep = (TVDinRep) vcRecords.get(0);
                                     Vector vcKeys = vDinRep.getVcKeys();
                                     String cDato = "";
                                     for(int i=0; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       Cel = 'A'; Cel2 = ' ';
                                       for(int j=0; j<vcKeys.size(); j++){
                                         Celda = Celda.valueOf(Cel) + (Celda.valueOf(Cel2).equals(" ") == true ? "" : Celda.valueOf(Cel2));
                                         cDato = ""+vDinRep.get((String) vcKeys.get(j));
                                         if(cDato.equalsIgnoreCase("null"))
                                            cDato = "";
                                         xlsObj.comDespliega(Celda,11 + i,cDato);
                                         if(Cel2 == 'Z' || Cel2 == ' ')
                                           Cel = (char)(Cel + 1);
                                         if(Cel2 != ' ')
                                           Cel2 = (char)(Cel2 + 1);
                                         if(Cel > 90){
                                           Cel = 'A';
                                           if(Cel2 == ' ')
                                             Cel2 = 'A';
                                         }
                                         if(Cel2 > 90)
                                           Cel2 = 'A';
                                       }
                                     }
                                     out.print(xlsObj.creaActiveX("pgReporteBase","",false,false,true));
                                  }else{ // HTML
                                     out.print("<tr>");
                                     while(stCols.hasMoreTokens()){
                                       out.print("<td class=\"ERTablaT\">"+stCols.nextToken()+"</td>");
                                     }
                                     out.print("</tr>");
                                     TVDinRep vDinRep;
                                     vDinRep = (TVDinRep) vcRecords.get(0);
                                     Vector vcKeys = vDinRep.getVcKeys();
                                     for(int i=0; i<vcRecords.size(); i++){
                                       vDinRep = (TVDinRep) vcRecords.get(i);
                                       out.print("<tr>");
                                       for(int j=0; j<vcKeys.size(); j++){
                                         out.print(vEti.Texto("ERCampo","&nbsp;" + vDinRep.get((String) vcKeys.get(j))));
                                       }
                                       out.print("</tr>");
                                     }
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
