<%/**
 * Title: pg070402012.jsp
 * Description:
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>

<html>
<%
  pg070402012CFG  clsConfig = new pg070402012CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070402012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070402013.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Cve.Vehículo|Matrícula|";    // modificar
  String cCveOrdenar  = "iCveVehiculo|cMatricula|";  // modificar
  String cDscFiltrar  = "Cve.Vehículo|Matrícula|";    // modificar
  String cCveFiltrar  = "iCveVehiculo|cMatricula|";  // modificar
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
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdiAnio" value="<%=request.getParameter("hdiAnio")!=null?request.getParameter("hdiAnio"):"0"%>">
  <input type="hidden" name="hdiCveMdoTrans" value="<%=request.getParameter("hdiCveMdoTrans")!=null?request.getParameter("hdiCveMdoTrans"):"0"%>">
  <input type="hidden" name="hdiIDDGPMPT" value="<%=request.getParameter("hdiIDDGPMPT")!=null?request.getParameter("hdiIDDGPMPT"):"0"%>">
  <input type="hidden" name="hdiCveVehiculo" value="">
  <input type="hidden" name="hdcDscMdoTrasn" value="">
<%-- inicio de los campos para saltar a la pagina pg070402010--%>
  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")!=null?request.getParameter("iAnioSel"):"0"%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=request.getParameter("iCveMdoTransSel")!=null?request.getParameter("iCveMdoTransSel"):"0"%>">
  <input type="hidden" name="iCveUniMedSel" value="<%=request.getParameter("iCveUniMedSel")!=null?request.getParameter("iCveUniMedSel"):"0"%>">
<%-- fin de los campos para saltar a la pagina pg070402010--%>
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="9" class="ETablaT">Vehículos Involucrados en el Accidente</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Año</td>
                              <td class="ETablaT">Modo de Transporte</td>
                              <td class="ETablaT">IDDGPMPT</td>
                              <td class="ETablaT">Cve. Vehículo</td>
                              <td class="ETablaT">Matrícula</td>
                              <td class="ETablaT">Servicio</td>
                              <td class="ETablaT">Origen</td>
                              <td colspan="2" class="ETablaT">Destino</td>

                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iAnio", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMdoTrans", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iIDDGPMPT", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iCveVehiculo", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cMatricula", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscServPrestado", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscPaisOr", "")+" "+ bs.getFieldValue("cDscEdoOr", "")+" "+bs.getFieldValue("cOrigen", "")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscPaisDest", "")+" "+ bs.getFieldValue("cDscEdoDest", "")+" "+ bs.getFieldValue("cDestino", "")));
                                       if (clsConfig.getLPagina(cCatalogo)){
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIr(" + bs.getFieldValue("iAnio","") + "," +
                                                                                                                   bs.getFieldValue("iCveMdoTrans","") + "," +
                                                                                                                   bs.getFieldValue("iIDDGPMPT","") + "," +
                                                                                                                   bs.getFieldValue("iCveVehiculo","") + ",'"+bs.getFieldValue("cDscMdoTrans", "")+"');","Detalle","Detalle"));
                                           out.print("</td>");
                                       }
                                   }
                               }else{
                                 out.print(vEti.TextoCS("ECampo", "No existen datos coincidentes con el filtro proporcionado.",9));
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
