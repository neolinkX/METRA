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
<%@ page import="com.micper.util.*" %>
<html>
<%
  pg071006011CFG  clsConfig = new pg071006011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071006011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071006011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";
  String cAsignar  = "pg071003033.jsp";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripci�n|";    // modificar
  String cCveOrdenar  = "iCveRequisito|cDscRequisito|";  // modificar
  String cDscFiltrar  = "Clave|Descripci�n|";    // modificar
  String cCveFiltrar  = "iCveRequisito|cDscRequisito|";  // modificar
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
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deber�n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensi�n js (vg. pg071006011.js)

  // Esta funci�n no debe modificarse
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
       cClave  = ""+bs.getFieldValue("iCveRequisito", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="iCveRequisito" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveRequisito")); else out.print(cClave);%>">
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
                              <td colspan="6" class="ETablaT">Requisito
                              </td>
                               <%
                               if (lNuevo){ // Modificar de acuerdo al cat�logo espec�fico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<td class=\"EEtiqueta\">Descripci�n:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                 out.println("<td>");
                                 out.println("<textarea cols='75' rows='5' name='cDscRequisito' OnKeyPress='fChgArea500(this);' OnChange='fChgArea500(this);' OnBlur='fMayus(this);'></textarea>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripci�n Breve:", "ECampo", "text", 75, 100, "cDscReqBreve", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                 out.println("<td>");
                                 out.println("<textarea cols='75' rows='5' name='cObservacion' OnKeyPress='fChgArea2000(this);' OnChange='fChgArea2000(this);' OnBlur='fMayus(this);'></textarea>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta", "Situaci�n:"));
                                 out.println("<td>");
                                 out.println("<input type='checkbox' name='lActivo' checked>");
                                 out.println("</td>");
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveRequisito", ""+bs.getFieldValue("iCveRequisito", "&nbsp;"), 3, "", "", true, true, false));
                                   out.println("</tr>");

                                 if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                   out.print("<tr>");
                                   out.println("<td class=\"EEtiqueta\">Descripci�n:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                   out.print("<td class='EEtiqueta'>");
                                   out.print("<textarea cols=\"75\" rows=\"5\" name=\"cDscRequisito\" OnKeyPress=\"fChgArea500(this);\" OnChange=\"fChgArea500(this);\" OnBlur=\"fMayus(this);\">");
                                   out.print(""+bs.getFieldValue("cDscRequisito", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print(vEti.Texto("EEtiqueta", "Descripci�n:"));
                                   out.print("<td class='EEtiqueta'>");
                                   out.print("<textarea cols=\"75\" rows=\"5\" name=\"cDscRequisito\" readOnly>");
                                   out.print(""+bs.getFieldValue("cDscRequisito", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripci�n Breve:", "ECampo", "text", 75, 100, "cDscReqBreve", ""+bs.getFieldValue("cDscReqBreve", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                   out.print("<tr>");
                                   out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                   out.print("<td class='EEtiqueta'>");
                                   out.print("<textarea cols=\"75\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea2000(this);\" OnChange=\"fChgArea2000(this);\" OnBlur=\"fMayus(this);\">");
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

                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situaci�n:", "ECampo", 10, 10, "lActivo", "INACTIVO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situaci�n:", "ECampo", 10, 10, "lActivo", "ACTIVO", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Situaci�n:"));
                                     out.print("<td>");
                                     out.print("<input type='checkbox' name='lActivo' ");
                                         if(bs.getFieldValue("lActivo").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                     out.print("></td>");
                                   }
                                   out.println("</tr>");

                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
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