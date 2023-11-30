<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="gob.sct.ingresosws.ws.ConUsrIng.*"%>
<%
System.out.println("Llego a tramite");
System.out.println(request.getParameter("hdICvePersonal"));

  pg070103011CFG clsConfig = new pg070103011CFG();                // modificar Ok
  
  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103011.jsp");                    // modificar Ok
  /*vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103011.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  System.out.println("Llego a tramite2");
  String cCatalogo    = "pg070103010.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "No Disponible|";                // modificar Ok
  String cCveOrdenar  = "No Disponible|";            // modificar Ok
  String cDscFiltrar  = "No Disponible|";                // modificar Ok
  String cCveFiltrar  = "No Disponible|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = true;                       // modificar Ok
  boolean lIra        = true;                       // modificar Ok
  String cEstatusIR   = "Imprimir";                  // modificar ?
		  */
  System.out.println("Llego a tramite3");
  
  String id =""+request.getParameter("hdICvePersonal");
  String modotrans =""+request.getParameter("id_modo_transporte");
  String categoria =""+request.getParameter("categoria_tramite");
  String puesto =""+request.getParameter("puesto_tramite");
  String motivo =""+request.getParameter("id_motivo");
  String cunimed ="1";
  String cmodulo ="1";
  String idUser = "13900";
  int numexamen =0;
  int numid = 0;
  numid = Integer.parseInt(id);
  
  session.setAttribute("puesto_tramite",request.getParameter("puesto_tramite"));
  session.setAttribute("id_motivo",request.getParameter("id_motivo"));

 try{
	 String respuest =clsConfig.Guardar2(id,modotrans,categoria,puesto,motivo,cunimed,cmodulo,idUser,"true");
		if(respuest.length() > 2){  
			//out.println("[{\"resp\":\"true\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]");
			out.println("[{\"resp\":\""+respuest+"\"}]");
			if(respuest.equals("success")){
				TDEXPExamAplica aplica = new TDEXPExamAplica();
				numexamen= aplica.FindByMaxExamen(numid);
				session.setAttribute("episodio", ""+numexamen);
			}
		}else{
			out.println("[{\"resp\":\"Error al generar Trámite\"}]");
		}
     
  }catch(Exception e){
  	e.printStackTrace();
     
  }




///Agregando Examen////



%>