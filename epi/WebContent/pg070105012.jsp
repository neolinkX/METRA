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
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070105012CFG  clsConfig = new pg070105012CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070105012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070105012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101011.jsp";       // modificar
  String cDetalle    = "pg070101011.jsp";       // modificar
  String cDiagnostico    = "pg070101020.jsp";       // modificar
  String cRecomendaciones    = "pg070101030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();

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

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

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
  <input type="hidden" name="FILNumReng" value="10000">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave1" value="">

  <input type="hidden" name="hdiCveExpediente" value="">
  <input type="hidden" name="hdiNumExamen" value="">
  <input type="hidden" name="hdICveServicio" value="">
  <input type="hidden" name="hdICveRama" value="">
  <input type="hidden" name="hdICveProceso" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){  %>
  <tr valign="top"><td><input type="hidden" name="hdBoton" value="">
   <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%">
  <tr>
      <td valign="top">
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
    <tr>
      <td class="ETablaT" colspan="2">Buscar Diagn&oacute;stico</td>
    </tr>
    <tr>
      <td class="ETablaST" colspan="2">Indique los Criterios de B&uacute;squeda a utilizar.</td>
    </tr>
    <tr>
      <td class="EEtiqueta">CIE 10:</td>
      <td ><input type="text" name="cCIE10" size="20" onchange="fMayus(this);"></td>
    </tr>
    <tr>
      <td class="EEtiqueta" rowspan="3" valign="top">Descripci&oacute;n:</td>
      <td class="ETabla"><input type="radio" value="Inicie" checked name="cOpDesc">Inicie con la palabra</td>
    </tr>
    <tr>
      <td class="ETabla"><input type="radio" value="Contenga" name="cOpDesc">Contenga la palabra</td>
    </tr>
    <tr>
      <td ><input type="text" name="cDscDiagnostico" size="20" onchange="fMayus(this);"></td>
    </tr>
    <tr>
      <td class="ETablaST" colspan="2" valign="top">Ordernar Por</td>
    </tr>
      <td class="ETabla" colspan="2"><input type="radio" value="cCIE" checked name="cOrden">CIE 10</td>
    </tr>
    <tr>
      <td class="ETabla" colspan="2"><input type="radio" name="cOrden" value="cDscDiagnostico">Diagn&oacute;stico</td>
    </tr>
    <tr>
      <td class="ETablaC" colspan="2">
        <%= vEti.clsAnclaTexto("EAncla"," Buscar","JavaScript:{fBuscar();}", "Buscar Diagnóstico","") %>
      </td>
    </tr>
</table>
  </td></tr>
  <tr><td class="EPie">&nbsp;</td></tr>
  <tr>
      <td valign="top" align="center">
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
    <tr>
      <td class="ETablaT" colspan="3">Resultado de la B&uacute;squeda</td>
    </tr>
<%
    if (bs != null){
      bs.start();
      out.println("<tr>");
      out.println(vEti.Texto("ETablaSTC","CIE"));
      out.println(vEti.Texto("ETablaSTC","Diagn&oacute;stico"));
      out.println(vEti.Texto("ETablaSTC","Especialidad"));
//      out.println(vEti.Texto("ETablaSTC","Seleccionar"));
      out.println("</tr>");
     while(bs.nextRow()){
      out.println("<tr>");
//      out.println(vEti.Texto("ETabla",bs.getFieldValue("cCIE","&nbsp;").toString()));
      out.println("<td class=\"ETabla\">" + vEti.clsAnclaTexto("EAncla",bs.getFieldValue("cCIE","&nbsp;").toString(),"JavaScript:{fSeleccionar('" + bs.getFieldValue("iCveEspecialidad","&nbsp;").toString() + "','" + bs.getFieldValue("iCveDiagnostico","&nbsp;").toString() + "','" + bs.getFieldValue("cDscDiagnostico","&nbsp;").toString() + "', '" + bs.getFieldValue("cCIE","&nbsp;").toString() + "');}", "Seleccionar Diagnóstico","") + "</td>");
      out.println(vEti.Texto("ETabla",bs.getFieldValue("cDscDiagnostico","&nbsp;").toString()));
      out.println(vEti.Texto("ETabla",bs.getFieldValue("cDscEspecialidad","&nbsp;").toString()));

      out.println("</tr>");
     }// Barrido de los datos
    }
    else
      out.println(vEti.TextoCS("ETablaT","No se encontraron Diagnósticos con el Filtro Proporcionado.",3));
%>
</table></td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
</table></td></tr>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
