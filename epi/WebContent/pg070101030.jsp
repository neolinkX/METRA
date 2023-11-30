<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*" %>

<html>
<%
  pg070101030CFG  clsConfig = new pg070101030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cDetalle    = "pg070101031.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Identificador|Descripción Breve|";    // modificar
  String cCveOrdenar  = "cIdentificador|cDscBreve|";  // modificar
  String cDscFiltrar  = "Identificador|Descripción Breve|";    // modificar
  String cCveFiltrar  = "cIdentificador|cDscBreve|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
       cClave = ""+bs.getFieldValue("iCveRecomendacion", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td align="center" width="100%">
     <% if (bs != null){  %>
        <%=vEti.clsAnclaTexto("EAncla","Generar Archivo: Recomendaciones de uso Frecuente.","javascript:fGenArchivo();", "Generar Archivo JS","")%>
     <% }  %>
     <%
        if(request.getParameter("hdBoton").compareTo("GenJS") == 0 ){
           TDMEDRecomendacion dMedReco = new TDMEDRecomendacion();
           if(dMedReco.genJS()){
             vErrores.acumulaError("",0,"El archivo JS se ha generado exitosamente!");
           }else{
             vErrores.acumulaError("",0,"El archivo JS no ha sido generado. Consulte a su área de Sistemas!");
           }
        }
     %>
  </td></tr><tr>
      <td valign="top">

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="6" class="ETablaT">Recomendaciones
                              </td>
                            </tr>
                            <tr>
            <tr>
              <td class="ETablaTR">Especialidad:</td>
              <td class="ETablaTL" colspan='5'>
              <%
              if(request.getParameter("hdBoton").compareTo("Modificar")==0 || request.getParameter("hdBoton").compareTo("Nuevo")==0)
                out.print("<select name=\"iCveEspecialidad\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\" disabled>");
              else
                out.print("<select name=\"iCveEspecialidad\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");

                TDMEDEspecialidad dMEDEspecialidad = new TDMEDEspecialidad();
                TVMEDEspecialidad vMEDEspecialidad = new TVMEDEspecialidad();
                Vector vcMEDEspecialidad = new Vector();
                vcMEDEspecialidad = dMEDEspecialidad.FindByAll("", " order by iCveEspecialidad ");
                if (vcMEDEspecialidad.size() > 0){
                  out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcMEDEspecialidad.size(); i++){
                    vMEDEspecialidad = (TVMEDEspecialidad)vcMEDEspecialidad.get(i);
                    if (request.getParameter("hdCampoClave1")!=null && request.getParameter("hdCampoClave1").compareToIgnoreCase(new Integer(vMEDEspecialidad.getICveEspecialidad()).toString())==0)
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + " selected>" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                    else if(request.getParameter("iCveEspecialidad")!=null && request.getParameter("iCveEspecialidad").compareToIgnoreCase(new Integer(vMEDEspecialidad.getICveEspecialidad()).toString())==0)
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + " selected>" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                    else if (request.getParameter("hdEspecialidad")!=null && request.getParameter("hdEspecialidad").compareToIgnoreCase(new Integer(vMEDEspecialidad.getICveEspecialidad()).toString())==0)
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + " selected>" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                    else
                      out.print("<option value = " + vMEDEspecialidad.getICveEspecialidad() + ">" + vMEDEspecialidad.getCDscEspecialidad() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              %>
              </select>
              </td>
              </tr>
                            </tr>
                            <%
                              // modificar según listado
                               boolean lDetalle = clsConfig.getLPagina(cDetalle);

                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Identificador</td>");
                                   out.print("<td class=\"ETablaT\">Descripción Breve</td>");
                                   out.print("<td class=\"ETablaT\" colspan=\"4\">Activa</td>");
                                   out.print("</tr>");
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cIdentificador", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscBreve", "&nbsp;").toString()));
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.Texto("ETablaC", "NO"));
                                     else
                                       out.print(vEti.Texto("ETablaC", "SI"));

                                     if(lDetalle) {
                                       out.print("<td class=\"ETablaC\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveEspecialidad") + "', '" + bs.getFieldValue("iCveRecomendacion") + "');", ""));
                                       out.print("</td>");
                                     }
                                     out.print("</tr>");
                                   }
                               }
                               else{
                                   out.print("<td class='ECampo' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
                               }

                            %>
                          </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
