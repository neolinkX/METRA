<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070803050CFG  clsConfig = new pg070803050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070803050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Fecha/Hora de Solicitud|Área|";    // modificar
  String cCveOrdenar  = "ss.dtSolicitud|ss.iCveArea|";  // modificar

  String cDscFiltrar  = "Fecha de Solicitud|Clave de Solicitud|";    // modificar
  String cCveFiltrar  = "ss.dtSolicitud|ss.iCveSolicSum|";  // modificar
  String cTipoFiltrar = "5|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Reporte";            // modificar
  DecimalFormat df = new DecimalFormat("#,##0");

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TFechas dtFecha = new TFechas();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";

  String cNavStatus  = "";
  if(request.getParameter("hdNavStatus")!=null && request.getParameter("hdNavStatus").compareTo("null")!=0)
    cNavStatus  = clsConfig.getNavStatus();
  else
    cNavStatus  = "Disabled";

  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";/*

  TVVEHVehiculo vVEHVehiculo = new TVVEHVehiculo();
  TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
  Vector vcVEHVehiculo = new Vector();*/

  int iKmDif = 0;
  int iKmSum = 0;
  int iAsignados = 0;
  int iEntregados = 0;
  int iCancelados = 0;

  int iAnioIni = new Integer(vParametros.getPropEspecifica("iAniosIni")).intValue();
  int iAnioFin = dtFecha.getIntYear(dtFecha.TodaySQL())+1;
//  String cMeses = vParametros.getPropEspecifica("NombresMes");
//  StringTokenizer st = new StringTokenizer(cMeses,",");
  int j = 0; //Auxiliar para iMes
  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<head>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">

  <input type="hidden" name="hdIAnio" value="">
  <input type="hidden" name="hdICveAlmacen" value="">
  <input type="hidden" name="hdICveSolicSum" value="">
  <input type="hidden" name="hdDscUniMed" value="">
  <input type="hidden" name="hdDscArea" value="">
  <input type="hidden" name="hdDtSolicitud" value="">
  <input type="hidden" name="hdDtSuministro" value="">
  <input type="hidden" name="hdDscPrioridad" value="">
  <input type="hidden" name="hdDscSolicitud" value="">
  <input type="hidden" name="hdNavStatus" value="<%if(request.getParameter("hdNavStatus")==null) out.print("1"); else out.print(request.getParameter("hdNavStatus"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
     <td valign="top">
                          &nbsp;
                          <input type="hidden" name="hdBoton" value="">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                           <tr><td colspan="6" class="ETablaT">Buscar Solicitudes
                            <%
// S E L E C T   L A B O R A T O R I O
                               out.print("<tr>");
                               String cValActual = "";
                               out.print(vEti.Texto("ETablaTR", "Unidad Médica:"));
                               TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                               TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                               boolean lFirst = false;
                               BeanScroller beanSc = new BeanScroller(dUMUsuario.FindByAll(" where GRLUMUsuario.iCveProceso = " + iCveProceso + " and GRLUMUsuario.iCveUsuario = " + vUsuario.getICveusuario()));
                               if(request.getParameter("iCveUniMed")!=null &&request.getParameter("iCveUniMed").compareTo("")!=0)
                                 cValActual = "" + request.getParameter("iCveUniMed");
                               else
                                 cValActual = "0";
                               out.print("<td><select name=\"iCveUniMed\" size=\"1\" OnChange=\"llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);\">");
                               if (beanSc != null) {
                                 out.print("<option value=0>Seleccione...</option>");
                                 int i;
                                 for (i = 1; i <= beanSc.rowSize(); i++) {
                                   beanSc.setRowIdx(i);
                                   String CampoClave = "" + beanSc.getFieldValue("iCveUniMed", "");
                                   if (cValActual.compareTo("") == 0 && lFirst == false) {
                                     CampoClave = " selected ";
                                     lFirst = true;
                                   }
                                   else {
                                     if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
                                       CampoClave = " selected ";
                                     }else{
                                       CampoClave = "";
                                     }
                                   }
                                   out.print("<option " + CampoClave + " value=\"" + beanSc.getFieldValue("iCveUniMed", "" + i) + "\">" + beanSc.getFieldValue("cDscUniMed", "") + "</option>");
                                 }
                                 out.print("</select></td>");
                               }

// S E L E C T   M O D U L O
                               out.print("<td class='ETablaTR'>Módulo:</td>");
                               out.print("<td class='ETabla'>");
                               out.println("<select name=\"iCveModulo\" size=\"1\" onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\">");
                               if(request.getParameter("iCveUniMed")!= null){
                                 TDGRLModulo dGRLModulo = new TDGRLModulo();
                                 TVGRLModulo vGRLModulo = new TVGRLModulo();
                                 Vector vcGRLModulo = new Vector();
                                 vcGRLModulo = dGRLModulo.FindByAll(" where iCveUniMed = " + request.getParameter("iCveUniMed"));
                                 if (vcGRLModulo.size() > 0){
                                   out.print("<option value = 0>Seleccione...</option>");
                                   for (int i = 0; i < vcGRLModulo.size(); i++){
                                     vGRLModulo = (TVGRLModulo)vcGRLModulo.get(i);
                                     if (request.getParameter("iCveModulo")!=null && request.getParameter("iCveModulo").compareToIgnoreCase(new Integer(vGRLModulo.getICveModulo()).toString())==0)
                                       out.print("<option value = " + vGRLModulo.getICveModulo() + " selected>" + vGRLModulo.getCDscModulo() + "</option>");
                                     else
                                       out.print("<option value = " + vGRLModulo.getICveModulo() + ">" + vGRLModulo.getCDscModulo() + "</option>");
                                   }
                                 }
                                 else{
                                   out.print("<option value = 0>Datos no disponibles</option>");
                                 }
                               }
                               else if((request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed").toString())<1) || request.getParameter("iCveUniMed")==null)
                                 out.println("<option value='0' selected>Datos no disponibles</option>");
                                 out.print("</select></td>");
// S E L E C T   A R E A
                               out.print("<td class='ETablaTR'>Área:</td>");
                               out.print("<td class='ETabla'>");
                               out.println("<select name=\"iCveArea\" size=\"1\">");
                               if(request.getParameter("iCveModulo")!= null){
                                 DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
                                 TVGRLAreaModulo  vGRLAreaModulo = new TVGRLAreaModulo();
                                 Vector vcGRLAreaModulo = new Vector();
                                 vcGRLAreaModulo = dGRLAreaModulo.FindByAll(" where iCveUniMed = " + request.getParameter("iCveUniMed") + " and iCveModulo = " + request.getParameter("iCveModulo"));
                                 if (vcGRLAreaModulo.size() > 0){
                                   out.print("<option value = 0>Seleccione...</option>");
                                   for (int i = 0; i < vcGRLAreaModulo.size(); i++){
                                     vGRLAreaModulo = (TVGRLAreaModulo)vcGRLAreaModulo.get(i);
                                     if(request.getParameter("iCveArea")!=null && request.getParameter("iCveArea").compareToIgnoreCase(new Integer(vGRLAreaModulo.getICveArea()).toString())==0)
                                       out.print("<option value = " + vGRLAreaModulo.getICveArea() + " selected>" + vGRLAreaModulo.getCDscArea() + "</option>");
                                     else
                                       out.print("<option value = " + vGRLAreaModulo.getICveArea() + ">" + vGRLAreaModulo.getCDscArea() + "</option>");
                                   }
                                 }
                                 else{
                                   out.print("<option value = 0>Datos no disponibles</option>");
                                 }
                               }
                               else if((request.getParameter("iCveModulo")!=null && Integer.parseInt(request.getParameter("iCveModulo").toString())<1) || request.getParameter("iCveModulo")==null)
                                 out.println("<option value='0' selected>Datos no disponibles</option>");
                                 out.print("</select></td>");
                               out.print("</tr>");

// S E L E C T   A Ñ O
                               out.println("<td class=\"ETablaTR\">Año:</td>");
                               out.println("<td class=\"ETabla\">");
                               out.println("<select name=\"iAnio\" onchange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\" >");
                               out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                 if(request.getParameter("iAnio")!=null && !request.getParameter("iAnio").equals("") && Integer.parseInt(request.getParameter("iAnio"))==i)
                                   out.print("<option value = \"" + i + "\" selected >" + i + "</option>");
                                 else
                                   out.print("<option value = \"" + i + "\">" + i + "</option>");
                               }
                               out.print("</select>");
                               out.print("</td>");

// S E L E C T  P E R I O D O
                               out.print("<td class='ETablaT'>Periodo:</td>");
                               out.println("<td><select name=\"iCvePeriodo\" size=\"1\">");
                               out.print("<option value = \"-1\" > Seleccione...</option>");
                               String cAnio = "";
                               if(request.getParameter("iAnio") != null){
                                  cAnio = "WHERE iAnio = "+request.getParameter("iAnio");
                               }
                               Vector vPeriodo = new TDALMPeriodo().FindByAll(cAnio,"") ;
                               if(vPeriodo != null && vPeriodo.size() > 0){
                                 for(int k = 0; k < vPeriodo.size(); k++) {
                                   TVALMPeriodo tvALMPeriodo = (TVALMPeriodo)vPeriodo.get(k);
                                   if (request.getParameter("iCvePeriodo") != null && Integer.parseInt(request.getParameter("iCvePeriodo")) == tvALMPeriodo.getICvePeriodo())
                                     out.print("<option value = \"" + tvALMPeriodo.getICvePeriodo() + "\" selected >" + tvALMPeriodo.getCDscPeriodo() + "</option>");
                                   else
                                     out.print("<option value = \"" + tvALMPeriodo.getICvePeriodo() + "\" >" + tvALMPeriodo.getCDscPeriodo() + "</option>");
                                 }
                               }
                               out.println("</select></td>");

                               out.println("</tr>");
                               out.println("</table>");
                               out.println("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3' width=500>");
                               out.println("<tr>");
// SELECT DE TIPO ARTICULO
                               out.println("<td class=\"ETablaT\" colspan=\"6\">Art&iacute;culos</td>");
                               out.println("</tr>");
                               out.println("<tr>");
                               out.println("<td class=\"ETablaTR\">Tipo Art&iacute;culo:</td>");
                               out.println("<td class=\"ETabla\" colspan=\"2\">");

                               out.println("<select name=\"iCveTpoArticulo\"  onChange=\"llenaSLT(3,'',document.forms[0].iCveTpoArticulo.value,'',document.forms[0].iCveFamilia,'');\" >");
                               out.print("<option value = \"0\" >Seleccione...</option>");
                               Vector vTipoArticulo = new TDALMTpoArticulo().FindByAll("", "");

                               if (vTipoArticulo != null && vTipoArticulo.size() > 0){
                                 for (int i=0; i < vTipoArticulo.size(); i++){
                                   TVALMTpoArticulo tvTpoArticulo = (TVALMTpoArticulo)vTipoArticulo.get(i);
                                   if(request.getParameter("iCveTpoArticulo")!=null && Integer.parseInt(request.getParameter("iCveTpoArticulo")) == tvTpoArticulo.getICveTpoArticulo())
                                     out.print("<option value = \"" + tvTpoArticulo.getICveTpoArticulo() + "\" selected >" + tvTpoArticulo.getCDscBreve() + "</option>");
                                   else
                                     out.print("<option value = \"" + tvTpoArticulo.getICveTpoArticulo() + "\">" + tvTpoArticulo.getCDscBreve() + "</option>");
                                 }
                               }
                               out.print("</select>");
                               out.print("</td>");
// FAMILIA
                               out.println("<td class=\"ETablaTR\">Familia:</td>");
                               out.println("<td class=\"ETabla\" colspan=\"2\">");

                               out.println("<select name=\"iCveFamilia\" onChange=\"llenaSLT(4,'',document.forms[0].iCveTpoArticulo.value,document.forms[0].iCveFamilia.value,document.forms[0].iCveArticulo,'','');\" >");
                               out.print("<option value = \"0\">Seleccione...</option>");
                               String cFamilia = "";
                               if(request.getParameter("iCveTpoArticulo") != null)
                                 cFamilia = "WHERE F.iCveTpoArticulo = "+ request.getParameter("iCveTpoArticulo");

                               Vector vFamilia = new TDALMFamilia().FindByAll(cFamilia, "");

                               if (vFamilia != null && vFamilia.size() > 0){
                                 for (int i=0; i < vFamilia.size(); i++){
                                   TVALMFamilia tvFamilia = (TVALMFamilia)vFamilia.get(i);
                                   if(request.getParameter("iCveFamilia") != null && Integer.parseInt(request.getParameter("iCveFamilia")) == tvFamilia.getiCveFamilia())
                                     out.print("<option value = \"" + tvFamilia.getiCveFamilia() + "\" selected >" + tvFamilia.getcDscBreve() + "</option>");
                                   else
                                     out.print("<option value = \"" + tvFamilia.getiCveFamilia() + "\">" + tvFamilia.getcDscBreve() + "</option>");
                                 }
                               }
                               out.print("</select>");
                               out.print("</td>");
                               out.println("</tr>");

                               out.println("<tr>");
                               out.println("<td class=\"ETablaTR\">Art&iacute;culo:</td>");
                               out.println("<td class=\"ECampo\" colspan=\"5\">");
                               out.println("<select name=\"iCveArticulo\">");
                               String cvArticulo    = "";
                               if (request.getParameter("iCveArticulo") != null){
                                 if (request.getParameter("iCveArticulo") != "")
                                   cvArticulo = request.getParameter("iCveArticulo");
                               }
                               out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                              if(request.getParameter("iCveTpoArticulo") != null && request.getParameter("iCveFamilia") != null){
                                Vector vArticulo = clsConfig.Articulo(request.getParameter("iCveTpoArticulo"), request.getParameter("iCveFamilia"));
                                if(vArticulo != null && vArticulo.size() > 0 ){
                                  for(int i = 0; i < vArticulo.size(); i++){
                                    TVALMArticulo VALMArticulo = (TVALMArticulo) vArticulo.get(i);
                                    if(cvArticulo.compareToIgnoreCase(new Integer(VALMArticulo.getiCveArticulo()).toString()) == 0)
                                      out.print("<option selected value=\"" + new Integer(VALMArticulo.getiCveArticulo()).toString() + "\">"  + VALMArticulo.getcDscBreve() + "</option>");
                                    else
                                      out.print("<option value=\"" + new Integer(VALMArticulo.getiCveArticulo()).toString() + "\">"  + VALMArticulo.getcDscBreve() + "</option>");
                                  }
                                }
                              }
                              out.println("</select>");
                       out.println("</td>");
                       out.println("</tr>");

                    out.println("</table>");
                    out.println("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3' width=500>");
                    out.println("<tr><td colspan='17' class='ETablaT'>Datos de Búsqueda</td></tr>");

// R E S U L T A D O S   D E   L A   C O N S U L T A
                                  if (bs != null){
                                    bs.start();
                                    out.println("<tr><td colspan='14' class='ETablaT'>Solicitudes Encontradas "+clsConfig.getICantidad()+" </td></tr>");
                                    out.print("<tr>");
                                      out.print("<td class='ETablaT' >N&uacute;m. Solicitud</td>");
                                      out.print("<td class='ETablaT' >Fecha Solicitud</td>");
                                      out.print("<td class='ETablaT' >Unidad M&eacute;dica</td>");
                                      out.print("<td class='ETablaT' >M&oacute;dulo</td>");
                                      out.print("<td class='ETablaT' >&Aacute;rea</td>");
                                      out.print("<td class='ETablaT' >Usuario Solicitante</td>");
                                      out.print("<td class='ETablaT' >Tipo Art&iacute;culo</td>");
                                      out.print("<td class='ETablaT' >Familia</td>");
                                      out.print("<td class='ETablaT' >Art&iacute;culo</td>");
                                      out.print("<td class='ETablaT' >Unidad</td>");
                                      out.print("<td class='ETablaT' >Unidad Solicitada</td>");
                                      out.print("<td class='ETablaT' >Unidad Autorizada</td>");
                                      out.print("<td class='ETablaT' >Fecha de Suministro</td>");
                                      out.print("<td class='ETablaT' >Meta</td>");
                                    out.print("</tr>");
                                      while(bs.nextRow()){
                                        out.print("<tr>");
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveSolicSum", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("dtSolicitud", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscModulo", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscArea", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cNombre", "&nbsp;")+" "+bs.getFieldValue("cApPaterno", "&nbsp;")+" "+ bs.getFieldValue("cApMaterno", "&nbsp;")));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscTpoArticulo", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscFamilia", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscArticulo", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscUnidad", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("dUnidSolicita", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("dUnidAutor", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("dtSuministro", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cDscMeta", "&nbsp;").toString()));
                                        out.print("</tr>");
                                      }
                                    }
                                    else{
                                      out.print("<tr><td class='ETablaC' colspan='14'>No existen datos coincidentes con el filtro proporcionado.</td></tr>");
                                    }
                            %>
                          </table><% // Fin de Datos %>
     </td>
   </tr>
  <%
    }else{%>
      <script language="JavaScript">
         fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
<%
  if(clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() > 0){
     out.println(clsConfig.getSbReporte());
  }
  else if(request.getParameter("hdBoton") != null && request.getParameter("hdBoton").equals("Generar Reporte") &&
    clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() == 0){
%><script language="JavaScript">alert("No Hay Registros Coincidentes.");</script>
<%
  }
%>

</form>
</body>
<%= vErrores.muestraError()%><%= new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%  vEntorno.clearListaImgs();%>
</html>