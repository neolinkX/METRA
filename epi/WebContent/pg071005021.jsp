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
<%@ page import="com.micper.util.*" %>
<%@ page import="java.util.*" %>

<html>
<%
  pg071005021CFG  clsConfig = new pg071005021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071005021.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071005021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";
  String cAsignar  = "pg071003033.jsp";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Motivo|Descripción Motivo|Clave Proceso|Descripción Proceso|";
  String cCveOrdenar  = "GRLMotivo.iCveMotivo|GRLMotivo.cDscMotivo|GRLProceso.iCveProceso|GRLProceso.cDscProceso|";
  String cDscFiltrar  = "Clave Motivo|Descripción Motivo|Clave Proceso|Descripción Proceso|";
  String cCveFiltrar  = "GRLMotivo.iCveMotivo|GRLMotivo.cDscMotivo|GRLProceso.iCveProceso|GRLProceso.cDscProceso|";
  String cTipoFiltrar = "7|8|7|8|";                // modificar
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

  TDGRLProceso dGRLProceso = new TDGRLProceso();
  TVGRLProceso vGRLProceso = new TVGRLProceso();
  Vector vcGRLProceso = new Vector();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg071005021.js)

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
       cClave  = ""+bs.getFieldValue("iCveMotivo", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="iCveMotivo" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveMotivo")); else out.print(cClave);%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="2" class="ETablaT">Motivo
                              </td>
                               <%
                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 50, 50, "cDscMotivo", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println("<td class='EEtiqueta'>Proceso:</td>");
                                 vcGRLProceso = dGRLProceso.FindByAll("", " order by cDscProceso ");
                                 out.print("<td><select name='iCveProceso'>");
                                 out.print("<option value=0>Seleccione...</option>");
                                 for(int i=0; i<vcGRLProceso.size(); i++){
                                   vGRLProceso = (TVGRLProceso)vcGRLProceso.get(i);
                                   out.print("<option value='" + vGRLProceso.getICveProceso() + "'>" + vGRLProceso.getCDscProceso() + "</option>");
                                 }
                                 out.print("</select></td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta", "Cita:"));
                                 out.println("<td>");
                                 out.println("<input type='checkbox' name='lCita' checked>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta", "Pago:"));
                                 out.println("<td>");
                                 out.println("<input type='checkbox' name='lPago' checked>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta", "Constancia:"));
                                 out.println("<td>");
                                 out.println("<input type='checkbox' name='lConstancia' checked>");
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta", "Situación:"));
                                 out.println("<td>");
                                 out.println("<input type='checkbox' name='lActivo' checked>");
                                 out.println("</td>");
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveMotivo", ""+bs.getFieldValue("iCveMotivo", "&nbsp;"), 3, "", "", true, true, false));
                                   out.println("</tr>");

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 50, 50, "cDscMotivo", ""+bs.getFieldValue("cDscMotivo", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");

                                   out.println("<tr>");
                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.println("<td class='EEtiqueta'>Proceso:</td>");
                                     vcGRLProceso = dGRLProceso.FindByAll("", " order by cDscProceso ");
                                     out.print("<td><select name='iCveProceso'>");
                                     for(int i=0; i<vcGRLProceso.size(); i++){
                                       vGRLProceso = (TVGRLProceso)vcGRLProceso.get(i);
                                       if(Integer.parseInt(bs.getFieldValue("iCveProceso").toString()) == (int)vGRLProceso.getICveProceso())
                                         out.print("<option value='" + vGRLProceso.getICveProceso() + "' selected>" + vGRLProceso.getCDscProceso() + "</option>");
                                       else
                                         out.print("<option value='" + vGRLProceso.getICveProceso() + "'>" + vGRLProceso.getCDscProceso() + "</option>");
                                     }
                                     out.print("</select></td>");
                                   }
                                   else
                                     out.print(vEti.EtiCampo("EEtiqueta", "Proceso:", "ECampo", "text", 50, 50, "cDscProceso", ""+bs.getFieldValue("cDscProceso", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));

                                   out.println("</tr>");

                                   out.print("<tr>");
                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lCita"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Cita:", "ECampo", 10, 10, "lCita", "INACTIVO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Cita:", "ECampo", 10, 10, "lCita", "ACTIVO", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Cita:"));
                                     out.print("<td>");
                                     out.print("<input type='checkbox' name='lCita' ");
                                         if(bs.getFieldValue("lCita").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                     out.print("></td>");
                                   }
                                   out.print("</tr>");

                                   out.print("<tr>");
                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lPago"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Pago:", "ECampo", 10, 10, "lPago", "INACTIVO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Pago:", "ECampo", 10, 10, "lPago", "ACTIVO", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Pago:"));
                                     out.print("<td>");
                                     out.print("<input type='checkbox' name='lPago' ");
                                         if(bs.getFieldValue("lPago").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                     out.print("></td>");
                                   }
                                   out.print("</tr>");

                                   out.print("<tr>");
                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lConstancia"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Constancia:", "ECampo", 10, 10, "lConstancia", "INACTIVO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Constancia:", "ECampo", 10, 10, "lConstancia", "ACTIVO", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Constancia:"));
                                     out.print("<td>");
                                     out.print("<input type='checkbox' name='lConstancia' ");
                                         if(bs.getFieldValue("lConstancia").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                     out.print("></td>");
                                   }
                                   out.print("</tr>");

                                   out.print("<tr>");
                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "INACTIVO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "ACTIVO", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Situación:"));
                                     out.print("<td>");
                                     out.print("<input type='checkbox' name='lActivo' ");
                                         if(bs.getFieldValue("lActivo").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                     out.print("></td>");
                                   }
                                   out.println("</tr>");

                                 }
                                 else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='2'>No existen datos coincidentes con el filtro proporcionado.</td>");
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