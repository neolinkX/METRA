<%/**
 * Title: Modificación de Kilometraje
 * Description: Modificación de Kilometraje
 * Copyright: Micros
 * Company: Micros
 * @author Itzia Sánchez Méndez
 * @version 1.0
 * Clase: pg070703023CFG
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
  pg070703023CFG  clsConfig = new pg070703023CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070703023.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070703023.jsp\" target=\"FRMCuerpo"); // modificar
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
                                    out.println(vEti.Texto("EEtiqueta", "Solicitud:"));
                                    out.println(vEti.Texto("ECampo", "" + clsConfig.tvSolicitud.getICveSolicitud()));
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
                                    out.println("</tr><tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta", "Km. Inicial:", "ECampo", "text", 10, 8, "iKmInicial", String.valueOf(clsConfig.tvSolicitud.getIKmInicial()), 0, "", "", false, true, true));
                                    out.println(vEti.EtiCampo("EEtiqueta", "Km. Final:", "ECampo", "text", 10, 8, "iKmFinal", String.valueOf(clsConfig.tvSolicitud.getIKmFinal()), 0, "", "", false, true, true));
                                    out.println("</tr>");

                                 } else {
                                    out.println("<tr>");
                                    out.println(vEti.Texto("ECampo", "Esta solicitud aún tiene un vehículo asignado."));
                                    out.println("</tr>");
                                 }
%>                               </tr>
                              </table>
                        </td></tr>
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
