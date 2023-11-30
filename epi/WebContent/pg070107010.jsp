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
  pg070107010CFG  clsConfig = new pg070107010CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070107010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070107010.jsp\" target=\"FRMCuerpo"); // modificar
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
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";            // modificar
//  String cEstatusIR   = "Imprimir";            // modificar

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
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  var wExp;
  function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

       wExp = open("pg070104021.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=700,height=500,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }

   function fIrCatalogo(page,val){
    var form=document.forms[0];
    form.action=page + "?iNum xamen=" + val;
    form.target="FRMDatos";
    form.submit();
  }


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
     String cBusqueda = "";
       if(request.getParameter("tpoBusqueda") != null)
         cBusqueda = request.getParameter("tpoBusqueda");
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <!-- input type="hidden" name="iNumExamen" value="" -->
  <input type="hidden" name="iCvePerfil" value="">
  <input type="hidden" name="iCvePersonal" value="">
  <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
  <input type="hidden" name="tpoBusqueda" value="<%=cBusqueda%>">
  <input type="hidden" name="ramaInicial" value="0">
  <input type="hidden" name="hdMacAddress" value="">
  <input type="hidden" name="hdIpAddress" value="">
  <input type="hidden" name="hdComputerName" value="">

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
                            <tr><td class="ETablaT" colspan="6">Liberar Serv&iacute;cios y Ramas</td></tr>
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
                                   <%=vEti.SelectOneRowSinTD("iCveModulo","fCambiaSelects(2);", clsConfig.getModulos(request.getParameter("iCveUniMed")), "cIndice","cDescripcion",request,"0",true)%>

                            </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Servicio:</td>
<%                             Vector vServicios = new TDGRLUSUMedicos().findServXUsu(vUsuario.getICveusuario(),iCveUniMed, iCveProceso, iCveModulo);
%>                            <td class="ETabla" colspan="5">
                                   <%=vEti.SelectOneRowSinTD("iCveServicio","", vServicios, "iCveServicio","cDscServicio",request,"0",true)%>
                            </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Expediente:</td>
<%                            out.print(vEti.CeldaCampoCS("ETabla", "text", 30, 9, 3, "iCveExpediente", "", 0, "", "", true, true, true, request));%>
                              <td class="ETabla" colspan="1">
<%                               out.print(vEti.clsAnclaTexto("EAnclaC","Buscar Expediente","javascript:fBuscar();","Buscar"));
%>                            </td>
                              <td class="ETabla" colspan="1">&nbsp;
<% //                              out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer();", "Buscar Personal",""));
%>                            </td>
                            </tr>
                            <tr>
                              <td class="ETablaT" colspan="1">Expediente</td>
                              <td class="ETablaT" colspan="3">Nombre</td>
                              <td class="ETablaT" colspan="1">RFC</td>
                              <td class="ETablaT" colspan="1">Fecha</td>
                            </tr>
                            <%
                            if (bs != null){
                              bs.start();
                              int iVariosMedicos = 0;
                              String exp = bs.getFieldValue("iCveExpediente","").toString();
                              String exm = bs.getFieldValue("iNumExamen","").toString();
                              String per = bs.getFieldValue("iCvePersonal","").toString();
%>                              <input type="hidden" name="iNumExamen" value="<%= exm %>">
<%                              while(bs.nextRow()){
%>                                <tr>
                                    <td class="ETablaC" colspan="1"><%=bs.getFieldValue("iCveExpediente","&nbsp;")%>
<%//                                    out.print(vEti.clsAnclaTexto("EAnclaC","Capturar Servicio", "javascript:if(fValidaSelectores())fIr('pg070104030.jsp'," + exp + "," + exm + "," + per + ");","exp"));
%>                                  </td>
                                    <td class="ETablaC" colspan="3"><%=bs.getFieldValue("cNombre","&nbsp;")+" "+bs.getFieldValue("cApPaterno","&nbsp;")+" "+bs.getFieldValue("cApMaterno","&nbsp;")%></td>
                                    <td class="ETablaC" colspan="1"><%=bs.getFieldValue("cRFC","&nbsp;")%></td>
                                    <td class="ETablaC" colspan="1"><%=clsConfig.formatDate(bs.getFieldValue("dtSolicitado").toString())%></td>
                                  </tr>
<%                              }

                                if(request.getParameter("iCveServicio") != null && Integer.parseInt(request.getParameter("iCveServicio")) > 0){
                                  int iReqDiag = 0;
                                  Vector vMEDServicio = new TDMEDServicio().FindByAll(" WHERE iCveServicio = "+request.getParameter("iCveServicio"));
                                  if(vMEDServicio != null && vMEDServicio.size() > 0){
                                    TVMEDServicio tvMEDServicio = (TVMEDServicio)vMEDServicio.get(0);
                                    iReqDiag = tvMEDServicio.getLReqDiag();
                                    iVariosMedicos = tvMEDServicio.getLVariosMeds();
//                                    iVariosMedicos = 1;
%>                                  <input type="hidden" name="lVariosMeds" value="<%=iVariosMedicos%>">
<%                                  if(iVariosMedicos == 0){
%>                                    <tr>
                                        <td class="ETablaT" >Servicio: </td>
                                        <td class="ETablaC" colspan="4"><%= tvMEDServicio.getCDscServicio() %></td>
                                        <td class="ETablaC" ><%= (vEti.clsAnclaTexto("EAnclaC","Liberar", "javascript:if(fValidaSelectores())fLiberar('pg070107010.jsp');","exp")) %></td>
                                      </tr>
<%                                  }
                                    else{
                                         HashMap p = new HashMap();
                                         p.put("iCveServicio", request.getParameter("iCveServicio"));
                                         p.put("iCveExpediente", exp);
                                         p.put("iNumExamen", exm);
                                         p.put("lConcluido", "1");
					 //System.out.println("pg070107010");
                                      Vector vMEDRama = new TDEXPRama().findByPK(p);
                                      if(vMEDRama != null && vMEDRama.size() > 0){
                                        String cChecked = "";
                                        if(request.getParameter("iTodos") != null)
                                           cChecked = "checked";
%>                                      <input type="hidden" name="maxValue" value="<%=vMEDRama.size()%>">
                                        <tr>
                                          <td class="ETablaT" colspan="2" >Servicio: </td>
                                          <td class="ETablaC" colspan="2">Todos</td>
                                          <td class="ETablaC" ><input type="checkbox" name="iTodos" value="1" onClick="fCheckAll()" <%= cChecked %> ></td>
                                          <td class="ETablaC" ><%= (vEti.clsAnclaTexto("EAnclaC","Liberar", "javascript:if(fValidaSelectores())fLiberar('pg070107010.jsp');","exp")) %></td>
                                        </tr>
<%                                      for(int i = 0; i < vMEDRama.size(); i++){
                                          TVEXPRama tvExpRama = (TVEXPRama)vMEDRama.get(i);
                                          if(request.getParameter("iRama"+i) != null)
                                            cChecked = "checked";
                                          else
                                            cChecked = "";
%>                                        <tr>
                                            <td class="EEtiqueta" colspan="5"><%= tvExpRama.getCDscRama() %></td>
                                            <td class="ETablaC" ><input type="checkbox" name="iRama<%=i%>" value="<%= tvExpRama.getICveRama() %>" <%= cChecked %> ></td>
                                          </tr>
<%                                      }
                                      }
                                    }
                                    if(iReqDiag != 0){
                                      String cChecked = "";
                                      if(request.getParameter("iReqDiag") != null)
                                        cChecked = "checked";
%>                                    <tr>
                                        <td class="ETablaT" colspan="3" >Diagnostico y Recomendaci&oacute;n: </td>
                                        <td class="ETablaC" colspan="3" ><input type="checkbox" name="iReqDiag" value="1" <%= cChecked %> ></td>
                                      </tr>
<%                                  }
                                  }
                                }
                            }
                            else{
%>                             <tr>
                                 <td class="ETablaT" colspan="6">No Hay Registro Coincidente</td>
                               </tr>
<%                          }
%>                        </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
<%
  if(clsConfig.getIFlag() != 0){
    out.println("<script language=\"JavaScript\">alert('Se Ha Liberado El Servicio.');</script>");
  }
%>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>