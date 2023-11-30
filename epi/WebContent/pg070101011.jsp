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
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<html>
<%
  pg070101011CFG  clsConfig = new pg070101011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101011.jsp\" target=\"FRMCuerpo"); // modificar
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

  String cDiagnostico    = "pg070101020.jsp";       // modificar
  String cRecomendaciones    = "pg070101030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
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
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070101011.js)
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
       cClave  = ""+bs.getFieldValue("iCveEspecialidad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
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
                              <td colspan="2" class="ETablaT">Especialidad
                              </td>
                            </tr>
                            <%
                               boolean lDiagnostico = clsConfig.getLPagina(cDiagnostico);
                               boolean lRecomendacion = clsConfig.getLPagina(cRecomendaciones);

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 75, 100, "cDscEspecialidad", "", 3, "", "fMayus(this);", false, true, lCaptura));
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
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveEspecialidad", ""+bs.getFieldValue("iCveEspecialidad", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 75, 100, "cDscEspecialidad", ""+bs.getFieldValue("cDscEspecialidad", "&nbsp;"), 3, "", "fMayus(this);", false, true, lCaptura));
                                   out.println("</tr>");
                                 if(request.getParameter("hdBoton").compareTo("Modificar")==0){
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
                                   out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }
                                 out.println("<tr>");
                                 if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                   if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                     out.print(vEti.EtiAreaTexto("EEtiqueta", "Activo:", "ECampo", 10, 10, "lActivo", "NO", 3, "", "", true, true, false));
                                   else
                                     out.print(vEti.EtiAreaTexto("EEtiqueta", "Activo:", "ECampo", 10, 10, "lActivo", "SI", 3, "", "", true, true, false));
                                 }
                                 else{
                                   out.print(vEti.Texto("EEtiqueta", "Activo:"));
                                 %>
                                     <td>
                                       <input type="checkbox" name="chklActivo"<%
                                         if(bs.getFieldValue("lActivo").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                       %>>
                                     </td>
                                     <%
                                   }
                                   out.println("</tr>");
                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0 && (clsConfig.getLPagina(cDiagnostico) || clsConfig.getLPagina(cRecomendaciones))){
                                     out.println("<tr>");
                                     out.print("<td class=\"ETablaC\" colspan=\"2\">");
                                     if(lDiagnostico)
                                       out.print(vEti.clsAnclaTexto("EAncla","Diagnósticos","JavaScript:fIrDiagnostico('" + bs.getFieldValue("iCveEspecialidad") + "');", "") + "&nbsp;&nbsp;&nbsp;");
                                     if(lRecomendacion)
                                       out.print(vEti.clsAnclaTexto("EAncla","Recomendaciones","JavaScript:fIrRecomendaciones('" + bs.getFieldValue("iCveEspecialidad") + "');", ""));
                                     out.print("</td>");
                                     out.println("</tr>");
                                   }
                                 }
                                 else{
                                   out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
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