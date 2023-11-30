<%/**
 * Title: Selección del paciente al servicio
 * Description: Selección del paciente al servicio
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez   
 * @version 1
 * Clase: pg070104020
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070104020CFG  clsConfig = new pg070104020CFG();                 // modificar
  
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Grupo|Inicio vigencia|Fin vigencia|Vigente|";    // modificar
  String cCveOrdenar  = "cDscGrupo|dtInicio|dtFin|lVigente|";  // modificar
  String cDscFiltrar  = "Inicio vigencia|Fin vigencia|";    // modificar
  String cCveFiltrar  = "p.dtInicio|p.dtFin|";  // modificar
  String cTipoFiltrar = "5|5|";                // modificar 7/8
  boolean lFiltros    = false;                  // modificar
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
  String cNavStatus  = "Hidden";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  int iCveUniMed =  Integer.parseInt(request.getParameter("iCveUniMed")==null||request.getParameter("iCveUniMed").equals("")||request.getParameter("iCveUniMed").equals("null")?"0":request.getParameter("iCveUniMed"));
  int iCveProceso = Integer.parseInt(request.getParameter("iCveProceso")==null||request.getParameter("iCveProceso").equals("")||request.getParameter("iCveProceso").equals("null")?"0":request.getParameter("iCveProceso"));
  int iCveModulo =  Integer.parseInt(request.getParameter("iCveModulo")==null||request.getParameter("iCveModulo").equals("")||request.getParameter("iCveModulo").equals("null")?"0":request.getParameter("iCveModulo"));

  TDEXPServicio dEXPServicio = new TDEXPServicio();
  TVEXPServicio vEXPServicio = new TVEXPServicio();
  Vector vcEXPServicio = new Vector();

  int iConcluido = 0;
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
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
  <input type="hidden" name="FILNumReng" value="500">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     } else {
       cPosicion = request.getParameter("hdRowNum");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iNumExamen" value="">
  <input type="hidden" name="iCvePerfil" value="">
  <input type="hidden" name="iCvePersonal" value="">
  <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
  <input type="hidden" name="tpoBusqueda" value="unMedico">
  <input type="hidden" name="ramaInicial" value="0">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                            %>
                            <tr><td class="ETablaT" colspan="6">Selección del Paciente al Servicio</td></tr>
                            <tr>
                              <td class="EEtiqueta" colspan="3">Proceso:</td>
                              <td class="ETabla" colspan="3"><%=clsConfig.getProceso(vParametros.getPropEspecifica("EPIProceso"))%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Unidad Médica:</td>
                              <td class="ETabla" colspan="2">
                                   <%=vEti.SelectOneRowSinTD("iCveUniMed","fCambiaSelects(1);",
                                                             clsConfig.getUniMedsValidas(vParametros.getPropEspecifica("EPIProceso")),
                                                             "iCveUniMed","cDscUniMed",request,"0",true)%>
                              </td>
                              <td class="EEtiqueta" colspan="2">Módulo:</td>
                              <td class="ETabla" colspan="1">
                                   <%=vEti.SelectOneRowSinTD("iCveModulo","fCambiaSelects(2);",
                                                             clsConfig.getModulos(request.getParameter("iCveUniMed"),vUsuario.getICveusuario()),
                                                             "cIndice","cDescripcion",request,"0",true)%>

                            </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Servicio:</td>
<%
Vector vServicios = new TDGRLUSUMedicos().findServicios(vUsuario.getICveusuario(),iCveUniMed, iCveProceso, iCveModulo, false);
%>
                              <td class="ETabla" colspan="5">
                                   <%=vEti.SelectOneRowSinTD("iCveServicio","",
                                                             vServicios,
                                                             "iCveServicio","cDscServicio",request,"0",true)%>
                            </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Expediente:</td>
<%                            out.print(vEti.CeldaCampoCS("ETabla", "text", 30, 9, 3, "iCveExpediente", "", 0, "", "", true, true, true, request));%>

                              <td class="ETabla" colspan="1">
<%
                                 out.print(vEti.clsAnclaTexto("EAnclaC","Buscar expediente",
                                                              "javascript:fBuscar();","Buscar"));
%>
                            </td>
                              <td class="ETabla" colspan="1">
<%
                              out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer();", "Buscar Personal",""));
%>
                            </td>
                            </tr>


                            <tr>
                              <td class="ETablaT" colspan="4">Expediente</td>
                              <td class="ETablaT" colspan="1">RFC</td>
                              <td class="ETablaT" colspan="1">Fecha</td>
                            </tr>
                            <%
                            if (bs != null){
                              bs.start();
                              
                              String exp = bs.getFieldValue("iCveExpediente","").toString();
                              String exm = bs.getFieldValue("iNumExamen","").toString();
                        //      String exm = "1";
                              String per = bs.getFieldValue("iCvePersonal","").toString();
                        //    String per = exm;

                              // Comprueba que el operador tenga activado el servicio
                              //System.out.println("Parametros: " + request.getParameter("iCveExpediente")+","+ exm +","+ request.getParameter("iCveServicio"));
                              vcEXPServicio = dEXPServicio.getLConcluido(request.getParameter("iCveExpediente"), exm, request.getParameter("iCveServicio"));
                              //System.out.println("Tamaño del Vector" + vcEXPServicio.size());
                              if(vcEXPServicio.size()>0){
                                vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                                iConcluido = vEXPServicio.getLConcluido();
                              }
                              else
                                iConcluido = -1; // Para que no entre a ninguna condición de búsqueda

                              //System.out.println("en jsp lconcluido: " + iConcluido);
                              if(iConcluido == 0){
                                while(bs.nextRow()){
                                  %>
                                  <tr>
                                  <td class="ETablaC" colspan="4"><%
                                  out.print(vEti.clsAnclaTexto("EAnclaC","Capturar Servicio",
                                  "javascript:if(fValidaSelectores()){" +
                                          "fIr('pg070104030.jsp'," + exp + "," + exm + "," + per + ");" +
                                          "" +
                                          "}","exp"));
                                  %>
                                  </td>
                                  <td class="ETablaC" colspan="1"><%=bs.getFieldValue("cRFC","&nbsp;")%></td>
                                  <td class="ETablaC" colspan="1"><%=clsConfig.formatDate(bs.getFieldValue("dtSolicitado").toString())%></td>
                                  </tr>
                                  <%

                                }
                              }
                              else if(iConcluido == 1){
                                out.println("<tr>");
                                out.println("<td colspan=6 align='center'>");
                                out.print(vEti.clsAnclaTexto("EAncla","Ver/Imprimir Resultados del Servicio","JavaScript:fVerExamen(" + exp + ", " + exm + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                                out.println("</td>");
                                out.println("</tr>");
                                String cInter    = "pg070104034.jsp";

                              if (clsConfig.getLPagina(cInter)){

                                 out.println("<tr><td align='center' colspan='8'>");
                                 out.print(vEti.clsAnclaTexto("EAncla","InterConsulta","JavaScript:fIrCatalogo('pg070104034.jsp'," + vEXPServicio.getINumExamen()+ ");", "InterConsulta",""));
                                 out.println("</tr></td>");
                              }

                              }
                              else if(iConcluido==-1){
                                out.println("<tr>");
                                out.println("<td class='ETablaC' colspan=6>No existen datos coincidentes con el filtro proporcionado</td>");
                                out.println("</tr>");
                              }



                            }
                            else {
                              if(request.getParameter("iCveExpediente")!=null && request.getParameter("iCveExpediente").compareTo("null")!=0 && request.getParameter("iCveExpediente").compareTo("")!=0 && request.getParameter("iNumExamen")!=null && request.getParameter("iNumExamen").compareTo("null")!=0 && request.getParameter("iNumExamen").compareTo("")!=0 && request.getParameter("iCveServicio")!=null && request.getParameter("iCveServicio").compareTo("null")!=0 && request.getParameter("iCveServicio").compareTo("")!=0){
                                // Comprueba que el operador tenga activado el servicio
                                vcEXPServicio = dEXPServicio.getLConcluido(request.getParameter("iCveExpediente"), request.getParameter("iNumExamen"), request.getParameter("iCveServicio"));
                                if(vcEXPServicio.size()>0){
                                  vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                                  iConcluido = vEXPServicio.getLConcluido();
                                }
                                else
                                  iConcluido = -1; // Para que no entre a ninguna condición de búsqueda

                                if(iConcluido == 1){
                                  out.println("<tr>");
                                  out.println("<td colspan=6 align='center'>");
                                  out.print(vEti.clsAnclaTexto("EAncla","Ver/Imprimir Resultados del Servicio","JavaScript:fVerExamen(" + request.getParameter("iCveExpediente") + ", " + request.getParameter("iNumExamen") + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                                  out.println("</td>");
                                  out.println("</tr>");
                                }
                                else if(iConcluido==-1){
                                  out.println("<tr>");
                                  out.println("<td class='ETablaC' colspan=6>No existen datos coincidentes con el filtro proporcionado</td>");
                                  out.println("</tr>");
                                }
                              }
                              else{
                                out.println("<tr>");
                                out.println("<td class='ETablaC' colspan=6>No existen datos coincidentes con el filtro proporcionado</td>");
                                out.println("</tr>");
                              }
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
