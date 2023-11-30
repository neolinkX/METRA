<%/**
 * Title:        Insertable Biométricos
 * Description:  Archivo
 * Copyright:    Copyright (c) 2007
 * Company:      Secretaría de Comunicaciones y Transportes
 * @author       Ing. Arturo Ehecatl Robles Maloof
 * @version 1.2
 * Clase:        Insertable Biométricos
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
    TEntorno    vEntorno      = new TEntorno();
    vEntorno.setNumModulo(07);
    TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
    vEntorno.setArchFuncs("FValida");
    vEntorno.setArchTooltips("07_Tooltips");
    vEntorno.clearListaImgs();
    vEntorno.setMetodoForm("POST");
    vEntorno.setActionForm("pg0700009.jsp");
    vEntorno.setNombrePagina("pg0700009.jsp");
    vEntorno.setOnLoad("fOnLoad();");
    vEntorno.setArchFCatalogo("FFiltro");
    vEntorno.setArchAyuda(vEntorno.getNombrePagina());

    String cRutaImg = vParametros.getPropEspecifica("RutaImg");
    String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
    String cRutaAyuda = vParametros.getPropEspecifica("html");

    ACMCFG.setACMCFG("07");
    
    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
    int user = vUsuario.getICveusuario();

%>
<script language="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cPagina = '<%=vEntorno.getNumModuloStr()%>';
  var cAyuda = '<%=vEntorno.getArchAyuda()%>';
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700009.js"></SCRIPT>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body onLoad="document.forma1.submit();" bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form action="http://app.sct.gob.mx/elic/pgCapturaBio.jsp" target="_new" name="forma1" Method="POST">
<input type="hidden" name="iCve" id="iCve" value="3764" >
<input type="hidden" name="iCveMP" id="iCveMP" value="<%=user%>" >
</form>

<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdBoton" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>relleno2.jpg">
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <!--<tr>
            <td width="50%" align="right" valign="bottom">
                  <img SRC="<%=cRutaImg%>inicio01.jpg">
            </td>
            <td valign="middle" rowspan="2">
                  <img SRC="<%=cRutaImg%>inicio03.jpg">
            </td>
          </tr>-->
          <tr>
            <td  align="right" valign="top">
                  <img SRC="<%=cRutaImg%>inicio02.jpg">
            </td>
          </tr>
          <tr>
            <td align="center" colspan="2" valign="top">
              <marquee class="MRQ" align="center" scrollamount="1" scrolldelay="10" direction="up" width="600" height="125">
                MENSAJES   DE   LA   D.G.P.M.P.T.<br><br><%= vParametros.getPropEspecifica("msginicio").toUpperCase() %>
              </marquee>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
<%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
</html>
