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
  pg071003030CFG  clsConfig = new pg071003030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cDetalle    = "pg071003021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Nombre|";    // modificar
  String cCveOrdenar  = "iCveModulo|cDscModulo|";  // modificar
  String cDscFiltrar  = "Clave|Nombre|";    // modificar
  String cCveFiltrar  = "iCveModulo|cDscModulo|";  // modificar
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
  <input type="hidden" name="hdCampoClave2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ 
      boolean cargaModServAdmin =  false;
      cargaModServAdmin = clsConfig.CargaModulosYServiciosAdmin();
	  //System.out.println("Se han cargado los modulos y servicio "+cargaModServAdmin);
  %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="8" class="ETablaT">Módulos
                              </td>
                            </tr>

            <tr>
              <td class="ETablaTR">Unidad Médica:</td>
              <td class="ETabla" colspan='5'>
              <%
              TDGRLUniMed dGRLUniMed = new TDGRLUniMed();
              TVGRLUniMed vGRLUniMed = new TVGRLUniMed();
              Vector vcGRLUniMed = new Vector();
              if(request.getParameter("hdBoton").compareTo("Guardar")==0 || request.getParameter("hdBoton").compareTo("GuardarA")==0 || request.getParameter("hdBoton").compareTo("Cancelar")==0){
                out.print("<select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLUniMed = dGRLUniMed.FindByAll("", "");
                if (vcGRLUniMed.size() > 0){
                  for (int i = 0; i < vcGRLUniMed.size(); i++){
                    vGRLUniMed = (TVGRLUniMed)vcGRLUniMed.get(i);
                    if (request.getParameter("hdUniMed")!=null && request.getParameter("hdUniMed").compareToIgnoreCase(new Integer(vGRLUniMed.getICveUniMed()).toString())==0)
                      out.print("<option value = " + vGRLUniMed.getICveUniMed() + " selected>" + vGRLUniMed.getCDscUniMed() + "</option>");
                    else
                      out.print("<option value = " + vGRLUniMed.getICveUniMed() + ">" + vGRLUniMed.getCDscUniMed() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              else{
                if(request.getParameter("hdBoton").compareTo("Nuevo")==0 || request.getParameter("hdBoton").compareTo("Modificar")==0)
                  out.print("<select name=\"iCveUniMed\" disabled>");
                else
                  out.print("<select name=\"iCveUniMed\" size=\"1\" OnChange=\"document.forms[0].target='FRMDatos';document.forms[0].hdBoton.value='Primero';document.forms[0].submit();\">");
                vcGRLUniMed = dGRLUniMed.FindByAll("", " order by cDscUniMed ");
                if (vcGRLUniMed.size() > 0){
                  if(request.getParameter("iCveUniMed")==null || request.getParameter("iCveUniMed").compareTo("")==0)
                    out.print("<option value = 0>Seleccione...</option>");
                  else if(request.getParameter("iCveUniMed")!=null && Integer.parseInt(request.getParameter("iCveUniMed"))<1 )
                    out.print("<option value = 0>Seleccione...</option>");
                  for (int i = 0; i < vcGRLUniMed.size(); i++){
                    vGRLUniMed = (TVGRLUniMed)vcGRLUniMed.get(i);
                    if (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveUniMed").compareToIgnoreCase(new Integer(vGRLUniMed.getICveUniMed()).toString())==0)
                      out.print("<option value = " + vGRLUniMed.getICveUniMed() + " selected>" + vGRLUniMed.getCDscUniMed() + "</option>");
                    else
                      out.print("<option value = " + vGRLUniMed.getICveUniMed() + ">" + vGRLUniMed.getCDscUniMed() + "</option>");
                  }
                }
                else{
                  out.print("<option value = 0>Datos no disponibles...</option>");
                }
              }
              out.print("</select>");
              out.print("</td>");
              out.print("</tr>");

                              // modificar según listado
                              out.print("<tr>");
                              out.print("<td class='ETablaT'>Clave</td>");
                              out.print("<td class='ETablaT'>Nombre</td>");
                              out.print("<td class='ETablaT'>Dirección</td>");
                              out.print("<td class='ETablaT'>Teléfono</td>");
                              out.print("<td class='ETablaT'>Correo Electrónico</td>");
                              if(clsConfig.getLPagina(cDetalle))
                                out.print("<td class=\"ETablaT\">Selección</td>");
                              out.print("</tr>");

                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveModulo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cDscModulo", "&nbsp;").toString()));

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
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cTel", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampo", bs.getFieldValue("cCorreoE", "&nbsp;").toString()));

                                     if(clsConfig.getLPagina(cDetalle)){
                                       out.print("<td class=\"ECampo\">");
                                       out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrDetalle('" + bs.getFieldValue("iCveUniMed") + "', '" + bs.getFieldValue("iCveModulo") + "');", ""));
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
