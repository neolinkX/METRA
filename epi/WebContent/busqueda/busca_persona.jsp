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

	String condicion = request.getParameter("filtro").toUpperCase();

	System.out.println("condicion = " + condicion);

	TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
	TDLICFFH dLICFFH = new TDLICFFH();
	TDEXPExamAplica dAplica = new TDEXPExamAplica();

	String cBuscar = "" + request.getParameter("filtro").toUpperCase();
	if (cBuscar.length() > 0) {
		TDPERDatos dPerDatos = new TDPERDatos();
		TDMEDInhabilita dMEDInhabilita = new TDMEDInhabilita();
		TFechas dtFecha = new TFechas();
		//cBuscar = "" + request.getParameter("filtro");

		Vector vcPersonal = new Vector();
		Vector vcInhabilita = new Vector();

		try {
			vcPersonal = dPerDatos.FindBySelector(cBuscar);
		} catch (Exception e) {
			vcPersonal = new Vector();
			e.printStackTrace();
		}

		TVPERDatos vPerDatos;
		TVMEDInhabilita vMEDInhabilita;
		int contador = 0;
		System.out.println("resultados="+vcPersonal.size() );
		if (vcPersonal.size() > 0) {
			for (int i = 0; i < vcPersonal.size(); i++) {
				vPerDatos = (TVPERDatos) vcPersonal.get(i);
				System.out.println(vPerDatos.getICveExpediente());
				if(contador==0){
					regresa="["+vPerDatos.getICveExpediente()+",\""+vPerDatos.getCNombre()+"\",\""+vPerDatos.getCApPaterno()+"\",\""+vPerDatos.getCApMaterno()+"\",\""+vPerDatos.getDtNacimiento()+"\"]";
				}else{
					regresa=regresa+",["+vPerDatos.getICveExpediente()+",\""+vPerDatos.getCNombre()+"\",\""+vPerDatos.getCApPaterno()+"\",\""+vPerDatos.getCApMaterno()+"\",\""+vPerDatos.getDtNacimiento()+"\"]";
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

