<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<%
	TEntorno vEntorno2 = new TEntorno();
	vEntorno2.setNumModulo(07);
	TParametro vParametros2 = new TParametro(vEntorno2.getNumModuloStr());
	//vEntorno.setNombrePagina("SEPer01.jsp");
	TEtiCampo vEti = new TEtiCampo();
	TSimpleCampo vSCampo = new TSimpleCampo();
	String NombreCompleto ="";
	String CRFC ="";
	String CCURP ="";
	String CSEXO ="";
	String CEDAD="";	

	String cBuscar = "where icveexpediente =" + session.getAttribute("id");
	if (cBuscar.length() > 0) {
		TDPERDatos dPerDatos = new TDPERDatos();
		Vector vcPersonal = new Vector();
		try {
			vcPersonal = dPerDatos.FindBySelector(cBuscar);
		} catch (Exception e) {
			vcPersonal = new Vector();
			e.printStackTrace();
		}

		TVPERDatos vPerDatos;
		int contador = 0;
		System.out.println("resultados="+vcPersonal.size() );
		if (vcPersonal.size() > 0) {
			for (int i = 0; i < vcPersonal.size(); i++) {
				vPerDatos = (TVPERDatos) vcPersonal.get(i);
				NombreCompleto = vPerDatos.getCNombreCompleto();
				CRFC = vPerDatos.getCRFC();
				CCURP = vPerDatos.getCCURP();
				CSEXO = vPerDatos.getCSexo();
				
				///// OBTENER EDAD /////////
				Calendar hoy = Calendar.getInstance();
				Calendar nac = Calendar.getInstance();
				nac.setTime(vPerDatos.getDtNacimiento());
				int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);
				if ((hoy.get(Calendar.MONTH) << 5) + hoy.get(Calendar.DATE) < (nac
						.get(Calendar.MONTH) << 5) + nac.get(Calendar.DATE)){
					edad--;
				}
				CEDAD=edad+"";
					
				
			
			}
		}
	}
	
%>

