<%/**
 * Title: Catalogo de Representantes Legales de la Empresas
 * Description: Catalogo de Representantes Legales de la Empresas
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070502015CFG
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="java.text.*" %>

<html>
<%
  pg070502015CFG  clsConfig = new pg070502015CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070502015.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502015.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Representante|RFC|";    // modificar
  String cCveOrdenar  = "CTRRepresentante.iCveRepresentante|CTRRepresentante.cRFC|";  // modificar
  String cDscFiltrar  = "Clave Representante|RFC|";    // modificar
  String cCveFiltrar  = "CTRRepresentante.iCveRepresentante|CTRRepresentante.cRFC|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = ""+bs.getFieldValue("iCveRepresentante", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="iCveRepresentante" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
           <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
           <tr>
               <td colspan="4" class="ETablaT">Representantes Legales del Transportista
           </td>
           </tr>
<%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                Vector vPais = new Vector();
                                Vector vEstado = new Vector();
                                Vector vMunicipio = new Vector();
                                DecimalFormat dfNumber = new DecimalFormat("00000");
                                if (lNuevo){
                                // Nuevo
                                 /*
                                    out.println("<tr><td colspan=\"4\" class=\"ECampo\">Transportista:");
                                    Vector vEmp = new Vector();
                                    TDCTRRepresentante dEmp = new TDCTRRepresentante();
                                    TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    vEmp = dEmp.FindByAllEmprUsu(tvUsu.getICveusuario());
                                    out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "", vEmp, "iCveEmpresa", "cDscEmpresa", request, "0"));
                                    out.println("</td></tr>"); */
                                 TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
                                 TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
                                 Vector vcGRLEmpresas;
                                 vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                 if(request.getParameter("iCveEmpresa")==null || request.getParameter("iCveEmpresa").compareTo("")==0) {
                                   vGRLEmpresas.setCDenominacion("Seleccione...");
                                   vGRLEmpresas.setICveEmpresa(0);
                                   vcGRLEmpresas.add(vGRLEmpresas);
                                 }
                                 out.println("<tr>");
                                 out.print(vEti.Texto("ETablaTR", "Transportista:"));
                                 out.println("<td colspan='6'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "fCambioFiltro();", vcGRLEmpresas, "iCveEmpresa", "cDenominacion", request, "0"));
                                 out.println("</td></tr>");


                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT","Datos Generales del Representante",4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",17,13,"cRFC","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",23,18,"cCURP","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",30,25,"cApPaterno","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",30,25,"cApMaterno","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Nombre(s):","ECampo","text",35,30,3,"cNombre","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",55,50,3,"cPuesto","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT","Domicilio",4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",55,50,3,"cCalle","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",35,30,"cColonia","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,5,"iCP","",0,"","",false,true,lCaptura));
                                    out.println("</tr>");
// Pais
                                       TDPais dPais = new TDPais();
                                       TVPais tvPais = new TVPais();
                                       Vector vPaises = dPais.FindByAll(" order by cNombre");
                                       out.println("<tr>");
                                       out.print(vEti.Texto("EEtiqueta","País:"));
                                       out.println("<td class='ECampo'><SELECT NAME='iCvePais' SIZE='1' onChange=\"llenaSLT(2,this.value,'','',document.forms[0].iCveEstado,document.forms[0].iCveMunicipio);\">");
                                       if (vPaises.size() > 0) {
                                           out.println("<option value='0' selected>Seleccione...</option>");
                                           for (int i = 0; i < vPaises.size(); i++) {
                                               tvPais = (TVPais) vPaises.get(i);
                                               out.println("<option value=\"" + tvPais.getICvePais() + "\">" + tvPais.getCNombre() + "</option>");
                                           }
                                       } else {
                                           out.println("<option value='0'>Datos No Disponibles.</option>");
                                       }
                                       out.println("</SELECT></td>");
// E S T A D O
                                       out.print(vEti.Texto("EEtiqueta","EDO (Estado):"));
                                       out.println("<td class='ECampo'><SELECT NAME='iCveEstado' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio,'');\">");
                                       out.println("<option value='-1'>Seleccione...</option>");
                                       out.println("</SELECT>");
                                       out.println("</td></tr>");
// M U N I C I P I O
                                       out.println("<tr>");
                                       out.print(vEti.Texto("EEtiqueta","Delegación o MUN (Municipio):"));
                                       out.println("<td class='ECampo'><SELECT NAME='iCveMunicipio' SIZE='1'>");
                                       out.println("<option value='-1'>Seleccione...</option>");
                                       out.println("</SELECT></td>");

                                    out.print(vEti.EtiCampo("EEtiqueta","Teléfono:","ECampo","text",25,20,"cTel","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fax:","ECampo","text",25,20,"cFax","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Correo Electrónico:","ECampo","text",55,50,"cCorreoElec","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                    out.println("<td colspan=\"3\">");
                                    out.println("<textarea cols=\"115\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\"></textarea>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                } else {
                                   if (lCaptura) {
                                   // Modificar
 /*                                      out.println("<tr><td colspan=\"4\" class=\"ECampo\">Transportista:");
                                   Vector vEmp = new Vector();
                                       TDCTRRepresentante dEmp = new TDCTRRepresentante();
                                       TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                       vEmp = dEmp.FindByAllEmprUsu(tvUsu.getICveusuario());
                                       out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "", vEmp, "iCveEmpresa", "cDscEmpresa", request, "0"));
                                       out.println("</td></tr>");
*/

                                 TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
                                 TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
                                 Vector vcGRLEmpresas;
                                 vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                 if(request.getParameter("iCveEmpresa")==null || request.getParameter("iCveEmpresa").compareTo("")==0) {
                                   vGRLEmpresas.setCDenominacion("Seleccione...");
                                   vGRLEmpresas.setICveEmpresa(0);
                                   vcGRLEmpresas.add(vGRLEmpresas);
                                 }
                                 out.println("<tr>");
                                 out.print(vEti.Texto("ETablaTR", "Transportista:"));
                                 out.println("<td colspan='6'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "fCambioFiltro();", vcGRLEmpresas, "iCveEmpresa", "cDenominacion", request, "0"));
                                 out.println("</td></tr>");


                                       out.println("<tr>");
                                       out.print(vEti.TextoCS("ETablaT","Datos Generales del Representante",4));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",17,13,"cRFC",bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",23,18,"cCURP",bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",30,25,"cApPaterno",bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",30,25,"cApMaterno",bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Nombre(s):","ECampo","text",35,30,3,"cNombre",bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",55,50,3,"cPuesto",bs.getFieldValue("cPuesto","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.TextoCS("ETablaT","Domicilio:",4));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",55,50,3,"cCalle",bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",35,30,"cColonia",bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       //out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,5,"iCP",bs.getFieldValue("iCP","&nbsp;").toString(),0,"","",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,5,"iCP","" + dfNumber.format(new Double(bs.getFieldValue("iCP","&nbsp;").toString())),0,"","",false,true,lCaptura));
                                       out.println("</tr>");

// P A I S
                                       TDPais dPais = new TDPais();
                                       TVPais tvPais = new TVPais();
                                       Vector vPaises = dPais.FindByAll();
                                       out.println("<tr>");
                                       out.print(vEti.Texto("EEtiqueta","País:"));
                                       out.println("<td class='ECampo'><SELECT NAME='iCvePais' SIZE='1' onChange=\"llenaSLT(2,this.value,'','',document.forms[0].iCveEstado,document.forms[0].iCveMunicipio);\">");
                                       int iPais = 0;
                                       if (bs.getFieldValue("iCvePais") != null && !bs.getFieldValue("iCvePais").equals(""))
                                           iPais = Integer.parseInt(bs.getFieldValue("iCvePais").toString());
                                       if (vPaises.size() > 0) {
                                           out.println("<option value='0'>Seleccione...</option>");
                                           for (int i = 0; i < vPaises.size(); i++) {
                                               tvPais = (TVPais) vPaises.get(i);
                                               if (iPais == tvPais.getICvePais())
                                                   out.println("<option value=\"" + tvPais.getICvePais() + "\" selected>" + tvPais.getCNombre() + "</option>");
                                               else
                                                   out.println("<option value=\"" + tvPais.getICvePais() + "\">" + tvPais.getCNombre() + "</option>");
                                           }
                                       } else {
                                           out.println("<option value='0'>Datos No Disponibles.</option>");
                                       }
                                       out.println("</SELECT></td>");

// E S T A D O
                                       TDEntidadFed dEdo = new TDEntidadFed();
                                       int cEdo = 0;
                                       Vector vEdos = new Vector();
                                       TVEntidadFed tvEnti = new TVEntidadFed();
                                       if (bs.getFieldValue("iCvePais") != null && !bs.getFieldValue("iCvePais").equals("")) {
                                           vEdos = dEdo.FindByAll(" where iCvePais=" + bs.getFieldValue("iCvePais"));
                                           cEdo = Integer.parseInt(bs.getFieldValue("iCvePais").toString());
                                       } else
                                           vEdos = dEdo.FindByAll("");

                                       out.print(vEti.Texto("EEtiqueta","EDO (Estado):"));
                                       out.println("<td class='ECampo'><SELECT NAME='iCveEstado' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCvePais.value,this.value,'',document.forms[0].iCveMunicipio,'');\">");
                                       if (vEdos.size() > 0) {
                                           out.println("<option value='0'>Seleccione...</option>");
                                           for (int i = 0; i < vEdos.size(); i++) {
                                               tvEnti = (TVEntidadFed) vEdos.get(i);
                                                if (cEdo == tvEnti.getICveEntidadFed())
                                                   out.println("<option value=\"" + tvEnti.getICveEntidadFed() + "\" selected>" + tvEnti.getCNombre() + "</option>");
                                               else
                                                   out.println("<option value=\"" + tvEnti.getICveEntidadFed() + "\">" + tvEnti.getCNombre() + "</option>");
                                           }
                                       }
                                       out.println("</SELECT>");
                                       out.println("</td></tr>");

// M U N I C I P I O
                                       TDMunicipio dMunicipio = new TDMunicipio();
                                       int iMunicipio = 0;
                                       TVMunicipio tvMun = new TVMunicipio();
                                       Vector vMunicipios = new Vector();

                                       if (bs.getFieldValue("iCveMunicipio") != null && !bs.getFieldValue("iCveMunicipio").equals(""))
                                           iMunicipio = Integer.parseInt(bs.getFieldValue("iCveMunicipio").toString());

                                       vMunicipios = dMunicipio.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "0") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado", "0"));
                                       out.println("<tr>");
                                       out.print(vEti.Texto("EEtiqueta","Delegación o MUN (Municipio):"));
                                       out.println("<td class='ECampo'><SELECT NAME='iCveMunicipio' SIZE='1'>");
                                       if (vMunicipios.size() > 0) {
                                           out.println("<option value='0'>Seleccione...</option>");
                                           for (int i = 0; i < vMunicipios.size(); i++) {
                                               tvMun = (TVMunicipio) vMunicipios.get(i);
                                                if (iMunicipio == tvMun.getICveMunicipio())
                                                   out.println("<option value=\"" + tvMun.getICveMunicipio() + "\" selected>" + tvMun.getCNombre() + "</option>");
                                               else
                                                   out.println("<option value=\"" + tvMun.getICveMunicipio() + "\">" + tvMun.getCNombre() + "</option>");
                                           }
                                       }
                                       out.println("</SELECT>");

                                       out.print(vEti.EtiCampo("EEtiqueta","Teléfono:","ECampo","text",25,20,"cTel",bs.getFieldValue("cTel","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Fax:","ECampo","text",25,20,"cFax",bs.getFieldValue("cFax","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta","Correo Electrónico:","ECampo","text",55,50,"cCorreoElec",bs.getFieldValue("cCorreoElec","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");

                                       out.println("<tr>");
                                       out.println("<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                       out.print("<td class='EEtiqueta' colspan=\"3\">");
                                       out.print("<textarea cols=\"115\" rows=\"5\" name=\"cObservacion\" OnKeyPress=\"fChgArea(this);\" OnChange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                                       out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                       out.print("</textarea>");
                                       out.print("</td>");
                                       out.println("</tr>");

                                   } else {
                                      if (bs != null) {
                                      // Solo Mostrar Datos
/*
                                          out.println("<tr><td colspan=\"4\" class=\"ECampo\">Transportista:");
                                          Vector vEmp = new Vector();
                                          TDCTRRepresentante dEmp = new TDCTRRepresentante();
                                          TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                          vEmp = dEmp.FindByAllEmprUsu(tvUsu.getICveusuario());
                                          out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "fCambioFiltro();", vEmp, "iCveEmpresa", "cDscEmpresa", request, "0"));
                                          out.println("</td></tr>");
*/
                                 TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
                                 TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
                                 Vector vcGRLEmpresas;
                                 vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                 if(request.getParameter("iCveEmpresa")==null || request.getParameter("iCveEmpresa").compareTo("")==0) {
                                   vGRLEmpresas.setCDenominacion("Seleccione...");
                                   vGRLEmpresas.setICveEmpresa(0);
                                   vcGRLEmpresas.add(vGRLEmpresas);
                                 }
                                 out.println("<tr>");
                                 out.print(vEti.Texto("ETablaTR", "Transportista:"));
                                 out.println("<td colspan='6'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "fCambioFiltro();", vcGRLEmpresas, "iCveEmpresa", "cDenominacion", request, "0"));
                                 out.println("</td></tr>");


                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Clave Transportista:","ECampo","text",4,4,3,"iCveEmpresa", bs.getFieldValue("iCveEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta","ID DGPMPT:","ECampo","text",4,4,"iIDDGPMPT", bs.getFieldValue("iIDDGPMPT","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.print(vEti.EtiCampo("EEtiqueta","ID Modo de Transporte:","ECampo","text",4,4,"iIDMdoTrans", bs.getFieldValue("iIDMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Modo de Transporte:","ECampo","text",4,4,3,"cDscMdoTrans", bs.getFieldValue("cDscMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.TextoCS("ETablaT","Datos Generales del Representante",4));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Clave del Representante:","ECampo","text",4,4,3,"iCveRepresentante", bs.getFieldValue("iCveRepresentante","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",17,13,"cRFC", bs.getFieldValue("cRFC","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.print(vEti.EtiCampo("EEtiqueta","CURP:","ECampo","text",23,18,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",30,25,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",30,25,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Nombre(s):","ECampo","text",35,30,3,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Puesto:","ECampo","text",55,50,3,"cPuesto", bs.getFieldValue("cPuesto","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.TextoCS("ETablaT","Domicilio:",4));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampoCS("EEtiqueta","Calle:","ECampo","text",55,50,3,"cCalle", bs.getFieldValue("cCalle","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta","Colonia:","ECampo","text",35,30,"cColonia", bs.getFieldValue("cColonia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          //out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"iCP", bs.getFieldValue("iCP","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.print(vEti.EtiCampo("EEtiqueta","C.P.:","ECampo","text",10,10,"iCP", "" + dfNumber.format(new Double(bs.getFieldValue("iCP","&nbsp;").toString())),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta","País:","ECampo","text",10,10,"cMunicipio", bs.getFieldValue("cPais","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.print(vEti.EtiCampo("EEtiqueta","EDO (Estado):","ECampo","text",10,10,"cMunicipio", bs.getFieldValue("cDscEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta","Delegación o MUN (Municipio):","ECampo","text",10,10,"cMunicipio", bs.getFieldValue("cMunicipio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.print(vEti.EtiCampo("EEtiqueta","Teléfono:","ECampo","text",25,20,"cTel", bs.getFieldValue("cTel","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta","Fax:","ECampo","text",25,20,"cFax", bs.getFieldValue("cFax","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.print(vEti.EtiCampo("EEtiqueta","Correo Electrónico:","ECampo","text",55,50,"cCorreoElec", bs.getFieldValue("cCorreoElec","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                          out.println("</tr>");

                                          out.println("<tr>");
                                          out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                          out.print("<td class='EEtiqueta' colspan=\"3\">");
                                          out.print("<textarea cols=\"115\" rows=\"5\" name=\"cObservacion\" readOnly>");
                                          out.print(""+bs.getFieldValue("cObservacion", "&nbsp;"));
                                          out.print("</textarea>");
                                          out.print("</td>");
                                          out.println("</tr>");


                                      } else {
/*                                          out.println("<tr><td colspan=\"4\" class=\"ECampo\">Transportista:");
                                          Vector vEmp = new Vector();
                                          TDCTRRepresentante dEmp = new TDCTRRepresentante();
                                          TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                          vEmp = dEmp.FindByAllEmprUsu(tvUsu.getICveusuario());
                                          out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "fCambioFiltro();", vEmp, "iCveEmpresa", "cDscEmpresa", request, "0"));
                                          out.println("</td></tr>");
*/
                                 TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
                                 TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
                                 Vector vcGRLEmpresas;
                                 vcGRLEmpresas = dGRLEmpresas.FindByAll(" order by iCveEmpresa ");
                                 if(request.getParameter("iCveEmpresa")==null || request.getParameter("iCveEmpresa").compareTo("")==0) {
                                   vGRLEmpresas.setCDenominacion("Seleccione...");
                                   vGRLEmpresas.setICveEmpresa(0);
                                   vcGRLEmpresas.add(vGRLEmpresas);
                                 }
                                 out.println("<tr>");
                                 out.print(vEti.Texto("ETablaTR", "Transportista:"));
                                 out.println("<td colspan='6'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "fCambioFiltro();", vcGRLEmpresas, "iCveEmpresa", "cDenominacion", request, "0"));
                                 out.println("</td></tr>");


                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                          out.println("</tr>");
                                      }
                                   }
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