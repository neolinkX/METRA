<%/**
 * Title:        Administraci�n y Seguridad
 * Description:  M�dulo de Administraci�n y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S. A. de C.V.
 * @author Generador de C�digo J2EE.
 * @version 1.0
 * Clase:        JSP para prueba de ingenier�a
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.seguridad.dao.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>


<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>


<html>
<%  // Unica Parte del C�digo que debe modificarse
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
 //System.out.print(cTitulo);

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
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:
           // ENVIOS
           TDEnvio dEnvio = new TDEnvio();
           TVEnvio vEnvio = new TVEnvio();
           Vector vcEnviosNE = new Vector();
           int iAnio = Integer.parseInt(request.getParameter("val1"));
           int iUniMed = Integer.parseInt(request.getParameter("val2"));
           vcEnviosNE = dEnvio.FindBydtEnvio(iAnio,iUniMed);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");

           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Env�o Nuevo" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");

           for (int i = 0; i < vcEnviosNE.size(); i++){
              j = j + 1;
              vEnvio = (TVEnvio) vcEnviosNE.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vEnvio.getICveEnvio() + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" +vEnvio.getICveEnvio() + "\"" +";");
           }
        break;
     }
  }
  out.print("</script>");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<head>
  <title><%= vEntorno.getTituloVentana() /* Obtiene el t�tulo de la ventana de los par�metros y lo muestra */ %></title>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabeceras(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<script language="JavaScript">
  window.defaultStatus = "<%= vParametros.getPropEspecifica("BarraEdo") %>";
</script>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdEstado" value="">
  <input type="hidden" name="hdTitulo" value="<%if((""+request.getParameter("hdTitulo")).compareTo("null") != 0) out.print(request.getParameter("hdTitulo"));%>">
  <input type="hidden" name="hdAccion" value="">
  <input type="hidden" name="hdTitulo" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
      <td class="ETituloTPag" align="center" width="100%"><%=cTitulo.toUpperCase()%></td>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la p�gina se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
