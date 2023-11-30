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
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070801031CFG  clsConfig = new pg070801031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070801031.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070801031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";
  String cValActual = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveAlmacen|cDscAlmacen|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveAlmacen|cDscAlmacen|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070801031.js)
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
       cClave  = ""+bs.getFieldValue("iCveAlmacen", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdUniMed" value="<%out.print(request.getParameter("iCveUniMed"));%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Almacén
                              </td>
                            </tr>
                            <%
                               TEtiCampo vEti = new TEtiCampo();
                               boolean lCaptura = clsConfig.getCaptura();
                               boolean lNuevo = clsConfig.getNuevo();

                               out.println("<tr>");
                               out.print(vEti.Texto("ETablaTR", "Unidad Médica:"));
                               TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                               int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                               TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                               boolean lFirst = false;
                               BeanScroller beanSc = new BeanScroller(dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()));

                               if(request.getParameter("iCveUniMed")!=null &&request.getParameter("iCveUniMed").compareTo("")!=0)
                                 cValActual = "" + request.getParameter("iCveUniMed");
                               else if(request.getParameter("hdUniMed")!=null &&request.getParameter("hdUniMed").compareTo("")!=0)
                                 cValActual = "" + request.getParameter("hdUniMed");

                               if (cValActual.compareTo("null") == 0) {
                                 cValActual = "0";
                               }
                               if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0)
                                 out.print("<td><select name=\"iCveUniMed\" size=\"1\" disabled>");
                               else
                                 out.print("<td><select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                               if (beanSc != null) {
                                 out.print("<option value=0>Seleccione...</option>");
                                 for (int i = 0; i < beanSc.rowSize(); i++) {
                                   beanSc.setRowIdx(i + 1);
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
                               out.println("</tr>");

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 100, 100, "cDscAlmacen", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 Vector vcPersonal;
                                 iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                                 int iUniMed = 0;
                                 if(request.getParameter("iCveUniMed") == null){
                                    vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                    if(vcPersonal.size() != 0){
                                        iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                    }
                                 }else{
                                       iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                                 }
                                 vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta","Responsable:"));
                                 out.println("<td colspan=\"2\">");
                                 out.print(vEti.SelectOneRowSinTD("iCveUsuResp", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                                 out.println("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Situación:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="chklActivo" CHECKED>
                                     </td>
                                     <%
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveAlmacen", ""+bs.getFieldValue("iCveAlmacen", "&nbsp;"), 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 100, 100, "cDscAlmacen", ""+bs.getFieldValue("cDscAlmacen", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");
                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                     Vector vcPersonal;
                                     iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("AlmacenProc"));
                                     int iUniMed = 0;
                                     if(request.getParameter("iCveUniMed") == null){
                                        vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                        if(vcPersonal.size() != 0){
                                            iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                        }
                                     }else{
                                           iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                                     }
                                     vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                     out.println("<tr>");
                                     out.println(vEti.Texto("EEtiqueta","Responsable:"));
                                     out.println("<td colspan=\"2\">");
                                     out.print(vEti.SelectOneRowSinTD("iCveUsuResp", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, bs.getFieldValue("iCveUsuResp","").toString()));
                                     out.println("</td>");
                                     out.println("</tr>");
                                   }
                                   else{
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta", "Responsable:", "ECampo", "text", 100, 100, "cResponsable", bs.getFieldValue("cNombre").toString() + " " + bs.getFieldValue("cApPaterno").toString() + " " + bs.getFieldValue("cApMaterno").toString(), 3, "", "fMayus(this);", true, true, lCaptura));
                                     out.println("</tr>");
                                   }
                                 if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                   if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                     out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "INACTIVO", 3, "", "", true, true, false));
                                   else
                                     out.print(vEti.EtiAreaTexto("EEtiqueta", "Situación:", "ECampo", 10, 10, "lActivo", "ACTIVO", 3, "", "", true, true, false));
                                 }
                                 else{
                                   out.print(vEti.Texto("EEtiqueta", "Situación:"));
                                 %>
                                     <td>
                                       <input type="checkbox" name="chklActivo"<%
                                         if(bs.getFieldValue("lActivo").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                       %>>
                                     </td>
                                     <%
                                   }
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