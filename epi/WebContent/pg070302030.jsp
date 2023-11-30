<%/**
 * Title: Envío del Laboratorio
 * Description: Envío del Laboratorio
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Mauricio Delgadillo
 * @version 1.0
 * Clase:pg070302030CFG
 */%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
  pg070302030CFG  clsConfig = new pg070302030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070302030.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070302030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "NIM|Fecha de Recolección|";    // modificar
  String cCveOrdenar  = "iCveMuestra|dtRecoleccion|";  // modificar
  String cDscFiltrar  = "No Aplica|";    // modificar
  String cCveFiltrar  = "No Aplica|";  // modificar
  String cTipoFiltrar = "8|";                // modificar


  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  /*String cUpdStatus  = clsConfig.getUpdStatus();*/
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "PermCombos.js"%>"></SCRIPT>
<script language="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
  
 function fHieleras(){
	 	var form = document.forms[0];
	 	var n1 = prompt("Numero de hielera que se asignara");
	    var n2 = prompt("Rango de muestras valor 1");
	    var n3 = prompt("Rango de muestras valor 2");
	    var contador = 0;
	    for(var i=n2; i <= n3; i++){
	    	var id = "HS"+i+"";
	    	if (document.getElementById(id)){
	        	//alert("existe = "+id);
	        	if(contador <= 50){
	        		document.getElementById(id).value = n1;
	        	}
	        	contador++;
		  	}
	    }
	    //alert(contador);
 } 
 
 function fMensajeria(x){
	 	var form = document.forms[0];
	    /*var n2 = prompt("Rango de muestras valor 1");
	    var n3 = prompt("Rango de muestras valor 2");
	    for(var i=n2; i <= n3; i++ ){
	    	var id = "idM"+i+"";
	    	if (document.getElementById(id)) {
	        		document.getElementById(id).selectedIndex=x;
		  	}
	    }*/
} 
 
 function fMensajeriaIguales(x){
	 	var form = document.forms[0];
	    var n2 = form.hdListaMuestras.value;
	    var n3 = form.iCveTipoEnvio.value;
	    //alert(n2);
	    var elem = n2.split('/');
	    var p;
		if(n3 == 2){
			//alert("paqueteria")
			p = parseInt(x)+1;
		}else{
			p = parseInt(x)+1;
		}
		//alert("p="+p);
	    for(var i=0; i <= elem.length; i++ ){
	    	var id = "idM"+elem[i]+"";
	    	//alert(id);
	    	if (document.getElementById(id)) {
	        		document.getElementById(id).selectedIndex=p;
		  	}
	    }
} 
 
 
  
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = "" + bs.getFieldValue("iCveEnvio", "");
       cPropiedad = "" + bs.getFieldValue(" ", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <!--input type="hidden" name="hdOPPbaRapida" value="<%=cPropiedad%>" -->
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ 
  
	  TDToxMensajeria dToxMensajeria = new TDToxMensajeria();
      Vector vcMensajeria = new Vector();
      vcMensajeria = dToxMensajeria.FindByAll();
  
  %>
  <tr>
    <td valign="top">
                          <input type="hidden" name="hdBoton" value="">
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><%
                                   TCreaBoton bBtn = new TCreaBoton();
                                   TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                                   TFechas dtFechas = new TFechas();
                                   String cBoton = "";
                                   int iPAnio = 0;
                                   int iPUniMed = 1;
                                   int iEnvio = 0;
                               %>
                              <td colspan="9" class="ETablaT">Búsqueda del Envío
                                <% //TDSistema dSistema = new TDSistema();
                                   //out.println(vEti.SelectOneRowSinTD("SLSSistema", "document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='CambioSist';document.forms[0].submit();", dSistema.FindByAll(), "iCveSistema", "cNombre", request, "99"));
                                %>
                              </td>
                             </tr>
                            <%
                                      out.println("<tr>");
                                     out.println(vEti.Texto("EEtiqueta","Año:"));
                                     %>
                                     <td class="ETabla">
                                     <select name="iAnio" size="1">
                                     <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                         String cAnio = "0";
                                         if (request.getParameter("iAnio") == null)
                                            cAnio = "0";
                                         else
                                            cAnio = request.getParameter("iAnio");
                                          if (cAnio.compareTo("" + i) == 0){
                                           out.print("<option value = '" + i + "' selected>" + i + "</option>");
                                           iPAnio = i;
                                            }
                                          else
                                           out.print("<option value = '" + i + "' >" + i + "</option>");
                                        }
                                     %>
                                     </select>
                                     <%
                                     if (request.getParameter("iAnio") == null)
                                         iPAnio = iAnio;
                                     out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                     out.println("<td class='ECampo'>");
                                     TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                     //Laboratorio
                                     TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
                                     TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
                                     Vector vcTDGRLUniMed = new Vector();
                                     String where = " where LLaboratorio = 1 ";
                                     String Orderby = "";
                                     
                                     TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                     out.println(vEti.SelectOneRowSinTD("iCveUniMed", "llenaSLT(1,"+ iPAnio + ",this.value,'',document.forms[0].iCveEnvio);",dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso != 3 "),"iCveUniMed", "cDscUniMed", request, "0", true));
                                     out.println("</td>");

                                     if (request.getParameter("iCveUniMed") != null){
                                        iPUniMed = Integer.parseInt(request.getParameter("iCveUniMed"));
                                     }

                                      String cEnvio = "0";

                                     if (request.getParameter("iCveEnvio") != null){
                                        out.println(vEti.Texto("EEtiqueta","Envío:"));
                                        Vector vcEnviosNE = new Vector();
                                        TDEnvio dEnvio = new TDEnvio();
                                        vcEnviosNE = dEnvio.FindBydtEnvio(iPAnio, iPUniMed);
                                        Object obEnvio;
%>
                                        <td class="ETabla">
                                        <select name="iCveEnvio" size="1">
                                        <option value='0'>Envío Nuevo</option>
<%
                                        String cRequest = "0";
                                        if (request.getParameter("iCveEnvio") == null)
                                           cRequest = "0";
                                        else
                                           cRequest = request.getParameter("iCveEnvio");

                                        if (request.getParameter("iCveEnvio")!=null && request.getParameter("iCveEnvio").compareToIgnoreCase("0")==0 &&
                                           (request.getParameter("hdBoton").compareTo("Guardar") == 0)){
                                            cRequest = cClave2;
                                        }
                                        else
                                            cRequest = request.getParameter("iCveEnvio");

                                        if (vcEnviosNE.size()>0){
                                           for (int i = 0;i < vcEnviosNE.size();i++){
                                              obEnvio =  vcEnviosNE.get(i);
                                              TVEnvio vEnvio = (TVEnvio) obEnvio;
                                              if (cRequest.toString().compareToIgnoreCase("" + vEnvio.getICveEnvio()) == 0){
                                                 out.print("<option value = '" + vEnvio.getICveEnvio() + "' selected>" + vEnvio.getICveEnvio() + "</option>");
                                                 cEnvio = "" + vEnvio.getICveEnvio();
                                                 iEnvio = vEnvio.getICveEnvio();
                                              }
                                              else
                                                out.print("<option value = '" + vEnvio.getICveEnvio() + "' >" + vEnvio.getICveEnvio() + "</option>");
                                           }
                                        }
                                     }
                                     else{
                                        out.println(vEti.Texto("EEtiqueta","Envío:"));
                                        out.println("<td class='ECampo'>");
                                        out.println("<SELECT NAME='iCveEnvio' SIZE='1'>");
                                        out.println("<option value='-1'>Datos no disponibles</option>");
                                        out.println("</SELECT>");
                                        out.println("</td>");
                                     }
                                     %>
                                     </select>
                                     <%
                                      out.println("<td>");
                                      out.println(bBtn.clsCreaBoton(vEntorno, 1, "Cancelar",
                                      "bBuscar", vEntorno.getTipoImg(),
                                      "Retorno a Búsqueda","Cancelar", vParametros));
                                      out.println("</td>");
                                      out.println("</tr>");
                                     out.println("</table>");
               // Tabla de Captura

                             if (cEnvio.compareTo("0") == 0 && request.getParameter("hdBoton").compareTo("Guardar") != 0){ // Modificar de acuerdo al catálogo específico
                            %>
                                &nbsp;
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr>
                               <td colspan="8" class="ETablaT">Información del Envío
                               </td>
                              </tr>
                             <%
                                TDTipoEnvio dTipoEnvio = new TDTipoEnvio();
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10,"dtEnvio", dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/"), 4, 
                                		"{forma = document.forms[0];fActualizaCombo('3',forma,this,forma.iCveModulo,this.value,0,1 );}", "", true, true, true));
                                out.println("</tr>");
                                /*out.println("<tr>");
                                out.println(vEti.Texto("EEtiqueta","Tipo de Envío:"));
                                out.println("<td class='ECampo'>");
                                out.println(vEti.SelectOneRowSinTD("iCveTipoEnvio","",dTipoEnvio.FindByAllSinAereo(), "iCveTipoEnvio", "cDscTipoEnvio", request, "0"));
                                out.println("</td>");
                                out.println(vEti.Texto("EEtiqueta","Tipo de Envío:"));
                                out.println("<td class='ECampo'>");
                                out.println(vEti.SelectOneRowSinTD("iCveTipoEnvio","",dTipoEnvio.FindByAllSinAereo(), "iCveTipoEnvio", "cDscTipoEnvio", request, "0"));
                                out.println("</td>");
                                out.println("</tr>");
                                */
                                %>
                                
                                
                                
                                
                                
                                <tr>
    							<td class="EEtiqueta">Tipo de Envío::</td>
    							<td class="ETabla"><%=vEti
    						.SelectOneRowSinTD2(
    								"iCveTipoEnvio",
    								"{forma = document.forms[0];fActualizaComboM('3',forma,this,forma.iCveMensajeria,this.value,0,1 );}",dTipoEnvio.FindByAllSinAereo(), "iCveTipoEnvio",
    								"cDscTipoEnvio", request, "0", true)%>
    								<%
    									int iCveTipoEnvio = (request.getParameter("iCveTipoEnvio") != null && request
    												.getParameter("iCveTipoEnvio").toString().length() > 0) ? Integer
    												.parseInt(request.getParameter("iCveTipoEnvio").toString())
    												: 0;
    												
    									String cWhere = "";			
    									if (iCveTipoEnvio > 2){
    										cWhere = " Where iCveMensajeria = 0 ";
    									}else{
    										cWhere = " Where iCveMensajeria > 0 ";
    									}
    								%>
    							</td>
    							<td class="EEtiqueta">Mensajería:</td>
    							<td class="ETabla"><%=vEti.SelectOneRowSinTD2(
    						"iCveMensajeria",
    						"fMensajeriaIguales(this.value)",
    						//vUsuario.getModuloFUP(iCveUniMed,Integer.parseInt("1")),
    						dToxMensajeria.FindByAll(cWhere),
    						"iClave",
    						"cDescripcion", request, "0", true)%>
    							</td>
    						</tr>
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                <%
                                
                                out.println("<tr>");
                                out.println(vEti.TextoCS("EEtiqueta","Laboratorio:",1));
                                out.println("<td colspan=\"3\" class='ECampo'>");
                                //out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "",dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso = " + vParametros.getPropEspecifica("ToxProcesoLab")),"iCveUniMed", "cDscUniMed", request, "0", true));
                                out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "",dGRLUniMed.FindByAll(where, Orderby),"iCveUniMed", "cDscUniMed", request, "0", true));
                                out.println("</td>");
                                out.println("</tr>");
                                out.println("<tr>");
                                out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,5,"cObsEnvio","",0,"","",false,true,true));
                                out.println("</tr>");
                                out.println("</table>");
                               %>
                                &nbsp;
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                  <td colspan="9" class="ETablaT">Muestras para Asociar a un Envío</td>
                                 </tr>
                                 <tr>
                                 <td class="ETablaT">Envío</td>
                                 <td class="ETablaT">Recolección</td>
                                 <td class="ETablaT">NIM</td>
                                 <td class="ETablaT">Proceso</td>
                                 <td class="ETablaT">Motivo</td>
                                 <td class="ETablaT">Selección</td>
                                 <td class="ETablaT">Eliminar</td>
                                 <!-- <td class="ETablaT">Hielera</td> -->
                                 <td class="ETablaT">Mensajería</td>
                                 </tr>
                             <%

                                if (iPAnio == 0 )
                                  iPAnio = iAnio;
                                Vector vcMuestra = null;
                                TDMuestra dMuestra = new TDMuestra();
                                String cOrden ;
                                if(request.getParameter("hdCOrdenar") != null)
                                   cOrden = request.getParameter("hdCOrdenar").toString();
                                else
                                   cOrden = " order by dtRecoleccion ";
                                vcMuestra = dMuestra.FindMuestraNE(iPAnio,iPUniMed,iEnvio, cOrden );
                                
                                
                                Object obElemento;
                                String cSeleccion = "";
                                String ListaMuestras = "";
                                int i;

                                for (i = 0;i < vcMuestra.size();i++){
                                   obElemento =  vcMuestra.get(i);
                                   TVMuestra vMuestra = (TVMuestra) obElemento;
                                   out.println("<tr>");
                                   out.println(vEti.Texto("ETablaR","" + vMuestra.getICveEnvio()));
                                   out.println(vEti.Texto("ETablaC","" + dtFechas.getFechaDDMMYYYY(vMuestra.getDtRecoleccion(),"/")));
                                   /*                        */
                                      String subAnio = "";
                                      subAnio = "" + iPAnio;
                                      subAnio = subAnio.substring(2);
                                      //System.out.println("2-.- var iPAnio :" + iPAnio);
                                      //System.out.println("2-.- var subAnio :" + subAnio);
 
                                        //out.println(vEti.Texto("ETablaR","" + subAnio + vMuestra.getICveMuestra()));
                                        out.println(vEti.Texto("ETablaR","" + vMuestra.getICveMuestra()));
                                      /*                       */
                                   out.println(vEti.Texto("ETabla","" + vMuestra.getCDscProceso()));
                                   out.println(vEti.Texto("ETabla","" + vMuestra.getCDscMotivo()));
                                   cSeleccion = "" + vMuestra.getICveEnvio();
                                   if ((cSeleccion.compareTo("null") == 0) || (cSeleccion.compareTo("0") == 0) ){
                                   out.println("<td class='ETablaC'>");
                                   out.println("<input type='checkbox' name='S"+ vMuestra.getICveMuestra() + "' value='ON')");
                                   out.println("</td>");
                                   out.println("<td class='ETablaC'>");
                                   out.println("&nbsp;");
                                   out.println("</td>"); 

                               		ListaMuestras = ListaMuestras + Integer.toString(vMuestra.getICveMuestra()) + "/";
                                   
                                   //out.println("<td class='ETablaC'>");
                                   //out.println("<input type='text' size='2' name='HS"+ vMuestra.getICveMuestra() + "' value='' onclick=\"fHieleras()\")");
                                   //out.println("</td>");
                                   out.println("<td class='ETablaC'>");
                                   //out.println("<input type='text' size='2' name='MS"+ vMuestra.getICveMuestra() + "' value='')");
                                   //out.println(vEti.SelectOneRowSinTD("iCveMensajeria1","",vcMensajeria, "iCveMensajeria", "cDscMensajeria", request, "0"));
                                   out.println(vEti.SelectOneRowSinTD2("M"+vMuestra.getICveMuestra()+"",
												"fMensajeria(this.value)",vcMensajeria,
												"iCveMensajeria", "cDscMensajeria", request, "0",
												true));
                                   out.println("</td>");
                                   
                                   }
                                   else{
                                   out.println(vEti.Texto("ETablaC","Asignada"));
                                   out.println("<td class='ETablaC'>");
                                   out.println("<input type='checkbox' name='D"+ vMuestra.getICveMuestra() + "' value='ON')");
                                   out.println("</td>");
                                   

                                   //String Hielera = dMuestra.FindMuestraHielera(iPAnio,iPUniMed,iEnvio, vMuestra.getICveMuestra())+"";
                                   String Mensajeria = dMuestra.FindMuestraMensajeria(iPAnio,iPUniMed,iEnvio, vMuestra.getICveMuestra())+"";
                                   
                                   //out.println(vEti.Texto("ETablaC",Hielera+""));
                                   out.println(vEti.Texto("ETablaC",Mensajeria+""));
                                   
                                   }

                                   out.println("</tr>");
                                }
                                
                                %>
                                <input type="hidden" name="hdListaMuestras" value="<%=ListaMuestras%>">

                                <%
                                   out.println("</table>");
                               }
                               else{
                                  if (bs != null){
                                    out.println("<p align='center'>");
                                    out.print(vEti.clsAnclaTexto("EAncla","Emitir Envío","JavaScript:fEnvio();", ""));
                                     %>
                                &nbsp;
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr>
                               <td colspan="8" class="ETablaT">Información del Envío
                               </td>
                              </tr>
                             <%

                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10,"dtEnvio", dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/"), 4, "", "", true, true, false));
                                out.print(vEti.EtiCampo("EEtiqueta", "Tipo de Envío:", "ECampo", "text", 25, 50, "iCveTipoEnvio", bs.getFieldValue("cDscTipoEnvio","&nbsp;").toString(), 3, "", "", true, false, false));
                                out.println("</tr>");

                                out.println("<tr>");
                                out.println(vEti.TextoCS("EEtiqueta","Laboratorio:",2));
                                out.println(vEti.TextoCS("ECampo",bs.getFieldValue("cDscLaboratorio","&nbsp;").toString(),2));
                                out.println("</tr>");

                                out.println("<tr>");
                                out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,5,"cObsEnvio",bs.getFieldValue("cObsEnvio","&nbsp;").toString(),0,"","",false,true,false));
                                out.println("</tr>");
                                out.println("</table>");

                             %>
                              &nbsp;
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                  <td colspan="9" class="ETablaT">Muestras para Asociar a un Envío</td>
                                 </tr>
                                 <tr>
                                 <td class="ETablaT">Envío</td>
                                 <td class="ETablaT">Recolección</td>
                                 <td class="ETablaT">NIM</td>
                                 <td class="ETablaT">Proceso</td>
                                 <td class="ETablaT">Motivo</td>
                                 <td class="ETablaT">Selección</td>
                                 <td class="ETablaT">Eliminar</td>
                                 <!--<td class="ETablaT">Hielera</td> -->
                                 <td class="ETablaT">Mensajería</td>
                                 </tr>
                             <%
                                Vector vcMuestra = null;
                                TDMuestra dMuestra = new TDMuestra();
                                String cOrden ;
                                if(request.getParameter("hdCOrdenar") != null){
                                   cOrden = request.getParameter("hdCOrdenar").toString();
                                }else{
                                   cOrden = " order by dtRecoleccion ";
                                   }

                                
                                vcMuestra = dMuestra.FindMuestraNE(iPAnio,iPUniMed,iEnvio, cOrden);
                                Object obElemento;
                                String cSeleccion = "";
                                int i;
                                //System.out.println(vcMensajeria.size());
                                if (vcMuestra.size()>0){
                                   for (i = 0;i < vcMuestra.size();i++){
                                      obElemento =  vcMuestra.get(i);
                                      TVMuestra vMuestra = (TVMuestra) obElemento;
                                      out.println("<tr>");
                                      out.println(vEti.Texto("ETablaR","" + vMuestra.getICveEnvio()));
                                      out.println(vEti.Texto("ETablaC","" + dtFechas.getFechaDDMMYYYY(vMuestra.getDtRecoleccion(),"/")));
                                      /*                        */
                                      String subAnio = "";
                                      subAnio = "" + iPAnio;
                                      subAnio = subAnio.substring(2);
                                      //System.out.println("2.-- var iPAnio :" + iPAnio);
                                      //System.out.println("2.-- var subAnio :" + subAnio);
                                      //out.println(vEti.Texto("ETablaR","" + subAnio + vMuestra.getICveMuestra()));
                                      out.println(vEti.Texto("ETablaR","" + vMuestra.getICveMuestra()));

                                      /*                       */
                                      out.println(vEti.Texto("ETabla","" + vMuestra.getCDscProceso()));
                                      out.println(vEti.Texto("ETabla","" + vMuestra.getCDscMotivo()));
                                      cSeleccion = "" + vMuestra.getICveEnvio();
                                      if ((cSeleccion.compareTo("null") == 0) || (cSeleccion.compareTo("0") == 0) ){
                                      out.println("<td class='ETablaC'>");
                                      out.println("<input type='checkbox' name='S"+ vMuestra.getICveMuestra() + "' value='ON')");
                                      out.println("</td>");
                                      out.println("<td class='ETablaC'>");
                                      out.println("&nbsp;");
                                      out.println("</td>");

                                      //out.println("<td class='ETablaC'>");
                                      //out.println("<input type='text' size='2' name='HS"+ vMuestra.getICveMuestra() + "' value='' onclick=\"fHieleras()\")");
                                      //out.println("</td>");
                                      out.println("<td class='ETablaC'>");
                                      out.println(vEti.SelectOneRowSinTD2("M"+vMuestra.getICveMuestra()+"",
												"fMensajeria(this.value)",vcMensajeria,
												"iCveMensajeria", "cDscMensajeria", request, "0",
												true));
                                      out.println("</td>");
                                      
                                      }
                                      else{
                                         out.println(vEti.Texto("ETablaC","Asignada"));
                                         out.println("<td class='ETablaC'>");
                                         out.println("<input type='checkbox' name='D"+ vMuestra.getICveMuestra() + "' value='ON')");
                                         out.println("</td>");
                                         //out.println("<td class='ETablaC'>");

                                         String Hielera = dMuestra.FindMuestraHielera(iPAnio,iPUniMed,iEnvio, vMuestra.getICveMuestra())+"";
                                         String Mensajeria = dMuestra.FindMuestraMensajeria(iPAnio,iPUniMed,iEnvio, vMuestra.getICveMuestra())+"";
                                         
                                         //out.println(vEti.Texto("ETablaC",Hielera+""));
                                         out.println(vEti.Texto("ETablaC",Mensajeria+""));

                                      }
                                      out.println("</tr>");
                                   }
                                }
                                else{
                                  out.println("<tr>");
                                  out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 7,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                  out.println("</tr>");
                                }
                                out.println("</table>");

                                 }
                                 else{
                                %>
                                 &nbsp;
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("</table>");
                                 }
                             }
                            %>
                           <tr>
                           </tr>
                          </table><% // Fin de Datos %>

  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

