<%/**
 * Title: Listado de las Ramas
 * Description: Listado de las Ramas
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070101050CFG
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070101050CFG  clsConfig = new pg070101050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101051.jsp";       // modificar
  String cReordenar    = "pg070101052.jsp";       // modificar
  String cConfiguracion    = "pg070101060.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Descripcion de Rama|Orden|";    // modificar
  String cCveOrdenar  = "cDscRama|iOrden|";  // modificar
  String cDscFiltrar  = "Descripcion de Rama|Orden|";    // modificar
  String cCveFiltrar  = "cDscRama|iOrden|";  // modificar
  String cTipoFiltrar = "8|7|";                // modificar
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
  String cClave2    = "";
  String cPosicion = "";
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
       cClave = bs.getFieldValue("iCveServicio").toString();
       cClave2 = bs.getFieldValue("iCveRama").toString();
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="">
  <input type="hidden" name="hdCampoClave1" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClave2%>">
  <input type="hidden" name="vienede" value="L">
  <input type="hidden" name="iCveRama" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="6" class="ETablaT">Ramas</td>
                            </tr>
                            <tr>
<%                              TDMEDServicio dMedServ = new TDMEDServicio();
                                TVMEDServicio tvMedServ = new TVMEDServicio();
                                Vector vMedServ = new Vector();
                                vMedServ = dMedServ.FindByAll("");
                                if (request.getParameter("iCveServicio") == null || request.getParameter("iCveServicio").equals("")  || request.getParameter("iCveServicio").equals("0")) {
                                    tvMedServ.setCDscServicio("Seleccione...");
                                    tvMedServ.setICveServicio(0);
                                    vMedServ.add(tvMedServ);
                                }

                                boolean lCatalogo = clsConfig.getLPagina(cCatalogo);
                                boolean lConfiguracion = clsConfig.getLPagina(cConfiguracion);
                                boolean lReordenar = clsConfig.getLPagina(cReordenar);

                                out.print(vEti.Texto("ETablaTR", "Servicio:"));

                                if(request.getParameter("hdCampoClave")!=null && request.getParameter("hdCampoClave").compareTo("")!=0)
                                  out.print("<td class=\"ETabla\"colspan=\"3\">" + vEti.SelectOneRowSinTD("iCveServicio", "fCambiaServicio();", vMedServ, "iCveServicio", "cDscServicio", request, request.getParameter("hdCampoClave")));
                                else
                                  out.print("<td class=\"ETabla\"colspan=\"3\">" + vEti.SelectOneRowSinTD("iCveServicio", "fCambiaServicio();", vMedServ, "iCveServicio", "cDscServicio", request, "0"));
                                if(lReordenar){
                                  out.print("<td colspan=\"2\" class=\"ETablaC\">");
                                  out.print(vEti.clsAnclaTexto("EAncla","Reordenar","JavaScript:fIrReordenar();","Ir a..."));
                                  out.print("</td>");
                                }
                                out.println("</tr>");


                               if (bs != null){
                                   String valor = "";
                                   out.println("<tr>");
                                   out.println(vEti.Texto("ETablaT", "Rama"));
                                   out.println(vEti.Texto("ETablaT", "Orden"));
                                   out.println(vEti.Texto("ETablaT", "Estudio"));
                                   out.println("<td class=\"ETablaT\" colspan=\"3\">Activa</td>");
                                   out.println("</tr>");
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.println("<tr>");
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscRama", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("iOrden", "&nbsp;").toString()));

                                     if (bs.getFieldValue("lEstudio").equals("1")) { valor = "SI"; }
                                     else if (bs.getFieldValue("lEstudio").equals("0")) { valor = "NO"; }
                                     out.print(vEti.Texto("ECampo", valor));
                                     valor = "";

                                     if (bs.getFieldValue("lActivo").equals("1")) { valor = "ACTIVA"; }
                                     else if (bs.getFieldValue("lActivo").equals("0")) { valor = "INACTIVA"; }
                                     out.print(vEti.Texto("ECampo", valor));
                                     valor = "";

                                     if(lCatalogo){
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveServicio", "") + "', '" + bs.getFieldValue("iCveRama", "") + "');", "Ir a..."));
                                       out.print("</td>");
                                     }
                                     if(lConfiguracion){
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Configuración","JavaScript:fConfig('" + bs.getFieldValue("iCveRama", "") + "');", "Ir a..."));
                                       out.print("</td>");
                                     }

                                     out.println("</tr>");
                                   }
                               } else {
                                 out.println("<tr>");
                                 out.print("<td class='ETablaC' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
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