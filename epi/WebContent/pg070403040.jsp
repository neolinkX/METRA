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
  pg070403040CFG  clsConfig = new pg070403040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070403040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070403040.jsp\" target=\"FRMCuerpo"); // modificar
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
  int ivDomingo=0,ivLunes=0,ivMartes=0,ivMiercoles=0,ivJueves=0,ivViernes=0,ivSabado = 0;
  int ivTotDomingo=0,ivTotLunes=0,ivTotMartes=0,ivTotMiercoles=0,ivTotJueves=0,ivTotViernes=0,ivTotSabado=0;
  int ivTotUniMed = 0,ivTotGeneral = 0;
  Vector vAccidentes = new Vector();
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
                                   out.print("<td class=\"ETablaT\" colspan=\"14\">Días de la Semana</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Modo de Transporte</td>");
                                   out.print("<td class=\"ETablaT\">Domingo</td>");
                                   out.print("<td class=\"ETablaT\">Lunes</td>");
                                   out.print("<td class=\"ETablaT\">Martes</td>");
                                   out.print("<td class=\"ETablaT\">Miercoles</td>");
                                   out.print("<td class=\"ETablaT\">Jueves</td>");
                                   out.print("<td class=\"ETablaT\">Viernes</td>");
                                   out.print("<td class=\"ETablaT\">Sabado</td>");
                                   out.print("<td class=\"ETablaT\">Total</td>");
                                   out.print("</tr>");
                                   //Obtención de los Valores de la Base de Datos
                                   if (request.getParameter("iAnio") != null)
                                       vAccidentes = clsConfig.getValores(new Integer(request.getParameter("iAnio").toString()).intValue());

                                   bs.start();
                                   while(bs.nextRow()){
                                     ivDomingo = 0;
                                     ivLunes = 0;
                                     ivMartes = 0;
                                     ivMiercoles = 0;
                                     ivJueves = 0;
                                     ivViernes = 0;
                                     ivSabado = 0;
                                     if(!vAccidentes.isEmpty()){
                                       for(int i=0;i<vAccidentes.size();i++){
                                         TVINVRegistro VINVRegistro = new TVINVRegistro();
                                         VINVRegistro = (TVINVRegistro) vAccidentes.get(i);
                                         if (VINVRegistro.getICveMdoTrans() == new Integer(bs.getFieldValue("iCveMdoTrans", "").toString()).intValue()){
                                            if(VINVRegistro.getIIDDGPMPT() == 1)
                                              ivDomingo = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 2)
                                              ivLunes = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 3)
                                              ivMartes = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 4)
                                              ivMiercoles = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 5)
                                              ivJueves = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 6)
                                              ivViernes = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 7)
                                              ivSabado = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                         }
                                       }
                                     }
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMdoTrans", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampoR", ""+ ivDomingo)); // Enero
                                     out.print(vEti.Texto("ECampoR", ""+ ivLunes)); // Febrero
                                     out.print(vEti.Texto("ECampoR", ""+ ivMartes)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ ivMiercoles)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ ivJueves)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ ivViernes)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ ivSabado)); // Julio
                                     ivTotUniMed = ivDomingo + ivLunes + ivMartes + ivMiercoles + ivJueves + ivViernes + ivSabado;
                                     out.print(vEti.Texto("ECampoR", ""+ ivTotUniMed)); // Total
                                     out.print("</tr>");
                                     ivTotDomingo   = ivTotDomingo + ivDomingo;
                                     ivTotLunes = ivTotLunes + ivLunes;
                                     ivTotMartes   = ivTotMartes + ivMartes;
                                     ivTotMiercoles   = ivTotMiercoles + ivMiercoles;
                                     ivTotJueves    = ivTotJueves  + ivJueves;
                                     ivTotViernes   = ivTotViernes + ivViernes;
                                     ivTotSabado   = ivTotSabado + ivSabado;
                                   }
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ETablaR", "T O T A L"));
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotDomingo)); // Enero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotLunes)); // Febrero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotMartes)); // Marzo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotMiercoles)); // Abril
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotJueves)); // Mayo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotViernes)); // Junio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotSabado)); // Julio
                                   ivTotGeneral = ivTotDomingo + ivTotLunes + ivTotMartes      + ivTotMiercoles   + ivTotJueves      + ivTotViernes + ivTotSabado;
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
