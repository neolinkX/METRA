
<%/**
En CALE DE SERVICIO para TERCEROS que tengan HABILITADA la VALIDACION biometrica en alguno de sus MODULOS ASIGNADOS, se valida HUELLA MEDICO
 Title: pg070104002
 * Description:
 * Copyright:
 * Company:
 * @author AG SA
 * @version 
 * Clase:
 */%>
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


<html>
<%
  pg070104002CFG  clsConfig = new pg070104002CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104002.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104002.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104002.jsp";       // modificar
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
  String cUpdStatus  = "SaveCancelOnly";
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
      TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
%>
<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type='text/javascript'
	src='/medprev/dwr/interface/MedPredDwr.js'></script>



<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>

<script language="JavaScript">
function ValidaHuellaDoc(iCveDocto)
{
    var fso;
    var exe;
    var result = false;
    var ForReading = 1;
    var flag = 0;
    var oShell = new ActiveXObject("Shell.Application");
    var aplicacion = "C:\\fingerprint\\fingerprint.exe";
    var parametros_del_comando = "";

    if(iCveDocto != "0"){
        var URL = '"<%=vParametros.getPropEspecifica("RutaContextoGral")%>BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&IDEDO=_DEDO_&idTipoDocumento=3"';
        var dedo = 2;
        parametros_del_comando =  " "+URL+" "+iCveDocto +" "+dedo;
    }	else {
        parametros_del_comando =  " "+URL+" "+iCveDocto +" "+dedo;
    }

    exe = oShell.ShellExecute(aplicacion, parametros_del_comando, "", "open", "1");
    fso = new ActiveXObject("Scripting.FileSystemObject");

    while(flag==0){
        try{
            archivo = fso.OpenTextFile("C:\\fingerprint\\matchresult.txt", ForReading, false);
            result = archivo.readline();
            archivo.Close();
            archivo = fso.GetFile("C:\\fingerprint\\matchresult.txt");
            archivo.Delete();
            flag = 1;
        }catch(err){
        //alert(err);
        }
    }

    return result;
}

function ValidaHuellaDoc2(iCvePersonal)
{
    var fso;
    var result = false;
    var ForReading = 1;
    var flag = 0;
    var oShell = new ActiveXObject("Shell.Application");
    var aplicacion = "C:\\fingerprint\\fingerprint.exe";
    var parametros_del_comando = "";

    if(iCvePersonal != "0"){
        var URL = '"http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&IDEDO=_DEDO_&ICVETIPOFFH=3"';
        //var URL = 'http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=2';
        var expediente = iCvePersonal;
        var dedo = 2;
        parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
    //parametros_del_comando =  " 0 "+expediente ;
    }	else {
        parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
    //parametros_del_comando =  " 0 "+expediente;
    //fAlert("ccahuellas (servidor interno)");
    }

    oShell.ShellExecute(aplicacion, parametros_del_comando, "", "open", "1");
    fso = new ActiveXObject("Scripting.FileSystemObject");

    while(flag==0){
        try{
            archivo = fso.OpenTextFile("C:\\fingerprint\\matchresult.txt", ForReading, false);
            result = archivo.readline();
            archivo.Close();
            archivo = fso.GetFile("C:\\fingerprint\\matchresult.txt");
            archivo.Delete();
            flag = 1;
        }catch(err){
        //alert(err);
        }
    }
    return result;
}
</script>

<SCRIPT LANGUAGE="JavaScript">
    
  var aDatosHojaAyuda = new Array();
  
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  
  function generaPDFConsentInf(){
	frm = document.forms[0];
	var cConds = "alwaysRaised=yes,dependent=yes,width=800,height=485,location=no,menubar=no,resizable=yes,scrollbars=yes,titlebar=yes,statusbar=yes,toolbar=no";
        hWinHojaAyuda = window.open("servConsenInfCFG?hdNoExpedienteRep="+frm.hdNoExpedienteRep.value+"&hdiNumExamenRep="+frm.hdiNumExamenRep.value
                                        +"&hdMdoTransRep="+frm.hdMdoTransRep.value+"&hdDscCategoriaRep="+frm.hdDscCategoriaRep.value
                                        +"&hdDscMotivoRep="+frm.hdDscMotivoRep.value+"&iCveProceso="+frm.iCveProceso.value
                                        +"&iCveUniMed="+frm.iCveUniMed.value, "FRMConsenInf", cConds);
}

function GethdNoExpedienteRep(){
   return frm.hdNoExpedienteRep.value;
}

function GethdiNumExamenRep(){
   return frm.hdiNumExamenRep.value;
}

function GethdMdoTransRep(){
   return frm.hdMdoTransRep.value;
}
function GethdDscCategoriaRep(){
   return frm.hdDscCategoriaRep.value;
}

function GethdDscMotivoRep(){
   return frm.hdDscMotivoRep.value;
}

function GetiCveProceso(){
   return frm.iCveProceso.value;
}

function GetiCveUniMed(){
   return frm.iCveUniMed.value;
}
</SCRIPT>
<head>
<meta name="Autor"
	content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet"
	href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>"
	TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>"
	topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
	<div id="loading" style="position:absolute; width:100%; text-align:center; top:250px;"><img src="<%= vParametros.getPropEspecifica("RutaImg") %>nuevo6.gif" border=0></div>
	<form method="<%= vEntorno.getMetodoForm() %>"
		action="<%= vEntorno.getActionForm() %>">
		<input type="hidden" name="hdLCondicion"
			value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
		<input type="hidden" name="hdLOrdenar"
			value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
		<input type="hidden" name="FILNumReng"
			value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
		<%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>

		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave" value="<%=cClave%>"> <input
			type="hidden" name="hdCPropiedad" value=""> <input
			type="hidden" name="hdType" value=""> <input type="hidden"
			name="lIniciado" value="1"> <input type='hidden'
			name='hdBuscado' value='0'>

		<%  if (request.getParameter("hdICvePersonal") != null){
%>
		<input type="hidden" name="hdICvePersonal"
			value="<%=request.getParameter("hdICvePersonal").toString()%>">
		<%
    }else{
%>
		<input type="hidden" name="hdICvePersonal" value="">
		<%
    }
%>
		<table
			background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg"
			width="100%" height="100%">
			<% if(clsConfig.getAccesoValido()){
       TVPERDatos vPerDatos = null;
       if (request.getParameter("hdType") != null){
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
             vPerDatos=clsConfig.findUser();
          }else if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
             vPerDatos=clsConfig.findExpediente();
          }
       }


       if (request.getParameter("hdType") != null){
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
%>
			<input type="hidden" name="iNumExamen"
				value="<%=request.getParameter("iNumExamen").toString()%>">
			<%
          }else if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
             if(vPerDatos!=null){
%>
			<input type="hidden" name="iNumExamen"
				value="<%=vPerDatos.getINumExamen()%>">
			<%
             }else{
%>
			<input type="hidden" name="iNumExamen" value="">
			<%
             }
          }else{
%>
			<input type="hidden" name="iNumExamen" value="">
			<%
          }
       }else{
%>
			<input type="hidden" name="iNumExamen" value="">
			<%
       }
%>

			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><input type="hidden" name="hdBoton" value=""></td>
				<td valign="top">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<tr>
							<td align="center" colspan="4" class="EResalta">Vale de
								Servicios</td>
						</tr>
						<tr>
							<td colspan="5" class="ETablaT">Inicio del Examen</td>
						</tr>
						<tr>
							<td class="EEtiqueta">Unidad M�dica:</td>
							<td class="ETabla">
								<%
                                  //TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      Vector vProceso = new Vector();
                                      vProceso = vUsuario.getVUnidades();
                                      TVGRLUMUsuario vGRLUMUsu;
                                      if (request.getParameter("iCveUniMed") != null){
                                         out.print(vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",vUsuario.getVUniNoDup(),"iCveUniMed","cDscUniMed",request,request.getParameter("iCveUniMed").toString(), true)) ;
                                      }else{
                                         out.print(vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",vUsuario.getVUniNoDup(),"iCveUniMed","cDscUniMed",request,"0", true));
                                      }
                                  %>
							</td>
							<td class="EEtiqueta">M&oacute;dulo:</td>
							<td class="ETabla">
								<%
                                        TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                                        TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                                        Vector vGModulo = new Vector();
                                        String cFiltro = "";
                                        if (request.getParameter("iCveUniMed") != null){
                                          cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                                    " and GRLUsuMedicos.iCveUniMed =  " + request.getParameter("iCveUniMed") +
                                                    " and GRLUsuMedicos.iCveProceso = " + vParametros.getPropEspecifica("EPIProceso")  ;
                                        }else{
                                          cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                                    " and GRLUsuMedicos.iCveUniMed =  0 " +
                                                    " and GRLUsuMedicos.iCveProceso = " + vParametros.getPropEspecifica("EPIProceso")  ;
                                        }
                                        vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                                     %> <select name="iCveModulo"
								size="1" onChange="">
									<%
                                    if (vGModulo.size() > 0){
                                     out.print("<option value=0>Seleccione...</option>");
                                     for (int x = 0;x<vGModulo.size(); x++){
                                          vGRLUSUMedicos = (TVGRLUSUMedicos) vGModulo.get(x);
                                          if (request.getParameter("iCveModulo")!=null&&request.getParameter("iCveModulo").compareToIgnoreCase(new Integer(vGRLUSUMedicos.getICveModulo()).toString())==0){
                                             out.print("<option value =" + vGRLUSUMedicos.getICveModulo() + " Selected>" + vGRLUSUMedicos.getCDscModulo() + "</option>");
                                          }else{
                                             out.print("<option value =" + vGRLUSUMedicos.getICveModulo() + ">" + vGRLUSUMedicos.getCDscModulo() + "</option>");
                                          }
                                       }
                                    }else{
                                       out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                    }
                                    %>
							</select></td>
						</tr>
						<tr>
							<td class="EEtiqueta">Proceso:</td>
							<td class="ETabla" colspan="3"><select name="iCveProceso"
								size="1" onChange="">
									<%
                                      int iCveUniMed = 0;
                                      if (request.getParameter("iCveUniMed") != null){
                                         iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed").toString());
                                      }else{
                                         iCveUniMed = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
                                      }
                                      if(vProceso.size() >0 ){
                                        out.print("<option value=0>Seleccione...</option>");
                                        for (int w = 0;w<vProceso.size(); w++){
                                           vGRLUMUsu = (TVGRLUMUsuario) vProceso.get(w);
                                           if (vGRLUMUsu.getICveUniMed() == iCveUniMed){
                                              if (request.getParameter("iCveProceso")!=null&&request.getParameter("iCveProceso").compareToIgnoreCase(new Integer(vGRLUMUsu.getICveProceso()).toString())==0){
                                                 out.print("<option value =" + vGRLUMUsu.getICveProceso() + " Selected>" + vGRLUMUsu.getCDscProceso() + "</option>");
                                             }else{
                                                 out.print("<option value =" + vGRLUMUsu.getICveProceso() + ">" + vGRLUMUsu.getCDscProceso() + "</option>");
                                             }
                                           }
                                         }
                                      }else{
                                         out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                      }
                                    %>
							</select></td>
						</tr>
						<%
                                 out.print("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10,3,"dtSolicitado", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                 out.print("</tr>");
                                 if(vPerDatos!=null){
                                    out.print("<tr>");
                                    out.print("<td class=\"EEtiqueta\">Expediente:</td>");
                                    out.print("<td>");
                                    out.print("<Input Type=\"Text\" Value=\""+vPerDatos.getICveExpediente()+"\" Name=\"iCveExpediente\" Size=\"10\" MaxLength=\"10\">");
                                    out.print("</td>");
                                 }else{
                                    out.print("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Expediente:", "ECampo", "text", 10, 10,"iCveExpediente", "", 0, "", "", false, true,true, request));
                                 }
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Expediente","JavaScript:fSelExp();", "Buscar Expediente",""));
                                 out.print("</td>");
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer(1);", "Buscar Personal",""));
                                 out.print("</td>");
                                 out.print("</tr>");
                              %>
					</table>&nbsp; <%
                        if(vPerDatos!=null){
                          if(vPerDatos.getCNombre() !=null){
                        %> <script language>
                          form = document.forms[0];
                          form.hdBuscado.value = 1;
                        </script>

					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<tr>
							<td colspan="2" class="ETablaT">Personal Registrado para el
								Examen</td>
						</tr>
						<tr>
							<td class="EEtiqueta">Expediente:</td>
							<td class="ECampo"><%=vPerDatos.getICveExpediente()%></td>
						</tr>
						<%

    int icveexpediente = vPerDatos.getICveExpediente();
    //System.out.println("EXPEDIENTE PACIENTE HUELLA" + icveexpediente);
    //System.out.println("EXPEDIENTE DOCTOR HUELLA" + vUsuario.getIdIcveExpediente());
    int iDoctoHuellaDigital = vUsuario.getIcveDoctoHuella();
    //System.out.println("IDOCTO HUELLA DOCTOR" + iDoctoHuellaDigital);
    int inumeroDedoMedico = vUsuario.getiDedoAValidar();

    boolean flag = false;
    Vector vUsuMedicos = vUsuario.getVUsuMedicos();
    for (int i = 0; i < vUsuMedicos.size(); i++) {
        if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == 107) {
            flag = true;
        }
    }
    boolean esMedicoTercero = false;

    for (int i = 0; i < vUsuMedicos.size(); i++) {
        if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == 107) {
            esMedicoTercero = true;
        }
    }

%>

						<script language="JavaScript">
        var GRALiCveExp, GRALiNumExm, ENCUESTA, ISOPEN, VALIDO=false;
        
        function procesoDeValidacion(iCveExp,iNumExm){
        	//alert('proceso');
        if(!VALIDO){
			ENCUESTA = document.forms[0].iFolioEs.value;
			document.forms[0].iFolioEs.value = '';
  	    	var form=document.forms[0];
  	    	form.iCveExpediente.focus();
          	GRALiCveExp=iCveExp;
          	GRALiNumExm=iNumExm;
          	
          	openpopupValidaMedico();
        }
        
        }
        
        function closepopup(){
        	ISOPEN = false;
        }
        
        function openpopupValidaMedico(){
        	Loading();
        	if(ISOPEN){
        		alert('Tiene una validacion biometrica de medico en ejecuci�n');
        	}else{
              	//if(confirm('Para capturar la encuesta de salida se solicitara la validacion biometrica del medico y el paciente, \n Desea realizar la validacion biometrica?')){
            		var popurl="validaBiometricaValeDeServicios.jsp?idPersona=1&iNumExm=0&iCveExp=0";
                    ISOPEN = true;
                    window.open(popurl,"","width=1,height=1,status,menubar,");
              	//}else{
              		//form.iCveExpediente.focus();
              		//return false;
              	//}
        	}
        }
        function respuestaopenpopupValidaMedico(esValido){
        	if(esValido)
            	openpopupValidaPaciente();
        }
        function openpopupValidaPaciente(){
        	Loading();
        	if(ISOPEN){
        		alert('Tiene una validacion biometrica del paciente en ejecuci�n');        		
        	}else{
           	    var popurl="validaBiometricaValeDeServicios.jsp?idPersona=2&iNumExm="+GRALiNumExm+"&iCveExp="+GRALiCveExp;
                    ISOPEN = true;
                    window.open(popurl,"","width=600,height=200,status,menubar,");
              	
        	}
        }
        function respuestaopenpopupValidaPaciente(esValido){
        	if(esValido)
        		siguientePagina();
        }

        function siguientePagina(){
        	document.forms[0].iFolioEs.value = ENCUESTA;
        	VALIDO = true;        	
        }
        
    </script>

						<tr>
							<td class="EEtiqueta">R.F.C.:</td>
							<td class="ECampo"><%=vPerDatos.getCRFC()%></td>
						</tr>
						<tr>
							<td class="EEtiqueta">Nombre:</td>
							<td class="ECampo"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
							<input type="hidden" name="cGenero"
								value="<%=vPerDatos.getCSexo()%>">
						</tr>
						<tr>

							<input type="hidden" name="hdNombre"
								value="<%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%>">
							<input type="hidden" name="hdNoExpediente"
								value="<%=vPerDatos.getICveExpediente()%>">
							<input type="hidden" name="hdRFC"
								value="<%=vPerDatos.getCRFC()%>">
<td class="EEtiqueta">Encuesta de Salida:</td>
<td class="ECampo" colspan="3">
<input type="text" size="10" maxlength="10" name="iFolioEs"
value=""
 onBlur="fMayus(this);"
 onfocus="procesoDeValidacion(document.forms[0].iCveExpediente.value,document.forms[0].iNumExamen.value);"
 onMouseOut="if (window.fOutField) window.fOutField();"
 onMouseOver="if (window.fOverField) window.fOverField(0);">

							<%
                              //out.print(vEti.EtiCampoCS("EEtiqueta","Encuesta de Salida:","ECampo","text",10,10,3,"iFolioEs","",0,"","fMayus(this);",false,true,true));
                            %>
						</tr>
					</table> <%
                          }else{
                          %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                          %>
					</table> <%
                          }
                        }else{
//******************************************************************************
                          if(request.getParameter("hdBoton").compareTo("Guardar")==0){
                            %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
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
                                     out.print("<td class='ETablaT'>Categor�a</td>");
                                     out.print("<td class='ETablaT'>Motivo</td>");
                                   out.print("</tr>");

                                   TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
                                   TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
                                   Vector vcEXPExamCat = new Vector();
                                   out.print("<input type='hidden' name='hdNoExpedienteRep' value='"+request.getParameter("hdNoExpediente")+"'>");
                                   out.print("<input type='hidden' name='hdiNumExamenRep' value='"+request.getParameter("iNumExamen")+"'>");

                                   vcEXPExamCat = dEXPExamCat.FindBy(request.getParameter("hdNoExpediente"), request.getParameter("iNumExamen"));
                                   if (vcEXPExamCat.size() > 0) {
                                       out.print("<input type='hidden' name='hdExamCatSize' value='"+vcEXPExamCat.size()+"'>");
                                     for (int i = 0; i < vcEXPExamCat.size(); i++){
                                       vEXPExamCat = (TVEXPExamCat)vcEXPExamCat.get(i);
                                       out.print("<tr>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscMdoTrans() + "</td>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscCategoria() + "</td>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscMotivo() + "</td>");
                                        out.print("</tr>");
                                        out.print("<input type='hidden' name='hdMdoTransRep' value='"+vEXPExamCat.getCDscMdoTrans()+"'>");
                                        out.print("<input type='hidden' name='hdDscCategoriaRep' value='"+vEXPExamCat.getCDscCategoria()+"'>");
                                        out.print("<input type='hidden' name='hdDscMotivoRep' value='"+vEXPExamCat.getCDscMotivo()+"'>");
                                     }
                                   }
                            %>
					</table> <%

                            %> &nbsp;
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
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
				<%//Validacion de fecha (La declaracion de salud solo se imprimira si la fecha del examen es posterior al 30 de julio del 2012) 
				String stFecha1 = "30/07/2012";
				String stFecha2 = "" + request.getParameter("dtSolicitado");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
				Date fecha1 = sdf.parse(stFecha1 , new ParsePosition(0));
				Date fecha2 = sdf.parse(stFecha2 , new ParsePosition(0));
				if ( fecha1.before(fecha2)){
					//System.out.println("La declaracion de salud se debe mostrar");
				%>	
					<table border="1" align="center">
						<tr>
							<td><a href="javascript:generaPDFConsentInf()">Generar
									formato de consentimiento informado y declaraci�n de salud</a>
							</td>
						</tr>
					</table> <%
                      }
                          }
//******************************************************************************
                          if (request.getParameter("hdType") != null && request.getParameter("hdType").length()>0){
                             //System.out.println("hdType2 ==>>"+request.getParameter("hdType"));
                          %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                          %>
					</table> <%
                          }
                        }
                        %>
				</td>
			</tr>
			<%}else{%>
			<script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
			<%}%>
		</table>
	</form>
	<SCRIPT LANGUAGE="JavaScript"> 
var ld=(document.all);  
var ns4=document.layers; 
var ns6=document.getElementById&&!document.all; 
var ie4=document.all;  
if (ns4) 	ld=document.loading; 
else if (ns6) 	ld=document.getElementById("loading").style; 
else if (ie4) 	ld=document.all.loading.style;  
function Loaded() { 
	if(ns4){ld.visibility="collapse";} 
else if (ns6||ie4) ld.display="none"; } 
function Loading() { 
	if(ns4){ld.visibility="visible";} 
else if (ns6||ie4) ld.display="block"; } 
Loaded();
</script>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
