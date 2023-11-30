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
     int  iModulo;
     switch(new Integer(request.getParameter("opc")).intValue()) {
       case 4:
           // Modulos
         TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
         TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
         Vector vcModulo = new Vector();
         int  iUsuario, iUniMed;
         //iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
         iUsuario = Integer.parseInt(request.getParameter("val2"));
         iUniMed = Integer.parseInt(request.getParameter("val1"));
         String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + iUsuario +
                         " and GRLUsuMedicos.iCveUniMed =  " + iUniMed ;

         vcModulo = dGRLUSUMedicos.FindModulos(cFiltro);

           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcModulo.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcModulo.size(); i++){
              j = j + 1;
              vGRLUSUMedicos = (TVGRLUSUMedicos) vcModulo.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLUSUMedicos.getCDscModulo()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLUSUMedicos.getICveModulo() + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;

       case 5:
           // Servicios consulta

         iUsuario = Integer.parseInt(request.getParameter("val2"),10);
         iUniMed  = Integer.parseInt(request.getParameter("val1"),10);
         iModulo  = Integer.parseInt(request.getParameter("val3"),10);

         dGRLUSUMedicos = new TDGRLUSUMedicos();
         vcModulo = dGRLUSUMedicos.FindServConsulta(iUsuario, iUniMed, iModulo);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcModulo.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcModulo.size(); i++){
              j = j + 1;
              vGRLUSUMedicos = (TVGRLUSUMedicos) vcModulo.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLUSUMedicos.getCDscServicio()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLUSUMedicos.getICveServicio() + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;

       case 6:
           // Ramas
         Vector vRama = new Vector(), vServicio;
         TVMEDRama VRama;
         iModulo = 1;
         int  iServ = 1;
         iUsuario = Integer.parseInt(request.getParameter("val2").toString(),10);
         iUniMed  = Integer.parseInt(request.getParameter("val1").toString(),10);
         iModulo  = Integer.parseInt(request.getParameter("val3").toString(),10);
         iServ    = Integer.parseInt(request.getParameter("val4").toString(),10);
         // Buscar el servicio
         vServicio = new TDMEDServicio().FindByAll(" where iCveServicio = " + iServ);
         if(vServicio.size() > 0){
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if(( (TVMEDServicio)vServicio.get(0)).getLVariosMeds() ==  1){
             vRama = new TDGRLUSUMedicos().findRamas(iUsuario,iUniMed, 1, iModulo, iServ);
             if (vRama.size() > 0){
               out.println("form." + ObjetoDes + ".disabled=false;");
               out.println("form." + ObjetoDes + ".length=1;");
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               for (int i = 0; i < vRama.size(); i++){
                 j = j + 1;
                 VRama = (TVMEDRama) vRama.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + VRama.getCDscRama()  + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + VRama.getICveRama() + "\"" +";");
               }
             }else{
                out.println("form." + ObjetoDes + ".length=1;");
                out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
                out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
             }
           }else{
                out.println("form." + ObjetoDes + ".length=1;");
                out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                out.println("form." + ObjetoDes + ".disabled=true;");
           }
         }
        else{
                out.println("form." + ObjetoDes + ".length=1;");
                out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
                out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }
        break;
       case 7:
           // Servicios permiso
         iUsuario = Integer.parseInt(request.getParameter("val2"),10);
         iUniMed  = Integer.parseInt(request.getParameter("val1"),10);
         iModulo  = Integer.parseInt(request.getParameter("val3"),10);

         dGRLUSUMedicos = new TDGRLUSUMedicos();
         TVMEDServicio VServicio = new TVMEDServicio();
         vcModulo = dGRLUSUMedicos.findServXUsu(iUsuario, iUniMed, 1, iModulo);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcModulo.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcModulo.size(); i++){
              j = j + 1;
              VServicio = (TVMEDServicio) vcModulo.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + VServicio.getCDscServicio()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + VServicio.getICveServicio() + "\"" +";");
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
  <input type="hidden" name="iCvePersonal">
  <input type="hidden" name="hdAccion" value="">
  <input type="hidden" name="hdTitulo" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
      <td align="center" width="100%" valign="middle">
        <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%"  class="fondoSTitulo">
          <TR>
            <TD class="fondoSTitulo" align="left">&nbsp;&nbsp;&nbsp;<%=cTitulo.toUpperCase()%>
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
