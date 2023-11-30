<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="gob.sct.medprev.vo.TVGRLUniMed"%>
<%@ page import="gob.sct.medprev.vo.TVPERDireccion"%>
<%@ page import="gob.sct.medprev.dao.TDGRLUniMed"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>


<%@ page import="gob.sct.medprev.vo.TVPERDatos"%>

<%

TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

TVPERDatos vPERDatos = new TVPERDatos();
TVGRLUniMed vUnidad = new TVGRLUniMed();

System.out.println("Unidad - 1");

///////   Llena Parametros   /////////////
vUnidad.setICveUniMed(Integer.parseInt(request.getParameter("icveunimed")));
vUnidad.setCDscUniMed(request.getParameter("cdscunimmed"));

if(request.getParameter("lcis") != null){
	vUnidad.setLCis(1);
}else{
	vUnidad.setLCis(0);
}

if(request.getParameter("lpago") != null){
	vUnidad.setLPago(1);
}else{
	vUnidad.setLPago(0);
}

if(request.getParameter("lvigente") != null){
	vUnidad.setLVigente(1);
}else{
	vUnidad.setLVigente(0);
}

if(request.getParameter("lprivada") != null){
	vUnidad.setLPrivada(1);
	//System.out.println("not null");
}else{
	vUnidad.setLPrivada(0);
	//System.out.println("null");
}

vUnidad.setCCalle(request.getParameter("ccalle"));
vUnidad.setCColonia(request.getParameter("ccolonia"));
vUnidad.setICP(Integer.parseInt(request.getParameter("icp")));
vUnidad.setICvePais(Integer.parseInt(request.getParameter("dom_id_pais")));
vUnidad.setICveEstado(Integer.parseInt(request.getParameter("dom_id_estado")));
vUnidad.setICveMunicipio(Integer.parseInt(request.getParameter("dom_id_municipio")));
vUnidad.setCCorreoE(request.getParameter("ccalle"));
vUnidad.setCTel(request.getParameter("ctel"));
vUnidad.setCCiudad(request.getParameter("dom_ciudad"));


TDGRLUniMed datos = new TDGRLUniMed();

try{
	int expediente = datos.updateDos(null, vUnidad);
	
	if(expediente > 0){
		out.println("[{\"resp\":\"success\"},{\"exp\":\""+expediente+"\"},{\"via\":\"correcta\"}]");
	}else{
		out.println("[{\"resp\":\"error\"},{\"exp\":\"0\"},{\"msg\":\"correcta\"}]");
	}
	System.out.println("Agregado");
}catch(Exception e){
	System.out.println("Fallo");
}


%>