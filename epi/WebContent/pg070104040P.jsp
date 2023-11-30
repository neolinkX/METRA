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
           // PROCESOS

           TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
           TVGRLUMUsuario vGRLUMUsuario = new TVGRLUMUsuario();
           Vector vcUMUsuario = new Vector();
           int iUsuario = Integer.parseInt(request.getParameter("val1"));
           int iUniMed = Integer.parseInt(request.getParameter("val2"));
           vcUMUsuario = dGRLUMUsuario.getProcesos(iUsuario,iUniMed);
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
       if (vcUMUsuario.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcUMUsuario.size(); i++){
              j = j + 1;
              vGRLUMUsuario = (TVGRLUMUsuario) vcUMUsuario.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLUMUsuario.getCDscProceso() + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLUMUsuario.getICveProceso()  + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }


         TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
         Vector vcPersonal;
         int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
         //int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("EMOProceso"));
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
           out.println("form.iCveUsuAplica.length=1;");
           out.println("form.iCveUsuAplica[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form.iCveUsuAplica[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcPersonal.size(); i++){
              j = j + 1;
              vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
              out.println("i=form.iCveUsuAplica.length + 1;");
              out.println("form.iCveUsuAplica.length=i;");
              out.println("form.iCveUsuAplica[" + j  + "].text=" + "\"" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "\"" +";");
              out.println("form.iCveUsuAplica[" + j + "].value="+ "\"" + vGRLUMUsuario.getICveUsuario()  + "\"" +";");
           }
        }else{
           out.println("form.iCveUsuAplica.length=1;");
           out.println("form.iCveUsuAplica[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form.iCveUsuAplica[0].value=" + "\"0\"" + ";");
        }


          /* TVGRLModulo vModulo= new TVGRLModulo();
           TDGRLModulo dModulo = new TDGRLModulo();
           Vector vcModulo= new Vector();
           vcModulo = dModulo.FindByAll(request.getParameter("val2").toString());

           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           j = 0;
           if (vcModulo.size() > 0){
           out.println("form.iCveModulo.length=1;");
           out.println("form.iCveModulo[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form.iCveModulo[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcModulo.size(); i++){
              j = j + 1;
              vModulo = (TVGRLModulo) vcModulo.get(i);
              out.println("i=form.iCveModulo.length + 1;");
              out.println("form.iCveModulo.length=i;");
              out.println("form.iCveModulo[" + j  + "].text=" + "\"" + vModulo.getCDscModulo()  + "\"" +";");
              out.println("form.iCveModulo[" + j + "].value="+ "\"" + vModulo.getICveModulo() + "\"" +";");
           }
        }else{
           out.println("form.iCveModulo.length=1;");
           out.println("form.iCveModulo[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form.iCveModulo[0].value=" + "\"0\"" + ";");
        }*/

        //Modulos
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
           // motivos
           TVMotivo vMotivo = new TVMotivo();
           Vector vcMotivo = new Vector();
           vcMotivo = (Vector) AppCacheManager.getColl("GRLMotivo",request.getParameter("val1").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcMotivo.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcMotivo.size(); i++){
              j = j + 1;
              vMotivo = (TVMotivo) vcMotivo.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vMotivo.getCDscMotivo()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vMotivo.getICveMotivo()  + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;

         case 3:
           // Modos de Transporte
           TDGRLProcesoMDOT dGRLProcesoMDOT = new TDGRLProcesoMDOT();
           TVGRLProcesoMDOT vGRLProcesoMDOT = new TVGRLProcesoMDOT();

           Vector vcGRLProcesoMDOT = new Vector();
           iUniMed = Integer.parseInt(request.getParameter("val1"));
           int iModulo = Integer.parseInt(request.getParameter("val2"));

           vcGRLProcesoMDOT = dGRLProcesoMDOT.FindMTXMod(" where iCveUniMed = " + iUniMed + " and iCveModulo = " + iModulo );

           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcGRLProcesoMDOT.size() > 0){
           out.println("form.iCveModTrans.length=1;");
           out.println("form.iCveModTrans[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form.iCveModTrans[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcGRLProcesoMDOT.size(); i++){
              j = j + 1;
              vGRLProcesoMDOT = (TVGRLProcesoMDOT) vcGRLProcesoMDOT.get(i);
              out.println("i=form.iCveModTrans.length + 1;");
              out.println("form.iCveModTrans.length=i;");
              out.println("form.iCveModTrans[" + j  + "].text=" + "\"" + vGRLProcesoMDOT.getCDscMdoTrans()  + "\"" +";");
              out.println("form.iCveModTrans[" + j + "].value="+ "\"" + vGRLProcesoMDOT.getICveMdoTrans()  + "\"" +";");
           }
        }else{
           out.println("form.iCveModTrans.length=1;");
           out.println("form.iCveModTrans[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form.iCveModTrans[0].value=" + "\"0\"" + ";");
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
  <input type="hidden" name="hdAccion" value="">
  <input type="hidden" name="hdTitulo" value="">
  <input type="hidden" name="hdEstado" value="">
  <input type="hidden" name="hdTitulo" value="<%if((""+request.getParameter("hdTitulo")).compareTo("null") != 0) out.print(request.getParameter("hdTitulo"));%>">
    <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
     <TD align="left" width="100%" class="ETituloTPag"><%=cTitulo.toUpperCase()%></TD>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
