<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>    
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>

<%  
TEntorno    vEntorno10      = new TEntorno();
vEntorno10.setNumModulo(07);
TParametro  vParametros10 = new TParametro(vEntorno10.getNumModuloStr());
TVUsuario vUsuario10 = (TVUsuario) request.getSession(true).getAttribute("UsrID");
%>
<html>
<head>
<meta name="viewport" content="width=device-width, minimum-scale=0.1">
<title>timthumb (300×328)</title>
<script type="text/javascript" async=""
	src="//s3.amazonaws.com/js-cache/bc2556d1c1ea2a2d00.js"></script>
</head>
<body style="margin: 0px; background: rgb(14, 14, 14);">
	<img id="avatar_top2"
		style="-webkit-user-select: none; background-position: 0px 0px, 10px 10px; background-size: 20px 20px; background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee 100%), linear-gradient(45deg, #eee 25%, white 25%, white 75%, #eee 75%, #eee 100%);"
		src="<%=vParametros10.getPropEspecifica("RutaNASAvatarURL")%><%=vUsuario10.getICveusuario()%>.jpg">
</body>
</html>