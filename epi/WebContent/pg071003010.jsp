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
  pg071003010CFG  clsConfig = new pg071003010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cDetalle    = "pg071003011.jsp";       // modificar
  String cModulos    = "pg071003030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Desripción|";    // modificar
  String cCveOrdenar  = "iCveUniMed|cDscUniMed|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveUniMed|cDscUniMed|";  // modificar
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
  String cClave    = "";
  String cPosicion = "";

  TDPais dPais = new TDPais();
  TVPais vPais = new TVPais();
  Vector vcPais = new Vector();

  TDEntidadFed dEntidadFed = new TDEntidadFed();
  TVEntidadFed vEntidadFed = new TVEntidadFed();
  Vector vcEntidadFed = new Vector();

  TDMunicipio dMunicipio = new TDMunicipio();
  TVMunicipio vMunicipio = new TVMunicipio();
  Vector vcMunicipio = new Vector();

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
       cClave  = ""+bs.getFieldValue("iCveUniMed", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="iCveUniMed" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Unidades Médicas
                              </td>
                            </tr>
                             <% // modificar según listado

                              boolean lDetalle = clsConfig.getLPagina(cDetalle);
                              boolean lModulos = clsConfig.getLPagina(cModulos);

                              out.print("<tr>");
                              out.print("<td class='ETablaT'>Clave</td>");
                              out.print("<td class='ETablaT'>Nombre</td>");
                              out.print("<td class='ETablaT'>Dirección</td>");
                              out.print("<td class='ETablaT'>Teléfono</td>");
                              out.print("<td class='ETablaT'>Correo Electrónico</td>");
                              out.print("<td class='ETablaT'>Privada</td>");

                              String cColspan = "";
                              if(lDetalle && lModulos)
                                cColspan = " colspan='2'";
                              else
                                cColspan = "";
                              if(lDetalle || lModulos)
                                out.print("<td class=\"ETablaT\"" + cColspan + ">Selección</td>");

                              out.print("</tr>");



                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveUniMed", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));

                                       vcPais = dPais.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nsbp;"));
                                       if (vcPais.size() > 0)
                                         for (int i = 0; i < vcPais.size(); i++)
                                           vPais = (TVPais)vcPais.get(i);
                                       vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nsbp;") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado", "&nsbp;"));
                                       if (vcEntidadFed.size() > 0)
                                         for (int i = 0; i < vcEntidadFed.size(); i++)
                                           vEntidadFed = (TVEntidadFed)vcEntidadFed.get(i);
                                       vcMunicipio = dMunicipio.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePais", "&nsbp;") + " and iCveEntidadFed = " + bs.getFieldValue("iCveEstado", "&nsbp;") + " and iCveMunicipio = " + bs.getFieldValue("iCveMunicipio", "&nsbp;"));
                                       if (vcMunicipio.size() > 0)
                                         for (int i = 0; i < vcMunicipio.size(); i++)
                                           vMunicipio = (TVMunicipio)vcMunicipio.get(i);

                                     out.print("<td><textarea cols='40' rows='3' readOnly>" + bs.getFieldValue("cCalle", "&nbsp;") + ", " + bs.getFieldValue("cColonia", "&nbsp;") + ", " + bs.getFieldValue("cCiudad", "&nbsp;") + ", " + bs.getFieldValue("iCP", "&nbsp;") + ", " + vMunicipio.getCNombre() + ", " + vEntidadFed.getCNombre() + ", " + vPais.getCNombre() + "</textarea></td>");
                                     if(bs.getFieldValue("cTel", "&nbsp;").toString().compareTo("null")==0)
                                       out.print(vEti.Texto("ECampo", "&nbsp;"));
                                     else
                                       out.print(vEti.Texto("ECampo", bs.getFieldValue("cTel", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cCorreoE", "&nbsp;").toString()));

                                     if(Integer.parseInt(""+bs.getFieldValue("lPrivada"))==0)
                                       out.print(vEti.Texto("ETablaC", "NO"));
                                     else
                                       out.print(vEti.Texto("ETablaC", "SI"));

                                     if(lDetalle){
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrDetalle('" + bs.getFieldValue("iCveUniMed") + "');", ""));
                                       out.print("</td>");
                                     }
                                     if(lModulos){
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Módulos","JavaScript:fIrModulos('" + bs.getFieldValue("iCveUniMed") + "');", ""));
                                       out.print("</td>");
                                     }
                                     out.print("</tr>");
                                   }
                               }
                               else{
                                 out.println("<tr>");
                                 out.print("<td class='ETablaC' colspan='8'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 out.println("</tr>");
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
