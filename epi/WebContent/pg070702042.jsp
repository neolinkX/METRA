<%/**
 * Title: Seguimiento del Mantenimiento
 * Description: Seguimiento del Mantenimiento
 * Copyright: Micros
 * Company: Micros
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070702041CFG
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
  pg070702042CFG  clsConfig = new pg070702042CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702042.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702042.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702042.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Fecha|Etapa|";    // modificar
  String cCveOrdenar  = "VehSeguimiento.dtSolicitud|GrlEtapa.cDscEtapa|";  // modificar
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
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  if (request.getParameter("hdLectura") != null && request.getParameter("hdLectura").equals("1"))
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

  <input type="hidden" name="dtInicio" value="<% if (request.getParameter("dtInicio") != null)  out.print(request.getParameter("dtInicio"));%>">
  <input type="hidden" name="dtFin" value="<% if (request.getParameter("dtFin") != null)  out.print(request.getParameter("dtFin"));%>">
  <input type="hidden" name="iCveUniMed" value="<% if (request.getParameter("iCveUniMed") != null)  out.print(request.getParameter("iCveUniMed"));%>">


  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
   TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdCampoClave") %>">
  <input type="hidden" name="hdLectura" value="<%=request.getParameter("hdLectura") %>">
  <input type="hidden" name="iCveMantenimiento" value="<%=request.getParameter("iCveMantenimiento") %>">
  <input type="hidden" name="iCveVehiculo" value="<%=request.getParameter("iCveVehiculo") %>">
  <input type="hidden" name="iCveUsuReg" value="<%=tvUsu!=null?tvUsu.getICveusuario():0%>">
  <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("CtrlVeh") %>">
  <input type="hidden" name="esExterno" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
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
                                    out.println(vEti.Texto("EEtiqueta", "Núm. Serie:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCNumSerie()));
                                    out.println(vEti.Texto("EEtiqueta", "Núm. Motor:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvVehiculo.getCNumMotor()));
                                    out.println("</tr><tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Inventario:"));
                                    out.println(vEti.TextoCS("ECampo", clsConfig.tvVehiculo.getCInventario(), 3));
                                 }
%>                               </tr>
                              </table>
                          <br>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Movimientos</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Fecha</td>
                              <td class="ETablaT">Etapa</td>
                              <td class="ETablaT">Solicita</td>
                              <td class="ETablaT">Usuario</td>
                              <td class="ETablaT">Observación</td>
                            </tr>
                             <% // modificar según listado
                              TFechas hoy = new TFechas();
                              if (bs != null){
                                 bs.start();
                                 TVVEHSeguimiento tvSeg = new TVVEHSeguimiento();
                                 String array = "var externos = new Array(";
                                 while(bs.nextRow()){
                                    tvSeg = (TVVEHSeguimiento) bs.getCurrentBean();
                                    String soli = bs.getFieldValue("cDscSolicitante", "&nbsp").toString();
                                    out.println("<tr>");
                                    out.println(vEti.Texto("ETablaC", hoy.getFechaDDMMYYYY(tvSeg.getDtSolicitud(), "/")));
                                    out.println(vEti.Texto("ECampo", bs.getFieldValue("cDscEtapa", "&nbsp").toString()));
                                    if (!bs.getFieldValue("cSolicitante").equals(""))
                                       soli = soli + " - " + bs.getFieldValue("cSolicitante").toString();
                                    out.println(vEti.Texto("ECampo", soli));
                                    out.println(vEti.Texto("ECampo", bs.getFieldValue("cDscUsuReg", "&nbsp").toString()));
                                    out.println(vEti.Texto("ECampo", bs.getFieldValue("cObservacion", "&nbsp").toString()));
                                    out.println("</tr>");
                                 }
                              } else {
// No hay Datos
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
                                   out.println("</tr>");
                              }
         if (request.getParameter("hdLectura") != null && request.getParameter("hdLectura").equals("0")) {
System.out.print("validacion = " +clsConfig.lRegNuevo);
           if(clsConfig.lRegNuevo){
                              String arregloExternos = "aExternos = new Array(";
                              Vector vSolicit = clsConfig.vSolicitantes;
                              boolean primera = true;
                              for (int i = 0; i < vSolicit.size(); i++) {
                                 TVGRLSolicitante tvSolic = (TVGRLSolicitante) vSolicit.get(i);
                                 if (tvSolic.getlExterno() == 1) {
                                    if (i > 0 && !primera)
                                       arregloExternos = arregloExternos + ",";
                                    arregloExternos = arregloExternos + tvSolic.getiCveSolictante();
                                    primera = false;
                                 }
                              }
                              if (vSolicit.size() <= 0 ) {
                                  arregloExternos = arregloExternos + "0";
                              }
                              arregloExternos = arregloExternos + ");";
                              out.println("<tr>");
                              out.println(vEti.CeldaCampoBR("ETablaC\" valign=\"top", "text", 10, 10, "dtSolicitud", hoy.getFechaDDMMYYYY(hoy.TodaySQL(), "/"), 0, "", "", false, true, true));
                              out.println(vEti.SelectOneRow("ECampo\" valign=\"top", "iCveEtapa", "", clsConfig.vEtapas, "iCveEtapa", "cDscEtapa", request, "0"));
//                              out.println("<td class='ECampo'>" + vEti.SelectOneRowSinTD("iCveSolicitante", arregloExternos + "fValidaExterno(aExternos, this);", clsConfig.vSolicitantes, "iCveSolictante", "cDscSolicitante", request, "-1", true, true));
                              Vector vSol = clsConfig.vSolicitantes;
                              out.println("<td class='ECampo' valign='top'><Select name=\"iCveSolicitante\" size=\"1\" onChange=\"" + arregloExternos + "fValidaExterno(aExternos, this);\">" );
                              out.println("<option value=\"\" selected>Seleccione...</option>");
                              for (int i = 0; i < vSol.size(); i++) {
                                 TVGRLSolicitante tvS = (TVGRLSolicitante) vSol.get(i);
                                 out.println("<option value=\"" + tvS.getiCveSolictante() + "\">" + tvS.getcDscSolicitante() + "</option>");
                              }
                              out.println("</select>");
                              out.println("<br>Nombre Externo:<br><input type='text' name='cSolicitante' size='25' maxlength='100'></td>");
                              out.println(vEti.SelectOneRow("ECampo\" valign=\"top", "iCveUsuSolicita", "", clsConfig.vUsuarios, "iCveUsuAutoriza", "cDscUsuAutoriza", request, "0"));
                              out.println("<td class=\"ECampo\" valign=\"top\"><textarea name=\"cObservacion\" cols=\"26\" rows=\"5\" onkeyup=\"fChgArea(this,2);\" onchange=\"fChgArea(this);\" onBlur=\"fMayus(this);\"></textarea>");
                              out.println("<br><div class'EPieR'>Caracteres Disponibles: <input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled><div></td>");
                              out.println("</tr>");
           }
         }
                            %>
                          </table>
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
