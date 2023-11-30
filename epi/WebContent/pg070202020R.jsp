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
  Vector vcDictamenNeg = new Vector();
  Vector vcDictamenPos = new Vector();

  String cRep = "";
  int iAptos = 0, iNoAptos = 0;
  try{
    TDDinRep dDinRep = new TDDinRep();

    cRep =  " and {FN YEAR(expexamaplica.dtsolicitado)} = " + request.getParameter("iAnio") +
            " and {FN MONTH(expexamaplica.dtsolicitado)} = " + request.getParameter("iMeses");

    String cSQL =
       "select count(ldictamen) as iNoAptos "+
       "from emoexamen "+
       "join expexamaplica on emoexamen.icveexpediente = expexamaplica.icveexpediente "+
       "and emoexamen.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icvenumempresa = " + request.getParameter("iCveEmpresa") +
       cRep +
       " join expexamcat on emoexamen.icveexpediente = expexamcat.icveexpediente "+
       "and emoexamen.inumexamen = expexamcat.inumexamen "+
       "where ldictamen = 0 ";

    vcDictamenNeg = dDinRep.findByAll(cSQL);

    iNoAptos = ((TVDinRep) vcDictamenNeg.get(0)).getInt("INOAPTOS");

    cSQL =
       "select count(ldictamen) as iAptos  "+
       "from emoexamen "+
       "join expexamaplica on emoexamen.icveexpediente = expexamaplica.icveexpediente "+
       "and emoexamen.inumexamen = expexamaplica.inumexamen "+
       " and expexamaplica.icveunimed = " + request.getParameter("iCveUniMed") +
       " and expexamaplica.icvemodulo = " + request.getParameter("iCveModulo") +
       " and expexamaplica.icvenumempresa = " + request.getParameter("iCveEmpresa") +
       cRep +
       " join expexamcat on emoexamen.icveexpediente = expexamcat.icveexpediente "+
       "and emoexamen.inumexamen = expexamcat.inumexamen "+
       "where ldictamen = 1 ";

    vcDictamenPos = dDinRep.findByAll(cSQL);

    iAptos = ((TVDinRep) vcDictamenPos.get(0)).getInt("IAPTOS");

  }catch(Exception e){
    cError = e.getMessage();
  }

  String cExcel = ""+request.getParameter("hdTipo");

%>
<html>
<SCRIPT LANGUAGE="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  <%if(!vcDictamenPos.isEmpty() && !cExcel.equals("1")){%>
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
                               if (!vcDictamenPos.isEmpty() || !vcDictamenNeg.isEmpty()){
                                  if(cExcel.equals("1")){ // Excel
                                     out.print("<TR><td class='ERCampo'>El reporte ha sido generado a través de Excel</td></TR>");
                                     TExcel xlsObj = new TExcel("07");
                                     TFechas fHoy = new TFechas("07");
                                     java.sql.Date sqlHoy = new java.sql.Date(new java.util.Date().getTime());
                                     xlsObj.comDespliega("B",20,request.getParameter("cEmpresa"));
                                     String cExamenes = ""+(iAptos + iNoAptos)+" Exámenes Médicos en Operación en el Módulo "+
                                                        request.getParameter("cModulo")+", resultando ";

                                     if(iNoAptos == 0)
                                       cExamenes = cExamenes + " todos aptos";
                                     else
                                       cExamenes = cExamenes + " aptos " + iAptos + " y no aptos " + iNoAptos;

                                     cExamenes = cExamenes + ", correspondientes al mes de " + request.getParameter("cMeses") + " de " + request.getParameter("iAnio") + ".";

                                     xlsObj.comDespliega("B",27,cExamenes);

                                     try{
                                       TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                       String cFecha =  "México, " +  vUsuario.getCDscEntidadFed().substring(0,1).toUpperCase() +  vUsuario.getCDscEntidadFed().substring(1).toLowerCase() + " a " + fHoy.getIntDay(sqlHoy) + " de " + fHoy.getMonthName(sqlHoy).toLowerCase() + " de " + fHoy.getIntYear(sqlHoy);
                                       xlsObj.comDespliega("E",16,cFecha);
                                       xlsObj.comDespliega("B",38,"Dr(a). " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno());
                                     }catch(Exception e){}

                                     out.print(xlsObj.creaActiveX("pg070202020a","",false,false,true));

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
