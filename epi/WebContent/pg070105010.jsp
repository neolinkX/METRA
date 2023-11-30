<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	/**
	 * Title: Listado del detalle de Exï¿½menes Concluidos
	 * Description: JSP para mostrar el detalle de los exï¿½menes concluidos
	 * Copyright: ?
	 * Company: Micros Personales S.A. de C.V.
	 * @author Esteban Viveros
	 * @version 1
	 * Clase: ?
	 */
%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<html>
<%
	pg070105010CFG clsConfig = new pg070105010CFG(); // modificar

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setNombrePagina("pg070105011.jsp"); // modificar
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setArchFuncs("FValida");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg070105010.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cCatalogo = "pg070105011.jsp"; // modificar
	String cOperador = "3"; // modificar
	String cDscOrdenar = "No Disponible|"; // modificar
	String cCveOrdenar = "0|"; // modificar
	String cDscFiltrar = "No Disponible|"; // modificar
	String cCveFiltrar = "0|"; // modificar
	String cTipoFiltrar = "8|"; // modificar
	boolean lFiltros = true; // modificar
	boolean lIra = true; // modificar
	String cEstatusIR = "Imprimir"; // modificar

	// LLamado al Output Header
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	int iNumRowsPrin = new Integer(
			vParametros.getPropEspecifica("NumRowsPrin")).intValue();
	int iNumRowsSec = new Integer(
			vParametros.getPropEspecifica("NumRowsSec")).intValue();

	TError vErrores = new TError();
	StringBuffer sbErroresAcum = new StringBuffer();
	TEtiCampo vEti = new TEtiCampo();

	clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	String cPaginas = clsConfig.getPaginas();
	String cDscPaginas = clsConfig.getDscPaginas();
	PageBeanScroller bs = clsConfig.getBeanScroller();
	String cUpdStatus = "Hidden";
	String cNavStatus = "Hidden"; //clsConfig.getNavStatus();
	String cOtroStatus = clsConfig.getOtroStatus();
	String cCanWrite = "No";
	String cSaveAction = "Guardar";
	String cDeleteAction = "BorrarB";
	String cClave = "";
	String cPosicion = "";

	TVUsuario vUsuario = (TVUsuario) request.getSession(true)
			.getAttribute("UsrID");
	TVPERDatos vPERDatos = clsConfig.getUser(request
			.getParameter("hdICveExpediente"));
		
	//////////////Verificando Biometricos menores a 3k y actualizacion de Biometricos de Content a NAS ////////////////
	TDLICFFH dLicFFH = new TDLICFFH();
	String mensaje = dLicFFH.findByVerificaBiometricos(request.getParameter("hdICveExpediente").toString().trim());
	
%>
<script language="JavaScript">



 function fGetFile2(cLocal,cURL){
	  oHTP.cArchivoLocal = cLocal;
	  oHTP.cURLArchivo = cURL;
	  oHTP.fGetFile();
	}
	function descArch(){
		var rutaLocalTemp = "<%=""+new TParametro("07").getPropEspecifica("LOCAL_FOLDER_TEMP")%>";
		var fs = new ActiveXObject("Scripting.FileSystemObject");
		var rutaDllsEspanol64 = "C:\\Archivos de Programa (x86)\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsEspanol32 = "C:\\Archivos de Programa\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsIngles64 = "C:\\Program Files (x86)\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		var rutaDllsIngles32 = "C:\\Program Files\\Griaule\\Fingerprint SDK 2009\\bin\\x64\\";
		try{
			//alert(rutaLocalTemp);
			folderBool = fs.FolderExists(rutaLocalTemp);
			if (!folderBool){
			 	fs.CreateFolder(rutaLocalTemp);
			}
			folderA = fs.FolderExists(rutaDllsEspanol64);
			if (folderA){
				fs.CopyFile(rutaDllsEspanol64 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderB = fs.FolderExists(rutaDllsEspanol32);
			if (folderB){
				fs.CopyFile(rutaDllsEspanol32 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderC = fs.FolderExists(rutaDllsIngles64);
			if (folderC){
				fs.CopyFile(rutaDllsIngles64 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			folderD = fs.FolderExists(rutaDllsIngles32);
			if (folderD){
				fs.CopyFile(rutaDllsIngles32 + "*.dll",rutaLocalTemp,false);
				descargaFinal();
			}
			
		 } catch (err) {
			//alert("Favor de instalar FingerPrint. " + err.message);
			 descargaFinal();
		 }
	}
	function descargaFinal(){
		var fsx = new ActiveXObject("Scripting.FileSystemObject");
		var archiExist1 = fsx.FileExists("C:/Windows/Temp/TCS/Sct_Fingerprint.zip");
		var archiExist2 = fsx.FileExists("C:/Windows/Temp/TCS/fingerUtilities.jar");
		var archiExist3 = fsx.FileExists("C:/Windows/Temp/TCS/FingerDesc.jar");
		//alert(archiExist1 + " " + archiExist2);
		if(!archiExist1){
			fGetFile2("C:\\Windows\\Temp\\TCS\\Sct_Fingerprint.zip","http://servidorimagenes.sct.gob.mx/medprev/Temp/Sct_Fingerprint.zip");
		}
		if(!archiExist2){
			fGetFile2("C:\\Windows\\Temp\\TCS\\fingerUtilities.jar","http://servidorimagenes.sct.gob.mx/medprev/Temp/fingerUtilities.jar");
		}
		if(!archiExist3){
			fGetFile2("C:\\Windows\\Temp\\TCS\\FingerDesc.jar","http://servidorimagenes.sct.gob.mx/medprev/Temp/FingerDesc.jar");
		}
		var shell = new ActiveXObject("WScript.shell");
		var pathProg = "javaw -jar c:\\Windows\\Temp\\TCS\\FingerDesc.jar ";
		shell.run(pathProg);
	}
</script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0,
							vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\medprev2\Archivos\funciones\pg070105010.js"></SCRIPT>-->
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "calendario.js"%>"></SCRIPT>

<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type='text/javascript'
	src='/medprev/dwr/interface/MedPredDwr.js'></script>



<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
	}
	function fIrCatalogo(page, fieldName, value, fieldName2, value2) {
		var form = document.forms[0];
		form.action = page;
		if (value)
			form[fieldName].value = value;
		if (value2)
			form[fieldName2].value = value2;
		form.target = "FRMDatos";
		form.submit();
	}
	var GRALiCveExp, GRALiNumExm;

	function estaDictaminado(iCveExp, iNumExm) {
		var form = document.forms[0];
		if(<%=request.getParameter("hdICveProceso") != null ? ""+request
				.getParameter("hdICveProceso") : 0%> == 1){
			MedPredDwr.evaluaSiExamenEstaDictaminado(iCveExp, iNumExm, {
				async : false, // synchronous call
				callback : function(data) {
					
					//alert(data);
					if (data == 1) {
						respuestaopenpopupValidaPaciente();
					} else {
						fIrCatalogoISM(iCveExp, iNumExm);
					}
				}
			});			
		}else{
			respuestaopenpopupValidaPaciente();
		}
	}
	function sal(){
        form.action = 'pg0700000.jsp';
        form.target = '_top';
        form.submit();
}
	function fIrCatalogoISM(iCveExp, iNumExm) {
		var form = document.forms[0];
		form.hdICveExpediente.value = iCveExp;
		form.hdINumExamen.value = iNumExm;
		/*    form.action="pg070105010.jsp";
		    form.target="FRMDatos";
		    form.submit();*/
		GRALiCveExp = iCveExp;
		GRALiNumExm = iNumExm;

		openpopupValidaMedico();

	}
	var MEDICOVALIDADO = false;
	function openpopupValidaMedico() {
		if(!MEDICOVALIDADO){
			Loading();
			var popurl = "validaBiometricoMedicoPacienteDictamen2.jsp?idPersona=1&iNumExm="
				+ GRALiNumExm + "&iCveExp=" + GRALiCveExp;
			window.open(popurl, "", "width=10,height=10,status,menubar,");			
		}else{
			respuestaopenpopupValidaMedico()
		}
	}
	function respuestaopenpopupValidaMedico() {
		openpopupValidaPaciente();
	}
	function openpopupValidaPaciente() {
		MEDICOVALIDADO = true;
		Loading();
		var popurl = "validaBiometricoMedicoPacienteDictamen2.jsp?idPersona=2&iNumExm="
				+ GRALiNumExm + "&iCveExp=" + GRALiCveExp;
		window.open(popurl, "", "width=600,height=200,status,menubar,");
	}
	function respuestaopenpopupValidaPaciente() {
		fIrCatalogo('pg070105040.jsp');
	}
</script>

<head>
<meta name="Autor" content="<%=vParametros.getPropEspecifica("Autor")%>">
</head>
<%
	new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracterï¿½sticas generales de las pï¿½ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
%>
<link rel="stylesheet"
	href="<%=vParametros.getPropEspecifica("RutaCSS")
					+ vParametros.getPropEspecifica("HojaCSS")%>"
	TYPE="text/css">
<body bgcolor="<%=vParametros.getPropEspecifica("ColorFondoPagina")%>"
	topmargin="0" leftmargin="0" onLoad="<%=vEntorno.getOnLoad()%>">
	<div id="loading"
		style="position: absolute; width: 100%; text-align: center; top: 250px;">
		<img src="<%=vParametros.getPropEspecifica("RutaImg")%>nuevo6.gif"	border=0>
	</div>
	<form method="<%=vEntorno.getMetodoForm()%>"
		action="<%=vEntorno.getActionForm()%>">
		<input type="hidden" name="hdLCondicion"
			value="<%if (request.getParameter("hdLCondicion") != null)
				out.print(request.getParameter("hdLCondicion"));%>">
		<input type="hidden" name="hdLOrdenar"
			value="<%if (request.getParameter("hdLOrdenar") != null)
				out.print(request.getParameter("hdLOrdenar"));%>">
		<!--input type="hidden" name="FILNumReng" value="<%if (request.getParameter("FILNumReng") != null)
				out.print(request.getParameter("FILNumReng"));
			else
				out.print(vParametros.getPropEspecifica("NumRengTab"));%>"-->
		<input type="hidden" name="FILNumReng" value="200"> <input
			type="hidden" name="hdRowNum"
			value="<%=bs != null ? bs.pageNo() : 0%>"> <input
			type="hidden" name="hdCampoClave" value="<%=cClave%>"> <input
			type="hidden" name="hdICveExpediente"
			value="<%=request.getParameter("hdICveExpediente") != null
					? request.getParameter("hdICveExpediente")
					: ""%>">
		<input type="hidden" name="hdINumExamen"
			value="<%=request.getParameter("hdINumExamen") != null ? request
					.getParameter("hdINumExamen") : ""%>">
		<input type="hidden" name="hdICveUniMed"
			value="<%=request.getParameter("hdICveUniMed") != null ? request
					.getParameter("hdICveUniMed") : ""%>">
		<input type="hidden" name="hdICveModulo"
			value="<%=request.getParameter("hdICveModulo") != null ? request
					.getParameter("hdICveModulo") : ""%>">
		<input type="hidden" name="hdICveProceso"
			value="<%=request.getParameter("hdICveProceso") != null ? request
					.getParameter("hdICveProceso") : ""%>">
		<input type="hidden" name="hdDtConcluido"
			value="<%=request.getParameter("hdDtConcluido") != null ? request
					.getParameter("hdDtConcluido") : ""%>">
		<input type="hidden" name="hdCDscGrupo" value=""> <input
			type="hidden" name="hdICvePerfil" value="">
		<%-- Estos parametros son para las paginas 070101080,070104031,070104033 y 070105040--%>
		<input type="hidden" name="iCveExpediente"
			value="<%=request.getParameter("hdICveExpediente") != null
					? request.getParameter("hdICveExpediente")
					: ""%>">
		<input type="hidden" name="iNumExamen"
			value="<%=request.getParameter("hdINumExamen") != null ? request
					.getParameter("hdINumExamen") : ""%>">
		<input type="hidden" name="iCveUniMed"
			value="<%=request.getParameter("hdICveUniMed") != null ? request
					.getParameter("hdICveUniMed") : ""%>">
		<input type="hidden" name="iCveModulo"
			value="<%=request.getParameter("hdICveModulo") != null ? request
					.getParameter("hdICveModulo") : ""%>">
		<input type="hidden" name="iCveProceso"
			value="<%=request.getParameter("hdICveProceso") != null ? request
					.getParameter("hdICveProceso") : ""%>">
		<input type="hidden" name="iCvePersonal"
			value="<%=vPERDatos != null ? vPERDatos.getICvePersonal() : 0%>">
		<input type="hidden" name="iCveServicio" value="-1">
		<%-- Hasta aqui los parametros para las paginas 070101080,070104031,070104033 y 070105040--%>
		<input type="hidden" name="hdCPropiedad" value=""> <input
			type="hidden" name="hdBoton" value="">
		<table
			background="<%=vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg"
			width="100%" height="100%">
			<%
				if (clsConfig.getAccesoValido()) {
			%>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td valign="top">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<tr>
							<td class="ETablaT" colspan="6">Datos del Personal</td>
						</tr>
						<%
							if (vPERDatos != null) {
						%>
						<td class="EEtiqueta">Nombre:</td>
						<td class="ETabla" colspan="2"><%=vPERDatos.getCNombre() + " "
							+ vPERDatos.getCApPaterno() + " "
							+ vPERDatos.getCApMaterno()%></td>
						<td class="EEtiqueta">Expediente:</td>
						<td class="ETabla" colspan='2'><%=vPERDatos.getICveExpediente()%></td>
						</tr>
						<tr>
							<td class="EEtiqueta">Edad:</td>
							<td class="ETabla" colspan="2"><%=clsConfig.getEdad(vPERDatos.getDtNacimiento())%></td>
							<td class="EEtiqueta">Sexo:</td>
							<td class="ETabla" colspan='2'><%=("F".compareTo(vPERDatos.getCSexo()) == 0? "Mujer" : "Hombre" )%></td>
						</tr>
						<tr>
							<td class="EEtiqueta">Proceso:</td>
							<td class="ETabla" colspan="5"><%=clsConfig.getProceso(
							request.getParameter("hdICveProceso"))
							.getCDscProceso()%></td>
						</tr>
						<tr>
							<td class="EEtiqueta">Unidad M&eacute;dica:</td>
							<td class="ETabla" colspan="2"><%=clsConfig.getUniMed(
							request.getParameter("hdICveUniMed"))
							.getCDscUniMed()%></td>
							<td class="EEtiqueta">M&oacute;dulo:</td>
							<td class="ETabla" colspan='2'><%=clsConfig.getModulo(
							request.getParameter("hdICveUniMed"),
							request.getParameter("hdICveModulo"))
							.getCDscModulo()%></td>
						</tr>
						<tr>
							<td class="EEtiqueta">Fecha:</td>
							<td class="ETabla" colspan="5"><%=request.getParameter("hdDtConcluido")%></td>
						</tr>
						<%
							} else {
						%>
						<tr>
							<td class="EResalta" colspan="5">Datos no disponibles</td>
						</tr>
						<%
							}
						%>
						<tr>
							<td class="ETablaT" colspan="6">Puestos Solicitados</td>
						</tr>
						<tr class="ETablaSTC">
							<td>Mdo. Trans.</td>
							<td>Grupo</td>
							<td>Categor&iacute;a</td>
							<td>Puesto</td>
							<td>Motivo</td>
							<td>Perfil</td>
						</tr>
						<%
							Vector vcPuestos = clsConfig.getPuestos(
										request.getParameter("hdICveExpediente"),
										request.getParameter("hdINumExamen"));
								if (vcPuestos.size() > 0) {
									for (Enumeration e = vcPuestos.elements(); e
											.hasMoreElements();) {
										TVEXPExamPuesto vEXPExamPuesto = (TVEXPExamPuesto) e
												.nextElement();
						%>
						<tr class="ETabla">
							<td><%=vEXPExamPuesto.getCDscMdoTrans()%></td>
							<td><%=vEXPExamPuesto.getCDscGrupo()%></td>
							<td><%=vEXPExamPuesto.getCDscCategoria()%></td>
							<td><%=vEXPExamPuesto.getCDscPuesto()%></td>

							<%
								TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
											TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
											Vector vcEXPExamCat = new Vector();
											vcEXPExamCat = dEXPExamCat.FindByCat(
													request.getParameter("hdICveExpediente"),
													request.getParameter("hdINumExamen"), ""
															+ vEXPExamPuesto.getICveMdoTrans(), ""
															+ vEXPExamPuesto.getICveCategoria());
											if (vcEXPExamCat.size() > 0)
												for (int i = 0; i < vcEXPExamCat.size(); i++)
													vEXPExamCat = (TVEXPExamCat) vcEXPExamCat
															.get(i);
							%>
							<td><%=vEXPExamCat.getCDscMotivo()%></td>

							<td align="right"><a class="EAncla"
								href="javascript:fIrCatalogo('pg070105100.jsp','hdICvePerfil','<%=vEXPExamPuesto.getICvePerfil()%>','hdCDscGrupo','<%=vEXPExamPuesto.getCDscGrupo()%>')"><%=vEXPExamPuesto.getICvePerfil()%></a>
							</td>
						</tr>
						<%
							}
								} else {
						%>
						<tr class="EResalta">
							<td colspan="2" align="center">Datos no disponibles</td>
						</tr>
						<%
							}
						%>
					</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td valign="top">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"	cellpadding="3">
						<tr class="ETablaT">
							<td colspan="2">Dictamen</td>
						</tr>
						<tr class="ETabla">
							<td>1</td>
							<td><a class="EAncla"
								href="javascript:fIrCatalogo('pg070105020.jsp')">RESULTADO	DEL EXAMEN ACTUAL</a></td>
						</tr>
						<tr class="ETabla">
							<td>2</td>
							<td><a class="EAncla"
								href="javascript:fIrCatalogo('pg070105030.jsp')">DIAGN&Oacute;STICOS
									Y RECOMENDACIONES</a></td>
						</tr>
						<%
							int i = 3;
								String cadena = "OBSERVACIONES DE DIAGNÓSTICO Y RECOMENDACIÓN DEL DICTAMINADOR";
								int x = 0;
								if (bs != null) {
									bs.start();
									while (bs.nextRow()) {
										if (cadena.equals(bs.getFieldValue("cDscServicio",
												"&nbsp;"))) {
											x++;
										} else {
											if (clsConfig.getLPagina(cCatalogo)) {
						%>
						<tr class="ETabla">
							<td><%=i++%></td>
							<td><a class="EAncla"
								href="javascript:fIrCatalogo('pg070105011.jsp','iCveServicio','<%=bs
										.getFieldValue("iCveServicio", "-1")%>')"><%=bs.getFieldValue("cDscServicio",
										"&nbsp;")%></a></td>
						</tr>
						<%
							} else {
						%>
						<tr class="ETabla">
							<td><%=i++%></td>
							<td><%=bs.getFieldValue("cDscServicio",
										"&nbsp;")%></td>
						</tr>
						<%
							}
										}
									}
								} else {
						%>
						<tr>
							<td class="ETabla" colspan="5">No Existen Servicios de
								Diagnosticos Disponibles</td>
						</tr>
						<%
							}
						%>
						<tr class="ETabla">
							<td><%=i%></td>
							<td>
								<!-- <a class="EAncla" href="javascript:fIrCatalogo('pg070105040.jsp')">DICTAMEN</a> -->
								<a class="EAncla"
								href="javascript:estaDictaminado('<%=request.getParameter("hdICveExpediente") != null
						? request.getParameter("hdICveExpediente")
						: ""%>','<%=request.getParameter("hdINumExamen") != null
						? request.getParameter("hdINumExamen")
						: ""%>');">DICTAMEN</a>

							</td>
							</tr>
							<tr class="ETabla">							
							<td colspan="2"><a ass="EAncla"
								href="javascript:fIrCatalogo('pg070105050.jsp');">Ex&aacute;menes
									Anteriores</a> &nbsp;&nbsp;&nbsp; <a class="EAncla"
								href="javascript:fIrCatalogo('pg070104033.jsp');">Interconsulta</a>

							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%
				} else {
			%>
			<script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
			<%
				}
			%>
		</table>
	</form>
	<SCRIPT LANGUAGE="JavaScript">
		var ld = (document.all);
		var ns4 = document.layers;
		var ns6 = document.getElementById && !document.all;
		var ie4 = document.all;
		if (ns4)
			ld = document.loading;
		else if (ns6)
			ld = document.getElementById("loading").style;
		else if (ie4)
			ld = document.all.loading.style;
		function Loaded() {
			if (ns4) {
				ld.visibility = "collapse";
			} else if (ns6 || ie4)
				ld.display = "none";
		}
		function Loading() {
			if (ns4) {
				ld.visibility = "visible";
			} else if (ns6 || ie4)
				ld.display = "block";
		}
		Loaded();
	</script>
	<div>
<object id="oHTP" classid="clsid:4CCB05E0-E1BB-4999-A3BB-84172549A276"
codebase="/medprev/activex/HTTPGetProj1.ocx"
width=1 height=1 align=center hspace=0 vspace=0></object>
</div>
</body>
<script>
//setTimeout("insertaImagenes()",50);
descArch();
</script>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%
	vEntorno.clearListaImgs();
%>
</html>