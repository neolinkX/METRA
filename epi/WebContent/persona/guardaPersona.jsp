<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="gob.sct.medprev.vo.TVPERDatos"%>
<%@ page import="gob.sct.medprev.vo.TVPERDireccion"%>
<%@ page import="gob.sct.medprev.dao.TDEPICisCita"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%

TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

TVPERDatos vPERDatos = new TVPERDatos();
//vPERDatos.setICveExpediente(); // null

System.out.println("Persona - 1");

vPERDatos.setCSexo(request.getParameter("sexo"));
vPERDatos.setCNombre(request.getParameter("nombre"));
vPERDatos.setCApPaterno(request.getParameter("apaterno"));
System.out.println("Persona - 2");
vPERDatos.setCApMaterno(request.getParameter("amaterno"));
vPERDatos.setCRFC(request.getParameter("rfc"));
vPERDatos.setCHomoclave(""); // null
System.out.println("Persona - 3");
vPERDatos.setCCURP(request.getParameter("curp"));

SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//Date parsed = format.parse("20110210");
Date parsed = format.parse(request.getParameter("fecha_nacimiento"));
java.sql.Date sql = new java.sql.Date(parsed.getTime());
vPERDatos.setDtNacimiento(sql);

System.out.println("Persona - 4");

vPERDatos.setICvePaisNac(Integer.parseInt(request.getParameter("id_nacionalidad")));
vPERDatos.setICveEstadoNac(Integer.parseInt( request.getParameter("estado_nacimiento")));

System.out.println("Persona - 5");
vPERDatos.setCExpediente("");
vPERDatos.setCSenasPersonal(""); // null
vPERDatos.setCCorreoElec(""); // null
vPERDatos.setLDonadorOrg(0); // null
vPERDatos.setCPersonaAviso(""); // null
vPERDatos.setCTelAviso(""); // null
vPERDatos.setCDirecAviso(""); // null
vPERDatos.setCCorreoAviso(""); // null
vPERDatos.setICveDireccion(1); // null
vPERDatos.setICveFoto(0); // null
vPERDatos.setLNoHuellas(0); // null
vPERDatos.setICveNumEmpresa(0); // null
vPERDatos.setICveUsuRegistra(vUsuario.getICveusuario()); // Toamdo de la sesi�n
System.out.println("Persona - 6");
// validar transfronterizo
//unidad = request.getParameter(1);
//modulo = request.getParameter(42);
// Licencias
vPERDatos.setCLicencia(request.getParameter("licencia"));
vPERDatos.setCLicenciaInt(request.getParameter("licencia_int"));
System.out.println("Persona - 7");
vPERDatos.setCSexo_DGIS("");
//vPERDatos.setICveLocalidad(request.getParameter(47));


TVPERDireccion vPERDireccion = new TVPERDireccion();
//vPERDireccion.setICvePersonal(request.getParameter("15));
vPERDireccion.setCCalle(request.getParameter("dom_calle"));
vPERDireccion.setCNumExt(request.getParameter("dom_numext"));
System.out.println("Persona - 8");
vPERDireccion.setCNumInt(request.getParameter("dom_numint"));
vPERDireccion.setCColonia(request.getParameter("dom_colonia"));
System.out.println("Persona - 9");
vPERDireccion.setICP(Integer.parseInt(request.getParameter("dom_codpos")));
vPERDireccion.setCCiudad("");
vPERDireccion.setICvePais(Integer.parseInt(request.getParameter("dom_id_pais")));
vPERDireccion.setICveEstado(Integer.parseInt(request.getParameter("dom_id_estado")));
System.out.println("Persona - 10");
vPERDireccion.setICveMunicipio(Integer.parseInt(request.getParameter("dom_id_municipio")));
vPERDireccion.setCTel(request.getParameter("telefono"));
vPERDireccion.setICveLocalidad(Integer.parseInt(request.getParameter("dom_id_localidad")));
System.out.println("Persona - 11");
TDEPICisCita datos = new TDEPICisCita();

try{
	int expediente = datos.AltaPersonalBootstrap(vPERDatos, vPERDireccion);
	
	if(expediente > 0){
		out.println("[{\"resp\":\"success\"},{\"exp\":\""+expediente+"\"},{\"via\":\"correcta\"}]");
	}else{
		out.println("[{\"resp\":\"error\"},{\"exp\":\"0\"},{\"msg\":\"correcta\"}]");
	}
	
	System.out.println("Agregado");
}catch(Exception e){
	
}


%>