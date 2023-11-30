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
<%@ page import="java.util.*" %>

<html>
<%
  pg070603010CFG  clsConfig = new pg070603010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070603010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070603010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "EQMEquipo.iCveEquipo|cDscEquipo|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "EQMEquipo.iCveEquipo|cDscEquipo|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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

  String cCalEqProceso = vParametros.getPropEspecifica("CalEqProceso");

  TVUsuario vUsuario;
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
  <input type="hidden" name="lMostrarDatos" value="">
  <input type="hidden" name="lProgramar" value="">
  <input type="hidden" name="hdUser" value="<%vUsuario = (TVUsuario)request.getSession(true).getAttribute("UsrID"); out.print(vUsuario.getICveusuario());%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
              <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <tr>
              <td colspan="9" class="ETablaT">Programación de Mantenimiento
              </td>
              </tr>
              <%

// T I P O   D E   M A N T E N I M I E N T O

              out.print("<tr>");
                out.print("<td class='ETablaTR'>Tipo de Mantenimiento:</td>");
                out.print("<td class='ETabla' colspan='2'>");
                out.print("<select name='iCveTpoMantto'>");
                TDEQMTpoMantto dEQMTpoMantto = new TDEQMTpoMantto();
                TVEQMTpoMantto vEQMTpoMantto = new TVEQMTpoMantto();
                Vector vcEQMTpoMantto = new Vector();
                vcEQMTpoMantto = dEQMTpoMantto.FindByAll(" where lActivo = 1 ", " order by iCveTpoMantto ");
                if (vcEQMTpoMantto.size() > 0){
                  if(request.getParameter("iCveTpoMantto")==null || request.getParameter("iCveTpoMantto").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveTpoMantto")!=null && Integer.parseInt(request.getParameter("iCveTpoMantto"))<1)
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcEQMTpoMantto.size(); i++){
                    vEQMTpoMantto = (TVEQMTpoMantto)vcEQMTpoMantto.get(i);
                    if (request.getParameter("iCveTpoMantto")!=null && request.getParameter("iCveTpoMantto").compareToIgnoreCase(new Integer(vEQMTpoMantto.getICveTpoMantto()).toString())==0)
                      out.print("<option value = " + vEQMTpoMantto.getICveTpoMantto() + " selected>" + vEQMTpoMantto.getCDscBreve() + "</option>");
                    else
                      out.print("<option value = " + vEQMTpoMantto.getICveTpoMantto() + ">" + vEQMTpoMantto.getCDscBreve() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
                out.print("</select>");
                out.print("</td>");

// M O T I V O   D E   M A N T E N I M I E N T O

                out.print("<td class='ETablaTR'>Motivo de Mantenimiento:</td>");
                out.print("<td class='ETabla' colspan='2'>");
                out.print("<select name='iCveMotivo'>");
                TDGRLMotivo dGRLMotivo = new TDGRLMotivo();
                TVGRLMotivo vGRLMotivo = new TVGRLMotivo();
                Vector vcGRLMotivo = new Vector();
                vcGRLMotivo = dGRLMotivo.FindByAll(" where iCveProceso = " + cCalEqProceso + " ");
                if (vcGRLMotivo.size() > 0){
                  if(request.getParameter("iCveMotivo")==null || request.getParameter("iCveMotivo").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveMotivo")!=null && Integer.parseInt(request.getParameter("iCveMotivo"))<1)
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcGRLMotivo.size(); i++){
                    vGRLMotivo = (TVGRLMotivo)vcGRLMotivo.get(i);
                    if (request.getParameter("iCveMotivo")!=null && request.getParameter("iCveMotivo").compareToIgnoreCase(new Integer(vGRLMotivo.getICveMotivo()).toString())==0)
                      out.print("<option value = " + vGRLMotivo.getICveMotivo() + " selected>" + vGRLMotivo.getCDscMotivo() + "</option>");
                    else
                      out.print("<option value = " + vGRLMotivo.getICveMotivo() + ">" + vGRLMotivo.getCDscMotivo() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
                out.print("</select>");
                out.print("</td>");

                out.print("<td class='ETablaTR'>Programado para:</td>");
                if(request.getParameter("dtProgramado")!=null)
                  out.print("<td colspan='2'><input type='text' size='10' maxlength='10' name='dtProgramado' value='" + request.getParameter("dtProgramado") + "'>");
                else
                  out.print("<td colspan='2'><input type='text' size='10' maxlength='10' name='dtProgramado' value=''>");
                out.print("<a class='EAncla' name='calendario' href='JavaScript:fLoadCal(5,8,2004,document.forms[0].dtProgramado);'>(dd/mm/aaaa)");
                out.print("</a></td>");
              out.print("</tr>");

              out.print("<tr>");
                out.print("<td class='ETablaT' colspan='9'>Filtrado de Equipos</td>");
              out.print("</tr>");

              out.print("<tr>");
                out.print("<td class='ETablaTL' colspan='9'>Para:</td>");
              out.print("</tr>");

// C L A S I F I C A C I O N

              out.print("<tr>");
                if(request.getParameter("chkClasificacion")!=null){
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkClasificacion' OnClick='fActClasificacion();' checked>Clasificación:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.print("<select name='iCveClasificacion' onChange=\"llenaSLT(3,this.value,'','',document.forms[0].iCveTpoEquipo);\">");
                }
                else{
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkClasificacion' OnClick='fActClasificacion();'>Clasificación:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.print("<select name='iCveClasificacion' disabled onChange=\"llenaSLT(3,this.value,'','',document.forms[0].iCveTpoEquipo);\">");
                }
                TDEQMClasificacion dEQMClasificacion = new TDEQMClasificacion();
                TVEQMClasificacion vEQMClasificacion = new TVEQMClasificacion();
                Vector vcEQMClasificacion = new Vector();
                vcEQMClasificacion = dEQMClasificacion.FindByAll("", " order by iCveClasificacion ");
                if (vcEQMClasificacion.size() > 0){
                  if(request.getParameter("iCveClasificacion")==null || request.getParameter("iCveClasificacion").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveClasificacion")!=null && Integer.parseInt(request.getParameter("iCveClasificacion"))<1)
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcEQMClasificacion.size(); i++){
                    vEQMClasificacion = (TVEQMClasificacion)vcEQMClasificacion.get(i);
                    if (request.getParameter("iCveClasificacion")!=null && request.getParameter("iCveClasificacion").compareToIgnoreCase(new Integer(vEQMClasificacion.getICveClasificacion()).toString())==0)
                      out.print("<option value = " + vEQMClasificacion.getICveClasificacion() + " selected>" + vEQMClasificacion.getCDscBreve() + "</option>");
                    else
                      out.print("<option value = " + vEQMClasificacion.getICveClasificacion() + ">" + vEQMClasificacion.getCDscBreve() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
                out.print("</select>");
                out.print("</td>");

// T I P O   D E   E Q U I P O

                if(request.getParameter("chkTpoEquipo")!=null){
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkTpoEquipo' OnClick='fActTpoEquipo();' checked>Tipo de Equipo:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.println("<select name=\"iCveTpoEquipo\">");
                }
                else{
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkTpoEquipo' OnClick='fActTpoEquipo();'>Tipo de Equipo:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.println("<select name=\"iCveTpoEquipo\" disabled>");
                }
                if(request.getParameter("iCveClasificacion")!= null){
                  TDEQMTpoEquipo dEQMTpoEquipo = new TDEQMTpoEquipo();
                  TVEQMTpoEquipo vEQMTpoEquipo = new TVEQMTpoEquipo();
                  Vector vcEQMTpoEquipo = new Vector();
                  vcEQMTpoEquipo = dEQMTpoEquipo.FindByAll(" where iCveClasificacion = " + request.getParameter("iCveClasificacion"), " order by iCveTpoEquipo ");
                  if (vcEQMTpoEquipo.size() > 0){
                    for (int i = 0; i < vcEQMTpoEquipo.size(); i++){
                      vEQMTpoEquipo = (TVEQMTpoEquipo)vcEQMTpoEquipo.get(i);
                      if (request.getParameter("iCveTpoEquipo")!=null && request.getParameter("iCveTpoEquipo").compareToIgnoreCase(new Integer(vEQMTpoEquipo.getICveTpoEquipo()).toString())==0)
                        out.print("<option value = " + vEQMTpoEquipo.getICveTpoEquipo() + " selected>" + vEQMTpoEquipo.getCDscBreve() + "</option>");
                      else
                        out.print("<option value = " + vEQMTpoEquipo.getICveTpoEquipo() + ">" + vEQMTpoEquipo.getCDscBreve() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveClasificacion")!=null && Integer.parseInt(request.getParameter("iCveClasificacion").toString())<1) || request.getParameter("iCveClasificacion")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");

// M A R C A

                if(request.getParameter("chkMarca")!=null){
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkMarca' OnClick='fActMarca();' checked>Marca:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.print("<select name='iCveMarca'>");
                }
                else{
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkMarca' OnClick='fActMarca();'>Marca:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.print("<select name='iCveMarca' disabled>");
                }
                TDEQMMarca dEQMMarca = new TDEQMMarca();
                TVEQMMarca vEQMMarca = new TVEQMMarca();
                Vector vcEQMMarca = new Vector();
                vcEQMMarca = dEQMMarca.FindByAll("", " order by iCveMarca ");
                if (vcEQMMarca.size() > 0){
                  if(request.getParameter("iCveMarca")==null || request.getParameter("iCveMarca").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveMarca")!=null && Integer.parseInt(request.getParameter("iCveMarca"))<1)
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcEQMMarca.size(); i++){
                    vEQMMarca = (TVEQMMarca)vcEQMMarca.get(i);
                    if (request.getParameter("iCveMarca")!=null && request.getParameter("iCveMarca").compareToIgnoreCase(new Integer(vEQMMarca.getICveMarca()).toString())==0)
                      out.print("<option value = " + vEQMMarca.getICveMarca() + " selected>" + vEQMMarca.getCDscBreve() + "</option>");
                    else
                      out.print("<option value = " + vEQMMarca.getICveMarca() + ">" + vEQMMarca.getCDscBreve() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
                out.print("</select>");
                out.print("</td>");
              out.print("</tr>");

              out.print("<tr>");
                out.print("<td class='ETablaTL' colspan='9'>Ubicación:</td>");
              out.print("</tr>");

// U N I D A D   M E D I C A

              out.print("<tr>");
                if(request.getParameter("chkUniMed")!=null)
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkUniMed' OnClick='fActUniMed();' checked>Unidad Médica:</td>");
                else
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkUniMed' OnClick='fActUniMed();'>Unidad Médica:</td>");
                String cValActual = "";
                vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                boolean lFirst = false;
                BeanScroller beanSc = new BeanScroller(vUsuario.getVUniFiltro(iCveProceso));
                if(request.getParameter("iCveUniMed")!=null &&request.getParameter("iCveUniMed").compareTo("")!=0)
                  cValActual = "" + request.getParameter("iCveUniMed");
                else
                  cValActual = "0";
                if(request.getParameter("chkUniMed")!=null)
                  out.print("<td colspan='2'><select name=\"iCveUniMed\" OnChange=\"llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);\">");
                else
                  out.print("<td colspan='2'><select name=\"iCveUniMed\" OnChange=\"llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);\" disabled>");
                if (beanSc != null) {
                  out.print("<option value=0>Seleccione...</option>");
                  for (int i = 1; i <= beanSc.rowSize(); i++) {
                    beanSc.setRowIdx(i);
                    String CampoClave = "" + beanSc.getFieldValue("iCveUniMed", "");
                    if (cValActual.compareTo("") == 0 && lFirst == false) {
                      CampoClave = " selected ";
                      lFirst = true;
                    }
                    else {
                      if (CampoClave.compareToIgnoreCase(cValActual) == 0) {
                        CampoClave = " selected ";
                      }else{
                        CampoClave = "";
                      }
                    }
                    out.print("<option " + CampoClave + " value=\"" + beanSc.getFieldValue("iCveUniMed", "" + i) + "\">" + beanSc.getFieldValue("cDscUniMed", "") + "</option>");
                  }
                  out.print("</select></td>");
                }

// M O D U L O

                if(request.getParameter("chkModulo")!=null){
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkModulo' OnClick='fActModulo();' checked>Módulo:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.println("<select name=\"iCveModulo\" onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\">");
                }
                else{
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkModulo' OnClick='fActModulo();'>Módulo:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.println("<select name=\"iCveModulo\" onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\" disabled>");
                }
                if(request.getParameter("iCveUniMed")!= null){
                  TDGRLModulo dGRLModulo = new TDGRLModulo();
                  TVGRLModulo vGRLModulo = new TVGRLModulo();
                  Vector vcGRLModulo = new Vector();
                  vcGRLModulo = dGRLModulo.FindByAll(" where iCveUniMed = " + request.getParameter("iCveUniMed"));
                  if (vcGRLModulo.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLModulo.size(); i++){
                      vGRLModulo = (TVGRLModulo)vcGRLModulo.get(i);
                      if (request.getParameter("iCveModulo")!=null && request.getParameter("iCveModulo").compareToIgnoreCase(new Integer(vGRLModulo.getICveModulo()).toString())==0)
                        out.print("<option value = " + vGRLModulo.getICveModulo() + " selected>" + vGRLModulo.getCDscModulo() + "</option>");
                      else
                        out.print("<option value = " + vGRLModulo.getICveModulo() + ">" + vGRLModulo.getCDscModulo() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed").toString())<1) || request.getParameter("iCveUniMed")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");

// A R E A

                if(request.getParameter("chkArea")!=null){
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkArea' OnClick='fActArea();' checked>Área:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.println("<select name=\"iCveArea\">");
                }
                else{
                  out.print("<td class='EEtiquetaL'><input type='checkbox' name='chkArea' OnClick='fActArea();'>Área:</td>");
                  out.print("<td class='ETabla' colspan='2'>");
                  out.println("<select name=\"iCveArea\" disabled>");
                }
                if(request.getParameter("iCveModulo")!= null){
                  DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
                  TVGRLAreaModulo  vGRLAreaModulo = new TVGRLAreaModulo();
                  Vector vcGRLAreaModulo = new Vector();
                  vcGRLAreaModulo = dGRLAreaModulo.FindByAll(" where iCveUniMed = " + request.getParameter("iCveUniMed") + " and iCveModulo = " + request.getParameter("iCveModulo"));
                  if (vcGRLAreaModulo.size() > 0){
                    for (int i = 0; i < vcGRLAreaModulo.size(); i++){
                      vGRLAreaModulo = (TVGRLAreaModulo)vcGRLAreaModulo.get(i);
                        if(request.getParameter("iCveArea")!=null && request.getParameter("iCveArea").compareToIgnoreCase(new Integer(vGRLAreaModulo.getICveArea()).toString())==0)
                          out.print("<option value = " + vGRLAreaModulo.getICveArea() + " selected>" + vGRLAreaModulo.getCDscArea() + "</option>");
                        else
                          out.print("<option value = " + vGRLAreaModulo.getICveArea() + ">" + vGRLAreaModulo.getCDscArea() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveModulo")!=null && Integer.parseInt(request.getParameter("iCveModulo").toString())<1) || request.getParameter("iCveModulo")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");
              out.print("</tr>");

// R E S U L T A D O S

                    out.print("<tr>");
                      out.print("<td class=\"ETablaT\">Último Programado:</td>");
                      out.print("<td class=\"ETablaT\">Tipo</td>");
                      out.print("<td class=\"ETablaT\">Equipo</td>");
                      out.print("<td class=\"ETablaT\">No. Serie</td>");
                      out.print("<td class=\"ETablaT\">Inventario</td>");
                      out.print("<td class=\"ETablaT\">Uni. Med.</td>");
                      out.print("<td class=\"ETablaT\">Módulo</td>");
                      out.print("<td class=\"ETablaT\">Área</td>");
                      out.print("<td class=\"ETablaT\">Programar</td>");
                    out.print("</tr>");
                if (bs != null){
                    bs.start();
                    int iChk = 1;
                    while(bs.nextRow()){
                      out.print("<tr>");
                        TVEQMMantenimiento vEQMMantenimiento = new TVEQMMantenimiento();
                        TDEQMMantenimiento dEQMMantenimiento = new TDEQMMantenimiento();
                        Vector vcEQMMantenimiento = new Vector();
                        vcEQMMantenimiento = dEQMMantenimiento.FindByAll(" where EQMMantenimiento.iCveEquipo = " + bs.getFieldValue("iCveEquipo") + " and EQMMantenimiento.lConcluido = 0  and EQMMantenimiento.lCancelado = 0 ", " order by EQMMantenimiento.dtProgramado ");
                        if (vcEQMMantenimiento.size() > 0)
                          for (int i = 0; i < vcEQMMantenimiento.size(); i++)
                            vEQMMantenimiento = (TVEQMMantenimiento)vcEQMMantenimiento.get(i);

                        if (vEQMMantenimiento.getCDtProgramado() != null)
                           out.print(vEti.Texto("ETablaC", vEQMMantenimiento.getCDtProgramado().toString()));
                        else
                           out.print(vEti.Texto("ETablaC", "&nbsp"));
                        out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscBreveTpoEquipo", "&nbsp;").toString()));
                        out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscEquipo", "&nbsp;").toString()));
                        out.print(vEti.Texto("ECampo", bs.getFieldValue("cNumSerie", "&nbsp;").toString()));
                        out.print(vEti.Texto("ECampo", bs.getFieldValue("cInventario", "&nbsp;") + " " + bs.getFieldValue("cApPaterno", "&nbsp;") + " " + bs.getFieldValue("cApMaterno", "&nbsp;")));
                        out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));
                        out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscModulo", "&nbsp;").toString()));
                        out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscArea", "&nbsp;").toString()));
                        out.print("<td class='ETablaC'><input type='checkbox' name='chkProg" + iChk + "'></td>");
                        out.print("<input type='hidden' name='iCveEquipo" + iChk + "' value='" + bs.getFieldValue("iCveEquipo", "&nbsp;") + "'>");
                      out.print("</tr>");
                      iChk++;
                    }
                    out.print("<input type='hidden' name='hdTotalReg' value='" + iChk + "'>");
                    out.print("<td class='ETablaC' colspan='9'>");
                    out.print(vEti.clsAnclaTexto("EAncla","Programar","JavaScript:fProgramar();", ""));
                    out.print("</td>");

                }
                else{
                  out.println("<tr>");
                  out.print("<td class='ETablaC' colspan='9'>No existen datos coincidentes con el filtro proporcionado.</td>");
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
