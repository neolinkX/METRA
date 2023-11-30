<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="gob.sct.medprev.dao.TDEXPResultado"%>
<%@ page import="gob.sct.medprev.dao.TDPERDatos"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%@ page import="gob.sct.medprev.dao.TDEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPServicio"%>
<%@ page import="gob.sct.medprev.vo.TVEXPResultado"%>
<%@ page import="java.util.*"%>
<%
	System.out.println("datosServicios --");
	TDEXPResultado dEXPResultadosR485 = new TDEXPResultado();
	TVEXPResultado vEXPResultadosR485;
	TDPERDatos dPERDatosR485 = new TDPERDatos();
	TDEXPServicio dEXPServicioR485 = new TDEXPServicio();
	TVEXPServicio vEXPServicioR485 = new TVEXPServicio();
	Vector vcEXPResultadosR485 = new Vector();
	Vector vcEXPServicioR485 = new Vector();

	String cExpedienteR485 = "0";
	cExpedienteR485 = session.getAttribute("id") + "";
	String cInumExamenR485 = "0";
	cInumExamenR485 = session.getAttribute("episodio") + "";
	String iCveServicioR485 = "" + session.getAttribute("servicio");
	String iCveRama2R485 = "" + session.getAttribute("rama485");
	String filtroR485 = "";
	String generoR485 = "";
	try {
		generoR485 = dPERDatosR485
				.SenFindBy("Select cSexo from perdatos where icveexpediente = " + cExpedienteR485);
	} catch (Exception e) {
		generoR485 = "";
	}

	////////////////carga de Servicio///////////////////////////////////////////
	int iConcluidoR485 = 0;
	vcEXPServicioR485 = dEXPServicioR485.getLConcluidoCarga(cExpedienteR485, cInumExamenR485, iCveServicioR485);
	if (vcEXPServicioR485.size() > 0) {
		vEXPServicioR485 = (TVEXPServicio) vcEXPServicioR485.get(0);
		iConcluidoR485 = vEXPServicioR485.getLConcluido();
	} else {
		iConcluidoR485 = -1;
	}
	///////////////////////////////////////////////////////////////////////////

	TDEXPExamAplica dEXPExamAplicaR485 = new TDEXPExamAplica();
	int transporteR485 = 0;
	String cWhereR485 = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT " + "WHERE ICVEEXPEDIENTE = " + cExpedienteR485
			+ " " + "AND INUMEXAMEN = " + cInumExamenR485 + " ORDER BY ICVEMDOTRANS DESC";

	try {
		transporteR485 = dEXPExamAplicaR485.RegresaInt(cWhereR485);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat" + ex);
	}
	System.out.println("Contetransporte=" + transporteR485);

	int categoriaR485 = 0;
	cWhereR485 = "SELECT ICVECATEGORIA FROM EXPEXAMCAT " + "WHERE ICVEEXPEDIENTE = " + cExpedienteR485 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR485 + " " + "AND ICVEMDOTRANS = " + transporteR485
			+ " ORDER BY ICVECATEGORIA DESC";
	try {
		categoriaR485 = dEXPExamAplicaR485.RegresaInt(cWhereR485);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat" + ex);
	}
	System.out.println("categoria=" + categoriaR485);

	int motivoR485 = 0;
	cWhereR485 = "SELECT ICVEMOTIVO FROM EXPEXAMCAT " + "WHERE ICVEEXPEDIENTE = " + cExpedienteR485 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR485 + " " + "AND ICVEMDOTRANS = " + transporteR485 + " "
			+ "ORDER BY ICVECATEGORIA DESC";
	try {
		motivoR485 = dEXPExamAplicaR485.RegresaInt(cWhereR485);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat" + ex);
	}
	System.out.println("motivo=" + motivoR485);

	int Concluido2R485 = 0;
	cWhereR485 = "SELECT LCONCLUIDO FROM EXPSERVICIO " + "WHERE ICVEEXPEDIENTE = " + cExpedienteR485 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR485 + " " + "AND iCveServicio = " + iCveServicioR485 + " ";
	try {
		Concluido2R485 = dEXPExamAplicaR485.RegresaInt(cWhereR485);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat" + ex);
	}
	System.out.println("Concluido2=" + Concluido2R485);

	filtroR485 = " where                      " + "    	    r.icveproceso = 1"
			+ "		    and r.icvemdotrans = " + transporteR485 + "" + "		    and r.icvemotivo = "
			+ motivoR485 + "" + "		    and r.iCveServicio =  " + iCveServicioR485 + ""
			+ "		    and r.iCveRama     =  " + iCveRama2R485 + "" + "		    and (s.cGenero     =   '"
			+ generoR485 + "' OR s.cGenero='A')  ";

	try {
		vcEXPResultadosR485 = dEXPResultadosR485.findResultadoSintoma3(filtroR485);
	} catch (Exception e) {
		vcEXPResultadosR485 = new Vector();
		System.out.println("Error");
	}

	System.out.println(vcEXPResultadosR485.size());
%>  
