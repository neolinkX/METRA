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
<%@ page import="java.util.*"%>
 
<html>
<%
  pg071004040CFG  clsConfig = new pg071004040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071004040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071004040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg071004041.jsp";       // modificar
  String cDetalle    = "pg071004041.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripci�n Breve|";    // modificar
  String cCveOrdenar  = "iCvePuesto|cDscBreve|";  // modificar
  String cDscFiltrar  = "Clave|Descripci�n Breve|";    // modificar
  String cCveFiltrar  = "iCvePuesto|cDscBreve|";  // modificar
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
  String cDeleteAction = "BorrarB";
  String cClave2    = "";
  String cPosicion = "";

  TDGRLMdoTrans dGRLMdoTrans = new TDGRLMdoTrans();
  TVGRLMdoTrans vGRLMdoTrans = new TVGRLMdoTrans();
  Vector vcGRLMdoTrans;

  TDGRLCategoria dGRLCategoria = new TDGRLCategoria();
  TVGRLCategoria vGRLCategoria = new TVGRLCategoria();
  Vector vcGRLCategoria;
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg071004040.js"></SCRIPT>-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
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
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
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
  <input type="hidden" name="iCvePuesto" value="">
  <input type="hidden" name="hdMdoTrans" value="<%out.print(request.getParameter("iCveMdoTrans"));%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="7" class="ETablaT">Puestos
                              </td>
                            </tr>
                          <tr>
                                 <%
                                 vcGRLMdoTrans = dGRLMdoTrans.findByAll("", "");
                                 if(request.getParameter("iCveMdoTrans")==null || request.getParameter("iCveMdoTrans").compareTo("")==0) {
                                   vGRLMdoTrans.setCDscMdoTrans("Seleccione...");
                                   vGRLMdoTrans.setICveMdoTrans(0);
                                   vcGRLMdoTrans.add(vGRLMdoTrans);
                                 }
                                 else if(request.getParameter("iCveMdoTrans")!=null && Integer.parseInt(request.getParameter("iCveMdoTrans"))<1 ){
                                   vGRLMdoTrans.setCDscMdoTrans("Seleccione...");
                                   vGRLMdoTrans.setICveMdoTrans(0);
                                   vcGRLMdoTrans.add(vGRLMdoTrans);
                                 }

                                 out.print(vEti.Texto("EEtiqueta", "Modo de Transporte:"));
                                 out.println("<td>");
                                 out.println(vEti.SelectOneRowSinTD("iCveMdoTrans","document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();", vcGRLMdoTrans, "iCveMdoTrans", "cDscMdoTrans", request, "0"));
                                 out.println("</td>");


                out.print("<td class='EEtiqueta'>Categor�a:</td>");
                out.print("<td class='ETabla' colspan='3'>");
                out.println("<select name=\"iCveCategoria\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                if(request.getParameter("iCveMdoTrans")!= null){
                  dGRLCategoria = new TDGRLCategoria();
                  vGRLCategoria = new TVGRLCategoria();
                  vcGRLCategoria = new Vector();
                  vcGRLCategoria = dGRLCategoria.FindByAll(request.getParameter("iCveMdoTrans"));
                  if (vcGRLCategoria.size() > 0){
                    out.print("<option value = 0>Seleccione...</option>");
                    for (int i = 0; i < vcGRLCategoria.size(); i++){
                      vGRLCategoria = (TVGRLCategoria)vcGRLCategoria.get(i);
                      if (request.getParameter("iCveCategoria")!=null && request.getParameter("iCveCategoria").compareToIgnoreCase(new Integer(vGRLCategoria.getICveCategoria()).toString())==0 && Integer.parseInt(request.getParameter("iCveCategoria"))>0)
                        out.print("<option value = " + vGRLCategoria.getICveCategoria() + " selected>" + vGRLCategoria.getCDscCategoria() + "</option>");
                      else
                      out.print("<option value = " + vGRLCategoria.getICveCategoria() + ">" + vGRLCategoria.getCDscCategoria() + "</option>");
                    }
                  }
                  else{
                    out.print("<option value = 0>Datos no disponibles</option>");
                  }
                }
                else if((request.getParameter("iCveMdoTrans")!=null && Integer.parseInt(request.getParameter("iCveMdoTrans").toString())<1) || request.getParameter("iCveMdoTrans")==null)
                  out.println("<option value='0' selected>Datos no disponibles</option>");
                out.print("</select></td>");


                                 %>
                          </tr>
                            <%
                              // modificar seg�n listado
                               if (bs != null){
                                   boolean lDetalle = clsConfig.getLPagina(cDetalle);
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Clave</td>");
                                   out.print("<td class=\"ETablaT\">Descripci�n</td>");
                                   out.print("<td class=\"ETablaT\">Situaci�n</td>");
                                   if(lDetalle)
                                     out.print("<td class=\"ETablaT\">Selecci�n</td>");
                                   out.print("<td class=\"ETablaT\">Regla de Vigencia</td>");
                                   out.print("</tr>");
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCvePuesto", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscPuesto", "&nbsp;").toString()));
                                     if(Integer.parseInt(""+bs.getFieldValue("lActivo"))==0)
                                       out.print(vEti.Texto("ETabla", "INACTIVO"));
                                     else
                                       out.print(vEti.Texto("ETabla", "ACTIVO"));
                                     if(lDetalle){
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrDetalle('" + bs.getFieldValue("iCveModoTrans") + "', '" + bs.getFieldValue("iCveCategoria") + "', '" + bs.getFieldValue("iCvePuesto") + "');", ""));
                                       out.print("</td>");
                                     }
                                       out.print("<td class=\"ECampo\">");
                                       TDGRLCONVIGPUE dGRLCONVIGPUE = new TDGRLCONVIGPUE();
                                       int existe = 0;
                                       try{
                                            String Sentenia =    "Select count (icvemdotrans)	"
                                                               + "from grlconvigpue	"
                                                               + "where 	"
                                                               + "icvemdotrans = "+bs.getFieldValue("iCveModoTrans")+" and	"
                                                               + "icvecategoria = "+bs.getFieldValue("iCveCategoria")+" and	"
                                                               + "icvepuesto = "+bs.getFieldValue("iCvePuesto")+"";
                                           existe = dGRLCONVIGPUE.RegresaInt(Sentenia);
                                       }catch(Exception Ex){
                                           existe = 0;
                                       }
                                       if(existe > 0)
                                            out.print(vEti.clsAnclaTexto("EAncla","Vigencia","JavaScript:fIrVigencia('" + bs.getFieldValue("iCveModoTrans") + "', '" + bs.getFieldValue("iCveCategoria") + "', '" + bs.getFieldValue("iCvePuesto") + "');", ""));
                                       else
                                           out.print(vEti.clsAnclaTexto("EAncla"," - "," ", " "));
                                       out.print("</td>");
                                     
                                     out.print("</tr>");
                                   }
                               }
                               else{
                                   out.print("<td class='ETablaC' colspan='6'>No existen datos coincidentes con el filtro proporcionado.</td>");
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
