<%/**
            En el DICTAMEN para TODOS que tengan HABILITADA la VALIDACION BIOMETRICA en alguno de sus MODULOS ASIGNADOS, se valida HUELLA DOCTOR y PACIENTE

             * Title: Listado de Exámenes Concluidos
             * Description: JSP para listar los exámenes concluidos
             * Copyright: ?
             * Company: Micros Personales S.A. de C.V.
             * @author Esteban Viveros
             * @version 1
             * Clase: ?
             */%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<html>
    <%            pg070105080CFG clsConfig = new pg070105080CFG();               // modificar

                TEntorno vEntorno = new TEntorno();
                vEntorno.setNumModulo(07);
                vEntorno.setNombrePagina("pg070105080.jsp");                    // modificar
                vEntorno.setArchTooltips("07_Tooltips");
                vEntorno.setOnLoad("fOnLoad();");
                vEntorno.setArchFuncs("FValida");
                vEntorno.setMetodoForm("POST");
                vEntorno.setActionForm("pg070105080.jsp\" target=\"FRMCuerpo"); // modificar
                vEntorno.setUrlLogo("Acerca");
                vEntorno.setBtnPrincVisible(true);
                vEntorno.setArchFCatalogo("FFiltro");
                vEntorno.setArchAyuda(vEntorno.getNombrePagina());

                String cCatalogo = "pg070105010.jsp";     // modificar
                String cOperador = "1";                   // modificar
                String cDscOrdenar = "Propiedad|Valor|";    // modificar
                String cCveOrdenar = "cPropiedad|cValor|";  // modificar
                String cDscFiltrar = "Propiedad|Valor|";    // modificar
                String cCveFiltrar = "cPropiedad|cValor|";  // modificar
                String cTipoFiltrar = "7|8|";                // modificar
                boolean lFiltros = false;                 // modificar
                boolean lIra = false;                 // modificar
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
                String cNavStatus = "Hidden"; //clsConfig.getNavStatus();
                String cOtroStatus = clsConfig.getOtroStatus();
                String cCanWrite = "No";
                String cSaveAction = "Guardar";
                String cDeleteAction = "BorrarB";
                String cClave = "";
                String cPosicion = "";
                String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
                String dFechaActual = request.getParameter("hdDtConcluido") != null ? request.getParameter("hdDtConcluido") : new TFechas().getFechaDDMMYYYY(new java.sql.Date(new java.util.Date().getTime()), "/");
                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
    %>
    <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + vEntorno.getNombrePagina().substring(0, vEntorno.getNombrePagina().length() - 1)%>"></SCRIPT>
    <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "calendario.js"%>"></SCRIPT>
    <SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "PermCombos.js"%>"></SCRIPT>
    <SCRIPT LANGUAGE="JavaScript">
        function fOnLoad(){
            fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
            '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
            '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
        }
    </SCRIPT>

    <script type='text/javascript' src='/medprev/dwr/engine.js'></script>
    <script type='text/javascript' src='/medprev/dwr/util.js'></script>
    <script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>
    <script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
    <head>
        <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor")%>">
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
            <!--input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null) {
                            out.print(request.getParameter("FILNumReng"));
                        } else {
                            out.print(vParametros.getPropEspecifica("NumRengTab"));
                        }%>"-->
            <input type="hidden" name="FILNumReng" value="600">
            <input type="hidden" name="hdRowNum" value="<%=bs != null ? Integer.toString(bs.pageNo()) : ""%>">
            <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
            <input type="hidden" name="hdICveExpediente" value="">
            <input type="hidden" name="hdINumExamen" value="">
            <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("iCveUniMed") != null ? request.getParameter("iCveUniMed") : request.getParameter("hdICveUniMed") != null ? request.getParameter("hdICveUniMed") : ""%>">
            <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("iCveModulo") != null ? request.getParameter("iCveModulo") : request.getParameter("hdICveModulo") != null ? request.getParameter("hdICveModulo") : ""%>">
            <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("iCveProceso") != null ? request.getParameter("iCveProceso") : request.getParameter("hdICveProceso") != null ? request.getParameter("hdICveProceso") : ""%>">
            <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("dtConcluido") != null ? request.getParameter("dtConcluido") : request.getParameter("hdDtConcluido") != null ? request.getParameter("hdDtConcluido") : ""%>">
            <input type="hidden" name="hdCPropiedad" value="">
            <input type="hidden" name="hdBoton" value="">
            <table background="<%= vParametros.getPropEspecifica("RutaImg")%>fondo01.jpg" width="100%" height="100%">
                <%  if (clsConfig.getAccesoValido()) {
                                String cTmp = request.getParameter("iCveUniMed") != null ? request.getParameter("iCveUniMed") : request.getParameter("hdICveUniMed") != null ? request.getParameter("hdICveUniMed") : "0";
                %>    <tr><td>&nbsp;</td></tr>
                <tr><td valign="top">
                        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr><td colspan="4" class="ETablaT">Ex&aacute;menes Concluidos</td></tr>
                            <tr>
                                <td class="EEtiqueta">Unidad M&eacute;dica:</td>
                                <td class="ETabla">
                                    <%= vEti.SelectOneRowSinTD("iCveUniMed", "{forma = document.forms[0];fActualizaCombo('2',forma,this,forma.iCveModulo,this.value,0,0);forma.iCveProceso.options.length = 0; forma.iCveProceso.options[0]= new Option('Datos no disponibles','-1');}", vUsuario.getVUniMed(), "iClave", "cDescripcion", request, "0", true)%>
                                    <%
                                          int iCveUniMed = 0;
                                          String Proceso = "";

                                          //System.out.println("Antes de entrar al if " + iCveUniMed);
                                          //System.out.println("unimed = " + request.getParameter("iCveUniMed"));

                                          //Validamos Unidad Medica
                                          if (request.getParameter("iCveUniMed") == null || request.getParameter("iCveUniMed").equals("") || request.getParameter("iCveUniMed").equals("null")) {
                                               //System.out.println("Dentro del if de unimed");
                                              iCveUniMed = 0;
                                               //System.out.println("unimed = "+iCveUniMed);
                                          } else {
                                               //System.out.println("Entramos al else");
                                              		iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed").toString());
                                               //System.out.println("unimed = "+iCveUniMed);
                                          }

                                          //System.out.println("valor final Unimed = "+iCveUniMed);
                                          //Validamos Proceso
                                          //System.out.println("Antes de entrar al if " + iCveProceso);
                                          if (iCveProceso == null || iCveProceso.toString().equals("") || iCveProceso.toString().equals("null")) {
                                                      //System.out.println("Dentro de if proceso " + iCveProceso);
                                              Proceso = "0";
                                          } else {
                                                     //System.out.println("Dentro de else proceso ");
                                              Proceso = iCveProceso;
                                                     //System.out.println("Proceso = "+Proceso);
                                          }
                                          //System.out.println("fin validaciones");

                                          //int iCveUniMed = (request.getParameter("iCveUniMed") != null && request.getParameter("iCveUniMed").toString().length() > 0) ? Integer.parseInt(request.getParameter("iCveUniMed").toString()) : 0;
%>
                                </td>
                                <td class="EEtiqueta">M&oacute;dulo:</td>
                                <td class="ETabla"><%=vEti.SelectOneRowSinTD("iCveModulo", "{forma = document.forms[0];fActualizaCombo('4',forma,this,forma.iCveProceso,forma.iCveUniMed.value,this.value,0);}", vUsuario.getModuloFUP(iCveUniMed, Integer.parseInt(Proceso)), "iClave", "cDescripcion", request, "0", true)%>
                                    <% 
                                    //System.out.println("fin validaciones2");
                                    //System.out.println(request.getParameter("iCveModulo"));
                                    int iCveModulo = 0;
                                    if (request.getParameter("iCveModulo") == null || request.getParameter("iCveModulo").equals("") || request.getParameter("iCveModulo").equals("null")) {
                                    	iCveModulo = 0;
                                    }else{
                                    	iCveModulo = Integer.parseInt(request.getParameter("iCveModulo").toString());
                                    }
                                    //int iCveModulo = (!request.getParameter("iCveModulo").equals("null") && request.getParameter("iCveModulo") != null && request.getParameter("iCveModulo").toString().length() > 0) ? Integer.parseInt(request.getParameter("iCveModulo").toString()) : 0;
                                    //System.out.println("fin validaciones3");
                                    %>
                                </td>
                            </tr>
                            <tr>
                                <td class="EEtiqueta">Proceso:</td>
                                <td class="ETabla"><%=vEti.SelectOneRowSinTD("iCveProceso", "", vUsuario.getVProcXModulo(iCveUniMed, iCveModulo), "iClave", "cDescripcion", request, "0", true)%>
                            </tr>
                            <tr><%=vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10, 3, "dtConcluido", dFechaActual, 0, "", "fValFecha(this.value);", false, true, true, request)%></tr>
                            <tr class="ECampo"><td colspan="4" align="center"><a class="EAncla" href="JavaScript:fSubmite();">Buscar</a></td></tr>
                        </table>
                    </td></tr>
                <tr><td>&nbsp;</td></tr>
                <tr><td valign="top">
                        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr class="ETablaT"><td colspan="2">Personal que ha concluido su examen</td></tr>
                            <tr class="ETablaT"><td>Concluido</td><td>Expediente</td></tr>
                            <script language="JavaScript">

                                function fIrCatalogo(iCveExp,iNumExm){
                                    var form=document.forms[0];
                                        form.hdICveExpediente.value=iCveExp;
                                        form.hdINumExamen.value=iNumExm;
                                        form.action="pg070105010.jsp";
                                        form.target="FRMDatos";
                                        form.submit();
                                }
                            </script>
                            <%    
                            //System.out.println("paso validaciones");
                            if (bs != null) {
                                      bs.start();
                                      while (bs.nextRow()) {
                                          java.sql.Date dtTmp = ((TVEXPExamAplica) bs.getCurrentBean()).getDtConcluido();
                            %>        <tr class="ETablaC">
                                <td><%= dtTmp != null ? new TFechas().getFechaDDMMYYYY(dtTmp, "/") : "Los resultados del examen no están capturados;"%></td>
                                <%        if (clsConfig.getLPagina(cCatalogo)) {
                                %>          <td><a class="EAncla" href="javascript:fIrCatalogo('<%=bs.getFieldValue("iCveExpediente", "")%>','<%=bs.getFieldValue("iNumExamen", "")%>')"><%=bs.getFieldValue("iCveExpediente", "&nbsp;")%></a></td>
                                <%        } else {
                                %>          <td><%=bs.getFieldValue("iCveExpediente", "&nbsp;")%></td>
                                <%        }
                                %>        </tr>
                                <%      }
                                    } else {
                                %>        <tr class="ETablaC"><td colspan="2" align="center">Datos no disponibles</td></tr>
                            <%      }
                            %>      </table>
                    </td></tr>
                    <%  } else {
                    %>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
                <%  }
                %>  </table>
        </form>
    </body>
    <%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
    <% vEntorno.clearListaImgs();%>
</html>
