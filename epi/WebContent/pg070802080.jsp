<%/**
 * Title:       Asignación de Pruebas Rápidas y FFCCC
 * Description: Pantalla para solicitar información para asignación
 * Copyright:   2004
 * Company:     Micros Personales
 * @author      Rafael Miranda Blumenkron
 * @version     1.0
 * Clase:       pg070802080.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070802080CFG  clsConfig = new pg070802080CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802080.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802080.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "";    // modificar
  String cCveOrdenar  = "";  // modificar
  String cDscFiltrar  = "";    // modificar
  String cCveFiltrar  = "";  // modificar
  String cTipoFiltrar = "";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar
  Vector vAlmacen     = new Vector();
  Vector vTpoArticulo = new Vector();
  Vector vFamilia     = new Vector();
  Vector vArticulo    = new Vector();
  Vector vLotes       = new Vector();
  String cvAlmacen     = "";
  String cvTpoArticulo = "";
  String cvFamilia     = "";
  String cvArticulo    = "";
  String cvConcepto    = "";
  String cvOpcion      = "";
  TVALMLote VLote;

  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();
  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  boolean lCaptura = clsConfig.getCaptura();
  boolean lNuevo = clsConfig.getNuevo();

  TFechas dtFecha = new TFechas(vEntorno.getNumModuloStr());
  String cFiltro = "";

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  String cUsuario = "";
  if (vUsuario!=null)
     cUsuario = vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno();

  String cCondicion = "";
  String cClave    = "";
  String cPosicion = "";
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
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
 %>
  <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="5">Asignación de Pruebas Rápidas y F.F.C.C.C.</td>
            </tr>
             <tr>
              <td class="EEtiqueta">Ejercicio:</td>
              <td class="ECampo"><select name="iEjercicio"><%
                int iEjercicioAct = dtFecha.getIntYear(dtFecha.TodaySQL());
                int iEjercicioFin = iEjercicioAct + 1;
                for (int i=iEjercicioAct;i<=iEjercicioFin;i++){
                  if (clsConfig.ivAnio == i)
                    out.println("<option selected value=\"" + i + "\">" + i + "</option>");
                  else
                    out.println("<option value=\"" + i + "\">" + i + "</option>");
                }
              %></select></td>
              <td class="EEtiqueta">Unidad Médica:</td>
              <td class="ECampo"><%
                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                if (vUsuario!=null)
                  out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
              %></td>
             <td class="ECampo" rowspan="3"><%
               out.println(new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar", "bBuscar", vEntorno.getTipoImg(),
                              "Buscar los Lotes del Artículo","Buscar", vParametros));
             %></td>
             </tr>
             <tr>
              <td class="EEtiqueta">Almacén:</td>
               <td class="ECampo">
                 <select name="SLSAlmacen" size="1" onChange="llenaSLT(1,document.forms[0].SLSAlmacen.value,'','',document.forms[0].SLSTipoArticulo,'','');">
                 <% vAlmacen = clsConfig.vAlmacenes;
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
                 %></select>
               </td>
               <td class="EEtiqueta">Tipo de Artículo:</td>
               <td class="ETabla">
               <select name="SLSTipoArticulo" size="1" onChange="llenaSLT(2,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,'',document.forms[0].SLSFamilia,'','');">
               <% vTpoArticulo = clsConfig.vTpoArticulos;
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
               %></select>
               </td>
             </tr>
             <tr>
               <td class="EEtiqueta">Familia:</td>
               <td class="ECampo">
                 <select name="SLSFamilia" size="1" onChange="llenaSLT(3,document.forms[0].SLSAlmacen.value,document.forms[0].SLSTipoArticulo.value,document.forms[0].SLSFamilia.value,document.forms[0].SLSArticulo,'','');" >
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
                 %></select>
               </td>
               <td class="EEtiqueta">Artículo:</td>
               <td class="ECampo">
                 <select name="SLSArticulo" size="1">
                 <% if (request.getParameter("SLSArticulo") != null){
                      if (request.getParameter("SLSArticulo") != "")
                        cvArticulo = request.getParameter("SLSArticulo");
                    }
                    if (cvArticulo.compareToIgnoreCase("") == 0)
                      out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                      out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    vArticulo = clsConfig.vArticulos;
                    if (vArticulo != null)
                      if (!vArticulo.isEmpty())
                        for(int i=0;i<vArticulo.size();i++){
                          TVALMArticulo VALMArticulo = (TVALMArticulo) vArticulo.get(i);
                          if (cvArticulo.compareToIgnoreCase(new Integer(VALMArticulo.getiCveArticulo()).toString()) == 0)
                            out.print("<option selected value=\"" + new Integer(VALMArticulo.getiCveArticulo()).toString() + "\">"  + VALMArticulo.getcDscBreve().toString() + "</option>");
                          else
                            out.print("<option value=\"" + new Integer(VALMArticulo.getiCveArticulo()).toString() + "\">"  + VALMArticulo.getcDscBreve().toString() + "</option>");
                        }
                 %></select>
               </td>
             </tr>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
            <tr>
              <td class="ETablaT" colspan="2">Indique Cantidad y Opción a Generar</td>
            </tr>
            <tr>
              <td class="EEtiqueta">Cantidad:</td>
              <% String cTemp = "";
                 if (request.getParameter("iCantidad") != null){
                   if (request.getParameter("iCantidad") != "")
                     cTemp = request.getParameter("iCantidad");
                 }
                 out.println(vEti.CeldaCampo("ECampo","text",6,4,"iCantidad",cTemp,0,"","fMayus(this);",true,true,true));
              %>
            </tr>
            <tr>
              <td class="ECampo">&nbsp;</td><td class="ECampo">
              <% if (request.getParameter("RSTOpcion") != null){
                   if (request.getParameter("RSTOpcion") != "")
                     cvOpcion = request.getParameter("RSTOpcion");
                 }
                 cTemp = "";
                 if (cvOpcion.compareToIgnoreCase("1") == 0)
                   cTemp = "checked";
                 out.println("<input type=\"radio\" name=\"RSTOpcion\" " + cTemp + " value=\"1\">Formatos Federales de Control y Cadena de custodia (F.F.C.C.C.)<br>");
                 cTemp = "";
                 if (cvOpcion.compareToIgnoreCase("2") == 0)
                   cTemp = "checked";
                 out.println("<input type=\"radio\" name=\"RSTOpcion\" " + cTemp + " value=\"2\">Pruebas Rápidas");
              %>
              </td>
            </tr>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
            <tr>
              <td class="ETablaT" colspan="3">Lotes Encontrados para el Artículo</td>
            </tr>
            <% vLotes = clsConfig.vLotes;
               float iTotal = (float)0.0,
                     iCant  = (float)0.0;
               String cLote = "", cCaducidad = "";
              if (vLotes != null && vLotes.size() > 0){
            %>
            <tr>
              <td class="EEtiqueta">Tipo Artículo:</td>
              <td class="ECampo" colspan="2"><%=clsConfig.cNombreTpo!=null?clsConfig.cNombreTpo.equals("")?"&nbsp;":clsConfig.cNombreTpo:"&nbsp;"%></td>
            </tr>
            <tr>
              <td class="EEtiqueta">Familia:</td>
              <td class="ECampo" colspan="2"><%=clsConfig.cNombreFam!=null?clsConfig.cNombreFam.equals("")?"&nbsp;":clsConfig.cNombreFam:"&nbsp;"%></td>
            </tr>
            <tr>
              <td class="EEtiqueta">Artículo:</td>
              <td class="ECampo" colspan="2"><%=clsConfig.cNombreArt!=null?clsConfig.cNombreArt.equals("")?"&nbsp;":clsConfig.cNombreArt:"&nbsp;"%></td>
            </tr>
            <%  for (int i=0; i<vLotes.size();i++){
                  VLote = (TVALMLote)vLotes.get(i);
                  if (VLote != null){
                    out.println("<tr>");
                    cLote = VLote.getcLote()!=null?VLote.getcLote():"&nbsp;";
                    cCaducidad = VLote.getdtCaducidad()!=null?dtFecha.getFechaDDMMMYYYY(VLote.getdtCaducidad(),"/"):"&nbsp;";
                    iCant = (float)(VLote.getdUnidades()>0.0?VLote.getdUnidades():0.0);
                    iTotal += iCant;
                    out.println("<td class=\"ETabla\">" + cLote + "</td>");
                    out.println("<td class=\"ETablaC\">" + cCaducidad + "</td>");
                    out.println("<td class=\"ETablaR\">" + iCant + "</td>");
                    out.println("</tr>");
                  }
                }
                out.println("<tr>");
                out.println("<td class=\"ETablaR\" colspan=\"2\"><strong>Total Unidades:</strong></td>");
                out.println("<td class=\"ETablaR\"><strong>" + iTotal + "</strong></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td class=\"ETablaR\" colspan=\"2\"><strong>Total Existencia:</strong></td>");
                out.println("<td class=\"ETablaR\"><strong>" + clsConfig.dExistencias + "</strong></td>");
                out.println("</tr>");
              }
              else
                out.println("<tr><td class=\"ETabla\" colspan=\"3\">Este artículo no maneja lotes o no se localizaron lotes vigentes con existencias.</td></tr>");
              out.println("<input type=\"hidden\" name=\"hdTotal\" value=\"" + iTotal + "\">");
            %>
          </table>
      </td>
  </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

