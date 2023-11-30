<%/**
 * Title: Ubicación de Vehículos
 * Description: Ubicación de Vehículos
 * Copyright: Micros
 * Company: Micros
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070702020CFG
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>

<html>
<%
  pg070702020CFG  clsConfig = new pg070702020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Núm. Serie|Placas|Tipo de Vehiculo|Marca|Modelo|";    // modificar
  String cCveOrdenar  = "VehVehiculo.cNumSerie|VehVehiculo.cPlacas|VehTpoVehiculo.cDscTpoVehiculo|VehMarca.cDscMarca|VehModelo.cDscModelo|";  // modificar
  String cDscFiltrar  = "Núm. Serie|Placas|Tipo de Vehiculo|Marca|Modelo|";    // modificar
  String cCveFiltrar  = "VehVehiculo.cNumSerie|VehVehiculo.cPlacas|VehTpoVehiculo.cDscTpoVehiculo|VehMarca.cDscMarca|VehModelo.cDscModelo|";  // modificar
  String cTipoFiltrar = "8|8|8|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
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
  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value == "Guardar"){
      if (!confirm(" ¿ Desea Guardar los Cambios ? "))
        return false;
    }
    return true;
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
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdCampoClave") %>">
  <input type="hidden" name="iCveAsignacion" value="<%=request.getParameter("iCveAsignacion") %>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                         <table border="0" align="center">
                            <tr><td>
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                    <td class="ETablaT" colspan="2">Mostrar Vehículos:</td>
                                 </tr>
                                 <tr>
<%                                  String checked = "";
                                    if (request.getParameter("RSTVehic") == null || request.getParameter("RSTVehic").equals("D"))
                                       checked = " checked";
                                    out.println("<td class=\"ECampo\"><input type=\"radio\" name=\"RSTVehic\" value=\"D\"" + checked + ">Disponibles &nbsp;&nbsp;</td>");
                                    if (request.getParameter("RSTVehic") != null && request.getParameter("RSTVehic").equals("A"))
                                       checked = " checked";
                                    else
                                       checked = "";
                                    out.println("<td class=\"ECampo\"><input type=\"radio\" name=\"RSTVehic\" value=\"A\"" + checked + ">Ubicados &nbsp;&nbsp;</td>");
%>                                </tr>
                              </table>
                            </td>
                            <td>&nbsp;&nbsp;</td>
                            <td>
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
<%                                  String cTitulo = "Ubicar en:";
                                    if (request.getParameter("RSTVehic") != null && request.getParameter("RSTVehic").equals("A"))
                                        cTitulo = "Ubicados en:";
%>
                                    <td class="ETablaT" colspan="2"><%=cTitulo %></td>
                                 </tr>
                                 <tr>
                                    <td class="EEtiqueta">Unidad Médica:</td>
<%                                  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh"));
                                    if (vUsuario != null)
                                      out.println(vEti.SelectOneRow("ECampo", "iCveUniMed", "", vUsuario.getVUniFiltro(iCveProceso), "iCveUniMed", "cDscUniMed", request, "0")); %>
                                 </tr>
                              </table>
                            </td></tr>
                            <tr><td>&nbsp;
                            </td></tr>
                          </table>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="6" class="ETablaT">Datos de los Vehículos
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Tipo de Vehículo</td>
                              <td class="ETablaT">Marca</td>
                              <td class="ETablaT">Modelo</td>
                              <td class="ETablaT">Núm. Serie</td>
                              <td class="ETablaT">Placas</td>
                              <td class="ETablaT">Ubicado</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   int i = 0;
                                   while(bs.nextRow()){
                                     checked = "";
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscTpoVehiculo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscMarca", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscModelo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cNumSerie", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cPlacas", "&nbsp;").toString()));
// Asignados o Disponibles
                                     if (request.getParameter("RSTVehic") != null && request.getParameter("RSTVehic").equals("D")) {
                                         checked = "";
                                     } else if (request.getParameter("RSTVehic") != null && request.getParameter("RSTVehic").equals("A")) {
                                         checked = " checked";
                                     }
                                     out.println("<td align=\"center\"><input type=\"checkbox\" name=\"iCveAsignar" + i + "\" value=\"" + bs.getFieldValue("iCveVehiculo") +"\"" + checked + ">");
                                     out.println("<input type=\"hidden\" name=\"iCveExiste" + i + "\" value=\"" + bs.getFieldValue("iCveVehiculo") +"\">");
                                     out.println("<input type=\"hidden\" name=\"iCveUbicacion" + i + "\" value=\"" + bs.getFieldValue("iCveUbicacion") +"\"></td>");
                                     out.print("</tr>");
                                     i++;
                                   }
                                   out.println("<input type='hidden' name='num_veh' value='" + i + "'>");
                                   out.println("<input type='hidden' name='accion' value='" + (request.getParameter("RSTVehic") == null ? "D" : request.getParameter("RSTVehic")) + "'>");
                               } else  {
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
                                   out.println("</tr>");
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