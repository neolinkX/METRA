<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
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
  pg070502020CFG  clsConfig = new pg070502020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802021.jsp";       // modificar
  String cDetalle     = "pg070802021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Denominacion|";    // modificar
  String cCveOrdenar  = "iCveEmpresa|cDenominacion|";  // modificar
  String cDscFiltrar  = "Clave|Denominación|";    // modificar
  String cCveFiltrar  = "GRLEmpresas.iCveEmpresa|GRLEmpresas.cDenominacion|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  String cCanWrite   = clsConfig.getCanWrite();  //"yes";
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

                 out.print(vEti.Texto("EEtiqueta", "Transportista:"));
                 out.print("<td class=\"ECampo\">");
                 out.print("<select name=\"SLSEmpresas\">");

                 if (request.getParameter("SLSEmpresas") != null){
                   if (request.getParameter("SLSEmpresas") != "")
                      cvEmpresas = request.getParameter("SLSEmpresas");
                 }

                 if (cvEmpresas.compareToIgnoreCase("") == 0 || cvEmpresas.compareToIgnoreCase("0") == 0)
                   out.print("<option selected value=\"" + "0"  + "\">" + "Asignadas" + "</option>");
                 else
                   out.print("<option value=\"" + "0"  + "\">" + "Asignadas" + "</option>");
                 if (cvEmpresas.compareToIgnoreCase("1") == 0)
                   out.print("<option selected value=\"" + "1"  + "\">" + "Disponibles" + "</option>");
                 else
                   out.print("<option value=\"" + "1"  + "\">" + "Disponibles" + "</option>");
                 if (cvEmpresas.compareToIgnoreCase("2") == 0)
                   out.print("<option selected value=\"" + "2"  + "\">" + "Asignadas y Disponibles" + "</option>");
                 else
                   out.print("<option value=\"" + "2"  + "\">" + "Asignadas y Disponibles" + "</option>");
                 out.print("</select>");
                 out.print("</td>");
                 out.print("<td>&nbsp;</td>");
                 out.print("</tr>");

                 out.print("<tr>");
                 out.print("<td colspan=\"9\" class=\"ETablaT\">Ubicación del Transportista</td>");
                 out.print("</tr><tr>");

                 out.print(vEti.Texto("EEtiqueta", "País:"));
                 out.print("<td class=\"ECampo\">");
                 vPais = clsConfig.vPais;
                 out.println(vEti.SelectOneRowSinTD("SLSPais", "llenaSLT(1,document.forms[0].SLSPais.value,'','',document.forms[0].SLSEstado,document.forms[0].SLSMunicipio);", vPais, "iCvePais", "cNombre", request, "0"));
                 out.print("</td>");


                 out.print(vEti.Texto("EEtiqueta", "EDO (Estado):"));
                 out.print("<td class=\"ECampo\">");
                 out.print("<select name=\"SLSEstado\" onChange=\"llenaSLT(2,document.forms[0].SLSPais.value,document.forms[0].SLSEstado.value,'',document.forms[0].SLSMunicipio,'');\">");
                 if (request.getParameter("SLSEstado") != null){
                   if (request.getParameter("SLSEstado") != "")
                      cvEstado = request.getParameter("SLSEstado");
                 }
                 if (cvEstado.compareToIgnoreCase("") == 0 || cvEstado.compareToIgnoreCase("0") == 0)
                   out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                 else
                   out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                 vEstado = clsConfig.vEstado;
                 if (!vEstado.isEmpty()){
                   for (int i=0;i<vEstado.size();i++){
                     TVEntidadFed VEntidadFed = new TVEntidadFed();
                     VEntidadFed = (TVEntidadFed) vEstado.get(i);
                     if (cvEstado.compareToIgnoreCase(new Integer(VEntidadFed.getICveEntidadFed()).toString()) == 0)
                        out.print("<option selected value=\"" + VEntidadFed.getICveEntidadFed()  + "\">" + VEntidadFed.getCNombre() + "</option>");
                     else
                        out.print("<option value=\"" + VEntidadFed.getICveEntidadFed()  + "\">" + VEntidadFed.getCNombre() + "</option>");
                   }
                 }
                 out.print("</select>");
                 out.print("</td>");

                 out.print(vEti.Texto("EEtiqueta", "MUN (Municipio):"));
                 out.print("<td class=\"ECampo\">");
                 out.print("<select name=\"SLSMunicipio\">");
                 if (request.getParameter("SLSMunicipio") != null){
                   if (request.getParameter("SLSMunicipio") != "")
                      cvMunicipio = request.getParameter("SLSMunicipio");
                 }
                 if (cvMunicipio.compareToIgnoreCase("") == 0 || cvMunicipio.compareToIgnoreCase("0") == 0)
                   out.print("<option selected value=\"" + "-1"  + "\">" + "Todos" + "</option>");
                 else
                   out.print("<option value=\"" + "-1"  + "\">" + "Todos" + "</option>");
                 vMunicipio = clsConfig.vMunicipio;
                 if (!vMunicipio.isEmpty()){
                   for (int i=0;i<vMunicipio.size();i++){
                     TVMunicipio VMunicipio = new TVMunicipio();
                     VMunicipio = (TVMunicipio) vMunicipio.get(i);
                     if (cvMunicipio.compareToIgnoreCase(new Integer(VMunicipio.getICveMunicipio()).toString()) == 0)
                       out.print("<option selected value=\"" + VMunicipio.getICveMunicipio()  + "\">" + VMunicipio.getCNombre() + "</option>");
                     else
                       out.print("<option value=\"" + VMunicipio.getICveMunicipio()  + "\">" + VMunicipio.getCNombre() + "</option>");
                   }
                 }
                 out.print("</select>");
                 out.print("</td>");
                %>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 3, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar el Trasnportista","javascript:fSubmitForm1('Buscar');", vParametros) %></td>
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
                                   out.print(vEti.Texto("ETablaT", "Clave"));
                                   out.print(vEti.Texto("ETablaT", "Denominación"));
                                   out.print(vEti.Texto("ETablaT", "Modo de Transporte"));
                                   out.print(vEti.Texto("ETablaT", "Dirección"));
                                   out.print(vEti.Texto("ETablaT", "Selección"));
                                   out.print("</tr>");
                                   while(bs.nextRow()){
                                     boolean lPinto = false;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveEmpresa", "&nbsp;").toString()));
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
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cCalle", "&nbsp;").toString() + "," +
                                                                    bs.getFieldValue("cColonia", "&nbsp;").toString() + "," +
                                                                    bs.getFieldValue("cCiudad", "&nbsp;").toString() + "" ));
                                     if (new Integer(bs.getFieldValue("iCveUniMed", "&nbsp;").toString()).intValue() == 0){
                                       if (cCanWrite.compareTo("yes") == 0)
                                          out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveEmpresa", "&nbsp;").toString(),"0" ,"", 59, true, "Sí", "No", true));
                                       else
                                          out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveEmpresa", "&nbsp;").toString(),"0" ,"", 59, true, "Sí", "No", false));
                                     }
                                     else{
                                       if (cCanWrite.compareTo("yes") == 0)
                                          out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveEmpresa", "&nbsp;").toString(),"1" ,"", 59, true, "Sí", "No", true));
                                       else
                                          out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveEmpresa", "&nbsp;").toString(),"1" ,"", 59, true, "Sí", "No", false));
                                     }
                                     out.print("<input type=\"hidden\" name=\"TBXHid-" + bs.getFieldValue("iCveEmpresa", "&nbsp;").toString() + "\" value=\"1\">");
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
