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
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
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
  int j = 0;
  if(cTitulo.compareTo("null") == 0)
     cTitulo = "";
  else
     cTitulo = vParametros.getPropEspecifica("NomModulo") + " " + cTitulo;

  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     Vector vcReactivo = new Vector();
     TDTOXReactivo dReactivo = new TDTOXReactivo();
     String cFiltro, cFiltro2 = "";
     cFiltro = request.getParameter("val1");
     cFiltro2 = request.getParameter("val2");
     TVTOXReactivo vReactivo = new TVTOXReactivo();
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:
                 if (cFiltro.compareTo("null") != 0 && cFiltro.compareTo("") != 0) {
                 cFiltro = " and R.iCveTpoReact = " + cFiltro;
                     if (cFiltro2.compareTo("null") != 0 && cFiltro2.compareTo("") != 0)
                       cFiltro += " and R.iCveLaboratorio = "+cFiltro2;
                 }
                 cFiltro +=  " order by R.iCodigo";
                 vcReactivo = dReactivo.FindByReactivos(cFiltro);
                 out.println("form." + ObjetoDes + ".length=1;");
                 if (vcReactivo.size() > 0){
                    out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                    out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vcReactivo.size(); i++){
                 j = j + 1;
                 vReactivo = (TVTOXReactivo)vcReactivo.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vReactivo.getICodigo() + " - " + vReactivo.getCDscBreve() + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vReactivo.getICveReactivo() + "\"" +";");
                 }
                }else{
                   out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                   out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                }
        break;
        case 2:
                 cFiltro = request.getParameter("val1");
                 cFiltro2 = request.getParameter("val2");
                 if (cFiltro.compareTo("null") != 0 && cFiltro.compareTo("") != 0) {
                 cFiltro = " where R.iCveTpoReact = " + cFiltro;
                     if (cFiltro2.compareTo("null") != 0 && cFiltro2.compareTo("") != 0)
                       cFiltro += " and R.iCveLaboratorio = "+cFiltro2;
                 }
                 cFiltro +=  " order by R.iCodigo";

                 vcReactivo = dReactivo.FindByReactivosNV(cFiltro);

                 out.println("form." + ObjetoDes + ".length=1;");
                 if (vcReactivo.size() > 0){
                    out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                    out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vcReactivo.size(); i++){
                 j = j + 1;
                 vReactivo = (TVTOXReactivo)vcReactivo.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vReactivo.getICodigo() + " - " + vReactivo.getCDscBreve() + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vReactivo.getICveReactivo() + "\"" +";");
                 }
                }else{
                   out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                   out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                }
        break;
     }
  }
  out.print("</script>");
%>

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
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
      <td class="ETituloTPag" align="center" width="100%"><%=cTitulo.toUpperCase()%></td>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
