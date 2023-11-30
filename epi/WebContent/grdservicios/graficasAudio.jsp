<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<%
TEntorno vEntorno2 = new TEntorno();
vEntorno2.setNumModulo(07);
TParametro vParametros2 = new TParametro(vEntorno2.getNumModuloStr());
%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<head>
		<title>Test</title>

<!-- AUDIOMETRIA -->
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros2.getPropEspecifica("RutaFuncs")%>Audio01.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros2.getPropEspecifica("RutaFuncs")%>Audio02.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros2.getPropEspecifica("RutaFuncs")%>Audio03.js"></SCRIPT>
<%

TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
				TVEXPAudiomet vEXPAudiomet;
				Vector vcAudio = new Vector();
				String Servicio = "6";
				
				////Try catch para imprimir el error   
				try {
					//    vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + session.getAttribute("id") + " and iNumExamen = " + session.getAttribute("episodio") );

					String condicion = " where iCveExpediente = " + session.getAttribute("id")
							+ " and iNumExamen = " + session.getAttribute("episodio")
							+ " and iCveServicio = " + Servicio;
					vcAudio = dEXPAudiomet.FindByAll(condicion);
					if (vcAudio.size() > 0) {
						System.out.println("Generar");
						if (request.getParameter("hdBoton").equals("AudioMuestra")
								&& !(request.getParameter("hdGOido").equals("null"))) {
							out.println("<SCRIPT LANGUAGE='JavaScript'>");
							%>
							  var cRutaImgServer = '<%=vParametros2.getPropEspecifica("RutaImgServer")%>';
							  var cRutaImgLocal  = '<%=vParametros2.getPropEspecifica("RutaImgLocal")%>';
							  var cStyle = '<link rel="stylesheet" href="/exm/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';
							  var aSel = new Array();
							  var aSelMuestra = new Array();
							
							
							<%
							for (int i = 0; i < vcAudio.size(); i++) {
								vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
								out.println("aSel[" + i + "]=[" + vEXPAudiomet.getIOido() + ","
										+ vEXPAudiomet.getITipo() + "," + vEXPAudiomet.getIX() + ","
										+ vEXPAudiomet.getIY() + "];");
							}
							 
						
							out.print("fGraphAudio(" + request.getParameter("hdGOido") + ",aSel);");
							out.print("</SCRIPT>");
							out.print("</head>");
							out.print("<body onload=\"fGraphAudio(" + request.getParameter("hdGOido") + ",aSel); window.close();\">");							
							out.print("</body>	</html>");
							
						}
						/*if (request.getParameter("hdBoton").equals("AudioMuestra")
								&& !(request.getParameter("hdGOido").equals("null"))) {
							out.println("<SCRIPT LANGUAGE='JavaScript'>");
							out.print("alert(\"Datos guardados correctamente\"));");
							out.print("</SCRIPT>");
						}*/

					}
					//out.println("[{\"resp\":\"success\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]");	
				} catch (Exception e) {
					vcAudio = new Vector();
					e.printStackTrace();
				}

 %>
 
