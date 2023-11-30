<%/**
 * Title: Listado de Representantes Legales de la Empresas
 * Description: Listado de Representantes Legales de la Empresas
 * Copyright: Copyright (c) 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070502014CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>

<html>
<%
  pg070502014CFG clsConfig = new pg070502014CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502014.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502014.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502015.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Representante|";    // modificar
  String cCveOrdenar  = "iCveRepresentante|";  // modificar
  String cDscFiltrar  = "Clave Representante|";    // modificar
  String cCveFiltrar  = "iCveRepresentante|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
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
  String cUpdStatus  = clsConfig.getUpdStatus();  //"SaveOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  //if (bs == null)
  //       cCanWrite = "No";
  cCanWrite = clsConfig.getCanWrite();
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
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="iCveRepresentante" value="">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="GuardaRepresentante" value="N">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Representates Legales del Transportista
                              </td>
                            </tr>
                            <tr>
<%
/*                            Vector vEmp = new Vector();
                                  TDCTRRepresentante dEmp = new TDCTRRepresentante();
                                  TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                  vEmp = dEmp.FindByAllEmprUsu(tvUsu.getICveusuario());
                                  out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "fCambioFiltro();", vEmp, "iCveEmpresa", "cDscEmpresa", request, "0"));
*/
                                 TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
                                 TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
                                 Vector vcGRLEmpresas;
                                 vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                 if(request.getParameter("iCveEmpresa")==null || request.getParameter("iCveEmpresa").compareTo("")==0) {
                                   vGRLEmpresas.setCDenominacion("Seleccione...");
                                   vGRLEmpresas.setICveEmpresa(0);
                                   vcGRLEmpresas.add(vGRLEmpresas);
                                 }
                                 out.print(vEti.Texto("ETablaTR", "Transportista:"));
                                 out.println("<td colspan='6'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();", vcGRLEmpresas, "iCveEmpresa", "cDenominacion", request, "0"));
                                 out.println("</td>");

%>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT">Representante</td>
                              <td class="ETablaT">Dirección</td>
                              <td class="ETablaT">Teléfono</td>
                              <td class="ETablaT">Fax</td>
                              <td class="ETablaT">Correo Electrónico</td>
                              <td class="ETablaT" colspan="2">Actual</td>
                            </tr>
                             <% // modificar según listado

                               if (bs != null){
                                   bs.start();
                                   TFechas f = new TFechas();
                                   TVGRLEmpresas tvEm = new TVGRLEmpresas();
                                   while(bs.nextRow()){
                                     String nombre = bs.getFieldValue("cNombre", "&nbsp;") + " " +
                                                     bs.getFieldValue("cApPaterno", "&nbsp;") + " " +
                                                     bs.getFieldValue("cApMaterno", "&nbsp;");
                                     String dir = bs.getFieldValue("cCalle", "&nbsp;") + "<br> " +
                                                     bs.getFieldValue("cColonia", "&nbsp;") + ", " +
                                                     bs.getFieldValue("iCP", "&nbsp;") + "<br> " +
                                                     bs.getFieldValue("cMunicipio", "&nbsp;") + ", " +
                                                     bs.getFieldValue("cDscEstado", "&nbsp;") + "<br> " +
                                                     bs.getFieldValue("cPais", "&nbsp;");
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("iCveRepresentante", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", nombre));
                                     out.print(vEti.Texto("ECampo", dir));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cTel", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cFax", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cCorreoElec", "&nbsp;").toString()));

                                     if (bs.getFieldValue("lActivo") != null && bs.getFieldValue("lActivo").equals("1")){
                                       if (cCanWrite.compareTo("yes") == 0)
                                          out.print("<td><input type=\"radio\" name=\"lActivo\" value=\"" + bs.getFieldValue("iCveRepresentante") + "\" checked>");
                                       else
                                          out.print("<td><input type=\"radio\" name=\"lActivo\" value=\"" + bs.getFieldValue("iCveRepresentante") + "\" checked disabled>");
                                     }
                                     else if (bs.getFieldValue("lActivo").equals("0")){
                                       if (cCanWrite.compareTo("yes") == 0)
                                          out.print("<td><input type=\"radio\" name=\"lActivo\" value=\"" + bs.getFieldValue("iCveRepresentante") + "\">");
                                       else
                                          out.print("<td><input type=\"radio\" name=\"lActivo\" value=\"" + bs.getFieldValue("iCveRepresentante") + "\" disabled>");
                                     }

                                     // Ver si tiene permisos para ir a las paginas
                                     if (clsConfig.getLPagina("pg070502015.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEmpresa") + ", " + bs.getFieldValue("iCveRepresentante") + ");\">Detalle</a></td>");
                                     out.print("</tr>");
                                   }
                               } else {
                                   // NO HAY REGISTROS
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                   out.print("<td class='ECampo' colspan='8'>No existen datos coincidentes con el filtro proporcionado</td>");
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
