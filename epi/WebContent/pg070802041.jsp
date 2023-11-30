<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>


<html>
<%
  pg070802041CFG  clsConfig = new pg070802041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802041.jsp");                     // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802041.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

//  String cCatalogo    = "pg070802041.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Artículo|";    // modificar
  String cCveOrdenar  = "S.iCveArticulo|A.cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Artículo|";    // modificar
  String cCveFiltrar  = "S.iCveArticulo|A.cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;            // modificar
  String cCanWrite   = "yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cAnio = "";
  String cvLoteConfirmativo = "";

  Vector vAlmacen        = new Vector();
  Vector vTpoPrioridad   = new Vector();
  Vector vPeriodo        = new Vector();


  String cvAlmacen      = "";
  String cvTpoPrioridad = "";
  String cvPeriodo      = "";
  String chkProgramada  = "";
  String chkExtra       = "";

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

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
  <input type="hidden" name="iCveAlmacen" value="<%=request.getParameter("iCveAlmacen") %>">
  <input type="hidden" name="iCveTpoPrioridad" value="<%=request.getParameter("iCveTpoPrioridad") %>">
  <input type="hidden" name="iCvePeriodo" value="<%=request.getParameter("iCvePeriodo") %>">
  <input type="hidden" name="hdCampoClave3" value="<%=request.getParameter("hdCampoClave3") %>">
  <input type="hidden" name="hdCampoClave2" value="<%=request.getParameter("hdCampoClave2") %>">
  <input type="hidden" name="hdCampoClave" value="<%=request.getParameter("hdCampoClave") %>">
  <input type="hidden" name="lProgramada" value="<%=request.getParameter("lProgramada") %>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIni" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>

<!-- Tabla de Filtros -->

  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="8" class="ETablaT">Suministrar</td>
           </tr><tr>

             <!-- **  Almacen ** -->

             <td class="EEtiqueta">Almacén:</td>
             <td class="ECampo">
<%               TVALMAlmacen tvAlm = clsConfig.tvAlmacen;
                 out.println(tvAlm.getCDscAlmacen());
%>           </td>

             <!-- ** Prioridad ** -->

             <td class="EEtiqueta">Prioridad:</td>
             <td class="ECampo">
<%               TVALMTpoPrioridad tvPrio = clsConfig.tvPrioridad;
                 out.println(tvPrio.getCDscPrioridad());
%>
             </td>

             <!-- **  Periodo ** -->
             <td class="EEtiqueta">Periodo:</td>
             <td class="ECampo">
<%               TVALMPeriodo tvPeriodo = clsConfig.tvPeriodo;
                 out.println(tvPeriodo.getCDscPeriodo());
%>
             </td>
           </tr>
         </table><br>

<!--      </td>
      </tr>


<!-- Tabla de Radio Sets -->

<!--      <tr>
      <td valign="top">
-->
                         <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="2" class="ETablaT">Tipo de Solicitudes</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Solicitudes</td>
                              <td class="ETabla">
                              <%
                                  chkProgramada = "";
                                  chkExtra = "";
                                  if (request.getParameter("lProgramada")!= null){
                                     if (request.getParameter("lProgramada").toString().compareToIgnoreCase("1")==0)
                                          chkProgramada = "checked"; else
                                          chkExtra = "checked";
                                  } else chkProgramada = "checked";

                              out.print("<input type=\"radio\" disabled name=\"lProgramada\" value=\"1\" "+chkProgramada + ">Programadas");
                              out.print("<input type=\"radio\" disabled name=\"lProgramada\" value=\"0\" "+chkExtra +">Extras");
                              %>
                             </td>
                            </tr>
                          </table><br>

  <!-- </td></tr> -->



      <!-- Tabla de Desplegado -->

 <!--     <tr>
      <td valign="top">
-->
                         <table border="1" class="ETablaInfo" align="center" cellpadding="2" cellspacing="2"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="9" class="ETablaT">Solicitudes de Suministro</td>
                            </tr>
                            <tr>
                              <td class="ETablaT" rowspan="2">Clave</td>
                              <td class="ETablaT" rowspan="2">Artículo</td>
                              <td class="ETablaT" colspan="2">Cantidad</td>
                              <td class="ETablaT" rowspan="2">Observación</td>
                              <td class="ETablaT" rowspan="2">Maneja Lotes</td>
                              <td class="ETablaT" rowspan="2">Lote</td>
                              <td class="ETablaT" rowspan="2">Caducidad</td>
                              <td class="ETablaT" rowspan="2">Unidades</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Solicitada</td>
                              <td class="ETablaT">Autorizada</td>
                            </tr>
                             <%
                              // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   TFechas f = new TFechas();
                                   TVALMSumArt tvSum = new TVALMSumArt();
                                   DecimalFormat df = new DecimalFormat("##,###,##0.00");
                                   while(bs.nextRow()){
                                       String sol = "&nbsp;";
                                       String aut = "&nbsp;";
                                       String fecha = "&nbsp;";
                                       String cLote = "&nbsp;";
                                       String lLote = "&nbsp;";
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveArticulo", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscBreve", "&nbsp;")));
                                       if (bs.getFieldValue("dUnidSolicita") != null && !bs.getFieldValue("dUnidSolicita").equals(""))
                                           sol = df.format(Double.parseDouble(bs.getFieldValue("dUnidSolicita").toString()));
                                       out.print(vEti.Texto("ETablaR","" + sol));
                                       if (bs.getFieldValue("dUnidAutor") != null && !bs.getFieldValue("dUnidAutor").equals(""))
                                           aut = df.format(Double.parseDouble(bs.getFieldValue("dUnidAutor").toString()));
                                       out.print(vEti.Texto("ETablaR","" + aut));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cObservacion", "&nbsp;")));
                                       if (bs.getFieldValue("lLote").toString().equals("1")){
                                           lLote = "SI";
                                       } else if (bs.getFieldValue("lLote").toString().equals("0")) {
                                           lLote = "NO";
                                       }
                                       out.print(vEti.Texto("ETabla", lLote));
                                       cLote = bs.getFieldValue("cLote") != null ? bs.getFieldValue("cLote", "&nbsp;").toString() : "&nbsp;";
                                       out.print(vEti.Texto("ETabla","" + cLote));
                                       tvSum = (TVALMSumArt) bs.getCurrentBean();
                                       if (tvSum.getdtCaducidad() != null && !tvSum.getdtCaducidad().equals(""))
                                           fecha = f.getFechaDDMMYYYY(tvSum.getdtCaducidad(), "/");
                                       out.print(vEti.Texto("ETabla", fecha));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("dUnidades", "&nbsp;")));
                                       out.println("</tr>");
                                   }
                               }else {
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50,8, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
