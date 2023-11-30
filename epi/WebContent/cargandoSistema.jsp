<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="gob.sct.medprev.dwr.MedPredDwr"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.micper.util.logging.*" %>
<%@ page import="gob.sct.medprev.*" %>
<% 	/**
	* Validacion de sesion iniciada
	* @author Ing. Andres Esteban Bernal Muñoz - 16/05/2014
	*/
      if(request.getSession(true).getAttribute("UsrID")==null){    	    
    	    response.sendRedirect("pg0700000.jsp");
    	    return;    	  
      } //Fin validacion %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cargando Sistema...</title>
<style type="text/css">
body table tr td {
	color: #919294;
	font-family:Times New Roman;
}
</style>
<script>

	function fGetFile(cLocal,cURL){
	  oHTP.cArchivoLocal = cLocal;
	  oHTP.cURLArchivo = cURL;
	  oHTP.fGetFile();
	}
  function recarga(){
	  var cUrlRedirigir = '<%=""+new TParametro("07").getPropEspecifica("RutaProg")%>pg0700000.jsp';
	  top.location = cUrlRedirigir;
  }
  
 function insertaImagenes(){
	 var fso = new ActiveXObject("Scripting.FileSystemObject");
	 aplica="C:/sct/";
	 folderBool = fso.FolderExists(aplica);
	 if (!folderBool){
	 	fso.CreateFolder(aplica);
	 }
	 aplicados="C:/sct/img/";
	 //alert(aplica);
	 folderBooll = fso.FolderExists(aplicados);
	 if (!folderBooll){
	 	fso.CreateFolder(aplicados);
	 }
	 <%
	 String path = ""+new TParametro("07").getPropEspecifica("RutaDescargaImagen");//"/nas/img/medprev/";
	 //System.out.println("Path Inicial (entro a metodo existAllImages): "+ path);
	 MedPredDwr dwr = new MedPredDwr();
	 if (dwr.actualizaImagenes()==1) {
	 	//System.out.println("pase la bandera actualizaImg");
	 	File directorio = new File(path);
	 	String[] ficheros = directorio.list();
	 	//System.out.println("si existe la carpeta ");
	 	for (int i = 0; i < ficheros.length; i++) {
	 		/*BufferedReader br = new BufferedReader(new FileReader(path
	 				+ ficheros[i]));*/
	 		if (!ficheros[i].contains(".svn")) {
	 			if (ficheros[i].contains(".")){
	 				%>
	 				fileBool<%=i%> = fso.FileExists("<%="C:/sct/img/"+ficheros[i]%>");
					fGetFile("<%=""+new TParametro("07").getPropEspecifica("RutaImg") + ficheros[i]%>","<%=""+new TParametro("07").getPropEspecifica("UrlImagenes") + ficheros[i]%>");
					<%
	 			}
	 		}
	 	}
	 }
	 %>
	 recarga();
 }
 
 //recarga();
</script>
</head>
<body><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="60" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="60" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="60" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="60" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="60" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5" background="<%=""+new TParametro("07").getPropEspecifica("UrlImagenes")%>fondoCarga.png">&nbsp;</td>
  </tr>
  <tr>
    <td height="318" colspan="5" bgcolor="#FFFFFF"><div align="center">
      <p><strong>Se está actualizando el sistema, por favor espere unos minutos...</strong></p>
      <p><img src="<%=""+new TParametro("07").getPropEspecifica("UrlImagenes")%>Cargando.gif" width="32" height="32" /></p>
    </div></td>
  </tr>
  <tr>
    <td colspan="5" background="<%=""+new TParametro("07").getPropEspecifica("UrlImagenes")%>fondoCarga.png">&nbsp;</td>
  </tr>
  <tr>
    <td height="40" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="40" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
</table>
<div>
<object id="oHTP" classid="clsid:4CCB05E0-E1BB-4999-A3BB-84172549A276"
codebase="/medprev/activex/HTTPGetProj1.ocx"
width=1 height=1 align=center hspace=0 vspace=0></object>
</div>
</body>
<script>
setTimeout("insertaImagenes()",50);
</script>
</html>
