<%/**
 * Title: Aplicación Prueba Rápida
 * Description: Aplicación Prueba Rápida EPI
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1.0
 * Clase:pg070104040CFG
 */%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
  pg070104040CFG  clsConfig = new pg070104040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070104040.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";
  String cPersonal = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar


  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
/*  String cUpdStatus = "AddSaveOnly";*/
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  String cValor = "";

  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
  TDEMOExamen dEMOExamen = new TDEMOExamen();
  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">

function fMantenerVal(){
  var form = document.forms[0];
  form.iCveUniMed[0].value = form.iCveUniMed[1].value;
  form.hdiCveUniMed.value = form.iCveUniMed[1].value;
  form.hdiCveMotivo.value = form.iCveMotivo.value;
}
   // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
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
       cClave2  = "" + bs.getFieldValue("iAnio", "");
       cPropiedad = "" + bs.getFieldValue("iCvePbaRapida", "");
       cPersonal = "" + bs.getFieldValue("iCvePersonal", "");
     }

  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="hdPbaRapida" value="<%=cPropiedad%>" >
  <input type="hidden" name="hdPersonal" value="<%=cPersonal%>">
  <input type="hidden" name="hdiCvePersonal" value="<%=request.getParameter("hdiCvePersonal")%>">
  <input type="hidden" name="hdiCveExpediente" value="<%=request.getParameter("hdiCveExpediente")%>">
  <input type="hidden" name="hdiNumExamen" value="<%=request.getParameter("hdiNumExamen")%>">
  <input type="hidden" name="xCveUniMed" value="" >
  <input type="hidden" name="hdiCveUniMed" value="<%if(request.getParameter("hdiCveUniMed")!=null && request.getParameter("hdiCveUniMed").compareTo("hdiCveUniMed")!=0 && request.getParameter("hdiCveUniMed").compareTo("")!=0) out.print(request.getParameter("hdiCveUniMed"));%>" >
  <input type="hidden" name="iCvePersonal" value="<%if(request.getParameter("iCvePersonal")!=null && request.getParameter("iCvePersonal").compareTo("null")!=0 && request.getParameter("iCvePersonal").compareTo("")!=0) out.print(request.getParameter("iCvePersonal"));%>">
  <input type="hidden" name="pName" value="<%if(request.getParameter("pName")!=null && request.getParameter("pName").compareTo("null")!=0 && request.getParameter("pName").compareTo("")!=0) out.print(request.getParameter("pName"));%>">
  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("iCveExpediente")%>">
  <input type="hidden" name="iNumExamen" value="<%=request.getParameter("iNumExamen")%>">
  <!--input type="hidden" name="iCveUniMed" value="<%//=request.getParameter("iCveUniMed")%>"-->
  <input type="hidden" name="hdiCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <!--input type="hidden" name="hdiCveProceso" value="<%//=request.getParameter("iCveProceso")%>"-->
  <input type="hidden" name="hdiCveMotivo" value="<%=request.getParameter("hdiCveMotivo")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><%
                                   TCreaBoton bBtn = new TCreaBoton();
                                   TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                                   String cBoton = "";
                               %>
                              <td colspan="10" class="ETablaT">Aplicación Prueba Rápida EPI
                              </td>
                             </tr>
                              <tr>
                            </tr>

                            <%
                                //out.println("hdBoton:" + request.getParameter("hdBoton"));
                                //|| request.getParameter("hdBoton").compareTo("") == 0
                                if (lNuevo ){ // Modificar de acuerdo al catálogo específico

                                out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                out.println("<td class='ECampo'>");
                                String User = "";
                                TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                User = "" + vUsuario.getICveusuario();
                              %>
                                <input type="hidden" name="iCveUsuCaptura" value="<%=User%>">
                               <%

                               /* if(request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareTo("null")!=0 && request.getParameter("iCveUniMed").compareTo("")!=0){
                                  out.println(vEti.SelectOneRowSinTD("iCveUniMed", "llenaSLT(1,"+ User + ",this.value,'',document.forms[0].iCveProceso);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, request.getParameter("iCveUniMed"), true));
                                }
                                else*/ if(request.getParameter("hdiCveUniMed")!=null && request.getParameter("hdiCveUniMed").compareTo("null")!=0 && request.getParameter("hdiCveUniMed").compareTo("")!=0){
                                  out.println(vEti.SelectOneRowSinTD("iCveUniMed", "llenaSLT(1,"+ User + ",this.value,'',document.forms[0].iCveProceso);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, request.getParameter("hdiCveUniMed"), true));
                                }
                                else{
                                  out.println(vEti.SelectOneRowSinTD("iCveUniMed", "llenaSLT(1,"+ User + ",this.value,'',document.forms[0].iCveProceso);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                }
                                out.println("</td>");


                                //Insercion de los Modulos
                                /*if (request.getParameter("iCveModulo") == null){
                                  out.println("<td class='ECampo'>");
                                  out.println("<SELECT NAME='iCveModulo' SIZE='1' ");
                                  out.println("</SELECT>");
                                  out.println("</td>");
                                }
                                else{
                                  TDGRLModulo dModulo = new TDGRLModulo();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveModulo","",dModulo.FindByAll(request.getParameter("hdiCveUniMed")),"iCveModulo", "cDscModulo", request, "0", true));
                                  out.println("</td>");
                                }*/

                                /* Comento MGonzalez
                                if(request.getParameter("hdiCveUniMed")!=null && request.getParameter("hdiCveUniMed").compareTo("null")!=0 && request.getParameter("hdiCveUniMed").compareTo("")!=0 && Integer.parseInt(request.getParameter("hdiCveUniMed"))>0){
                                  TDGRLModulo dModulo = new TDGRLModulo();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveModulo","",dModulo.FindByAll(request.getParameter("hdiCveUniMed")),"iCveModulo", "cDscModulo", request, request.getParameter("hdiCveModulo"), true));
                                  out.println("</td>");
                                }
                                else{
                                  out.println("<td class='ECampo'>");
                                  out.println("<select name='iCveModulo' size='1'>");
                                  out.println("<option selected>Datos no disponibles...</option>");
                                  out.println("</select>");
                                  out.println("</td>");
                                }
                                */

                               //Módulo
                               out.print(vEti.Texto("EEtiqueta", "Módulo:"));
                               //System.out.println("vals jsp: hd:" + request.getParameter("hdiCveUniMed") + " ** fill:" + request.getParameter("iCveUniMed"));
                               if (request.getParameter("iCveModulo") == null){
                                 out.println("<td class='ECampo'>");
                                 out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveModTrans);\">");
                                 out.println("</SELECT>");
                               }
                               else{
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

                                /*if (request.getParameter("iCveProceso") == null){
                                  out.println("<td class='ECampo'>");
                                  out.println("<SELECT NAME='iCveProceso' SIZE='1' onChange=\"llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);\">");
                                  out.println("</SELECT>");
                                }
                                else{
                                  TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveProceso","llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);",dGRLUMUsuario.getProcesos(Integer.parseInt(User,10),Integer.parseInt(request.getParameter("iCveUniMed"),10)),"iCveProceso", "cDscProceso", request, "0", true));
                                  out.println("</td>");
                                }*/

                                if(request.getParameter("hdiCveUniMed")!=null && request.getParameter("hdiCveUniMed").compareTo("null")!=0 && request.getParameter("hdiCveUniMed").compareTo("")!=0 && Integer.parseInt(request.getParameter("hdiCveUniMed"))>0){
                                  TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveProceso","llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);",dGRLUMUsuario.getProcesos(Integer.parseInt(User,10),Integer.parseInt(request.getParameter("iCveUniMed"),10)),"iCveProceso", "cDscProceso", request, request.getParameter("hdiCveProceso"), true));
                                  out.println("</td>");
                                }
                                else{
                                  out.println("<td class='ECampo'>");
                                  out.println("<SELECT NAME='iCveProceso' SIZE='1' onChange=\"llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);\">");
                                  out.println("<option selected>Datos no disponibles...</option>");
                                  out.println("</SELECT>");
                                }

                                out.println("</td>");
                                out.println(vEti.Texto("EEtiqueta","Motivo:"));

                               /*if (request.getParameter("iCveMotivo") == null){
                                out.println("<td class='ECampo'>");
                                out.println("<SELECT NAME='iCveMotivo' SIZE='1' onChange=''>");
                                out.println("</SELECT>");
                                out.println("</td>");
                                }
                                else{
                                 TVMotivo vMotivo = new TVMotivo();
                                 out.println("<td>");
                                 out.println(vEti.SelectOneRowSinTD("iCveMotivo","",(Vector) AppCacheManager.getColl("GRLMotivo",request.getParameter("iCveProceso").toString()),"iCveMotivo", "cDscMotivo", request, "0", true));
                                 out.println("</td>");
                                }*/
                                if(request.getParameter("iCveProceso")!=null && request.getParameter("iCveProceso").compareTo("null")!=0 && request.getParameter("iCveProceso").compareTo("")!=0 && Integer.parseInt(request.getParameter("iCveProceso"))>0){
                                  TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveMotivo","",dGRLMotivo.getMotivos(Integer.parseInt(request.getParameter("iCveProceso"))),"iCveMotivo", "cDscMotivo", request, request.getParameter("iCveMotivo"), true));
                                  out.println("</td>");
                                }
                                else if(request.getParameter("hdiCveProceso")!=null && request.getParameter("hdiCveProceso").compareTo("null")!=0 && request.getParameter("hdiCveProceso").compareTo("")!=0 && Integer.parseInt(request.getParameter("hdiCveProceso"))>0){
                                  TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveMotivo","",dGRLMotivo.getMotivos(Integer.parseInt(request.getParameter("hdiCveProceso"))),"iCveMotivo", "cDscMotivo", request, request.getParameter("hdiCveMotivo"), true));
                                  out.println("</td>");
                                }
                                else{
                                  out.println("<td class='ECampo'>");
                                  out.println("<SELECT NAME='iCveMotivo' SIZE='1'>");
                                  out.println("<option selected>Datos no disponibles...</option>");
                                  out.println("</SELECT>");
                                  out.println("</td>");
                                }

                                out.println("</tr>");
                                out.println("<tr>");
                                //Modos de transporte

                                out.print(vEti.Texto("EEtiqueta", "Modo de Transporte:"));
                                if (request.getParameter("iCveModTrans") == null){
                                   out.println("<td class='ECampo' colspan='4'>");
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

                                out.println("<tr>");
                                out.println(vEti.Texto("EEtiqueta","Año:"));
                            %>
                                 <td class="ETabla" colspan="3">
                                 <select name="iAnio" size="1">
                                    <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                            <%
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta", "Identificador Prueba Rápida:", "ECampo", "text", 25, 50,5, "iCvePbaRapida", "", 3, "", "", true, true, lCaptura,request));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 if (request.getParameter("hdiCvePersonal") != null && request.getParameter("hdiCvePersonal").toString().compareTo("") != 0 && request.getParameter("hdiCvePersonal").toString().compareTo("null") != 0 ){
                                    out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", request.getParameter("hdiCvePersonal"), 3, "", "", true, true, false,request));
                                 }
                                 else if (request.getParameter("iCvePersonal") != null && request.getParameter("iCvePersonal").toString().compareTo("") != 0 && request.getParameter("iCvePersonal").toString().compareTo("null") != 0 ){
                                    out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", request.getParameter("iCvePersonal"), 3, "", "", true, true, false,request));
                                 }
                                 else{
                                   out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal","&nbsp;", 3, "", "", true, true, false,request));
                                 }

                                 if (request.getParameter("hdiCvePersonal") != null && request.getParameter("hdiCvePersonal").compareTo("") != 0  && request.getParameter("hdiCvePersonal").compareTo("null") != 0 ) {
                                    out.println("<input type='hidden' name='iCvePersonal' value='" + request.getParameter("hdiCvePersonal")  + "'>" );
                                    int i = 0;
                                    Object obElemento;
                                    TDPERDatos dPerDatos = new TDPERDatos();
                                    Vector vcPersonal = new Vector();
                                    vcPersonal = dPerDatos.FindByAll(request.getParameter("hdiCvePersonal"));
                                    obElemento =  vcPersonal.get(i);
                                    TVPERDatos vPERDatos = (TVPERDatos) obElemento;
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text", 50, 50,3, "cDscPersonal",vPERDatos.getCNombreCompleto(), 4, "", "", true, true, false));
                                 }
                                 else if(request.getParameter("pName")!=null && request.getParameter("pName").compareTo("null")!=0 && request.getParameter("pName").compareTo("")!=0){
                                    //System.out.println("antes de Aqui el mesandaje !!! --" + request.getParameter("pName") + "--");
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text",50, 50,3, "cDscPersonal",request.getParameter("pName"), 4, "", "", true, true, false));
                                 }
                                 else{
                                    //System.out.println("Aqui el mesandaje !!!");
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text",50, 50,3, "cDscPersonal","&nbsp;", 4, "", "", true, true, false));
                                 }

                                 //out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text", 20, 25,5, "iCvePersonal", "", 4, "", "", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.println("<td align='center' colspan='6'>");
                                 out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fMantenerVal();fSelPer(4);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta","Médico que Aplica:"));


                                 out.println("<input type='hidden' name='hdToxProceso' value='" + vParametros.getPropEspecifica("ToxProcesoLab")+ "' >");

                                 if (request.getParameter("iCveUsuAplica") == null){
                                   out.println("<td>");
                                   out.println("<SELECT NAME='iCveUsuAplica' SIZE='1' onChange=''>");
                                   out.println("</SELECT>");
                                   //out.print(vEti.SelectOneRowSinTD("iCveUsuAplica", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                                   out.println("</td>");
                                 }
                                  else{
                                TVUsuario vUsuarioAplica = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                int iUniMed = 0;

                                if(request.getParameter("iCveUniMed")==null || request.getParameter("iCveUniMed").compareTo("null")==0 ){
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                   }
                                }else{
                                      iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                                }
                                vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                 new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                                 out.println("<td>");
                                 out.print(vEti.SelectOneRowSinTD("iCveUsuAplica", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "", true));
                                 out.println("</td>");

                                 }


                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha Aplicación:", "ECampo", "text", 10, 10,5, "dtAplicacion", dFechaActual, 4, "", "", true, true, true,request));
                                 %>
                                   <input type="hidden" name="hdHoy" value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")%>" >
                                <%
                                 out.println("</tr>");
                                 out.println("</table>");
                             %>
                              <p>&nbsp;</p>
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                <tr>
                                  <td colspan="3" class="ETablaT">Sustancias</td>
                                 </tr>
                             <%
                                Vector vcSustancia = null;
                                TDSustancia dSustancia = new TDSustancia();
                                vcSustancia = dSustancia.FindByAll(" Where lAnalizada = 1 And lActivo = 1 ");
                                Object obElemento;
                                int i;

                                for (i = 0;i < vcSustancia.size();i++){
                                   obElemento =  vcSustancia.get(i);
                                   TVSustancia vSustancia = (TVSustancia) obElemento;
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta",vSustancia.getCDscSustancia()));
                                   out.println("<td>");
                                   out.println("<input type='checkbox' name='"+ vSustancia.getCDscSustancia() + "' value='ON')");
                                   out.println("</td>");
                                   out.println("</tr>");
                                }
                                out.println("</table>");
                               }
                               else{
                                  cBoton = ""+ request.getParameter("hdBoton");
                                  if (cBoton.compareTo("PbaRapida") != 0 && cBoton.compareTo("Guardar") != 0  ) {

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta","Año:"));
                               %>
                                 <td class="ETabla">
                                 <select name="iAnio" size="1">
                                    <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                                 </td>
                                 <%

                                out.print(vEti.EtiCampo("EEtiqueta", "Identificador Prueba Rápida:", "ECampo", "text", 25, 50,"iCvePbaRapida", "", 3, "", "", true, true, true,request));
                                out.println("</tr>");
                                out.println("<tr>");
                                if (request.getParameter("hdiCvePersonal") != null &&
                                    request.getParameter("hdiCvePersonal").toString().compareTo("") != 0 &&
                                    request.getParameter("hdiCvePersonal").toString().compareTo("null") != 0
                                    ){
                                  out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", request.getParameter("hdiCvePersonal"), 3, "", "", true, true, false,request));
                                }
                                else if (request.getParameter("iCvePersonal") != null && request.getParameter("iCvePersonal").toString().compareTo("") != 0 && request.getParameter("iCvePersonal").toString().compareTo("null") != 0 ){
                                  out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", request.getParameter("iCvePersonal"), 3, "", "", true, true, false,request));
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
                                    out.print(vEti.EtiCampo("EEtiqueta", "Nombre del Personal:", "ECampo", "text", 50, 50, "cDscPersonal",vPERDatos.getCNombreCompleto(), 4, "", "", true, true, false));
                                 }
                                 else if(request.getParameter("pName")!=null && request.getParameter("pName").compareTo("null")!=0){
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text",50, 50,3, "cDscPersonal",request.getParameter("pName"), 4, "", "", true, true, false));
                                 }
                                 else{
                                    out.print(vEti.EtiCampo("EEtiqueta", "Nombre del Personal:", "ECampo", "text",50, 50, "cDscPersonal","&nbsp;", 4, "", "", true, true, false));
                                 }
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.println("<td align='center' colspan='2'>");
                                 out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fSelPer(4);","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                 out.println("</td>");
                                  out.println("<td align='center' colspan='2'>");
                                 out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fPbaRapida();","PbaRapida") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 /*out.println("<tr>");
                                 out.println("<td>");
                                 out.println(bBtn.clsCreaBoton(vEntorno, 1, "PbaRapida",
                                 "bBuscar", vEntorno.getTipoImg(),
                                 "Buscar Prueba Rápida","PbaRapida", vParametros));
                                 out.println("</td>");
                                 out.println("</tr>");*/


                                  }
                                  else {
                                 if (bs != null){
                                    cValor = "Nuevo";
                                %>
                                   <input type="hidden" name="hdOPPbaRapida" value="<%=cPropiedad%>">
                                 <%

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Unidad Médica:", "ECampo", "text", 25, 50, "iCveUniMed", bs.getFieldValue("cDscUM","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                   out.print(vEti.EtiCampo("EEtiqueta", "Módulo:", "ECampo", "text", 25, 50, "iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Proceso:", "ECampo", "text", 25, 50, "iCveProceso", bs.getFieldValue("cDscProceso","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                   out.print(vEti.EtiCampo("EEtiqueta", "Motivo:", "ECampo", "text", 25, 50, "iCveMotivo", bs.getFieldValue("cDscMotivo","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Modo de Transporte:", "ECampo", "text", 25, 50,4, "iCveMdoTrans", bs.getFieldValue("cDscMdoTrans","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Año:", "ECampo", "text", 25, 50,5, "iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                   out.println("<tr>");
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Identificador Prueba Rápida:", "ECampo", "text", 25, 50,5, "iCvePbaRapida", bs.getFieldValue("iCvePbaRapida","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                   out.println("</tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal",  bs.getFieldValue("iCvePersonal","&nbsp;").toString(), 3, "", "", true, true, false,request));
                                   //Nombre de la persona
                                    int iD = 0;
                                    String cPersonalD = "";
                                    Object obElementoD;
                                    TDPERDatos dPerDatos = new TDPERDatos();
                                    Vector vcPersonal = new Vector();
                                    cPersonalD = bs.getFieldValue("iCvePersonal","&nbsp;").toString();
                                    vcPersonal = dPerDatos.FindByAll(cPersonalD);
                                    obElementoD =  vcPersonal.get(iD);
                                    TVPERDatos vPERDatos = (TVPERDatos) obElementoD;

                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text", 100, 200,3,"iCvePersonal", vPERDatos.getCNombreCompleto(), 4, "", "", true, false, lCaptura));


                                  /* out.println("<td>");
                                   out.println(bBtn.clsCreaBoton(vEntorno, 1, "Cancelar",
                                   "bBuscar", vEntorno.getTipoImg(),
                                   "Retorno a Búsqueda","Cancelar", vParametros));
                                   out.println("</td>");*/

                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Médico que Aplica:", "ECampo", "text", 100, 200,5, "iCveUsuAplica", bs.getFieldValue("cDscUsuAplica","&nbsp;").toString(), 4, "", "", true, false, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 100, 200,5, "dtAplicacion", bs.getFieldValue("cDtCaptura","&nbsp;").toString(), 4, "", "", true, false, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.println("</td>");
                                   out.println("<td align='center' colspan='6 '>");
                                   out.print(vEti.clsAnclaTexto("EEtiqueta","Regresar Búsqueda","javascript:fCancelar();","Cancelar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                   out.println("</td>");
                                    out.println("</tr>");
                                   out.println("</table>");
                                %>
                                <p>&nbsp;</p>
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                <tr>
                                 <td colspan="3" class="ETablaT">Sustancias</td>
                                </tr>
                             <%

                                Vector vcSustancia = null;
                                TDSustancia dSustancia = new TDSustancia();
                                TDPbaRapida dPbaRapida = new TDPbaRapida();
                                vcSustancia = dSustancia.FindByAll("Where lAnalizada = 1 And lActivo = 1 ");
                                //vcSustancia = dSustancia.FindByAll("");
                                Object obElemento;
                                int i;
                                int iActivo = 0;
                                int iNoActivos = 0;

                                for (i = 0;i < vcSustancia.size();i++){
                                   obElemento =  vcSustancia.get(i);
                                   TVSustancia vSustancia = (TVSustancia) obElemento;
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta",vSustancia.getCDscSustancia()));
                                   out.println("<td>");
                                   iActivo = dPbaRapida.FindSust(bs.getFieldValue("iAnio","&nbsp;").toString(),bs.getFieldValue("iCvePbaRapida","&nbsp;").toString(),"" + vSustancia.getICveSustancia());
                                   if (iActivo == 1){
                                      out.println("<input type='checkbox' name='"+ vSustancia.getCDscSustancia() + "' value='ON' disabled = 'true' checked >");
                                      iNoActivos++;
                                   }
                                   else{
                                      out.println("<input type='checkbox' name='"+ vSustancia.getCDscSustancia() + "' value='ON' disabled = 'true'>");
                                   }
                                   out.println("</td>");
                                   out.println("</tr>");
                                }
                                out.println("</table>");
                                 String cUniMed = bs.getFieldValue("iCveUniMed","&nbsp;").toString();
                                 String cProceso = bs.getFieldValue("iCveProceso","&nbsp;").toString();
                                 String cMotivo = bs.getFieldValue("iCveMotivo","&nbsp;").toString();
                                 String cUsuAplica = bs.getFieldValue("iCveUsuAplica","&nbsp;").toString();
                                 String cModulo = bs.getFieldValue("iCveModulo","&nbsp;").toString();
                                 String cTransporte = bs.getFieldValue("iCveMdoTrans","&nbsp;").toString();

                                 if (iNoActivos > 0 && request.getParameter("hdBoton").compareTo("Guardar") == 0 &&
                                    clsConfig.getISiPagina() == 1) {
                                      %>
                                    <script language="JavaScript">
                                        //alert("alert: " + <%=cModulo%> + " // " + <%=cTransporte%>);
                                        fIrPagina(<%=cUniMed%>,<%=cProceso%>,<%=cMotivo%>,<%=cUsuAplica%>,<%=cModulo%>,<%=cTransporte%>);
                                    </script>
                                   <%
                                   }
                                 }
                                 else{
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</td>");
                                   out.println("<td align='center' colspan='2'>");
                                   out.print(vEti.clsAnclaTexto("EEtiqueta","Regresar Búsqueda","javascript:fCancelar();","Cancelar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                   out.println("</td>");
                                   /*out.println("<td>");
                                   out.println(bBtn.clsCreaBoton(vEntorno, 1, "Cancelar",
                                   "bBuscar", vEntorno.getTipoImg(),
                                   "Retorno a Búsqueda","Cancelar", vParametros));
                                   out.println("</td>");*/
                                   out.println("</tr>");
                                 }
                               }
                             }
                            %>
                           <tr>
                           </tr>
                          </table><% // Fin de Datos %>

<input type="hidden" name="hdBoton" value="<%=cValor%>">
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