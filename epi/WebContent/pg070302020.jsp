<%/**
 * Title: Formato Federal de Control y Cadena de Custodia para Análisis de Drogas
 * Description: Formato Federal de Control y Cadena de Custodia para Análisis de Drogas
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Mauricio Delgadillo
 * @version 1.0
 * Clase:pg070302020CFG
 */%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
  pg070302020CFG  clsConfig = new pg070302020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070302020.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070302020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Muestra|";    // modificar
  String cCveOrdenar  = "iCveMuestra|";  // modificar
  String cDscFiltrar  = "Muestra|";    // modificar
  String cCveFiltrar  = "iCveMuestra|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                 // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

                                  
                                  String User2 ="";
                                  TVUsuario vUsuario2 = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                 User2 = "" + vUsuario2.getICveusuario();
                          //    //        System.out.println("************** Usuario que se encuentra en sesion === "+ User2 + "  **************");
                                

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

      String cCampo = "";
      String cPanel = "";

    if ( (request.getParameter("hdOPPbaRapida") == null) ||
         (request.getParameter("hdBoton").compareTo("Guardar") == 0) ||
          (request.getParameter("hdBoton").compareTo("Cancelar") == 0)){
         cCampo = "0";
    }
    else{
         cCampo = "" + request.getParameter("hdOPPbaRapida");
     }

    
  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  TEtiCampo vEti = new TEtiCampo();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"empr.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selEmp.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = ""+bs.getFieldValue("iCveMuestra", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdiCvePersonal" value="<%=request.getParameter("hdiCvePersonal")%>">
  <input type="hidden" name="hdCveEmpresa" value="<%=request.getParameter("hdCveEmpresa")%>">
  <input type="hidden" name="hdDscEmpresa" value="<%=request.getParameter("hdDscEmpresa")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
  <td valign="top">
                              &nbsp;
                              <input type="hidden" name="hdBoton" value="">
                              <input type="hidden" name="hdOPPbaRapida" value="<%=cCampo%>">

                               <%
                                 boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();


                                if (lNuevo || cCampo.compareTo("0") != 0){
                                %>
                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="7" class="ETablaT">Registro del Formato Federal de Control y Cadena de Custodia de Muestras para Análisis de drogas
                                 </td>
                                 </tr>
                                 <tr>
                                 <%
                                out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                out.println("<td class='ECampo'>");
                                String User = "";
                                String cDef1 = request.getParameter("xCveUniMed");
                                String cDef2 = request.getParameter("iCveProceso");
                                if (cDef1 != null){
                                   if (cDef1.compareTo("null") == 0 && cDef1.compareTo("") == 0) {
                                      cDef1 = "0";
                                   }
                                }else{
                                   cDef1 = "0";
                                }
                                if (cDef2 != null){
                                   if (cDef2.compareTo("null") == 0 && cDef2.compareTo("") == 0) {
                                      cDef2 = "0";
                                   }
                                }else{
                                   cDef2 = "0";
                                }


                                TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                User = "" + vUsuario.getICveusuario();
                              //        System.out.println("************** Usuario que se encuentra en sesion === "+ User + "  **************");
                                %>
                                <input type="hidden" name="iCveUsuCaptura" value="<%=User%>">
                               <%
                                out.println(vEti.SelectOneRowSinTD("iCveUniMed", "llenaSLT(1,"+ User + ",this.value,'',document.forms[0].iCveProceso);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                out.println("</td>");

                                //Módulo
                                out.print(vEti.Texto("EEtiqueta", "Módulo:"));
                                if (request.getParameter("iCveModulo") == null){
                                   out.println("<td class='ECampo' colspan='4'>");
                                   out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveModTrans);\">");
                                  out.println("</SELECT>");
                                }
                                else{
                                  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                                  TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                                  Vector vGModulo = new Vector();
                                  String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                                   " and GRLUsuMedicos.iCveUniMed =  " + request.getParameter("iCveUniMed") ;
                                  vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                                  out.print(vEti.SelectOneRow("ECampo","iCveModulo","",vGModulo,"iCveModulo","cDscModulo",request,""));
                                }

                                out.println("</tr>");
                                out.println("<tr>");


                                out.println(vEti.Texto("EEtiqueta","Proceso:"));


                                 if (request.getParameter("iCveProceso") == null){
                                  out.println("<td class='ECampo'>");
                                  out.println("<SELECT NAME='iCveProceso' SIZE='1' onChange=\"llenaSLT(2,this.value,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,document.forms[0].iCveMotivo);\">");
                                  out.println("</SELECT>");
                                }
                                else{
                                  TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveProceso","llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);",dGRLUMUsuario.getProcesos(Integer.parseInt(User,10),Integer.parseInt(request.getParameter("iCveUniMed"),10)),"iCveProceso", "cDscProceso", request, "0", true));
                                  out.println("</td>");
                                }
                                /*
                                out.println("<td class='ECampo'>");
                                out.println("<SELECT NAME='iCveProceso' SIZE='1' onChange=\"llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);\">");
                                out.println("</SELECT>");
                                out.println("</td>");
                                */


                                out.println(vEti.Texto("EEtiqueta","Motivo:"));
                                if (request.getParameter("iCveMotivo") == null){
                                out.println("<td class='ECampo' colspan='4'>");
                                out.println("<SELECT NAME='iCveMotivo' SIZE='1' onChange=''>");
                                out.println("</SELECT>");
                                out.println("</td>");
                                }
                                else{
                                 TVMotivo vMotivo = new TVMotivo();
                                 out.println("<td>");
                                 out.println(vEti.SelectOneRowSinTD("iCveMotivo","",(Vector) AppCacheManager.getColl("GRLMotivo",request.getParameter("iCveProceso").toString()),"iCveMotivo", "cDscMotivo", request, "0", true));
                                 out.println("</td>");
                                }
                                out.println("</tr><tr>");
                                out.print(vEti.Texto("EEtiqueta", "Modo de Transporte:"));
                                if (request.getParameter("iCveModTrans") == null){
                                   out.println("<td class='ECampo' colspan='8'>");
                                   out.println("<SELECT NAME='iCveModTrans' SIZE='1'>");
                                   out.println("</SELECT>");
                                }
                                else{

                                   TDGRLProcesoMDOT dGRLProcesoMDOT = new TDGRLProcesoMDOT();
                                   TVGRLProcesoMDOT vGRLProcesoMDOT = new TVGRLProcesoMDOT();

                                   Vector vcGRLProcesoMDOT = new Vector();

                                   vcGRLProcesoMDOT = dGRLProcesoMDOT.FindMTXMod(" where iCveUniMed = " +  request.getParameter("iCveUniMed") + " and iCveModulo = " +  request.getParameter("iCveModulo") );
                                   out.print(vEti.SelectOneRow("ECampo","iCveModTrans","",vcGRLProcesoMDOT,"iCveMdoTrans","cDscMdoTrans",request,""));
                                }

                                 out.println("</tr>");

                                out.println("<tr><td class='EEtiqueta'>A&ntilde;o:</td><td class='ETabla'>");
                                // Agregar la información del Año para la captura de las muestras
                                iAniosAtras = dtFecha.aumentaDisminuyeDias(dtFecha.getDateSQL("01","01",String.valueOf(iAnio)),Integer.parseInt(vParametros.getPropEspecifica("TOXVigenciaFFCCC"))).compareTo(dtFecha.TodaySQL()) >= 0 ? 1:0 ;
                                out.println(" <select name=\"iAnio\" size=\"1\">");
                                for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                     out.print("<option value = " + i + ">" + i + "</option>");
                                }
                                out.println("</select></td>");

                                out.println();
                                out.print(vEti.EtiCampoCS("EEtiqueta","No. de Identificación de la Muestra:","ECampo","text",15,8,5,"iCveMuestra","",0,"","fMayus(this);",false,true,true,request));
                                out.println("</tr>");

                                out.println("<tr>");
                                if (request.getParameter("hdiCvePersonal") != null &&
                                    request.getParameter("hdiCvePersonal").toString().compareTo("") != 0 &&
                                    request.getParameter("hdiCvePersonal").toString().compareTo("null") != 0
                                    ){
                                  out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", request.getParameter("hdiCvePersonal"), 3, "", "", true, true, false,request));
                                }
                                else{
                                  out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", "&nbsp;", 3, "", "", true, true, false,request));
                                }
                                //Aqui agregue lo del Selector de Personas
                                 if (request.getParameter("hdiCvePersonal") != null && request.getParameter("hdiCvePersonal").compareTo("") != 0 && request.getParameter("hdiCvePersonal").toString().compareTo("null") != 0) {
                                    out.println("<input type='hidden' name='iCvePersonal' value='" + request.getParameter("hdiCvePersonal")  + "'>" );
                                    int i = 0;
                                    Object obElemento;
                                    TDPERDatos dPerDatos = new TDPERDatos();
                                    Vector vcPersonal = new Vector();
                                    vcPersonal = dPerDatos.FindByAll(request.getParameter("hdiCvePersonal"));
                                    obElemento =  vcPersonal.get(i);
                                    TVPERDatos vPERDatos = (TVPERDatos) obElemento;
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text", 50, 50,3, "cDscPersonal",vPERDatos.getCNombreCompleto(), 4, "", "", true, true, false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Edad:","ECampo","text",2,2,5,"iEdad","" + clsConfig.getEdad(vPERDatos.getDtNacimiento()),0,"","fMayus(this);",false,true,true,request));
                                    out.println("</tr>");
                                 }
                                 else{
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text",50, 50,3, "cDscPersonal","&nbsp;", 4, "", "", true, true, false));
                                 }


                                   /* if (request.getParameter("hdiCvePersonal") == null)
                                         out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", "" ,0,"","fMayus(this);",false,true,true));
                                    else{
                                         out.print(vEti.EtiCampo("EEtiqueta","Personal:","ECampo","text",10,10,"iCvePersonal", request.getParameter("hdiCvePersonal") ,0,"","fMayus(this);",false,true,true));
                                    }
                                    */
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.println("<td align='center' colspan='6'>");
                                 out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fSelPer();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                 out.println("</td>");
                                 out.println("</tr>");


                                 
                                 TDEmpresa dEmpresa = new TDEmpresa();
                                 out.println("<tr>");

                                 if (request.getParameter("hdCveEmpresa") != null &&
                                 request.getParameter("hdCveEmpresa").toString().compareTo("") != 0 &&
                                 request.getParameter("hdCveEmpresa").toString().compareTo("null") != 0
                                 ){
                                  out.println("<input type='hidden' name='iCveEmpresa' value='" + request.getParameter("hdCveEmpresa")  + "'>" );
                                   out.print(vEti.EtiCampo("EEtiqueta", "Cve. Empresa:", "ECampo", "text", 25, 50, "CveEmpresa", request.getParameter("hdCveEmpresa"), 3, "", "", true, true, false,request));
                                }
                                else{
                                  out.print(vEti.EtiCampo("EEtiqueta", "Cve. Empresa:", "ECampo", "text", 25, 50, "CveEmpresa", "&nbsp;", 3, "", "", true, true, false,request));
                                }

                                if (request.getParameter("hdDscEmpresa") != null &&
                                 request.getParameter("hdDscEmpresa").toString().compareTo("") != 0 &&
                                 request.getParameter("hdDscEmpresa").toString().compareTo("null") != 0
                                 ){
                                  out.print(vEti.EtiCampo("EEtiqueta", "Empresa:", "ECampo", "text", 25, 50, "DscEmpresa", request.getParameter("hdDscEmpresa"), 3, "", "", true, true, false,request));
                                }
                                else{
                                  out.print(vEti.EtiCampo("EEtiqueta", "Empresa:", "ECampo", "text", 25, 50, "DscEmpresa", "&nbsp;", 3, "", "", true, true, false,request));
                                }
                                 out.println("<tr>");


                                 out.println("<tr>");
                                 out.println("<td align='center' colspan='6'>");
                                 out.println(vEti.clsAnclaTexto("EAncla","Buscar Empresa","JavaScript:fSelEmp();", "Buscar Empresa",""));
                                 out.println("</td>");
                                 //out.println("<td class='ECampo' colspan='5'>");
                                 //out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "", dEmpresa.FindByAll(), "iCveEmpresa", "cDscEmpresa", request, "0",true));
                                 //out.println("</td>");
                                 out.println("</tr>");


                                ///// EDAD <<<<<<<< -----

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha Recolección:", "ECampo", "text",10, 10,5, "dtRecoleccion","", 3, "", "", false, true, true));
                                %>
                                <input type="hidden" name="hdHoy" value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")%>" >
                                <%
                                out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta","Médico Recolector:"));


                             if (request.getParameter("iCveUniMed") == null){
                                out.println("<td>");
                                out.println("<SELECT NAME='iCveUsuRecolec' SIZE='1' onChange=''>");
                                out.println("</SELECT>");
                                out.println("</td>");
                             }
                             else{
                                TVUsuario vUsuarioAplica = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                                int iCveProceso = Integer.parseInt(request.getParameter("iCveProceso"));
                                int iUniMed = 0;
                                int iModulo = 0;

                                if(request.getParameter("iCveUniMed") == null){
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                   }
                                }else{
                                   iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                             }
                             iModulo = Integer.parseInt(request.getParameter("iCveModulo"),10);

                             vcPersonal = new TDGRLUMUsuario().getUniMedMod(iCveProceso,iUniMed,iModulo);

                             new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                             out.println("<td>");
                             out.print(vEti.SelectOneRowSinTD("iCveUsuRecolec", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "", true));
                             out.println("</td>");
                             }


                             /*    out.println("<td>");
                                 out.println("<SELECT NAME='iCveUsuRecolec' SIZE='1' onChange=''>");
                                 out.println("</SELECT>");
                                 out.println("</td>");*/

                                 TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
                                 out.println(vEti.Texto("EEtiqueta","Situaci&oacute;n:"));
                                 out.println(vEti.Texto("EEtiquetaL","Recolección"));
                                 /*out.println(vEti.Texto("EEtiqueta","Modo de Transporte:"));
                                 out.println("<td class='ECampo'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveMdoTrans", "", dMdoTrans.findByAll(""), "iCveMdoTrans", "cDscMdoTrans", request, "0",true));
                                 out.println("</td>");*/
                                 out.println("</tr>");

 								%>
                                    <td colspan="7" class='ETablaT'>Prueba Rápida</td>
                                  <%
                                 out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Resultado:"));

                                    /*if (request.getParameter("hdPbaRapida") == null || cCampo.compareTo("0") == 0) {
                                         out.print(vEti.TextoCS("EEtiquetaL", "0",5));
                                    }
                                    else{
                                         out.print(vEti.TextoCS("EEtiquetaL", request.getParameter("hdPbaRapida"),5));
                                     }*/
                                     out.println("<td colspan = \"4\">");
                                    out.println(" <select name=\"lResultado\" size=\"1\" onClick=\"disableDos()\" >");
                                         out.print("<option value = \"1\">POSITIVO</option>");
                                         out.print("<option value = \"0\">NEGATIVO</option>");
                                         out.print("<option value = \"2\">NO REALIZO</option>");
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                   out.println("<tr>");
                                   //out.print(vEti.Texto("EEtiqueta", "Positivo a:"));
                                   out.print("<td rowspan=\"2\" class=\"EEtiqueta\">Positivo a:</td>");
                                   
                                   out.println("<td class=\"EEtiquetaL\" colspan = \"1\">ANFETAMINAS");
                                   out.println("<input type='checkbox' name='lAnfetaminas' value='ON' onClick=\"disableUno()\" >");
                                   out.println("<td class=\"EEtiquetaL\" colspan = \"1\">CANNABINOIDES");
                                   out.println("<input type='checkbox' name='lCannabis' value='ON' onClick=\"disableUno()\" >");
                                   out.println("</td>");
                                   out.println("<td class=\"EEtiquetaL\" colspan = \"1\">COCAINA");
                                   out.println("<input type='checkbox' name='lCocaina' value='ON' onClick=\"disableUno()\" >");
                                   out.println("</td>");
                                   
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.println("<td class=\"EEtiquetaL\" colspan = \"1\">OPIACEOS");
                                   out.println("<input type='checkbox' name='lOpiaceos' value='ON' onClick=\"disableUno()\" >");
                                   out.println("</td>");
                                   out.println("<td class=\"EEtiquetaL\" colspan = \"1\">FENCICLIDINA");
                                   out.println("<input type='checkbox' name='lFenciclidina' value='ON' onClick=\"disableUno()\" >");
                                   out.println("</td>");
                                   out.println("<td class=\"EEtiquetaL\" colspan = \"1\">METANFETAMINAS");
                                   out.println("<input type='checkbox' name='lMetanfetaminas' value='ON' onClick=\"disableUno()\" >");
                                   out.println("</td>");
                                   
                                   out.println("</tr>");
                                   
                                    out.println("<tr>");
                                   %>
                                    <td colspan="7" class='ETablaT'>Características de la Muestra</td>
                                  <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Temperatura correcta:"));
                                    //out.println("<td>");
                                    //out.println("<input type='checkbox' name='lTemperaC' value='ON'>");
                                    %>
                                    <td class="EEtiquetaL">SI<input type="radio"
										name="lTemperaC" value="1" checked> NO<input
										type="radio" name="lTemperaC" value="0">
                                    <%
                                    out.println("</td>");
                                    
                                    out.println(vEti.Texto("EEtiqueta","Presenta alguna alteración:"));
                                    //out.println("<td>");
                                    //out.println("<input type='checkbox' name='lAlteracion' value='ON'>");
                                    %>
                                    <td class="EEtiquetaL">SI<input type="radio"
										name="lAlteracion" value="1" checked> NO<input
										type="radio" name="lAlteracion" value="0">
                                    <%
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Se tomó bajo observación:"));
                                    //out.println("<td>");
                                    //out.println("<input type='checkbox' name='lBajoObserva' value='ON'>");
                                    %>
                                    <td colspan="5" class="EEtiquetaL">SI<input type="radio"
										name="lBajoObserva" value="1" checked> NO<input
										type="radio" name="lBajoObserva" value="0">
                                    <%
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Observaciones:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
                                    out.print("<td colspan='3'><textarea cols=\"80\" rows=\"5\" name=\"cObsMuestra\" value=\"\" onkeypress=\"fChgArea(this);\" onChange=\"fChgArea(this);\"></textarea></td>");
                                    //out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,5,"cObsMuestra","",0,"","",false,true,lCaptura));
                                    out.println("</tr>");
                                %>
                                   </table>
                               <%
                                   }
                                else {
                                 %>

                                   <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                   <tr>
                                   <td colspan="7" class="ETablaT">Búsqueda
                                   </td>
                                   </tr>
                                   <tr>
                                   <%
                                       out.println(vEti.Texto("EEtiqueta","Año:"));
                                     %>
                                    <td class="ETabla">
                                    <select name="iAnioB" size="1">
                                    <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                   </select>
                                   </tr>
                                <%
                                   out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                   out.println("<td class='ECampo'>");
                                   String UserB = "";
                                   TDGRLUMUsuario dUMUsuarioB = new TDGRLUMUsuario();
                                   Vector vcUMInicial = new Vector();
                                   TVUsuario vUsuarioB = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                   UserB = "" + vUsuarioB.getICveusuario();
                                   out.println(vEti.SelectOneRowSinTD("iCveUniMedB", "",dUMUsuarioB.getUniMedxUsu(vUsuarioB.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                   out.println("</td>");
                                %>
                                </table>
                                &nbsp;
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                   <td colspan="7" class="ETablaT">Generar Formato Federal de Control y Cadena de Custodia de Muestras para Análisis de Drogas
                                   </td>
                                 </tr>
                                 <tr>
                                 <%
                                    if (bs != null){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Unidad Médica:", "ECampo", "text", 25, 50, "iCveUniMed", bs.getFieldValue("cDscUM","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta", "Proceso:", "ECampo", "text", 25, 50, "iCveProceso", bs.getFieldValue("cDscProceso","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta", "Motivo:", "ECampo", "text", 25, 50, "iCveMotivo", bs.getFieldValue("cDscMotivo","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    /* ///////////////////////////////////////////////////////////////////////////////     */
                                    String ano = "",anio = "";
                                    ano = bs.getFieldValue("iAnio","&nbsp;").toString();
                                    anio = ano.substring(2);
                                  //        System.out.println("El año seleccionado es :" + ano);
                                    String mue = "";
                                    mue = bs.getFieldValue("iCveMuestra","&nbsp;").toString();
                                  //        System.out.println("La muestra es :" + mue);
                                    String mue_comp = "";
                                    mue_comp = "" + anio;
                                    mue_comp = mue_comp + " " + mue ;
                                  //        System.out.println("La muestra completa ES :" + mue_comp);
                                    out.print(vEti.EtiCampoCS("EEtiqueta","No. de Identificación de la Muestra (NIM):","ECampo","text",10,10,3,"iAnio", mue_comp,0,"","fMayus(this);",false,true,lCaptura));
                                    /////////////////////////////////////////////////////////////////////////////////////////*   */
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Año:"                                      ,"ECampo","text",10,10,3,"iAnio",bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Personal:","ECampo","text",10,10,3,"cDscPersonal", bs.getFieldValue("cNombreCompleto","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    ///// EDAD <<<<<<<< -----
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Edad:","ECampo","text",2,2,5,"iEdad",bs.getFieldValue("iEdad","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Prueba Rápida:","ECampo","text",10,10,6,"iCvePbaRápida", bs.getFieldValue("iCvePbaRapida","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Empresa:","ECampo","text",10,10,"iCveEmpresa" ,bs.getFieldValue("iCveEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Empresa:","ECampo","text",10,10,3,"cDscEmpresa" ,bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Modo de Transporte:","ECampo","text",10,10,"iCveMdoTrans" ,bs.getFieldValue("iCveMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,3,"cDscMdoTrans" ,bs.getFieldValue("cDscMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha Captura:","ECampo","text",10,10,"dtCaptura",bs.getFieldValue("cDtCaptura","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Fecha Recolección:","ECampo","text",10,10,3,"dtRecoleccion",bs.getFieldValue("cDtRecoleccion","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    
                                    
                                    
                                    TDPbaRapida dPbaRapida = new TDPbaRapida();
                                    TVPbaRapida vPbaRapida = new TVPbaRapida();
                                    Vector vcPbaRapida = new Vector();
                                    
                                    int ianio = Integer.parseInt(bs.getFieldValue("iAnio").toString());
                                    int personal = Integer.parseInt(bs.getFieldValue("iCvePersonal").toString());
                                    int pbarapida = Integer.parseInt(bs.getFieldValue("iCvePbaRapida").toString());
                                    vcPbaRapida = dPbaRapida.FindByAllFCC(ianio, personal, pbarapida);
                                    
                                    if(vcPbaRapida.size() > 0){
                                    	 for(int i=0;i<vcPbaRapida.size();i++){
                                    		 vPbaRapida = (TVPbaRapida) vcPbaRapida.get(i);

                                    		 String Resultado = "";
                                    		 if(vPbaRapida.getLPosiblePost()==0){Resultado = "NEGATIVO";}
                                    		 if(vPbaRapida.getLPosiblePost()==1){Resultado = "POSITIVO";}
                                    		 if(vPbaRapida.getLPosiblePost()==2){Resultado = "NO REALIZO";}
                                    
                                    

     								%>
                                        <td colspan="7" class='ETablaT'>Prueba Rápida</td>
                                      <%
                                     out.println("<tr>");
                                        out.print(vEti.Texto("EEtiqueta", "Resultado:"));
                                        out.println("<td class=\"ECampo\" colspan = \"5\">"+Resultado);
                                        out.println("</td>");
                                        out.println("</tr>");
                                       out.println("<tr>");
                                       out.print("<td rowspan=\"2\" class=\"EEtiqueta\">Positivo a:</td>");                                       
                                       out.println("<td class=\"EEtiquetaL\" colspan = \"1\">ANFETAMINAS");
                                       if(vPbaRapida.getLAnfetaminas()==1){
                                    	 	out.println("<input type='checkbox' name='lAnfetaminas' value='ON' disabled = 'true' checked>");
                                    	}else{
                                    		out.println("<input type='checkbox' name='lAnfetaminas' value='ON' disabled = 'true' >");	
                                    	}
                                       out.println("<td class=\"EEtiquetaL\" colspan = \"1\">CANNABINOIDES");
                                       if(vPbaRapida.getLCanabis()==1){
                                    	 	out.println("<input type='checkbox' name='lCannabis' value='ON' disabled = 'true' checked>");
                                    	}else{
                                    		out.println("<input type='checkbox' name='lCannabis' value='ON' disabled = 'true' >");	
                                    	}
                                       out.println("</td>");
                                       out.println("<td class=\"EEtiquetaL\" colspan = \"3\">COCAINA");
                                       if(vPbaRapida.getLCocaina()==1){
                                   	 		out.println("<input type='checkbox' name='lCocaina' value='ON' disabled = 'true' checked>");
                                   		}else{
                                   			out.println("<input type='checkbox' name='lCocaina' value='ON' disabled = 'true' >");	
                                   		}
                                       out.println("</td>");
                                       
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.println("<td class=\"EEtiquetaL\" colspan = \"1\">OPIACEOS");
                                       if(vPbaRapida.getLOpiaceos()==1){
                                  	 		out.println("<input type='checkbox' name='lOpiaceos' value='ON' disabled = 'true' checked>");
                                  		}else{
                                  			out.println("<input type='checkbox' name='lOpiaceos' value='ON' disabled = 'true' >");	
                                  		}
                                       out.println("</td>");
                                       out.println("<td class=\"EEtiquetaL\" colspan = \"1\">FENCICLIDINA");
                                       if(vPbaRapida.getLFenciclidina()==1){
                                 	 		out.println("<input type='checkbox' name='lFenciclidina' value='ON' disabled = 'true' checked>");
                                 		}else{
                                 			out.println("<input type='checkbox' name='lFenciclidina' value='ON' disabled = 'true' >");	
                                 		}
                                       out.println("</td>");
                                       out.println("<td class=\"EEtiquetaL\" colspan = \"3\">METANFETAMINAS");
                                       if(vPbaRapida.getLMetanfetaminas()==1){
                                	 		out.println("<input type='checkbox' name='lMetanfetaminas' value='ON' disabled = 'true' checked>");
                                		}else{
                                			out.println("<input type='checkbox' name='lMetanfetaminas' value='ON' disabled = 'true' >");	
                                		}
                                       out.println("</td>");
                                       
                                       out.println("</tr>");
                                       
                                    
                                       
                                       
                                    
                                    	 }
                                    }
                                    
                                   %>
                                    <td colspan="7" class='ETablaT'>Características de la Muestra</td>
                                  <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Temperatura correcta:"));
                                    out.println("<td class=\"ECampo\" colspan=\"5\">");
                                    if ( bs.getFieldValue("lTemperaC","&nbsp;").toString().compareTo("1") == 0){
                                        //out.println("<input type='checkbox' name='lTemperaC' value='ON' disabled = 'true' checked>");
                                    	%>SI<input type="radio"
    											name="lTemperaC" value="1" checked disabled = 'true'> NO<input
    											type="radio" name="lTemperaC" value="0" disabled = 'true'>
    										<%
                                    }else{
                                        //out.println("<input type='checkbox' name='lTemperaC' value='ON' disabled = 'true'>");
                                        %>SI<input type="radio"
    											name="lTemperaC" value="1" disabled = 'true'> NO<input
    											type="radio" name="lTemperaC" value="0" checked disabled = 'true'>
    										<%
                                    }
                                    
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Presenta alguna alteración:"));
                                    out.println("<td class=\"ECampo\" colspan=\"5\">");
                                    if ( bs.getFieldValue("lAlteracion","&nbsp;").toString().compareTo("1") == 0){
                                        //out.println("<input type='checkbox' name='lAlteracion' value='ON' disabled = 'true' checked>");
                                    	%>SI<input type="radio"
    											name="lAlteracion" value="1" checked disabled = 'true'> NO<input
    											type="radio" name="lAlteracion" value="0" disabled = 'true'>
    										<%
                                    }else{
                                        //out.println("<input type='checkbox' name='lAlteracion' value='ON' disabled = 'true'>");
                                    	%>SI<input type="radio"
    											name="lAlteracion" value="1" disabled = 'true'> NO<input
    											type="radio" name="lAlteracion" value="0" checked disabled = 'true'>
    										<%
                                    }
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Se tomó bajo observación:"));
                                    out.println("<td class=\"ECampo\" colspan = '5'>");
                                    if ( bs.getFieldValue("lBajoObserva","&nbsp;").toString().compareTo("1") == 0){
                                        //out.println("<input type='checkbox' name='lBajoObserva' value='ON' disabled = 'true' checked>");
                                    	%>SI<input type="radio"
    											name="lBajoObserva" value="1" checked disabled = 'true'> NO<input
    											type="radio" name="lBajoObserva" value="0" disabled = 'true'>
    										<%
                                    }else{
                                        //out.println("<input type='checkbox' name='lBajoObserva' value='ON' disabled = 'true'>");
                                    	%>SI<input type="radio"
    											name="lBajoObserva" value="1" disabled = 'true'> NO<input
    											type="radio" name="lBajoObserva" value="0" checked disabled = 'true'>
    										<%
                                    }
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones","ECampo",10,5,5,"cObsMuestra",bs.getFieldValue("cObsMuestra","").toString(),0,"","",false,true,lCaptura));
                                    out.println("</tr>");
                                    }
                                    else{
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                      out.println("</tr>");
                                    }
                                }
                            %>
                           <tr>
                           </tr>
                          </table><% // Fin de Datos %>

  </td></tr>

  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
