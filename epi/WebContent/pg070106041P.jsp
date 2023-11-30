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
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:{
           // Llenado de Categorias
           TDGRLCategoria dCategoria = new TDGRLCategoria();
           TVGRLCategoria vCategoria = new TVGRLCategoria();
           Vector vcCategoria = new Vector();
           vcCategoria = dCategoria.FindByAll(" where GRLCategoria.iCveMdoTrans = " + request.getParameter("val1").toString() +
                                              "   and GRLCategoria.iCveCategoria not in " +
                                              " (select PERCatalogoNoAp.iCveCategoria   " +
                                              "  from PERCatalogoNoAp " +
                                              "  where PERCatalogoNoAp.iCvePersonal     = " + request.getParameter("iCvePersonal").toString() +
                                              "    and PERCatalogoNoAp.iCveMdoTrans     = " + request.getParameter("val1").toString() +
                                              "    and PERCatalogoNoAp.lVigente         = 1 )"
                                              ,"");
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcCategoria.size() > 0){
              j = 0;
              out.println("form.iCveCategoria.length=1;");
              out.println("form.iCveCategoria[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form.iCveCategoria[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < vcCategoria.size(); i++){
                 j = j + 1;
                 vCategoria = (TVGRLCategoria) vcCategoria.get(i);



/*FCSReq4 -Se puso mas abajo-
                 out.println("i=form.iCveCategoria.length + 1;");
                 out.println("form.iCveCategoria.length=i;");

                 out.println("form.iCveCategoria[" + j  + "].text=" + "\"" + vCategoria.getCDscCategoria()  + "\"" +";");
                 out.println("form.iCveCategoria[" + j + "].value="+ "\"" + vCategoria.getICveCategoria()  + "\"" +";");
 */

//=============================================================================================
//FCSReq4
                 if ( request.getParameter("val1").toString().equalsIgnoreCase("2") )  {
                    if ( vCategoria.getCDscCategoria().equalsIgnoreCase("UNICA") )  {
                    //Solo debe Mostrar la Opción "UNICA" en el Cbo Categoría, cuando se selecciono en el Cbo. Modo de Transporte "AUTOTRANSPORTE"
 
//                      //        System.out.println("\n********** val1: " +request.getParameter("val1").toString()+ " **********");
//                      //        System.out.println("\n********** CDscCategoria: " +vCategoria.getCDscCategoria()+ " **********");

                        out.println("i=form.iCveCategoria.length + 1;");
                        out.println("form.iCveCategoria.length=i;");
                        out.println("form.iCveCategoria[1].text=" + "\"" + vCategoria.getCDscCategoria()  + "\"" +";");
                        out.println("form.iCveCategoria[1].value="+ "\"" + vCategoria.getICveCategoria()  + "\"" +";");
                      }
                 }
                 else   {
                    //Muestra todas las demá opciones en el Cbo Categoría, de acuerdo a lo que selecciono en el Cbo "Modo de Transporte"

                   out.println("i=form.iCveCategoria.length + 1;");
                   out.println("form.iCveCategoria.length=i;");
                   out.println("form.iCveCategoria[" + j  + "].text=" + "\"" + vCategoria.getCDscCategoria()  + "\"" +";");
                   out.println("form.iCveCategoria[" + j + "].value="+ "\"" + vCategoria.getICveCategoria()  + "\"" +";");
                 }

//=============================================================================================
              }
           }else{
              out.println("form.iCveCategoria.length=1;");
              out.println("form.iCveCategoria[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
              out.println("form.iCveCategoria[0].value=" + "\"0\"" + ";");
           }
           break;
        }
        case 2:{
           // Llenado de Rubros de No Aptitud por Motivo.
           TDPERRubroNoAp DPERRubroNoAp = new TDPERRubroNoAp();
           TVPERRubroNoAp VPERRubroNoAp = new TVPERRubroNoAp();
           Vector vcPERRubroNoAp = new Vector();
           vcPERRubroNoAp = DPERRubroNoAp.FindByAll("where PERRubroNoAp.iCveMotivoNoAp = " + request.getParameter("val1").toString() +
                                                    "  and PERRubroNoAp.iCveRubroNoAp not in " +
                                                    "   (select PERCatMotRubNoAp.iCveRubroNoAp " +
                                                    "    from PERCatMotRubNoAp " +
                                                    "    where PERCatMotRubNoAp.iCvePersonal     = " + request.getParameter("iCvePersonal").toString() +
                                                    "      and PERCatMotRubNoAp.iCveMdoTrans     = " + request.getParameter("iCveMdoTrans").toString() +
                                                    "      and PERCatMotRubNoAp.iCveCategoria    = " + request.getParameter("iCveCategoria").toString() +
                                                    "      and PERCatMotRubNoAp.iCveCatalogoNoAp = " + request.getParameter("iCveCatalogoNoAp").toString() +
                                                    "      and PERCatMotRubNoAp.iCveMotivoNoAp   = " + request.getParameter("val1").toString() +
                                                    "   ) "+ 
                                                    "  and PERRubroNoAp.lActivo = 1");
           //" where iCveMotivoNoAp = " + request.getParameter("val1").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcPERRubroNoAp.size() > 0){
              j = 0;
              out.println("form.iCveRubroNoAp.length=1;");
              out.println("form.iCveRubroNoAp[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form.iCveRubroNoAp[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < vcPERRubroNoAp.size(); i++){
                j = j + 1;
                VPERRubroNoAp = (TVPERRubroNoAp) vcPERRubroNoAp.get(i);
                out.println("i=form.iCveRubroNoAp.length + 1;");
                out.println("form.iCveRubroNoAp.length=i;");
                out.println("form.iCveRubroNoAp[" + j  + "].text=" + "\"" + VPERRubroNoAp.getcDscRubroNoAp()  + "\"" +";");
                out.println("form.iCveRubroNoAp[" + j + "].value="+ "\"" + VPERRubroNoAp.getICveRubroNoAp()  + "\"" +";");
             }
           } else{
             out.println("form.iCveRubroNoAp.length=1;");
             out.println("form.iCveRubroNoAp[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
             out.println("form.iCveRubroNoAp[0].value=" + "\"0\"" + ";");
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
  <input type="hidden" name="iCvePersonal">
  <input type="hidden" name="hdAccion" value="">
  <input type="hidden" name="hdTitulo" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
     <TD class="ETituloTPag" ><%=cTitulo.toUpperCase()%></td>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
