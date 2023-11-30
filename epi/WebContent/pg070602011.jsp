<%/**
 * Title: Catalogo de Equipos
 * Description: Catalogo de Equipos
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070602011CFG
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070602011CFG  clsConfig = new pg070602011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070602011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070602011.jsp\" target=\"FRMCuerpo"); // modificar
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
  TFechas Fecha = new TFechas();
  //java.sql.Date dtToday = Fecha.TodaySQL();

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Modelo|Número de Serie|Número de Inventario|";    // modificar
  String cCveOrdenar  = "cModelo|cNumSerie|cInventario|";  // modificar
  String cDscFiltrar  = "Modelo|Número de Serie|Número de Inventario|";    // modificar
  String cCveFiltrar  = "cModelo|cNumSerie|cInventario|";  // modificar
  String cTipoFiltrar = "8|8|8|";                // modificar
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
       cClave2  = ""+bs.getFieldValue("iCveEquipo", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><td class="ETablaT" colspan="4">Registro de Equipos</td></tr>
                              <tr>
                            <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                TFechas fecha = new TFechas();
                                TVEQMEquipo tvEquipo = new TVEQMEquipo();
                                TVUsuario tvUsu = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                int iUsu = tvUsu!=null?tvUsu.getICveusuario():0;
                                if (lNuevo){
                                  // Nuevo
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Clasificación:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCveClasificacion", "llenaSLT(1,this.value,'','',document.forms[0].iCveTpoEquipo);", clsConfig.vClasif, "iCveClasificacion", "cDscClasificacion", request, "0"));
                                    out.println("<input type=\"hidden\" name=\"iCveUsuRegistra\" value=\"" + iUsu + "\">");
                                    out.println("</td>");

                                    TDEQMTpoEquipo dTipo = new TDEQMTpoEquipo();
                                    Vector vTipo = new Vector();
                                    TVEQMTpoEquipo tvTipo = new TVEQMTpoEquipo();
                                    out.println(vEti.Texto("EEtiqueta", "Tipo de Equipo:"));
                                    if (request.getParameter("iCveClasificacion") != null && !request.getParameter("iCveClasificacion").equals("")) {
                                        String clasif = request.getParameter("iCveClasificacion");
                                        String tpoEq = request.getParameter("iCveTpoEquipo").toString();
                                        vTipo = dTipo.FindByAll(" where lActivo=1 and iCveClasificacion=" + clasif, " order by iCveTpoEquipo ");
                                        out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                        out.println("<option value='0'>Seleccione...</option>");
                                        for (int i = 0; i < vTipo.size(); i++) {
                                            tvTipo = (TVEQMTpoEquipo) vTipo.get(i);
                                            if (tpoEq.equals("" + tvTipo.getICveTpoEquipo()))
                                               out.println("<option value='" + tvTipo.getICveTpoEquipo() + "' selected>" + tvTipo.getCDscBreve() + "</option>");
                                            else
                                               out.println("<option value='" + tvTipo.getICveTpoEquipo() + "'>" + tvTipo.getCDscBreve() + "</option>");
                                        }
                                    } else {
                                        out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                        out.println("<option value='0'>Datos no Disponibles...</option>");
                                    }
                                    out.println("</SELECT></td>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Identificador del Equipo:","ECampo","text",30,50,3,"cCveEquipo","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",90,80,3,"cDscEquipo","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Serie:","ECampo","text",30,250,"cNumSerie","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Inventario:","ECampo","text",30,30,"cInventario","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Marca:"));
                                    out.print(vEti.SelectOneRow("ECampo","iCveMarca","",clsConfig.vMarca,"iCveMarca","cDscBreve",request,"0"));
                                    out.print(vEti.EtiCampo("EEtiqueta","Modelo:","ECampo","text",30,50,"cModelo","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Adquisición:","ECampo","text",12,10,3,"dtAdquisicion","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Estado del Equipo:"));
                                    out.println("<td colspan='3'>");
                                    out.print(vEti.SelectOneRowSinTD("iCveEstadoEQM","",clsConfig.vEstado,"iCveEstadoEQM","cDscEstadoEQM",request,"0"));
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<td class='EEtiqueta'>Observación:<p class=\"EPieR\">Caracteres Disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                    out.println("<td colspan=\"3\"><textarea name=\"cObservacion\" cols=\"80\" rows=\"3\" onkeyup=\"fChgArea(this);\" onchange=\"fChgArea(this);\" onBlur=\"fMayus(this);\"></textarea></td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT","Especificaciones del Fabricante", 4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Periodo de Mantenimiento:"));
                                    out.println("<td class='ECampo'><input type=\"text\" size=\"4\" value=\"\" maxlength=\"6\" name=\"iPerManttoPrev\">&nbsp;&nbsp;Meses</td>");
                                    out.print(vEti.Texto("EEtiqueta","Uso Límite para Mantenimiento:"));
                                    out.println("<td class='ECampo'><input type=\"text\" size=\"8\" maxlength=\"7\" name=\"iLimiteUso\" value=\"\">&nbsp;&nbsp;</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Periodo de Calibración:"));
                                    out.println("<td class='ECampo' colspan='3'><input type=\"text\" size=\"4\" maxlength=\"6\" name=\"iPerCalibracion\" value=\"\">&nbsp;&nbsp;Meses</td>");
                                    out.println("</tr>");
                                } else {

                                  if (lCaptura) {
                                  // Modificar
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Clasificación:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCveClasificacion", "llenaSLT(1,this.value,'','',document.forms[0].iCveTpoEquipo);", clsConfig.vClasif, "iCveClasificacion", "cDscClasificacion", request, "0"));
                                    out.println("<input type=\"hidden\" name=\"iCveEquipo\" value=\"" + bs.getFieldValue("iCveEquipo") + "\">");
                                    out.println("<input type=\"hidden\" name=\"iCveUsuRegistra\" value=\"" + iUsu + "\">");
                                    out.println("</td>");

                                    TDEQMTpoEquipo dTipo = new TDEQMTpoEquipo();
                                    Vector vTipo = new Vector();
                                    TVEQMTpoEquipo tvTipo = new TVEQMTpoEquipo();
                                    out.println(vEti.Texto("EEtiqueta", "Tipo de Equipo:"));
                                    if (request.getParameter("iCveClasificacion") != null && !request.getParameter("iCveClasificacion").equals("")) {
                                        String clasif = request.getParameter("iCveClasificacion");
                                        String tpoEq = request.getParameter("iCveTpoEquipo").toString();
                                        vTipo = dTipo.FindByAll(" where lActivo=1 and iCveClasificacion=" + clasif, " order by iCveTpoEquipo ");
                                        out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                        out.println("<option value='0'>Seleccione...</option>");
                                        for (int i = 0; i < vTipo.size(); i++) {
                                            tvTipo = (TVEQMTpoEquipo) vTipo.get(i);
                                            if (tpoEq.equals("" + tvTipo.getICveTpoEquipo()))
                                               out.println("<option value='" + tvTipo.getICveTpoEquipo() + "' selected>" + tvTipo.getCDscBreve() + "</option>");
                                            else
                                               out.println("<option value='" + tvTipo.getICveTpoEquipo() + "'>" + tvTipo.getCDscBreve() + "</option>");
                                        }
                                    } else {
                                        out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                        out.println("<option value='0'>Datos no Disponibles...</option>");
                                    }
                                    out.println("</SELECT></td>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Identificador del Equipo:","ECampo","text",30,50,3,"cCveEquipo", bs.getFieldValue("cCveEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",90,80,3,"cDscEquipo", bs.getFieldValue("cDscEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Serie:","ECampo","text",30,250,"cNumSerie", bs.getFieldValue("cNumSerie","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Inventario:","ECampo","text",30,30,"cInventario", bs.getFieldValue("cInventario","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Marca:"));
                                    out.print(vEti.SelectOneRow("ECampo","iCveMarca","",clsConfig.vMarca,"iCveMarca","cDscBreve",request,bs.getFieldValue("iCveMarca").toString()));
                                    out.print(vEti.EtiCampo("EEtiqueta","Modelo:","ECampo","text",30,50,"cModelo", bs.getFieldValue("cModelo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    tvEquipo = (TVEQMEquipo) bs.getCurrentBean();
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Adquisición:","ECampo","text",12,10,3,"dtAdquisicion", fecha.getFechaDDMMYYYY(tvEquipo.getDtAdquisicion(), "/"),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    String eqAct = "";
                                    if (bs.getFieldValue("lActivo","&nbsp;").toString().equals("1"))
                                       eqAct = " checked";
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Estado del Equipo:"));
                                    out.println("<td colspan='3'>");
                                    out.print(vEti.SelectOneRowSinTD("iCveEstadoEQM","",clsConfig.vEstado,"iCveEstadoEQM","cDscEstadoEQM",request,bs.getFieldValue("iCveEstadoEQM").toString()));
                                    out.println("</td></tr>");

                                    out.println("<tr>");
                                    out.println("<td class='EEtiqueta'>Observación:<p class=\"EPieR\">Caracteres Disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                    out.println("<td colspan=\"3\"><textarea name=\"cObservacion\" cols=\"80\" rows=\"3\" onkeyup=\"fChgArea(this);\" onchange=\"fChgArea(this);\" onBlur=\"fMayus(this);\">" + bs.getFieldValue("cObservacion","&nbsp;").toString() + "</textarea></td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT","Especificaciones del Fabricante:", 4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Periodo de Mantenimiento:"));
                                    out.println("<td class='ECampo'><input type=\"text\" size=\"4\" value=\"" + bs.getFieldValue("iPerManttoPrev").toString() + "\" maxlength=\"6\" name=\"iPerManttoPrev\">&nbsp;&nbsp;Meses</td>");
                                    out.print(vEti.Texto("EEtiqueta","Uso Límite para Mantenimiento:"));
                                    out.println("<td class='ECampo'><input type=\"text\" size=\"8\" maxlength=\"7\" name=\"iLimiteUso\" value=\"" + bs.getFieldValue("iLimiteUso").toString() + "\">&nbsp;&nbsp;</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Periodo de Calibración:"));
                                    out.println("<td class='ECampo' colspan='3'><input type=\"text\" size=\"4\" maxlength=\"6\" name=\"iPerCalibracion\" value=\"" + bs.getFieldValue("iPerCalibracion","&nbsp;").toString() + "\">&nbsp;&nbsp;Meses</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT","Baja del Equipo", 4));
                                    out.println("</tr>");
                                    String baja = bs.getFieldValue("lBaja").toString();
                                    String fechabaja = "";
                                    String causab = "&nbsp;";
                                    String checked = "";
                                    if (baja.equals("1")) {
                                        tvEquipo = (TVEQMEquipo) bs.getCurrentBean();
                                        fechabaja = fecha.getFechaDDMMYYYY(tvEquipo.getDtBaja(), "/");
                                        causab = bs.getFieldValue("cDscBreveCausaBaja","&nbsp;").toString();
                                        checked = " checked";
                                    }
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Dado de Baja:"));
                                    out.println("<td><input type='checkbox' value='1' name='lBaja'" + checked + "></td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Baja:","ECampo","text",8,8,"dtBaja", fechabaja,0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Causa:"));
                                    out.println("<td colspan='3'>");
                                    out.println(vEti.SelectOneRowSinTD("iCveCausaBaja", "", clsConfig.vCausa, "iCveCausaBaja", "cDscCausaBaja", request, "0"));
                                    out.println("</td></tr>");

                                  } else if (bs != null) {
                                  // Mostrar Datos
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Clasificación:"));

                                    Vector vClasifica = clsConfig.vClasif;
                                    if (vClasifica == null || vClasifica.size() == 0)
                                      vClasifica = new Vector();
                                    TVEQMClasificacion tvClasif;
                                    String clasif = bs.getFieldValue("iCveClasificacion","0").toString();
                                    out.println("<td class='ECampo'><SELECT NAME='iCveClasificacion' SIZE='1' onChange=\"llenaSLT(1,this.value,'','',document.forms[0].iCveTpoEquipo);\">");
                                    for (int i = 0; i < vClasifica.size(); i++) {
                                      tvClasif = (TVEQMClasificacion) vClasifica.get(i);
                                      if (clasif.equals("" + tvClasif.getICveClasificacion()))
                                        out.println("<option value='" + tvClasif.getICveClasificacion() + "' selected>" + tvClasif.getCDscBreve() + "</option>");
                                      else
                                        out.println("<option value='" + tvClasif.getICveClasificacion() + "'>" + tvClasif.getCDscBreve() + "</option>");
                                    }
                                    out.println("</SELECT>");

                                    out.println("<input type=\"hidden\" name=\"iCveEquipo\" value=\"" + bs.getFieldValue("iCveEquipo") + "\">");
                                    out.println("<input type=\"hidden\" name=\"dadoBaja\" value=\"" + bs.getFieldValue("lBaja") + "\">");
                                    out.println("</td>");

                                    TDEQMTpoEquipo dTipo = new TDEQMTpoEquipo();
                                    Vector vTipo = new Vector();
                                    TVEQMTpoEquipo tvTipo = new TVEQMTpoEquipo();
                                    out.println(vEti.Texto("EEtiqueta", "Tipo de Equipo:"));
                                    if (request.getParameter("iCveClasificacion") != null && !request.getParameter("iCveClasificacion").equals("")) {
                                        String tpoEq = bs.getFieldValue("iCveTpoEquipo","0").toString();
                                        vTipo = dTipo.FindByAll(" where lActivo=1 and iCveClasificacion=" + clasif, " order by iCveTpoEquipo ");
                                        out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                        out.println("<option value='0'>Seleccione...</option>");
                                        for (int i = 0; i < vTipo.size(); i++) {
                                            tvTipo = (TVEQMTpoEquipo) vTipo.get(i);
                                            if (tpoEq.equals("" + tvTipo.getICveTpoEquipo()))
                                               out.println("<option value='" + tvTipo.getICveTpoEquipo() + "' selected>" + tvTipo.getCDscBreve() + "</option>");
                                            else
                                               out.println("<option value='" + tvTipo.getICveTpoEquipo() + "'>" + tvTipo.getCDscBreve() + "</option>");
                                        }
                                    } else {
                                        out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                        out.println("<option value='0'>Datos no Disponibles...</option>");
                                    }
                                    out.println("</SELECT></td>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",10,10,"iCveEquipo", bs.getFieldValue("iCveEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Identificador del Equipo:","ECampo","text",70,50,"cCveEquipo", bs.getFieldValue("cCveEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Nombre:","ECampo","text",90,80,3,"cDscEquipo", bs.getFieldValue("cDscEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Serie:","ECampo","text",50,250,"cNumSerie", bs.getFieldValue("cNumSerie","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Inventario:","ECampo","text",40,30,"cInventario", bs.getFieldValue("cInventario","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Marca:","ECampo","text",40,30,"cInventario", bs.getFieldValue("cDscBreveMarca","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Modelo:","ECampo","text",60,50,"cModelo", bs.getFieldValue("cModelo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    tvEquipo = (TVEQMEquipo) bs.getCurrentBean();
                                    out.print(vEti.EtiCampo("EEtiqueta","Adquisición:","ECampo","text",12,10,"dtAdquisicion", fecha.getFechaDDMMYYYY(tvEquipo.getDtAdquisicion(), "/"),0,"","fMayus(this);",false,true,lCaptura));
                                    String cNombre = bs.getFieldValue("iCveUsuRegistra","0").toString();
                                    try{
                                      TDSEGUsuario dDatosUsr = new TDSEGUsuario();
                                      if (!cNombre.equals("") && !cNombre.equals("0")){
                                        Vector vDatosUsr = dDatosUsr.FindByAll(" where iCveUsuario = " + cNombre);
                                        if (vDatosUsr != null && vDatosUsr.size() > 0){
                                          TVSEGUsuario VDatosUsr = (TVSEGUsuario)vDatosUsr.get(0);
                                          cNombre += VDatosUsr.getCNombre()!=null?" - " + VDatosUsr.getCNombre():"";
                                          cNombre += VDatosUsr.getCApPaterno()!=null?" " + VDatosUsr.getCApPaterno():"";
                                          cNombre += VDatosUsr.getCApMaterno()!=null?" " + VDatosUsr.getCApMaterno():"";
                                        }
                                      }
                                    }catch (Exception ex){}
                                    out.print(vEti.EtiCampo("EEtiqueta","Usuario que Registra:","ECampo","text",12,12,"iCveUsuRegistra", cNombre!=null?cNombre:"&nbsp;",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    String eqAct = "";
                                    if (bs.getFieldValue("lActivo","&nbsp;").toString().equals("1"))
                                       eqAct = " checked";
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Estado del Equipo:","ECampo","text",1,1,"cDscBreveEstado", bs.getFieldValue("cDscBreveEstado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.Texto("EEtiqueta","Activo:"));
                                    out.println("<td><input type='checkbox' value='1' name='lBaja' disabled" + eqAct + "></td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta", "Observación:"));
                                    out.println("<td colspan=\"3\"><textarea name=\"cObservacion\" cols=\"80\" rows=\"3\" readonly>" + bs.getFieldValue("cObservacion","&nbsp;").toString() + "</textarea></td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT","Especificaciones del Fabricante", 4));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Periodo de Mantenimiento:","ECampo","text",4,6,"iPerManttoPrev", bs.getFieldValue("iPerManttoPrev","&nbsp;").toString() + " &nbsp; Meses",0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Uso Límite para Mantenimiento:","ECampo","text",8,7,"iLimiteUso", bs.getFieldValue("iLimiteUso","&nbsp;").toString() + " &nbsp; ",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Periodo de Calibración:","ECampo","text",4,6,3,"iPerCalibracion", bs.getFieldValue("iPerCalibracion","&nbsp;").toString() + " &nbsp; Meses",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.TextoCS("ETablaT","Baja del Equipo", 4));
                                    out.println("</tr>");
                                    String baja = bs.getFieldValue("lBaja").toString();
                                    String fechabaja = "&nbsp;";
                                    String causab = "&nbsp;";
                                    String checked = "";
                                    if (baja.equals("1")) {
                                        tvEquipo = (TVEQMEquipo) bs.getCurrentBean();
                                        fechabaja = fecha.getFechaDDMMYYYY(tvEquipo.getDtBaja(), "/");
                                        causab = bs.getFieldValue("cDscBreveCausaBaja","&nbsp;").toString();
                                        checked = " checked";
                                    }
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Dado de Baja:"));
                                    out.println("<td><input type='checkbox'" + checked + " disabled></td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Baja:","ECampo","text",8,8,"dtBaja", fechabaja,0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Causa:","ECampo","text",8,8,3,"cDscBreveCausaBaja", causab,0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                  } else {
                                       // NO HAY REGISTROS
                                       out.println("<tr>");
                                       out.println(vEti.Texto("EEtiqueta", "Clasificación:"));
                                       out.println("<td>");
                                       out.println(vEti.SelectOneRowSinTD("iCveClasificacion", "llenaSLT(1,this.value,'','',document.forms[0].iCveTpoEquipo);", clsConfig.vClasif, "iCveClasificacion", "cDscClasificacion", request, "0"));
                                       out.println("</td>");

                                       TDEQMTpoEquipo dTipo = new TDEQMTpoEquipo();
                                       Vector vTipo = new Vector();
                                       TVEQMTpoEquipo tvTipo = new TVEQMTpoEquipo();
                                       out.println(vEti.Texto("EEtiqueta", "Tipo de Equipo"));
                                       if (request.getParameter("iCveClasificacion") != null && !request.getParameter("iCveClasificacion").equals("")) {
                                           String clasif = request.getParameter("iCveClasificacion");
                                           String tpoEq = request.getParameter("iCveTpoEquipo").toString();
                                           vTipo = dTipo.FindByAll(" where lActivo=1 and iCveClasificacion=" + clasif, " order by iCveTpoEquipo ");
                                              out.println("<td class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                           out.println("<option value='0'>Seleccione...</option>");
                                           for (int i = 0; i < vTipo.size(); i++) {
                                               tvTipo = (TVEQMTpoEquipo) vTipo.get(i);
                                               if (tpoEq.equals("" + tvTipo.getICveTpoEquipo()))
                                                  out.println("<option value='" + tvTipo.getICveTpoEquipo() + "' selected>" + tvTipo.getCDscBreve() + "</option>");
                                               else
                                                  out.println("<option value='" + tvTipo.getICveTpoEquipo() + "'>" + tvTipo.getCDscBreve() + "</option>");
                                           }
                                       } else {
                                           out.println("<td colspan='4' class='ECampo'><SELECT NAME='iCveTpoEquipo' SIZE='1'>");
                                           out.println("<option value='0'>Datos no Disponibles...</option>");
                                       }
                                       out.println("</SELECT></td>");
                                       out.println("</td>");
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.println(vEti.Texto("EEtiqueta", "Mensaje:"));
                                       out.print("<td class='ECampo' colspan='10'>No existen datos coincidentes con el filtro proporcionado</td>");
                                       out.println("</tr>");
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
