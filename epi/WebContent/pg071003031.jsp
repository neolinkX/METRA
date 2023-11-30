
<%
	/* Title:
	 * Description:
	 * Copyright:
	 * Company:
	 * @author Leonel Popoca G.
	 * @version
	 * Clase:
	 */
%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%
	pg071003031CFG clsConfig = new pg071003031CFG(); // modificar

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setNombrePagina("pg071003031.jsp"); // modificar
	vEntorno.setArchFuncs("FValida");
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg071003031.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cClave = "";
	String cPosicion = "";

	int iFiltro = 0;
	TError vErrores = new TError();
	StringBuffer sbErroresAcum = new StringBuffer();
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());

	String cAsignacion = "pg071003032.jsp"; // modificar
	String cOperador = "1"; // modificar
	String cDscOrdenar = "Clave|Nombre|"; // modificar
	String cCveOrdenar = "iCveModulo|cDscModulo|"; // modificar
	String cDscFiltrar = "Clave|Nombre|"; // modificar
	String cCveFiltrar = "iCveModulo|cDscModulo|"; // modificar
	String cTipoFiltrar = "7|8|"; // modificar
	boolean lFiltros = true; // modificar
	boolean lIra = true; // modificar
	String cEstatusIR = "Reporte"; // modificar

	int iNumRowsPrin = new Integer(
			vParametros.getPropEspecifica("NumRowsPrin")).intValue();
	int iNumRowsSec = new Integer(
			vParametros.getPropEspecifica("NumRowsSec")).intValue();

	clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	String cPaginas = clsConfig.getPaginas();
	String cDscPaginas = clsConfig.getDscPaginas();
	BeanScroller bs = clsConfig.getBeanScroller();
	String cRutaImg = vParametros.getPropEspecifica("RutaImg");
	String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
	String cUpdStatus = clsConfig.getUpdStatus();
	String cNavStatus = clsConfig.getNavStatus();
	String cOtroStatus = clsConfig.getOtroStatus();
	String cCanWrite = clsConfig.getCanWrite();
	String cSaveAction = clsConfig.getSaveAction();
	String cDeleteAction = clsConfig.getDeleteAction();

	TDPais dPais = new TDPais();
	TVPais vPais = new TVPais();
	Vector vcPais = new Vector();

	TDEntidadFed dEntidadFed = new TDEntidadFed();
	TVEntidadFed vEntidadFed = new TVEntidadFed();
	Vector vcEntidadFed = new Vector();

	TDMunicipio dMunicipio = new TDMunicipio();
	TVMunicipio vMunicipio = new TVMunicipio();
	Vector vcMunicipio = new Vector();
	TVUsuario vUsuario = (TVUsuario) request.getSession(true)
			.getAttribute("UsrID");

	TDGRLDispositivo dDisp = new TDGRLDispositivo();
	TVGRLDispositivo vDispositivo = new TVGRLDispositivo();
	Vector vcDispositivo = new Vector();

	TDGRLModuloDisp dMDisp = new TDGRLModuloDisp();
	boolean DispositivoActivo = false;
%>
<script
	src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0,
							vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg071003031.js)
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
<meta name="Autor" content="<%=vParametros.getPropEspecifica("Autor")%>">
</head>
<%
	new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros);
%>
<link rel="stylesheet"
	href="<%=vParametros.getPropEspecifica("RutaCSS")
					+ vParametros.getPropEspecifica("HojaCSS")%>"
	TYPE="text/css">
<body bgcolor="<%=vParametros.getPropEspecifica("ColorFondoPagina")%>"
	topmargin="0" leftmargin="0" onLoad="<%=vEntorno.getOnLoad()%>">
	<form method="<%=vEntorno.getMetodoForm()%>"
		action="<%=vEntorno.getActionForm()%>">
		<input type="hidden" name="hdCCondicion"
			value="<%if (request.getParameter("hdCCondicion") != null)
				out.print(request.getParameter("hdCCondicion"));%>">
		<input type="hidden" name="hdCOrdenar"
			value="<%if (request.getParameter("hdCOrdenar") != null)
				out.print(request.getParameter("hdCOrdenar"));%>">
		<%
			if (bs != null) { // Agregar tanto Hds como variables dependiendo del campo llave.
				cPosicion = Integer.toString(bs.rowNo());
				cClave = "" + bs.getFieldValue("iCveModulo", "");
			}
		%>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave"
			value="<%if (cClave.compareTo("") == 0)
				out.print(request.getParameter("hdCampoClave"));
			else
				out.print(cClave);%>">
		<input type="hidden" name="hdCampoClave1" value=""> <input
			type="hidden" name="hdCampoClave2" value=""> <input
			type="hidden" name="hdUniMed"
			value="<%out.print(request.getParameter("iCveUniMed"));%>"> <input
			type="hidden" name="hdUsuarioActivo"
			value="<%=vUsuario.getICveusuario()%>"> <input type="hidden"
			name="hdMacAddress" value=""> <input type="hidden"
			name="hdIpAddress" value=""> <input type="hidden"
			name="hdComputerName" value=""> <input type="hidden"
			name="hdNameUniMed" value=""> <input type="hidden"
			name="hdNameValidaBio" value=""> <input type="hidden"
			name="hdNameUsr"
			value="<%=vUsuario.getCNombre() + " " + vUsuario.getCApPaterno()
					+ " " + vUsuario.getCApMaterno()%>">
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
				<td><input type="hidden" name="hdBoton" value=""></td>
				<td valign="top">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
							// Inicio de Datos
						%>
						<tr>
							<%
								TEtiCampo vEti = new TEtiCampo();
									boolean lCaptura = clsConfig.getCaptura();
									boolean lNuevo = clsConfig.getNuevo();
							%>
						</tr>

						<tr>
							<td class="ETablaT" colspan='4'>Unidad Médica</td>
						</tr>
						<tr>
							<td class="ETablaC" colspan='4'>
								<%
									TDGRLUniMed dEQMUniMed = new TDGRLUniMed();
										TVGRLUniMed vEQMUniMed = new TVGRLUniMed();
										Vector vcEQMUniMed = new Vector();
										if (request.getParameter("hdBoton").compareTo("Guardar") == 0
												|| request.getParameter("hdBoton")
														.compareTo("GuardarA") == 0
												|| request.getParameter("hdBoton")
														.compareTo("Cancelar") == 0) {
											out.print("<select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
											vcEQMUniMed = dEQMUniMed.FindByAll("",
													" order by cDscUniMed ");
											if (vcEQMUniMed.size() > 0) {
												for (int i = 0; i < vcEQMUniMed.size(); i++) {
													vEQMUniMed = (TVGRLUniMed) vcEQMUniMed.get(i);
													if (request.getParameter("hdUniMed") != null
															&& request.getParameter("hdUniMed")
																	.compareToIgnoreCase(
																			new Integer(vEQMUniMed
																					.getICveUniMed())
																					.toString()) == 0)
														out.print("<option value = "
																+ vEQMUniMed.getICveUniMed()
																+ " selected>"
																+ vEQMUniMed.getCDscUniMed()
																+ "</option>");
													else
														out.print("<option value = "
																+ vEQMUniMed.getICveUniMed() + ">"
																+ vEQMUniMed.getCDscUniMed()
																+ "</option>");
												}
											} else {
												out.print("<option value = 0>Datos no disponibles...</option>");
											}
										} else {
											if (request.getParameter("hdBoton").compareTo("Nuevo") == 0
													|| request.getParameter("hdBoton").compareTo(
															"Modificar") == 0)
												out.print("<select name=\"iCveUniMed\" disabled>");
											else
												out.print("<select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
											vcEQMUniMed = dEQMUniMed.FindByAll("",
													" order by cDscUniMed ");
											if (vcEQMUniMed.size() > 0) {
												if (request.getParameter("iCveUniMed") == null
														|| request.getParameter("iCveUniMed")
																.compareTo("") == 0)
													out.print("<option value = 0>Seleccione...</option>");
												else if (request.getParameter("iCveUniMed") != null
														&& Integer.parseInt(request
																.getParameter("iCveUniMed")) < 1)
													out.print("<option value = 0>Seleccione...</option>");
												for (int i = 0; i < vcEQMUniMed.size(); i++) {
													vEQMUniMed = (TVGRLUniMed) vcEQMUniMed.get(i);
													if (request.getParameter("iCveUniMed") != null
															&& request.getParameter("iCveUniMed")
																	.compareToIgnoreCase(
																			new Integer(vEQMUniMed
																					.getICveUniMed())
																					.toString()) == 0)
														out.print("<option value = "
																+ vEQMUniMed.getICveUniMed()
																+ " selected>"
																+ vEQMUniMed.getCDscUniMed()
																+ "</option>");
													else
														out.print("<option value = "
																+ vEQMUniMed.getICveUniMed() + ">"
																+ vEQMUniMed.getCDscUniMed()
																+ "</option>");
												}
											} else {
												out.print("<option value = 0>Datos no disponibles...</option>");
											}
										}
										out.print("</select>");
										out.print("</td>");
										out.print("</tr>");

										out.print("<tr><td colspan='4' class='ETablaT'>Módulo</td></tr>");

										boolean lAsignacion = clsConfig.getLPagina(cAsignacion);

										// N U E V O

										if (lNuevo) { // Modificar de acuerdo al catálogo específico

											out.println("<tr>");
											out.print(vEti.Texto("EEtiqueta", "Clave:"));
											out.println("<td>");
											out.print("<input type='text' size='10' disabled>");
											out.println("</td>");
											out.print("<td class='EEtiqueta'>Vigente:</td>");
											out.print("<td class='ECampo'>Si<input type='radio' name='lVigente' value='Si' checked>");
											out.print("No<input type='radio' name='lVigente' value='No'></td>");
											out.println("</tr>");

											out.println("<tr>");
											out.print("<td class='EEtiqueta'>Nombre:</td>");
											out.print("<td colspan='3'><input type='text' name='cDscModulo' size=40 maxLength=30 OnBlur='fMayus(this);'></td>");
											out.println("</tr>");

											out.println("<tr>");
											out.print("<td class='EEtiqueta'>CLUES:</td>");
											out.print("<td colspan='3'><input type='text' name='cClues' size=20 maxLength=12 OnBlur='fMayus(this);'></td>");
											out.println("</tr>");

											out.print("<tr>");
											out.print("<td class='ETablaT' colspan='4'>Ubicación</td>");
											out.print("</tr>");

											out.print("<tr>");
											out.print("<td class='EEtiqueta'>Calle:</td>");
											out.print("<td colspan='3'><input type='text' name='cCalle' size=40 maxLength=50 OnBlur='fMayus(this);'></td>");
											out.print("</tr>");

											out.print("<tr>");
											out.print("<td class='EEtiqueta'>Colonia:</td>");
											out.print("<td><input type='text' name='cColonia' size=40 maxLength=30 OnBlur='fMayus(this);'></td>");
											out.print("<td class='EEtiqueta'>Ciudad:</td>");
											out.print("<td><input type='text' name='cCiudad' size=40 maxLength=50 OnBlur='fMayus(this);'></td>");
											out.print("</tr>");

											out.print("<tr>");
											out.print("<td class='EEtiqueta'>Pais:</td>");
											out.print("<td>");
											out.print("<select name=\"iCvePais\" size=\"1\" OnChange=\"llenaSLT(1,document.forms[0].iCvePais.value,'','',document.forms[0].iCveEstado);\">");
											vcPais = dPais.FindByAll(" order by cNombre ");
											if (vcPais.size() > 0) {
												out.print("<option value = 0>Seleccione...</option>");
												for (int i = 0; i < vcPais.size(); i++) {
													vPais = (TVPais) vcPais.get(i);
													out.print("<option value = " + vPais.getICvePais()
															+ ">" + vPais.getCNombre() + "</option>");
												}
											} else {
												out.print("<option value = 0>Datos no disponibles...</option>");
											}
											out.print("</select>");
											out.print("</td>");

											out.print("<td class='EEtiqueta'>EDO (Estado):</td>");
											out.print("<td>");
											out.print("<select name=\"iCveEstado\" size=\"1\" OnChange=\"llenaSLT(2,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,'',document.forms[0].iCveMunicipio);\">");
											if (request.getParameter("iCvePais") != null) {
												if (Integer.parseInt(request.getParameter("iCvePais")
														.toString()) < 1)
													out.print("<option selected>Datos no disponibles...</option>");
											} else
												out.print("<option selected>Datos no disponibles...</option>");
											out.print("</select></td>");
											out.print("</tr>");

											out.print("<tr>");
											out.print("<td class='EEtiqueta'>Municipio o Delegación:</td>");
											out.print("<td><select name='iCveMunicipio'>");
											if (request.getParameter("iCveEstado") != null) {
												if (Integer.parseInt(request.getParameter("iCveEstado")
														.toString()) < 1)
													out.print("<option selected>Datos no disponibles...</option>");
											} else
												out.print("<option selected>Datos no disponibles...</option>");
											out.print("</select></td>");

											out.print("<td class='EEtiqueta'>C.P.:</td>");
											out.print("<td><input type='text' name='iCP' size=10 maxLength=5></td>");
											out.print("</tr>");

											out.print("<tr>");
											out.print("<td class='EEtiqueta'>Teléfono:</td>");
											out.print("<td><input type='text' name='cTel' size=40 maxLength=20></td>");
											out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
											out.print("<td><input type='text' name='cCorreoE' size=40 maxLength=100></td>");
											out.print("</tr>");

											out.print("<tr>");
											out.print("<td colspan='4' class='ETablaT'>Configuración1</td>");
											out.print("</tr>");

											out.print("<tr>");
											out.print("<td colspan='2' class='EEtiqueta'>Interconsulta:</td>");
											out.print("<td class='ECampo' colspan='2'>Si<input type='radio' name='linterconsulta' value='Si' checked>");
											out.print("No<input type='radio' name='linterconsulta' value='No'></td>");
											out.print("</tr>");
											out.print("<tr>");
											out.print("<td colspan='2' class='EEtiqueta'>¿Habilitar validación biometrica?:</td>");

											int lvalida = 0;
											if (bs != null) {
												if (bs.getFieldValue("lValida") != null)
													lvalida = Integer.parseInt(bs.getFieldValue(
															"lValida").toString());
											}
											out.print("<input type='hidden' name='hdLValidaAnt' value='"
													+ lvalida + "'>");
											if (lvalida == 0) {
												out.print("<td class='ECampo'>"
														+ "<select name='lValida' id='lValida' onchange=''>"
														+ "<option value='0' selected='selected'>Ninguno</option>"
														+ "<option value='1'>Doctor</option>"
														+ "<option value='2'>Paciente</option>"
														+ "<option value='3'>Ambos</option>"
														+ "</select></td>");
												out.print("<td colspan='2' class='ECampo'> </td>");
											}

											if (lvalida == 1) {
												out.print("<td class='ECampo'>"
														+ "<select name='lValida' id='lValida' onchange=''>"
														+ "<option value='0'>Ninguno</option>"
														+ "<option value='1' selected='selected'>Doctor</option>"
														+ "<option value='2'>Paciente</option>"
														+ "<option value='3'>Ambos</option>"
														+ "</select></td>");
												out.print("<td colspan='2' class='ECampo'> </td>");
											}
											if (lvalida == 2) {
												out.print("<td class='ECampo'>"
														+ "<select name='lValida' id='lValida' onchange=''>"
														+ "<option value='0'>Ninguno</option>"
														+ "<option value='1'>Doctor</option>"
														+ "<option value='2' selected='selected'>Paciente</option>"
														+ "<option value='3'>Ambos</option>"
														+ "</select></td>");
												out.print("<td colspan='2' class='ECampo'> </td>");
											}
											if (lvalida == 3) {
												out.print("<td class='ECampo'>"
														+ "<select name='lValida' id='lValida' onchange=''>"
														+ "<option value='0'>Ninguno</option>"
														+ "<option value='1'>Doctor</option>"
														+ "<option value='2'>Paciente</option>"
														+ "<option value='3' selected='selected'>Ambos</option>"
														+ "</select></td>");
												out.print("<td colspan='2' class='ECampo'> </td>");
											}
											out.print("</tr>");

										} else {
											if (bs != null) {

												// M O D I F I C A R

												if (request.getParameter("hdBoton").compareTo(
														"Modificar") == 0) {

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Clave:</td>");
													out.print("<td class='ECampo'>"
															+ bs.getFieldValue("iCveModulo", "&nbsp;")
															+ "</td>");
													out.print("<td class='EEtiqueta'>Vigente:</td>");
													if (Integer.parseInt(bs.getFieldValue("lVigente")
															.toString()) == 1) {
														out.print("<td class='ECampo'>Si<input type='radio' name='lVigente' value='Si' checked>");
														out.print("No<input type='radio' name='lVigente' value='No'></td>");
													} else {
														out.print("<td class='ECampo'>Si<input type='radio' name='lVigente' value='Si'>");
														out.print("No<input type='radio' name='lVigente' value='No' checked></td>");
													}
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Nombre:</td>");
													out.print("<td colspan='3'><input type='text' name='cDscModulo' size=40 maxLength=30 OnBlur='fMayus(this);' value='");
													out.print("" + bs.getFieldValue("cDscModulo"));
													out.print("'></td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>CLUES:</td>");
													out.print("<td colspan='3'><input type='text' name='cClues' size=20 maxLength=12 OnBlur='fMayus(this);' value='");
													out.print("" + bs.getFieldValue("cClues"));
													out.print("'></td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='ETablaT' colspan='4'>Ubicación</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Calle:</td>");
													out.print("<td colspan='3'><input type='text' name='cCalle' size=40 maxLength=50 OnBlur='fMayus(this);' value='");
													out.print("" + bs.getFieldValue("cCalle", "&nbsp;"));
													out.print("'></td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Colonia:</td>");
													out.print("<td><input type='text' name='cColonia' size=40 maxLength=30 OnBlur='fMayus(this);' value='");
													out.print(""
															+ bs.getFieldValue("cColonia", "&nbsp;"));
													out.print("'></td>");
													out.print("<td class='EEtiqueta'>Ciudad:</td>");
													out.print("<td><input type='text' name='cCiudad' size=40 maxLength=50 OnBlur='fMayus(this);' value='");
													out.print(""
															+ bs.getFieldValue("cCiudad", "&nbsp;"));
													out.print("'></td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Pais:</td>");
													out.print("<td>");
													out.print("<select name=\"iCvePais\" size=\"1\" OnChange=\"llenaSLT(1,document.forms[0].iCvePais.value,'','',document.forms[0].iCveEstado);\">");
													vcPais = dPais.FindByAll();
													if (vcPais.size() > 0) {
														out.print("<option value = 0>Seleccione...</option>");
														for (int i = 0; i < vcPais.size(); i++) {
															vPais = (TVPais) vcPais.get(i);
															if (Integer.parseInt(bs.getFieldValue(
																	"iCvePais").toString()) == Integer
																	.parseInt(vPais.getICvePais() + ""))
																out.print("<option selected value = "
																		+ vPais.getICvePais() + ">"
																		+ vPais.getCNombre()
																		+ "</option>");
															else
																out.print("<option value = "
																		+ vPais.getICvePais() + ">"
																		+ vPais.getCNombre()
																		+ "</option>");
														}
													} else {
														out.print("<option value = 0>Datos no disponibles...</option>");
													}
													out.print("</select>");
													out.print("</td>");

													out.print("<td class='EEtiqueta'>EDO (Estado):</td>");
													out.print("<td>");
													out.print("<select name=\"iCveEstado\" size=\"1\" OnChange=\"llenaSLT(2,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,'',document.forms[0].iCveMunicipio);\">");
													vcEntidadFed = dEntidadFed
															.FindByAll(" where iCvePais = "
																	+ bs.getFieldValue("iCvePais"));
													if (vcEntidadFed.size() > 0) {
														out.print("<option value = 0>Seleccione...</option>");
														for (int i = 0; i < vcEntidadFed.size(); i++) {
															vEntidadFed = (TVEntidadFed) vcEntidadFed
																	.get(i);
															if (Integer.parseInt(bs.getFieldValue(
																	"iCveEstado").toString()) == Integer
																	.parseInt(vEntidadFed
																			.getICveEntidadFed() + ""))
																out.print("<option selected value = "
																		+ vEntidadFed
																				.getICveEntidadFed()
																		+ ">"
																		+ vEntidadFed.getCNombre()
																		+ "</option>");
															else
																out.print("<option value = "
																		+ vEntidadFed
																				.getICveEntidadFed()
																		+ ">"
																		+ vEntidadFed.getCNombre()
																		+ "</option>");
														}
													} else {
														out.print("<option value = 0>Datos no disponibles...</option>");
													}
													out.print("</select>");
													out.print("</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Municipio o Delegación:</td>");
													out.print("<td>");
													out.print("<select name=\"iCveMunicipio\" size=\"1\">");
													vcMunicipio = dMunicipio
															.FindByAll(" where iCvePais = "
																	+ bs.getFieldValue("iCvePais")
																	+ " and iCveEntidadFed = "
																	+ bs.getFieldValue("iCveEstado"));
													if (vcMunicipio.size() > 0) {
														out.print("<option value = 0>Seleccione...</option>");
														for (int i = 0; i < vcMunicipio.size(); i++) {
															vMunicipio = (TVMunicipio) vcMunicipio
																	.get(i);
															if (Integer.parseInt(bs.getFieldValue(
																	"iCveMunicipio").toString()) == Integer
																	.parseInt(vMunicipio
																			.getICveMunicipio() + ""))
																out.print("<option selected value = "
																		+ vMunicipio.getICveMunicipio()
																		+ ">" + vMunicipio.getCNombre()
																		+ "</option>");
															else
																out.print("<option value = "
																		+ vMunicipio.getICveMunicipio()
																		+ ">" + vMunicipio.getCNombre()
																		+ "</option>");
														}
													} else {
														out.print("<option value = 0>Datos no disponibles...</option>");
													}
													out.print("</select>");
													out.print("</td>");

													out.print("<td class='EEtiqueta'>C.P.:</td>");
													out.print("<td><input type='text' name='iCP' size=10 maxLength=5 value='");
													out.print("" + bs.getFieldValue("iCP", "&nbsp;"));
													out.print("'></td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Teléfono:</td>");
													out.print("<td><input type='text' name='cTel' size=40 maxLength=20 value='");
													out.print("" + bs.getFieldValue("cTel", "&nbsp;"));
													out.print("'></td>");
													out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
													out.print("<td><input type='text' name='cCorreoE' size=40 maxLength=100 value='");
													out.print(""
															+ bs.getFieldValue("cCorreoe", "&nbsp;"));
													out.print("'></td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='ETablaT' colspan='4'>Configuración2</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td colspan='2' class='EEtiqueta'>Interconsulta:</td>");
													if (Integer.parseInt(bs.getFieldValue(
															"linterconsulta").toString()) == 1) {
														out.print("<td class='ECampo' colspan='2'>Si<input type='radio' name='linterconsulta' value='Si' checked>");
														out.print("No<input type='radio' name='linterconsulta' value='No'></td>");
													} else {
														out.print("<td class='ECampo' colspan='2'>Si<input type='radio' name='linterconsulta' value='Si'>");
														out.print("No<input type='radio' name='linterconsulta' value='No' checked></td>");
													}
													out.print("</tr>");
													out.print("<tr>");
													out.print("<td colspan='2' class='EEtiqueta'>¿Habilitar validación biometrica?:</td>");

													int lvalida = 0;
													if (bs != null) {
														if (bs.getFieldValue("lValida") != null)
															lvalida = Integer.parseInt(bs
																	.getFieldValue("lValida")
																	.toString());
													}
													out.print("<input type='hidden' name='hdLValidaAnt' value='"
															+ lvalida + "'>");
													if (lvalida == 0) {
														out.print("<td colspan='2' class='ECampo'>"
																+ "<select name='lValida' id='lValida' onchange=''>"
																+ "<option value='0' selected='selected'>Ninguno</option>"
																+ "<option value='1'>Doctor</option>"
																+ "<option value='2'>Paciente</option>"
																+ "<option value='3'>Ambos</option>"
																+ "</select></td>");
														//out.print("<td colspan='2' class='ECampo'> </td>");
													}

													if (lvalida == 1) {
														out.print("<td colspan='2' class='ECampo'>"
																+ "<select name='lValida' id='lValida' onchange=''>"
																+ "<option value='0'>Ninguno</option>"
																+ "<option value='1' selected='selected'>Doctor</option>"
																+ "<option value='2'>Paciente</option>"
																+ "<option value='3'>Ambos</option>"
																+ "</select></td>");
														//out.print("<td colspan='2' class='ECampo'> </td>");
													}
													if (lvalida == 2) {
														out.print("<td colspan='2' class='ECampo'>"
																+ "<select name='lValida' id='lValida' onchange=''>"
																+ "<option value='0'>Ninguno</option>"
																+ "<option value='1'>Doctor</option>"
																+ "<option value='2' selected='selected'>Paciente</option>"
																+ "<option value='3'>Ambos</option>"
																+ "</select></td>");
														//out.print("<td colspan='2' class='ECampo'> </td>");
													}
													if (lvalida == 3) {
														out.print("<td colspan='2' class='ECampo'>"
																+ "<select name='lValida' id='lValida' onchange=''>"
																+ "<option value='0'>Ninguno</option>"
																+ "<option value='1'>Doctor</option>"
																+ "<option value='2'>Paciente</option>"
																+ "<option value='3' selected='selected'>Ambos</option>"
																+ "</select></td>");
														//out.print("<td colspan='2' class='ECampo'> </td>");
													}
													out.print("</tr>");

													/////Dispositivos/// 
													vcDispositivo = dDisp.FindByAll();
													String cWhere = "";
													try {
														if (vcDispositivo.size() > 0) {
															for (int i = 0; i < vcDispositivo.size(); i++) {
																vDispositivo = (TVGRLDispositivo) vcDispositivo
																		.get(i);

																///Verificar que el dispositivo este activo
																cWhere = " where "
																		+ "A.iCveUnimed = "
																		+ bs.getFieldValue("iCveUniMed")
																		+ " and "
																		+ "A.iCveModulo = "
																		+ bs.getFieldValue("iCveModulo")
																		+ " and "
																		+ "A.iCveDispositivo = "
																		+ vDispositivo
																				.getICveDispositivos()
																		+ " and A.lActivo = 1 and ";
																DispositivoActivo = dMDisp
																		.FindByTrue(cWhere);
																out.print("<tr>");
																out.print("<td colspan='2' class='EEtiqueta'>Dispositivo "
																		+ vDispositivo
																				.getCNombreDispositivo()
																		+ ":</td>");
																//System.out.println("DispositivoActivo"+DispositivoActivo);
																if (!DispositivoActivo) {
																	//out.print("<td colspan='2' class='ECampo'>Inhabilitado</td>");
																	out.print("<td class='ECampo' colspan='2'>Habilitado<input type='radio' name='D"
																			+ vDispositivo
																					.getICveDispositivos()
																			+ "' value='Si'>");
																	out.print("Inhabilitado<input type='radio' name='D"
																			+ vDispositivo
																					.getICveDispositivos()
																			+ "' value='No' checked></td>");
																	out.print("<input type='hidden' name='hdLValidaDispAnt"+vDispositivo
																			.getICveDispositivos()
																	+"' value='No'>");
																} else {
																	//out.print("<td colspan='2' class='ECampo'>Habilitado</td>");
																	out.print("<td class='ECampo' colspan='2'>Habilitado<input type='radio' name='D"
																			+ vDispositivo
																					.getICveDispositivos()
																			+ "' value='Si' checked>");
																	out.print("Inhabilitado<input type='radio' name='D"
																			+ vDispositivo
																					.getICveDispositivos()
																			+ "' value='No'></td>");
																	out.print("<input type='hidden' name='hdLValidaDispAnt"+vDispositivo
																			.getICveDispositivos()
																	+"' value='Si'>");
																}
																out.print("</tr>");
																
															}
														}
													} catch (Exception e) {
														System.out.println("Se produjo un error al tratar de obtener el listado de Dispositivos");
													}

												} else {

													// V E R   D A T O S

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Clave:</td>");
													out.print("<td class='ECampo'>"
															+ bs.getFieldValue("iCveModulo", "")
															+ "</td>");
													out.print("<input type='hidden' name='iCveModulo' value='"
															+ bs.getFieldValue("iCveUniMed", "&nbsp;")
															+ "'>");
													out.print("<td class='EEtiqueta'>Vigente:</td>");
													if (Integer.parseInt(""
															+ bs.getFieldValue("lVigente")) == 0)
														out.print("<td class='ECampo'>NO</td>");
													else
														out.print("<td class='ECampo'>SI</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Nombre:</td>");
													out.print("<td class='ECampo' colspan='3'>"
															+ bs.getFieldValue("cDscModulo", "&nbsp;")
															+ "</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>CLUES:</td>");
													out.print("<td class='ECampo' colspan='3'>"
															+ bs.getFieldValue("cClues", "&nbsp;")
															+ "</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='ETablaT' colspan='4'>Ubicación</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Calle:</td>");
													out.print("<td class='ECampo' colspan='3'>"
															+ bs.getFieldValue("cCalle", "&nbsp;")
															+ "</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Colonia:</td>");
													out.print("<td class='ECampo'>"
															+ bs.getFieldValue("cColonia", "&nbsp;")
															+ "</td>");
													out.print("<td class='EEtiqueta'>Ciudad:</td>");
													out.print("<td class='ECampo'>"
															+ bs.getFieldValue("cCiudad", "&nbsp;")
															+ "</td>");
													out.print("</tr>");

													out.print("<tr>");
													vcPais = dPais.FindByAll(" where iCvePais = "
															+ bs.getFieldValue("iCvePais", "&nbsp;"));
													if (vcPais.size() > 0)
														for (int i = 0; i < vcPais.size(); i++)
															vPais = (TVPais) vcPais.get(i);
													out.print("<td class='EEtiqueta'>Pais:</td>");
													out.print("<td class='ECampo'>"
															+ vPais.getCNombre() + "</td>");
													vcEntidadFed = dEntidadFed
															.FindByAll(" where iCvePais = "
																	+ bs.getFieldValue("iCvePais",
																			"&nbsp;")
																	+ " and iCveEntidadFed = "
																	+ bs.getFieldValue("iCveEstado",
																			"&nbsp;"));
													if (vcEntidadFed.size() > 0)
														for (int i = 0; i < vcEntidadFed.size(); i++)
															vEntidadFed = (TVEntidadFed) vcEntidadFed
																	.get(i);
													out.print("<td class='EEtiqueta'>EDO (Estado):</td>");
													out.print("<td class='ECampo'>"
															+ vEntidadFed.getCNombre() + "</td>");
													out.print("</tr>");

													out.print("<tr>");
													vcMunicipio = dMunicipio
															.FindByAll(" where iCvePais = "
																	+ bs.getFieldValue("iCvePais",
																			"&nbsp;")
																	+ " and iCveEntidadFed = "
																	+ bs.getFieldValue("iCveEstado",
																			"&nbsp;")
																	+ " and iCveMunicipio = "
																	+ bs.getFieldValue("iCveMunicipio",
																			"&nbsp;"));
													if (vcMunicipio.size() > 0)
														for (int i = 0; i < vcMunicipio.size(); i++)
															vMunicipio = (TVMunicipio) vcMunicipio
																	.get(i);
													out.print("<td class='EEtiqueta'>MUN (Municipio):</td>");
													out.print("<td class='ECampo'>"
															+ vMunicipio.getCNombre() + "</td>");
													out.print("<td class='EEtiqueta'>C.P.:</td>");
													out.print("<td class='ECampo'>"
															+ bs.getFieldValue("iCP", "&nbsp;")
															+ "</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='EEtiqueta'>Teléfono:</td>");
													out.print("<td class='ECampo'>"
															+ bs.getFieldValue("cTel", "&nbsp;")
															+ "</td>");
													out.print("<td class='EEtiqueta'>Correo Electrónico:</td>");
													out.print("<td class='ECampo'>"
															+ bs.getFieldValue("cCorreoe", "&nbsp;")
															+ "</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td class='ETablaT' colspan='4'>Configuración3</td>");
													out.print("</tr>");

													out.print("<tr>");
													out.print("<td colspan='2' class='EEtiqueta'>Interconsulta:</td>");
													if (Integer.parseInt(""
															+ bs.getFieldValue("linterconsulta")) == 0)
														out.print("<td colspan='2' class='ECampo'>NO</td>");
													else
														out.print("<td colspan='2' class='ECampo'>SI</td>");
													out.print("</tr>");
													out.print("<tr>");
													out.print("<td colspan='2' class='EEtiqueta'>¿Habilitar validación biometrica?:</td>");

													int lvalida = 0;
													if (bs != null) {
														if (bs.getFieldValue("lValida") != null)
															lvalida = Integer.parseInt(bs
																	.getFieldValue("lValida")
																	.toString());
													}
													out.print("<input type='hidden' name='hdLValidaAnt' value='"
															+ lvalida + "'>");
													if (lvalida == 0) {
														out.print("<td colspan='2' class='ECampo'>No solicitar</td>");
													}

													if (lvalida == 1) {
														out.print("<td colspan='2' class='ECampo'>Solo a Doctores</td>");
													}
													if (lvalida == 2) {
														out.print("<td colspan='2' class='ECampo'>Solo a Pacientes</td>");
													}
													if (lvalida == 3) {
														out.print("<td colspan='2' class='ECampo'>Solicitar a ambos</td>");
													}
													out.print("</tr>");

													/////Dispositivos/// 
													vcDispositivo = dDisp.FindByAll();
													String cWhere = "";
													try {
														if (vcDispositivo.size() > 0) {
															for (int i = 0; i < vcDispositivo.size(); i++) {
																vDispositivo = (TVGRLDispositivo) vcDispositivo
																		.get(i);

																///Verificar que el dispositivo este activo
																cWhere = " where "
																		+ "A.iCveUnimed = "
																		+ bs.getFieldValue("iCveUniMed")
																		+ " and "
																		+ "A.iCveModulo = "
																		+ bs.getFieldValue("iCveModulo")
																		+ " and "
																		+ "A.iCveDispositivo = "
																		+ vDispositivo
																				.getICveDispositivos()
																		+ " and A.lActivo=1 and ";
																DispositivoActivo = dMDisp
																		.FindByTrue(cWhere);
																out.print("<tr>");
																out.print("<td colspan='2' class='EEtiqueta'>Dispositivo "
																		+ vDispositivo
																				.getCNombreDispositivo()
																		+ ":</td>");
																if (!DispositivoActivo) {
																	out.print("<td colspan='2' class='ECampo'>Inhabilitado</td>");
																} else {
																	out.print("<td colspan='2' class='ECampo'>Habilitado</td>");
																}
																out.print("</tr>");
															}
														}
													} catch (Exception e) {
														System.out
																.println("Se produjo un error al tratar de obtener el listado de Dispositivos");
													}

													if (lAsignacion) {
														out.print("<td class='ETablaC' colspan='4'>");
														out.print(vEti
																.clsAnclaTexto(
																		"EAncla",
																		"Asignación de Areas",
																		"JavaScript:fIrAsignacion('"
																				+ bs.getFieldValue("iCveUniMed")
																				+ "', '"
																				+ bs.getFieldValue("iCveModulo")
																				+ "');", ""));
														out.print("</td>");
													}
												}
											} else {
												out.print("<tr>");
												out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
												out.print("</tr>");
											}
										}
								%>
							
					</table> <%
 	// Fin de Datos
 %>
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
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%
	vEntorno.clearListaImgs();
%>
</html>
