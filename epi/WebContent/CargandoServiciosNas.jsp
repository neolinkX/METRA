<%/**
 * Copyright: 2014
 * @author AG SA
 
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.cntmgr.placas.*"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cargar Servicios desde la NAS</title>
<style type="text/css">
body,td,th {
	color: #FFF;
}
body {
	background-color: #000;
}
</style>
</head>
<%
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
%>
<body>
<table width="800" border="1" align="center">
  <tr>
  <th scope="row">
      <img src="<%=vParametros.getPropEspecifica("RutaImgServer")%>serviciosmedicos.jpg" width="336" height="386"> </th>
    <th width="550" scope="row">
    <%
    //System.out.println(request.getParameter("cargando"));
    String msg = "";
    String desde = request.getParameter("desde");
	String hasta = request.getParameter("hasta"); 
    if(request.getParameter("cargando")==null){%>
		 Cargar Servicios desde la NAS
	    <form action="CargandoServiciosNas.jsp" method="post">
	    	<p>
    		<input name="cargando" type="hidden" value="1" />
    		Desde
    		<input name="desde" type="text" value="" size="10" maxlength="10" />
    		</p>
		    <p><br />
			Hasta
			<input name="hasta" type="text" value="" size="10" maxlength="10" />
            </p>
    		<p><br />
    		<input name="Cargar Servicios" type="submit" value="cargar" />
    		</p>
    	</form>
	<%
    }else{
    	//AdministradorContenidos subir = new AdministradorContenidos();
    	//System.out.println("Desde = "+desde + " / Hasta = "+ hasta);
	    	AdministradorContenidosArchivoLog cargarlog = new AdministradorContenidosArchivoLog();
	    	try{
	    		//subir.CargaDeServiciosDesdeNas(desde, hasta);
	    		cargarlog.LeerLineasDeArchivo();
	    		msg="La carga fue exitosa";
	    	}catch(Exception ex){
	    		System.out.println("error al realizar la carga\n"+ex);
	    		msg="La carga tuvo errores";
	    	}
        	out.println(msg);
    } %>
    </th>
  </tr>
</table>
</body>

</html>