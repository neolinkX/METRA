<%/**
 * Title: Consulta de Servicios por Equipo
 * Description: Consulta de Servicios por Equipo
 * Copyright: Micros
 * Company: Micros
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070602021CFG
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>

<html>
<%
  pg070602021CFG  clsConfig = new pg070602021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070602021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070602021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070603021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave del Servicio|Descripción|";    // modificar
  String cCveOrdenar  = "MedServicio.iCveServicio|MedServicio.cDscServicio|";  // modificar
  String cDscFiltrar  = "Clave del Servicio|Descripción|";    // modificar
  String cCveFiltrar  = "MedServicio.iCveServicio|MedServicio.cDscServicio|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  if (bs == null)
     cNavStatus = "Disabled";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  function fValidaTodo(){
    var form = document.forms[0];
    if (form.hdBoton.value == "Guardar"){
      if (!confirm(" ¿ Desea Guardar los Cambios ? "))
        return false;
    }
    return true;
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
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdCampoClave") %>">
  <input type="hidden" name="iCveAsignacion" value="<%=request.getParameter("iCveAsignacion") %>">
  <input type="hidden" name="hdRiPag" value="S">
  <input type="hidden" name="RSTMostrar" value="1">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed") %>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo") %>">
  <input type="hidden" name="iCveArea" value="<%=request.getParameter("iCveArea") %>">
  <input type="hidden" name="cDscUniMed" value="<%=request.getParameter("cDscUniMed") %>">
  <input type="hidden" name="cDscModulo" value="<%=request.getParameter("cDscModulo") %>">
  <input type="hidden" name="cDscArea" value="<%=request.getParameter("cDscArea") %>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="7" class="ETablaT">Equipo
<%                                Vector vEq = clsConfig.vEquipo;
                                  if (vEq.size() > 0) {
                                      TVEQMEquipo tvEquipo = (TVEQMEquipo) vEq.get(0);
                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Clasificación:"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscBreveClasif(),2));
                                      out.println(vEti.Texto("EEtiqueta", "Tipo:"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscBreveTpoEquipo(),3));
                                      out.println("</tr><tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Equipo:"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscEquipo(),2));
                                      out.println(vEti.Texto("EEtiqueta", "Marca:"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCDscBreveMarca(),3));
                                      out.println("</tr><tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Modelo:"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCModelo(),2));
                                      out.println(vEti.Texto("EEtiqueta", "Serie:"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCNumSerie(),3));
                                      out.println("</tr><tr>");
                                      out.println(vEti.Texto("EEtiqueta", "Inventario:"));
                                      out.println(vEti.TextoCS("ECampo", tvEquipo.getCInventario(),6));
                                      out.println("</tr>");
                                  }
                                  out.println("<tr>");
                                  if(request.getParameter("cDscUniMed")!=null && request.getParameter("cDscUniMed").compareTo("null")!=0){
                                    out.println(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                                    out.println(vEti.Texto("ECampo", request.getParameter("cDscUniMed") + "&nbsp;"));
                                    out.println(vEti.Texto("EEtiqueta", "Módulo:"));
                                    out.println(vEti.Texto("ECampo", request.getParameter("cDscModulo") + "&nbsp;"));
                                    out.println(vEti.Texto("EEtiqueta", "Área:"));
                                    out.println(vEti.Texto("ECampo", request.getParameter("cDscArea") + "&nbsp;"));
                                  }
                                  else{
                                    out.println(vEti.Texto("EEtiqueta", "Unidad Medica:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvAsignacion.getCDscUniMed() + "&nbsp;"));
                                    out.println(vEti.Texto("EEtiqueta", "Modulo:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvAsignacion.getCDscModulo() + "&nbsp;"));
                                    out.println(vEti.Texto("EEtiqueta", "Área:"));
                                    out.println(vEti.Texto("ECampo", clsConfig.tvAsignacion.getCDscArea() + "&nbsp;"));
                                  }
                                  out.println("</tr>");
%>
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT" colspan="3">Servicio</td>
                              <td class="ETablaT" colspan="3">Asignar</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   int i = 0;
                                   while(bs.nextRow()){
                                     String disabled = "";
                                     String checked = "";
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveServicio", "&nbsp;").toString()));
                                     out.print(vEti.TextoCS("ECampo", bs.getFieldValue("cDscServicio", "&nbsp;").toString(), 3));
                                     if (!bs.getFieldValue("lActivo").toString().equals("1"))
                                         disabled = " disabled";

// lDiagnostico se usa para diferenciar los que existen en la tabla de EqmSerXEq de los que se obtienen de GrlSerUM
                                     if (bs.getFieldValue("lDiagnostico").toString().equals("1"))
                                         disabled = " checked";
                                     out.println("<td colspan=\"3\" align=\"center\"><input type=\"checkbox\" name=\"iCveAsignar" + i + "\" value=\"" + bs.getFieldValue("iCveServicio") +"\"" + disabled + checked + ">");
                                     out.println("<input type=\"hidden\" name=\"iCveExiste" + i + "\" value=\"" + bs.getFieldValue("lDiagnostico") +"\">");
                                     out.println("<input type=\"hidden\" name=\"iCveServicio" + i + "\" value=\"" + bs.getFieldValue("iCveServicio") +"\"></td>");
                                     out.print("</tr>");
                                     i++;
                                   }
                                   out.println("<input type='hidden' name='num_serv' value='" + i + "'>");
                               } else  {
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
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