<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>

<html>
<%
  pg070101041CFG  clsConfig = new pg070101041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101041.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101041.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveServicio|cDscServicio|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveServicio|cDscServicio|";  // modificar
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
<SCRIPT LANGUAGE="JavaScript">

  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }



</SCRIPT>
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
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                            %>

                             <tr>
                             <td colspan="3" class="ETablaT">Servicio</td>
                             </tr>

                             <%
                               if (lNuevo){ // Nuevo Registro

                                    // Descripcion - cDscServicio
                                    out.print("<tr>"+vEti.EtiCampoCS("EEtiqueta","Descripción:","ECampo","text",100,100,2,"cDscServicio","",0,"","fMayus(this);",false,true,lCaptura)+"</tr>");

                                    //Observaciones - cDscObservacion
                                    out.println("<tr>"+"<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" name=\"iNoLetras\" size=\"4\" value=\"\" disabled></p></td>");
                                    out.println("<td><textarea cols=\"100\" rows=\"7\" name=\"cObservacion\" value=\"\" onBlur=\"fMayus(this);\" onkeyup=\"fChgArea(this);\" onchange=\"fChgArea(this);\"></textarea></td></tr>");

                                    // Realizado Por Varios Medicos - lVariosMeds
                                    out.println("<tr>"+"<td class=\"EEtiqueta\">Realizada por Varios<br>Médicos:</td>");
                                    out.println("<td colspan=\"2\" class=\"ETabla\"><input type=\"checkbox\" name=\"lVariosMeds\" value=\"1\"></td></tr>");

                                    // Tipo de Servicio - lPostDiag, lInterconsulta, lDiagnostico
                                    out.println("<tr>"+vEti.Texto("EEtiqueta","Tipo de Servicio:"));
                                    out.println("<td class=\"ETabla\"><input type=\"radio\" name=\"lPostDiag\" Value=\"0\" checked>General"+"&nbsp;"+//"&nbsp;"+"&nbsp;"+
                                                "<input type=\"radio\" name=\"lPostDiag\" value=\"1\">Interconsulta"+//"&nbsp;"+"&nbsp;"+"&nbsp;"+
                                                "<input type=\"radio\" name=\"lPostDiag\" Value=\"2\">Diagnóstico"+//"&nbsp;"+"&nbsp;"+"&nbsp;"+
                                                "<input type=\"radio\" name=\"lPostDiag\" value=\"3\">Adicional</td></tr>");

                                    // Diagnóstico y Recomendaciones - lReqDiag
                                    out.print("<tr>"+vEti.EtiToggle("EEtiqueta","Diagnósticos y/o Recomendaciones:","ECampo","lReqDiag","1","",1,true,"Si","No",lCaptura)+"<tr>");

                                       // Puede subir y ver archivos
                                    out.print("<tr>"+vEti.EtiToggle("EEtiqueta","Puede subir y ver archivos:","ECampo","lSUBIRARCHIVOS","1","",1,true,"Si","No",lCaptura)+"<tr>");

                                    // Activo - lActivo
                                    out.print("<tr>"+vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo","1","",1,true,"Si","No",lCaptura)+"<tr>");

/* Modificacion */                               }
                                  else{      // Consultar o Modificar Registro
                                 if (bs != null){

                                    // Clave - iCveServicio
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Clave:","ECampo","text",5,5,"iCveServicio", bs.getFieldValue("iCveServicio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false)+"</tr>");

                                    // Descripcion - cDscServicio
                                    out.print("<tr>"+vEti.EtiCampo("EEtiqueta","Descripción:","ECampo","text",100,100,"cDscServicio", bs.getFieldValue("cDscServicio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura)+"</tr>");

                                    // Observaciones - cDscObservacion
                                    if (lCaptura) {
                                          out.println("<tr>"+"<td class=\"EEtiqueta\">Observaciones:<br><p class=\"EPieR\">Caract. disponibles:<br><input type=\"text\" name=\"iNoLetras\" size=\"4\" value=\"\" disabled></p></td>");
                                          out.println("<td><textarea cols=\"100\" rows=\"7\" name=\"cObservacion\" value=\"\" onBlur=\"fMayus(this);\" onkeyup=\"fChgArea(this);\" onchange=\"fChgArea(this);\">"+ bs.getFieldValue("cObservacion", "&nbsp;").toString()+"</textarea></td></tr>");

                                          //out.println("<td><textarea cols=\"100\" rows=\"7\" name=\"cObservacion\" value=\"\" onBlur=\"fMayus(this);\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\"></textarea></td></tr>");

                                    } else
                                    out.print("<tr>"+vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",100,7,2,"cObservacion",bs.getFieldValue("cObservacion","&nbsp;").toString(),0,"","",true,true,lCaptura)+"</tr>");


                                    //Realizada por Varios Medicos - lVariosMeds
                                    out.println("<tr>"+"<td class=\"EEtiqueta\">Realizada por Varios<br>Médicos:</td>");
                                    String cDisable = "";
                                    String cChecked = "";
                                    if (!lCaptura)
                                       cDisable = "disabled";
                                    if (bs.getFieldValue("lVariosMeds","0").toString().compareToIgnoreCase("0")!=0)
                                       cChecked = "checked";

                                    out.println("<td>"+"<input type=\"checkbox\" name=\"lVariosMeds\" colspan=\"2\"  value=\"1\" " + cDisable + " " +  cChecked + ">"+"</td>"+"</tr>");

                                    // Tipo de Servicio - lPostDiag, lInterconsulta, lDiagnostico
                                    String cChkGeneral = "";
                                    String cChkInterConsulta = "";
                                    String cChkDiagnostico = "";
                                    String cChkAdicional = "";
                                    if (bs.getFieldValue("lPostDiag","0").toString().compareToIgnoreCase("0")!=0)
                                       cChkGeneral = "checked"; else {
                                            if (bs.getFieldValue("lInterConsulta","0").toString().compareToIgnoreCase("0")!=0)
                                                 cChkInterConsulta = "checked"; else
                                            {
                                                 if (bs.getFieldValue("lDiagnostico","0").toString().compareToIgnoreCase("0")!=0)
                                                      cChkAdicional = "checked"; else cChkDiagnostico = "checked";
                                            }
                                   }

                                    cDisable = "";
                                    if (!lCaptura) cDisable = "disabled";

                                    out.println("<tr>"+vEti.Texto("EEtiqueta","Tipo de Servicio:"));
                                    out.println("<td class=\"ETabla\"><input type=\"radio\" name=\"lPostDiag\" Value=\"0\" "+ cChkGeneral+" "+ cDisable+">General"+"&nbsp;"+"&nbsp;"+"&nbsp;"+
                                                "<input type=\"radio\" name=\"lPostDiag\" value=\"1\" "+cChkInterConsulta+" "+cDisable+">Interconsulta"+"&nbsp;"+"&nbsp;"+"&nbsp;"+
                                                "<input type=\"radio\" name=\"lPostDiag\" Value=\"2\" "+cChkDiagnostico+" "+cDisable+">Diagnóstico"+"&nbsp;"+"&nbsp;"+"&nbsp;"+
                                                "<input type=\"radio\" name=\"lPostDiag\" value=\"3\" "+cChkAdicional+" "+cDisable+">Adicional</td></tr>");

                                    // Diagnóstico y Recomendaciones - lReqDiag
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Diagnósticos y/o Recomendaciones:"));
                                    cDisable = "";
                                    cChecked = "";
                                    if (!lCaptura)
                                       cDisable = "disabled";
                                    if (bs.getFieldValue("lReqDiag","0").toString().compareToIgnoreCase("0")!=0)
                                       cChecked = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lReqDiag\" colspan=\"2\"  value=\"1\" " + cDisable + " " +  cChecked + ">");
                                    out.println("</td>");
                                    out.println("</tr>");


                                    // Permite que aparescan las ligas para subir y ver los archivos - lSubirArchivos
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Puede subir y ver archivos"));
                                    cDisable = "";
                                    cChecked = "";
                                    if (!lCaptura)
                                       cDisable = "disabled";
                                    if (bs.getFieldValue("lSUBIRARCHIVOS","0").toString().compareToIgnoreCase("0")!=0)
                                       cChecked = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lSUBIRARCHIVOS\" colspan=\"2\"  value=\"1\" " + cDisable + " " +  cChecked + ">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    // FIN     Permite que aparescan las ligas para subir y ver los archivos - lSubirArchivos

                                    // Activo - lActivo
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Activo:"));
                                    cDisable = "";
                                    cChecked = "";
                                    if (!lCaptura)
                                       cDisable = "disabled";
                                    if (bs.getFieldValue("lActivo","0").toString().compareToIgnoreCase("0")!=0)
                                       cChecked = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lActivo\" colspan=\"2\"  value=\"1\" " + cDisable + " " +  cChecked + ">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                 }
                                 else{
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
