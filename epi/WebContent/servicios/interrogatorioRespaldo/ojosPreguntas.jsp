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
	TDEXPResultado dEXPResultadosR482 = new TDEXPResultado();
	TVEXPResultado vEXPResultadosR482;
	TDPERDatos dPERDatosR482 = new TDPERDatos();
    TDEXPServicio dEXPServicioR482 = new TDEXPServicio();
    TVEXPServicio vEXPServicioR482 = new TVEXPServicio();
	Vector vcEXPResultadosR482 = new Vector();
	Vector vcEXPServicioR482 = new Vector();
	
	String cExpedienteR482 = "0";
	cExpedienteR482 = session.getAttribute("id")+"";
	String cInumExamenR482 = "0";
	cInumExamenR482 = session.getAttribute("episodio")+"";
	String iCveServicioR482 = ""+session.getAttribute("servicio");
	String iCveRama2R482 = ""+session.getAttribute("rama482");
	String filtroR482 = "";
	String generoR482 = "";
	try {
		generoR482 = dPERDatosR482.SenFindBy("Select cSexo from perdatos where icveexpediente = " + cExpedienteR482);
	} catch (Exception e) {
		generoR482 = "";
	}
	
	
	////////////////carga de Servicio///////////////////////////////////////////
	int iConcluidoR482 = 0;
	vcEXPServicioR482 = dEXPServicioR482.getLConcluidoCarga(cExpedienteR482, cInumExamenR482, iCveServicioR482);
	 if (vcEXPServicioR482.size() > 0) {
         vEXPServicioR482 = (TVEXPServicio) vcEXPServicioR482.get(0);
         iConcluidoR482 = vEXPServicioR482.getLConcluido();
     } else {
    	 iConcluidoR482=-1;
     }
	///////////////////////////////////////////////////////////////////////////


	
	TDEXPExamAplica dEXPExamAplicaR482 = new TDEXPExamAplica();
	int transporteR482 = 0;
	String cWhereR482 = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR482+ " "
			+ "AND INUMEXAMEN = " + cInumExamenR482
			+ " ORDER BY ICVEMDOTRANS DESC";

	try {
		transporteR482 = dEXPExamAplicaR482.RegresaInt(cWhereR482);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Contetransporte="+transporteR482);
	
	int categoriaR482 = 0;
	cWhereR482 = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR482 + " " 
			+ "AND INUMEXAMEN = " + cInumExamenR482 + " "
			+ "AND ICVEMDOTRANS = " + transporteR482
			+ " ORDER BY ICVECATEGORIA DESC";
	try {
		categoriaR482 = dEXPExamAplicaR482.RegresaInt(cWhereR482);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("categoria="+categoriaR482);
	
	int motivoR482 = 0;
	cWhereR482 = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR482 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR482 + " "
			+ "AND ICVEMDOTRANS = " + transporteR482 + " "
			+ "ORDER BY ICVECATEGORIA DESC";
	try {
		motivoR482 = dEXPExamAplicaR482.RegresaInt(cWhereR482); 
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("motivo="+motivoR482);
	
	int Concluido2R482 = 0;
	cWhereR482 = "SELECT LCONCLUIDO FROM EXPSERVICIO "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR482 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR482 + " "
			+ "AND iCveServicio = " + iCveServicioR482 + " "; 
	try {
		Concluido2R482 = dEXPExamAplicaR482.RegresaInt(cWhereR482);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Concluido2="+Concluido2R482);
	
	filtroR482 = " where                      " + "    	    r.icveproceso = 1" + "		    and r.icvemdotrans = "
	    	+ transporteR482 + "" + "		    and r.icvemotivo = " + motivoR482 + ""
			+ "		    and r.iCveServicio =  " + iCveServicioR482 + "" + "		    and r.iCveRama     =  "
			+ iCveRama2R482 + "" + "		    and (s.cGenero     =   '" + generoR482 + "' OR s.cGenero='A')  ";
	
	try {
		vcEXPResultadosR482 = dEXPResultadosR482.findResultadoSintoma3(filtroR482);	
	} catch (Exception e) {
		vcEXPResultadosR482 = new Vector();
		System.out.println("Error");
	}
	
    System.out.println(vcEXPResultadosR482.size());
  %>  
