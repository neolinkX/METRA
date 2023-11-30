<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.text.*" %>

<html>
<%
  pg070802030CFG  clsConfig = new pg070802030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070802030.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Movimiento|Descripción Articulo|";    // modificar
  String cCveOrdenar  = "iCveMovimiento|cDscArticulo|";  // modificar
  String cDscFiltrar  = "Movimiento|Descripción Articulo|";    // modificar
  String cCveFiltrar  = "iCveMovimiento|cDscArticulo|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar
  Vector vAlmacen     = new Vector();
  Vector vTpoArticulo = new Vector();
  Vector vFamilia     = new Vector();
  Vector vArticulo    = new Vector();
  Vector vConcepto    = new Vector();
  Vector vLotes       = new Vector();
  String cvAlmacen     = "";
  String cvTpoArticulo = "";
  String cvFamilia    = "";
  String cvArticulo    = "";
  String cvConcepto    = "";

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
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070802030.js)
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
  <input type="hidden" name="lValidaG" value="0">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveAlmacen", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><input type="hidden" name="hdBoton" value="">
  <td valign="top">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda de los Artículos</td>
           </tr><tr>
                                 <td class="EEtiqueta">Almacén:</td>
                                 <td class="ECampo">
                                   <select name="SLSAlmacen" size="1" onChange="llenaSLT(1,document.forms[0].SLSAlmacen.value,'','',document.forms[0].SLSTipoArticulo,'','');">
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
                                   <% boolean lNuevo = clsConfig.getNuevo();
                                      if (lNuevo){
                                        out.print("<script language=\"JavaScript\">");
                                        out.print("   document.forms[0].SLSAlmacen.disabled = 1;");
                                        out.print("</script>");
                                        if (request.getParameter("SLSAlmacen") != null)
                                          if (request.getParameter("SLSAlmacen").toString().compareToIgnoreCase("") != 0)
                                            out.println("<input type=\"hidden\" name=\"SLSAlmacen\" value=\"" + request.getParameter("SLSAlmacen").toString() + "\">");
                                      }
                                   %>
                                 </td>
                                 <td class="EEtiqueta">Tipo de Artículo:</td>
                                 <td class="ETabla">
                                   <select name="SLSTipoArticulo" size="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,'','');">
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
                                            out.print("<option selected value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">" + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                                          else
                                            out.print("<option value=\"" + new Integer(VALMTpoArticulo.getICveTpoArticulo()).toString() + "\">" + VALMTpoArticulo.getCDscBreve().toString() + "</option>");
                                        }
                                      }
                                   %>
                                   </select>
                                   <% if (lNuevo){
                                        out.print("<script language=\"JavaScript\">");
                                        out.print("   document.forms[0].SLSTipoArticulo.disabled = 1;");
                                        out.print("</script>");
                                        if (request.getParameter("SLSTipoArticulo") != null)
                                          if (request.getParameter("SLSTipoArticulo").toString().compareToIgnoreCase("") != 0)
                                            out.println("<input type=\"hidden\" name=\"SLSTipoArticulo\" value=\"" + request.getParameter("SLSTipoArticulo").toString() + "\">");
                                      }
                                   %>

                                 </td>
                                 <td>&nbsp;</td>
                                 </tr>
                                 <tr>
                                 <td class="EEtiqueta">Familia:</td>
                                 <td class="ECampo">
                                   <select name="SLSFamilia" size="1" >
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
                                   <% if (lNuevo){
                                        out.print("<script language=\"JavaScript\">");
                                        out.print("   document.forms[0].SLSFamilia.disabled = 1;");
                                        out.print("</script>");
                                        if (request.getParameter("SLSFamilia") != null)
                                          if (request.getParameter("SLSFamilia").toString().compareToIgnoreCase("") != 0)
                                            out.println("<input type=\"hidden\" name=\"SLSFamilia\" value=\"" + request.getParameter("SLSFamilia").toString() + "\">");
                                      }
                                   %>
                                 </td>
             <td>&nbsp;</td>
             <td>&nbsp;</td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar los Artículos","Buscar", vParametros) %></td>
           </tr>
         </table>
</td>
<tr>
</tr>
<td>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="6" class="ETablaT">Entrada al Almacén por Artículo
                              </td>
                            </tr>
                            <%
                               TEtiCampo vEti = new TEtiCampo();
                               boolean lCaptura = clsConfig.getCaptura();
                               DecimalFormat dfNumber = new DecimalFormat("##,###,##0.00");
                               //boolean lNuevo = clsConfig.getNuevo();
                               TFechas Fecha = new TFechas();
                               vArticulo = clsConfig.vArticulos;
                               vConcepto = clsConfig.vConceptos;

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Movimiento:", "ECampo", "text", 10, 10, "FILMovimiento", "" , 3, "", "", true, true, false));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 %>

                                 <%
                                 out.println("</tr>");
                                   %>
                                 <td class="EEtiqueta">Artículo:</td>
                                 <td class="ECampo">
                                   <select name="SLSArticulo" size="1" onChange="llenaSLT(4,document.forms[0].SLSArticulo.value,'','',document.forms[0].FILClave,'','');">
                                   <% if (request.getParameter("SLSArticulo") != null){
                                        if (request.getParameter("SLSArticulo") != "")
                                          cvArticulo = request.getParameter("SLSArticulo");
                                      }

                                      if (cvArticulo.compareToIgnoreCase("") == 0)
                                        out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                                      else
                                        out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                                      if (vArticulo != null)
                                        if (!vArticulo.isEmpty())
                                          for(int i=0;i<vArticulo.size();i++){
                                            TVALMArticulo VALMArticulo = (TVALMArticulo) vArticulo.get(i);
                                            if (cvArticulo.compareToIgnoreCase(new Integer(VALMArticulo.getiCveArticulo()).toString()) == 0)
                                              out.print("<option selected value=\"" + new Integer(VALMArticulo.getiCveArticulo()).toString() + "\">"  + VALMArticulo.getcDscBreve().toString() + "</option>");
                                            else
                                              out.print("<option value=\"" + new Integer(VALMArticulo.getiCveArticulo()).toString() + "\">"  + VALMArticulo.getcDscBreve().toString() + "</option>");
                                         }
                                   %>
                                   </select>
                                 </td>
                                 <%
                                 out.print(vEti.EtiCampo("EEtiqueta", "Clave:" , "ECampo", "text", 20, 20, "FILClave", "", 23, "", "fMayus(this);", true, true, lCaptura));
                            %>
<script language="JavaScript">
  if (document.forms[0].FILClave)
    document.forms[0].FILClave.disabled = 1;
</script>
                            <%
                                 out.println("<td class=\"EEtiqueta\">Producto No Conforme:</td>");
                                 out.print(vEti.ToggleMov("ETabla", "TBXlPNC","0" ,"", 59, true, "Sí", "No", lCaptura));
                                 //out.print(vEti.EtiCampo("EEtiqueta", "Clave:" , "ECampo", "text", 20, 20, "FILClave", "", 23, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print("<td class='ETablaT' colspan='6'>Información de la Entrada del Artículo</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 %>

                                 <td class="EEtiqueta">Concepto:</td>
                                 <td class="ECampo">
                                   <select name="SLSConcepto" size="1">
                                   <% if (request.getParameter("SLSConcepto") != null){
                                        if (request.getParameter("SLSConcepto") != "")
                                          cvConcepto = request.getParameter("SLSConcepto");
                                      }

                                      if (cvConcepto.compareToIgnoreCase("") == 0)
                                        out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                                      else
                                        out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                                      if (vConcepto != null)
                                        if (!vConcepto.isEmpty())
                                          for(int i=0;i<vConcepto.size();i++){
                                            TVALMConcepto VALMConcepto = (TVALMConcepto) vConcepto.get(i);
                                            if (cvConcepto.compareToIgnoreCase(new Integer(VALMConcepto.getICveConcepto()).toString()) == 0)
                                              out.print("<option selected value=\"" + new Integer(VALMConcepto.getICveConcepto()).toString() + "\">"  + VALMConcepto.getCDscConcepto().toString() + "</option>");
                                            else
                                              out.print("<option value=\"" + new Integer(VALMConcepto.getICveConcepto()).toString() + "\">"  + VALMConcepto.getCDscConcepto().toString() + "</option>");
                                         }
                                   %>
                                   </select>
                                 </td>
                                 <%
                                 out.println("<td class=\"EEtiqueta\">Ingreso:</td>");
                                 out.println("<td class=\"ECampo\">");
                                 out.print(vEti.CampoSinCelda("input",10,10,"dtIngreso" ,Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(),"/"),3,"","",true,true));
                                 out.println("</td>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Unidades:" , "ECampo", "text", 10, 10, "FILUnidades", "", 23, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print("<td class='ETablaT' colspan='6'>Información del Lote del Artículo</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Lote:" , "ECampo", "text", 20, 20, "FILLote", "", 23, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("<td class=\"EEtiqueta\">Caducidad:</td>");
                                 out.println("<td class=\"ECampo\">");
                                 out.print(vEti.CampoSinCelda("input",10,10,"dtCaducidad" ,Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(),"/"),3,"","",true,true));
                                 out.println("</td>");
                                 //out.print(vEti.EtiCampo("EEtiqueta", "Caducidad:" , "ECampo", "text", 20, 20, "FILCaducidad", "", 23, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print("<td class='ETablaT' colspan='6'>Información Complementaria</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,3,3,"TXTObservacion","",4,"","fMayus(this);",true,true,lCaptura));
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){
                                   TVALMMovimiento x = (TVALMMovimiento) bs.getCurrentBean();
                                   boolean lPinto = false;
                                   boolean lPinto2 = false;
                                   boolean lPinto3 = false;
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Movimiento:", "ECampo", "text", 10, 10, "FILMovimiento", bs.getFieldValue("iCveMovimiento", "&nbsp;").toString() , 3, "", "", true, true, lCaptura));
                                   out.println("<td colspan=\"4\">&nbsp;</td>");
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   if (!vArticulo.isEmpty()) {
                                      for(int i=0;i<vArticulo.size();i++){
                                         TVALMArticulo VALMArticulo = new TVALMArticulo();
                                         VALMArticulo = (TVALMArticulo) vArticulo.get(i) ;
                                         if (bs.getFieldValue("iCveArticulo", "&nbsp;").toString().compareToIgnoreCase(new Integer(VALMArticulo.getiCveArticulo()).toString()) == 0){
                                           out.print(vEti.EtiCampo("EEtiqueta", "Artículo:", "ECampo", "text", 10, 10, "FILArticulo", VALMArticulo.getcDscBreve() , 3, "", "", true, true, lCaptura));
                                           if (VALMArticulo.getcCveArticulo().toString().compareToIgnoreCase("") != 0)
                                             out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 10, 10, "FILClave", VALMArticulo.getcCveArticulo() , 3, "", "", true, true, lCaptura));
                                           else
                                             out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 10, 10, "FILClave", "&nbsp;", 3, "", "", true, true, lCaptura));
                                            lPinto = true;
                                         }
                                      }
                                   }
                                   if (!lPinto){
                                     out.print(vEti.EtiCampo("EEtiqueta", "Artículo:", "ECampo", "text", 10, 10, "FILArticulo", "&nbsp;" , 3, "", "", true, true, lCaptura));
                                     out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 10, 10, "FILClave", "&nbsp;" , 3, "", "", true, true, lCaptura));
                                   }
                                   out.println("<td colspan=\"2\">&nbsp;</td>");
                            %>
<script language="JavaScript">
  if (document.forms[0].FILClave)
    document.forms[0].FILClave.disabled = 1;
</script>
                            <%
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.println("<td class=\"EEtiqueta\">Producto No Conforme:</td>");
                                   if (bs.getFieldValue("lPNC", "&nbsp;").toString().compareToIgnoreCase("1") == 0)
                                     out.print(vEti.ToggleMov("ETabla", "TBXlPNC","1" ,"", 59, true, "Sí", "No", lCaptura));
                                   else
                                     out.print(vEti.ToggleMov("ETabla", "TBXlPNC","0" ,"", 59, true, "Sí", "No", lCaptura));
                                   out.println("<td colspan=\"4\">&nbsp;</td>");
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print("<td class='ETablaT' colspan='6'>Información de la Entrada del Artículo</td>");
                                   out.println("</tr>");
                                   if (!vConcepto.isEmpty()){
                                     for(int j=0;j<vConcepto.size();j++){
                                        TVALMConcepto VALMConcepto = new TVALMConcepto();
                                        VALMConcepto = (TVALMConcepto) vConcepto.get(j);
                                        if (new Integer(VALMConcepto.getICveConcepto()).toString().compareToIgnoreCase(bs.getFieldValue("iCveConcepto", "&nbsp;").toString()) == 0){
                                          out.print(vEti.EtiCampo("EEtiqueta", "Concepto:", "ECampo", "text", 10, 10, "FILConcepto", VALMConcepto.getCDscConcepto(), 3, "", "", true, true, lCaptura));
                                          lPinto2 = true;
                                        }
                                     }
                                   }
                                   if (!lPinto2)
                                     out.print(vEti.EtiCampo("EEtiqueta", "Concepto:", "ECampo", "text", 10, 10, "FILConcepto", "&nbsp;", 3, "", "", true, true, lCaptura));
                                   out.print(vEti.EtiCampo("EEtiqueta", "Ingreso:", "ECampo", "text", 10, 10, "FILIngreso", Fecha.getFechaDDMMYYYY(x.getdtMovimiento(), "/") , 3, "", "", true, true, lCaptura));
                                   out.print(vEti.EtiCampo("EEtiqueta", "Unidades:", "ECampo", "text", 10, 10, "FILUnidades", dfNumber.format(new Double(bs.getFieldValue("dUnidades", "&nbsp;").toString())) , 3, "", "", true, true, lCaptura));

                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print("<td class='ETablaT' colspan='6'>Información del Lote del Artículo</td>");
                                   out.println("</tr>");
                                   vLotes = clsConfig.getLotes(Fecha.getIntYear(Fecha.TodaySQL()),new Integer(bs.getFieldValue("iCveAlmacen", "&nbsp;").toString()).intValue(),new Integer(bs.getFieldValue("iCveMovimiento", "&nbsp;").toString()).intValue());
                                   if (!vLotes.isEmpty()){
                                     for(int i=0;i<vLotes.size();i++){
                                       TVALMLote VALMLote = new TVALMLote();
                                       VALMLote = (TVALMLote)vLotes.get(i);
                                       out.print(vEti.EtiCampo("EEtiqueta", "Lote:", "ECampo", "text", 10, 10, "FILLote", VALMLote.getcLote() , 3, "", "", true, true, lCaptura));
                                       out.print(vEti.EtiCampo("EEtiqueta", "Caducidad:", "ECampo", "text", 10, 10, "FILCaducidad", Fecha.getFechaDDMMYYYY(VALMLote.getdtCaducidad(),"/") , 3, "", "", true, true, lCaptura));
                                       lPinto3 = true;
                                     }
                                   }
                                   if (!lPinto3){
                                      out.print(vEti.EtiCampo("EEtiqueta", "Lote:", "ECampo", "text", 10, 10, "FILLote", "&nbsp;", 3, "", "", true, true, lCaptura));
                                      out.print(vEti.EtiCampo("EEtiqueta", "Caducidad:", "ECampo", "text", 10, 10, "FILCaducidad", "&nbsp;" , 3, "", "", true, true, lCaptura));
                                   }
                                   out.println("<td colspan=\"2\">&nbsp;</td>");
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print("<td class='ETablaT' colspan='6'>Información Complementaria</td>");
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   if (bs.getFieldValue("cObservacion", "&nbsp;").toString().compareToIgnoreCase("")!= 0)
                                      out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,3,3,"TXTObservacion",bs.getFieldValue("cObservacion", "&nbsp;").toString(),4,"","",true,true,lCaptura));
                                   else
                                      out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,3,3,"TXTObservacion", "&nbsp;",4,"","",true,true,lCaptura));
                                   out.println("<td colspan=\"2\">&nbsp;</td>");
                                   out.println("</tr>");
                                   }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ECampo' colspan='5'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
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
