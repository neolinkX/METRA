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
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070502016CFG  clsConfig = new pg070502016CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502016.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502016.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave del Servicio|Descripción del Servicio|";    // modificar
  String cCveOrdenar  = "iCveServPrestado|cDscServPrestado|";  // modificar
  String cDscFiltrar  = "Clave del Servicio|Descripción del Servicio|";    // modificar
  String cCveFiltrar  = "iCveServPrestado|cDscServPrestado|";  // modificarPrestado
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

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();   //"Si";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  TDPais dPais = new TDPais();
  TVPais vPais = new TVPais();
  Vector vcPais = new Vector();

  TDEntidadFed dEntidadFed = new TDEntidadFed();
  TVEntidadFed vEntidadFed = new TVEntidadFed();
  Vector vcEntidadFed = new Vector();

  TDMunicipio dMunicipio = new TDMunicipio();
  TVMunicipio vMunicipio = new TVMunicipio();
  Vector vcMunicipio = new Vector();

  TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
  TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
  Vector vcGRLEmpresas;
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
       cClave  = ""+bs.getFieldValue("iCveServPrestado", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="iCveMdoTrans" value="<%if(request.getParameter("iCveMdoTrans")!=null) out.print(request.getParameter("iCveMdoTrans"));%>">
  <input type="hidden" name="iCveUniMed" value="<%if(request.getParameter("iCveUniMed")!=null) out.print(request.getParameter("iCveUniMed"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="4" class="ETablaT">Servicio Prestado por el Transportista
                              </td>
                              <%
                              out.print("<tr><td class='ETablaTR'colspan='2'>Transportista:</td><td class='ETabla' colspan='2'>");
                                out.print("<select name=\"iCveEmpresa\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\" >");
                                  vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                  if (vcGRLEmpresas.size() > 0){
                                    for (int i = 0; i < vcGRLEmpresas.size(); i++){
                                      vGRLEmpresas = (TVGRLEmpresas)vcGRLEmpresas.get(i);
                                      if (request.getParameter("iCveEmpresa")!=null && request.getParameter("iCveEmpresa").compareToIgnoreCase(new Integer(vGRLEmpresas.getICveEmpresa()).toString())==0)
                                        out.print("<option value = " + vGRLEmpresas.getICveEmpresa() + " selected>" + vGRLEmpresas.getCDenominacion() + "</option>");
                                      else
                                        out.print("<option value = " + vGRLEmpresas.getICveEmpresa() + ">" + vGRLEmpresas.getCDenominacion() + "</option>");
                                    }
                                  }
                                  else{
                                    out.print("<option value = 0>Datos no disponibles...</option>");
                                  }
                               out.print("</select></td></tr>");

                                 vcGRLEmpresas = dGRLEmpresas.FindByAll2(" where iCveEmpresa = " + request.getParameter("iCveEmpresa") + " order by iCveEmpresa ");
                                 if (vcGRLEmpresas.size() > 0){
                                   for (int i = 0; i < vcGRLEmpresas.size(); i++)
                                     vGRLEmpresas = (TVGRLEmpresas)vcGRLEmpresas.get(i);

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Id. DGPMPT:</td>");
                                     out.print("<td class='ECampo'>" + vGRLEmpresas.getcIDDGPMPT() + "</td>");
                                     out.print("<td class='EEtiqueta'>Id. Modo de Transporte:</td>");
                                     out.print("<td class='ECampo'>" + vGRLEmpresas.getICveMdoTrans() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Modo de Transporte:</td>");
                                     out.print("<td  colspan='3' class='ECampo'>" + vGRLEmpresas.getCDscMdoTrans() + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='ETablaT' colspan='4'>Servicios Prestados</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                     out.print("<td class='ETablaT' colspan='2'>Servicio</td>");
                                     out.print("<td class='ETablaT' colspan='2'>Asignar</td>");
                                   out.print("</tr>");
                                 }
                                 if (bs != null){
                                   bs.start();
                                   int iChk = 1;
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                       out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("cDscServPrestado") + "</td>");

                                       TVCTRServEmpresa vCTRServEmpresa = new TVCTRServEmpresa();
                                       TDCTRServEmpresa dCTRServEmpresa = new TDCTRServEmpresa();
                                       Vector vcCTRServEmpresa = new Vector();
                                       vcCTRServEmpresa = dCTRServEmpresa.FindByAll(" where iCveEmpresa = " + request.getParameter("iCveEmpresa") + " and iCveServPrestado = " + bs.getFieldValue("iCveServPrestado"), "");
                                       if(vcCTRServEmpresa.size()>0){
                                         if (cCanWrite.compareTo("yes") == 0)
                                            out.print("<td colspan='2' align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveServPrestado") + "' name='chk" + iChk + "' checked></td>");
                                         else
                                            out.print("<td colspan='2' align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveServPrestado") + "' name='chk" + iChk + "' checked disabled></td>");
                                       }
                                       else {
                                         if (cCanWrite.compareTo("yes") == 0)
                                           out.print("<td colspan='2' align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveServPrestado") + "' name='chk" + iChk + "'></td>");
                                         else
                                           out.print("<td colspan='2' align='center'><input type='checkbox' value='" + bs.getFieldValue("iCveServPrestado") + "' name='chk" + iChk + "' disabled></td>");

                                       }
                                       out.print("<input type='hidden' value='" + bs.getFieldValue("iCveServPrestado") + "' name='hdChk" + iChk + "'></td>");
                                     out.print("</tr>");
                                     iChk++;
                                   }
                                   out.print("<input type='hidden' name='hdTotalReg' value='" + iChk + "'>");
                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
                                 }
                            %>
                          </table><% // Fin de Datos %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>