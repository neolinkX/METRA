<%/**
 * Title: Listado de Especialidades por Perfil Médico Científico
 * Description: Listado de Especialidades por Perfil Médico Científico
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070101085
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070101085CFG  clsConfig = new pg070101085CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101085.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101085.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101085.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Especialidad|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  TFechas tf = new TFechas();
  java.sql.Date d = null;
  String fechaFormateada = "";

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
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave2" value="">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveMdoTrans" value="<%=request.getParameter("iCveMdoTrans")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="ETablaTR">Perfil:
                              </td>
<td class="ECampo">
    <SELECT NAME="iCvePerfil" SIZE="1" onChange="fCambiaPerfil('Actual');">
        <option value="0">Seleccione...</option>
<%

   Vector perfiles = new TDMEDPerfilMC().findPerfilGpoMdo(" WHERE m.iCveMdoTrans="+request.getParameter("iCveMdoTrans")," order by m.cDscMdoTrans, g.cDscGrupo ");
   String selected="";
   String optionSel="";
   String llave="";
   String valor="";
   for(int c = 0; c < perfiles.size(); c++) {
       TVMEDPerfilMC tv = (TVMEDPerfilMC) perfiles.elementAt(c);
       if (tv.getDtInicio()==null) {
           fechaFormateada = "&nbsp;";
       } else {
           d = tv.getDtInicio();
           fechaFormateada = sdf.format(d);
       }
       llave = ""+tv.getICvePerfil();
       valor = tv.getCDscMdoTrans()+" - "+tv.getCDscGrupo()+" ["+fechaFormateada+"]";
       selected = llave.equals(request.getParameter("iCvePerfil"))?"SELECTED":"";
       optionSel = selected.equals("SELECTED")?valor:"";
%>
        <option <%=selected%> value="<%=llave%>"><%=valor%></option>
<% } %>
    </SELECT>
</td>

                            </tr>
                         </table>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="5" class="ETablaT">Especialidades
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Perfil</td>
                              <td class="ETablaT" colspan="2">Especialidad</td>
                            </tr>
                            <%
                               if (bs != null){
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscPerfil", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEspecialidad", "&nbsp;")));
                                        //out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cEspecificacion", "&nbsp;")));
                                       if (clsConfig.getLPagina(cCatalogo)){
                                           out.print("<td class=\"ECampo\">");
                                           out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIr('" + bs.getFieldValue("iCvePerfil","") + "','" + bs.getFieldValue("iCveEspecialidad","") + "','pg070101082','pg070101082.jsp');","Ir a..."));
                                           out.print("</td>");
                                       }
                                   }
                               } else {
                                      out.println("<tr>");
                                      out.print(vEti.EtiCampo("EResalta", "Mensaje:", "EResalta", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
