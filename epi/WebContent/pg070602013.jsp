<%/**
 * Title: Consulta de Mantenimientos
 * Description: Consulta de Mantenimientos por Equipo
 * Copyright: Micros
 * Company: Micros
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070602013CFG
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>

<html>
<%
  pg070602013CFG  clsConfig = new pg070602013CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070602013.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070602013.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070603021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave del Mantenimiento|Fecha de Solicitud|Fecha Programada|";    // modificar
  String cCveOrdenar  = "EqmMantenimiento.iCveMantenimiento|EqmMantenimiento.dtSolicitud|EqmMantenimiento.dtProgramado|";  // modificar
  String cDscFiltrar  = "Clave del Mantenimiento|Fecha de Solicitud|Fecha Programada|";    // modificar
  String cCveFiltrar  = "EqmMantenimiento.iCveMantenimiento|EqmMantenimiento.dtSolicitud|EqmMantenimiento.dtProgramado|";  // modificar
  String cTipoFiltrar = "7|5|5|";                // modificar
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
  String cClave    = "";
  String cPosicion = "";
  int ivEquipo = 0;
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  function fDetalles(iCveMan, pag) {
      var form = document.forms[0];
      form.iCveMantenimiento.value = iCveMan;
      form.target = 'FRMDatos';
      form.action = pag;
      form.submit();
  }
  function fSeguimiento(iCveEqu,iCveMan, pag) {
      var form = document.forms[0];
      form.hdIni.value = iCveEqu;
      form.iCveEquipo.value = iCveEqu;
      form.hdIni2.value = iCveMan;
      form.iCveMantenimiento.value = iCveMan;
      form.target = 'FRMDatos';
      form.action = pag;
      form.submit();
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
  <input type="hidden" name="iCveEquipo" value="<%=request.getParameter("iCveEquipo")%>">
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("iCveEquipo")%>">
  <input type="hidden" name="hdListado" value="S">
  <input type="hidden" name="iCveMantenimiento" value="">
  <input type="hidden" name="iCveClasificacion" value="<%=request.getParameter("iCveClasificacion") %>">
  <input type="hidden" name="iCveTpoEquipo" value="<%=request.getParameter("iCveTpoEquipo") %>">
  <input type="hidden" name="hdIni" value="">
  <input type="hidden" name="hdIni2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="7" class="ETablaT">Equipo
<%                                Vector vEq = clsConfig.vEquipo;
                                  if (vEq.size() > 0) {
                                      TVEQMEquipo tvEquipo = (TVEQMEquipo) vEq.get(0);
                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Clasificación"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscBreveClasif(),2));
                                      out.println(vEti.Texto("EEtiqueta", "Tipo"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscBreveTpoEquipo(),3));
                                      out.println("</tr><tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Equipo"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscEquipo(),2));
                                      out.println(vEti.Texto("EEtiqueta", "Marca"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscBreveMarca(),3));
                                      out.println("</tr><tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Modelo"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCModelo(),2));
                                      out.println(vEti.Texto("EEtiqueta", "Serie"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCNumSerie(),3));
                                      out.println("</tr><tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Inventario"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCInventario(),6));
                                      out.println("</tr>");
                                      ivEquipo = tvEquipo.getICveEquipo();
                                  }
%>
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Mantenimiento</td>
                              <td class="ETablaT">Solicitado</td>
                              <td class="ETablaT">Tipo de Mantenimiento</td>
                              <td class="ETablaT">Causa</td>
                              <td class="ETablaT" colspan="3">Programado</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   TVEQMMantenimiento tvMantto = new TVEQMMantenimiento();
                                   TFechas f = new TFechas();
                                   String fSol = "&nbsp;";
                                   String fPro = "&nbsp;";
                                   while(bs.nextRow()){
                                     tvMantto = (TVEQMMantenimiento) bs.getCurrentBean();
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveMantenimiento", "&nbsp;").toString()));
                                     if (bs.getFieldValue("dtSolicitud") != null && !bs.getFieldValue("dtSolicitud").equals(""))
                                        fSol = f.getFechaDDMMYYYY(tvMantto.getDtSolicitud(), "/");
                                     out.print(vEti.Texto("ECampo", fSol));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscTpoMantto", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscMotivo", "&nbsp;").toString()));
                                     if (bs.getFieldValue("dtProgramado") != null && !bs.getFieldValue("dtProgramado").equals(""))
                                        fPro = f.getFechaDDMMYYYY(tvMantto.getDtProgramado(), "/");
                                     out.print(vEti.Texto("ECampo", fPro));
                                     if (clsConfig.getLPagina("pg070603021.jsp"))
                                        out.print("<td><a href=\"JavaScript:fDetalles(" + bs.getFieldValue("iCveMantenimiento") + ", 'pg070603021.jsp');\">Detalle</a></td>");
                                     if (clsConfig.getLPagina("pg070603032.jsp"))
                                        out.print("<td><a href=\"JavaScript:fSeguimiento("  + ivEquipo + "," + bs.getFieldValue("iCveMantenimiento") + ", 'pg070603032.jsp');\">Seguimiento</a></td>");
                                     out.print("</tr>");
                                   }
                               } else  {
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
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