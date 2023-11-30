<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<%@ page import="java.text.*"%>



<%
  pg070104002CFG  clsConfig = new pg070104002CFG();                 // modificar

  
  System.out.println("InicioProcesoEpi");
  System.out.println(session.getAttribute("id"));
  System.out.println(session.getAttribute("inumExamen"));
  System.out.println(session.getAttribute("cGenero"));

  try{
 	 String respuest = clsConfig.Guardar2(session.getAttribute("id")+"", session.getAttribute("inumExamen")+"", session.getAttribute("cGenero")+"", "1");
 		if(respuest.length() > 2){  
 			out.println("[{\"resp\":\""+respuest+"\"}]");
 			if(respuest.equals("success")){
 				TDEXPExamAplica aplica = new TDEXPExamAplica();
 				//numexamen= aplica.FindByMaxExamen(numid);
 				//session.setAttribute("episodio", ""+numexamen);
 			}
 		}else{
 			out.println("[{\"resp\":\"Error al Iniciar el Proceso EPI\"}]");
 		}
      
   }catch(Exception e){
   	e.printStackTrace();
      
   }

  
  
  %>