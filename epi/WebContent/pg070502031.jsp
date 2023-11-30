<%/**
 * Title: Plantillas por Empresa
 * Description: Consulta de Plantillas por Empresa.
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
  pg070502031CFG  clsConfig = new pg070502031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502031.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802032.jsp";       // modificar
  String cDetalle     = "pg070802032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No. Plantilla|Fecha de Solicitud|";    // modificar
  String cCveOrdenar  = "iCvePlantilla|dtSolicitud|";  // modificar
  String cDscFiltrar  = "No. Plantilla|Fecha de Solicitud|";    // modificar
  String cCveFiltrar  = "iCvePlantilla|dtSolicitud|";  // modificar
  String cTipoFiltrar = "7|5|";                // modificar
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
  Vector vPersonalActivo = new Vector();
  String cvEmpresas = "";

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
                   out.print("<input type=\"hidden\" name=\"iCveUsuario\" value=\"" + vUsuario.getICveusuario() + "\">");
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
                               if (bs != null){
                                   bs.start();
                                   out.print("<tr>");
                                   out.print("<td colspan=\"9\" class=\"ETablaT\">Plantillas de Personal</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ETablaT", "No. Plantilla"));
                                   out.print(vEti.Texto("ETablaT", "Programada"));
                                   out.print(vEti.Texto("ETablaT", "Periodo"));
                                   out.print(vEti.Texto("ETablaT", "Solicitud"));
                                   out.print(vEti.Texto("ETablaT", "Vencimiento"));
                                   out.print(vEti.Texto("ETablaT", "Personal Activo"));
                                   out.print("<td colspan=\"3\" class=\"ETablaT\">Recepción</td>");
                                   out.print("</tr>");
                                   vPeriodPla = clsConfig.vPeriodPla;
                                   vPersonalActivo = clsConfig.vPersonalActivo;
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCvePlantilla", "&nbsp;").toString()));
                                     if (bs.getFieldValue("lProgramada", "&nbsp;").toString().compareToIgnoreCase("1") == 0){
                                        out.print(vEti.Texto("ETablaC", "SÍ"));
                                        if (!vPeriodPla.isEmpty()){
                                          for(int i=0;i<vPeriodPla.size();i++){
                                            TVCTRPeriodPla VCTRPeriodPla = new TVCTRPeriodPla();
                                            VCTRPeriodPla = (TVCTRPeriodPla) vPeriodPla.get(i);
                                            if (new Integer(bs.getFieldValue("iAnio", "&nbsp;").toString()).intValue() == VCTRPeriodPla.getiAnio() &&
                                                new Integer(bs.getFieldValue("iCvePeriodPla", "&nbsp;").toString()).intValue() == VCTRPeriodPla.getiCvePeriodPla()){
                                              out.print(vEti.Texto("ETabla", "" + VCTRPeriodPla.getiAnio() + " - " + VCTRPeriodPla.getcDscPeriodPla()));
                                              lPinto = true;
                                            }
                                          }
                                          if (!lPinto){
                                            out.print(vEti.Texto("ETabla", "&nbsp"));
                                          }
                                        }
                                     }
                                     else {
                                        out.print(vEti.Texto("ETablaC", "NO"));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("iAnio", "&nbsp;").toString()));
                                     }
                                     TVCTRPlantilla x = (TVCTRPlantilla) bs.getCurrentBean();

                                     out.print(vEti.Texto("ETablaC", dtFecha.getFechaDDMMYYYY(x.getdtSolicitud(),"/")));
                                     if (x.getdtVencimiento() != null)
                                       out.print(vEti.Texto("ETablaC", dtFecha.getFechaDDMMYYYY(x.getdtVencimiento(),"/")));
                                     else
                                       out.print(vEti.Texto("ETablaC", "&nbsp"));
                                     //Valor del No. de Personal Activo.
                                     if (!vPersonalActivo.isEmpty()){
                                        int ivPersonalActivo = 0;
                                        for(int i=0;i<vPersonalActivo.size();i++){
                                          TVCTRPersonal VCTRPersonal = new TVCTRPersonal();
                                          VCTRPersonal = (TVCTRPersonal) vPersonalActivo.get(i);
                                          if (new Integer(bs.getFieldValue("iCvePlantilla", "").toString()).intValue() == VCTRPersonal.getiCvePlantilla())
                                             ivPersonalActivo = VCTRPersonal.getINumPersonal();
                                        }
                                        out.print(vEti.Texto("ETablaR", new Integer(ivPersonalActivo).toString()));
                                     } else
                                       out.print(vEti.Texto("ETablaC", "&nbsp"));
                                     if (x.getdtRecepcion() != null)
                                        out.print(vEti.Texto("ETablaC", dtFecha.getFechaDDMMYYYY(x.getdtRecepcion(),"/")));
                                     else
                                        out.print(vEti.Texto("ETablaC", "&nbsp"));
                                     out.print("<td class=\"ECampo\">");
                                     out.print(vEti.clsAnclaTexto("EAncla","Detalle de la Plantilla","JavaScript:fListado1('" + bs.getFieldValue("iCveEmpresa","") + "','" + bs.getFieldValue("iCvePlantilla","") +  "');",""));
                                     out.print("</td>");
                                     out.print("<td class=\"ECampo\">");
                                     if (x.getdtRecepcion() != null)
                                        out.print(vEti.clsAnclaTexto("EAncla","Copiar","JavaScript:fCopiar('" + bs.getFieldValue("iCveEmpresa","") + "','" + bs.getFieldValue("iCvePlantilla","") +  "');",""));
                                     else
                                        out.print("No se puede Copiar");
                                     out.print("</td>");
                                     out.print("</tr>");
                                   }
                               } else {
                                  out.print("<tr>");
                                  out.print("<td class=\"ETablaT\" colspan=\"7\">Mensaje</td>");
                                  out.print("</tr>");
                                  out.print("<td class=\"ETabla\" colspan=\"7\">No existen datos coincidentes con el filtro proporcionado.</td>");
                                  out.print("</tr>");
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
