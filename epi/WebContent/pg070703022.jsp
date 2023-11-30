<%/**
 * Title: Revisión del Vehiculo
 * Description: Revisión del Vehiculo
 * Copyright: Micros
 * Company: Micros
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070703022CFG
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>

<html>
<%
  pg070703022CFG  clsConfig = new pg070703022CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070703022.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070703022.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702042.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanSC();
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  if (clsConfig.vEtapaAGuadar.size() <= 0)
     cCanWrite = "No";

  if (bs == null)
     cNavStatus = "Disabled";
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
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
//       cPosicion = Integer.toString(bs.pageNo());
     }
   TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
   String cUsuario   = "";
   String cConductor = "";
   String cLicencia  = "";
   String cVigencia  = "";
   TFechas pFecha = new TFechas(vEntorno.getNumModuloStr());
   String cRespVeh   = vParametros.getPropEspecifica("VEHRespVehiculos");
   if (tvUsu != null){
     cUsuario = tvUsu.getCNombre()!=null?tvUsu.getCNombre():"";
     cUsuario += tvUsu.getCApPaterno()!=null?" "+tvUsu.getCApPaterno():"";
     cUsuario += tvUsu.getCApMaterno()!=null?" "+tvUsu.getCApMaterno():"";
   }
   int tipo = 0;
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="iAnio" value="<%=request.getParameter("iAnio") %>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed") %>">
  <input type="hidden" name="iCveSolicitud" value="<%=request.getParameter("iCveSolicitud") %>">
  <input type="hidden" name="grabaPrimeraEtapa" value="<%=clsConfig.grabaPrimeraEtapa %>">
  <input type="hidden" name="grabaUltimaEtapa" value="<%=clsConfig.grabaUltimaEtapa %>">
  <input type="hidden" name="iCveUsuReg" value="<%=tvUsu!=null?tvUsu.getICveusuario():0%>">
  <input type="hidden" name="iCveVehiculo" value="<%=clsConfig.iCveVehiculo %>">
  <input type="hidden" name="dtSolicitudDe" value="<% if (request.getParameter("dtSolicitudDe") != null) out.print(request.getParameter("dtSolicitudDe"));%>">
  <input type="hidden" name="dtSolicitudA" value="<% if (request.getParameter("dtSolicitudA") != null) out.print(request.getParameter("dtSolicitudA"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                   <table border="0" align="center">
                      <tr valign="top"><td>
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                    <td class="ETablaT" colspan="4">Datos del Solicitante</td>
                                 </tr>
<%                               if (clsConfig.tvSolicitud != null) {
                                    if (clsConfig.tvSolicitud.getCDscUsuConductor()!=null)
                                      cConductor = clsConfig.tvSolicitud.getCDscUsuConductor();
                                    if (clsConfig.tvSolicitud.getCLicencia()!=null)
                                      cLicencia = clsConfig.tvSolicitud.getCLicencia();
                                    if (clsConfig.tvSolicitud.getLLicPermanente()==1)
                                      cVigencia = "Pemanente";
                                    else
                                      cVigencia = clsConfig.tvSolicitud.getDtVenceLic()!=null?pFecha.getFechaDDMMMYYYY(clsConfig.tvSolicitud.getDtVenceLic(),"/"):"No Registrada";
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvSolicitud.getCDscUniMed()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Módulo:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvSolicitud.getCDscModulo()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Área:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvSolicitud.getCDscArea()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Usuario:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvSolicitud.getCDscUsuSolic()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Año:"));
                                    out.println(vEti.Texto("ECampo", "" + clsConfig.tvSolicitud.getIAnio()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Fecha:"));
                                    out.println(vEti.Texto("ECampo", "" + clsConfig.tvSolicitud.getDtSolicitud()));
                                    out.println("</tr>");
                                 }
%>                            </table>
                      </td><td>
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                    <td class="ETablaT" colspan="4">Datos del Vehículo</td>
                                 </tr>
                                 <tr>
<%                               if (clsConfig.tvVehiculo != null) {
                                    out.println(vEti.Texto("EEtiqueta", "Tipo de Vehículo:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCDscTpoVehiculo()));
                                    out.println(vEti.Texto("EEtiqueta", "Placas:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCPlacas()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Marca:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCDscMarca()));
                                    out.println(vEti.Texto("EEtiqueta", "Modelo:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCDscModelo()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Serie:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCNumSerie()));
                                    out.println(vEti.Texto("EEtiqueta", "Motor:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCNumMotor()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Inventario:"));
                                    out.println(vEti.TextoCS("ECampo", clsConfig.tvVehiculo.getCInventario(), 3));
                                 } else {
                                    out.println("<tr>");
                                    out.println(vEti.Texto("ECampo", "Esta solicitud aún tiene un vehículo asignado."));
                                    out.println("</tr>");
                                 }
%>                               </tr>
                              </table>
                        </td></tr>
                  </table>
<%      if (clsConfig.tvVehiculo != null) {
               if (clsConfig.vEtapaAGuadar.size() > 0) {     %>
                      <br>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="4" class="ETablaT">Registro de Datos</td>
                            </tr>
<%                             out.println("<tr>");
                               out.println(vEti.Texto("EEtiqueta", "Etapa:"));
                               out.println("<td><select name=\"iCveEtapaSolic\" size=\"1\" disabled>");
                               for (int i = 0; i < clsConfig.vEtapas.size(); i++) {
                                  TVVEHControlSolic tvEt = new TVVEHControlSolic();
                                  tvEt = (TVVEHControlSolic) clsConfig.vEtapas.get(i);
                                  if (tvEt.getICveEtapaSolic() == clsConfig.siguienteEtapa)
                                    out.println("<option value=\"" + tvEt.getICveEtapaSolic() + "\" selected>" + tvEt.getCDscEtapaSolic() + "</option>");
                                  else
                                    out.println("<option value=\"" + tvEt.getICveEtapaSolic() + "\">" + tvEt.getCDscEtapaSolic() + "</option>");
                               }
                               out.println("<input type=\"hidden\" name=\"iCveEtapaSolic\" value=\"" + clsConfig.siguienteEtapa + "\">");
                               out.println("</td>");
                               boolean lActivar = false;
                               if (clsConfig.grabaPrimeraEtapa == 1 || clsConfig.grabaUltimaEtapa == 1)
                                 lActivar = true;
                               out.println(vEti.EtiCampo("EEtiqueta", "Kilometraje:", "ECampo", "text", 10, 8, "iKmInicial", "", 0, "", "", false, lActivar, true));
                               out.println("</tr>");
                               out.println("<tr>");
                               out.println(vEti.Texto("ETablaT", "Orden"));
                               out.println(vEti.Texto("ETablaT", "Descripción"));
                               out.println(vEti.Texto("ETablaT", "Respuesta"));
                               out.println(vEti.Texto("ETablaT", "Obligatorio"));
                               out.println("</tr>");

                           String VLogico = vParametros.getPropEspecifica("VEHLogico");
                           String VDecimal = vParametros.getPropEspecifica("VEHDecimal");
                           String VRango = vParametros.getPropEspecifica("VEHRango");
                           String VTexto = vParametros.getPropEspecifica("VEHTexto");
                           StringTokenizer st;
                           String arregloTipodeDatos = "tipoDato = new Array();";

                           for(int i = 0; i < clsConfig.vEtapaAGuadar.size(); i++) {
                               arregloTipodeDatos = arregloTipodeDatos + " tipoDato[" + i + "]=[";
                               TVVEHConfControl tvCfgCtr = (TVVEHConfControl) clsConfig.vEtapaAGuadar.get(i);
// Ver que tipo d Control es
                               String control = "" + tvCfgCtr.getICveTpoResp();
                               st = new StringTokenizer(VLogico, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 1;
                                  }
                               }
                               st = new StringTokenizer(VDecimal, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 2;
                                  }
                               }
                               st = new StringTokenizer(VRango, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 3;
                                  }
                               }
                               st = new StringTokenizer(VTexto, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 4;
                                  }
                               }

                               out.println("<tr>");
                               out.println(vEti.Texto("ECampo", "" +tvCfgCtr.getIOrden()));
                               out.println(vEti.Texto("ECampo", tvCfgCtr.getCDscTpoResp()));
                               String cEtiqueta = tvCfgCtr.getCEtiqueta()!=null?tvCfgCtr.getCEtiqueta():"";
// Dibujar los Controles Preguntas
                               switch (tipo) {
                                  case 1:
                                     // Logico
                                     out.println("<td class=\"ECampo\"><input type=\"radio\" name=\"control" + i + "\" value=\"1\">Sí&nbsp;&nbsp;");
                                     out.println("<input type=\"radio\" checked name=\"control" + i + "\" value=\"0\">No&nbsp;&nbsp;" + cEtiqueta + "</td>");
                                     arregloTipodeDatos = arregloTipodeDatos + "0,1,'control" + i + "'";
                                     break;
                                  case 2:
                                     // Decimal
                                     out.println("<td><input type=\"text\" name=\"control" + i + "\" value=\"\" size=\"10\" maxlength=\"10\">&nbsp;&nbsp;" + cEtiqueta + "</td>");
                                     arregloTipodeDatos = arregloTipodeDatos + "4,2,'control" + i + "'";
                                     break;
                                  case 3:
                                     // Rango
                                     out.println("<td class=\"ECampo\"><input type=\"text\" name=\"dRango1\" value=\"\" size=\"10\" maxlength=\"10\">");
                                     out.println(" A: <input type=\"text\" name=\"dRango2\" value=\"\" size=\"10\" maxlength=\"10\">&nbsp;&nbsp;" + cEtiqueta + "</td>");
                                     arregloTipodeDatos = arregloTipodeDatos + "3,3,'control" + i + "'";
                                     break;
                                  case 4:
                                     // Texto
                                     out.println("<td class=\"EPieR\"><textarea value=\"\" name=\"control" + i + "\" rows=\"5\" cols=\"50\" onBlur=\"fMayus(this);\" onkeyup=\"fChgArea(this);\" onchange=\"fChgArea(this);\"></textarea>&nbsp;&nbsp;" + cEtiqueta);
                                     out.println("<br>Caracteres Disponibles: <input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
                                     arregloTipodeDatos = arregloTipodeDatos + "2,4,'control" + i + "'";
                                     break;
                                  default :
                                     out.println(vEti.Texto("ECampo", "" + tvCfgCtr.getICveTpoResp()));
                               }
                               out.println(vEti.Texto("ECampo", tvCfgCtr.getLObligatorio() == 1 ? "SI" : "NO"));
                               arregloTipodeDatos = arregloTipodeDatos + "," + tvCfgCtr.getLObligatorio();
                               arregloTipodeDatos = arregloTipodeDatos + ",'" + tvCfgCtr.getCDscTpoResp() + "'";
                               out.println("<input type=\"hidden\" name=\"campo" + i + "\" value=\"" + tipo + "\">");
                               out.println("<input type=\"hidden\" name=\"iCveConfControl" + i + "\" value=\"" + tvCfgCtr.getICveConfControl() + "\">");
                               out.println("</tr>");
                               arregloTipodeDatos = arregloTipodeDatos + "];";
                           }
%>
                          </table>
<SCRIPT LANGUAGE="JavaScript">
   function fValorDatos(){
     <%=arregloTipodeDatos %>
     return fValidaDatos(tipoDato);
   }
</SCRIPT>
<%             }   // del if (clsConfig.vEtapaAGuadar != null) {
               out.println("<input type=\"hidden\" name=\"num_reg\" value=\"" + clsConfig.vEtapaAGuadar.size() + "\">");
%>
                      <br>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Datos Registrados en Etapas Anteriores</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Etapa</td>
                              <td class="ETablaT">Fecha</td>
                              <td class="ETablaT">Orden</td>
                              <td class="ETablaT">Descripción</td>
                              <td class="ETablaT">Respuesta</td>
                            </tr>
                             <% // modificar según listado
                              TFechas hoy = new TFechas();
                              if (bs != null){
                                 TVVEHControlSolic tvCtrol = new TVVEHControlSolic();
                                 String cDscEtapaAnterior = "";
                                 while(bs.nextRow()){
                                    tvCtrol = (TVVEHControlSolic) bs.getCurrentBean();
                                    out.println("<tr>");
                                    if (!cDscEtapaAnterior.equals(bs.getFieldValue("cDscEtapaSolic").toString())) {
                                       out.println(vEti.Texto("ECampo", bs.getFieldValue("cDscEtapaSolic", "&nbsp").toString()));
                                       if (tvCtrol.getDtRegistro() != null && tvCtrol.getDtRegistro().toString().compareTo("")!=0 &&
                                           tvCtrol.getDtRegistro().toString().compareTo("null")!=0)
                                           out.println(vEti.Texto("ECampo", hoy.getFechaDDMMYYYY(tvCtrol.getDtRegistro(), "/")));
                                       else
                                          out.println(vEti.Texto("ECampo", "&nbsp;"));
                                    } else {
                                       out.println("<td>&nbsp;</td>");
                                       out.println("<td>&nbsp;</td>");
                                    }
                                    out.println(vEti.Texto("ECampo", bs.getFieldValue("iOrden", "&nbsp").toString()));
                                    out.println(vEti.Texto("ECampo", bs.getFieldValue("cDscTpoResp", "&nbsp").toString()));
// Ver que tipo d Control es
                               String VLogico = vParametros.getPropEspecifica("VEHLogico");
                               String VDecimal = vParametros.getPropEspecifica("VEHDecimal");
                               String VRango = vParametros.getPropEspecifica("VEHRango");
                               String VTexto = vParametros.getPropEspecifica("VEHTexto");
                               String control = "" + bs.getFieldValue("iCveTpoResp");
                               StringTokenizer st;
                               st = new StringTokenizer(VLogico, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 1;
                                  }
                               }
                               st = new StringTokenizer(VDecimal, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 2;
                                  }
                               }
                               st = new StringTokenizer(VRango, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 3;
                                  }
                               }
                               st = new StringTokenizer(VTexto, ",", false);
                               while (st.hasMoreTokens()) {
                                  if (st.nextToken().toString().equals(control)) {
                                     tipo = 4;
                                  }
                               }
// Dibujar los Controles
                               String cEtiqueta = bs.getFieldValue("cEtiqueta","").toString();
                               switch (tipo) {
                                  case 1:
                                     // Logico
                                     if (bs.getFieldValue("lLogico") != null && bs.getFieldValue("lLogico").toString().equals("1"))
                                        out.println("<td class='ECampo' align=\"center\">SI&nbsp;&nbsp;&nbsp;"+ cEtiqueta + "</td>");
                                     else if (bs.getFieldValue("lLogico") != null && bs.getFieldValue("lLogico").toString().equals("0"))
                                        out.println("<td class='ECampo' align=\"center\">NO&nbsp;&nbsp;&nbsp;"+ cEtiqueta + "</td>");
                                     break;
                                  case 2:
                                     // Decimal
                                     out.println("<td class='ECampo'>" + bs.getFieldValue("dValorIni", "&nbsp;") + "&nbsp;&nbsp;&nbsp;"+ cEtiqueta + "</td>");
                                     break;
                                  case 3:
                                     // Rango
                                     out.println("<td class=\"ECampo\">" + bs.getFieldValue("dValorIni", "&nbsp;") + " a " + bs.getFieldValue("dValorFin", "&nbsp;") + "&nbsp;&nbsp;&nbsp;"+ cEtiqueta + "</td>");
                                     break;
                                  case 4:
                                     // Texto
                                     out.println("<td class=\"ECampo\">" + bs.getFieldValue("cCaracter", "&nbsp;") + "&nbsp;&nbsp;&nbsp;"+ cEtiqueta + "</td>");
                                     break;
                                  default :
                                     out.println(vEti.Texto("ECampo", "La Respuesta."));
                               }

                                    out.println("</tr>");
                                    cDscEtapaAnterior = bs.getFieldValue("cDscEtapaSolic").toString();
                                 }
                              } else {
// No hay Datos
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
                                   out.println("</tr>");
                              }
                            %>
                          </table>
                      <br>
                      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                        <tr>
                          <td class="ETabla" width="50%">
                            <table border="0">
                              <tr>
                                <td class="ETabla"><strong>Conductor:&nbsp;&nbsp;</strong><%=cConductor%><br><strong>Licencia:&nbsp;&nbsp;</strong><%=cLicencia%><br><strong>Vigencia:&nbsp;&nbsp;</strong><%=cVigencia%></td>
                              </tr>
                              <tr>
                                <td class="ETabla"><br><strong>Cargo:&nbsp;&nbsp;</strong>___________________________________</td>
                              </tr>
                              <tr>
                                <td class="ETablaC"><br><br><br><br><br>______________________________<br><strong>Firma</strong></td>
                              </tr>
                            </table>
                          </td>
                          <td class="ETabla" width="50%">
                            <table border="0">
                              <tr>
                                <td class="ETabla"><strong>Entrega/Recibe:&nbsp;&nbsp;</strong><%=cRespVeh%><br><strong>y/o&nbsp;&nbsp;</strong><%=cUsuario%></td>
                              </tr>
                              <tr>
                                <td class="ETabla"><br><strong>Cargo:&nbsp;&nbsp;</strong>___________________________________</td>
                              </tr>
                              <tr>
                                <td class="ETablaC"><br><br><br><br><br>______________________________<br><strong>Firma</strong></td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                      </table>
<%         }   // del if (clsConfig.tvVehiculo != null) {    %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
