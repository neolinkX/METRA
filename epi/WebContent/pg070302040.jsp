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
  pg070302040CFG  clsConfig = new pg070302040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070302040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070302040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No. de Envío|Clave del Tipo de Envio|";    // modificar
  String cCveOrdenar  = "iCveEnvio|TOXEnvio.iCveTipoEnvio|";  // modificar
  String cDscFiltrar  = "No. de Envío|Clave del Tipo de Envio|";    // modificar
  String cCveFiltrar  = "iCveEnvio|TOXEnvio.iCveTipoEnvio|";  // modificar
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
  String cClave2   = "";
  String cClave3   = "";
  String cClaveA    = "";
  String cClaveB   = "";
  String cClaveC   = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fIrpg070302041(cCampoClave, cCampoClave2, cCampoClave3){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070302041.jsp';
    form.submit();
  }
  function fIrpg070303010(cCampoClaveA, cCampoClaveB, cCampoClaveC){
    form = document.forms[0];
    form.hdCampoClaveA.value = cCampoClaveA;
    form.hdCampoClaveB.value = cCampoClaveB;
    form.hdCampoClaveC.value = cCampoClaveC;
    form.hdRowNum.value = cCampoClaveA;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070303010.jsp';
    form.submit();
  }
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
       cClave2 = ""+bs.getFieldValue("iCveUniMed", "");
       cClave3 = ""+bs.getFieldValue("iCveEnvio", "");
       cClaveA = ""+bs.getFieldValue("iAnio", "");
       cClaveB = ""+bs.getFieldValue("iCveUniMed", "");
       cClaveC = ""+bs.getFieldValue("iCveEnvio", "");

     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClave2%>">
  <input type="hidden" name="hdCampoClave3" value="<%=cClave3%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIAnio" value="">
  <input type="hidden" name="hdICveUniMed" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdCampoClaveA" value="<%=cClaveA%>">
  <input type="hidden" name="hdCampoClaveB" value="<%=cClaveB%>">
  <input type="hidden" name="hdCampoClaveC" value="<%=cClaveC%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <tr>
     <td valign="top">
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Búsqueda del Envío
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
                                TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                out.print(vEti.SelectOneRowSinTD("iCveUniMed","",dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso  = " + iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                out.print("</td>");
                              %>
                            </tr>
                            </table>

                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Envíos
                              </td>
                            </tr>
                          <% if (bs != null){ %>
                            <tr>
                              <td class="ETablaT">Envío</td>
                              <td class="ETablaT">Fecha de Envío</td>
                              <td class="ETablaT">Tipo</td>
                              <td class="ETablaT">Persona que Envía</td>
                              <td class="ETablaT" colspan="3">Observaciones</td>
                            </tr>
                             <% // modificar según listado
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     TVTOXEnvio vTVTOXEnvio = (TVTOXEnvio)bs.getCurrentBean();

                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveEnvio", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETablaR", vTVTOXEnvio.getCdtEnvio()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscTipoEnvio", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cNombre","&nbsp;").toString() + " " + bs.getFieldValue("cApPaterno").toString() + " " + bs.getFieldValue("cApMaterno").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cObsEnvio","&nbsp;").toString()));
                                     out.print("<td class=\"ETabla\">");
                                     out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrpg070302041('" + bs.getFieldValue("iAnio", "") + "', '" + bs.getFieldValue("iCveUniMed", "") + "', '" + bs.getFieldValue("iCveEnvio", "") + "', 'pg070302041.jsp');", ""));
                                     out.print("</td>");
                                     if(bs.getFieldValue("dtRecepcion")!=null && bs.getFieldValue("dtRecepcion","&nbsp;").toString().compareToIgnoreCase("null")!=0 &&
                                        bs.getFieldValue("dtRecepcion","&nbsp;").toString().compareToIgnoreCase("")!=0)
                                       out.print(vEti.Texto("ETabla", "Recibida"));
                                     else{
                                       out.print("<td class=\"ETabla\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Recepción","JavaScript:fIrpg070303010('" + bs.getFieldValue("iAnio", "") + "', '" + bs.getFieldValue("iCveUniMed", "") + "', '" + bs.getFieldValue("iCveEnvio", "") + "', 'pg070302041.jsp');", ""));
                                       out.print("</td>");
                                     }
                                     out.print("</tr>");
                                   }
                              }
                               else{
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                 out.println("</tr>");
                               }
                          %>
                          </table>

      </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
