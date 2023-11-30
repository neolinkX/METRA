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
  pg070802040CFG  clsConfig = new pg070802040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070802040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802040.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Unidad|";    // modificar
  String cCveOrdenar  = "iCveSolicSum|cDscUniMed|";  // modificar
  String cDscFiltrar  = "Clave|Unidad|";    // modificar
  String cCveFiltrar  = "ALMSolicSumin.iCveSolicSum|GRLUniMed.cDscUniMed|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  TVUsuario vUsuario = null;
  try{
    vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  }catch(Exception ex){}
  clsConfig.setICveUsuario(vUsuario!=null?vUsuario.getICveusuario():0);

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

  Vector vAlmacen        = new Vector();
  Vector vTpoPrioridad   = new Vector();
  Vector vPeriodo        = new Vector();


  String cvAlmacen      = "";
  String cvTpoPrioridad = "";
  String cvPeriodo      = "";
  String chkProgramada  = "";
  String chkExtra       = "";

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
  <input type="hidden" name="hdCampoClave" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCampoClave3" value="">

  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIni" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>

<!-- Tabla de Filtros -->

  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Suministrar</td>
           </tr><tr>

             <!-- **  Almacen ** -->

             <td class="EEtiqueta">Almacén:</td>
             <td class="ECampo">
                <select name="iCveAlmacen" size="1">
                <%  vAlmacen = clsConfig.vAlmacenes;
                    if (request.getParameter("iCveAlmacen") != null){
                      if (request.getParameter("iCveAlmacen") != "")
                          cvAlmacen = request.getParameter("iCveAlmacen");
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

             <!-- ** Prioridad ** -->

             <td class="EEtiqueta">Prioridad:</td>
             <td class="ETabla">
                <select name="iCveTpoPrioridad" size="1">
             <%     vTpoPrioridad = clsConfig.vALMTpoPrioridad;
                    if (request.getParameter("iCveTpoPrioridad") != null){
                      if (request.getParameter("iCveTpoPrioridad") != "")
                          cvTpoPrioridad = request.getParameter("iCveTpoPrioridad");
                    }

                    if (cvTpoPrioridad.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                    if (!vTpoPrioridad.isEmpty()){
                       for(int i=0;i<vTpoPrioridad.size();i++){
                         TVALMTpoPrioridad vALMTpoPrioridad = new TVALMTpoPrioridad();
                         vALMTpoPrioridad = (TVALMTpoPrioridad) vTpoPrioridad.get(i);
                          if (cvTpoPrioridad.compareToIgnoreCase(new Integer(vALMTpoPrioridad.getICvePrioridad()).toString()) == 0)
                             out.print("<option selected value=\"" + new Integer(vALMTpoPrioridad.getICvePrioridad()).toString() + "\">"  + vALMTpoPrioridad.getCDscPrioridad() + "</option>");
                          else
                             out.print("<option value=\"" + new Integer(vALMTpoPrioridad.getICvePrioridad()).toString() + "\">"  + vALMTpoPrioridad.getCDscPrioridad() + "</option>");
                       }
                    }
             %>
                </select>
             </td>

             <!-- **  Periodo ** -->

             <td class="EEtiqueta">Periodo:</td>
             <td class="ECampo">
                <select name="iCvePeriodo" size="1">
                <% if (request.getParameter("iCvePeriodo") != null){
                      if (request.getParameter("iCvePeriodo") != "")
                          cvPeriodo = request.getParameter("iCvePeriodo");
                    }

                    if (cvPeriodo.compareToIgnoreCase("") == 0)
                       out.print("<option selected value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");
                    else
                       out.print("<option value=\"" + "0"  + "\">" + "Seleccione..." + "</option>");

                    vPeriodo = clsConfig.vALMPeriodo;
                    if (vPeriodo != null)
                      if (!vPeriodo.isEmpty())
                        for(int i=0;i<vPeriodo.size();i++){
                          TVALMPeriodo vALMPeriodo = (TVALMPeriodo) vPeriodo.get(i);
                          if (cvPeriodo.compareToIgnoreCase(new Integer(vALMPeriodo.getICvePeriodo()).toString()) == 0)
                             out.print("<option selected value=\"" + new Integer(vALMPeriodo.getICvePeriodo()).toString() + "\">"  + vALMPeriodo.getCDscPeriodo().toString() + "</option>");
                          else
                             out.print("<option value=\"" + new Integer(vALMPeriodo.getICvePeriodo()).toString() + "\">"  + vALMPeriodo.getCDscPeriodo().toString() + "</option>");
                        }
                %>
                </select>
             </td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar Solicitudes de Suministro","Buscar", vParametros) %></td>
           </tr>
         </table><br>

<!--      </td>
      </tr>


<!-- Tabla de Radio Sets -->

<!--      <tr>
      <td valign="top">
-->
                         <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="3" class="ETablaT">Filtro de Solicitudes </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Solicitudes</td>
                              <td class="ETabla">

                              <%
                                  chkProgramada = "";
                                  chkExtra = "";
                                  if (request.getParameter("lProgramada")!= null){
                                     if (request.getParameter("lProgramada").toString().compareToIgnoreCase("1")==0)
                                          chkProgramada = "checked"; else
                                          chkExtra = "checked";
                                  } else chkProgramada = "checked";

                              out.print("<input type=\"radio\" name=\"lProgramada\" value=\"1\" "+chkProgramada + ">Programadas");
                              out.print("<input type=\"radio\" name=\"lProgramada\" value=\"0\" "+chkExtra +">Extras");
                              %>


                             </td>
                             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar",
                                      "bBuscar", vEntorno.getTipoImg(),
                                      "Buscar Solicitudes Programadas","Buscar", vParametros) %></td>

                            </tr>
                          </table><br>
<%
        if(request.getParameter("hdBoton").toString().compareTo("Comprobante")==0 ||
           request.getParameter("hdBoton").toString().compareTo("Vale")==0  ) {
           out.println(clsConfig.getActiveX());
        }

  %>




                         <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Solicitudes de Suministro</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT">Unidad</td>
                              <td class="ETablaT">Módulo</td>
                              <td class="ETablaT">Área</td>
                              <td class="ETablaT" colspan="4">Situación</td>
                            </tr>

                             <%
                              // modificar según listado
                               if (bs != null){
                                   bs.start();

                                         while(bs.nextRow()){
                                                out.println("<tr>");
                                                out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveSolicSum", "&nbsp;")));
                                                out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscUniMed", "&nbsp;")));
                                                out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscModulo", "&nbsp;")));
                                                out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscArea", "&nbsp;")));


                                                // lSuministrada
                                                if (bs.getFieldValue("lSuministrada").toString().compareToIgnoreCase("0")==0){
                                                     if (clsConfig.getLPagina(cCatalogo)){
                                                          out.print("<td class=\"ECampo\">");
                                                          out.print(vEti.clsAnclaTexto("EAncla","Suministrar","JavaScript:fSuministrar('" + bs.getFieldValue("iAnio","") + "'," + "'" + bs.getFieldValue("iCveAlmacen","") + "'," + "'"+ bs.getFieldValue("iCveSolicSum","") + "'" + ");","Ir a..."));
                                                          out.print("</td>");
                                                     }
                                                } else out.print(vEti.Texto("ETabla","Suministrada"));


                                                // lSuministrada - Para Ver Detalle
                                                if (bs.getFieldValue("lSuministrada").toString().compareToIgnoreCase("1")==0){
                                                     if (clsConfig.getLPagina(cCatalogo)){
                                                          out.print("<td class=\"ECampo\">");
                                                          out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iAnio","") + "'," + "'" + bs.getFieldValue("iCveAlmacen","") + "'," + "'"+ bs.getFieldValue("iCveSolicSum","") + "'" + ");","Ir a..."));
                                                          out.print("</td>");
                                                     }
                                                } else out.print(vEti.Texto("ETabla","&nbsp;"));

                                                // lSuministrada para Generar Comprobante de Salida.
                                                if (bs.getFieldValue("lSuministrada").toString().compareToIgnoreCase("1")==0){
                                                     if (clsConfig.getLPagina(cCatalogo)){
                                                          out.print("<td class=\"ECampo\">");
                                                          out.print(vEti.clsAnclaTexto("EAncla","Comprobante","JavaScript:fComprobante('" + bs.getFieldValue("iAnio","") + "'," + "'" + bs.getFieldValue("iCveAlmacen","") + "'," + "'"+ bs.getFieldValue("iCveSolicSum","") + "'" + ");","Ir a..."));
                                                          out.print("</td>");
                                                     }
                                                } else out.print(vEti.Texto("ETabla","&nbsp;"));

                                                // lSuministrada para Generar Vale de Salida.
                                                if (bs.getFieldValue("lSuministrada").toString().compareToIgnoreCase("1")==0){
                                                     if (clsConfig.getLPagina(cCatalogo)){
                                                          out.print("<td class=\"ECampo\">");
                                                          out.print(vEti.clsAnclaTexto("EAncla","Pase","JavaScript:fVale('" + bs.getFieldValue("iAnio","") + "'," + "'" + bs.getFieldValue("iCveAlmacen","") + "'," + "'"+ bs.getFieldValue("iCveSolicSum","") + "'" + ");","Ir a..."));
                                                          out.print("</td>");
                                                     }
                                                } else out.print(vEti.Texto("ETabla","&nbsp;"));
                                        }
                               }
                                 else {
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50,5, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                               }
                            %>
                          </table>
      </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
  <%if(clsConfig.isBFlag()){
      out.println( "<script language=\"JavaScript\">alert('Se Ha Suministrado El Producto');</script>");
  }%>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
