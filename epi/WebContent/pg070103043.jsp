<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="iso-8859-1"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>


<%
  pg070103043CFG clsConfig = new pg070103043CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103043.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103043.jsp"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());


  String cCatalogo    = "pg070102031.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cEstatusIR   = "Imprimir";                  // modificar ?

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
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
  String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
%>


<jsp:useBean id="imgs" class="gob.sct.medprev.ImagenesFFH" scope="session"/>
<jsp:setProperty name="imgs" property="numExpediente" param="numExpediente"/>
<html>
<head>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<script type="text/javascript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script type="text/javascript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script type="text/javascript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"PermCombos.js"%>"></SCRIPT>
<script type="text/javascript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>','<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>',
          '<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
</head>

<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm()%>" action="pg070103043.jsp">
  <input type="hidden" name="hdBoton" value="Primero">
  <table width="100%" height="100%" border="0">
    <tr><td>&nbsp;</td></tr>
    <tr><td>&nbsp;</td></tr>
<%
   if(clsConfig.getAccesoValido()){
%>
    <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" border="0" width="50%" align="center" cellpadding="2" cellspacing="1" bgcolor="#CCCCCC">
        <tr><td class="ETablaT" colspan="3">Consulta de Biométricos</td></tr>
        <tr align="center">
            <td class="EEtiquetaC" colspan="3">Número de expediente</td>
        </tr>
        <tr bgcolor="#FFFFFF" align="center">
            <td class="ETabla" colspan="2"><input type="text" name="numExpediente" value="<jsp:getProperty name="imgs" property="numExpediente" />" maxlength="9" /></td>
            <td><input type="submit" value="Buscar" /></td>
        </tr>
        <tr align="center"><td class="EEtiquetaC" colspan="3">Nombre de la persona</td></tr>
        <tr bgcolor="#FFFFFF" align="center"><td colspan="3"><jsp:getProperty name="imgs" property="nombreCompleto" /></td></tr>
        <tr align="center">
            <td class="EEtiquetaC">Foto</td>
            <td class="EEtiquetaC">Huella</td>
            <td class="EEtiquetaC">Firma</td>
        </tr>
        <%if(imgs.getNumExpediente()>0){%>
        <tr bgcolor="#FFFFFF" align="center">
            <!--<td class="ETablaR" align="center"><img src="pg070103044.jsp?index=0" border="0" width="130" height="160"></td>-->
            <!--<td class="ETablaR" align="center"><img src="pg070103044.jsp?index=1" border="0" width="130" height="160"></td>-->
            <!--<td class="ETablaR" align="center"><img src="pg070103044.jsp?index=2" border="0" width="130" height="160"></td>-->
            
            <td class="ETablaR" align="center"><center><img src="BajarArchivoCMServlet?LINTICVEDOCUMEN=<jsp:getProperty name="imgs" property="idDoctoFoto" />&idTipoDocumento=0" border="0" width="130" height="160"></td>
            <td class="ETablaR" align="center"><center><img src="BajarArchivoCMServlet?LINTICVEDOCUMEN=<jsp:getProperty name="imgs" property="idDoctoHuella" />&idTipoDocumento=1" border="1" width="130" height="160"></td>
            <td class="ETablaR" align="center"><center><img src="BajarArchivoCMServlet?LINTICVEDOCUMEN=<jsp:getProperty name="imgs" property="idDoctoFirma" />&idTipoDocumento=2" border="1" width="130" height="160"></td>
        </tr>
        <tr align="center">
            <td class="EEtiquetaC">Fecha de captura</td>
            <td class="EEtiquetaC">Fecha de captura</td>
            <td class="EEtiquetaC">Fecha de captura</td>
        </tr>
        <tr bgcolor="#FFFFFF" align="center">
            <td class="ETablaR" align="center"><center><jsp:getProperty name="imgs" property="FechaidDoctoFoto" /></td>
            <td class="ETablaR" align="center"><center><jsp:getProperty name="imgs" property="FechaidDoctoHuella" /></td>
            <td class="ETablaR" align="center"><center><jsp:getProperty name="imgs" property="FechaidDoctoFirma" /></td>
        </tr>
        <tr bgcolor="#FFFFFF" align="center">
            <td class="ETablaR" align="center" colspan="3"><p class="ETituloTSist">* La captura de biométricos se realiza con fecha y hora de la Ciudad de México(GMT-5)</p></td>
        </tr>
        
        <%}%>
    </table>
<%}else{%>
    <script type="text/javascript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%}
    
%>
    </table>
</form>
</body>
</html>