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
  pg071004031CFG  clsConfig = new pg071004031CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg071004031.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071004031.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Descripci�n|";    // modificar
  String cCveOrdenar  = "iCveGrupo|cDscGrupo|";  // modificar
  String cDscFiltrar  = "Clave|Descripci�n|";    // modificar
  String cCveFiltrar  = "iCveGrupo|cDscGrupo|";  // modificar
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
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deber�n ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensi�n js (vg. pg071004031.js)

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
       cClave  = ""+bs.getFieldValue("iCveGrupo", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="iNoLetras" value="">
  <input type="hidden" name="hdMdoTrans" value="<%out.print(request.getParameter("iCveMdoTrans"));%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                              <% TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                               %>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <td colspan="6" class="ETablaT">Grupo
                              </td>

            <tr>
              <td class="ETablaTR">Modo de Transporte:</td>
              <td class="ETabla" colspan='5'>
              <%

              TDGRLMdoTrans dGRLMdoTrans = new TDGRLMdoTrans();
              TVGRLMdoTrans vGRLMdoTrans = new TVGRLMdoTrans();
              Vector vcGRLMdoTrans = new Vector();
              if(request.getParameter("hdBoton").compareTo("Guardar")==0 || request.getParameter("hdBoton").compareTo("GuardarA")==0 || request.getParameter("hdBoton").compareTo("Cancelar")==0){
                out.print("<select name=\"iCveMdoTrans\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLMdoTrans = dGRLMdoTrans.findByAll("");
                if (vcGRLMdoTrans.size() > 0){
                  for (int i = 0; i < vcGRLMdoTrans.size(); i++){
                    vGRLMdoTrans = (TVGRLMdoTrans)vcGRLMdoTrans.get(i);
                    if (request.getParameter("hdMdoTrans")!=null && request.getParameter("hdMdoTrans").compareToIgnoreCase(new Integer(vGRLMdoTrans.getICveMdoTrans()).toString())==0)
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + " selected>" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                    else
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + ">" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else{
                if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0)
                  out.print("<select name=\"iCveMdoTrans\" disabled>");
                else
                  out.print("<select name=\"iCveMdoTrans\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLMdoTrans = dGRLMdoTrans.findByAll("");
                if (vcGRLMdoTrans.size() > 0){
                  if(request.getParameter("iCveMdoTrans")==null || request.getParameter("iCveMdoTrans").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveMdoTrans")!=null && Integer.parseInt(request.getParameter("iCveMdoTrans"))<1 )
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcGRLMdoTrans.size(); i++){
                    vGRLMdoTrans = (TVGRLMdoTrans)vcGRLMdoTrans.get(i);
                    if (request.getParameter("iCveMdoTrans")!=null && request.getParameter("iCveMdoTrans").compareToIgnoreCase(new Integer(vGRLMdoTrans.getICveMdoTrans()).toString())==0)
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + " selected>" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                    else
                      out.print("<option value = " + vGRLMdoTrans.getICveMdoTrans() + ">" + vGRLMdoTrans.getCDscMdoTrans() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              out.print("</select>");
              out.print("</td>");
              out.print("</tr>");

                               if (lNuevo){ // Modificar de acuerdo al cat�logo espec�fico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripci�n:", "ECampo", "text", 50, 100, "cDscGrupo", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Situaci�n:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="lActivo" CHECKED>
                                     </td>
                                     <%
                                 out.println("</tr>");
                               }
                               else{
                                 if (bs != null){
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveGrupo", ""+bs.getFieldValue("iCveGrupo", "&nbsp;"), 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripci�n:", "ECampo", "text", 50, 100, "cDscGrupo", ""+bs.getFieldValue("cDscGrupo", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situaci�n:", "ECampo", 10, 10, "lActivo", "INACTIVO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Situaci�n:", "ECampo", 10, 10, "lActivo", "ACTIVO", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Situaci�n:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="lActivo"<%
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
                                   out.print("<td class='ECampo' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
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