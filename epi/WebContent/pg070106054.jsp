<%/**
 * Title: pg070106054.jsp
 * Description:
 * Copyright:
 * Company:
 * @author Ivan Santiago Mï¿½ndez
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {

            String paramName = (String) en.nextElement();
            //System.out.println(paramName + " = " + request.getParameter(paramName) + " ");

        }

  pg070106054CFG  clsConfig = new pg070106054CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106054.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106054.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(false);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070106054.jsp";       // modificar
  String cOperador    = "0";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar

  String cEstatusIR = "";            // modificar
  if(request.getParameter("hdBoton").compareTo("Guardar")==0)
    cEstatusIR   = "Imprimir";            // modificar
  else
    cEstatusIR   = "Reporte";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "";
  String cNavStatus  = "Hidden";
  String cOtroStatus = "";
  String cCanWrite   = "";
  String cSaveAction = "";
  String cDeleteAction = "";
  String cClave    = "";
  String cPosicion = "";
  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  TFechas dtFecha = new TFechas();
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
%>
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070104001.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fBuscaExamenBorrar(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    cVMsg = '';
    if (form.iCveExpediente.value != null && form.iCveExpediente.value.length > 0 &&
    		form.iNumExamen.value != null && form.iNumExamen.value.length > 0)
        {
       if (form.iCveExpediente)
         cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
         if (cVMsg != ''){
            alert("Datos no Validos: \n" + cVMsg);
         }else{
            //form.iNumExamen.value = "";
            form.hdType.value = "E";
            form.hdBoton.value ="Primero";
            form.hdRowNum.value = 0;
            form.submit();
         }
    }else{
       if (form.iCveExpediente.value == null || form.iCveExpediente.value.length == 0){
           cVMsg = cVMsg + "- Ingrese un Número de Expediente \n";
       }
       if (form.iNumExamen.value == null || form.iNumExamen.value.length == 0){
           cVMsg = cVMsg + "- Ingrese un Número de Examen \n";
       }
       if (cVMsg != ''){
         alert("Datos no Validos: \n" + cVMsg);
       }
    }
}


</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracterï¿½sticas generales de las pï¿½ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdType" value="">
  <input type="hidden" name="iCveServicio" value="2">
  <input type="hidden" name="lIniciado" value="1">
  <input type='hidden' name='hdBuscado' value='0'>
  <input type='hidden' name='hdUsuario' value='71'>
  <input type='hidden' name='iCveModulo' value='-1'>
  <input type='hidden' name='iCveUniMed' value='-1'>
  <input type="hidden" name="hdMacAddress" value="">
  <input type="hidden" name="hdIpAddress" value="">
  <input type="hidden" name="hdComputerName" value="">

<%  if (request.getParameter("hdICvePersonal") != null){
%>
       <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal").toString()%>">
<%
    }else{
%>
        <input type="hidden" name="hdICvePersonal" value="">
<%
    } 
%>
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <%
  String RESULTADOBORRADO ="";
  if(clsConfig.getAccesoValido()){
       Vector ReglasExpediente = new Vector();
       if (request.getParameter("hdType") != null){
	       if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
	           //vPerDatos=clsConfig.findExamenToDelete();
	    	   ReglasExpediente = clsConfig.findExamenToReglas();
	        }
       }
       
%>

  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
        
                            <tr>
                              <td colspan="5" class="ETablaT">&nbsp;&nbsp;&nbsp;&nbsp; CONSULTA DE REGLAS POR EXPEDIENTE &nbsp;&nbsp;&nbsp;&nbsp;
                              </td>
                            </tr>

                              <%

                                 if(ReglasExpediente!=null && request.getParameter("iCveExpediente")!=null){
                                    out.print("<tr>");
                                    
                                    out.print("</tr>");

                                    out.print("<td class=\"EEtiqueta\">Expediente:</td>");
                                    out.print("<td>");
                                    out.print("<Input Type=\"Text\" Value=\""+request.getParameter("iCveExpediente")+"\" Name=\"iCveExpediente\" Size=\"10\" MaxLength=\"10\">");
                                    out.print("</td>");
                                    out.print("<tr>");
                                    out.print("<td class=\"EEtiqueta\">N&uacute;mero Examen</td>");
                                    out.print("<td>");
                                    out.print("<Input Type=\"Text\" Value=\""+request.getParameter("iNumExamen")+"\" Name=\"iNumExamen\" Size=\"10\" MaxLength=\"10\">");
                                    out.print("</td>");
                                 }else{
                                	 out.print("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Expediente:", "ECampo", "text", 10, 10,"iCveExpediente", "", 0, "", "", false, true,true, request));
                                	 out.print("</tr>");

                                     out.print("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "N&uacute;mero Examen:", "ECampo", "text", 10, 10,"iNumExamen", "", 0, "", "", false, true,true, request));
                                    
                                 }
                                 
                                 out.print("</tr>");
                                 out.print("<tr>");
                                 out.print("<td align=\"center\" colspan=\"5\">");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Expediente","JavaScript:fBuscaExamenBorrar();", "Buscar Expediente",""));
                                 out.print("</td>");
                                 out.print("</tr>");
                                 
                              %>
                          </table>&nbsp;
                            <%
                        //if(vPerDatos!=null){
                        if(ReglasExpediente!=null){
                         	TVEXPRegSin vEXPRegSin= new TVEXPRegSin();
	                          if(ReglasExpediente.size()>0){
	                        	  for(int i = 0; i < ReglasExpediente.size(); i++ ){
	                            		vEXPRegSin = (TVEXPRegSin)  ReglasExpediente.get(i);
				                        %>
				                        <script language>
				                          form = document.forms[0];
				                          form.hdBuscado.value = 1;
				                        </script>
				                            <h3><%=RESULTADOBORRADO%> </h3>
				                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
				                            <tr>
				                              <td colspan="4" class="ETablaT">REGLA CAUSAL DE LA NO APTITUD
				                              </td>
				                            </tr>
				                            <tr>
				                              <td class="EEtiqueta">Modo de Transporte:</td>
				                              <td class="ECampo"><%=vEXPRegSin.getCMdoTrans()%></td>
				                              <td class="EEtiqueta">Categoría:</td>
				                              <td class="ECampo"><%=vEXPRegSin.getCCategorias()%></td>
				                            </tr>
				                            <tr>
				                              <td class="EEtiqueta">Servicio:</td>
				                              <td class="ECampo"><%=vEXPRegSin.getCServicios()%></td>
				                              <td class="EEtiqueta">Rama:</td>
				                              <td class="ECampo"><%=vEXPRegSin.getCRama()%></td>
				                            </tr>
				                            <tr>
				                              <td class="EEtiqueta">Sintoma:</td>
				                              <td class="ECampo" colspan="3"><%=vEXPRegSin.getCPregunta()%></td>
				                            </tr>
				                            <tr>
					                             <%///////////////// Pregunta Logica /////////////
					                            	if(vEXPRegSin.getICveTpoResp() == 1){
					                            		String Respuesta = "No";
					                            		if(vEXPRegSin.getLogicaR()==1){
					                            			Respuesta =  "Si";
					                            		}
					                           	%>
					                           	  <td class="EEtiqueta">Información Capturada:</td>
					                              <td class="ECampo" colspan="3"><%=Respuesta%></td>
					                            <%		
					                            	}
					                            
					                            ///////////////// Pregunta Numerica /////////////
					                            	if(vEXPRegSin.getICveTpoResp() == 3){
					                           	%>
					                           	  <td class="EEtiqueta">Información Capturada:</td>
					                              <td class="ECampo" colspan="3"><%=vEXPRegSin.getIDvalorIni()%></td>
					                            <%		
					                            	}
					                             ///////////////// Pregunta Combo /////////////
					                            	if(vEXPRegSin.getICveTpoResp() == 8){
					                            		String Respuesta = "";
					                            	
						                            	String cwhere ="icveservicio = "+vEXPRegSin.getICveServicio()+" and "+
						                            			"icverama = "+vEXPRegSin.getICveRama()+" and "+
						                            			"icvesintoma = "+vEXPRegSin.getICveSintoma()+" and "+
						                            			"iorden = "+vEXPRegSin.getCCaracterR()+" ";
					                            		TDMEDRespSint dRespSint= new TDMEDRespSint();
					                            		Respuesta = dRespSint.FindByResp(cwhere);
					                           	%>
					                           	  <td class="EEtiqueta">Información Capturada:</td>
					                              <td class="ECampo" colspan="3"><%=Respuesta%></td>
					                            <%		
					                            	}
					                            %>
				                            </tr>
				                             <tr>
				                             <% 
				                             	String Regla = "";
				                             	if(vEXPRegSin.getIMayorA() > 0 || vEXPRegSin.getIMayorA() < 0 ){
				                             		Regla = Regla + "Mayor a "+vEXPRegSin.getIMayorA();
				                             	}
				                             	if(vEXPRegSin.getIMenorA() > 0 || vEXPRegSin.getIMenorA() < 0 ){
				                             		if(Regla.length()>2){
				                             			Regla = Regla + "  |  Menor a "+vEXPRegSin.getIMenorA();
				                             		}else{
				                             			Regla = Regla + "Menor a "+vEXPRegSin.getIMenorA();
				                             		}
				                             	}
				                             	if(vEXPRegSin.getIIgualA() > 0 || vEXPRegSin.getIIgualA() < 0 ){
				                             		if(Regla.length()>2){
				                             			Regla = Regla + "  |  Igual a "+vEXPRegSin.getIIgualA();
				                             		}else{
				                             			Regla = Regla + "Igual a "+vEXPRegSin.getIIgualA();
				                             		}
				                             	}
				                             	
				                             	if(vEXPRegSin.getICveTpoResp() == 8){
				                             		String Respuesta = "";					                            	
					                            	String cwhere ="icveservicio = "+vEXPRegSin.getICveServicio()+" and "+
					                            			"icverama = "+vEXPRegSin.getICveRama()+" and "+
					                            			"icvesintoma = "+vEXPRegSin.getICveSintoma()+" and "+
					                            			"iorden = "+vEXPRegSin.getIIgualA()+" ";
				                            		TDMEDRespSint dRespSint= new TDMEDRespSint();
				                            		Respuesta = dRespSint.FindByResp(cwhere);
				                            		Regla =  "Igual a "+Respuesta;
				                             	}
				                             	
				                             	if(vEXPRegSin.getICveTpoResp() == 1){
				                             		String Respuesta = "No";					                            	
					                            	if(vEXPRegSin.getLogica() == 1){
					                            		Respuesta = "Si";			
					                            	}
				                            		Regla =  "Igual a "+Respuesta;
				                             	}
				                             
				                             %>
				                              <td class="EEtiqueta">Parámetros de la regla:</td>
				                              <td class="ECampo" colspan="3"><%=Regla%></td>
				                            </tr>
				                            
				                            
				                            <tr>
				                              <td class="EEtiqueta">Alerta:</td>
				                              <td class="ECampo" colspan="3"><%=vEXPRegSin.getCAlerta()%></td>
				                            </tr>
				                            <tr>
				                              <td class="EEtiqueta">Usuario que genero la regla:</td>
				                              <td class="ECampo"><%=vEXPRegSin.getCUsuario()%></td>
				                              <td class="EEtiqueta">Regla vigente a partir de:</td>
				                              <td class="ECampo"><%= vEXPRegSin.getDtGenerado() %></td>
				                            </tr>
				                            <tr>
				                            </tr>
				                          </table>
				                        <%
	                          	}///ciclo for
                          }else{
	                          %>
	                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
	                          <%
	                                out.println("<tr>");
	                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionados", 3, "", "", true, true, false));
	                                out.println("</tr>");
	                          %>
	                                </table>
	                          <%
                          }
                        }
                        %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
