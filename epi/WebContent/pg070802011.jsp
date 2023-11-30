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
<%@ page import="java.util.*"%>

<html>
<%
  pg070802011CFG  clsConfig = new pg070802011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070802011.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070802011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cPosicion  = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No.Art.|Descripción|Clave|";    // modificar
  String cCveOrdenar  = "ALMArticulo.iCveArticulo|ALMArticulo.cDscBreve|ALMArticulo.cCveArticulo|";  // modificar
  String cDscFiltrar  = "No.Art.|Descripción|Clave|";    // modificar
  String cCveFiltrar  = "ALMArticulo.iCveArticulo|ALMArticulo.cDscBreve|ALMArticulo.cCveArticulo|";  // modificar
  String cTipoFiltrar = "7|8|8|";                // modificar
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
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070802011.js)
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
       cClave  = ""+bs.getFieldValue("iCveArticulo", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdTpoArticulo" value="<%out.print(request.getParameter("iCveTpoArticulo"));%>">
  <input type="hidden" name="hdFamilia" value="<%out.print(request.getParameter("iCveFamilia"));%>">
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
                              <td colspan="15" class="ETablaT">Artículo
                              </td>
                            </tr>
                            <% if (lNuevo){ // Modificar de acuerdo al catálogo específico
// N U E V O
                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Número:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print("<input type='text' name='iCveArticulo' size='10' disabled>");
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Clave:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   out.print("<input type='text' name='cCveArticulo' size='10' maxlength='20' OnBlur='fMayus(this);'>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Tipo de Artículo:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
                                   TVALMTpoArticulo vTpoArticulo = new TVALMTpoArticulo();
                                   vTpoArticulo.setICveTpoArticulo(0);
                                   vTpoArticulo.setCDscBreve("Seleccione...");
                                   Vector vTpoArticulos = dALMTpoArticulo.FindByAll("", " Order By iCveTpoArticulo ");
                                   vTpoArticulos.insertElementAt(vTpoArticulo,0);
                                   out.print(vEti.SelectOneRowSinTD("iCveTpoArticulo","llenaSLT(1,document.forms[0].iCveTpoArticulo.value,'','',document.forms[0].iCveFamilia);", vTpoArticulos , "iCveTpoArticulo", "cDscBreve", request, "0"));
                                   out.print("</td>");
                                   %>
                                   <td class="EEtiqueta" colspan='3'>Familia:</td>
                                   <td class="ECampo" colspan='3'>
                                   <%
                                    out.println("<select name=\"iCveFamilia\" size=\"1\" >");
                                    if((request.getParameter("iCveTpoArticulo")!=null && Integer.parseInt(request.getParameter("iCveTpoArticulo").toString())<1) ||request.getParameter("iCveTpoArticulo")==null || request.getParameter("hdBoton").compareTo("Primero")==0)
                                      out.println("<option value='0' selected>Datos no disponibles</option>");
                                    out.println("</select>");
                                  out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td colspan='3' class=\"EEtiqueta\">Descripción:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='80' rows='3' name='cDscArticulo' OnKeyPress='fChgArea(this);' OnChange='fChgArea(this);' OnBlur='fMayus(this);'></textarea></td>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Descripción Breve:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print("<input type='text' name='cDscBreve' size='50' maxlength='100' OnBlur='fMayus(this);'>");
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Lote:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                     %><input type="checkbox" name="chklLote"><%
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Unidad Almacenaje:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   TDALMUnidad dALMUnidad = new TDALMUnidad();
                                   out.print(vEti.SelectOneRowSinTD("iCveUniAlm","", dALMUnidad.FindByAll("", " Order By iCveUnidad ") , "iCveUnidad", "cDscUnidad", request, "0"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='2'>Unidad Suministro:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   out.print(vEti.SelectOneRowSinTD("iCveUniSum","", dALMUnidad.FindByAll("", " Order By iCveUnidad ") , "iCveUnidad", "cDscUnidad", request, "0"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Máximos y Mínimos:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                     %>
                                       <input type="checkbox" name="chklMaxMin">
                                     <%
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td  colspan='3' class=\"EEtiqueta\">Observación:<br><input type=\"text\" size=\"5\" name=\"iNoLetras2\" value=\"\" disabled></td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='80' rows='5' name='cObservacion' OnKeyPress='fChgArea2(this);' OnChange='fChgArea2(this);' OnBlur='fMayus(this);'></textarea></td>");
                                 out.print("</tr>");
                           }
                           else{
                             if (bs != null){
// M O D I F I C A R
                               if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Número:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print("<input type='text' name='iCveArticulo' size='10' disabled value='");
                                   out.print(""+bs.getFieldValue("iCveArticulo"));
                                   out.print("'></td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Clave:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   out.print("<input type='text' name='cCveArticulo' size='10' maxlength='20' OnBlur='fMayus(this);' value='");
                                   out.print(""+bs.getFieldValue("cCveArticulo"));
                                   out.print("'></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Tipo de Artículo:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
                                   out.print(vEti.SelectOneRowSinTD("iCveTpoArticulo","llenaSLT(1,document.forms[0].iCveTpoArticulo.value,'','',document.forms[0].iCveFamilia);", dALMTpoArticulo.FindByAll("", " Order By iCveTpoArticulo ") , "iCveTpoArticulo", "cDscBreve", request, ""+bs.getFieldValue("iCveTpoArticulo")));
                                   out.print("</td>");
                                   %>
                                   <td class="EEtiqueta" colspan='3'>Familia:</td>
                                   <td class="ECampo" colspan='3'>
                                   <select name="iCveFamilia" size="1">
                                   <%
                                   if(bs.getFieldValue("iCveTpoArticulo")!=null){
                                     TDALMFamilia dALMFamilia = new TDALMFamilia();
                                     TVALMFamilia vALMFamilia = new TVALMFamilia();
                                     Vector vcALMFamilia = new Vector();
                                     vcALMFamilia = dALMFamilia.FindByCustomWhere(" where ALMFamilia.iCveTpoArticulo = " + bs.getFieldValue("iCveTpoArticulo") + " order by  ALMFamilia.cDscBreve ");
                                     if (vcALMFamilia.size() > 0){
                                       out.print("<option value = 0>Seleccione...</option>");
                                       for (int i = 0; i < vcALMFamilia.size(); i++){
                                         vALMFamilia = (TVALMFamilia)vcALMFamilia.get(i);
                                         String cCve = ""+vALMFamilia.getiCveFamilia();
                                         if(bs.getFieldValue("iCveFamilia").toString().compareTo(cCve)==0)
                                           out.print("<option value = " + bs.getFieldValue("iCveFamilia") + " selected>" + bs.getFieldValue("cDscFamilia") + "</option>");
                                         else
                                           out.print("<option value = " + vALMFamilia.getiCveFamilia() + ">" + vALMFamilia.getcDscFamilia() + "</option>");
                                       }
                                     }
                                     else{
                                       out.print("<option value = 0>Datos no disponibles...</option>");
                                     }
                                   }
                                   %>
                                   </select>
                                   </td>
                                   <%
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td colspan='3' class=\"EEtiqueta\">Descripción:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='80' rows='3' name='cDscArticulo' OnKeyPress='fChgArea(this);' OnChange='fChgArea(this);' OnBlur='fMayus(this);'>");
                                   out.print(""+bs.getFieldValue("cDscArticulo"));
                                   out.print("</textarea></td></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Descripción Breve:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print("<input type='text' name='cDscBreve' size='50' maxlength='100' OnBlur='fMayus(this);' value='");
                                   out.print(""+bs.getFieldValue("cDscBreve"));
                                   out.print("'></td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Lote:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   if(bs.getFieldValue("lLote").toString().compareTo("0")==0)
                                     out.print("<input type=\"checkbox\" name=\"chklLote\">");
                                   else
                                     out.print("<input type=\"checkbox\" name=\"chklLote\" checked>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Unidad Almacenaje:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   TDALMUnidad dALMUnidad = new TDALMUnidad();
                                   out.print(vEti.SelectOneRowSinTD("iCveUniAlm","", dALMUnidad.FindByAll("", " Order By iCveUnidad ") , "iCveUnidad", "cDscUnidad", request, ""+bs.getFieldValue("iCveUniAlm")));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='2'>Unidad Suministro:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   out.print(vEti.SelectOneRowSinTD("iCveUniSum","", dALMUnidad.FindByAll("", " Order By iCveUnidad ") , "iCveUnidad", "cDscUnidad", request, ""+bs.getFieldValue("iCveUniSum")));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Máximos y Mínimos:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   if(bs.getFieldValue("lMaxMin").toString().compareTo("0")==0)
                                     out.print("<input type=\"checkbox\" name=\"chklMaxMin\">");
                                   else
                                     out.print("<input type=\"checkbox\" name=\"chklMaxMin\" checked>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td colspan='3' class=\"EEtiqueta\">Observación:<br><input type=\"text\" size=\"5\" name=\"iNoLetras2\" value=\"\" disabled></td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='80' rows='5' name='cObservacion' OnKeyPress='fChgArea2(this);' OnChange='fChgArea2(this);' OnBlur='fMayus(this);'>");
                                   out.print(""+bs.getFieldValue("cObservacion"));
                                 out.print("</textarea></td></tr>");
                               }
// V E R   D A T O S
                               else{
                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Número:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print(""+bs.getFieldValue("iCveArticulo"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Clave:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   out.print(""+bs.getFieldValue("cCveArticulo"));
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Tipo de Artículo:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print(""+bs.getFieldValue("cDscTpoArticulo"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Familia:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   out.print(""+bs.getFieldValue("cDscFamilia"));
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td colspan='3' class=\"EEtiqueta\">Descripción:</td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='80' rows='3' name='cDscArticulo' readOnly>");
                                   out.print(""+bs.getFieldValue("cDscArticulo"));
                                   out.print("</textarea></td></td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Descripción Breve:</td>");
                                   out.print("<td class='ECampo' colspan='6'>");
                                   out.print(""+bs.getFieldValue("cDscBreve"));
                                   out.print("<td class='EEtiqueta' colspan='3'>Lote:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   if(bs.getFieldValue("lLote").toString().compareTo("0")==0)
                                     out.print("<input type=\"checkbox\" name=\"chklLote\" disabled>");
                                   else
                                     out.print("<input type=\"checkbox\" name=\"chklLote\" checked disabled>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Unidad Almacenaje:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   out.print(""+bs.getFieldValue("cDscUniAlm"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='2'>Unidad Suministro:</td>");
                                   out.print("<td class='ECampo' colspan='2'>");
                                   out.print(""+bs.getFieldValue("cDscUniSum"));
                                   out.print("</td>");
                                   out.print("<td class='EEtiqueta' colspan='3'>Máximos y Mínimos:</td>");
                                   out.print("<td class='ECampo' colspan='3'>");
                                   if(bs.getFieldValue("lMaxMin").toString().compareTo("0")==0)
                                     out.print("<input type=\"checkbox\" name=\"chklMaxMin\" disabled>");
                                   else
                                     out.print("<input type=\"checkbox\" name=\"chklMaxMin\" checked disabled>");
                                   out.print("</td>");
                                 out.print("</tr>");

                                 out.print("<tr>");
                                   out.println("<td colspan='3' class=\"EEtiqueta\">Observación:</td>");
                                   out.print("<td class='ECampo' colspan='12'>");
                                   out.print("<textarea cols='80' rows='5' name='cObservacion' readOnly>");
                                   out.print(""+bs.getFieldValue("cObservacion"));
                                 out.print("</textarea></td></tr>");
                               }
                             }
                             else{
                               out.print(vEti.Texto("ECampo", "No existen datos coincidentes con el filtro proporcionado."));
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
