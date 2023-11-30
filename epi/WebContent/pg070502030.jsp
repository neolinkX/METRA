<%/**
 * Title: Consulta de Empresas por Unidad.
 * Description: Consulta de Empresas por Unidad.
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
  pg070502030CFG  clsConfig = new pg070502030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802021.jsp";       // modificar
  String cDetalle     = "pg070802021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "ID DGPMPT|Razón Social|";    // modificar
  String cCveOrdenar  = "cIDDGPMPT|cDenominacion|";  // modificar
  String cDscFiltrar  = "ID DGPMPT|Razón Social|";    // modificar
  String cCveFiltrar  = "GRLEmpresas.cIDDGPMPT|GRLEmpresas.cDenominacion|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar

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
  String cCanWrite   = clsConfig.getCanWrite();         //"yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TFechas dtFecha = new TFechas();
  Vector vMdoTrans  = new Vector();
  Vector vPais      = new Vector();
  Vector vEstado    = new Vector();
  Vector vMunicipio = new Vector();
  Vector vUnidades = new Vector();
  String cvEmpresas = "";
  String cvEstado = "";
  String cvMunicipio = "";
  String cvUnidades = "";
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
  <input type="hidden" name="hdIni" value="">
  <input type="hidden" name="iCveEmpresa" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda del Transportista</td>
           </tr><tr>
                <%
                 //out.print(vEti.Texto("EEtiqueta", "Laboratorio:"));
                 //out.print("<td>");
                 TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                 int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));

                 vUnidades = vUsuario.getVUniFiltro(iCveProceso);
                 out.print(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                 out.print("<td class=\"ECampo\">");
                 out.print("<select name=\"SLSUniMed\">");
                 if (request.getParameter("SLSUniMed") != null){
                   if (request.getParameter("SLSUniMed") != "")
                      cvUnidades = request.getParameter("SLSUniMed");
                 }
                 if (cvUnidades.compareToIgnoreCase("") == 0 || cvUnidades.compareToIgnoreCase("0") == 0)
                   out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                 else
                   out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                 if (!vUnidades.isEmpty()){
                   for (int i=0;i<vUnidades.size();i++){
                     TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                     VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                     if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                        out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                     else
                        out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                   }
                 }
                 out.print("</select>");
                 out.print("</td>");

                 //out.println(vEti.SelectOneRowSinTD("SLSUniMed", "", , "iCveUniMed", "cDscUniMed", request, "0"));
                 //out.print("</td>");

                 vMdoTrans = clsConfig.vMdoTrans;
                 out.print(vEti.Texto("EEtiqueta", "Modos de Transporte:"));
                 out.print("<td class=\"ECampo\">");
                 out.println(vEti.SelectOneRowSinTD("SLSMdoTransporte", "", vMdoTrans, "iCveMdoTrans", "cDscMdoTrans", request, "0"));
                 out.print("</td>");

                %>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 3, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar el Transportista","javascript:fSubmitForm1('Buscar');", vParametros) %></td>
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
                                   out.print("<td colspan=\"7\" class=\"ETablaT\">Transportistas</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ETablaT", "ID DGPMPT"));
                                   out.print(vEti.Texto("ETablaT", "Razón Social"));
                                   out.print(vEti.Texto("ETablaT", "Modo de Transporte"));
                                   out.print("<td colspan=\"2\" class=\"ETablaT\">Plantillas</td>");
                                   out.print("</tr>");
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("cIDDGPMPT", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDenominacion", "&nbsp;").toString()));
                                     if (!vMdoTrans.isEmpty())
                                     for (int i=0;i< vMdoTrans.size();i++){
                                       TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                                       VGRLMdoTrans = (TVGRLMdoTrans) vMdoTrans.get(i);
                                       if (VGRLMdoTrans.getICveMdoTrans() == new Integer(bs.getFieldValue("iCveMdoTrans", "&nbsp;").toString()).intValue()){
                                         out.print(vEti.Texto("ETabla", VGRLMdoTrans.getCDscMdoTrans()));
                                         lPinto = true;
                                       }
                                     }
                                     if (!lPinto)
                                        out.print(vEti.Texto("ETabla", "&nbsp;"));
                                     out.print("<td class=\"ECampo\">");
                                     out.print(vEti.clsAnclaTexto("EAncla","Detalle del Transportista","JavaScript:fListado1('" + bs.getFieldValue("iCveEmpresa","") + "'" + ");",""));
                                     out.print("</td>");
                                     out.print("<td class=\"ECampo\">");
                                     out.print(vEti.clsAnclaTexto("EAncla","Plantillas","JavaScript:fListado2('" + bs.getFieldValue("iCveEmpresa","") + "'" + ");",""));
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
