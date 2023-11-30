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
  pg070801041CFG  clsConfig = new pg070801041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070801041.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070801041.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Descripción Breve|";    // modificar
  String cCveOrdenar  = "iCveArea|ALMArea.cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripción Breve|";    // modificar
  String cCveFiltrar  = "iCveArea|ALMArea.cDscBreve|";  // modificar
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
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070801041.js)

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
       cClave  = ""+bs.getFieldValue("iCveArea", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="iNoLetras" value="">
  <input type="hidden" name="hdAlmacen" value="<%out.print(request.getParameter("iCveAlmacen"));%>">
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
                              <td colspan="6" class="ETablaT">Áreas del Almacén
                              </td>

            <tr>
              <td class="ETablaTR">Almacén:</td>
              <td class="ETabla" colspan='5'>
              <%
              if(request.getParameter("hdBoton").compareTo("Modificar")==0 || request.getParameter("hdBoton").compareTo("Nuevo")==0)
                out.print("<select name=\"iCveAlmacen\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\" disabled>");
              else
                out.print("<select name=\"iCveAlmacen\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");

                TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
                TVALMAlmacen vALMAlmacen = new TVALMAlmacen();
                Vector vcALMAlmacen = new Vector();
                vcALMAlmacen = dALMAlmacen.FindByAll("", " order by iCveAlmacen ");
                if (vcALMAlmacen.size() > 0){
                  if(request.getParameter("iCveAlmacen")==null || request.getParameter("iCveAlmacen").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcALMAlmacen.size(); i++){
                    vALMAlmacen = (TVALMAlmacen)vcALMAlmacen.get(i);
                    if (request.getParameter("iCveAlmacen")!=null && request.getParameter("iCveAlmacen").compareToIgnoreCase(new Integer(vALMAlmacen.getICveAlmacen()).toString())==0)
                      out.print("<option value = " + vALMAlmacen.getICveAlmacen() + " selected>" + vALMAlmacen.getCDscAlmacen() + "</option>");
                    else if (request.getParameter("hdAlmacen")!=null && request.getParameter("hdAlmacen").compareToIgnoreCase(new Integer(vALMAlmacen.getICveAlmacen()).toString())==0)
                      out.print("<option value = " + vALMAlmacen.getICveAlmacen() + " selected>" + vALMAlmacen.getCDscAlmacen() + "</option>");
                    else
                      out.print("<option value = " + vALMAlmacen.getICveAlmacen() + ">" + vALMAlmacen.getCDscAlmacen() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              %>
              </select>
              </td>
              </tr>

                            <%
                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 100, 100, "cDscArea", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción Breve:", "ECampo", "text", 50, 50, "cDscBreve", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Tipo de Artículo:"));
                                 out.println("<td colspan='5'>");
                                 TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
                                 out.println(vEti.SelectOneRowSinTD("iCveTpoArticulo", "", dALMTpoArticulo.FindByCustomWhere(""), "iCveTpoArticulo", "cDscBreve", request, "0"));
                                 out.println("</td>");
                                 out.print("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                 %>
                                 <td>
                                 <textarea cols="100" rows="5" name="cObservacion" OnKeyPress="fChgArea(this);" OnChange="fChgArea(this);" OnBlur="fMayus(this);"></textarea>
                                 </td>
                                 </tr>
                                 <%
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
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveArea", ""+bs.getFieldValue("iCveArea", "&nbsp;"), 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 100, 100, "cDscArea", ""+bs.getFieldValue("cDscArea", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción Breve:", "ECampo", "text", 50, 50, "cDscBreve", ""+bs.getFieldValue("cDscBreve", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");
                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.print(vEti.Texto("EEtiqueta", "Tipo de Artículo:"));
                                     out.println("<td colspan='5'>");
                                     TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
                                     out.println(vEti.SelectOneRowSinTD("iCveTpoArticulo", "", dALMTpoArticulo.FindByCustomWhere(""), "iCveTpoArticulo", "cDscBreve", request, ""+bs.getFieldValue("iCveTpoArticulo")));
                                     out.println("</td>");
                                   }
                                   else{
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Tipo de Artículo:", "ECampo", "text", 100, 100, "cDscTpoArticulo", ""+bs.getFieldValue("cDscBreveTpoArticulo", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                     out.println("</tr>");
                                   }
                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                     out.print("<td class='ETablaL'>");
                                     out.print("<textarea cols=\"100\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                                     out.print(""+bs.getFieldValue("cObservacion"));
                                     out.print("</textarea>");
                                     out.print("</td>");
                                     out.print("</tr>");
                                   }
                                   else{
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                     out.print("<td class='EEtiqueta'>");
                                     out.print("<textarea cols=\"100\" rows=\"5\" name=\"cObservacion\" readOnly>");
                                     out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                     out.print("</textarea>");
                                     out.print("</td>");
                                     out.print("</tr>");
                                   }
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