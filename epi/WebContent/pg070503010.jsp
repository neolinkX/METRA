<%/**
 * Title:         Concentrado de Empresas
 * Description:   Concentrado de Empresas
 * Copyright:     2004
 * Company:       Microe Personales S.A. de C.V.
 * @author        Marco Antonio Hernández García
 * @version       1.0
 * Clase:         pg070502050
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070503010CFG  clsConfig = new pg070503010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070503010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070503010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "RFC|Licencia|Nombre|Apellido Paterno|Apellido Materno|";    // modificar
  String cCveOrdenar  = "CTRPersonal.cRFC|CTRPersonal.cLicencia|CTRPersonal.cNombre|CTRPersonal.cApPaterno|CTRPersonal.cApMaterno|";  // modificar
  String cDscFiltrar  = "RFC|Licencia|Nombre|Apellido Paterno|Apellido Materno|";    // modificar
  String cCveFiltrar  = "CTRPersonal.cRFC|CTRPersonal.cLicencia|CTRPersonal.cNombre|CTRPersonal.cApPaterno|CTRPersonal.cApMaterno|";  // modificar
  String cTipoFiltrar = "8|8|8|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                 // modificar
  String cEstatusIR   = "Imprimir";            // modificar
  TFechas Fecha = new TFechas();
  String dtToday = Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(),"/");


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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cSQL = "";
  DecimalFormat df = new DecimalFormat("##,###,##0.00");
  String cValor = "";
  TFechas dtFecha = new TFechas();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  Vector vMdoTransp = new Vector();
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
  <input type="hidden" name="hdIni" value="<%  /*EMPRESA*/   if (request.getParameter("hdIni") != null) out.print(request.getParameter("hdIni"));%>">
  <input type="hidden" name="hdIni2" value="<% /*PLANTILLA*/ if (request.getParameter("hdIni2") != null) out.print(request.getParameter("hdIni2"));%>">

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="<%if (request.getParameter("hdBoton") != null)  out.print(request.getParameter("hdBoton"));%>">
  <input type="hidden" name="hdToday" value="<%=dtToday%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
               <td colspan="6" class="ETablaT">Filtro de la Búsqueda</td>
             </tr>
                  <%
                    TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
                    //Vector        vMdoTransp   = new Vector();

                    try{
                      vMdoTransp = DGRLMdoTrans.findByAll(" where lActivo = 1");
                    } catch (Exception ex){
                      ex.printStackTrace();
                    }
                    out.print("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta", "RFC:", "ECampo", "text", 15, 15,5,"cRFC", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta", "Licencia:", "ECampo", "text", 20, 20,5,"cLicencia", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print(vEti.EtiCampo("EEtiqueta", "Nombre:", "ECampo", "text", 30, 30,"cNombre", "", 3, "", "", true, true, true,request));
                    out.print(vEti.EtiCampo("EEtiqueta", "Apellido Paterno:", "ECampo", "text", 25, 25,"cApPaterno", "", 3, "", "", true, true, true,request));
                    out.print(vEti.EtiCampo("EEtiqueta", "Apellido Materno:", "ECampo", "text", 25, 25,"cApMaterno", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td class=\"EEtiqueta\">Modo de Transporte</td>");
                    out.print("<td class=\"ECampo\">");
                    out.print("<select name=\"SLSModoTransporte\">");
                    if (request.getParameter("SLSModoTransporte") != null){
                       if (request.getParameter("SLSModoTransporte").toString().compareToIgnoreCase("-1") == 0)
                          out.print("<option value=\"-1\" selected>Todos los Modos</option>");
                       else
                           out.print("<option value=\"-1\">Todos los Modos</option>");
                    } else{
                       out.print("<option value=\"-1\">Todos los Modos</option>");
                    }
                    if (!vMdoTransp.isEmpty()){
                        for(int i=0;i<vMdoTransp.size();i++){
                           TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                           VGRLMdoTrans = (TVGRLMdoTrans) vMdoTransp.get(i);
                           if (request.getParameter("SLSModoTransporte") != null){
                             if (request.getParameter("SLSModoTransporte").toString().compareToIgnoreCase(new Integer(VGRLMdoTrans.getICveMdoTrans()).toString()) == 0)
                               out.print("<option value=\"" + VGRLMdoTrans.getICveMdoTrans() + "\" selected>" + VGRLMdoTrans.getCDscMdoTrans() + "</option>");
                             else
                               out.print("<option value=\"" + VGRLMdoTrans.getICveMdoTrans() + "\" >" + VGRLMdoTrans.getCDscMdoTrans() + "</option>");
                           } else
                             out.print("<option value=\"" + VGRLMdoTrans.getICveMdoTrans() + "\" >" + VGRLMdoTrans.getCDscMdoTrans() + "</option>");
                        }
                    }
                    out.print("</select>");
                    out.print("</td>");
                    out.print("</tr>");
                  %>
            </table>
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <%
                 float fDiferencia = 0;
                 String cPersona = "";
                 if (bs != null){
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"7\">Operadores</td>");
                     out.println("</tr>");
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\">Fecha</td>");
                     out.println("<td class=\"ETablaT\">RFC</td>");
                     out.println("<td class=\"ETablaT\">Nombre</td>");
                     out.println("<td class=\"ETablaT\">Licencia</td>");
                     out.println("<td class=\"ETablaT\">Vencimiento</td>");
                     out.println("<td class=\"ETablaT\">Plantilla</td>");
                     out.println("<td class=\"ETablaT\">Empresa</td>");
                     out.println("</tr>");
                     bs.start();
                     while(bs.nextRow()){
                         out.println("<tr>");
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtLicVencimiento", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cRFC", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNombre", "&nbsp;") + " " +
                                                           bs.getFieldValue("cApPaterno", "&nbsp;") + " " +
                                                           bs.getFieldValue("cApMaterno", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cLicencia", "&nbsp;")));
                         out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("dtLicVencimiento", "&nbsp;")));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCvePlantilla", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEmpresa", "&nbsp;")));
                         out.println("</tr>");
                     }
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"7\">Envio de Información a Excel</td>");
                     out.println("</tr>");
                     cSQL = clsConfig.cValSQL;
                     request.getSession(true).setAttribute("cRepSQL",cSQL);
                     out.print("<td class=\"ECampoC\" colspan=\"7\">");
                     out.print(vEti.clsAnclaTexto("EAncla","Enviar a Excel","JavaScript:fEnviarExcel('pgReporteBase.jsp','1','Consulta de Operadores','Clave de la Empresa|No. Plantilla|No. Personal|No. Expediente|Nombre|Apellido Paterno|Apellido Materno|RFC|CURP|Fecha de Nacimiento|Pais Nac.|Estado Nac.|Modo de Transporte|Clave del Puesto|Licencia|Calle|No. Exterior|No. Interior|Colonia|CP|Ciudad|Pais|Estado|Municipio|Telefono|Razon Social|Pais|Estado|Modo de Transporte|Puesto|País|Estado|Municipio|Fecha de Recepción|Fecha de Vencimiento|','');",""));
                     out.print("</td>");
                     out.println("</tr>");
                 } else {
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"11\">Mensaje</td>");
                     out.println("</tr>");
                     out.println("<tr>");
                     out.println("<td class=\"ECampo\">No Existen datos con el Filtro Proporcionado</td>");
                     out.println("</tr>");
                 }

              %>
            </table>
      </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>


