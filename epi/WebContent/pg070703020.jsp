<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070703020CFG  clsConfig = new pg070703020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070703020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070703020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cAsignacion  = "pg070703021.jsp";       // modificar
  String cRevision    = "pg070703022.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Fecha/Hora de solicitud|Usuario que solicita|Tipo de vehículo|";    // modificar
  String cCveOrdenar  = "VEHSolicitud.tsSolicitud|SEGUsuario.cNombre|VEHTpoVehiculo.cDscBreve|";  // modificar
  String cDscFiltrar  = "Fecha de Solicitud|Usuario que solicita|Tipo de vehículo|";    // modificar
  String cCveFiltrar  = "VEHSolicitud.dtSolicitud|SEGUsuario.cNombre|VEHTpoVehiculo.cDscBreve|";  // modificar
  String cTipoFiltrar = "5|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

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
  String cClave2    = "";
  String cPosicion = "";

  TVSEGUsuario vSEGUsuario = new TVSEGUsuario();
  TDSEGUsuario dSEGUsuario = new TDSEGUsuario();
  Vector vcSEGUsuario = new Vector();

  TVVEHVehiculo vVEHVehiculo = new TVVEHVehiculo();
  TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
  Vector vcVEHVehiculo = new Vector();

  int iCveProceso     = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7

  TVVEHEtapaXSolic vVEHEtapaXSolic = new TVVEHEtapaXSolic();
  TDVEHEtapaXSolic dVEHEtapaXSolic = new TDVEHEtapaXSolic();
  Vector vcVEHEtapaXSolic = new Vector();

  String cConcluido = vParametros.getPropEspecifica("VEHEtapaSolFin");
  String cTemp = "";
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
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="iAnio" value="">
  <input type="hidden" name="iCveSolicitud" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <%

                          out.print("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3'>");
                            out.print("<tr>");
                              out.print("<td colspan='6' class='ETablaT'>Buscar Solicitudes</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                              out.print(vEti.EtiCampo("EEtiqueta", "Fecha de Solicitud de:", "ECampo", "", 10, 10, "dtSolicitudDe", "", 3, "", "", true, true, true, request));
                              out.print(vEti.EtiCampo("EEtiqueta", "A:", "ECampo", "", 10, 10, "dtSolicitudA", "", 3, "", "", true, true, true, request));
                              // LLenado del Select de Unidad Médica
                               String cValActual = "";
                               out.print(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                               TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                               TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                               boolean lFirst = false;
                               BeanScroller beanSc = new BeanScroller(dUMUsuario.FindByAll(" where GRLUMUsuario.iCveProceso = " + iCveProceso + " and GRLUMUsuario.iCveUsuario = " + vUsuario.getICveusuario() + " "));
                               if(request.getParameter("iCveUniMed")!=null &&request.getParameter("iCveUniMed").compareTo("")!=0)
                                 cValActual = "" + request.getParameter("iCveUniMed");
                               else
                                 cValActual = "0";
                               out.print("<td><select name=\"iCveUniMed\">");
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
                               // Termina llenado del select
                            out.print("</tr>");
                          out.print("</table>");

                            out.print("&nbsp;");

                          out.print("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3'>");
                            out.print("<tr>");
                            out.print("<td colspan='12' class='ETablaT'>Solicitudes Vigentes");
                            out.print("</td>");
                            out.print("</tr>");
                               if (bs != null){
                                   boolean lAsignacion = clsConfig.getLPagina(cAsignacion);
                                   boolean lRevision = clsConfig.getLPagina(cRevision);
                                   boolean lEntregado = false;
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Núm.</td>");
                                   out.print("<td class=\"ETablaT\">Fecha</td>");
                                   out.print("<td class=\"ETablaT\">Hora</td>");
                                   out.print("<td class=\"ETablaT\">Usuario Solicita</td>");
                                   out.print("<td class=\"ETablaT\">Unid. Médica</td>");
                                   out.print("<td class=\"ETablaT\">Módulo</td>");
                                   out.print("<td class=\"ETablaT\">Área</td>");
                                   out.print("<td class=\"ETablaT\">Tipo</td>");
                                   out.print("<td class=\"ETablaT\">Usuario Asignó</td>");
                                   out.print("<td class=\"ETablaT\">Placas</td>");
                                   if(lAsignacion || lRevision)
                                     out.print("<td class=\"ETablaT\" colspan=\"3\">Acción</td>");

                                   out.print("</tr>");
                                   bs.start();
                                   int iClaveVehiculo = 0;
                                   while(bs.nextRow()){
                                     iClaveVehiculo = 0;
                                     if (!bs.getFieldValue("dtEntrega","null").toString().equals("null"))
                                       lEntregado = true;
                                     out.print("<tr>");
                                       out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveSolicitud", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ETabla", bs.getFieldValue("dtSolicitud", "&nbsp;").toString()));

                                       String cHora = bs.getFieldValue("tsSolicitud", "").toString();
                                       if(cHora.compareTo("")!=0){
                                         cHora = cHora.substring(13,18);
                                         out.print(vEti.Texto("ETabla", cHora));
                                       }
                                       else
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));

                                       out.print(vEti.Texto("ETabla", bs.getFieldValue("cNombre") + " " + bs.getFieldValue("cApPaterno") + " " + bs.getFieldValue("cApMaterno")));

                                       out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscModulo", "&nbsp;").toString()));
                                       out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscArea", "&nbsp;").toString()));

                                       if (bs.getFieldValue("cDscBreveTpoVeh","")!=null && bs.getFieldValue("cDscBreveTpoVeh","").toString().compareToIgnoreCase("")!=0 &&
                                           bs.getFieldValue("cDscBreveTpoVeh","").toString().compareToIgnoreCase("null")!=0)
                                         cTemp = bs.getFieldValue("cDscBreveTpoVeh","").toString();
                                       else
                                         cTemp = "INDISTINTO";
                                       out.print(vEti.Texto("ETabla", cTemp));
                                       vcSEGUsuario = dSEGUsuario.FindByAll(" where iCveUsuario = " + bs.getFieldValue("iCveUsuAsigna"));
                                       if(vcSEGUsuario.size()!=0){
                                         vSEGUsuario = (TVSEGUsuario)vcSEGUsuario.get(0);
                                         out.print(vEti.Texto("ETabla", vSEGUsuario.getCNombre() + " " + vSEGUsuario.getCApPaterno() + " " + vSEGUsuario.getCApMaterno()));
                                       }
                                       else
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));

                                       vcVEHVehiculo = dVEHVehiculo.FindByAll(" where VEHVehiculo.iCveVehiculo = " + bs.getFieldValue("iCveVehiculo", "-1"), "");
                                       if(vcVEHVehiculo.size()>0){
                                         vVEHVehiculo = (TVVEHVehiculo)vcVEHVehiculo.get(0);
                                         out.print(vEti.Texto("ETabla", vVEHVehiculo.getCPlacas()));
                                         iClaveVehiculo = vVEHVehiculo.getICveVehiculo();
                                       }
                                       else
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));

                                       vcVEHEtapaXSolic = dVEHEtapaXSolic.FindByAll(" where iAnio = " + bs.getFieldValue("iAnio") + " and iCveUniMed = " + bs.getFieldValue("iCveUniMed") + " and iCveSolicitud = " + bs.getFieldValue("iCveSolicitud"), "");
                                       boolean lEncontrado = false;
                                       if(vcVEHEtapaXSolic.size()!=0)
                                       lEncontrado = true;

                                       if(!lEncontrado || vcVEHVehiculo.size()==0){
                                         out.print("<td class=\"ETablaC\">");
                                         if(lAsignacion)
                                           out.print(vEti.clsAnclaTexto("EAncla","Asignar","JavaScript:fIrAsignacion('" + bs.getFieldValue("iAnio","") + "','" + bs.getFieldValue("iCveUniMed","") + "','" + bs.getFieldValue("iCveSolicitud","") + "');","Ir a Asignaciones..."));
                                         else
                                           if (lEntregado)
                                             out.print("Entregado");
                                           else
                                             out.print("&nbsp;");
                                         out.print("</td>");
                                       }
                                       else
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));
                                       if (iClaveVehiculo != 0){
                                         out.print("<td class=\"ETablaC\">");
                                         if(lRevision)
                                           out.print(vEti.clsAnclaTexto("EAncla","Revisar","JavaScript:fIrRevision('" + bs.getFieldValue("iAnio","") + "','" + bs.getFieldValue("iCveUniMed","") + "','" + bs.getFieldValue("iCveSolicitud","") + "');","Ir a Revisiones..."));
                                         else
                                           out.print("&nbsp;");
                                         out.print("</td>");
                                       }
                                       else
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));
                                       // Agregar liga para la modificación del Kilometraje
                                       if (iClaveVehiculo != 0){
                                         out.print("<td class=\"ETablaC\">");
                                         if(clsConfig.getLPagina("pg070703023.jsp"))
                                           out.print(vEti.clsAnclaTexto("EAncla","Modificar","JavaScript:fIrModifica('" + bs.getFieldValue("iAnio","") + "','" + bs.getFieldValue("iCveUniMed","") + "','" + bs.getFieldValue("iCveSolicitud","") + "');","Ir a Modificaciones..."));
                                         else
                                           out.print("&nbsp;");
                                         out.print("</td>");
                                       }
                                       else
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));

                                     out.print("</tr>");
                                   }
                               }
                               else{
                                 out.println("<tr>");
                                 out.print("<td class='ETablaC' colspan='11'>No existen datos coincidentes con el filtro proporcionado.</td>");
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
