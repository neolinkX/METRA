<%/**
 * Title: Configuración de Aviso
 * Description: Modifica y habilita el mensaje de inicio
 * Copyright: 2012
 * @author Infotec
 * @version 1
 * Clase: pg071001032
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
  pg071001032CFG  clsConfig = new pg071001032CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071001032.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071001032.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  //String cCatalogo    = "pg070104021.jsp";       // modificar
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

   /*function fIrCatalogo(page,val){
    var form=document.forms[0];
    form.action=page + "?iNum xamen=" + val;
    form.target="FRMDatos";
    form.submit();
  }*/


  function fOnLoad(){
    fOnSet(window,"",'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
    <!-- Estilos de Extjs-Base -->
<link rel="stylesheet" type="text/css" href="/medprev/extjs/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css" href="/medprev/extjs/resources/css/Ingresos.css" >
<!-- Librerias de Extjs-Base -->
<script type="text/javascript" src="/medprev/extjs/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="/medprev/extjs/ext-all.js"></script>
<!-- Librerias de Dwr-Base -->
<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<!-- Objetos de Dwr de la Aplicación -->

<script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>
<script type='text/javascript' src='/medprev/dwr/interface/AdmonUsuarios.js'></script>
<!--  <link rel="stylesheet" type="text/css" href="./extjs/examples/ux/FilterRow/FilterRow.css"/>-->
    <!--  <script type="text/javascript" src="./extjs/examples/ux/FilterRow/FilterRow.js"></script>-->
<script type="text/javascript" src="/medprev/extjs/examples/ux/SearchField.js"></script>
<script type="text/javascript" src="/medprev/extjs/examples/ux/PagingMemoryProxy.js"></script>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg071001032.js"%>"></script>
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

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>

  </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>