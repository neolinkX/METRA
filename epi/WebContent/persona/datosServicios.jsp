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
	TDEXPResultado dEXPResultados = new TDEXPResultado();
	TVEXPResultado vEXPResultados;
	TDPERDatos dPERDatos = new TDPERDatos();
    TDEXPServicio dEXPServicio = new TDEXPServicio();
    TVEXPServicio vEXPServicio = new TVEXPServicio();
	Vector vcEXPResultados = new Vector();
	Vector vcEXPServicio = new Vector();
	
	String cExpediente = "0";
	cExpediente = session.getAttribute("id")+"";
	String cInumExamen = "0";
	cInumExamen = session.getAttribute("episodio")+"";
	String iCveServicio = ""+session.getAttribute("servicio");
	String iCveRama2 = ""+session.getAttribute("rama");
	String filtro = "";
	String genero = "";
	try {
		genero = dPERDatos.SenFindBy("Select cSexo from perdatos where icveexpediente = " + cExpediente);
	} catch (Exception e) {
		genero = "";
	}
	
	
	////////////////carga de Servicio///////////////////////////////////////////
	int iConcluido = 0;
	vcEXPServicio = dEXPServicio.getLConcluidoCarga(cExpediente, cInumExamen, iCveServicio);
	 if (vcEXPServicio.size() > 0) {
         vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
         iConcluido = vEXPServicio.getLConcluido();
     } else {
    	 iConcluido=-1;
     }
	///////////////////////////////////////////////////////////////////////////


	
	TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
	int transporte = 0;
	String cWhere = "SELECT ICVEMDOTRANS FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente+ " "
			+ "AND INUMEXAMEN = " + cInumExamen
			+ " ORDER BY ICVEMDOTRANS DESC";

	try {
		transporte = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Contetransporte="+transporte);
	
	int categoria = 0;
	cWhere = "SELECT ICVECATEGORIA FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente + " "
			+ "AND INUMEXAMEN = " + cInumExamen + " "
			+ "AND ICVEMDOTRANS = " + transporte
			+ " ORDER BY ICVECATEGORIA DESC";
	try {
		categoria = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("categoria="+categoria);
	
	int motivo = 0;
    cWhere = "SELECT ICVEMOTIVO FROM EXPEXAMCAT "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente + " "
			+ "AND INUMEXAMEN = " + cInumExamen + " "
			+ "AND ICVEMDOTRANS = " + transporte + " "
			+ "ORDER BY ICVECATEGORIA DESC";
	try {
		motivo = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("motivo="+motivo);
	
	int Concluido2 = 0;
    cWhere = "SELECT LCONCLUIDO FROM EXPSERVICIO "
			+ "WHERE ICVEEXPEDIENTE = " + cExpediente + " "
			+ "AND INUMEXAMEN = " + cInumExamen + " "
			+ "AND ICVESERVICIO = " + iCveServicio + " ";
	try {
		Concluido2 = dEXPExamAplica.RegresaInt(cWhere);
	} catch (Exception ex) {
		System.out.println("Error al buscar registros en EXPExamCat"+ ex);
	}
	System.out.println("Concluido2="+Concluido2);
	
	filtro = " where                      " + "    	    r.icveproceso = 1" + "		    and r.icvemdotrans = "
	    	+ transporte + "" + "		    and r.icvemotivo = " + motivo + ""
			+ "		    and r.iCveServicio =  " + iCveServicio + "" + "		    and r.iCveRama     =  "
			+ iCveRama2 + "" + "		    and (s.cGenero     =   '" + genero + "' OR s.cGenero='A')  ";
	
	try {
		vcEXPResultados = dEXPResultados.findResultadoSintoma3(filtro);	
	} catch (Exception e) {
		vcEXPResultados = new Vector();
		System.out.println("Error");
	}
	
    System.out.println(vcEXPResultados.size());
    
    //session.setMaxInactiveInterval(30000);
    
  %>  
