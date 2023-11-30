<%/**
 * Title:       Listado de Cortes por Sustancias Sicotropicas
 * Description: Listado de Cortes por Sustancias Sicotropicas
 * Copyright:   2004
 * Company:     Macros Personales
 * @author      Marco Antonio Hernández García
 * @version     1
 * Clase:       pg070301310.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.TDTOXSustancia" %>
<%@ page import="gob.sct.medprev.vo.TVTOXSustancia" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>

<html>
<%
  pg070301310CFG  clsConfig = new pg070301310CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070301310.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301310.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070301221.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Corte|";    // modificar
  String cCveOrdenar  = "iCveCorte|";  // modificar
  String cDscFiltrar  = "Corte|";    // modificar
  String cCveFiltrar  = "iCveCorte|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave        = "";
  String iCveSustancia = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg070301310.js"></SCRIPT>-->
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
       cPosicion     = Integer.toString(bs.pageNo());
       cClave        = bs.getFieldValue("iCveCorte").toString();
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
      <input type="hidden" name="hdBoton" value="">
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Sustancias Sicotropicas</td>
                            </tr>
                            <tr>
                              <td colspan="8" class="EEtiqueta">
                              <%
                                  TDTOXSustancia dTOXSustancia = new TDTOXSustancia();
                                  TVTOXSustancia vTOXSustancia = new TVTOXSustancia();
                                  Vector VSustancia = new Vector();
                                  String cFiltro = "";
                                  String cOrden = "";
                                  cFiltro = " WHERE lActivo = 1 ";
                                  cOrden  = " ORDER BY iCveSustancia ";
                                  VSustancia = dTOXSustancia.FindByAll(cFiltro,cOrden);
                                  iCveSustancia = "";
                                  if (request.getParameter("iCveSustancia")!=null && request.getParameter("iCveSustancia").compareTo("")!=0)
                                  iCveSustancia = request.getParameter("iCveSustancia");
                                  out.print(vEti.SelectOneRowSinTD("iCveSustancia","",VSustancia,"iCveSustancia","cDscSustancia",request,iCveSustancia,true));
                              %>
                              </td>
                            </tr>
                          </table>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Configuración del Corte por Sustancia Sicotropica
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Corte</td>
                              <td class="ETablaT">Activo</td>
                              <!--<td class="ETablaT">Negativo</td>-->
                              <td class="ETablaT">Límite de Corte</td>
                              <td class="ETablaT">Positivo</td>
                              <td class="ETablaT" colspan="2">Margen de Error</td>
                            </tr>
                            <%
                               TEtiCampo Eti = new TEtiCampo();
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       //out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscSustancia", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveCorte", "&nbsp;")));
                                       out.println(Eti.ToggleMov("ETablaC","lActivo",bs.getFieldValue("lActivo", "0").toString(),"",1,false,"Sí", "No", false));
                                       //out.println(Eti.Texto("ETablaR",bs.getFieldValue("dCorteNeg", "0").toString()));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dCorte", "0")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dCortePost", "0")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dMargenError", "0")));
                                       if (clsConfig.getLPagina(cCatalogo)){
                                           out.print("<td class=\"ETablaC\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" +  bs.getFieldValue("iCveCorte","") + "'," + "'pg070301310');","Ir a..."));
                                           out.print("</td>");
                                       }
                                   }
                               }
                               else{
                                  out.println("<tr>");
                                  out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 8, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
