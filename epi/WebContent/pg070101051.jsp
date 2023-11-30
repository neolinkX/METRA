<%/**
 * Title: Catálogo de Ramas
 * Description: Catálogo de Ramas
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V. 
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070101051CFG
 */%>
 
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070101051CFG  clsConfig = new pg070101051CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101051.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101051.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Descripcion de Rama|Orden|Clave de la Rama|";    // modificar
  String cCveOrdenar  = "cDscRama|iOrden|iCveRama|";  // modificar
  String cDscFiltrar  = "Descripcion de Rama|Orden|Clave de la Rama|";    // modificar
  String cCveFiltrar  = "cDscRama|iOrden|iCveRama|";  // modificar
  String cTipoFiltrar = "8|7|7|";                // modificar
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
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)

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
       cClave  = ""+bs.getFieldValue("iCveServicio", "");
       cClave2 = ""+bs.getFieldValue("iCveRama", "");
     }

  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave1")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClaveA" value="<%if(cClave2.compareTo("")==0) out.print(request.getParameter("hdCampoClave2")); else out.print(cClave2);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                              <td colspan="2" class="ETablaT">Rama</td>
                            </tr>
                            <% if (lNuevo){ // NUEVO
//System.out.println("--->Entrando a Nuevo.");
                                 TDMEDServicio dMedServ = new TDMEDServicio();
                                 Vector vMedServ = new Vector();
                                 vMedServ = dMedServ.FindByAll("");
                                 out.println("<tr>");
                                 out.print(vEti.Texto("ETablaTR", "Servicio:"));
                                 out.print(vEti.SelectOneRow("ETablaTL", "iCveServicio", "", vMedServ, "iCveServicio", "cDscServicio", request, "0"));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Rama:", "ECampo", "text", 80, 100, "cDscRama", "", 0, "", "fMayus(this);", true, true, lCaptura, request));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.println("<td class=\"EEtiqueta\">Observaciones:<p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                 out.print("<td><textarea cols=\"80\" rows=\"5\" name=\"cObservacion\" value=\"\" onkeypress=\"fChgArea(this);\" OnBlur=\"fMayus(this);\" onChange=\"fChgArea(this);\"></textarea></td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiToggle("EEtiqueta", "Estudio:", "ECampo", "lEstudio", "", "", 0, lCaptura, "1", "0", true));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiToggle("EEtiqueta", "Activa:", "ECampo", "lActivo", "1", "", 0, lCaptura, "1", "0", true));
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){
                                     if (lCaptura) {
                                         // MODIFICAR
                                         TDMEDServicio dMedServ = new TDMEDServicio();
                                         Vector vMedServ = new Vector();
                                         vMedServ = dMedServ.FindByAll("");

                                         out.println("<input type=\"hidden\" name=\"iCveServicio\" value=\"" + bs.getFieldValue("iCveServicio").toString() + "\">");
                                         out.println("<input type=\"hidden\" name=\"iCveRama\" value=\"" + bs.getFieldValue("iCveRama").toString() + "\">");
                                         out.println("<input type=\"hidden\" name=\"iOrden\" value=\"" + bs.getFieldValue("iOrden").toString() + "\">");

                                         out.println("<tr>");
                                         out.println(vEti.Texto("ETablaTR", "Servicio:"));
                                         out.print(vEti.Texto("ETablaTL", bs.getFieldValue("cDscServicio").toString()));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.Texto("EEtiqueta", "Clave Rama:"));
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("iCveRama").toString()));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.EtiCampo("EEtiqueta", "Rama:", "ECampo", "text", 80, 100, "cDscRama", bs.getFieldValue("cDscRama").toString(), 0, "", "fMayus(this)", true, true, lCaptura, request));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.println("<td class=\"EEtiqueta\">Observaciones:<p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></p></td>");
                                         out.println("<td><textarea cols=\"80\" rows=\"5\"  name=\"cObservacion\" value=\"" + bs.getFieldValue("cObservacion").toString() + "\" OnBlur=\"fMayus(this);\" onkeypress=\"fChgArea(this);\">" + bs.getFieldValue("cObservacion").toString() + "</textarea></td>");
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.EtiToggle("EEtiqueta", "Estudio:", "ECampo", "lEstudio", bs.getFieldValue("lEstudio").toString(), "", 0, lCaptura, "1", "0", true));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.EtiToggle("EEtiqueta", "Activa:", "ECampo", "lActivo", bs.getFieldValue("lActivo").toString(), "", 0, lCaptura, "1", "0", true));
                                         out.println("</tr>");

                                     } else {
                                         // SOLO MOSTRAR DATOS
                                         String valor = "";
                                         TDMEDServicio dMedServ = new TDMEDServicio();
                                         Vector vMedServ = new Vector();
                                         vMedServ = dMedServ.FindByAll("");

                                         out.println("<input type=\"hidden\" name=\"iCveRama\" value=\"" + bs.getFieldValue("iCveRama").toString() + "\">");

                                         out.println("<tr>");
                                         out.print(vEti.Texto("ETablaTR", "Servicio:"));
                                         out.print(vEti.SelectOneRow("ETablaTL", "iCveServicio", "fCambiaServicio();", vMedServ, "iCveServicio", "cDscServicio", request, "0"));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.Texto("EEtiqueta", "Clave Rama:"));
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("iCveRama").toString()));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.Texto("EEtiqueta", "Rama:"));
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscRama").toString()));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.Texto("EEtiqueta", "Observaciones:"));
                                         out.println(vEti.Texto("ECampo", bs.getFieldValue("cObservacion").toString()));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.Texto("EEtiqueta", "Estudio:"));
                                         if (bs.getFieldValue("lEstudio").equals("1")) { valor = "SI"; }
                                         else if (bs.getFieldValue("lEstudio").equals("0")) { valor = "NO"; }
                                         out.print(vEti.Texto("ECampo", valor));
                                         valor = "";
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         out.print(vEti.Texto("EEtiqueta", "Orden:"));
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("iOrden").toString()));
                                         out.println("</tr>");

                                         out.println("<tr>");
                                         if (bs.getFieldValue("lActivo").equals("1")) { valor = "ACTIVA"; }
                                         else if (bs.getFieldValue("lActivo").equals("0")) { valor = "INACTIVA"; }
                                         out.print(vEti.Texto("EEtiqueta", "Activa:"));
                                         out.print(vEti.Texto("ECampo", valor));
                                         valor = "";
                                         out.println("</tr>");
                                     }
                                 }
                                 else{
                                   // NO HAY REGISTROS
                                   TDMEDServicio dMedServ = new TDMEDServicio();
                                   Vector vMedServ = new Vector();
                                   vMedServ = dMedServ.FindByAll("");
                                   TVMEDServicio tvMedServ = new TVMEDServicio();

                                   if (request.getParameter("iCveServicio") == null || request.getParameter("iCveServicio").equals("")  || request.getParameter("iCveServicio").equals("0")) {
                                       tvMedServ.setCDscServicio("Seleccione...");
                                       tvMedServ.setICveServicio(0);
                                       vMedServ.add(tvMedServ);
                                   }

                                   out.println("<tr>");
                                   out.print(vEti.Texto("ETablaTR", "Servicio:"));
                                   out.print(vEti.SelectOneRow("ETablaTL", "iCveServicio", "fCambiaServicio();", vMedServ, "iCveServicio", "cDscServicio", request, "0"));
                                   out.println("</tr>");

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
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