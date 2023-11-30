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
  pg070301031CFG  clsConfig = new pg070301031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070301031.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción del Motivo|";    // modificar
  String cCveOrdenar  = "iCveMotRecep|cDscMotRecep|";  // modificar
  String cDscFiltrar  = "Clave|Descripción del Motivo|";    // modificar
  String cCveFiltrar  = "iCveMotRecep|cDscMotRecep|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
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
       cClave2  = ""+bs.getFieldValue("iCveMotRecep", "");
       cPropiedad = ""+bs.getFieldValue("cDscMotRecep", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Motivo de Recepción
                              </td>
                            </tr>
                              <tr><% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                            </tr>
                            <% if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ETabla", "text", 5, 5, "iCveMotRecep", "&nbsp", 3, "", "", true, true, false));
                                 out.println("</tr>");

                                 TDTipoRecep dTipoRecep = new TDTipoRecep();
                                 out.print("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Descripción del Tipo:"));
                                 out.print("<td>");
                                 out.print(vEti.SelectOneRowSinTD("iCveTipoRecep", "", dTipoRecep.FindByAll(""), "iCveTipoRecep", "cDscTipoRecep", request, "99"));
                                 out.print("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción del Motivo:", "ETabla", "text", 50, 50, "cDscMotRecep", "", 4, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                 %>
                                 <td>
                                 <textarea cols="75" rows="5" name="cObservacion" OnKeyPress="fChgArea(this);" OnChange="fChgArea(this);" OnBlur="fMayus(this);"></textarea>
                                 </td>
                                 </tr>
                                 <%
                                 out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Activo:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="chklActivo" CHECKED>
                                     </td>
                                     <%
                                 out.println("</tr>");


                               }
                               else{
                                 if (bs != null){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ETabla", "text", 5, 5, "iCveMotRecep", bs.getFieldValue("iCveMotRecep","&nbsp;").toString(), 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   if (lCaptura){
                                      TDTipoRecep dTipoRecep = new TDTipoRecep();
                                      out.print("<tr>");
                                      out.print(vEti.Texto("EEtiqueta", "Descripción del Tipo:"));
                                      out.print("<td>");
                                      out.print(vEti.SelectOneRowSinTD("iCveTipoRecep", "", dTipoRecep.FindByAll(""), "iCveTipoRecep", "cDscTipoRecep", request, bs.getFieldValue("iCveTipoRecep","0").toString()));
                                      out.print("</td>");
                                      out.println("</tr>");
                                   }else{
                                      out.println("<tr>");
                                      if (bs.getFieldValue("cDscTipoRecep","&nbsp;").toString().compareToIgnoreCase("null") != 0)
                                         out.print(vEti.EtiCampo("EEtiqueta", "Descripción del Tipo:", "ETabla", "text", 5, 5, "cDscTipoRecep", bs.getFieldValue("cDscTipoRecep","&nbsp;").toString(), 3, "", "fMayus(this);", true, true, lCaptura));
                                      else
                                         out.print(vEti.EtiCampo("EEtiqueta", "Descripción del Tipo:", "ETabla", "text", 5, 5, "cDscTipoRecep", "&nbsp", 3, "", "fMayus(this);", true, true, lCaptura));
                                      out.println("</tr>");
                                   }
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción del Motivo:", "ECampo", "text", 50, 50, "cDscMotRecep", bs.getFieldValue("cDscMotRecep","&nbsp;").toString(), 4, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");

                                 if(lCaptura){
                                   out.print("<tr>");
                                   out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                   out.print("<td class='EEtiqueta'>");
                                   out.print("<textarea cols=\"75\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                                   out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                   out.print("<td class='EEtiqueta'>");
                                   out.print("<textarea cols=\"75\" rows=\"5\" name=\"cObservacion\" readOnly>");
                                   out.print(""+bs.getFieldValue("cDscLong", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }



                                 }
                                 else{
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                 }
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