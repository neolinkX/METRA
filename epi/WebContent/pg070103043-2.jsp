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


  <!--  BOOTASTRAP -->
    <meta name="description" content="Framedev -  Framework para desarrolladores">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="http://10.33.143.52:7001/epi/assets/app/js/webfont.js"></script>
<script>
    WebFont.load({
       //google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       google: {"families":["Montserrat:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       active: function() {
           sessionStorage.fonts = true;
       }
     });
</script>
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/demo/default/base/menu_alter.css" rel="stylesheet" type="text/css" />

<!--JQuery-->
<script src="http://10.33.143.52:7001/epi/assets/plugins/jquery-3.2.1.min.js"></script>

<!--Plugins JQuery-->
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.min.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />

<!--APP-->
<link href="http://10.33.143.52:7001/epi/css/app.css" rel="stylesheet" type="text/css">

<!--calendar-->
<link href="http://10.33.143.52:7001/epi/assets/vendors/custom/jquery-ui/jquery-ui.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/vendors/custom/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css" />

<!--iconos-->
<link href="http://10.33.143.52:7001/epi/css/svg.css" rel="stylesheet" type="text/css">

<link href="http://10.33.143.52:7001/epi/css/loader.css" rel="stylesheet" type="text/css" />
<script src="http://10.33.143.52:7001/epi/assets/js/loader.js"></script>

<!--jquery steps-->
<link href="http://10.33.143.52:7001/epi/css/jquery.steps.css" rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="http://10.33.143.52:7001/epi/img/favicon.ico">
<!--  FIN BOOTASTRAP -->




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
            <tr align="center">
            <td class="ETablaT">Foto</td>
            <td class="ETablaT">Huella</td>
            <td class="ETablaT">Firma</td>
        </tr>
        <%if(imgs.getNumExpediente()>0){%>
        <tr bgcolor="#FFFFFF" align="center">
            <!--<td class="ETablaT" align="center"><img src="pg070103044.jsp?index=0" border="0" width="130" height="160"></td>-->
            <!--<td class="ETablaT" align="center"><img src="pg070103044.jsp?index=1" border="0" width="130" height="160"></td>-->
            <!--<td class="ETablaT" align="center"><img src="pg070103044.jsp?index=2" border="0" width="130" height="160"></td>-->
            
            <td class="ETablaT" align="center"><center><img src="BajarArchivoCMServlet?LINTICVEDOCUMEN=<jsp:getProperty name="imgs" property="idDoctoFoto" />&idTipoDocumento=0" border="0" width="130" height="160"></td>
            <td class="ETablaT" align="center"><center><img src="BajarArchivoCMServlet?LINTICVEDOCUMEN=<jsp:getProperty name="imgs" property="idDoctoHuella" />&idTipoDocumento=1" border="1" width="130" height="160"></td>
            <td class="ETablaT" align="center"><center><img src="BajarArchivoCMServlet?LINTICVEDOCUMEN=<jsp:getProperty name="imgs" property="idDoctoFirma" />&idTipoDocumento=2" border="1" width="130" height="160"></td>
        </tr>
        <tr align="center">
            <td class="ETablaT">Fecha de captura</td>
            <td class="ETablaT">Fecha de captura</td>
            <td class="ETablaT">Fecha de captura</td>
        </tr>
        <tr bgcolor="#FFFFFF" align="center">
            <td class="ETablaT" align="center"><center><jsp:getProperty name="imgs" property="FechaidDoctoFoto" /></td>
            <td class="ETablaT" align="center"><center><jsp:getProperty name="imgs" property="FechaidDoctoHuella" /></td>
            <td class="ETablaT" align="center"><center><jsp:getProperty name="imgs" property="FechaidDoctoFirma" /></td>
        </tr>
        <tr bgcolor="#FFFFFF" align="center">
            <td class="ETablaT" align="center" colspan="3"><p class="ETituloTSist"><br>* La captura de biométricos se realiza con fecha y hora de la Ciudad de México(GMT-5)</p></td>
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