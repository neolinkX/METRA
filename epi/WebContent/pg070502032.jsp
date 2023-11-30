<%/**
 * Title: Consulta de Empresas por Unidad Médica.
 * Description: Consulta de Empresas por Unidad Médica.
 * Copyright: Micros Personales, S.A. de C.V.
 * Company: Micros Personales, S.A. de C.V.
 * @author LCI. Oscar Castrejón Adame.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>
<html>
<%
  pg070502032CFG  clsConfig = new pg070502032CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502032.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502032.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802032.jsp";       // modificar
  String cDetalle     = "pg070802032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|";    // modificar
  String cCveOrdenar  = "iCvePlantilla|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCvePlantilla|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;            // modificar
  String cCanWrite   = clsConfig.getCanWrite();   //"yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TFechas dtFecha = new TFechas();
  Vector vEmpresa = new Vector();
  Vector vMdoTrans = new Vector();
  Vector vUnidades = new Vector();
  Vector vPeriodPla = new Vector();
  Vector vcPersonal = new Vector();
  Vector vMedRecep = new Vector();
  Vector vTpoEntrega = new Vector();
  String cvEmpresas = "";
  TFechas Fecha = new TFechas();
  java.sql.Date dtToday = Fecha.TodaySQL();

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
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
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="10" class="ETablaT">Transportista Seleccionado</td>
           </tr><tr>
                <%
                 if (request.getParameter("SLSUniMed") != null)
                   if (request.getParameter("SLSUniMed") != "")
                      out.print("<input type=\"hidden\" name=\"SLSUniMed\" value=\"" + request.getParameter("SLSUniMed") + "\">");
                   else
                      out.print("<input type=\"hidden\" name=\"SLSUniMed\" value=\"\">");
                 else
                   out.print("<input type=\"hidden\" name=\"SLSUniMed\" value=\"\">");

                 if (request.getParameter("SLSMdoTransporte") != null)
                   if (request.getParameter("SLSMdoTransporte") != "")
                      out.print("<input type=\"hidden\" name=\"SLSMdoTransporte\" value=\"" + request.getParameter("SLSMdoTransporte")  + "\">");
                   else
                      out.print("<input type=\"hidden\" name=\"SLSMdoTransporte\" value=\"\">");
                 else
                   out.print("<input type=\"hidden\" name=\"SLSMdoTransporte\" value=\"\">");

                 if (request.getParameter("hdIni") != null)
                   if (request.getParameter("hdIni") != "")
                      out.print("<input type=\"hidden\" name=\"hdIni\" value=\"" + request.getParameter("hdIni") + "\">");
                   else
                      out.print("<input type=\"hidden\" name=\"hdIni\" value=\"\">");
                 else
                   out.print("<input type=\"hidden\" name=\"hdIni\" value=\"\">");

                 if (request.getParameter("hdIni2") != null)
                   if (request.getParameter("hdIni2") != "")
                      out.print("<input type=\"hidden\" name=\"hdIni2\" value=\"" + request.getParameter("hdIni2") + "\">");
                   else
                      out.print("<input type=\"hidden\" name=\"hdIni2\" value=\"\">");
                 else
                   out.print("<input type=\"hidden\" name=\"hdIni2\" value=\"\">");

                 vEmpresa = clsConfig.vEmpresas;
                 if (!vEmpresa.isEmpty()){
                   TVGRLEmpresas VGRLEmpresas = new TVGRLEmpresas();
                   VGRLEmpresas = (TVGRLEmpresas) vEmpresa.get(0);
                   out.print("<td class=\"EEtiqueta\">Clave:</td>");
                   out.print("<td class=\"ETablaR\">" + VGRLEmpresas.getICveEmpresa() + "</td>");
                   out.print("<td class=\"EEtiqueta\">ID DGPMPT:</td>");
                   out.print("<td class=\"ETablaR\">" + VGRLEmpresas.getcIDDGPMPT() + "</td>");
                   out.print("<td class=\"EEtiqueta\">Razón Social:</td>");
                   out.print("<td class=\"ECampo\">" + VGRLEmpresas.getCDenominacion() + "</td>");
                   out.print("</tr>");
                   vMdoTrans = clsConfig.vMdoTrans;
                   if (!vMdoTrans.isEmpty()){
                      for (int i=0;i<vMdoTrans.size();i++){
                        TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                        VGRLMdoTrans = (TVGRLMdoTrans) vMdoTrans.get(i);
                        if (VGRLMdoTrans.getICveMdoTrans() == VGRLEmpresas.getICveMdoTrans()){
                          out.print("<tr>");
                          out.print("<td class=\"EEtiqueta\">Modo de Transporte:</td>");
                          out.print("<td class=\"ECampo\" colspan=\"9\">" + VGRLMdoTrans.getCDscMdoTrans() + "</td>");
                          out.print("</tr>");
                        }
                      }
                   } else {
                    out.print("<td class=\"ETablaT\">&nbsp</td>");
                   }
                   TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                   int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                   vUnidades = vUsuario.getVUniFiltro(iCveProceso);
                   if (!vUnidades.isEmpty()){
                     for (int i=0;i<vUnidades.size();i++){
                       TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                       VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                       if (new Integer(VGRLEmpresas.getICveUniMed()).toString().compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0){
                          out.print("<tr>");
                          out.print("<td class=\"EEtiqueta\">Unidad Médica:</td>");
                          out.print("<td class=\"ECampo\" colspan=\"9\">" + VGRLUMUsuario.getCDscUniMed() + "</td>");
                        //  out.print("</tr>");
                       }
                     }
                   } else {
                      out.print("<td class=\"ETablaT\">&nbsp</td>");
                   }
                 } else
                    out.print("<td class=\"ETablaT\">&nbsp</td>");

// Información de la Empresa Seleccionada.
                %>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                             <% // modificar según listado
                               TVUsuario vUsuario     = new TVUsuario();
                               vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                boolean lActivo = true;
                                boolean lModificar = clsConfig.lModificar; //false;
                                if (lModificar)
                                  out.print("<input type=\"hidden\" name=\"hdModificar\" value=\"1\">");
                                else
                                  out.print("<input type=\"hidden\" name=\"hdModificar\" value=\"0\">");
                               if (bs != null){
                                   bs.start();
                                   out.print("<tr>");
                                   out.print("<td colspan=\"7\" class=\"ETablaT\">Información de la Plantilla</td>");
                                   out.print("</tr>");
                                   vPeriodPla = clsConfig.vPeriodPla;
                                   vMedRecep = clsConfig.vMedRecep;
                                   vTpoEntrega = clsConfig.vTpoEntrega;
                                   while(bs.nextRow()){
                                     TVCTRPlantilla VCTRPlantilla = new TVCTRPlantilla();
                                     VCTRPlantilla = (TVCTRPlantilla) bs.getCurrentBean();
                                     boolean lPinto = false;
                                     if (VCTRPlantilla.getdtRecepcion() != null)
                                        lActivo = false;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                     if (lModificar)
                                       out.print(vEti.Texto("ETabla", "&nbsp"));
                                     else
                                       out.print(vEti.Texto("ETabla", bs.getFieldValue("iCvePlantilla", "&nbsp;").toString()));
                                     out.print(vEti.Texto("EEtiqueta", "Solicitud:"));
                                     out.print("<td class=\"ECampo\">");
                                     if (lModificar)
                                        out.print(vEti.CampoSinCelda("input",10,10,"dtSolicitud", "",3,"","",true,lModificar));
                                     else
                                        out.print(vEti.CampoSinCelda("input",10,10,"dtSolicitud", Fecha.getFechaDDMMYYYY(VCTRPlantilla.getdtSolicitud(),"/"),3,"","",true,lModificar));
                                     out.print("</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Programada:"));
                                     if (bs.getFieldValue("lProgramada", "&nbsp;").toString().compareToIgnoreCase("1") == 0)
                                       out.print(vEti.ToggleMov("ETabla", "TBXProgramada","1" ,"", 59, true, "Sí", "No", lModificar));
                                     else
                                       out.print(vEti.ToggleMov("ETabla", "TBXProgramada","0" ,"", 59, true, "Sí", "No", lModificar));
                                     out.print(vEti.Texto("EEtiqueta", "Vencimiento:"));
                                     out.print("<td class=\"ECampo\">");
                                     //if (bs.getFieldValue("dtVencimiento", "&nbsp;") != null)
                                     if (lModificar)
                                        out.print(vEti.CampoSinCelda("input",10,10,"dtVencimiento", "",3,"","",true,lModificar));
                                     else
                                        out.print(vEti.CampoSinCelda("input",10,10,"dtSolicitud", Fecha.getFechaDDMMYYYY(VCTRPlantilla.getdtVencimiento(),"/"),3,"","",true,lModificar));
                                     out.print("</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Año:"));
                                     out.print("<td class=\"ETabla\">");
                                     if (lModificar)
                                       out.print("<select name=\"SLSAnio\" onChange=\"llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].SLSPeriodo);\">");
                                     else
                                       out.print("<select name=\"SLSAnio\" onChange=\"llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].SLSPeriodo);\" disabled>");
                                     for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                        if (request.getParameter("SLSAnio")!=null&&request.getParameter("SLSAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                          out.print("<option value = " + i + " selected>" + i + "</option>");
                                        else
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                     }
                                     out.print("</select>");
                                     out.print("</td>");
                                     out.print(vEti.Texto("EEtiqueta", "Periodo:"));
                                     out.print("<td class=\"ETabla\">");
                                     if (lModificar)
                                        out.print("<select name=\"SLSPeriodo\" onChange=\"llenaFecha(2,document.forms[0].SLSAnio.value,document.forms[0].SLSPeriodo.value,document.forms[0].dtVencimiento);\">");
                                     else
                                        out.print("<select name=\"SLSPeriodo\" disabled>");
                                     if (!vPeriodPla.isEmpty()){
                                       for(int i=0;i<vPeriodPla.size();i++){
                                         TVCTRPeriodPla VCTRPeriodPla = new TVCTRPeriodPla();
                                         VCTRPeriodPla = (TVCTRPeriodPla) vPeriodPla.get(i);
                                         if (request.getParameter("SLSPeriodo")!=null&&request.getParameter("SLSPeriodo").compareToIgnoreCase((new Integer(VCTRPeriodPla.getiCvePeriodPla())).toString())==0 &&
                                             request.getParameter("SLSAnio")!=null&&request.getParameter("SLSAnio").compareToIgnoreCase((new Integer(VCTRPeriodPla.getiAnio())).toString())==0 )
                                            out.print("<option value = " + VCTRPeriodPla.getiCvePeriodPla() + " selected>" + VCTRPeriodPla.getiCvePeriodPla() + " - " + VCTRPeriodPla.getcDscPeriodPla() + "</option>");
                                         else
                                            out.print("<option value = " + VCTRPeriodPla.getiCvePeriodPla() + ">" + VCTRPeriodPla.getiCvePeriodPla() + " - " + VCTRPeriodPla.getcDscPeriodPla() + "</option>");
                                       }
                                     }
                                     out.print("</select>");
                                     out.print("</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETablaT\" colspan=\"2\">Solicita</td>");
                                     out.print("<td class=\"ETablaT\" colspan=\"2\">Recibe</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Unidad Medica:"));
                                     out.print("<td class=\"ETabla\">");
                                     String UserSol = "";
                                     if (vUsuario != null)
                                       UserSol = "" + vUsuario.getICveusuario();
                                     if (lModificar)
                                       out.print("<select name=\"SLSUniMedSolicita\" onChange=\"llenaUsuario(4," + UserSol + ",this.value,document.forms[0].SLSUsrSolicita);\">");
                                     else
                                         out.print("<select name=\"SLSUniMedSolicita\" disabled>");
                                     if (!vUnidades.isEmpty()){
                                       for (int i=0;i<vUnidades.size();i++){
                                         TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                         VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                         if (request.getParameter("SLSUniMedSolicita")!=null&&request.getParameter("SLSUniMedSolicita").compareToIgnoreCase((new Integer(VGRLUMUsuario.getICveUniMed())).toString())==0  )
                                            out.print("<option value = " + VGRLUMUsuario.getICveUniMed() + " selected>" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                         else
                                            out.print("<option value = " + VGRLUMUsuario.getICveUniMed() + ">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                       }
                                     }
                                     out.print("</select>");
                                     out.print("</td>");
                                     if (!lModificar){
                                        out.print(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                                        out.print("<td class=\"ETabla\">");
                                        String User = "";
                                        if (vUsuario != null)
                                          User = "" + vUsuario.getICveusuario();
                                        if (lActivo)
                                                                                                 //llenaSLT(1,"+ User + ",this.value,'',document.forms[0].iCveProceso);"
                                          out.print("<select name=\"SLSUniMedRecibe\" onChange=\"llenaUsuario(3," + User + ",this.value,document.forms[0].SLSUsrSolicita);\">");
                                        else
                                          out.print("<select name=\"SLSUniMedRecibe\" disabled>");
                                        if (VCTRPlantilla.getiCveUMRecibe() != 0)
                                          out.print("<option value =\"0\" selected>" + "Seleccione..." + "</option>");
                                        else
                                          out.print("<option value =\"0\" >" + "Seleccione..." + "</option>");
                                        if (!vUnidades.isEmpty()){
                                          for (int i=0;i<vUnidades.size();i++){
                                            TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                            VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                            if (new Integer(VGRLUMUsuario.getICveUniMed()).toString().compareToIgnoreCase(new Integer(VCTRPlantilla.getiCveUMRecibe()).toString()) == 0)
                                           //if (request.getParameter("SLSUniMedRecibe")!=null&&request.getParameter("SLSUniMedRecibe").compareToIgnoreCase((new Integer(VGRLUMUsuario.getICveUniMed())).toString())==0  )
                                               out.print("<option value = " + VGRLUMUsuario.getICveUniMed() + " selected>" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                            else
                                               out.print("<option value = " + VGRLUMUsuario.getICveUniMed() + ">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                          }
                                        }
                                        out.print("</select>");
                                        out.print("</td>");
                                     } else {
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                     }
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                                     int iUniMed = 0;
                                     if(request.getParameter("SLSUniMedSolicita") == null){
                                       vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                       if(vcPersonal.size() != 0){
                                          iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                       }
                                     }else{
                                       iUniMed = Integer.parseInt(request.getParameter("SLSUniMedSolicita"),10);
                                     }
                                     vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                                     out.print(vEti.Texto("EEtiqueta", "Usuario:"));
                                     out.print("<td class=\"ETabla\">");
                                     if (lModificar)
                                       out.print(vEti.SelectOneRowSinTD("SLSUsrSolicita", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "","1"));
                                     else
                                       out.print(vEti.SelectOneRowSinTD("SLSUsrSolicita", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "" +VCTRPlantilla.getiCveUsuSolicita(),"0"));
                                     out.print("</td>");
                                     if (!lModificar){
                                       out.print(vEti.Texto("EEtiqueta", "Usuario:"));
                                       if(request.getParameter("SLSUniMedRecibe") == null){
                                         vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                         if(vcPersonal.size() != 0){
                                            iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                         }
                                       }else{
                                         iUniMed = Integer.parseInt(request.getParameter("SLSUniMedRecibe"),10);
                                       }

                                       if(request.getParameter("SLSUniMedRecibe") == null){
                                         vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                         if(vcPersonal.size() != 0){
                                            iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                         }
                                       }else{
                                         iUniMed = Integer.parseInt(request.getParameter("SLSUniMedRecibe"),10);
                                       }
                                       vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                                       //if (vcPersonal.isEmpty()){
                                          TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                          VGRLUMUsuario.setICveUniMed(iUniMed);
                                          VGRLUMUsuario.setCDscUniMed("");
                                          VGRLUMUsuario.setICveProceso(iCveProceso);
                                          VGRLUMUsuario.setCDscProceso("");
                                          VGRLUMUsuario.setICveUsuario(0);
                                          VGRLUMUsuario.setCNombre("Seleccione...");
                                          VGRLUMUsuario.setCApPaterno("");
                                          VGRLUMUsuario.setCApMaterno("");
                                          vcPersonal.addElement(VGRLUMUsuario);
                                       //}
                                       out.print("<td class=\"ETabla\">");
                                       if (lActivo)
                                         out.print(vEti.SelectOneRowSinTD("SLSUsrRecibe", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "","1"));
                                       else
                                         out.print(vEti.SelectOneRowSinTD("SLSUsrRecibe", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "","0"));
                                       out.print("</td>");
                                     } else {
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                     }
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     if (!lModificar){
                                       out.print("<td class=\"EEtiqueta\">Medio de Recepción:</td>");
                                       out.print("<td class=\"ETabla\">");
                                       if (lActivo)
                                         out.print("<select name=\"SLSMedio\" >");
                                       else
                                         out.print("<select name=\"SLSMedio\" disabled>");
                                       if (VCTRPlantilla.getiCveMedRecep() == 0)
                                          out.print("<option value =\"0\" selected>Seleccione...</option>");
                                       else
                                          out.print("<option value =\"0\">Seleccione...</option>");
                                       if (!vMedRecep.isEmpty()){
                                         for (int i=0;i<vMedRecep.size();i++){
                                           TVCTRMedRecep VCTRMedRecep = new TVCTRMedRecep();
                                           VCTRMedRecep = (TVCTRMedRecep) vMedRecep.get(i);
                                           if (new Integer(VCTRMedRecep.getICveMedRecep()).toString().compareToIgnoreCase(new Integer(VCTRPlantilla.getiCveMedRecep()).toString()) ==0)
                                              out.print("<option value = " + VCTRMedRecep.getICveMedRecep() + " selected>" + VCTRMedRecep.getCDscMedRecep() + "</option>");
                                           else
                                              out.print("<option value = " + VCTRMedRecep.getICveMedRecep() + ">" + VCTRMedRecep.getCDscMedRecep() + "</option>");
                                         }
                                       }
                                       out.print("</select>");
                                       out.print("</td>");
                                     } else {
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                     }
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     if (!lModificar){
                                       out.print("<td class=\"EEtiqueta\">Tipo de Entrega:</td>");
                                       out.print("<td class=\"ETabla\">");
                                       if (lActivo)
                                         out.print("<select name=\"SLSTpoEntrega\" >");
                                       else
                                         out.print("<select name=\"SLSTpoEntrega\" disabled>");
                                       if (VCTRPlantilla.getiCveTpoEntrega() == 0)
                                          out.print("<option value =\"0\" selected>Seleccione...</option>");
                                       else
                                          out.print("<option value =\"0\">Seleccione...</option>");
                                       if (!vTpoEntrega.isEmpty()){
                                         for (int i=0;i<vTpoEntrega.size();i++){
                                           TVCTRTpoEntrega VCTRTpoEntrega = new TVCTRTpoEntrega();
                                           VCTRTpoEntrega = (TVCTRTpoEntrega) vTpoEntrega.get(i);
                                           if (new Integer(VCTRTpoEntrega.getICveTpoEntrega()).toString().compareToIgnoreCase(new Integer(VCTRPlantilla.getiCveTpoEntrega()).toString()) ==0)
                                              out.print("<option value = " + VCTRTpoEntrega.getICveTpoEntrega() + " selected>" + VCTRTpoEntrega.getCDscTpoEntrega() + "</option>");
                                           else
                                              out.print("<option value = " + VCTRTpoEntrega.getICveTpoEntrega() + ">" + VCTRTpoEntrega.getCDscTpoEntrega() + "</option>");
                                         }
                                       }
                                       out.print("</select>");
                                       out.print("</td>");
                                     } else {
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                     }
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     if (!lModificar){
                                       out.print("<td class=\"EEtiqueta\">Recepción:</td>");
                                       out.print("<td class=\"ECampo\">");
                                       if (VCTRPlantilla.getdtRecepcion() != null)
                                          out.print(vEti.CampoSinCelda("input",10,10,"dtRecepcion", Fecha.getFechaDDMMYYYY(VCTRPlantilla.getdtRecepcion(),"/"),3,"","",true,false));
                                       else
                                          out.print(vEti.CampoSinCelda("input",10,10,"dtRecepcion", "",3,"","",true,false));
                                       out.print("</td>");
                                     } else {
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                       out.print("<td class=\"ETabla\">&nbsp</td>");
                                     }
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     if (VCTRPlantilla.getdtRecepcion() == null){
                                        if (lModificar){
                                          out.print("<td class=\"ECampo\" colspan=\"7\">");
                                        } else {
                                          out.print("<td class=\"ECampo\" colspan=\"4\">");
                                          out.print("<div align=\"center\">");
                                          out.print(vEti.clsAnclaTexto("ECampoC","Cerrar Plantilla","JavaScript:fCerrar('" + bs.getFieldValue("iCveEmpresa","") + "','" + bs.getFieldValue("iCvePlantilla","") +  "');",""));
                                          out.print("</div>");
                                          out.print("</td>");
                                        }
                                     }
                                     else {
                                        if (!lModificar){
                                          out.print("<td class=\"EEtiqueta\" colspan=\"4\">");
                                          out.print("<div align=\"center\">");
                                          out.print("Plantilla Cerrada");
                                          out.print("</div>");
                                          out.print("</td>");
                                        } else
                                          out.print("<td class=\"ECampo\" colspan=\"7\">");
                                     }

                                     out.print("</tr>");

                                     if (!lModificar){
                                       out.print("<tr>");
                                       out.print("<td class=\"ECampo\" colspan=\"2\">");
                                       out.print("<div align=\"center\">");
                                       out.print(vEti.clsAnclaTexto("ECampoC","Seguimiento","JavaScript:fListado1('" + bs.getFieldValue("iCveEmpresa","") + "','" + bs.getFieldValue("iCvePlantilla","") +  "');",""));
                                       out.print("</div>");
                                       out.print("</td>");
                                       out.print("<td class=\"ECampo\" colspan=\"2\" align=\"center\">");
                                       out.print("<div align=\"center\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Personal","JavaScript:fListado2('" + bs.getFieldValue("iCveEmpresa","") + "','" + bs.getFieldValue("iCvePlantilla","") +  "');",""));
                                       out.print("</div>");
                                       out.print("</td>");
                                     }
                                   }
                               } else {
                                  if (lModificar){
                                     vPeriodPla = clsConfig.vPeriodPla;
                                     vMedRecep = clsConfig.vMedRecep;
                                     vTpoEntrega = clsConfig.vTpoEntrega;
                                     out.print("<tr>");
                                     out.print("<td colspan=\"7\" class=\"ETablaT\">Información de la Plantilla</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                     out.print(vEti.Texto("ETabla", "&nbsp"));
                                     out.print(vEti.Texto("EEtiqueta", "Solicitud:"));
                                     out.print("<td class=\"ECampo\">");
                                     out.print(vEti.CampoSinCelda("input",10,10,"dtSolicitud", "",3,"","",true,lModificar));
                                     out.print("</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Programada:"));
                                     out.print(vEti.ToggleMov("ETabla", "TBXProgramada","0" ,"", 59, true, "Sí", "No", lModificar));
                                     out.print(vEti.Texto("EEtiqueta", "Vencimiento:"));
                                     out.print("<td class=\"ECampo\">");
                                     out.print(vEti.CampoSinCelda("input",10,10,"dtVencimiento", "",3,"","",true,lModificar));
                                     out.print("</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Año:"));
                                     out.print("<td class=\"ETabla\">");
                                     out.print("<select name=\"SLSAnio\" onChange=\"llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].SLSPeriodo);\">");
                                     for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                        if (request.getParameter("SLSAnio")!=null&&request.getParameter("SLSAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                          out.print("<option value = " + i + " selected>" + i + "</option>");
                                        else
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                     }
                                     out.print("</select>");
                                     out.print("</td>");
                                     out.print(vEti.Texto("EEtiqueta", "Periodo:"));
                                     out.print("<td class=\"ETabla\">");
                                     out.print("<select name=\"SLSPeriodo\" onChange=\"llenaFecha(2,document.forms[0].SLSAnio.value,document.forms[0].SLSPeriodo.value,document.forms[0].dtVencimiento);\">> >");
                                     if (!vPeriodPla.isEmpty()){
                                       for(int i=0;i<vPeriodPla.size();i++){
                                         TVCTRPeriodPla VCTRPeriodPla = new TVCTRPeriodPla();
                                         VCTRPeriodPla = (TVCTRPeriodPla) vPeriodPla.get(i);
                                         if (request.getParameter("SLSPeriodo")!=null&&request.getParameter("SLSPeriodo").compareToIgnoreCase((new Integer(VCTRPeriodPla.getiCvePeriodPla())).toString())==0 &&
                                             request.getParameter("SLSAnio")!=null&&request.getParameter("SLSAnio").compareToIgnoreCase((new Integer(VCTRPeriodPla.getiAnio())).toString())==0 )
                                            out.print("<option value = " + VCTRPeriodPla.getiCvePeriodPla() + " selected>" + VCTRPeriodPla.getiCvePeriodPla() + " - " + VCTRPeriodPla.getcDscPeriodPla() + "</option>");
                                         else
                                            out.print("<option value = " + VCTRPeriodPla.getiCvePeriodPla() + ">" + VCTRPeriodPla.getiCvePeriodPla() + " - " + VCTRPeriodPla.getcDscPeriodPla() + "</option>");
                                       }
                                     }
                                     out.print("</select>");
                                     out.print("</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETablaT\" colspan=\"2\">Solicita</td>");
                                     out.print("<td class=\"ETablaT\" colspan=\"2\">Recibe</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Unidad Medica:"));
                                     out.print("<td class=\"ETabla\">");
                                     out.print("<select name=\"SLSUniMedSolicita\" >");
                                     if (!vUnidades.isEmpty()){
                                       for (int i=0;i<vUnidades.size();i++){
                                         TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                         VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                         out.print("<option value = " + VGRLUMUsuario.getICveUniMed() + ">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                       }
                                     }
                                     out.print("</select>");
                                     out.print("</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                                     int iUniMed = 0;
                                     if(request.getParameter("SLSUniMedSolicita") == null){
                                       vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                       if(vcPersonal.size() != 0){
                                          iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                       }
                                     }else{
                                       iUniMed = Integer.parseInt(request.getParameter("SLSUniMedSolicita"),10);
                                     }
                                     vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                                     out.print(vEti.Texto("EEtiqueta", "Usuario:"));
                                     out.print("<td class=\"ETabla\">");
                                     out.print(vEti.SelectOneRowSinTD("SLSUsrSolicita", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "","1"));
                                     out.print("</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("</tr>");
                                     out.print("<tr>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("<td class=\"ETabla\">&nbsp</td>");
                                     out.print("</tr>");
                                  } else {
                                    out.print("<tr>");
                                    out.print("<td class=\"ETablaT\" colspan=\"7\">Mensaje</td>");
                                    out.print("</tr>");
                                    out.print("<td class=\"ETabla\" colspan=\"7\">No existen datos coincidentes con el filtro proporcionado.</td>");
                                    out.print("</tr>");
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
