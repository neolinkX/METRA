<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<%
	/**
	 * Title: Selección del paciente al servicio
	 * Description: Selección del paciente al servicio
	 * Copyright: 2012
	 * @author Lic. AG SA L
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
	pg070104035CFG clsConfig = new pg070104035CFG(); // modificar
	TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
	//  clsConfig.setUsuario(vUsuario.getICveusuario());

	//////Otenemos Reques ///////////
	int iCveUniMed = Integer
			.parseInt(request.getParameter("iCveUniMed") == null ? "0" : request.getParameter("iCveUniMed"));
	int iCveProceso = Integer
			.parseInt(request.getParameter("iCveProceso") == null ? "0" : request.getParameter("iCveProceso"));
	int iCveModulo = Integer
			.parseInt(request.getParameter("iCveModulo") == null ? "0" : request.getParameter("iCveModulo"));
	int iCveServicio = Integer.parseInt(
			request.getParameter("iCveServicio") == null ? "0" : request.getParameter("iCveServicio"));
	int iCveRama = Integer
			.parseInt(request.getParameter("iCveRama") == null ? "0" : request.getParameter("iCveRama"));

	/////MODULO CONFIGURADO PARA EL DISPOSITIVO Spot Vital Signs LXi//////
	TDGRLModuloDisp MD = new TDGRLModuloDisp();
	boolean SVSLXi = false;

	try {
		if (iCveServicio == 11) {
			SVSLXi = MD.FindByTrue(" WHERE A.ICVEUNIMED = " + request.getParameter("iCveUniMed") + " AND "
					+ "A.iCveModulo = " + request.getParameter("iCveModulo") + " AND " + "A.iCveProceso = "
					+ request.getParameter("iCveProceso") + " AND " + "A.iCvedispositivo = 1  AND "
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

	String validaIMC = "";

	if (iCveServicio == 11) {
		validaIMC = "onMouseOver=\"IMC('');\"";
	}
	if (iCveServicio == 75) {
		validaIMC = "onMouseOver=\"IMC2('');\"";
	}

	TVEXPRama nextRama2;
	nextRama2 = clsConfig.getNextRama2();

	int iCveMdoTrans = clsConfig.getMDOTrans();
	TDEXPExamCatExt1 dEXPExamCatExt1 = new TDEXPExamCatExt1();
	String TrasnposrteCategoriaMotivo = dEXPExamCatExt1.DepedientesSQL(request.getParameter("iCveExpediente"),
			request.getParameter("iNumExamen"));
	int iCvecategoria = clsConfig.getCategoria();
	String iCveMotivo22 = clsConfig.getMotivo22();
	int iCveMotivo = clsConfig.getMotivo();
	int iCveRama2 = 0;
	int Dependiente2 = 0;
	TDMEDREGSIN dMEDREGSIN2 = new TDMEDREGSIN();
	TDMEDREGSINExt1 dMEDREGSINExt = new TDMEDREGSINExt1();
	TDPERDatos dPERDatos = new TDPERDatos();
	String SCWhere2 = "";
	String SCWhere3 = "";

	if (nextRama2 != null) {
		iCveRama2 = nextRama2.getICveRama();

		///Actualiza fecha de la rama///
		clsConfig.ActualizaFechaInicioRama(request.getParameter("iCveExpediente"),
				request.getParameter("iNumExamen"), request.getParameter("iCveServicio"), iCveRama2 + "");

		///EXISTIRAN PREGUNTAS DEPENDIENTES
		SCWhere2 = "";
		/*SCWhere2 = "               D.ICVESERVICIO = "+iCveServicio+" AND	"
		+ "               D.ICVERAMA = "+iCveRama2+" AND		"
		+ "               D.ICVEMDOTRANS =  "+iCveMdoTrans+" AND		"
		+ "               D.ICVECATEGORIA =  "+iCvecategoria+" AND"
		+ "	         R.ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente")+" AND"
		+ "              R.INUMEXAMEN = "+request.getParameter("iNumExamen")+"" ;*/
		SCWhere2 = "      D.ICVESERVICIO = " + iCveServicio + "  AND"
				+ "                           D.ICVERAMA = " + iCveRama2 + " AND"
				+ "                           D.ICVEMDOTRANS =   " + iCveMdoTrans + " AND		"
				+ "                           D.ICVECATEGORIA =   " + iCvecategoria + "  AND"
				+ " 			  R.ICVEMOTIVO =  " + iCveMotivo + " ";

		SCWhere3 = "      M.ICVESERVICIO = " + iCveServicio + "  AND"
				+ "                           M.ICVERAMA = " + iCveRama2 + " AND"
				+ "                           T.ICVEMDOTRANS =   " + iCveMdoTrans + " AND		"
				+ "                           C.ICVECATEGORIA =   " + iCvecategoria + " ";

		try {
			//Dependiente2 = dMEDREGSIN2.FindByAllDep2(SCWhere2);
			Dependiente2 = dMEDREGSIN2.FindByAllDep3(SCWhere2);
		} catch (Exception ex) {
			Dependiente2 = 0;
		}
		if (Dependiente2 > 0) {
			if (iCveRama2 == 0) {
%>
<html onMouseOver="fIrVerExamen();" xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="en" lang="en">
<%
	} else {
%>
<html onMouseOver="mostrardiv();" xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="en" lang="en">
<%
	}
		} else {
			if (iCveRama2 == 0) {
%>
<html onMouseOver="fIrVerExamen();" xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="en" lang="en">
<%
	} else {
%>
<html <%=validaIMC%> xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"
	lang="en">
<%
	}
		}
	} else {
		if (Dependiente2 > 0) {
%>
<html onMouseOver="mostrardiv();fIrVerExamen(0, 0,'pg07010407.jsp');"
	xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%
	} else {
%>
<html onMouseOver="fIrVerExamen(0, 0,'pg07010407.jsp');"
	xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%
	}
	}
%>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0, vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "SelPer.js"%>"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "Audio01.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "Audio02.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "Audio03.js"%>"></SCRIPT>
<!--
<SCRIPT LANGUAGE="JavaScript" SRC="C:\Users\AG\Desktop\sct\medprev\Archivos\funciones\Audio01.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="C:\Users\AG\Desktop\sct\medprev\Archivos\funciones\Audio02.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="C:\Users\AG\Desktop\sct\medprev\Archivos\funciones\Audio03.js"></SCRIPT>
-->
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "SpotVitalSignsLXI.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "JSON-js-master/json2.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cStyle = '<link rel="stylesheet" href="/exm/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';
  var aSel = new Array();
  var aSelMuestra = new Array();

  // Esta función recibe el vector con los valores capturados en el tipo de examen al dar click en guardar.
  function fSaveAudio(aSel,iTipoSel, oido){
    form = document.forms[0];
    //alert(aSel + '..' + iTipoSel + '..' + oido);
    if (oido == "Derecho") {
      if (iTipoSel == 1)
         form.hdOIM.value = aSel;
      else if (iTipoSel == 2)
         form.hdOIC.value = aSel;
      else if (iTipoSel == 3)
         form.hdOIT.value = aSel;
      else
         form.hdOIN.value = aSel;
    }
     if (oido == "Izquierdo") {
      if (iTipoSel == 1)
         form.hdODM.value = aSel;
      else if (iTipoSel == 2)
         form.hdODC.value = aSel;
      else if (iTipoSel == 3)
         form.hdODT.value = aSel;
      else
         form.hdODN.value = aSel;
    }
    fAsigna2(oido);    
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  // Variable global para hacer referencia a la subventana
  var newWindowCholestech;

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  // Generar y llenar la nueva ventana
  function makeNewWindowCholestech() {
      // asegurarse de que no se habia abierto
      if (!newWindowCholestech || newWindowCholestech.closed) {
          newWindowCholestech = window.open("", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
          // retardar escritura hasta que la ventana exista en IE/Windows
          setTimeout("writeToWindowCholestech()", 50);
          newWindowCholestech.focus( );
      } else if (newWindowCholestech.focus) {
          // la ventana ya estaba abierta y con focus para traerla al frente
          newWindowCholestech.focus( );
      }
  }


  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  function writeToWindowCholestech() {
      // crear la nueva ventana
      var cPagina='<html><title>Cargar Archivo del Equipo Cholestech</title>'+
                  '<body>'+
                  '<form method="post" action="servCholestechUpXLS" name="formXLS" enctype="multipart/form-data">'+
                  'Seleccione el archivo: <input type="file" name="fileName"/>'+
                  '<br />'+
                  '<input type="submit" value="Cargar archivo"/>'+
                  '</form>'+
                  '</body>'+
                  '</html>';
      // escribir HTML al documento de la nueva ventana
      newWindowCholestech.document.write(cPagina);
      newWindowCholestech.document.close( ); // cerrar el stream
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006
  // Variable global para hacer referencia a la subventana
  var newWindowDaytona;

  // Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006
  // Generar y llenar la nueva ventana
  function makeNewWindowDaytona() {
      // asegurarse de que no se habia abierto
      if (!newWindowDaytona || newWindowDaytona.closed) {
          newWindowDaytona = window.open("", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
          // retardar escritura hasta que la ventana exista en IE/Windows
          setTimeout("writeToWindowDaytona()", 50);
          newWindowDaytona.focus( );
      } else if (newWindowDaytona.focus) {
          // la ventana ya estaba abierta y con focus para traerla al frente
          newWindowDaytona.focus( );
      }
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006
  function writeToWindowDaytona() {
      form = document.forms[0];

      // crear la nueva ventana
      var cPagina='<html><title>Cargar Archivo del Equipo Daytona</title>'+
                  '<body>'+
                  '<form method="post" action="servDaytonaLabAnalisisClinicoUpXLS?icveexpediente='+form.iCveExpediente.value+
                  '&icveservicio='+form.iCveServicio.value+
                  '&icverama='+form.iCveRama.value+'" name="formXLS" enctype="multipart/form-data">'+
                  'Seleccione el archivo: <input type="file" name="fileName"/>'+
                  '<br />'+
                  '<input type="submit" value="Cargar archivo"/>'+
                  '</form>'+
                  '</body>'+
                  '</html>';

      // escribir HTML al documento de la nueva ventana
      newWindowDaytona.document.write(cPagina);
      newWindowDaytona.document.close( ); // cerrar el stream
  }
  
  
  
  
  
  
  
  //Agregado AG SA 09/07/2008
  function IMC(valor){
        form = document.forms[0];
        var peso = form.dValorI_16.value ;
        var estatura = form.dValorI_17.value;
        var estaturametros = estatura/100;
        var imc = peso / (estaturametros * estaturametros);
        imc = imc.toFixed(2); //trunca a 2 decimales
      //form.dValorI_18.value = imc;
        if(isNaN(imc)){
        	form.dValorI_117.value = "0";
        }else{
        	form.dValorI_117.value = imc;
        }
    }
  
  function IMC2(valor){
      form = document.forms[0];
      var peso = form.dValorI_115.value ;
      var estatura = form.dValorI_116.value;
      var estaturametros = estatura/100;
      var imc = peso / (estaturametros * estaturametros);
      imc = imc.toFixed(2); //trunca a 2 decimales
    //form.dValorI_18.value = imc;
      if(isNaN(imc)){
      	form.dValorI_119.value = "0";
      }else{
      	form.dValorI_119.value = imc;
      }
  }


  //Agregado AG SA 09/04/2012
            function mostrardiv() {
                form = document.forms[0];
                <%TDMEDAReg dAReg = new TDMEDAReg();
			TVMEDAReg vAReg = new TVMEDAReg();
			TVMEDAReg vAReg2 = new TVMEDAReg();
			TVMEDAReg vAReg3 = new TVMEDAReg();
			String CondReg = "";
			Vector CReglas = new Vector();
			try {
				CondReg = " T.ICVESERVICIO = " + iCveServicio + " AND" + "		T.ICVERAMA = " + iCveRama2 + " AND"
				///////// Modificado el dia 20 de julio del 2015 ///////////////
				//+ "		T.ICVEMOTIVO = " + iCveMotivo + " AND"
				//+ "		T.ICVEMDOTRANS = " + iCveMdoTrans + " AND"
				//+ "		R.ICVECATEGORIA = " + iCvecategoria + " AND"
				///////////////////////////////////////////////////////////////
						+ TrasnposrteCategoriaMotivo + " AND " + "		T.ICVEPROCESO = 1 ";
				CReglas = dAReg.FindByAll2(CondReg);
			} catch (Exception e) {

			}
			for (int i = 0; i < CReglas.size(); i++) {
				/*  String respuesta ="";
				        try{
				            respuesta = dPERDatos.SenFindBy("select icvetporesp from medsintomas where icveservicio = "+iCveServicio+" and icverama = "+iCveRama2+" and icvesintoma = "+vAReg.getICveSintoma());
				            }catch(Exception e){
				                respuesta = "";
				        }*/

				////////// Inicion - Verifica que un sintoma no dependa de mas de una respuesta/////////////////
				String condicion = "";
				int count = 0;
				for (int j = i; j < (CReglas.size() - 1); j++) {
					vAReg2 = (TVMEDAReg) CReglas.get(j);
					vAReg3 = (TVMEDAReg) CReglas.get(j + 1);
					if (vAReg2.getICveSintoma() != vAReg3.getICveSintoma()) {
						j = CReglas.size();
						//System.out.println("Los sintomas no son iguales sale de ciclo");
						//System.out.println(vAReg2.getICveSintoma() +"!="+ vAReg3.getICveSintoma());
					}
					if (vAReg2.getICveSintomaDep() == vAReg3.getICveSintomaDep()
							&& vAReg2.getICveSintoma() == vAReg3.getICveSintoma()
							&& (vAReg2.getICveTpoResp() == 8 || vAReg2.getICveTpoResp() == 13)
							&& (vAReg3.getICveTpoResp() == 8 || vAReg3.getICveTpoResp() == 13)) {
						//System.out.println("Los sintomas son iguales");
						if (count == 0) {
							//System.out.println("Contador = 0");
							condicion = " if(form.cCaracter_" + iCveRama2 + "" + vAReg2.getICveSintomaDep()
									+ ".value == " + vAReg2.getIIgualA() + " || " + "form.cCaracter_" + iCveRama2 + ""
									+ vAReg3.getICveSintomaDep() + ".value == " + vAReg3.getIIgualA();
							//System.out.println(condicion);
						} else {
							//System.out.println("Contador > 0");
							condicion = condicion + " || form.cCaracter_" + iCveRama2 + "" + vAReg3.getICveSintomaDep()
									+ ".value == " + vAReg3.getIIgualA();
							//System.out.println(condicion);
						}
						count++;
					}
				}

				if (count > 0) {
					condicion = condicion + "){";
				}
				//System.out.println(condicion);
				i = i + count;/// Evita se repitan los sintomas ya contabilizados en el anterior ciclo
				////////// Fin - Verifica que un sintoma no dependa de mas de una respuesta/////////////////	

				vAReg = (TVMEDAReg) CReglas.get(i);

				if (vAReg.getICveTpoResp() == 8 || vAReg.getICveTpoResp() == 13) {

					if (iCveServicio == 50 && iCveRama2 == 1
							&& (vAReg.getICveSintomaDep() == 17 || vAReg.getICveSintomaDep() == 27)) {
						out.println(" if(form.cCaracter_" + iCveRama2 + "" + vAReg.getICveSintomaDep() + ".value >= "
								+ vAReg.getIIgualA() + "){");
					} else {
						if (count > 0) {
							out.println(condicion);
						} else {
							out.println(" if(form.cCaracter_" + iCveRama2 + "" + vAReg.getICveSintomaDep()
									+ ".value == " + vAReg.getIIgualA() + "){");
						}
					}
					out.println("                    div = document.getElementById('fcCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                    div.style.display = '';");
					out.println("                    div = document.getElementById('f2cCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                    div.style.display = '';");
					out.println("            }else{");
					out.println("                div = document.getElementById('fcCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                div.style.display='none';");
					out.println("                div = document.getElementById('f2cCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                div.style.display='none';");
					out.println("  }");
				}
				if (vAReg.getICveTpoResp() == 1) {
					out.println(" if(form.lLogico_" + iCveRama2 + "" + vAReg.getICveSintomaDep() + "["
							+ vAReg.getIIgualA() + "].checked){");
					out.println("                    div = document.getElementById('fcCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                    div.style.display = '';");
					out.println("                    div = document.getElementById('f2cCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                    div.style.display = '';");
					out.println("            }else{");
					out.println("                div = document.getElementById('fcCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                div.style.display='none';");
					out.println("                div = document.getElementById('f2cCaracter_" + iCveRama2 + ""
							+ vAReg.getICveSintoma() + "');");
					out.println("                div.style.display='none';");
					out.println("  }");
				}
			}%>
            }
            /*function cerrar() {
                out.println("    if(form.cCaracter_12.value => 1 || form.cCaracter_12.value <= 1){ ");
                out.println("        div = document.getElementById('fcCaracter_13');  ");
                out.println("        div.style.display='none';  ");
                out.println("        div = document.getElementById('f2cCaracter_13');  ");
                out.println("        div.style.display='none';  ");
                out.println("    }  ");
            }*/
            
            
            
            
            


  // Agregado IVAN SANTIAGO  06/Oct/2010
  // Variable global para hacer referencia a la subventana
  var newPlacaToraxFile;

  // Agregado IVAN SANTIAGO  06/Oct/2010
  // Generar y llenar la nueva ventana
  function makenewPlacaToraxFile() {
      // asegurarse de que no se habia abierto
      if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
          //newPlacaToraxFile = window.open("./SubirArchivo.jsp", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
          newPlacaToraxFile = window.open("./SubirArchivo.jsp?"
                +"iCveExpediente="+<%=request.getParameter("iCveExpediente")%>
                +"&iNumExamen="+<%=request.getParameter("iNumExamen")%>
                +"&iCveServicio="+<%=iCveServicio%>
                +"&iCveRama="+<%=iCveRama%>
                +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,scrollbars=0,menubar=0");
          // retardar escritura hasta que la ventana exista en IE/Windows
          //setTimeout("writeToWindowPlacaToraxFile()", 50);
          newPlacaToraxFile.focus( );
      } else if (newPlacaToraxFile.focus) {
          // la ventana ya estaba abierta y con focus para traerla al frente
          newPlacaToraxFile.focus( );
      }
  }

  // Agregado IVAN SANTIAGO  07/Mayo/2012
  // Variable global para hacer referencia a la subventana para subir imagenes en un servicio 
  var newImagenServicioFile;

  // Agregado IVAN SANTIAGO  07/Mayo/2012
  // Generar y llenar la nueva ventana
  function makenewImagenServicioFile() {
      // asegurarse de que no se habia abierto
      if (!newImagenServicioFile || newImagenServicioFile.closed) {
          //newPlacaToraxFile = window.open("./SubirArchivo.jsp", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
          newImagenServicioFile = window.open("SubirArchivo.jsp?"
                +"iCveExpediente="+<%=request.getParameter("iCveExpediente")%>
                +"&iNumExamen="+<%=request.getParameter("iNumExamen")%>
                +"&iCveServicio="+<%=iCveServicio%>
                +"&iCveRama="+<%=iCveRama%>
                +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,scrollbars=0,menubar=0");
          // retardar escritura hasta que la ventana exista en IE/Windows
          //setTimeout("writeToWindowPlacaToraxFile()", 50);
          newImagenServicioFile.focus( );
      } else if (newImagenServicioFile.focus) {
          // la ventana ya estaba abierta y con focus para traerla al frente
          newImagenServicioFile.focus( );
      }
  }


    function showPlacasToraxFiles() {
      // asegurarse de que no se habia abierto
      if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
          //newPlacaToraxFile = window.open("./SubirArchivo.jsp", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
          newPlacaToraxFile = window.open("./MostrarArchivos.jsp?"
                +"iCveExpediente="+<%=request.getParameter("iCveExpediente")%>
                +"&iNumExamen="+<%=request.getParameter("iNumExamen")%>
                +"&iCveServicio="+<%=iCveServicio%>
                +"&iCveRama="+<%=iCveRama%>
                +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,resizable=yes,menubar=0,scrollbars=yes");
          // retardar escritura hasta que la ventana exista en IE/Windows
          //setTimeout("writeToWindowPlacaToraxFile()", 50);
          newPlacaToraxFile.focus( );
      } else if (newPlacaToraxFile.focus) {
          // la ventana ya estaba abierta y con focus para traerla al frente
          newPlacaToraxFile.focus( );
      }
  }
  function leerGET(){
    var cadGET = location.search.substr(1,location.search.length);
    var arrGET = cadGET.split("&");
    return arrGET;
  }
  function fSetValue(claveDocumento){
      //alert("Clave de documento "+claveDocumento);
  }


<%/////////////////////GENERACION DE ALERTAS///////////////////
			TDEXPResultado dEXPResultados = new TDEXPResultado();
			TVEXPResultado vEXPResultados;
			Vector vcEXPResultados = new Vector();
			Vector CAlertas = new Vector();
			TVMEDREGSIN vMEDREGSIN;
			String NumeroCaracteres = "";

			String filtro = "";
			String genero = "";

			try {
				genero = dPERDatos.SenFindBy(
						"Select cSexo from perdatos where icveexpediente = " + request.getParameter("iCveExpediente"));
			} catch (Exception e) {
				genero = "";
			}

			filtro = " where                      " + "    	    r.icveproceso = 1" + "		    and r.icvemdotrans = "
					+ iCveMdoTrans + "" + "		    and r.icvemotivo = " + iCveMotivo + ""
					+ "		    and r.iCveServicio =  " + iCveServicio + "" + "		    and r.iCveRama     =  "
					+ iCveRama2 + "" + "		    and (s.cGenero     =   '" + genero + "' OR s.cGenero='A')  ";
			try {
				//vcEXPResultados = dEXPResultados.findResultadoSintoma2(filtro);
				vcEXPResultados = dEXPResultados.findResultadoSintoma3(filtro);
			} catch (Exception e) {
				vcEXPResultados = new Vector();
			}

			filtro = " WHERE S.ICVESERVICIO = V.ICVESERVICIO AND " + "  S.ICVESERVICIO = R.ICVESERVICIO AND "
					+ "  S.ICVERAMA = R.ICVERAMA AND  " + "  S.ICVESERVICIO = M.ICVESERVICIO AND "
					+ " S.ICVERAMA = M.ICVERAMA AND  " + " S.ICVESINTOMA = M.ICVESINTOMA AND " + " S.iCveServicio = "
					+ iCveServicio + " And " + " S.iCveRama = " + iCveRama2 + " And " + " M.LActivo = 1 and "
					+ " S.iCveMdoTrans = 0 And " + " S.iCveCategoria = 0 And " + " S.RDINFO = 1 AND "
					+ " (M.cGenero     =   '" + genero + "' OR M.cGenero='A') AND " + " M.ICVETPORESP IN(3,5) ";
			try {
				CAlertas = dMEDREGSIN2.FindByAllDetAl(filtro);
			} catch (Exception e) {
				CAlertas = new Vector();
			}%>
    
    
  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
     
     if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
       cVMsg = "";
       //fValidaTodo2();
       //form.hdBoton.value = "activo";
       <%///////Valida Imagen Torax
			if (iCveServicio == 54 && iCveRama2 == 2) {%>
       var torax = "";
       
       if (form.lLogico_21[0].checked)
           torax = "0";
       if (form.lLogico_21[1].checked)
           torax = "0";
       if (form.lLogico_21[2].checked)
           torax = "0";
       if (form.lLogico_21[3].checked)
           torax = "1";
       if (form.lLogico_21[4].checked)
           torax = "2";
       if (form.lLogico_21[5].checked)
           torax = "3";
       if (form.lLogico_21[6].checked)
           torax = "4";
       if(torax == "")
           cVMsg = cVMsg + " -Seleccione una opcion de forma y volumen de Torax .\n "
       <%} //Fin valida imagen torax

			////Preguntas dependientes de este Servicio ///////////
			Vector vcMEDREGSINAlertas = new Vector();
			TVMEDREGSIN vMEDREGSINAlert;
			if (SCWhere3.length() > 1) {
				vcMEDREGSINAlertas = dMEDREGSINExt
						.FindByAllAlertasDependientes(" WHERE " + SCWhere3 + " AND S.LOBLIGATORIO = 1");
			} else {
				vcMEDREGSINAlertas = dMEDREGSINExt.FindByAllAlertasDependientes(" WHERE S.LOBLIGATORIO = 1");
			}

			//System.out.println("vcMEDREGSINAlertas = "+vcMEDREGSINAlertas.size());

			for (int i = 0; i < vcEXPResultados.size(); i++) {
				vEXPResultados = (TVEXPResultado) vcEXPResultados.get(i);

				//////////////// Alertas de preguntas dependientes 2017-05-04 LAS/////////////////////////////
				boolean dependienteAlert = false;
				boolean PadredependienteAlert = false;
				System.out.println("######################### vEXPResultados.getICveSintoma() / "
						+ vEXPResultados.getICveSintoma());

				for (int j = 0; j < vcMEDREGSINAlertas.size(); j++) {
					vMEDREGSINAlert = (TVMEDREGSIN) vcMEDREGSINAlertas.get(j);
					//System.out.println("For vcMEDREGSINAlertas SintomaDep = "+vMEDREGSINAlert.getSintomaDep());
					//System.out.println("For vcMEDREGSINAlertas Sintoma = "+vMEDREGSINAlert.getICveSintoma());

					//////// Validar no sea dependiente ///////////
					if (vEXPResultados.getICveServicio() == vMEDREGSINAlert.getICveServicio()
							&& vEXPResultados.getICveRama() == vMEDREGSINAlert.getICveRama()
							&& vEXPResultados.getICveSintoma() == vMEDREGSINAlert.getICveSintoma()) {
						dependienteAlert = true;
						System.out.println("//////// Validar no sea dependiente ///////////");
						System.out.println("Sintoma Dependiente = " + vMEDREGSINAlert.getSintomaDep());
						System.out.println("Sintoma getLDependiente = " + vMEDREGSINAlert.getLDependiente());
						System.out.println("vMEDREGSINAlert.getICveSintoma() = " + vMEDREGSINAlert.getICveSintoma());
					}

					/////// Validar si del sintoma dependen preguntas /////////////
					if (vEXPResultados.getICveServicio() == vMEDREGSINAlert.getICveServicio()
							&& vEXPResultados.getICveRama() == vMEDREGSINAlert.getICveRama()
							&& vEXPResultados.getICveSintoma() == vMEDREGSINAlert.getSintomaDep()) {
						PadredependienteAlert = true;
						dependienteAlert = false;
						System.out.println("/////// Validar si del sintoma dependen preguntas /////////////");
						System.out.println("vMEDREGSINAlert.getSintomaDep() = " + vMEDREGSINAlert.getSintomaDep());
						System.out.println("PadredependienteAlert= " + PadredependienteAlert);
					}
				}

				//////////////// Parte 1 Alertas de preguntas dependientes /////////////////////////////

				System.out.println("dependienteAlert = " + dependienteAlert);
				System.out.println("vEXPResultados.getLObligatorio() = " + vEXPResultados.getLObligatorio());
				if (dependienteAlert == false) {
					if (vEXPResultados.getLObligatorio() == 1) { // La respuesta es obligatoria y debe contener un valor.
						System.out.println("La respuesta es obligatoria y debe contener un valor");
						if (vEXPResultados.getICveTpoResp() == 1) {
							System.out.println("dependienteAlert-2.3");
							out.println("var Log" + iCveRama2 + "" + vEXPResultados.getICveSintoma() + " = \"0\";");
							out.println("if (form.lLogico_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ "[0].checked)");
							out.println("Log" + iCveRama2 + "" + vEXPResultados.getICveSintoma() + " = \"1\";");
							out.println("if (form.lLogico_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ "[1].checked)");
							out.println("Log" + iCveRama2 + "" + vEXPResultados.getICveSintoma() + " = \"1\";");
							out.println("if (Log" + iCveRama2 + "" + vEXPResultados.getICveSintoma() + " == \"0\")");
							out.println("       cVMsg = cVMsg + \" -" + vEXPResultados.getCPregunta() + ".\\n\" ");

							if (PadredependienteAlert) {
								System.out.println("PadredependienteAlert-2");
								boolean AlertaAsignada = false;
								for (int j = 0; j < vcMEDREGSINAlertas.size(); j++) {
									vMEDREGSINAlert = (TVMEDREGSIN) vcMEDREGSINAlertas.get(j);
									if (vEXPResultados.getICveServicio() == vMEDREGSINAlert.getICveServicio()
											&& vEXPResultados.getICveRama() == vMEDREGSINAlert.getICveRama()
											&& vEXPResultados.getICveSintoma() == vMEDREGSINAlert.getSintomaDep()) {
										if (!AlertaAsignada) {
											System.out.println("Escribiendo Regla-2 ");
											System.out.println(
													"vMEDREGSINAlert.getIIgualA() = " + vMEDREGSINAlert.getIIgualA());
											if (vMEDREGSINAlert.getIIgualA() == 0.0) {
												out.println("if (form.lLogico_" + iCveRama2 + ""
														+ vMEDREGSINAlert.getSintomaDep() + "[0].checked){");
												System.out.println("Escribiendo Regla-3 ");
											} else {
												out.println("if (form.lLogico_" + iCveRama2 + ""
														+ vMEDREGSINAlert.getSintomaDep() + "[1].checked){");
												System.out.println("Escribiendo Regla-4 ");
											}

											out.println("var Log" + iCveRama2 + "" + vMEDREGSINAlert.getICveSintoma()
													+ " = \"0\";");
											out.println("if (form.lLogico_" + iCveRama2 + ""
													+ vMEDREGSINAlert.getICveSintoma() + "[0].checked)");
											out.println("Log" + iCveRama2 + "" + vMEDREGSINAlert.getICveSintoma()
													+ " = \"1\";");
											out.println("if (form.lLogico_" + iCveRama2 + ""
													+ vMEDREGSINAlert.getICveSintoma() + "[1].checked)");
											out.println("Log" + iCveRama2 + "" + vMEDREGSINAlert.getICveSintoma()
													+ " = \"1\";");
											out.println("if (Log" + iCveRama2 + "" + vMEDREGSINAlert.getICveSintoma()
													+ " == \"0\")");
											out.println("       cVMsg = cVMsg + \" -" + vMEDREGSINAlert.getCPregunta()
													+ ".\\n\" ");
											out.println("}");
											AlertaAsignada = true;
										}

									}
								}

							}
						}

						if (vEXPResultados.getICveTpoResp() == 2 || vEXPResultados.getICveTpoResp() == 4
								|| vEXPResultados.getICveTpoResp() == 7 || vEXPResultados.getICveTpoResp() == 16) {
							out.println("if (form.cCaracter_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ ".value.length == 0){");
							out.println("       cVMsg = cVMsg + \" -" + vEXPResultados.getCPregunta() + ".\\n\" ");
							out.println("}");
						}

						if (vEXPResultados.getICveTpoResp() == 8 || vEXPResultados.getICveTpoResp() == 13) {
							out.println("if (form.cCaracter_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ ".selectedIndex==0)");
							out.println("       cVMsg = cVMsg + \" -" + vEXPResultados.getCPregunta() + ".\\n\" ");

							if (PadredependienteAlert) {
								for (int j = 0; j < vcMEDREGSINAlertas.size(); j++) {
									vMEDREGSINAlert = (TVMEDREGSIN) vcMEDREGSINAlertas.get(j);
									if (vEXPResultados.getICveServicio() == vMEDREGSINAlert.getICveServicio()
											&& vEXPResultados.getICveRama() == vMEDREGSINAlert.getICveRama()
											&& vEXPResultados.getICveSintoma() == vMEDREGSINAlert.getSintomaDep()) {

										int check = (int) Math.floor(vMEDREGSINAlert.getIIgualA());

										out.println("if (form.cCaracter_" + iCveRama2 + ""
												+ vMEDREGSINAlert.getSintomaDep() + ".selectedIndex==" + check + "){");
										out.println("if (form.cCaracter_" + iCveRama2 + ""
												+ vMEDREGSINAlert.getICveSintoma() + ".selectedIndex==0)");
										out.println("       cVMsg = cVMsg + \" -" + vMEDREGSINAlert.getCPregunta()
												+ ".\\n\" ");
										out.println("}");

									}
								}

							}

						}

						if (vEXPResultados.getICveTpoResp() == 5 || vEXPResultados.getICveTpoResp() == 3) {
							out.println("if (form.dValorI_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ ".value.length == 0)");
							out.println("       cVMsg = cVMsg + \" -" + vEXPResultados.getCPregunta() + ".\\n\" ");
						}

						if (vEXPResultados.getICveTpoResp() == 15) {
							out.println("if (form.iCvePais_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ ".selectedIndex < 1)");
							out.println("       cVMsg = cVMsg + \" -" + vEXPResultados.getCPregunta()
									+ " - SELECCIONA EL PA\u00C1�S.\\n\" ");
							out.println("if (form.iCveEstado_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ ".selectedIndex < 1)");
							out.println("       cVMsg = cVMsg + \" -" + vEXPResultados.getCPregunta()
									+ " - SELECCIONA EL ESTADO.\\n\" ");
							out.println("if (form.iCveMunicipio_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
									+ ".selectedIndex < 1)");
							out.println("       cVMsg = cVMsg + \" -" + vEXPResultados.getCPregunta()
									+ " - SELECCIONA EL MUNICIPIO.\\n\" ");
						}
					}

					//////Limita numero de caracteres  30 de Julio 2015
					if (vEXPResultados.getICveTpoResp() == 4 || vEXPResultados.getICveTpoResp() == 7
							|| vEXPResultados.getICveTpoResp() == 16) {
						NumeroCaracteres = NumeroCaracteres + "" + "function actualizaInfo_" + iCveRama2 + ""
								+ vEXPResultados.getICveSintoma() + "(maximoCaracteres) {"
								+ "    var elemento = document.getElementById(\"texto_" + iCveRama2 + ""
								+ vEXPResultados.getICveSintoma() + "\");"
								+ "    var info = document.getElementById(\"info_" + iCveRama2 + ""
								+ vEXPResultados.getICveSintoma() + "\");"
								+ "    if(elemento.value.length >= maximoCaracteres ) {"
								+ "        info.innerHTML = \"M\u00C1ximo \"+maximoCaracteres+\" caracteres\";"
								+ "    } else{"
								+ "        info.innerHTML = \"Puedes escribir hasta \"+(maximoCaracteres-elemento.value.length)+\" caracteres adicionales\";"
								+ "    }" + "}" + "function limita_" + iCveRama2 + "" + vEXPResultados.getICveSintoma()
								+ "(elEvento, maximoCaracteres) {" + "  var elemento = document.getElementById(\"texto_"
								+ iCveRama2 + "" + vEXPResultados.getICveSintoma() + "\");"
								// Obtener la tecla pulsada 
								+ "var evento = elEvento || window.event;"
								+ "var codigoCaracter = evento.charCode || evento.keyCode;"
								// Permitir utilizar las teclas con flecha horizontal
								+ "if(codigoCaracter == 37 || codigoCaracter == 39) {" + "    return true;" + "  }"
								// Permitir borrar con la tecla Backspace y con la tecla Supr.
								+ "if(codigoCaracter == 8 || codigoCaracter == 46) {" + "return true;"
								+ "}else if(elemento.value.length >= maximoCaracteres ) {" + "return false;" + "}else {"
								+ "    return true;" + "  }" + "}";
						///////////////////////////////////////////////////////
					}
					////////Valida Fechas ////////////////////////////////
					if (vEXPResultados.getICveTpoResp() == 14) {
						out.println(" cVMsg = cVMsg + fSinValor(form.dtFecha_" + iCveRama2 + ""
								+ vEXPResultados.getICveSintoma() + ",5,'" + vEXPResultados.getCPregunta()
								+ ":', true);");
					}
					///////////////////////////////////////////////////////

				} ////Dependiente

			}

			for (int i = 0; i < CAlertas.size(); i++) {
				vMEDREGSIN = (TVMEDREGSIN) CAlertas.get(i);
				System.out.println("Alertas no dependientes");
				if (vMEDREGSIN.getIMayorA() > 0) {
					out.println("if (form.dValorI_" + iCveRama2 + "" + vMEDREGSIN.getICveSintoma() + ".value > "
							+ vMEDREGSIN.getIMayorA() + "){");
					out.println("       cVMsg = cVMsg + \" -" + vMEDREGSIN.getCAlerta() + ".\\n\" ");
					out.println("}");
				}
				if (vMEDREGSIN.getIMenorA() > 0) {
					out.println("if (form.dValorI_" + iCveRama2 + "" + vMEDREGSIN.getICveSintoma() + ".value < "
							+ vMEDREGSIN.getIMenorA() + "){");
					out.println("       cVMsg = cVMsg + \" -" + vMEDREGSIN.getCAlerta() + ".\\n\" ");
					out.println("}");
				}
				if (vMEDREGSIN.getIIgualA() > 0) {
					out.println("if (form.dValorI_" + iCveRama2 + "" + vMEDREGSIN.getICveSintoma() + ".value == "
							+ vMEDREGSIN.getIIgualA() + "){");
					out.println("       cVMsg = cVMsg + \" -" + vMEDREGSIN.getCAlerta() + ".\\n\" ");
					out.println("}");
				}
				if (vMEDREGSIN.getCdscRegla().length() > 0) {%> var patt1=/^((\d+(\.\d*)?)|((\d*\.)?\d+))$/;
    	   		<%out.println("var str = form.dValorI_" + iCveRama2 + "" + vMEDREGSIN.getICveSintoma() + ".value;");
					out.println("if(patt1.test(str)) {");
					out.println("	try {");
					out.println("		part= str.split(\".\");");
					out.println("		if(part[1].length > " + vMEDREGSIN.getCdscRegla() + "){");
					out.println("       	cVMsg = cVMsg + \" - El s\u00EDntoma " + vMEDREGSIN.getCPregunta()
							+ " solo permite como m\u00E1ximo " + vMEDREGSIN.getCdscRegla() + " decimales.\\n\" ");
					out.println("		}");
					out.println("	} catch(e){");
					out.println("       	cVMsg = cVMsg + \"\"");
					out.println("	}");
					out.println("}");
				}
			}

			////Diabetes Mellitus Laboratorio de Analisis
			System.out.println(iCveServicio + "-" + iCveRama2);
			if (iCveServicio == 2 && iCveRama2 == 4) {
				System.out.println("En el servicio " + iCveServicio + " y la rama = " + iCveRama2);
				/////OPCION A
				out.println("if(form.dValorI_41.value >= 126){");
				out.println("	if(form.dValorI_410.value =='' || form.dValorI_410.value < 0){");
				out.println("       cVMsg = cVMsg + \" - HEMOGLOBINA GLUCOSILADA (HbA1C)\\n\" ");
				out.println("	}");
				out.println("}");
				////OPCION B
				RDiagnosticoRecomendacionesAuto rDiag = new RDiagnosticoRecomendacionesAuto();
				//Diagnostico Diabetes Mellitus
				boolean DiagnosticoDiabetesMellitusAntecedentesPersPat = false;
				DiagnosticoDiabetesMellitusAntecedentesPersPat = rDiag.DiagnosticoDiabetesMellitusAntecedentesPersPat(
						request.getParameter("iCveExpediente"), request.getParameter("iNumExamen"));
				if (!DiagnosticoDiabetesMellitusAntecedentesPersPat) {
					//out.println("if(form.dValorI_41.value >= 100 && form.dValorI_41.value <= 125){");
					out.println("if(form.dValorI_41.value > 100 ){");
					out.println("	if(form.dValorI_410.value =='' || form.dValorI_410.value < 0){");
					out.println("       cVMsg = cVMsg + \" - HEMOGLOBINA GLUCOSILADA (HbA1C)\\n\" ");
					out.println("	}");
					out.println("	if(form.dValorI_454.value =='' || form.dValorI_454.value < 0){");
					out.println("       cVMsg = cVMsg + \" - GLUCOSA EN ORINA\\n\" ");
					out.println("	}");
					out.println("	if(form.dValorI_47.value =='' || form.dValorI_47.value < 0){");
					out.println("       cVMsg = cVMsg + \" - GLUCOSA POST-CARGA\\n\" ");
					out.println("	}");
					out.println("}"); //Se omitio la siguiente regla por peticion de la Dra. Jessica Chaparro el dia 27 de Oct 2017
				}

				// REGLAS PARA DETERMINAR OTROS CAMPOS QUE NO SON OBLIGATORIOS COMO OBLIGATORIOS
				// 21 De Marzo del 2017
				RServicioLaboratorioDeAnalisisClinicos RSLab = new RServicioLaboratorioDeAnalisisClinicos();
				out.println(RSLab.ObligatoriosLaboratorio(request.getParameter("iCveExpediente"),
						request.getParameter("iNumExamen")));
			}%>
       
       if(cVMsg!=""){
         alert("FAVOR DE CONTESTAR LAS SIGUIENTES PREGUNTAS: \n" + cVMsg);
         return false;
       }
       else if(!confirm("�Est\u00E1 Seguro de Guardar la Informaci\u00F3n?")){
        			return false;
        	}<%if (SVSLXi) {%>else{
        		//alert("se guardara");
        		liberaMem();
        		//alert("se guardo");
        	}<%}%>
    }

    if (form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
      if (form.iCveUniMed)
        cVMsg = cVMsg + fSinValor(form.iCveUniMed,3,'Unidad m\u00E9dica', true);
      if (form.iCveModulo)
        cVMsg = cVMsg + fSinValor(form.iCveModulo,3,'M\u00F3dulo', true);
      if (form.iCveServicio)
        cVMsg = cVMsg + fSinValor(form.iCveServicio,0,'Servicio', false);
      if (form.iCveExpediente)
        cVMsg = cVMsg + fSinValor(form.iCveExpediente,3,'Expediente', false);
        if (cVMsg != ''){
          alert("Datos no V\u00E1lidos: \n" + cVMsg);
          return false;
        }
    }
     return true;
   }
    
  
  /////Agregado el 22 de julio 2015 por AG SA
  function llenaSLT(opc,val1,val2,val3,objDes){
      if(objDes!=''){
          if(objDes.name!=''){
              objDes.length=1;
              objDes[0].text="Cargando Datos...";
              window.parent.FRMOtroPanel.location="pg070102021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
          } else {
              alert("Debe seleccionar un dato valido!");
              objDes.length=0;
              objDes.length=1;
              return false;
          }
      } else {
          window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
      }

      
  }
  
<%////Restringe numero de caracteres 30 de julio 2015
			out.println(NumeroCaracteres);%>
  
  
</SCRIPT>

<%
	///CODIGO DEL CUADRO FLOTANTE///
%>
<style>
#cuadroFlotante {
	position: absolute;
	right: 20px;
	top: 0;
	line-height: 15px;
	font-weight: bold;
	text-align: left;
	padding: 20px;
	border-bottom-style: solid;
	border-left-style: solid;
}

#cuadroFlotante2 {
	/*position:absolute;*/
	right: 20px;
	top: 0;
	line-height: 15px;
	font-weight: bold;
	text-align: left;
	padding: 20px;
	border-bottom-style: solid;
	border-left-style: solid;
}
</style>
<SCRIPT LANGUAGE="JavaScript"
	src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.6.4.min.js"></SCRIPT>
<%
	///FIN DEL CODIGO DEL CUADRO FLOTANTE///
%>



<head>
<meta name="Autor" content="<%=vParametros.getPropEspecifica("Autor")%>">

	<META content="100000682718088" property="fb:admins">
		<LINK rel="stylesheet" type="text/css"
			href="<%=vParametros.getPropEspecifica("RutaCSS")%>css.css">
			<LINK rel="stylesheet" type="text/css"
				href="<%=vParametros.getPropEspecifica("RutaCSS")%>demo.css">
				<LINK rel="stylesheet" type="text/css"
					href="<%=vParametros.getPropEspecifica("RutaCSS")%>adipoli.css">
					<LINK rel="shortcut icon" href="http://jobyj.in/favicon.ico">
						<SCRIPT type="text/javascript"
							src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.7.1.js"></SCRIPT>

						<SCRIPT type="text/javascript"
							src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery.adipoli.min.js"></SCRIPT>
						<!--
                        <SCRIPT type="text/javascript">

                                    $(function(){
                                        $('.row1').adipoli({
                                            'startEffect' : 'normal',
                                            'hoverEffect' : 'popout'
                                        });
                                        $('.row2').adipoli({
                                            'startEffect' : 'overlay',
                                            'hoverEffect' : 'sliceDown'
                                        });
                                        $('.row3').adipoli({
                                            'startEffect' : 'transparent',
                                            'hoverEffect' : 'boxRandom'
                                        });
                                        $('.row4').adipoli({
                                            'startEffect' : 'overlay',
                                            'hoverEffect' : 'foldLeft'
                                        });
                                    $('.row5').adipoli({
                                        'startEffect' : 'transparent',
                                        'hoverEffect' : 'boxRainGrowReverse'
                                    });
                                    $('.row6').adipoli({
                                        'startEffect' : 'grayscale',
                                        'hoverEffect' : 'normal'
                                    });
                                });

                                </SCRIPT>  -->



						<script
							src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery.min.js"
							type="text/javascript"></script>
						<script type="text/javascript">

                        jQuery.noConflict()

                        jQuery.imageMagnify={
                                dsettings: {
                                        magnifyby: 4, //default increase factor of enlarged image
                                        duration: 500, //default duration of animation, in millisec
                                        imgopacity: 0.02//opacify of original image when enlarged image overlays it
                                },
                                cursorcss: 'url(magnify.cur), -moz-zoom-in', //Value for CSS's 'cursor' attribute, added to original image
                                zIndexcounter: 100,

                                refreshoffsets:function($window, $target, warpshell){
                                        var $offsets=$target.offset()
                                        var winattrs={x:$window.scrollLeft(), y:$window.scrollTop(), w:$window.width(), h:$window.height()}
                                        warpshell.attrs.x=$offsets.left //update x position of original image relative to page
                                        warpshell.attrs.y=$offsets.top
                                        warpshell.newattrs.x=winattrs.x+winattrs.w/2-warpshell.newattrs.w/2
                                        warpshell.newattrs.y=winattrs.y+winattrs.h/2-warpshell.newattrs.h/2
                                        if (warpshell.newattrs.x<winattrs.x+5){ //no space to the left?
                                                warpshell.newattrs.x=winattrs.x+5	
                                        }
                                        else if (warpshell.newattrs.x+warpshell.newattrs.w > winattrs.x+winattrs.w){//no space to the right?
                                                warpshell.newattrs.x=winattrs.x+5
                                        }
                                        if (warpshell.newattrs.y<winattrs.y+5){ //no space at the top?
                                                warpshell.newattrs.y=winattrs.y+5
                                        }
                                },

                                magnify:function($, $target, options){
                                        var setting={} //create blank object to store combined settings
                                        var setting=jQuery.extend(setting, this.dsettings, options)
                                        var attrs=(options.thumbdimensions)? {w:options.thumbdimensions[0], h:options.thumbdimensions[1]} : {w:$target.outerWidth(), h:$target.outerHeight()}
                                        var newattrs={}
                                        newattrs.w=(setting.magnifyto)? setting.magnifyto : Math.round(attrs.w*setting.magnifyby)
                                        newattrs.h=(setting.magnifyto)? Math.round(attrs.h*newattrs.w/attrs.w) : Math.round(attrs.h*setting.magnifyby)
                                        $target.css('cursor', jQuery.imageMagnify.cursorcss)
                                        if ($target.data('imgshell')){
                                                $target.data('imgshell').$clone.remove()
                                                $target.css({opacity:1}).unbind('click.magnify')
                                        }	
                                        var $clone=$target.clone().css({position:'absolute', left:0, top:0, visibility:'hidden', border:'1px solid gray', cursor:'pointer'}).appendTo(document.body)
                                        $clone.data('$relatedtarget', $target) //save $target image this enlarged image is associated with
                                        $target.data('imgshell', {$clone:$clone, attrs:attrs, newattrs:newattrs})
                                        $target.bind('click.magnify', function(e){ //action when original image is clicked on
                                                var $this=$(this).css({opacity:setting.imgopacity})
                                                var imageinfo=$this.data('imgshell')
                                                jQuery.imageMagnify.refreshoffsets($(window), $this, imageinfo) //refresh offset positions of original and warped images
                                                var $clone=imageinfo.$clone
                                                $clone.stop().css({zIndex:++jQuery.imageMagnify.zIndexcounter, left:imageinfo.attrs.x, top:imageinfo.attrs.y, width:imageinfo.attrs.w, height:imageinfo.attrs.h, opacity:0, visibility:'visible', display:'block'})
                                                .animate({opacity:1, left:imageinfo.newattrs.x, top:imageinfo.newattrs.y, width:imageinfo.newattrs.w, height:imageinfo.newattrs.h}, setting.duration,
                                                function(){ //callback function after warping is complete
                                                        //none added		
                                                }) //end animate
                                        }) //end click
                                        $clone.click(function(e){ //action when magnified image is clicked on
                                                var $this=$(this)
                                                var imageinfo=$this.data('$relatedtarget').data('imgshell')
                                                jQuery.imageMagnify.refreshoffsets($(window), $this.data('$relatedtarget'), imageinfo) //refresh offset positions of original and warped images
                                                $this.stop().animate({opacity:0, left:imageinfo.attrs.x, top:imageinfo.attrs.y, width:imageinfo.attrs.w, height:imageinfo.attrs.h},  setting.duration,
                                                function(){
                                                        $this.hide()
                                                        $this.data('$relatedtarget').css({opacity:1}) //reveal original image
                                                }) //end animate
                                        }) //end click
                                }
                        };

                        jQuery.fn.imageMagnify=function(options){
                                var $=jQuery
                                return this.each(function(){ //return jQuery obj
                                        var $imgref=$(this)
                                        if (this.tagName!="IMG")
                                                return true //skip to next matched element
                                        if (parseInt($imgref.css('width'))>0 && parseInt($imgref.css('height'))>0 || options.thumbdimensions){ //if image has explicit width/height attrs defined
                                                jQuery.imageMagnify.magnify($, $imgref, options)
                                        }
                                        else if (this.complete){ //account for IE not firing image.onload
                                                jQuery.imageMagnify.magnify($, $imgref, options)
                                        }
                                        else{
                                                $(this).bind('load', function(){
                                                        jQuery.imageMagnify.magnify($, $imgref, options)
                                                })
                                        }
                                })
                        };

                        jQuery.fn.applyMagnifier=function(options){ //dynamic version of imageMagnify() to apply magnify effect to an image dynamically
                                var $=jQuery
                                return this.each(function(){ //return jQuery obj
                                        var $imgref=$(this)
                                        if (this.tagName!="IMG")
                                                return true //skip to next matched element

                                })	

                        };

                        jQuery(document).ready(function($){
                                var $targets=$('.magnify')
                                $targets.each(function(i){
                                        var $target=$(this)
                                        var options={}
                                        if ($target.attr('data-magnifyto'))
                                                options.magnifyto=parseFloat($target.attr('data-magnifyto'))
                                        if ($target.attr('data-magnifyby'))
                                                options.magnifyby=parseFloat($target.attr('data-magnifyby'))
                                        if ($target.attr('data-magnifyduration'))
                                                options.duration=parseInt($target.attr('data-magnifyduration'))
                                        $target.imageMagnify(options)
                                })
                                var $triggers=$('a[rel^="magnify["]')
                                $triggers.each(function(i){
                                        var $trigger=$(this)
                                        var targetid=$trigger.attr('rel').match(/\[.+\]/)[0].replace(/[\[\]']/g, '') //parse 'id' from rel='magnify[id]'
                                        $trigger.data('magnifyimageid', targetid)
                                        $trigger.click(function(e){
                                                $('#'+$(this).data('magnifyimageid')).trigger('click.magnify')
                                                e.preventDefault()
                                        })
                                })
                        })

                        </script>
						<script type="text/javascript">(function(){if(-1!=navigator.userAgent.indexOf("Mobile")&&-1!=navigator.userAgent.indexOf("WebKit")&&-1==navigator.userAgent.indexOf("iPad")||-1!=navigator.userAgent.indexOf("Opera Mini")){var a;a:{var b=window.location.href,c=b.split("?");switch(c.length){case 1:a=b+"?m=1";break a;case 2:a=0<=c[1].search("(^|&)m=")?null:b+"&m=1";break a;default:a=null}}a&&window.location.replace(a)};})();
                        </script>


						<style type="text/css">
/* Codigo correspondente al pie de pagina */
#footer {
	background-color: White;
	height: 100px;
}

.Estilo21 {
	color: black;
	font-family: Verdana;
	font-size: 9pt;
}
</style>

						<%
							///Llamado a funicones extra por servicio
							String FuncionO = "";
							if (iCveServicio == 65) {
								FuncionO = "fCardio();";
							}
							if (iCveServicio == 48 && iCveRama2 == 16) {
								FuncionO = "fSaos();";
							}
						%>
					
</head>
<%
	new TCabeceras(pageContext).TCabecerasCat(vEntorno,
			vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
%>
<link rel="stylesheet"
	href="<%=vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>"
	TYPE="text/css">

	<body bgcolor="<%=vParametros.getPropEspecifica("ColorFondoPagina")%>"
		topmargin="0" leftmargin="0"
		onLoad="<%=FuncionO%><%=vEntorno.getOnLoad()%>">

		<form method="<%=vEntorno.getMetodoForm()%>"
			action="<%=vEntorno.getActionForm()%>">
			<!--MENSAJES -->
			<input type="hidden" name="hdLCondicion"
				value="<%if (request.getParameter("hdLCondicion") != null)
				out.print(request.getParameter("hdLCondicion"));%>">
				<input type="hidden" name="hdLOrdenar"
				value="<%if (request.getParameter("hdLOrdenar") != null)
				out.print(request.getParameter("hdLOrdenar"));%>">
					<input type="hidden" name="FILNumReng" value="500"> <input
						type="hidden" name="hdICveProceso"
						value="<%if (request.getParameter("iCveProceso") != null)
				out.print(request.getParameter("iCveProceso"));%>">
							<input type="hidden" name="hdICveServicio"
							value="<%if (request.getParameter("iCveServicio") != null)
				out.print(request.getParameter("iCveServicio"));%>">
								<input type="hidden" name="hdiCveExpediente"
								value="<%if (request.getParameter("iCveExpediente") != null)
				out.print(request.getParameter("iCveExpediente"));%>">
									<input type="hidden" name="hdiNumExamen"
									value="<%if (request.getParameter("iNumExamen") != null)
				out.print(request.getParameter("iNumExamen"));%>">



										<%
											if (bs != null) {
												cPosicion = Integer.toString(bs.pageNo());
											} else {
												cPosicion = request.getParameter("hdRowNum");
											}
										%> <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
											<input type="hidden" name="hdCampoClave" value="<%=cClave%>">
												<input type="hidden" name="hdCPropiedad" value=""> <input
													type="hidden" name="iCvePerfil"
													value="<%=request.getParameter("iCvePerfil")%>"> <input
														type="hidden" name="iCveUniMed"
														value="<%=request.getParameter("iCveUniMed")%>"> <input
															type="hidden" name="iCveModulo"
															value="<%=request.getParameter("iCveModulo")%>">
																<input type="hidden" name="iCvePersonal"
																value="<%=request.getParameter("iCvePersonal")%>">
																	<input type="hidden" name="iCveExpediente"
																	value="<%=request.getParameter("iCveExpediente")%>">
																		<input type="hidden" name="iCveServicio"
																		value="<%=request.getParameter("iCveServicio")%>">
																			<input type="hidden" name="iNumExamen"
																			value="<%=request.getParameter("iNumExamen")%>">
																				<input type="hidden" name="hdOIM"
																				value="<%=request.getParameter("hdOIM")%>">
																					<input type="hidden" name="hdOIC"
																					value="<%=request.getParameter("hdOIC")%>">
																						<input type="hidden" name="hdOIT"
																						value="<%=request.getParameter("hdOIT")%>">
																							<input type="hidden" name="hdOIN"
																							value="<%=request.getParameter("hdOIN")%>">
																								<input type="hidden" name="hdODM"
																								value="<%=request.getParameter("hdODM")%>">
																									<input type="hidden" name="hdODC"
																									value="<%=request.getParameter("hdODC")%>">
																										<input type="hidden" name="hdODT"
																										value="<%=request.getParameter("hdODT")%>">
																											<input type="hidden" name="hdODN"
																											value="<%=request.getParameter("hdODN")%>">
																												<input type="hidden" name="hdGOido"
																												value="<%=request.getParameter("hdGOido")%>">
																													<%
																														////////////OBTENCION DE PARAMETROS ADICIONALES//////////////////////
																														/////DESCRIPCIÓN UNIDAD MEDICA//////
																														String UniMed = "";
																														try {
																															UniMed = dPERDatos.SenFindBy(
																																	"SELECT CDSCUNIMED FROM GRLUNIMED WHERE ICVEUNIMED = " + request.getParameter("iCveUniMed"));
																														} catch (Exception e) {
																															UniMed = "";
																														}
																														/////DESCRIPCIÓN MODULO//////
																														String ModUniMed = "";
																														try {
																															ModUniMed = dPERDatos.SenFindBy(
																																	"SELECT CDSCMODULO FROM GRLMODULO  WHERE ICVEUNIMED = " + request.getParameter("iCveUniMed")
																																			+ " AND ICVEMODULO = " + request.getParameter("iCveModulo"));
																														} catch (Exception e) {
																															ModUniMed = "";
																														}
																														/////DESCRIPCIÓN MOTIVO DE EXAMEN//////
																														String ExpMotExa = "";
																														try {
																															ExpMotExa = dPERDatos.SenFindBy(
																																	"SELECT M.CDSCMOTIVO FROM EXPEXAMCAT AS C, GRLMOTIVO AS M WHERE C.ICVEMOTIVO = M.ICVEMOTIVO AND C.ICVEEXPEDIENTE = "
																																			+ request.getParameter("iCveExpediente") + " AND INUMEXAMEN = "
																																			+ request.getParameter("iNumExamen"));
																														} catch (Exception e) {
																															ExpMotExa = "";
																														}

																														if (request.getParameter("iCveProceso") != null && request.getParameter("iCveProceso").length() > 0) {
																													%> <input type="hidden" name="iCveProceso"
																													value="<%=request.getParameter("iCveProceso")%>">
																														<%
																															} else {
																														%> <input type="hidden" name="iCveProceso"
																														value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
																															<%
																																}
																															%> <input type="hidden"
																															name="tpoBusqueda"
																															value="<%=request.getParameter("tpoBusqueda")%>">
																																<input type="hidden" name="iCveUsuario"
																																value="<%=vUsuario.getICveusuario()%>">
																																	<input type="hidden" name="SVSLXi"
																																	value="<%=SVSLXi%>"> <%
 	int ramaInicial = Integer.parseInt(request.getParameter("ramaInicial"));
 	ramaInicial++;
 %> <input type="hidden" name="ramaInicial" value="<%=ramaInicial%>">
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
																																					<td><input type="hidden"
																																						name="hdBoton" value=""></td>
																																					<td valign="top">
																																						<table border="1"
																																							class="ETablaInfo" align="center"
																																							cellspacing="0" cellpadding="3">
																																							<%
																																								// Inicio de Datos
																																							%>



																																							<%
																																								///CODIGO DEL CUADRO FLOTANTE/// 
																																									if (SVSLXi) {
																																							%>
																																							<div class="ETablaT"
																																								id="cuadroFlotante">
																																								Importar datos <br>_______________
																																									<div class="ETablaInfo"
																																										id="cuadroFlotante2">
																																										<center> <a
																																											class="EAncla" name=""
																																											href="JavaScript:lee_json('2');"
																																											onMouseOut="self.status='';return true;"
																																											onMouseOver="self.status='Buscar Personal';return true;"
																																											title="Importar Datos"> <img
																																											src="<%=vParametros.getPropEspecifica("RutaImgServer")%>spotvital.jpg"
																																											alt="" width="93"
																																											height="160" />
																																										</a> <br>
																																									</div>
																																							</div>
																																							<input type="hidden" name="Hoy"
																																								value="<%=cHoy%>"> <input
																																								type="hidden"
																																								name="DatosAdicionales" value="">
																																									<%
																																										}
																																											///FIN DEL CODIGO DEL CUADRO FLOTANTE///
																																									%>



																																									<tr>
																																										<td class="ETablaT"><img
																																											src="<%=vParametros.getPropEspecifica("RutaImgServer")%>lsct.jpg"
																																											width="173" height="115" /></td>
																																										<td class="ETablaT"
																																											colspan="5"><p>
																																												EXPEDIENTE CL&Iacute;NICO
																																												ELECTR&Oacute;NICO NO.
																																												<%=request.getParameter("iCveExpediente")%>
																																												DE LA DIRECCI&Oacute;N
																																											</p>
																																											<p>GENERAL DE
																																												PROTECCI&Oacute;N Y MEDICINA
																																												PREVENTIVA EN EL TRANSPORTE.</p></td>
																																									</tr>

																																									<tr>
																																										<td class="ETablaT"
																																											colspan="6">Datos del
																																											Usuario</td>
																																									</tr> <%
 	// inicializador de datos
 		TVPERDatos personal = clsConfig.getPersonal();
 		long edad = clsConfig.getEdadPersonal();
 		HashMap hm = vUsuario.getVServicios(iCveUniMed, iCveProceso, iCveModulo);
 		String servicio = (String) hm.get("" + iCveServicio); // descripción del servicio
 		TVEXPRama voRama = clsConfig.getCurrentRama();
 		TDEXPResultado dEXPResultado = new TDEXPResultado();
 		TVEXPResultado vEXPResultado;
 		Vector vcEXPResultado = new Vector();

 		TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
 		TVMEDRespSint vMEDRespSint = new TVMEDRespSint();

 		vcEXPResultado = dEXPResultado.FindServAdicionales(" where EXPResultado.iCveExpediente = "
 				+ request.getParameter("iCveExpediente") + " and EXPResultado.iNumExamen = "
 				+ request.getParameter("iNumExamen") + " and EXPResultado.iCveServicio = "
 				+ vParametros.getPropEspecifica("Signos Vitales"));

 		String rama = "";
 		if (voRama != null) {
 			rama = voRama.getCDscRama();
 		} else {
 			//  pageContext.forward("pg070104031.jsp");
 		}

 		// -- Escribe en EXPDictamenServ para los servicios que no hacen diagnósticos y/o recomendaciones

 		TDMEDServicio dMEDServicio = new TDMEDServicio();
 		TVMEDServicio vMEDServicio = new TVMEDServicio();
 		TDEXPRama dEXPRama = new TDEXPRama();
 		Vector vcMEDServicio = new Vector();
 		////Try catch para imprimir el error
 		try {
 			vcMEDServicio = dMEDServicio
 					.FindByAll(" where iCveServicio = " + request.getParameter("iCveServicio"), "");
 			if (vcMEDServicio.size() > 0)
 				for (int i = 0; i < vcMEDServicio.size(); i++)
 					vMEDServicio = (TVMEDServicio) vcMEDServicio.get(i);

 			int iDictamina = vMEDServicio.getLReqDiag();

 			if (clsConfig.ultimaRama) {
 				if (vMEDServicio.getICveServicio() == 5) {// condicion oftalmologia
 					int regreso = 0;
 					String SQL = "SELECT COUNT(ICVEEXPEDIENTE) FROM EXPRAMA WHERE ICVEEXPEDIENTE = "
 							+ request.getParameter("iCveExpediente") + " AND ICVESERVICIO = "
 							+ vMEDServicio.getICveServicio() + " AND INUMEXAMEN =  "
 							+ request.getParameter("iNumExamen") + " AND LCONCLUIDO = 0";
 					regreso = dEXPRama.RegresaInt(SQL);
 					if (iDictamina == 1) {
 						if (regreso == 0) {
 							//pageContext.forward("pg070104031.jsp?hdBoton=Actual");
 %> <script type="text/javascript">
                        fIrVerExamen(0, 0, 'pg070104071.jsp');
                     </script> <%
 	} else {
 							//pageContext.forward("pg070104010.jsp?hdBoton=Actual");
 %> <script type="text/javascript">
                        fIrVerExamen(0, 0, 'pg070104071.jsp');
                     </script> <%
 	}
 					} else if (request.getParameter("tpoBusqueda").equals("variosMedicos")) {
 						//clsConfig.GuardarDiagRec();
 						//pageContext.forward("pg070104010.jsp?hdBoton=Actual");
 %> <script type="text/javascript">
                        fIrVerExamen(0, 0, 'pg070104071.jsp');
                     </script> <%
 	} else if (request.getParameter("tpoBusqueda").equals("unMedico")) {
 						//clsConfig.GuardarDiagRec();
 						//pageContext.forward("pg070104020.jsp?hdBoton=Actual");
 %> <script type="text/javascript">
                        fIrVerExamen(0, 0, 'pg070104071.jsp');
                     </script> <%
 	}
 				} else {
 					if (iDictamina == 1) {
 %> <script type="text/javascript">
                     //fIr(0, 0, 'pg070104031.jsp?hdBoton=Actual');
                     fIrVerExamen(0, 0, 'pg070104071.jsp');
                </script> <%
 	//response.isCommitted();
 						//response.resetBuffer();
 						//pageContext.forward("pg070104031.jsp?hdBoton=Actual");
 					} else if (request.getParameter("tpoBusqueda").equals("variosMedicos")) {
 						//clsConfig.GuardarDiagRec();
 						//pageContext.forward("pg070104010.jsp?hdBoton=Actual");
 %> <script type="text/javascript">
                        fIrVerExamen(0, 0, 'pg070104071.jsp');
                     </script> <%
 	} else if (request.getParameter("tpoBusqueda").equals("unMedico")) {
 						//clsConfig.GuardarDiagRec();
 						//pageContext.forward("pg070104020.jsp?hdBoton=Actual");
 %> <script type="text/javascript">
                        fIrVerExamen(0, 0, 'pg070104071.jsp');
                     </script> <%
 	}
 				} //condicion oftalmologia
 			}

 			// -- Termina la escritura en EXPDictamenServ
 %> <input type="hidden" name="iCveRama"
																																									value="<%=(voRama == null ? "" : voRama.getICveRama() + "")%>">
																																										<input type="hidden"
																																										name="iCveServicio"
																																										value="<%=iCveServicio%>">
																																											<!-- Personal -->
																																											<tr>
																																												<td class="EEtiqueta"
																																													colspan="3">Proceso:</td>
																																												<td class="ETabla"
																																													colspan="3"><%=clsConfig.getProceso("" + iCveProceso)%></td>
																																											</tr>

																																											<tr>
																																												<td class="EEtiqueta"
																																													colspan="1">Nombre:</td>
																																												<td class="ETabla"
																																													colspan="2"><%=personal.getCApPaterno() + " " + personal.getCApMaterno() + " " + personal.getCNombre()%></td>
																																												<td class="EEtiqueta"
																																													colspan="2">RFC:</td>
																																												<td class="ETabla"
																																													colspan="1"><%=personal.getCRFC()%>
																																												</td>
																																											</tr>
																																											<tr>
																																												<td class="EEtiqueta"
																																													colspan="1">Edad:</td>
																																												<td class="ETabla"
																																													colspan="2"><%=edad%>&nbsp;a&ntilde;os
																																													<input type="hidden"
																																													name="edad"
																																													value="<%=edad%>"></td>
																																												<td class="EEtiqueta"
																																													colspan="2">Sexo:</td>
																																												<td class="ETabla"
																																													colspan="1"><%=personal.getCSexo().equalsIgnoreCase("M") ? "Hombre" : "Mujer"%></td>
																																											</tr>
																																											<tr>
																																												<td class="EEtiqueta"
																																													colspan="1">Unidad
																																													M&eacute;dica:</td>
																																												<td class="ETabla"
																																													colspan="2"><%=UniMed%>
																																												</td>
																																												<td class="EEtiqueta"
																																													colspan="2">Modulo:</td>
																																												<td class="ETabla"
																																													colspan="1"><%=ModUniMed%>
																																												</td>
																																											</tr>
																																											<tr>
																																												<td class="EEtiqueta"
																																													colspan="1">Motivo:</td>
																																												<td class="ETabla"
																																													colspan="2"><%=ExpMotExa%>
																																												</td>
																																												<td class="EEtiqueta"
																																													colspan="2">&nbsp;</td>
																																												<td class="ETabla"
																																													colspan="1">&nbsp;<%
																																													//Sin valor y etiqueta asignado
																																												%>
																																												</td>
																																											</tr> <%
 	if (vParametros.getPropEspecifica("Signos Vitales")
 					.compareTo(request.getParameter("iCveServicio")) != 0) {
 %>
																																											<tr>
																																												<td class="ETablaT"
																																													colspan="6">Signos
																																													Vitales</td>
																																											</tr>
																																											<tr>
																																												<%
																																													int y = 0;
																																																if (vcEXPResultado.size() > 0) {
																																																	out.println("<tr>");
																																																	for (int z = 0; z < vcEXPResultado.size(); z++) {
																																																		vEXPResultado = (TVEXPResultado) vcEXPResultado.get(z);
																																																		String sCadenaSinBlancos = "";
																																																		String sTexto = vEXPResultado.getCPregunta();
																																																		for (int x = 0; x < sTexto.length(); x++) {
																																																			if (sTexto.charAt(x) != ' ')
																																																				sCadenaSinBlancos += sTexto.charAt(x);
																																																		}

																																																		out.println("<td class='EEtiqueta'>" + vEXPResultado.getCPregunta() + "</td>");
																																																		if (vEXPResultado.getICveTpoResp() == 1 || vEXPResultado.getICveTpoResp() == 4) {
																																																			if (vEXPResultado.getLLogico() == 0)
																																																				out.println("<td class='ECampo'>NO</td>");
																																																			else
																																																				out.println("<td class='ECampo'>SI</td>");
																																																		}
																																																		if (vEXPResultado.getICveTpoResp() == 2) {
																																																			if (vEXPResultado.getCCaracter() != null) {
																																																				out.println("<td class='ECampo'>" + vEXPResultado.getCCaracter() + "</td>");
																																																				out.println("<input type=\"hidden\" name=\"" + sCadenaSinBlancos + "\" value=\""
																																																						+ vEXPResultado.getCCaracter() + "\">");
																																																			} else
																																																				out.println("<td class='ECampo'>&nbsp;</td>");
																																																		}
																																																		if (vEXPResultado.getICveTpoResp() == 3) {
																																																			if (vEXPResultado.getDValorIni() != 0) {
																																																				out.println("<td class='ECampo'>" + vEXPResultado.getDValorIni() + "</td>");
																																																				out.println("<input type=\"hidden\" name=\"" + sCadenaSinBlancos + "\" value=\""
																																																						+ vEXPResultado.getDValorIni() + "\">");
																																																			} else
																																																				out.println("<td class='ECampo'>&nbsp;</td>");
																																																		}
																																																		if (vEXPResultado.getICveTpoResp() == 5) {
																																																			if (vEXPResultado.getDValorIni() != 0) {
																																																				out.println("<td class='ECampo'>" + vEXPResultado.getDValorIni() + " - "
																																																						+ vEXPResultado.getDValorFin() + "</td>");
																																																				out.println("<input type=\"hidden\" name=\"" + sCadenaSinBlancos + "\" value=\""
																																																						+ vEXPResultado.getDValorIni() + " - " + vEXPResultado.getDValorFin()
																																																						+ "\">");
																																																			} else
																																																				out.println("<td class='ECampo'>&nbsp;</td>");
																																																		}
																																																		y += 1;
																																																		if (y == 3) {
																																																			out.println("<tr>");
																																																			out.println("</tr>");
																																																			y = 0;
																																																		}

																																																	}
																																																	if (y > 1) {
																																																		for (int q = y; q < 3; q++) {
																																																			out.print("<td>&nbsp</td>");
																																																			out.print("<td>&nbsp</td>");
																																																		}
																																																	}

																																																	out.println("</tr>");
																																																}
																																															}
																																															TDMEDConsulta dMEDConsulta = new TDMEDConsulta();
																																															TVMEDConsulta vMEDConsulta = new TVMEDConsulta();

																																															Vector vcConsulta = new Vector();

																																															////Try catch para imprimir el error
																																															try {
																																																vcConsulta = dMEDConsulta
																																																		.FindByAll(" where MEDConsulta.iCveServicio = " + request.getParameter("iCveServicio"));
																																																int x;
																																																if (vcConsulta.size() > 0) {
																																																	out.print("<tr><td class='ETablaT' colspan='6'>Servicios de Consulta</td></tr>");
																																																	out.println("<tr>");
																																																	x = 0;
																																																	for (int w = 0; w < vcConsulta.size(); w++) {
																																																		vMEDConsulta = (TVMEDConsulta) vcConsulta.get(w);
																																																		out.print("<td class='ETablaC' colspan='2'>");
																																																		out.print(vEti.clsAnclaTexto("EAnclaC", "" + vMEDConsulta.getCDscServCons(),
																																																				"javascript:fServicio(" + request.getParameter("iCveExpediente") + ","
																																																						+ request.getParameter("iNumExamen") + ","
																																																						+ vMEDConsulta.getICveServCons() + ",'" + ""
																																																						+ vMEDConsulta.getCDscServCons() + "');",
																																																				"Servicio"));
																																																		out.print("</td>");
																																																		x += 1;
																																																		if (x == 3) {
																																																			out.println("</tr>");
																																																			out.println("<tr>");
																																																			x = 0;
																																																		}

																																																	}
																																																	if (x > 1) {
																																																		for (int s = x; s < 3; s++) {
																																																			out.print("<td>&nbsp</td>");
																																																			out.print("<td>&nbsp</td>");
																																																		}
																																																	}
																																																}
																																															} catch (Exception e) {
																																																vcConsulta = new Vector();
																																																e.printStackTrace();
																																															}
																																												%>
																																												<%
																																													// NOTA DE LA INTERCONSULTA
																																															if (vcMEDServicio.size() > 0) {
																																																vMEDServicio = (TVMEDServicio) vcMEDServicio.get(0);
																																																if (vMEDServicio.getLInterConsulta() == 1) {
																																																	TDEXPInterconsulta dEXPInterconsulta = new TDEXPInterconsulta();
																																																	TVEXPInterconsulta vEXPInterconsulta = new TVEXPInterconsulta();
																																																	Vector vcEXPInterconsulta = new Vector();
																																																	vcEXPInterconsulta = dEXPInterconsulta.findByAll(" where iCveExpediente = "
																																																			+ request.getParameter("iCveExpediente") + " and iNumExamen = "
																																																			+ request.getParameter("iNumExamen") + " and iCveServicio = " + iCveServicio);
																																																	if (vcEXPInterconsulta.size() > 0) {
																																																		vEXPInterconsulta = (TVEXPInterconsulta) vcEXPInterconsulta.get(0);
																																																		out.print("<tr><td class='ETablaT' colspan='6'>Motivo de Solicitud</td></tr>");
																																																		out.println("<tr><td class='ETablaC' colspan = '6'>");
																																																		out.println(vEXPInterconsulta.getCMtvoSolicitud());
																																																		out.println("<td></tr>");
																																																	}
																																																}
																																															}
																																														} catch (Exception e) {
																																															vcMEDServicio = new Vector();
																																															e.printStackTrace();
																																														}
																																												%>
																																												<!-- Servicio -->
																																												<tr>
																																													<td class="ETablaT"
																																														colspan="6">Servicio</td>
																																												</tr>
																																												<tr>
																																													<td class="EEtiqueta"
																																														colspan="1">Servicio:</td>
																																													<td class="ETabla"
																																														colspan="2"><%=servicio%>
																																													</td>
																																													<%
																																														
																																													%>
																																													<td class="EEtiqueta"
																																														colspan="1">Rama:</td>
																																													<td class="ETabla"
																																														colspan="2"><%=rama%>
																																													</td>
																																												</tr>
																																												<!-- Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006 -->
																																												<!-- ******************************************* -->
																																												<%
																																													if (nextRama2 != null) {
																																															if (iCveServicio == 2) {
																																												%>
																																												<tr>
																																													<td></td>
																																													<td class="ETablaC"
																																														colspan="3">
																																														<%
																																															out.print(vEti.clsAnclaTexto("EAncla", "Cargar Archivo de Cholestech",
																																																				"JavaScript:makeNewWindowCholestech();", "Cholestech"));
																																														%>
																																													</td>
																																												</tr>
																																												<!-- ******************************************* -->
																																												<!-- Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006 -->
																																												<!-- ******************************************* -->
																																												<tr>
																																													<td></td>
																																													<td class="ETablaC"
																																														colspan="3">
																																														<%
																																															out.print(vEti.clsAnclaTexto("EAncla", "Cargar Archivo de Daytona",
																																																				"JavaScript:makeNewWindowDaytona();", "Daytona"));
																																														%>
																																													</td>
																																												</tr>
																																												<%
																																													}
																																												%>


																																												<tr>
																																													<td class="ETablaT"
																																														colspan="6">Preguntas
																																														/ S&iacute;ntomas /
																																														Resultados</td>
																																												</tr>


																																												<!-- ******************************************* -->
																																												<!--INICIO DE CAMBIO1-->
																																												<!-- Agregado IVAN SANTIAGO MENDEZ 06/Oct/2010 -->
																																												<!-- Para subir archivos de las placas de torax al
                                                        content manager.-->
																																												<!-- ******************************************* -->
																																												<%
																																													//SERVICIO RAYOS X y RAMA TORAX
																																															int iCveRamaANTERIOR = iCveRama;
																																															iCveRama = voRama.getICveRama();

																																															if (iCveServicio == 4 || iCveServicio == 10) {
																																																if (iCveRama == 1) {
																																												%>
																																												<tr>
																																													<td></td>
																																													<td class="ETablaC"
																																														colspan="7">
																																														<%
																																															out.print(vEti.clsAnclaTexto("EAncla", "Cargar archivo de imagen",
																																																					"JavaScript:makenewPlacaToraxFile();", "Placa de torax"));
																																														%>
																																													</td>
																																												</tr>
																																												<%
																																													}
																																																if (iCveRama == 2) {
																																												%>
																																												<tr>
																																													<td></td>
																																													<td class="ETablaC"
																																														colspan="7">
																																														<%
																																															out.print(vEti.clsAnclaTexto("EAncla", "Mostrar archivos de imagenes",
																																																					"JavaScript:showPlacasToraxFiles();", "Placa de torax"));
																																														%>
																																													</td>
																																												</tr>
																																												<%
																																													}
																																															}
																																															iCveRama = iCveRamaANTERIOR;
																																												%>
																																												<!-- ******************************************* -->
																																												<!-- FIN DE CAMBIO1-->

																																												<!--<tr><td class="ETablaT" colspan="6">Preguntas / Síntomas / Resultados</td></tr>-->

																																												<%
																																													String cOdonto = "";
																																															if (bs != null) {
																																																bs.start();
																																																int c = 0;
																																																int d = 0;
																																																String cFlag = "";
																																																String cAux = "";
																																																int Dependiente = 0;
																																																while (bs.nextRow()) {

																																																	String iCveTpoResp = bs.getFieldValue("iCveTpoResp").toString();
																																																	String iCveSintoma = bs.getFieldValue("iCveSintoma").toString();

																																																	cFlag = bs.getFieldValue("iCveRama") + "" + bs.getFieldValue("iCveSintoma");

																																																	boolean esTexto = false;
																																																	boolean esCheck = false;
																																																	boolean esNumero = false;
																																																	boolean esRango = false;
																																																	int tpoResp = iCveTpoResp == null || iCveTpoResp.equals("null")
																																																			? 0
																																																			: Integer.parseInt(iCveTpoResp);
																																																	cOdonto = cOdonto + "<td class='EEtiquetaC' width='30%'>"
																																																			+ bs.getFieldValue("cPregunta", "&nbsp;").toString() + "</td>";
																																																	if ((vParametros.getPropEspecifica("EPIServicioOdonto")
																																																			.compareTo(request.getParameter("iCveServicio")) != 0)
																																																			|| ((vParametros.getPropEspecifica("EPIServicioOdonto")
																																																					.compareTo(request.getParameter("iCveServicio"))) == 0) && (iCveRama > 0)) {

																																																		out.print("<tr>");
																																																		if (tpoResp != 6) {
																																																			TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
																																																			String SCWhere = "";
																																																			SCWhere = "               ICVESERVICIO = " + iCveServicio + " AND	"
																																																					+ "               ICVERAMA = " + iCveRama2 + " AND		"
																																																					+ "               ICVEMDOTRANS =  " + iCveMdoTrans + " AND		"
																																																					+ "               ICVECATEGORIA =  " + iCvecategoria + " AND"
																																																					+ "	         ICVESINTOMA =  " + iCveSintoma + "";
																																																			try {
																																																				Dependiente = dMEDREGSIN.FindByAllDep(SCWhere);
																																																			} catch (Exception ex) {
																																																				Dependiente = 0;
																																																			}

																																																			if (Dependiente == 0) {
																																																				if (!iCveTpoResp.equals("11"))
																																																					out.print("<td class='EEtiqueta' colspan='2' width='30%'>"
																																																							+ bs.getFieldValue("cPregunta", "&nbsp;").toString() + "</td>");
																																																			} else {
																																																				out.print("<td class='EEtiqueta' colspan='2' width='30%'>");
																																																				out.print("<div id=\"fcCaracter_" + cFlag + "\" style=\"display:none;\">");
																																																				out.print(bs.getFieldValue("cPregunta", "&nbsp;").toString());
																																																				out.print("</div>");
																																																				out.print("</td>");
																																																			}
																																																		}
																																																		// Respuestas del tipo correspondiente
																																												%>
																																												<input type="hidden"
																																													name="iCveTpoResp_<%=cFlag%>"
																																													value="<%=iCveTpoResp%>">
																																													<input type="hidden"
																																													name="iCveSintoma_<%=cFlag%>"
																																													value="<%=iCveSintoma%>">
																																														<%
																																															String ServicioMG = "12";
																																																				String ServicioCAR = "0";//Se modifico esta parte = 10
																																																				String ServicioAUD = "6";
																																																				String ExaMax = "";
																																																				int max = 0;
																																																				String SQL = "";
																																																				String numeroexamen = "";
																																																				int countexa = 0;
																																																				String Contador = "";
																																																				int claverama = Integer.parseInt(bs.getFieldValue("iCveRama").toString());

																																																				TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
																																																				try {
																																																					SQL = "SELECT COUNT(INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "
																																																							+ request.getParameter("iCveExpediente") + "  AND ICVEPROCESO = 1";
																																																					numeroexamen = dMEDSintoma.Consultas(SQL);
																																																				} catch (Exception e) {
																																																					numeroexamen = "1";
																																																					e.printStackTrace();
																																																				}
																																																				countexa = Integer.parseInt(numeroexamen.trim());
																																																				String Checkbox = "";

																																																				//Validacion MEDICINA GENERAL
																																																				if (ServicioMG.equals(request.getParameter("iCveServicio"))
																																																						|| ServicioCAR.equals(request.getParameter("iCveServicio"))
																																																						|| ServicioAUD.equals(request.getParameter("iCveServicio")) && countexa > 1) {

																																																					switch (tpoResp) {
																																																						case 1 : // si/no
																																																							esCheck = true;
																																																							out.println("<td class='ECampo'>");
																																																							if (Dependiente > 0) {
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																							}

																																																							if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																								String cCheck1 = "";
																																																								String cCheck2 = "";
																																																								if (request.getParameter("lLogico_" + cFlag) != null
																																																										&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																									cCheck1 = "";
																																																									cCheck2 = "";
																																																								}

																																																								out.println("(+)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"1\" " + cCheck1 + " >  ");
																																																								out.println("(-)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"0\" " + cCheck2 + " >  ");
																																																							} else {
																																																								String cCheck1 = "";
																																																								//String cCheck2 = "";
																																																								String cCheck2 = "";
																																																								if (request.getParameter("lLogico_" + cFlag) != null
																																																										&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																									cCheck1 = "";
																																																									cCheck2 = "";
																																																								}
																																																								out.println("Si<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"1\" " + cCheck1 + " >");
																																																								out.println("No<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"0\" " + cCheck2 + " >");
																																																							}
																																																							if (Dependiente > 0) {
																																																								out.println("</div>");
																																																							}
																																																							out.println("</td>");

																																																							if (Dependiente > 0) {
																																																								out.print("<td class='ECampo' colspan='3'> <div id=\"f2cCaracter_"
																																																										+ cFlag + "\" style=\"display:none;\">&nbsp;</div> </td>");
																																																							} else {
																																																								out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
																																																							}

																																																							break;
																																																						case 2 : // letras y números
																																																							esTexto = true;
																																																							String cValor = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cValor = request.getParameter("cCaracter_" + cFlag);
																																																							}
																																																							if (Dependiente == 0) {
																																																								out.println(
																																																										"<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"
																																																												+ cFlag + "\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cValor + "</textarea></td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea cols=\"50\" rows=\"2\" name=\"cCaracter_" + cFlag
																																																										+ "\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cValor + "</textarea>");
																																																								out.println("</div>");
																																																								out.println("</td>");
																																																							}
																																																							break;
																																																						case 3 : // números
																																																							//System.out.println("1.-"+cFlag);
																																																							esNumero = true;
																																																							if (cFlag.equals("17")
																																																									&& request.getParameter("iCveServicio").equals("11")) {
																																																								out.print(
																																																										"<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_17\"");
																																																								out.print("value=\"\"");
																																																								out.print(" onfocus=\"this.select();\"");
																																																								out.print(" onBlur=\"validaNumero(this);\"");
																																																								out.print(" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
																																																								out.print(
																																																										" onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
																																																								out.print(" onChange=\"javascript:IMC(this.value);\"></td>");
																																																							} else {
																																																								out.print("<td class='ECampo' colspan='1'>"
																																																										+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorI_" + cFlag, "",
																																																												0, "", "validaNumero(this);", true, true)
																																																										+ "</td>");
																																																							}
																																																							if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																								out.print("<td class='ECampo' colspan='2'>"
																																																										+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																								out.println(vEti.Texto("ETabla",
																																																										bs.getFieldValue("cValRef", "&nbsp;") + ""));
																																																								out.print("<input type='hidden' name='cValRef_" + c + "' value='"
																																																										+ bs.getFieldValue("cValRef", "&nbsp;") + "'>");
																																																							} else
																																																								out.print("<td class='ECampo' colspan='3'>"
																																																										+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																							break;
																																																						case 4 : // notas
																																																							esTexto = true;
																																																							String cVal = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cVal = request.getParameter("cCaracter_" + cFlag);
																																																							}
																																																							///////////
																																																							if (bs.getFieldValue("lObligatorio", "&nbsp;").equals("1")) {
																																																								out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																										+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																								d++;
																																																							}
																																																							///////////
																																																							if (Dependiente == 0) {
																																																								out.println(
																																																										"<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																												+ cFlag + "\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea></td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea cols=\"50\" rows=\"4\" name=\"cCaracter_" + cFlag
																																																										+ "\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea>");
																																																								out.println("</div>");
																																																								out.println("</td>");

																																																							}
																																																							break;
																																																						case 5 : // rango de números
																																																							esRango = true;
																																																							String cValor1 = "";
																																																							String cValor2 = "";
																																																							if (request.getParameter("dValorI_" + cFlag) != null
																																																									&& request.getParameter("dValorI_" + cFlag).trim().length() > 0) {
																																																								cValor1 = request.getParameter("dValorI_" + cFlag);
																																																							}
																																																							if (request.getParameter("dValorF_" + cFlag) != null
																																																									&& request.getParameter("dValorF_" + cFlag).trim().length() > 0) {
																																																								cValor2 = request.getParameter("dValorF_" + cFlag);
																																																							}

																																																							out.print("<td class='ECampo'>Entre</td>");
																																																							out.print(
																																																									"<td class='ECampo'>"
																																																											+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorI_" + cFlag,
																																																													cValor1, 0, "", "validaNumero(this);", true, true)
																																																											+ "</td>");
																																																							out.print("<td class='ECampo'>y</td>");
																																																							out.print(
																																																									"<td class='ECampo'>"
																																																											+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorF_" + cFlag,
																																																													cValor2, 0, "", "validaNumero(this);", true, true)
																																																											+ "</td>");
																																																							break;
																																																						case 6 : // titulo
																																																							out.println("<td class=\"ETablaSTC\" colspan='6'>"
																																																									+ bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
																																																							out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																									+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																							d++;
																																																							break;

																																																						//Nuevo tipo
																																																						case 7 : // notas
																																																							esTexto = true;
																																																							cVal = "";
																																																							out.println(
																																																									"<td class=\"ECampo\" colspan='1'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																											+ cFlag + "\""); //
																																																							out.println(
																																																									" onChange=\"javascript:truncar2(this)\" onBlur=\"fMayus(this);\">"
																																																											+ cVal + "</textarea></td>");
																																																							out.print("<td class='ECampo' colspan='3'>"
																																																									+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																							break;
																																																						//fin Nuevo tipo
																																																						default :
																																																							out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
																																																					}

																																																				} else {

																																																					switch (tpoResp) {
																																																						case 1 : // si/no
																																																							esCheck = true;
																																																							out.println("<td class='ECampo'>");
																																																							if (Dependiente > 0) {
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																							}

																																																							if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																								String cCheck1 = "";
																																																								String cCheck2 = "";
																																																								if (request.getParameter("lLogico_" + cFlag) != null
																																																										&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																									cCheck1 = "";
																																																									cCheck2 = "";
																																																								}

																																																								out.println("(+)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"1\" " + cCheck1 + " >  ");
																																																								out.println("(-)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"0\" " + cCheck2 + " >  ");
																																																							} else {
																																																								String cCheck1 = "";
																																																								String cCheck2 = "";
																																																								if (request.getParameter("lLogico_" + cFlag) != null
																																																										&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																									cCheck1 = "";
																																																									cCheck2 = "";
																																																								}
																																																								out.println("Si<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"1\" " + cCheck1 + " >");
																																																								out.println("No<input type=\"radio\" name=\"lLogico_" + cFlag
																																																										+ "\" value=\"0\" " + cCheck2 + " >");
																																																							}
																																																							if (Dependiente > 0) {
																																																								out.println("</div>");
																																																							}
																																																							out.println("</td>");

																																																							if (Dependiente > 0) {
																																																								out.print("<td class='ECampo' colspan='3'> <div id=\"f2cCaracter_"
																																																										+ cFlag + "\" style=\"display:none;\">&nbsp;</div> </td>");
																																																							} else {
																																																								out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
																																																							}

																																																							break;
																																																						case 2 : // letras y números
																																																							esTexto = true;
																																																							String cValor = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cValor = request.getParameter("cCaracter_" + cFlag);
																																																							}
																																																							if (Dependiente == 0) {
																																																								out.println(
																																																										"<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"
																																																												+ cFlag + "\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cValor + "</textarea></td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea cols=\"50\" rows=\"2\" name=\"cCaracter_" + cFlag
																																																										+ "\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cValor + "</textarea>");
																																																								out.println("</div>");
																																																								out.println("</td>");
																																																							}
																																																							break;
																																																						case 3 : // números
																																																							esNumero = true;
																																																							//System.out.println("2.-"+cFlag);
																																																							if ((cFlag.equals("17") || cFlag.equals("117"))
																																																									&& request.getParameter("iCveServicio").equals("11")) {
																																																								if (cFlag.equals("17")) {
																																																									out.print(
																																																											"<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_17\"");
																																																									out.print(" value=\"\"");
																																																									out.print(" onfocus=\"this.select();\"");
																																																									out.print(" onBlur=\"validaNumero(this);\"");
																																																									out.print(
																																																											" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
																																																									out.print(
																																																											" onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
																																																									out.print(" onChange=\"javascript:IMC(this.value);\"></td>");
																																																								}
																																																								if (cFlag.equals("117")) {
																																																									out.print(
																																																											"<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_117\"");
																																																									out.print(" value=\"\"");
																																																									out.print(" readonly=\"true\" >");//Inhabilita el campo de texto  
																																																									out.print("</td>");
																																																								}
																																																							} else {
																																																								if ((cFlag.equals("116") || cFlag.equals("119"))
																																																										&& request.getParameter("iCveServicio").equals("75")) {
																																																									if (cFlag.equals("116")) {
																																																										out.print(
																																																												"<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_116\"");
																																																										out.print(" value=\"\"");
																																																										out.print(" onfocus=\"this.select();\"");
																																																										out.print(" onBlur=\"validaNumero(this);\"");
																																																										out.print(
																																																												" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
																																																										out.print(
																																																												" onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
																																																										out.print(" onChange=\"javascript:IMC2(this.value);\"></td>");
																																																									}
																																																									if (cFlag.equals("119")) {
																																																										out.print(
																																																												"<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_119\"");
																																																										out.print(" value=\"\"");
																																																										out.print(" readonly=\"true\" >");//Inhabilita el campo de texto  
																																																										out.print("</td>");
																																																									}
																																																								} else {

																																																									if (Dependiente == 0) {
																																																										out.print(
																																																												"<td class='ECampo' colspan='3'>"
																																																														+ vEti.CampoSinCelda("ETablaC", 10, 10,
																																																																"dValorI_" + cFlag, "", 0, "",
																																																																"validaNumero(this);", true, true)
																																																														+ "</td>");
																																																									} else {
																																																										out.print("<td class='ECampo' colspan='3'>");
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																										out.print(vEti.CampoSinCelda("ETablaC", 10, 10,
																																																												"dValorI_" + cFlag, "", 0, "", "validaNumero(this);",
																																																												true, true));
																																																										out.println("<div>" + bs.getFieldValue("cEtiqueta", "&nbsp;")
																																																												+ "</div>");
																																																										out.println("</div>");
																																																										out.print("</td>");
																																																									}
																																																								}
																																																							}
																																																							if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																								out.print("<td class='ECampo' colspan='2'>"
																																																										+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																								out.println(vEti.Texto("ETabla",
																																																										bs.getFieldValue("cValRef", "&nbsp;") + ""));
																																																								out.print("<input type='hidden' name='cValRef_" + c + "' value='"
																																																										+ bs.getFieldValue("cValRef", "&nbsp;") + "'>");
																																																							} else {
																																																								if (Dependiente == 0) {
																																																									out.print("<td class='ECampo' colspan='3'>"
																																																											+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																								} else {
																																																									out.print("<td class='ECampo' colspan='3'>");
																																																									out.println("<div id=\"fcCaracter_" + cFlag
																																																											+ "\" style=\"display:none;\">");
																																																									out.print(bs.getFieldValue("cEtiqueta", "&nbsp;"));
																																																									out.println("</div>");
																																																									out.print("</td>");
																																																								}
																																																							}
																																																							break;
																																																						case 4 : // notas
																																																							esTexto = true;
																																																							String cVal = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cVal = request.getParameter("cCaracter_" + cFlag);
																																																							}
																																																							///////////
																																																							if (bs.getFieldValue("lObligatorio", "&nbsp;").equals("1")) {
																																																								out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																										+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																								d++;
																																																							}
																																																							///////////
																																																							/*if (Dependiente == 0) {
																																																								out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																										+ cFlag + "\""); //
																																																								out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																										+ cVal
																																																										+ "</textarea></td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_"
																																																										+ cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																										+ cFlag + "\""); //
																																																								out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																										+ cVal + "</textarea>");
																																																								out.println("</div>");
																																																								out.println("</td>");
																																																							
																																																							}*/
																																																							if (Dependiente == 0) {
																																																								out.println("<td class=\"ECampo\" colspan='1'><textarea id=\"texto_"
																																																										+ cFlag + "\" cols=\"50\" rows=\"4\" name=\"cCaracter_" + cFlag
																																																										+ "\" onkeypress=\"return limita_" + cFlag + "(event, 500)\" "
																																																										+ "onkeyup=\"actualizaInfo_" + cFlag + "(500)\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea></td>");
																																																								out.print("<td class='ECampo' colspan='3'>"
																																																										+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "<div id=\"info_"
																																																										+ cFlag + "\">M&aacute;ximo 500 caracteres</div></td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea id=\"texto_" + cFlag
																																																										+ "\" cols=\"50\" rows=\"4\" name=\"cCaracter_" + cFlag
																																																										+ "\" onkeypress=\"return limita(event, 500)\" "
																																																										+ "onkeyup=\"actualizaInfo_" + cFlag + "(500)\""); //	
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea><div id=\"info_" + cFlag
																																																												+ "\">M&aacute;ximo 500 caracteres</div></div></td>");
																																																							}

																																																							break;
																																																						case 5 : // rango de números
																																																							esRango = true;
																																																							String cValor1 = "";
																																																							String cValor2 = "";
																																																							if (request.getParameter("dValorI_" + cFlag) != null
																																																									&& request.getParameter("dValorI_" + cFlag).trim().length() > 0) {
																																																								cValor1 = request.getParameter("dValorI_" + cFlag);
																																																							}
																																																							if (request.getParameter("dValorF_" + cFlag) != null
																																																									&& request.getParameter("dValorF_" + cFlag).trim().length() > 0) {
																																																								cValor2 = request.getParameter("dValorF_" + cFlag);
																																																							}

																																																							out.print("<td class='ECampo'>Entre</td>");
																																																							out.print(
																																																									"<td class='ECampo'>"
																																																											+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorI_" + cFlag,
																																																													cValor1, 0, "", "validaNumero(this);", true, true)
																																																											+ "</td>");
																																																							out.print("<td class='ECampo'>y</td>");
																																																							out.print(
																																																									"<td class='ECampo'>"
																																																											+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorF_" + cFlag,
																																																													cValor2, 0, "", "validaNumero(this);", true, true)
																																																											+ "</td>");
																																																							break;
																																																						case 6 : // titulo
																																																							out.println("<td class=\"ETablaSTC\" colspan='6'>"
																																																									+ bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
																																																							out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																									+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																							d++;
																																																							break;

																																																						//Nuevo tipo
																																																						case 7 : // notas
																																																							esTexto = true;
																																																							cVal = "";
																																																							if (Dependiente == 0) {
																																																								out.println("<td class=\"ECampo\" colspan='1'><textarea id=\"texto_"
																																																										+ cFlag + "\" cols=\"50\" rows=\"4\" name=\"cCaracter_" + cFlag
																																																										+ "\" onkeypress=\"return limita_" + cFlag + "(event, 2000)\" "
																																																										+ "onkeyup=\"actualizaInfo_" + cFlag + "(2000)\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar2(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea></td>");
																																																								out.print("<td class='ECampo' colspan='3'>"
																																																										+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "<div id=\"info_"
																																																										+ cFlag + "\">M&aacute;ximo 2000 caracteres</div></td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea id=\"texto_" + cFlag
																																																										+ "\" cols=\"50\" rows=\"4\" name=\"cCaracter_" + cFlag
																																																										+ "\" onkeypress=\"return limita(event, 2000)\" "
																																																										+ "onkeyup=\"actualizaInfo_" + cFlag + "(2000)\""); //	
																																																								out.println(
																																																										" onChange=\"javascript:truncar2(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea><div id=\"info_" + cFlag
																																																												+ "\">M&aacute;ximo 2000 caracteres</div></div></td>");
																																																							}

																																																							break;

																																																						//Combos
																																																						case 8 : // Combos
																																																							esTexto = true;
																																																							cVal = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cVal = request.getParameter("cCaracter_" + cFlag);
																																																							}

																																																							Vector respuestas = new Vector();

																																																							try {
																																																								String condicion = " icveservicio = "
																																																										+ request.getParameter("iCveServicio") + " and icverama = "
																																																										+ bs.getFieldValue("iCveRama") + " and icvesintoma = "
																																																										+ iCveSintoma + " ";
																																																								respuestas = dMEDRespSint.FindByAll(condicion);
																																																							} catch (Exception e) {
																																																								respuestas = new Vector();
																																																								e.printStackTrace();
																																																							}

																																																							if (Dependiente == 0) {
																																																								out.println("<td class=\"ETabla\" colspan=\"5\">");
																																																								out.println(vEti.SelectOneRowSinTD("cCaracter_" + cFlag + "", "",
																																																										respuestas, "iOrden", "cDescripcion", request, "0", true));
																																																								out.print("</td></tr>");
																																																							} else {
																																																								out.println("<td class=\"ETabla\" colspan=\"5\">");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println(vEti.SelectOneRowSinTD("cCaracter_" + cFlag + "", "",
																																																										respuestas, "iOrden", "cDescripcion", request, "0", true));
																																																								out.println("</div>");
																																																								out.print("</td></tr>");
																																																							}
																																																							//out.print("</td> <td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");

																																																							break;

																																																						// Imagenes Zoom
																																																						case 9 : // Imagenes Zoom
																																																							esTexto = true;
																																																							cVal = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cVal = request.getParameter("cCaracter_" + cFlag);
																																																							}

																																																							String IMG = "";

																																																							try {
																																																								String condicion2 = " icveservicio = "
																																																										+ request.getParameter("iCveServicio") + " and icverama = "
																																																										+ bs.getFieldValue("iCveRama") + " and icvesintoma = "
																																																										+ iCveSintoma + " ";
																																																								IMG = dMEDRespSint.FindByAllS(condicion2);
																																																							} catch (Exception e) {
																																																								IMG = "";
																																																								e.printStackTrace();
																																																							}

																																																							out.println("<td class=\"ETabla\" colspan=\"5\">");
																																																							out.println("");
																																																							out.println("<img src=\"" + vParametros.getPropEspecifica("RutaImgServer")
																																																									+ IMG
																																																									+ "\" class=\"magnify\" class=\"img-style row5\" style=\"width: 225px; height: 150px; cursor: url(http://wixusimg.blogspot.mx/magnify.cur); opacity: 1; \">");
																																																							out.println("");
																																																							out.print("</td></tr>");

																																																							//out.print("</td> <td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");

																																																							break;

																																																						// Hidden
																																																						case 10 : // Hidden
																																																							esTexto = true;
																																																							out.print("<input type=\"hidden\" name=\"FILNumReng\" value=\"500\">");

																																																							break;

																																																						// Casos Especiales
																																																						case 11 : // Casos Especiales
																																																							esTexto = true;
																																																							/////TORAX
																																																							if (request.getParameter("iCveServicio").equals("54")) {
																																																								if (bs.getFieldValue("iCveRama").equals("2")) {
																																																									if (iCveSintoma.equals("1")) {
																																																										out.print(
																																																												"<tr><td class='EEtiqueta' colspan='2' >INSPECCI&Oacute;N</td> ");
																																																										out.print(
																																																												"<input type=\"hidden\" name=\"iCveTpoResp_21\" value=\"1\"><input type=\"hidden\" name=\"iCveSintoma_21\" value=\"1\"> ");
																																																										out.print("<td class='ECampo'colspan='4'> ");
																																																										out.print("<table width=\"200\" border=\"1\"> ");
																																																										out.print("<tr> ");
																																																										out.print(
																																																												"        <td class='ECampo'colspan='1'><div align=\"center\"><img src=\""
																																																														+ vParametros.getPropEspecifica("RutaImgServer")
																																																														+ "torax-1.png\" ></div></td> ");
																																																										out.print(
																																																												"            <td class='ECampo'><div align=\"center\"><img src=\""
																																																														+ vParametros.getPropEspecifica("RutaImgServer")
																																																														+ "torax-2.png\" ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><img src=\""
																																																														+ vParametros.getPropEspecifica("RutaImgServer")
																																																														+ "torax-3.png\" ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><img src=\""
																																																														+ vParametros.getPropEspecifica("RutaImgServer")
																																																														+ "torax-4.png\" ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><img src=\""
																																																														+ vParametros.getPropEspecifica("RutaImgServer")
																																																														+ "torax-5.png\" ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><img src=\""
																																																														+ vParametros.getPropEspecifica("RutaImgServer")
																																																														+ "torax-6.png\" ></div></td> ");
																																																										out.print(
																																																												"           <td class='ECampo'><div align=\"center\"><img src=\""
																																																														+ vParametros.getPropEspecifica("RutaImgServer")
																																																														+ "torax-7.png\" ></div></td></tr> ");
																																																										out.print("    </tr> ");
																																																										out.print("    <tr> ");
																																																										out.print(
																																																												"        <td class='ECampo'colspan='1'><div align=\"center\"><input type=\"radio\" name=\"lLogico_21\" value=\"0\"  ></div></td> ");
																																																										out.print(
																																																												"            <td class='ECampo'><div align=\"center\"><input type=\"radio\" name=\"lLogico_21\" value=\"0\"  ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><input type=\"radio\" name=\"lLogico_21\" value=\"0\"  ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><input type=\"radio\" name=\"lLogico_21\" value=\"1\"  ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><input type=\"radio\" name=\"lLogico_21\" value=\"2\"  ></div></td> ");
																																																										out.print(
																																																												"                <td class='ECampo'><div align=\"center\"><input type=\"radio\" name=\"lLogico_21\" value=\"3\"  ></div></td> ");
																																																										out.print(
																																																												"            <td class='ECampo'><div align=\"center\"><input type=\"radio\" name=\"lLogico_21\" value=\"4\"  ></div></td></tr> ");
																																																										out.print("    </tr> ");
																																																										out.print("    </table>  ");
																																																										out.print(
																																																												"    <tr><td class='EEtiqueta' colspan='2' >FORMA Y VOLUMEN</td> ");
																																																										out.print(
																																																												"<input type=\"hidden\" name=\"iCveTpoResp_11\" value=\"1\"><input type=\"hidden\" name=\"iCveSintoma_11\" value=\"1\"> ");
																																																										out.print("<td colspan=\"1\" class='ECampo'></td> ");
																																																										out.print(
																																																												"<td colspan=\"3\" class='ECampo'>PECTUNM EXCAVATUM, INVESTIGUE GRADO DE RESTRICCI&Oacute;N</td> ");
																																																										out.print("</tr>");
																																																									}
																																																								}
																																																							}

																																																							break;

																																																						case 12 : // Links para JSP de imagenes de puntos de referencia
																																																							esTexto = true;
																																																							//out.println("<tr>");
																																																							if (iCveServicio == 54 && iCveRama2 == 2) {
																																																								if (iCveSintoma.equals("7")) {
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Capturar punto",
																																																											"javascript:fCapRef70("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama2 + ");",
																																																											"Capturar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</div></td>");
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Mostrar puntos",
																																																											"javascript:fMosRef70("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama2 + ");",
																																																											"Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</td>");
																																																								}

																																																								if (iCveSintoma.equals("215")) {
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Capturar punto",
																																																											"javascript:fCapRef54t("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama2 + ");",
																																																											"Capturar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</div></td>");
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Mostrar puntos",
																																																											"javascript:fMosRef54t("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama2 + ");",
																																																											"Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</td>");
																																																								}

																																																							}
																																																							if (iCveServicio == 54 && iCveRama2 == 1) {
																																																								if (iCveSintoma.equals("216")) {
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Capturar punto",
																																																											"javascript:fCapRef54a("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Capturar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</div></td>");
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Mostrar puntos",
																																																											"javascript:fMosRef54a("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</td>");
																																																								}
																																																								if (iCveSintoma.equals("217")) {
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Capturar punto",
																																																											"javascript:fCapRef54AE("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Capturar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</div></td>");
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Mostrar puntos",
																																																											"javascript:fMosRef54AE("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</td>");
																																																								}
																																																								if (iCveSintoma.equals("218")) {
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Capturar punto",
																																																											"javascript:fCapRef54AMD("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Capturar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</div></td>");
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Mostrar puntos",
																																																											"javascript:fMosRef54AMD("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</td>");
																																																								}
																																																								if (iCveSintoma.equals("219")) {
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Capturar punto",
																																																											"javascript:fCapRef54AMI("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Capturar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</div></td>");
																																																									out.println(
																																																											"<td class='ECampo' colspan='2' align='center'><div align=\"center\">");
																																																									out.print(vEti.clsAnclaTexto("EEtiqueta", "Mostrar puntos",
																																																											"javascript:fMosRef54AMI("
																																																													+ request.getParameter("iCveExpediente") + ","
																																																													+ request.getParameter("iNumExamen") + ","
																																																													+ iCveServicio + "," + iCveRama + ");",
																																																											"Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
																																																									out.println("</td>");
																																																								}
																																																							}
																																																							break;

																																																						//Combos
																																																						case 13 : // Combos
																																																							esTexto = true;
																																																							cVal = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cVal = request.getParameter("cCaracter_" + cFlag);
																																																							}

																																																							Vector respuestas2 = new Vector();

																																																							try {
																																																								String condicion = " icveservicio = "
																																																										+ request.getParameter("iCveServicio") + " and icverama = "
																																																										+ bs.getFieldValue("iCveRama") + " and icvesintoma = "
																																																										+ iCveSintoma + " ";
																																																								respuestas2 = dMEDRespSint.FindByAll(condicion);
																																																							} catch (Exception e) {
																																																								respuestas2 = new Vector();
																																																								e.printStackTrace();
																																																							}

																																																							if (Dependiente == 0) {
																																																								out.println("<td class=\"ETabla\" colspan=\"3\">");
																																																								out.println(vEti.SelectOneRowSinTD3("cCaracter_" + cFlag + "", "",
																																																										respuestas2, "iOrden", "cDescripcion", request, "0", true));
																																																								out.print(
																																																										"</td><td class=\"ETabla\" colspan=\"2\">Si desea elegir m&aacute;s de una respuesta presione la tecla CTRL</td></tr>");
																																																							} else {
																																																								out.println("<td class=\"ETabla\" colspan=\"3\">");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println(vEti.SelectOneRowSinTD3("cCaracter_" + cFlag + "", "",
																																																										respuestas2, "iOrden", "cDescripcion", request, "0", true));
																																																								out.println("</div>");
																																																								out.print(
																																																										"</td><td class=\"ETabla\" colspan=\"2\">Si desea elegir m&aacute;s de una respuesta presione la tecla CTRL</td></tr>");
																																																							}
																																																							//out.print("</td> <td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");

																																																							break;

																																																						case 14 : // Fecha y hora
																																																							esTexto = true;
																																																							cValor = "";
																																																							if (request.getParameter("cCaracter_" + cFlag) != null
																																																									&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																								cValor = request.getParameter("cCaracter_" + cFlag);
																																																							}
																																																							if (Dependiente == 0) {
																																																								/*out.println("<td class=\"ECampo\" colspan='2'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"
																																																										+ cFlag + "\""); //
																																																								out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																										+ cValor
																																																										+ "</textarea></td>");*/
																																																								out.println("<td class=\"ECampo\" colspan='2'>");
																																																								out.print(vEti.EtiCampo2("EEtiqueta", "Fecha:", "ECampo", "text", 10,
																																																										10, "dtFecha_" + cFlag + "", cHoy2, 0, "", "fMayus(this);",
																																																										false, true, true, request));
																																																								out.println("</td>");
																																																								out.println(
																																																										"<td class=\"ECampo\" colspan='1'>Hora: <select name=\"cHhora_"
																																																												+ cFlag + "\" size=\"1\">");
																																																								out.println(
																																																										"<option>00</option><option>01</option><option>02</option><option>03</option><option>04</option>");
																																																								out.println(
																																																										"<option>05</option><option>06</option><option>07</option><option>08</option><option>09</option>");
																																																								out.println(
																																																										"<option>10</option><option>11</option><option>12</option><option>13</option><option>14</option>");
																																																								out.println(
																																																										"<option>15</option><option>16</option><option>17</option><option>18</option><option>19</option>");
																																																								out.println(
																																																										"<option>20</option><option>21</option><option>22</option><option>23</option></select> </td>");
																																																								out.println(
																																																										"<td class=\"ECampo\" colspan='1'>Minutos: <select name=\"cMinutos_"
																																																												+ cFlag + "\" size=\"1\">");
																																																								out.println(
																																																										"<option>00</option><option>01</option><option>02</option><option>03</option><option>04</option>");
																																																								out.println(
																																																										"<option>05</option><option>06</option><option>07</option><option>08</option><option>09</option>");
																																																								out.println(
																																																										"<option>10</option><option>11</option><option>12</option><option>13</option><option>14</option>");
																																																								out.println(
																																																										"<option>15</option><option>16</option><option>17</option><option>18</option><option>19</option>");
																																																								out.println(
																																																										"<option>20</option><option>21</option><option>22</option><option>23</option><option>24</option>");
																																																								out.println(
																																																										"<option>25</option><option>26</option><option>27</option><option>28</option><option>29</option>");
																																																								out.println(
																																																										"<option>30</option><option>31</option><option>32</option><option>33</option><option>34</option>");
																																																								out.println(
																																																										"<option>35</option><option>36</option><option>37</option><option>38</option><option>39</option>");
																																																								out.println(
																																																										"<option>40</option><option>41</option><option>42</option><option>43</option><option>44</option>");
																																																								out.println(
																																																										"<option>45</option><option>46</option><option>47</option><option>48</option><option>49</option>");
																																																								out.println(
																																																										"<option>50</option><option>51</option><option>52</option><option>53</option><option>54</option>");
																																																								out.println(
																																																										"<option>55</option><option>56</option><option>57</option><option>58</option><option>59</option>");
																																																								out.println("</select> </td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea cols=\"50\" rows=\"2\" name=\"cCaracter_" + cFlag
																																																										+ "\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cValor + "</textarea>");
																																																								out.print(vEti.EtiCampo("EEtiqueta", "Fecha4:", "ECampo", "text", 10,
																																																										10, "dtFecha", cHoy, 0, "", "fMayus(this);", false, true, true,
																																																										request));
																																																								out.println("</div>");
																																																								out.println("</td>");
																																																							}
																																																							break;

																																																						case 15 :
																																																							//out.println("<tr>");
																																																							TDPais dPais = new TDPais();
																																																							out.println("<td class='ECampo' colspan='4'>");
																																																							out.println(
																																																									"<table width=\"100%\" border=\"1\" class=\"ETablaInfo\" cellspacing=\"0\" cellpadding=\"3\">");
																																																							out.println("<tr> <td class='ECampo'>");
																																																							out.println(vEti.Texto3("EEtiqueta", "Pa&iacute;s:"));
																																																							out.println(vEti.SelectOneRowSinTD2("iCvePais_" + cFlag + "",
																																																									"llenaSLT(2,this.value,'','',document.forms[0].iCveEstado_" + cFlag
																																																											+ ");",
																																																									dPais.FindByAll(), "iCvePais", "cNombre", request, "0", true));
																																																							out.println("</td><td class='ECampo'>");
																																																							//out.println("<td class='ECampo' colspan='1'>");
																																																							out.println(vEti.Texto3("EEtiqueta", "EDO (Estado):"));
																																																							//out.println("<td class='ECampo'>");
																																																							out.println("<SELECT NAME='iCveEstado_" + cFlag
																																																									+ "' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCvePais_"
																																																									+ cFlag + ".value,this.value,'',document.forms[0].iCveMunicipio_"
																																																									+ cFlag + ");\" ");
																																																							out.println("</SELECT>");
																																																							out.println("</td><tr>");
																																																							out.println("<tr><td class='ECampo'>");
																																																							out.println(vEti.Texto3("EEtiqueta", "MUN (Municipio):"));
																																																							//out.println("<td class='ECampo'>");
																																																							out.println("<SELECT NAME='iCveMunicipio_" + cFlag
																																																									+ "' SIZE='1' onChange=\"llenaSLT(5,document.forms[0].iCvePais_"
																																																									+ cFlag + ".value,document.forms[0].iCveEstado_" + cFlag
																																																									+ ".value,this.value,document.forms[0].iCveLocalidad_" + cFlag
																																																									+ ");\" ");
																																																							out.println("</SELECT>");
																																																							out.println("</td>");
																																																							out.println("<td class='ECampo'>");
																																																							out.println(vEti.Texto3("EEtiqueta", "LOC (Poblaci&oacute;n):"));
																																																							//out.println("<td class='ECampo'>");
																																																							out.println("<SELECT NAME='iCveLocalidad_" + cFlag + "' SIZE='1' ");
																																																							out.println("</SELECT>");
																																																							out.println("</td>");
																																																							out.println("<tr> </table> </td>");
																																																							break;

																																																						//Nuevo tipo
																																																						case 16 : // notas
																																																							esTexto = true;
																																																							cVal = "";
																																																							if (Dependiente == 0) {
																																																								out.println("<td class=\"ECampo\" colspan='1'><textarea id=\"texto_"
																																																										+ cFlag + "\" cols=\"80\" rows=\"10\" name=\"cCaracter_" + cFlag
																																																										+ "\" onkeypress=\"return limita_" + cFlag + "(event, 6000)\" "
																																																										+ "onkeyup=\"actualizaInfo_" + cFlag + "(6000)\""); //
																																																								out.println(
																																																										" onChange=\"javascript:truncar3(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea></td>");
																																																								out.print("<td class='ECampo' colspan='3'>"
																																																										+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "<div id=\"info_"
																																																										+ cFlag + "\">M&aacute;ximo 6000 caracteres</div></td>");
																																																							} else {
																																																								out.println("<td class=\"ECampo\" colspan='4'>");
																																																								out.println("<div id=\"f2cCaracter_" + cFlag
																																																										+ "\" style=\"display:none;\">");
																																																								out.println("<textarea id=\"texto_" + cFlag
																																																										+ "\" cols=\"80\" rows=\"10\" name=\"cCaracter_" + cFlag
																																																										+ "\" onkeypress=\"return limita(event, 6000)\" "
																																																										+ "onkeyup=\"actualizaInfo_" + cFlag + "(6000)\""); //	
																																																								out.println(
																																																										" onChange=\"javascript:truncar3(this)\" onBlur=\"fMayus(this);\">"
																																																												+ cVal + "</textarea><div id=\"info_" + cFlag
																																																												+ "\">M&aacute;ximo 6000 caracteres</div></div></td>");
																																																							}

																																																							break;

																																																						default :
																																																							out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
																																																					}

																																																				} // Validacion Medicina General
																																																				c++;
																																																			} // De Validación de EPIServicioOdonto
																																																			else {
																																																				//Despliegue Odontograma
																																																				if (c == 0)
																																																					out.print("<table align ='center'><tr>");
																																														%> <input type="hidden"
																																														name="iCveTpoResp_<%=cFlag%>"
																																														value="<%=iCveTpoResp%>">
																																															<input type="hidden"
																																															name="iCveSintoma_<%=cFlag%>"
																																															value="<%=iCveSintoma%>">
																																																<%
																																																	if (c < 32) {
																																																							switch (tpoResp) {
																																																								case 1 : // si/no
																																																									esCheck = true;
																																																									out.println("<td class='ECampo'>");
																																																									if (Dependiente > 0) {
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																									}

																																																									if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																										String cCheck1 = "";
																																																										String cCheck2 = "";
																																																										if (request.getParameter("lLogico_" + cFlag) != null
																																																												&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																											cCheck1 = "";
																																																											cCheck2 = "";
																																																										}

																																																										out.println("(+)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"1\" " + cCheck1 + " >  ");
																																																										out.println("(-)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"0\" " + cCheck2 + " >  ");
																																																									} else {
																																																										String cCheck1 = "";
																																																										//String cCheck2 = "";
																																																										String cCheck2 = "";
																																																										if (request.getParameter("lLogico_" + cFlag) != null
																																																												&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																											cCheck1 = "";
																																																											cCheck2 = "";
																																																										}
																																																										out.println("Si<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"1\" " + cCheck1 + " >");
																																																										out.println("No<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"0\" " + cCheck2 + " >");
																																																									}
																																																									if (Dependiente > 0) {
																																																										out.println("</div>");
																																																									}
																																																									out.println("</td>");

																																																									if (Dependiente > 0) {
																																																										out.print("<td class='ECampo' colspan='3'> <div id=\"f2cCaracter_"
																																																												+ cFlag + "\" style=\"display:none;\">&nbsp;</div> </td>");
																																																									} else {
																																																										out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
																																																									}

																																																									break;
																																																								case 2 : // letras y números
																																																									esTexto = true;
																																																									String cValor = "";
																																																									if (request.getParameter("cCaracter_" + cFlag) != null
																																																											&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																										cValor = request.getParameter("cCaracter_" + cFlag);
																																																									}
																																																									out.println(
																																																											"<td class=\"ECampo\"><textarea cols=\"5\" rows=\"2\" name=\"cCaracter_"
																																																													+ cFlag + "\""); //
																																																									out.println(
																																																											" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																													+ cValor + "</textarea></td>");
																																																									break;
																																																								case 3 : // números
																																																									esNumero = true;
																																																									//System.out.println("3.-"+cFlag);
																																																									if (cFlag.equals("17")
																																																											&& request.getParameter("iCveServicio").equals("11")) {
																																																										out.print(
																																																												"<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_17\"");
																																																										out.print("value=\"\"");
																																																										out.print("onfocus=\"this.select();\"");
																																																										out.print("onBlur=\"validaNumero(this);\"");
																																																										out.print("onMouseOut=\"if (window.fOutField) window.fOutField();\"");
																																																										out.print(
																																																												"onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
																																																										out.print("onChange=\"javascript:IMC(this.value);\"></td>");
																																																									} else {
																																																										out.print("<td class='ECampo' colspan='1'>"
																																																												+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorI_" + cFlag, "",
																																																														0, "", "validaNumero(this);", true, true)
																																																												+ "</td>");
																																																									}
																																																									if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																										out.print("<td class='ECampo' colspan='2'>"
																																																												+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																										out.println(vEti.Texto("ETabla",
																																																												bs.getFieldValue("cValRef", "&nbsp;") + ""));
																																																										out.print("<input type='hidden' name='cValRef_" + c + "' value='"
																																																												+ bs.getFieldValue("cValRef", "&nbsp;") + "'>");
																																																									} else
																																																										out.print("<td class='ECampo' colspan='3'>"
																																																												+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																									break;
																																																								case 4 : // notas
																																																									esTexto = true;
																																																									String cVal = "";
																																																									if (request.getParameter("cCaracter_" + cFlag) != null
																																																											&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																										cVal = request.getParameter("cCaracter_" + cFlag);
																																																									}
																																																									///////////
																																																									if (bs.getFieldValue("lObligatorio", "&nbsp;").equals("1")) {
																																																										out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																												+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																										d++;
																																																									}
																																																									///////////
																																																									if (Dependiente == 0) {
																																																										out.println(
																																																												"<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																														+ cFlag + "\""); //
																																																										out.println(
																																																												" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																														+ cVal + "</textarea></td>");
																																																									} else {
																																																										out.println("<td class=\"ECampo\" colspan='4'>");
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																										out.println("<textarea cols=\"50\" rows=\"4\" name=\"cCaracter_" + cFlag
																																																												+ "\""); //
																																																										out.println(
																																																												" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																														+ cVal + "</textarea>");
																																																										out.println("</div>");
																																																										out.println("</td>");

																																																									}
																																																									break;
																																																								case 5 : // rango de números
																																																									esRango = true;
																																																									String cValor1 = "";
																																																									String cValor2 = "";
																																																									if (request.getParameter("dValorI_" + cFlag) != null
																																																											&& request.getParameter("dValorI_" + cFlag).trim().length() > 0) {
																																																										cValor1 = request.getParameter("dValorI_" + cFlag);
																																																									}
																																																									if (request.getParameter("dValorF_" + cFlag) != null
																																																											&& request.getParameter("dValorF_" + cFlag).trim().length() > 0) {
																																																										cValor2 = request.getParameter("dValorF_" + cFlag);
																																																									}

																																																									out.print("<td class='ECampo'>Entre</td>");
																																																									out.print(
																																																											"<td class='ECampo'>"
																																																													+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorI_" + cFlag,
																																																															cValor1, 0, "", "validaNumero(this);", true, true)
																																																													+ "</td>");
																																																									out.print("<td class='ECampo'>y</td>");
																																																									out.print(
																																																											"<td class='ECampo'>"
																																																													+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorF_" + cFlag,
																																																															cValor2, 0, "", "validaNumero(this);", true, true)
																																																													+ "</td>");
																																																									break;
																																																								case 6 : // titulo
																																																									out.println("<td class=\"ETablaSTC\" colspan='6'>"
																																																											+ bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
																																																									out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																											+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																									d++;
																																																									break;

																																																								//Nuevo tipo
																																																								case 7 : // notas
																																																									esTexto = true;
																																																									cVal = "";
																																																									out.println(
																																																											"<td class=\"ECampo\" colspan='1'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																													+ cFlag + "\""); //
																																																									out.println(
																																																											" onChange=\"javascript:truncar2(this)\" onBlur=\"fMayus(this);\">"
																																																													+ cVal + "</textarea></td>");
																																																									out.print("<td class='ECampo' colspan='3'>"
																																																											+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																									break;

																																																								//Combos
																																																								case 8 : // Combos
																																																									esTexto = true;
																																																									cVal = "";
																																																									if (request.getParameter("cCaracter_" + cFlag) != null
																																																											&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																										cVal = request.getParameter("cCaracter_" + cFlag);
																																																									}

																																																									Vector respuestas = new Vector();

																																																									try {
																																																										String condicion = " icveservicio = "
																																																												+ request.getParameter("iCveServicio") + " and icverama = "
																																																												+ bs.getFieldValue("iCveRama") + " and icvesintoma = "
																																																												+ iCveSintoma + " ";
																																																										respuestas = dMEDRespSint.FindByAll(condicion);
																																																									} catch (Exception e) {
																																																										respuestas = new Vector();
																																																										e.printStackTrace();
																																																									}

																																																									if (Dependiente == 0) {
																																																										out.println("<td class=\"ETabla\" colspan=\"5\">");
																																																										out.println(vEti.SelectOneRowSinTD("cCaracter_" + cFlag + "", "",
																																																												respuestas, "iOrden", "cDescripcion", request, "0", true));
																																																										out.print("</td></tr>");
																																																									} else {
																																																										out.println("<td class=\"ETabla\" colspan=\"5\">");
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																										out.println(vEti.SelectOneRowSinTD("cCaracter_" + cFlag + "", "",
																																																												respuestas, "iOrden", "cDescripcion", request, "0", true));
																																																										out.println("</div>");
																																																										out.print("</td></tr>");
																																																									}
																																																									//out.print("</td> <td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
																																																									break;

																																																								default :
																																																									out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
																																																							}
																																																							c++;
																																																							if (c == 8 || c == 24) {
																																																								out.print("<td>&nbsp;&nbsp;&nbsp;</td>");
																																																								cOdonto = cOdonto + "<td>&nbsp;&nbsp;&nbsp;</td>";
																																																							}
																																																							String cLinea = "";
																																																							if (c == 16 || c == 32) {
																																																								out.print("</tr>");
																																																								out.print("<tr>");
																																																								out.print(cOdonto);
																																																								out.print("</tr>");
																																																								out.print("<tr>");
																																																								cOdonto = "";
																																																							}
																																																						} // validación < 33
																																																						else { // Despliegue normal !!!!!
																																																							if (c == 32)
																																																								out.print("<table class='ETabla' align ='center' background='"
																																																										+ vParametros.getPropEspecifica("RutaImg")
																																																										+ "fondo01.jpg' width='100%' height='100'>");
																																																							out.print("<tr>");
																																																							if (tpoResp != 6)
																																																								out.print("<td class='EEtiqueta' colspan='2' width='30%'>"
																																																										+ bs.getFieldValue("cPregunta", "&nbsp;").toString() + "</td>");
																																																							switch (tpoResp) {
																																																								case 1 : // si/no
																																																									esCheck = true;
																																																									out.println("<td class='ECampo'>");
																																																									if (Dependiente > 0) {
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																									}

																																																									if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																										String cCheck1 = "";
																																																										String cCheck2 = "";
																																																										if (request.getParameter("lLogico_" + cFlag) != null
																																																												&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																											cCheck1 = "";
																																																											cCheck2 = "";
																																																										}

																																																										out.println("(+)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"1\" " + cCheck1 + " >  ");
																																																										out.println("(-)<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"0\" " + cCheck2 + " >  ");
																																																									} else {
																																																										String cCheck1 = "";
																																																										//String cCheck2 = "";
																																																										String cCheck2 = "";
																																																										if (request.getParameter("lLogico_" + cFlag) != null
																																																												&& request.getParameter("lLogico_" + cFlag).equals("1")) {
																																																											cCheck1 = "";
																																																											cCheck2 = "";
																																																										}
																																																										out.println("Si<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"1\" " + cCheck1 + " >");
																																																										out.println("No<input type=\"radio\" name=\"lLogico_" + cFlag
																																																												+ "\" value=\"0\" " + cCheck2 + " >");
																																																									}
																																																									if (Dependiente > 0) {
																																																										out.println("</div>");
																																																									}
																																																									out.println("</td>");

																																																									if (Dependiente > 0) {
																																																										out.print("<td class='ECampo' colspan='3'> <div id=\"f2cCaracter_"
																																																												+ cFlag + "\" style=\"display:none;\">&nbsp;</div> </td>");
																																																									} else {
																																																										out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
																																																									}

																																																									break;
																																																								case 2 : // letras y números
																																																									esTexto = true;
																																																									String cValor = "";
																																																									if (request.getParameter("cCaracter_" + cFlag) != null
																																																											&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																										cValor = request.getParameter("cCaracter_" + cFlag);
																																																									}
																																																									if (Dependiente == 0) {
																																																										out.println(
																																																												"<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"
																																																														+ cFlag + "\""); //
																																																										out.println(
																																																												" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																														+ cValor + "</textarea></td>");
																																																									} else {
																																																										out.println("<td class=\"ECampo\" colspan='4'>");
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																										out.println("<textarea cols=\"50\" rows=\"2\" name=\"cCaracter_" + cFlag
																																																												+ "\""); //
																																																										out.println(
																																																												" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																														+ cValor + "</textarea>");
																																																										out.println("</div>");
																																																										out.println("</td>");
																																																									}
																																																									break;
																																																								case 3 : // números
																																																									//System.out.println("4.-"+cFlag);
																																																									esNumero = true;
																																																									if (cFlag.equals("17")
																																																											&& request.getParameter("iCveServicio").equals("11")) {
																																																										out.print(
																																																												"<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_17\"");
																																																										out.print("value=\"\"");
																																																										out.print("onfocus=\"this.select();\"");
																																																										out.print("onBlur=\"validaNumero(this);\"");
																																																										out.print("onMouseOut=\"if (window.fOutField) window.fOutField();\"");
																																																										out.print(
																																																												"onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
																																																										out.print("onChange=\"javascript:IMC(this.value);\"></td>");
																																																									} else {
																																																										out.print("<td class='ECampo' colspan='1'>"
																																																												+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorI_" + cFlag, "",
																																																														0, "", "validaNumero(this);", true, true)
																																																												+ "</td>");
																																																									}
																																																									if (iCveLabC == Integer.parseInt(request.getParameter("iCveServicio"))) {
																																																										out.print("<td class='ECampo' colspan='2'>"
																																																												+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																										out.println(vEti.Texto("ETabla",
																																																												bs.getFieldValue("cValRef", "&nbsp;") + ""));
																																																										out.print("<input type='hidden' name='cValRef_" + c + "' value='"
																																																												+ bs.getFieldValue("cValRef", "&nbsp;") + "'>");
																																																									} else
																																																										out.print("<td class='ECampo' colspan='3'>"
																																																												+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																									break;
																																																								case 4 : // notas
																																																									esTexto = true;
																																																									String cVal = "";
																																																									if (request.getParameter("cCaracter_" + cFlag) != null
																																																											&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																										cVal = request.getParameter("cCaracter_" + cFlag);
																																																									}
																																																									///////////
																																																									if (bs.getFieldValue("lObligatorio", "&nbsp;").equals("1")) {
																																																										out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																												+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																										d++;
																																																									}
																																																									///////////
																																																									if (Dependiente == 0) {
																																																										out.println(
																																																												"<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																														+ cFlag + "\""); //
																																																										out.println(
																																																												" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																														+ cVal + "</textarea></td>");
																																																									} else {
																																																										out.println("<td class=\"ECampo\" colspan='4'>");
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																										out.println("<textarea cols=\"50\" rows=\"4\" name=\"cCaracter_" + cFlag
																																																												+ "\""); //
																																																										out.println(
																																																												" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"
																																																														+ cVal + "</textarea>");
																																																										out.println("</div>");
																																																										out.println("</td>");

																																																									}
																																																									break;
																																																								case 5 : // rango de números
																																																									esRango = true;
																																																									String cValor1 = "";
																																																									String cValor2 = "";
																																																									if (request.getParameter("dValorI_" + cFlag) != null
																																																											&& request.getParameter("dValorI_" + cFlag).trim().length() > 0) {
																																																										cValor1 = request.getParameter("dValorI_" + cFlag);
																																																									}
																																																									if (request.getParameter("dValorF_" + cFlag) != null
																																																											&& request.getParameter("dValorF_" + cFlag).trim().length() > 0) {
																																																										cValor2 = request.getParameter("dValorF_" + cFlag);
																																																									}

																																																									out.print("<td class='ECampo'>Entre</td>");
																																																									out.print(
																																																											"<td class='ECampo'>"
																																																													+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorI_" + cFlag,
																																																															cValor1, 0, "", "validaNumero(this);", true, true)
																																																													+ "</td>");
																																																									out.print("<td class='ECampo'>y</td>");
																																																									out.print(
																																																											"<td class='ECampo'>"
																																																													+ vEti.CampoSinCelda("ETablaC", 10, 10, "dValorF_" + cFlag,
																																																															cValor2, 0, "", "validaNumero(this);", true, true)
																																																													+ "</td>");
																																																									break;
																																																								case 6 : // titulo
																																																									out.println("<td class=\"ETablaSTC\" colspan='6'>"
																																																											+ bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
																																																									out.print("<input type='hidden' name='hdTitulo_" + d + "' value='"
																																																											+ bs.getFieldValue("cPregunta", "&nbsp;") + "'>");
																																																									d++;
																																																									break;

																																																								//Nuevo tipo
																																																								case 7 : // notas
																																																									esTexto = true;
																																																									cVal = "";
																																																									out.println(
																																																											"<td class=\"ECampo\" colspan='1'><textarea cols=\"50\" rows=\"4\" name=\"cCaracter_"
																																																													+ cFlag + "\""); //
																																																									out.println(
																																																											" onChange=\"javascript:truncar2(this)\" onBlur=\"fMayus(this);\">"
																																																													+ cVal + "</textarea></td>");
																																																									out.print("<td class='ECampo' colspan='3'>"
																																																											+ bs.getFieldValue("cEtiqueta", "&nbsp;") + "</td>");
																																																									break;

																																																								//Combos
																																																								case 8 : // Combos
																																																									esTexto = true;
																																																									cVal = "";
																																																									if (request.getParameter("cCaracter_" + cFlag) != null
																																																											&& request.getParameter("cCaracter_" + cFlag).trim().length() > 0) {
																																																										cVal = request.getParameter("cCaracter_" + cFlag);
																																																									}

																																																									Vector respuestas = new Vector();

																																																									try {
																																																										String condicion = " icveservicio = "
																																																												+ request.getParameter("iCveServicio") + " and icverama = "
																																																												+ bs.getFieldValue("iCveRama") + " and icvesintoma = "
																																																												+ iCveSintoma + " ";
																																																										respuestas = dMEDRespSint.FindByAll(condicion);
																																																									} catch (Exception e) {
																																																										respuestas = new Vector();
																																																										e.printStackTrace();
																																																									}

																																																									if (Dependiente == 0) {
																																																										out.println("<td class=\"ETabla\" colspan=\"5\">");
																																																										out.println(vEti.SelectOneRowSinTD("cCaracter_" + cFlag + "", "",
																																																												respuestas, "iOrden", "cDescripcion", request, "0", true));
																																																										out.print("</td></tr>");
																																																									} else {
																																																										out.println("<td class=\"ETabla\" colspan=\"5\">");
																																																										out.println("<div id=\"f2cCaracter_" + cFlag
																																																												+ "\" style=\"display:none;\">");
																																																										out.println(vEti.SelectOneRowSinTD("cCaracter_" + cFlag + "", "",
																																																												respuestas, "iOrden", "cDescripcion", request, "0", true));
																																																										out.println("</div>");
																																																										out.print("</td></tr>");
																																																									}
																																																									//out.print("</td> <td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
																																																									break;

																																																								default :
																																																									out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
																																																							}
																																																							c++;
																																																						}
																																																					}
																																																					cAux += cFlag + "|";
																																																				}

																																																				out.print("</tr>");
																																																				if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("iCveServicio"))
																																																						|| vParametros.getPropEspecifica("Audiologia")
																																																								.equals(request.getParameter("iCveServicio"))
																																																						|| vParametros.getPropEspecifica("GAudiologia")
																																																								.equals(request.getParameter("iCveServicio"))) {
																																																					out.print("</tr>");
																																																%>
																																																<td colspan='6'
																																																align='center'><%=vEti.clsAnclaTexto("EAncla", "Oido Derecho", "JavaScript:fAudio('Derecho');", "")%> <%=vEti.clsAnclaTexto("EAncla", "Oido Izquierdo", "JavaScript:fAudio2('Izquierdo');",
									"")%></td>
																																											</tr>
																																											<tr align='center'>
																																												<%
																																													TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
																																																	TVEXPAudiomet vEXPAudiomet;
																																																	Vector vcAudio = new Vector();

																																																	////Try catch para imprimir el error   
																																																	try {
																																																		//    vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + request.getParameter("iCveExpediente") + " and iNumExamen = " + request.getParameter("iNumExamen") );

																																																		String condicion = " where iCveExpediente = " + request.getParameter("iCveExpediente")
																																																				+ " and iNumExamen = " + request.getParameter("iNumExamen")
																																																				+ " and iCveServicio = " + request.getParameter("iCveServicio");
																																																		vcAudio = dEXPAudiomet.FindByAll(condicion);
																																																		if (vcAudio.size() > 0) {
																																																			if (request.getParameter("hdBoton").equals("AudioMuestra")
																																																					&& !(request.getParameter("hdGOido").equals("null"))) {
																																																				out.println("<SCRIPT LANGUAGE='JavaScript'>");
																																																				for (int i = 0; i < vcAudio.size(); i++) {
																																																					vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
																																																					out.println("aSel[" + i + "]=[" + vEXPAudiomet.getIOido() + ","
																																																							+ vEXPAudiomet.getITipo() + "," + vEXPAudiomet.getIX() + ","
																																																							+ vEXPAudiomet.getIY() + "];");
																																																				}

																																																				out.print("fGraphAudio(" + request.getParameter("hdGOido") + ",aSel);");
																																																				out.print("</SCRIPT>");
																																																			}
																																																			if (request.getParameter("hdBoton").equals("AudioMuestra")
																																																					&& !(request.getParameter("hdGOido").equals("null"))) {
																																																				out.println("<SCRIPT LANGUAGE='JavaScript'>");
																																																				out.print("alert(\"Datos guardados correctamente\"));");
																																																				out.print("</SCRIPT>");
																																																			}

																																																		}
																																																	} catch (Exception e) {
																																																		vcAudio = new Vector();
																																																		e.printStackTrace();
																																																	}
																																												%>
																																												<td colspan='6'
																																													align='center'><%=vEti.clsAnclaTexto("EAncla", "Muestra Derecho ", "JavaScript:fAsigna(1);", "")%><%=vEti.clsAnclaTexto("EAncla", " Muestra Izquierdo", "JavaScript:fAsigna(2);", "")%></td>
																																												<%
																																													out.print("</tr>");
																																																}
																																												%>
																																												<!-- ******************************************* -->
																																												<!--INICIO DE CAMBIO 2-->
																																												<!-- Agregado IVAN SANTIAGO MENDEZ 07/Mayo/2012 -->
																																												<!-- Para subir archivos de imagenes en los servicios que lo tengan configurado en la base de datos al
                                                        content manager.-->
																																												<!-- ******************************************* -->
																																												<%
																																													//int iCveRamaANTERIOR = iCveRama;
																																																//iCveRama = voRama.getICveRama();
																																																boolean puedeSubirArchivo = false;
																																																puedeSubirArchivo = dMEDServicio.puedeSubirArchivos(iCveServicio);
																																																if (puedeSubirArchivo) {
																																												%>
																																												<tr>
																																													<td class="ETablaT"
																																														colspan="6">Subir
																																														Archivos</td>
																																												</tr>
																																												<tr>
																																													<td class="ETablaC"
																																														colspan="6">
																																														<%
																																															out.print(vEti.clsAnclaTexto("EAncla", "Cargar archivo de imagen",
																																																					"JavaScript: makenewImagenServicioFile();", "Subir imagenes relacionadas"));
																																														%>
																																													</td>
																																												</tr>
																																												<tr>
																																													<td class="ETablaC"
																																														colspan="6">
																																														<%
																																															out.print(vEti.clsAnclaTexto("EAncla", "Mostrar archivos de imagenes",
																																																					"JavaScript:showPlacasToraxFiles();", "Ver imagenes relacionadas"));
																																														%>
																																													</td>
																																												</tr>
																																												<%
																																													}
																																																//iCveRama=iCveRamaANTERIOR;
																																												%>
																																												<!-- ******************************************* -->
																																												<!-- FIN DE CAMBIO 2-->
																																												<%
																																													out.print("<table align='center'>");
																																																out.print("<tr><td>&nbsp;</td></tr>");

																																																TVGRLUsuario vGRLUsuario = new TVGRLUsuario();
																																																TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
																																																Vector vcGRLUsuario = new Vector();
																																																////Try catch para imprimir el error
																																																try {
																																																	String cSiglas = "";
																																																	vcGRLUsuario = dGRLUsuario
																																																			.getSiglas(" where GRLUsuario.iCveUsuario = " + vUsuario.getICveusuario());
																																																	if (vcGRLUsuario.size() > 0) {
																																																		vGRLUsuario = (TVGRLUsuario) vcGRLUsuario.get(0);
																																																		cSiglas = vGRLUsuario.getCSiglas();
																																																	}

																																																	String cNombre = cSiglas + " " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno()
																																																			+ " " + vUsuario.getCApMaterno();
																																																	int iLong = cNombre.length();
																																																	String cLinea = "";
																																																	for (int i = 0; i < iLong; i++)
																																																		cLinea += "_";

																																																	out.print("&nbsp;");
																																																	out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cLinea + "</td></tr>");
																																																	out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cNombre + "</td></tr>");
																																																	out.print("</table>");
																																																} catch (Exception e) {
																																																	vcGRLUsuario = new Vector();
																																																	e.printStackTrace();
																																																}
																																												%>
																																												<input type="hidden"
																																													name="hdTotalRows"
																																													value="<%=c%>"> <input
																																													type="hidden"
																																													name="hdFlags"
																																													value="<%=cAux%>">
																																														<%
																																															} else {
																																																		out.println("<tr>");
																																																		out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 6, "Mensaje",
																																																				"No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true,
																																																				false));
																																																		out.println("</tr>");
																																																	}
																																														%>
																																												
																																						</table>
																																					</td>
																																				</tr>
																																				<%
																																					} //Rama diferenete de nulo
																																					} else {
																																				%>
																																				<script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
																																				<%
																																					}
																																				%>
																																			</table>
		</form>
		<%
			/////////////////////////////CODIGO CORRESPONDIENTE AL PIE DE PAGINA///////////////////////////////////////
		%>

		<div class="Estilo21" id="footer">
			<p>
				Este expediente cl&iacute;nico es propiedad de la Secretaria de
				Comunicaciones y Transportes. La informaci&oacute;n contenida
				ser&aacute; manejada con discreci&oacute;n y confidencialidad,
				atendiendo a los principios cient&iacute;ficos y &eacute;ticos que
				orientan la pr&aacute;ctica m&eacute;dica y s&oacute;lo podr&aacute;
				ser dada a conocer a terceros mediante orden de la autoridad
				competente de conformidad con NORMA OFICIAL MEXICANA
				NOM-168-SSA1-1998, DEL EXPEDIENTE CLINICO.<br>
			</p>
		</div>

		<%
			if (!(iCveServicio == 67 || iCveServicio == 75)) {
		%>
		<script
			src="<%=vParametros.getPropEspecifica("RutaFuncs") + "jquery.min2.js"%>"
			type="text/javascript"></script>


		<script
			src="<%=vParametros.getPropEspecifica("RutaFuncs") + "jquery.constantfooter.js"%>"
			type="text/javascript"></script>
		<script type="text/javascript">
		       $(document).ready(function() {
			       $("#footer").constantfooter({ feed: "", showclose: true });
		       });
	       </script>
		<%
			}
		%>
	</body>
	<%=vErrores.muestraError2()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
	<%
		vEntorno.clearListaImgs();
	%>


	<%
		///CODIGO DEL CUADRO FLOTANTE///
	%>
	<script>
	$(document).ready(function() {
        var posicion = $("#cuadroFlotante").offset();
        var margenSuperior = 118;
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
	<%
		///FIN DEL CODIGO DEL CUADRO FLOTANTE///
	%>

</html>