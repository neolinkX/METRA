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
  pg070502012CFG  clsConfig = new pg070502012CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502013.jsp";       // modificar
  String cDetalle    = "pg070502013.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|";    // modificar
  String cCveOrdenar  = "iCveDomicilio|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCveDomicilio|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
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
       cClave  = ""+bs.getFieldValue("iCveDomicilio", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdEmpresa" value="<%out.print(request.getParameter("iCveEmpresa"));%>">
  <input type="hidden" name="hdSel" value="">
  <input type="hidden" name="iCveMdoTrans" value="<%if(request.getParameter("iCveMdoTrans")!=null) out.print(request.getParameter("iCveMdoTrans"));%>">
  <input type="hidden" name="iCveUniMed" value="<%if(request.getParameter("iCveUniMed")!=null) out.print(request.getParameter("iCveUniMed"));%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="7" class="ETablaT">Domicilios Fiscales del Transportista
                              </td>
                            </tr>
                          <tr>
                                 <%
                                 TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
                                 TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
                                 Vector vcGRLEmpresas;
                                 vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                 if(request.getParameter("iCveEmpresa")==null || request.getParameter("iCveEmpresa").compareTo("")==0) {
                                   vGRLEmpresas.setCDenominacion("Seleccione...");
                                   vGRLEmpresas.setICveEmpresa(0);
                                   vcGRLEmpresas.add(vGRLEmpresas);
                                 }
                                 out.print(vEti.Texto("ETablaTR", "Transportista:"));
                                 out.println("<td colspan='6'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();", vcGRLEmpresas, "iCveEmpresa", "cDenominacion", request, "0"));
                                 out.println("</td>");
                                 %>
                          </tr>
                            <%
                              // modificar según listado
                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Clave</td>");
                                   out.print("<td class=\"ETablaT\">Domicilio Fiscal</td>");
                                   out.print("<td class=\"ETablaT\">Teléfono</td>");
                                   out.print("<td class=\"ETablaT\">Fax</td>");
                                   out.print("<td class=\"ETablaT\">Correo Electrónico</td>");
                                   out.print("<td class=\"ETablaT\">Actual</td>");
                                   if(clsConfig.getLPagina(cDetalle))
                                     out.print("<td class=\"ETablaT\">Selección</td>");
                                   out.print("</tr>");
                                   bs.start();
                                   int iNRadios = 1;
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveDomicilio", "&nbsp;").toString()));

                                     out.print("<td class='EEtiqueta'>");
                                     vcPais = dPais.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais"));
                                     if (vcPais.size() > 0)
                                       for (int i = 0; i < vcPais.size(); i++)
                                         vPais = (TVPais)vcPais.get(i);
                                     vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado"));
                                     if (vcEntidadFed.size() > 0)
                                       for (int i = 0; i < vcEntidadFed.size(); i++)
                                         vEntidadFed = (TVEntidadFed)vcEntidadFed.get(i);
                                     vcMunicipio = dMunicipio.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado") + " and iCveMunicipio = " + bs.getFieldValue("iCveMunicipio"));
                                     if (vcMunicipio.size() > 0)
                                       for (int i = 0; i < vcMunicipio.size(); i++)
                                         vMunicipio = (TVMunicipio)vcMunicipio.get(i);
                                     out.print("<textarea cols=\"30\" rows=\"3\" name=\"cDireccion\" readOnly>");
                                     out.print("" + bs.getFieldValue("cCalle", "&nbsp;"));
                                     out.print(", " + bs.getFieldValue("cColonia", "&nbsp;"));
                                     out.print(", " + bs.getFieldValue("iCP", "&nbsp;"));
                                     out.print(", " + vMunicipio.getCNombre());
                                     out.print(", " + vEntidadFed.getCNombre());
                                     out.print(", " + vPais.getCNombre());
                                     out.print("</textarea>");
                                     out.print("</td>");

                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cTel", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cFax", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cCorreoElec", "&nbsp;").toString()));

                                     out.print("<td class='ETablaC'>");
                                     if(Integer.parseInt(bs.getFieldValue("lActivo", "&nbsp;").toString())==0){
                                       if (cCanWrite.compareTo("yes") == 0)
                                         out.print("<input type='radio' name='radioDom' OnClick='fRegSel(" + bs.getFieldValue("iCveDomicilio") + ");'>");
                                       else
                                         out.print("<input type='radio' name='radioDom' OnClick='fRegSel(" + bs.getFieldValue("iCveDomicilio") + ");' disabled>");
                                     }
                                     else {
                                       if (cCanWrite.compareTo("yes") == 0)
                                          out.print("<input type='radio' name='radioDom' OnClick='fRegSel(" + bs.getFieldValue("iCveDomicilio") + ");' checked>");
                                       else
                                          out.print("<input type='radio' name='radioDom' OnClick='fRegSel(" + bs.getFieldValue("iCveDomicilio") + ");' checked disabled>");
                                     }
                                     out.print("</td'>");

                                     if(clsConfig.getLPagina(cDetalle)){
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrDetalle('" + bs.getFieldValue("iCveEmpresa") + "', '" + bs.getFieldValue("iCveDomicilio") + "');", ""));
                                       out.print("</td>");
                                     }

                                     out.print("</tr>");
                                     iNRadios++;
                                   }
                               }
                               else{
                                   out.print("<td class='ETablaC' colspan='7'>No existen datos coincidentes con el filtro proporcionado.</td>");
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
