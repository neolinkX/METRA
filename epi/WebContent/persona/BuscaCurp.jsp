<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>

<%
/////////////////// LOCALIZANDO CURP /////////////////////////////
		TDPERDatos datos = new TDPERDatos();
		int existe = 0;
		String cCURP = request.getParameter("ccurp");
		String resultadoJson = "";
		try{
			existe = datos.iCURP(cCURP);
			System.out.println(existe);
				if(existe>0){
					resultadoJson = "[{\"resp\":\"success\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]"; 
				}else{
					resultadoJson = "[{\"resp\":\"acceso_incorrecto\"}]";
				}
				out.println(resultadoJson);
		}catch(Exception e){
			System.out.println("Error al buscar registros del catalogo de Puestos"+ e);
		}
	%>