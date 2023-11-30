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
  if(cTitulo.compareTo("null") == 0)
     cTitulo = "";
  else
     cTitulo = vParametros.getPropEspecifica("NomModulo") + " " + cTitulo;

  int j = 0;
  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:
           if (request.getParameter("val1")!=null){
               TDLoteCualita dLoteCualita = new TDLoteCualita();
               TVLoteCualita vLoteCualita = new TVLoteCualita();
               Vector vExCuali = new Vector();
               vLoteCualita.setIAnio(new Integer(request.getParameter("val1")).intValue());
               vLoteCualita.setICveLaboratorio(new Integer(request.getParameter("val2")).intValue());
               vExCuali = dLoteCualita.FindAllLoteCualita2(vLoteCualita);
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes + ".length=1;");

               if (vExCuali.size() > 0){
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vExCuali.size(); i++){
                           j = j + 1;
                     vLoteCualita = (TVLoteCualita)vExCuali.get(i);
                     out.println("i=form." + ObjetoDes + ".length + 1;");
                           out.println("form." + ObjetoDes + ".length=i;");
                           out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vLoteCualita.getICveLoteCualita() + "\"" +";");
                     out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vLoteCualita.getICveLoteCualita() + "\"" +";");
                  }
               }else{
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               }
           }
        break;
        case 2:
           if (request.getParameter("val1")!=null && request.getParameter("val2")!=null){
              TDTOXExamenCualita dExamenCualita = new TDTOXExamenCualita();
              TVTOXExamenCualita vExamenCualita = new TVTOXExamenCualita();
              Vector vExamCualita = new Vector();
              String cFiltro = "";
              String cOrden  = "";
              out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
              out.println("form." + ObjetoDes + ".length=1;");
              cFiltro = " WHERE TOXExamenCualita.iAnio = " + request.getParameter("val1") +
                        "   AND TOXExamenCualita.iCveLaboratorio = " + request.getParameter("val2") +
                        "   AND TOXExamenCualita.iCveLoteCualita = " + request.getParameter("val3") +
                        "   AND TOXExamenCualita.lAutorizado = 1";
              cOrden = " ORDER BY TOXExamenCualita.iCveExamCualita " ;
              vExamCualita = dExamenCualita.FindByAll(cFiltro,cOrden);
              if (vExamCualita.size() > 0){
                 out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                 out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                 for (int i = 0; i < vExamCualita.size(); i++){
                    j = j + 1;
                    vExamenCualita = (TVTOXExamenCualita)vExamCualita.get(i);
                    out.println("i=form." + ObjetoDes + ".length + 1;");
                    out.println("form." + ObjetoDes + ".length=i;");
                    out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vExamenCualita.getICveExamCualita() + "\"" +";");
                    out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vExamenCualita.getICveExamCualita() + "\"" +";");
                 }
              }else{
                 out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
                 out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
              }
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
