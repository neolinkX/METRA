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
	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	//vEntorno.setNombrePagina("SEPer01.jsp");
	TEtiCampo vEti = new TEtiCampo();
	TSimpleCampo vSCampo = new TSimpleCampo();
	int TamVec = 0;
	String regresa = "";
	System.out.println("buscando0");
	System.out.println(request.getParameter("unidad_medica"));
	String unidad = request.getParameter("unidad_medica");
	String modulo = request.getParameter("catalogo_modulo");
	String fecha = request.getParameter("fecha_cita");

	//System.out.println("condicion = " + condicion);

	TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
	TDEXPExamAplica dAplica = new TDEXPExamAplica();

	String cBuscar = " where EXPExamAplica.icveunimed = " + unidad +" and EXPExamAplica.icvemodulo="+modulo+" and EXPExamAplica.dtsolicitado ='"+fecha+"' ";
	if (cBuscar.length() > 0) {
		TDPERDatos dPerDatos = new TDPERDatos();
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		TFechas dtFecha = new TFechas();
		//cBuscar = "" + request.getParameter("filtro");
		
System.out.println("buscando1");
		Vector vcAplica = new Vector();

		try {
			vcAplica = dAplica.FindByAll(cBuscar);//dPerDatos.FindBySelector(cBuscar);
			System.out.println("vcAplica ="+vcAplica.size());
		} catch (Exception e) {
			vcAplica = new Vector();
			e.printStackTrace();
			System.out.println("resultados error");
		}

		TVEXPExamAplica vEXPExamAplica;
		TVMEDInhabilita vMEDInhabilita;
		int contador = 0;
		System.out.println("resultados="+vcAplica.size() );
		if (vcAplica.size() > 0) {
			for (int i = 0; i < vcAplica.size(); i++) {
				vEXPExamAplica = (TVEXPExamAplica) vcAplica.get(i);
				System.out.println(vEXPExamAplica.getICveExpediente());
				if(contador==0){
					regresa="["+vEXPExamAplica.getICveExpediente()+",\""+vEXPExamAplica.getICveExpediente()+"\",\""+vEXPExamAplica.getCNombre()+"\",\""+vEXPExamAplica.getCApPaterno()+"\",\""+vEXPExamAplica.getCApMaterno()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\"]";
				}else{
					regresa=regresa+",["+vEXPExamAplica.getICveExpediente()+",\""+vEXPExamAplica.getICveExpediente()+"\",\""+vEXPExamAplica.getCNombre()+"\",\""+vEXPExamAplica.getCApPaterno()+"\",\""+vEXPExamAplica.getCApMaterno()+"\",\""+vEXPExamAplica.getDtSolicitado()+"\"]";
				}
				contador++;
			}
			regresa = "{\"draw\":"+contador+",\"recordsTotal\":"+contador+",\"recordsFiltered\":"+contador+",\"data\":["+regresa+"]}";
			
		}else{
			regresa = "{\"draw\":"+contador+",\"recordsTotal\":"+contador+",\"recordsFiltered\":"+contador+",\"data\":["+regresa+"]}";
			//regresa = "{\"draw\":false}";
		}
	}
	out.println(regresa);
	//out.println("{\"draw\":1,\"recordsTotal\":2,\"recordsFiltered\":2,\"data\":[[1,\"admin\",\"framedev@gmail.com\",\"Usuario1\",45,\"\r\n      <a data-paciente=\"1\" id=\"btn_atender_persona\"  data-episodio=\"1\" class=\"btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air\" title=\"Atender Persona\">        <i class=\"flaticon-user-ok\"></i>      </a>'\r\n      \"],[2,\"admin1\",\"framedev@gmail.com\",\"Usuario12\",6,\"\r\n      <a data-function=\"2\" class=\"usr_js_fn_03 btn btn-outline-brand m-btn m-btn--icon m-btn--icon-only m-btn--custom m-btn--pill m-btn--air\">\r\n        <i class=\"flaticon-cogwheel\"></i>\r\n      </a>\r\n      \"]]}");
	//{"draw":1,"recordsTotal":2,"recordsFiltered":2,"data":[[1,"admin","framedev@gmail.com","Usuario1",45],[2,"admin1","framedev@gmail.com","Usuario12",6]]}
%>

