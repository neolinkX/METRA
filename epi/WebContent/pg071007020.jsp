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
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg071007020CFG  clsConfig = new pg071007020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071007020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071007020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cDetalle    = "pg071007021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Usuario|Primer Apellido|Segundo Apellido|Nombre|";    // modificar
  String cCveOrdenar  = "iCveUsuario|cApPaterno|cApMaterno|SEGUsuario.cNombre|";  // modificar
  String cDscFiltrar  = "Clave Usuario|Primer Apellido|Segundo Apellido|Nombre|";    // modificar
  String cCveFiltrar  = "iCveUsuario|cApPaterno|cApMaterno|SEGUsuario.cNombre|";  // modificar
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

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";//clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Si";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  TVGRLUsuario vGRLUsuario = new TVGRLUsuario();
  TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
  Vector vcGRLUsuario = new Vector();

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
  
  
  
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
    	 //System.out.println(bs.rowNo());
       //cPosicion = Integer.toString(bs.rowNo());
       cPosicion = Integer.toString(bs.pageNo());
       //System.out.println(cPosicion);
       cClave  = ""+bs.getFieldValue("iCveArea", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="<%out.print(request.getParameter("hdCampoClave1"));%>">
  <input type="hidden" name="hdCampoClave2" value="<%out.print(request.getParameter("hdCampoClave2"));%>">
  <input type="hidden" name="hdCampoClave3" value="<%out.print(request.getParameter("hdCampoClave3"));%>">
  <input type="hidden" name="hdCampoClave4" value="<%out.print(request.getParameter("hdCampoClave4"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="6" class="ETablaT">Usuarios
                              </td>

              <%

                               out.print("<tr>");
                                 out.print("<td class='ETablaT'>Clave</td>");
                                 out.print("<td class='ETablaT'>Primer Apellido</td>");
                                 out.print("<td class='ETablaT'>Segundo Apellido</td>");
                                 out.print("<td class='ETablaT'>Nombre</td>");
                                 out.print("<td class=\"ETablaT\">Selección</td>");
                               out.print("</tr>");

                                 if (bs != null){
                                   boolean lDetalle = clsConfig.getLPagina(cDetalle);
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print("<td class='ETablaR'>" + bs.getFieldValue("iCveUsuario", "&nbsp;") + "</td>");
                                       out.print("<td class='ETabla'>" + bs.getFieldValue("cApPaterno", "&nbsp;") + "</td>");
                                       out.print("<td class='ETabla'>" + bs.getFieldValue("cApMaterno", "&nbsp;") + "</td>");
                                       out.print("<td class='ETabla'>" + bs.getFieldValue("cNombre", "&nbsp;") + "</td>");
                                       if(lDetalle){
                                         out.print("<td class=\"ECampo\">");
                                         out.print(vEti.clsAnclaTexto("EAncla","RFC, Cédula, Profesión","JavaScript:fIrDetalle('" + bs.getFieldValue("iCveUsuario") + "', '" + bs.getFieldValue("cApPaterno") + "', '" + bs.getFieldValue("cApMaterno") + "', '" + bs.getFieldValue("cNombre") + "');", ""));
                                         out.print("</td>");
                                       }
                                   }
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
                                 }
                            %>
                          </table><% // Fin de Datos %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>