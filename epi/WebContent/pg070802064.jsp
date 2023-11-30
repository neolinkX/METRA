<%/**
 * Title: Movimientos por Artìculo
 * Description: Consulta de los Movimientos por Articulo
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070802064CFG
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
<%@ page import="com.micper.util.*" %>

<html>
<%
  pg070802064CFG  clsConfig = new pg070802064CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802064.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802064.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802060.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Movimiento|Tipo de Movimiento|Concepto|";    // modificar
  String cCveOrdenar  = "M.iCveMovimiento|T.cDscBreve|C.cDscConcepto|";  // modificar
  String cDscFiltrar  = "Movimiento|Tipo de Movimiento|Concepto|";    // modificar
  String cCveFiltrar  = "M.iCveMovimiento|T.cDscBreve|C.cDscConcepto|";  // modificar
  String cTipoFiltrar = "7|8";                // modificar
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
  Vector vArticulo = new Vector();
  TVALMArticulo tvArt = new TVALMArticulo();

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
  <input type="hidden" name="oldFamilia" value="<%=request.getParameter("SLSFamilia")%>">
  <input type="hidden" name="hdMov" value="S">
  <input type="hidden" name="hdIni" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda de los Artículos</td>
           </tr><tr>
             <td class="EEtiqueta">Almacén:</td>
             <td class="ECampo">
                <select name="SLSAlmacen" size="1" onChange="llenaSLT(1,document.forms[0].SLSAlmacen.value,'','',document.forms[0].SLSTipoArticulo,document.forms[0].SLSFamilia,document.forms[0].iCveArticulo);">
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
                <select name="SLSTipoArticulo" size="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,document.forms[0].iCveArticulo,'');">
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
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos
// Tabla de Articulos
%>
                            <tr>
                              <td colspan="6" class="ETablaT">Artículo</td>
                            </tr>
                            <tr>
<%
                              tvArt = clsConfig.tvArticulo;
                              vArticulo = clsConfig.vArticulos;
                              // Si el vector no tiene datos
                              if (vArticulo.size() > 0) {
                                  out.println(vEti.Texto("EEtiqueta", "No. Artículo:"));
                                  out.println(vEti.Texto("ECampo", "" + tvArt.getiCveArticulo()));
                                  out.println(vEti.Texto("EEtiqueta", "Artículo:"));
                                  out.println(vEti.SelectOneRow("EEtiqueta", "iCveArticulo", "fCambiaArt();", vArticulo, "iCveArticulo", "cDscBreve", request, "0"));
                                  out.println(vEti.Texto("EEtiqueta", "Clave:"));
                                  out.println(vEti.Texto("ECampo", tvArt.getcCveArticulo()));
                              } else {
                                  out.println(vEti.Texto("EEtiqueta", "No. Artículo:"));
                                  out.println(vEti.Texto("ECampo", "&nbsp;&nbsp;&nbsp;&nbsp;"));
                                  out.println(vEti.Texto("EEtiqueta", "Artículo:"));
                                  out.println("<td><select name=\"iCveArticulo\" size=\"1\"><option value=\"0\">Seleccione...</option></select>");
                                  out.println(vEti.Texto("EEtiqueta", "Clave:"));
                                  out.println(vEti.Texto("ECampo", "&nbsp;&nbsp;&nbsp;&nbsp;"));
                              }

%>                          </tr>
                          </table>
     </td>
   <tr>
     <td>
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="7" class="ETablaT">Movimientos por Artículo</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">No. Movimiento</td>
                              <td class="ETablaT">Tipo</td>
                              <td class="ETablaT">Concepto</td>
                              <td class="ETablaT">Unidades</td>
                              <td class="ETablaT">Usuario</td>
                              <td class="ETablaT">Fecha</td>
                              <td class="ETablaT">Observaciones</td>
                            </tr>
                             <% // modificar según listado
                               vAlmxArt = clsConfig.vAlmxArt;
                               if (bs != null){
                                   TFechas f = new TFechas();
                                   DecimalFormat df = new DecimalFormat("##,###,##0.00");
                                   bs.start();
                                   while(bs.nextRow()){
                                      TVALMMovimiento x = (TVALMMovimiento) bs.getCurrentBean();
                                      String unid = "&nbsp;";
                                      out.print("<tr>");
                                         out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveMovimiento", "&nbsp;").toString()));
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscBreve", "&nbsp;").toString()));
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscConcepto", "&nbsp;").toString()));
                                         if (bs.getFieldValue("dUnidades") != null && !bs.getFieldValue("dUnidades").equals(""))
                                             unid = df.format(Double.parseDouble(bs.getFieldValue("dUnidades").toString()));
                                         out.print(vEti.Texto("ETablaR", unid));
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("cUsuario", "&nbsp;").toString()));
                                         out.print(vEti.Texto("ECampo", f.getFechaDDMMYYYY(x.getdtMovimiento(), "/")));
                                         out.print("<td class=\"ECampo\"><textarea name=\"cObservacion\" cols=\"50\" rows=\"4\" readonly>" + bs.getFieldValue("cObservacion", "&nbsp;") + "</textarea></td>");
                                         out.print("</tr>");
                                 }
                               } else {
                                   out.println("<tr>");
                                   out.print(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class=\"ECampo\" colspan=\"6\">No existen datos coincidentes con el filtro proporcionado</td>");
                                   out.println("</tr>");
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
