<%/**
 * Title: Catalogo de las Sustancias
 * Description: Catalogo de las Sustancias
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase: pg070306071CGF
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.TDSustancia"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<html>
<%
  pg070306071CFG  clsConfig = new pg070306071CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070306071.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306071.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Muestra|Carrucel|Posición|";    // modificar
  String cCveOrdenar  = "iCveMValida|iCarrucel|iPosicion|";  // modificar
  String cDscFiltrar  = "Muestra|Carrucel|Posición|";    // modificar
  String cCveFiltrar  = "iCveMValida|iCarrucel|iPosicion|";  // modificar
  String cTipoFiltrar = "7|7|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";             // modificar

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

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
TEtiCampo vEti = new TEtiCampo();
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
       cClave2  = ""+bs.getFieldValue("iCveEquipo", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
<td valign="top" align="center">
  <input type="hidden" name="hdBoton" value="">
&nbsp;

                         <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                          <tr>
                           <td class="ETablaT" colspan="4">Filtrar</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Año:</td>
                              <td class="ETabla">
                                 <select name="iAnio" size="1">
                                    <%
                                       for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                             out.print("<option value = " + i + " selected>" + i + "</option>");
                                          else
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                 </select>
                              </td>
                              <td class="EEtiqueta">Laboratorio:</td>
                              <td class="ETabla">
                                  <%  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                      out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                                      // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);
                                  %>
                              </td>
                            </tr>
                          </table>
                          &nbsp;


 <% // Inicio de Datos
                                iAnio = new Integer(request.getParameter("iAnio")).intValue();
                                String iCveUniMed = request.getParameter("iCveUniMed");
                                String hdCampoClave4 = request.getParameter("hdCampoClave4");
                                int iMuestras = 0;
                                try { iMuestras = Integer.parseInt(request.getParameter("iMuestras")); }
                                catch (Exception e) { }
                                String iCveSustancia = request.getParameter("iCveSustancia");
                                String dtEstudio = request.getParameter("dtEstudio");
                                String dCorte = request.getParameter("dCorte");
                                String iCveLaboratorio = "";

      if (request.getParameter("iCveUniMed") != null && !request.getParameter("iCveUniMed").equals(""))
        iCveLaboratorio = request.getParameter("iCveUniMed").toString();
      else if (request.getParameter("hdCampoClave3") != null && !request.getParameter("hdCampoClave3").equals(""))
        iCveLaboratorio = request.getParameter("hdCampoClave3").toString();
      else if (request.getParameter("iCveLaboratorio") != null && !request.getParameter("iCveLaboratorio").equals(""))
        iCveLaboratorio = request.getParameter("iCveLaboratorio").toString();

                                out.println("<input type=\"hidden\" name=\"iAnio\" value=\"" + iAnio + "\">");
                                out.println("<input type=\"hidden\" name=\"iCveUniMed\" value=\"" + iCveUniMed + "\">");
                                out.println("<input type=\"hidden\" name=\"hdCampoClave4\" value=\"" + hdCampoClave4 + "\">");
                                out.println("<input type=\"hidden\" name=\"hdCampoClave3\" value=\"" + iCveLaboratorio + "\">");
                                out.println("<input type=\"hidden\" name=\"iCveLaboratorio\" value=\"" + iCveLaboratorio + "\">");
                                out.println("<input type=\"hidden\" name=\"iMuestras\" value=\"" + iMuestras + "\">");
                                out.println("<input type=\"hidden\" name=\"iCveSustancia\" value=\"" + iCveSustancia + "\">");
                                out.println("<input type=\"hidden\" name=\"dtEstudio\" value=\"" + dtEstudio + "\">");
                                out.println("<input type=\"hidden\" name=\"dCorte\" value=\"" + dCorte + "\">");


                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
                                    TDSustancia dSust = new TDSustancia();
                                    Vector vSust = dSust.FindByAll(" where iCveSustancia=" + iCveSustancia);
                                    out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Corte:"));
                                    out.print(vEti.Texto("ECampo", dCorte));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Fecha:"));
                                    out.print(vEti.Texto("ECampo", dtEstudio));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Droga:"));
                                    out.print(vEti.SelectOneRow("ECampo", "iCveSustancia", "", vSust, "iCveSustancia", "cDscSustancia", request, ""));
                                    out.println("</tr></table><br>");


                                    out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
                                    out.println("<tr>"); %>
                                    <td class="ETablaT">Muestra</td>
                                    <td class="ETablaT">%</td>
                                    <td class="ETablaT">Concentración</td>
                                    <td class="ETablaT">Carrucel</td>
                                    <td class="ETablaT">Posición</td>
                                    <td class="ETablaT">Resultados</td>
<%                                   out.println("</tr>");

                                    for (int i = 0; i < iMuestras; i++) {
                                        out.println("<tr>");
                                        out.print(vEti.Texto("ECampo", "" + (i + 1)));
                                        out.print("<td><input type=\"text\" name=\"dPorcentaje" + i + "\", size=\"8\" maxlength=\"8\"></td>");
                                        out.print("<td><input type=\"text\" name=\"dConcentracion" + i + "\" size=\"12\" maxlength=\"12\"></td>");
                                        out.print(vEti.Texto("ECampo", "1"));
                                        out.print(vEti.Texto("ECampo", "" + (i + 1)));
                                        out.print("<td><input type=\"text\" name=\"dResultados" + i + "\" size=\"12\" maxlength=\"12\"></td>");
                                        out.println("</tr>");
                                    }
                                    out.print("</table>");
                                }
                                else {
                                    if (bs != null) {
                                        out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
                                        %>
                                        <tr>
                                            <td class="ETablaT" colspan="6">Parámetros de Validación - Arrastre</td>
                                        </tr>
                                        <tr>
                                        <td class="ETablaT">Muestra</td>
                                        <td class="ETablaT">%</td>
                                        <td class="ETablaT">Concentración</td>
                                        <td class="ETablaT">Carrucel</td>
                                        <td class="ETablaT">Posición</td>
                                        <td class="ETablaT">Resultados</td>
                                        </tr>
<%

                                        for (int i = 1; i <= bs.rowSize(); i++) {
                                            bs.setRowIdx(i);
                                            out.println("<tr class=\"ETabla\">");
                                            out.print(vEti.Texto("ECampo", "" + bs.getFieldValue("iCveMValida")));
                                            out.print(vEti.Texto("ECampo", "" + bs.getFieldValue("dPorcentaje")));
                                            out.print(vEti.Texto("ECampo", "" + bs.getFieldValue("dConcentracion")));
                                            out.print(vEti.Texto("ECampo", "" + bs.getFieldValue("iCarrusel")));
                                            out.print(vEti.Texto("ECampo", "" + bs.getFieldValue("iPosicion")));
                                            out.print(vEti.Texto("ECampo", "" + bs.getFieldValue("dResultado")));
                                            out.println("</tr>");
                                        }
                                        bs = null;
                                    } else {
                                        out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\"<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                        out.println("</tr></table>");
                                    }
                                }
                            %>

                          </table><% // Fin de Datos %>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>