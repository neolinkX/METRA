<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>

<html>

<%
  pg070803010CFG  clsConfig = new pg070803010CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070803010.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803010.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Solicitud|";    // modificar
  String cCveOrdenar  = "ALMSolicSumin.iCveSolicSum|";  // modificar
  String cDscFiltrar  = "Solicitud|";    // modificar
  String cCveFiltrar  = "ALMSolicSumin.iCveSolicSum|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
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

  TFechas dtFecha = new TFechas();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  String cFiltro = "";

  TEtiCampo vEti = new TEtiCampo();
  boolean lCaptura = clsConfig.getCaptura();
  boolean lNuevo = clsConfig.getNuevo();
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  String cUsuario = "";
  if (vUsuario!=null)
     cUsuario = vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
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
       cClave2  = ""+bs.getFieldValue("iCveSistema", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){  %>
  <tr>
     <td valign="top">
                          &nbsp;
                          <input type="hidden" name="hdBoton" value="">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                           <tr><td colspan="5" class="ETablaT">Solicitud</td></tr>
                            <%
                                if (lNuevo){
                                    /*out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Unidad:</td>");
                                    out.println("<td>");*/
                                    int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                                    /*if (vUsuario!=null)
                                       out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                    out.println("</td>");
                                    out.println("<td class=\"EEtiqueta\">Modulo:</td>");
                                    out.println("<td>");
                                    out.println("<select name=\"iCveModulo\" size=\"1\" onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\">");
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Área:</td>");
                                    out.println("<td>");
                                    out.println("<select name=\"iCveArea\" size=\"1\" onChange=\"llenaSLT(3,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,this.value,document.forms[0].iCveAlmacen);\">");
                                    out.println("</select>");
                                    */

                      String User = "" + vUsuario.getICveusuario();
                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Unidad Médica:</td>");
                      out.println("<td>");
                      if (vUsuario!=null)
                         out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,"+User+",this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                      out.println("</td>");

                      //Módulo
                      TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                      out.print(vEti.Texto("EEtiqueta", "Módulo:"));
                      if (request.getParameter("iCveModulo") == null){
                         out.println("<td class='ECampo'>");
                         out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value," + vUsuario.getICveusuario()  + ",document.forms[0].iCveArea);\">");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                         Vector vGModulo = new Vector();
                         cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                   " and GRLUsuMedicos.iCveUniMed =  " + request.getParameter("iCveUniMed") ;
                         vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveModulo","llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);",vGModulo,"iCveModulo","cDscModulo",request,""));
                       }

                      out.println("</td>");
                      out.println("</tr>");
                      out.println("<tr>");

                      //Área
                      DAOGRLAreaModulo dAreaModuloC = new DAOGRLAreaModulo();
                      out.print(vEti.Texto("EEtiqueta", "Área:"));
                      //if (request.getParameter("iCveArea") == null){
                      if (request.getParameter("iCveModulo") == null){
                         out.println("<td class='ECampo'>");
                         out.println("<SELECT NAME='iCveArea' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,this.value,document.forms[0].iCveAlmacen);\">");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLAreaModulo vAreaModulo = new  TVGRLAreaModulo();
                         Vector vcAreaModulo = new Vector();
                         cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + request.getParameter("iCveUniMed") +
                                   "   AND GRLAreaModulo.iCveModulo = " + request.getParameter("iCveModulo");
                         vcAreaModulo = dAreaModuloC.FindByAll(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveArea","llenaSLT(3,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,this.value,document.forms[0].iCveAlmacen);",vcAreaModulo,"iCveArea","cDscArea",request,""));
                        }


                                    out.println("</td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Usuario que Solicita:","ECampo","text",4,4,"iCveUsuSolicita", cUsuario,0,"","fMayus(this);",false,true,false));
                                    if (vUsuario!=null)
                                       out.println("<input type=\"hidden\" name=\"iCveUsuSolicita\" value=\""+vUsuario.getICveusuario()+"\">");
                                    out.println("</tr>");

                                    out.println("<tr><td colspan=\"5\" class=\"ETablaT\">Suministro</td></tr>");

                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Año:</td>");
                                    out.println("<td class=\"ETabla\">");
                                    out.println("<select name=\"iAnio\" size=\"1\" onchange=\"llenaSLT(4,this.value,'','',document.forms[0].iCvePeriodo);\">");
                                    out.print("<option value = \"-1\" selected>Seleccione...</option>");
                                     for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                          else
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                       }

                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("<td class=\"EEtiqueta\">Almacen:</td>");
                                    out.println("<td>");
                                    out.println("<select name=\"iCveAlmacen\" size=\"1\">");
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Solicitud de Suministro:","ECampo","text",4,4,"iCveSolicSum", "&nbsp;",0,"","fMayus(this);",false,true,false));
                                    out.println("<td class=\"EEtiqueta\">Fecha de Solicitud:</td>");
                                    out.println("<td class=\"ECampo\">");
                                    out.println("<input type=\"text\" size=\"10\" maxlength=\"10\" name=\"dtSolicitud\" value=\""+dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")+"\">");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">&nbsp;&nbsp;</td>");
                                    out.println("<td class=\"EEtiquetaL\">");
                                    out.println("<input type=\"radio\" value=\"0\" checked name=\"lProgramada\" onclick=\"document.forms[0].dtSuministro.disabled=0;\">Extra");
                                    out.println("<input type=\"radio\" value=\"1\" name=\"lProgramada\" onclick=\"document.forms[0].dtSuministro.value='';document.forms[0].dtSuministro.disabled=1;\">Programada");
                                    out.println("</td>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha de Suministro:","ECampo","text",10,10,"dtSuministro","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Periodo:</td>");
                                    out.println("<td>");
                                    out.println("<select name=\"iCvePeriodo\" size=\"1\">");
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("<td class=\"EEtiqueta\">Prioridad:</td>");
                                    out.println("<td>");
                                    TDALMTpoPrioridad dALMTpoPrioridad = new TDALMTpoPrioridad();
                                    TVALMTpoPrioridad vALMTpoPrioridad = new TVALMTpoPrioridad();
                                    Vector vTpoPrioridad = new Vector();
                                    vTpoPrioridad = dALMTpoPrioridad.FindByAll(""," order by icveprioridad");
                                    out.print(vEti.SelectOneRowSinTD("iCvePrioridad","",vTpoPrioridad,"iCvePrioridad","cDscPrioridad",request,"0",true));
                                    out.println("</td>");
                                    out.println("</tr>");

                                }
                                else {
                                       if (bs != null){
                                          out.println("<input type=\"hidden\" name=\"iAnio\" value=\""+bs.getFieldValue("iAnio","&nbsp;").toString()+"\">");
                                          out.println("<input type=\"hidden\" name=\"iCveAlmacen\" value=\""+bs.getFieldValue("iCveAlmacen","&nbsp;").toString()+"\">");
                                          out.println("<input type=\"hidden\" name=\"iCveSolicSum\" value=\""+bs.getFieldValue("iCveSolicSum","&nbsp;").toString()+"\">");
                                          out.println("<input type=\"hidden\" name=\"lAutorizada\" value=\""+bs.getFieldValue("lAutorizada").toString()+"\">");
                                          if (lCaptura){

                                             out.println("<tr>");
                                             out.println("<td class=\"EEtiqueta\">Unidad:</td>");
                                             out.println("<td>");
                                             int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                                             if (vUsuario!=null)
                                                out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,bs.getFieldValue("iCveUniMed","0").toString(),true));
                                             out.println("</td>");
                                             out.println("<td class=\"EEtiqueta\">Modulo:</td>");
                                             out.println("<td>");
                                             TVGRLModulo vGRLModulo = new TVGRLModulo();
                                             Vector vModulo = new Vector();
                                             int iUniMed = new Integer(bs.getFieldValue("iCveUniMed","0").toString()).intValue();
                                             vModulo = (Vector) AppCacheManager.getColl("GRLModulo",iUniMed + "|");
                                             out.print(vEti.SelectOneRowSinTD("iCveModulo","llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);",vModulo,"iCveModulo","cDscModulo",request,bs.getFieldValue("iCveModulo","0").toString(),true));
                                             out.println("</td>");
                                             out.println("</tr>");

                                             out.println("<tr>");
                                             out.println("<td class=\"EEtiqueta\">Área:</td>");
                                             out.println("<td>");
                                             DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
                                             TVGRLAreaModulo  vGRLAreaModulo = new TVGRLAreaModulo();
                                             Vector vAreaModulo = new Vector();
                                             if (bs.getFieldValue("iCveUniMed")!=null && bs.getFieldValue("iCveModulo")!=null)
                                                cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + bs.getFieldValue("iCveUniMed").toString() +
                                                          "  AND GRLAreaModulo.iCveModulo = " + bs.getFieldValue("iCveModulo").toString() + " ";
                                             vAreaModulo = dGRLAreaModulo.FindByAll(cFiltro);
                                             out.print(vEti.SelectOneRowSinTD("iCveArea","",vAreaModulo,"iCveArea","cDscArea",request,bs.getFieldValue("iCveArea","0").toString(),true));
                                             out.println("</td>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Usuario que Solicita:","ECampo","text",4,4,"iCveUsuSolicita",bs.getFieldValue("cDscUsuSolicita","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                             out.println("<input type=\"hidden\" name=\"iCveUsuSolicita\" value=\""+bs.getFieldValue("iCveUsuSolicita","&nbsp;").toString()+"\">");
                                             out.println("</tr>");

                                             out.println("<tr><td colspan=\"5\" class=\"ETablaT\">Suministro</td></tr>");
                                             out.println("<tr>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",4,4,"iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                             out.print(vEti.EtiCampo("EEtiqueta","Almacen:","ECampo","text",4,4,"iCveAlmacen", bs.getFieldValue("cDscAlmacen","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                             out.println("</tr>");

                                             out.println("<tr>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Solicitud de Suministro:","ECampo","text",4,4,"iCveSolicSum", bs.getFieldValue("iCveSolicSum","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud", bs.getFieldValue("dtSolicitud","").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");

                                             out.println("<tr>");
                                             out.println("<td class=\"EEtiqueta\">&nbsp;&nbsp;</td>");
                                             out.println("<td class=\"EEtiquetaL\">");
                                             if (bs.getFieldValue("lProgramada")!=null && bs.getFieldValue("lProgramada","&nbsp;").toString().compareTo("0")==0){
                                                out.println("<input type=\"radio\" value=\"0\" checked name=\"Programada\" onclick=\"document.forms[0].dtSuministro.disabled=0;\">Extra");
                                                out.println("<input type=\"radio\" value=\"1\" name=\"Programada\" onclick=\"document.forms[0].dtSuministro.value='';document.forms[0].dtSuministro.disabled=1;\">Programada");
                                             }
                                             else{
                                                out.println("<input type=\"radio\" value=\"0\" name=\"Programada\" onclick=\"document.forms[0].dtSuministro.disabled=0;\">Extra");
                                                out.println("<input type=\"radio\" value=\"1\" checked name=\"Programada\" onclick=\"document.forms[0].dtSuministro.value='';document.forms[0].dtSuministro.disabled=1;\">Programada");
                                             }

                                             out.println("</td>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Suministro:","ECampo","text",10,10,"dtSuministro", bs.getFieldValue("dtSuministro","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");

                                             out.println("<tr>");
                                             out.println("<td class=\"EEtiqueta\">Periodo:</td>");
                                             out.println("<td>");
                                             TDALMPeriodo dALMPeriodo = new TDALMPeriodo();
                                             TVALMPeriodo vALMPeriodo = new TVALMPeriodo();
                                             Vector vPeriodo = new Vector();
                                             vPeriodo = dALMPeriodo.FindByAll(" WHERE iAnio = " + iAnio + " AND lActivo = 1 "," ORDER BY iCvePeriodo ");
                                             out.print(vEti.SelectOneRowSinTD("iCvePeriodo","",vPeriodo,"iCvePeriodo","cDscPeriodo",request,bs.getFieldValue("iCvePeriodo","0").toString(),true));
                                             out.println("</td>");
                                             out.println("<td class=\"EEtiqueta\">Prioridad:</td>");
                                             out.println("<td>");
                                             TDALMTpoPrioridad dALMTpoPrioridad = new TDALMTpoPrioridad();
                                             TVALMTpoPrioridad vALMTpoPrioridad = new TVALMTpoPrioridad();
                                             Vector vTpoPrioridad = new Vector();
                                             vTpoPrioridad = dALMTpoPrioridad.FindByAll(""," order by icveprioridad");
                                             out.print(vEti.SelectOneRowSinTD("iCvePrioridad","",vTpoPrioridad,"iCvePrioridad","cDscPrioridad",request,bs.getFieldValue("iCvePrioridad","0").toString(),true));
                                             out.println("</td>");
                                             out.println("</tr>");

                                         }else{
                                             out.println("<tr>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Unidad Medica:","ECampo","text",4,4,"cDscUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.print(vEti.EtiCampo("EEtiqueta","Modulo:","ECampo","text",4,4,"cDscModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");

                                             out.println("<tr>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Área:","ECampo","text",4,4,"iCveArea", bs.getFieldValue("cDscArea","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.print(vEti.EtiCampo("EEtiqueta","Usuario que Solicita:","ECampo","text",20,20,"cDscUsuSolicita", bs.getFieldValue("cDscUsuSolicita","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");
                                             out.println("<tr><td colspan=\"5\" class=\"ETablaT\">Suministro</td></tr>");
                                             out.println("<tr>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",4,4,"iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.print(vEti.EtiCampo("EEtiqueta","Almacen:","ECampo","text",4,4,"iCveAlmacen", bs.getFieldValue("cDscAlmacen","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");

                                             out.println("<tr>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Solicitud de Suministro:","ECampo","text",4,4,"iCveSolicSum", bs.getFieldValue("iCveSolicSum","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Solicitud:","ECampo","text",10,10,"dtSolicitud", bs.getFieldValue("dtSolicitud","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");


                                             out.println("<tr>");
                                             out.println("<td class=\"EEtiqueta\">&nbsp;&nbsp;</td>");
                                             out.println("<td class=\"EEtiquetaL\">");
                                             if (bs.getFieldValue("lProgramada")!=null && bs.getFieldValue("lProgramada","&nbsp;").toString().compareTo("1")==0){
                                                 out.println("Programada");
                                             }else{
                                                 out.println("Extra");
                                             }
                                             out.println("</td>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Fecha de Suministro:","ECampo","text",10,10,"dtSuministro", bs.getFieldValue("dtSuministro","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");

                                             out.println("<tr>");
                                             out.print(vEti.EtiCampo("EEtiqueta","Periodo:","ECampo","text",4,4,"iCvePeriodo", bs.getFieldValue("cDscPeriodo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.print(vEti.EtiCampo("EEtiqueta","Prioridad:","ECampo","text",4,4,"iCvePrioridad", bs.getFieldValue("cDscPrioridad","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                             out.println("</tr>");
                                             if (bs.getFieldValue("lAutorizada").toString().compareTo("1")==0){
                                                out.println("<tr>");
                                                out.println("<td class=\"EEtiquetaC\" colspan=\"4\">Solicitud Autorizada</td>");
                                                out.println("</tr>");
                                             }
                                          }
                                       }
                                       else{
                                          out.println("<tr>");
                                          out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                          out.println("</tr>");
                                       }
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
