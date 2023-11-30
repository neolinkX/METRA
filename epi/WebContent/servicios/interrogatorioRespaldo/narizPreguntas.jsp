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
	TDEXPResultado dEXPResultadosR484 = new TDEXPResultado();
	TVEXPResultado vEXPResultadosR484;
	TDPERDatos dPERDatosR484 = new TDPERDatos();
    TDEXPServicio dEXPServicioR484 = new TDEXPServicio();
    TVEXPServicio vEXPServicioR484 = new TVEXPServicio();
	Vector vcEXPResultadosR484 = new Vector();
	Vector vcEXPServicioR484 = new Vector();
	
	String cExpedienteR484 = "0";
	cExpedienteR484 = session.getAttribute("id")+"";
	String cInumExamenR484 = "0";
	cInumExamenR484 = session.getAttribute("episodio")+"";
	String iCveServicioR484 = ""+session.getAttribute("servicio");
	String iCveRama2R484 = ""+session.getAttribute("rama484");
	String filtroR484 = "";
	String generoR484 = "";
	try {
		generoR484 = dPERDatosR484.SenFindBy("Select cSexo from perdatos where icveexpediente = " + cExpedienteR484);
	} catch (Exception e) {
		generoR484 = "";
	}
	
	
	////////////////carga de Servicio///////////////////////////////////////////
	int iConcluidoR484 = 0;
	vcEXPServicioR484 = dEXPServicioR484.getLConcluidoCarga(cExpedienteR484, cInumExamenR484, iCveServicioR484);
	 if (vcEXPServicioR484.size() > 0) {
         vEXPServicioR484 = (TVEXPServicio) vcEXPServicioR484.get(0);
         iConcluidoR484 = vEXPServicioR484.getLConcluido();
     } else {
    	 iConcluidoR484=-1;
     }
	///////////////////////////////////////////////////////////////////////////


	
	TDEXPExamAplica dEXPExamAplicaR484 = new TDEXPExamAplica();
	int transporteR484 = 0;
	String cWhereR484 = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR484+ " "
			+ "AND INUMEXAMEN = " + cInumExamenR484
			+ " ORDER BY ICVEMDOTRANS DESC";

	try {
		transporteR484 = dEXPExamAplicaR484.RegresaInt(cWhereR484);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Contetransporte="+transporteR484);
	
	int categoriaR484 = 0;
	cWhereR484 = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR484 + " " 
			+ "AND INUMEXAMEN = " + cInumExamenR484 + " "
			+ "AND ICVEMDOTRANS = " + transporteR484
			+ " ORDER BY ICVECATEGORIA DESC";
	try {
		categoriaR484 = dEXPExamAplicaR484.RegresaInt(cWhereR484);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("categoria="+categoriaR484);
	
	int motivoR484 = 0;
	cWhereR484 = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR484 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR484 + " "
			+ "AND ICVEMDOTRANS = " + transporteR484 + " "
			+ "ORDER BY ICVECATEGORIA DESC";
	try {
		motivoR484 = dEXPExamAplicaR484.RegresaInt(cWhereR484); 
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("motivo="+motivoR484);
	
	int Concluido2R484 = 0;
	cWhereR484 = "SELECT LCONCLUIDO FROM EXPSERVICIO "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR484 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR484 + " "
			+ "AND iCveServicio = " + iCveServicioR484 + " "; 
	try {
		Concluido2R484 = dEXPExamAplicaR484.RegresaInt(cWhereR484);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Concluido2="+Concluido2R484);
	
	filtroR484 = " where                      " + "    	    r.icveproceso = 1" + "		    and r.icvemdotrans = "
	    	+ transporteR484 + "" + "		    and r.icvemotivo = " + motivoR484 + ""
			+ "		    and r.iCveServicio =  " + iCveServicioR484 + "" + "		    and r.iCveRama     =  "
			+ iCveRama2R484 + "" + "		    and (s.cGenero     =   '" + generoR484 + "' OR s.cGenero='A')  ";
	
	try {
		vcEXPResultadosR484 = dEXPResultadosR484.findResultadoSintoma3(filtroR484);	
	} catch (Exception e) {
		vcEXPResultadosR484 = new Vector();
		System.out.println("Error");
	}
	
    System.out.println(vcEXPResultadosR484.size());
  %>  
