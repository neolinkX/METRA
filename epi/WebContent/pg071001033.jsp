
<%
	/**
	 * Catalogo de Tox Mensajeria
	 * 
	 * @author Ing. Andres Esteban Bernal Munoz.
	 */
%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
	pg071001033CFG clsConfig = new pg071001033CFG(); // modificar

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setNombrePagina("pg071001033.jsp"); // modificar
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setArchFuncs("FValida");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg071001033.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cDetalle = "pg071001034.jsp"; // modificar
	String cOperador = "1"; // modificar
	String cDscOrdenar = "Clave|Descripción|"; // modificar 
	String cCveOrdenar = "ToxMensajeria.iCveMensajeria|ToxMensajeria.cDscMensajeria|"; // modificar
	String cDscFiltrar = "Clave|Descripción|"; // modificar 
	String cCveFiltrar = "ToxMensajeria.iCveMensajeria|ToxMensajeria.cDscMensajeria|"; // modificar
	String cTipoFiltrar = "7|8|"; // modificar
	boolean lFiltros = true; // modificar
	boolean lIra = true; // modificar
	String cEstatusIR = "Imprimir"; // modificar

	// LLamado al Output Header
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	int iNumRowsPrin = new Integer(
			vParametros.getPropEspecifica("NumRowsPrin")).intValue();
	int iNumRowsSec = new Integer(
			vParametros.getPropEspecifica("NumRowsSec")).intValue();

	int iAniosAtras = new Integer(
			vParametros.getPropEspecifica("Anios")).intValue();
	TFechas dtFecha = new TFechas();
	int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

	TError vErrores = new TError();
	StringBuffer sbErroresAcum = new StringBuffer();
	TEtiCampo vEti = new TEtiCampo();

	clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	String cPaginas = clsConfig.getPaginas();
	String cDscPaginas = clsConfig.getDscPaginas();
	PageBeanScroller bs = clsConfig.getBeanScroller();
	String cUpdStatus = "Hidden";
	String cNavStatus = clsConfig.getNavStatus();
	String cOtroStatus = clsConfig.getOtroStatus();
	String cCanWrite = "No";
	String cSaveAction = "Guardar";
	String cDeleteAction = "BorrarB";
	String cClave = "";
	String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0,
							vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<head>
<meta name="Autor"
	content="<%=vParametros.getPropEspecifica("Autor")%>">
</head>
<%
	new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
%>
<link rel="stylesheet"
	href="<%=vParametros.getPropEspecifica("RutaCSS")
					+ vParametros.getPropEspecifica("HojaCSS")%>"
	TYPE="text/css">
<body bgcolor="<%=vParametros.getPropEspecifica("ColorFondoPagina")%>"
	topmargin="0" leftmargin="0" onLoad="<%=vEntorno.getOnLoad()%>">
	<form method="<%=vEntorno.getMetodoForm()%>"
		action="<%=vEntorno.getActionForm()%>">
		<input type="hidden" name="hdLCondicion"
			value="<%if (request.getParameter("hdLCondicion") != null)
				out.print(request.getParameter("hdLCondicion"));%>">
		<input type="hidden" name="hdLOrdenar"
			value="<%if (request.getParameter("hdLOrdenar") != null)
				out.print(request.getParameter("hdLOrdenar"));%>">
		<input type="hidden" name="FILNumReng"
			value="<%if (request.getParameter("FILNumReng") != null)
				out.print(request.getParameter("FILNumReng"));
			else
				out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
		<%
			if (bs != null) {
				cPosicion = Integer.toString(bs.pageNo());
				cClave = "" + bs.getFieldValue("iCveMensajeria", "");
			}
		%>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave1" value=""> <input
			type="hidden" name="iCveMensajeria"
			value="<%if (cClave.compareTo("") == 0)
				out.print(request.getParameter("iCveMensajeria"));
			else
				out.print(cClave);%>">
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
							<td colspan="4" class="ETablaT">Catálogo de Mensajería</td>
						</tr>
						<%
							// modificar según listado
								if (bs != null) {
									boolean lDetalle = clsConfig.getLPagina(cDetalle);
									out.print("<tr>");
									out.print("<td class=\"ETablaT\">No.</td>");
									out.print("<td class=\"ETablaT\">Mensaje</td>");
									out.print("<td class=\"ETablaT\">URL</td>");
									if (lDetalle)
										out.print("<td class=\"ETablaT\">Selección</td>");
									out.print("</tr>");
									bs.start();
									String mensajeTmp = "";
									while (bs.nextRow()) {
										out.print("<tr>");
										mensajeTmp = bs.getFieldValue("cDscMensajeria",
												"&nbsp;").toString();
										out.print(vEti.Texto("ETablaR",
												bs.getFieldValue("iCveMensajeria", "&nbsp;")
														.toString()));
										if (mensajeTmp.length() > 30)
											out.print(vEti.Texto("ECampo",
													mensajeTmp.substring(0, 30) + "..."));
										else
											out.print(vEti.Texto("ECampo", mensajeTmp));
										mensajeTmp = bs.getFieldValue("SitioOficial", "&nbsp;")
												.toString();
										if (mensajeTmp.length() > 30)
											out.print(vEti.Texto("ECampo",
													mensajeTmp.substring(0, 30) + "..."));
										else
											out.print(vEti.Texto("ECampo", mensajeTmp));

										if (lDetalle) {
											out.print("<td class=\"ECampo\">");
											out.print(vEti
													.clsAnclaTexto(
															"EAncla",
															"Detalle",
															"JavaScript:fIrDetalle('"
																	+ bs.getFieldValue("iCveMensajeria")
																	+ "');", ""));
											out.print("</td>");
										}
										out.print("</tr>");
									}
								} else {
									out.println("<tr>");
									out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
									out.println("</tr>");
								}
						%>
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
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%
	vEntorno.clearListaImgs();
%>
</html>
