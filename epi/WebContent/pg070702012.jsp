<%/**
 * Title:       Sistema Integral de Protección y Medicina Preventiva en el Transporte.
 * Description: Módulo de Control de Vehículos.
 * Copyright:   Copyright (c) 2004
 * Company:     Micros Personales S.A. de C.V.
 * @author      LSC. Rafael Miranda Blumenkron
 * @version 1.0
 * Clase:       JSP para despliegue de ubicaciones de un vehículo.
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070702012CFG  clsConfig = new pg070702012CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Ubicación|";    // modificar
  String cCveOrdenar  = "VEHUbicacion.iCveUbicacion|";  // modificar
  String cDscFiltrar  = "Ubicación|";    // modificar
  String cCveFiltrar  = "VEHUbicacion.iCveUbicacion|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
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
  TFechas pFecha = new TFechas("07");

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
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
    Vector vVehiculo = clsConfig.getVVehiculo();
    if (vVehiculo.size() != 0){
      TVVEHVehiculo VVehic = (TVVEHVehiculo)vVehiculo.get(0);
      cClave = "" + VVehic.getICveVehiculo();
    }
  %>
  <input type="hidden" name="hdRowNum" value="<%=request.getParameter("hdRowNum")!=null?request.getParameter("hdRowNum"):""%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdVehiculo" value="<%= request.getParameter("hdVehiculo")!=null?request.getParameter("hdVehiculo"):request.getParameter("hdCampoClave")%>">
  <input type="hidden" name="iCveVehiculo" value="<%= request.getParameter("hdVehiculo")!=null?request.getParameter("hdVehiculo"):request.getParameter("hdCampoClave")%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td valign="top"><input type="hidden" name="hdBoton" value="">&nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="4" class="ETablaT">Datos del Vehículo</td>
                              <%
                                String cTipo   = "&nbsp;",
                                       cPlaca  = "&nbsp;",
                                       cMarca  = "&nbsp;",
                                       cModelo = "&nbsp;",
                                       cSerie  = "&nbsp;",
                                       cMotor  = "&nbsp;",
                                       cInventario = "&nbsp;";
                                vVehiculo = clsConfig.getVVehiculo();
                                if (vVehiculo.size() != 0){
                                  TVVEHVehiculo VVehiculo = (TVVEHVehiculo)vVehiculo.get(0);
                                    cTipo   = VVehiculo.getCDscTpoVehiculo()!=null?VVehiculo.getCDscTpoVehiculo():"&nbsp;";
                                    cPlaca  = VVehiculo.getCPlacas()!=null?VVehiculo.getCPlacas():"&nbsp;";
                                    cMarca  = VVehiculo.getCDscMarca()!=null?VVehiculo.getCDscMarca():"&nbsp;";
                                    cModelo = VVehiculo.getCDscModelo()!=null?VVehiculo.getCDscModelo():"&nbsp;";
                                    cSerie  = VVehiculo.getCNumSerie()!=null?VVehiculo.getCNumSerie():"&nbsp;";
                                    cMotor  = VVehiculo.getCNumMotor()!=null?VVehiculo.getCNumMotor():"&nbsp;";
                                    cInventario = VVehiculo.getCInventario()!=null?VVehiculo.getCInventario():"&nbsp;";
                                }
                              %>
                            </tr>
                            <tr>
                              <td colspan="1" class="EEtiqueta">Tipo de Vehículo:</td>
                              <td colspan="1" class="ETabla"><%= cTipo %></td>
                              <td colspan="1" class="EEtiqueta">Placas:</td>
                              <td colspan="1" class="ETabla"><%= cPlaca %></td>
                            </tr>
                            <tr>
                              <td colspan="1" class="EEtiqueta">Marca:</td>
                              <td colspan="1" class="ETabla"><%= cMarca %></td>
                              <td colspan="1" class="EEtiqueta">Modelo:</td>
                              <td colspan="1" class="ETabla"><%= cModelo %></td>
                            </tr>
                            <tr>
                              <td colspan="1" class="EEtiqueta">No. Serie:</td>
                              <td colspan="1" class="ETabla"><%= cSerie %></td>
                              <td colspan="1" class="EEtiqueta">No. Motor:</td>
                              <td colspan="1" class="ETabla"><%= cMotor %></td>
                            </tr>
                            <tr>
                              <td colspan="1" class="EEtiqueta">Inventario:</td>
                              <td colspan="3" class="ETabla"><%= cInventario %></td>
                            </tr>
                          </table><br>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Ubicaciones</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Ubicación</td>
                              <td class="ETablaT">Unidad Médica</td>
                              <td class="ETablaT">Inicio</td>
                              <td class="ETablaT">Término</td>
                              <td class="ETablaT">Actual</td>
                            </tr>
                            <% String cActivo = "";
                               String cFecha = "";
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                       cActivo = bs.getFieldValue("lActivo", "0").toString().equals("0")?"No":"Sí";
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("iCveUbicacion", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla","" + bs.getFieldValue("cDscUniMed", "&nbsp;")));
                                       cFecha = bs.getFieldValue("dtAsignacion", "&nbsp;").toString();
                                       cFecha = cFecha.equalsIgnoreCase("&nbsp;")||cFecha.equalsIgnoreCase("null")?"&nbsp;":pFecha.getFechaDDMMMYYYY(pFecha.getSQLDatefromSQLString(cFecha),"/");
                                       out.print(vEti.Texto("ETablaC",""+ cFecha));
                                       cFecha = bs.getFieldValue("dtDesasigna", "&nbsp;").toString();
                                       cFecha = cFecha.equalsIgnoreCase("&nbsp;")||cFecha.equalsIgnoreCase("null")?"&nbsp;":pFecha.getFechaDDMMMYYYY(pFecha.getSQLDatefromSQLString(cFecha),"/");
                                       out.print(vEti.Texto("ETablaC",""+ cFecha));
                                       out.print(vEti.Texto("ETablaC",cActivo));
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
