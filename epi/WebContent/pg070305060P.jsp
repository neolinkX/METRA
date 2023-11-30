<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S. A. de C.V.
 * @author Generador de Código J2EE.
 * @version 1.0
 * Clase:        JSP para prueba de ingeniería
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>

<html>
<%  // Unica Parte del Código que debe modificarse
        pg0700007CFG  clsConfig = new pg0700007CFG();
%>
<script language="JavaScript">
  function fOnLoad(){
  }
  function fValidaTodo(){
    return true;
  }
  function fSubmite(objImg, cValor){
  }
  function cambiaSource(objImagen, cEstado){
  }
  function fMouseOver(objDoc, cImagen, cEstatus){
  }
  function fMouseOut(objDoc, cImagen){
  }
  function cambiaEstado(cEstado){
  }
  function fTitulo(cAccion){
    var form=document.forms[0];
    form.hdAccion.value=cAccion;
    form.submit();
  }
</script>
<%
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TEntorno    vEntorno      = new TEntorno();
  TNavPanel   NavPanel      = new TNavPanel();
  Vector vBotones = new Vector();
  Vector vUrls    = new Vector();
  Vector vEstatus = new Vector();
  Vector lActivos = new Vector();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  clsConfig.runConfig(vEntorno, vBotones, vUrls, vEstatus, lActivos);
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cRutaAyuda = vParametros.getPropEspecifica("html");
  String cTitulo = "" + request.getParameter("hdTitulo");
  if(cTitulo.compareTo("null") == 0)
    cTitulo = "";
  else
    cTitulo = vParametros.getPropEspecifica("NomModulo") + " " + cTitulo;

  String cAccion=""+request.getParameter("hdAccion");
  if(cAccion.compareTo("")!=0){
    try{
       HashMap hmPermisos=(HashMap)request.getSession(true).getAttribute("PermisosUsuario");
       if(hmPermisos.containsKey(cAccion)){
          StringTokenizer stTitulo=new StringTokenizer(""+hmPermisos.get(cAccion),"|");
          cTitulo=stTitulo.nextToken();
       }
    }catch(Exception e){
       System.out.print("pg0700007.jsp:");
       e.printStackTrace();
    }
  }
%><head>
  <title><%= vEntorno.getTituloVentana() /* Obtiene el título de la ventana de los parámetros y lo muestra */ %></title>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabeceras(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<script language="JavaScript">
  window.defaultStatus = "<%= vParametros.getPropEspecifica("BarraEdo") %>";
</script>
<script LANGUAGE="JavaScript">
  function fOnLoad(){
    try{
      var forma=window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];
      if(forma){
<%  int    iOpc   = request.getParameter("iOpc") != null && request.getParameter("iOpc").length() > 0 ? Integer.parseInt(request.getParameter("iOpc").toString()) : 0;
    String cCombo = request.getParameter("cCombo") != null && request.getParameter("cCombo").length() > 0 ? request.getParameter("cCombo").toString() : "ninguno";
    Vector vcCombo = null;
    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
    switch  (iOpc){
      case 2: // Módulo
              // Obtener Clave de la Unidad Médica
              int iCveUniMed = request.getParameter("val1") != null && request.getParameter("val1").length() > 0 ? Integer.parseInt(request.getParameter("val1").toString()) : 0;
              vcCombo = new TDGRLModulo().FindByAll("where iCveUniMed = " + iCveUniMed ," order by cDscModulo");
    }
%>        var cCombo=forma.<%=cCombo%>;
        if(cCombo){
          while(cCombo.length>0) cCombo.options[0]=null;//pon en blanco al combo

<%  if(vcCombo!=null && vcCombo.size()>0){
  %>          cCombo.options[0]=new Option("Seleccione...","-1");
  <%
      for(Enumeration eCombo=vcCombo.elements();eCombo.hasMoreElements();){
        TVGRLModulo vDinamico = (TVGRLModulo) eCombo.nextElement();
%>          cCombo.options[cCombo.length]=new Option("<%=vDinamico.getCDscModulo()%>","<%=vDinamico.getICveModulo()%>");
<%    }
%>

<%  }else{
%>          cCombo.options[cCombo.length]=new Option("Datos no disponibles...","");
<%  }
%>        }
      }
    }catch(e){}
    cCombo.options[0].defaultSelected = true;
  }
</script>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdEstado" value="">
  <input type="hidden" name="hdTitulo" value="<%if((""+request.getParameter("hdTitulo")).compareTo("null")!=0) out.print(request.getParameter("hdTitulo"));%>">
  <input type="hidden" name="hdAccion" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
    <TD class="ETituloTPag" align="center"><%=cTitulo.toUpperCase()%></TD>
    </TR>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
