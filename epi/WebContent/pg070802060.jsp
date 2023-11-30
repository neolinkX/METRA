<%/**
 * Title: Articulos por Almacen
 * Description: Consulta de Articulos por Almacen
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070802060CFG
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
  pg070802060CFG  clsConfig = new pg070802060CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802060.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802060.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802060.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Artículo|Descripción|";    // modificar
  String cCveOrdenar  = "ALMArticulo.iCveArticulo|ALMArticulo.cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave Artículo|Descripción|";    // modificar
  String cCveFiltrar  = "ALMArticulo.iCveArticulo|ALMArticulo.cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar

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
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdCampoClave4" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCampoClave3" value="">
  <input type="hidden" name="iCveArticulo" value="">
  <input type="hidden" name="hdIni" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="7" class="ETablaT">Búsqueda de los Artículos</td>
           </tr><tr>
             <td class="EEtiqueta">Almacén:</td>
             <td class="ECampo">
                <select name="SLSAlmacen" size="1" onChange="llenaSLT(1,document.forms[0].SLSAlmacen.value,'','',document.forms[0].SLSTipoArticulo,document.forms[0].SLSFamilia);">
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
             <td class="EEtiqueta">Tipo de Artículo:</td>
             <td class="ETabla">
                <select name="SLSTipoArticulo" size="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,'');">
             <%     vTpoArticulo = clsConfig.vTpoArticulos;
                    if (request.getParameter("SLSTipoArticulo") != null){
                      if (request.getParameter("SLSTipoArticulo") != "")
                          cvTpoArticulo = request.getParameter("SLSTipoArticulo");
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
             </td>
          </tr>
          <tr>
             <td class="EEtiqueta">Familia:</td>
             <td class="ECampo">
                <select name="SLSFamilia" size="1">
                <% if (request.getParameter("SLSFamilia") != null){
                      if (request.getParameter("SLSFamilia") != "")
                          cvFamilia = request.getParameter("SLSFamilia");
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
             </td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar los Artículos","Buscar", vParametros) %></td>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                             <% // modificar según listado
                               vAlmxArt = clsConfig.vAlmxArt;
                               vUbicacion = clsConfig.vUbicacion;
                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td colspan=\"9\" class=\"ETablaT\">Artículos por Almacén</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Artículos</td>");
                                   out.print("<td class=\"ETablaT\">Descripción</td>");
                                   out.print("<td class=\"ETablaT\">Máximo</td>");
                                   out.print("<td class=\"ETablaT\">Mínimo</td>");
                                   out.print("<td class=\"ETablaT\" colspan=\"5\">Existencias</td>");
                                   out.print("</tr>");

                                   bs.start();
                                   DecimalFormat df = new DecimalFormat("##,###,##0.00");
                                   String max = "&nbsp;";
                                   String min = "&nbsp;";
                                   String exist = "&nbsp;";
                                   while(bs.nextRow()){
                                      out.print("<tr>");
                                      out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveArticulo", "&nbsp;").toString()));
                                      out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscBreve", "&nbsp;").toString()));

                                      if (bs.getFieldValue("dMaximo") != "" && !bs.getFieldValue("dMaximo").equals(""))
                                         max = df.format(Double.parseDouble(bs.getFieldValue("dMaximo").toString()));
                                      out.print(vEti.Texto("ETablaR", max));

                                      if (bs.getFieldValue("dMinimo") != "" && !bs.getFieldValue("dMinimo").equals(""))
                                         min = df.format(Double.parseDouble(bs.getFieldValue("dMinimo").toString()));
                                      out.print(vEti.Texto("ETablaR", min));

                                      if (bs.getFieldValue("dExistencia") != "" && !bs.getFieldValue("dExistencia").equals(""))
                                         exist = df.format(Double.parseDouble(bs.getFieldValue("dExistencia").toString()));
                                      out.print(vEti.Texto("ETablaR", exist));
                                      // Ver si tiene permisos
                                      if (clsConfig.getLPagina("pg070802061.jsp"))
                                          out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveArticulo") + ");\">Detalle</a></td>");
                                      if (clsConfig.getLPagina("pg070802062.jsp")) {
                                          if (bs.getFieldValue("lLote").equals("1")) {
                                              out.print("<td><a href=\"JavaScript:fLote(" + bs.getFieldValue("iCveArticulo") + ");\">Lote</a></td>");
                                          } else {
                                              out.print(vEti.Texto("ECampo", "Sin Lote"));
                                          }
                                      }
                                      if (clsConfig.getLPagina("pg070802063.jsp"))
                                          out.print("<td><a href=\"JavaScript:fUbicacion(" + bs.getFieldValue("iCveArticulo") + ");\">Ubicación</a></td>");
                                      if (clsConfig.getLPagina("pg070802064.jsp"))
                                          out.print("<td><a href=\"JavaScript:fMovimiento(" + bs.getFieldValue("iCveArticulo") + ");\">Movimiento</a></td>");
                                      out.print("</tr>");
                                   }
                               } else {
                                 out.print("<tr>");
                                 out.print("<td colspan=\"5\" class=\"ETablaT\">Mensaje</td>");
                                 out.print("</tr>");
                                 out.print("<tr>");
                                 out.print("<td class='ECampo' colspan='5'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 out.print("</tr>");
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
