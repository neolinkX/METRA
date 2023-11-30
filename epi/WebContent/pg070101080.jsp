<%/**
 * Title: Listado de Perfil Médico Científico
 * Description: Listado de Perfil Médico Científico
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070101080
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070101080CFG  clsConfig = new pg070101080CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101080.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101080.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101081.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Grupo|Inicio vigencia|Fin vigencia|Vigente|";    // modificar
  String cCveOrdenar  = "cDscGrupo|dtInicio|dtFin|lVigente|";  // modificar
  String cDscFiltrar  = "Inicio vigencia|Fin vigencia|";    // modificar
  String cCveFiltrar  = "p.dtInicio|p.dtFin|";  // modificar
  String cTipoFiltrar = "5|5|";                // modificar 7/8
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar
  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";
  String fechaTmp = "";

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
     } else {
       //cPosicion = request.getParameter("hdRowNum");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCvePerfil" value="">


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                            %>
                            <tr>
                              <td class="ETablaTR">&nbsp;Modo de transporte:</td>
                              <td class="ETablaT" colspan="8">
                              <%int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                               out.print(vEti.SelectOneRowSinTD("iCveMdoTrans", "fCambiaMdo('Actual');", new TDGRLMdoTrans().findByAll("where 1=1"), "iCveMdoTrans","cDscMdoTrans", request, "0"));
                               %>
                            </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">&nbsp;Grupo&nbsp;</td>
                              <td class="ETablaT">&nbsp;Inicio Vigencia&nbsp;</td>
                              <td class="ETablaT">&nbsp;Fin Vigencia&nbsp;</td>
                              <td class="ETablaT" colspan="6">&nbsp;Vigente&nbsp;</td>
                            </tr>
                            <%
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscGrupo", "&nbsp;")));
  fechaTmp = bs.getFieldValue("dtInicio","-").toString();
if( !fechaTmp.equals("null") ) {
  d = tf.getSQLDatefromSQLString(fechaTmp);
  fechaFormateada = sdf.format(d);
} else {
  fechaFormateada = "&nbsp;";
}
                                       out.print(vEti.Texto("ETabla", fechaFormateada));
  fechaTmp = bs.getFieldValue("dtFin","-").toString();
//System.out.println("fechaTmp: " + fechaTmp);
if( !fechaTmp.equals("null") ) {
  d = tf.getSQLDatefromSQLString(fechaTmp);
  fechaFormateada = sdf.format(d);
} else {
  fechaFormateada = "&nbsp;";
}
                                       out.print(vEti.Texto("ETabla", fechaFormateada));
//                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("lVigente", "&nbsp;")));
//out.print(vEti.ToggleMovCS("ECampo", 1, "lVigente", ""+bs.getFieldValue("lVigente", "&nbsp;"), "", 0, true, "1", "0", true));
                                       out.print(vEti.TextoCS("ECampo",bs.getFieldValue("lVigente").toString().equals("1")?"SI":"NO",1));

//                                       out.println("<td class=\"ETabla\"><input type=\"checkbox\"/></td>");

                                       if (clsConfig.getLPagina(cCatalogo)){

                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Catálogo",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101081','pg070101081.jsp');","Catálogo"));
                                           out.print("</td>");

                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Detalle",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101082','pg070101082.jsp');","Detalle"));
                                           out.print("</td>");

                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Alerta",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101083','pg070101083.jsp');","Alerta"));
                                           out.print("</td>");

                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Evaluación",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101084','pg070101084.jsp');","Evaluación"));
                                           out.print("</td>");

/*                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Especialidades",
                                                                        "javascript:fIr('" +
                                                                        bs.getFieldValue("iCvePerfil","") + "'," +
                                                                        "'pg070101085','pg070101085.jsp');","Especialidades"));
                                           out.print("</td>");
*/                                       }
                                   }
                               } else {
                                   out.println("<tr>");
                                   out.println("<td class=\"EEtiqueta\">Mensaje:</td>");
                                   out.println("<td class=\"ECampo\" colspan=\"8\">No existen datos coincidentes con el filtro proporcionado</td>");
                                   //out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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