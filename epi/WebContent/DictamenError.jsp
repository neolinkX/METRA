
<%
	com.micper.ingsw.TEntorno vEntorno = new com.micper.ingsw.TEntorno();
	vEntorno.setNumModulo(07);
	com.micper.ingsw.TParametro vParametros = new com.micper.ingsw.TParametro(vEntorno.getNumModuloStr());
%>
<html>
<link rel="stylesheet" href="http://aplicaciones2.sct.gob.mx/imagenes/medprev/estilos/07_estilos.css" TYPE="text/css">
<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">

<table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<tr>
	<td>
		<table align="center" border="0">
		<tr>
			<td>
				<h1>&nbsp;<br></h1>
				<h2>No s&eacute; encontraron Datos para generar el dictamen</h2>
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="button" value="CERRAR"  onclick="window.close()">
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</body>
