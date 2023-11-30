<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	/**
	 * Title: Selección del paciente al servicio
	 * Description: Selección del paciente al servicio
	 * Copyright: 2012
	 * @author Lic. AGSA
	 * @version 1
	 * Clase: pg070104035
	 */
%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="gob.sct.medprev.util.reglas.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

 
<%
	pg070104035CFG2 clsConfig = new pg070104035CFG2(); // modificar
	TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
	//  clsConfig.setUsuario(vUsuario.getICveusuario());
	String rama = "1";
	session.setAttribute("rama", rama);
	
		TDEXPExamCat cat = new TDEXPExamCat();
 int lconcluido = cat.FindByInt("Select lconcluido from exprama where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio")+
					" and icveservicio = "+session.getAttribute("servicio") +" and icverama = "+session.getAttribute("rama") +"");
		 if(lconcluido==0){
	
	//////Otenemos Reques ///////////
	//int iCveUniMed = Integer.parseInt(iCveUniMed == null ? "0" : iCveUniMed);
	TDEXPExamCat cat2 = new TDEXPExamCat();
	int iCveUniMed = cat2.FindByInt("Select icveunimed from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio"));
	//int iCveProceso = Integer.parseInt(iCveProceso == null ? "0" : iCveProceso);
	int iCveProceso = cat2.FindByInt("Select icveproceso from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio"));
	//int iCveModulo = Integer.parseInt(iCveModulo == null ? "0" : iCveModulo);
	int iCveModulo = cat2.FindByInt("Select icvemodulo from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio"));
	int iCveServicio = Integer.parseInt( ""+session.getAttribute("servicio") == null ? "0" :  ""+session.getAttribute("servicio"));
	int iCveRama = Integer.parseInt( ""+session.getAttribute("rama") == null ? "0" :  ""+session.getAttribute("rama"));
	String ciCveExpediente = session.getAttribute("id")+"";
	System.out.println(session.getAttribute("id")+"");
	String cExamen = session.getAttribute("episodio")+"";
	System.out.println(session.getAttribute("episodio")+"");
	String cToken = session.getAttribute("stToken")+"";
	System.out.println("cToken="+cToken);	
	

System.out.println("1");
	/////MODULO CONFIGURADO PARA EL DISPOSITIVO Spot Vital Signs LXi//////
	TDGRLModuloDisp MD = new TDGRLModuloDisp();
	boolean SVSLXi = false;

	try {
		if (iCveServicio == 11) {
			SVSLXi = MD.FindByTrue(" WHERE A.ICVEUNIMED = " + iCveUniMed + " AND "
					+ "A.iCveModulo = " + iCveModulo + " AND " + "A.iCveProceso = "
					+ iCveProceso + " AND " + "A.iCvedispositivo = 1  AND "
					+ "A.lActivo = 1 and ");
		}
	} catch (Exception e) {
		SVSLXi = false;
	}

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setNombrePagina("pg070104035.jsp"); // modificar
	vEntorno.setArchTooltips("07_Tooltips");

	if (SVSLXi) {
		vEntorno.setOnLoad("fOnLoad();BloqueaCampos();lee_json('1');");
	} else {
		vEntorno.setOnLoad("fOnLoad();");
	}
	System.out.println("2");
	vEntorno.setArchFuncs("FValida");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg070104035.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cCatalogo = "pg070104011.jsp"; // modificar
	String cOperador = "1"; // modificar
	String cDscOrdenar = "Grupo|Inicio vigencia|Fin vigencia|Vigente|"; // modificar
	String cCveOrdenar = "cDscGrupo|dtInicio|dtFin|lVigente|"; // modificar
	String cDscFiltrar = "Inicio vigencia|Fin vigencia|"; // modificar
	String cCveFiltrar = "p.dtInicio|p.dtFin|"; // modificar
	String cTipoFiltrar = "5|5|"; // modificar 7/8
	boolean lFiltros = false; // modificar
	boolean lIra = true; // modificar
	String cEstatusIR = "Imprimir"; // modificar
	System.out.println("3");
	// LLamado al Output Header
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
	int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
	int iNumRowsSec = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
	int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();

	TError vErrores = new TError();
	StringBuffer sbErroresAcum = new StringBuffer();
	TEtiCampo vEti = new TEtiCampo();

	clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	// si los sintomas que se guardaron corresponden a la ultima rama del servicio,
	// se debe direccionar a la pagina de diagnosticos (pg070104032.jsp)
	String cPaginas = clsConfig.getPaginas();
	String cDscPaginas = clsConfig.getDscPaginas();
	PageBeanScroller bs = clsConfig.getBeanScroller();
	String cUpdStatus = "SaveOnly";
	String cNavStatus = "Hidden";//clsConfig.getNavStatus();
	String cOtroStatus = clsConfig.getOtroStatus();
	String cCanWrite = clsConfig.getCanWrite();
	String cSaveAction = "Guardar";
	String cDeleteAction = "BorrarB";
	String cClave = "";
	String cPosicion = "";

	TFechas oFecha = new TFechas();
	String cHoy = oFecha.getFechaYYYYMMDD(oFecha.TodaySQL(), "/");
	String cHoy2 = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(), "/");
	System.out.println("4");
	String validaIMC = "";

	if (iCveServicio == 11) {
		validaIMC = "onMouseOver=\"IMC('');\"";
	}
	if (iCveServicio == 75) {
		validaIMC = "onMouseOver=\"IMC2('');\"";
	}

	TVEXPRama nextRama2;
	nextRama2 = clsConfig.getNextRama2();

	int iCveMdoTrans = clsConfig.getMDOTransDos(ciCveExpediente,
			cExamen);
	TDEXPExamCatExt1 dEXPExamCatExt1 = new TDEXPExamCatExt1();
	String TrasnposrteCategoriaMotivo = dEXPExamCatExt1.DepedientesSQL(ciCveExpediente,
			cExamen);
	int iCvecategoria = clsConfig.getCategoriaDos(ciCveExpediente,
			cExamen,iCveMdoTrans+"");
	String iCveMotivo22 = clsConfig.getMotivoDos(ciCveExpediente,
			cExamen,iCveMdoTrans+"");
	int iCveMotivo = clsConfig.getMotivoDosI(ciCveExpediente,
			cExamen,iCveMdoTrans+"");
	int iCveRama2 = 0;
	int Dependiente2 = 0;
	TDMEDREGSIN dMEDREGSIN2 = new TDMEDREGSIN();
	TDMEDREGSINExt1 dMEDREGSINExt = new TDMEDREGSINExt1();
	TDPERDatos dPERDatos = new TDPERDatos();
	String SCWhere2 = "";
	String SCWhere3 = "";
	System.out.println("5");
	System.out.println("Usuario="+vUsuario.getICveusuario());
	
	 try{
		 
		 System.out.println("########################################################");
		 System.out.println(ciCveExpediente);
		 System.out.println(cExamen);
		 
		 int iexpediente = Integer.parseInt(ciCveExpediente);
		 int iexamen = Integer.parseInt(cExamen);

		 System.out.println(iexpediente);
		 System.out.println(iexamen);
		 
		 
		 
		 
		 

			TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
			TVEXPAudiomet vEXPAudiomet = new TVEXPAudiomet();

			try {
				iCveServicio = 6;
			
				if (request.getParameter("hdOIM") != null
						&& request.getParameter("hdOIM").trim().length() > 0
						&& !request.getParameter("hdOIM").equals("null")) {

					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdOIM"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(1);
						vEXPAudiomet.setITipo(1);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}

				}
				if (request.getParameter("hdOIC") != null
						&& request.getParameter("hdOIC").trim().length() > 0
						&& !request.getParameter("hdOIC").equals("null")) {
					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdOIC"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(1);
						vEXPAudiomet.setITipo(2);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}

				}

				if (request.getParameter("hdOIT") != null
						&& request.getParameter("hdOIT").trim().length() > 0
						&& !request.getParameter("hdOIT").equals("null")) {
					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdOIT"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(1);
						vEXPAudiomet.setITipo(3);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}

				}
				if (request.getParameter("hdOIN") != null
						&& request.getParameter("hdOIN").trim().length() > 0
						&& !request.getParameter("hdOIN").equals("null")) {
					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdOIN"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(1);
						vEXPAudiomet.setITipo(4);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			
			try {
				iCveServicio = 6;
				
				if (request.getParameter("hdODM") != null
						&& request.getParameter("hdODM").trim().length() > 0
						&& !request.getParameter("hdODM").equals("null")) {
					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdODM"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(2);
						vEXPAudiomet.setITipo(1);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}
				}

				if (request.getParameter("hdODC") != null
						&& request.getParameter("hdODC").trim().length() > 0
						&& !request.getParameter("hdODC").equals("null")) {
					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdODC"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(2);
						vEXPAudiomet.setITipo(2);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}
				}
				if (request.getParameter("hdODT") != null
						&& request.getParameter("hdODT").trim().length() > 0
						&& !request.getParameter("hdODT").equals("null")) {
					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdODT"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(2);
						vEXPAudiomet.setITipo(3);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}
				}
				if (request.getParameter("hdODN") != null
						&& request.getParameter("hdODN").trim().length() > 0
						&& !request.getParameter("hdODN").equals("null")) {
					StringTokenizer stKey = new StringTokenizer(
							request.getParameter("hdODN"), ",");
					while (stKey.hasMoreElements()) {

						vEXPAudiomet.setIX(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIY(Integer.parseInt(stKey.nextToken()));
						vEXPAudiomet.setIOido(2);
						vEXPAudiomet.setITipo(4);
						vEXPAudiomet.setICveServicio(iCveServicio);
						dEXPAudiomet.insert2(null, null, vEXPAudiomet, iexpediente, iexamen);
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		 
			out.println("[{\"resp\":\"success\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]");
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 /*
		 
		if(clsConfig.Guardar(iCveServicio+"", iCveRama+"",ciCveExpediente, cExamen, cToken, vUsuario.getICveusuario())==1){
			out.println("[{\"resp\":\"success\"},{\"dispositivo\":\"pc\"},{\"via\":\"correcta\"}]");	
		}else{
			out.println("[{\"resp\":\"Error al guardar la Rama\"}]");
		}*/
        
     }catch(Exception e){
     	e.printStackTrace();
        
     }
		 }else{
			out.println("[{\"resp\":\"La Rama ya fue cargada\"}]");
		}
	
	%>
