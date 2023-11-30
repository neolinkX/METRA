
<%
	/**
	 * Title: Modificación de Categoría y Motivo para examenes no inicializados.
	 * Description: JSP para listar las categorias y motivos solicitados en un examen.
	 * Copyright: 2006
	 * Company: Micros Personales S.A. de C.V.
	 * @author Itzia María del Carmen Sánchez Méndez
	 * @version 1
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
	pg070106072CFG clsConfig = new pg070106072CFG(); // modificar

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setNombrePagina("pg070106072.jsp"); // modificar
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setArchFuncs("FValida");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg070106072.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cCatalogo = ""; // modificar
	String cOperador = "1"; // modificar
	String cDscOrdenar = "Propiedad|Valor|"; // modificar
	String cCveOrdenar = "cPropiedad|cValor|"; // modificar
	String cDscFiltrar = "Propiedad|Valor|"; // modificar
	String cCveFiltrar = "cPropiedad|cValor|"; // modificar
	String cTipoFiltrar = "7|8|"; // modificar
	boolean lFiltros = false; // modificar
	boolean lIra = false; // modificar
	String cEstatusIR = ""; // modificar

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
%>

<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

function fBuscar(){
		var msg = "";
		if (form.cCodigo.value == ""){
			msg = "Favor de introducir el código\n";
		}
		
		if (!/^([0-9])*$/.test(form.cCodigo.value)){
			msg = "El valor del código no es número\n";
		}
		if(form.cCodigo.value.length < 20){
			form.hdConsulta.value = "2";
			mostrardiv();
			if(form.iCveExpediente.value == ""){
				msg = "Para códigos menores a 20 dígitos deberá ingresar el número de Expediente\n\n      ¡Favor de ingresarlo!";
			}else{
				if (!/^([0-9])*$/.test(form.iCveExpediente.value)){
					msg = "El valor del expediente no es número\n";
				}
			}
			
			
		}else{
			form.hdConsulta.value = "1";
		}
	if(msg == ""){
		form.hdBoton.value = 'Buscar';
		form.target = "_self";
		form.submit();
	}else{
		alert(msg);
	}
}

function mostrardiv() {
    form = document.forms[0];
     //if(form.cCaracter_13.value == 1){
	        div = document.getElementById('iCveExpediente');
	        div.style.display = '';
	        div = document.getElementById('iCveExpediente2');
	        div.style.display = '';
		//}else{
		  //  div = document.getElementById('iCveExpediente');
		    //div.style.display='none';
		    //div = document.getElementById('f2cCaracter_13');
		    //div.style.display='none';
		//}
}


  </SCRIPT>
<head>
<meta name="Autor"
	content="<%=vParametros.getPropEspecifica("Autor")%>">
<style type="text/css">
<!--
.Estilo2 {
	color: #FF0000;
	font-weight: bold;
}

.ECampo {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #003300;
}
-->
</style>
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
		<!--input type="hidden" name="FILNumReng" value="< % if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));% >"-->
		<input type="hidden" name="FILNumReng" value="200"> <input
			type="hidden" name="hdRowNum"
			value="<%=bs != null ? Integer.toString(bs.pageNo()) : ""%>"> <input
			type="hidden" name="hdCampoClave" value="<%=cClave%>"> <input
			type="hidden" name="hdICveExpediente" value=""> <input
			type="hidden" name="hdINumExamen" value=""> <input
			type="hidden" name="hdDtConcluido"
			value="<%=request.getParameter("dtConcluido") != null ? request
					.getParameter("dtConcluido") : request
					.getParameter("hdDtConcluido") != null ? request
					.getParameter("hdDtConcluido") : ""%>">
		<input type="hidden" name="hdICveUniMed"
			value="<%=request.getParameter("iCveUniMed") != null ? request
					.getParameter("iCveUniMed") : request
					.getParameter("hdICveUniMed") != null ? request
					.getParameter("hdICveUniMed") : ""%>">
		<input type="hidden" name="hdICveModulo"
			value="<%=request.getParameter("iCveModulo") != null ? request
					.getParameter("iCveModulo") : request
					.getParameter("hdICveModulo") != null ? request
					.getParameter("hdICveModulo") : ""%>">
		<input type="hidden" name="hdICveProceso"
			value="<%=request.getParameter("iCveProceso") != null ? request
					.getParameter("iCveProceso") : request
					.getParameter("hdICveProceso") != null ? request
					.getParameter("hdICveProceso") : ""%>">
		<input type="hidden" name="hdDtConcluido"
			value="<%=request.getParameter("dtConcluido") != null ? request
					.getParameter("dtConcluido") : request
					.getParameter("hdDtConcluido") != null ? request
					.getParameter("hdDtConcluido") : ""%>">
					
		<input type="hidden" name="hdCPropiedad" value=""> <input
			type="hidden" name="hdBoton" value=""> <input type="hidden"
			name="iNumExamen1" value=""> <input type="hidden"
			name="iNumExamen2" value=""> <input type="hidden"
			name="hdMacAddress" value=""> <input type="hidden"
			name="hdIpAddress" value=""> <input type="hidden"
			name="hdComputerName" value="">
			<input type="hidden" name="hdConsulta" value="">
		<table
			background="<%=vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg"
			width="100%" height="100%">

			<%
				if (clsConfig.getAccesoValido()) {
					String cTmp = request.getParameter("iCveUniMed") != null
							? request.getParameter("iCveUniMed")
							: request.getParameter("hdICveUniMed") != null
									? request.getParameter("hdICveUniMed")
									: "0";
					String cFiltro = "where GRLUsuMedicos.iCveUsuario = "
							+ vUsuario.getICveusuario()
							+ "  and GRLUsuMedicos.iCveUniMed =  " + cTmp;
					//Vector vGModulo = new TDGRLUSUMedicos().FindModulos(cFiltro);
			%>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td valign="top">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<tr>
							<td colspan="6" class="ETablaT">VALIDA CONSTANCIA</td>
						</tr>

						<tr>
							<td class="EEtiqueta"><div align="center">C&Oacute;DIGO:</div></td>
							<td class="ETabla" colspan="3"><span class="ECampo">
									<input type="text" size="35" maxlength="35" name="cCodigo"
									value="" onMouseOut="if (window.fOutField) window.fOutField();"
									onMouseOver="if (window.fOverField) window.fOverField(0);">
									<td class="ETablaC" colspan="2"><a class="EAnclaC"
										name="Buscar" href="javascript:fBuscar();">Validar</a></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div id="iCveExpediente" align="center" style="display:none;">EXPEDIENTE:</div></td>
							<td class="ETabla" colspan="3"><span class="ECampo">
							<div id="iCveExpediente2" style="display:none;">
									<input type="text" size="35" maxlength="35" name="iCveExpediente"
									value="" onMouseOut="if (window.fOutField) window.fOutField();"
									onMouseOver="if (window.fOverField) window.fOverField(0);"></div>
						</tr>
					</table> <%
 	if (request.getParameter("cCodigo") != null
 				&& request.getParameter("cCodigo").trim().length() > 0) {
 			Vector cCodigo = new Vector();
 			Vector Expediente2 = new Vector();
 			TDPERDatos dPERDatos = new TDPERDatos();
 			TVPERDatos vPerDatos;
 			TVPERDatos vPerDatos2;
 			String Igualdad = "";
 			String Resultado = "";

 			try {
 				if(request.getParameter("hdConsulta").equals("1")){
 					cCodigo = dPERDatos.Conatancias(request.getParameter("cCodigo"));
 				}
 				if(request.getParameter("hdConsulta").equals("2")){
 					cCodigo = dPERDatos.ConatFAnt(request.getParameter("cCodigo"),request.getParameter("iCveExpediente")); 
 				}
 				//Expediente2 = dPERDatos.Migraciones(request.getParameter("iCveExpediente2"));

 			} catch (Exception e) {
 				e.printStackTrace();
 			}

 			if (cCodigo.size() > 0) {
 				for (int i = 0; i < cCodigo.size(); i++) {
 					vPerDatos = (TVPERDatos) cCodigo.get(i);
 					//vPerDatos2 = (TVPERDatos) Expediente2.get(i);
 					//        System.out.println(vPerDatos.getICveExpediente());
 					//        System.out.println(vPerDatos.getCApMaterno());
 					//        System.out.println(vPerDatos.getCNombre());

 					//        System.out.println(vPerDatos.getCDscEstadoNac());
 					//        System.out.println(vPerDatos.getINumExamen());
 %> <br>
					<table border=1 class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">

						<tr>
							<td colspan="2" class="ETablaT">Información de la Constancia</td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Nombre:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCNombre()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Apellido
									Paterno:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCApPaterno()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Apellido
									Materno:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCApMaterno()%></span></td>
						</tr>
						<!-- <tr>
							<td class="EEtiqueta"><div align="left">RFC:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCRFC()%></span></td>
						</tr>  -->
						<tr>
							<td class="EEtiqueta"><div align="left">Fecha de
									Nacimiento:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getDtNacimiento()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Pais de
									nacimiento:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCDscPaisNac()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Estado de
									nacimiento:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCDscEstadoNac()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Expediente:</div></td>
							<td class="ECampo"><%=vPerDatos.getICveExpediente()%></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Numero de
									examen:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getINumExamen()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Proceso:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCCalle()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Dictamen:</div></td>
							<td class="ECampo"><span class="ECampo">
									<%
										String Apto = "";
														if (vPerDatos.getICP() == 1) {
															Apto = "Apto";
														} else {
															Apto = "No Apto";
														}
									%><%=Apto%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Unidad Medica:
								</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCDscEmpresa()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Modulo:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getCDscEmpresaTmp()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Solicitado:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getDtSolicitado()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Inicio Vigencia:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getDtIniVig()%></span></td>
						</tr>
						<tr>
							<td class="EEtiqueta"><div align="left">Fin Vigencia:</div></td>
							<td class="ECampo"><span class="ECampo"><%=vPerDatos.getDtFinVig()%></span></td>
						</tr>

					</table> <%
 	}// llave del for
 			} else {
 %>
					<table border=1 class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<td width="370" class="ETablaT">Informaci&oacute;n de
							Conatancia</td>
						<tr>
							<td class="EEtiqueta"><div align="center">No existen
									Datos coincidentes con el filtro</div></td>
						</tr>
					</table> <%
 	}
 		} else {
 			//        System.out.println("La condicion no se cumplio");
 		}
 %> <%
 	} else {
 %> <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
					<%
						}
					%>
				
		</table>
		<%
			if (clsConfig.getIFlag() != 0) {
				out.println("<script language=\"JavaScript\">alert('La información fue modificada de manera correcta.');</script>");
			}
		%>
	</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%
	vEntorno.clearListaImgs();
%>
</html>

mensaje(s)