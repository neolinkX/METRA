<%/**
 * Title: pg070107050
 * Description:
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version
 * Clase:
 */%> 
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%> 
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070107050CFG  clsConfig = new pg070107050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070107050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070107050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070107050.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar

  String cEstatusIR = "";            // modificar
  if(request.getParameter("hdBoton").compareTo("Guardar")==0)
    cEstatusIR   = "Imprimir";            // modificar
  else
    cEstatusIR   = "Reporte";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "SaveCancelOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  TFechas dtFecha = new TFechas();
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
%>
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdType" value="">
  <input type="hidden" name="lIniciado" value="1">
  <input type='hidden' name='hdBuscado' value='0'>
  <input type="hidden" name="hdMacAddress" value="">
<input type="hidden" name="hdIpAddress" value="">
<input type="hidden" name="hdComputerName" value="">

<%  if (request.getParameter("hdICvePersonal") != null){
%>
       <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal").toString()%>">
<%
    }else{
%>
        <input type="hidden" name="hdICvePersonal" value=""> 
<% 
    }
%>
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ 
       //TVPERDatos vPerDatos = null;
       TVEXPExamAplica vEXPExamAplica = null;
       if (request.getParameter("hdType") != null){
           //System.out.println("hdType");
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
               //System.out.println("=P");
             vEXPExamAplica=clsConfig.findUser();
          }else if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
               //System.out.println("=E");
             vEXPExamAplica=clsConfig.findExpediente();
          }
       }


       if (request.getParameter("hdType") != null){
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
%>
             <input type="hidden" name="iNumExamen" value="<%=request.getParameter("iNumExamen").toString()%>">
<%
          }else if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
             if(vEXPExamAplica!=null){
%>
                <input type="hidden" name="iNumExamen" value="<%=vEXPExamAplica.getINumExamen()%>">
<%
             }else{
%>
                <input type="hidden" name="iNumExamen" value="">
<%
             }
          }else{
%>
                <input type="hidden" name="iNumExamen" value="">
<%
          }
       }else{
%>
         <input type="hidden" name="iNumExamen" value="">
<%
       }
%>

  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
        <tr><td align="center" colspan="4" class="EResalta">Desbloquear Examen</td></tr>
                            <tr>
                              <td colspan="5" class="ETablaT">Buscar Examen a Desbloquear
                              </td>
                            </tr>

                              <%
                                 if(vEXPExamAplica!=null){
                                    //System.out.println("examaplica != null");
                                    out.print("<tr>");
                                    out.print("<td class=\"EEtiqueta\">Expediente:</td>");
                                    out.print("<td>");
                                    out.print("<Input Type=\"Text\" Value=\""+vEXPExamAplica.getICveExpediente()+"\" Name=\"iCveExpediente\" Size=\"10\" MaxLength=\"10\">");
                                    out.print("</td>");
                                 }else{
                                    //System.out.println("examaplica = null");
                                    out.print("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Expediente:", "ECampo", "text", 10, 10,"iCveExpediente", "", 0, "", "", false, true,true, request));
                                 }
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Expediente","JavaScript:fSelExp();", "Buscar Expediente",""));
                                 out.print("</td>");
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer(1);", "Buscar Personal",""));
                                 out.print("</td>");
                                 out.print("</tr>");
                                 
                              %>
                          </table>&nbsp;
                            <%
                        if(vEXPExamAplica!=null){
                                     //System.out.println("Expexamaplica2 != null");
                          if(vEXPExamAplica.getICveExpediente() > 0){
                                         //System.out.println("Expexamaplica2 = null");
                        %>
                        <script language>
                          form = document.forms[0];
                          form.hdBuscado.value = 1;
                        </script>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="2" class="ETablaT">Datos del Examen
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Expediente:</td>
                              <td class="ECampo"><%=vEXPExamAplica.getICveExpediente()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Examen numero:</td>
                              <td class="ECampo"><%=vEXPExamAplica.getINumExamen()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Fecha vencida para dictaminar:</td>
                              <td class="ECampo"><%=vEXPExamAplica.getDtBlqDictamen()%></td>
                            </tr>
                            <tr>
                            <%
                             out.print("<tr>");
                             out.print(vEti.EtiCampoCS("EEtiqueta", "Nueva Fecha para Dictaminar:", "ECampo", "text", 10, 10,3,"dtSolicitado", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                             out.print("</tr>");
                             %>
                             
                            <input type="hidden" name="hdDtBlqDictamen" value="<%=dFechaActual%>">
                            <input type="hidden" name="hdNoExpediente" value="<%=vEXPExamAplica.getICveExpediente()%>">
                            <input type="hidden" name="iCveExpediente" value="<%=vEXPExamAplica.getICveExpediente()%>">
                            <input type="hidden" name="hdInumExamen" value="<%=vEXPExamAplica.getINumExamen()%>">
                            <input type="hidden" name="hdFechaVen" value="<%=vEXPExamAplica.getDtBlqDictamen()%>">
                            <input type="hidden" name="hdBuscado" value="<%=dFechaActual%>">

                            <%
                              //out.print(vEti.EtiCampoCS("EEtiqueta","Encuesta de Salida:","ECampo","text",10,10,3,"iFolioEs","",0,"","fMayus(this);",false,true,true));
                            %>
                            </tr>
                          </table>
                        <%
                          }else{
                          %>
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                          <%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                          %>
                                </table>
                          <%
                          }
                        }else{
//******************************************************************************
                          if(request.getParameter("hdBoton").compareTo("Guardar")==0){
                              //System.out.println("Guardar");
                            %>
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                   out.print("<tr>");
                                     out.print("<td colspan=\"3\" class=\"ETablaT\">Datos del Examen</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Expediente:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdNoExpediente") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Examen numero:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdInumExamen") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Fecha vencida para dictaminar:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdFechaVen") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='ETablaT'>El examen podrá dictaminarse en la siguiente fecha:</td>");
                                   out.print("<td colspan='2' class='ECampo'>" + request.getParameter("dtSolicitado") + "</td>");
                                   out.print("</tr>");

                                   TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
                                   TVEXPExamAplica vEXPExamAplica2 = new TVEXPExamAplica();
                                   Vector vcEXPExamAplica = new Vector(); 

                                   //vcEXPExamCat = dEXPExamCat.FindBy(request.getParameter("hdNoExpediente"), request.getParameter("iNumExamen"));
                                   /*vcEXPExamAplica = dEXPExamAplica.FindByAll(request.getParameter("hdNoExpediente"));
                                   if (vcEXPExamAplica.size() > 0)
                                     for (int i = 0; i < vcEXPExamAplica.size(); i++){
                                       vEXPExamAplica2 = (TVEXPExamAplica)vcEXPExamAplica.get(i);
                                       out.print("<tr>");
                                         out.print("<td class='ECampo'>" + vEXPExamAplica2.getDtBlqDictamen() + "</td>");
                                         //out.print("<td class='ECampo'>" + vEXPExamCat.getCDscCategoria() + "</td>");
                                         //out.print("<td class='ECampo'>" + vEXPExamCat.getCDscMotivo() + "</td>");
                                       out.print("</tr>");
                                     }*/

                            %>
                                </table>

                            <%
                          }
//******************************************************************************
                          if (request.getParameter("hdType") != null && request.getParameter("hdType").length()>0){
                             // //System.out.println("hdType2 ==>>"+request.getParameter("hdType"));
                          %>
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                          <%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                          %>
                                </table>
                          <%
                          }
                        }
                        %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
