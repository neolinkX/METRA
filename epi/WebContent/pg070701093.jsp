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
  pg070701093CFG  clsConfig = new pg070701093CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070701093.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070701093.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Clave|Descripción|Orden|";    // modificar
  String cCveOrdenar  = "iCveConfControl|cDscTpoResp|iOrden|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|Orden|";    // modificar
  String cCveFiltrar  = "iCveConfControl|cDscTpoResp|iOrden|";  // modificar
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

  TVMEDTpoResp vMEDTpoResp = new TVMEDTpoResp();
  TDMEDTpoResp dMEDTpoResp = new TDMEDTpoResp();
  Vector vcMEDTpoResp = new Vector();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg070701093.js)

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
       cClave  = ""+bs.getFieldValue("iCveConfControl", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdEtapaSolic" value="<%out.print(request.getParameter("iCveEtapaSolic"));%>">
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
                              <td colspan="6" class="ETablaT">Control por Etapa
                              </td>

            <tr>
              <td class="ETablaTR">Etapa:</td>
              <td class="ETabla" colspan='5'>
              <%

              TDVEHEtapaSolic dEQMEtapaSolic = new TDVEHEtapaSolic();
              TVVEHEtapaSolic vEQMEtapaSolic = new TVVEHEtapaSolic();
              Vector vcEQMEtapaSolic = new Vector();
              if(request.getParameter("hdBoton").compareTo("Guardar")==0 || request.getParameter("hdBoton").compareTo("GuardarA")==0 || request.getParameter("hdBoton").compareTo("Cancelar")==0){
                out.print("<select name=\"iCveEtapaSolic\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcEQMEtapaSolic = dEQMEtapaSolic.FindByAll("", " order by iCveEtapaSolic ");
                if (vcEQMEtapaSolic.size() > 0){
                  for (int i = 0; i < vcEQMEtapaSolic.size(); i++){
                    vEQMEtapaSolic = (TVVEHEtapaSolic)vcEQMEtapaSolic.get(i);
                    if (request.getParameter("hdEtapaSolic")!=null && request.getParameter("hdEtapaSolic").compareToIgnoreCase(new Integer(vEQMEtapaSolic.getICveEtapaSolic()).toString())==0)
                      out.print("<option value = " + vEQMEtapaSolic.getICveEtapaSolic() + " selected>" + vEQMEtapaSolic.getCDscBreve() + "</option>");
                    else
                      out.print("<option value = " + vEQMEtapaSolic.getICveEtapaSolic() + ">" + vEQMEtapaSolic.getCDscBreve() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else{
                if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0)
                  out.print("<select name=\"iCveEtapaSolic\" disabled>");
                else
                  out.print("<select name=\"iCveEtapaSolic\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcEQMEtapaSolic = dEQMEtapaSolic.FindByAll("", " order by iCveEtapaSolic ");
                if (vcEQMEtapaSolic.size() > 0){
                  if(request.getParameter("iCveEtapaSolic")==null || request.getParameter("iCveEtapaSolic").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveEtapaSolic")!=null && Integer.parseInt(request.getParameter("iCveEtapaSolic"))<1 )
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcEQMEtapaSolic.size(); i++){
                    vEQMEtapaSolic = (TVVEHEtapaSolic)vcEQMEtapaSolic.get(i);
                    if (request.getParameter("iCveEtapaSolic")!=null && request.getParameter("iCveEtapaSolic").compareToIgnoreCase(new Integer(vEQMEtapaSolic.getICveEtapaSolic()).toString())==0)
                      out.print("<option value = " + vEQMEtapaSolic.getICveEtapaSolic() + " selected>" + vEQMEtapaSolic.getCDscBreve() + "</option>");
                    else
                      out.print("<option value = " + vEQMEtapaSolic.getICveEtapaSolic() + ">" + vEQMEtapaSolic.getCDscBreve() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              out.print("</select>");
              out.print("</td>");
              out.print("</tr>");

                               if (lNuevo){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.Texto("EEtiqueta", "Clave:"));
                                 out.println("<td>");
                                 out.print("<input type='text' size='10' disabled>");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 50, 25, "cDscTpoResp2", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Descripción Breve:", "ECampo", "text", 50, 50, "cDscBreve", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Etiqueta:", "ECampo", "text", 50, 20, "cEtiqueta", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                   out.print("<td class='EEtiqueta'>Tipo Resp.:</td>");
                                   out.print("<td>");
                                   out.print("<select name='iCveTpoResp'>");
                                   vcMEDTpoResp = dMEDTpoResp.FindByAll();
                                   if (vcMEDTpoResp.size() > 0){
                                     out.print("<option value = 0>Seleccione...</option>");
                                     for (int i = 0; i < vcMEDTpoResp.size(); i++){
                                       vMEDTpoResp = (TVMEDTpoResp)vcMEDTpoResp.get(i);
                                         out.print("<option value = " + vMEDTpoResp.getICveTpoResp() + ">" + vMEDTpoResp.getCDscTpoResp() + "</option>");
                                     }
                                   }
                                   else{
                                     out.print("<option value = 0>Datos no disponibles...</option>");
                                   }
                                   out.print("</select>");
                                   out.print("</td>");
                                 out.println("</tr>");

                                 out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Obligatorio:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="lObligatorio" CHECKED>
                                     </td>
                                     <%
                                 out.println("</tr>");

                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Orden:", "ECampo", "text", 5, 2, "iOrden", "", 3, "", "fMayus(this);", true, true, lCaptura));
                                 out.println("</tr>");

                                 out.println("<tr>");
                                     out.print(vEti.Texto("EEtiqueta", "Situación:"));
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
                                   out.print(vEti.EtiCampo("EEtiqueta", "Clave:", "ECampo", "text", 4, 4, "iCveConfControl", ""+bs.getFieldValue("iCveConfControl", "&nbsp;"), 3, "", "", true, true, false));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción:", "ECampo", "text", 50, 25, "cDscTpoResp2", ""+bs.getFieldValue("cDscTpoResp2", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Descripción Breve:", "ECampo", "text", 50, 50, "cDscBreve", ""+bs.getFieldValue("cDscBreve", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Etiqueta:", "ECampo", "text", 50, 20, "cEtiqueta", ""+bs.getFieldValue("cEtiqueta", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");

                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0){
                                     out.print("<tr>");
                                       out.print("<td class='EEtiqueta'>Tipo Resp.:</td>");
                                       out.print("<td>");
                                       out.print("<select name=\"iCveTpoResp\" size=\"1\" OnChange=\"llenaSLT(1,document.forms[0].iCveTpoResp.value,'','',document.forms[0].iCveEstado);\">");
                                       vcMEDTpoResp = dMEDTpoResp.FindByAll();
                                       if (vcMEDTpoResp.size() > 0){
                                         out.print("<option value = 0>Seleccione...</option>");
                                         for (int i = 0; i < vcMEDTpoResp.size(); i++){
                                           vMEDTpoResp = (TVMEDTpoResp)vcMEDTpoResp.get(i);
                                             if(Integer.parseInt(bs.getFieldValue("iCveTpoResp").toString()) == Integer.parseInt(vMEDTpoResp.getICveTpoResp()+""))
                                               out.print("<option selected value = " + vMEDTpoResp.getICveTpoResp() + ">" + vMEDTpoResp.getCDscTpoResp() + "</option>");
                                             else
                                               out.print("<option value = " + vMEDTpoResp.getICveTpoResp() + ">" + vMEDTpoResp.getCDscTpoResp() + "</option>");
                                         }
                                       }
                                       else{
                                         out.print("<option value = 0>Datos no disponibles...</option>");
                                       }
                                       out.print("</select>");
                                       out.print("</td>");
                                     out.print("</tr>");
                                   }
                                   else{
                                     out.print("<tr>");
                                       vcMEDTpoResp = dMEDTpoResp.FindByAll(" where iCveTpoResp = " + bs.getFieldValue("iCveTpoResp", "&nbsp;"), "");
                                       if (vcMEDTpoResp.size() > 0)
                                         for (int i = 0; i < vcMEDTpoResp.size(); i++)
                                           vMEDTpoResp = (TVMEDTpoResp)vcMEDTpoResp.get(i);
                                       out.print("<td class='EEtiqueta'>Tipo Resp.:</td>");
                                       out.print("<td class='ECampo'>" + vMEDTpoResp.getCDscTpoResp() + "</td>");
                                     out.print("</tr>");
                                   }

                                   if(request.getParameter("hdBoton").toString().compareTo("Modificar")!=0){
                                     if(Integer.parseInt(""+bs.getFieldValue("lObligatorio"))==0)
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Obligatorio:", "ECampo", 10, 10, "lActivo", "NO", 3, "", "", true, true, false));
                                     else
                                       out.print(vEti.EtiAreaTexto("EEtiqueta", "Obligatorio:", "ECampo", 10, 10, "lActivo", "SI", 3, "", "", true, true, false));
                                   }
                                   else{
                                     out.print(vEti.Texto("EEtiqueta", "Obligatorio:"));
                                     %>
                                     <td>
                                       <input type="checkbox" name="lObligatorio"<%
                                         if(bs.getFieldValue("lObligatorio").toString().compareTo("0")!=0)
                                           out.print(" CHECKED");
                                       %>>
                                     </td>
                                     <%
                                     }
                                     out.println("</tr>");

                                   out.println("<tr>");
                                   out.print(vEti.EtiCampo("EEtiqueta", "Orden:", "ECampo", "text", 5, 2, "iOrden", ""+bs.getFieldValue("iOrden", "&nbsp;"), 3, "", "fMayus(this);", true, true, lCaptura));
                                   out.println("</tr>");

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