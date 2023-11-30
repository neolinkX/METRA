<%/**
 * Title:       Agregar Articulos a la Solicitud
 * Description: Listado para agregar artículos a la Solicitud
 * Copyright:   2004
 * Company:     Micros Personles
 * @author      Marco Antonio Hernández García
 * @version     1.0
 * Clase:       pg070803012.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070803012CFG  clsConfig = new pg070803012CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070803012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  String cCatalogo    = "pg0702061.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Articulo|Familia|";    // modificar
  String cCveOrdenar  = "ALMArticulo.iCveArticulo|ALMArticulo.iCveFamilia|";  // modificar
  String cDscFiltrar  = "Articulo|Familia|";    // modificar
  String cCveFiltrar  = "ALMArticulo.iCveArticulo|ALMArticulo.iCveFamilia|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";            // modificar
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
  String cUpdStatus  = "SaveOnly";

  TDALMSolicSumin   dALMSolicSumin  =  new TDALMSolicSumin();
  TVALMSolicSumin   vALMSolicSumin  =  new TVALMSolicSumin();
  Vector vSoliSumin =  new Vector();
  String cCondicion = "";
  int lAutorizada = 0;
  if (request.getParameter("iAnio") != null && request.getParameter("iCveAlmacen") != null &&
     request.getParameter("iCveSolicSum") != null ){
     cCondicion = "";
     if (request.getParameter("iAnio").length() > 0)
       cCondicion = " WHERE ALMSolicSumin.iAnio        = " + request.getParameter("iAnio");
     if (request.getParameter("iCveAlmacen").length() > 0)
       if (cCondicion.length() > 0)
         cCondicion += "   AND ALMSolicSumin.iCveAlmacen  = " + request.getParameter("iCveAlmacen");
       else
         cCondicion += " WHERE ALMSolicSumin.iCveAlmacen  = " + request.getParameter("iCveAlmacen");
     if (request.getParameter("iCveSolicSum").length() > 0)
       if (cCondicion.length() > 0)
         cCondicion += "   AND ALMSolicSumin.iCveSolicSum = " + request.getParameter("iCveSolicSum");
       else
         cCondicion = " WHERE  ALMSolicSumin.iCveSolicSum = " + request.getParameter("iCveSolicSum");
     vSoliSumin = dALMSolicSumin.FindByAll(cCondicion,"");
     if (vSoliSumin.size()>0){
        vALMSolicSumin  =  (TVALMSolicSumin)vSoliSumin.get(0);
     }
     lAutorizada = vALMSolicSumin.getLAutorizada();
  }

  if (lAutorizada == 1)
     cUpdStatus  = "Hidden";

  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Si";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  DecimalFormat df = new DecimalFormat("##,###,##0.00");
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
  <input type="hidden" name="iAnio" value="<% if (request.getParameter("iAnio") != null) out.print(request.getParameter("iAnio"));%>">
  <input type="hidden" name="iCveAlmacen" value="<% if (request.getParameter("iCveAlmacen") != null) out.print(request.getParameter("iCveAlmacen"));%>">
  <input type="hidden" name="iCveSolicSum" value="<% if (request.getParameter("iCveSolicSum") != null) out.print(request.getParameter("iCveSolicSum"));%>">
  <input type="hidden" name="iCveTpoArticulo" value="<% if (request.getParameter("iCveTpoArticulo") != null) out.print(request.getParameter("iCveTpoArticulo"));%>">
  <input type="hidden" name="iCveFamilia" value="<% if (request.getParameter("iCveFamilia") != null) out.print(request.getParameter("iCveFamilia"));%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
        if(request.getParameter("hdBoton").toString().compareTo("Generar Reporte")==0) {
           out.println(clsConfig.getActiveX());
        }
 %>
  <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="4">Resumen de la Solicitud</td>
            </tr>
             <tr>
              <td class="EEtiqueta">Solicitud:</td>
              <td class="EEtiqueta"><%out.print(request.getParameter("iCveSolicSum"));%></td>
             </tr>
          </table>
          &nbsp;

          <%
               int iFamilia = 0;
               DAOGRLMetaProceso dGRLMetaProceso = new DAOGRLMetaProceso();
               TVGRLMetaProceso  vGRLMetaProceso = new TVGRLMetaProceso();
               Vector     vMetaProceso    = new  Vector();
               int iAnio = 0;
               if (request.getParameter("iAnio")!=null && request.getParameter("iAnio").toString().compareTo("")!=0)
                  iAnio = new Integer(request.getParameter("iAnio").toString()).intValue();
               String cFiltro = " where iAnio = " + iAnio + " ";
               vMetaProceso = dGRLMetaProceso.FindByAll(cFiltro);
          if (bs != null){
          %>
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="7">Articulos</td>
            </tr>
            <tr>
              <td class="ETablaT">Clave</td>
              <td class="ETablaT">cClave</td>
              <td class="ETablaT">Descripción</td>
              <td class="ETablaT">Unidad</td>
              <td class="ETablaT">Cantidad</td>
              <td class="ETablaT">Meta</td>
<%
              if (lAutorizada != 1){
                     out.println("<td class=\"ETablaT\">Eliminar</td>");
              }
%>
            </tr>
            <%     String cValor = "";
                   bs.start();
                   while(bs.nextRow()){
                       if (iFamilia!=new Integer(bs.getFieldValue("iCveFamilia", "0").toString()).intValue()){
                          iFamilia = new Integer(bs.getFieldValue("iCveFamilia", "0").toString()).intValue();
                          out.println("<tr>");
                          out.println("<td class=\"ETablaT\" colspan=\"7\">"+bs.getFieldValue("cDscFamilia", "&nbsp;").toString()+"</td>");
                          out.println("</tr>");
                       }
                       out.println("<tr>");
                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveArticulo", "&nbsp;")));
                       out.print("<input type=\"hidden\" name=\"iCveArticulo\" value=\""+bs.getFieldValue("iCveArticulo", "0")+"\">");
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cCveArticulo", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscBreve", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscUniSum", "&nbsp;")));

                       if (bs.getFieldValue("dUnidSolicita")!=null && bs.getFieldValue("dUnidSolicita").toString().compareTo("")!=0)
                          cValor = df.format(Double.parseDouble(bs.getFieldValue("dUnidSolicita","0").toString()));
                       else
                          cValor = "&nbsp;";

                       out.print(vEti.Texto("ETablaR",cValor));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMeta", "&nbsp;")));
                       if (lAutorizada != 1){
                          out.println("<td align=\"center\">");
                          out.println("<input type=\"checkbox\" name=\"lEliminar"+bs.getFieldValue("iCveArticulo", "0").toString()+"\" value=\"1\">");
                          out.println("</td>");
                       }
                   }
            %>
          </table>
          &nbsp;
          <%  if (lAutorizada != 1){ %>
          <table border="1" class="ETablaInfo" align="center">
            <tr>
              <td><% out.print(vEti.clsAnclaTexto("EAncla","Autorizar","JavaScript:fAccion('Autorizar');", "Autorizar")); %>  </td>
            </tr>
          </table>
          <% }  %>
          <%}else{%>
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT">No existen Articulos a ser Autorizados!</td>
              </td>
            </tr>
          </table>
          <%}%>
      </td>
  </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

