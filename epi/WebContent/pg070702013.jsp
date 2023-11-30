<%/**
 * Title:       Sistema Integral de Protección y Medicina Preventiva en el Transporte.
 * Description: Módulo de Control de Vehículos.
 * Copyright:   Copyright (c) 2004
 * Company:     Micros Personales S.A. de C.V.
 * @author      LSC. Rafael Miranda Blumenkron
 * @version 1.0
 * Clase:       JSP para despliegue de mantenimientos de un vehículo.
 */%>
<%@ page import="com.micper.ingsw.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="java.util.*" %>

<html>
<%
  pg070702013CFG  clsConfig = new pg070702013CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702013.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702013.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Mantenimiento|";    // modificar
  String cCveOrdenar  = "M.iCveMantenimiento|";  // modificar
  String cDscFiltrar  = "Mantenimiento|";    // modificar
  String cCveFiltrar  = "M.iCveMantenimiento|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  <input type="hidden" name="hdLectura" value="1">
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
  <input type="hidden" name="iCveMantenimiento" value="<%=request.getParameter("iCveMantenimiento")!=null?request.getParameter("iCveMantenimiento"):""%>">
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
                              <td colspan="10" class="ETablaT">Mantenimientos</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">No.</td>
                              <td class="ETablaT">Solicitado</td>
                              <td class="ETablaT">Kilometraje</td>
                              <td class="ETablaT">Tipo Mantenimiento</td>
                              <td class="ETablaT">Programado</td>
                              <td class="ETablaT">Inicia</td>
                              <td class="ETablaT">Empresa</td>
                              <td class="ETablaT" colspan="3">Recepción</td>
                            </tr>
                            <% String cActivo = "";
                               if (bs != null){
                                   bs.start();
                                   String cFecha = "";
                                   String cDetalle = "pg070702041.jsp",
                                          cSeguimi = "pg070702042.jsp";
                                   boolean lDetalle = clsConfig.getLPagina(cDetalle);
                                   boolean lSeguimi = clsConfig.getLPagina(cSeguimi);
                                   while(bs.nextRow()){
                                       cActivo = bs.getFieldValue("lActivo", "0").toString().equals("0")?"No":"Sí";
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("iCveMantenimiento", "&nbsp;")));
                                       cFecha = bs.getFieldValue("dtSolicitud", "&nbsp;").toString();
                                       cFecha = cFecha.equalsIgnoreCase("&nbsp;")||cFecha.equalsIgnoreCase("null")?"&nbsp;":cFecha;
                                       out.print(vEti.Texto("ETablaC",""+ cFecha));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iKilometraje", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla","" + bs.getFieldValue("cDscTpoMantto", "&nbsp;")));
                                       cFecha = bs.getFieldValue("dtProgramado", "&nbsp;").toString();
                                       cFecha = cFecha.equalsIgnoreCase("&nbsp;")||cFecha.equalsIgnoreCase("null")?"&nbsp;":cFecha;
                                       out.print(vEti.Texto("ETablaC",""+ cFecha));
                                       cFecha = bs.getFieldValue("dtInicia", "&nbsp;").toString();
                                       cFecha = cFecha.equalsIgnoreCase("&nbsp;")||cFecha.equalsIgnoreCase("null")?"&nbsp;":cFecha;
                                       out.print(vEti.Texto("ETablaC",""+ cFecha));
                                       out.print(vEti.Texto("ETabla","" + bs.getFieldValue("cDscEmpMantto", "&nbsp;")));
                                       cFecha = bs.getFieldValue("dtRecepcion", "&nbsp;").toString();
                                       cFecha = cFecha.equalsIgnoreCase("&nbsp;")||cFecha.equalsIgnoreCase("null")?"&nbsp;":cFecha;
                                       out.print(vEti.Texto("ETablaC",""+  cFecha));
                                       if (lDetalle){
                                         out.print("<td class=\"ETablaC\">");
                                         out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','"+bs.getFieldValue("iCveMantenimiento","")+"','" + cDetalle + "');","Ir a detalle..."));
                                         out.print("</td>");
                                       }
                                       if (lSeguimi){
                                         out.print("<td class=\"ETablaC\">");
                                         out.print(vEti.clsAnclaTexto("EAncla","Seguimiento","JavaScript:fIrCatalogo('"+bs.getFieldValue("iCveVehiculo","")+"','"+bs.getFieldValue("iCveMantenimiento","")+"','" + cSeguimi + "');","Ir a seguimiento..."));
                                         out.print("</td>");
                                       }
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
