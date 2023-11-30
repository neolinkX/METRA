<%/**
 * Title:       Ajustes al Inventario
 * Description: Pantalla para solicitar información para ajustes
 * Copyright:   2004
 * Company:     Micros Personales
 * @author      Rafael Miranda Blumenkron
 * @version     1.0
 * Clase:       pg070802070.jsp
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
  pg070802070CFG  clsConfig = new pg070802070CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802070.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802070.jsp\" target=\"FRMCuerpo"); // modificar
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
  Vector vLotes       = new Vector();
  Vector vArticuloLote;
  Vector vTipoMov = new Vector();
  Vector vConceptoMov = new Vector();
  String cvAlmacen     = "";
  String cvTpoArticulo = "";
  String cvFamilia     = "";
  String cvTipoMov     = "";
  String cvConceptoMov = "";
  TVALMLote VLote;
  TVALMArticulo VArticulo;

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
              <td class="ETablaT" colspan="5">Ajustes al Inventario del Almacén</td>
            </tr>
             <tr>
              <td class="EEtiqueta">Unidad Médica:</td>
              <td class="ECampo"><%
                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                if (vUsuario!=null)
                  out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(3,document.forms[0].iCveUniMed.value,'','',document.forms[0].SLSAlmacen,'','');",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
              %></td>
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
             <td class="ECampo" rowspan="3"><%
               out.println(new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar", "bBuscar", vEntorno.getTipoImg(),
                              "Buscar los Artículos","Buscar", vParametros));
             %></td>
             </tr>
             <tr>
              <td class="EEtiqueta">Ejercicio:</td>
              <td class="ECampo"><select name="iEjercicio"><%
                int iEjercicioAct = dtFecha.getIntYear(dtFecha.TodaySQL());
                int iEjercicioIni = iEjercicioAct - Integer.parseInt(vParametros.getPropEspecifica("Anios"),10);
                for (int i=iEjercicioAct;i>=iEjercicioIni;i--){
                  if (clsConfig.ivAnio == i)
                    out.println("<option selected value=\"" + i + "\">" + i + "</option>");
                  else
                    out.println("<option value=\"" + i + "\">" + i + "</option>");
                }
              %></select></td>
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
               <td class="ECampo" colspan="3">
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
                 %></select>
               </td>
             </tr>
             <tr>
               <td class="EEtiqueta">Tipo de Movimiento:</td>
               <td class="ETabla">
               <select name="SLSTipoMov" size="1" onChange="llenaSLT(4,document.forms[0].SLSTipoMov.value,'','',document.forms[0].SLSConceptoMov,'','');">
               <% vTipoMov = clsConfig.vTpoMovimiento;
                  if (request.getParameter("SLSTipoMov") != null){
                    if (request.getParameter("SLSTipoMov") != "")
                      cvTipoMov = request.getParameter("SLSTipoMov");
                  }
                  if (cvTipoMov.compareToIgnoreCase("") == 0)
                    out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                  else
                    out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                  if (!vTipoMov.isEmpty()){
                    for(int i=0;i<vTipoMov.size();i++){
                      TVALMTpoMovimiento VALMTpoMov;
                      VALMTpoMov = (TVALMTpoMovimiento) vTipoMov.get(i);
                      if (cvTipoMov.compareToIgnoreCase(new Integer(VALMTpoMov.getICveTpoMovimiento()).toString()) == 0)
                        out.print("<option selected value=\"" + new Integer(VALMTpoMov.getICveTpoMovimiento()).toString() + "\">" + VALMTpoMov.getCDscBreve().toString() + "</option>");
                      else
                        out.print("<option value=\"" + new Integer(VALMTpoMov.getICveTpoMovimiento()).toString() + "\">" + VALMTpoMov.getCDscBreve().toString() + "</option>");
                    }
                  }
               %></select>
               </td>
               <td class="EEtiqueta">Concepto:</td>
               <td class="ECampo">
                 <select name="SLSConceptoMov" size="1" >
                 <% if (request.getParameter("SLSConceptoMov") != null){
                      if (request.getParameter("SLSConceptoMov") != "")
                        cvConceptoMov = request.getParameter("SLSConceptoMov");
                    }
                    if (cvConceptoMov.compareToIgnoreCase("") == 0)
                      out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                      out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    vConceptoMov = clsConfig.vConcepto;
                    if (vConceptoMov != null)
                      if (!vConceptoMov.isEmpty())
                        for(int i=0;i<vConceptoMov.size();i++){
                          TVALMConcepto VALMConcepto = (TVALMConcepto) vConceptoMov.get(i);
                          if (cvConceptoMov.compareToIgnoreCase(new Integer(VALMConcepto.getICveConcepto()).toString()) == 0)
                            out.print("<option selected value=\"" + new Integer(VALMConcepto.getICveConcepto()).toString() + "\">"  + VALMConcepto.getCDscConcepto().toString() + "</option>");
                          else
                            out.print("<option value=\"" + new Integer(VALMConcepto.getICveConcepto()).toString() + "\">"  + VALMConcepto.getCDscConcepto().toString() + "</option>");
                        }
                 %></select>
               </td>
             </tr>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
            <tr>
              <td class="ETablaT" colspan="4">Datos de los Artículos</td>
            </tr>
            <tr>
              <td class="ETablaT">Id.</td>
              <td class="ETablaT">Clave</td>
              <td class="ETablaT">Descripción</td>
              <td class="ETablaT">Unidad de<br>Almacenaje</td>
              <td class="ETablaT" width="500px">&nbsp;</td>
            <% String cTemp = "", cNombreCant = "", cNombreObs = "";
               vArticuloLote = clsConfig.vArticuloLote;
               float iCant  = (float)0.0;
               String cLote = "", cCaducidad = "";
              if (vArticuloLote!=null && vArticuloLote.size()>0){
                for (int x=0; x<vArticuloLote.size(); x++){
                  out.println("<tr vAlign=\"top\">");
                  VArticulo = (TVALMArticulo)((Vector)vArticuloLote.get(x)).get(0);
                  // Id. Articulo
                  cTemp = VArticulo.getiCveArticulo()!=0?VArticulo.getiCveArticulo()+"":"&nbsp;";
                  cTemp = cTemp.equals("")?"&nbsp;":cTemp;
                  out.println("<td class=\"ETabla\">" + cTemp + "</td>");
                  // Clave Articulo
                  cTemp = VArticulo.getcCveArticulo()!=null?VArticulo.getcCveArticulo():"&nbsp;";
                  cTemp = cTemp.equals("")?"&nbsp;":cTemp;
                  out.println("<td class=\"ETabla\">" + cTemp + "</td>");
                  // Descripción Artículo
                  cTemp = VArticulo.getcDscBreve()!=null?VArticulo.getcDscBreve():"&nbsp;";
                  cTemp = cTemp.equals("")?"&nbsp;":cTemp;
                  out.println("<td class=\"ETabla\">" + cTemp + "</td>");
                  // Unidad Almacenaje
                  cTemp = VArticulo.getcDscUniAlm()!=null?VArticulo.getcDscUniAlm():"&nbsp;";
                  cTemp = cTemp.equals("")?"&nbsp;":cTemp;
                  out.println("<td class=\"ETabla\">" + cTemp + "</td>");
                  out.println("<td class=\"ETabla\"><table border=\"1\" class=\"ETablaInfo\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">");
                  out.println("<tr vAlign=\"top\"><td class=\"ETablaT\" width=\"25%\">");
                  out.println(VArticulo.getlLote()==1?"Lote":"&nbsp;");
                  out.println("</td><td class=\"ETablaT\" width=\"25%\">");
                  out.println(VArticulo.getlLote()==1?"Caducidad":"&nbsp;");
                  out.println("</td>");
                  out.println("<td class=\"ETablaT\" width=\"25%\">Unidades</td>");
                  out.println("<td class=\"ETablaT\" width=\"25%\" colspan=\"2\">Ajuste</td></tr>");
                  vLotes = (Vector)((Vector)vArticuloLote.get(x)).get(1);
                    if (vLotes != null && vLotes.size() > 0){
                      for (int i=0; i<vLotes.size();i++){
                        VLote = (TVALMLote)vLotes.get(i);
                        if (VLote != null){
                          out.println("<tr vAlign=\"top\">");
                          cLote = VLote.getcLote()!=null?VLote.getcLote():"&nbsp;";
                          cCaducidad = VLote.getdtCaducidad()!=null?dtFecha.getFechaDDMMMYYYY(VLote.getdtCaducidad(),"/"):"&nbsp;";
                          iCant = (float)(VLote.getdUnidades()>0.0?VLote.getdUnidades():0.0);
                          out.println("<td class=\"ETabla\">" + cLote + "</td>");
                          out.println("<td class=\"ETablaC\">" + cCaducidad + "</td>");
                          out.println("<td class=\"ETablaR\">" + iCant + "</td>");
                          cNombreCant = "iCantidad_" + VArticulo.getiCveArticulo() + "_";
                          cNombreCant += VArticulo.getlLote()==1?VLote.getiCveLote()+"":"0";
                          cTemp = "";
                          if (request.getParameter("hdBoton")!=null &&
                              !request.getParameter("hdBoton").equalsIgnoreCase("Guardar"))
                            if (request.getParameter(cNombreCant) != null){
                              if (request.getParameter(cNombreCant) != "")
                                cTemp = request.getParameter(cNombreCant);
                            }
                          out.println(vEti.CeldaCampo("ECampo","text",6,12,cNombreCant,cTemp,0,"","fMayus(this);",true,true,true));
                          cNombreObs = "cObserva_" + VArticulo.getiCveArticulo() + "_";
                          cNombreObs += VArticulo.getlLote()==1?VLote.getiCveLote()+"":"0";
                          cTemp = "";
                          if (request.getParameter("hdBoton")!=null &&
                              !request.getParameter("hdBoton").equalsIgnoreCase("Guardar"))
                            if (request.getParameter(cNombreObs) != null){
                              if (request.getParameter(cNombreObs) != "")
                                cTemp = request.getParameter(cNombreObs);
                            }
                          out.println(vEti.EtiAreaTextoSINEtiqueta("ECampo",20,2,cNombreObs,cTemp,0,"","fMayus(this);",false,true,true));
                          out.println("</tr>");
                        }
                      }
                    }
                    else{
                      iCant = (float)(VArticulo.getdUnidades()>0.0?VArticulo.getdUnidades():0.0);
                      out.println("<tr vAlign=\"top\">");
                        out.println("<td class=\"ETabla\">&nbsp;</td>");
                        out.println("<td class=\"ETablaC\">&nbsp;</td>");
                        out.println("<td class=\"ETablaR\">" + iCant + "</td>");
                        cNombreCant = "iCantidad_" + VArticulo.getiCveArticulo() + "_0";
                        cTemp = "";
                        if (request.getParameter("hdBoton")!=null &&
                            !request.getParameter("hdBoton").equalsIgnoreCase("Guardar"))
                          if (request.getParameter(cNombreCant) != null){
                            if (request.getParameter(cNombreCant) != "")
                              cTemp = request.getParameter(cNombreCant);
                          }
                        out.println(vEti.CeldaCampo("ECampo","text",6,12,cNombreCant,cTemp,0,"","fMayus(this);",true,true,true));
                        cNombreObs = "cObserva_" + VArticulo.getiCveArticulo() + "_0";
                        cTemp = "";
                        if (request.getParameter("hdBoton")!=null &&
                            !request.getParameter("hdBoton").equalsIgnoreCase("Guardar"))
                          if (request.getParameter(cNombreObs) != null){
                            if (request.getParameter(cNombreObs) != "")
                              cTemp = request.getParameter(cNombreObs);
                          }
                        out.println(vEti.EtiAreaTextoSINEtiqueta("ECampo",20,2,cNombreObs,cTemp,0,"","fMayus(this);",false,true,true));
                      out.println("</tr>");
                    }
                  out.println("</table></td></tr>");
                }
              }
              else
                out.println("<tr><td class=\"ETabla\" colspan=\"4\">No existen artículos con esta clasificación.</td></tr>");
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

