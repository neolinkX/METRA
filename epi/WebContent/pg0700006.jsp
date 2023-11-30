<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S.A. de C.V.
 * @author       LSC. Rafael Miranda Blumenkron / LSC. Jaime Enrique Suárez Romero
 * @version      1.0
 * Clase:        Panel de Navegación 
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<% 	/**
	* Validacion de sesion iniciada
	* @author Ing. Andres Esteban Bernal Muñoz - 16/05/2014
	*/
      if(request.getSession(true).getAttribute("UsrID")==null){    	    
    	    response.sendRedirect("pg0700000.jsp");
    	    return;    	  
      } //Fin validacion %>
<html>
<%
  pg0700006CFG  clsConfig = new pg0700006CFG();

  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TEntorno    vEntorno      = new TEntorno();
  TNavPanel   NavPanel      = new TNavPanel();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg0700006.jsp");
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  clsConfig.runConfig(vEntorno, NavPanel);
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(1).toString();
  String cRutaAyuda = vParametros.getPropEspecifica("html");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
</SCRIPT>

<% new TCabeceras(pageContext).TCabeceras(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdEstado" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>FondoStitulo<%= cTipoImg %>">
    <tr>
      <td align="center" width="100%" valign="middle">
        <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%">
                                  <% if (NavPanel.getVisible() == true){ %>
                                        <TR>
                                          <TD ALIGN="CENTER" VALIGN="TOP">
                                                  <%= new TShowNavPanel(NavPanel, vEntorno, 1, vParametros).getResultado() %>
                                          </TD>
          </TR><% } %>
                                </TABLE>
      </td>
    </tr>
  </table>
</form>
</body>
<%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
</html>
