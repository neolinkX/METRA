<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070106040CFG  clsConfig = new pg070106040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cDetalle    = "pg070106041.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Expediente|";    // modificar
  String cCveOrdenar  = "iCveExpediente|";  // modificar
  String cDscFiltrar  = "Expediente|";    // modificar
  String cCveFiltrar  = "iCveExpediente|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
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
  String cClave2    = "";
  String cPosicion = "";

  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  TFechas dtFecha = new TFechas();
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
  TDEMOExamen dEMOExamen = new TDEMOExamen();
  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">


  function fSelectedPer(iCvePersonal,iCveExpediente,iNumExamen){
    var form = document.forms[0];
    form.iCvePersonal.value = iCvePersonal;
    form.lMostrarDatos.value = true;
    form.target = "_self";
    form.submit();
  }

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
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCampoClave3" value="">
  <input type="hidden" name="hdCampoClave4" value="">
  <input type="hidden" name="hdMarca" value="<%out.print(request.getParameter("iCveMarca"));%>">
  <input type="hidden" name="iCvePersonal" value="">
  <input type="hidden" name="lMostrarDatos" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="10" class="ETablaT">Consulta de No Aptos
                              </td>
                            </tr>
                          <tr>
                            <%
                              out.print(vEti.EtiCampoCS("EEtiqueta", "Periodo de:", "ECampo", "text", 10, 10,1,"dtFechaDe", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                              out.print(vEti.EtiCampoCS("EEtiqueta", "a:", "ECampo", "text", 10, 10,1,"dtFechaA", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                              if(request.getParameter("lVigente")!=null && request.getParameter("lVigente").toString().compareTo("on")==0)
                                out.print("<td class='EEtiqueta'>Vigente:</td><td colspan='3'><input type='checkbox' name='lVigente' checked></td>");
                              else
                                out.print("<td class='EEtiqueta'>Vigente:</td><td colspan='5'><input type='checkbox' name='lVigente'></td>");

                               out.print("<tr>");
                               out.print("<td class='ETablaC' colspan='10'>");
                               out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer();", "Buscar Personal",""));
                               out.print("</td>");
                               out.print("</tr>");

                            %>
                          </tr>
                            <%
                              // modificar según listado 
                               if (bs != null){
                                   boolean lDetalle = clsConfig.getLPagina(cDetalle);
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Expediente</td>");
                                   out.print("<td class=\"ETablaT\">Nombre</td>");
                                   out.print("<td class=\"ETablaT\">Solicitado</td>");
                                   out.print("<td class=\"ETablaT\">Dictaminado</td>");
                                   out.print("<td class=\"ETablaT\">Periodo</td>");
                                   out.print("<td class=\"ETablaT\">Motivo</td>");
                                   out.print("<td class=\"ETablaT\">Mdo. Transporte</td>");
                                   out.print("<td class=\"ETablaT\">Categoría</td>");
                                   out.print("<td class=\"ETablaT\">Unidad Médica</td>");
                                   out.print("<td class=\"ETablaT\">Diagnóstico</td>");
                                   out.print("</tr>");
                                   bs.start();
                                   while(bs.nextRow()){
                                           out.print("<tr>");
                                           out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCvePersonal", "&nbsp;").toString()));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cNombre", "&nbsp;") + " " + bs.getFieldValue("cApPaterno", "&nbsp;") + " " + bs.getFieldValue("cApMaterno", "&nbsp;")));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDtInicio", "&nbsp;").toString()));
                                           if (bs.getFieldValue("dtFin", "&nbsp;").toString().compareTo("null") != 0) {
                                               out.print(vEti.Texto("ETabla", bs.getFieldValue("cDtFin").toString()));
                                           } else {
                                               out.print(vEti.Texto("ETabla", "&nbsp;"));
                                           }
                                           out.print(vEti.Texto("ETablaR", bs.getFieldValue("iPeriodo", "&nbsp;").toString()));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMotivo", "&nbsp;").toString()));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMdoTrans", "&nbsp;").toString()));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscCategoria", "&nbsp;").toString()));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscRubroNoAp", "&nbsp;").toString()));
                                           out.print("</tr>");
                                   }
                               }
                               else{
                                   out.print("<tr><td class='ETablaC' colspan='8'>No existen datos coincidentes con el filtro proporcionado.</td></tr>");
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
