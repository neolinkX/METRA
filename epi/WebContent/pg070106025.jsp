<%/**
 * Title: Selecci�n del paciente al servicio
 * Description: Selecci�n del paciente al servicio
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070106025
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
  pg070106025CFG  clsConfig = new pg070106025CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106025.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106025.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Grupo|Inicio vigencia|Fin vigencia|Vigente|";    // modificar
  String cCveOrdenar  = "cDscGrupo|dtInicio|dtFin|lVigente|";  // modificar
  String cDscFiltrar  = "Inicio vigencia|Fin vigencia|";    // modificar
  String cCveFiltrar  = "p.dtInicio|p.dtFin|";  // modificar
  String cTipoFiltrar = "5|5|";                // modificar 7/8
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Reporte";            // modificar

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
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  int iCveUniMed =  Integer.parseInt(request.getParameter("iCveUniMed")==null||request.getParameter("iCveUniMed").equals("")||request.getParameter("iCveUniMed").equals("null")?"0":request.getParameter("iCveUniMed"));
  int iCveProceso = Integer.parseInt(request.getParameter("iCveProceso")==null||request.getParameter("iCveProceso").equals("")||request.getParameter("iCveProceso").equals("null")?"0":request.getParameter("iCveProceso"));
  int iCveModulo =  Integer.parseInt(request.getParameter("iCveModulo")==null||request.getParameter("iCveModulo").equals("")||request.getParameter("iCveModulo").equals("null")?"0":request.getParameter("iCveModulo"));
/*
  TDEXPRama dEXPRama = new TDEXPRama();
  TVEXPRama vEXPRama = new TVEXPRama();
  Vector vcEXPRama = new Vector();*/

  int iConcluido = 0;

String dFechaActual=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):new TFechas().getFechaDDMMYYYY(new java.sql.Date(new java.util.Date().getTime()),"/");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  var wExp;
  function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
//      cInicio += "&hdICveRama=" + iCveRama;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

       wExp = open("pg070104011.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=600,height=300,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }

      function fIrCatalogo(page,val){
    var form=document.forms[0];
    form.action=page + "?iNumExamen=" + val;
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
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="500">
  <%/*
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     } else {
       cPosicion = request.getParameter("hdRowNum");
     }*/
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iNumExamen" value="">
  <input type="hidden" name="iCvePerfil" value="">
  <input type="hidden" name="iCvePersonal" value="">
  <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
  <input type="hidden" name="tpoBusqueda" value="variosMedicos">
  <!--input type="hidden" name="ramaInicial" value="0"-->

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <%
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                            %>
                            <tr><td class="ETablaT" colspan="6">Selecci&oacute;n del Paciente al Servicio</td></tr>

                            <tr>
                              <td class="EEtiqueta" colspan="1">Unidad M&eacute;dica:</td>
                              <td class="ETabla" colspan="1">
                                   <%=vEti.SelectOneRowSinTD("iCveUniMed","fCambiaSelects(1);",
                                                             clsConfig.getUniMedsValidas(vParametros.getPropEspecifica("EPIProceso")),
                                                             "iCveUniMed","cDscUniMed",request,"0",true)%>
                              </td>
                              <td class="EEtiqueta" colspan="1">M&oacute;dulo:</td>
                              <td class="ETabla" colspan="1">
                              <%=vEti.SelectOneRowSinTD("iCveModulo","fCambiaSelects(2);", clsConfig.getModulos(request.getParameter("iCveUniMed")), "cIndice","cDescripcion",request,"0",true)%>
                            </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Servicio:</td>
<%
HashMap hm = vUsuario.getVServicios(iCveUniMed, iCveProceso, iCveModulo);
Vector vServicios = new TDGRLUSUMedicos().findServXUsu(vUsuario.getICveusuario(),iCveUniMed, iCveProceso, iCveModulo);
%>
                              <td class="ETabla" colspan="2">
                                   <%=vEti.SelectOneRowSinTD("iCveServicio","",  vServicios, "iCveServicio","cDscServicio",request,"0",true)%>
                             </td>
                            </tr>

                           <tr><%=vEti.EtiCampoCS("EEtiqueta", "Fecha desde: ", "ECampo", "text\" readonly=\"yes", 10, 10,1,"dtDesde", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request)%>
                               <%=vEti.EtiCampoCS("EEtiqueta", "Hasta: ", "ECampo", "text\" readonly=\"yes", 10, 10,1,"dtHasta", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request)%>
                           </tr>
                           <tr><%
                              String cRadio1 = "checked";
                              String cRadio2 = "";
                              /*if(request.getParameter("rdFecha") != null &&Integer.parseInt(request.getParameter("rdFecha")) == 1){
                                cRadio2 = cRadio1;
                                cRadio1 = "";
                              }*/
                             %>
                             <!-- 
                           <td class="EEtiqueta" >Fecha de Examen <input type="radio" name="rdFecha" value="0" <%= cRadio1 %> ></td>
                           <td class="EEtiqueta" >Fecha de Dictamen <input type="radio" name="rdFecha" value="1" <%= cRadio2 %> ></td>
                            -->
<%
                              String cSelect1 = "selected";
                              String cSelect2 = "";
                              if(request.getParameter("iOrden") != null && Integer.parseInt(request.getParameter("iOrden")) == 1){
                                cSelect2 = cSelect1;
                                cSelect1 = "";
                              }
%>
                              <td class="EEtiqueta" >Ordenar por:</td>
                              <td align="left" colspan="5">
                               <select name="iOrden">
                                <option value="0" <%= cSelect1 %> >Num. de Expediente</option>
                                <option value="1" <%= cSelect2 %> >Ap. Paterno</option>
                               </select>
                              </td>
                          </tr>
                          </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
<%
  if(clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() > 0){
     out.println(clsConfig.getSbReporte());
  }
  else if(request.getParameter("hdBoton") != null && request.getParameter("hdBoton").equals("Generar Reporte") &&
    clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() == 0){
%><script language="JavaScript">alert("No Hay Registros Coincidentes.");</script>
<%
  }
%>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>