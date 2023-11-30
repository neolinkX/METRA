<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	/**
	 * Title: pg070105040
	 * Description:
	 * Copyright:
	 * Company:
	 * @author Javier Mendoza
	 * @version
	 * Clase:
	 */
%>
<%@ page import="gob.sct.medprev.*"%>    
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>

<html onMouseOver="fValidaTodo2()">

<%

System.out.println("Constancia");

	pg070105040CFG clsConfig = new pg070105040CFG(); // modificar
	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setNombrePagina("pg070105040.jsp"); // modificar
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setArchFuncs("FValida");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg070105040.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cCatalogo = "pg070105040.jsp"; // modificar
	String cOperador = "1"; // modificar
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
	TFechas dtFecha = new TFechas();

	clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	String cPaginas = clsConfig.getPaginas();
	String cDscPaginas = clsConfig.getDscPaginas();
	PageBeanScroller bs = clsConfig.getBeanScroller();
	String cUpdStatus = "SaveOnly";
	int lDictaminado = 0;
	int lCatDic = clsConfig.lDictamenCat();
	lDictaminado = clsConfig.lDictaminadoEA();
	
	///Evitar se actualice la fecha TFinExamen
	if(lDictaminado == 1){
		cUpdStatus = "SaveOnly";
	}else{
		cUpdStatus  = "Hidden";
	}
	
	boolean Guardar = false; // modificar
	
	/*
	if (lCatDic != 0){
	   cUpdStatus  = "SaveOnly";
	}else{
	   cUpdStatus  = "Hidden";
	}*/

	//cUpdStatus = "SaveOnly";

	String cNavStatus = "Hidden";
	String cOtroStatus = clsConfig.getOtroStatus();
	String cCanWrite = clsConfig.getCanWrite();
	String cSaveAction = "Guardar";
	String cDeleteAction = "BorrarB";
	String cClave = "";
	String cPosicion = "";
	int procesomed = 0;
	String dictamenes = "";

	/**
	 * Listas de Resultados
	 */

	Vector vcExpNMedica = new Vector();
	Vector vcExpDicExam = new Vector();
	Vector vcExpRecomen = new Vector();
	Vector vcExpDiagnos = new Vector();

	vcExpNMedica = clsConfig.listanMedica();
	vcExpDicExam = clsConfig.listaExaCat();
	vcExpRecomen = clsConfig.listaExpRec();
	vcExpDiagnos = clsConfig.listaExpDia();

	TVEXPDictamenServ vExpNMedica = new TVEXPDictamenServ();
	TVEXPDictamenServ vExpDicExam = new TVEXPDictamenServ();
	TVEXPDictamenServ vExpRecomen = new TVEXPDictamenServ();
	TVEXPDictamenServ vExpDiagnos = new TVEXPDictamenServ();
	TVEXPExamAplica vExpEAplica = new TVEXPExamAplica();

	TDEXPFunDictamen dEXPFunDictamen = new TDEXPFunDictamen();

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//LAS candado DICTAMEN
	java.util.Date dtNow = new java.util.Date();
	java.sql.Date dtToday = new java.sql.Date(dtNow.getYear(),
			dtNow.getMonth(), dtNow.getDate());
	java.sql.Date DtBlqDictamen = dtToday;

	try {
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		Vector vcEXPExamAplica = new Vector();
		TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
		String WhereD = "";
		WhereD = " Where iCveExpediente = "
				+ request.getParameter("iCveExpediente").toString()
				+ " and iNumExamen = "
				+ request.getParameter("iNumExamen").toString() + " ";
		vcEXPExamAplica = dEXPExamAplica.FindByAllDesbl(WhereD);
		for (int i = 0; i < vcEXPExamAplica.size(); i++) {
			vEXPExamAplica = (TVEXPExamAplica) vcEXPExamAplica.get(i);
		}

		if (vEXPExamAplica.getDtBlqDictamen() != null) {
			if (vEXPExamAplica.getLDictaminado() == 1)
				DtBlqDictamen = dtToday;
			else
				DtBlqDictamen = dtToday; //DtBlqDictamen = vEXPExamAplica.getDtBlqDictamen();  /////////SE MODIFICO CON LA FINALIDAD DE INHABILITAR ESTA FUNCIONALIDA
		} else {
			if (vEXPExamAplica.getLDictaminado() == 1)
				DtBlqDictamen = dtToday;
			else
				DtBlqDictamen = dtToday; //DtBlqDictamen = vEXPExamAplica.getDtSolicitado(); /////////SE MODIFICO CON LA FINALIDAD DE INHABILITAR ESTA FUNCIONALIDA
		}

		if (vEXPExamAplica.getICveProceso() == 2) {
			DtBlqDictamen = dtToday;
			procesomed = 2;
			Guardar = true;
		}
	} catch (Exception e) {
		System.out
				.println("Se  produjo un error en la obtension de información");
	}

	System.out.println("Constancia2");
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	///Transfronterizo
	boolean bTransfronterizo = false;
	
	//Motivos
	TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
	Vector vcEXPExamCat = new Vector();
	TVEXPExamCat vEXPExamCat = new TVEXPExamCat();

	
	//Obtension de Servicios para validar
	TDMEDSERVObligatorio dMEDSERVObligatorio = new TDMEDSERVObligatorio();
	TVMEDSERVObligatorio vMEDSERVObligatorio = new TVMEDSERVObligatorio();
	Vector vcMEDSERVObligatorio = new Vector();
	vcMEDSERVObligatorio = dMEDSERVObligatorio.FindByAll(request.getParameter("iCveExpediente").toString(), request.getParameter("iNumExamen").toString());

	////////////Diagnosticos Diabetes //////////////////
	TDEXPDiagnostico dEXPDiagnostico = new TDEXPDiagnostico();
	boolean DiagnosticoDiabetes =  false;
	DiagnosticoDiabetes = dEXPDiagnostico.Diabetes(request.getParameter("iCveExpediente").toString(), request.getParameter("iNumExamen").toString());
	
	TDEXPServicio dEXPServicio = new TDEXPServicio();
	Vector vcEXPServicio = new Vector();
	TVEXPServicio vEXPServicio = new TVEXPServicio();

	int ServGABOFTA = 0; //Gabinete de Oftalmologia (67)
	int ServEVOFTA = 0; //Evaluación de Oftalmologica (59)
	int ServDiabetes = 0; //EVALUACIÓN Y CONTROL DEL PACIENTE CON DIABETES MELLITUS (75)


	//Variable para fundamentacion
	int Funda = 0;
	int DiagRec = 0;
	int DiagReCon = 0;
	int DiagRecEmo = 0;
	int DiagReConEmo = -1;
	String Mensaje = "";
	int ActivaCdr = 0;

	//Diagnostico y Recomendacion
	TDEXPDictamenServ dEXPDictamenServ2 = new TDEXPDictamenServ();
	Vector vcEXPDictamenServ2 = new Vector();
	TVEXPDictamenServ vEXPDictamenServ2 = new TVEXPDictamenServ();
	/////Aptitud en diagnostico y recomendaciones del dictaminador///////
	String apDiag = "select count(ldictamen) from expdictamenserv where icveexpediente = "
			+ request.getParameter("iCveExpediente").toString()
			+ " and inumexamen = "
			+ request.getParameter("iNumExamen").toString()
			+ " and icveservicio = 31 and ldictamen = 0";
	DiagRec = dEXPDictamenServ2.CountNoApt(apDiag);
	if (DiagRec >= 1) {
		DiagRec = 0;
	} else {
		apDiag = "select count(ldictamen) from expdictamenserv where icveexpediente = "
				+ request.getParameter("iCveExpediente").toString()
				+ " and inumexamen = "
				+ request.getParameter("iNumExamen").toString()
				+ " and icveservicio = 31 and ldictamen = 1";
		DiagRec = dEXPDictamenServ2.CountNoApt(apDiag);
		if (DiagRec >= 1) {
			System.out.println("Listo Apto");
			DiagRec = 1;
		} else {
			DiagRec = 0;
		}
	}
	
	
	int Motivo = 0;
	String whereMot = "";

	int NumExpe = Integer.parseInt(request.getParameter(
			"iCveExpediente").trim());
	int NumExa = Integer.parseInt(request.getParameter("iNumExamen")
			.trim());
	whereMot = " where  iCveExpediente = " + NumExpe
			+ " and  iNumExamen = " + NumExa + "";

	System.out.println("Constancia3");
	
	try {
		//Condicion EMO   
		if (procesomed == 2) {
			ServGABOFTA = 1; //Gabinete de Oftalmologia (67)
			ServEVOFTA = 0; //Evaluación Oftalmologica (59)
			Funda = 1;
			//DiagRec = 1;
			//Capturando Diagnostico y Recomendaciones
			vcEXPServicio = dEXPServicio.getLConcluido(request
					.getParameter("iCveExpediente").toString(), request
					.getParameter("iNumExamen").toString(), "31");
			for (int i = 0; i < vcEXPServicio.size(); i++) {
				vEXPServicio = (TVEXPServicio) vcEXPServicio.get(i);
			}
			DiagReConEmo = vEXPServicio.getLConcluido();
			
		} else {
			vcEXPExamCat = dEXPExamCat.FindByAll(whereMot);
			for (int i = 0; i < vcEXPExamCat.size(); i++) {
				vEXPExamCat = (TVEXPExamCat) vcEXPExamCat.get(i);
			}
			Motivo = vEXPExamCat.getICveMotivo();
			System.out.println(Motivo);
			//Capturando Diagnostico y Recomendaciones
			vcEXPServicio = dEXPServicio.getLConcluido(request
					.getParameter("iCveExpediente").toString(), request
					.getParameter("iNumExamen").toString(), "31");
			for (int i = 0; i < vcEXPServicio.size(); i++) {
				vEXPServicio = (TVEXPServicio) vcEXPServicio.get(i);
			}
			DiagReCon = vEXPServicio.getLConcluido();

			if (Motivo == 1 || Motivo == 6 || Motivo == 5) {

				//Gabinete de Oftalmologia (67)
				vcEXPServicio = dEXPServicio.getLConcluido(request.getParameter("iCveExpediente").toString(), request.getParameter("iNumExamen").toString(), "67");
				for (int i=0;i<vcEXPServicio.size();i++){
				      vEXPServicio = (TVEXPServicio) vcEXPServicio.get(i);
				   }  
				ServGABOFTA = vEXPServicio.getLConcluido();  
				//ServGABOFTA = 1;
				
				//Evaluacion de Oftalmologica (59)
				TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
				ServEVOFTA = dEXPExamAplica.RegresaInt("SELECT COUNT(LLOGICO) FROM EXPRESULTADO "+ 
													  " WHERE		"+				
													  " ICVESERVICIO = 67 AND	"+
													  " ICVERAMA = 1 AND		"+				
													  " ICVESINTOMA = 24 AND	"+					
													  " ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente").toString()+" AND		"+				
													  " INUMEXAMEN = "+request.getParameter("iNumExamen").toString()+" AND				"+
													  " LLOGICO = 1 AND				"+		
													  " (SELECT LCONCLUIDO FROM EXPSERVICIO WHERE ICVESERVICIO = 59 AND ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente").toString()
													  +" AND INUMEXAMEN = "+request.getParameter("iNumExamen").toString()+") = 0"); 
				
				
				//EVALUACIÓN Y CONTROL DEL PACIENTE CON DIABETES MELLITUS (75)
				vcEXPServicio = dEXPServicio.getLConcluido(request.getParameter("iCveExpediente").toString(), request.getParameter("iNumExamen").toString(), "75");
				for (int i=0;i<vcEXPServicio.size();i++){
				      vEXPServicio = (TVEXPServicio) vcEXPServicio.get(i);
				   }  
				ServDiabetes = vEXPServicio.getLConcluido(); 
				
			} else {
				
				//Gabinete de Oftalmologia (67)
				vcEXPServicio = dEXPServicio.getLConcluido(request.getParameter("iCveExpediente").toString(), request.getParameter("iNumExamen").toString(), "67");
				for (int i=0;i<vcEXPServicio.size();i++){
				      vEXPServicio = (TVEXPServicio) vcEXPServicio.get(i);
				   }  
				ServGABOFTA = vEXPServicio.getLConcluido();  
				
				//Evaluacion de Oftalmologica (59)
				TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
				ServEVOFTA = dEXPExamAplica.RegresaInt("SELECT COUNT(LLOGICO) FROM EXPRESULTADO "+ 
													  " WHERE		"+				
													  " ICVESERVICIO = 67 AND	"+
													  " ICVERAMA = 1 AND		"+				
													  " ICVESINTOMA = 24 AND	"+					
													  " ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente").toString()+" AND		"+				
													  " INUMEXAMEN = "+request.getParameter("iNumExamen").toString()+" AND				"+
													  " LLOGICO = 1 AND				"+		
													  " (SELECT LCONCLUIDO FROM EXPSERVICIO WHERE ICVESERVICIO = 59 AND ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente").toString()
													  +" AND INUMEXAMEN = "+request.getParameter("iNumExamen").toString()+") = 0"); 
				
			}//else
		}// fin de else condicion EMO
		System.out.println("Constancia4");
		//Deshabilitar candado de servicios para Transfronterizo
		if (request.getParameter("iCveModulo").equals("14"))
			if (request.getParameter("iCveUniMed").equals("1")) {
				ServGABOFTA = 1; //Gabinete de Oftalmologia (67)
				ServEVOFTA = 0; //Evaluacion Oftalmologica (59)
				Funda = 1;
				DiagRec = 1;
				bTransfronterizo = true;
			}

		//Verificando que haya informacion de la fundamentacion

		String QueryF = "select cFundamentacion from EXPFunDictamen where icveexpediente = "
				+ request.getParameter("iCveExpediente").toString()
				+ " and inumexamen = "
				+ request.getParameter("iNumExamen").toString()
				+ " and icveservicio = 31";
		String QueryFun = "select count(cFundamentacion) from EXPFunDictamen where icveexpediente = "
				+ request.getParameter("iCveExpediente").toString()
				+ " and inumexamen = "
				+ request.getParameter("iNumExamen").toString()
				+ " and icveservicio = 31";
		String QFundamentacion = "";
		try {
			QFundamentacion = dEXPFunDictamen.FindByAll(QueryF);
			Funda = dEXPFunDictamen.FindByAllI(QueryFun);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (procesomed == 2) {
			Funda = 1;
			DiagRec = 0;
			DiagReCon = 1;
		}

		//Deshabilitar candado de fundamentacion para Transfronterizo
		if (request.getParameter("iCveModulo").equals("14"))
			if (request.getParameter("iCveUniMed").equals("1")) {
				Funda = 1;
				DiagRec = 0;
				DiagReCon = 1;
			}
	} catch (Exception e) {
		//        System.out.println("Se produjo un error en la obtension de informaci�n de los Servicios");
	}
	/*  FindServicios
	 */
	int resultServ =  ServGABOFTA;
	
%>

<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0,
							vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<script LANGUAGE="JavaScript">

  // Esta funci�n no debe modificarse
  function fOnLoad(){
	  var form = document.forms[0];
	  //alert(form.hdGuardar.value);
		if (form.hdGuardar.value == "true"){
		    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
		          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
		          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
    	}else{
    		fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','Hidden','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
  		          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
  		          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
    	}
  } 

  function fFunda(){
  var form = document.forms[0];
  			if (form.hdFund.value == 0){
		           alert("Favor de capturar la fundamentación y motivación de diagnósticos. \n");
                      }
  }

  function muestra_oculta(id){
                                if (document.getElementById){ //se obtiene el id
                                            var el = document.getElementById(id); //se define la variable "el" igual a nuestro div
                                            //el.style.display = (el.style.display == 'none') ? 'block' : 'none'; //damos un atributo display:none que oculta el div
                                            el.style.display = (el.style.display == 'block') ? 'none' : 'block'; //damos un atributo display:none que oculta el div
                                                            }
                            }

//window.onload = function(){/*hace que se cargue la funci�n lo que predetermina que div estar� oculto hasta llamar a la funci�n nuevamente*/
function div(){/*hace que se cargue la funci�n lo que predetermina que div estar� oculto hasta llamar a la funci�n nuevamente*/
                muestra_oculta('contenido_a_mostrar');/* "contenido_a_mostrar" es el nombre que le dimos al DIV */
}

function mensaje(){
    alert("Si deseas Generar la constancia da clic en el botón Aceptar");
}

  var cStyle = '<link rel="stylesheet" href="<%=vParametros.getPropEspecifica("RutaCSS")
					+ vParametros.getPropEspecifica("HojaCSS")%>" TYPE="text/css">';
</script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ "calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaNas") + "cie.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "selDiag.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "reco.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "selReco.js"%>"></script>
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
	topmargin="0" leftmargin="0"
	onLoad="<%=vEntorno.getOnLoad()%> div();">
	<form method="<%=vEntorno.getMetodoForm()%>" id="dictamen"
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
		<input type="hidden" name="hdICveExpediente"
			value="<%=request.getParameter("hdICveExpediente") != null ? request
					.getParameter("hdICveExpediente") : ""%>">
		<input type="hidden" name="hdINumExamen"
			value="<%=request.getParameter("hdINumExamen") != null ? request
					.getParameter("hdINumExamen") : ""%>">

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

		<input type="hidden" name="iCveExpediente"
			value="<%=request.getParameter("hdICveExpediente") != null ? request
					.getParameter("hdICveExpediente") : ""%>">
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
		<!--input type="hidden" name="iCvePersonal" value="<%//=vPERDatos!=null?vPERDatos.getICvePersonal():0%>"-->
		<input type="hidden" name="iCveServicio" value="-1"> <input
			type="hidden" name="iServSanguineo"
			value="<%=vParametros.getPropEspecifica("EPIServSanguineo")%>">
		<input type="hidden" name="iRamaSanguineo"
			value="<%=vParametros.getPropEspecifica("EPIRamaSanguineo")%>">
		<input type="hidden" name="iGrupoSanguineo"
			value="<%=vParametros.getPropEspecifica("EPISintGSanguineo")%>">
		<input type="hidden" name="iFactorSanguineo"
			value="<%=vParametros.getPropEspecifica("EPISintFSanguineo")%>">

		<%
			if (bs != null) {
				cPosicion = Integer.toString(bs.pageNo());
			}
		%>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave" value="<%=cClave%>"> <input
			type="hidden" name="hdCPropiedad" value="">
		<%
		
		System.out.println("Constancia4");
		
			int iCveExpediente = 0;
			int iCveServicio = 0;
			int iNumExamen = 0;
			int iCvePersonal = 0;
			int iCveUniMed = 0;
			java.sql.Date hdDtConcluido;

			if (request.getParameter("iCveExpediente") != null
					&& request.getParameter("iCveExpediente").length() > 0) {
				out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\""
						+ request.getParameter("iCveExpediente") + "\"> \n");
				iCveExpediente = Integer.parseInt(request.getParameter(
						"iCveExpediente").toString());
			} else {
				out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\"10\"> \n");
				iCveExpediente = 1;
			}
			if (request.getParameter("iCveServicio") != null
					&& request.getParameter("iCveServicio").length() > 0) {
				out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\""
						+ request.getParameter("iCveServicio") + "\"> \n");
				iCveServicio = Integer.parseInt(request.getParameter(
						"iCveServicio").toString());
			} else {
				out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\"1\"> \n");
				iCveServicio = 1;
			}
			if (request.getParameter("iNumExamen") != null
					&& request.getParameter("iNumExamen").length() > 0) {
				out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\""
						+ request.getParameter("iNumExamen") + "\"> \n");
				iNumExamen = Integer.parseInt(request
						.getParameter("iNumExamen").toString());
			} else {
				out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\"1\"> \n");
				iNumExamen = 1;
			}

			if (request.getParameter("iCveProceso") != null
					&& request.getParameter("iCveProceso").length() > 0) {
				out.print(" <input type=\"hidden\" name=\"iCveProceso\" value=\""
						+ request.getParameter("iCveProceso") + "\"> \n");
				iCveServicio = Integer.parseInt(request.getParameter(
						"iCveProceso").toString());
			} else {
				out.print(" <input type=\"hidden\" name=\"iCveProceso\" value=\"1\"> \n");
				iCveServicio = 1;
			}

			if (request.getParameter("iCveUniMed") != null
					&& request.getParameter("iCveUniMed").length() > 0) {
				out.print(" <input type=\"hidden\" name=\"iCveUniMed\" value=\""
						+ request.getParameter("iCveUniMed") + "\"> \n");
				iCveUniMed = Integer.parseInt(request
						.getParameter("iCveUniMed").toString());
			} else {
				out.print(" <input type=\"hidden\" name=\"iCveUniMed\" value=\"1\"> \n");
				iCveUniMed = 1;
			}

			if (request.getParameter("hdDtConcluido") != null
					&& request.getParameter("hdDtConcluido").length() > 0) {
				out.print(" <input type=\"hidden\" name=\"hdDtConcluido\" value=\""
						+ request.getParameter("hdDtConcluido") + "\"> \n");
				hdDtConcluido = dtFecha.getDateSQL(request.getParameter(
						"hdDtConcluido").toString());
			} else {
				out.print(" <input type=\"hidden\" name=\"hdDtConcluido\" value=\"01/01/2004\"> \n");
				hdDtConcluido = dtFecha.TodaySQL();
			}
		%>


		<table
			background="<%=vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg"
			width="100%" height="100%">
			<%
				if (clsConfig.getAccesoValido()) {
					TVUsuario vUsuario = (TVUsuario) request.getSession(true)
							.getAttribute("UsrID");
					Vector vcEXPDicSer = new Vector();
					Vector vcEXPListaCat = new Vector();
					TVEXPDictamenServ vEXPDicSer = new TVEXPDictamenServ();
					TVEXPDictamenServ vEXPListaCat = new TVEXPDictamenServ();
					vcEXPDicSer = clsConfig.findCatSol();
					vcEXPListaCat = clsConfig.listaCategoria();
					TVPERDatos vPerDatos = clsConfig.findExpediente();
					//out.print(" <input type=\"hidden\" name=\"Doctor\" value=\""+vUsuario.getCApPaterno().substring(0,1).toUpperCase()+vUsuario.getCApPaterno().substring(1,vUsuario.getCApPaterno().length()).toLowerCase()+" "+
					//                                                             vUsuario.getCApMaterno().substring(0,1).toUpperCase()+vUsuario.getCApMaterno().substring(1,vUsuario.getCApMaterno().length()).toLowerCase()+", "+
					//                                                             vUsuario.getCNombre().substring(0,1).toUpperCase()+vUsuario.getCNombre().substring(1,vUsuario.getCNombre().length()).toLowerCase()+"\">");

					out.print(" <input type=\"hidden\" name=\"Doctor\" value=\""
							+ vUsuario.getCNombre().toString() + " "
							+ vUsuario.getCApPaterno().toString() + ", "
							+ vUsuario.getCApMaterno().toString() + "\">");
					out.print(" <input type=\"hidden\" name=\"iCveUsuDictamen\" value=\""
							+ vUsuario.getICveusuario() + "\">");
					out.print(" <input type=\"hidden\" name=\"Cedula\" value=\""
							+ vUsuario.getCUsuario() + "\">");
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
							<td colspan="5" class="ETablaT">Datos del Personal</td>
						</tr>
						<%
						
						System.out.println("Constancia5");
							if (vPerDatos != null) {
									out.println("<tr>");
									out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\""
											+ vPerDatos.getICvePersonal() + "\"> \n");
									out.println("<td class=\"EEtiqueta\">Nombre:</td>");
									out.println("<td class=\"ECampo\">"
											+ vPerDatos.getCNombre() + " "
											+ vPerDatos.getCApPaterno() + " "
											+ vPerDatos.getCApMaterno() + "</td>");
									out.println("<Input Type=\"Hidden\" Name=\"pName\" Value=\""
											+ vPerDatos.getCNombre()
											+ " "
											+ vPerDatos.getCApPaterno()
											+ " "
											+ vPerDatos.getCApMaterno() + "\">");
									out.println("<Input Type=\"Hidden\" Name=\"pEmpresa\" Value=\""
											+ vPerDatos.getCDscEmpresa() + "\">");
									out.println("<td class=\"EEtiqueta\" colspan=\"2\">Expediente:</td>");
									out.println("<td class=\"ECampo\">"
											+ vPerDatos.getICveExpediente() + "</td>");
									out.println("</tr>");
									out.println("<tr>");
									out.println("<td class=\"EEtiqueta\">Edad:</td>");
									out.println("<td class=\"ECampo\">"
											+ clsConfig.getEdad(vPerDatos.getDtNacimiento())
											+ " Años</td>");
									out.println("<td class=\"EEtiqueta\" colspan=\"2\">Sexo:</td>");
									if (vPerDatos.getCSexo().equalsIgnoreCase("M")) {
										out.print("<td class=\"ECampo\">Hombre</td>");
									} else {
										out.print("<td class=\"ECampo\">Mujer</td>");
									}
									out.print("</tr>");
								} else {
									if (request.getParameter("iCvePersonal") != null
											&& request.getParameter("iCvePersonal").length() > 0) {
										out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\""
												+ request.getParameter("iCvePersonal")
												+ "\"> \n");
										iCvePersonal = Integer.parseInt(request.getParameter(
												"iCvePersonal").toString());
									} else {
										out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\"1\"> \n");
										iCvePersonal = 1;
									}
									out.print("<tr>");
									out.print("<td class=\"EResalta\" colspan=\"4\" align=\"center\">Datos de Personal no disponibles</td>");
									out.print("</tr>");
								}
						%>
					</table>&nbsp;
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
						//VALIDANDO SE HAYA CONCLUIDO LA CAPTURA DE SERVICIOS
						
						
						System.out.println("vcMEDSERVObligatorio.size() ="+vcMEDSERVObligatorio.size());
						System.out.println("DiagnosticoDiabetes = "+DiagnosticoDiabetes);
						System.out.println("lDictaminado = "+lDictaminado);
						if((vcMEDSERVObligatorio.size() > 0 || (DiagnosticoDiabetes==true && ServDiabetes == 0)) && lDictaminado > 0){
							System.out.println("size ="+vcMEDSERVObligatorio.size());
							%>
							<tr>
								<td colspan="9" class="ETablaT">FAVOR DE CONCLUIR LOS SIGUIENTES SERVICIOS</td>
							</tr>
							<%
							for (int i = 0; i < vcMEDSERVObligatorio.size(); i++) {
								vMEDSERVObligatorio = (TVMEDSERVObligatorio) vcMEDSERVObligatorio.get(i);
								out.println("<tr><td class=\"ETablaST\">"+vMEDSERVObligatorio.getCDcsServicio()+"</td></tr>");
								System.out.println(vMEDSERVObligatorio.getICveServicio());
								System.out.println(vMEDSERVObligatorio.getCDcsServicio());
							}
							if(DiagnosticoDiabetes == true){
								if(ServDiabetes == 0){
									out.println("<tr><td class=\"ETablaST\">EVALUACIÓN Y CONTROL DEL PACIENTE CON DIABETES MELLITUS</td></tr>");
								}
							}
						}else{
							System.out.println("NO EXISTEN SERVICIOS POR CONCLUIR");
							
							// Inicio de Datos
						%>
						<tr>
							<td colspan="9" class="ETablaT">Categorías Solicitadas</td>
						</tr>
						<%
						
						System.out.println("Constancia6");
						System.out.println(vcEXPDicSer.size());
							if (vcEXPDicSer.size() > 0) {
									String cModo = "";
									out.println("<tr>");
									out.println("<td class=\"ETablaST\">Modo de Transporte</td>");
									out.println("<td class=\"ETablaST\">Grupo</td>");
									out.println("<td class=\"ETablaST\">Categoría</td>");
									out.println("<td class=\"ETablaST\" ColSpan=\"2\">Motivo</td>");
									out.println("<td class=\"ETablaST\" ColSpan=\"4\">Puesto</td>");
									for (int i = 0; i < vcEXPDicSer.size(); i++) {
										vEXPDicSer = (TVEXPDictamenServ) vcEXPDicSer.get(i);
										out.println("<tr>");
										if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(
												cModo)) {
											out.println("<td class=\"ECampo\">"
													+ vEXPDicSer.getCDscMdoTrans() + "</td>");
										} else {
											out.println("<td>&nbsp;</td>");
										}
										out.println("<td class=\"ECampo\">"
												+ vEXPDicSer.getCDscGrupo() + "</td>");
										out.println("<td class=\"ECampo\">"
												+ vEXPDicSer.getCDscCategoria() + "</td>");
										out.println("<td class=\"ECampo\" ColSpan=\"2\">"
												+ vEXPDicSer.getCDscMotivo() + "</td>");
										out.println("<input type=\"hidden\" name=\"iCveMotivo"
												+ i + "\" value=\""
												+ vEXPDicSer.getICveMotivo() + "\">");
										out.println("<td class=\"ECampo\" ColSpan=\"3\">"
												+ vEXPDicSer.getCDscPuesto() + "</td>");
										out.println("</tr>");
										cModo = vEXPDicSer.getCDscMdoTrans();
									}
								} else {
									out.println("<tr>");
									out.println("<td class=\"ECampo\" colspan=\"4\" align=\"center\">Datos de Categorias no disponibles</td>");
									out.println("</tr>");
								}
								/*if (dtToday.compareTo(DtBlqDictamen) == 0) {
									%>
										<tr>
											<td colspan="9" class="ETablaST">Información M&eacute;dica
												para la Licencia</td>
										</tr>
									<%
								}*/
						}//NO EXISTEN SERVICIOS POR CONCLUIR
								if (lDictaminado == 0 && vcMEDSERVObligatorio.size() == 0) {
										System.out.println("OP 1");
										TDPERDatos dPERDatos = new TDPERDatos();
										TVPERDatos vPERDatos = new TVPERDatos();
										Vector vcPERDatos = new Vector();
										vcPERDatos = dPERDatos.FindByAll(request
												.getParameter("iCveExpediente"));
										if (vcPERDatos.size() > 0) {
											vPERDatos = (TVPERDatos) vcPERDatos.get(0);
											out.println("<tr>");

											if(vPERDatos.getCGpoSang()!=null){
											out.print(vEti.EtiCampo("EEtiqueta",
													"Grupo Sanguíneo:", "ECampo", "text", 10, 10,
													"cDscServicio", vPERDatos.getCGpoSang()
															.toString(), 0, "", "fMayus(this);",
													false, true, false));
											}else{
												out.print(vEti.EtiCampo("EEtiqueta",
														"Grupo Sanguíneo:", "ECampo", "text", 10, 10,
														"cDscServicio", "", 0, "", "fMayus(this);",
														false, true, false));
											}

											out.println("<td class=\"EEtiqueta\" colspan='2'>Factor RH:</td>");
											if (vPERDatos.getLRH() == 1) {
												out.println("<td colspan='5' class='EEtiquetaL'>");
												out.println("<input type=\"radio\" name=\"lRH\" value=\"1\" checked disabled>+");
												//out.println(vEti.Texto("EEtiqueta","+"));
												out.println("<input type=\"radio\" name=\"lRH\" value=\"0\" disabled>-");
												//out.println(vEti.Texto("EEtiqueta","-"));
												out.println("</td>");
											} else {
												out.println("<td colspan='5' class='EEtiquetaL'>");
												out.println("<input type=\"radio\" name=\"lRH\" value=\"1\" checked disabled>+");
												//out.println(vEti.Texto("EEtiqueta","+"));
												out.println("<input type=\"radio\" name=\"lRH\" value=\"0\" checked disabled>-");
												//out.println(vEti.Texto("EEtiqueta","-"));
												out.println("</td>");
											}
	
											out.println("<tr>");
											out.println("<td class=\"EEtiqueta\">Usa Lentes:</td>");
											if (vPERDatos.getLUsaLentes() == 1)
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" checked disabled></td>");
											else
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" disabled></td>");
	
											out.println("<td class=\"EEtiqueta\">Aéreo:</td>");
											if (vPERDatos.getLAereo() == 1)
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lAereo\" value=\"1\" checked disabled></td>");
											else
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lAereo\" value=\"1\" disabled></td>");
	
											out.println("<td class=\"EEtiqueta\">Contacto:</td>");
											if (vPERDatos.getLContacto() == 1)
												out.println("<td colspan=\"4\" class=\"ETabla\"><input type=\"checkbox\" name=\"lContacto\" value=\"1\" checked disabled></td>");
											else
												out.println("<td colspan=\"4\" class=\"ETabla\"><input type=\"checkbox\" name=\"lContacto\" value=\"1\" disabled></td>");
	
											out.println("</tr>");
											out.println("<tr>");
											out.println("<td class=\"EEtiqueta\">Hipertensión:</td>");
											if (vPERDatos.getLHipertension() == 1)
												out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" checked disabled></td>");
											else
												out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" disabled></td>");
	
											out.println("<td class=\"EEtiqueta\">Diabetes:</td>");
											if (vPERDatos.getLDiabetes() == 1)
												out.println("<td colspan=\"5\" class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" checked disabled></td>");
											else
												out.println("<td colspan=\"5\" class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" disabled></td>");
											out.println("</tr>");
										}

								} // lDictaminado == 0
								else { // lDictaminado != 0
									System.out.println("OP 2");
									//Bloquea examen por dtbloqueado
									if (dtToday.compareTo(DtBlqDictamen) == 0) {
										//if((Funda == 0 && DiagRec == 1) || (Funda == 1 && DiagRec == 0)){
										/*System.out.println("Funda = "+Funda);
										System.out.println("DiagRec = "+DiagRec);
										System.out.println("DiagReCon = "+DiagReCon);
										System.out.println("Vector = "+vcMEDSERVObligatorio.size());*/
										if (DiagRec == 1 && vcMEDSERVObligatorio.size() == 0) {/////DIAGNOSTICO Y RECOMENDACIONES APTO
											System.out.println("apto diaRec");
											Calendar hoy = Calendar.getInstance();
											//Calendaro fecha = null;
											TDPERDatos dPERDatos2 = new TDPERDatos();
											TVPERDatos vPERDatos2 = new TVPERDatos();
											Vector vcPERDatos2 = new Vector();
											vcPERDatos2 = dPERDatos2.FindByAll(request
													.getParameter("iCveExpediente"));
											vPERDatos2 = (TVPERDatos) vcPERDatos2.get(0);

											out.println("<tr>");

											String GSan = "";
											GSan = "" + vPERDatos2.getCGpoSang();

											if (GSan.equals("null")) {
												out.println(vEti.EtiCampo("EEtiqueta",
														"Grupo Sanguíneo:", "ECampo", "text",
														2, 2, "cGpoSang", "", 3, "",
														"fMayus(this);", false, true, true));
											} else {
												out.print(vEti.EtiCampo("EEtiqueta",
														"Grupo Sanguíneo:", "ECampo", "text",
														2, 2, "cGpoSang", vPERDatos2
																.getCGpoSang().toString(), 3,
														"", "fMayus(this);", false, true, true));
											}
											out.println("<td class=\"EEtiqueta\" colspan='2'>Factor RH:</td>");

											if (!String.valueOf(vPERDatos2.getLRH()).equals(
													null)) {
												if (vPERDatos2.getLRH() == 1) {
													out.println("<td colspan='5' class='EEtiquetaL'>");
													out.println("<input type=\"radio\" name=\"lRH\" value=\"1\" checked>+");
													//out.println(vEti.Texto("EEtiqueta","+"));
													out.println("<input type=\"radio\" name=\"lRH\" value=\"0\" >-");
													//out.println(vEti.Texto("EEtiqueta","-"));
													out.println("</td>");
												}
												if (vPERDatos2.getLRH() == 0) {
													out.println("<td colspan='5' class='EEtiquetaL'>");
													out.println("<input type=\"radio\" name=\"lRH\" value=\"1\" >+");
													//out.println(vEti.Texto("EEtiqueta","+"));
													out.println("<input type=\"radio\" name=\"lRH\" value=\"0\" checked>-");
													//out.println(vEti.Texto("EEtiqueta","-"));
													out.println("</td>");
												}
											} else {
												out.println("<td colspan='5' class='EEtiquetaL'>");
												out.println("<input type=\"radio\" name=\"lRH\" value=\"1\">+");
												out.println("<input type=\"radio\" name=\"lRH\" value=\"0\">-");
												out.println("</td>");
											}

											out.println("<tr>");

											out.println("<td class=\"EEtiqueta\">Usa Lentes:</td>");
											if (vPERDatos2.getLUsaLentes() == 1)
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" checked></td>");
											else
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\"></td>");

											out.println("<td class=\"EEtiqueta\">Aéreo:</td>");
											if (vPERDatos2.getLAereo() == 1)
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lAereo\" value=\"1\" checked></td>");
											else
												out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lAereo\" value=\"1\"></td>");

											out.println("<td class=\"EEtiqueta\">Contacto:</td>");
											if (vPERDatos2.getLContacto() == 1)
												out.println("<td colspan=\"4\" class=\"ETabla\"><input type=\"checkbox\" name=\"lContacto\" value=\"1\" checked ></td>");
											else
												out.println("<td colspan=\"4\" class=\"ETabla\"><input type=\"checkbox\" name=\"lContacto\" value=\"1\" ></td>");

											out.println("</tr>");

											out.println("<tr>");
											out.println("<td class=\"EEtiqueta\">Hipertensión:</td>");
											if (vPERDatos2.getLHipertension() == 1)
												out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lHipertension\" value=\"1\" checked></td>");
											else
												out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lHipertension\" value=\"1\"></td>");

											out.println("<td class=\"EEtiqueta\">Diabetes:</td>");
											if (vPERDatos2.getLDiabetes() == 1) {
												out.println("<td colspan=\"5\" class=\"ETabla\"><input type=\"checkbox\" name=\"lDiabetes\" value=\"1\" checked></td>");
											} else {
												out.println("<td colspan=\"5\" class=\"ETabla\"><input type=\"checkbox\" name=\"lDiabetes\" value=\"1\" ></td>");
											}

											
											out.println("<input type=\"hidden\" name=\"hdServGABOFTA\" value=\""
													+ ServGABOFTA + "\">");
											out.println("<input type=\"hidden\" name=\"hdServEVOFTA\" value=\""
													+ ServEVOFTA + "\">");

											//campo que nos servira para validar la fundamentacion
											out.println("<input type=\"hidden\" name=\"hdFund\" value=\"1\">");
										} else {
											System.out.println("no apto diaRec");
											if (Funda == 0) {
												if (DiagReCon == 0) {
													Mensaje = "Favor de Capturar la información de Diagnostico y Recomendación del Dictaminador.";
												} else {
													if(vcMEDSERVObligatorio.size() == 0){
														Mensaje = "Favor de capturar la Fundamentación y Motivación para poder dictaminar.";
													}
												}
						%>
						<tr>
							<td colspan="9" class="ETablaST"><%=Mensaje%></td>
						</tr>
						<%
							} else {/////DIAGNOSTICO Y RECOMENDACIONES NO APTO
										if(vcMEDSERVObligatorio.size() == 0){								
													System.out.println("Diag y rec no apto");								
														Calendar hoy = Calendar.getInstance();
														//Calendaro fecha = null;
														TDPERDatos dPERDatos2 = new TDPERDatos();
														TVPERDatos vPERDatos2 = new TVPERDatos();
														Vector vcPERDatos2 = new Vector();
														vcPERDatos2 = dPERDatos2.FindByAll(request
																.getParameter("iCveExpediente"));
														vPERDatos2 = (TVPERDatos) vcPERDatos2.get(0);
		
														out.println("<tr>");
		
														String GSan = "";
														GSan = "" + vPERDatos2.getCGpoSang();
		
														if (GSan.equals("null")) {
															out.println(vEti.EtiCampo("EEtiqueta",
																	"Grupo Sanguíneo:", "ECampo",
																	"text", 2, 2, "cGpoSang", "", 3,
																	"", "fMayus(this);", false, true,
																	true));
														} else {
															out.print(vEti
																	.EtiCampo("EEtiqueta",
																			"Grupo Sanguíneo:",
																			"ECampo", "text", 2, 2,
																			"cGpoSang", vPERDatos2
																					.getCGpoSang()
																					.toString(), 3, "",
																			"fMayus(this);", false,
																			true, true));
														}
														out.println("<td class=\"EEtiqueta\" colspan='2'>Factor RH:</td>");
		
														if (!String.valueOf(vPERDatos2.getLRH())
																.equals(null)) {
															if (vPERDatos2.getLRH() == 1) {
																out.println("<td colspan='5' class='EEtiquetaL'>");
																out.println("<input type=\"radio\" name=\"lRH\" value=\"1\" checked>+");
																//out.println(vEti.Texto("EEtiqueta","+"));
																out.println("<input type=\"radio\" name=\"lRH\" value=\"0\" >-");
																//out.println(vEti.Texto("EEtiqueta","-"));
																out.println("</td>");
															}
															if (vPERDatos2.getLRH() == 0) {
																out.println("<td colspan='5' class='EEtiquetaL'>");
																out.println("<input type=\"radio\" name=\"lRH\" value=\"1\" >+");
																//out.println(vEti.Texto("EEtiqueta","+"));
																out.println("<input type=\"radio\" name=\"lRH\" value=\"0\" checked>-");
																//out.println(vEti.Texto("EEtiqueta","-"));
																out.println("</td>");
															}
														} else {
															out.println("<td colspan='5' class='EEtiquetaL'>");
															out.println("<input type=\"radio\" name=\"lRH\" value=\"1\">+");
															out.println("<input type=\"radio\" name=\"lRH\" value=\"0\">-");
															out.println("</td>");
														}
		
														out.println("<tr>");
		
														out.println("<td class=\"EEtiqueta\">Usa Lentes:</td>");
														if (vPERDatos2.getLUsaLentes() == 1)
															out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\" checked></td>");
														else
															out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lUsaLentes\" value=\"1\"></td>");
		
														out.println("<td class=\"EEtiqueta\">Aéreo:</td>");
														if (vPERDatos2.getLAereo() == 1)
															out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lAereo\" value=\"1\" checked></td>");
														else
															out.println("<td class=\"ETabla\"><input type=\"checkbox\" name=\"lAereo\" value=\"1\"></td>");
		
														out.println("<td class=\"EEtiqueta\">Contacto:</td>");
														if (vPERDatos2.getLContacto() == 1)
															out.println("<td colspan=\"4\" class=\"ETabla\"><input type=\"checkbox\" name=\"lContacto\" value=\"1\" checked ></td>");
														else
															out.println("<td colspan=\"4\" class=\"ETabla\"><input type=\"checkbox\" name=\"lContacto\" value=\"1\" ></td>");
		
														out.println("</tr>");
		
														out.println("<tr>");
														out.println("<td class=\"EEtiqueta\">Hipertensión:</td>");
														if (vPERDatos2.getLHipertension() == 1)
															out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lHipertension\" value=\"1\" checked></td>");
														else
															out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lHipertension\" value=\"1\"></td>");
		
														out.println("<td class=\"EEtiqueta\">Diabetes:</td>");
														if (vPERDatos2.getLDiabetes() == 1)
															out.println("<td colspan=\"5\" class=\"ETabla\"><input type=\"checkbox\" name=\"lDiabetes\" value=\"1\" checked></td>");
														else
															out.println("<td colspan=\"5\" class=\"ETabla\"><input type=\"checkbox\" name=\"lDiabetes\" value=\"1\" ></td>");
		
														
														out.println("<input type=\"hidden\" name=\"hdServEVOFTA\" value=\""
																+ ServEVOFTA + "\">");
		
														//campo que nos servira para validar la fundamentacion
														out.println("<input type=\"hidden\" name=\"hdFund\" value=\"1\">");
												}
											}
										}

									}//fin if bloquea dictamen DTBLOQEADO
									else {
						%>
						<tr>
							<td colspan="9" class="ECampo">El periodo para dictaminar
								este examen ha concluido.</td>
						</tr>
						<%
							}
						} // lDictaminado != 0
									System.out.println(DtBlqDictamen);

								if (dtToday.compareTo(DtBlqDictamen) == 0) {
									//if((Funda == 0 && DiagRec == 1) || (Funda == 1 && DiagRec == 0)){
									if (DiagRec == 1 && vcMEDSERVObligatorio.size() == 0) {
						%>
						<tr>
							<td colspan="9" class="ETablaT">Dictamen por
								Categor&iacute;a</td>
						</tr>
						<%
							} else {
										if (Funda == 1 && vcMEDSERVObligatorio.size() == 0) {
						%><tr>
							<td colspan="9" class="ETablaT">Dictamen por
								Categor&iacute;a</td>
						</tr>
						<%
							}
									}
								}
								if (vcExpDicExam.size() > 0 && vcMEDSERVObligatorio.size() == 0) {
									System.out.println("OP3");
									//Bloquea examen por dtbloqueado
									if (dtToday.compareTo(DtBlqDictamen) == 0) {
										//Bloquea examen por falta de Fundamentacion
										//if((Funda == 0 && DiagRec == 1) || (Funda == 1 && DiagRec == 0)){
										if (DiagRec == 1 && vcMEDSERVObligatorio.size() == 0) {
											String cModob = "", cNomRadio = "", cCheckA = "", cCheckNA = "";
											boolean lActivo = false;
											for (int b = 0; b < vcExpDicExam.size(); b++) {
												vExpDicExam = (TVEXPDictamenServ) vcExpDicExam
														.get(b);
												out.println("<tr>");
												if (!vExpDicExam.getCDscMdoTrans()
														.equalsIgnoreCase(cModob))
													out.println(vEti.Texto("ETablaST",
															vExpDicExam.getCDscMdoTrans()));
												else
													out.println(vEti
															.Texto("ETablaST", "&nbsp;"));
												out.println(vEti.Texto("ETablaST",
														vExpDicExam.getCDscCategoria()));
												System.out.println("Numero dictaminador = " +vExpDicExam.getLDictamEm());
												System.out.println("Dictaminado como = " +vExpDicExam.getLDictamen());
												if (vExpDicExam.getLDictamEm() == 0)
													lActivo = true;
												else
													lActivo = false;
												cCheckA = "";
												cCheckNA = "";
												
											////validamos el resultado de diagnostico y recomendaciones del dictaminador
												String SqlAptoCat = "SELECT LDICTAMEN FROM EXPDICTAMENSERV WHERE ICVEEXPEDIENTE = "
															+ request
																	.getParameter("hdICveExpediente")
															+ " AND INUMEXAMEN = "
															+ request
																	.getParameter("hdINumExamen")
															+ " AND ICVEMDOTRANS = "
															+ vExpDicExam.getICveMdoTrans()
															+ " AND ICVECATEGORIA = "
															+ vExpDicExam.getICveCategoria()
															+ " AND ICVESERVICIO = 31";
													String ResulDictCat = dEXPDictamenServ2
															.SenFindBy(SqlAptoCat);//Obtenemos el Resultado que el dictamen de diagnostico y recomendaciones de la categoria;
												//////////////////
												
												if (vExpDicExam.getLDictamen() == 1)
													cCheckA = "\" Checked \""; 
												else
													cCheckNA = "\" Checked \""; 
												
												if(ResulDictCat.equals("1")){
													cCheckA = "\" Checked \""; 
												}
												
												cNomRadio = "lDictamen"
														+ vExpDicExam.getICveCategoria() + "_"
														+ vExpDicExam.getICveMdoTrans();
												out.println("<td class='EPieC'>"
														+ vEti.ObjRadioSE("EPieC", cNomRadio,
																"1", "", "", cCheckA, "", "",
																0, true, lActivo) + "</td>");
												System.out.println("- lDictamen"	+ vExpDicExam.getICveCategoria() + "_"+ vExpDicExam.getICveMdoTrans());
												out.println(vEti.Texto("EEtiquetaL", "Apto"));
												if (vExpDicExam.getLDictamEm() == 0) {
													%>
													<td class='EPieC'><input type="radio" name="<%=cNomRadio%>"
														value="0" onMouseOut="if (window.fOutField) window.fOutField();"
														onMouseOver="if (window.fOverField) window.fOverField(0);fFunda();"
														onClick="" onBlur="" onFocus=""></td>
													<%
												} else {
													out.println("<td class='EPieC'>"
															+ vEti.ObjRadioSE("EPieC",
																	cNomRadio, "0", "", "",
																	cCheckNA, "", "", 0, true,
																	lActivo) + "</td>");
												}
												
												out.println(vEti.TextoCS("EEtiquetaL",
														"No Apto", 4));
												out.println("</tr>");
												cModob = vExpDicExam.getCDscMdoTrans();
												dictamenes = dictamenes + ""
														+ vExpDicExam.getICveCategoria() + "_"
														+ vExpDicExam.getICveMdoTrans() + ",";
											}// for vcExpDicExam
											
											Guardar = true;
											
										}//Bloquea ductamen por falta de Fundamentacion.
										else {
											if (Funda == 1) {
												String cModob = "", cNomRadio = "", cCheckA = "", cCheckNA = "";
												boolean lActivo = false;
												for (int b = 0; b < vcExpDicExam.size(); b++) {
													vExpDicExam = (TVEXPDictamenServ) vcExpDicExam
															.get(b);
													out.println("<tr>");
													if (!vExpDicExam.getCDscMdoTrans()
															.equalsIgnoreCase(cModob))
														out.println(vEti.Texto("ETablaST",
																vExpDicExam.getCDscMdoTrans()));
													else
														out.println(vEti.Texto("ETablaST",
																"&nbsp;"));
													out.println(vEti.Texto("ETablaST",
															vExpDicExam.getCDscCategoria()));
													System.out.println("Numero dictaminador = " +vExpDicExam.getLDictamEm());
													System.out.println("Dictaminado como = " +vExpDicExam.getLDictamen());
													if (vExpDicExam.getLDictamEm() == 0)
														lActivo = true;
													else
														lActivo = false;
													cCheckA = "";
													cCheckNA = "";
													if (vExpDicExam.getLDictamen() == 1)
														cCheckA = "\" Checked \"";
													else
														cCheckNA = "\" Checked \"";
													cNomRadio = "lDictamen"
															+ vExpDicExam.getICveCategoria()
															+ "_"
															+ vExpDicExam.getICveMdoTrans();
													String SqlAptoCat = "SELECT LDICTAMEN FROM EXPDICTAMENSERV WHERE ICVEEXPEDIENTE = "
															+ request
																	.getParameter("hdICveExpediente")
															+ " AND INUMEXAMEN = "
															+ request
																	.getParameter("hdINumExamen")
															+ " AND ICVEMDOTRANS = "
															+ vExpDicExam.getICveMdoTrans()
															+ " AND ICVECATEGORIA = "
															+ vExpDicExam.getICveCategoria()
															+ " AND ICVESERVICIO = 31";
													String ResulDictCat = dEXPDictamenServ2
															.SenFindBy(SqlAptoCat);//Obtenemos el Resultado que el dictamen de diagnostico y recomendaciones de la categoria;
													System.out.println("-- lDictamen"+ vExpDicExam.getICveCategoria()+ "_"+ vExpDicExam.getICveMdoTrans()+ " = " + ResulDictCat);
													if (ResulDictCat.equals("1")) {//Resultado que el dictamen de diagnostico y recomendaciones de la categoria se apto;
														out.println("<td class='EPieC'>"
																+ vEti.ObjRadioSE("EPieC",
																		cNomRadio, "1", "", "",
																		cCheckA, "", "", 0,
																		true, lActivo)
																+ "</td>");
														out.println(vEti.Texto("EEtiquetaL",
																"Apto"));
														if (vExpDicExam.getLDictamEm() == 0) {
						%>
						<td class='EPieC'><input type="radio" name="<%=cNomRadio%>"
							value="0" onMouseOut="if (window.fOutField) window.fOutField();"
							onMouseOver="if (window.fOverField) window.fOverField(0);fFunda();"
							onClick="" onBlur="" onFocus=""></td>
						<%
							} else {
															out.println("<td class='EPieC'>"
																	+ vEti.ObjRadioSE("EPieC",
																			cNomRadio, "0", "",
																			"", cCheckNA, "",
																			"", 0, true,
																			lActivo) + "</td>");
														}
														out.println(vEti.TextoCS("EEtiquetaL",
																"No Apto", 4));
													} else {
														if (vExpDicExam.getLDictamEm() == 0 && procesomed < 2) {
															System.out.println("Opcion 1");
						%>
						<td class='EPieC'><input type="radio" name="<%=cNomRadio%>"
							value="1" onMouseOut="if (window.fOutField) window.fOutField();"
							onMouseOver="if (window.fOverField) window.fOverField(0);"
							<% if(bTransfronterizo){ %>	onBlur="" onFocus="">
							<% }else{ %>onClick="activa('<%=cNomRadio%>')" onBlur="" onFocus=""><% }%>
						</td>
						<td class="EEtiquetaL">Apto</td>

						<td class='EPieC'><input type="radio" name="<%=cNomRadio%>"
							value="0" onMouseOut="if (window.fOutField) window.fOutField();"
							onMouseOver="if (window.fOverField) window.fOverField(0);"
							onClick="" Checked "" onBlur="" onFocus=""></td>
						<td class="EEtiquetaL" colspan="4">No Apto</td>
						<%
							Guardar = true;
							} else {
									System.out.println("DiagReConEmo = "+DiagReConEmo);
									if (DiagReConEmo == 0 && bTransfronterizo == false) {
										Mensaje = "No podrá dictaminar el examen hasta que haya Capturado la información de Diagnostico y Recomendación del Dictaminador.";
										%>
										<tr>
											<td colspan="9" class="ETablaST"><%=Mensaje%></td>
										</tr>
										<%
									} else {
													if(DiagRec == 1){
														out.println("<td class='EPieC'>"
																		+ vEti.ObjRadioSE("EPieC",
																				cNomRadio, "1", "",
																				"", cCheckA, "",
																				"", 0, true,
																				lActivo) + "</td>");
													}else{
														if(lDictaminado == 0){
															out.println("<td class='EPieC'>"
																	+ vEti.ObjRadioSE("EPieC",
																			cNomRadio, "1", "",
																			"", cCheckA, "",
																			"", 0, true,
																			lActivo) + "</td>");
														}else{
															out.println("<td class='EPieC'><input type=\"radio\" name=\""+cNomRadio+"\""+
																"value=\"1\" onMouseOut=\"if (window.fOutField) window.fOutField();\""+
																"onMouseOver=\"if (window.fOverField) window.fOverField(0);\""+
																"onClick=\"activa('"+cNomRadio+"')\" onBlur=\"\" onFocus=\"\">"+
															"</td>");
														}
													}
													
																out.println(vEti.Texto(
																		"EEtiquetaL", "Apto"));
																System.out.println("DiagRec = "+DiagRec);

																out.println("<td class='EPieC'>"
																		+ vEti.ObjRadioSE("EPieC",
																				cNomRadio, "0", "",
																				"", cCheckNA, "",
																				"", 0, true,
																				lActivo) + "</td>");
																out.println(vEti
																		.TextoCS("EEtiquetaL",
																				"No Apto", 4));
									}
			
														}
													}

													out.println("</tr>");
													cModob = vExpDicExam.getCDscMdoTrans();
													dictamenes = dictamenes + ""
															+ vExpDicExam.getICveCategoria()
															+ "_"
															+ vExpDicExam.getICveMdoTrans()
															+ ",";
												}
											}
										}
									}//if bloquea dictamen
									else {
						%>
						<tr>
							<td colspan="9" class="ECampo">Favor de ponerse en contacto
								con el administrador del sistema.</td>
						</tr>
						<%
							}
								

								} else {
									System.out.println("OP4");
									if (vcEXPDicSer.size() > 0 && vcMEDSERVObligatorio.size() == 0) {
										String cModoc = "";
										for (int i = 0; i < vcEXPDicSer.size(); i++) {
											vEXPDicSer = (TVEXPDictamenServ) vcEXPDicSer.get(i);
											out.println("<tr>");
											if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(
													cModoc)) {
												out.println("<td class=\"ETablaT\">"
														+ vEXPDicSer.getCDscMdoTrans()
														+ "</td>");
											} else {
												out.println("<td class=\"ETablaT\">&nbsp;</td>");
											}
											out.println("<td class=\"ETablaT\">"
													+ vEXPDicSer.getCDscCategoria() + "</td>");
											out.println("<td align=\"right\">");
											out.println("<input type=\"radio\" name=\"lDictamen"
													+ vEXPDicSer.getICveCategoria()
													+ "_"
													+ vEXPDicSer.getICveMdoTrans()
													+ "\" value=\"1\" Checked>");
											out.println("</td>");
											out.println("<td align=\"left\" class=\"EEtiquetaL\">");
											out.println("Apto");
											out.println("</td>");
											out.println("<td align=\"right\">");
											out.println("<input type=\"radio\" name=\"lDictamen"
													+ vEXPDicSer.getICveCategoria()
													+ "_"
													+ vEXPDicSer.getICveMdoTrans()
													+ "\" value=\"0\">");
											out.println("</td>");
											out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"4\">");
											out.println("No Apto");
											out.println("</td>");
											out.println("</tr>");
											cModoc = vEXPDicSer.getCDscMdoTrans();
										}
									}
								}
						%>
					</table> <%
					
					
					System.out.println("Constancia7");
					System.out.println("clsConfig.getAccion() = "+clsConfig.getAccion());
 	if (clsConfig.getAccion().compareToIgnoreCase(
 				"Imprime Documentacion") == 0
 				|| clsConfig.getlGuardar()) {
 			System.out.println("Guardardando e Imprimiendo");
 			//out.println(clsConfig.getActiveX());
 			//BEA SYSTEMS		18/10/2006
 			byte[] data = (byte[]) request.getAttribute("REPORTE_PDF");

 			long time = System.currentTimeMillis();
 			
 			String str = new String(data, "UTF-8");
 			System.out.println("str = "+str);
 			
			
 			if (data == null){
	 				request.getRequestDispatcher(
	 						"DictamenError.jsp?time=" + time).forward(
	 						request, response);
 				}else{
 					if(str.equals("errorfoto")){
 						str = "";
 						vErrores.acumulaError("", 22, ", favor de capturarla nuevamente con una resolución menor a 1024 píxeles");
 						vErrores.muestraError();
 					}else{
 						if(str.equals("0bytes")){
 							str = "";
 							vErrores.acumulaError("", 23, ", y si el problema persiste capture biométricos de nuevo.");
 	 						vErrores.muestraError();
 						}else{
 							//request.getRequestDispatcher("Dictamen.pdf?time="+time).forward(request,response);
 							str = "";
 							request.getRequestDispatcher("DictamenPDF.jsp?time=" + time)
 								.forward(request, response);
 						}
 				}
 			}
 		}
					
 		if (lDictaminado == 0) {
 			/*if(vcExpDicExam.size()>0 && (request.getParameter("hdBoton").compareTo("Guardar") == 0 ||
 			   request.getParameter("hdBoton").compareTo("Actual") == 0 ||
 			   request.getParameter("hdBoton").compareTo("Imprime Documentacion") == 0 )){*/
 %>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
							// Inicio de Datos
						%>
						<%
							out.println("<tr>");
									out.print("<td class=\"ECampo\">");
									out.println("<div id=\"contenido_a_mostrar\">");
									out.print(vEti
											.clsAnclaTexto(
													"EAncla",
													"Impresión de Dictamen",
													"JavaScript:muestra_oculta('contenido_a_mostrar');Genera_Doc();mensaje();",
													"Generar Documentacion"));
									//out.print(vEti.clsAnclaTexto("EAncla","Impresi�n de Dictamen","JavaScript:muestra_oculta('contenido_a_mostrar');mensaje();","Generar Documentacion"));
									out.println("</div>");
									out.print("</td>");
									out.println("</tr>");
						%>
					</table> <%
 	} else {
 			out.println("<tr>");
 			out.print("<td class=\"ECampo\">");
 			out.println("<div id=\"contenido_a_mostrar\">");
 			//out.print(vEti.clsAnclaTexto("EAncla","Impresi�n de Dictamen","JavaScript:muestra_oculta('contenido_a_mostrar');Genera_Doc();mensaje();","Generar Documentacion"));
 			//out.print(vEti.clsAnclaTexto("EAncla","Impresi�n de Dictamen","JavaScript:muestra_oculta('contenido_a_mostrar');mensaje();","Generar Documentacion"));
 			out.println("</div>");
 			out.print("</td>");
 			out.println("</tr>");
 		}
 		out.println(" <input type=\"hidden\" name=\"hdDictamenes\" value=\""
 				+ dictamenes + "\"> ");
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
		
		<% if(Guardar){ %>
				 <input type="hidden" name="hdGuardar" value="true">
		<% }else{ %>
				<input type="hidden" name="hdGuardar" value="false">
		<% } %>
	</form>
	<script LANGUAGE="JavaScript">
  // Esta funcion no debe modificarse
  function activa(str){
    var form = document.forms[0];
	var sAux="";
	var idele = 0;
	var frm = document.getElementById("dictamen");
	for (i=0;i<frm.elements.length;i++)
	{
		if(frm.elements[i].name == str){
			//sAux += i+"NOMBRE: " + frm.elements[i].name + " ";
			//sAux += "TIPO :  " + frm.elements[i].type + " "; ;
			//sAux += "VALOR: " + frm.elements[i].value + "\n" ;
			idele = i;
			break 
		}
	}
//	alert(sAux);
//	alert(idele);
    var campo=document.forms[0][idele+1];
	campo.checked="true";
	alert("Esta categoría solo puede ser dictaminada como No apto.");
  } 
</script>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%
	vEntorno.clearListaImgs();
%>
</html>

