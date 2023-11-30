<%@page import="gob.sct.medprev.dwr.MedPredDwr"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.micper.util.logging.*" %>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html> 
<%
  try{
      request.getSession(true).removeAttribute("UsrID");
      request.getSession(true).removeAttribute("MenuUsuario");
      request.getSession(true).removeAttribute("PermisosUsuario");
  }catch (Exception exc){}
  pg0700000CFG  clsConfig = new pg0700000CFG();
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  clsConfig.runConfig(vEntorno);
  clsConfig.outputHeader(vEntorno, vErrores, pageContext, request, response);

  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cNumModulo = vEntorno.getNumModuloStr();
%>
<script>
function recarga(){
	var cUrlRedirigir = '<%=vParametros.getPropEspecifica("RutaProg")%>cargandoSistema.jsp';
	  top.location = cUrlRedirigir;
}
function fGetFile(cLocal,cURL){
	  oHTP.cArchivoLocal = cLocal;
	  oHTP.cURLArchivo = cURL;
	  oHTP.fGetFile();
	}

function revisaArchivos() {
	var cambios = false;

	<%
	 String path = vParametros.getPropEspecifica("RutaDescargaImagen");//"/nas/img/medprev/";
	 //System.out.println("Path Inicial (entro a metodo existAllImages): "+ path);
	 MedPredDwr dwr = new MedPredDwr();
	 if (dwr.actualizaImagenes()==1) {
	 	//System.out.println("pase la bandera actualizaImg");
	 	%>
		 var fso = new ActiveXObject("Scripting.FileSystemObject");
		 aplica="C:/sct/";
		 folderBool = fso.FolderExists(aplica);
		 if (!folderBool){
		 	recarga();
		 }
		 aplicados="C:/sct/img/";
		 folderBooll = fso.FolderExists(aplicados);
		 if (!folderBooll){
		 	recarga();
		 }
		 <%
	 	File directorio = new File(path);
	 	String[] ficheros = directorio.list();
	 	//System.out.println("si existe la carpeta ");
	 	for (int i = 0; i < ficheros.length; i++) {
	 		/*BufferedReader br = new BufferedReader(new FileReader(path
	 				+ ficheros[i]));*/
	 		if (!ficheros[i].contains(".svn") || ficheros[i].contains("Cargando")) {
	 			if (ficheros[i].contains(".")){
	 				%>
	 				fileBool<%=i%> = fso.FileExists("<%="C:/sct/img/"+ficheros[i]%>");
	 				//alert(fileBool<%=i%>);
					if (!fileBool<%=i%>) {
						cambios = true;
					}
	 				<%
	 			}
	 		}
	 	}
	 }
	 %>
	 return cambios;
}
if(revisaArchivos()){
	recarga();
}
</script>
<SCRIPT LANGUAGE="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
</SCRIPT> 
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700000.js"></SCRIPT>
<frameset framespacing="0" border="false" rows="52,*" frameborder="0"> 
  <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%= cNumModulo %>00001.jsp" scrolling="no" name="FRMTitulo" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
  <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%= cNumModulo %>00002.jsp" scrolling="no" name="FRMCuerpo" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
  <noframes>
  <body topmargin="0" leftmargin="0">
  <p>Su navegador no soporta el uso de frames, favor de obtener una versión mas reciente</p>
  </body>
  </noframes>
</frameset>
</html>
<%= vErrores.muestraError() %>