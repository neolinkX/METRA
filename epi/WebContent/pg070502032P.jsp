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
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
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
  TFechas Fecha = new TFechas();
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
           if (request.getParameter("val1")!=null){
               TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
               TVCTRPeriodPla VCTRPeriodPla = new TVCTRPeriodPla();
               Vector vPeriodPla = new Vector();
               vPeriodPla = DCTRPeriodPla.FindByAll(" where iAnio         = " + request.getParameter("val1") +
                                                    "   and lActivo = 1 ");
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               out.println("form." + ObjetoDes + ".length=1;");
               if (vPeriodPla.size() > 0){
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
                  for (int i = 0; i < vPeriodPla.size(); i++){
                           j = j + 1;
                     VCTRPeriodPla = (TVCTRPeriodPla) vPeriodPla.get(i);
                     out.println("i=form." + ObjetoDes + ".length + 1;");
                     out.println("form." + ObjetoDes + ".length=i;");
                     out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + VCTRPeriodPla.getiCvePeriodPla() + " - " + VCTRPeriodPla.getcDscPeriodPla() + "\"" +";");
                     out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + VCTRPeriodPla.getiCvePeriodPla() + "\"" +";");
                  }
               }else{
                  out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                  out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               }
           }
        break;
        }
        case 2:{
           if (request.getParameter("val1")!=null && request.getParameter("val2")!=null){
               TDCTRPeriodPla DCTRPeriodPla = new TDCTRPeriodPla();
               TVCTRPeriodPla VCTRPeriodPla = new TVCTRPeriodPla();
               Vector vPeriodPla = new Vector();
               vPeriodPla = DCTRPeriodPla.FindByAll(" where iAnio         = " + request.getParameter("val1") +
                                                    "   and iCvePeriodPla = " + request.getParameter("val2") +
                                                    "   and lActivo = 1 ");
               out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
               if (vPeriodPla.size() > 0){
                  for (int i = 0; i < vPeriodPla.size(); i++){
                     VCTRPeriodPla = (TVCTRPeriodPla) vPeriodPla.get(i);
                     out.println("form." + ObjetoDes + ".value="+ "\"" + Fecha.getFechaDDMMYYYY(VCTRPeriodPla.getdtVencimiento(),"/") + "\"" +";");
                  }
               }else{
                  out.println("form." + ObjetoDes + ".value=" + "\"0\"" + ";");
               }
           }
        break;
        }

        case 3:{
           TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
           TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
           //Vector vcUMUsuario = new Vector();
           int iUsuario = Integer.parseInt(request.getParameter("val1"));
           int iUniMed = Integer.parseInt(request.getParameter("val2"));
           //vcUMUsuario = dGRLUMUsuario.getProcesos(iUsuario,iUniMed);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
           Vector vcPersonal;
           int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
           if(request.getParameter("iCveUniMed") == null){
              vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
              if(vcPersonal.size() != 0){
                iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
              }
           }else{
             iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
           }
           vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           j = 0;
           if (vcPersonal.size() > 0){
             out.println("form.SLSUsrRecibe.length=1;");
             out.println("form.SLSUsrRecibe[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
             out.println("form.SLSUsrRecibe[0].value=" + "\"0\"" + ";");
             for (int i = 0; i < vcPersonal.size(); i++){
                j = j + 1;
                vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
                out.println("i=form.SLSUsrRecibe.length + 1;");
                out.println("form.SLSUsrRecibe.length=i;");
                out.println("form.SLSUsrRecibe[" + j  + "].text=" + "\"" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "\"" +";");
                out.println("form.SLSUsrRecibe[" + j + "].value="+ "\"" + vGRLUMUsuario.getICveUsuario()  + "\"" +";");
             }
           }else{
           out.println("form.SLSUsrRecibe.length=1;");
           out.println("form.SLSUsrRecibe[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form.SLSUsrRecibe[0].value=" + "\"0\"" + ";");
        }
        break;
       }

        case 4:{
           TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
           TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
           //Vector vcUMUsuario = new Vector();
           int iUsuario = Integer.parseInt(request.getParameter("val1"));
           int iUniMed = Integer.parseInt(request.getParameter("val2"));
           //vcUMUsuario = dGRLUMUsuario.getProcesos(iUsuario,iUniMed);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
           Vector vcPersonal;
           int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
           if(request.getParameter("iCveUniMed") == null){
              vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
              if(vcPersonal.size() != 0){
                iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
              }
           }else{
             iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
           }
           vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           j = 0;
           if (vcPersonal.size() > 0){
             out.println("form.SLSUsrSolicita.length=1;");
             out.println("form.SLSUsrSolicita[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
             out.println("form.SLSUsrSolicita[0].value=" + "\"0\"" + ";");
             for (int i = 0; i < vcPersonal.size(); i++){
                j = j + 1;
                vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
                out.println("i=form.SLSUsrSolicita.length + 1;");
                out.println("form.SLSUsrSolicita.length=i;");
                out.println("form.SLSUsrSolicita[" + j  + "].text=" + "\"" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "\"" +";");
                out.println("form.SLSUsrSolicita[" + j + "].value="+ "\"" + vGRLUMUsuario.getICveUsuario()  + "\"" +";");
             }
           }else{
           out.println("form.SLSUsrSolicita.length=1;");
           out.println("form.SLSUsrSolicita[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form.SLSUsrSolicita[0].value=" + "\"0\"" + ";");
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
      <TD class="ETituloTPag" align="center" width="100%" valign="middle"><%=cTitulo.toUpperCase()%></td>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
