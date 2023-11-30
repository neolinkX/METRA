<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S. A. de C.V.
 * @author LCI. Oscar Castrejón Adame.
 * @version 1.0
 * Clase:        JSP para prueba de ingeniería
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<html>
<%  // Unica Parte del Código que debe modificarse
        pg0700007CFG  clsConfig = new pg0700007CFG();
%>
<script language="JavaScript">
  function fTitulo(cAccion){
    form = document.forms[0];
    form.hdAccion.value = cAccion;
    form.submit();
  }
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
  String ObjetoDes2 = "";
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
     if (request.getParameter("objDes2") != null)
        ObjetoDes2 = request.getParameter("objDes2");
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:{
           if (request.getParameter("val1")!=null){
               TDALMTpoArticulo DALMTpoArticulo = new TDALMTpoArticulo();
               TVALMTpoArticulo VALMTpoArticulo = new TVALMTpoArticulo();
               Vector vExCuali = new Vector();
               vExCuali = DALMTpoArticulo.FindByCustomWhere(" join ALMArea " +
                                                            " on ALMArea.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo " +
                                                            " and ALMArea.iCveAlmacen    = " + request.getParameter("val1") +
                                                            " where ALMTpoArticulo.lActivo = 1 " );
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes + ".length=1;");

               if (vExCuali.size() > 0){
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vExCuali.size(); i++){
                           j = j + 1;
                     VALMTpoArticulo = (TVALMTpoArticulo) vExCuali.get(i);
                     out.println("i=form." + ObjetoDes + ".length + 1;");
                           out.println("form." + ObjetoDes + ".length=i;");
                           out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + VALMTpoArticulo.getCDscBreve() + "\"" +";");
                     out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + VALMTpoArticulo.getICveTpoArticulo() + "\"" +";");
                  }
               }else{
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               }
           }
           //Cargando los Valores del Inventario.
           if (request.getParameter("val1")!=null){
               TDALMInventario DALMInventario = new TDALMInventario();
               TVALMInventario VALMInventario = new TVALMInventario();
               TFechas          Fecha          = new TFechas();
               Vector vExCuali = new Vector();
               vExCuali = DALMInventario.FindByAll(" where ALMInventario.iAnio       = " + Fecha.getIntYear(Fecha.TodaySQL()) +
                                              "   and ALMInventario.iCveAlmacen = " + request.getParameter("val1") +
                                              "   and ALMInventario.dtConteo        is not null " +
                                              "   and ALMInventario.dtActualizacion is null " +
                                              "");
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes2 + ".length=1;");

               if (vExCuali.size() > 0){
                  out.println("form." + ObjetoDes2 + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes2 + "[0].value=" + "\"0\"" + ";");
                  j = 0;
                  for (int i = 0; i < vExCuali.size(); i++){
                           j = j + 1;
                     VALMInventario = (TVALMInventario) vExCuali.get(i);
                     out.println("i=form." + ObjetoDes2 + ".length + 1;");
                           out.println("form." + ObjetoDes2 + ".length=i;");
                           out.println("form." + ObjetoDes2 + "[" + j  + "].text=" + "\"" + VALMInventario.getiCveInventario() + "\"" +";");
                     out.println("form." + ObjetoDes2 + "[" + j + "].value="+ "\"" + VALMInventario.getiCveInventario() + "\"" +";");
                  }
               }else{
                  out.println("form." + ObjetoDes2 + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes2 + "[0].value=" + "\"0\"" + ";");
               }
           }


        break;
        }

        case 2:{
           if (request.getParameter("val1")!=null && request.getParameter("val2")!=null ){
               TDALMFamilia DALMFamilia = new TDALMFamilia();
               TVALMFamilia VALMFamilia = new TVALMFamilia();
               Vector vExCuali = new Vector();
               vExCuali = DALMFamilia.FindByCustomWhere( " join ALMTpoArticulo " +
                                                         " on  ALMTpoArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo " +
                                                         " and ALMTpoArticulo.lActivo         = 1 " +
                                                         " join ALMArea " +
                                                         " on ALMArea.iCveTpoArticulo         = ALMTpoArticulo.iCveTpoArticulo " +
                                                         " and ALMArea.iCveAlmacen            = " + request.getParameter("val1") +
                                                         " and ALMArea.lActivo                = 1 " +
                                                         " where ALMFamilia.iCveTpoArticulo   = " + request.getParameter("val2") +
                                                         " and   ALMFamilia.lActivo           = 1 " +
                                                         " ORDER BY  ALMFamilia.cDscBreve ");
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes + ".length=1;");

               if (vExCuali.size() > 0){
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vExCuali.size(); i++){
                           j = j + 1;
                     VALMFamilia = (TVALMFamilia) vExCuali.get(i);
                     out.println("i=form." + ObjetoDes + ".length + 1;");
                           out.println("form." + ObjetoDes + ".length=i;");
                           out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + VALMFamilia.getcDscBreve() + "\"" +";");
                     out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + VALMFamilia.getiCveFamilia() + "\"" +";");
                  }
               }else{
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
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
