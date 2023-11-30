<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S.A. de C.V.
 * @author       Leonel Popoca G.
 * @version 1.0
 * Clase:        JSP para prueba de ingeniería
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.util.caching.AppCacheManager"%>
<%@ page import="com.micper.seguridad.vo.*"%>

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

  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7

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
  String cFiltro = "";

  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:
           j = 0;
           TVGRLModulo vGRLModulo = new TVGRLModulo();
           Vector vModulo = new Vector();
           int iUniMed = new Integer(request.getParameter("val1")).intValue();
           vModulo = (Vector) AppCacheManager.getColl("GRLModulo",iUniMed + "|");
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (!vModulo.isEmpty()){
              out.println("form." + ObjetoDes + ".length=1;");
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < vModulo.size(); i++){
                 j = j + 1;
                 vGRLModulo = (TVGRLModulo)vModulo.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLModulo.getCDscModulo() + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLModulo.getICveModulo() + "\"" +";");
              }
           }else{
              out.println("form." + ObjetoDes + ".length=1;");
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           }

           j = 0;
           TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
           TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
           Vector vcPersonal = new Vector();
           vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso, Integer.parseInt(request.getParameter("val1"), 10));
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcPersonal.size()>0){
              out.println("form.iCveUsuSolic.length=1;");
              out.println("form.iCveUsuSolic[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form.iCveUsuSolic[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < vcPersonal.size(); i++){
                 j = j + 1;
                 vGRLUMUsuario = (TVGRLUMUsuario)vcPersonal.get(i);
                 out.println("i=form.iCveUsuSolic.length + 1;");
                 out.println("form.iCveUsuSolic.length=i;");
                 out.println("form.iCveUsuSolic[" + j  + "].text=" + "\"" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "\"" +";");
                 out.println("form.iCveUsuSolic[" + j + "].value="+ "\"" + vGRLUMUsuario.getICveUsuario() + "\"" +";");
              }
           }else{
              out.println("form.iCveUsuSolic.length=1;");
              out.println("form.iCveUsuSolic[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
              out.println("form.iCveUsuSolic[0].value=" + "\"0\"" + ";");
           }
        break;
        case 2:
           DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
           TVGRLAreaModulo  vGRLAreaModulo = new TVGRLAreaModulo();
           Vector vAreaModulo = new Vector();
           if (request.getParameter("val1")!=null && request.getParameter("val2")!=null)
               cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + request.getParameter("val1") +
                          "  AND GRLAreaModulo.iCveModulo = " + request.getParameter("val2") + " ";
           vAreaModulo = dGRLAreaModulo.FindByAll(cFiltro);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vAreaModulo.size()>0){
              out.println("form." + ObjetoDes + ".length=1;");
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < vAreaModulo.size(); i++){
                 j = j + 1;
                 vGRLAreaModulo = (TVGRLAreaModulo)vAreaModulo.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLAreaModulo.getCDscArea() + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLAreaModulo.getICveArea() + "\"" +";");
              }
           }else{
              out.println("form." + ObjetoDes + ".length=1;");
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           }
        break;
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
      <td align="center" width="100%" valign="middle">
        <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%"  class="ETablaST">
          <TR>
            <TD class="ETituloTPag" align="left">&nbsp;&nbsp;&nbsp;<%=cTitulo.toUpperCase()%>
            </TD>
          </TR>
        </TABLE>
      </td>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
