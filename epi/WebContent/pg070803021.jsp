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
<%@ page import="java.util.*"%>

<html>
<%
  pg070803021CFG  clsConfig = new pg070803021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070803021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "ALMSumArt.iCveArticulo|ALMArticulo.cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "ALMSumArt.iCveArticulo|ALMArticulo.cDscBreve|";  // modificar
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
  String cDeleteAction = "Borrar";
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
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">

  <input type="hidden" name="hdIAnio" value="">
  <input type="hidden" name="hdICveAlmacen" value="">
  <input type="hidden" name="hdICveSolicSum" value="">
  <input type="hidden" name="hdDscUniMed" value="">
  <input type="hidden" name="hdDscArea" value="">
  <input type="hidden" name="hdDtSolicitud" value="">
  <input type="hidden" name="hdDtSuministro" value="">
  <input type="hidden" name="hdDscPrioridad" value="">
  <input type="hidden" name="hdDscSolicitud" value="">

  <input type="hidden" name="iCveUniMed" value="<%out.print(request.getParameter("iCveUniMed"));%>">
  <input type="hidden" name="iCveModulo" value="<%out.print(request.getParameter("iCveModulo"));%>">
  <input type="hidden" name="iCveArea" value="<%out.print(request.getParameter("iCveArea"));%>">
  <input type="hidden" name="iAnio" value="<%out.print(request.getParameter("iAnio"));%>">
  <input type="hidden" name="iCvePeriodo" value="<%out.print(request.getParameter("iCvePeriodo"));%>">
  <input type="hidden" name="chkProgExt" value="<%out.print(request.getParameter("chkProgExt"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
     <td valign="top">
                        &nbsp;
                        <input type="hidden" name="hdBoton" value="">
                        <%
                        if(request.getParameter("hdICveSolicSum")!=null && request.getParameter("hdICveSolicSum").compareTo("")!=0 && request.getParameter("hdICveSolicSum").compareTo("null")!=0){
                          out.print("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3'>");
                            out.print("<tr><td colspan='7' class='ETablaT'>Solicitud</td></tr>");
                            out.print("<tr>");
                              out.print("<td class='ETablaT'>No. Solicitud</td>");
                              out.print("<td class='ETablaT'>Unidad</td>");
                              out.print("<td class='ETablaT'>Area</td>");
                              out.print("<td class='ETablaT'>Solicitud</td>");
                              out.print("<td class='ETablaT'>Suministro</td>");
                              out.print("<td class='ETablaT'>Prioridad</td>");
                              out.print("<td class='ETablaT'>Solicitud</td>");
                            out.print("</tr>");
                              out.print("<tr>");

                              out.print("<td class='ETablaR'>" + request.getParameter("hdICveSolicSum") + "</td>");
                              out.print("<td class='ECampo'>" + request.getParameter("hdDscUniMed") + "</td>");
                              out.print("<td class='ECampo'>" + request.getParameter("hdDscArea") + "</td>");
                              out.print("<td class='ETablaC'>" + request.getParameter("hdDtSolicitud") + "</td>");
                              out.print("<td class='ETablaC'>" + request.getParameter("hdDtSuministro") + "</td>");
                              out.print("<td class='ECampo'>" + request.getParameter("hdDscPrioridad") + "</td>");
                              out.print("<td class='ECampo'>" + request.getParameter("hdDscSolicitud") + "</td>");
                              out.print("</tr>");
                          out.print("</table>");
                          }
                          %>
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                           <tr><td colspan="7" class="ETablaT">Detalle de la Solicitud</td></tr>
                            <%

                                    if (bs != null){
                                      bs.start();
                                      int iCveTpoArticuloTemp = -1;
                                      int iCveFamiliaTemp = -1;
                                      while(bs.nextRow()){
                                        if(iCveTpoArticuloTemp != Integer.parseInt(bs.getFieldValue("iCveTpoArticulo").toString()))
                                          out.print("<tr><td class='ETablaT' colspan='6'>Tipo de Artículo: " + bs.getFieldValue("cDscTpoArticulo") + "</td></tr>");
                                        if(iCveFamiliaTemp != Integer.parseInt(bs.getFieldValue("iCveFamilia").toString())){
                                          out.print("<tr><td class='ETablaT' colspan='6'>Familia: " + bs.getFieldValue("cDscFamilia") + "</td></tr>");

                                          out.print("<tr>");
                                            out.print("<td class='ETablaT' rowspan='2'>Clave</td>");
                                            out.print("<td class='ETablaT' rowspan='2'>Descripción Breve</td>");
                                            out.print("<td class='ETablaT' rowspan='2'>Descripción</td>");
                                            out.print("<td class='ETablaT' colspan='2'>Cantidad</td>");
                                            out.print("<td class='ETablaT' rowspan='2'>Unidad</td>");
                                          out.print("</tr>");
                                          out.print("<tr>");
                                            out.print("<td class='ETablaT'>Solicitada</td>");
                                            out.print("<td class='ETablaT'>Autorizada</td>");
                                          out.print("</tr>");
                                        }

                                        out.print("<tr>");
                                          out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveArticulo", "&nbsp;").toString()));
                                          out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscBreve", "&nbsp;").toString()));
                                          out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscArticulo", "&nbsp;").toString()));
                                          out.print(vEti.Texto("ETablaR", bs.getFieldValue("dUnidSolicita", "&nbsp;").toString()));
                                          out.print(vEti.Texto("ETablaR", bs.getFieldValue("dUnidAutor", "&nbsp;").toString()));
                                          out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscUnidad", "&nbsp;").toString()));
                                        out.print("</tr>");

                                        iCveTpoArticuloTemp = Integer.parseInt(bs.getFieldValue("iCveTpoArticulo").toString());
                                        iCveFamiliaTemp = Integer.parseInt(bs.getFieldValue("iCveFamilia").toString());
                                      }
                                    }
                                    else{
                                      out.print("<td class='ECampo' colspan='7'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                    }
                            %>
                          </table><% // Fin de Datos %>
     </td>
      <script language="JavaScript">
      form = document.forms[0];
         if (form.dtSolicitud)
           form.dtSolicitud.readOnly = true;
      </script>
   </tr>
  <%}else{%>
      <script language="JavaScript">
         fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

