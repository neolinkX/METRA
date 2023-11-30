<%/**
 * Title: Ubicacion de los articulos en Almacen
 * Description: Consulta de Ubicacion de los Articulos en el Almacen
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070802063CFG
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
  pg070802063CFG  clsConfig = new pg070802063CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802063.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802063.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802060.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Área|Área|";    // modificar
  String cCveOrdenar  = "ALMSeccion.iCveArea|ALMArea.cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave Área|Área|";    // modificar
  String cCveFiltrar  = "ALMSeccion.iCveArea|ALMArea.cDscBreve|";  // modificar
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
  String cUpdStatus  = clsConfig.getUpdStatus();
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

  <input type="hidden" name="SLSAlmacen" value="<%=request.getParameter("SLSAlmacen")%>">
  <input type="hidden" name="SLSTipoArticulo" value="<%=request.getParameter("SLSTipoArticulo")%>">
  <input type="hidden" name="SLSFamilia" value="<%=request.getParameter("SLSFamilia")%>">
  <input type="hidden" name="iCveArticulo" value="<%=request.getParameter("iCveArticulo")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda de los Artículos</td>
           </tr><tr>
             <td class="EEtiqueta">Almacén:</td>
<%               TVALMAlmacen tvAlm = clsConfig.tvAlmacen;
                 out.println(vEti.Texto("ECampo", tvAlm.getCDscAlmacen()));
%>
             <td class="EEtiqueta">Tipo de Artículo:</td>
<%               TVALMTpoArticulo tvTpoArt = clsConfig.tvTpoArticulo;
                 out.println(vEti.Texto("ECampo", tvTpoArt.getCDscBreve()));
%>
           </tr>
           <tr>
             <td class="EEtiqueta">Familia:</td>
<%               TVALMFamilia tvFam = clsConfig.tvFamilia;
                 out.println(vEti.TextoCS("ECampo", tvFam.getcDscBreve(),3));
%>
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
                              out.println(vEti.Texto("EEtiqueta", "No. Artículo:"));
                              out.println(vEti.Texto("ECampo", "" + tvArt.getiCveArticulo() + "&nbsp;"));
                              out.println(vEti.Texto("EEtiqueta", "Artículo:"));
                              out.println(vEti.Texto("ECampo", tvArt.getcDscBreve() + "&nbsp;"));
                              out.println(vEti.Texto("EEtiqueta", "Clave:"));
                              out.println(vEti.Texto("ECampo", tvArt.getcCveArticulo() + "&nbsp;"));
%>                          </tr>
                          </table>
     </td>
   <tr>
     <td>
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="3" class="ETablaT">Ubicación del Artículo en el Almacén</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave Área</td>
                              <td class="ETablaT">Área</td>
                              <td class="ETablaT">Sección</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveArea", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cObservacion", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscSeccion", "&nbsp;").toString()));
                                     out.print("</tr>");
                                   }
                               } else {
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 2, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
