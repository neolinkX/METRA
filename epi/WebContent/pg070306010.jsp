<%/**
 * Title: Listado de Reactivos
 * Description: Listado de Reactivos
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Jaime Enrique Suárez Romero
 * @version 1
 * Clase: pg070306010CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070306010CFG  clsConfig = new pg070306010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070306010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070306011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Código|Descripción|";    // modificar
  String cCveOrdenar  = "iCodigo|cDscBreve|";  // modificar
  String cDscFiltrar  = "Código|Descripción|";    // modificar
  String cCveFiltrar  = "iCodigo|cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";
  DecimalFormat nf = new DecimalFormat("#,##0.0000"); // 4 decimales

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
  String cClaveA    = "";
  String cClaveB    = "";
  String cPosicion = "";
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
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
  <input type="hidden" name="hdCampoClave1" value="<%=cClaveA%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClaveB%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdPagina" value="<%=vEntorno.getNombrePagina()%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
      <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="8">Búsqueda del Lote</td>
            </tr>
            <tr>
              <td class="EEtiqueta">Año:</td>
              <td class="ETabla">
                 <select name="iAnio" size="1" onChange="">
                    <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                             out.print("<option value = " + i + " selected>" + i + "</option>");
                          else
                             out.print("<option value = " + i + ">" + i + "</option>");
                       }
                    %>
                 </select>
              </td>
              <td class="EEtiqueta ">Laboratorio:</td>
              <td class="ETabla">
                  <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                  %>
              </td>
            </tr>
          </table>

           &nbsp;

          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="6">Reactivos</td>
            </tr>
            <tr>
              <td class="ETablaT">&nbsp;Código&nbsp;</td>
              <td class="ETablaT">&nbsp;Descripción Breve&nbsp;</td>
              <td class="ETablaT">&nbsp;Tipo&nbsp;</td>
              <td class="ETablaT">&nbsp;Fecha de Preparación&nbsp;</td>
              <td class="ETablaT" colspan="1">&nbsp;Volumen Total&nbsp;</td>
              <td class="ETablaT" colspan="1">&nbsp;</td>
            </tr>
            <%
              if (bs != null) {
                  bs.start();
                  while (bs.nextRow()) {
                    out.println("<tr>");
                    out.print(vEti.Texto("ETabla","" + bs.getFieldValue("iCodigo", "&nbsp;")));
                    out.print(vEti.Texto("ETabla","" + bs.getFieldValue("cDscBreve", "&nbsp;")));
                    out.print(vEti.Texto("ETabla","" + bs.getFieldValue("cDscTpoReact", "&nbsp;")));
                    if (bs.getFieldValue("dtCaducidad","")==null || bs.getFieldValue("dtCaducidad","").equals("null")) {
                       fechaFormateada = "&nbsp;";
                    }
                    else {
                       d = tf.getSQLDatefromSQLString(bs.getFieldValue("dtPreparacion","").toString());
                       fechaFormateada = sdf.format(d);
                    }
                    out.print(vEti.Texto("ETablaC", fechaFormateada));
                    out.print(vEti.Texto("ETablaR", nf.format(Double.parseDouble(bs.getFieldValue("dVolumen", "&nbsp;").toString()))));
                    if (clsConfig.getLPagina(cCatalogo)) {
                      out.print("<td class=\"ETablaC\">");
                      out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveLaboratorio","") + "','" + bs.getFieldValue("iCveReactivo","") + "'," + "'pg070306011.jsp');", "Ir a..."));
                      out.print("</td>");
                    }
                    out.print("</tr>");
                  }
                }
                else{
                   out.println("<tr>");
                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 5,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                   out.println("</tr>");
                }
            %>
          </table>
      </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%//vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>