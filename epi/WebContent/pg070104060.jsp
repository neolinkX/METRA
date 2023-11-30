
<%
	/**
	 * Title:
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
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
	pg070104060CFG clsConfig = new pg070104060CFG(); // modificar

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setNombrePagina("pg070104060.jsp"); // modificar
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setArchFuncs("FValida");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg070104060.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cCatalogo = "pg070101011.jsp"; // modificar
	String cOperador = "1"; // modificar
	String cDscOrdenar = "No Aplica"; // modificar
	String cCveOrdenar = ""; // modificar
	String cDscFiltrar = "No Aplica"; // modificar
	String cCveFiltrar = ""; // modificar
	String cTipoFiltrar = ""; // modificar
	boolean lFiltros = false; // modificar
	boolean lIra = false; // modificar
	String cEstatusIR = ""; // modificar

	// LLamado al Output Header
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	int iNumRowsPrin = new Integer(
			vParametros.getPropEspecifica("NumRowsPrin")).intValue();
	int iNumRowsSec = new Integer(
			vParametros.getPropEspecifica("NumRowsSec")).intValue();
	int iEPIProceso = new Integer(
			vParametros.getPropEspecifica("EPIProceso")).intValue();

	TError vErrores = new TError();
	StringBuffer sbErroresAcum = new StringBuffer();
	TEtiCampo vEti = new TEtiCampo();

	clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	String cPaginas = clsConfig.getPaginas();
	String cDscPaginas = clsConfig.getDscPaginas();
	PageBeanScroller bs = clsConfig.getBeanScroller();
	String cUpdStatus = "Hidden";
	String cNavStatus = "Hidden";
	String cOtroStatus = "Hidden";
	String cCanWrite = "No";
	String cSaveAction = "Guardar";
	String cDeleteAction = "BorrarB";
	String cClave = "";
	String cPosicion = "";

	TVUsuario vUsuario = (TVUsuario) request.getSession(true)
			.getAttribute("UsrID");

	TDEXPServicio dEXPServicio = new TDEXPServicio();
	TVEXPServicio vEXPServicio = new TVEXPServicio();
	Vector vcEXPServicio = new Vector();
	TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
	TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
	Vector vcEXPExamAplica = new Vector();
	int examenes = 0;
%>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0,
							vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "jquery-1.6.4.min.js"%>"></SCRIPT>					
<style> 
	#cuadroFlotante{
			position:absolute;
			right:20px;
			top:0;
			line-height: 15px;
			font-weight: bold;
			text-align:left;
			padding:20px;
			border-bottom-style: solid;
			border-left-style: solid;
		}
		#cuadroFlotante2{
			/*position:absolute;*/
			right:20px;
			top:0;
			line-height: 15px;
			font-weight: bold;
			text-align:left;
			padding:20px;
			border-bottom-style: solid;
			border-left-style: solid;
		}
</style>					
		
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
	new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
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
			}
		%>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave1" value=""> <input
			type="hidden" name="hdiCveExpediente" value=""> <input
			type="hidden" name="hdiNumExamen" value=""><input
			type="hidden" name="hdSolicitado" value="">


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
						   <%if (request.getParameter("hdSolicitado") != null
									&& request.getParameter("hdSolicitado").compareTo(
									"") != 0
							&& (!request.getParameter("hdSolicitado").equals(
									"null"))
							&& (!request.getParameter("hdSolicitado").equals(
									"0"))) {%>
														
									<div class="ETablaT" id="cuadroFlotante" >
				 	 						Seleccione el examen
				 	 					 <br>que desea Consultar
				 	 					 <br>______________________
				 	 					 <div class="ETablaInfo" id="cuadroFlotante2" >
				 	 					 <%
				 	 					vcEXPExamAplica = dEXPExamAplica.FindByAllEPIEMO(" Where EXPExamAplica.iCveExpediente = "+request.getParameter("hdiCveExpediente")+" and EXPExamAplica.iCveProceso < 3 ");
				 	 					for(int i=0;i<vcEXPExamAplica.size();i++){
											vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(i);
											if(i==0){
												out.print("<a class=\"EAncla\" name=\"\"");
											}else{
												out.print("<br> <a class=\"EAncla\" name=\"\"");
											}
											
											out.print("href=\"JavaScript:fSelectedPer("+vEXPExamAplica.getICveExpediente()+",");
											out.print(vEXPExamAplica.getINumExamen()+",'"+vEXPExamAplica.getDtSolicitado().toString()+"');\"  ");
											out.print("onMouseOut=\"self.status='';return true;\" ");
											out.print("onMouseOver=\"self.status='Buscar Personal';return true;\">");
											if(vEXPExamAplica.getINumExamen()<10){
												out.print("Examen: "+vEXPExamAplica.getINumExamen());
											}else{
												out.print("Examen: "+vEXPExamAplica.getINumExamen());
											}
											if(vEXPExamAplica.getICveProceso() == 1){
												out.print(" - EPI");
											}else{
												out.print(" - EMO");
											}
											out.print("</a>");
				 	 					} 
				 	 					 %>
			                             
						           </div>
								
						<%
								}
							// Inicio de Datos
						%>
						<%
							out.print("<tr>");
								out.print("<td class='ETablaT' colspan='4'>Recorrido del Personal</td>");
								out.print("</tr>");

								out.print("<tr>");
								out.print("<td class='ETablaC' colspan='4'>");
								out.print(vEti.clsAnclaTexto("EAncla", "Buscar Personal",
										"JavaScript:fSelPer();", "Buscar Personal", ""));
								out.print("</td>");
								out.print("</tr>");

								if (request.getParameter("hdiCveExpediente") != null
										&& request.getParameter("hdiCveExpediente").compareTo(
												"") != 0
										&& (!request.getParameter("hdiCveExpediente").equals(
												"null"))) {
									out.print("<tr>");
									out.print("<td class='ETablaT' colspan='4'>Datos Personales</td>");
									out.print("</tr>");

									TVGRLProceso vGRLProceso = new TVGRLProceso();
									TDGRLProceso dGRLProceso = new TDGRLProceso();
									Vector vcGRLProceso = new Vector();
									vcGRLProceso = dGRLProceso.FindByAll(
											" where iCveProceso = " + iEPIProceso, "");
									vGRLProceso = (TVGRLProceso) vcGRLProceso.get(0);

									TVPERDatos vPERDatos = new TVPERDatos();
									TDPERDatos dPERDatos = new TDPERDatos();
									Vector vcPERDatos = new Vector();
									vcPERDatos = dPERDatos.FindByAll(request
											.getParameter("hdiCveExpediente"));
									if (vcPERDatos.size() > 0)
										vPERDatos = (TVPERDatos) vcPERDatos.get(0);
	
									out.print("<tr>");
									out.print("<td class='EEtiqueta'>Proceso:</td>");
									out.print("<td class='ETabla'>"
											+ vGRLProceso.getCDscProceso() + "</td>");
									out.print("<td class='EEtiqueta'>No. Expediente:</td>");
									out.print("<td class='ETabla'>"
											+ vPERDatos.getICveExpediente() + "</td>");
									out.print("</tr>");
									if (request.getParameter("hdSolicitado") != null
											&& request.getParameter("hdSolicitado").compareTo(
													"") != 0
											&& (!request.getParameter("hdSolicitado").equals(
													"null"))
											&& (!request.getParameter("hdSolicitado").equals(
													"0"))) {
											out.print("<tr>");
											out.print("<td class='EEtiqueta'>Examen:</td>");
											out.print("<td class='ETabla'>"
													+ request.getParameter("hdiNumExamen") + "</td>");
											out.print("<td class='EEtiqueta'>Solicitado:</td>");
											out.print("<td class='ETabla'>"
													+ request.getParameter("hdSolicitado") + "</td>");
											out.print("</tr>");										
									}
									        
									out.print("<tr>");
									out.print("<td class='EEtiqueta'>R.F.C.:</td>");
									out.print("<td class='ETabla'>" + vPERDatos.getCRFC()
											+ "</td>");
									out.print("<td class='EEtiqueta'>Sexo:</td>");
									String cGenero = vPERDatos.getCSexo();
									if (cGenero != null && cGenero.compareTo("M") == 0)
										out.print("<td class='ETabla'>HOMBRE</td>");
									else
										out.print("<td class='ETabla'>MUJER</td>");
									out.print("</tr>");

									out.print("<tr>");
									out.print("<td class='EEtiqueta'>Nombre:</td>");
									out.print("<td class='ETabla' colspan='3'>"
											+ vPERDatos.getCApPaterno() + " "
											+ vPERDatos.getCApMaterno() + " "
											+ vPERDatos.getCNombre() + "</td>");
									out.print("</tr>");
								}

								if (bs != null) {
									bs.start();
									String cServicio = "";
									int iServConcluido = 0;
									out.print("<tr>");
									out.print("<td class='ETablaT' colspan='2'>Servicio / Rama</td>");
									out.print("<td class='ETablaT' colspan='2'>Situaci&oacute;n</td>");
									out.print("</tr>");
									while (bs.nextRow()) {
										if (cServicio.compareTo(bs
												.getFieldValue("cDscServicio").toString()) != 0) {
											cServicio = bs.getFieldValue("cDscServicio")
													.toString();
											vcEXPServicio = dEXPServicio
													.getLConcluido(
															bs.getFieldValue("iCveExpediente")
																	.toString(),
															bs.getFieldValue("iNumExamen")
																	.toString(),
															bs.getFieldValue("iCveServicio")
																	.toString());
											vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
											iServConcluido = vEXPServicio.getLConcluido();
											out.print("<tr>");
											out.print("<td class='ETablaSTC' colspan='2'>"
													+ cServicio + "</td>");
											if (iServConcluido == 1) {
												out.print("<td class='ETablaSTC' >Concluido</td><td class='ETablaSTC'>");
												out.print(vEti
														.clsAnclaTexto(
																"EAncla",
																"Resultado ",
																"JavaScript:fVerExamen("
																		+ bs.getFieldValue(
																				"iCveExpediente")
																				.toString()
																		+ ", "
																		+ bs.getFieldValue(
																				"iNumExamen")
																				.toString()
																		+ ", "
																		+ bs.getFieldValue(
																				"iCveServicio")
																				.toString()
																		+ ", "
																		+ vParametros
																				.getPropEspecifica("EPIProceso")
																		+ ");", "Ver Examen",
																""));
												out.println("</td>");
											} else
												out.print("<td class='EPositivosC' colspan='2'>Sin Concluir</td>");
											out.print("</tr>");
										}
										out.print("<tr>");
										out.print("<td class='EEtiquetaL' colspan='2'>"
												+ bs.getFieldValue("cDscRama") + "</td>");
										if (bs.getFieldValue("iConcluido").toString()
												.compareTo("1") == 0) {
											if (iServConcluido == 0) {
												out.print("<td class='EEtiquetaL'>Concluido</td><td class='ETablaC'>");
												out.print(vEti
														.clsAnclaTexto(
																"EAncla",
																"Resultado ",
																"JavaScript:fVerExamen("
																		+ bs.getFieldValue(
																				"iCveExpediente")
																				.toString()
																		+ ", "
																		+ bs.getFieldValue(
																				"iNumExamen")
																				.toString()
																		+ ", "
																		+ bs.getFieldValue(
																				"iCveServicio")
																				.toString()
																		+ ", "
																		+ vParametros
																				.getPropEspecifica("EPIProceso")
																		+ ");", "Ver Examen",
																""));
												out.println("</td>");
											} else
												out.print("<td class='EEtiquetaL' colspan='2'>Concluido</td>");
										} else
											out.print("<td class='ETabla' colspan='2'>Sin Concluir</td>");
										out.print("</tr>");
									}
								} else {
									if (request.getParameter("hdSolicitado") != null
											&& request.getParameter("hdSolicitado").compareTo(
											"") != 0
									&& (!request.getParameter("hdSolicitado").equals(
											"null"))
									&& (!request.getParameter("hdSolicitado").equals(
											"1"))) {
											out.print("<td class='ETablaT' colspan='4'>Seleccione el examen que desea consultar</td>");
											%>
											<tr><td  colspan='5'>
											<div align="center" class="ECampoC">					
											<div id="capa" style="position:relative; width:500px; height:100px; z-index:1; border: 1px none #000000;"><strong>
											<div align="left">	
						 	 					 <%
						 	 					vcEXPExamAplica = dEXPExamAplica.FindByAllEPIEMO(" Where EXPExamAplica.iCveExpediente = "+request.getParameter("hdiCveExpediente")+" and EXPExamAplica.iCveProceso < 3 ");
						 	 					for(int i=0;i<vcEXPExamAplica.size();i++){
													vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(i);
													out.print("<a class=\"EAncla\" name=\"\"");
													
													out.print("href=\"JavaScript:fSelectedPer("+vEXPExamAplica.getICveExpediente()+",");
													out.print(vEXPExamAplica.getINumExamen()+",'"+vEXPExamAplica.getDtSolicitado().toString()+"');\"  ");
													out.print("onMouseOut=\"self.status='';return true;\" ");
													out.print("onMouseOver=\"self.status='Buscar Personal';return true;\">");
													if(vEXPExamAplica.getINumExamen()<10){
														out.print("Examen: "+vEXPExamAplica.getINumExamen());
													}else{
														out.print("Examen: "+vEXPExamAplica.getINumExamen());
													}
													if(vEXPExamAplica.getICveProceso() == 1){
														out.print(" - EPI");
													}else{
														out.print(" - EMO");
													}
													out.print("</a><br> ");
						 	 					} 
						 	 					 %>
						 	 					 </div>
					                             </strong></div>
								           </div>
								           </td></tr>
										
								<%
								   }else{
									out.print("<tr>");
									out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
									out.print("</tr>");
									}
								}
						%>
					</table></td>
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
 <script>
	$(document).ready(function() {
        var posicion = $("#cuadroFlotante").offset();
        var margenSuperior = 15;
        $("#cuadroFlotante").stop().animate({
                     marginTop: margenSuperior
                 });
         $(window).scroll(function() {
             if ($(window).scrollTop() > posicion.top) {
                 $("#cuadroFlotante").stop().animate({
                     marginTop: $(window).scrollTop() - posicion.top + margenSuperior
                 });
             } else {
                 $("#cuadroFlotante").stop().animate({
                     marginTop: margenSuperior
                 });
             };
         }); 
});
</script>

</html>



