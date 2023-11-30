<%/**
 * Title: Orden de Servicio por Rama
 * Description: Orden de Servicio por Rama
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070101052CFG
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070101052CFG  clsConfig = new pg070101052CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101052.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101052.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cClave1    = "";
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());


  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar

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
//  String cUpdStatus  = clsConfig.getUpdStatus();
//  String cUpdStatus  = "SaveCancelOnly";
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = "Hidden";
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
       cClave1  = ""+bs.getFieldValue("iCveServicio", "");
       cClave2 = ""+bs.getFieldValue("iCveRama", "");
     }

  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave1%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><% TEtiCampo vEti = new TEtiCampo();
                               %>
                              <td colspan="4" class="ETablaT">Orden de Servicio por Rama</td>
                            </tr>
                            <%
                                 if (bs != null){
                                     // MODIFICAR
                                     TDMEDServicio dMedServ = new TDMEDServicio();
                                     Vector vMedServ = new Vector();
                                     vMedServ = dMedServ.FindByAll(" where lActivo=1 ", "");

                                     out.println("<tr>");
                                     out.print(vEti.Texto("ETablaTR", "Servicio:"));
                                     out.println("<td class=\"ECampo\" colspan=\"3\">");
                                     out.print(vEti.SelectOneRowSinTD("iCveServicio", "fCambiaServicio();", vMedServ, "iCveServicio", "cDscServicio", request, "0"));
                                     out.println("</td>");
                                     out.println("</tr>");

                                     out.println("<tr>");
                                     out.print(vEti.Texto("ETablaT", "Orden Anterior"));
                                     out.print(vEti.Texto("ETablaT", "Nuevo Orden"));
                                     out.print(vEti.Texto("ETablaT", "Descripción"));
                                     out.print(vEti.Texto("ETablaT", "Estudio"));
                                     out.println("</tr>");
                                     String combo = "";
                                     String valor = "";
                                     int i = 1;
                                     int num_ramas = 0;
                                     num_ramas = bs.rowSize();
                                     String selected = "";
                                     bs.prevTo(1);
                                     while(bs.nextRow()) {
                                         out.println("<tr>");
                                         out.print(vEti.Texto("ETablaR", bs.getFieldValue("iOrden").toString()));
                                         combo = "<SELECT NAME=\"iOrden" + i + "\" SIZE=\"1\">";
                                         for(int j = 1; j <= num_ramas; j++) {
                                             if (bs.getFieldValue("iOrden").toString().equals("" + j))
                                                 selected = " selected";
                                             else
                                                 selected = "";
                                             combo += "<option  value=\"" + j + "\"" + selected + ">" + j + "</option>";
                                         }
                                         combo += "</SELECT>";
                                         out.print("<td>" + combo);
                                         out.print("</td>");
                                         out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscRama").toString()));
                                         if (bs.getFieldValue("lEstudio").equals("1")) { valor = "SI"; }
                                         else if (bs.getFieldValue("lEstudio").equals("0")) { valor = "NO"; }
                                         out.print(vEti.Texto("ECampo", valor));
                                         out.println("</tr>");
                                         out.println("<input type=\"hidden\" name=\"iCveRama" + i + "\" value=\"" + bs.getFieldValue("iCveRama") + "\">");
                                         i++;
                                     }

                                       /*
                                       out.print("<tr><td colspan=\"4\" class=\"ETablaC\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Regresar a 'Listado de Ramas' ","JavaScript:fIrRamas('" + bs.getFieldValue("iCveServicio", "") + "');", "Ir a..."));
                                       out.print("</td></tr>");
                                       */

                                     out.println("<input type=\"hidden\" name=\"num_ramas\" value=\"" + i + "\">");
                                 } else {
                                   // NO HAY REGISTROS
                                   TDMEDServicio dMedServ = new TDMEDServicio();
                                   TVMEDServicio tvMedServ = new TVMEDServicio();
                                   Vector vMedServ = new Vector();
                                   vMedServ = dMedServ.FindByAll(" where lActivo=1 ", "");

                                   if (request.getParameter("iCveServicio") == null || request.getParameter("iCveServicio").equals("")  || request.getParameter("iCveServicio").equals("0")) {
                                       tvMedServ.setCDscServicio("Seleccione...");
                                       tvMedServ.setICveServicio(0);
                                       vMedServ.add(tvMedServ);
                                   }
//System.out.println("--->Servicio: " + request.getParameter("iCveServicio"));
                                   out.println("<tr>");
                                   out.print(vEti.Texto("EEtiqueta", "Servicio:"));
                                   out.print(vEti.SelectOneRow("ECampo", "iCveServicio", "fCambiaServicio();", vMedServ, "iCveServicio", "cDscServicio", request, "0"));
                                   out.println("</tr>");

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
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