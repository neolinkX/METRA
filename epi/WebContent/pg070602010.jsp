<%/**
 * Title: Listado de Equipos
 * Description: Listado de Equipos
 * Copyright: Copyright (c) 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070602010CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="gob.sct.medprev.vo.*"%>

<html>
<%
  pg070602010CFG  clsConfig = new pg070602010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070602010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070602010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070602011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Modelo|Número de Serie|Número de Inventario|";    // modificar
  String cCveOrdenar  = "cModelo|cNumSerie|cInventario|";  // modificar
  String cDscFiltrar  = "Modelo|Número de Serie|Número de Inventario|";    // modificar
  String cCveFiltrar  = "cModelo|cNumSerie|cInventario|";  // modificar
  String cTipoFiltrar = "8|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  if (bs == null)
    cNavStatus = "Disabled";
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
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveEquipo" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="9" class="ETablaT">Listado de Equipos
                              </td>
                            </tr>
                            <tr>
<%                            out.println(vEti.Texto("EEtiqueta", "Clasificación:"));
                              out.println("<td colspan='3'>");
                              out.println(vEti.SelectOneRowSinTD("iCveClasificacion", "llenaSLT(1,this.value,'','',document.forms[0].iCveTpoEquipo);", clsConfig.vClasif, "iCveClasificacion", "cDscClasificacion", request, "0"));
                              out.println("</td>");

                              TDEQMTpoEquipo dTipo = new TDEQMTpoEquipo();
                              Vector vTipo = new Vector();
                              TVEQMTpoEquipo tvTipo = new TVEQMTpoEquipo();
                              out.println(vEti.Texto("EEtiqueta", "Tipo de Equipo:"));
                              if (request.getParameter("iCveClasificacion") != null && request.getParameter("iCveClasificacion").toString().length() > 0) {
                                  String clasif = request.getParameter("iCveClasificacion").toString();
                                  String tpoEq;
                                  if(request.getParameter("iCveTpoEquipo") != null && request.getParameter("iCveTpoEquipo").toString().length() > 0)
                                    tpoEq = request.getParameter("iCveTpoEquipo").toString();
                                  else
                                    tpoEq = "0";
                                  vTipo = dTipo.FindByAll(" where lActivo=1 and iCveClasificacion=" + clasif, " order by iCveTpoEquipo ");
                                  out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                  out.println("<option value='0'>Seleccione...</option>");
                                  for (int i = 0; i < vTipo.size(); i++) {
                                      tvTipo = (TVEQMTpoEquipo) vTipo.get(i);
                                      if (tpoEq.equals("" + tvTipo.getICveTpoEquipo()))
                                         out.println("<option value='" + tvTipo.getICveTpoEquipo() + "' selected>" + tvTipo.getCDscBreve() + "</option>");
                                      else
                                         out.println("<option value='" + tvTipo.getICveTpoEquipo() + "'>" + tvTipo.getCDscBreve() + "</option>");
                                  }
                              } else {
                                  out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                  out.println("<option value='0'>Datos no Disponibles...</option>");
                              }
                              out.println("</SELECT></td>");
                              out.println("</td>");
%>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave del Equipo</td>
                              <td class="ETablaT">Nombre</td>
                              <td class="ETablaT">Marca</td>
                              <td class="ETablaT">Modelo</td>
                              <td class="ETablaT">Inventario</td>
                              <td class="ETablaT" colspan="4">No. de Serie</td>
                            </tr>
                             <% // modificar según listado

                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveEquipo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscEquipo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscBreveMarca", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cModelo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cInventario", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cNumSerie", "&nbsp;").toString()));

                                     // Ver si tiene permisos para ir a las paginas
                                     if (clsConfig.getLPagina("pg070602011.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEquipo") + ", 1);\">Detalle</a></td>");
                                     if (clsConfig.getLPagina("pg070602012.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEquipo") + ", 2);\">Asignaciones</a></td>");
                                     if (clsConfig.getLPagina("pg070602013.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEquipo") + ", 3);\">Mantenimiento</a></td>");
                                     out.print("</tr>");
                                   }
                               } else {
                                   // NO HAY REGISTROS
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
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
