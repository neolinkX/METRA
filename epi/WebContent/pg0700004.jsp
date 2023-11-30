<%/**
 * Title:        Administraciï¿½n y Seguridad
 * Description:  Mï¿½dulo de Administraciï¿½n y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S.A. de C.V.
 * @author       LSC. Rafael Miranda Blumenkron
 * @version      1.0
 * Clase:        JSP para solicitar Usuario y Contraseï¿½a, colocando cookie y dirigiendo al menï¿½
 */ %>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.cis.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%/**
* Validacion de sesion iniciada
* @author Ing. Andres Esteban Bernal Muñoz - 16/05/2014
*/
	if(request.getSession(true).getAttribute("UsrID")==null){
		response.sendRedirect("pg0700000.jsp");
   	    return;    	  
     } //Fin valida %>

<html>
<%  // Unica Parte del Cï¿½digo que debe modificarse
  pg0700004CFG  clsConfig = new pg0700004CFG();
%>
<%

  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  clsConfig.runConfig(vEntorno);
  clsConfig.outputHeader(vEntorno, vErrores, pageContext, request, response, vParametros);
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  NumberFormat nf = NumberFormat.getNumberInstance();
  nf.setMinimumIntegerDigits(2);
  nf.setMaximumIntegerDigits(2);
  nf.setGroupingUsed(false);
  String cPagUpd = "00005";
  String cPagNav = "00006";
  String cNumModulo = nf.format(vEntorno.getNumModulo());


          //Clase que valida las citas
              pg070102040CFG Citas = new pg070102040CFG();
              TDCARGACITAS dCARGACITAS = new TDCARGACITAS();
            //Clase que valida el cierre de examenes como no aptos
              TDDicNoApto dDicNoApto = new TDDicNoApto();              
              Calendar calendar = Calendar.getInstance();
              Calendar hoy = Calendar.getInstance();
              Calendar hoy2 = Calendar.getInstance();
              Calendar hoy3 = Calendar.getInstance();
            //Obtenemos la fecha actual
                TFechas oFecha = new TFechas();
                String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");
                String cHoy2 = oFecha.getFechaYYYYMMDD(oFecha.TodaySQL(),"-");
                //System.out.println("###############################");
                //System.out.println(cHoy);
                //System.out.println(cHoy2);
                //System.out.println("###############################");
                  int VerCit = 0;
                  String regresa="";
				
                    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                    int user = vUsuario.getICveusuario();

            // verificacion de carga de cita
             int hora = calendar.get(Calendar.HOUR_OF_DAY);




             //System.out.println("hora = " +hora);
         if(hora > 6){
            //System.out.println("CONDICION hora = " +hora);
                VerCit = Citas.VerCCLogin(cHoy);
                 //System.out.println(VerCit);
                    if(VerCit == 0){
                            // //Calculando Timestamp para registrar la carga de citas
                                  int annoAct = calendar.get(Calendar.YEAR);
                                  int mesAct = calendar.get(Calendar.MONTH)+1;
                                  int diaAct = calendar.get(Calendar.DAY_OF_MONTH);
                                  java.util.Date utilDate = new java.util.Date(); //fecha actual
                                  long lnMilisegundos = utilDate.getTime();
                                  java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
                                  String cSQL5 = "insert into EXPBITMOD "
                                               + "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, ldictamen)"
                                               + " values(0, 0, 10, '"+sqlTimestamp+"', 'CARGA DE CITAS = "+cHoy2+"', "+user+", 0)";
                                  dCARGACITAS.Sentencia(cSQL5);
                                  cSQL5 = "insert into EXPBITMOD "
                                               + "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, ldictamen)"
                                               + " values(0, 0, 11, '"+sqlTimestamp+"', 'CARGA DE CITAS SIAF = "+cHoy2+"', "+user+", 0)";
                                  dCARGACITAS.Sentencia(cSQL5);
                                  Citas.CargaCitasLog(user, cHoy);
                                //System.out.println("Error en la carga");
                    }
                    
                  ///Cambiar estatus de Examenes a dictaminados////
                    ////////// VALIDANDO PROPIEDADES //////////////
                    int CierreActivo = 0;
                    int diasCierreUM = 0;
                    int diasCierreTerceros = 0;
				          try{  
				        	  CierreActivo = Integer.parseInt(vParametros.getPropEspecifica("ActivaCierreDeExamenes"));  
					        } catch(NumberFormatException nfe) {  
					        	CierreActivo = 0;  
					        }  
					      try{
					    	  diasCierreUM = Integer.parseInt(vParametros.getPropEspecifica("DiasCierraNoAptoUM"));  
						    } catch(NumberFormatException nfe) {  
						    	diasCierreUM = 0;  
						    }  
					      try{
					    	  diasCierreTerceros = Integer.parseInt(vParametros.getPropEspecifica("DiasCierraNoAptoTerceros"));  
						    } catch(NumberFormatException nfe) {  
						    	diasCierreTerceros = 0;  
						    }  
                    
					if(CierreActivo == 1){
	                    VerCit = dDicNoApto.VerCNALogin(cHoy);
			            if(VerCit == 0){
			            	//System.out.println("iniciando cierre");
			                    ///Cambiar estatus de Examenes a dictaminados////
			                    java.util.Date utilDate = new java.util.Date(); //fecha actual
	                                  long lnMilisegundos = utilDate.getTime();
	                                  java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
	                            //System.out.println("Insertando en ExpBitMod");
			                    String cSQL6 = "insert into EXPBITMOD "
			                                 + "(icveexpediente, inumexamen, ioperacion, dtrealizado, cdescripcion, icveusurealiza, ldictamen)"
			                                 + " values(0, 0, 13, '"+sqlTimestamp+"', 'CERRAR EXAMENES NO APTOS = "+cHoy2+"', "+user+", 0)";
			                    dCARGACITAS.Sentencia(cSQL6);
			                    
			                    //vParametros.getPropEspecifica("RutaProg");
			                   
			         ///Calculo de dias para cierre de dictamen Unidades Medicas
								hoy.add(Calendar.DATE, -diasCierreUM);
								int ano = hoy.get(Calendar.YEAR);
								int mes = hoy.get(Calendar.MONTH)+1;
								int dia = hoy.get(Calendar.DATE);
								String mes2= "";
								String dia2= "";
								if(mes < 10){
								   mes2= "0"+mes;
								   }else{
									   mes2= ""+mes;   
								   }
								if(dia < 10){
									dia2= "0"+dia;
								}else{
									dia2= ""+dia;   
								}
								//System.out.println(ano +"-"+mes2+"-"+dia2);
								String fecha = ano +"-"+mes2+"-"+dia2;
								
					//Formateando Fecha actual en string
								ano = hoy2.get(Calendar.YEAR);
								mes = hoy2.get(Calendar.MONTH)+1;
								dia = hoy2.get(Calendar.DATE);
								mes2= "";
								dia2= "";
								if(mes < 10){
								   mes2= "0"+mes;
								   }else{
									   mes2= ""+mes;    
								   }
								if(dia < 10){
									dia2= "0"+dia;
								}else{
									dia2= ""+dia;   
								}
								//System.out.println(ano +"-"+mes2+"-"+dia2);
								String fecha2 = ano +"-"+mes2+"-"+dia2;
								
					///Calculo de dias para cierre de dictamen Terceros
								hoy3.add(Calendar.DATE, -diasCierreTerceros);
								ano = hoy3.get(Calendar.YEAR);
								mes = hoy3.get(Calendar.MONTH)+1;
								dia = hoy3.get(Calendar.DATE);
								mes2= "";
								dia2= "";
								if(mes < 10){
								   mes2= "0"+mes;
								   }else{
									   mes2= ""+mes;    
								   }
								if(dia < 10){
									dia2= "0"+dia;
								}else{
									dia2= ""+dia;   
								}
								//System.out.println(ano +"-"+mes2+"-"+dia2);
								String fecha3 = ano +"-"+mes2+"-"+dia2;
																
								//System.out.println("Carga en proceso");
			                    dDicNoApto.CierraExaNoApto(user, fecha, fecha2, fecha3);
			                    //System.out.println("fecha = "+fecha);
			                    //System.out.println("fecha2 = "+fecha2);
			                    //System.out.println("fecha3 = "+fecha3);
			                    //System.out.println("Carga terminada");
	                       }
		            }
             }


  String cPagPrin  = request.getParameter("PagPrin");
  String cPagPanel = request.getParameter("PagPanel");
  String cPagFiltro = "00010";
  if (cPagPanel == null)
    cPagPanel = "";
  if (cPagPanel == "")
    cPagPanel = "00007";
  if (cPagPrin == null)
    cPagPrin = "";
  // Verificar la existencia de archivos solicitados
  if (cPagPrin != ""){
    // Cï¿½digo de verificaciï¿½n de existencia de archivos JSP en ejecuciï¿½n
  }
  if (cPagPrin == ""){
    cPagPrin = "pg0700011";
    cPagUpd = cPagNav = cPagPanel = "00007";
  }
%>
<% out.println(new TCreaFunGral().getResultado() + "\n");

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<frameset framespacing="0" border="false" rows="1,1,*" frameborder="0">
    <frameset framespacing="0" border="false" cols="25%,50%,*" frameborder="0">
      <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%= cNumModulo %><%= cPagPanel %>.jsp" scrolling="no" name="FRMOtroPanel" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
      <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%= cNumModulo %><%= cPagUpd %>.jsp" scrolling="no" name="FRMUpdPanel" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
      <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%= cNumModulo %><%= cPagNav %>.jsp" scrolling="no" name="FRMNavPanel" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
    </frameset>
    <frameset framespacing="0" border="false" cols="100%,*" frameborder="0">
       <frame src="<%= vParametros.getPropEspecifica("RutaProg") %>pg<%= cNumModulo + cPagFiltro%>.jsp" scrolling="no" name="FRMFiltro" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
    </frameset>
  <frame src="<%= vParametros.getPropEspecifica("RutaProg") %><%= cPagPrin %>.jsp" scrolling="yes" name="FRMDatos" marginwidth="0" marginheight="0" style="margin: 0px; padding: 0px">
  <noframes>
      <body topmargin="0" leftmargin="0">
  <p>Su navegador no soporta el uso de frames, favor de obtener una versiï¿½n mas reciente</p>
  </body>
  </noframes>
</frameset>
</html>


