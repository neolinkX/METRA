<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.util.*"%>
<%
 System.out.println(request.getParameter("cat"));
 System.out.println(request.getParameter("valor"));
 
	String resultadoJson = "";

	///////////////////  OBTENIENDO CATEGORIAS /////////////////////////////
if(request.getParameter("cat").equals("cat_tramite_categoria")){
	TDGRLCategoria cat = new TDGRLCategoria();
	TVGRLCategoria vGRLCategoria;
	Vector vcCategorias = new Vector();
	try{
		vcCategorias = cat.FindByAll(""+request.getParameter("valor").trim());	
		for (int i = 0; i < vcCategorias.size(); i++) {
			vGRLCategoria = (TVGRLCategoria) vcCategorias.get(i);
			if(i==0){
				resultadoJson = "{\"id\":\""+vGRLCategoria.getICveCategoria()+"\",\"value\":\""+vGRLCategoria.getCDscBreve()+"\"}"; 
			}else{
				resultadoJson = resultadoJson+ ",{\"id\":\""+vGRLCategoria.getICveCategoria()+"\",\"value\":\""+vGRLCategoria.getCDscBreve()+"\"}";
			}
		}
		session.setAttribute("MdoTransTramite", ""+request.getParameter("valor").trim());
		resultadoJson="["+resultadoJson+"]";
	}catch(Exception e){
		System.out.println("Error al buscar registros del catalogo de Categorias"+ e);
	}
}

///////////////////  OBTENIENDO PUESTOS /////////////////////////////
	if(request.getParameter("cat").equals("cat_tramite_puesto")){
		TDGRLPuesto puesto = new TDGRLPuesto();
		TVGRLPuesto vGRLPuesto;
		Vector vcPuesto = new Vector();
		try{
			vcPuesto = puesto.FindByAll(""+session.getAttribute("MdoTransTramite"), ""+request.getParameter("valor").trim());	
			for (int i = 0; i < vcPuesto.size(); i++) {
				vGRLPuesto = (TVGRLPuesto) vcPuesto.get(i);
				if(i==0){
					resultadoJson = "{\"id\":\""+vGRLPuesto.getICvePuesto()+"\",\"value\":\""+vGRLPuesto.getCDscPuesto()+"\"}"; 
				}else{
					resultadoJson = resultadoJson+ ",{\"id\":\""+vGRLPuesto.getICvePuesto()+"\",\"value\":\""+vGRLPuesto.getCDscPuesto()+"\"}";
				}
			}
			session.setAttribute("iCveCategoria", ""+request.getParameter("valor").trim());
			resultadoJson="["+resultadoJson+"]";
		}catch(Exception e){
			System.out.println("Error al buscar registros del catalogo de Puestos"+ e);
		}
	}
	
	
///////////////////  OBTENIENDO LUGAR NACIMIENTO /////////////////////////////
if(request.getParameter("cat").equals("cat_lugar_nacimiento") || request.getParameter("cat").equals("cat_estado")){
TDGRLEntidadFed eFed = new TDGRLEntidadFed();
TVGRLEntidadFed vGRLEntidadFed;
Vector vcGRLEntidadFed = new Vector();
try{
	vcGRLEntidadFed = eFed.FindByAll(" icvepais = "+request.getParameter("valor").trim(),"");	
for (int i = 0; i < vcGRLEntidadFed.size(); i++) {
	vGRLEntidadFed = (TVGRLEntidadFed) vcGRLEntidadFed.get(i);
	if(i==0){
		resultadoJson = "{\"id\":\""+vGRLEntidadFed.getICveEntidadFed()+"\",\"value\":\""+vGRLEntidadFed.getCNombre()+"\"}"; 
	}else{
		resultadoJson = resultadoJson+ ",{\"id\":\""+vGRLEntidadFed.getICveEntidadFed()+"\",\"value\":\""+vGRLEntidadFed.getCNombre()+"\"}";
	}
}
if(request.getParameter("cat").equals("cat_lugar_nacimiento") || request.getParameter("cat").equals("cat_estado")){
	session.setAttribute("LugarNacimiento", ""+request.getParameter("valor").trim());	
}
if(request.getParameter("cat").equals("cat_lugar_nacimiento") || request.getParameter("cat").equals("cat_estado")){
	session.setAttribute("DomPais", ""+request.getParameter("valor").trim());
}

resultadoJson="["+resultadoJson+"]";
}catch(Exception e){
System.out.println("Error al buscar registros del catalogo de Puestos"+ e);
}
}



///////////////////  OBTENIENDO LUGAR MUNICIPIO /////////////////////////////
if(request.getParameter("cat").equals("cat_municipio")){
TDGRLMunicipio Mun = new TDGRLMunicipio();
TVGRLMunicipio vGRLMunicipio;
Vector vcGRLMunicipio = new Vector();
try{
vcGRLMunicipio = Mun.FindByAll(" icvepais = "+session.getAttribute("DomPais")+ " and icveEntidadFed = "+request.getParameter("valor").trim(),null);	
for (int i = 0; i < vcGRLMunicipio.size(); i++) {
vGRLMunicipio = (TVGRLMunicipio) vcGRLMunicipio.get(i);
if(i==0){
resultadoJson = "{\"id\":\""+vGRLMunicipio.getICveMunicipio()+"\",\"value\":\""+vGRLMunicipio.getCNombre()+"\"}"; 
}else{
resultadoJson = resultadoJson+ ",{\"id\":\""+vGRLMunicipio.getICveMunicipio()+"\",\"value\":\""+vGRLMunicipio.getCNombre()+"\"}";
}
}
session.setAttribute("DomEstado", ""+request.getParameter("valor").trim());
resultadoJson="["+resultadoJson+"]";
}catch(Exception e){
System.out.println("Error al buscar registros del catalogo de Puestos"+ e);
}
}


///////////////////  OBTENIENDO LOCALIDADES /////////////////////////////
if(request.getParameter("cat").equals("cat_localidad")){
TDGRLLocalidad Loc = new TDGRLLocalidad();
TVGRLLocalidad vGRLLocalidad;
Vector vcGRLLocalidad = new Vector();
try{
vcGRLLocalidad = Loc.FindByAll(" icvepais = "+session.getAttribute("DomPais")+ " and icveEntidadFed = "+session.getAttribute("DomEstado")+ " and icvemunicipio = "+request.getParameter("valor").trim() ," order by cNombreLoc");
for (int i = 0; i < vcGRLLocalidad.size(); i++) {
vGRLLocalidad = (TVGRLLocalidad) vcGRLLocalidad.get(i);
if(i==0){
resultadoJson = "{\"id\":\""+vGRLLocalidad.getICveLocalidad()+"\",\"value\":\""+vGRLLocalidad.getCNombre()+"\"}"; 
}else{
resultadoJson = resultadoJson+ ",{\"id\":\""+vGRLLocalidad.getICveLocalidad()+"\",\"value\":\""+vGRLLocalidad.getCNombre()+"\"}";
}
}
session.setAttribute("Municipio", ""+request.getParameter("valor").trim());
resultadoJson="["+resultadoJson+"]";
}catch(Exception e){
System.out.println("Error al buscar registros del catalogo de Puestos"+ e);
}
}



///////////////////  OBTENIENDO ModuloES /////////////////////////////
if(request.getParameter("cat").equals("catalogo_modulo")){
TDGRLModulo Mod = new TDGRLModulo();
TVGRLModulo vGRLModulo;
Vector vcGRLModulo = new Vector();
try{
	System.out.println("Buscar Modulo");
vcGRLModulo = Mod.FindByAll("  where icveunimed = "+request.getParameter("valor") +" and lvigente =1");
for (int i = 0; i < vcGRLModulo.size(); i++) {
vGRLModulo = (TVGRLModulo) vcGRLModulo.get(i);
if(i==0){
resultadoJson = "{\"id\":\""+vGRLModulo.getICveModulo()+"\",\"value\":\""+vGRLModulo.getCDscModulo()+"\"}"; 
}else{
resultadoJson = resultadoJson+ ",{\"id\":\""+vGRLModulo.getICveModulo()+"\",\"value\":\""+vGRLModulo.getCDscModulo()+"\"}";
}
}
session.setAttribute("UnidadMedica", ""+request.getParameter("valor").trim());
resultadoJson="["+resultadoJson+"]";
}catch(Exception e){
System.out.println("Error al buscar registros del catalogo de Puestos"+ e);
}
}




///Escribiendo resultados para el catalogo solicitado///
out.print(resultadoJson);


/*
 [
    {"id":"1","value":"Alto Orinoco"},
    {"id":"2","value":"Atabapo"},
    {"id":"3","value":"Atures"},
    {"id":"4","value":"Autana"},
    {"id":"5","value":"Maroa"}
]
*/

 %>
