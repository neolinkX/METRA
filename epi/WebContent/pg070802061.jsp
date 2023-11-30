<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070802061CFG  clsConfig = new pg070802061CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070802061.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802061.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No.Art.|Descripción|Clave|";    // modificar
  String cCveOrdenar  = "ALMArticulo.iCveArticulo|ALMArticulo.cDscBreve|ALMArticulo.cCveArticulo|";  // modificar
  String cDscFiltrar  = "No.Art.|Descripción|Clave|";    // modificar
  String cCveFiltrar  = "ALMArticulo.iCveArticulo|ALMArticulo.cDscBreve|ALMArticulo.cCveArticulo|";  // modificar
  String cTipoFiltrar = "7|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = "hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070802061.js)
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveArticulo", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCampoClave3" value="">
  <input type="hidden" name="hdCampoClave4" value="">

  <input type="hidden" name="SLSAlmacen" value="<%= request.getParameter("SLSAlmacen")%>">
  <input type="hidden" name="SLSTipoArticulo" value="<%= request.getParameter("SLSTipoArticulo")%>">
  <input type="hidden" name="SLSFamilia" value="<%= request.getParameter("SLSFamilia")%>">
  <input type="hidden" name="iCveArticulo" value="<%= request.getParameter("iCveArticulo")%>">

  <input type="hidden" name="hdTpoArticulo" value="<%out.print(request.getParameter("iCveTpoArticulo"));%>">
  <input type="hidden" name="hdFamilia" value="<%out.print(request.getParameter("iCveFamilia"));%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                              <td colspan="15" class="ETablaT">Artículo
                              </td>
                            </tr>
                             <%
                             if (bs != null){
// V E R   D A T O S
                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Número:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print(""+bs.getFieldValue("iCveArticulo", "&nbsp"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Clave:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   out.print(""+bs.getFieldValue("cCveArticulo", "&nbsp"));
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Tipo de Artículo:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print(""+bs.getFieldValue("cDscTpoArticulo", "&nbsp"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Familia:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   out.print(""+bs.getFieldValue("cDscFamilia", "&nbsp"));
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td colspan='3' class=\"EEtiqueta\">Descripción:</td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='90' rows='3' name='cDscArticulo' readOnly>");
                                   out.print(""+bs.getFieldValue("cDscArticulo", "&nbsp"));
                                   out.print("</textarea></td></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Descripción Breve:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print(""+bs.getFieldValue("cDscBreve", "&nbsp"));
                                   out.print("<td class='EEtiqueta' colspan='3'>Lote:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   if(bs.getFieldValue("lLote", "&nbsp").toString().compareTo("0")==0)
                                     out.print("<input type=\"checkbox\" name=\"chklLote\" disabled>");
                                   else
                                     out.print("<input type=\"checkbox\" name=\"chklLote\" checked disabled>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Unidad Almacenaje:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   out.print(""+bs.getFieldValue("cDscUniAlm", "&nbsp"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='2'>Unidad Suministro:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   out.print(""+bs.getFieldValue("cDscUniSum", "&nbsp"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Máximos y Mínimos:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   if(bs.getFieldValue("lMaxMin", "&nbsp").toString().compareTo("0")==0)
                                     out.print("<input type=\"checkbox\" name=\"chklMaxMin\" disabled>");
                                   else
                                     out.print("<input type=\"checkbox\" name=\"chklMaxMin\" checked disabled>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td colspan='3' class=\"EEtiqueta\">Observación:</td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='90' rows='5' name='cObservacion' readOnly>");
                                   out.print(""+bs.getFieldValue("cObservacion", "&nbsp"));
                                 out.print("</textarea></td></tr>");
                             }
                             else{
                               out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
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