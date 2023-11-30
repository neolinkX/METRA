<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Suárez Romero
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*" %>

<html>
<%
  pg071002010CFG  clsConfig = new pg071002010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071002010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071002010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";            // modificar

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
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";

  String cSQL = "", cTitulo = "", cCampos = "";

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="">
  <input type="hidden" name="hdCampoClave" value="">
  <input type="hidden" name="hdQuery" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdNameRep" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td align="center" width="100%">
  </td></tr>
  <tr><td valign="top">
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <tr>
                <td colspan="6" class="ETablaT">Reportes Existentes:
                  <%
                    out.print(vEti.SelectOneRowSinTD("cReportes","fSelRep();",new TDGRLReportes().FindByAll(""),"cNombre","cNombre",request,"-1",true));
                    String cBoton = ""+request.getParameter("hdBoton");
                    String cRep = ""+request.getParameter("cReportes");
                    if(cBoton.equals("Selected") || ( !cRep.equals("null") && !cRep.equals("-1") ) ) {
                       String cWhere = " where cNombre = '" + request.getParameter("cReportes") + "' ";
                       Vector vcReporte = new TDGRLReportes().FindByAll(cWhere);
                       if(!vcReporte.isEmpty()){
                         TVGRLReportes vGRLReportes = (TVGRLReportes) vcReporte.get(0);
                         cSQL = vGRLReportes.getCQuery();
                         cTitulo = vGRLReportes.getCTitulo();
                         cCampos = vGRLReportes.getCCampos();
                       }
                    }
                  %>
                </td>
              </tr>

              <tr>
                <td class="EEtiquetaC">
                  <%=vEti.clsAnclaTexto("EAncla","Generar Reporte HTML","JavaScript:fShowReport('pgReporteBase.jsp',0);", "Generar Reporte HTML","")%>
                  &nbsp;
                  <%=vEti.clsAnclaTexto("EAncla","Generar Reporte EXCEL","JavaScript:fShowReport('pgReporteBase.jsp',1);", "Generar Reporte EXCEL","")%>
                  &nbsp;
                  <%=vEti.clsAnclaTexto("EAncla","Guardar Reporte","JavaScript:fSaveReport();", "Guardar Reporte","")%>
                  &nbsp;
                  <%=vEti.clsAnclaTexto("EAncla","Eliminar Reporte","JavaScript:fDelReport();", "Eliminar Reporte","")%>
                </td>
              </tr>

              <tr>
                <td colspan="6" class="ETablaT">Introducir Query
                </td>
              </tr>
              <tr>
                <td class="EEtiquetaC">
                    <textarea cols="100" rows="30" name="cSQL" ><%=cSQL%></textarea>
                </td>
              </tr>
              <tr>
                <td colspan="6" class="ETablaT">Introducir Título del Reporte
                </td>
              </tr>
              <tr>
                <td class="EEtiquetaC">
                    <input type="text" name="cTitulo" value="<%=cTitulo%>" maxlength="100" size="100" OnBlur="fMayus(this);">
                </td>
              </tr>
              <tr>
                <td colspan="6" class="ETablaT">Introducir Etiqueta de los Campos (Separados por |)
                </td>
              </tr>
              <tr>
                <td class="EEtiquetaC">
                    <textarea cols="100" rows="3" name="cCampos" OnBlur="fMayus(this);"><%=cCampos%></textarea>
                </td>
              </tr>
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
