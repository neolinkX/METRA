<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>


<html>
<%
  pg070802021CFG  clsConfig = new pg070802021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveArea|cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveArea|cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;            // modificar
  String cCanWrite   = "yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cAnio = "";
  String cvLoteConfirmativo = "";
  Vector vAlmacen     = new Vector();
  Vector vTpoArticulo = new Vector();
  Vector vFamilia     = new Vector();
  Vector vAlmxArt     = new Vector();
  Vector vUbicacion   = new Vector();
  String cvAlmacen     = "";
  String cvTpoArticulo = "";
  String cvFamilia     = "";
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

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
  <input type="hidden" name="hdIni"      value="<% if (request.getParameter("hdIni") != null)  out.print(request.getParameter("hdIni")); else out.print("");%>">
  <input type="hidden" name="hdTipoArticulo" value="<% if (request.getParameter("SLSTipoArticulo") != null)  out.print(request.getParameter("SLSTipoArticulo")); else
                                                           if (request.getParameter("hdTipoArticulo") != null) out.print(request.getParameter("hdTipoArticulo")); else out.print("");%>">
  <input type="hidden" name="hdFamilia" value="<% if (request.getParameter("SLSFamilia") != null)  out.print(request.getParameter("SLSFamilia")); else
                                                      if (request.getParameter("hdFamilia") != null) out.print(request.getParameter("hdFamilia")); else out.print("");%>">


  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Artículo Seleccionado</td>
           </tr><tr>
             <td class="EEtiqueta">Tipo de Artículo:</td>
             <td class="ETabla">
                <select name="SLSTipoArticulo" size="1"  disabled="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,'');">
             <%     vTpoArticulo = clsConfig.vTpoArticulos;
                    if (request.getParameter("SLSTipoArticulo") != null){
                      if (request.getParameter("SLSTipoArticulo") != "")
                          cvTpoArticulo = request.getParameter("SLSTipoArticulo");
                    } else {
                      if (request.getParameter("hdTipoArticulo") != null)
                         if (request.getParameter("hdTipoArticulo") != "")
                           cvTpoArticulo = request.getParameter("hdTipoArticulo");
                    }

                    if (cvTpoArticulo.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                    if (!vTpoArticulo.isEmpty()){
                       for(int i=0;i<vTpoArticulo.size();i++){
                         TVALMTpoArticulo VALMTpoArticulo = new TVALMTpoArticulo();
                         VALMTpoArticulo = (TVALMTpoArticulo) vTpoArticulo.get(i);
                          if (cvTpoArticulo.compareToIgnoreCase(new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString()) == 0)
                             out.print("<option selected value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">"  + VALMTpoArticulo.getIIDPartida()  + "-"  + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                          else
                             out.print("<option value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">"  + VALMTpoArticulo.getIIDPartida()  + "-"  + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                       }
                    }

             %>
                </select>
             <% if (request.getParameter("SLSTipoArticulo") != null)
                  if (request.getParameter("SLSTipoArticulo").toString().compareToIgnoreCase("") != 0)
                    out.println("<input type=\"hidden\" name=\"SLSTipoArticulo\" value=\"" + request.getParameter("SLSTipoArticulo").toString() + "\">");
             %>
             </td>
             <td class="EEtiqueta">Familia:</td>
             <td class="ECampo">
                <select name="SLSFamilia" size="1" disabled="1">
                <% if (request.getParameter("SLSFamilia") != null){
                      if (request.getParameter("SLSFamilia") != "")
                          cvFamilia = request.getParameter("SLSFamilia");
                    } else {
                      if (request.getParameter("hdFamilia") != null)
                         if (request.getParameter("hdFamilia") != "")
                           cvFamilia = request.getParameter("hdFamilia");
                    }

                    if (cvFamilia.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    vFamilia = clsConfig.vFamilias;
                    if (vFamilia != null)
                      if (!vFamilia.isEmpty())
                        for(int i=0;i<vFamilia.size();i++){
                          TVALMFamilia VALMFamilia = (TVALMFamilia) vFamilia.get(i);
                          if (cvFamilia.compareToIgnoreCase(new Integer(VALMFamilia.getiCveFamilia()).toString()) == 0)
                             out.print("<option selected value=\"" + new Integer(VALMFamilia.getiCveFamilia()).toString() + "\">"  + VALMFamilia.getcDscBreve().toString() + "</option>");
                          else
                             out.print("<option value=\"" + new Integer(VALMFamilia.getiCveFamilia()).toString() + "\">"  + VALMFamilia.getcDscBreve().toString() + "</option>");
                        }
                %>
                </select>
                <% if (request.getParameter("SLSFamilia") != null)
                     if (request.getParameter("SLSFamilia").toString().compareToIgnoreCase("") != 0)
                      out.println("<input type=\"hidden\" name=\"SLSFamilia\" value=\"" + request.getParameter("SLSFamilia").toString() + "\">");
                 %>
             </td>
           </tr>
           <tr>
           <%
            vAlmxArt = clsConfig.vAlmxArt;
            int ivCveArticulo = 0;
            if (request.getParameter("hdIni") != null)
               ivCveArticulo = new Integer(request.getParameter("hdIni").toString()).intValue();
            if (!vAlmxArt.isEmpty()){
              for (int i=0;i<vAlmxArt.size();i++){
                TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
                VALMArtxAlm = (TVALMArtxAlm) vAlmxArt.get(i);
                if (VALMArtxAlm.getiCveArticulo() == ivCveArticulo){
                   out.print("<td class=\"EEtiqueta\">Artículo:</td>");
                   out.print("<td class=\"ECampo\">" + VALMArtxAlm.getiCveArticulo() + "</td>");
                   out.print("<td class=\"EEtiqueta\">Descripción Breve:</td>");
                   out.print("<td class=\"ECampo\">" + VALMArtxAlm.getcDscArticulo() + "</td>");
                }
              }
            }
           %>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Ubicación del Artículo en el Almacén</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Almacén:</td>
                              <td class="ECampo" colspan="2">
                              <select name="SLSAlmacen" size="1" onChange="JavaScript:if (window.fSubmitForm) window.fSubmitForm('Buscar')">
                              <%  vAlmacen = clsConfig.vAlmacenes;
                                  if (request.getParameter("SLSAlmacen") != null){
                                      if (request.getParameter("SLSAlmacen") != "")
                                         cvAlmacen = request.getParameter("SLSAlmacen");
                                  }

                                  if (cvAlmacen.compareToIgnoreCase("") == 0)
                                    out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                                  else
                                    out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                                  if (!vAlmacen.isEmpty()){
                                    for(int i=0;i<vAlmacen.size();i++){
                                      TVALMAlmacen VALMAlmacen = new TVALMAlmacen();
                                      VALMAlmacen = (TVALMAlmacen) vAlmacen.get(i);
                                      if (cvAlmacen.compareToIgnoreCase(new Integer(VALMAlmacen.getICveAlmacen()).toString()) == 0)
                                        out.print("<option selected value=\"" + new Integer(VALMAlmacen.getICveAlmacen()).toString() + "\">"  + VALMAlmacen.getCDscAlmacen().toString() + "</option>");
                                      else
                                        out.print("<option value=\"" + new Integer(VALMAlmacen.getICveAlmacen()).toString() + "\">"  + VALMAlmacen.getCDscAlmacen().toString() + "</option>");
                                    }
                                  }
                              %>
                              </select>
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Área</td>
                              <td class="ETablaT">Sección</td>
                              <td class="ETablaT">Selección</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   vUbicacion = clsConfig.vUbicacion;
                                   String ivArticulo = "";
                                   if (request.getParameter("hdIni") != null)
                                      ivArticulo = request.getParameter("hdIni").toString();
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cObservacion", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscSeccion", "&nbsp;").toString()));
                                     if (!vUbicacion.isEmpty()){
                                       for (int i=0;i<vUbicacion.size();i++){
                                          TVALMUbicacion VALMUbicacion = new TVALMUbicacion();
                                          VALMUbicacion = (TVALMUbicacion) vUbicacion.get(i);
                                          if (bs.getFieldValue("iCveArea", "&nbsp;").toString().compareToIgnoreCase(new Integer(VALMUbicacion.getiCveArea()).toString()) == 0 &&
                                              bs.getFieldValue("iCveSeccion", "&nbsp;").toString().compareToIgnoreCase(new Integer(VALMUbicacion.getiCveSeccion()).toString()) == 0 &&
                                              ivArticulo.compareToIgnoreCase(new Integer(VALMUbicacion.getiCveArticulo()).toString()) == 0){
                                            out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveArea", "&nbsp;").toString() + "-" +  bs.getFieldValue("iCveSeccion", "&nbsp;").toString(),"1" ,"", 59, true, "Sí", "No", true));
                                            lPinto = true;
                                          }
                                       }
                                     }
                                     if (!lPinto)
                                       out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveArea", "&nbsp;").toString() + "-" +  bs.getFieldValue("iCveSeccion", "&nbsp;").toString(),"0" ,"", 59, true, "Sí", "No", true));
                                     if (lPinto)
                                       out.print("<input type=\"hidden\" name=\"TBXHid-" + bs.getFieldValue("iCveArea", "&nbsp;").toString() + "-" +  bs.getFieldValue("iCveSeccion", "&nbsp;").toString() + "\" value=\"1\">");
                                   }
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
