<%/**
 * Title: pg070301111.jsp
 * Description: Catalog de Marcas de sustancias
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<html>
<%
  pg070301111CFG  clsConfig = new pg070301111CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070301111.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070301111.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Descripcion|";    // modificar
  String cCveOrdenar  = "iCveMarcaSust|cDscMarcaSust|";  // modificar
  String cDscFiltrar  = "Clave|Descripcion|";    // modificar
  String cCveFiltrar  = "iCveMarcaSust|cDscMarcaSust|";  // modificar
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
  // Esta funci�n no debe modificarse
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
       cClave2  = ""+bs.getFieldValue("iCveSistema", "");
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
                            <tr>
                              <td colspan="2" class="ETablaT">Marcas de las Sustancias
                              </td>
                            </tr>
                            <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                if (lNuevo){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta","Descripci�n:","ECampo","text",50,50,"cDscMarcaSust","",0,"","fMayus(this);",false,true,lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta","Activo:"));
                                   String cDisable = "";
                                   String cChecked = "checked";
                                   out.println("<td>");
                                   out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" " + cDisable + " " +  cChecked + ">");
                                   out.println("</td>");

                                    out.println("</tr>");
                                }
                                else {
                                    if (bs!=null){
                                       out.println("<input type=\"hidden\" name=\"iCveMarcaSust\" value=\""+bs.getFieldValue("iCveMarcaSust","0").toString()+"\">");
                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",5,5,"iCveMarcaSust",bs.getFieldValue("iCveMarcaSust","").toString(),0,"","fMayus(this);",false,true,false));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       out.print(vEti.EtiCampo("EEtiqueta","Descripci�n:","ECampo","text",50,50,"cDscMarcaSust",bs.getFieldValue("cDscMarcaSust","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");
                                       out.println("<tr>");
                                       /*out.print(vEti.EtiCampo("EEtiqueta","Activo","ECampo","text",1,1,"lActivo",bs.getFieldValue("lActivo","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                       out.println("</tr>");*/
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
                                    }
                                    else {
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
