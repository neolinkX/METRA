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
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*" %>

<html>
<%
  pg070801050CFG  clsConfig = new pg070801050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070801050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070801050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070801051.jsp";       // modificar
  String cDetalle    = "pg070801051.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveSeccion|cDscSeccion|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveSeccion|cDscSeccion|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  String cDeleteAction = "Borrar";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<head>
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
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCampoClave3" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td colspan="6" class="ETablaT">Secciones
            </td>
          </tr>
          <tr>
            <td class="ETablaTR">Almacén:</td>
            <td class="ETabla" colspan='5'>
              <select name="iCveAlmacen" size="1" OnChange="<%out.print("llenaSLT(1,document.forms[0].iCveAlmacen.value,'','',document.forms[0].iCveArea); document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();");%>">
              <%
              TDALMAlmacen dALMAlmacen = new TDALMAlmacen();
              TVALMAlmacen vALMAlmacen = new TVALMAlmacen();
              Vector vcALMAlmacen = new Vector();
              vcALMAlmacen = dALMAlmacen.FindByAll(""," order by iCveAlmacen ");
              if (vcALMAlmacen.size() > 0){
                out.print("<option value = 0>Seleccione...</option>");
                for (int i = 0; i < vcALMAlmacen.size(); i++){
                  vALMAlmacen = (TVALMAlmacen)vcALMAlmacen.get(i);
                  if (request.getParameter("iCveAlmacen")!=null && request.getParameter("iCveAlmacen").compareToIgnoreCase(new Integer(vALMAlmacen.getICveAlmacen()).toString())==0)
                    out.print("<option value = " + vALMAlmacen.getICveAlmacen() + " selected>" + vALMAlmacen.getCDscAlmacen() + "</option>");
                  else
                    out.print("<option value = " + vALMAlmacen.getICveAlmacen() + ">" + vALMAlmacen.getCDscAlmacen() + "</option>");
                  }
              }
              else{
                out.print("<option value = 0>Datos no disponibles...</option>");
              }
              %>
              </select>
            </td>
            </tr>
            <tr>
              <td class="ETablaTR">Área:</td>
              <td class="ETabla" colspan='5'>
              <select name="iCveArea" size="1" OnChange="<%out.print("document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();");%>">
              <%
              if(request.getParameter("iCveAlmacen") != null){
                TDALMArea dALMArea = new TDALMArea();
                TVALMArea vALMArea = new TVALMArea();
                Vector vcALMArea = new Vector();
                vcALMArea = dALMArea.FindByAll(" where ALMARea.iCveAlmacen = " + request.getParameter("iCveAlmacen")," order by ALMArea.cDscBreve ");
                if (vcALMArea.size() > 0){
                  out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcALMArea.size(); i++){
                    vALMArea = (TVALMArea)vcALMArea.get(i);
                    if (request.getParameter("iCveArea")!=null && request.getParameter("iCveArea").compareToIgnoreCase(new Integer(vALMArea.getICveArea()).toString())==0)
                      out.print("<option value = " + vALMArea.getICveArea() + " selected>" + vALMArea.getCDscBreve() + "</option>");
                    else
                      out.print("<option value = " + vALMArea.getICveArea() + ">" + vALMArea.getCDscBreve() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else
                out.println("<option value='0' selected>Datos no disponibles</option>");
              %>
              </select>
              </td>
              </tr>
              <%
                // modificar según listado
                if (bs != null){
                  out.print("<tr>");
                  out.print("<td class=\"ETablaT\">Clave</td>");
                  out.print("<td class=\"ETablaT\">Descripción</td>");
                  out.print("<td class=\"ETablaT\">Situación</td>");
                  if(clsConfig.getLPagina(cDetalle))
                    out.print("<td class=\"ETablaT\">Selección</td>");
                  out.print("</tr>");
                  bs.start();
                  while(bs.nextRow()){
                    out.print("<tr>");
                    out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveSeccion", "&nbsp;").toString()));
                    out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscSeccion", "&nbsp;").toString()));
                    if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                      out.print(vEti.Texto("ETabla", "INACTIVO"));
                    else
                      out.print(vEti.Texto("ETabla", "ACTIVO"));
                    if(clsConfig.getLPagina(cDetalle)){
                      out.print("<td class=\"ETabla\">");
                      out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrDetalle('" + bs.getFieldValue("iCveAlmacen") + "', '" + bs.getFieldValue("iCveArea") + "', '" + bs.getFieldValue("iCveSeccion") + "');", ""));
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
