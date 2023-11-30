
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
	pg070104060CFG2 clsConfig = new pg070104060CFG2(); // modificar

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
	
	
	
	
	
	
	
	
	
	
	
  
  <!--  BOOTASTRAP -->
    <meta name="description" content="Framedev -  Framework para desarrolladores">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="http://10.33.143.52:7001/epi/assets/app/js/webfont.js"></script>
<script>
    WebFont.load({
       //google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       google: {"families":["Montserrat:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       active: function() {
           sessionStorage.fonts = true;
       }
     });
</script>
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/demo/default/base/menu_alter.css" rel="stylesheet" type="text/css" />

<!--JQuery-->
<script src="http://10.33.143.52:7001/epi/assets/plugins/jquery-3.2.1.min.js"></script>

<!--Plugins JQuery-->
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.min.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />

<!--APP-->
<link href="http://10.33.143.52:7001/epi/css/app.css" rel="stylesheet" type="text/css">

<!--calendar-->
<link href="http://10.33.143.52:7001/epi/assets/vendors/custom/jquery-ui/jquery-ui.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/vendors/custom/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css" />

<!--iconos-->
<link href="http://10.33.143.52:7001/epi/css/svg.css" rel="stylesheet" type="text/css">

<link href="http://10.33.143.52:7001/epi/css/loader.css" rel="stylesheet" type="text/css" />
<script src="http://10.33.143.52:7001/epi/assets/js/loader.js"></script>

<!--jquery steps-->
<link href="http://10.33.143.52:7001/epi/css/jquery.steps.css" rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="http://10.33.143.52:7001/epi/img/favicon.ico">
<!--  FIN BOOTASTRAP -->

  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	
	
	<!-- Bootstrap -->
<div id="contenedor_movimientos">
<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"  data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
	
	
	
	
	
	
	<!-- <form method="<%=vEntorno.getMetodoForm()%>"
		action="<%=vEntorno.getActionForm()%>"> -->
		
		<form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed"
		id="frm_svs" name="frm_svs">
		
		
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
			type="hidden" name="hdiCveExpediente" value="<%=(session.getAttribute("id")+"").toString() %>"> <input
			type="hidden" name="hdiNumExamen" value=""><input
			type="hidden" name="hdSolicitado" value="">


		<table
			background="<%=vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg"
			width="100%" height="100%">
			<%
				if (clsConfig.getAccesoValido()) {
			%>


					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						   <%if (request.getParameter("hdSolicitado") != null
									&& request.getParameter("hdSolicitado").compareTo(
									"") != 0
							&& (!request.getParameter("hdSolicitado").equals(
									"null"))
							&& (!request.getParameter("hdSolicitado").equals(
									"0"))) {
									
							   vcEXPExamAplica = dEXPExamAplica.FindByAllEPIEMO(" Where EXPExamAplica.iCveExpediente = "+request.getParameter("hdiCveExpediente")+" and EXPExamAplica.iCveProceso =1 ");
							   
							   if(vcEXPExamAplica.size() > 1){
									%>
														
									<div class="ETablaT" id="cuadroFlotante" >
				 	 						Seleccione el examen
				 	 					 <br>que desea Consultar
				 	 					 <br>______________________
				 	 					 <div class="ETablaInfo" id="cuadroFlotante2" >
				 	 					 <%
				 	 					
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
			                             
						           </div></div>
								
						<%
							}
								}
							// Inicio de Datos
						%>
						<%
							/*out.print("<div class='m-portlet__foot'>");
								out.print("<div class='m-portlet__foot' class='ETablaT' colspan='4'>Recorrido del Personal</div>");
								out.print("</div>");*/
								%>
								              <div class="m-portlet__head-caption">
         <div class="m-portlet__head-title">
            <span class="m-portlet__head-icon">
            <i class="flaticon-statistics"></i>
            </span>
            <h3 class="m-portlet__head-text m--font-primary">
               Recorrido del Personal
            </h3>
         </div>
      </div>
            <div class="m-portlet__head-tools">
         <ul class="m-portlet__nav">
            <li class="m-portlet__nav-item">
               <a href="" data-portlet-tool="toggle" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Collapse">
               <i class="la la-plus"></i>
               </a>
            </li>

            <li class="m-portlet__nav-item">
               <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
               <i class="la la-expand"></i>
               </a>
            </li>
         </ul>
      </div>
   </div>
   
      <div class="m-portlet__body">
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            <i class="flaticon-profile-1" aria-hidden="true"></i>
            Datos Personales
            </a>
         </li>
        
        <!-- 
         <?php
         /*
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab">
            <i class="flaticon-transport" aria-hidden="true"></i>
            Tr&aacute;mite
            </a>
         </li>
         */
         ?> -->
      </ul>
            <div class="tab-content">
         <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
            <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed" id="form_alta_persona" name="form_alta_persona">
               <div class="form-group m-form__group row">
								<%

								/*out.print("<div class='m-portlet__foot'>");
								out.print("<div class='m-portlet__foot' class='ETablaC' colspan='4'>");
								out.print(vEti.clsAnclaTexto("EAncla", "Buscar Personal",
										"JavaScript:fSelPer();", "Buscar Personal", ""));
								out.print("</div>");
								out.print("</div>");*/

								if ((session.getAttribute("id")+"").toString() != null
										&& (session.getAttribute("id")+"").toString().compareTo(
												"") != 0
										&& (!(session.getAttribute("id")+"").toString().equals(
												"null"))) {
									//out.print("<div class='m-portlet__foot'>");
									//out.print("<div class='m-portlet__foot' class='ETablaT' colspan='4'>Datos Personales</div>");
									//out.print("</div>");

									TVGRLProceso vGRLProceso = new TVGRLProceso();
									TDGRLProceso dGRLProceso = new TDGRLProceso();
									Vector vcGRLProceso = new Vector();
									vcGRLProceso = dGRLProceso.FindByAll(
											" where iCveProceso = " + iEPIProceso, "");
									vGRLProceso = (TVGRLProceso) vcGRLProceso.get(0);

									TVPERDatos vPERDatos = new TVPERDatos();
									TDPERDatos dPERDatos = new TDPERDatos();
									Vector vcPERDatos = new Vector();
									vcPERDatos = dPERDatos.FindByAll((session.getAttribute("id")+"").toString());
									if (vcPERDatos.size() > 0)
										vPERDatos = (TVPERDatos) vcPERDatos.get(0);
	
									
									out.print(" <div class='col-lg-3'>");
											out.print(" <label>");
											out.print("Proceso:");
											out.print("</label>");
											out.print("<input type='text' class='form-control m-input' value='"+ vGRLProceso.getCDscProceso()+"' disabled>");
											out.print("</div>");
											
											out.print(" <div class='col-lg-3'>");
											out.print(" <label>");
											out.print("No. Expediente:");
											out.print("</label>");
											out.print("<input type='text' class='form-control m-input' value='"+ vPERDatos.getICveExpediente() +"' disabled>");
											out.print("</div>");
											
											out.print(" <div class='col-lg-3'>");
											out.print(" <label>");
											out.print("R.F.C.:");
											out.print("</label>");
											out.print("<input type='text' class='form-control m-input' value='"+ vPERDatos.getCRFC()+"' disabled>");
											out.print("</div>");
											
											out.print(" <div class='col-lg-3'>");
											out.print(" <label>");
											out.print("Sexo:");
											out.print("</label>");
											String cGenero = vPERDatos.getCSexo();
											if (cGenero != null && cGenero.compareTo("M") == 0)
												out.print("<input type='text' class='form-control m-input' value='HOMBRE' disabled>");
											else
												out.print("<input type='text' class='form-control m-input' value='MUJER' disabled>");
											out.print("</div>");
									
									
									out.print("<div class='form-group m-form__group row'>");
									out.print("<div class='col-lg-10'>");
											out.print(" <label>");
											out.print("Nombre:");
											out.print("</label>");
											out.print("<input type='text' class='form-control m-input' value='"+ vPERDatos.getCApPaterno() + " "
													+ vPERDatos.getCApMaterno() + " "
													+ vPERDatos.getCNombre() + "' disabled>");
											out.print("</div>");
											
										out.print(" </div>");
									out.print("</div>");
									out.print("</div>");
									
									
								}

								if (bs != null) {
									bs.start();
									String cServicio = "";
									int iServConcluido = 0;
									
									%>
									
									            <div class="tab-content">
							         <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
							            <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed" id="form_alta_persona" name="form_alta_persona">
							               <div class="form-group m-form__group row">
									
									 
									
									
									<%
									//out.print("<div class='m-portlet__foot'>");
									//out.print("<div class='m-portlet__foot' class='ETablaT' colspan='2'>Servicio / Rama</div>");
									//out.print("<div class='m-portlet__foot' class='ETablaT' colspan='2'>Situaci&oacute;n</div>");
									//out.print("</div>");
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
											
											%>
											<div class="m-portlet__head">
											<div class="m-portlet__head-tools">
							          <ul class="nav nav-tabs m-tabs m-tabs-line   m-tabs-line--left m-tabs-line--primary" role="tablist">
							            <li class="nav-item m-tabs__item">
							              <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_user_profile_tab_1" role="tab">
							                <i class="flaticon-share m--hide"></i>
							                Servicio:<%=cServicio %>
							                <% if (iServConcluido == 1) {
							                	
							                	out.print("<button class=\"btn btn-primary\" onClick=\"fVerExamen("
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
														+ ");\"  ");
												out.print("\">Ver Examen</button>");
							                	
							                }else{
												out.print(" (Sin Concluir)");
							                }
							                %>
							              </a>
							            </li>
							          </ul>
							        </div>
							        </div>
							        
							        
							        
							        
							        
							        
											<%
											
											/*
											
											out.print("<div class='m-portlet__foot'>");
											out.print("<div class='m-portlet__foot' class='ETablaSTC' colspan='2'>"
													+ cServicio + "</div>");
											if (iServConcluido == 1) {
												out.print("<div class='m-portlet__foot' class='ETablaSTC' >Concluido-1</div><div class='m-portlet__foot' class='ETablaSTC'>");
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
												out.println("</div>");
											} else
												out.print("<div class='m-portlet__foot' class='EPositivosC' colspan='2'>Sin Concluir</div>");
											out.print("</div>");*/
										}
										/*out.print("<div class='m-portlet__foot'>");
										out.print("<div class='m-portlet__foot' class='EEtiquetaL' colspan='2'>"
												+ bs.getFieldValue("cDscRama") + "</div>");*/
										String Status = "";
										if (bs.getFieldValue("iConcluido").toString()
												.compareTo("1") == 0) {
											if (iServConcluido == 0) {
												//out.print("<div class='m-portlet__foot' class='EEtiquetaL'>Concluido-2</div><div class='m-portlet__foot' class='ETablaC'>");
												/*out.print(vEti
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
																""));*/
												//out.println("</div>");
											} else
												//out.print("<div class='m-portlet__foot' class='EEtiquetaL' colspan='2'>Concluido-3</div>");
												Status = "Concluido";
										} else{
											//out.print("<div class='m-portlet__foot' class='ETabla' colspan='2'>Sin Concluir-4</div>");
											Status = "Sin Concluir";
										}
										
										%>
										</div>
										<div class="tab-content">
								        <div class="tab-pane active" id="m_user_profile_tab_1">
								          <form class="m-form m-form--fit m-form--label-align-right" id="editar_perfil">
								            <div class="m-portlet__body">
								              <div class="form-group m-form__group row">
								                <label for="example-text-input" class="col-2 col-form-label">
								                  Rama
								                </label>
								                <div class="col-7">
								                  <input class="form-control m-input" disabled type="text" id="Rama" name="Rama" placeholder="Rama (s)" value="<%=bs.getFieldValue("cDscRama") %>">
								                </div>
								              </div>
								              <div class="form-group m-form__group row">
								                <label for="example-text-input" class="col-2 col-form-label">
								                  Status
								                </label>
								                <div class="col-7">
								                  <input class="form-control m-input" disabled type="text" id="Status" name="Status" placeholder="" value="<%= Status %>">
								                </div>
								              </div>
								              
								
								            </div>
								           
								          </form>
								        </div>
								      </div>
										<%
										
										
										
										out.print("</div>");
									}
								} else {
									
											out.print("<div class='m-portlet__foot' class='ETablaT' colspan='4'>Seleccione el Episodio que desea consultar</div>");
											
						 	 					vcEXPExamAplica = dEXPExamAplica.FindByAllEPIEMO(" Where EXPExamAplica.iCveExpediente = "+(session.getAttribute("id")+"").toString()+" and EXPExamAplica.iCveProceso < 3 ");
						 	 					 if(vcEXPExamAplica != null){

						 	 					for(int i=0;i<vcEXPExamAplica.size();i++){
													vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(i);
													/*out.print("<a class=\"EAncla\" name=\"\"");
													
													out.print("href=\"JavaScript:fSelectedPer("+vEXPExamAplica.getICveExpediente()+",");
													out.print(vEXPExamAplica.getINumExamen()+",'"+vEXPExamAplica.getDtSolicitado().toString()+"');\"  ");
													out.print("onMouseOut=\"self.status='';return true;\" ");
													out.print("onMouseOver=\"self.status='Buscar Personal';return true;\">");
													if(vEXPExamAplica.getINumExamen()<10){
														out.print("Episodio # "+vEXPExamAplica.getINumExamen());
													}else{
														out.print("Episodio: "+vEXPExamAplica.getINumExamen());
													}
													if(vEXPExamAplica.getICveProceso() == 1){
														out.print(" - EPI");
													}else{
														out.print(" - EMO");
													}
													out.print("</a><br> ");*/
													
													out.print("<button class=\"btn btn-primary\" onClick=\"fSelectedPer("+vEXPExamAplica.getICveExpediente()+",");
													out.print(vEXPExamAplica.getINumExamen()+",'"+vEXPExamAplica.getDtSolicitado().toString()+"');\"  ");
													out.print("\">Episodio #"+vEXPExamAplica.getINumExamen()+"</button>");
													
													/*<div class="m-form__actions">
													<button type="submit" class="btn btn-primary" onchange="fValidaTodo()">Guardar</button>
													<button type="reset" class="btn btn-secondary">Cancel</button>
													<button class="btn btn-secondary" onClick="fPrint()">Imprimir</button>*/


						 	 					} 
						 	 					 %>
						 	 					 </div>
					                             </strong></div>
								           </div>
								           </div></div>
										
								<%
								   }else{
									out.print("<div class='m-portlet__foot'>");
									out.print("<div class='m-portlet__foot' class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</div>");
									out.print("</div>");
									}
								}
						%>
					</table></div>
			</div>
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



