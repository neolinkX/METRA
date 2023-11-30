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
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070305010CFG  clsConfig = new pg070305010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "NIM|Análisis|Envío|";    // modificar
  String cCveOrdenar  = "a.iCveMuestra|c.iCveAnalisis|a.iCveEnvio|";  // modificar
  String cDscFiltrar  = "Análisis|Envío|";    // modificar
  String cCveFiltrar  = "c.iCveAnalisis|a.iCveEnvio|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
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
       cClave = ""+bs.getFieldValue("iAnio", "");
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Consulta
                              </td>
                            </tr>
                            <tr>
                              <%
                                out.print(vEti.Texto("EEtiqueta", "Año:"));
                              %>
                              <td>
                                 <select name="iAnio" size="1">
                                    <%
                                      for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                        if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                          out.print("<option value = " + i + " selected>" + i + "</option>");
                                        else
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                              </td>
                              <%
                                out.print(vEti.Texto("EEtiqueta", "Unidad Médica:"));
                                out.print("<td>");
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                //out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0"));
                                out.print(vEti.SelectOneRowSinTD("iCveUniMed","", vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0", true));
                                out.print("</td>");

                                 out.println("<tr>");
                                 if (request.getParameter("dtPeriodoDe")!=null)
                                    out.print(vEti.EtiCampo("EEtiqueta", "Periodo Recolección de:", "ECampo", "text", 10, 10, "dtPeriodoDe", request.getParameter("dtPeriodoDe"), 4, "", "fMayus(this);", true, true, true));
                                 else
                                    out.print(vEti.EtiCampo("EEtiqueta", "Periodo Recolección de:", "ECampo", "text", 10, 10, "dtPeriodoDe", "", 4, "", "fMayus(this);", true, true, true));
                                 if (request.getParameter("dtPeriodoA")!=null)
                                    out.print(vEti.EtiCampo("EEtiqueta", "a:", "ECampo", "text", 10, 10, "dtPeriodoA",request.getParameter("dtPeriodoA") , 4, "", "fMayus(this);", true, true, true));
                                 else
                                     out.print(vEti.EtiCampo("EEtiqueta", "a:", "ECampo", "text", 10, 10, "dtPeriodoA","" , 4, "", "fMayus(this);", true, true, true));
                                 out.println("</tr>");

                                 TDSituacion dSituacion = new TDSituacion();
                                 out.print(vEti.Texto("EEtiqueta", "Situación:"));
                                 out.print("<td colspan='3'>");
                                 out.print(vEti.SelectOneRowSinTD("iCveSituacion", "", dSituacion.FindByAll(""), "iCveSituacion", "cDscSituacion", request, "0",true));
                                 out.print("</td>");

                                 out.println("<tr>");
                                 if (request.getParameter("iClaveDe")!=null)
                                    out.print(vEti.EtiCampo("EEtiqueta", "NIM de:", "ECampo", "text", 15, 15, "iClaveDe", request.getParameter("iClaveDe"), 4, "", "fMayus(this);", true, true, true));
                                 else
                                    out.print(vEti.EtiCampo("EEtiqueta", "NIM de:", "ECampo", "text", 15, 15, "iClaveDe", "", 4, "", "fMayus(this);", true, true, true));
                                 if (request.getParameter("iClaveA")!=null)
                                    out.print(vEti.EtiCampo("EEtiqueta", "a:", "ECampo", "text", 15, 15, "iClaveA", request.getParameter("iClaveA"), 4, "", "fMayus(this);", true, true, true));
                                 else
                                    out.print(vEti.EtiCampo("EEtiqueta", "a:", "ECampo", "text", 15,15, "iClaveA", "", 4, "", "fMayus(this);", true, true, true));
                                 out.println("</tr>");

                              %>
                            </tr>

                          </table>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="13" class="ETablaT">Control de F.F.C.C.
                              </td>
                            </tr>
                          <% if (bs != null){ %>
                   <tr>
                   <td class="ETablaT">NIM</td>
                   <td class="ETablaT">Env&iacute;o</td>
                   <td class="ETablaT">Tipo de <br> Recepci&oacute;n</td>
                   <td class="ETablaT">Motivo</td>
                   <td class="ETablaT">Lote<br>Cualitativo</td>
                   <td class="ETablaT">An&aacute;lisis</td>
                   <td class="ETablaT">Presunto <br> Positivo</td>
                   <td class="ETablaT">Sustancias <br> Presuntas</td>
                   <td class="ETablaT">Sustancias <br> Confirmadas</td>
                   <td class="ETablaT">Resultado</td>
                   <td class="ETablaT">Sustancia</td>
                   </tr>
                   <%
                   bs.start();
                   String cCveMuestra = "";

                   while(bs.nextRow()){
                     out.print("<tr>");
                     if (bs.getFieldValue("iCveMuestra")!=null && (bs.getFieldValue("iCveMuestra").toString().compareTo(cCveMuestra)!=0)){
                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveMuestra", "&nbsp;").toString()));
                     out.print(vEti.Texto("ETabla", bs.getFieldValue("iCveEnvio", "&nbsp;").toString()));
                     if (bs.getFieldValue("cDscTipoRecep").toString().compareTo("null")!=0)
                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscTipoRecep", "&nbsp;").toString()));
                     else
                        out.print("<td class=\"ETabla\">&nbsp;</td>");


                     if (bs.getFieldValue("cDscMotivo").toString().compareTo("null")!=0)
                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMotivo", "&nbsp;").toString()));
                     else
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                     if (bs.getFieldValue("iCveSituacion").toString().compareTo(vParametros.getPropEspecifica("TOXRechazo").toString())==0){
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                     }else{
                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveLoteCualita", "&nbsp;").toString()));
                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cCveAnalisis", "&nbsp;").toString()));
                        if (bs.getFieldValue("lAutorizado").toString().compareTo("1") !=0 ){
                          out.print("<td class=\"ETablaR\">Analizando</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        }
                        else {
                        if (bs.getFieldValue("lPresuntoPost").toString().compareTo("null")!=0){
                            if (bs.getFieldValue("lPresuntoPost").toString().compareTo("1")==0)
                               out.print(vEti.Texto("EResaltaR", "SI"));
                            else
                               out.print(vEti.Texto("ETablaR", "NO"));
                        }
                        else
                            out.print(vEti.Texto("ETablaR", "&nbsp;"));


                        if (bs.getFieldValue("iSustPost").toString().compareTo("null")!=0)
                           out.print(vEti.Texto("ETablaR", bs.getFieldValue("iSustPost", "&nbsp;").toString()));
                        else
                            out.print(vEti.Texto("ETablaR", "&nbsp;"));

                        if (bs.getFieldValue("iSustConf").toString().compareTo("null")!=0)
                            out.print(vEti.Texto("ETablaR", bs.getFieldValue("iSustConf", "&nbsp;").toString()));
                        else
                            out.print(vEti.Texto("ETablaR", "&nbsp;"));

                       if (bs.getFieldValue("iSustPost").toString().compareTo("null")!=0 &&
                           bs.getFieldValue("iSustConf").toString().compareTo("null")!=0 ){
                           if(bs.getFieldValue("iSustPost").toString().compareTo("0")!=0){
                             if(bs.getFieldValue("iSustPost").toString().compareTo(bs.getFieldValue("iSustConf").toString())!=0)
                                out.print(vEti.Texto("ETabla", "RESULTADO PENDIENTE"));
                             else
                                 if (bs.getFieldValue("lResultado").toString().compareTo("1")==0)
                                    out.print(vEti.Texto("EPositivosR", "POSITIVO"));
                                 else
                                    out.print(vEti.Texto("ETabla", "NEGATIVO"));
                             // IMPRESIÓN DE SUSTANCIAS
                             TVTOXCuantAnalisis vCAnal = new TVTOXCuantAnalisis();
                             vCAnal.setiAnio(new Integer(bs.getFieldValue("iAnio","0").toString()));
                             vCAnal.setiCveLaboratorio(new Integer(bs.getFieldValue("iCveLaboratorio","0").toString()));
                             vCAnal.setiCveAnalisis(new Integer(bs.getFieldValue("iCveAnalisis","0").toString()));
                             vCAnal.setiCveSustancia(new Integer(0));
                             out.println(vEti.Texto("ETablaC",clsConfig.getOtrasSust(vCAnal)));
                           }else{
                               if (bs.getFieldValue("lResultado").toString().compareTo("1")==0)
                                  out.print(vEti.Texto("EPositivosR", "POSITIVO"));
                               else
                                  out.print(vEti.Texto("ETabla", "NEGATIVO"));
                               out.println(vEti.Texto("ETablaC","&nbsp;"));
                           }
                       }else{
                           out.print(vEti.Texto("ETabla", "EN PROCESO"));
                           out.print(vEti.Texto("ETabla", "EN PROCESO"));
                       }
                     }
                    }

                   }
                   else{
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        if (bs.getFieldValue("cDscSustancia").toString().compareTo("null")!=0)
                          out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscSustancia", "&nbsp;").toString()));
                        else
                           out.print("<td class=\"ETablaR\">&nbsp;</td>");
                   }
                   out.print("</tr>");
                   if (bs.getFieldValue("iCveMuestra")!=null)
                      cCveMuestra = bs.getFieldValue("iCveMuestra").toString();
               }
                          }
                          else{
                            out.println("<tr>");
                            out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                            out.println("</tr>");
                          }
                          %>
    <td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
