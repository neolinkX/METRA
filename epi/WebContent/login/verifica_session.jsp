<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.micper.seguridad.vo.TVUsuario"%>
    <%@ page import="gob.sct.medprev.SEDetPerCFG"%>
    <%@ page import="java.util.*"%>
    <%
    
    
    TVUsuario vUsuario = (TVUsuario) request.getSession(true)
    .getAttribute("UsrID");    
    SEDetPerCFG  clsConfig = new SEDetPerCFG();
    clsConfig.verAcceso2();
    //System.out.println("***********VALIDO ************="+clsConfig.getAccesoValido());
    /*if(clsConfig.getAccesoValido()){ 
    	System.out.println(vUsuario.getICveusuario());
    }*/
    
    
    //////Calcular Tiempo de Session
    long totalSum = 0;
    long totalSum2 = 0;
        //long startTime = System.currentTimeMillis();
        long startTime = session.getCreationTime();
        long startTime2 = session.getCreationTime()+300000;
        totalSum+= (System.currentTimeMillis()-startTime);
        totalSum2= (System.currentTimeMillis()-startTime2);
/*
        System.out.println("##########################################################");
        System.out.println(session.getMaxInactiveInterval());
        System.out.println(session.getLastAccessedTime());
        System.out.println(session.getCreationTime());
        System.out.println(totalSum);
        System.out.println(totalSum2);
        System.out.println("##########################################################");
  */    
        if(request.getSession(true).getAttribute("UsrID")==null){    	    
    	    //response.sendRedirect("pg0700000.jsp");
    	    //return;    	  
        	//System.out.println(totalSum);
        	//System.out.println("Fin="+startTime);
        	out.println("[{\"resp\":\"intime\",\"tiempo\":"+startTime+"}]");
        	response.sendRedirect("login.jsp");
      } else{
    	  out.println("[{\"resp\":\"intime\",\"tiempo\":1084}]");    	  
      }
    
    
//out.println("[{\"resp\":\"intime\",\"tiempo\":1084}]");


//out.println("[{\"resp\":\"acceso_incorrecto\"}]");
 %>