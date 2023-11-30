<%/**
 * Title:       Sistema Integral de Protección y Medicina Preventiva en el Transporte.
 * Description: Módulo de Calibración de Equipo.
 * Copyright:   Copyright (c) 2004
 * Company:     Micros Personales S.A. de C.V.
 * @author      LSC. Rafael Miranda Blumenkron
 * @version 1.0
 * Clase:       JSP para asignación de equipos a las áreas de un módulo en una unidad médica.
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>

<html>
<%
  pg070602020CFG  clsConfig = new pg070602020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070602020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070602020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070602021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Equipo|Núm. Serie|";    // modificar
  String cCveOrdenar  = "E.iCveEquipo|E.cNumSerie|";  // modificar
  String cDscFiltrar  = "Equipo|Núm. Serie|";    // modificar
  String cCveFiltrar  = "E.iCveEquipo|E.cNumSerie|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar Imprimir / Reporte
  String cFiltro = "";

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();
  TVGRLModulo vGRLModulo = new TVGRLModulo();
  TVGRLAreaModulo  vcAreaModulo = new TVGRLAreaModulo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  Vector vDespliega = clsConfig.getVDespliega();
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  String cUsuario = "";
  if (vUsuario != null)
    cUsuario = vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
    fCargaListasOnLoad();
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
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveAsignacion" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
                      <table border="0" align="center" cellspacing="0" cellpadding="3">
                        <tr><td>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                           <tr><td colspan="1" class="ETablaT">Mostrar equipos:</td></tr>
                            <%
                              String cValor = "0";
                              String cTituloTabla = "Asignar equipo a:";
                              if (request.getParameter("RSTMostrar") != null)
                                cValor = request.getParameter("RSTMostrar");
                              if (cValor.equals("1"))
                                cTituloTabla = "Mostrar equipo asignado a:";
                              out.println("<tr><td class=\"EEtiquetaL\">");
                              out.println(vEti.ObjRadioSE("ECampo", "RSTMostrar", "0", "Disponibles", cValor, "", "", "", 0, true, true));
                              out.println("</td></tr>");
                              out.println("<tr><td class=\"EEtiquetaL\">");
                              out.println(vEti.ObjRadioSE("ECampo", "RSTMostrar", "1", "Asignados", cValor, "", "", "", 0, true, true));
                              out.println("</td></tr>");
                          %></table>
                          </td><td>&nbsp;</td><td>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                           <tr><td colspan="4" class="ETablaT"><%= cTituloTabla %></td></tr>
                            <%
                              out.println("<tr>");
                              out.println("<td class=\"EEtiqueta\">Unidad:</td>");
                              out.println("<td>");
                              int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
                              String cUniMed = "0";
                              if (request.getParameter("iCveUniMed") != null &&
                                  !request.getParameter("iCveUniMed").equals("0"))
                                cUniMed = request.getParameter("iCveUniMed");
                              out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,cUniMed,true));
                              out.println("</td>");
                              out.println("<td class=\"EEtiqueta\">Módulo:</td>");
                              out.println("<td>");
                              Vector vModulo = new Vector();
                              int iUniMed = new Integer(cUniMed).intValue();
                              vModulo = (Vector) AppCacheManager.getColl("GRLModulo",iUniMed + "|");
                              String cModulo = "0";
                              if (request.getParameter("iCveModulo") != null &&
                                  !request.getParameter("iCveModulo").equals("0"))
                                cModulo = request.getParameter("iCveModulo");
                              if (vModulo == null)
                                vModulo = new Vector();
                              out.print(vEti.SelectOneRowSinTD("iCveModulo","llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);",vModulo,"iCveModulo","cDscModulo",request,cModulo,true));
                              out.println("</td>");
                              out.println("</tr>");

                              out.println("<tr>");
                              out.println("<td class=\"EEtiqueta\">Área:</td>");
                              out.println("<td colspan=\"3\">");
                              DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
                              Vector vAreaModulo = new Vector();
                              if (!cUniMed.equals("0") && !cModulo.equals("0")){
                                cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + cUniMed +
                                          "  AND GRLAreaModulo.iCveModulo = " + cModulo + " ";
                                vAreaModulo = dGRLAreaModulo.FindByAll(cFiltro);
                              }
                              String cArea = "0";
                              if (request.getParameter("iCveArea") != null &&
                                  !request.getParameter("iCveArea").equals("0"))
                                cArea = request.getParameter("iCveArea");
                              if (vModulo == null || vAreaModulo == null)
                                vAreaModulo = new Vector();
                              out.print(vEti.SelectOneRowSinTD("iCveArea","",vAreaModulo,"iCveArea","cDscArea",request,cArea,true));
                              out.println("</td>");
                              out.println("</tr>");
                          %></table>
                          </td></tr>
                         </table><br>
                         <input type="hidden" name="iUnidadSelected" value="<%=cUniMed%>">
                         <input type="hidden" name="iModuloSelected" value="<%=cModulo%>">
                         <input type="hidden" name="iAreaSelected" value="<%=cArea%>">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="EEtiqueta">Clasificación:</td><%
                              out.println("<td colspan='4'>");
                              out.println(vEti.SelectOneRowSinTD("iCveClasificacion", "llenaSLT(3,this.value,'','',document.forms[0].iCveTpoEquipo);", clsConfig.vClasif, "iCveClasificacion", "cDscClasificacion", request, "0"));
                              out.println("</td>");
                              %><td class="EEtiqueta">Tipo Equipo:</td><%
                              TDEQMTpoEquipo dTipo = new TDEQMTpoEquipo();
                              Vector vTipo = new Vector();
                              TVEQMTpoEquipo tvTipo = new TVEQMTpoEquipo();
                              if(cValor.equals("1"))
                                out.println("<td colspan='4' class='ECampo'>");
                              else
                                out.println("<td colspan='4' class='ECampo'>");
                              out.println("<SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                              if (request.getParameter("iCveClasificacion") != null && !request.getParameter("iCveClasificacion").equals("")) {
                                  String clasif = request.getParameter("iCveClasificacion");
                                  String tpoEq = "0";
                                  if (request.getParameter("iCveTpoEquipo") != null)
                                    tpoEq = request.getParameter("iCveTpoEquipo").toString();
                                  vTipo = dTipo.FindByAll(" where lActivo=1 and iCveClasificacion=" + clasif, " order by iCveTpoEquipo ");
                                  out.println("<option value='0'>Todos...</option>");
                                  for (int i = 0; i < vTipo.size(); i++) {
                                      tvTipo = (TVEQMTpoEquipo) vTipo.get(i);
                                      if (tpoEq.equals("" + tvTipo.getICveTpoEquipo()))
                                         out.println("<option value='" + tvTipo.getICveTpoEquipo() + "' selected>" + tvTipo.getCDscBreve() + "</option>");
                                      else
                                         out.println("<option value='" + tvTipo.getICveTpoEquipo() + "'>" + tvTipo.getCDscBreve() + "</option>");
                                  }
                              } else {
                                  out.println("<option value='0'>Todos...</option>");
                              }
                              out.println("</SELECT></td>");
                              %>
                            <tr>
                            <%
                            if(cValor.equals("1")){
                              out.println("<tr><td class=\"ETablaTR\">Asignados a:</td>");
                              out.println("<td class=\"EEtiqueta\">Unidad:</td>");
                              Vector vUnidades = vUsuario.getVUniFiltro(iCveProceso);
                              TVGRLUMUsuario vUniMed;
                              String cDscUniMed = "Sin seleccionar...";
                              if (vUnidades == null)
                                vUnidades = new Vector();
                              for (int x=0; x < vUnidades.size(); x++){
                                vUniMed = (TVGRLUMUsuario)vUnidades.get(x);
                                if (vUniMed.getICveUniMed() == Integer.parseInt(cUniMed))
                                  cDscUniMed = vUniMed.getCDscUniMed();
                              }
                              out.println("<td class=\"ECampo\">" + cDscUniMed + "</td>");
                              out.println("<td class=\"EEtiqueta\">Módulo:</td>");
                              String cDscModulo = "Sin seleccionar...";
                              for (int x=0; x < vModulo.size(); x++){
                                vGRLModulo = (TVGRLModulo)vModulo.get(x);
                                if (vGRLModulo.getICveModulo() == Integer.parseInt(cModulo))
                                  cDscModulo = vGRLModulo.getCDscModulo();
                              }
                              out.println("<td class=\"ECampo\">" + cDscModulo + "</td>");
                              out.println("<td class=\"EEtiqueta\">Area:</td>");
                              String cDscArea = "Sin seleccionar...";
                              for (int x=0; x < vAreaModulo.size(); x++){
                                vcAreaModulo = (TVGRLAreaModulo)vAreaModulo.get(x);
                                if (vcAreaModulo.getICveArea() == Integer.parseInt(cArea))
                                  cDscArea = vcAreaModulo.getCDscArea();
                              }
                              out.println("<td class=\"ECampo\" colspan=\"4\">" + cDscArea + "</td>");
                            }
                            %>
                            <tr>
                              <td class="ETablaT">Clasificación</td>
                              <td class="ETablaT">Tipo Equipo</td>
                              <td class="ETablaT">Núm.</td>
                              <td class="ETablaT">Equipo</td>
                              <td class="ETablaT">Marca</td>
                              <td class="ETablaT">Modelo</td>
                              <td class="ETablaT">No. Serie</td>
                              <td class="ETablaT">Usuario Responsable</td>
                              <%= cValor.equals("1")?"<td class=\"ETablaT\">Servicios</td>":"" %>
                              <td class="ETablaT"><%= cValor.equals("1")?"Asignado":"Por Asignar" %></td>
                            </tr><%
                           boolean lDesplegar = false;
                           boolean lTodos = true;
                           if (cValor.equals("1")){
                             if (request.getParameter("iCveUniMed") != null &&
                                 (request.getParameter("iCveUniMed").equals("-1") ||
                                  request.getParameter("iCveUniMed").equals("0")))
                               if (lTodos)
                                 out.println("<tr>" + vEti.Texto("EEtiquetaC\" colspan=\"10", "Favor de seleccionar una unidad, un módulo y un área...") + "</tr>");
                               else
                                 out.println("<tr>" + vEti.Texto("EEtiquetaC\" colspan=\"10", "Favor de seleccionar una unidad...") + "</tr>");
                             else
                               if (lTodos){
                                 if (request.getParameter("iCveModulo") != null &&
                                     (request.getParameter("iCveModulo").equals("-1") ||
                                      request.getParameter("iCveModulo").equals("0")))
                                   out.println("<tr>" + vEti.Texto("EEtiquetaC\" colspan=\"10", "Favor de seleccionar un módulo y un área...") + "</tr>");
                                 else
                                   if (request.getParameter("iCveArea") != null &&
                                       (request.getParameter("iCveArea").equals("-1") ||
                                        request.getParameter("iCveArea").equals("0")))
                                     out.println("<tr>" + vEti.Texto("EEtiquetaC\" colspan=\"10", "Favor de seleccionar un área...") + "</tr>");
                                   else
                                     lDesplegar = true;
                               }
                               else
                                 lDesplegar = true;
                           }
                           else
                             lDesplegar = true;
                           if (lDesplegar){
                             boolean lAsignado = false;
                             String cDesp;
                             if (vDespliega.size() != 0){
                               TVEquipoAsignacion VEqAsignacion = null;
                               for (int i = 0; i < vDespliega.size(); i++){
                                 VEqAsignacion = (TVEquipoAsignacion)vDespliega.get(i);
                                 out.println("<tr>");
                                 cDesp = VEqAsignacion.VEquipo.getCDscBreveClasificacion();
                                 cDesp = cDesp!=null?cDesp:"";
                                 out.print(vEti.Texto("ETabla", cDesp.length()!=0?cDesp:"&nbsp;"));
                                 cDesp = VEqAsignacion.VEquipo.getCDscBreveTpoEquipo();
                                 cDesp = cDesp!=null?cDesp:"";
                                 out.print(vEti.Texto("ETabla", cDesp.length()!=0?cDesp:"&nbsp;"));
                                 cDesp = VEqAsignacion.VEquipo.getICveEquipo()+"";
                                 out.print(vEti.Texto("ETablaR", cDesp.length()!=0?cDesp:"&nbsp;"));
                                 cDesp = VEqAsignacion.VEquipo.getCDscEquipo();
                                 out.print(vEti.Texto("ETabla", cDesp.length()!=0?cDesp:"&nbsp;"));
                                 cDesp = VEqAsignacion.VEquipo.getCDscBreveMarca();
                                 out.print(vEti.Texto("ETabla", cDesp.length()!=0?cDesp:"&nbsp;"));
                                 cDesp = VEqAsignacion.VEquipo.getCModelo();
                                 out.print(vEti.Texto("ETabla", cDesp.length()!=0?cDesp:"&nbsp;"));
                                 cDesp = VEqAsignacion.VEquipo.getCNumSerie();
                                 out.print(vEti.Texto("ETabla", cDesp.length()!=0?cDesp:"&nbsp;"));
                                 lAsignado = VEqAsignacion.VAsignacion.getLActual() == 0?false:true;
                                 out.println("<td class=\"ETabla\">");
                                 if (!lAsignado){
                                   out.println("<SELECT NAME='iCveUsuAplica" + VEqAsignacion.VEquipo.getICveEquipo() + "' SIZE='1' onChange='fValidaElegido(this, document.forms[0].TBXAsigna" + VEqAsignacion.VEquipo.getICveEquipo() + ")'>");
                                   out.println("<option selected value=\"-1\">Seleccione...</option>");
                                   out.println("</SELECT>");
                                 }
                                 else{
                                   String cNombre    = "",
                                          cApPaterno = "",
                                          cApMaterno = "";
                                   cNombre = VEqAsignacion.getCNombre() != null?VEqAsignacion.getCNombre():"";
                                   cApPaterno = VEqAsignacion.getCApPaterno() != null?VEqAsignacion.getCApPaterno():"";
                                   cApMaterno = VEqAsignacion.getCApMaterno() != null?VEqAsignacion.getCApMaterno():"";
                                   out.println(cNombre + " " + cApPaterno + " " + cApMaterno);
                                 }
                                 out.println("</td>");
                                 if (lAsignado && clsConfig.getLPagina(cCatalogo))
                                   out.print("<td class=\"ETabla\">" + vEti.clsAnclaTexto("EAncla","Servicios","JavaScript:fIrCatalogo('" + VEqAsignacion.VEquipo.getICveEquipo() + "','" + VEqAsignacion.VAsignacion.getICveAsignacion() + "');","Servicios...") + "</td>");
                                 out.print(vEti.ToggleMov("ETablaC", "TBXAsigna" + VEqAsignacion.VEquipo.getICveEquipo(), "" + VEqAsignacion.VAsignacion.getLActual(), "fValidaReg(this, document.forms[0].iCveUsuAplica" + VEqAsignacion.VEquipo.getICveEquipo() + ");", 0, true, "Si", "No", true));
                                 out.println("</tr>");
                               }
                             }
                             else{
                               out.println("<tr>");
                               out.print(vEti.Texto("EEtiquetaC\" colspan=\"10", "No existen datos coincidentes con el filtro proporcionado"));
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
