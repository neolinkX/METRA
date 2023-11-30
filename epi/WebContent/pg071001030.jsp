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
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>

<html>
<%
  pg071001030CFG  clsConfig = new pg071001030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071001030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071001030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg071001031.jsp";
  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
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
  String cClave    = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPerM.js"%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg071001030.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\SelPerM.js"></SCRIPT>-->
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
  <input type="hidden" name="hdCampoClave" value="">
  <input type="hidden" name="hdiCveUniMed" value="">
  <input type="hidden" name="hdiCveProceso" value="">
  <input type="hidden" name="hdiCveModulo" value="">
  <input type="hidden" name="hdiCveServicio" value="">
  <input type="hidden" name="hdiCveRama" value="">
  <input type="hidden" name="hdiCveUsuario" value ="<%=request.getParameter("hdiCveUsuario")%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="3" class="ETablaT">Usuario, Unidad Médica, Proceso, Módulo, Servicio, Rama</td>
                            </tr>
                            <%
                               String cCveUsuario="", cDscUniMed="", cDscProceso="", cDscModulo="", cDscServicio="";

                               out.println("<td colspan='4' align='center'>");
                               out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Medico","javascript:fSelPerM();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                               out.println("</td>");
                               out.println("</tr>");
                               String where="";

                               if (request.getParameter("hdiCveUsuario") != null && request.getParameter("hdiCveUsuario").compareTo("") != 0  ) {      
                             
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                       if(!cCveUsuario.equals(bs.getFieldValue("iCveUsuario",""))){
                                          cCveUsuario = ""+bs.getFieldValue("iCveUsuario","");
                                          out.print("<tr>");
                                          out.print(vEti.Texto("EEtiqueta","Usuario:"));
                                          out.print(vEti.Texto("EEtiqueta",""+ bs.getFieldValue("cNombreCompleto", "&nbsp;")));
                                          out.print(vEti.Texto("EEtiqueta",""+ bs.getFieldValue("iCveUsuario", "&nbsp;")));
                                          out.print("</tr>");
                                       }
                                       if(!cDscUniMed.equals(bs.getFieldValue("cDscUniMed",""))){
                                          cDscUniMed = ""+bs.getFieldValue("cDscUniMed", "");
                                          out.print("<tr>");
                                          out.print(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                          out.print(vEti.TextoCS("ETabla",""+ bs.getFieldValue("cDscUniMed", "&nbsp;"),2));
                                          out.print("</tr>");
                                       }
                                       if(!cDscProceso.equals(bs.getFieldValue("cDscProceso",""))){
                                          cDscProceso = ""+bs.getFieldValue("cDscProceso", "");
                                          out.print("<tr>");
                                          out.print(vEti.Texto("EEtiqueta","Proceso:"));
                                          out.print(vEti.TextoCS("ETabla",""+ bs.getFieldValue("cDscProceso", "&nbsp;"),3));
                                          out.print("</tr>");
                                       }
                                       if(!cDscModulo.equals(bs.getFieldValue("cDscModulo",""))){
                                          cDscModulo = ""+bs.getFieldValue("cDscModulo", "");
                                          out.print("<tr>");
                                          out.print(vEti.Texto("EEtiqueta","Módulo:"));
                                          out.print(vEti.TextoCS("ETabla",""+ bs.getFieldValue("cDscModulo", "&nbsp;"),3));
                                          out.print("</tr>");
                                       }
                                       if(!cDscServicio.equals(bs.getFieldValue("cDscServicio",""))){
                                          cDscServicio = ""+bs.getFieldValue("cDscServicio", "");
                                          out.print("<tr>");
                                          out.print(vEti.Texto("EEtiqueta","Servicio:"));
                                          out.print(vEti.TextoCS("ETabla",""+ bs.getFieldValue("cDscServicio", "&nbsp;"),3));
                                          out.print("</tr>");
                                       }

                                       out.print("<tr>" + vEti.TextoCS("ETablaR",""+ bs.getFieldValue("cDscRama", "&nbsp;"),2));
                                       if (clsConfig.getLPagina(cCatalogo)){
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIr("+bs.getFieldValue("iCveUsuario", "")+','+bs.getFieldValue("iCveUniMed", "")+','+bs.getFieldValue("iCveProceso", "")+','+bs.getFieldValue("iCveModulo", "")+','+bs.getFieldValue("iCveServicio", "")+','+bs.getFieldValue("iCveRama", "")+");","Detalle","Detalle"));
                                           out.print("</td>");
                                       }
                                       out.print("<tr>");
                                   }
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
