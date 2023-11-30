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
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>

<html>
<%
  pg071003060CFG  clsConfig = new pg071003060CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003060.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003060.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101011.jsp";       // modificar
  String cProcXUM    = "pg070101011.jsp";       // modificar
  String cServRama    = "pg070101020.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Ap.Paterno|Ap.Materno|Nombre|";    // modificar
  String cCveOrdenar  = "iCveUsuario|cApPaterno|cApMaterno|cNombre|";  // modificar
  String cDscFiltrar  = "Clave|Ap.Paterno|Ap.Materno|Nombre|";    // modificar
  String cCveFiltrar  = "iCveUsuario|cApPaterno|cApMaterno|cNombre|";  // modificar
  String cTipoFiltrar = "7|8|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
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
  <input type="hidden" name="iCveUsuario" value="">
  <input type="hidden" name="cApPaterno" value="">
  <input type="hidden" name="cApMaterno" value="">
  <input type="hidden" name="cNombre" value="">
  <input type="hidden" name="cUsuario" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="7" class="ETablaT">Usuarios
                              </td>
                            </tr>
                             <% // modificar según listado
                               boolean lProcXUM = clsConfig.getLPagina(cProcXUM);
                               boolean lServRama = clsConfig.getLPagina(cServRama);
                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Clave</td>");
                                   out.print("<td class=\"ETablaT\">Primer Apellido</td>");
                                   out.print("<td class=\"ETablaT\">Segundo Apellido</td>");
                                   out.print("<td class=\"ETablaT\">Nombre</td>");
                                   out.print("<td class=\"ETablaT\">Clave Usuario</td>");
                                   if(lProcXUM || lServRama)
                                     out.print("<td class='ETablaT' colspan=2>Permisos a:</td>");
                                   out.print("</tr>");
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveUsuario", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cApPaterno", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cApMaterno", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cNombre", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cUsuario", "&nbsp;").toString()));
                                     if(lProcXUM){
                                       out.print("<td class=\"ETabla\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Procesos por U. M.","JavaScript:fIrProcXUM('" + bs.getFieldValue("iCveUsuario") + "','" + bs.getFieldValue("cApPaterno") + "','" + bs.getFieldValue("cApMaterno") + "','" + bs.getFieldValue("cNombre") + "','" + bs.getFieldValue("cUsuario") + "');", ""));
                                       out.print("</td>");
                                     }
                                     if(lServRama){
                                       out.print("<td class=\"ETabla\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Servicios y Ramas","JavaScript:fIrServRama('" + bs.getFieldValue("iCveUsuario") + "','" + bs.getFieldValue("cApPaterno") + "','" + bs.getFieldValue("cApMaterno") + "','" + bs.getFieldValue("cNombre") + "','" + bs.getFieldValue("cUsuario") + "');", ""));
                                       out.print("</td>");
                                     }
                                       out.print("</tr>");
                                   }
                               }
                               else{
                                 out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
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
