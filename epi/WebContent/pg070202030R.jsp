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
  Vector vcDatos = new Vector();

  String cRep = "";
  int iAptos = 0, iNoAptos = 0, i = 0;

  try{
    TDDinRep dDinRep = new TDDinRep();

    cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + request.getParameter("iAnio") +
            " and {FN MONTH(expexamaplica.dtsolicitado)} = " + request.getParameter("iMeses");

    String cSQL =
       " select icveusuario, "+
      // " {FN DAYOFMONTH(expexamaplica.dtsolicitado)} as iDia, "+
       " {FN DAY(expexamaplica.dtsolicitado)} as iDia, "+
       " count(*) as iNoExamenes "+
       " from emoexamen "+
       " join expexamaplica on emoexamen.icveexpediente = expexamaplica.icveexpediente "+
       " and emoexamen.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       cRep +
       " join segusuario on expexamaplica.icveususolicita = segusuario.icveusuario "+
       " group by icveusuario, expexamaplica.dtsolicitado "+
       " order by icveusuario, expexamaplica.dtsolicitado";

    vcDatos = dDinRep.findByAll(cSQL);

    cSQL =
       " select icveusuario, (segusuario.cnombre || ' ' || segusuario.cappaterno || ' ' || segusuario.capmaterno) as cnombre "+
       " from emoexamen "+
       " join expexamaplica on emoexamen.icveexpediente = expexamaplica.icveexpediente "+
       " and emoexamen.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       cRep +
       " join segusuario on expexamaplica.icveususolicita = segusuario.icveusuario "+
       " group by icveusuario, segusuario.cnombre, segusuario.cappaterno, segusuario.capmaterno ";

    vcRecords = dDinRep.findByAll(cSQL);

  }catch(Exception e){
    cError = e.getMessage();
  }

  String cExcel = ""+request.getParameter("hdTipo");

%>
<html>
<SCRIPT LANGUAGE="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  <%if(!cExcel.equals("1")){%>
    window.print();
  <%}%>
</SCRIPT>
<title>
Notificación Informe Estadístico
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
                              <td class="ERTablaT">Notificación Informe Estadístico</td>
                            </tr>
                            <%
                               if (!vcRecords.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo'>El reporte ha sido generado a través de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
                                     xlsObj.comDespliega("C",8,""+request.getParameter("cUniMed") + " - " + request.getParameter("cModulo"));
                                     xlsObj.comDespliega("AG",8,(""+request.getParameter("cMeses")).toUpperCase() + " DE " + request.getParameter("iAnio"));
                                     try{
                                       TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                       xlsObj.comDespliega("AD",52,"Dr(a). " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno());
                                     }catch(Exception e){}
                                     char   Cel = 'A', Cel2 = ' ';
                                     String Celda = "";
                                     TVDinRep vDinRep;
                                     vDinRep = (TVDinRep) vcRecords.get(0);
                                     Vector vcKeys = vDinRep.getVcKeys();
                                     String cDato = "";
                                     for(i=0; i<vcRecords.size(); i++){
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

                                     TVDinRep vDinExa = (TVDinRep) vcDatos.get(0);
                                     Vector vcLlaves = vDinExa.getVcKeys();
                                     String Colda = "", cColumna = "";
                                     int iAct = 0, iAnt = 0, iRen = 0, iCol = 4, iNo = 0;
                                     for(int k=0; k<vcDatos.size(); k++){
                                       vDinExa = (TVDinRep) vcDatos.get(k);
                                       iAct = vDinExa.getInt("ICVEUSUARIO");
                                       if(iAct != iAnt){
                                          iAnt = iAct;
                                          iRen = iRen + 1;
                                          iNo = 0;
                                       }
                                       for(i=1;i<=31;i++){
                                          if(i > 23){
                                            Celda = "A" + Celda.valueOf((char)(64 + (i % 23))).toUpperCase();
                                          }else{
                                            Celda = Celda.valueOf((char)('C' + i)).toUpperCase();
                                          }
                                          if(vDinExa.getInt("IDIA") == i ){
                                             iNo++;
                                             xlsObj.comDespliega(Celda,iRen + 10,""+vDinExa.getInt("INOEXAMENES"));
                                          }
                                       }
                                       xlsObj.comCreaFormula("AJ",10+iRen,"=(AI"+(10+iRen)+"/" + iNo + ")");
                                     }

                                     xlsObj.comBordeTotal("A",11,"AK",iRen+10,1);
                                     out.print(xlsObj.creaActiveX("pg070202030","",false,false,true));
                                  }
                               }
                               else{
                                 if(cError.equals(""))
                                   out.print("<td class='ERCampo' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 else
                                   out.print("<td class='ERCampo' colspan='6'>ERROR AL EJECUTAR EL REPORTE: "+cError+"</td>");
                               }
                            %>
                          </table>
  </td></tr>
 </table>
</form>
</body>
<%=vErrores.muestraError()%>
</html>
