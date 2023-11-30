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
<%@ page import="com.micper.util.caching.*"%>
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
    form = document.forms[0];
    form.hdAccion.value = cAccion;
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

  String cAccion = ""+request.getParameter("hdAccion");
  if(cAccion.compareTo("") != 0){
    try{
       HashMap hmPermisos = (HashMap) request.getSession(true).getAttribute("PermisosUsuario");
       if(hmPermisos.containsKey(cAccion)){
          StringTokenizer stTitulo = new StringTokenizer(""+hmPermisos.get(cAccion),"|");
          cTitulo = stTitulo.nextToken();
       }
    }catch(Exception e){
       System.out.print("pg0700007.jsp:");
       e.printStackTrace();
    }
  }

  int j = 0;
  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     String ObjetoDes2 = request.getParameter("objDes2");
     String ObjetoDes3 = request.getParameter("objDes3");

     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:{
           if (request.getParameter("val1")!=null){
               TVMotivo vMotivo = new TVMotivo();
               Vector vExCuali = new Vector();
               vExCuali = (Vector) AppCacheManager.getColl("GRLMotivo",request.getParameter("val1").toString());
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes + ".length=1;");

               if (vExCuali.size() > 0){
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vExCuali.size(); i++){
                           j = j + 1;
                     vMotivo = (TVMotivo) vExCuali.get(i);
                     out.println("i=form." + ObjetoDes + ".length + 1;");
                           out.println("form." + ObjetoDes + ".length=i;");
                           out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vMotivo.getCDscMotivo() + "\"" +";");
                     out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vMotivo.getICveMotivo() + "\"" +";");
                  }
                  out.println("if (form." + ObjetoDes2 + " ){");
                  out.println("  form." + ObjetoDes2 + ".length=1;");
                  out.println("  form." + ObjetoDes2 + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("  form." + ObjetoDes2 + "[0].value=" + "\"0\"" + ";");
                  out.println("}");
                  out.println("if (form." + ObjetoDes3 + " ){");
                  out.println("  form." + ObjetoDes3 + ".length=1;");
                  out.println("  form." + ObjetoDes3 + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("  form." + ObjetoDes3 + "[0].value=" + "\"0\"" + ";");
                  out.println("}");
               }else{
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  out.println("if (form." + ObjetoDes2 + " ){");
                  out.println("  form." + ObjetoDes2 + ".length=1;");
                  out.println("  form." + ObjetoDes2 + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                  out.println("  form." + ObjetoDes2 + "[0].value=" + "\"0\"" + ";");
                  out.println("}");
                  out.println("if (form." + ObjetoDes3 + " ){");
                  out.println("  form." + ObjetoDes3 + ".length=1;");
                  out.println("  form." + ObjetoDes3 + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                  out.println("  form." + ObjetoDes3 + "[0].value=" + "\"0\"" + ";");
                  out.println("}");

               }
           }
        break;
        }
        case 2:{
           if ((request.getParameter("val1")!=null) && (request.getParameter("val2")!=null)){
               TVMEDServicio vMEDServicio = new TVMEDServicio();
               Vector vExCuali = new Vector();
               vExCuali = (Vector) AppCacheManager.getColl("MEDServicio",request.getParameter("val1").toString() + "|" +
                                                                         request.getParameter("val2").toString() + "|");
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes + ".length=1;");
               if (vExCuali.size() > 0){
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vExCuali.size(); i++){
                           j = j + 1;
                     vMEDServicio = (TVMEDServicio) vExCuali.get(i);
                     out.println("i=form." + ObjetoDes + ".length + 1;");
                           out.println("form." + ObjetoDes + ".length=i;");
                           out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vMEDServicio.getCDscServicio() + "\"" +";");
                     out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vMEDServicio.getICveServicio() + "\"" +";");
                  }
                  out.println("if (form." + ObjetoDes2 + " ){");
                  out.println("  form." + ObjetoDes2 + ".length=1;");
                  out.println("  form." + ObjetoDes2 + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("  form." + ObjetoDes2 + "[0].value=" + "\"0\"" + ";");
                  out.println("}");
               }else{
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  out.println("if (form." + ObjetoDes2 + " ){");
                  out.println("  form." + ObjetoDes2 + ".length=1;");
                  out.println("  form." + ObjetoDes2 + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                  out.println("  form." + ObjetoDes2 + "[0].value=" + "\"0\"" + ";");
                  out.println("}");
               }
           }
        break;
        }
        case 3:{
           if ((request.getParameter("val1")!=null) && (request.getParameter("val2")!=null) &&
               (request.getParameter("val3")!=null)){
               TVMEDRama vMEDRama = new TVMEDRama();
               Vector vExCuali = new Vector();
               vExCuali = (Vector) AppCacheManager.getColl("MEDRama",request.getParameter("val1").toString() + "|");
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes + ".length=1;");
               if (vExCuali.size() > 0){
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vExCuali.size(); i++){
                           j = j + 1;
                     vMEDRama = (TVMEDRama) vExCuali.get(i);
                     out.println("i=form." + ObjetoDes + ".length + 1;");
                           out.println("form." + ObjetoDes + ".length=i;");
                           out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vMEDRama.getCDscRama() + "\"" +";");
                     out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vMEDRama.getICveRama() + "\"" +";");
                  }
               }else{
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               }
           }
        break;
        }
     }
  }
  out.print("</script>");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<head>
  <title><%= vEntorno.getTituloVentana() /* Obtiene el título de la ventana de los parámetros y lo muestra */ %></title>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabeceras(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<script language="JavaScript">
  window.defaultStatus = "<%= vParametros.getPropEspecifica("BarraEdo") %>";
</script>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdEstado" value="">
  <input type="hidden" name="hdAccion" value="">
  <input type="hidden" name="hdTitulo" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
      <TD class="ETituloTPag" align="center" width="100%"><%=cTitulo.toUpperCase()%></TD>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
