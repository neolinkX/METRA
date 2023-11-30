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
<%@ page import="com.micper.util.*" %>
<%@ page import="java.util.*"%>

<html>
<%
  pg070501071CFG  clsConfig = new pg070501071CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070501071.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070501071.jsp\" target=\"FRMCuerpo"); // modificar
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

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCvePeriodPla|cDscPeriodPla|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCvePeriodPla|cDscPeriodPla|";  // modificar
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

  TVCTRTpoPlantilla vCTRTpoPlantilla = new TVCTRTpoPlantilla();
  TDCTRTpoPlantilla dCTRTpoPlantilla = new TDCTRTpoPlantilla();
  Vector vcCTRTpoPlantilla = new Vector();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070501071.js)

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
       cClave  = ""+bs.getFieldValue("iCvePeriodPla", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdIAnio" value="<%out.print(request.getParameter("iAnio"));%>">
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
                              <td colspan="6" class="ETablaT">Periodo de la Plantilla
                              </td>
                               <%
                               out.println("<tr>");
                               out.println("<td class=\"ETablaTR\">Año:</td>");
                               out.println("<td class=\"ETabla\">");

                             if(request.getParameter("hdBoton").compareTo("Guardar")==0 || request.getParameter("hdBoton").compareTo("GuardarA")==0 || request.getParameter("hdBoton").compareTo("Cancelar")==0){
                               out.println("<select name=\"iAnio\" size=\"1\" onchange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                               if(request.getParameter("hdIAnio")==null)
                                 out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               else if(request.getParameter("hdIAnio")!=null && Integer.parseInt(request.getParameter("hdIAnio"))<1)
                                 out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               for (int i = iAnio; i >= (iAnio - iAniosAtras); i--)
                                 if(request.getParameter("hdIAnio")!=null && Integer.parseInt(request.getParameter("hdIAnio"))==i)
                                   out.print("<option value = " + i + " selected>" + i + "</option>");
                                 else
                                   out.print("<option value = " + i + ">" + i + "</option>");
                               out.print("</select>");
                             }
                             else{
                               if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0)
                                 out.println("<select name=\"iAnio\" size=\"1\" disabled>");
                               else
                                 out.println("<select name=\"iAnio\" size=\"1\" onchange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                               if(request.getParameter("iAnio")==null)
                                 out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               else if(request.getParameter("iAnio")!=null && Integer.parseInt(request.getParameter("iAnio"))<1)
                                 out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               for (int i = iAnio; i >= (iAnio - iAniosAtras); i--)
                                 if(request.getParameter("iAnio")!=null && Integer.parseInt(request.getParameter("iAnio"))==i)
                                   out.print("<option value = " + i + " selected>" + i + "</option>");
                                 else
                                   out.print("<option value = " + i + ">" + i + "</option>");
                               out.print("</select>");
                             }

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico

                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 20, 20, "cDscPeriodPla", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.print("<td class='EEtiqueta'>Plantilla:</td>");
                                 out.print("<td>");
                                 out.print("<select name=\"iCveTpoPlantilla\" size=\"1\">");
                                 vcCTRTpoPlantilla = dCTRTpoPlantilla.FindByAll(" where lActivo = 1", "");
                                 if (vcCTRTpoPlantilla.size() > 0){
                                   out.print("<option value = 0>Seleccione...</option>");
                                   for (int i = 0; i < vcCTRTpoPlantilla.size(); i++){
                                     vCTRTpoPlantilla = (TVCTRTpoPlantilla)vcCTRTpoPlantilla.get(i);
                                       out.print("<option value = " + vCTRTpoPlantilla.getICveTpoPlantilla() + ">" + vCTRTpoPlantilla.getCDscBreve() + "</option>");
                                   }
                                 }
                                 else{
                                   out.print("<option value = 0>Datos no disponibles...</option>");
                                 }
                                 out.print("</select>");
                                 out.print("</td>");

                                 out.print("<tr>");
                                 out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                 out.print("<td class='EEtiqueta' align='center'>");
                                 out.print("<textarea cols=\"50\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                                 out.print("</textarea>");
                                 out.print("</td>");
                                 out.print("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Generar plantillas el:", "ECampo", "text", 10, 10, "dtGeneracion", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Plantillas vencen el:", "ECampo", "text", 10, 10, "dtVencimiento", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Situación:"));
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
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCvePeriodPla", ""+bs.getFieldValue("iCvePeriodPla"), 3, "", "", true, true, false));
                                   out.println("</tr>");

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 20, 20, "cDscPeriodPla", ""+bs.getFieldValue("cDscPeriodPla", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");

                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.print("<td class='EEtiqueta'>Plantilla:</td>");
                                     out.print("<td>");
                                     out.print("<select name=\"iCveTpoPlantilla\" size=\"1\">");
                                     vcCTRTpoPlantilla = dCTRTpoPlantilla.FindByAll("", "");
                                     if (vcCTRTpoPlantilla.size() > 0){
                                       for (int i = 0; i < vcCTRTpoPlantilla.size(); i++){
                                         vCTRTpoPlantilla = (TVCTRTpoPlantilla)vcCTRTpoPlantilla.get(i);
                                           if(Integer.parseInt(bs.getFieldValue("iCveTpoPlantilla").toString()) == Integer.parseInt(vCTRTpoPlantilla.getICveTpoPlantilla()+""))
                                             out.print("<option selected value = " + vCTRTpoPlantilla.getICveTpoPlantilla() + ">" + vCTRTpoPlantilla.getCDscBreve() + "</option>");
                                           else
                                             out.print("<option value = " + vCTRTpoPlantilla.getICveTpoPlantilla() + ">" + vCTRTpoPlantilla.getCDscBreve() + "</option>");
                                       }
                                     }
                                     else{
                                       out.print("<option value = 0>Datos no disponibles...</option>");
                                     }
                                     out.print("</select>");
                                     out.print("</td>");
                                   }
                                   else{
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Plantilla:", "ECampo", "text", 100, 100, "cDscTpoPlantilla", ""+bs.getFieldValue("cDscBreve", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                     out.println("</tr>");
                                   }

                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.print("<tr>");
                                     out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                     out.print("<td class='EEtiqueta' align='center'>");
                                     out.print("<textarea cols=\"50\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                                     out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                     out.print("</textarea>");
                                     out.print("</td>");
                                     out.print("</tr>");
                                   }
                                   else{
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                     out.print("<td class='EEtiqueta' align='center'>");
                                     out.print("<textarea cols=\"50\" rows=\"5\" name=\"cObservacion\" readOnly>");
                                     out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                     out.print("</textarea>");
                                     out.print("</td>");
                                     out.print("</tr>");
                                   }

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Generar plantillas el:", "ECampo", "text", 10, 10, "dtGeneracion", ""+bs.getFieldValue("cdtGeneracion"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Plantillas vencen el:", "ECampo", "text", 10, 10, "dtVencimiento", ""+bs.getFieldValue("cdtVencimiento"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");

                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "INACTIVO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "ACTIVO", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Situación:"));
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
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ECampo' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
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