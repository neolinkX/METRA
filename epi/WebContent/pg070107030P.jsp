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
<%@ page import="com.micper.seguridad.dao.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>


<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.*"%>


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
     String cValor = request.getParameter("val2");
     String cValor2 = request.getParameter("val3");

     //System.out.println("val1 = "+request.getParameter("val1"));
     //System.out.println("val2 = "+request.getParameter("val2"));
     //System.out.println("val3 = "+request.getParameter("val3"));

     switch(Integer.parseInt(request.getParameter("opc"))) {
     case 1:
        // Llenado de Categorias
                TDGRLCategoria dCategoria = new TDGRLCategoria();
                TVGRLCategoria vCategoria = new TVGRLCategoria();
                Vector vcCategoria = new Vector();
                vcCategoria = dCategoria.FindByAll(request.getParameter("val1"));
                out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
                 if (vcCategoria.size() > 0){
                    j = 0;
                   out.println("form.iCveCategoria"+cValor+".length=1;");
                   out.println("form.iCveCategoria"+cValor+"[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                   out.println("form.iCveCategoria"+cValor+"[0].value=" + "\"0\"" + ";");
                   for (int i = 0; i < vcCategoria.size(); i++){
                      j = j + 1;
                      vCategoria = (TVGRLCategoria) vcCategoria.get(i);
                      out.println("i=form.iCveCategoria"+cValor+".length + 1;");
                      out.println("form.iCveCategoria"+cValor+".length=i;");
                      out.println("form.iCveCategoria"+cValor+"[" + j  + "].text=" + "\"" + vCategoria.getCDscCategoria()  + "\"" +";");
                      out.println("form.iCveCategoria"+cValor+"[" + j + "].value="+ "\"" + vCategoria.getICveCategoria()+"|"+vCategoria.getICveCategoria()+ "\"" +";");
                   }
                }else{
                   out.println("form.iCveCategoria"+cValor+".length=1;");
                   out.println("form.iCveCategoria"+cValor+"[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
                   out.println("form.iCveCategoria"+cValor+"[0].value=" + "\"0\"" + ";");
                }

      break;
        case 2:
                    TDGRLPuesto dPuesto = new TDGRLPuesto();
                    TVGRLPuesto vPuesto = new TVGRLPuesto();
                    Vector vcPuesto = new Vector();
                    String variable2 = "";
                    StringTokenizer stTitulo = new StringTokenizer(cValor2,"|");
                    variable2 = stTitulo.nextToken();
                    //System.out.println("variable2 = "+variable2);
                    vcPuesto = dPuesto.FindByAll(request.getParameter("val1"), variable2);
                    out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
                     if (vcPuesto.size() > 0){
                        j = 0;
                       out.println("form.iCvePuesto"+cValor+".length=1;");
                       out.println("form.iCvePuesto"+cValor+"[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                       out.println("form.iCvePuesto"+cValor+"[0].value=" + "\"0\"" + ";");
                       for (int i = 0; i < vcPuesto.size(); i++){
                          j = j + 1;
                          vPuesto = (TVGRLPuesto) vcPuesto.get(i);
                          out.println("i=form.iCvePuesto"+cValor+".length + 1;");
                          out.println("form.iCvePuesto"+cValor+".length=i;");
                          out.println("form.iCvePuesto"+cValor+"[" + j  + "].text=" + "\"" + vPuesto.getCDscPuesto()  + "\"" +";");
                          out.println("form.iCvePuesto"+cValor+"[" + j + "].value="+ "\"" + vPuesto.getICvePuesto()+"|"+vPuesto.getICveCategoria()+ "\"" +";");
                          //System.out.println(vPuesto.getCDscPuesto());
                       }
                    }else{
                       out.println("form.iCvePuesto"+cValor+".length=1;");
                       out.println("form.iCvePuesto"+cValor+"[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
                       out.println("form.iCvePuesto"+cValor+"[0].value=" + "\"0\"" + ";");
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
  <input type="hidden" name="iCvePersonal">
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
