<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Tecnología Inred S.A. de C.V.
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 * Clase:        JSP para solicitar Usuario y Contraseña, colocando cookie y dirigiendo al menú
 */ %>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*"%>
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
<%  // Unica Parte del Código que debe modificarse
  pg0700010CFG  clsConfig = new pg0700010CFG();

  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  clsConfig.runConfig(vEntorno);
  clsConfig.outputHeader(vEntorno, vErrores, pageContext, request, response, vParametros);
  NumberFormat nf = NumberFormat.getNumberInstance();
  nf.setMinimumIntegerDigits(2);
  nf.setMaximumIntegerDigits(2);
  nf.setGroupingUsed(false);
  String cNumModulo = nf.format(vEntorno.getNumModulo());
%>
<% out.println(new TCreaFunGral().getResultado() + "\n");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<script language="JavaScript">function fAccesos(){}</script>
<frameset framespacing="0" border="false" cols="100%,0%" frameborder="0">
  <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%=cNumModulo%>00008.jsp" scrolling="no" name="FRMBusqueda" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
  <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%=cNumModulo%>00011.jsp" scrolling="no" name="FRMBotones" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
  <noframes>
  <body topmargin="0" leftmargin="0">
  <p>Su navegador no soporta el uso de frames, favor de obtener una versión mas reciente</p>
  </body>
  </noframes>
</frameset>
</html>
