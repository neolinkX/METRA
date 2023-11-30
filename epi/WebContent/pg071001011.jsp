<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Jaime Enrique Suárez Romero
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>

<html>
<%
  pg071001011CFG  clsConfig = new pg071001011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071001011.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071001011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Nombre|";    // modificar
  String cCveOrdenar  = "iCveUsuario|segusuario.cNombre|";  // modificar
  String cDscFiltrar  = "Clave|Nombre|";    // modificar
  String cCveFiltrar  = "iCveUsuario|segusuario.cNombre|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
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
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave1" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="12" class="ETablaT">Listado de Usuarios</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT">Registro</td>
                              <td class="ETablaT">Nombre Usuario</td>
                              <td class="ETablaT">Nombre</td>
                              <td class="ETablaT">País</td>
                              <td class="ETablaT">Entidad</td>
                              <td class="ETablaT">Municipio</td>
                              <td class="ETablaT">Colonia</td>
                              <td class="ETablaT">Calle</td>
                              <td class="ETablaT">C.P.</td>
                              <td class="ETablaT">Teléfono</td>
                              <td class="ETablaT">Bloqueado</td>
                            </tr>
                            <%
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveUsuario", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtRegistro", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cUsuario", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNombre", "&nbsp;")+ " "+ bs.getFieldValue("cApPaterno", "&nbsp;") + " "+ bs.getFieldValue("cApMaterno", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscPais", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscEntidadFed", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscMunicipio", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cColonia", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cCalle", "&nbsp;")));
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCodigoPostal", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cTelefono", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("lBloqueado", "&nbsp;")));
                                   }
                               }else{
                                 out.print(vEti.TextoCS("ETabla","No existen registros coincidentes con el filtro proporcionado",12));
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
