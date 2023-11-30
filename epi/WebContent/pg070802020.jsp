<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>


<html>
<%
  pg070802020CFG  clsConfig = new pg070802020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802021.jsp";       // modificar
  String cDetalle     = "pg070802021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripci�n|";    // modificar
  String cCveOrdenar  = "iCveArticulo|cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripci�n|";    // modificar
  String cCveFiltrar  = "iCveArticulo|cDscBreve|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;            // modificar
  String cCanWrite   = "yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cAnio = "";
  String cvLoteConfirmativo = "";
  Vector vAlmacen     = new Vector();
  Vector vTpoArticulo = new Vector();
  Vector vFamilia     = new Vector();
  Vector vAlmxArt     = new Vector();
  Vector vUbicacion   = new Vector();
  String cvAlmacen     = "";
  String cvTpoArticulo = "";
  String cvFamilia     = "";
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

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
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
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
  <input type="hidden" name="hdIni" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">B�squeda de los Art�culos</td>
           </tr><tr>
             <td class="EEtiqueta">Almac�n:</td>
             <td class="ECampo">
                <select name="SLSAlmacen" size="1" onChange="llenaSLT(1,document.forms[0].SLSAlmacen.value,'','',document.forms[0].SLSTipoArticulo,document.forms[0].SLSFamilia);">
                <%  vAlmacen = clsConfig.vAlmacenes;
                    if (request.getParameter("SLSAlmacen") != null){
                      if (request.getParameter("SLSAlmacen") != "")
                          cvAlmacen = request.getParameter("SLSAlmacen");
                    }

                    if (cvAlmacen.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                    if (!vAlmacen.isEmpty()){
                       for(int i=0;i<vAlmacen.size();i++){
                         TVALMAlmacen VALMAlmacen = new TVALMAlmacen();
                         VALMAlmacen = (TVALMAlmacen) vAlmacen.get(i);
                          if (cvAlmacen.compareToIgnoreCase(new Integer(VALMAlmacen.getICveAlmacen()).toString()) == 0)
                             out.print("<option selected value=\"" + new Integer(VALMAlmacen.getICveAlmacen()).toString() + "\">"  + VALMAlmacen.getCDscAlmacen().toString() + "</option>");
                          else
                             out.print("<option value=\"" + new Integer(VALMAlmacen.getICveAlmacen()).toString() + "\">"  + VALMAlmacen.getCDscAlmacen().toString() + "</option>");
                       }
                    }
               %>
                </select>
             </td>
             <td class="EEtiqueta">Tipo de Art�culo:</td>
             <td class="ETabla">
                <select name="SLSTipoArticulo" size="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,'');">
             <%     vTpoArticulo = clsConfig.vTpoArticulos;
                    if (request.getParameter("SLSTipoArticulo") != null){
                      if (request.getParameter("SLSTipoArticulo") != "")
                          cvTpoArticulo = request.getParameter("SLSTipoArticulo");
                    }

                    if (cvTpoArticulo.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                    if (!vTpoArticulo.isEmpty()){
                       for(int i=0;i<vTpoArticulo.size();i++){
                         TVALMTpoArticulo VALMTpoArticulo = new TVALMTpoArticulo();
                         VALMTpoArticulo = (TVALMTpoArticulo) vTpoArticulo.get(i);
                          if (cvTpoArticulo.compareToIgnoreCase(new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString()) == 0)
                             out.print("<option selected value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">"  + VALMTpoArticulo.getIIDPartida()  + "-"  + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                          else
                             out.print("<option value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">"  + VALMTpoArticulo.getIIDPartida()  + "-"  + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                       }
                    }
             %>
                </select>
             </td>
             <td>&nbsp;</td>
           </tr>
           <tr>
             <td class="EEtiqueta">Familia:</td>
             <td class="ECampo">
                <select name="SLSFamilia" size="1">
                <% if (request.getParameter("SLSFamilia") != null){
                      if (request.getParameter("SLSFamilia") != "")
                          cvFamilia = request.getParameter("SLSFamilia");
                    }

                    if (cvFamilia.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    vFamilia = clsConfig.vFamilias;
                    if (vFamilia != null)
                      if (!vFamilia.isEmpty())
                        for(int i=0;i<vFamilia.size();i++){
                          TVALMFamilia VALMFamilia = (TVALMFamilia) vFamilia.get(i);
                          if (cvFamilia.compareToIgnoreCase(new Integer(VALMFamilia.getiCveFamilia()).toString()) == 0)
                             out.print("<option selected value=\"" + new Integer(VALMFamilia.getiCveFamilia()).toString() + "\">"  + VALMFamilia.getcDscBreve().toString() + "</option>");
                          else
                             out.print("<option value=\"" + new Integer(VALMFamilia.getiCveFamilia()).toString() + "\">"  + VALMFamilia.getcDscBreve().toString() + "</option>");
                        }
                %>
                </select>
             </td>
             <td>&nbsp;</td>
             <td>&nbsp;</td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 3, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar los Art�culos","javascript:fSubmitForm1('Buscar');", vParametros) %></td>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                             <%
                                if (bs != null) {
                             %>
                            <tr>
                              <td colspan="7" class="ETablaT">Art�culos por Almac�n</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT">Descripci�n Breve</td>
                              <td class="ETablaT">Asignar</td>
                              <td class="ETablaT">M�nimo</td>
                              <td class="ETablaT">M�ximo</td>
                              <td class="ETablaT">Existencia</td>
                              <td class="ETablaT">Ubicaci�n</td>
                            </tr>
                             <% // modificar seg�n listado
                                } else {
                                  out.print("<tr>");
                                  out.print("<td class=\"ETablaT\" colspan=\"7\">Mensaje</td>");
                                  out.print("</tr>");
                                  out.print("<td class=\"ETabla\" colspan=\"7\">No existen datos coincidentes con el filtro proporcionado.</td>");
                                  out.print("</tr>");
                                }

                               vAlmxArt = clsConfig.vAlmxArt;
                               vUbicacion = clsConfig.vUbicacion;
                               boolean lPinto = false;
                               boolean lPinto2 = false;
                               boolean lHabilitado = false;
                               DecimalFormat dfNumber = new DecimalFormat("##,###,##0.00");
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                     lPinto = false;
                                     lPinto2 = false;
                                     lHabilitado = false;
                                     double dvExistencia = 0;
                                     double dvMaximo     = 0;
                                     double dvMinimo     = 0;
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveArticulo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscBreve", "&nbsp;").toString()));
                                     if (!vAlmxArt.isEmpty()){
                                        for (int i=0;i<vAlmxArt.size();i++) {
                                           TVALMArtxAlm VALMArtxAlm = new TVALMArtxAlm();
                                           VALMArtxAlm = (TVALMArtxAlm) vAlmxArt.get(i);
                                           if (new Integer(VALMArtxAlm.getiCveArticulo()).toString().compareToIgnoreCase(bs.getFieldValue("iCveArticulo", "&nbsp;").toString()) == 0){
                                              if (VALMArtxAlm.getdExistencia() == 0){
                                                 out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString(),"1" ,"", 59, true, "S�", "No", true));
                                                 lHabilitado = true;
                                              }
                                              else {
                                                 out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString(),"1" ,"", 59, true, "S�", "No", false)); // era false.
                                                 out.print("<input type=\"hidden\" name=\"TBXHidSel-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString() + "\" value=\"1\">");
                                              }
                                              lPinto = true;
                                              dvExistencia = VALMArtxAlm.getdExistencia();
                                              dvMaximo = VALMArtxAlm.getdMaximo();
                                              dvMinimo = VALMArtxAlm.getdMinimo();
                                           }
                                        }
                                     }
                                     if (!lPinto){
                                        out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString(),"0" ,"", 59, true, "S�", "No", true));
                                        lHabilitado = true;
                                     }
                                     if (lHabilitado)
                                        out.print("<input type=\"hidden\" name=\"TBXHid-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString() + "\" value=\"1\">");

                                     //out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString(),"0" ,"", 59, true, "S�", "No", true));
                                     if (bs.getFieldValue("lMaxMin", "&nbsp;").toString().compareToIgnoreCase("1") == 0){
                                        out.print("<td class=\"ECampo\">");
                                        if (dvMinimo > 0)
                                           out.print(vEti.CampoSinCelda("input",6,6,"FILMin-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString()
                                                     , new Double(dvMinimo).toString(),3,"","",true,true));
                                        else
                                           out.print(vEti.CampoSinCelda("input",6,6,"FILMin-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString()
                                                     , "",3,"","",true,true));
                                        out.print("</td>");
                                        out.print("<td class=\"ECampo\">");
                                        if (dvMaximo > 0)
                                          out.print(vEti.CampoSinCelda("input",6,6,"FILMax-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString()
                                                       , new Double(dvMaximo).toString(),3,"","",true,true));
                                        else
                                           out.print(vEti.CampoSinCelda("input",6,6,"FILMax-" + bs.getFieldValue("iCveArticulo", "&nbsp;").toString()
                                                       , "",3,"","",true,true));
                                        out.print("</td>");
                                     } else {
                                        out.print(vEti.Texto("ETabla", "&nbsp;"));
                                        out.print(vEti.Texto("ETabla", "&nbsp;"));
                                     }
                                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(dvExistencia))));
                                    if (!vUbicacion.isEmpty()){
                                      for (int i=0;i<vUbicacion.size();i++){
                                        TVALMUbicacion VALMUbicacion = new TVALMUbicacion();
                                        VALMUbicacion = (TVALMUbicacion) vUbicacion.get(i);
                                        if (new Integer(VALMUbicacion.getiCveArticulo()).toString().compareToIgnoreCase(bs.getFieldValue("iCveArticulo", "&nbsp;").toString()) == 0 &&
                                            !lPinto2){
                                          out.print("<td class=\"ECampo\">");
                                          out.print(vEti.clsAnclaTexto("EAncla","�rea y Secci�n Asignada","JavaScript:fListado('" + bs.getFieldValue("iCveArticulo","") + "'" + ");",""));
                                          out.print("</td>");
                                          out.print("</tr>");
                                          lPinto2 = true;
                                        }
                                      }
                                    }
                                    if (!lPinto2) {
                                      if (!lPinto)
                                        out.print(vEti.Texto("ETabla", "&nbsp;"));
                                      else {
                                        out.print("<td class=\"ECampo\">");
                                        out.print(vEti.clsAnclaTexto("EAncla","Sin Ubicaci�n","JavaScript:fListado('" + bs.getFieldValue("iCveArticulo","") + "'" + ");",""));
                                        out.print("</td>");
                                        out.print("</tr>");
                                      }
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
