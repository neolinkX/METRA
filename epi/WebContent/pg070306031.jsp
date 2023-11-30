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
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>


<html>
<%
  pg070306031CFG  clsConfig = new pg070306031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306031.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave     = "";
  String cClave2    = "";
  String cClave3    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción Breve|";    // modificar
  String cCveOrdenar  = "C.iCveCtrolCalibra|C.cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripción Breve|";    // modificar
  String cCveFiltrar  = "C.iCveCtrolCalibra|C.cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";             // modificar


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

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070306011.js)


  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

</script>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>

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
       cClave   = ""+bs.getFieldValue("iAnio", "");
       cClave2  = ""+bs.getFieldValue("iCveLaboratorio", "");
       cClave3  = ""+bs.getFieldValue("iCveBaja", "");
     }

TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">

<%
if (request.getParameter("hdReporte") != null){
if(request.getParameter("hdReporte").compareTo("Reporte") ==0 ||
   "Guardar".equalsIgnoreCase(request.getParameter("hdBoton").toString()))
        out.println(clsConfig.getActiveX());
}


 %>


  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave2" value="<%if(cClave2.compareTo("")==0) out.print(request.getParameter("hdCampoClave2")); else out.print(cClave2);%>">
  <input type="hidden" name="hdCampoClave3" value="<%if(cClave3.compareTo("")==0) out.print(request.getParameter("hdCampoClave3")); else out.print(cClave3);%>">

  <input type="hidden" name="hdReporte">

  <input type="hidden" name="iCveUniMed" value="<% if (request.getParameter("iCveUniMed") != null) out.print(request.getParameter("iCveUniMed"));%>">



  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td>
<tr>
<td valign="top">
                         <%
                           TEtiCampo vEti = new TEtiCampo();
                           boolean lCaptura = clsConfig.getCaptura();
                           boolean lNuevo = clsConfig.getNuevo();
                           int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));

                           if (request.getParameter("RACiCveCtrolCalibra") != null) {
                                lNuevo = true;
                           }

                         if (!lCaptura) {
                         %>
                         <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                            %>

                          <tr>
                           <td class="ETablaT" colspan="4">Filtrar</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Año:</td>
                              <td class="ETabla">
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
                              <td class="EEtiqueta">Laboratorio:</td>
                              <td class="ETabla">
                                  <%
                                      String cCveLabx = ""+request.getParameter("iCveUniMed");
                                      if ( cCveLabx!=null && cCveLabx.compareToIgnoreCase("null")!=0&&cCveLabx.compareTo("")!=0)
                                         out.print(vEti.SelectOneRowSinTD("iCveLaboratorio","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,cCveLabx,true));
                                      else
                                         out.print(vEti.SelectOneRowSinTD("iCveLaboratorio","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                      // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);
                                  %>
                              </td>
                            </tr>
                          </table>
                          <br>
                          <%
                         }
                          %>



                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                            %>

                             <tr>
                             <td colspan="4" class="ETablaT">CONTROLES Y CALIBRADORES</td>
                             </tr>

                             <%

                                String vtipo = "";
                                if (request.getParameter("tipo") != null) {
                                   vtipo = request.getParameter("tipo");
                                }

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                out.println("<tr>");
                                out.print("<td colspan=\"3\" class=\"ETablaT\">INFORMACIÓN DE LA SUSTANCIA</td>");
                                out.println("</tr>");

                                out.println("<tr>" + vEti.Texto("EEtiqueta","Laboratorio:"));
                                out.println("<td class='ECampo' colspan=\"2\">");
                                out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "fCambiaLab('" + clsConfig.getAccionOriginal() + "');", vUsuario.getVUniFiltro(iCveProceso), "iCveUniMed", "cDscUniMed", request, "0",true));
                                out.println("</td>");

                                out.println("<tr>" + vEti.Texto("EEtiqueta", "Registrar Baja de:"));
                                out.println("<td class=\"EEtiqueta\">");
                                // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                // *************************************************
                                // La sig. linea se comenta a solicitud de la Dra. Escalante
                                //out.println("<input type=\"radio\" onClick=\"fReactCalib('" + clsConfig.getAccionOriginal() + "')\" name=\"tipo\" value=\"reactivo\"" + (vtipo.equals("reactivo") ? " checked" : "") + ">Reactivo");
                                //out.println("<input type=\"radio\" name=\"tipo\" onClick=\"fReactCalib('" + clsConfig.getAccionOriginal() + "')\" value=\"calibrador\"" + (vtipo.equals("calibrador") ? " checked" : "") + ">Calibrador</td>");
                                out.println("<input type=\"radio\" name=\"tipo\" onClick=\"fReactCalib('" + clsConfig.getAccionOriginal() + "')\" value=\"calibrador\"" + (vtipo.equals("calibrador") ? " checked" : "") + ">Control o Calibrador</td>");
                                if (request.getParameter("tipo") != null) {
                                     out.println("<td class=\"EEtiqueta\">");
                                     if (request.getParameter("tipo").equals("reactivo")){
                                          TDTOXReactivo dReact = new TDTOXReactivo();
                                          Vector vReact = new Vector();
                                          vReact = dReact.FindByAll(" where lbaja = 0 and lAgotado = 0 and iCveLaboratorio = " + request.getParameter("iCveLaboratorio"), " order by iCodigo");
                                          out.println(vEti.SelectOneRowSinTD("iCveReactivo", "", vReact, "iCveReactivo", "cComboBaja", request, "0", true) + "</td>");
                                     } else if (request.getParameter("tipo").equals("calibrador")){
                                          TDTOXCtrolCalibra dCtrlCalibra = new TDTOXCtrolCalibra();
                                          Vector vCalibra = new Vector();
                                          vCalibra = dCtrlCalibra.FindByAll2(" where lbaja = 0 and lAgotado = 0 and iCveLaboratorio = " + request.getParameter("iCveLaboratorio"), " order by cLote");
                                          out.println(vEti.SelectOneRowSinTD("iCveCtrolCalibra", "", vCalibra, "iCveCtrolCalibra", "cComboBaja", request, "0", true) + "</td>");
                                     }
                                     out.println("</td>");
                                }
                                else {
                                     // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                     //out.println(vEti.Texto("EEtiqueta", "Seleccione Reactivo o Calibrador"));
                                     out.println(vEti.Texto("EEtiqueta", "Seleccione Control o Calibrador"));
                                }
                                out.println("</tr>");

                                out.println("<tr>");
                                out.println(vEti.EtiCampoCS("EEtiqueta","Fecha:","ECampo","text",10,10,2,"dtDesechado","",0,"","fMayus(this);",false,true,true, request));
                                out.println("</tr>");

                                out.println("<tr>");
                                out.println(vEti.Texto("EEtiqueta", "Motivo de Baja:"));
                                out.println("<td>");
                                TDTOXMotBaja dTOXMotBaja = new TDTOXMotBaja();
                                out.println(vEti.SelectOneRowSinTD("iCveMotBaja", "", dTOXMotBaja.FindByAll("",""), "iCveMotBaja", "cDscMotBaja", request, "0", true));
                                out.println("</td>");
                                out.println("</tr>");

                                out.println("<tr>");
                                out.print("<td colspan=\"3\" class=\"ETablaT\">RESPONSABLES DE LA BAJA</td>");
                                out.println("</tr>");

                                out.println("<tr>");
                                // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                // *************************************************
                                // Se sustituye la etiqueta "Organo Interno:" por
                                // "Responsable de Aseguramiento de la Calidad"
                                // pero en la base de datos y en el bean TVTOXBaja
                                // seguira siendo cOrganoInterno
                                //
                                //out.print(vEti.EtiAreaTextoCS("EEtiqueta","Organo Interno:","ECampo", 50, 5, 2, "cOrganoInterno","",0,"","fMayus(this);",false,true,true));
                                out.print(vEti.EtiAreaTextoCS("EEtiqueta","Responsable de Aseguramiento de la Calidad:","ECampo", 50, 5, 2, "cOrganoInterno","",0,"","fMayus(this);",false,true,true));
                                // *************************************************
                                out.println("</tr>");

                               // TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                                int iUniMed = 0;
                                if(request.getParameter("iCveLaboratorio") == null){
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                   }
                                }else{
                                      iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                                }
                                vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                out.println("<tr>");
                                out.println(vEti.Texto("EEtiqueta","Usuario:"));
                                out.println("<td colspan=\"2\">");
                                out.print(vEti.SelectOneRowSinTD("iCveUsuBaja", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "0", true));
                                out.println("</td>");
                                out.println("</tr>");

                                out.println("<tr>");
                                // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                // *************************************************
                                // Se sustituye la etiqueta "Empresa que Inactiva:" por
                                // "Jefe del Laboratorio"
                                // pero en la base de datos y en el bean TVTOXBaja
                                // seguira siendo cInactiva
                                //
                                //out.print(vEti.EtiAreaTextoCS("EEtiqueta","Empresa que Inactiva:","ECampo",50,5,2,"cInactiva","",0,"","fMayus(this);",false,true,true));
                                out.print(vEti.EtiAreaTextoCS("EEtiqueta","Jefe del Laboratorio:","ECampo",50,5,2,"cInactiva","",0,"","fMayus(this);",false,true,true));
                                // *************************************************
                                out.println("</tr>");

                                out.println("<tr>");
                                out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,2,"cObservacion","",0,"","fMayus(this);",false,true,true));
                                out.println("</tr>");



/**************** MODIFICAR Y DESPLEGAR ****************/

                               }
                               else{      // Para Desplegar o Para Modificar
                                  if (bs != null){
                                   //cOrganoInterno
                                   if (!lCaptura){

                                   //out.println("<input type='hidden' name='iCveLaboratorio' value='" + bs.getFieldValue("iCveLaboratorio","&nbsp;").toString() + "'>");
                                   //out.println("<input type='hidden' name='iAnio' value='" + bs.getFieldValue("iAnio","&nbsp;").toString() + "'>");
                                   out.println("<input type='hidden' name='iCveBaja' value='" + bs.getFieldValue("iCveBaja","&nbsp;").toString() + "'>");

                                   out.println("<tr>");
                                   out.print("<td colspan=\"3\" class=\"ETablaT\">INFORMACIÓN DE LA SUSTANCIA</td>");
                                   out.println("</tr>");

                                   //out.print("<tr>");
                                   //out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",25,25,"iAnio", bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   //out.print("</tr>");

                                   out.print("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta","Laboratorio:","ECampo","text",25,25,"cDscLaboratorio", bs.getFieldValue("cDscLaboratorio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   if (bs.getFieldValue("cComboReactivo","&nbsp;").toString().compareTo("null-null") != 0)
                                      out.print(vEti.EtiCampo("EEtiqueta","Reactivo:","ECampo","text",25,25,"cComboReactivo", bs.getFieldValue("cComboReactivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   else
                                      out.print(vEti.EtiCampo("EEtiqueta","Calibrador:","ECampo","text",25,25,"cComboCalibra", bs.getFieldValue("cComboCalibra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",25,25,"dtDesechado", bs.getFieldValue("cDtDesechado","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta","Motivo de Baja:","ECampo","text",25,25,"cDscMotBaja", bs.getFieldValue("cDscMotBaja","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print("</tr>");
                                   out.println("<tr>");
                                   out.print("<td colspan=\"3\" class=\"ETablaT\">RESPONSABLES DE LA BAJA</td>");
                                   out.println("</tr>");
                                   out.print("<tr>");
                                   // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                   // *************************************************
                                   // Se sustituye la etiqueta "Organo Interno:" por
                                   // "Responsable de Aseguramiento de la Calidad"
                                   // pero en la base de datos y en el bean TVTOXBaja
                                   // seguira siendo cOrganoInterno
                                   //
                                   //out.print(vEti.EtiCampo("EEtiqueta","Organo Interno:","ECampo","text",25,25,"cOrganoInterno", bs.getFieldValue("cOrganoInterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print(vEti.EtiCampo("EEtiqueta","Responsable de Aseguramiento de la Calidad:","ECampo","text",25,25,"cOrganoInterno", bs.getFieldValue("cOrganoInterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   // *************************************************
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta","Usuario:","ECampo","text",25,25,"cNombreUsuario", bs.getFieldValue("cNombreUsuario","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                   // *************************************************
                                   // Se sustituye la etiqueta "Empresa que Inactiva:" por
                                   // "Jefe del Laboratorio"
                                   // pero en la base de datos y en el bean TVTOXBaja
                                   // seguira siendo cInactiva
                                   //
                                   //out.print(vEti.EtiCampo("EEtiqueta","Empresa que Inactiva:","ECampo","text",25,25,"cInactiva", bs.getFieldValue("cInactiva","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print(vEti.EtiCampo("EEtiqueta","Jefe del Laboratorio:","ECampo","text",25,25,"cInactiva", bs.getFieldValue("cInactiva","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   // *************************************************
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta","Observaciones:","ECampo","text",25,25,"cObservacion", bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                   out.print("</tr>");

                                   /*
                                    * ***************************************************************
                                    * Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                    *
                                    * Estas lineas se comentaron debido a que la Dra. Escalante
                                    * me solicitó que esta liga (la cual presenta el Formato para
                                    * la Inactivacion y Baja de Reactivos y Calibradores) se quitara
                                    * de manera definitiva
                                    * ***************************************************************
                                   out.println("<tr>");
                                   out.print("<td class=\"ECampoC\" colspan=\"2\">");
                                   out.print(vEti.clsAnclaTexto("EAncla","Formato Baja","JavaScript:Genera_Doc();","Formato Baja"));
                                   out.print("</td>");
                                   out.println("</tr>");
                                   */

                                   }
                                   // Modificar Registros
                                   else{
                               out.println("<tr>");
                              out.print("<td colspan=\"3\" class=\"ETablaT\">INFORMACIÓN DE LA SUSTANCIA</td>");
                              out.println("</tr>");

                              out.println("<tr>");
                              out.println(vEti.EtiCampoCS("EEtiqueta","Laboratorio:","ECampo","text",75,75,2,"cDscLaboratorio",bs.getFieldValue("cDscLaboratorio","&nbsp;").toString(),0,"","fMayus(this);",false,false,true));
                              out.println("</tr>");

                              out.println("<input type='hidden' name='iCveLaboratorio' value='" + bs.getFieldValue("iCveLaboratorio","&nbsp;").toString() + "'>");
                              out.println("<input type='hidden' name='iAnio' value='" + bs.getFieldValue("iAnio","&nbsp;").toString() + "'>");
                              out.println("<input type='hidden' name='iCveBaja' value='" + bs.getFieldValue("iCveBaja","&nbsp;").toString() + "'>");

                              out.println("<tr>");
                              if (bs.getFieldValue("cComboReactivo","&nbsp;").toString().compareTo("null-null") != 0)
                                out.print(vEti.EtiCampo("EEtiqueta","Reactivo/Calibrador:","ECampo","text",25,25,"cComboReactivo", bs.getFieldValue("cComboReactivo","&nbsp;").toString(),0,"","fMayus(this);",false,false,true));
                              else
                                out.print(vEti.EtiCampo("EEtiqueta","Reactivo/Calibrador:","ECampo","text",25,25,"cComboCalibra", bs.getFieldValue("cComboCalibra","&nbsp;").toString(),0,"","fMayus(this);",false,false,true));
                               out.print("</tr>");

                              out.println("<tr>");
                              out.println(vEti.EtiCampoCS("EEtiqueta","Fecha:","ECampo","text",10,10,2,"dtDesechado",bs.getFieldValue("cDtDesechado","&nbsp;").toString(),0,"","fMayus(this);",false,true,true, request));
                              out.println("</tr>");

                              out.println("<tr>");
                              out.println(vEti.Texto("EEtiqueta", "Motivo de Baja:"));
                              out.println("<td>");
                              TDTOXMotBaja dTOXMotBaja = new TDTOXMotBaja();
                              out.println(vEti.SelectOneRowSinTD("iCveMotBaja", "", dTOXMotBaja.FindByAll("",""), "iCveMotBaja", "cDscMotBaja", request,bs.getFieldValue("iCveMotBaja","&nbsp;").toString(), true));
                              out.println("</td>");
                              out.println("</tr>");

                              out.println("<tr>");
                              out.print("<td colspan=\"3\" class=\"ETablaT\">RESPONSABLES DE LA BAJA</td>");
                              out.println("</tr>");

                              out.println("<tr>");
                              // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                              // *************************************************
                              // Se sustituye la etiqueta "Organo Interno:" por
                              // "Responsable de Aseguramiento de la Calidad"
                              // pero en la base de datos y en el bean TVTOXBaja
                              // seguira siendo cOrganoInterno
                              //
                              //out.print(vEti.EtiAreaTextoCS("EEtiqueta","Organo Interno:","ECampo", 50, 5, 2, "cOrganoInterno",bs.getFieldValue("cOrganoInterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,true));
                              out.print(vEti.EtiAreaTextoCS("EEtiqueta","Responsable de Aseguramiento de la Calidad:","ECampo", 50, 5, 2, "cOrganoInterno",bs.getFieldValue("cOrganoInterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,true));
                              // *************************************************
                              out.println("</tr>");

                             // TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                              Vector vcPersonal;
                              int iUniMed = 0;
                              if(request.getParameter("iCveLaboratorio") == null){
                                 vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                 if(vcPersonal.size() != 0){
                                     iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                 }
                              }else{
                                    iUniMed = Integer.parseInt(request.getParameter("iCveLaboratorio"),10);
                              }
                              vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                              out.println("<tr>");
                              out.println(vEti.Texto("EEtiqueta","Usuario:"));
                              out.println("<td colspan=\"2\">");
                              out.print(vEti.SelectOneRowSinTD("iCveUsuBaja", "", vcPersonal,"iCveUsuario", "cNomCompleto", request,bs.getFieldValue("iCveUsuBaja","&nbsp;").toString() ));
                              out.println("</td>");
                              out.println("</tr>");

                              out.println("<tr>");
                              // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                              // *************************************************
                              // Se sustituye la etiqueta "Empresa que Inactiva:" por
                              // "Jefe del Laboratorio"
                              // pero en la base de datos y en el bean TVTOXBaja
                              // seguira siendo cInactiva
                              //
                              //out.print(vEti.EtiAreaTextoCS("EEtiqueta","Empresa que Inactiva:","ECampo",50,5,2,"cInactiva",bs.getFieldValue("cInactiva","&nbsp;").toString(),0,"","fMayus(this);",false,true,true));
                              out.print(vEti.EtiAreaTextoCS("EEtiqueta","Jefe del Laboratorio:","ECampo",50,5,2,"cInactiva",bs.getFieldValue("cInactiva","&nbsp;").toString(),0,"","fMayus(this);",false,true,true));
                              // *************************************************
                              out.println("</tr>");

                              out.println("<tr>");
                              out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,2,"cObservacion",bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","fMayus(this);",false,true,true));
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
