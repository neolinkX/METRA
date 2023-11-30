<%/**
 * Title: Listado de Consulta de Calibradores
 * Description: Listado de Consulta de Calibradores
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Juan Manuel Vazquez
 * @version 1
 * Clase: pg070306020CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<html>
<%
  pg070303090CFG  clsConfig = new pg070303090CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303090.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303090.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070303090.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Lote|";    // modificar
  String cCveOrdenar  = "iCveLoteCualita|";  // modificar
  String cDscFiltrar  = "Lote|";    // modificar
  String cCveFiltrar  = "iCveLoteCualita|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cAnio             = "";
  String cCveLoteCualita   = "";
  String cCveExamCualita   = "";
  String cCveEquipo        = "";
  String cCveLaboratorio   = "";

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

  function fIrCatalogo(cAnio, cCveLoteCualita, cCveExamCualita, cCveEquipo, cCveLaboratorio){
    form = document.forms[0];

    form.hdAnio.value = cAnio;
    form.hdCveLoteCualita.value = cCveLoteCualita;
    form.hdCveExamCualita.value = cCveExamCualita;
    form.hdCveEquipo.value      = cCveEquipo;
    form.hdCveLaboratorio.value = cCveLaboratorio;

    form.hdRowNum.value = cAnio;

    form.hdBoton.value = 'Guardar'; //'Actual
    form.target = 'FRMDatos';
    form.action = 'pg070303090.jsp';
    form.submit();
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

       cAnio             = ""+bs.getFieldValue("iAnio", "");
       cCveLoteCualita   = ""+bs.getFieldValue("iCveLoteCualita", "");
       cCveExamCualita   = ""+bs.getFieldValue("iCveExamCualita", "");
       cCveEquipo        = ""+bs.getFieldValue("iCveEquipo", "");
       cCveLaboratorio   = ""+bs.getFieldValue("iCveLaboratorio", "");
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdAnio" value="<%=cAnio%>">
  <input type="hidden" name="hdCveLoteCualita" value="<%=cCveLoteCualita%>">
  <input type="hidden" name="hdCveExamCualita" value="<%=cCveExamCualita%>">
  <input type="hidden" name="hdCveEquipo" value="<%=cCveEquipo%>">
  <input type="hidden" name="hdCveLaboratorio" value="<%=cCveLaboratorio%>">

  <input type="hidden" name="hdCPropiedad" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaT" colspan="8">Filtrar</td>
                            </tr>
                            <tr>
                              <td class="ETabla">Año:</td>
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
                              <td class="ETabla">Laboratorio:</td>
                              <td class="ETabla">
                                  <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0"));
                                  %>
                              </td>
                            </tr>
                          </table>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaT" colspan="4">Consultar Muestras del Análisis Presuntivo</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Año</td>
                              <td class="ETablaT">No. Lote</td>
                              <td class="ETablaT">No. Examen</td>
                              <td class="ETablaT">Selección</td>
                            </tr>
                                <%
                               if (bs != null){
                                   bs.start();
                                   int iCve = 0;
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iAnio", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveLoteCualita", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveExamCualita", "&nbsp;")));
                                       out.println("<td>");
                                       out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\">");
                                       out.println("<tr>");
                                       out.println("<td>");
                                       out.print(vEti.clsAnclaTexto("EAncla","Generar","JavaScript:fIrCatalogo('"+ bs.getFieldValue("iAnio","") + "','" +
                                                                                                                   bs.getFieldValue("iCveLoteCualita","") + "','" +
                                                                                                                   bs.getFieldValue("iCveExamCualita","") + "','" +
                                                                                                                   bs.getFieldValue("iCveEquipo","") + "','" +
                                                                                                                   bs.getFieldValue("iCveLaboratorio","") + "');", "Lote"));
                                       out.println("</td>");
                                       out.println("</tr>");
                                       out.println("</table>");
                                       out.println("</td>");
                                   }
                               }
                               else{
                                  out.println("<tr>");
                                  out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 3,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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