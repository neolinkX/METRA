<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Suárez Romero
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.msc.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.util.caching.*" %>

<html>
<%
  pg070307020CFG  clsConfig = new pg070307020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070307020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070307020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";            // modificar

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
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";

  JXLSBean jxlsBean = clsConfig.getJXLSBean();
  boolean lTempRefrigeradores = clsConfig.getLTempRefrigeradores();
  boolean lTemperaturaAmbiente = clsConfig.getLTemperaturaAmbiente();
  boolean lHumedad = clsConfig.getLHumedad();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function find(tipo) {
       form = document.forms[0];
       form.target = "_self";

       if (tipo == 1) {
            form.hdBuscarTemperaturas.value = 'BuscarRefrigeradores';
       } else if (tipo == 2) {
            form.hdBuscarTemperaturas.value = 'BuscarTemperaturaAmbiente';
       } else if (tipo == 3) {
            form.hdBuscarTemperaturas.value = 'BuscarHumedad';
       }

       alert("La información tardará en presentarse algunos minutos");
       form.submit();
  }

  // Con esta funcion se obtiene el valor del Checkbox
  function getCheckValue(objeto) {
    return objeto.checked ? objeto.value : 0;
  }

  // Con esta funcion se obtiene el valor del Radio Button
  function getRadioValue(objeto) {
    var iRadio = 0 ;

    for(i=0; i < objeto.length; i++ ) {
      if (objeto[i].checked) {
        iRadio = parseInt(objeto[i].value);
      }
    }

    return iRadio;
  }

  // Con esta funcion se obtiene el valor del Combo
  function getComboValue(objeto) {
    return objeto[objeto.selectedIndex].value;
  }

  // Abrir nueva ventana para generar el reporte de Excel
  function reporte(tipo) {
       form = document.forms[0];
       form.target = "_self";

       if (tipo == 1) {
            form.hdReporte.value = 'Reporte';
            form.hdBoton.value = 'Reporte';
            cPagina= "<html><head><title>Reporte Excel</title></head>" +
            "  <b>INSTRUCCIONES </b>" +
            " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
            " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
            " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
            " <body>"+
            "<form method=\"post\" action=\"servXLSpg070307020?iCveArea=" + getComboValue(form.iCveArea) +
                                           "&iAnio=" + getComboValue(form.iAnio) +
                                           "&iMeses=" + getComboValue(form.iMeses) +
                                           "&iCveEquipo=" + getComboValue(form.iCveEquipo) +
                                           "&hdReporte=" + form.hdReporte.value +
                                           "&hdBuscarTemperaturas=" + form.hdBuscarTemperaturas.value +
                                           "\"" +
            " enctype=\"multipart/form-data\">" +
            " <input type=\"submit\" value=\"Generar Excel\"> " +
            " </form>" +
            " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
            " </body>" +
            "</html>";
            wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
            wExp.document.write(cPagina);
       } else if (tipo == 2) {
            form.hdReporte.value = 'ReporteTemperaturaAmbiente';
            form.hdBoton.value = 'ReporteTemperaturaAmbiente';
            cPagina= "<html><head><title>Reporte Excel</title></head>" +
            "  <b>INSTRUCCIONES </b>" +
            " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
            " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
            " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
            " <body>"+
            "<form method=\"post\" action=\"servXLSpg070307020?iCveArea=" + getComboValue(form.iCveArea) +
                                           "&iAnio=" + getComboValue(form.iAnio) +
                                           "&iMeses=" + getComboValue(form.iMeses) +
                                           "&iCveEquipo=" + getComboValue(form.iCveEquipo) +
                                           "&hdReporte=" + form.hdReporte.value +
                                           "&hdBuscarTemperaturas=" + form.hdBuscarTemperaturas.value +
                                           "\"" +
            " enctype=\"multipart/form-data\">" +
            " <input type=\"submit\" value=\"Generar Excel\"> " +
            " </form>" +
            " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
            " </body>" +
            "</html>";
            wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
            wExp.document.write(cPagina);
       } else if (tipo == 3) {
            form.hdReporte.value = 'ReporteHumedad';
            form.hdBoton.value = 'ReporteHumedad';
            cPagina= "<html><head><title>Reporte Excel</title></head>" +
            "  <b>INSTRUCCIONES </b>" +
            " <br> 1.Presione el Botón <b>Generar Excel</b> para obtener el reporte. " +
            " <br> 2.Espere que se presente la ventana <b>Descarga de Archivos</b>." +
            " <br> 3.Presione el botón Abrir y aguarde un momento para que el archivo sea presentado.  " +
            " <body>"+
            "<form method=\"post\" action=\"servXLSpg070307020?iCveArea=" + getComboValue(form.iCveArea) +
                                           "&iAnio=" + getComboValue(form.iAnio) +
                                           "&iMeses=" + getComboValue(form.iMeses) +
                                           "&iCveEquipo=" + getComboValue(form.iCveEquipo) +
                                           "&hdReporte=" + form.hdReporte.value +
                                           "&hdBuscarTemperaturas=" + form.hdBuscarTemperaturas.value +
                                           "\"" +
            " enctype=\"multipart/form-data\">" +
            " <input type=\"submit\" value=\"Generar Excel\"> " +
            " </form>" +
            " <br> Favor de esperar a que se presente el archivo de Excel ...  " +
            " </body>" +
            "</html>";
            wExp = window.open("", "", "width=600,height=200,status=no,resizable=yes,menubar=yes,titlebar=yes,top=200,left=200,screenY=200,screenX=200,scrollbars=yes");
            wExp.document.write(cPagina);
       }

       //form.submit();
  }

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
//    fTipo();
  }
</SCRIPT>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdReporte">
  <input type="hidden" name="hdBuscarTemperaturas">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
      /*
       if("Reporte".compareToIgnoreCase(clsConfig.getAccion()) == 0) {
           out.println(clsConfig.getActiveX());
       } else if ("ReporteTemperaturaAmbiente".compareToIgnoreCase(clsConfig.getAccion()) == 0) {
            out.println(clsConfig.getActiveX());
       } else if ("ReporteHumedad".compareToIgnoreCase(clsConfig.getAccion()) == 0) {
            out.println(clsConfig.getActiveX());
       }
       */
  %>
  <tr><td align="center" width="100%">
  </td></tr>
  <tr><td valign="top">
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <tr>
                <td colspan="6" class="ETablaT">Seleccione las Características del Reporte
                </td>
              </tr>
              <tr>
                <%
                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                Vector vcGRLUniMed = vUsuario.getVUniFiltro(Integer.parseInt(vParametros.getPropEspecifica("EPIProceso")));

                TDTOXArea dArea = new TDTOXArea();
                out.print(vEti.Texto("EEtiqueta", "Refrigeradores del Área de:"));
                out.print("<td>");
                out.print(vEti.SelectOneRowSinTD("iCveArea", "", dArea.FindByAll(), "iCveArea", "cDscArea", request, "0"));
                out.print("</td>");

                %>
              </tr>
              <tr>
              </tr>
              <tr>
                <%
                int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
                TFechas dtFecha = new TFechas();
                int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
                Vector vcAnios = new Vector();
                int i;
                for(i = 0; i < iAniosAtras; i++){
                   vcAnios.add(""+iAnio+"|"+iAnio);
                   iAnio--;
                }
                out.print(vEti.Texto("EEtiqueta","Año:"));
                out.print(vEti.SelectOneRow("ECampo","iAnio","",vcAnios,request,""+iAnio));
                %>
              </tr>
              <tr>
                <%
                StringTokenizer stMeses = new StringTokenizer(vParametros.getPropEspecifica("NombresMes"),",");
                Vector vcMeses = new Vector();
                i = 1;
                while(stMeses.hasMoreTokens()){
                  vcMeses.add(""+i+"|"+stMeses.nextToken());
                  i++;
                }
                out.print(vEti.Texto("EEtiqueta","Mes:"));
                out.print(vEti.SelectOneRow("ECampo","iMeses","",vcMeses,request,""));
                %>
              </tr>
              <tr>
                <%//out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",10,10,"dtFecha","",0,"","",true,true,true,request));%>
              </tr>
              <tr>
                <%
                Vector vcTipo = new Vector();
                //vcTipo.add("1|Por Año");
                vcTipo.add("2|Por Año y Mes");
                //vcTipo.add("3|Por Fecha Exacta");
                out.print(vEti.Texto("EEtiqueta","Tipo de Consulta"));
                out.print(vEti.SelectOneRow("ECampo","iTipo","fTipo();",vcTipo,request,""));
                %>
              </tr>
              <!-- Agregado por Rafael Alcocer Caldera -->
              <!-- *********************************** -->
              <tr>
                   <%
                     TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
                     Vector vcEquipo = dEQMEquipo.FindByAll(" where cDscEquipo like '%REFRIGERADOR%' or cDscEquipo like '%CONGELADOR%' order by cCveEquipo");
                     out.print(vEti.Texto("EEtiqueta","Equipo"));
                     out.print(vEti.SelectOneRow("ECampo","iCveEquipo","",vcEquipo,"iCveEquipo","cCveEquipo",request,""));
                   %>
              </tr>
              <tr><td colspan="6" align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla","Buscar","JavaScript:{fBuscar();}", "Buscar...",""));
                %>
              </td></tr>
              <!-- Agregado por Rafael Alcocer Caldera -->
              <!-- *********************************** -->
              <!--     REFRIGERADORES Y CONGELADORES   -->
              <!-- *********************************** -->
              <tr><td align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla", "Refrigeradores y Congeladores", "JavaScript:{find(1);}", "Buscar...", "BuscarRefrigeradores"));
                %>
              </td>
              <!-- Agregado por Rafael Alcocer Caldera -->
              <!-- *********************************** -->
              <td  colspan="6" align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla", "Reporte", "JavaScript:reporte(1);", "Reporte...", "Reporte"));
                %>
              </td>
              </tr>
              <!-- Agregado por Rafael Alcocer Caldera -->
              <!-- *********************************** -->
              <!--         TEMPERATURA AMBIENTE        -->
              <!-- *********************************** -->
              <tr><td align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla", "Temperatura Ambiente", "JavaScript:{find(2);}", "Buscar...", "BuscarTemperaturaAmbiente"));
                %>
              </td>
              <!-- Agregado por Rafael Alcocer Caldera -->
              <!-- *********************************** -->
              <td  colspan="6" align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla", "Reporte", "JavaScript:reporte(2);", "Reporte...", "ReporteTemperaturaAmbiente"));
                %>
              </td>
              </tr>
              <!-- Agregado por Rafael Alcocer Caldera -->
              <!-- *********************************** -->
              <!--               HUMEDAD               -->
              <!-- *********************************** -->
              <tr><td align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla", "Humedad Relativa", "JavaScript:{find(3);}", "Buscar...", "BuscarHumedad"));
                %>
              </td>
              <!-- Agregado por Rafael Alcocer Caldera -->
              <!-- *********************************** -->
              <td  colspan="6" align="center">
                <%
                out.print(vEti.clsAnclaTexto("EAncla", "Reporte", "JavaScript:reporte(3);", "Reporte...", "ReporteHumedad"));
                %>
              </td>
              </tr>
            </table>
  </td></tr>
  <!-- Agregado por Rafael Alcocer Caldera -->
  <!-- *********************************** -->
  <!--    REFRIGERADORES Y CONGELADORES    -->
  <!-- *********************************** -->
  <tr>
    <td valign="top">
      <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
        <%
          if (jxlsBean != null && lTempRefrigeradores == true) {
        %>
          <tr>
            <td class="ETablaT">Equipo (Temp.)</td>
            <td class="ETablaT">Hora de lectura</td>
            <td class="ETablaT">1</td>
            <td class="ETablaT">2</td>
            <td class="ETablaT">3</td>
            <td class="ETablaT">4</td>
            <td class="ETablaT">5</td>
            <td class="ETablaT">6</td>
            <td class="ETablaT">7</td>
            <td class="ETablaT">8</td>
            <td class="ETablaT">9</td>
            <td class="ETablaT">10</td>
            <td class="ETablaT">11</td>
            <td class="ETablaT">12</td>
            <td class="ETablaT">13</td>
            <td class="ETablaT">14</td>
            <td class="ETablaT">15</td>
            <td class="ETablaT">16</td>
            <td class="ETablaT">17</td>
            <td class="ETablaT">18</td>
            <td class="ETablaT">19</td>
            <td class="ETablaT">20</td>
            <td class="ETablaT">21</td>
            <td class="ETablaT">22</td>
            <td class="ETablaT">23</td>
            <td class="ETablaT">24</td>
            <td class="ETablaT">25</td>
            <td class="ETablaT">26</td>
            <td class="ETablaT">27</td>
            <td class="ETablaT">28</td>
            <td class="ETablaT">29</td>
            <td class="ETablaT">30</td>
            <td class="ETablaT">31</td>
          </tr>
        <%
              for (int x = 0; x < jxlsBean.getList().size(); x++) {
                   pg070307020Bean bean = (pg070307020Bean)jxlsBean.getList().get(x);
                   out.println("<tr>");
                   out.print(vEti.Texto("ETabla", "" + bean.getCcveequipo()));
                   out.print(vEti.Texto("ETabla", "" + bean.getCdscbreve()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura1()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura2()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura3()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura4()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura5()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura6()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura7()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura8()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura9()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura10()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura11()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura12()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura13()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura14()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura15()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura16()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura17()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura18()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura19()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura20()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura21()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura22()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura23()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura24()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura25()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura26()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura27()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura28()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura29()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura30()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura31()));
                   out.println("</tr>");
              }
          /* ***********************************
           *        TEMPERATURA AMBIENTE
           * ***********************************
           */
          } else if (jxlsBean != null && lTemperaturaAmbiente == true) {
          %>
          <tr>
            <td class="ETablaT">Area (Temp. Amb.)</td>
            <td class="ETablaT">Hora de lectura</td>
            <td class="ETablaT">1</td>
            <td class="ETablaT">2</td>
            <td class="ETablaT">3</td>
            <td class="ETablaT">4</td>
            <td class="ETablaT">5</td>
            <td class="ETablaT">6</td>
            <td class="ETablaT">7</td>
            <td class="ETablaT">8</td>
            <td class="ETablaT">9</td>
            <td class="ETablaT">10</td>
            <td class="ETablaT">11</td>
            <td class="ETablaT">12</td>
            <td class="ETablaT">13</td>
            <td class="ETablaT">14</td>
            <td class="ETablaT">15</td>
            <td class="ETablaT">16</td>
            <td class="ETablaT">17</td>
            <td class="ETablaT">18</td>
            <td class="ETablaT">19</td>
            <td class="ETablaT">20</td>
            <td class="ETablaT">21</td>
            <td class="ETablaT">22</td>
            <td class="ETablaT">23</td>
            <td class="ETablaT">24</td>
            <td class="ETablaT">25</td>
            <td class="ETablaT">26</td>
            <td class="ETablaT">27</td>
            <td class="ETablaT">28</td>
            <td class="ETablaT">29</td>
            <td class="ETablaT">30</td>
            <td class="ETablaT">31</td>
          </tr>
        <%
              for (int x = 0; x < jxlsBean.getList().size(); x++) {
                   pg070307020Bean bean = (pg070307020Bean)jxlsBean.getList().get(x);
                   out.println("<tr>");
                   out.print(vEti.Texto("ETabla", "" + bean.getCdscArea()));
                   out.print(vEti.Texto("ETabla", "" + bean.getCdscbreve()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura1()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura2()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura3()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura4()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura5()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura6()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura7()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura8()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura9()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura10()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura11()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura12()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura13()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura14()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura15()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura16()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura17()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura18()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura19()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura20()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura21()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura22()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura23()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura24()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura25()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura26()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura27()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura28()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura29()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura30()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura31()));
                   out.println("</tr>");
              }
          /* ***********************************
           *              HUMEDAD
           * ***********************************
           */
          } else if (jxlsBean != null && lHumedad == true) {
          %>
          <tr>
            <td class="ETablaT">Area (Hum.)</td>
            <td class="ETablaT">Hora de lectura</td>
            <td class="ETablaT">1</td>
            <td class="ETablaT">2</td>
            <td class="ETablaT">3</td>
            <td class="ETablaT">4</td>
            <td class="ETablaT">5</td>
            <td class="ETablaT">6</td>
            <td class="ETablaT">7</td>
            <td class="ETablaT">8</td>
            <td class="ETablaT">9</td>
            <td class="ETablaT">10</td>
            <td class="ETablaT">11</td>
            <td class="ETablaT">12</td>
            <td class="ETablaT">13</td>
            <td class="ETablaT">14</td>
            <td class="ETablaT">15</td>
            <td class="ETablaT">16</td>
            <td class="ETablaT">17</td>
            <td class="ETablaT">18</td>
            <td class="ETablaT">19</td>
            <td class="ETablaT">20</td>
            <td class="ETablaT">21</td>
            <td class="ETablaT">22</td>
            <td class="ETablaT">23</td>
            <td class="ETablaT">24</td>
            <td class="ETablaT">25</td>
            <td class="ETablaT">26</td>
            <td class="ETablaT">27</td>
            <td class="ETablaT">28</td>
            <td class="ETablaT">29</td>
            <td class="ETablaT">30</td>
            <td class="ETablaT">31</td>
          </tr>
        <%
              for (int x = 0; x < jxlsBean.getList().size(); x++) {
                   pg070307020Bean bean = (pg070307020Bean)jxlsBean.getList().get(x);
                   out.println("<tr>");
                   out.print(vEti.Texto("ETabla", "" + bean.getCdscArea()));
                   out.print(vEti.Texto("ETabla", "" + bean.getCdscbreve()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura1()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura2()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura3()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura4()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura5()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura6()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura7()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura8()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura9()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura10()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura11()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura12()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura13()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura14()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura15()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura16()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura17()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura18()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura19()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura20()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura21()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura22()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura23()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura24()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura25()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura26()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura27()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura28()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura29()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura30()));
                   out.print(vEti.Texto("ETabla", "" + bean.getDtemperatura31()));
                   out.println("</tr>");
              }
          }
        %>
      </table>
    </td>
  </tr>
  <!-- *********************************** -->
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
