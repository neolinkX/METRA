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
	 * Clase:pg070108001CFG
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
	pg070108001CFG clsConfig = new pg070108001CFG(); // modificar

	TEntorno vEntorno = new TEntorno();
	vEntorno.setNumModulo(07);
	vEntorno.setOnLoad("fOnLoad();");
	vEntorno.setNombrePagina("pg070108001.jsp"); // modificar
	vEntorno.setArchFuncs("FValida");
	vEntorno.setArchTooltips("07_Tooltips");
	vEntorno.setMetodoForm("POST");
	vEntorno.setActionForm("pg070108001.jsp\" target=\"FRMCuerpo"); // modificar
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
	bloqueaGeneraCita = false;
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

        var newPlacaToraxFile;
        var newImagenServicioFile;
        function makenewImagenServicioFile() {
            if (!newImagenServicioFile || newImagenServicioFile.closed) {
                newImagenServicioFile = window.open("SubirVoucher.jsp?"
                      +"iCveExpediente="+<%=request.getParameter("hdiCvePersonal")%>
                      +"&iNumExamen="+<%=0%>
                      +"&iCveServicio="+<%=0%>
                      +"&iCveRama="+<%=0%>
                      +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,scrollbars=0,menubar=0");
                newImagenServicioFile.focus( );
            } else if (newImagenServicioFile.focus) {
                newImagenServicioFile.focus( );
            }
        }


          function showPlacasToraxFiles() {
            if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
                newPlacaToraxFile = window.open("./MostrarVoucher.jsp?"
                      +"iCveExpediente="+<%=request.getParameter("hdiCvePersonal")%>
                      +"&iNumExamen="+<%=0%>
                      +"&iCveMulta="+<%=request.getParameter("iCveMulta")%>
                      +"&iCveServicio="+<%=0%>
                      +"&iCveRama="+<%=0%>
                      +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,resizable=yes,menubar=0,scrollbars=yes");
                newPlacaToraxFile.focus( );
            } else if (newPlacaToraxFile.focus) {
                newPlacaToraxFile.focus( );
            }
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
                   cVCaract -- Caracter a 
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
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "calendario.js"%>"></SCRIPT>
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
							<td colspan="9" class="ETablaT">Búsqueda de Multas</td>
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
								/*out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, "dtFecha", cHoy, 0, "",
										"fMayus(this);", false, true, true, request));
*/
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
										//out.print(vEti.clsAnclaTexto("EAncla", "Cargar Información", "JavaScript:fInhabilita();","Cargar Información2...", ""));
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
							<td colspan="4" class="ETablaT">Registro de Multas</td>
						</tr>
						<tr>

							<%
								}
							%>

							<%
								if (lNuevo) {
										
										out.println("<tr>");
										out.println("<td colspan='4' align='center'>");
										out.print(vEti.clsAnclaTexto("EEtiqueta", "Buscar Persona", "javascript:fSelPer2();", "Buscar")
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
								//Obteniendo Multa
					               Multas CalculaMulta = new Multas();
					               int Multa = CalculaMulta.ObteniendoMontodeLaMulta(Integer.parseInt(request.getParameter("hdiCvePersonal")));
					               
					               		if(Multa>0){
												out.println("<input type='hidden' name='iCvePersonal' value='"
														+ request.getParameter("hdiCvePersonal") + "'>");
												out.println("<input type='hidden' name='iCveExpediente' value='"
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
												out.print(vEti.EtiCampo("EEtiqueta", "Primer Apellido1:", "ECampo", "text", 10, 10, "",
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
												out.print(vEti.EtiCampo("EEtiqueta", "RFC1:", "ECampo", "text", 10, 10, "",
														vPERDatos.getCRFC(), 0, "", "fMayus(this);", false, true, false));
												out.println(" <input type='hidden' name='cRFC' value='" + vPERDatos.getCRFC() + "'>");
												if (vPERDatos.getCCURP() != null) {
													out.print(vEti.EtiCampo("EEtiqueta", "CURP:", "ECampo", "text", 10, 10, "",
															vPERDatos.getCCURP(), 0, "", "fMayus(this);", false, true, false));
													out.println(" <input type='hidden' name='cCURP' value='" + vPERDatos.getCCURP() + "'>");
												}
												out.println("</tr>");
												
												out.println("<tr>");
												out.println(
														"<td class=\"EEtiqueta\">Cantidad1:</td>");
												out.print(
														"<td colspan='1'><input type:\"text\" name=\"cCantidad\" value=\"\" onkeypress=\"fChgArea(this);\" onChange=\"fChgArea(this);\"></td>");
												out.println(
														"<td class=\"EEtiqueta\">Tarifa1::</td>");
												out.print(
														"<td colspan='1'><input type:\"text\" name=\"cTarifa\" value=\"\" onkeypress=\"fChgArea(this);\" onChange=\"fChgArea(this);\"></td>");
												
												out.println("</tr>");
												
												out.println("<tr>");
												//out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha","&nbsp;",0,"","fMayus(this);",false,true, lCaptura,request));
												out.print(vEti.EtiCampo("EEtiqueta", "Cve. Multa:", "ECampo", "text", 10, 10, "iCveCita", "&nbsp;", 0,
														"", "fMayus(this);", false, true, false));
												out.println("</tr>");
												out.println("<tr>");
												out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, "dtFecha", cHoy, 0, "",
														"fMayus(this);", false, true, true, request));
												out.println(vEti.Texto("EEtiqueta", "Hora:"));
												%>
												<td class="ETabla"><select name="iHora" size="1">
														<%
															for (int x = 0; x <= 24; x++) {
																		out.print("<option value = " + x + ">" + x + "</option>");
																	}
														%>
												</select> <select name="iMinutos" size="1">
														<%
															for (int y = 0; y <= 59; y = y + 1) {
																		if (y == 0) {
																			out.print("<option value = " + y + ">00</option>");
																		} else {
																			out.print("<option value = " + y + ">" + y + "</option>");
																		}
	
																	}
														%>
												</select></td>
												<%
												out.println("</tr>");
												out.println("<tr>");
												out.println("<td class=\"EEtiqueta\"></td>");
												out.println("<td class=\"EEtiqueta\">");
												out.println("<br>");
												out.println(vEti.clsAnclaTexto("EAncla", "Cargar archivo de imagen",
														"JavaScript: makenewImagenServicioFile();", "Subir imagenes relacionadas"));
												out.println("</td>");
												out.println("<td>");
												out.println("<br>");
												out.print(vEti.clsAnclaTexto("EAncla", "Mostrar archivos de imagenes",
														"JavaScript:showPlacasToraxFiles();", "Ver imagenes relacionadas"));
												out.println("</td>");
												out.println("</tr>");
												
												
						               		}///Validando Multa
						               		else{
						               			out.println("<tr>");
												out.println(vEti.TextoCS("ETablaSTC", "El expediente "+request.getParameter("hdiCvePersonal")+" no es merecedor a una multa", 4));
												out.println("</tr>");
												
												////Verificar que el usuario no contenga multas////
												TDEXPMultas dEXPMultas = new TDEXPMultas();
												Vector vcMultas = new Vector();
												TVEXPMulta vMultas;
												vcMultas = dEXPMultas.FindByAllHist(request.getParameter("hdiCvePersonal"));
												if(vcMultas.size() > 0){ ////Existen Historicos de Multas
													%>
													<tr>
														<td colspan="4" class="ETablaT">Historico de Multas</td>
													<tr>
													</tr>
													<%
													 for(int i=0;i<vcMultas.size();i++){
														 vMultas = (TVEXPMulta) vcMultas.get(i);
															%>
															<tr>
															<td colspan="4" class="ETablaT">Multa #<%=vMultas.getICveMulta()%></td>
															<tr>
														</tr>
														<%
														out.println("<tr>");
														out.println(vEti.TextoCS("ETablaSTC", "Datos Personal", 4));
														out.println("</tr>");
														out.println("<tr>");
														out.print(vEti.EtiCampo("EEtiqueta", "Primer Apellido:", "ECampo", "text", 10, 10, "cApPaterno",
																vMultas.getCApPaterno(), 0, "", "fMayus(this);", false,
																true, false));
														out.print(vEti.EtiCampo("EEtiqueta", "Segundo Apellido:", "ECampo", "text", 10, 10,
																"cApMaterno", vMultas.getCApMaterno(), 0, "",
																"fMayus(this);", false, true, false));
														out.println("</tr>");
														out.println("<tr>");
														out.print(vEti.EtiCampo("EEtiqueta", "Nombre(s):", "ECampo", "text", 10, 10, "cNombre",
																vMultas.getCNombre(), 0, "", "fMayus(this);", false, true,
																false));
														
														out.print(vEti.EtiCampo("EEtiqueta", "Sexo:", "ECampo", "text", 10, 10, "cSexo_DGIS",
																vMultas.getCGenero(), 0, "", "fMayus(this);", false,
																true, false));
														out.println("</tr>");
														out.println("<tr>");
														// if (request.getParameter("hdBoton").compareTo("Modificar") == 0)
														out.print(vEti.EtiCampoCS("EEtiqueta", "Cve. Cita:", "ECampo", "text", 10, 10, 4, "iCveCita",
																vMultas.getICveMulta()+"", 0, "", "fMayus(this);", false, true,
																false));
														out.println(" <input type='hidden' name='iCveMulta' value='"
																+ vMultas.getICveMulta() + "'>");
														//else
														//   out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
														out.println("</tr>");
														
														out.println("<tr>");
													
														out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha del Pago", "ECampo", "text", 10,
																10, 4, "dtNacimiento", vMultas.getTRealizoPago()+"", 0,
																"", "fMayus(this);", false, true, false));
														out.println("</tr>");
														out.println("<tr>");
														out.print(vEti.EtiCampo("EEtiqueta", "Cantidad:", "ECampo", "text", 10, 10, "cRFC",
																vMultas.getICantidad()+"", 0, "", "fMayus(this);", false, true,
																false));
														out.print(vEti.EtiCampo("EEtiqueta", "Tarifa:", "ECampo", "text", 10, 10, "cCURP",
																vMultas.getITarifa()+"", 0, "", "fMayus(this);", false, true,
																false));
														out.println("</tr>");
														out.println("<tr>");
														out.println("<td class=\"EEtiqueta\"></td>");
														out.println("<td class=\"EEtiqueta\">");
														out.println("<br>");
														out.print(vEti.clsAnclaTexto("EAncla", "Mostrar archivos de imagenes",
																"JavaScript:showPlacasToraxFiles();", "Ver imagenes relacionadas"));
														out.println("</td>");
														out.println("</tr>");
														
									               		}

							               		}////Existen Historicos de Multas
											
						               		}
										} //no esta inhabilitado
									} // Alta al nuevo Usuario
									else {
										if (!bloqueaGeneraCita) {
											out.println("<tr>");
											out.println(vEti.TextoCS("ETablaSTC", "Datos Personal", 4));
											out.println("</tr>");
											out.println("<tr>");
											
											

											
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
										/*out.print(vEti.EtiCampo("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, "dtFecha",
												bs.getFieldValue("cDscDtFecha", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));*/
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
										for (int a = 8; a <= 18; a++) {
															out.print("<option value = " + a + ">" + a + "</option>");
														}
									%>
							</select> <select name="iMinutos" size="1">
									<%
										for (int b = 0; b <= 45; b = b + 15) {
															if (b == 0) {
																out.print("<option value = " + b + ">00</option>");
															} else {
																out.print("<option value = " + b + ">" + b + "</option>");
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
										
										out.print(vEti.EtiCampo("EEtiqueta", "Sexo:", "ECampo", "text", 10, 10, "cSexo_DGIS",
												bs.getFieldValue("cSexo", "&nbsp;").toString(), 0, "", "fMayus(this);", false,
												true, false));
										out.println("</tr>");
										out.println("<tr>");
										// if (request.getParameter("hdBoton").compareTo("Modificar") == 0)
										out.print(vEti.EtiCampoCS("EEtiqueta", "Cve. Cita:", "ECampo", "text", 10, 10, 4, "iCveCita",
												bs.getFieldValue("iCveMulta", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										out.println(" <input type='hidden' name='iCveMulta' value='"
												+ bs.getFieldValue("iCveMulta", "&nbsp;").toString() + "'>");
										//else
										//   out.print(vEti.EtiCampoCS("EEtiqueta","Cve. Cita:","ECampo","text",10,10,4,"iCveCita", bs.getFieldValue("iCveCita","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
										out.println("</tr>");
										
										out.println("<tr>");
									
										out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha del Pago", "ECampo", "text", 10,
												10, 4, "dtNacimiento", bs.getFieldValue("tRealizoPago", "&nbsp;").toString(), 0,
												"", "fMayus(this);", false, true, false));
										out.println("</tr>");
										out.println("<tr>");
										out.print(vEti.EtiCampo("EEtiqueta", "Cantidad:", "ECampo", "text", 10, 10, "cRFC",
												bs.getFieldValue("iCantidad", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										out.print(vEti.EtiCampo("EEtiqueta", "Tarifa:", "ECampo", "text", 10, 10, "cCURP",
												bs.getFieldValue("iTarifa", "&nbsp;").toString(), 0, "", "fMayus(this);", false, true,
												false));
										out.println("</tr>");
										out.println("<tr>");
										out.println("<td class=\"EEtiqueta\"></td>");
										out.println("<td class=\"EEtiqueta\">");
										out.println("<br>");
										out.print(vEti.clsAnclaTexto("EAncla", "Mostrar archivos de imagenes",
												"JavaScript:showPlacasToraxFiles();", "Ver imagenes relacionadas"));
										out.println("</td>");
										out.println("</tr>");



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

