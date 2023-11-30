<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>


<html>
<%
  pg070801030CFG  clsConfig = new pg070801030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070801030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070801030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070801031.jsp";       // modificar
  String cDetalle    = "pg070801031.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripci�n|";    // modificar
  String cCveOrdenar  = "iCveAlmacen|cDscAlmacen|";  // modificar
  String cDscFiltrar  = "Clave|Descripci�n|";    // modificar
  String cCveFiltrar  = "iCveAlmacen|cDscAlmacen|";  // modificar
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
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
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
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
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
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Almacenes
                              </td>
                            </tr>
                             <% // modificar seg�n listado
                               String cValActual = "";
                               out.println("<tr>");
                               out.print(vEti.Texto("ETablaTR", "Unidad M�dica:"));
                               TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                               TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                               boolean lFirst = false;
                               BeanScroller beanSc = new BeanScroller(dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()));
                               if(request.getParameter("iCveUniMed")!=null &&request.getParameter("iCveUniMed").compareTo("")!=0)
                                 cValActual = "" + request.getParameter("iCveUniMed");
                               else
                                 cValActual = "0";
                               out.print("<td colspan='4'><select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                               if (beanSc != null) {
                                 out.print("<option value=0>Seleccione...</option>");
                                 int i;
                                 for (i = 1; i <= beanSc.rowSize(); i++) {
                                   beanSc.setRowIdx(i);
                                   String CampoClave = "" + beanSc.getFieldValue("iCveUniMed", "");
                                   if (cValActual.compareTo("") == 0 && lFirst == false) {
                                     CampoClave = " selected ";
                                     lFirst = true;
                                   }
                                   else {
                                     if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
                                       CampoClave = " selected ";
                                     }else{
                                       CampoClave = "";
                                     }
                                   }
                                   out.print("<option " + CampoClave + " value=\"" + beanSc.getFieldValue("iCveUniMed", "" + i) + "\">" + beanSc.getFieldValue("cDscUniMed", "") + "</option>");
                                 }
                                 out.print("</select></td>");
                               }

                                if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Clave</td>");
                                   out.print("<td class=\"ETablaT\">Descripci�n</td>");
                                   out.print("<td class=\"ETablaT\">Responsable</td>");
                                   out.print("<td class=\"ETablaT\">Situaci�n</td>");
                                   if(clsConfig.getLPagina(cDetalle))
                                     out.print("<td class=\"ETablaT\">Selecci�n</td>");
                                   out.print("</tr>");
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveAlmacen", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscAlmacen", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cNombre").toString() + " " + bs.getFieldValue("cApPaterno").toString() + " " + bs.getFieldValue("cApMaterno").toString()));
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.Texto("ETabla", "INACTIVO"));
                                     else
                                       out.print(vEti.Texto("ETabla", "ACTIVO"));
                                     if(clsConfig.getLPagina(cDetalle)){
                                       out.print("<td class=\"ETabla\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrDetalle('" + bs.getFieldValue("iCveUniMed") + "', '" + bs.getFieldValue("iCveAlmacen") + "');", ""));
                                       out.print("</td>");
                                     }
                                     out.print("</tr>");
                                   }
                               }
                               else{
                                   out.print("<tr>");
                                   out.print("<td class='ECampo' colspan='5'>No existen datos coincidentes con el filtro proporcionado.</td>");
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
