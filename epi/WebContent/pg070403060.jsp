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
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070403060CFG  clsConfig = new pg070403060CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070403060.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070403060.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  //String cCatalogo    = "pg070501071.jsp";       // modificar
  //String cDetalle    = "pg070501071.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Descripción|";    // modificar
  String cCveOrdenar  = "cDscUniMed|";  // modificar
  String cDscFiltrar  = "Descripción|";    // modificar
  String cCveFiltrar  = "cDscUniMed|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

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
  String cClave2    = "";
  String cPosicion = "";
  int ivVigente=0,ivNoVigente=0,ivSeIgnora=0;
  int ivTotVigente=0,ivTotNoVigente=0,ivTotSeIgnora=0;
  int ivTotUniMed = 0,ivTotGeneral = 0;
  Vector vVigentes = new Vector();
  Vector vNoVigentes = new Vector();
  Vector vSeIgnora = new Vector();
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
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr>
                              <td colspan="5" class="ETablaT">Filtros para el Reporte</td>
                            </tr>
                               <% out.println("<tr>");
                                  out.println("<td class=\"ETablaTR\">Año:</td>");
                                  out.println("<td class=\"ETabla\">");
                                  out.println("<select name=\"iAnio\">");
                                  out.println("<option value = \"-1\" selected>Seleccione...</option>");
                                  for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                    if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                      out.println("<option value = " + i + " selected>" + i + "</option>");
                                    else
                                      out.println("<option value = " + i + ">" + i + "</option>");
                                  }
                                  out.println("</select>");
                                  out.println("</td>");
                                  out.println("</tr>");
                               %>
                          </table >
      </td>
      </tr>
      <tr><td>&nbsp;</td></tr>
      <tr>
      <td valign="top">&nbsp;</td>
      <td>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="14" class="ETablaT">Accidentes Registrados</td>
                            </tr>
                               <%
                               if(request.getParameter("hdBoton").toString().compareTo("Enviar")==0 ) {
                                 out.println(clsConfig.getActiveX());
                               }
                              // modificar según listado
                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\"></td>");
                                   out.print("<td class=\"ETablaT\" colspan=\"14\">Situación de la Licencia</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Modo de Transporte</td>");
                                   out.print("<td class=\"ETablaT\">Vigente</td>");
                                   out.print("<td class=\"ETablaT\">No Vigente</td>");
                                   out.print("<td class=\"ETablaT\">Se Ignora</td>");
                                   out.print("<td class=\"ETablaT\">Total</td>");
                                   out.print("</tr>");
                                   //Obtención de los Valores de la Base de Datos
                                   if (request.getParameter("iAnio") != null){
                                       vVigentes = clsConfig.getVigentes(new Integer(request.getParameter("iAnio").toString()).intValue());
                                       vNoVigentes = clsConfig.getNoVigentes(new Integer(request.getParameter("iAnio").toString()).intValue());
                                       vSeIgnora = clsConfig.getSeIgnora(new Integer(request.getParameter("iAnio").toString()).intValue());
                                   }

                                   bs.start();
                                   while(bs.nextRow()){
                                     ivVigente = 0;
                                     ivNoVigente = 0;
                                     ivSeIgnora = 0;
                                     //Valores de los Accidentes con Licencia Vigente.
                                     if(!vVigentes.isEmpty()){
                                       for(int i=0;i<vVigentes.size();i++){
                                         TVINVRegistro VINVRegistro = new TVINVRegistro();
                                         VINVRegistro = (TVINVRegistro) vVigentes.get(i);
                                         if (VINVRegistro.getICveMdoTrans() == new Integer(bs.getFieldValue("iCveMdoTrans", "").toString()).intValue()){
                                            ivVigente = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                         }
                                       }
                                     }
                                     //Valores de los Accidentes con Licencia No Vigente.
                                     if(!vNoVigentes.isEmpty()){
                                       for(int i=0;i<vNoVigentes.size();i++){
                                         TVINVRegistro VINVRegistro = new TVINVRegistro();
                                         VINVRegistro = (TVINVRegistro) vNoVigentes.get(i);
                                         if (VINVRegistro.getICveMdoTrans() == new Integer(bs.getFieldValue("iCveMdoTrans", "").toString()).intValue()){
                                            ivNoVigente = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                         }
                                       }
                                     }
                                     //Valores de los Accidentes con Licencia No Vigente.
                                     if(!vSeIgnora.isEmpty()){
                                       for(int i=0;i<vSeIgnora.size();i++){
                                         TVINVRegistro VINVRegistro = new TVINVRegistro();
                                         VINVRegistro = (TVINVRegistro) vSeIgnora.get(i);
                                         if (VINVRegistro.getICveMdoTrans() == new Integer(bs.getFieldValue("iCveMdoTrans", "").toString()).intValue()){
                                            ivSeIgnora = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                         }
                                       }
                                     }
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMdoTrans", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampoR", ""+ ivVigente)); // Enero
                                     out.print(vEti.Texto("ECampoR", ""+ ivNoVigente)); // Febrero
                                     out.print(vEti.Texto("ECampoR", ""+ ivSeIgnora)); // Marzo
                                     ivTotUniMed = ivVigente + ivNoVigente + ivSeIgnora;
                                     out.print(vEti.Texto("ECampoR", ""+ ivTotUniMed)); // Total
                                     out.print("</tr>");
                                     ivTotVigente   = ivTotVigente + ivVigente;
                                     ivTotNoVigente = ivTotNoVigente + ivNoVigente;
                                     ivTotSeIgnora   = ivTotSeIgnora + ivSeIgnora;
                                   }
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ETablaR", "T O T A L"));
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotVigente)); // Enero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotNoVigente)); // Febrero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotSeIgnora)); // Marzo
                                   ivTotGeneral = ivTotVigente + ivTotNoVigente + ivTotSeIgnora;
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotGeneral )); // Total
                                   out.print("</tr>");
                                   out.println("<tr>");
                                   out.println("<td class=\"ETablaT\" colspan=\"14\">Envio de Información a Excel</td>");
                                   out.println("</tr>");
                                   //cSQL = clsConfig.cValSQL;
                                   //request.getSession(true).setAttribute("cRepSQL",cSQL);
                                   out.print("<td class=\"ECampoC\" colspan=\"14\">");
                                   out.print(vEti.clsAnclaTexto("EAncla","Enviar a Excel","JavaScript:fEnviarExcel('','','','','');",""));
                                   out.print("</td>");
                                   out.println("</tr>");
                               }
                               else{
                                 out.println("<tr>");
                                 out.print("<td class='ETablaC' colspan='5'>No existen datos coincidentes con el filtro proporcionado.</td>");
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
