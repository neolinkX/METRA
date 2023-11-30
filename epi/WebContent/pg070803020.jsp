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
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>

<html>
<%
  pg070803020CFG  clsConfig = new pg070803020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070803020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cDetalle = "pg070803021.jsp";
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|";    // modificar
  String cCveOrdenar  = "iCveSolicSum|";  // modificar
  String cDscFiltrar  = "Clave|";    // modificar
  String cCveFiltrar  = "iCveSolicSum|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TFechas dtFecha = new TFechas();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
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
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">

  <input type="hidden" name="hdIAnio" value="">
  <input type="hidden" name="hdICveAlmacen" value="">
  <input type="hidden" name="hdICveSolicSum" value="">
  <input type="hidden" name="hdDscUniMed" value="">
  <input type="hidden" name="hdDscArea" value="">
  <input type="hidden" name="hdDtSolicitud" value="">
  <input type="hidden" name="hdDtSuministro" value="">
  <input type="hidden" name="hdDscPrioridad" value="">
  <input type="hidden" name="hdDscSolicitud" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
     <td valign="top">
                          &nbsp;
                          <input type="hidden" name="hdBoton" value="">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                           <tr><td colspan="6" class="ETablaT">Solicitudes por Usuario</td></tr>
                            <%

// S E L E C T   L A B O R A T O R I O

                               String cValActual = "";
                               out.println("<tr>");
                               out.print(vEti.Texto("ETablaTR", "Unidad Médica:"));
                               TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                               int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                               boolean lFirst = false;
                               BeanScroller beanSc = new BeanScroller(vUsuario.getVUniFiltro(iCveProceso));
                               if(request.getParameter("iCveUniMed")!=null &&request.getParameter("iCveUniMed").compareTo("")!=0)
                                 cValActual = "" + request.getParameter("iCveUniMed");
                               else
                                 cValActual = "0";
                               out.print("<td><select name=\"iCveUniMed\" size=\"1\" OnChange=\"llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);\">");
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

// S E L E C T   M O D U L O

                               out.print("<td class='ETablaTR'>Módulo:</td>");
                               out.print("<td class='ETabla'>");
                               out.println("<select name=\"iCveModulo\" size=\"1\" onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\">");
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
// S E L E C T   A R E A

                               out.print("<td class='ETablaTR'>Área:</td>");
                               out.print("<td class='ETabla'>");
                               out.println("<select name=\"iCveArea\" size=\"1\">");
                               if(request.getParameter("iCveModulo")!= null){
                                 DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
                                 TVGRLAreaModulo  vGRLAreaModulo = new TVGRLAreaModulo();
                                 Vector vcGRLAreaModulo = new Vector();
                                 vcGRLAreaModulo = dGRLAreaModulo.FindByAll(" where iCveUniMed = " + request.getParameter("iCveUniMed") + " and iCveModulo = " + request.getParameter("iCveModulo"));
                                 if (vcGRLAreaModulo.size() > 0){
                                   out.print("<option value = 0>Seleccione...</option>");
                                   for (int i = 0; i < vcGRLAreaModulo.size()-1; i++){
                                     vGRLAreaModulo = (TVGRLAreaModulo)vcGRLAreaModulo.get(i);
                                     if(request.getParameter("iCveArea")!=null && request.getParameter("iCveArea").compareToIgnoreCase(new Integer(vGRLAreaModulo.getICveArea()).toString())==0)
                                       out.print("<option value = " + vGRLAreaModulo.getICveArea() + " selected>" + vGRLAreaModulo.getCDscArea() + "</option>");
                                     else
                                       out.print("<option value = " + vGRLAreaModulo.getICveArea() + ">" + vGRLAreaModulo.getCDscArea() + "</option>");
                                   }
                                   if(request.getParameter("iCveArea")!=null && Integer.parseInt(request.getParameter("iCveArea").toString())==-1)
                                     out.print("<option value=-1 selected>Todas las Areas</option>");
                                   else
                                     out.print("<option value=-1>Todas las Areas</option>");
                                 }
                                 else{
                                   out.print("<option value = 0>Datos no disponibles</option>");
                                 }
                               }
                               else if((request.getParameter("iCveModulo")!=null && Integer.parseInt(request.getParameter("iCveModulo").toString())<1) || request.getParameter("iCveModulo")==null)
                                 out.println("<option value='0' selected>Datos no disponibles</option>");
                                 out.print("</select></td>");
                               out.print("</tr>");

// S E L E C T  A Ñ O

                               out.println("<tr>");
                               out.println("<td class=\"ETablaTR\">Año:</td>");
                               out.println("<td class=\"ETabla\">");
                               out.println("<select name=\"iAnio\" size=\"1\" onchange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                               if(request.getParameter("iAnio")==null)
                                 out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               else if(request.getParameter("iAnio")!=null && Integer.parseInt(request.getParameter("iAnio"))<1)
                                 out.print("<option value = \"-1\" selected>Seleccione...</option>");
                               for (int i = iAnio; i >= (iAnio - iAniosAtras); i--)
                                 if(request.getParameter("iAnio")!=null && Integer.parseInt(request.getParameter("iAnio"))==i)
                                   out.print("<option value = " + i + " selected>" + i + "</option>");
                                 else
                                   out.print("<option value = " + i + ">" + i + "</option>");
                               out.print("</select>");
                               out.print("</td>");

// S E L E C T   P E R I O D O

                               out.print("<td class='ETablaTR'>Periodo:</td>");
                               out.print("<td class='ETabla'>");
                               out.println("<select name=\"iCvePeriodo\" size=\"1\">");
                               if(request.getParameter("iAnio")!= null){
                                 TDALMPeriodo dALMPeriodo = new TDALMPeriodo();
                                 TVALMPeriodo vALMPeriodo = new TVALMPeriodo();
                                 Vector vcALMPeriodo = new Vector();
                                 vcALMPeriodo = dALMPeriodo.FindByAll(" where iAnio = " + request.getParameter("iAnio") + " "," order by iCvePeriodo ");
                                 if (vcALMPeriodo.size() > 0){
                                   out.print("<option value = 0>Seleccione...</option>");
                                   for (int i = 0; i < vcALMPeriodo.size(); i++){
                                     vALMPeriodo = (TVALMPeriodo)vcALMPeriodo.get(i);
                                     if(request.getParameter("iCvePeriodo")!=null && request.getParameter("iCvePeriodo").compareToIgnoreCase(new Integer(vALMPeriodo.getICvePeriodo()).toString())==0)
                                       out.print("<option value = " + vALMPeriodo.getICvePeriodo() + " selected>" + vALMPeriodo.getCDscPeriodo() + "</option>");
                                     else
                                       out.print("<option value = " + vALMPeriodo.getICvePeriodo() + ">" + vALMPeriodo.getCDscPeriodo() + "</option>");
                                   }
                                   if(request.getParameter("iCvePeriodo")!=null && Integer.parseInt(request.getParameter("iCvePeriodo").toString())==-1)
                                     out.print("<option value=-1 selected>Todos los Periodos</option>");
                                   else
                                     out.print("<option value=-1>Todos los Periodos</option>");
                                 }
                                 else{
                                   out.print("<option value = 0>Datos no disponibles</option>");
                                 }
                               }
                               else if((request.getParameter("iCveModulo")!=null && Integer.parseInt(request.getParameter("iCveModulo").toString())<1) || request.getParameter("iCveModulo")==null)
                                 out.println("<option value='0' selected>Datos no disponibles</option>");
                                 out.print("</select></td>");

// R A D I O   P R O G R A M A D A

                              out.print("<td class='ETablaTR'>Solicitudes:</td>");
                              out.print("<td class='ETabla'>");
                              if(request.getParameter("chkProgExt")!=null && request.getParameter("chkProgExt").compareTo("Programada")==0){
                                out.print("<input type='radio' name='chkProgExt' value='Programada' checked>Programadas");
                                out.print("<input type='radio' name='chkProgExt' value='Extra'>Extras");
                              }
                              else if(request.getParameter("chkProgExt")!=null && request.getParameter("chkProgExt").compareTo("Extra")==0){
                                out.print("<input type='radio' name='chkProgExt' value='Programada'>Programadas");
                                out.print("<input type='radio' name='chkProgExt' value='Extra' checked>Extras");
                              }
                              else{
                                out.print("<input type='radio' name='chkProgExt' value='Programada' checked>Programadas");
                                out.print("<input type='radio' name='chkProgExt' value='Extra'>Extras");
                              }

// R E S U L T A D O S   D E   L A   C O N S U L T A

                                    if (bs != null){
                                      out.print("<tr>");
                                      out.print("<td class=\"ETablaT\">Clave</td>");
                                      out.print("<td class=\"ETablaT\">Area</td>");
                                      out.print("<td class=\"ETablaT\">Solicitud</td>");
                                      out.print("<td class=\"ETablaT\">Suministro</td>");
                                      out.print("<td class=\"ETablaT\">Prioridad</td>");
                                      if(clsConfig.getLPagina(cDetalle)){
                                        out.print("<td class=\"ETablaT\">Detalle</td>");
                                      }
                                      out.print("</tr>");
                                      bs.start();
                                      while(bs.nextRow()){
                                        out.print("<tr>");
                                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveSolicSum", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscArea", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaC", bs.getFieldValue("dtSolicitud", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETablaC", bs.getFieldValue("dtSuministro", "&nbsp;").toString()));
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscPrioridad", "&nbsp;").toString()));
                                        if(clsConfig.getLPagina(cDetalle)){
                                          out.print("<td class=\"ETabla\">");
                                          out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrDetalle('" + bs.getFieldValue("iAnio") +  "', '" +  bs.getFieldValue("iCveAlmacen") + "', '" + bs.getFieldValue("iCveSolicSum") + "', '" +  bs.getFieldValue("cDscUniMed")  + "', '" +  bs.getFieldValue("cDscArea") + "', '" +  bs.getFieldValue("dtSolicitud") + "', '" +  bs.getFieldValue("dtSuministro") + "', '" +  bs.getFieldValue("cDscPrioridad") + "', '" + request.getParameter("chkProgExt") + "', '" + request.getParameter("iCveUniMed") +  "', '" +  request.getParameter("iCveModulo") + "', '" + request.getParameter("iCveArea") + "', '" +  request.getParameter("iCvePeriodo") + "');", ""));
                                          out.print("</td>");
                                        }
                                        out.print("</tr>");
                                      }
                                    }
                                    else{
                                      out.print("<tr><td class='ETablaC' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td></tr>");
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


