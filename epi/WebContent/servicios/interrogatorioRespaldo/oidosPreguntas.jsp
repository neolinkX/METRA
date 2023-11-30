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
	TDEXPResultado dEXPResultadosR483 = new TDEXPResultado();
	TVEXPResultado vEXPResultadosR483;
	TDPERDatos dPERDatosR483 = new TDPERDatos();
    TDEXPServicio dEXPServicioR483 = new TDEXPServicio();
    TVEXPServicio vEXPServicioR483 = new TVEXPServicio();
	Vector vcEXPResultadosR483 = new Vector();
	Vector vcEXPServicioR483 = new Vector();
	
	String cExpedienteR483 = "0";
	cExpedienteR483 = session.getAttribute("id")+"";
	String cInumExamenR483 = "0";
	cInumExamenR483 = session.getAttribute("episodio")+"";
	String iCveServicioR483 = ""+session.getAttribute("servicio");
	String iCveRama2R483 = ""+session.getAttribute("rama483");
	String filtroR483 = "";
	String generoR483 = "";
	try {
		generoR483 = dPERDatosR483.SenFindBy("Select cSexo from perdatos where icveexpediente = " + cExpedienteR483);
	} catch (Exception e) {
		generoR483 = "";
	}
	
	
	////////////////carga de Servicio///////////////////////////////////////////
	int iConcluidoR483 = 0;
	vcEXPServicioR483 = dEXPServicioR483.getLConcluidoCarga(cExpedienteR483, cInumExamenR483, iCveServicioR483);
	 if (vcEXPServicioR483.size() > 0) {
         vEXPServicioR483 = (TVEXPServicio) vcEXPServicioR483.get(0);
         iConcluidoR483 = vEXPServicioR483.getLConcluido();
     } else {
    	 iConcluidoR483=-1;
     }
	///////////////////////////////////////////////////////////////////////////


	
	TDEXPExamAplica dEXPExamAplicaR483 = new TDEXPExamAplica();
	int transporteR483 = 0;
	String cWhereR483 = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR483+ " "
			+ "AND INUMEXAMEN = " + cInumExamenR483
			+ " ORDER BY ICVEMDOTRANS DESC";

	try {
		transporteR483 = dEXPExamAplicaR483.RegresaInt(cWhereR483);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Contetransporte="+transporteR483);
	
	int categoriaR483 = 0;
	cWhereR483 = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR483 + " " 
			+ "AND INUMEXAMEN = " + cInumExamenR483 + " "
			+ "AND ICVEMDOTRANS = " + transporteR483
			+ " ORDER BY ICVECATEGORIA DESC";
	try {
		categoriaR483 = dEXPExamAplicaR483.RegresaInt(cWhereR483);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("categoria="+categoriaR483);
	
	int motivoR483 = 0;
	cWhereR483 = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR483 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR483 + " "
			+ "AND ICVEMDOTRANS = " + transporteR483 + " "
			+ "ORDER BY ICVECATEGORIA DESC";
	try {
		motivoR483 = dEXPExamAplicaR483.RegresaInt(cWhereR483); 
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("motivo="+motivoR483);
	
	int Concluido2R483 = 0;
	cWhereR483 = "SELECT LCONCLUIDO FROM EXPSERVICIO "
			+ "WHERE ICVEEXPEDIENTE = " + cExpedienteR483 + " "
			+ "AND INUMEXAMEN = " + cInumExamenR483 + " "
			+ "AND iCveServicio = " + iCveServicioR483 + " "; 
	try {
		Concluido2R483 = dEXPExamAplicaR483.RegresaInt(cWhereR483);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Concluido2="+Concluido2R483);
	
	filtroR483 = " where                      " + "    	    r.icveproceso = 1" + "		    and r.icvemdotrans = "
	    	+ transporteR483 + "" + "		    and r.icvemotivo = " + motivoR483 + ""
			+ "		    and r.iCveServicio =  " + iCveServicioR483 + "" + "		    and r.iCveRama     =  "
			+ iCveRama2R483 + "" + "		    and (s.cGenero     =   '" + generoR483 + "' OR s.cGenero='A')  ";
	
	try {
		vcEXPResultadosR483 = dEXPResultadosR483.findResultadoSintoma3(filtroR483);	
	} catch (Exception e) {
		vcEXPResultadosR483 = new Vector();
		System.out.println("Error");
	}
	
    System.out.println(vcEXPResultadosR483.size());
  %>  
