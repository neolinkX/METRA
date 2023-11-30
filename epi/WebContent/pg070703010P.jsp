<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S. A. de C.V.
 * @author       LSC. Marco Antonio Hernández García
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
  TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
  TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();

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

             //Modulos

         int iUsuario = Integer.parseInt(request.getParameter("val1"));
         int iUniMed = Integer.parseInt(request.getParameter("val2"));
         TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
         TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
         Vector vcModulo = new Vector();
         //iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
         iUsuario = Integer.parseInt(request.getParameter("val1"));
         iUniMed = Integer.parseInt(request.getParameter("val2"));
         String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + iUsuario +
                         " and GRLUsuMedicos.iCveUniMed =  " + iUniMed ;

         vcModulo = dGRLUSUMedicos.FindModulos(cFiltro);
         //vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
         out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
       j = 0;
       if (vcModulo.size() > 0){
           out.println("form.iCveModulo.length=1;");
           out.println("form.iCveModulo[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form.iCveModulo[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcModulo.size(); i++){
              j = j + 1;
              vGRLUSUMedicos = (TVGRLUSUMedicos) vcModulo.get(i);
              out.println("i=form.iCveModulo.length + 1;");
              out.println("form.iCveModulo.length=i;");
              out.println("form.iCveModulo[" + j  + "].text=" + "\"" + vGRLUSUMedicos.getCDscModulo() + "\"" +";");
              out.println("form.iCveModulo[" + j + "].value="+ "\"" + vGRLUSUMedicos.getICveModulo()  + "\"" +";");
           }
        }else{
           out.println("form.iCveModulo.length=1;");
           out.println("form.iCveModulo[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form.iCveModulo[0].value=" + "\"0\"" + ";");
        }

        break;
        case 2:
              Vector vArea = new Vector();
              //String
              cFiltro  = "";
              DAOGRLAreaModulo dAreaModulo = new DAOGRLAreaModulo();
              TVGRLAreaModulo vAreaModulo = new  TVGRLAreaModulo();
              cFiltro = " WHERE UA.iCveUniMed  = " + request.getParameter("val1") +
                        "   AND UA.iCveModulo  = " + request.getParameter("val2") +
                        "   AND UA.iCveUsuario = " + request.getParameter("val3") +
                        " ORDER BY A.cDscArea ";
              vArea = dAreaModulo.FindPermUsuario(cFiltro);
              out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
              if (vArea.size()>0){
                 out.println("form." + ObjetoDes + ".length=1;");
                 out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                 out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                 for (int i = 0; i < vArea.size(); i++){
                    j = j + 1;
                    vAreaModulo = (TVGRLAreaModulo)vArea.get(i);
                    out.println("i=form." + ObjetoDes + ".length + 1;");
                    out.println("form." + ObjetoDes + ".length=i;");
                    out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vAreaModulo.getCDscArea() + "\"" +";");
                    out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vAreaModulo.getICveArea() + "\"" +";");


                 }
              }else{
                 out.println("form." + ObjetoDes + ".length=1;");
                 out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
                 out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");



              }

         break;

        case 3:
              Vector vUsuarios = new Vector();
              //String
              cFiltro  = "";
              TDGRLUsuArea dGRLUsuArea = new TDGRLUsuArea();
              TVGRLUsuArea vGRLUsuArea = new  TVGRLUsuArea();
              cFiltro = " where GRLUsuArea.iCveUniMed = " + request.getParameter("val1") +
                        " and GRLUsuArea.iCveModulo = " + request.getParameter("val2") +
                        " and GRLUsuArea.iCveArea = " + request.getParameter("val3") +
                        " and UM.iCveProceso =  " +  vParametros.getPropEspecifica("CtrlVeh") +
                        " ORDER BY cNombre, cApPaterno, cApMaterno "; //CtrlVeh
              vUsuarios = dGRLUsuArea.FindByAll(cFiltro);
              out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
              if (vUsuarios.size()>0){
                 out.println("form." + ObjetoDes + ".length=1;");
                 out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                 out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");

                 out.println("form.iCveUsuConductor.length=1;");
                 out.println("form.iCveUsuConductor[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                 out.println("form.iCveUsuConductor[0].value=" + "\"0\"" + ";");

                 for (int i = 0; i < vUsuarios.size(); i++){
                    j = j + 1;
                    vGRLUsuArea = (TVGRLUsuArea) vUsuarios.get(i);
                    out.println("i=form." + ObjetoDes + ".length + 1;");
                    out.println("form." + ObjetoDes + ".length=i;");
                    out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLUsuArea.getCNombreCompleto() + "\"" +";");
                    out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLUsuArea.getICveUsuario() + "\"" +";");

                    out.println("i=form.iCveUsuConductor.length + 1;");
                    out.println("form.iCveUsuConductor.length=i;");
                    out.println("form.iCveUsuConductor[" + j  + "].text=" + "\"" + vGRLUsuArea.getCNombreCompleto() + "\"" +";");
                    out.println("form.iCveUsuConductor[" + j + "].value="+ "\"" + vGRLUsuArea.getICveUsuario() + "\"" +";");

                 }
              }else{
                 out.println("form." + ObjetoDes + ".length=1;");
                 out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
                 out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");

                 out.println("form.iCveUsuConductor.length=1;");
                 out.println("form.iCveUsuConductor[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
                 out.println("form.iCveUsuConductor[0].value=" + "\"0\"" + ";");

              }

        break;
     }
  }
  out.print("</script>");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700007.js"></SCRIPT>
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
  <input type="hidden" name="hdAccion" value="<%= request.getParameter("hdAccion")!=null ? request.getParameter("hdAccion") : ""%>">
  <input type="hidden" name="hdTitulo" value="">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
      <td align="center" width="100%" valign="middle">
        <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%"  >
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
