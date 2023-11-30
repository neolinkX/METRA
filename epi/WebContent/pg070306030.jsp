<%/**
 * Title: Listado de la Baja de Reactivos y Calibradores
 * Description: Listado de la Baja de Reactivos y Calibradores
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%

  pg070306030CFG  clsConfig = new pg070306030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070306030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070306030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|";    // modificar
  String cCveOrdenar  = "iCveReactivo|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCveReactivo|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
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
  String cClave2    = "";
  String cClave3   = "";
  String cPosicion = "";
  int j = 0;
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
       cClave   = ""+bs.getFieldValue("iAnio", "");
       cClave2  = ""+bs.getFieldValue("iCveCtrolCalibra", "");
       cClave3   = ""+bs.getFieldValue("iCveBaja", "");
     }


  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">

  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClave2%>">
  <input type="hidden" name="hdCampoClave3" value="<%=cClave3%>">

  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBuscar" value="N">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
         <input type="hidden" name="hdBoton" value="">
         &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                <tr><td colspan="3" class="ETablaT">FILTROS</td></tr><% // Inicio de Datos %>
                                <%
                                String vtipo = "reactivo";
                                if (request.getParameter("tipo") != null) {
                                   vtipo = request.getParameter("tipo");
                                }


                                out.println("<tr>");
                                out.println("<td>&nbsp;</td>");
                                out.println(vEti.Texto("EEtiqueta", "Laboratorio:"));
                                out.println("<td>");
                                /*if (request.getParameter("Busqueda1")!=null){
                                   out.println("<input type=\"checkbox\" name=\"Busqueda1\" value=\"1\" checked>");
                                }else{
                                   out.println("<input type=\"checkbox\" name=\"Busqueda1\" value=\"1\">");
                                }*/


                                    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                      out.print(vEti.SelectOneRowSinTD("iCveLaboratorio","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                      // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);


                               out.println("</td>");
/*
                                out.println("</td>");
                                out.println(vEti.Texto("EEtiqueta", "Laboratorio:") + "<td>");
                                out.println(vEti.SelectOneRowSinTD("iCveLaboratorio", "", (Vector) AppCacheManager.getColl("GRLUniMed",""), "iCveUniMed", "cDscUniMed", request, "0",true));
                                out.println("</td>
*/

                                out.println("<tr>");
                                out.println("<td>");
                                if (request.getParameter("Busqueda3")!=null){
                                   out.println("<input type=\"checkbox\" name=\"Busqueda3\" value=\"1\" checked>");
                                }else{
                                   out.println("<input type=\"checkbox\" name=\"Busqueda3\" value=\"1\">");
                                }
                                out.println("</td>");
                                out.println(vEti.EtiCampo("EEtiqueta","Fecha:","ECampo","text",15,10,"dtBaja","",0,"","fMayus(this);",false,true,true,request));
                                out.println("</tr>");

                                out.println("<tr>");
                                out.println("<td class=\"EEtiqueta\" colspan=\"2\">");
                                   out.println("<table>");
                                     /*
                                      * Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                      * A petición de la Dra. Escalante los reactivos no
                                      * se dan de baja solo Controles o Calibradores
                                      * *************************************************
                                     out.println("<tr>");
                                        out.println("<td  class=\"EEtiquetaL\">");
                                           out.println("<input type=\"radio\" onClick=\"fReactCalib()\" name=\"tipo\" value=\"reactivo\"" + (vtipo.equals("reactivo") ? " checked" : "") + ">Reactivo<br>");
                                        out.println("</td>");
                                     out.println("</tr>");
                                     */
                                     out.println("<tr>");
                                        out.println("<td  class=\"EEtiquetaL\">");
                                           // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                           // *************************************************
                                           //out.println("<input type=\"radio\" name=\"tipo\" onClick=\"fReactCalib()\" value=\"calibrador\"" + (vtipo.equals("calibrador") ? " checked" : "") + ">Calibrador");
                                           out.println("<input type=\"radio\" name=\"tipo\" onClick=\"fReactCalib()\" value=\"calibrador\"" + (vtipo.equals("calibrador") ? " checked" : "") + ">Control o Calibrador");
                                        out.println("</td>");
                                     out.println("</tr>");
                                   out.println("</table>");
                                out.println("</td>");
                                if (request.getParameter("tipo") != null) {
                                     out.println("<td>");
                                     // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                     // *************************************************
                                     // Como se comento lo arriba nunca va a entrar a este if
                                     if (request.getParameter("tipo").equals("reactivo")){
                                        out.println("<select name=\"iCveReactivo\" size=\"1\">");
                                        TDTOXReactivo dReact = new TDTOXReactivo();
                                        TVTOXReactivo vReact = new TVTOXReactivo();
                                        Vector VecReact = new Vector();
                                        if (request.getParameter("iCveLaboratorio")!=null)
                                           VecReact = dReact.FindByAllTpoReact(" and R.lBaja=1 and R.iCveLaboratorio=" + request.getParameter("iCveLaboratorio"), "");
                                        else
                                           VecReact = dReact.FindByAllTpoReact(" and R.lBaja=1 ", "");
                                        if (VecReact.size() > 0){
                                           out.println("<option  value=\"0\">Todos</option>");
                                           for (int i = 0; i < VecReact.size(); i++){
                                              j = j + 1;
                                              vReact = (TVTOXReactivo) VecReact.get(i);

                                              if (request.getParameter("iCveReactivo")!=null && request.getParameter("iCveReactivo").compareTo(""+vReact.getICveReactivo())==0)
                                                 out.println("<option  value=\""+vReact.getICveReactivo()+"\" selected>"+vReact.getCDscBreve()+"</option>");
                                              else
                                                 out.println("<option  value=\""+vReact.getICveReactivo()+"\">"+vReact.getCDscBreve()+"</option>");
                                           }
                                        }else{
                                           out.println("<option  value=\"-1\">No hay Bajas...</option>");
                                        }
                                        out.println("</select>");
                                     }
                                     // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                     // *************************************************
                                     // Aqui es donde si va a entrar siempre
                                     else
                                        if (request.getParameter("tipo").equals("calibrador")){
                                          out.println("<select name=\"iCveCtrolCalibra\" size=\"1\">");
                                          TDTOXCtrolCalibra dCtrlCalibra = new TDTOXCtrolCalibra();
                                          TVTOXCtrolCalibra vCtrlCalibra = new TVTOXCtrolCalibra();
                                          Vector vCalibra = new Vector();
                                          if (request.getParameter("iCveLaboratorio")!=null)
                                             vCalibra = dCtrlCalibra.FindByAll2(" where lBaja=1 and iCveLaboratorio=" + request.getParameter("iCveLaboratorio"), "");
                                          else
                                             vCalibra = dCtrlCalibra.FindByAll2(" where lBaja=1 ", "");
                                          if (vCalibra.size() > 0){
                                             out.println("<option  value=\"0\">Todos</option>");
                                             for (int i = 0; i < vCalibra.size(); i++){
                                                j = j + 1;
                                                vCtrlCalibra = (TVTOXCtrolCalibra) vCalibra.get(i);
                                                if (request.getParameter("iCveCtrolCalibra")!=null && request.getParameter("iCveCtrolCalibra").compareTo(""+vCtrlCalibra.getICveCtrolCalibra())==0)
                                                   out.println("<option  value=\""+vCtrlCalibra.getICveCtrolCalibra()+"\" selected>"+vCtrlCalibra.getCDscBreve()+"</option>");
                                                else
                                                   out.println("<option  value=\""+vCtrlCalibra.getICveCtrolCalibra()+"\">"+vCtrlCalibra.getCDscBreve()+"</option>");
                                             }
                                          }else{
                                             out.println("<option  value=\"-1\">No hay Bajas...</option>");
                                          }
                                          out.println("</select>");
                                        }
                                     out.println("</td>");
                                }
                                else {
                                   // Modificado por Rafael Alcocer Caldera 26/Sep/2006
                                   // *************************************************
                                   //out.println(vEti.Texto("EEtiqueta", "Seleccione Reactivo o Calibrador"));
                                   out.println(vEti.Texto("EEtiqueta", "Seleccione Control o Calibrador"));
                                }
                                out.println("</tr>");

                                %>
                                </table>
                                &nbsp;
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><tr>
                                <td colspan="9" class="ETablaT">SUSTANCIAS</td></tr><tr>
                                <td class="ETablaT">Clave</td>
                                <td class="ETablaT">Lote / C&oacute;digo</td>
                                <td class="ETablaT">Descripción</td>
                                <td class="ETablaT">Fecha de Baja</td>
                                <td class="ETablaT">Fecha de Desecho</td>
                                <td class="ETablaT">Observación</td>
                                <td class="ETablaT" colspan="2">Empresa que Inactiva</td></td>
<%
                               if (bs != null){
                                   bs.start();
                                   if (vtipo.equals("reactivo")) {
                                       while(bs.nextRow()){
                                           out.println("<tr>");
                                           out.print(vEti.Texto("ETablaR","" + bs.getFieldValue("iCveReactivo", "0")));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cLote","&nbsp;").toString()  ));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscBreve","&nbsp;").toString()));
                                           out.print(vEti.Texto("ETablaC","" + bs.getFieldValue("cBaja", "&nbsp;")));
                                           out.print(vEti.Texto("ETablaC", "" + bs.getFieldValue("cDesechado","&nbsp;")));
                                           out.print(vEti.Texto("ETabla","" + bs.getFieldValue("cObservacion", "&nbsp;")));
                                           out.print(vEti.Texto("ETabla", "" + bs.getFieldValue("cInactiva","&nbsp;")));
                                           if (clsConfig.getLPagina(cCatalogo)){
                                              out.print("<td class=\"ECampo\">");
                                              out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iAnio","") + "'," +"'"+ bs.getFieldValue("iCveLaboratorio","") + "','" + bs.getFieldValue("iCveBaja","") + "');","Ir a..."));
                                              out.print("</td>");
                                           }
                                           out.println("</tr>");
                                       }
                                   } else if (vtipo.equals("calibrador")) {
                                       while(bs.nextRow()){
                                           out.println("<tr>");
                                           out.print(vEti.Texto("ETablaR","" + bs.getFieldValue("iCveCtrolCalibra", "0")));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cLote","&nbsp;").toString()  ));
                                           out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscBreve","&nbsp;").toString()));
                                           out.print(vEti.Texto("ETablaC",""+ bs.getFieldValue("cBaja", "&nbsp;")));
                                           out.print(vEti.Texto("ETablaC","" + bs.getFieldValue("cDesechado","&nbsp;")));
                                           out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cObservacion", "&nbsp;")));
                                           out.print(vEti.Texto("ETabla","" + bs.getFieldValue("cInactiva","&nbsp;")));
                                            if (clsConfig.getLPagina(cCatalogo)){
                                              out.print("<td class=\"ECampo\">");
                                              out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iAnio","") + "'," +"'"+ bs.getFieldValue("iCveLaboratorio","") + "','" + bs.getFieldValue("iCveBaja","") + "');","Ir a..."));
                                              out.print("</td>");
                                           }
                                           out.println("</tr>");
                                       }
                                   }
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
