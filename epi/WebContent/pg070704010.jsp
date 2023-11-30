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
  pg070704010CFG  clsConfig = new pg070704010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070704010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070704010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Fecha/Hora de Solicitud|";    // modificar
  String cCveOrdenar  = "VEHSolicitud.tsSolicitud|";  // modificar
  String cDscFiltrar  = "Fecha de Solicitud|";    // modificar
  String cCveFiltrar  = "VEHSolicitud.dtSolicitud|";  // modificar
  String cTipoFiltrar = "5|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "IR";            // modificar
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
  String cPosicion = "";

  TVVEHVehiculo vVEHVehiculo = new TVVEHVehiculo();
  TDVEHVehiculo dVEHVehiculo = new TDVEHVehiculo();
  Vector vcVEHVehiculo = new Vector();

  int iKmDif = 0;
  int iKmSum = 0;
  int iAsignados = 0;
  int iEntregados = 0;
  int iCancelados = 0;

  int iAnioIni = new Integer(vParametros.getPropEspecifica("iAniosIni")).intValue();
  int iAnioFin = dtFecha.getIntYear(dtFecha.TodaySQL())+1;
  String cMeses = vParametros.getPropEspecifica("NombresMes");
  StringTokenizer st = new StringTokenizer(cMeses,",");
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

// S E L E C T   U S U A R I O
                             Vector vcPersonal;
                             int iUniMed = 0;
                             if(request.getParameter("iCveUniMed")!=null)
                               iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"));
                             vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso, iUniMed);
                             TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
                             out.println("<td class=\"ETablaTR\">Usuario:</td>");
                             out.println("<td>");
                             out.println("<SELECT NAME=\"iCveUsuSolic\" SIZE=\"1\">");
                             if (vcPersonal.size()>0){
                                out.println("<option value=\"0\">Seleccione...</option>");
                                for(int i =0;i<vcPersonal.size();i++){
                                   vGRLUMUsuario = (TVGRLUMUsuario)vcPersonal.get(i);
                                   if(request.getParameter("iCveUsuSolic")!=null && Integer.parseInt(request.getParameter("iCveUsuSolic"))==vGRLUMUsuario.getICveUsuario())
                                     out.println("<option value=\"" + vGRLUMUsuario.getICveUsuario() + "\" selected>" +vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno()+"</option>");
                                   else
                                     out.println("<option value=\"" + vGRLUMUsuario.getICveUsuario() + "\">" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() +"</option>");
                                }
                             }else{
                                out.println("<option value=\"0\">Datos no disponibles</option>");
                             }
                             out.println("</SELECT>");
                             out.println("</td>");

// S E L E C T   A Ñ O
                               out.println("<td class=\"ETablaTR\">Año:</td>");
                               out.println("<td class=\"ETabla\">");
                               out.println("<select name=\"iAnio\" size=\"1\">");
                               out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               for (int i = iAnio; i >= (iAnio - iAniosAtras); i--)
                                 if(request.getParameter("iAnio")!=null && !request.getParameter("iAnio").equals("") && Integer.parseInt(request.getParameter("iAnio"))==i)
                                   out.print("<option value = " + i + " selected>" + i + "</option>");
                                 else
                                   out.print("<option value = " + i + ">" + i + "</option>");
                               out.print("</select>");
                               out.print("</td>");

// S E L E C T  M E S
                               out.print("<td class='ETablaT'>");
                               out.println("Mes:</td><td><select name=\"iMes\" size=\"1\">");
                               out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               while (st.hasMoreTokens()) {
                                 ++j;
                                 if (request.getParameter("iMes")!=null && request.getParameter("iMes").toString().compareTo(""+j)==0)
                                   out.print("<option value = " + j + " selected>" + st.nextToken() + "</option>");
                                 else
                                   out.print("<option value = " + j + ">" + st.nextToken() + "</option>");
                               }
                               out.println("</select></td>");

                               out.println("</tr><tr>");
// SELECT DE PLACAS
                               out.println("<td class=\"ETablaTR\">Placas:</td>");
                               out.println("<td class=\"ETabla\" colspan=\"5\">");
                               out.println("<select name=\"iCveVehiculo\" size=\"1\">");
                               if(request.getParameter("iCveVehiculo")==null || request.getParameter("iCveVehiculo").equals("") || Integer.parseInt(request.getParameter("iCveVehiculo"))==0)
                                 out.print("<option value = \"0\" selected>Seleccione...</option>");
                               else
                                 out.print("<option value = \"0\">Seleccione...</option>");
                               vcVEHVehiculo = dVEHVehiculo.FindByAll("", " ORDER BY VEHVehiculo.cPlacas ");
                               if (vcVEHVehiculo != null && vcVEHVehiculo.size() > 0)
                                 for (int i=0; i < vcVEHVehiculo.size(); i++){
                                   vVEHVehiculo = (TVVEHVehiculo)vcVEHVehiculo.get(i);
                                   if(request.getParameter("iCveVehiculo")!=null && !request.getParameter("iCveVehiculo").equals("") && Integer.parseInt(request.getParameter("iCveVehiculo"))==vVEHVehiculo.getICveVehiculo())
                                     out.print("<option value = " + vVEHVehiculo.getICveVehiculo() + " selected>" + vVEHVehiculo.getCPlacas() + "</option>");
                                   else
                                     out.print("<option value = " + vVEHVehiculo.getICveVehiculo() + ">" + vVEHVehiculo.getCPlacas() + "</option>");
                                 }
                               out.print("</select>");
                               out.print("</td>");
                               out.println("</tr>");

                    out.println("</table>");

                    out.println("&nbsp;");

                    out.println("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3' width=500>");
                    out.println("<tr><td colspan='17' class='ETablaT'>Datos de Búsqueda</td></tr>");


// R E S U L T A D O S   D E   L A   C O N S U L T A

                                  if (bs != null){
                                    bs.start();

                                    out.println("<tr>");
                                      out.println("<td colspan='2' class='EEtiqueta'>Unidad Médica:</td>");
                                      out.println("<td class='ECampo' colspan='5'>" + bs.getFieldValue("cDscUniMed") + "</td>");
                                      out.println("<td class='EEtiqueta'>Año:</td>");
                                      out.println("<td class='ECampo' colspan='3'>" + bs.getFieldValue("iAnio") + "</td>");
                                      out.println("<td class='EEtiqueta'>Mes:</td>");
                                      TFechas pFecha = new TFechas(vEntorno.getNumModuloStr());
                                      int iMesTemp = request.getParameter("iMes")!=null?Integer.parseInt(request.getParameter("iMes"),10):0;
                                      String cMes = (iMesTemp>0)?(pFecha.getVNombresMes().size()==12)?pFecha.getVNombresMes().get(iMesTemp-1).toString():"Nombres de mes no registrados":"Todos";
                                      out.println("<td class='ECampo' colspan='5'>" + cMes + "</td>");
                                    out.println("</tr>");

                                    out.println("<tr><td colspan='17' class='ETablaT'>Solicitudes encontradas</td></tr>");

                                    out.print("<tr>");
                                      out.print("<td class='ETablaT' rowspan='2'>Núm.</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Fecha</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Hora</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Módulo</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Area</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Usuario</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Motivo</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Destino</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Tipo Vehiculo</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Asignado</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Entregado</td>");
                                      out.print("<td class='ETablaT' rowspan='2'>Cancelado</td>");
                                      out.print("<td class='ETablaT' colspan='2'>Vehículo</td>");
                                      out.print("<td class='ETablaT' colspan='3'>Kilometraje</td>");
                                    out.print("</tr>");
                                    out.print("<tr>");
                                      out.print("<td class='ETablaT'>Descripción</td>");
                                      out.print("<td class='ETablaT'>Placas</td>");
                                      out.print("<td class='ETablaT'>Inicial</td>");
                                      out.print("<td class='ETablaT'>Final</td>");
                                      out.print("<td class='ETablaT'>Total</td>");
                                    out.print("</tr>");
                                    String cValorTemp;
                                      while(bs.nextRow()){
                                        out.print("<tr>");
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveSolicitud", "&nbsp;").toString()));

                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("dtSolicitud", "&nbsp;").toString()));

                                        String cHora = bs.getFieldValue("tsSolicitud", "").toString();
                                        if(cHora.compareTo("")!=0){
                                          cHora = cHora.substring(13,18);
                                          out.print(vEti.Texto("ETabla", cHora));
                                        }
                                        else
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscModulo","&nbsp;").toString()));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscArea","&nbsp;").toString()));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscUsuSolic","&nbsp;").toString()));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMotivo", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDestino", "&nbsp;").toString()));
                                        cValorTemp = bs.getFieldValue("cDscBreveTpoVeh", "&nbsp;").toString();
                                        cValorTemp = cValorTemp.equalsIgnoreCase("null")?"&nbsp;":cValorTemp;
                                        out.print(vEti.Texto("ETabla", cValorTemp));

                                        if(bs.getFieldValue("dtAsignado", "").toString().compareTo("")!=0 && Integer.parseInt(bs.getFieldValue("lAsignado", "0").toString())==1){
                                          out.print(vEti.Texto("ETabla", bs.getFieldValue("dtAsignado", "&nbsp;").toString()));
                                          iAsignados++;
                                        }
                                        else
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));

                                        if(bs.getFieldValue("dtEntrega", "").toString().compareTo("")!=0 && Integer.parseInt(bs.getFieldValue("lAsignado", "0").toString())==1){
                                          out.print(vEti.Texto("ETabla", bs.getFieldValue("dtEntrega", "&nbsp;").toString()));
                                          iEntregados++;
                                        }
                                        else
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));

                                        if(bs.getFieldValue("dtCancelacion", "").toString().compareTo("")!=0 && Integer.parseInt(bs.getFieldValue("lAsignado", "0").toString())==0){
                                          out.print(vEti.Texto("ETabla", bs.getFieldValue("dtCancelacion", "&nbsp;").toString()));
                                          iCancelados++;
                                        }
                                        else
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));

                                        if(bs.getFieldValue("dtAsignado", "").toString().compareTo("")!=0 && Integer.parseInt(bs.getFieldValue("lAsignado", "0").toString())==1){
                                          String cMarca  = bs.getFieldValue("cDscMarca","").toString();
                                          cMarca = cMarca.equalsIgnoreCase("null")?"&nbsp;":cMarca;
                                          String cModelo = bs.getFieldValue("cDscModelo","").toString();
                                          cModelo = cModelo.equalsIgnoreCase("null")?"&nbsp;":cModelo;
                                          String cPlacas = bs.getFieldValue("cPlacas","").toString();
                                          cPlacas = cPlacas.equalsIgnoreCase("null")?"&nbsp;":cPlacas;
                                          out.print(vEti.Texto("ETabla", cMarca + " " + cModelo + " " + vVEHVehiculo.getIAnioVeh()));
                                          out.print(vEti.Texto("ETabla", cPlacas));

                                           out.print(vEti.Texto("ETablaR", df.format(Long.valueOf(bs.getFieldValue("iKmInicial", "&nbsp;").toString()).doubleValue())));

                                           if(bs.getFieldValue("dtEntrega", "").toString().compareTo("")!=0 && Integer.parseInt(bs.getFieldValue("lAsignado", "0").toString())==1 && bs.getFieldValue("iKmInicial", "").toString().compareTo("")!=0 && bs.getFieldValue("iKmFinal", "").toString().compareTo("")!=0) {
                                             out.print(vEti.Texto("ETablaR", df.format(Long.valueOf(bs.getFieldValue("iKmFinal", "&nbsp;").toString()).doubleValue())));
                                             iKmDif = Integer.parseInt(bs.getFieldValue("iKmFinal").toString()) - Integer.parseInt(bs.getFieldValue("iKmInicial").toString());
                                             out.print(vEti.Texto("ETablaR", "" + df.format(Long.valueOf(iKmDif + ""))));
                                             iKmSum += iKmDif;
                                           }
                                           else{
                                             out.print(vEti.Texto("ETabla", "&nbsp;"));
                                             out.print(vEti.Texto("ETabla", "&nbsp;"));
                                           }
                                        }
                                        else{
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));
                                          out.print(vEti.Texto("ETabla", "&nbsp;"));
                                        }
                                        out.print("</tr>");
                                      }

                                      out.print("<tr>");
                                        out.print("<td class='EEtiqueta' colspan='9'>Total Vehículos:</td>");
                                        out.print("<td class='ETablaR'>" + iAsignados + "</td>");
                                        out.print("<td class='ETablaR'>" + iEntregados + "</td>");
                                        out.print("<td class='ETablaR'>" + iCancelados + "</td>");
                                        out.print("<td class='EEtiqueta' colspan='4'>Total Kilómetros:</td>");
                                        out.print("<td class='ETablaR'>" + df.format(Long.valueOf(iKmSum+"")) + "</td>");
                                      out.print("</tr>");

                                    }
                                    else{
                                      out.print("<tr><td class='ETablaC' colspan='14'>No existen datos coincidentes con el filtro proporcionado.</td></tr>");
                                    }
                            %>
                          </table><% // Fin de Datos %>
     </td>
      <script language="JavaScript">
      form = document.forms[0];
         if (form.dtSolicitud)
           form.dtSolicitud.readOnly = true;
      </script>
   </tr>
  <%out.println(clsConfig.getReporte());}else{%>
      <script language="JavaScript">
         fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>


