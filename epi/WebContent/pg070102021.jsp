<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	/** En LA CAPTURA DE CITA para TERCEROS que tengan HABILITADA la VALIDACION biometrica en alguno de sus MODULOS ASIGNADOS, se valida HUELLA MEDICO


	 * Title: Registo de Citas
	 * Description: Registro de Citas
	 * Copyright: 2012
	 * Company: Micros Personales S.A. de C.V.
	 * @author AG SA
	 * @version 1.0
	 * Clase:pg070102021CFG
	 */
%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.micper.util.*"%>

<%@ page import="gob.sct.cis.dao.*"%>


<html>
<%
	pg070102021CFG clsConfig = new pg070102021CFG(); // modificar

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setNombrePagina("pg070102021.jsp"); // modificar
	vEntorno.setArchFuncs("FValida");
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg070102021.jsp\" target=\"FRMCuerpo"); // modificar
	vEntorno.setUrlLogo("Acerca");
	vEntorno.setBtnPrincVisible(true);
	vEntorno.setArchFCatalogo("FFiltro");
	vEntorno.setArchAyuda(vEntorno.getNombrePagina());

	String cClave2 = "";
	String cModulo = "";
	String cFecha = "";
	String cCita = "";

	int Inhabilitado = 0;

	String cPosicion = "";
	String cPropiedad = "";

	int iFiltro = 0;
	TError vErrores = new TError();
	StringBuffer sbErroresAcum = new StringBuffer();
	TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());

	/*Se agrega validacion en javascript para toma de huella
	 * cuando ya se tiene un usuario dentro de la pagina se carga el script para abrir el scanner instalado
	 * en la maquina cliente y se le envia el n�mero del expediente que se busca para la huella dactilar
	 */
	TVUsuario vUsuario = (TVUsuario) request.getSession(true)
			.getAttribute("UsrID");
	int icveexpediente = vUsuario.getIdIcveExpediente();
	//System.out.println("EXPEDIENTE HUELLA"+icveexpediente);
	int iDoctoHuellaDigital = vUsuario.getIcveDoctoHuella();//Numero de Documento de la huella del doctor
	//System.out.println("IDOCTO HUELLA"+iDoctoHuellaDigital);
	int inumeroDedoMedico = vUsuario.getiDedoAValidar();

	boolean esMedicoTercero = false;
	Vector vUsuMedicos = vUsuario.getVUsuMedicos();
	for (int i = 0; i < vUsuMedicos.size(); i++) {
		if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == 107) {
			esMedicoTercero = true;
		}
	}

	///Validar si es tercero para bloquear el registro de cita
	boolean bloqueaGeneraCita = false;
	//System.out.println(vUsuario.getCUsuario());
	bloqueaGeneraCita = clsConfig.getBloqueaGeneraCita(
			vUsuario.getCUsuario(), vUsuario.getICveusuario());
	//System.out.println("bloqueaGeneraCita="+bloqueaGeneraCita);

	String cOperador = "1"; // modificar
	String cDscOrdenar = "Muestra|"; // modificar
	String cCveOrdenar = "iCveMuestra|"; // modificar
	String cDscFiltrar = "Muestra|"; // modificar
	String cCveFiltrar = "iCveMuestra|"; // modificar
	String cTipoFiltrar = "8|"; // modificar
	boolean lFiltros = false; // modificar
	boolean lIra = false; // modificar
	String cEstatusIR = "Imprimir"; // modificar 

	int iNumRowsPrin = new Integer(
			vParametros.getPropEspecifica("NumRowsPrin")).intValue();
	int iNumRowsSec = new Integer(
			vParametros.getPropEspecifica("NumRowsSec")).intValue();

	clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

	String cCampo = "";
	String cPanel = "";

	if ((request.getParameter("hdOPPbaRapida") == null)
			|| (request.getParameter("hdBoton").compareTo("Guardar") == 0)
			|| (request.getParameter("hdBoton").compareTo("Cancelar") == 0)) {
		cCampo = "0";
	} else {
		cCampo = "" + request.getParameter("hdOPPbaRapida");
	}

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
	int iAniosAtras = new Integer(
			vParametros.getPropEspecifica("Anios")).intValue();
	TFechas dtFecha = new TFechas();
	int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
	String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
	TDPais dPaisNac = new TDPais();

	String irANuevo = "";
	if (request.getParameter("hdBoton").equals("Primero")) {
		irANuevo = "fNuevo();";
	} else {
		irANuevo = "Variables();";
	}

	TDGRLLocalidad dLocalidad = new TDGRLLocalidad();
	String Localidad = "";
	String iCveLocalidad = "";
	String cWhere = "";
%>
<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type='text/javascript'
	src='/medprev/dwr/interface/MedPredDwr.js'></script>
<script language="JavaScript">
        function fValidaTodo(){
            if(fValidaTodoOriginal()){
                return procesoDeValidacionMedico();
            }else{
                return false
            }
        }
        function procesoDeValidacionMedico(){

            var valido = false;

            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
            /*MedPredDwr.validaAccesosIncorrectosBitacora(<%=vUsuario.getICveusuario()%>,{
                    callback : function(data) {
                        if(data >= 3){
                            alert('Su cuenta a sido bloqueada, debido a que la validación dactilar excedió el número de intentos');
                            window.top.location.href  ='./pg0700004_3.jsp'
                        }else{*/
            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
            
            //if(<%=esMedicoTercero%>==true){Si ES Medico tercero
            
            	if(false){//Si ES Medico tercero
                MedPredDwr.ejecutaValidacionBiometrica(<%=vUsuario.getICveusuario()%>,{
                    async: false, // synchronous call
                    callback : function(data) {//Respuesta de busqueda del metodo si se ejecuta validacion biometrica
                        if(data==0){
                            //redirigirAPantallaAvisoOaPantallaPrincipal();
                            valido=true;
                        }else{
                            var V = ValidacionHuellaDactilarMedicos();
                            if(V == 'true'){
                                alert('Validación biometrica EXITOSA');
                                valido=true;
                            }else{
                                /*MedPredDwr.insertAccessFallidoBiometrico(<%=vUsuario.getICveusuario()%>,"",BrowserDetect.browser,BrowserDetect.version,"","",{
                                                        callback : function(data) {*/
                                alert('Validacion biometrica FALLIDA intentelo de nuevo');
                                valido=false;
                                /*},
                                                        scope : this
                                                    });*/
                            }
                        }
                    },
                    scope : this
                });
            }else{//Si NO es medico tercero
                valido=true;
            }
            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
            /*}
                    },
                    scope : this
                });*/
            /***ROberto Mireles solicita que el bloqueo de las cuentas sea solo en el dictamen*/
            return valido;
        }



        function ValidacionHuellaDactilarMedicos(){
            var fso;
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var shell = new ActiveXObject("WScript.shell");
            //VARIABLE  para bajar las huellas del Content por medio de la clave del expediente
            //var iNoMedico = <%=icveexpediente%>;

            //VARIABLE para bajar las huellas del Content por medio de la clave del documento
            var iNoMedico = <%=iDoctoHuellaDigital%>;

            var iNoDedo = ' <%=inumeroDedoMedico%>';
            var pathProg = "c:\\fingerprint\\Sct_Fingerprint.exe ";

            //Ruta para bajar las huellas del Content por medio de la clave del expediente
            //var pathWS = "http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=_DEDO_ ";

            //Ruta para bajar las huellas del Content por medio de la clave del documento
            var pathWS = "<%=vParametros.getPropEspecifica("RutaContextoGral")%>BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            //Ruta para PROBAR desde una maquina localmente
            //var pathWS = "http://10.33.143.52:7001/medprev/BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            shell.run(pathProg + pathWS + iNoMedico + iNoDedo);
            fso = new ActiveXObject("Scripting.FileSystemObject");
            while(flag==0){
                try{
                    archivo = fso.OpenTextFile("c:\\fingerprint\\temp\\matchresult.txt", ForReading, false);
                    result = archivo.readline();
                    archivo.Close();
                    flag = 1;
                }catch(err){
                    //alert(err);
                }
            }
            return result;
        }

        function ValidacionHuellaDactilarPacientes(){
            var fso;
            var result = false;
            var ForReading = 1;
            var flag = 0;
            var shell = new ActiveXObject("WScript.shell");
            var iNoMedico = <%=icveexpediente%>;
            //var iNoMedico = <%=iDoctoHuellaDigital%>;
            var iNoDedo = " 7";
            var pathProg = "c:\\fingerprint\\Sct_Fingerprint.exe ";

            //Ruta para bajar las huellas del Content por medio de la clave del expediente
            var pathWS = "http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=_DEDO_ ";

            //Ruta para bajar las huellas del Content por medio de la clave del documento
            //var pathWS = "<%=vParametros.getPropEspecifica("RutaContextoGral")%>BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            //Ruta para PROBAR desde una maquina localmente
            //var pathWS = "http://10.33.143.52:7001/medprev/BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&idTipoDocumento=3&IDEDO=_DEDO_ ";

            shell.run(pathProg + pathWS + iNoMedico + iNoDedo);
            fso = new ActiveXObject("Scripting.FileSystemObject");
            while(flag==0){
                try{
                    archivo = fso.OpenTextFile("c:\\fingerprint\\temp\\matchresult.txt", ForReading, false);
                    result = archivo.readline();
                    archivo.Close();
                    flag = 1;
                }catch(err){
                    //alert(err);
                }
            }
            return result;
        }

    </script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")
					+ vEntorno.getNombrePagina().substring(0,
							vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
<script language="JavaScript">

        function llenaSLT(opc,val1,val2,val3,objDes){
            if(objDes!=''){
                if(objDes.name!=''){
                    objDes.length=1;
                    objDes[0].text="Cargando Datos...";
                    window.parent.FRMOtroPanel.location="pg070102021P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
                } else {llenaSLT
                    alert("Debe seleccionar un dato valido!");
                    objDes.length=0;
                    objDes.length=1;
                    return false;
                }
            } else {
                window.parent.FRMOtroPanel.location="pg070302010P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + "&hdAccion="+ document.forms[0].action;
            }
     
            
        }


        function fChgArea(fArea){
            form = document.forms[0];
            cText = fArea.value;
            if(cText.length > 1999)
                fArea.value = cText = cText.substring(0,1999);
            form.iNoLetras.value = 1999 - cText.length;
        }


        function fChgArea(fArea){
            form = document.forms[0];
            cText = fArea.value;
            if(cText.length > 1999)
                fArea.value = cText = cText.substring(0,1999);
            form.iNoLetras.value = 1999 - cText.length;
            fArea.value = revisaCaracteresEspeciales(cText);
        }

        //Valida Apostrofo y Enter

        function revisaCaracteresEspeciales(texto){
            if(fEncCaract(texto, "'")){
                alert("No se permite la comilla simple");
                texto = texto.replace("'","");
            }
            if(fEncCaract(texto, String.fromCharCode(10))||fEncCaract(texto, String.fromCharCode(13))){
                alert("No se permite ejecutar un ENTER en este campo");
                texto = texto.replace(String.fromCharCode(13),"");
                texto = texto.replace(String.fromCharCode(10),"");
            }
            return texto;
        }

        /* Funci�n para localizar un caracter espec�fico dentro de una cadena
       Par�metros: cVCadena -- Cadena en la cual se va a localizar el caracter
                   cVCaract -- Caracter a buscar
       Valor que regresa:   true  -- Si lo localiza
                            false -- Si no lo encuentra */
        function fEncCaract(cVCadena, cVCaract){
            if (cVCadena.indexOf(cVCaract) != -1){
                return true;
            }else{
                return false;
            }
        }


        function fEncCaract(cVCadena, cVCaract){
            if (cVCadena.indexOf(cVCaract) != -1){
                return true;
            }else{
                return false;
            }
        }

        //Inhabilitando "Carga de Citas"

        function fInhabilita(){
            alert("Esta función ha sido deshabilitada.");
        }

        // Esta funci�n no debe modificarse
        function fOnLoad(){
            fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
            '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
            '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
        }


        var cStyle = '<link rel="stylesheet" href="<%=vParametros.getPropEspecifica("RutaCSS")
					+ vParametros.getPropEspecifica("HojaCSS")%>" TYPE="text/css">';
    </script>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "calendarioNom024.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "PermCombos.js"%>"></SCRIPT>

<head>
<meta name="Autor" content="<%=vParametros.getPropEspecifica("Autor")%>">
</head>
<%
	new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros);
%>
<link rel="stylesheet"
	href="<%=vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>"
	TYPE="text/css">
<body bgcolor="<%=vParametros.getPropEspecifica("ColorFondoPagina")%>"
	topmargin="0" leftmargin="0"
	onLoad="<%=vEntorno.getOnLoad()%><%=irANuevo%>">
	<form method="<%=vEntorno.getMetodoForm()%>"
		action="<%=vEntorno.getActionForm()%>">
		<input type="hidden" name="hdCCondicion"
			value="<%if (request.getParameter("hdCCondicion") != null) {
				out.print(request.getParameter("hdCCondicion"));
			}%>">
		<input type="hidden" name="hdCOrdenar"
			value="<%if (request.getParameter("hdCOrdenar") != null) {
				out.print(request.getParameter("hdCOrdenar"));
			}%>">
		<%
			if (bs != null) { // Agregar tanto Hds como variables dependiendo del campo llave.
				cPosicion = Integer.toString(bs.rowNo());
				cClave2 = "" + bs.getFieldValue("iCveUniMed", "");
				cModulo = "" + bs.getFieldValue("iCveModulo", "");
				cFecha = "" + bs.getFieldValue("cDscFecha", "");
				cCita = "" + bs.getFieldValue("iCveCita", "");
			}
		%>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave" value="<%=cClave2%>"> <input
			type="hidden" name="hdModulo" value="<%=cModulo%>"> <input
			type="hidden" name="hdFecha" value="<%=cFecha%>"> <input
			type="hidden" name="hdCita" value="<%=cCita%>"> <input
			type="hidden" name="hdAnio" value="<%=iAnio%>"> <input
			type="hidden" name="hdPbaRapida"> <input type="hidden"
			name="hdPersonal"> <input type="hidden" name="hdiCvePersonal">
		<input type="hidden" name="iCveDoctoAVALIDAR"
			value="<%=iDoctoHuellaDigital%>">

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
				<td><input type="hidden" name="hdBoton"></td>
				<td valign="top">
					<%
						TEtiCampo vEti = new TEtiCampo();
							TCreaBoton bBtn = new TCreaBoton();
							boolean lCaptura = clsConfig.getCaptura();
							boolean lNuevo = clsConfig.getNuevo();
							TFechas oFecha = new TFechas();
							String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(), "/");
					%> <input type="hidden" name="hdHoy"
					value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(), "/")%>">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
							// Inicio de Datos
						%>
						<tr>
							<td colspan="9" class="ETablaT">Búsqueda de Citas</td>
						</tr>
						<tr>
							<td class="EEtiqueta">Unidad M&eacute;dica:</td>
							<td class="ETabla"><%=vEti.SelectOneRowSinTD2("iCveUniMed",
						"{forma = document.forms[0];fActualizaCombo('3',forma,this,forma.iCveModulo,this.value,0,"
								+ iCveProceso + " );}",
						vUsuario.getVUniMed(), "iClave", "cDescripcion", request, "0", true)%> <%
 	int iCveUniMed = (request.getParameter("iCveUniMed") != null
 				&& request.getParameter("iCveUniMed").toString().length() > 0)
 						? Integer.parseInt(request.getParameter("iCveUniMed").toString())
 						: 0;
 %></td>
							<td class="EEtiqueta">M&oacute;dulo:</td>
							<td class="ETabla"><%=vEti.SelectOneRowSinTD2("iCveModulo", "",
						vUsuario.getModuloFUP(iCveUniMed, Integer.parseInt("1")), "iClave", "cDescripcion", request,
						"0", true)%></td>
						</tr>
						<%
							out.println("<tr>");
								out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, "dtFecha", cHoy, 0, "",
										"fMayus(this);", false, true, true, request));

								if (lNuevo == false && request.getParameter("hdBoton").compareTo("Modificar") != 0) {
									out.println("<td colspan='2' align='center'>");
									out.print(vEti.clsAnclaTexto("EEtiqueta", "Buscar", "javascript:fBuscar();", "Buscar"));
									out.println("</td>");
									out.println("</tr>");
								}
								if (lNuevo == false && request.getParameter("hdBoton").compareTo("Modificar") != 0) {

									if (cCanWrite.compareToIgnoreCase("yes") == 0) {
										out.print("<tr><td>");
										//out.print(vEti.clsAnclaTexto("EAncla","Cargar Informaci�n","JavaScript:fGetFile();", "Cargar Informaci�n...",""));
										out.print(vEti.clsAnclaTexto("EAncla", "Cargar Información", "JavaScript:fInhabilita();",
												"Cargar Información2...", ""));
										out.print("</td></tr>");
									}
								}
						%>

					</table>
					<p>&nbsp;
					<p>
						<%
							//}
						%>

						<%
							if (bs != null || lNuevo) {
						%>
					
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<%
							// Inicio de Datos
						%>
						<tr>
							<td colspan="4" class="ETablaT">Registro de Citas</td>
						</tr>
						<tr>

							<%
								}
							%>

							<%
								if (lNuevo) {
										out.println("<tr>");
										//out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha","&nbsp;",0,"","fMayus(this);",false,true, lCaptura,request));
										out.print(vEti.EtiCampo("EEtiqueta", "Cve. Cita:", "ECampo", "text", 5, 5, "iCveCita", "&nbsp;", 0,
												"", "fMayus(this);", false, true, false));
										out.println(vEti.Texto("EEtiqueta", "Hora:"));
							%>
							<td class="ETabla"><select name="iHora" size="1">
									<%
										for (int i = 8; i <= 18; i++) {
													out.print("<option value = " + i + ">" + i + "</option>");
												}
									%>
							</select> <select name="iMinutos" size="1">
									<%
										for (int i = 0; i <= 45; i = i + 15) {
													if (i == 0) {
														out.print("<option value = " + i + ">00</option>");
													} else {
														out.print("<option value = " + i + ">" + i + "</option>");
													}

												}
									%>
							</select></td>
							<%
								out.println("</tr>");
										out.println("<tr>");
										out.println("<td colspan='4' align='center'>");
										out.print(vEti.clsAnclaTexto("EEtiqueta", "Buscar Persona", "javascript:fSelPer();", "Buscar")
												+ "&nbsp;&nbsp;&nbsp;&nbsp;");
										out.println("</td>");
										out.println("</tr>");
										if (request.getParameter("hdiCvePersonal") != null
												&& request.getParameter("hdiCvePersonal").compareTo("") != 0) {
											Inhabilitado = clsConfig.getInhabilitado(request.getParameter("hdiCvePersonal"));
											if (Inhabilitado == 1) {//esta inhabilitado
							%>
						
						<tr>
							<td class="ETablaAyuda" colspan='4'><p>Este Expediente
									se encuentra Inhabilitado para Generar Ex&aacute;menes en
									Medicina Preventiva!</p>
								<p>Favor de contactar al Administrador del Sistema.</p></td>
						</tr>
						<%
							} else {//no esta inhabilitado
											out.println("<input type='hidden' name='iCvePersonal' value='"
													+ request.getParameter("hdiCvePersonal") + "'>");
											int i = 0;
											Object obElemento;
											TDPERDatos dPerDatos = new TDPERDatos();
											Vector vcPersonal = new Vector();
											vcPersonal = dPerDatos.FindByAll(request.getParameter("hdiCvePersonal"));
											obElemento = vcPersonal.get(i);
											TVPERDatos vPERDatos = (TVPERDatos) obElemento;

											out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC", "Datos Personal", 4));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Primer Apellido:", "ECampo", "text", 10, 10, "",
													vPERDatos.getCApPaterno(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cApPaterno' value='" + vPERDatos.getCApPaterno()
													+ "'>");
											out.print(vEti.EtiCampo("EEtiqueta", "Segundo Apellido:", "ECampo", "text", 10, 10, "",
													vPERDatos.getCApMaterno(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cApMaterno' value='" + vPERDatos.getCApMaterno()
													+ "'>");
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Nombre(s):", "ECampo", "text", 10, 10, "",
													vPERDatos.getCNombre(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cNombre' value='" + vPERDatos.getCNombre() + "'>");
											out.print(vEti.EtiCampo("EEtiqueta", "Sexo:", "ECampo", "text", 10, 10, "",
													vPERDatos.getCSexo_DGIS(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cGenero' value='" + vPERDatos.getCSexo() + "'>");
											out.println("</tr>");
											out.println("<tr>");
											/*out.print(vEti.EtiCampoCS("EEtiqueta",
													"FECNAC (Fecha de Nacimiento):", "ECampo", "text", 10, 10,
													4, "", vPERDatos.getCDscFecNacimiento(), 0, "",
													"fMayus(this);", false, true, false));*/
											//System.out.println("vPERDatos.getCDscFecNacimientoNom024() = "+vPERDatos.getCDscFecNacimientoNom024());
											out.print(vEti.EtiCampoCS("EEtiqueta", "FECNAC (Fecha de Nacimiento):", "ECampo", "text", 8,
													8, 4, "", vPERDatos.getCDscFecNacimientoNom024(), 0, "", "fMayus(this);", false,
													true, false));
											out.println(" <input type='hidden' name='dtNacimiento' value='"
													+ vPERDatos.getCDscFecNacimientoNom024() + "'>");
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "RFC:", "ECampo", "text", 10, 10, "",
													vPERDatos.getCRFC(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cRFC' value='" + vPERDatos.getCRFC() + "'>");
											if (vPERDatos.getCCURP() != null) {
												out.print(vEti.EtiCampo("EEtiqueta", "CURP:", "ECampo", "text", 10, 10, "",
														vPERDatos.getCCURP(), 0, "", "fMayus(this);", false, true, false));
												out.println(" <input type='hidden' name='cCURP' value='" + vPERDatos.getCCURP() + "'>");
											}
											out.println("</tr>");

											//Nuevo parametro de Licencia
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Licencia:", "ECampo", "text", 10, 10, "",
													vPERDatos.getCLicencia(), 0, "", "fMayus(this);", false, true, false));
											out.println(
													" <input type='hidden' name='cLicencia' value='" + vPERDatos.getCLicencia() + "'>");
											if (vPERDatos.getCLicenciaInt() != null) {
												out.print(vEti.EtiCampo("EEtiqueta", "Licencia Int.:", "ECampo", "text", 10, 10, "",
														vPERDatos.getCLicenciaInt(), 0, "", "fMayus(this);", false, true, false));
												out.println(" <input type='hidden' name='cLicenciaInt' value='"
														+ vPERDatos.getCLicenciaInt() + "'>");
											}
											out.println("</tr>");

											//
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "País de Nacimiento:", "ECampo", "text", 10, 10, "",
													dPaisNac.FindBySAllNacionalidad(vPERDatos.getICvePais()), 0, "", "fMayus(this);",
													false, true, false));
											out.println(" <input type='hidden' name='iCvePaisNac' value='" + vPERDatos.getICvePaisNac()
													+ "'>");
											out.print(vEti.EtiCampo("EEtiqueta", "EDONAC (Lugar de Nacimiento):", "ECampo", "text", 10,
													10, "", vPERDatos.getCDscEstadoNac(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='iCveEstadoNac' value='"
													+ vPERDatos.getICveEstadoNac() + "'>");
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Expediente Anterior:", "ECampo", "text", 10, 10, 4,
													"", vPERDatos.getCExpediente(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cExpediente' value='" + vPERDatos.getCExpediente()
													+ "'>");
											out.println("</tr>");
											out.println("<tr>");

											out.println(vEti.TextoCS("EEtiquetaL", "Dirección", 4));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Calle:", "ECampo", "text", 10, 10, 4, "",
													vPERDatos.getCCalle(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cCalle' value='" + vPERDatos.getCCalle() + "'>");
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "No. Ext.:", "ECampo", "text", 25, 25, "",
													vPERDatos.getCNumExt(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cNumExt' value='" + vPERDatos.getCNumExt() + "'>");
											out.print(vEti.EtiCampo("EEtiqueta", "No. Int.:", "ECampo", "text", 25, 25, "",
													vPERDatos.getCNumInt(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cNumInt' value='" + vPERDatos.getCNumInt() + "'>");
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Colonia:", "ECampo", "text", 10, 10, "",
													vPERDatos.getCColonia(), 0, "", "fMayus(this);", false, true, false));
											out.println(
													" <input type='hidden' name='cColonia' value='" + vPERDatos.getCColonia() + "'>");
											out.print(vEti.EtiCampo("EEtiqueta", "C.P.:", "ECampo", "text", 10, 10, "",
													"" + vPERDatos.getICP(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='iCP' value='" + vPERDatos.getICP() + "'>");
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "País:", "ECampo", "text", 10, 10, "",
													vPERDatos.getCDscPais(), 0, "", "fMayus(this);", false, true, false));
											out.println(
													" <input type='hidden' name='iCvePais' value='" + vPERDatos.getICvePais() + "'>");
											out.print(vEti.EtiCampo("EEtiqueta", "EDO (Estado):", "ECampo", "text", 10, 10, "",
													vPERDatos.getCDscEstado(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='iCveEstado' value='" + vPERDatos.getICveEstado()
													+ "'>");
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "MUN (Municipio):", "ECampo", "text", 10, 10, "",
													vPERDatos.getCDscMunicipio(), 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='iCveMunicipio' value='"
													+ vPERDatos.getICveMunicipio() + "'>");

											if (vPERDatos.getICveLocalidad() > 0) {
												cWhere = " icvepais = " + vPERDatos.getICvePais() + " and icveentidadfed = "
														+ vPERDatos.getICveEstado() + " and icvemunicipio = "
														+ vPERDatos.getICveMunicipio() + " and icvelocalidad = "
														+ vPERDatos.getICveLocalidad();
												Localidad = dLocalidad.UbicaLocalidad(cWhere);
												iCveLocalidad = "" + vPERDatos.getICveLocalidad();
											} else {
												Localidad = "DESCONOCIDO";
												iCveLocalidad = "0";
											}

											out.print(vEti.EtiCampo("EEtiqueta", "LOC (Población):", "ECampo", "text", 10, 10, "",
													Localidad, 0, "", "fMayus(this);", false, true, false));
											out.println(" <input type='hidden' name='cCiudad' value='" + iCveLocalidad + "'>");

											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel.:", "ECampo", "text", 10, 10, 4, "",
													vPERDatos.getCTelefono(), 0, "", "fMayus(this);", false, true, false));
											out.println(
													" <input type='hidden' name='cTelefono' value='" + vPERDatos.getCTelefono() + "'>");
											out.println("</tr>");
											out.println("<tr>");

											TDGRLMdoTrans dModoTrans = new TDGRLMdoTrans();
											//Vector vModoTrans = new Vector();
											//vModoTrans = dModoTrans.findByAll("");
											out.println(vEti.TextoCS("ETablaSTC", "Trámite", 4));
											out.println("</tr>");
											out.println(vEti.Texto("EEtiqueta", "Modo de Transporte:"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCveMdoTrans",
													"llenaSLT(1,this.value," + request.getParameter("hdiCvePersonal")
															+ ",'',document.forms[0].iCvePuesto);",
													dModoTrans.findByAll(""), "iCveMdoTrans", "cDscMdoTrans", request, "0", true));
											out.println("</td>");

											out.println(vEti.Texto("EEtiqueta", "Categoría:"));
											out.println("<td class='ECampo'>");
											out.println(
													"<SELECT NAME='iCveCategoria' SIZE='1' onChange=\"llenaSLT(6,document.forms[0].iCveMdoTrans.value,this.value,"
															+ request.getParameter("hdiCvePersonal")
															+ ",document.forms[0].iCvePuesto);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");

											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Puesto:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCvePuesto' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");

											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Proceso:"));
											out.println(vEti.Texto("EEtiquetaL", "EPI"));
											out.println(vEti.Texto("EEtiqueta", "Motivo:"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCveMotivo", "",
													(Vector) AppCacheManager.getColl("GRLMotivo", "1"), "iCveMotivo", "cDscMotivo",
													request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											TDEPISituacion dEPISituacion = new TDEPISituacion();
											out.println(vEti.Texto("EEtiqueta", "Situación:"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveSituacion", "", dEPISituacion.FindByAll(),
													"iCveSituacion", "cDscSituacion", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(
													"<td class=\"EEtiqueta\">Observaciones:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
											out.print(
													"<td colspan='3'><textarea cols=\"80\" rows=\"5\" name=\"cObservacion\" value=\"\" onkeypress=\"fChgArea(this);\" onChange=\"fChgArea(this);\"></textarea></td>");
											//out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,5,"cObservacion","",0,"","",false,true,lCaptura));
											out.println("</tr>");

											////Datos Adicionales de las personas
											/*out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC", "Adicionales", 4));
											out.println("</tr>");
											
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel2.:",
													"ECampo", "text", 20, 20, 4, "cTelefono2", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLVIVIENDA dGRLVIVIENDA = new TDGRLVIVIENDA();
											out.println(vEti.Texto("EEtiqueta", "Vivienda"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveVivienda", "",
													dGRLVIVIENDA.FindByAll(), "iCveVivienda",
													"cConcepto", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLDISCAPACIDAD dGRLDISCAPACIDAD = new TDGRLDISCAPACIDAD();
											out.println(vEti.Texto("EEtiqueta",
													"Discapacidad por GRUPO:"));
											out.println("<td>");
											out.println(vEti
													.SelectOneRowSinTD2(
															"iCveGrupoD",
															"llenaSLT(8,this.value,'','',document.forms[0].iCveSubgrupoD);",
															dGRLDISCAPACIDAD.FindByAllG(),
															"iCveGrupoD", "cDscGrupoD", request,
															"0", true));
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta",
													"Discapacidad por SUBGRUPO:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveSubgrupoD'  Id='IdiCveSubgrupoD'  SIZE='1' onChange=\"llenaSLT(9,document.forms[0].iCveGrupoD.value,this.value,'',document.forms[0].iCveDiscapacidad);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Discapacidad:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveDiscapacidad' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLGPOETNICO dGRLGPOETNICO = new TDGRLGPOETNICO();
											out.println(vEti.Texto("EEtiqueta", "Grupo Étnico"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveGpoEtnico",
													"", dGRLGPOETNICO.FindByAll(), "iCveGpoEtnico",
													"cGpoEtnico", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLRELIGION dGRLRELIGION = new TDGRLRELIGION();
											out.println(vEti.Texto("EEtiqueta",
													"Grupos de Religiones:"));
											out.println("<td>");
											out.println(vEti
													.SelectOneRowSinTD2(
															"iCveGrupo",
															"llenaSLT(10,this.value,'','',document.forms[0].iCveSubgrupo);",
															dGRLRELIGION.FindByAllG(), "iCveGrupo",
															"cGrupo", request, "0", true));
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta",
													"Subgrupos de Religiones:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveSubgrupo' id='idiCveSubgrupo' SIZE='1' onChange=\"llenaSLT(11,document.forms[0].iCveGrupo.value,this.value,'',document.forms[0].iCveReligiones);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Religiones:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveReligiones' id='idiCveReligiones' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLTPOSANGRE dGRLTPOSANGRE = new TDGRLTPOSANGRE();
											out.println(vEti.Texto("EEtiqueta", "Tipo Sanguíneo"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveTpoSangre",
													"", dGRLTPOSANGRE.FindByAll(), "iCveTpoSangre",
													"cTpoSangre", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLNIVELSEC dGRLNIVELSEC = new TDGRLNIVELSEC();
											out.println(vEti.Texto("EEtiqueta",
													"Nivel socioeconómico"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveNivelSEC", "",
													dGRLNIVELSEC.FindByAll(), "iCveNivelSEC",
													"cNivelSEC", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLPARPOL dGRLPARPOL = new TDGRLPARPOL();
											out.println(vEti
													.Texto("EEtiqueta",
															"Afiliación o preferencia <br>por un partido político"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveParPol", "",
													dGRLPARPOL.FindByAll(), "iCveParPol",
													"cDscParPol", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLECIVIL dGRLECIVIL = new TDGRLECIVIL();
											out.println(vEti.Texto("EEtiqueta", "Estado civil"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveECivil", "",
													dGRLECIVIL.FindByAll(), "iCveECivil",
													"cECivil", request, "0", true));
											out.println("</td>");
											out.println("</tr>");*/
										} //no esta inhabilitado
									} // Alta al nuevo Usuario
									else {
										if (!bloqueaGeneraCita) {
											out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC", "Datos Personal", 4));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Primer Apellido:", "ECampo", "text", 25, 50,
													"cApPaterno", "", 0, "", "fMayus(this);", false, true, lCaptura));
											out.print(vEti.EtiCampo("EEtiqueta", "Segundo Apellido:", "ECampo", "text", 25, 50,
													"cApMaterno", "", 0, "", "fMayus(this);", false, true, lCaptura));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Nombre(s):", "ECampo", "text", 35, 80, "cNombre", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.println(vEti.Texto("EEtiqueta", "Sexo:"));
						%>
						<td class="EEtiquetaL">Hombre<input type="radio"
							name="cGenero" value="M" checked> Mujer<input
							type="radio" name="cGenero" value="F">
						</td>
						<%
							out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCSNom024("EEtiqueta", "FECNAC (Fecha de Nacimiento):", "ECampo",
													"text", 8, 8, 4, "dtNacimiento", "", 0, "", "fMayus(this);", false, true,
													lCaptura));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "RFC:", "ECampo", "text", 13, 13, "cRFC", "", 0, "",
													"fMayus(this);", false, true, lCaptura));
						%>
						<td class="EEtiqueta">CURP:</td>
						<td class="ECampo"><input type="text" size="18"
							maxlength="18" name="cCURP" value="" onBlur="fMayus(this);"
							onMouseOut="if (window.fOutField) window.fOutField();"
							onMouseOver="if (window.fOverField) window.fOverField(0);"><a
							href='http://consultas.curp.gob.mx/' target='_blank'>Consulta
								CURP</a></td>
						<%
							out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Licencia:", "ECampo", "text", 12, 12, "cLicencia", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.print(vEti.EtiCampo("EEtiqueta", "Licencia Int.:", "ECampo", "text", 10, 10,
													"cLicenciaInt", "", 0, "", "fMayus(this);", false, true, lCaptura));
											out.println("</tr>");

											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "NACORIGEN (Nacionalidad):"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCvePaisNac",
													"llenaSLT(2,this.value,'','',document.forms[0].iCveEstadoNac);",
													dPaisNac.FindByAllNacionalidad(), "iCvePais", "cNombre", request, "0", true));
											out.println("</td>");
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta", "EDONAC (Lugar de Nacimiento):"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveEstadoNac' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											//out.println("<tr>");
											//out.print(vEti.EtiCampoCS("EEtiqueta","Expediente:","ECampo","text",10,10,4,"cExpedAnt","",0,"","fMayus(this);",false,true,lCaptura));
											//out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC", "Dirección", 4));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Calle:", "ECampo", "text", 35, 50, 4, "cCalle", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "No. Ext.:", "ECampo", "text", 25, 25, "cNumExt", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.print(vEti.EtiCampo("EEtiqueta", "No. Int.:", "ECampo", "text", 25, 25, "cNumInt", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Colonia:", "ECampo", "text", 30, 30, "cColonia", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.print(vEti.EtiCampo("EEtiqueta", "C.P.:", "ECampo", "text", 5, 5, "iCP", "", 0, "",
													"fMayus(this);", false, true, lCaptura));
											out.println("</tr>");
											out.println("<tr>");
											TDPais dPais = new TDPais();
											out.println(vEti.Texto("EEtiqueta", "País:"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCvePais",
													"llenaSLT(2,this.value,'','',document.forms[0].iCveEstado);", dPais.FindByAll(),
													"iCvePais", "cNombre", request, "0", true));
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta", "EDO (Estado):"));
											out.println("<td class='ECampo'>");
											out.println(
													"<SELECT NAME='iCveEstado' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "MUN (Municipio):"));
											out.println("<td class='ECampo'>");
											out.println(
													"<SELECT NAME='iCveMunicipio' SIZE='1' onChange=\"llenaSLT(5,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,this.value,document.forms[0].iCveLocalidad);\" ");
											out.println("</SELECT>");
											out.println("</td>");

											out.println(vEti.Texto("EEtiqueta", "LOC (Población):"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveLocalidad' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											/*out.print(vEti.EtiCampo("EEtiqueta", "LOC (Población):",
													"ECampo", "text", 35, 50, "cCiudad", "", 0, "",
													"fMayus(this);", false, true, lCaptura));
											out.println("</tr>");*/
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel.:", "ECampo", "text", 20, 20, 4, "cTelefono",
													"", 0, "", "fMayus(this);", false, true, lCaptura));
											out.println("</tr>");
											out.println("<tr>");

											TDGRLMdoTrans dModoTrans = new TDGRLMdoTrans();
											//Vector vModoTrans = new Vector();
											//vModoTrans = dModoTrans.findByAll("");
											out.println(vEti.TextoCS("ETablaSTC", "Trámite", 4));
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Modo de Transporte:"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCveMdoTrans",
													"llenaSLT(1,this.value,'','',document.forms[0].iCveCategoria);",
													dModoTrans.findByAll(""), "iCveMdoTrans", "cDscMdoTrans", request, "0", true));
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta", "Categoría:"));
											out.println("<td class='ECampo'>");
											out.println(
													"<SELECT NAME='iCveCategoria' SIZE='1' onChange=\"llenaSLT(7,document.forms[0].iCveMdoTrans.value,this.value,'',document.forms[0].iCvePuesto);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");

											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Puesto:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCvePuesto' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Proceso:"));
											out.println(vEti.Texto("EEtiquetaL", "EPI"));
											out.println(vEti.Texto("EEtiqueta", "Motivo:"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCveMotivo", "",
													(Vector) AppCacheManager.getColl("GRLMotivo", "1"), "iCveMotivo", "cDscMotivo",
													request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											TDEPISituacion dEPISituacion = new TDEPISituacion();
											out.println(vEti.Texto("EEtiqueta", "Situación:"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveSituacion", "", dEPISituacion.FindByAll(),
													"iCveSituacion", "cDscSituacion", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(
													"<td class=\"EEtiqueta\">Observaciones:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
											out.print(
													"<td colspan='3'><textarea cols=\"80\" rows=\"5\" name=\"cObservacion\" value=\"\" onkeypress=\"fChgArea(this);\" onChange=\"fChgArea(this);\"></textarea></td>");
											//out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,5,"cObservacion","",0,"","",false,true,lCaptura));
											out.println("</tr>");

											////Datos Adicionales de las personas
											/*
											out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC", "Adicionales", 4));
											out.println("</tr>");
											
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel2.:",
													"ECampo", "text", 20, 20, 4, "cTelefono2", "",
													0, "", "fMayus(this);", false, true, lCaptura));
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLVIVIENDA dGRLVIVIENDA = new TDGRLVIVIENDA();
											out.println(vEti.Texto("EEtiqueta", "Vivienda"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveVivienda", "",
													dGRLVIVIENDA.FindByAll(), "iCveVivienda",
													"cConcepto", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLDISCAPACIDAD dGRLDISCAPACIDAD = new TDGRLDISCAPACIDAD();
											out.println(vEti.Texto("EEtiqueta",
													"Discapacidad por GRUPO:"));
											out.println("<td>");
											out.println(vEti
													.SelectOneRowSinTD2(
															"iCveGrupoD",
															"llenaSLT(8,this.value,'','',document.forms[0].iCveSubgrupoD);",
															dGRLDISCAPACIDAD.FindByAllG(),
															"iCveGrupoD", "cDscGrupoD", request,
															"0", true));
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta",
													"Discapacidad por SUBGRUPO2:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveSubgrupoD'  Id='IdiCveSubgrupoD'  SIZE='1' onChange=\"llenaSLT(9,document.forms[0].iCveGrupoD.value,this.value,'',document.forms[0].iCveDiscapacidad);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Discapacidad:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveDiscapacidad'  Id='IdiCveDiscapacidad' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLGPOETNICO dGRLGPOETNICO = new TDGRLGPOETNICO();
											out.println(vEti.Texto("EEtiqueta", "Grupo Étnico"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveGpoEtnico",
													"", dGRLGPOETNICO.FindByAll(), "iCveGpoEtnico",
													"cGpoEtnico", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLRELIGION dGRLRELIGION = new TDGRLRELIGION();
											out.println(vEti.Texto("EEtiqueta",
													"Grupos de Religiones:"));
											out.println("<td>");
											out.println(vEti
													.SelectOneRowSinTD2(
															"iCveGrupo",
															"llenaSLT(10,this.value,'','',document.forms[0].iCveSubgrupo);",
															dGRLRELIGION.FindByAllG(), "iCveGrupo",
															"cGrupo", request, "0", true));
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta",
													"Subgrupos de Religiones:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveSubgrupo' Id='IdiCveSubgrupo' SIZE='1' onChange=\"llenaSLT(11,document.forms[0].iCveGrupo.value,this.value,'',document.forms[0].iCveReligiones);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Religiones:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveReligiones' Id='IdiCveReligiones' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLTPOSANGRE dGRLTPOSANGRE = new TDGRLTPOSANGRE();
											out.println(vEti.Texto("EEtiqueta", "Tipo Sanguíneo"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveTpoSangre",
													"", dGRLTPOSANGRE.FindByAll(), "iCveTpoSangre",
													"cTpoSangre", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLNIVELSEC dGRLNIVELSEC = new TDGRLNIVELSEC();
											out.println(vEti.Texto("EEtiqueta",
													"Nivel socioeconómico"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveNivelSEC", "",
													dGRLNIVELSEC.FindByAll(), "iCveNivelSEC",
													"cNivelSEC", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLPARPOL dGRLPARPOL = new TDGRLPARPOL();
											out.println(vEti
													.Texto("EEtiqueta",
															"Afiliación o preferencia <br>por un partido político"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveParPol", "",
													dGRLPARPOL.FindByAll(), "iCveParPol",
													"cDscParPol", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLECIVIL dGRLECIVIL = new TDGRLECIVIL();
											out.println(vEti.Texto("EEtiqueta", "Estado civil"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveECivil", "",
													dGRLECIVIL.FindByAll(), "iCveECivil",
													"cECivil", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											 */
										}
									}

								} else {
									if (bs != null) {
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "Unidad Médica:", "ECampo", "text", 10, 10, "iCveUniMed",
												bs.getFieldValue("cDscUniMed", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println(" <input type='hidden' name='iCveUniMedA' value='"
												+ bs.getFieldValue("iCveUniMed", "&nbsp;").toString() + "'>");
										out.print(vEti.EtiCampo("EEtiqueta", "Módulo:", "ECampo", "text", 10, 10, "iCveModulo",
												bs.getFieldValue("cDscModulo", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println(" <input type='hidden' name='iCveModuloA' value='"
												+ bs.getFieldValue("iCveModulo", "&nbsp;").toString() + "'>");
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, "dtFecha",
												bs.getFieldValue("cDscDtFecha", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println(" <input type='hidden' name='dtFechaA' value='"
												+ bs.getFieldValue("cDscDtFecha", "&nbsp;").toString() + "'>");
										out.print(vEti.EtiCampo("EEtiqueta", "Hora:", "ECampo", "text", 10, 10, "cHora",
												bs.getFieldValue("cHora", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										if (request.getParameter("hdBoton").compareTo("Modificar") == 0) {
						%>

						<tr>
							<td class="ETabla">&nbsp;</td>
							<td class="ETabla">&nbsp;</td>
							<td class="EEtiqueta">Nueva Hora:</td>
							<td><select name="iHora" size="1">
									<%
										for (int i = 8; i <= 18; i++) {
															out.print("<option value = " + i + ">" + i + "</option>");
														}
									%>
							</select> <select name="iMinutos" size="1">
									<%
										for (int i = 0; i <= 45; i = i + 15) {
															if (i == 0) {
																out.print("<option value = " + i + ">00</option>");
															} else {
																out.print("<option value = " + i + ">" + i + "</option>");
															}

														}
									%>
							</select></td>
						</tr>
						<%
							}
										out.println("</tr>");
										/*out.println("<tr>");
										out.println(vEti.TextoCS("EEtiquetaL","Boton Buscar",4));
										out.println("</tr>");*/
										out.println("<tr>");
										// if (request.getParameter("hdBoton").compareTo("Modificar") == 0)
										out.print(vEti.EtiCampoCS("EEtiqueta", "Cve. Cita:", "ECampo", "text", 10, 10, 4, "iCveCita",
												bs.getFieldValue("iCveCita", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										out.println(" <input type='hidden' name='iCveCita' value='"
												+ bs.getFieldValue("iCveCita", "&nbsp;").toString() + "'>");
										//else
										//   out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
										out.println("</tr>");
										out.println("<tr>");
										out.println(vEti.TextoCS("ETablaSTC", "Datos Personal", 4));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "Primer Apellido:", "ECampo", "text", 10, 10, "cApPaterno",
												bs.getFieldValue("cApPaterno", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.print(vEti.EtiCampo("EEtiqueta", "Segundo Apellido:", "ECampo", "text", 10, 10,
												"cApMaterno", bs.getFieldValue("cApMaterno", "&nbsp;").toString(), 0, "",
												"fMayus(this);", false, true, false));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "Nombre(s):", "ECampo", "text", 10, 10, "cNombre",
												bs.getFieldValue("cNombre", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										/*out.print(vEti.EtiCampo("EEtiqueta", "Sexo:",
												"ECampo", "text", 10, 10, "cGenero", bs
														.getFieldValue("cGenero", "&nbsp;")
														.toString(), 0, "", "fMayus(this);",
												false, true, false));*/
										out.print(vEti.EtiCampo("EEtiqueta", "Sexo:", "ECampo", "text", 10, 10, "cSexo_DGIS",
												bs.getFieldValue("cSexo_DGIS", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println("</tr>");
										out.println("<tr>");
										/*out.print(vEti.EtiCampoCS("EEtiqueta",
												"FECNAC (Fecha de Nacimiento):", "ECampo", "text", 10, 10,
												4, "dtNacimiento",
												bs.getFieldValue("cDscDtFechaNac", "&nbsp;")
														.toString(), 0, "", "fMayus(this);",
												false, true, false));*/
										out.print(vEti.EtiCampoCS("EEtiqueta", "FECNAC (Fecha de Nacimiento):", "ECampo", "text", 10,
												10, 4, "dtNacimiento", bs.getFieldValue("cDscDtFechaNacNom024", "&nbsp;").toString(), 0,
												"", "fMayus(this);", false, true, false));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "RFC:", "ECampo", "text", 10, 10, "cRFC",
												bs.getFieldValue("cRFC", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										out.print(vEti.EtiCampo("EEtiqueta", "CURP:", "ECampo", "text", 10, 10, "cCURP",
												bs.getFieldValue("cCURP", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										out.println("</tr>");

										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "Licencia:", "ECampo", "text", 10, 10, "cLicencia",
												bs.getFieldValue("cLicencia", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										out.print(vEti.EtiCampo("EEtiqueta", "Licencia Int.:", "ECampo", "text", 10, 10, "cLicenciaInt",
												bs.getFieldValue("cLicenciaInt", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println("</tr>");

										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "NACORIGEN (Nacionalidad):", "ECampo", "text", 10, 10,
												"iCvePaisNac",
												dPaisNac.FindBySAllNacionalidad(
														Integer.parseInt(bs.getFieldValue("iCvePaisNac", "").toString())),
												0, "", "fMayus(this);", false, true, false));
										out.print(vEti.EtiCampo("EEtiqueta", "EDONAC (Lugar de Nacimiento):", "ECampo", "text", 10, 10,
												"iCveEstadoNac", bs.getFieldValue("cDscEstadoNac", "&nbsp;").toString(), 0, "",
												"fMayus(this);", false, true, false));
										out.println("</tr>");
										out.println("<tr>");

										out.print(vEti.EtiCampoCS("EEtiqueta", "Expediente:", "ECampo", "text", 10, 10, 4, "cExpedAnt",
												bs.getFieldValue("cExpediente", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println("</tr>");

										//Nuevos datos para pais y estado de nacimiento

										if (request.getParameter("hdBoton").compareTo("Modificar") == 0) {
											out.println(vEti.TextoCS("ETablaSTC", "Nuevos datos de Nacimiento", 4));

											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "NACORIGEN (Nacionalidad):"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCvePaisNac",
													"llenaSLT(2,this.value,'','',document.forms[0].iCveEstadoNac);",
													dPaisNac.FindByAllNacionalidad(), "iCvePais", "cNombre", request, "0", true));
											out.println("</td>");
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta", "EDONAC (Lugar de Nacimiento):"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveEstadoNac' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
										}

										out.println("<tr>");
										out.println(vEti.TextoCS("ETablaSTC", "Dirección", 4));
										out.println("</tr>");
										/*out.println("<tr>");
										out.print(vEti.EtiCampoCS("EEtiqueta","Cambio de  Direcci�n","ECampo","text",10,10,4,"lCambioDir", bs.getFieldValue("lCambioDir","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
										out.println("</tr>");*/
										/*out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
										out.println("</tr>");*/
										if (request.getParameter("hdBoton").compareTo("Modificar") != 0) {
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Calle:", "ECampo", "text", 10, 10, 4, "cCalle",
													bs.getFieldValue("cCalle", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "No. Ext.:", "ECampo", "text", 25, 25, "cNumExt",
													bs.getFieldValue("cNumExt", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, false));
											out.print(vEti.EtiCampo("EEtiqueta", "No. Int.:", "ECampo", "text", 25, 25, "cNumInt",
													bs.getFieldValue("cNumInt", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Colonia:", "ECampo", "text", 10, 10, "cColonia",
													bs.getFieldValue("cColonia", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, false));
											out.print(vEti.EtiCampo("EEtiqueta", "C.P.:", "ECampo", "text", 10, 10, "iCP",
													bs.getFieldValue("iCP", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
													false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "País:", "ECampo", "text", 10, 10, "iCvePais",
													bs.getFieldValue("cDscPais", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, false));
											out.print(vEti.EtiCampo("EEtiqueta", "EDO (Estado):", "ECampo", "text", 10, 10,
													"iCveEstado", bs.getFieldValue("cDscEstado", "&nbsp;").toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "MUN (Municipio):", "ECampo", "text", 10, 10,
													"iCveMunicipio", bs.getFieldValue("cDscMunicipio", "&nbsp;").toString(), 0, "",
													"fMayus(this);", false, true, false));

											if (bs.getFieldValue("cCiudad", "").toString().equals("0")) {
												Localidad = "DESCONOCIDO";
											} else {
												if (bs.getFieldValue("cCiudad", "").toString().equals("")) {
													Localidad = "DESCONOCIDO";
												} else {
													cWhere = " icvepais = " + bs.getFieldValue("iCvePais", "").toString()
															+ " and icveentidadfed = " + bs.getFieldValue("iCveEstado", "").toString()
															+ " and icvemunicipio = " + bs.getFieldValue("iCveMunicipio", "").toString()
															+ " and icvelocalidad = "
															+ bs.getFieldValue("iCveLocalidad", "").toString();
													Localidad = dLocalidad.UbicaLocalidad(cWhere);
												}
											}

											out.print(vEti.EtiCampo("EEtiqueta", "LOC (Población):", "ECampo", "text", 10, 10,
													"cCiudad", Localidad, 0, "", "fMayus(this);", false, true, false));
											out.println("</tr>");

											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel.:", "ECampo", "text", 10, 10, 4, "cTel",
													bs.getFieldValue("cTelefono", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, false));
											out.println("</tr>");
										}

										//habilitar Modificacion de direccion
										if (request.getParameter("hdBoton").compareTo("Modificar") == 0) {
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "País:", "ECampo", "text", 10, 10, "iCvePais",
													bs.getFieldValue("cDscPais", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, false));
											out.print(vEti.EtiCampo("EEtiqueta", "EDO (Estado):", "ECampo", "text", 10, 10,
													"iCveEstado", bs.getFieldValue("cDscEstado", "&nbsp;").toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "MUN (Municipio):", "ECampo", "text", 10, 10,
													"iCveMunicipio", bs.getFieldValue("cDscMunicipio", "&nbsp;").toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");

											out.println(vEti.TextoCS("ETablaSTC", "Nuevos datos de Dirección", 4));
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Calle:", "ECampo", "text", 35, 50, 4, "cCalle",
													bs.getFieldValue("cCalle", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, lCaptura));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "No. Ext.:", "ECampo", "text", 25, 25, "cNumExt",
													bs.getFieldValue("cNumExt", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, lCaptura));
											out.print(vEti.EtiCampo("EEtiqueta", "No. Int.:", "ECampo", "text", 25, 25, "cNumInt",
													bs.getFieldValue("cNumInt", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, lCaptura));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Colonia:", "ECampo", "text", 30, 30, "cColonia",
													bs.getFieldValue("cColonia", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, lCaptura));
											out.print(vEti.EtiCampo("EEtiqueta", "C.P.:", "ECampo", "text", 5, 5, "iCP",
													bs.getFieldValue("iCP", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
													lCaptura));
											out.println("</tr>");

											TDPais dPais = new TDPais();
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "País:"));
											out.println("<td>");
											out.println(vEti.SelectOneRowSinTD2("iCvePais",
													"llenaSLT(2,this.value,'','',document.forms[0].iCveEstado);", dPais.FindByAll(),
													"iCvePais", "cNombre", request, "0", true));
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta", "EDO (Estado):"));
											out.println("<td class='ECampo'>");
											out.println(
													"<SELECT NAME='iCveEstado' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "MUN (Municipio):"));
											out.println("<td class='ECampo'>");
											out.println(
													"<SELECT NAME='iCveMunicipio' SIZE='1' onChange=\"llenaSLT(5,document.forms[0].iCvePais.value,document.forms[0].iCveEstado.value,this.value,'',document.forms[0].iCveLocalidad);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "LOC (Población):"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveLocalidad' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											/*out.print(vEti.EtiCampo("EEtiqueta", "LOC (Población):",
													"ECampo", "text", 35, 50, "cCiudad", bs
															.getFieldValue("cCiudad", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, lCaptura));*/
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel.:", "ECampo", "text", 20, 20, 4, "cTelefono",
													bs.getFieldValue("cTelefono", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
													true, lCaptura));
											out.println("</tr>");
										}

										out.println("<tr>");
										out.println(vEti.TextoCS("ETablaSTC", "Trámite", 4));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "Modo de Transporte:", "ECampo", "text", 10, 10,
												"iCveMdoTrans", bs.getFieldValue("cDscMdoTransporte", "&nbsp;").toString(), 0, "",
												"fMayus(this);", false, true, false));
										out.print(vEti.EtiCampo("EEtiqueta", "Categoría:", "ECampo", "text", 10, 10, "iCveCategoría",
												bs.getFieldValue("cDscCategoria", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampoCS("EEtiqueta", "Puesto:", "ECampo", "text", 10, 10, 4, "iCvePuesto",
												bs.getFieldValue("cDscPuesto", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println("</tr>");
										out.println("<tr>");
										out.println(vEti.Texto("EEtiqueta", "Proceso:"));
										out.println(vEti.Texto("EEtiquetaL", "EPI"));
										out.print(vEti.EtiCampo("EEtiqueta", "Motivo:", "ECampo", "text", 10, 10, "iCveMotivo",
												bs.getFieldValue("cDscMotivo", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampoCS("EEtiqueta", "Situación:", "ECampo", "text", 10, 10, 4,
												"iCveSituacion", bs.getFieldValue("cDscSituacion", "&nbsp;").toString(), 0, "",
												"fMayus(this);", false, true, false));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampoCS("EEtiqueta", "Observación:", "ECampo", "text", 10, 10, 4,
												"cObservacion", bs.getFieldValue("cObservacion", "&nbsp;").toString(), 0, "",
												"fMayus(this);", false, true, false));
										out.println("</tr>");

										////Datos Adicionales de las personas
										if (request.getParameter("hdBoton").compareTo("Modificar") != 0) {
											/*
											out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC",
													"Adicionales", 4));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel2.:",
													"ECampo", "text", 10, 10, 4, "cTel",
													bs.getFieldValue("cTelefono2", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Vivienda:",
													"ECampo", "text", 10, 10, 4,
													"iCveVivienda",
													bs.getFieldValue("cConcepto", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS(
													"EEtiqueta",
													"Discapacidad:",
													"ECampo",
													"text",
													10,
													10,
													4,
													"iCveDiscapacidad",
													bs.getFieldValue("cDcsDiscapacidad",
															"&nbsp;").toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta",
													"Grupo Étnico:", "ECampo", "text", 10, 10,
													4, "iCveGpoEtnico",
													bs.getFieldValue("cGpoEtnico", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta",
													"Religiones:", "ECampo", "text", 10, 10, 4,
													"iCveReligiones",
													bs.getFieldValue("cCodDsc", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta",
													"Tipo Sanguíneo:", "ECampo", "text", 10,
													10, 4, "iCveTpoSangre",
													bs.getFieldValue("cTpoSangre", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta",
													"Nivel socioeconómico:", "ECampo", "text",
													10, 10, 4, "iCveNivelSEC",
													bs.getFieldValue("cNivelSec", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti
													.EtiCampoCS(
															"EEtiqueta",
															"Afiliación o preferencia <br>por un partido político:",
															"ECampo",
															"text",
															10,
															10,
															4,
															"iCveParPol",
															bs.getFieldValue("cDscParPol",
																	"&nbsp;").toString(), 0,
															"", "fMayus(this);", false, true,
															false));
											out.println("</tr>");
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta",
													"Estado civil:", "ECampo", "text", 10, 10,
													4, "iCveECivil",
													bs.getFieldValue("cECivil", "&nbsp;")
															.toString(), 0, "",
													"fMayus(this);", false, true, false));
											out.println("</tr>");*/
										}

										if (request.getParameter("hdBoton").compareTo("Modificar") == 0) {
											/*
											out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC",
													"Adicionales", 4));
											out.println("</tr>");
											
											out.println("<tr>");
											out.print(vEti.EtiCampoCS("EEtiqueta", "Tel2.:",
													"ECampo", "text", 20, 20, 4, "cTelefono2",
													"", 0, "", "fMayus(this);", false, true,
													lCaptura));
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLVIVIENDA dGRLVIVIENDA = new TDGRLVIVIENDA();
											out.println(vEti.Texto("EEtiqueta", "Vivienda"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveVivienda",
													"", dGRLVIVIENDA.FindByAll(),
													"iCveVivienda", "cConcepto", request, "0",
													true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLDISCAPACIDAD dGRLDISCAPACIDAD = new TDGRLDISCAPACIDAD();
											out.println(vEti.Texto("EEtiqueta",
													"Discapacidad por GRUPO:"));
											out.println("<td>");
											out.println(vEti
													.SelectOneRowSinTD2(
															"iCveGrupoD",
															"llenaSLT(8,this.value,'','',document.forms[0].iCveSubgrupoD);",
															dGRLDISCAPACIDAD.FindByAllG(),
															"iCveGrupoD", "cDscGrupoD",
															request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta",
													"Discapacidad por SUBGRUPO:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveSubgrupoD'  Id='IdiCveSubgrupoD'  SIZE='1' onChange=\"llenaSLT(9,document.forms[0].iCveGrupoD.value,this.value,'',document.forms[0].iCveDiscapacidad);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti
													.Texto("EEtiqueta", "Discapacidad:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveDiscapacidad' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLGPOETNICO dGRLGPOETNICO = new TDGRLGPOETNICO();
											out.println(vEti.Texto("EEtiqueta", "Grupo Étnico"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2(
													"iCveGpoEtnico", "",
													dGRLGPOETNICO.FindByAll(), "iCveGpoEtnico",
													"cGpoEtnico", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLRELIGION dGRLRELIGION = new TDGRLRELIGION();
											out.println(vEti.Texto("EEtiqueta",
													"Grupos de Religiones:"));
											out.println("<td>");
											out.println(vEti
													.SelectOneRowSinTD2(
															"iCveGrupo",
															"llenaSLT(10,this.value,'','',document.forms[0].iCveSubgrupo);",
															dGRLRELIGION.FindByAllG(),
															"iCveGrupo", "cGrupo", request,
															"0", true));
											out.println("</td>");
											out.println(vEti.Texto("EEtiqueta",
													"Subgrupos de Religiones:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveSubgrupo' id='idiCveSubgrupo' SIZE='1' onChange=\"llenaSLT(11,document.forms[0].iCveGrupo.value,this.value,'',document.forms[0].iCveReligiones);\" ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											out.println("<tr>");
											out.println(vEti.Texto("EEtiqueta", "Religiones:"));
											out.println("<td class='ECampo'>");
											out.println("<SELECT NAME='iCveReligiones' id='idiCveReligiones' SIZE='1' ");
											out.println("</SELECT>");
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLTPOSANGRE dGRLTPOSANGRE = new TDGRLTPOSANGRE();
											out.println(vEti.Texto("EEtiqueta",
													"Tipo Sanguíneo"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2(
													"iCveTpoSangre", "",
													dGRLTPOSANGRE.FindByAll(), "iCveTpoSangre",
													"cTpoSangre", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLNIVELSEC dGRLNIVELSEC = new TDGRLNIVELSEC();
											out.println(vEti.Texto("EEtiqueta",
													"Nivel socioeconómico"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveNivelSEC",
													"", dGRLNIVELSEC.FindByAll(),
													"iCveNivelSEC", "cNivelSEC", request, "0",
													true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLPARPOL dGRLPARPOL = new TDGRLPARPOL();
											out.println(vEti
													.Texto("EEtiqueta",
															"Afiliación o preferencia <br>por un partido político"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveParPol",
													"", dGRLPARPOL.FindByAll(), "iCveParPol",
													"cDscParPol", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											
											out.println("<tr>");
											TDGRLECIVIL dGRLECIVIL = new TDGRLECIVIL();
											out.println(vEti.Texto("EEtiqueta", "Estado civil"));
											out.println("<td class='ECampo' colspan='3' >");
											out.println(vEti.SelectOneRowSinTD2("iCveECivil",
													"", dGRLECIVIL.FindByAll(), "iCveECivil",
													"cECivil", request, "0", true));
											out.println("</td>");
											out.println("</tr>");
											 */
										}

									} else {
						%>
						<p>&nbsp;</p>
						<table border="1" class="ETablaInfo" align="center"
							cellspacing="0" cellpadding="3">
							<%
								// Inicio de Datos
							%>
							<%
								out.println("<tr>");
											out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje",
													"No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true,
													false));
											out.println("</tr>");
											out.println("</table>");
										}
									}
							%>
							<tr>
							</tr>
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
					</form> <br> <br> <br>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%
	vEntorno.clearListaImgs();
%>
</html>

