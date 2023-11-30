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
  pg070702040CFG  clsConfig = new pg070702040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702041.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Fecha|Placas|";    // modificar
  String cCveOrdenar  = "VehMantenimiento.dtProgramado|VehVehiculo.cPlacas|";  // modificar
  String cDscFiltrar  = "Fecha|Placas|";    // modificar
  String cCveFiltrar  = "VehMantenimiento.dtProgramado|VehVehiculo.cPlacas|";  // modificar
  String cTipoFiltrar = "5|8|";                // modificar
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
  String cTemp = "1";
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
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdCampoClave") %>">
  <input type="hidden" name="hdLectura" value="1">
  <input type="hidden" name="iCveMantenimiento" value="">
  <input type="hidden" name="iCveVehiculo" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                 <tr>
                                    <td class="ETablaT" colspan="4">Mostrar Solicitudes</td>
                                 </tr>
                                 <tr>
<%                                  out.println(vEti.EtiCampo("EETiqueta", "Solicitados de:", "CCampo", "text", 11, 10, "dtInicio", "", 0, "", "", false, true, true, request));
                                    out.println(vEti.EtiCampo("EETiqueta", "&nbsp;&nbsp; a:", "CCampo", "text", 11, 10, "dtFin", "", 0, "", "", false, true, true, request));
                                    out.println("</tr><tr>");
                                    out.println(vEti.TextoCS("EEtiqueta", "Asignados a Unidad Médica:", 2));
                                    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh"));
                                    if (vUsuario!=null)
                                       out.println("<td colspan='2'>" + vEti.SelectOneRowSinTD("iCveUniMed", "", vUsuario.getVUniFiltro(iCveProceso), "iCveUniMed", "cDscUniMed", request, "0") + "</td>");
%>                               </tr>
                <tr><%
                   String cRadio = "0";
                   if(request.getParameter("RSConcluido") != null && request.getParameter("RSConcluido").toString().length() > 0)
                      cRadio = request.getParameter("RSConcluido").toString();
                   out.println(vEti.ObjRadioCE("EEtiqueta","Situación:","ETabla", "RSConcluido","0","Pendientes",cRadio,"","","",0,true,true));
                   out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSConcluido","1","Concluidos",cRadio,"","","",0,true,true) + "</td>");
                   out.println("<td class=\"ETabla\">" + vEti.ObjRadioSE("ETabla", "RSConcluido","2","Ambos",cRadio,"","","",0,true,true) + "</td>");
                %></tr>

                              </table>
                          <br>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="11" class="ETablaT">Solicitudes Encontradas
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Vehículo</td>
                              <td class="ETablaT">Mantto.</td>
                              <td class="ETablaT">Fecha Solicitud</td>
                              <td class="ETablaT">Tipo de Mantenimiento</td>
                              <td class="ETablaT">Tipo de Vehículo</td>
                              <td class="ETablaT">Unidad Médica</td>
                              <td class="ETablaT">Placas</td>
                              <td class="ETablaT">Fecha Programada</td>
                              <td class="ETablaT">Iniciar</td>
                              <td class="ETablaT" colspan="2">Registrar</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   int i = 0;
                                   while(bs.nextRow()){
                                     String checked = "";
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaC", bs.getFieldValue("iCveVehiculo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETablaC", bs.getFieldValue("iCveMantenimiento", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETablaC", bs.getFieldValue("dtSolicitud", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscTpoMantto", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscTpoVehiculo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cObservaciones", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cPlacas", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETablaC", bs.getFieldValue("dtProgramado", "&nbsp;").toString()));
// Iniciar Mantenimiento
                                     if (bs.getFieldValue("dtInicia") == null) {
                                         out.println("<td align=\"center\"><input type=\"checkbox\" name=\"iCveIniciar" + i + "\" value=\"" + bs.getFieldValue("iCveMantenimiento") +"\">");
                                         out.println("<input type=\"hidden\" name=\"iCveExiste" + i + "\" value=\"" + bs.getFieldValue("iCveMantenimiento") +"\">");
                                         out.println("<input type=\"hidden\" name=\"iCveVehiculo" + i + "\" value=\"" + bs.getFieldValue("iCveVehiculo") +"\"></td>");
                                         if (clsConfig.getLPagina("pg070702041.jsp")){
                                            if (bs.getFieldValue("lCancelado", "0").toString().compareTo("1")==0)
                                               cTemp = "0";
                                            else
                                               if (bs.getFieldValue("lConcluido", "0").toString().compareTo("1")==0)
                                                  cTemp = "0";
                                            out.println("<td class='ECampo'><a href=\"#\" onClick=\"fDetalles(1," + bs.getFieldValue("iCveMantenimiento") + "," + bs.getFieldValue("iCveVehiculo") + ", "+cTemp+");\">Detalle</a></td>");
                                         }
                                         i++;
                                     } else {
                                        String cEtiqueta = "Iniciado";
                                         if("1".compareToIgnoreCase(bs.getFieldValue("lCancelado", "0").toString()) == 0)
                                           cEtiqueta = "Cancelado";
                                         if("1".compareToIgnoreCase(bs.getFieldValue("lConcluido", "0").toString()) == 0)
                                           cEtiqueta = "Concluido";
                                         out.println(vEti.Texto("ECampo", cEtiqueta));
                                         out.println("<td class='ECampo'><a href=\"#\" onClick=\"fDetalles(1," + bs.getFieldValue("iCveMantenimiento") + "," + bs.getFieldValue("iCveVehiculo") + ", 0);\">Detalle</a></td>");
                                         out.println("<td class='ECampo'><a href=\"#\" onClick=\"fDetalles(2," + bs.getFieldValue("iCveMantenimiento") + "," + bs.getFieldValue("iCveVehiculo") + ", 0);\">Seguimiento</a></td>");
                                     }
                                     out.print("</tr>");
                                   }
                                   out.println("<input type='hidden' name='num_man' value='" + i + "'>");
                                   if (vUsuario!=null)
                                      out.println("<input type='hidden' name='iCveUsr' value='" + vUsuario.getICveusuario() + "'>");
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
