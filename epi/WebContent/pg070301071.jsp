<%/**
 * Title: Catalogo de las Sustancias
 * Description: Catalogo de las Sustancias
 * Copyright: Micros Personales S.A. de C.V.
 * Company: Micros Personales S.A. de C.V.
 * @author Leonel Popoca G.
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
  pg070301071CFG  clsConfig = new pg070301071CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070301071.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301071.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Cve. de Equipo|Descripcion|";    // modificar
  String cCveOrdenar  = "TOXEquipo.iCveEquipo|EQMEquipo.cDscEquipo|";  // modificar
  String cDscFiltrar  = "Cve. de Equipo|Descripcion|";    // modificar
  String cCveFiltrar  = "TOXEquipo.iCveEquipo|EQMEquipo.cDscEquipo|";  // modificar
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
       cClave  = ""+bs.getFieldValue("iCveEquipo", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
<%                              TEtiCampo vEti = new TEtiCampo();
                                out.println("<tr>");
                                out.print("<td colspan=\"3\" class=\"ETablaT\">Equipos</td>");
                                out.println("</tr>");

                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();

// N U E V O
                                if (lNuevo){
                                  out.println("<tr>");
                                  out.print(vEti.Texto("EEtiqueta", "Equipo:"));
                                  out.println("<td>");
                                  out.print("<select name=\"iCveEquipo\" size=\"1\" OnChange=\"\">");
                                  TDEQMEquipo dEquipo = new TDEQMEquipo();
                                  TVEQMEquipo vEquipo = new TVEQMEquipo();
                                  Vector vcEquipo = new Vector();
                                  vcEquipo = dEquipo.FindByNotEqual();
                                  if (vcEquipo.size() > 0){
                                    out.print("<option value = 0>Seleccione...</option>");
                                    for (int i = 0; i < vcEquipo.size(); i++){
                                      vEquipo = (TVEQMEquipo)vcEquipo.get(i);
                                      out.print("<option value = " + vEquipo.getICveEquipo() + ">" + vEquipo.getCDscEquipo() + ". No.Serie: " + vEquipo.getCNumSerie()  + ". Mod: " + vEquipo.getCModelo() + "</option>");
                                    }
                                  }
                                  else{
                                    out.print("<option value = 0>Datos no disponibles...</option>");
                                  }
                                  out.println("</td>");
                                  out.println("</tr>");
                                  out.println("<tr>");
                                  out.print(vEti.Texto("EEtiqueta","Usado en:"));
                                  out.print(vEti.Texto("ECampo", "Análisis Confirmativo:<input type='radio' name='lCuantCual' value='cuant' checked>" +
                                                                 "Análisis Presuntivo:<input type='radio' name='lCuantCual' value='cual'>"));
                                  out.println("</tr>");
                                  out.println("<tr>");
                                  out.print(vEti.EtiCampo("EEtiqueta","No. Discos:","ECampo","text",5,5,"iCarruseles","",0,"","fMayus(this);",false,true,lCaptura));
                                  out.println("</tr>");
                                  out.println("<tr>");
                                  out.print(vEti.EtiCampo("EEtiqueta","No. Posiciones por Disco:","ECampo","text",5,5,"iPosiciones","",0,"","fMayus(this);",false,true,lCaptura));
                                  out.println("</tr>");
                                }
                                else {
// M O D I F I C A R
                                  if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta", "Equipo:"));
                                    out.println("<td>");
                                    out.print("<select name=\"iCveEquipo\" size=\"1\" disabled>");
                                    TDEQMEquipo dEquipo = new TDEQMEquipo();
                                    TVEQMEquipo vEquipo = new TVEQMEquipo();
                                    Vector vcEquipo = new Vector();
                                    vcEquipo = dEquipo.FindByNotEqualIn(bs.getFieldValue("iCveEquipo","&nbsp;").toString());
                                    if (vcEquipo.size() > 0){
                                      out.print("<option value = 0>Seleccione...</option>");
                                      for (int i = 0; i < vcEquipo.size(); i++){
                                        vEquipo = (TVEQMEquipo)vcEquipo.get(i);
                                        if (bs.getFieldValue("iCveEquipo","&nbsp;").toString().compareToIgnoreCase(new Integer(vEquipo.getICveEquipo()).toString()) == 0)
                                          out.print("<option value = " + vEquipo.getICveEquipo() + " selected>" + vEquipo.getCDscEquipo() + ". No.Serie: " + vEquipo.getCNumSerie()  + ". Mod: " + vEquipo.getCModelo() + "</option>");
                                        else
                                          out.print("<option value = " + vEquipo.getICveEquipo() + ">" + vEquipo.getCDscEquipo() + ". No.Serie: " + vEquipo.getCNumSerie()  + ". Mod: " + vEquipo.getCModelo() + "</option>");
                                      }
                                    }
                                    else{
                                      out.print("<option value = 0>Datos no disponibles...</option>");
                                    }
                                    out.println("</td>");
                                    //out.print(vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",19,19,"iCveEquipo", bs.getFieldValue("iCveEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,!lCaptura,lCaptura,request));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.Texto("EEtiqueta","Usado en:"));
                                    if(bs.getFieldValue("lCuantCual").toString().compareTo("0")==0)
                                    out.print(vEti.Texto("ECampo", "Análisis Confirmativo:<input type='radio' name='lCuantCual' value='cuant'>" +
                                                                 "Análisis Presuntivo:<input type='radio' name='lCuantCual' value='cual' checked>"));
                                    else
                                    out.print(vEti.Texto("ECampo", "Análisis Confirmativo:<input type='radio' name='lCuantCual' value='cuant' checked>" +
                                                                 "Análisis Presuntivo:<input type='radio' name='lCuantCual' value='cual'>"));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Discos:","ECampo","text",5,5,"iCarruseles",""+bs.getFieldValue("iCarrucel"),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","No. Posiciones por Disco:","ECampo","text",5,5,"iPosiciones",""+bs.getFieldValue("iPosiciones"),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                  }
                                  else{
// V E R   D A T O S
                                    if (bs != null){
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",19,19,"iCveEquipo", bs.getFieldValue("iCveEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,!lCaptura,lCaptura,request));
                                      out.println("</tr>");
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",80,80,"cDscEquipo", bs.getFieldValue("cDscEquipo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura, request));
                                      out.println("</tr>");
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","No. Serie:","ECampo","text",50,50,"cNumSerie", bs.getFieldValue("cNumSerie","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                      out.println("</tr>");
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","Modelo:","ECampo","text",50,50,"cModelo", bs.getFieldValue("cModelo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                      out.println("</tr>");
                                      out.println("<tr>");
                                      if(bs.getFieldValue("lCuantCual").toString().compareTo("0")==0)
                                        out.print(vEti.EtiCampo("EEtiqueta","Usado en:","ECampo","text",5,5,"lCuantCual", "Análisis Presuntivo",0,"","fMayus(this);",false,true,lCaptura,request));
                                      else
                                        out.print(vEti.EtiCampo("EEtiqueta","Usado en:","ECampo","text",5,5,"lCuantCual", "Análisis Confirmativo",0,"","fMayus(this);",false,true,lCaptura,request));
                                      out.println("</tr>");
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","No. de Discos:","ECampo","text",5,5,"iCarruseles", bs.getFieldValue("iCarrucel","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                      out.println("</tr>");
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","No. de Posiciones por Disco:","ECampo","text",5,5,"iPosiciones", bs.getFieldValue("iPosiciones","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura,request));
                                      out.println("</tr>");
                                    } else {
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                      out.println("</tr>");
                                    }
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