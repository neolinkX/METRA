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
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070301081CFG  clsConfig = new pg070301081CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070301081.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301081.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Equipo|";    // modificar
  String cCveOrdenar  = "iCveRefrigerador|cDscEquipo|";  // modificar
  String cDscFiltrar  = "Clave|Equipo|";    // modificar
  String cCveFiltrar  = "iCveRefrigerador|cDscEquipo|";  // modificar
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
       cClave2  = ""+bs.getFieldValue("iCveRefrigerador", "");
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
                                out.print("<td colspan=\"2\" class=\"ETablaT\">Refrigeradores</td>");
                                out.println("</tr>");

                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();

                                TDEQMEquipo dRefri = new TDEQMEquipo();
                                TVEQMEquipo vRefri = new TVEQMEquipo();
                                Vector vExRefri = new Vector();
                                vExRefri = dRefri.FindByAll("");

                                TDTOXArea dArea = new TDTOXArea();
                                TVTOXArea vArea = new TVTOXArea();
                                Vector VecArea = new Vector();
                                VecArea = dArea.FindByAll();


                                if (lNuevo){
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                    out.println("<td>&nbsp</td>");
                                    out.println("</tr>");
                                    out.println("<tr>" + vEti.Texto("EEtiqueta", "Equipo:") + "<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCveEquipo", "", vExRefri, "iCveEquipo", "cDscEquipoC", request, "0"));
                                    out.println("</td></tr>");
                                    out.println("<tr>" + vEti.Texto("EEtiqueta", "&Aacute;rea:") + "<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCveArea", "", VecArea, "iCveArea", "cDscArea", request, "0"));
                                    out.println("</td></tr>");
                                }
                                else {
                                if (bs != null){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",5,5,"iCveRefrigerador", bs.getFieldValue("iCveRefrigerador","&nbsp;").toString(),0,"","fMayus(this);",false,false,lCaptura));
                                    out.println("</tr>");
                                    if (lCaptura) {
                                        out.println("<tr>" + vEti.Texto("EEtiqueta", "Equipo:") + "<td>");
                                        out.println(vEti.SelectOneRowSinTD("iCveEquipo", "", vExRefri, "iCveEquipo", "cDscEquipoC", request, bs.getFieldValue("iCveEquipo","0").toString()));
                                        out.println("</td></tr>");
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","N&uacute;mero de Serie:","ECampo","text",80,80,"cDscArea", bs.getFieldValue("cNumSerie","&nbsp;").toString(),0,"","fMayus(this);",false,false,lCaptura,request));
                                        out.println("</tr>");
                                        out.println("<tr>" + vEti.Texto("EEtiqueta", "&Aacute;rea:") + "<td>");
                                        out.println(vEti.SelectOneRowSinTD("iCveArea", "", VecArea, "iCveArea", "cDscArea", request, bs.getFieldValue("iCveArea","0").toString()));
                                        out.println("</td></tr>");

                                    } else {
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Equipo:","ECampo","text",80,80,"cDscEquipo", bs.getFieldValue("cDscEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                        out.println("</tr>");
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","N&uacute;mero de Serie:","ECampo","text",80,80,"cDscArea", bs.getFieldValue("cNumSerie","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                        out.println("</tr>");
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","&Aacute;rea:","ECampo","text",80,80,"cDscArea", bs.getFieldValue("cDscArea","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                        out.println("</tr>");
                                    }

                              } else {
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                 out.println("</tr>");
                              }

                                //out.println("<tr>");
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
