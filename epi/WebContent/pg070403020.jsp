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
  pg070403020CFG  clsConfig = new pg070403020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070403020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070403020.jsp\" target=\"FRMCuerpo"); // modificar
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
  int ivEnero = 0,ivFebrero=0,ivMarzo=0     ,ivAbril=0  ,ivMayo=0     ,ivJunio=0;
  int ivJulio = 0,ivAgosto=0 ,ivSeptiembre=0,ivOctubre=0,ivNoviembre=0,ivDiciembre=0;
  int ivTotEnero = 0,ivTotFebrero=0,ivTotMarzo=0     ,ivTotAbril=0  ,ivTotMayo=0     ,ivTotJunio=0;
  int ivTotJulio = 0,ivTotAgosto=0 ,ivTotSeptiembre=0,ivTotOctubre=0,ivTotNoviembre=0,ivTotDiciembre=0;
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
                               <% TDGRLMdoTrans DGRLMdoTrans = new TDGRLMdoTrans();
                                  try{
                                    vMdoTransp = DGRLMdoTrans.findByAll(" where lActivo = 1");
                                  } catch (Exception ex){
                                    ex.printStackTrace();
                                  }

                                  out.println("<tr>");
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
                                  out.print("<tr>");
                                  out.print("<td class=\"ETablaTR\">Modo de Transporte:</td>");
                                  out.print("<td class=\"ETabla\">");
                                  out.print("<select name=\"iCveMdoTrans\">");
                                  if (!vMdoTransp.isEmpty()){
                                    for(int i=0;i<vMdoTransp.size();i++){
                                      TVGRLMdoTrans VGRLMdoTrans = new TVGRLMdoTrans();
                                      VGRLMdoTrans = (TVGRLMdoTrans) vMdoTransp.get(i);
                                      if (request.getParameter("iCveMdoTrans") != null){
                                        if (request.getParameter("iCveMdoTrans").toString().compareToIgnoreCase(new Integer(VGRLMdoTrans.getICveMdoTrans()).toString()) == 0)
                                          out.print("<option value=\"" + VGRLMdoTrans.getICveMdoTrans() + "\" selected>" + VGRLMdoTrans.getCDscMdoTrans() + "</option>");
                                        else
                                          out.print("<option value=\"" + VGRLMdoTrans.getICveMdoTrans() + "\" >" + VGRLMdoTrans.getCDscMdoTrans() + "</option>");
                                      } else
                                        out.print("<option value=\"" + VGRLMdoTrans.getICveMdoTrans() + "\" >" + VGRLMdoTrans.getCDscMdoTrans() + "</option>");
                                    }
                                  }
                                  out.print("</select>");
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
                                   out.print("<td class=\"ETablaT\" colspan=\"14\">Meses del Año</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Entidad Federativa</td>");
                                   out.print("<td class=\"ETablaT\">Ene</td>");
                                   out.print("<td class=\"ETablaT\">Feb</td>");
                                   out.print("<td class=\"ETablaT\">Mar</td>");
                                   out.print("<td class=\"ETablaT\">Abr</td>");
                                   out.print("<td class=\"ETablaT\">May</td>");
                                   out.print("<td class=\"ETablaT\">Jun</td>");
                                   out.print("<td class=\"ETablaT\">Jul</td>");
                                   out.print("<td class=\"ETablaT\">Ago</td>");
                                   out.print("<td class=\"ETablaT\">Sep</td>");
                                   out.print("<td class=\"ETablaT\">Oct</td>");
                                   out.print("<td class=\"ETablaT\">Nov</td>");
                                   out.print("<td class=\"ETablaT\">Dic</td>");
                                   out.print("<td class=\"ETablaT\">Total</td>");
                                   out.print("</tr>");
                                   //Obtención de los Valores de la Base de Datos
                                   if (request.getParameter("iAnio") != null && request.getParameter("iCveMdoTrans") != null)
                                       vAccidentes = clsConfig.getValores(new Integer(request.getParameter("iAnio").toString()).intValue(),
                                                                          new Integer(request.getParameter("iCveMdoTrans").toString()).intValue());

                                   bs.start();
                                   while(bs.nextRow()){
                                     ivEnero = 0;
                                     ivFebrero = 0;
                                     ivMarzo = 0;
                                     ivAbril = 0;
                                     ivMayo = 0;
                                     ivJunio = 0;
                                     ivJulio = 0;
                                     ivAgosto = 0;
                                     ivSeptiembre = 0;
                                     ivOctubre = 0;
                                     ivNoviembre = 0;
                                     ivDiciembre = 0;
                                     if(!vAccidentes.isEmpty()){
                                       for(int i=0;i<vAccidentes.size();i++){
                                         TVINVRegistro VINVRegistro = new TVINVRegistro();
                                         VINVRegistro = (TVINVRegistro) vAccidentes.get(i);
                                         if (VINVRegistro.getICveEstado() == new Integer(bs.getFieldValue("iCveEntidadFed", "").toString()).intValue()){
                                            if(VINVRegistro.getIIDDGPMPT() == 1)
                                              ivEnero = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 2)
                                              ivFebrero = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 3)
                                              ivMarzo = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 4)
                                              ivAbril = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 5)
                                              ivMayo = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 6)
                                              ivJunio = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 7)
                                              ivJulio = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 8)
                                              ivAgosto = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 9)
                                              ivSeptiembre = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 10)
                                              ivOctubre = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 11)
                                              ivNoviembre = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 12)
                                              ivDiciembre = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                         }
                                       }
                                     }
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cNombre", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampoR", ""+ ivEnero)); // Enero
                                     out.print(vEti.Texto("ECampoR", ""+ ivFebrero)); // Febrero
                                     out.print(vEti.Texto("ECampoR", ""+ ivMarzo)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ ivAbril)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ ivMayo)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ ivJunio)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ ivJulio)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ ivAgosto)); // Agosto
                                     out.print(vEti.Texto("ECampoR", ""+ ivSeptiembre)); // Septiembre
                                     out.print(vEti.Texto("ECampoR", ""+ ivOctubre)); // Octubre
                                     out.print(vEti.Texto("ECampoR", ""+ ivNoviembre)); // Noviembre
                                     out.print(vEti.Texto("ECampoR", ""+ ivDiciembre)); // Diciembre
                                     ivTotUniMed = ivEnero + ivFebrero + ivMarzo + ivAbril + ivMayo + ivJunio +
                                                   ivJulio + ivAgosto + ivSeptiembre + ivOctubre + ivNoviembre + ivDiciembre;
                                     out.print(vEti.Texto("ECampoR", ""+ ivTotUniMed)); // Total
                                     out.print("</tr>");
                                     ivTotEnero   = ivTotEnero + ivEnero;
                                     ivTotFebrero = ivTotFebrero + ivFebrero;
                                     ivTotMarzo   = ivTotMarzo + ivMarzo;
                                     ivTotAbril   = ivTotAbril + ivAbril;
                                     ivTotMayo    = ivTotMayo  + ivMayo;
                                     ivTotJunio   = ivTotJunio + ivJunio;
                                     ivTotJulio   = ivTotJulio + ivJulio;
                                     ivTotAgosto  = ivTotAgosto + ivAgosto;
                                     ivTotSeptiembre = ivTotSeptiembre + ivSeptiembre;
                                     ivTotOctubre    = ivTotOctubre + ivOctubre;
                                     ivTotNoviembre = ivTotNoviembre + ivNoviembre;
                                     ivTotDiciembre = ivTotDiciembre + ivDiciembre;

                                   }
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ETablaR", "T O T A L"));
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotEnero)); // Enero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotFebrero)); // Febrero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotMarzo)); // Marzo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotAbril)); // Abril
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotMayo)); // Mayo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotJunio)); // Junio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotJulio)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotAgosto)); // Agosto
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotSeptiembre)); // Septiembre
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotOctubre)); // Octubre
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotNoviembre)); // Noviembre
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotDiciembre)); // Diciembre
                                   ivTotGeneral = ivTotEnero + ivTotFebrero + ivTotMarzo      + ivTotAbril   + ivTotMayo      + ivTotJunio +
                                                  ivTotJulio + ivTotAgosto  + ivTotSeptiembre + ivTotOctubre + ivTotNoviembre + ivTotDiciembre;
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
