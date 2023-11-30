
<%
	/**
	 * Muestra el catalogo de Mensajeria,
	 * realiza modificaciones, agrega mensajes nuevos de Tox Mensajeria
	 * @author Ing. Andres Esteban Bernal Munoz.
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
	pg071001034CFG clsConfig = new pg071001034CFG(); // modificar
	TVUsuario vUsuario = (TVUsuario) request.getSession(true)
			.getAttribute("UsrID");

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setNombrePagina("pg071001034.jsp"); // modificar
	vEntorno.setArchFuncs("FValida");
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg071001034.jsp\" target=\"FRMCuerpo"); // modificar
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

	String cAsignacion = "pg071001035.jsp"; // modificar
	String cOperador = "1"; // modificar
	String cDscOrdenar = "Clave|Descripción|"; // modificar
	String cCveOrdenar = "ToxMensajeria.iCveToxMensajeria|ToxMensajeria.cDscMensajeria|"; // modificar
	String cDscFiltrar = "Clave|Descripción|"; // modificar  
	String cCveFiltrar = "ToxMensajeria.iCveToxMensajeria|ToxMensajeria.cDscMensajeria|"; // modificar
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

	TDToxMensajeria dToxMensajeria = new TDToxMensajeria();
	TVToxMensajeria vToxMensajeria = new TVToxMensajeria();
	Vector vcToxMensajeria = new Vector();
%>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0,
							vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg071001034.js)
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
<meta name="Autor"
	content="<%=vParametros.getPropEspecifica("Autor")%>">
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
				cClave = "" + bs.getFieldValue("iCveMensajeria", "");
			}
		%>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave"
			value="<%if (cClave.compareTo("") == 0)
				out.print(request.getParameter("hdCampoClave"));
			else
				out.print(cClave);%>">
		<input type="hidden" name="hdUsuarioActivo"
			value="<%=vUsuario.getICveusuario()%>"> <input type="hidden"
			name="hdMacAddress" value=""> <input type="hidden"
			name="hdIpAddress" value=""> <input type="hidden"
			name="hdComputerName" value="">

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
							<td class="ETablaT" colspan='4'>Catálogo de Mensajería</td>
						</tr>
						<%
							// N U E V O

								if (lNuevo) { // Modificar de acuerdo al catálogo específico
									vcToxMensajeria = dToxMensajeria.FindByLast();
									out.print("<input type='hidden' name='iCveMensajeria' value='"
											+ ((TVToxMensajeria) vcToxMensajeria.get(0))
													.getICveMensajeria() + "'>");
									out.print("<tr>");
									out.print("<td class='EEtiqueta'>Nuevo Mensaje:</td>");
									out.print("<td colspan=3><textarea cols='80' rows ='5' onKeyDown='isValidLength(this);' onKeyUp='isValidLength(this);' maxlength='300' name='cDscMensajeria' value='';></textarea></td>");
									out.print("</tr>");
									out.print("<tr>");
									out.print("<td class='EEtiqueta'>URL Oficial:</td>");									
									out.print("<td colspan='3'><input type='text' onKeyDown='isValidLength(this);' onKeyUp='isValidLength(this);' name='SitioOficial' size='80%' maxLength='300';></td>");
									out.print("</tr>");
								} else {
									if (bs != null) {

										// M O D I F I C A R

										if (request.getParameter("hdBoton").compareTo(
												"Modificar") == 0) {
											out.print("<tr>");											
											out.print("<td class='EEtiqueta'>Modificar Mensaje:</td>");
											out.print("<td colspan=3><textarea cols='80' rows ='5' onKeyDown='isValidLength(this);' onKeyUp='isValidLength(this);' name='cDscMensajeria' value='';>"
													+ bs.getFieldValue("cDscMensajeria")
													+ "</textarea></td>");											
											out.print("<input type='hidden' name='iCveMensajeria' value='"
													+ bs.getFieldValue("iCveMensajeria") + "'>");
											out.print("</tr>");
											out.print("<tr>");											
											out.print("<td class='EEtiqueta'>URL:</td>");											
											out.print("<td colspan='3'><input type='text' onKeyDown='isValidLength(this);' onKeyUp='isValidLength(this);'  name='SitioOficial' size='80%' maxLength='300' value='");											
											out.print(""
													+ bs.getFieldValue("SitioOficial", "&nbsp;"));
											out.print("'></td>");
											out.print("</tr>");
										} else {

											// V E R   D A T O S

											out.print("<tr>");											
											out.print("<td class='EEtiqueta'>Mensaje:</td>");											
											out.print("<td class='ECampo'>"
													+ bs.getFieldValue("cDscMensajeria",
															"&nbsp;") + "</td>");
											out.print("</tr>");
											out.print("<tr>");											
											out.print("<td class='EEtiqueta'>URL:</td>");
											if(bs.getFieldValue("SitioOficial")==null){
											out.print("<td class='ECampo'>"+bs.getFieldValue("SitioOficial","&nbsp;")+"</td>");
											}
											else{
												out.print("<td class='ECampo'><a href='"
														+ bs.getFieldValue("SitioOficial","&nbsp;")
														+ "'>"+ bs.getFieldValue("SitioOficial","&nbsp;")+"</td>");
											}
											
											out.print("</tr>");
											
										}
									} else {
										out.print("<tr>");
										out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
										out.print("</tr>");
									}
								}
						%>
					</table>
					<%
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