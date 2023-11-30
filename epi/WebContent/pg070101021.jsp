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
  pg070101021CFG  clsConfig = new pg070101021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101021.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101021.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "CIE|Descripci�n Breve|";    // modificar
  String cCveOrdenar  = "cCIE|cDscBreve|";  // modificar
  String cDscFiltrar  = "CIE|Descripci�n Breve|";    // modificar
  String cCveFiltrar  = "cCIE|cDscBreve|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
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
  //  al directorio funciones de wwwrooting en un archivo con extensi�n js (vg. pg070101021.js)

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
       cClave  = ""+bs.getFieldValue("iCveDiagnostico", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdEspecialidad" value="<%out.print(request.getParameter("iCveEspecialidad"));%>">
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
                              <td colspan="6" class="ETablaT">Diagn�stico
                              </td>

            <tr>
              <td class="ETablaT">Especialidad:</td>
              <td class="ETablaTL" colspan='5'>
              <%

              TDMEDEspecialidad dMEDEspecialidad = new TDMEDEspecialidad();
              TVMEDEspecialidad vMEDEspecialidad = new TVMEDEspecialidad();
              Vector vcMEDEspecialidad = new Vector();
              if(request.getParameter("hdBoton").compareTo("Guardar")==0 || request.getParameter("hdBoton").compareTo("GuardarA")==0 || request.getParameter("hdBoton").compareTo("Cancelar")==0){
                out.print("<select name=\"iCveEspecialidad\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcMEDEspecialidad = dMEDEspecialidad.FindByAll("", " order by iCveEspecialidad ");
                if (vcMEDEspecialidad.size() > 0){
                  for (int i = 0; i < vcMEDEspecialidad.size(); i++){
                    vMEDEspecialidad = (TVMEDEspecialidad)vcMEDEspecialidad.get(i);
                    if (request.getParameter("hdEspecialidad")!=null && request.getParameter("hdEspecialidad").compareToIgnoreCase(new Integer(vMEDEspecialidad.getICveEspecialidad()).toString())==0)
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + " selected>" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                    else
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + ">" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else{
                if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0)
                  out.print("<select name=\"iCveEspecialidad\" disabled>");
                else
                  out.print("<select name=\"iCveEspecialidad\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcMEDEspecialidad = dMEDEspecialidad.FindByAll("", " order by iCveEspecialidad ");
                if (vcMEDEspecialidad.size() > 0){
                  if(request.getParameter("iCveEspecialidad")==null || request.getParameter("iCveEspecialidad").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveEspecialidad")!=null && Integer.parseInt(request.getParameter("iCveEspecialidad"))<1 )
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcMEDEspecialidad.size(); i++){
                    vMEDEspecialidad = (TVMEDEspecialidad)vcMEDEspecialidad.get(i);
                    if (request.getParameter("iCveEspecialidad")!=null && request.getParameter("iCveEspecialidad").compareToIgnoreCase(new Integer(vMEDEspecialidad.getICveEspecialidad()).toString())==0)
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + " selected>" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                    else
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + ">" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              out.print("</select>");
              out.print("</td>");
              out.print("</tr>");

                               if (lNuevo){ // Modificar de acuerdo al cat�logo espec�fico
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "CIE:", "ECampo", "text", 20, 20, "cCIE", "", 3, "", "fMayus(this);", false, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<td class=\"EEtiqueta\">Diagn�stico:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras2\" value=\"\" disabled></p></td>");
                                 %>
                                 <td>
                                 <textarea cols="115" rows="3" name="cDscDiagnostico" OnKeyPress="fChgArea2(this);" OnChange="fChgArea2(this);" OnBlur="fMayus(this);"></textarea>
                                 </td>
                                 </tr>
                                 <%
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripci�n Breve:", "ECampo", "text", 100, 100, "cDscBreve", "", 3, "", "fMayus(this);", false, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                 %>
                                 <td>
                                 <textarea cols="115" rows="5" name="cObservacion" OnKeyPress="fChgArea(this);" OnChange="fChgArea(this);" OnBlur="fMayus(this);"></textarea>
                                 </td>
                                 </tr>
                                 <%
                                 out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Uso Frecuente:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="chklFrecuente">
                                     </td>
                                     <%
                                 out.println("</tr>");
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
                                 out.print(vEti.EtiCampo("EEtiqueta", "CIE:", "ECampo", "text", 20, 20, "cCIE", ""+bs.getFieldValue("cCIE", "&nbsp;"), 3, "", "fMayus(this);", false, true, lCaptura));
                                 out.println("</tr>");
                                 if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                   out.println("<td class=\"EEtiqueta\">Diagn�stico:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras2\" value=\"\" disabled></p></td>");
                                   out.print("<td class='EEtiqueta' align='center'>");
                                   out.print("<textarea cols=\"115\" rows=\"3\" name=\"cDscDiagnostico\" OnKeyPress=\"fChgArea2(this);\" OnChange=\"fChgArea2(this);\" OnBlur=\"fMayus(this);\">");
                                   out.print(""+bs.getFieldValue("cDscDiagnostico", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print(vEti.Texto("EEtiqueta", "Diagn�stico:"));
                                   out.print("<td class='EEtiqueta' align='center'>");
                                   out.print("<textarea cols=\"115\" rows=\"3\" name=\"cDscDiagnostico\" readOnly>");
                                   out.print(""+bs.getFieldValue("cDscDiagnostico", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripci�n Breve:", "ECampo", "text", 100, 100, "cDscBreve", ""+bs.getFieldValue("cDscBreve", "&nbsp;"), 3, "", "fMayus(this);", false, true, lCaptura));
                                 out.println("</tr>");
                                 if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                   out.print("<tr>");
                                   out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                   out.print("<td class='EEtiqueta' align='center'>");
                                   out.print("<textarea cols=\"115\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                                   out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                   out.print("<td class='EEtiqueta' align='center'>");
                                   out.print("<textarea cols=\"115\" rows=\"5\" name=\"cObservacion\" readOnly>");
                                   out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                   out.print("</textarea>");
                                   out.print("</td>");
                                   out.print("</tr>");
                                 }
                                   out.println("<tr>");
                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lFrecuente"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Uso Frecuente:", "ECampo", 10, 10, "lFrecuente", "NO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Uso Frecuente:", "ECampo", 10, 10, "lFrecuente", "SI", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Activo:", "ECampo", 10, 10, "lActivo", "NO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Activo:", "ECampo", 10, 10, "lActivo", "SI", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   }
                                   else{
                                     out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Uso Frecuente:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="chklFrecuente"<%
                                         if(bs.getFieldValue("lFrecuente").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                       %>>
                                     </td>
                                     <%
                                     out.println("</tr>");
                                     out.println("<tr>");
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