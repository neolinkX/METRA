<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2004
 * Company:      Micros Personales S.A. de C.V.
 * @author LSC.  Marco Antonio Hernández García
 * @version      1.0
 * Clase:        pg070502035P
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
<%@ page import="java.text.*"%>



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
  function fTitulo(cAccion){
    form = document.forms[0];
    form.hdAccion.value = cAccion;
    form.submit();
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

  TDPais dPais = new TDPais();
  TVPais vPais = new TVPais();
  Vector VecPais = new Vector();
  VecPais = dPais.FindByAll();

  TDEntidadFed dEntidadFed = new TDEntidadFed();
  TVEntidadFed vEntidadFed = new TVEntidadFed();
  Vector VecEntidadFed = new Vector();

  TDMunicipio dMunicipio = new TDMunicipio();
  TVMunicipio vMunicipio = new TVMunicipio();
  Vector VecMunicipio = new Vector();

  String cWhere = "";
  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:
           TDPERDatos dPerDatos = new TDPERDatos();
           Vector vcPersonal = new Vector();
           vcPersonal = dPerDatos.FindByAll(request.getParameter("val1"));
           TVPERDatos vPERDatos = (TVPERDatos)vcPersonal.get(0);

           out.println("form.iNumPersonal.value="+vPERDatos.getICvePersonal()+";");
           out.println("form.iNumPersonal.readOnly = true;");

           out.println("form.iCveExpediente.value="+vPERDatos.getICveExpediente()+";");
           out.println("form.iCveExpediente.readOnly = true;");

           out.println("form.cNombre.value='"+vPERDatos.getCNombre()+"';");
           out.println("form.cNombre.readOnly = true;");

           out.println("form.cApPaterno.value='"+vPERDatos.getCApPaterno()+"';");
           out.println("form.cApPaterno.readOnly = true;");

           out.println("form.cApMaterno.value='"+vPERDatos.getCApMaterno()+"';");
           out.println("form.cApMaterno.readOnly = true;");

           out.println("form.cRFC.value='"+vPERDatos.getCRFC()+"';");

           out.println("form.cCURP.value='"+vPERDatos.getCCURP()+"';");
// dtNacimiento
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           TFechas tf = new TFechas();
           String fechaFormateada = "";
           if (vPERDatos.getDtNacimiento()!=null && vPERDatos.getDtNacimiento().toString().compareTo("")!=0){
              fechaFormateada = sdf.format(tf.getSQLDatefromSQLString(vPERDatos.getDtNacimiento().toString()));
           }
           out.println("form.dtNacimiento.value='"+fechaFormateada+"';");
// dtNacimiento

// iCvePaisNac
            out.println("form.iCvePaisNac.selectedIndex=" + vPERDatos.getICvePaisNac() + ";");
// iCvePaisNac

j = 0;
// iCveEstadoNac
           VecEntidadFed = dEntidadFed.FindByAll(" Where iCvePais = "+vPERDatos.getICvePaisNac());
           out.println("form.iCveEstadoNac.length=1;");
           if (VecEntidadFed.size() > 0){
              out.println("form.iCveEstadoNac[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form.iCveEstadoNac[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < VecEntidadFed.size(); i++){
                 j = j + 1;
                 vEntidadFed = (TVEntidadFed)VecEntidadFed.get(i);
                 out.println("i=form.iCveEstadoNac.length + 1;");
                 out.println("form.iCveEstadoNac.length=i;");
                 out.println("form.iCveEstadoNac[" + j  + "].text=" + "\"" + vEntidadFed.getCNombre() + "\"" +";");
                 out.println("form.iCveEstadoNac[" + j + "].value="+ "\"" + vEntidadFed.getICveEntidadFed() + "\"" +";");
                 if (vEntidadFed.getICveEntidadFed()==vPERDatos.getICveEstadoNac()){
                    out.println("form.iCveEstadoNac[" + j + "].selected=true;");
                 }
              }
           }else{
              out.println("form.iCveEstadoNac[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
              out.println("form.iCveEstadoNac[0].value=" + "\"0\"" + ";");
           }
// iCveEstadoNac

           out.println("form.cCalle.value='"+vPERDatos.getCCalle()+"';");
           out.println("form.cNumExt.value='"+vPERDatos.getCNumExt()+"';");
           out.println("form.cNumInt.value='"+vPERDatos.getCNumInt()+"';");
           out.println("form.cColonia.value='"+vPERDatos.getCColonia()+"';");
           out.println("form.iCP.value="+vPERDatos.getICP()+";");
           out.println("form.cCiudad.value='"+vPERDatos.getCCiudad()+"';");

j = 0;
// iCvePais
           out.println("form.iCvePais.selectedIndex=" + vPERDatos.getICvePais() + ";");
// iCvePais

j = 0;
// iCveEstado
           VecEntidadFed = dEntidadFed.FindByAll(" Where iCvePais = "+vPERDatos.getICvePais());
           out.println("form.iCveEstado.length=1;");
           if (VecEntidadFed.size() > 0){
              out.println("form.iCveEstado[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form.iCveEstado[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < VecEntidadFed.size(); i++){
                 j = j + 1;
                 vEntidadFed = (TVEntidadFed)VecEntidadFed.get(i);
                 out.println("i=form.iCveEstado.length + 1;");
                 out.println("form.iCveEstado.length=i;");
                 out.println("form.iCveEstado[" + j  + "].text=" + "\"" + vEntidadFed.getCNombre() + "\"" +";");
                 out.println("form.iCveEstado[" + j + "].value="+ "\"" + vEntidadFed.getICveEntidadFed() + "\"" +";");
                 if (vEntidadFed.getICveEntidadFed()==vPERDatos.getICveEstado()){
                    out.println("form.iCveEstado[" + j + "].selected=true;");
                 }
              }
           }else{
              out.println("form.iCveEstado[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
              out.println("form.iCveEstado[0].value=" + "\"0\"" + ";");
           }
// iCveEstado
j = 0;
// iCveMunicipio
           cWhere = " where iCvePais = " + vPERDatos.getICvePais() + " and iCveEntidadFed = " + vPERDatos.getICveEstado();
           VecMunicipio = dMunicipio.FindByAll(cWhere);
           out.println("form.iCveMunicipio.length=1;");
           if (VecMunicipio.size() > 0){
              out.println("form.iCveMunicipio[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form.iCveMunicipio[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < VecMunicipio.size(); i++){
                 j = j + 1;
                 vMunicipio = (TVMunicipio)VecMunicipio.get(i);
                 out.println("i=form.iCveMunicipio.length + 1;");
                 out.println("form.iCveMunicipio.length=i;");
                 out.println("form.iCveMunicipio[" + j  + "].text=" + "\"" + vMunicipio.getCNombre() + "\"" +";");
                 out.println("form.iCveMunicipio[" + j + "].value="+ "\"" + vMunicipio.getICveMunicipio() + "\"" +";");
                 if (vMunicipio.getICveMunicipio()==vPERDatos.getICveMunicipio()){
                    out.println("form.iCveMunicipio[" + j + "].selected=true;");
                 }
              }
           }else{
              out.println("form.iCveMunicipio[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
              out.println("form.iCveMunicipio[0].value=" + "\"0\"" + ";");
           }
// iCveMunicipio

           out.println("form.cTel.value='"+vPERDatos.getCTelefono()+"';");

        break;
        case 2:
           TDGRLPuesto dGRLPuesto = new TDGRLPuesto();
           TVGRLPuesto vGRLPuesto = new TVGRLPuesto();
           Vector vPuesto = new Vector();
           vPuesto = dGRLPuesto.FindByAll(request.getParameter("val1")+"","");
           out.println("form." + ObjetoDes + ".length=1;");
           if (vPuesto.size()>0){
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < vPuesto.size(); i++){
                 j = j + 1;
                 vGRLPuesto = (TVGRLPuesto)vPuesto.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vGRLPuesto.getCDscPuesto() + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vGRLPuesto.getICvePuesto() + "\"" +";");
              }
           }else{
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           }
        break;
        case 3:
           VecEntidadFed = dEntidadFed.FindByAll(" Where iCvePais = "+request.getParameter("val1"));
           out.println("form." + ObjetoDes + ".length=1;");
           if (VecEntidadFed.size()>0){
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < VecEntidadFed.size(); i++){
                 j = j + 1;
                 vEntidadFed = (TVEntidadFed)VecEntidadFed.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vEntidadFed.getCNombre() + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vEntidadFed.getICveEntidadFed() + "\"" +";");
              }
           }else{
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles..." + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           }
        break;
        case 4:
           cWhere = "";
           cWhere = " where iCvePais = " + request.getParameter("val1") + " and iCveEntidadFed = " + request.getParameter("val2");
           VecMunicipio = dMunicipio.FindByAll(cWhere);
           out.println("form." + ObjetoDes + ".length=1;");
           if (VecMunicipio.size()>0){
              out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < VecMunicipio.size(); i++){
                 j = j + 1;
                 vMunicipio = (TVMunicipio)VecMunicipio.get(i);
                 out.println("i=form." + ObjetoDes + ".length + 1;");
                 out.println("form." + ObjetoDes + ".length=i;");
                 out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vMunicipio.getCNombre() + "\"" +";");
                 out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vMunicipio.getICveMunicipio() + "\"" +";");
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
  <input type="hidden" name="hdTitulo" value="<%if((""+request.getParameter("hdTitulo")).compareTo("null") != 0) out.print(request.getParameter("hdTitulo"));%>">
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
