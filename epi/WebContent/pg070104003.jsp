<%/**
 * Title: pg070104003.jsp
 * Description:
 * Copyright:
 * Company:
 * @author AG SA
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

  pg070104003CFG  clsConfig = new pg070104003CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104003.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104003.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104003.jsp";       // modificar
  String cOperador    = "1";                   // modificar
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
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
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
  
  String RutaFunc = ""+vParametros.getPropEspecifica("RutaFuncs");
  
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

  function fBuscaExpediente(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    cVMsg = '';
    if (form.iCveExpediente.value != null && form.iCveExpediente.value.length > 0)
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
           cVMsg = cVMsg + "- Ingrese un Numero de Expediente \n";
       }
       if (cVMsg != ''){
         alert("Datos no Validos: \n" + cVMsg);
       }
    }
}

function fLiberaBiometrico(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    if(confirm("¿Estas seguro que deseas borrar este examen?")){
    	var  macAddress = "";
    	var ipAddress = "";
    	var computerName = "";
    	var revision = "C:/Windows/Temp/FingerInfo.jar";
    	var fso = new ActiveXObject("Scripting.FileSystemObject");
    	var folderExistencia = fso.FileExists(revision);
    	 
    	if (BrowserDetect.browser == 'Explorer' && parseInt(BrowserDetect.version,10) >= 9) {
    	//alert("entro a explorer 9 o mayor");
    		if (folderExistencia) {
    			var ts;
    			var ForReading = 1;
    			ts = fso.OpenTextFile("C:\\Windows\\Temp\\FingerPrintInf.txt", ForReading);
    			ipAddress = ts.ReadLine();
    			computerName = ts.ReadLine();
    			macAddress =ts.ReadLine();
    			//Response.Write("Contenido del archivo= '" + s + "'");
    			ts.Close();
    		
    		}

    	} else {
    		var wmi = GetObject("winmgmts:{impersonationLevel=impersonate}");
    		e = new Enumerator(wmi.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration WHERE IPEnabled = True"));
    		for(; !e.atEnd(); e.moveNext()) {
    			var s = e.item();
    			macAddress = s.MACAddress;
    			ipAddress = s.IPAddress(0);
    			computerName = s.DNSHostName;
    		}
    	}
    	form.hdMacAddress.value = macAddress;
    	form.hdIpAddress.value = ipAddress;
    	form.hdComputerName.value = computerName;
    	form.hdType.value = "B";
        form.hdBoton.value ="Primero";
        form.hdRowNum.value = 0;
        form.submit();
    }
}

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracterï¿½sticas generales de las pï¿½ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">

<!-- Efecto texto -->
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS")%>textualizer/project.css" type="text/css">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS")%>textualizer/pygments.css" type="text/css">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS")%>textualizer/textualizer.css" type="text/css">

  
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
  boolean respuesta = false;
  if(clsConfig.getAccesoValido()){
       TVPERDatos vPerDatos = null;
       if (request.getParameter("hdType") != null){
    	   //System.out.println("Op 1");
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
        	  //System.out.println("Op 2");
             vPerDatos=clsConfig.findUser();
          }else{
              if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
            	  //System.out.println("Op 3");
                 vPerDatos=clsConfig.findExpToEliminaBiometricoNAS();
              }
               if (request.getParameter("hdType").toString().equalsIgnoreCase("B")){
            	   //System.out.println("Op 4");
            	   respuesta = clsConfig.LiberaBiometrico();
                 //vPerDatos=clsConfig.LiberaBiometrico();
                 if(respuesta==false){
                	 //System.out.println("Op 5");
                     vPerDatos=clsConfig.findExpToEliminaBiometricoNAS();
                          RESULTADOBORRADO = "NO SE PUDO LIBERERAR LOS BIOMÉTRICOS";
                     }else{
                        RESULTADOBORRADO = "LOS BIOMÉTRICOS FUERON LIBERADOS";
                        //System.out.println("Op 6");
                     }
              }
          }
       }


%>

  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
        <tr><td align="center" colspan="4" class="EResalta">LIBERAR BIOMÉTRICOS DE LA NAS</td></tr>
                            <tr>
                              <td colspan="5" class="ETablaT">Ingrese el expediente que desea liberar
                              </td>
                            </tr>

                              <%
                                 /*out.print("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de solicitud:", "ECampo", "text", 10, 10,3,"dtSolicitado", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                 out.print("</tr>");*/
                                 if(vPerDatos!=null){
                                    /*out.print("<tr>");
                                    out.print("<td class=\"EEtiqueta\">N&uacute;mero Examen</td>");
                                    out.print("<td>");
                                    out.print("<Input Type=\"Text\" Value=\""+vPerDatos.getINumExamen()+"\" Name=\"iNumExamen\" Size=\"10\" MaxLength=\"10\">");
                                    out.print("</td>");
                                    out.print("</tr>");*/
                                    out.print("<tr>");
                                    out.print("<td class=\"EEtiqueta\">Expediente:</td>");
                                    out.print("<td>");
                                    out.print("<Input Type=\"Text\" Value=\""+vPerDatos.getICveExpediente()+"\" Name=\"iCveExpediente\" Size=\"10\" MaxLength=\"10\">");
                                    out.print("</td>");
                                 }else{
                                    /*out.print("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "N&uacute;mero Examen:", "ECampo", "text", 10, 10,"iNumExamen", "", 0, "", "", false, true,true, request));
                                    out.print("</tr>");*/

                                    out.print("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Expediente:", "ECampo", "text", 10, 10,"iCveExpediente", "", 0, "", "", false, true,true, request));
                                 }
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Expediente","JavaScript:fBuscaExpediente();", "Buscar Expediente",""));
                                 out.print("</td>");
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer(1);", "Buscar Personal",""));
                                 out.print("</td>");
                                 out.print("</tr>");
                              %>
                          </table>&nbsp;
                            <%
                        if(vPerDatos!=null){
                          if(vPerDatos.getCNombre() !=null){
                        %>
                        <script language>
                          form = document.forms[0];
                          form.hdBuscado.value = 1;
                        </script>
                            <h3><%
                            if(!RESULTADOBORRADO.equals("")){
                          if(respuesta){%>
                        	  <jsp:include  page = "textualizer.jsp" > 
    							<jsp:param  name = "nombreTextualizer"  value = "libera-biometricosTrue" />
    							<jsp:param  name = "ruta"  value = "<%=RutaFunc%>" />     							
   							  </jsp:include>
                          <%}else{%>
                        	 <jsp:include  page = "textualizer.jsp" > 
    							<jsp:param  name = "nombreTextualizer"  value = "libera-biometricosFalse" />
    							<jsp:param  name = "ruta"  value = "<%=RutaFunc%>" />     							
   							  </jsp:include>
                        	  <%}
                          }
                          //RESULTADOBORRADO%> </h3>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="2" class="ETablaT">Información del último Examen Registrado para este Expediente
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Unidad Medica:</td>
                              <td class="ECampo"><%=vPerDatos.getCDscEmpresa()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Expediente:</td>
                              <td class="ECampo"><%=vPerDatos.getICveExpediente()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">N&uacute;mero Examen:</td>
                              <td class="ECampo"><%=vPerDatos.getINumExamen()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">R.F.C.:</td>
                              <td class="ECampo"><%=vPerDatos.getCRFC()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Nombre del solicitante:</td>
                              <td class="ECampo"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
                            <tr>
                              <td class="EEtiqueta">Sexo:</td>
                              <%
                            if(vPerDatos.getCSexo().equals("M")){
                                %>
                                    <td class="ECampo">Masculino</td>
                                <%}else{%>
                                    <td class="ECampo">Femenino</td>
                                <%}
                            %>
                            </tr>
                            <input type="hidden" name="cGenero" value="<%=vPerDatos.getCSexo()%>">
                            </tr>
                            <tr>
                                <td colspan="2" align="center">
                                    <%out.print(vEti.clsAnclaTexto("EAncla","Liberar Biométricos","JavaScript: fLiberaBiometrico();", "Buscar Expediente",""));%>
                                </td>
                                <td></td>
                            </tr>
                            <tr>
                            <input type="hidden" name="hdNombre" value="<%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%>">
                            <input type="hidden" name="hdNoExpediente" value="<%=vPerDatos.getICveExpediente()%>">
                            <input type="hidden" name="hdRFC" value="<%=vPerDatos.getCRFC()%>">
                            <input type="hidden" name="iFolioEs" value="1">
                            <%
                              //out.print(vEti.EtiCampoCS("EEtiqueta","Encuesta de Salida:","ECampo","text",10,10,3,"iFolioEs","",0,"","fMayus(this);",false,true,true));
                            %>
                            </tr>
                          </table>
                        <%
                          }else{
                          %>
                          <h3><%=RESULTADOBORRADO+"a"%> </h3>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                          <%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                          %>
                                </table>
                          <%
                          }
                        }else{
//******************************************************************************
                          if(request.getParameter("hdBoton").compareTo("GuardarDISABBLED")==0){
                            %>
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                   out.print("<tr>");
                                     out.print("<td colspan=\"3\" class=\"ETablaT\">Datos del Personal</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Expediente:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdNoExpediente") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Nombre:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdNombre") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>R.F.C.:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdRFC") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='ETablaT'>Modo Trans.</td>");
                                     out.print("<td class='ETablaT'>Categor&iacute;a</td>");
                                     out.print("<td class='ETablaT'>Motivo</td>");
                                   out.print("</tr>");

                                   TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
                                   TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
                                   Vector vcEXPExamCat = new Vector();

                                   vcEXPExamCat = dEXPExamCat.FindBy(request.getParameter("hdNoExpediente"), request.getParameter("iNumExamen"));
                                   if (vcEXPExamCat.size() > 0)
                                     for (int i = 0; i < vcEXPExamCat.size(); i++){
                                       vEXPExamCat = (TVEXPExamCat)vcEXPExamCat.get(i);
                                       out.print("<tr>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscMdoTrans() + "</td>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscCategoria() + "</td>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscMotivo() + "</td>");
                                       out.print("</tr>");
                                     }

                            %>
                                </table>
                            <%

                            %>
                                &nbsp;
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                   out.print("<tr>");
                                     out.print("<td colspan=\"5\" class=\"ETablaT\">Servicios</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='ETablaT'>Aplicado</td>");
                                     out.print("<td class='ETablaT'>Servicio</td>");
                                     out.print("<td class='ETablaT'>Hora de Entrada</td>");
                                     out.print("<td class='ETablaT'>Hora de Salida</td>");
                                     out.print("<td width='200'class='ETablaT'>Firma</td>");
                                   out.print("</tr>");

                                   TDEXPServicio dEXPServicio = new TDEXPServicio();
                                   TVEXPServicio vEXPServicio = new TVEXPServicio();
                                   Vector vcEXPServicio = new Vector();

                                   vcEXPServicio = dEXPServicio.FindBy(request.getParameter("hdNoExpediente"), request.getParameter("iNumExamen"));
                                   if (vcEXPServicio.size() > 0)
                                     for (int i = 0; i < vcEXPServicio.size(); i++){
                                       vEXPServicio = (TVEXPServicio)vcEXPServicio.get(i);
                                       out.print("<tr>");
                                         out.print("<td class='ETablaC'><input type='checkbox'></td>");
                                         out.print("<td class='ECampo'>" + vEXPServicio.getCDscServicio() + "</td>");
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                       out.print("</tr>");
                                     }


                            %>
                                </table>
                            <%
                          }
//******************************************************************************
                          if (request.getParameter("hdType") != null && request.getParameter("hdType").length()>0){
                             // System.out.println("hdType2 ==>>"+request.getParameter("hdType"));
                          %>
                          <h3><%
                          if(respuesta){%>
                        	  <jsp:include  page = "textualizer.jsp" > 
    							<jsp:param  name = "nombreTextualizer"  value = "libera-biometricosTrue" />
    							<jsp:param  name = "ruta"  value = "<%=RutaFunc%>" />     							
   							  </jsp:include>
                          <%}else{%>
                        	 <jsp:include  page = "textualizer.jsp" > 
    							<jsp:param  name = "nombreTextualizer"  value = "libera-biometricosFalse" />
    							<jsp:param  name = "ruta"  value = "<%=RutaFunc%>" />     							
   							  </jsp:include>
                        	  <%}
                          //RESULTADOBORRADO%> </h3>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                          <%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
