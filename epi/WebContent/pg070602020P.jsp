<%/**
 * Title:        Sistema Integral de Protección y Medicina Preventiva en el Transporte.
 * Description:  Módulo de Calibración de Equipo.
 * Copyright:    Copyright (c) 2004
 * Company:      Micros Personales S.A. de C.V.
 * @author       Generador de Código J2EE.
 * @version 1.0
 * Clase:        JSP para llenado de Listas de selección en frame oculto
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
  String cFiltro = "";

  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     switch(new Integer(request.getParameter("opc")).intValue()) {
        case 1:
           TVGRLModulo vGRLModulo = new TVGRLModulo();
           Vector vModulo = new Vector();
           int iUniMed = new Integer(request.getParameter("val1")).intValue();
           int iUniMedOriginal = iUniMed;
           vModulo = (Vector) AppCacheManager.getColl("GRLModulo",iUniMed + "|");
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           out.println("form.iCveArea.length=1;");
           out.println("form.iCveArea[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form.iCveArea[0].value=" + "\"0\"" + ";");
           if (vModulo.size()>0){
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
              if (iUniMed == 0 || iUniMed == -1)
                out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              else
                out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           }
          // Cambia selects de usuarios
          TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
          Vector vcPersonal;
          int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
          if(request.getParameter("iCveUniMed") == null){
            vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
            if(vcPersonal.size() != 0){
              iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
            }
          }else{
            iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
          }
          if (iUniMedOriginal == 0 || iUniMedOriginal == -1){
            out.println("for(var z = 0;z < form.elements.length; z++) {");
            out.println("  if (form.elements[z].name.substring(0,13)  == 'iCveUsuAplica') {");
            out.println("    vObjeto = form.elements[z];");
            out.println("    vObjeto.length=1;");
            out.println("    vObjeto[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
            out.println("    vObjeto[0].value=" + "\"0\"" + ";");
            out.println("  }");
            out.println("  if (form.elements[z].name.substring(0,9)  == 'TBXAsigna') {");
            out.println("    vObjeto = form.elements[z];");
            out.println("    vObjeto.checked=false;");
            out.println("  }");
            out.println("}");
          }
          else{
            vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
            out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
            j = 0;
            if (vcPersonal.size() > 0){
              out.println("for(var z = 0;z < form.elements.length; z++) {");
              out.println("  if (form.elements[z].name.substring(0,9)  == 'TBXAsigna') {");
              out.println("    vObjeto = form.elements[z];");
              out.println("    vObjeto.checked=false;");
              out.println("  }");
              out.println("  if (form.elements[z].name.substring(0,13)  == 'iCveUsuAplica') {");
              out.println("    vObjeto = form.elements[z];");
              out.println("    vObjeto.length=1;");
              out.println("    vObjeto[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              out.println("    vObjeto[0].value=" + "\"0\"" + ";");
              for (int i = 0; i < vcPersonal.size(); i++){
                j = j + 1;
                vGRLUMUsuario = (TVGRLUMUsuario) vcPersonal.get(i);
                out.println("    i=vObjeto.length + 1;");
                out.println("    vObjeto.length=i;");
                out.println("    vObjeto[" + j  + "].text=" + "\"" + vGRLUMUsuario.getCNombre() + " " + vGRLUMUsuario.getCApPaterno() + " " + vGRLUMUsuario.getCApMaterno() + "\"" +";");
                out.println("    vObjeto[" + j + "].value="+ "\"" + vGRLUMUsuario.getICveUsuario()  + "\"" +";");
              }
              out.println("  }");
              out.println("}");
            }else{
              out.println("for(var z = 0;z < form.elements.length; z++) {");
              out.println("  if (form.elements[z].name.substring(0,13)  == 'iCveUsuAplica') {");
              out.println("    vObjeto = form.elements[z];");
              out.println("    vObjeto.length=1;");
              out.println("    vObjeto[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
              out.println("    vObjeto[0].value=" + "\"0\"" + ";");
              out.println("  }");
              out.println("}");
            }
          }
        break;
        case 2:
           DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
           TVGRLAreaModulo  vGRLAreaModulo = new TVGRLAreaModulo();
           Vector vAreaModulo = new Vector();
           int iUnidMed = 0;
           int iModulo  = 0;
           if (request.getParameter("val1")!=null && request.getParameter("val2")!=null){
             iUnidMed = new Integer(request.getParameter("val1")).intValue();
             iModulo  = new Integer(request.getParameter("val2")).intValue();
             cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + request.getParameter("val1") +
                          "  AND GRLAreaModulo.iCveModulo = " + request.getParameter("val2") + " ";
           }
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
              if (iUnidMed == 0 || iUnidMed == -1 || iModulo == 0 || iModulo == -1)
                out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
              else
                out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
              out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           }
        break;
        case 3:
           TDEQMTpoEquipo dTipo = new TDEQMTpoEquipo();
           Vector vTipo = new Vector();
           TVEQMTpoEquipo tvTipo = new TVEQMTpoEquipo();
           int iClasif = new Integer(request.getParameter("val1")).intValue();
           vTipo = dTipo.FindByAll(" where lActivo=1 and iCveClasificacion=" + iClasif + " ", " order by iCveTpoEquipo ");

           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vTipo.size() > 0) {
               out.println("form." + ObjetoDes + ".length=1;");
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Todos..." + "\"" + ";");
               out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
               for(int i = 0; i < vTipo.size(); i++) {
                   j = j + 1;
                   tvTipo = (TVEQMTpoEquipo) vTipo.get(i);
                   out.println("i=form." + ObjetoDes + ".length + 1;");
                   out.println("form." + ObjetoDes + ".length=i;");
                   out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + tvTipo.getCDscBreve() + "\"" +";");
                   out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + tvTipo.getICveTpoEquipo() + "\"" +";");
               }
           } else {
             out.println("form." + ObjetoDes + ".length=1;");
             if (iClasif == 0 || iClasif == -1)
               out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Todos..." + "\"" + ";");
             else
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
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" background="<%= cRutaImg %>fondoSTitulo<%= cTipoImg %>">
    <tr>
      <TD class="ETituloTPag" align="center">&nbsp;&nbsp;&nbsp;<%=cTitulo.toUpperCase()%>
      </TD>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la página se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
