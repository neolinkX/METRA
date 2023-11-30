<%/**
             * @author AG SA
             * Clase: pg070104022
             */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.cis.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>
<html>
    <%            pg070104022CFG clsConfig = new pg070104022CFG();                 // modificar

                TEntorno vEntorno = new TEntorno();
                vEntorno.setNumModulo(07);
                vEntorno.setNombrePagina("pg070104022.jsp");                    // modificar
                vEntorno.setArchTooltips("07_Tooltips");
                vEntorno.setOnLoad("fOnLoad();");
                vEntorno.setArchFuncs("FValida");
                vEntorno.setMetodoForm("POST");
                vEntorno.setActionForm("pg070104022.jsp\" target=\"FRMCuerpo"); // modificar
                vEntorno.setUrlLogo("Acerca");
                vEntorno.setBtnPrincVisible(true);
                vEntorno.setArchFCatalogo("FFiltro");
                vEntorno.setArchAyuda(vEntorno.getNombrePagina());

                String cCatalogo = "pg070104022.jsp";       // modificar
                String cOperador = "1";                   // modificar
                String cDscOrdenar = "Grupo|Inicio vigencia|Fin vigencia|Vigente|";    // modificar
                String cCveOrdenar = "cDscGrupo|dtInicio|dtFin|lVigente|";  // modificar
                String cDscFiltrar = "Inicio vigencia|Fin vigencia|";    // modificar
                String cCveFiltrar = "p.dtInicio|p.dtFin|";  // modificar
                String cTipoFiltrar = "5|5|";                // modificar 7/8
                boolean lFiltros = false;                  // modificar
                boolean lIra = true;                  // modificar
                String cEstatusIR = "Imprimir";            // modificar

                // LLamado al Output Header
                TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
                int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
                int iNumRowsSec = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

                TError vErrores = new TError();
                StringBuffer sbErroresAcum = new StringBuffer();
                TEtiCampo vEti = new TEtiCampo();

                clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

                String cPaginas = clsConfig.getPaginas();
                String cDscPaginas = clsConfig.getDscPaginas();
                PageBeanScroller bs = clsConfig.getBeanScroller();
                String cUpdStatus = "Hidden";
                String cNavStatus = "Hidden";//clsConfig.getNavStatus();
                String cOtroStatus = clsConfig.getOtroStatus();
                String cCanWrite = "No";
                String cSaveAction = "Guardar";
                String cDeleteAction = "BorrarB";
                String cClave = "";
                String cPosicion = "";
                int ConHuella = 0;
                int IndexServ = 0;
                String ServActual = "";
                String ServIndex = "";

                int iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed") == null || request.getParameter("iCveUniMed").equals("") || request.getParameter("iCveUniMed").equals("null") ? "0" : request.getParameter("iCveUniMed"));
                int iCveProceso = Integer.parseInt(request.getParameter("iCveProceso") == null || request.getParameter("iCveProceso").equals("") || request.getParameter("iCveProceso").equals("null") ? "0" : request.getParameter("iCveProceso"));
                int iCveModulo = Integer.parseInt(request.getParameter("iCveModulo") == null || request.getParameter("iCveModulo").equals("") || request.getParameter("iCveModulo").equals("null") ? "0" : request.getParameter("iCveModulo"));

                TDPERDatos dPERDatos = new TDPERDatos();
                TDEXPServicio dEXPServicio = new TDEXPServicio();
                TVEXPServicio vEXPServicio = new TVEXPServicio();
                Vector vcEXPServicio = new Vector();

                int iConcluido = 0;
    %>
    <script LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>PGABRESCANNER.js"></script>
    <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + vEntorno.getNombrePagina().substring(0, vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
    <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "calendario.js"%>"></SCRIPT>
    <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "SelPer.js"%>"></SCRIPT>
    <SCRIPT LANGUAGE="JavaScript">

        function fOnLoad(){
            fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
            '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
            '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
        }
    </SCRIPT>
    <head>
        <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor")%>">

        <script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.3.2.min.js" type="text/javascript"></script>
        <script>
            $(document).ready(function(){

                $("#capaefectos").hide("slow");///oculta la capa al cargar la pagina

                $("#ocultar").click(function(event){
                    event.preventDefault();
                    $("#capaefectos").hide("slow");
                });

                $("#mostrar").click(function(event){
                    event.preventDefault();
                    $("#capaefectos").show(2000);
                });
            });
        </script>

    </head>
    <% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */%>
    <link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>" TYPE="text/css">
    <body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina")%>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad()%>">
        <form method="<%= vEntorno.getMetodoForm()%>" action="<%= vEntorno.getActionForm()%>">
            <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) {
                            out.print(request.getParameter("hdLCondicion"));
                        }%>">
            <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) {
                            out.print(request.getParameter("hdLOrdenar"));
                        }%>">
            <input type="hidden" name="FILNumReng" value="500">


            <input type="hidden" name="hdICveProceso" value="<% if (request.getParameter("iCveProceso") != null) {
                            out.print(request.getParameter("iCveProceso"));
                        }%>">
            <input type="hidden" name="hdICveServicio" value="<% if (request.getParameter("iCveServicio") != null) {
                            out.print(request.getParameter("iCveServicio"));
                        }%>">
            <input type="hidden" name="hdiCveExpediente" value="<% if (request.getParameter("iCveExpediente") != null) {
                            out.print(request.getParameter("iCveExpediente"));
                        }%>">
            <input type="hidden" name="hdiNumExamen" value="<% if (request.getParameter("iNumExamen") != null) {
                            out.print(request.getParameter("iNumExamen"));
                        }%>">



            <%
                        if (bs != null) {
                            cPosicion = Integer.toString(bs.pageNo());
                        } else {
                            cPosicion = request.getParameter("hdRowNum");
                        }
            %>
            <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
            <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
            <input type="hidden" name="hdCPropiedad" value="">

            <input type="hidden" name="iNumExamen" value="">
            <input type="hidden" name="iCvePerfil" value="">
            <input type="hidden" name="iCvePersonal" value="">
            <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
            <input type="hidden" name="tpoBusqueda" value="unMedico">
            <input type="hidden" name="ramaInicial" value="0">

            <table background="<%= vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg" width="100%" height="100%">
                <% if (clsConfig.getAccesoValido()) {%>
                <tr><td>&nbsp;</td></tr>
                <tr><td><input type="hidden" name="hdBoton" value="">
                    </td>
                    <td valign="top">
                        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                 TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                            %>
                            <tr><td class="ETablaT" colspan="6">Selección del Paciente al Servicio</td></tr>
                            <tr>
                                <td class="EEtiqueta" colspan="3">Proceso:</td>
                                <td class="ETabla" colspan="3"><%=clsConfig.getProceso(vParametros.getPropEspecifica("EPIProceso"))%></td>
                            </tr>
                            <tr>
                                <td class="EEtiqueta" colspan="1">Unidad Médica:</td>
                                <td class="ETabla" colspan="2">
                                    <%=vEti.SelectOneRowSinTD("iCveUniMed", "fCambiaSelects(1);",
                                                            clsConfig.getUniMedsValidas(vParametros.getPropEspecifica("EPIProceso")),
                                                            "iCveUniMed", "cDscUniMed", request, "0", true)%>
                                </td>
                                <td class="EEtiqueta" colspan="2">Módulo:</td>
                                <td class="ETabla" colspan="1">
                                    <%=vEti.SelectOneRowSinTD("iCveModulo", "fCambiaSelects(2);",
                                                            clsConfig.getModulos(request.getParameter("iCveUniMed"), vUsuario.getICveusuario()),
                                                            "cIndice", "cDescripcion", request, "0", true)%>

                                </td>
                            </tr>
                            <tr>
                                <td class="EEtiqueta" colspan="1">Servicio:</td>
                                <%
                                     Vector vServicios = new TDGRLUSUMedicos().findServicios2(vUsuario.getICveusuario(), iCveUniMed, iCveProceso, iCveModulo, false);
                                %>
                                <td class="ETabla" colspan="5">
                                    <%=vEti.SelectOneRowSinTD("iCveServicio", "",
                                                            vServicios,
                                                            "iCveServicio", "cDscServicio", request, "0", true)%>
                                </td>
                            </tr>
                            <tr>
                                <td class="EEtiqueta" colspan="1">Expediente:</td>
                                <%                            out.print(vEti.CeldaCampoCS("ETabla", "text", 30, 9, 3, "iCveExpediente", "", 0, "", "", true, true, true, request));%>

                                <td class="ETabla" colspan="1">
                                    <%
                                         out.print(vEti.clsAnclaTexto("EAnclaC", "Buscar expediente",
                                                 "javascript:fBuscar();", "Buscar"));
                                    %>
                                </td>
                                <td class="ETabla" colspan="1">
                                    <%
                                         out.print(vEti.clsAnclaTexto("EAncla", "Buscar Personal", "JavaScript:fSelPer();", "Buscar Personal", ""));
                                    %>
                                </td>
                            </tr>


                            <tr>
                                <td class="ETablaT" colspan="4">Expediente</td>
                                <td class="ETablaT" colspan="1">RFC</td>
                                <td class="ETablaT" colspan="1">Fecha</td>
                            </tr>
                            <%
                                 if (bs != null) {
                                     bs.start();

                                     String exp = bs.getFieldValue("iCveExpediente", "").toString();
                                     String exm = bs.getFieldValue("iNumExamen", "").toString();
                                     //      String exm = "1";
                                     String per = bs.getFieldValue("iCvePersonal", "").toString();
                                     //    String per = exm;

                                     // Comprueba que el operador tenga activado el servicio
                                     //System.out.println("Parametros: " + request.getParameter("iCveExpediente")+","+ exm +","+ request.getParameter("iCveServicio"));
                                     vcEXPServicio = dEXPServicio.getLConcluidoCarga(request.getParameter("iCveExpediente"), exm, request.getParameter("iCveServicio"));
                                     //System.out.println("Tamaño del Vector" + vcEXPServicio.size());
                                     if (vcEXPServicio.size() > 0) {
                                         vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                                         iConcluido = vEXPServicio.getLConcluido();
                                     } else {
                                         iConcluido = -1; // Para que no entre a ninguna condición de búsqueda
                                     }
                                     //System.out.println("en jsp lconcluido: " + iConcluido);
                                     if (iConcluido == 0) {
                                         while (bs.nextRow()) {
                            %>
                            <tr>
                                <td class="ETablaC" colspan="4"><%


                                                                             ////////////OBTENCION DE PARAMETROS ADICIONALES//////////////////////
                                                                             /////DESCRIPCIÓN SERVICIO//////
                                                                             try {
                                                                                 ServActual = dPERDatos.SenFindBy("SELECT CDSCSERVICIO FROM MEDSERVICIO WHERE ICVESERVICIO = " + request.getParameter("iCveServicio"));
                                                                             } catch (Exception e) {
                                                                                 ServActual = "";
                                                                             }

                                                                             /////////Verificamos no existan servicios por atender antes del que se desea consultar
                                                                             String Consulta = " E.ICVEEXPEDIENTE = " + exp + " AND"
                                                                                     + " E.INUMEXAMEN = " + exm + " AND "
                                                                                     + " M.IORDEN < (SELECT IORDEN FROM MEDSERVICIO WHERE ICVESERVICIO = " + request.getParameter("iCveServicio") + ")";
                                                                             Vector vcServIndex = new Vector();
                                                                             TVEXPServicio vEXPServicio2 = new TVEXPServicio();
                                                                             try {
                                                                                 vcServIndex = dEXPServicio.FindSerIndex(Consulta);
                                                                             } catch (Exception e) {
                                                                                 vcServIndex = new Vector();
                                                                                 e.printStackTrace();
                                                                             }
                                                                             
                                                                             //System.out.println("vcServIndex.size() = "+vcServIndex.size());
                                                                             if (vcServIndex.size() > 0) {
                                                                            	 //System.out.println("1");
                                                                                 IndexServ = vcServIndex.size();
                                                                                 for (int i = 0; i < vcServIndex.size(); i++) {
                                                                                     vEXPServicio2 = (TVEXPServicio) vcServIndex.get(i);
                                                                                     ServIndex = ServIndex + "- " + vEXPServicio2.getCDscServicio() + "<br>";
                                                                                 }

                                                                                 out.print("<a class=\"EAnclaC\" name=\"exp\" href=\"\" id=\"mostrar\">Capturar Servicio</a>");

                                                                             } else {
                                                                            	 //System.out.println("2");
                                                                               //        System.out.println("EXPEDIENTE DOCTOR HUELLA" + vUsuario.getIdIcveExpediente());
                                                                                 int iDoctoHuellaDigital = vUsuario.getIcveDoctoHuella();
                                                                               //        System.out.println("IDOCTO HUELLA DOCTOR" + iDoctoHuellaDigital);

                                                                                 boolean flag = false;
                                                                                 Vector vUsuMedicos = vUsuario.getVUsuMedicos();
                                                                                 for (int i = 0; i < vUsuMedicos.size(); i++) {
                                                                                     if (((TVGRLUSUMedicos) vUsuMedicos.get(i)).getICveUniMed() == 107) {
                                                                                         flag = true;
                                                                                     }
                                                                                 }
                                    %>
                                    <script type='text/javascript' src='/medprev/dwr/engine.js'></script>
                                    <script type='text/javascript' src='/medprev/dwr/util.js'></script>
                                    <script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>
                                    <script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
                                    <script language="JavaScript">
                                        var GRALiCveExp, GRALiNumExm;
                                        function Validacion(value){
                                            if(value == 'true'){
                                                if(fValidaSelectores()){fIr('pg070104035.jsp',<%=exp%>, <%=exm%> , <%=per%>);}
                                            }else{
                                                alert("La huella no pertenece a la persona");
                                            }
                                        }
                                        function fValidarHuellas(iCveExp, icveservicio){
                                            GRALiCveExp=<%=exp%>;
                                            GRALiNumExm=<%=exm%>;


                                            //if(<%=flag%> && (icveservicio == 68 || icveservicio == 67) ){
                                           	if(false){
                                                openpopupValidaMedico();
                                            }else{
                                                Validacion('true');
                                            }
                                        }
                                        function openpopupValidaMedico(){
                                            var popurl="validaBiometricoMedicoPacienteDictamen.jsp?idPersona=1&iNumExm=0&iCveExp=0";
                                            window.open(popurl,"","width=10,height=10,status,menubar,");
                                        }
                                        function respuestaopenpopupValidaMedico(){
                                            openpopupValidaPaciente();
                                        }
                                        function openpopupValidaPaciente(){
                                            var popurl="validaBiometricoMedicoPacienteDictamen.jsp?idPersona=2&iNumExm="+GRALiNumExm+"&iCveExp="+GRALiCveExp;
                                            window.open(popurl,"","width=10,height=10,status,menubar,");
                                        }
                                        function respuestaopenpopupValidaPaciente(){
                                            Validacion('true');
                                        }
                                    </script>

                                    <script language="JavaScript">
                                        function ValidaHuellaDoc(iCveDocto)
                                        {
                                            var fso;
                                            var exe;
                                            var result = false;
                                            var ForReading = 1;
                                            var flag = 0;
                                            var oShell = new ActiveXObject("Shell.Application");
                                            var aplicacion = "C:\\fingerprint\\fingerprint.exe";
                                            var parametros_del_comando = "";

                                            if(iCveDocto != "0"){
                                                var URL = '"<%=vParametros.getPropEspecifica("RutaContextoGral")%>BajarArchivoCMServlet?LINTICVEDOCUMEN=_CLAVE_&IDEDO=_DEDO_&idTipoDocumento=3"';
                                                var dedo = 2;
                                                parametros_del_comando =  " "+URL+" "+iCveDocto +" "+dedo;
                                            }	else {
                                                parametros_del_comando =  " "+URL+" "+iCveDocto +" "+dedo;
                                            }

                                            exe = oShell.ShellExecute(aplicacion, parametros_del_comando, "", "open", "1");
                                            fso = new ActiveXObject("Scripting.FileSystemObject");

                                            while(flag==0){
                                                try{
                                                    archivo = fso.OpenTextFile("C:\\fingerprint\\matchresult.txt", ForReading, false);
                                                    result = archivo.readline();
                                                    archivo.Close();
                                                    archivo = fso.GetFile("C:\\fingerprint\\matchresult.txt");
                                                    archivo.Delete();
                                                    flag = 1;
                                                }catch(err){
                                                    //alert(err);
                                                }
                                            }

                                            return result;
                                        }

                                        function ValidaHuellaDoc2(iCvePersonal)
                                        {
                                            var fso;
                                            var result = false;
                                            var ForReading = 1;
                                            var flag = 0;
                                            var oShell = new ActiveXObject("Shell.Application");
                                            var aplicacion = "C:\\fingerprint\\fingerprint.exe";
                                            var parametros_del_comando = "";

                                            if(iCvePersonal != "0"){
                                                var URL = '"http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&IDEDO=_DEDO_&ICVETIPOFFH=3"';
                                                //var URL = 'http://aplicaciones3.sct.gob.mx/elic/LICDownload?ICVEPERSONAL=_CLAVE_&ICVETIPOFFH=3&IDEDO=2';
                                                var expediente = iCvePersonal;
                                                var dedo = 2;
                                                parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
                                                //parametros_del_comando =  " 0 "+expediente ;
                                            }	else {
                                                parametros_del_comando =  " "+URL+" "+expediente +" "+dedo;
                                                //parametros_del_comando =  " 0 "+expediente;
                                                //fAlert("ccahuellas (servidor interno)");
                                            }

                                            oShell.ShellExecute(aplicacion, parametros_del_comando, "", "open", "1");
                                            fso = new ActiveXObject("Scripting.FileSystemObject");

                                            while(flag==0){
                                                try{
                                                    archivo = fso.OpenTextFile("C:\\fingerprint\\matchresult.txt", ForReading, false);
                                                    result = archivo.readline();
                                                    archivo.Close();
                                                    archivo = fso.GetFile("C:\\fingerprint\\matchresult.txt");
                                                    archivo.Delete();
                                                    flag = 1;
                                                }catch(err){
                                                    //alert(err);
                                                }
                                            }
                                            return result;
                                        }
                                    </script>
                                    <%                                        if (ConHuella == 0) {


                                                                                                                out.print(vEti.clsAnclaTexto("EAnclaC", "Capturar Servicio",
                                                                                                                        "javascript:"
                                                                                                                        + "if(fValidarHuellas(" + exp + "," + request.getParameter("iCveServicio") + "))"
                                                                                                                        + "if(fValidaSelectores()){"
                                                                                                                        + "fIr('pg070104035.jsp'," + exp + "," + exm + "," + per + ");"
                                                                                                                        + ""
                                                                                                                        + "}", "exp"));
                                                                                                            } else {

                                                                                                                int iDoctoHuellaDigitalMedico = vUsuario.getIcveDoctoHuella();
                                                                                                                out.print(vEti.clsAnclaTexto("EAnclaC", "Capturar Servicio3",
                                                                                                                        "javascript:"
                                                                                                                        + "fValidarHuellas(" + exp + "," + request.getParameter("iCveServicio") + ")"
                                                                                                                        + "", "exp"));


                                    %>
                                    <% }
                                                                                 }// else de index de servicio %>
                                </td>
                                <td class="ETablaC" colspan="1"><%=bs.getFieldValue("cRFC", "&nbsp;")%></td>
                                <td class="ETablaC" colspan="1"><%=clsConfig.formatDate(bs.getFieldValue("dtSolicitado").toString())%></td>
                            </tr>
                            <%

                                         }
                                     } else if (iConcluido == 1) {
                                         out.println("<tr>");
                                         out.println("<td colspan=6 align='center'>");
                                         //out.print(vEti.clsAnclaTexto("EAncla","Ver/Imprimir Resultados del Servicio","JavaScript:fVerExamen(" + exp + ", " + exm + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                                         out.print(vEti.clsAnclaTexto("EAncla", "Ver/Imprimir Resultados del Servicio", "JavaScript:fIrVerExamen(0,0,'pg070104071.jsp'," + exm + ");", "Ver Examen", ""));
                                         out.println("</td>");
                                         out.println("</tr>");
                                         String cInter = "pg070104038.jsp";

                                         if (clsConfig.getLPagina(cInter)) {

                                             out.println("<tr><td align='center' colspan='8'>");
                                             out.print(vEti.clsAnclaTexto("EAncla", "InterConsulta", "JavaScript:fIrCatalogo('pg070104038.jsp'," + vEXPServicio.getINumExamen() + ");", "InterConsulta", ""));
                                             out.println("</tr></td>");
                                         }

                                     } else if (iConcluido == -1) {
                                         out.println("<tr>");
                                         out.println("<td class='ETablaC' colspan=6>No existen datos coincidentes con el filtro proporcionado</td>");
                                         out.println("</tr>");
                                     }



                                 } else {
                                	//System.out.println("-"+request.getParameter("iCveExpediente"));
                                	//System.out.println("-"+request.getParameter("iNumExamen"));
                                	//System.out.println("-"+request.getParameter("iCveServicio"));
                                	
                                     if (request.getParameter("iCveExpediente") != null && request.getParameter("iCveExpediente").compareTo("null") != 0 && request.getParameter("iCveExpediente").compareTo("") != 0 && request.getParameter("iNumExamen") != null && request.getParameter("iNumExamen").compareTo("null") != 0 && request.getParameter("iNumExamen").compareTo("") != 0 && request.getParameter("iCveServicio") != null && request.getParameter("iCveServicio").compareTo("null") != 0 && request.getParameter("iCveServicio").compareTo("") != 0) {
                                         // Comprueba que el operador tenga activado el servicio
                                         vcEXPServicio = dEXPServicio.getLConcluidoCarga(request.getParameter("iCveExpediente"), request.getParameter("iNumExamen"), request.getParameter("iCveServicio"));
                                         if (vcEXPServicio.size() > 0) {
                                             vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                                             iConcluido = vEXPServicio.getLConcluido();
                                         } else {
                                             iConcluido = -1; // Para que no entre a ninguna condición de búsqueda
                                         }
                                         if (iConcluido == 1) {
                                             out.println("<tr>");
                                             out.println("<td colspan=6 align='center'>");
                                             out.print(vEti.clsAnclaTexto("EAncla", "Ver/Imprimir Resultados del Servicio", "JavaScript:fVerExamen(" + request.getParameter("iCveExpediente") + ", " + request.getParameter("iNumExamen") + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen", ""));
                                             out.println("</td>");
                                             out.println("</tr>");
                                         } else if (iConcluido == -1) {
                                             out.println("<tr>");
                                             out.println("<td class='ETablaC' colspan=6>No existen datos coincidentes con el filtro proporcionado1</td>");
                                             out.println("</tr>");
                                         }
                                     } else {
                                         out.println("<tr>");
                                         out.println("<td class='ETablaC' colspan=6>No existen datos coincidentes con el filtro proporcionado2</td>");
                                         out.println("</tr>");
                                     }
                                 }
                            %>
                        </table>
                        <%
                             ////////Capa del anuncio de servicios que aun faltan por capturarse
                             if (IndexServ > 0) {%>
                        <div id="capaefectos" style="background-color: #37AC75; color:fff; padding:10px; display:none;">
                            <p>No podrá capturar el servicio de <%=ServActual%> hasta que concluya los siguientes servicios en el orden mostrado:
                                <br>
                                <br>
                                <%=ServIndex%></p>
                            <p>  <a class="EAnclaC" name="ocultar" href="#" id="ocultar">Ocultar</a> | </p>
                        </div>
                        <% }%>
                    </td></tr>
                    <%} else {%>
                <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
                <%}%>
            </table>
        </form>
    </body>
    <%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
    <% vEntorno.clearListaImgs();%>
</html>
