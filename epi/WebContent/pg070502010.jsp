<%/**
 * Title: Listado de Empresas
 * Description: Listado de Empresas
 * Copyright: Copyright (c) 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070502010CFG
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>

<html>
<%
  pg070502010CFG  clsConfig = new pg070502010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "ID DGPMPT|ID Modo de Transporte|RFC|Razón Social|";    // modificar
  String cCveOrdenar  = "cIDDGPMPT|iIDMdoTrans|cRFC|cDscEmpresa|";  // modificar
  String cDscFiltrar  = "ID DGPMPT|ID Modo de Transporte|RFC|Razón Social|";    // modificar
  String cCveFiltrar  = "cIDDGPMPT|iIDMdoTrans|cRFC|cDscEmpresa|";  // modificar
  String cTipoFiltrar = "8|7|8|8|";                // modificar
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

  function fCambioFiltro() {
     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.target         = 'FRMDatos';
     form.action         = 'pg070502010.jsp';
     form.submit();
  }
  function fDetalle(iCveEmp, iPag) {
     var cPag = '';
     if (iPag == 1)
        cPag = 'pg070502011.jsp';
     if (iPag == 2)
        cPag = 'pg070502012.jsp';
     if (iPag == 3)
        cPag = 'pg070502014.jsp';
     if (iPag == 4)
        cPag = 'pg070502016.jsp';

     form = document.forms[0];
     form.hdBoton.value  = 'Actual';
     form.iCveEmpresa.value = iCveEmp;
     form.hdRowNum.value = iCveEmp;
     form.target         = 'FRMDatos';
     form.action         = cPag;
     form.submit();
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
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveEmpresa" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="10" class="ETablaT">Transportistas
                              </td>
                            </tr>
                            <tr>
                              <td colspan="10" class="ECampo">Transportistas del Modo de Transporte
<%
//Conexion con otras paginas
                 if (request.getParameter("SLSUniMed") != null && request.getParameter("SLSUniMed") != "")
                      out.print("<input type=\"hidden\" name=\"SLSUniMed\" value=\"" + request.getParameter("SLSUniMed") + "\">");
                 else
                      out.print("<input type=\"hidden\" name=\"SLSUniMed\" value=\"\">");

                 if (request.getParameter("SLSMdoTransporte") != null && request.getParameter("SLSMdoTransporte") != "")
                      out.print("<input type=\"hidden\" name=\"SLSMdoTransporte\" value=\"" + request.getParameter("SLSMdoTransporte")  + "\">");
                 else
                      out.print("<input type=\"hidden\" name=\"SLSMdoTransporte\" value=\"\">");

                 if (request.getParameter("hdIni") != null && request.getParameter("hdIni") != "")
                      out.print("<input type=\"hidden\" name=\"hdIni\" value=\"" + request.getParameter("hdIni") + "\">");
                 else
                      out.print("<input type=\"hidden\" name=\"hdIni\" value=\"\">");

                 out.print("<input type=\"hidden\" name=\"hdIni2\" value=\"\">");

// Esto ya es de la pagina
                                  Vector vMdoT = new Vector();
                                  TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
                                  TVGRLMdoTrans tvMdoTrans = new TVGRLMdoTrans();

                                  // Modo de Transporte
                                  vMdoT = dMdoTrans.findByAll("");
                                  tvMdoTrans.setCDscMdoTrans("Todos");
                                  tvMdoTrans.setICveMdoTrans(0);
                                  vMdoT.add(tvMdoTrans);
                                  out.println(vEti.SelectOneRowSinTD("iCveMdoTrans", "fCambioFiltro();", vMdoT, "iCveMdoTrans", "cDscMdoTrans", request, "0"));

                                  // Unidades Médicas
                                  out.println(" controladas por la Unidad Médica ");
//                                  out.println(vEti.SelectOneRowSinTD("iCveUniMed", "fCambioFiltro();", clsConfig.labs, "iCveUniMed", "cDscUniMed", request, "0"));
                                  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                  Vector vUnidades = new Vector();
                                  String cvUnidades = "";
                                  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                                  vUnidades = vUsuario.getVUniFiltro(iCveProceso);

                                  out.print("<select name=\"iCveUniMed\" onChange=\"fCambioFiltro();\">");
                                  if (request.getParameter("iCveUniMed") != null){
                                    if (request.getParameter("iCveUniMed") != "")
                                       cvUnidades = request.getParameter("iCveUniMed");
                                  }
                                  if (cvUnidades.compareToIgnoreCase("") == 0 || cvUnidades.compareToIgnoreCase("0") == 0)
                                    out.print("<option selected value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  else
                                    out.print("<option value=\"" + "0"  + "\">" + "Sin Asignar" + "</option>");
                                  if (!vUnidades.isEmpty()){
                                    for (int i=0;i<vUnidades.size();i++){
                                      TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                                      VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                                      if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                                         out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                      else
                                         out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                                    }
                                  }
                                  out.print("</select>");
%>
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">ID DGPMPT</td>
                              <td class="ETablaT">Id Modo de Transporte</td>
                              <td class="ETablaT">RFC</td>
                              <td class="ETablaT">Tipo Persona</td>
                              <td class="ETablaT">Razón Social</td>
                              <td class="ETablaT" colspan="5">Registro</td>
                            </tr>
                             <% // modificar según listado

                               if (bs != null){
                                   bs.start();
                                   TFechas f = new TFechas();
                                   TVGRLEmpresas tvEm = new TVGRLEmpresas();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cIDDGPMPT", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("iIDMdoTrans", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cRFC", "&nbsp;").toString()));
                                     String tipo = "&nbsp;";
                                     if (bs.getFieldValue("cTpoPersona").toString().equals("F"))
                                        tipo = "FÍSICA";
                                     if (bs.getFieldValue("cTpoPersona").toString().equals("M"))
                                        tipo = "MORAL";
                                     out.print(vEti.Texto("ECampo", tipo));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDenominacion", "&nbsp;").toString()));
                                     tvEm = (TVGRLEmpresas) bs.getCurrentBean();
                                     out.print(vEti.Texto("ECampo", f.getFechaDDMMYYYY(tvEm.getDtRegistro(), "/")));

                                     // Ver si tiene permisos para ir a las paginas
                                     if (clsConfig.getLPagina("pg070502011.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEmpresa") + ", 1);\">Detalle</a></td>");
                                     if (clsConfig.getLPagina("pg070502012.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEmpresa") + ", 2);\">Domicilio Fiscal</a></td>");
                                     if (clsConfig.getLPagina("pg070502014.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEmpresa") + ", 3);\">Representante</a></td>");
                                     if (clsConfig.getLPagina("pg070502016.jsp"))
                                         out.print("<td><a href=\"JavaScript:fDetalle(" + bs.getFieldValue("iCveEmpresa") + ", 4);\">Servicios</a></td>");
                                     out.print("</tr>");
                                   }
                               } else {
                                   // NO HAY REGISTROS
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
