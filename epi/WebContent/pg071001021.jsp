<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="java.util.*"%>
<html>
<%
  pg071001021CFG  clsConfig = new pg071001021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071001021.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071001021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave1 = "", cClave2 = "", cClave3 = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar



  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg071001021.js)
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave1  = ""+bs.getFieldValue("iCveUsuario", "");
       cClave2  = ""+bs.getFieldValue("iCveUniMed", "");
       cClave3  = ""+bs.getFieldValue("iCveProceso", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave1%>">
  <input type="hidden" name="hdiCveUniMed" value="<%=cClave2%>">
  <input type="hidden" name="hdiCveProceso" value="<%=cClave3%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                              <td colspan="2" class="ETablaT">Usuarios, Unidades Médicas y Procesos</td>
                            </tr>
                            <% if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Usuario:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveUsuario","",new TDSEGUsuario().FindByAll(""),"iCveUsuario","cNomCompleto",request,""));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 Vector vcGRLUniMed = (Vector) AppCacheManager.getColl("GRLUniMed","");
                                 out.print(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveUniMed","fChg();",vcGRLUniMed,"iCveUniMed","cDscUniMed",request,""));
                                 String cUniMed = "";
                                 try{
                                    if(request.getParameter("iCveUniMed")==null)
                                      cUniMed = ""+((TVUniMed)vcGRLUniMed.get(0)).getICveUniMed();
                                    else
                                      cUniMed = ""+request.getParameter("iCveUniMed");
                                 }catch(Exception e){
                                    e.printStackTrace();
                                    cUniMed = "";
                                 }
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Proceso:"));
                                 out.print(vEti.SelectOneRow("ECampo","iCveProceso","",(Vector) AppCacheManager.getColl("GRLProceso",cUniMed+"|"),"iCveProceso","cDscProceso",request,""));
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Usuario:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("iCveUsuario", "&nbsp;")+" - "+bs.getFieldValue("cUsuario", "&nbsp;")+" - "+bs.getFieldValue("cNomCompleto", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr><tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Unidad Médica:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("cDscUniMed", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr><tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Proceso:", "ECampo", "text", 4, 4, "", ""+bs.getFieldValue("cDscProceso", "&nbsp;"), 3, "", "", false, true, false));
                                   out.println("</tr>");
                                 }
                                 else{
                                   out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
                                 }
                               }
                            %>
                          </table><% // Fin de Datos %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>