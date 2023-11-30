<%/**
 * Title: Consulta de Programación
 * Description: Consulta de Programación
 * Copyright: Micros
 * Company: Micros
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070604010CFG
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.util.caching.*"%>

<html>
<%
  pg070604010CFG  clsConfig = new pg070604010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070604010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070604010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070603021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Fecha Programada|Numero Serie|Inventario|";    // modificar
  String cCveOrdenar  = "EqmMantenimiento.dtProgramado desc|EqmEquipo.cNumSerie|EqmEquipo.cInventario|";  // modificar
  String cDscFiltrar  = "Fecha Programada|Numero Serie|Inventario|";    // modificar
  String cCveFiltrar  = "EqmMantenimiento.dtProgramado|EqmEquipo.cNumSerie|EqmEquipo.cInventario|";  // modificar
  String cTipoFiltrar = "5|8|8|";                // modificar
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
  <input type="hidden" name="iCveEquipo" value="<%=request.getParameter("iCveEquipo")%>">
  <input type="hidden" name="hdPropiedad" value="">
  <input type="hidden" name="iCveMantenimiento" value="">
  <input type="hidden" name="hdCampoClave" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="12" class="ETablaT">Consulta de Mantenimientos
<%                                String checked = "";
                                  out.println("<tr>");
                                  out.println("<td class='ECampo' colspan='12'>Tipo de Mantenimiento: ");
                                  out.println(vEti.SelectOneRowSinTD("iCveTpoMantto", "", clsConfig.vTpoMantto, "iCveTpoMantto", "cDscBreve", request, "0"));
                                  out.println("</td>");
                                  out.println("</tr><tr>");

                                  if (request.getParameter("xMesRango") != null && request.getParameter("xMesRango").equals("R"))
                                     checked = " checked";
                                  else
                                     checked = "";
                                  out.println("<td colspan='2' class='EEtiquetaL'><input type='checkbox' name='xMesRango' value='R'" + checked + "> Rango:");
                                  out.println(vEti.EtiCampoCS("EEtiqueta", "Fecha Inicio:", "ECampo", "text", 11,10,2,"dtInicio","",0,"","",false,true,true,request));
                                  out.println(vEti.EtiCampoCS("EEtiqueta", "Fecha Fin:", "ECampo", "text", 11,10,6,"dtFin","",0,"","",false,true,true,request));
                                  out.println("</tr><tr>");

                                  out.println(vEti.TextoCS("EEtiquetaL", "Ubicación:",12));
                                  out.println("</tr><tr>");
// Unidades Medicas
                                  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                  TVGRLUniMed tvUniMed = new TVGRLUniMed();
                                  tvUniMed.setICveUniMed(0);
                                  tvUniMed.setCDscUniMed("Seleccione...");
//                                  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
                                  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                  Vector vUnidades = new Vector();
                                  vUnidades = vUsuario.getVUniFiltro(iCveProceso);
                                  vUnidades.add(tvUniMed);

                                  if (request.getParameter("xUniMed") != null && request.getParameter("xUniMed").equals("1"))
                                     checked = " checked";
                                  else
                                     checked = "";
                                  out.println("<td colspan='2' class='EEtiquetaL'><input type='checkbox' name='xUniMed' value='1'" + checked + "> Unidad Médica:");

                                  out.println("<td class='ECampo' colspan='11'>");
                                  out.println(vEti.SelectOneRowSinTD("iCveUniMed", "llenaSLT(1,this.value,'','',document.forms[0].iCveModulo,document.forms[0].iCveArea);", vUnidades, "iCveUniMed", "cDscUniMed", request, "0"));
                                  out.println("</td>");
                                  out.println("</tr><tr>");
// Modulos
                                  if (request.getParameter("xModulo") != null && request.getParameter("xModulo").equals("1"))
                                     checked = " checked";
                                  else
                                     checked = "";
                                  out.println("<td colspan='2' class='EEtiquetaL'><input type='checkbox' name='xModulo' value='1'" + checked + "> Módulo:");
                                  out.println("<td class='ECampo' colspan='11'>");
                                  if (request.getParameter("iCveUniMed") != null && !request.getParameter("iCveUniMed").equals("")) {
                                      String iUniMed = request.getParameter("iCveUniMed").toString();
                                      String iModulo = "";
                                      if (request.getParameter("iCveModulo") != null && !request.getParameter("iCveModulo").equals(""))
                                          iModulo = request.getParameter("iCveModulo").toString();
                                      TDGRLModulo dModulo = new TDGRLModulo();
                                      Vector vModulos = new Vector();
                                      vModulos = dModulo.FindByAll(" where iCveUniMed = " + iUniMed);
                                      TVGRLModulo tvModulo = new TVGRLModulo();
                                      out.println("<select name=\"iCveModulo\" size=\"1\" onChange=\"llenaSLT(2,this.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveArea,'');\"><option value='0'>Seleccione...</option>");
                                      for (int i = 0; i < vModulos.size(); i++) {
                                          tvModulo = (TVGRLModulo) vModulos.get(i);
                                          if (iModulo.equals("" + tvModulo.getICveModulo()))
                                              out.println("<option value='" + tvModulo.getICveModulo() + "' selected>" + tvModulo.getCDscModulo() + "</option>");
                                          else
                                              out.println("<option value='" + tvModulo.getICveModulo() + "'>" + tvModulo.getCDscModulo() + "</option>");
                                      }
                                      out.println("</select>");
                                  } else {
                                      out.println("<select name='iCveModulo' size='1' onChange=\"llenaSLT(2,this.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveArea,'');\"><option value='0'>Seleccione...</option></select>");
                                  }
                                  out.println("</td>");
                                  out.println("</tr><tr>");
// Áreas
                                  if (request.getParameter("xArea") != null && request.getParameter("xArea").equals("1"))
                                     checked = " checked";
                                  else
                                     checked = "";
                                  out.println("<td colspan='2' class='EEtiquetaL'><input type='checkbox' name='xArea' value='1'" + checked + "> Área:");
                                  out.println("<td class='ECampo' colspan='11'>");
                                  if (request.getParameter("iCveUniMed") != null && !request.getParameter("iCveUniMed").equals("") &&
                                    request.getParameter("iCveModulo") != null && !request.getParameter("iCveModulo").equals("")) {
                                      DAOGRLAreaModulo dAreaModulo = new DAOGRLAreaModulo();
                                      TVGRLAreaModulo tvAreaM = new TVGRLAreaModulo();
                                      Vector vAreas = new Vector();
                                      String q = " WHERE GRLAreaModulo.iCveUniMed = " + request.getParameter("iCveUniMed").toString() +
                                                 "  AND GRLAreaModulo.iCveModulo = " + request.getParameter("iCveModulo").toString() + " ";
                                      vAreas = dAreaModulo.FindByAll(q);
                                      out.print(vEti.SelectOneRowSinTD("iCveArea","",vAreas,"iCveArea","cDscArea",request,request.getParameter("iCveArea"),true));
                                  } else {
                                      out.println("<select name=\"iCveArea\" size=\"1\"><option value='0'>Datos no Disponibles...</option>");
                                      out.println("</select>");
                                  }
                                  out.println("</td>");
                                  out.println("</tr><tr>");
%>
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Equipo</td>
                              <td class="ETablaT">Mantto.</td>
                              <td class="ETablaT">Mes</td>
                              <td class="ETablaT">Fecha</td>
                              <td class="ETablaT">Tipo de Equipo</td>
                              <td class="ETablaT">Equipo</td>
                              <td class="ETablaT">Num. Serie</td>
                              <td class="ETablaT">Inventario</td>
                              <td class="ETablaT">Unidad Medica</td>
                              <td class="ETablaT">Módulo</td>
                              <td class="ETablaT" colspan="2">Área</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   TVEQMMantenimiento tvMantto = new TVEQMMantenimiento();
                                   TFechas f = new TFechas();
                                   String fSol = "&nbsp;";
                                   String fPro = "&nbsp;";
                                   String dMes = "";
                                   String mes = "&nbsp;";

                                   while(bs.nextRow()){
                                     tvMantto = (TVEQMMantenimiento) bs.getCurrentBean();
                                     if (bs.getFieldValue("dtProgramado")!=null && bs.getFieldValue("dtProgramado").toString().compareTo("")!=0 &&
                                         bs.getFieldValue("dtProgramado").toString().compareTo("null")!=0){
                                        dMes = bs.getFieldValue("dtProgramado","0").toString();
                                        dMes = dMes.substring(3, 5);
                                     }
                                     if (dMes.equals("01"))
                                         mes = "ENERO";
                                     else if (dMes.equals("02"))
                                         mes = "FEBRERO";
                                     else if (dMes.equals("03"))
                                         mes = "MARZO";
                                     else if (dMes.equals("04"))
                                         mes = "ABRIL";
                                     else if (dMes.equals("05"))
                                         mes = "MAYO";
                                     else if (dMes.equals("06"))
                                         mes = "JUNIO";
                                     else if (dMes.equals("07"))
                                         mes = "JULIO";
                                     else if (dMes.equals("08"))
                                         mes = "AGOSTO";
                                     else if (dMes.equals("09"))
                                         mes = "SEPTIEMBRE";
                                     else if (dMes.equals("10"))
                                         mes = "OCTUBRE";
                                     else if (dMes.equals("11"))
                                         mes = "NOVIEMBRE";
                                     else if (dMes.equals("12"))
                                         mes = "DICIEMBRE";
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ECampoR", bs.getFieldValue("iCveEquipo", "0").toString()));
                                     out.print(vEti.Texto("ECampoR", bs.getFieldValue("iCveMantenimiento", "0").toString()));
                                     out.print(vEti.Texto("ECampo", mes));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("dtProgramado", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscTpoEquipo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscEquipo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cNumSerie", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cInventario", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscModulo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscArea", "&nbsp;").toString()));
                                     if (clsConfig.getLPagina("pg070603021.jsp"))
                                        out.print("<td><a href=\"JavaScript:fDetalles(" + bs.getFieldValue("iCveEquipo", "0").toString() + "," + bs.getFieldValue("iCveMantenimiento") + ", 'pg070603021.jsp');\">Detalle</a></td>");
                                     out.print("</tr>");
                                   }
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
