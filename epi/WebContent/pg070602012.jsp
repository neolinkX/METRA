<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*" %>

<html>
<%
  pg070602012CFG  clsConfig = new pg070602012CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070602012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070602012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Clave(desc)|";    // modificar
  String cCveOrdenar  = "iCveAsignacion|iCveAsignacion desc|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCveAsignacion|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave1    = "";
  String cClave2    = "";
  String cClave3    = "";
  String cDsc1    = "";
  String cDsc2    = "";
  String cDsc3    = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
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
       cClave1 = "" + bs.getFieldValue("iCveUniMed");
       cClave2 = "" + bs.getFieldValue("iCveModulo");
       cClave3 = "" + bs.getFieldValue("iCveArea");
       cDsc1 = "" + bs.getFieldValue("cDscUniMed");
       cDsc2 = "" + bs.getFieldValue("cDscModulo");
       cDsc3 = "" + bs.getFieldValue("cDscArea");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="iCveEquipo" value="<%if(request.getParameter("iCveEquipo")!=null) out.print(request.getParameter("iCveEquipo"));%>">
  <input type="hidden" name="iCveClasificacion" value="<%if(request.getParameter("iCveClasificacion")!=null) out.print(request.getParameter("iCveClasificacion"));%>">
  <input type="hidden" name="iCveTpoEquipo" value="<%if(request.getParameter("iCveTpoEquipo")!=null) out.print(request.getParameter("iCveTpoEquipo"));%>">

  <input type="hidden" name="iCveUniMed" value="<%=cClave1%>">
  <input type="hidden" name="iCveModulo" value="<%=cClave2%>">
  <input type="hidden" name="iCveArea" value="<%=cClave3%>">

  <input type="hidden" name="cDscUniMed" value="<%=cDsc1%>">
  <input type="hidden" name="cDscModulo" value="<%=cDsc2%>">
  <input type="hidden" name="cDscArea" value="<%=cDsc3%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Asignaciones del Equipo
                              </td>
                            </tr>
                               <%
                              // modificar según listado
                                 TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
                                 TVEQMEquipo vEQMEquipo = new TVEQMEquipo();
                                 Vector vcEQMEquipo = new Vector();
                                 if(request.getParameter("iCveEquipo")!=null && request.getParameter("iCveEquipo").compareTo("")!=0)
                                   vcEQMEquipo = dEQMEquipo.FindDsc(" where EQMEquipo.iCveEquipo = " + request.getParameter("iCveEquipo"), "");
                                 else{
                                   TDEQMAsignacion dEQMAsignacionT = new TDEQMAsignacion();
                                   TVEQMAsignacion vEQMAsignacionT = new TVEQMAsignacion();
                                   Vector vcEQMAsignacionT = new Vector();
                                   vcEQMAsignacionT = dEQMAsignacionT.FindDsc("", "");
                                   vEQMAsignacionT = (TVEQMAsignacion)vcEQMAsignacionT.get(0);
                                   vcEQMEquipo = dEQMEquipo.FindDsc(" where EQMEquipo.iCveEquipo = " + vEQMAsignacionT.getICveEquipo(), "");
                                 }

                                 if (vcEQMEquipo.size() > 0){
                                   for (int i = 0; i < vcEQMEquipo.size(); i++)
                                     vEQMEquipo = (TVEQMEquipo)vcEQMEquipo.get(i);

                                   out.print("<tr>");
                                    out.print("<td class='ETablaT' colspan='8'>Equipo</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Equipo:</td>");
                                     out.print("<td class='ECampo' colspan='7'>" + vEQMEquipo.getCDscEquipo() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Clasificación:</td>");
                                     out.print("<td class='ECampo' colspan='3'>" + vEQMEquipo.getCDscBreveClasificacion() + "</td>");
                                     out.print("<td class='EEtiqueta'>Tipo:</td>");
                                     out.print("<td class='ECampo' colspan='3'>" + vEQMEquipo.getCDscBreveTpoEquipo() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Marca:</td>");
                                     out.print("<td class='ECampo' colspan='3'>" + vEQMEquipo.getCDscBreveMarca() + "</td>");
                                     out.print("<td class='EEtiqueta'>Modelo:</td>");
                                     out.print("<td class='ECampo' colspan='3'>" + vEQMEquipo.getCModelo() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Serie:</td>");
                                     out.print("<td class='ECampo' colspan='3'>" + vEQMEquipo.getCNumSerie() + "</td>");
                                     out.print("<td class='EEtiqueta'>Inventario:</td>");
                                     out.print("<td class='ECampo' colspan='3'>" + vEQMEquipo.getCInventario() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class=\"ETablaT\">Asignación</td>");
                                     out.print("<td class=\"ETablaT\">Unidad Médica</td>");
                                     out.print("<td class=\"ETablaT\">Módulo</td>");
                                     out.print("<td class=\"ETablaT\">Área</td>");
                                     out.print("<td class=\"ETablaT\">Usuario Responsable</td>");
                                     out.print("<td class=\"ETablaT\">Inicio</td>");
                                     out.print("<td class=\"ETablaT\">Término</td>");
                                     out.print("<td class=\"ETablaT\">Actual</td>");
                                   out.print("</tr>");
                                 }

                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveAsignacion", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscModulo", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscArea", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cNombre", "&nbsp;") + " " + bs.getFieldValue("cApPaterno", "&nbsp;") + " " + bs.getFieldValue("cApMaterno", "&nbsp;")));
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cDtAsigna", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cDtDesasigna", "&nbsp;").toString()));
                                       if(Integer.parseInt(""+bs.getFieldValue("lActual"))==0)
                                         out.print(vEti.Texto("ETabla", "NO"));
                                       else
                                         out.print(vEti.Texto("ETabla", "SÍ"));
                                     out.print("</tr>");
                                   }
                               }
                               else{
                                 out.println("<tr>");
                                 out.print("<td class='ETablaC' colspan='8'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 out.println("</tr>");
                               }

                            %>
                          </table>
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
