<%/**
 * Title: Catalogo de las Sustancias
 * Description: Catalogo de las Sustancias
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Ernesto Avalos
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<html>
<%
  pg070301121CFG  clsConfig = new pg070301121CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070301121.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301121.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveSustancia|cDscSustancia|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveSustancia|cDscSustancia|";  // modificar
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
       cClave2  = ""+bs.getFieldValue("iCveSustancia", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
<%                              TEtiCampo vEti = new TEtiCampo();
                                out.println("<tr>");
                                out.print("<td colspan=\"2\" class=\"ETablaT\">Catálogo de las Sustancias Psicotrópicas</td>");
                                out.println("</tr>");

                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",35,35,"cDscSustancia","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiToggle("EEtiqueta", "Activo:", "ECampo", "lActivo", "1", "", 1, true, "1", "0", true));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiToggle("EEtiqueta", "Analizado:", "ECampo", "lAnalizada", "1", "", 1, true, "1", "0", true));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiToggle("EEtiqueta", "Genera Positivos:", "ECampo", "lPositiva", "1", "", 1, true, "1", "0", true));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaST","Informaci&oacute;n para An&aacute;lisis Presuntivo",2));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Indentificador en el Equipo:","ECampo","text",5,5,"cAbrvEq","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaST","Informaci&oacute;n para An&aacute;lisis Confirmatorio",2));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Título del Lote:","ECampo","text",35,35,"cTitRepConf","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Identificador del Lote:","ECampo","text",35,35,"cPrefLoteConf","",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta", "Muestras m&aacute;ximas por lote:", "ECampo", "text",5,3, "iMuestrasLC", "15", 0,"","",true, true, true, request));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta", "Identificador en el Equipo:", "ECampo", "text",35,30, "cAbrevEqAC", "15", 0,"","",true, true, true, request));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta", "Sustancias que Unifica <br>(Separadas por comas):", "ECampo", "text",25,20, "cSustUnifica", "15", 0,"","",true, true, true, request));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiToggle("EEtiqueta", "En la calibración se validan controles:", "ECampo", "lValidaCtrol", "1", "", 1, true, "1", "0", true));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiToggle("EEtiqueta", "Se toma en cuenta la dilución <br> para marcar como erronea la muestra:", "ECampo", "lValConcentracion", "1", "", 1, true, "1", "0", true));
                                    out.println("</tr>");

                                }
                                else {
                                    if (bs!=null){
                                       out.println("<tr>");
                                       out.println(vEti.Texto("EEtiqueta", "Clave:"));
                                       out.println(vEti.Texto("ECampo", bs.getFieldValue("iCveSustancia","&nbsp;").toString()));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",35,35,"cDscSustancia", bs.getFieldValue("cDscSustancia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.println(vEti.Texto("EEtiqueta","Activo:"));
                                       String cDisable = "";
                                       String cChecked = "";
                                       if (!lCaptura)
                                          cDisable = "disabled";
                                       if (bs.getFieldValue("lActivo","0").toString().compareToIgnoreCase("0")!=0)
                                           cChecked = "checked";
                                       out.println("<td>");
                                       out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" " + cDisable + " " +  cChecked + ">");
                                       out.println("</td>");
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.println(vEti.Texto("EEtiqueta","Analizada:"));
                                       String cDisable2 = "";
                                       String cChecked2 = "";
                                       if (!lCaptura)
                                          cDisable2 = "disabled";
                                       if (bs.getFieldValue("lAnalizada","0").toString().compareToIgnoreCase("0")!=0)
                                           cChecked2 = "checked";
                                       out.println("<td>");
                                       out.println("<input type=\"checkbox\" name=\"lAnalizada\" value=\"1\" " + cDisable2 + " " +  cChecked2 + ">");
                                       out.println("</td>");
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.println(vEti.Texto("EEtiqueta","Genera Positivos:"));
                                       String cDisable3 = "";
                                       String cChecked3 = "";
                                       if (!lCaptura)
                                         cDisable3 = "disabled";
                                       if (bs.getFieldValue("lPositiva","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked3 = "checked";
                                       out.println("<td>");
                                       out.println("<input type=\"checkbox\" name=\"lPositiva\" colspan=\"2\"  value=\"1\" " + cDisable3 + " " +  cChecked3 + ">");
                                       out.println("</td>");
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.println(vEti.TextoCS("ETablaST","Informaci&oacute;n para An&aacute;lisis Presuntivo",2));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Clave en el Equipo:","ECampo","text",6,6,"cAbrvEq", bs.getFieldValue("cAbrvEq","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.println(vEti.TextoCS("ETablaST","Informaci&oacute;n para An&aacute;lisis Confirmatorio",2));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Título de Lote:","ECampo","text",35,35,"cTitRepConf", bs.getFieldValue("cTitRepConf","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Identificador del Lote:","ECampo","text",35,35,"cPrefLoteConf", bs.getFieldValue("cPrefLoteConf","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                       out.println("</tr>");
                                    }
                                    else {
                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                       out.println("</tr>");
                                    }
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta", "Muestras máximas por lote:", "ECampo", "text",5,3, "iMuestrasLC", bs.getFieldValue("iMuestrasLC","15").toString(), 0,"","",true, true, lCaptura, request));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta", "Identificador en el Equipo:", "ECampo", "text",35,30, "cAbrevEqAC", bs.getFieldValue("cAbrevEqAC","&nbsp;").toString(), 0,"","",true, true, lCaptura, request));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiCampo("EEtiqueta", "Sustancias que Unifica <br>(Separadas por comas):", "ECampo", "text",25,20, "cSustUnifica", bs.getFieldValue("cSustUnifica","&nbsp;").toString(), 0,"","",true, true, lCaptura, request));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","En la calibración se validan controles:"));
                                    String cDisable4 = "";
                                       String cChecked4 = "";
                                       if (!lCaptura)
                                         cDisable4 = "disabled";
                                       if (bs.getFieldValue("lValidaCtrol","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked4 = "checked";
                                       out.println("<td>");
                                       out.println("<input type=\"checkbox\" name=\"lValidaCtrol\" colspan=\"2\"  value=\"1\" " + cDisable4 + " " +  cChecked4 + ">");
                                       out.println("</td>");
                                       out.println("</tr><tr>");
                                       out.println(vEti.Texto("EEtiqueta","Se toma en cuenta la dilución <br> para marcar como erronea la muestra:"));
                                  if (!lCaptura)
                                         cDisable4 = "disabled";
                                       if (bs.getFieldValue("lValConcentracion","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked4 = "checked";
                                       out.println("<td>");
                                       out.println("<input type=\"checkbox\" name=\"lValConcentracion\" colspan=\"2\"  value=\"1\" " + cDisable4 + " " +  cChecked4 + ">");
                                       out.println("</td>");
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
