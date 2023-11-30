<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.cis.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Validación de Huella Dactilar</title>
    </head>
    <%
                request.getSession(true).removeAttribute("UsrID");
                request.getSession(true).removeAttribute("MenuUsuario");
                request.getSession(true).removeAttribute("PermisosUsuario");
                request.getSession(true).removeAttribute("SelPer");
                //pageContext.forward("pg0700000.jsp");
    %>
    <script>
        function mensajeNO(){
            alert('La huella dactilar no coincide con el usuario ingresado, \n favor de ingresar nuevamente.');
            window.top.location.href  ='./pg0700000.jsp';
        }
    </script>
    <body onload="javascript://mensajeNO();">

    </body>
</html>
