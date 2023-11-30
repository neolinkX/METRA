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
       //System.out.print("pg0700007.jsp:");
       e.printStackTrace();
    }
  }

  int j = 0;
  out.println("<script LANGUAGE=" + "\""  + "JavaScript"  + "\""  + ">");
  if (request.getParameter("opc") != null){
     String ObjetoDes = request.getParameter("objDes");
     switch(new Integer(request.getParameter("opc")).intValue()) {

      case 7:
           // Puesto
           TVGRLPuesto vPuestoN = new TVGRLPuesto();
           TDGRLPuesto dPuestoN = new TDGRLPuesto();
           Vector vcPuestoN = new Vector();
           vcPuestoN = dPuestoN.FindByAll2(request.getParameter("val1").toString(),request.getParameter("val2").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcPuestoN.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcPuestoN.size(); i++){
              j = j + 1;
              vPuestoN = (TVGRLPuesto) vcPuestoN.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vPuestoN.getCDscPuesto()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vPuestoN.getICvePuesto()  + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }



   break;

        case 6:
           // Puesto
           TVGRLPuesto vPuesto = new TVGRLPuesto();
           TDGRLPuesto dPuesto = new TDGRLPuesto();
           Vector vcPuesto = new Vector();
           vcPuesto = dPuesto.FindByAll(request.getParameter("val1").toString(),request.getParameter("val2").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcPuesto.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcPuesto.size(); i++){
              j = j + 1;
              vPuesto = (TVGRLPuesto) vcPuesto.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vPuesto.getCDscPuesto()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vPuesto.getICvePuesto()  + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }


  // Código de Verificación al Catálogo de No Aptos

        TDPERTpoLicencia dPERTpoLicencia = new TDPERTpoLicencia();
        TVPERTpoLicencia vPERTpoLicencia = new TVPERTpoLicencia();
        Vector vcLicencia = new Vector();
        String cCondicion;
        cCondicion = "where iCvePersonal = " + request.getParameter("val3").toString() + " and iCveMdoTrans = " + request.getParameter("val1").toString() + " ";
         cCondicion =  cCondicion + "and iCveCategoria = " + request.getParameter("val2").toString() +  " " ;
        vcLicencia = dPERTpoLicencia.FindByPersonaMdoTrans(cCondicion);
        if (! vcLicencia.isEmpty()){
           String cCondicionNoApto;
           String cValor;
           Vector vcNoApto = new Vector();
           vPERTpoLicencia = (TVPERTpoLicencia) vcLicencia.get(0);
           TDPERCatalogoNoAp dCatNoAp = new TDPERCatalogoNoAp();
           cCondicionNoApto = "where iCvePersonal = " + vPERTpoLicencia.getICvePersonal() + " and iCveMdoTrans = " + vPERTpoLicencia.getICveMdoTrans() + " ";
           cCondicionNoApto = cCondicionNoApto + " and iCveCategoria = " + vPERTpoLicencia.getICveCategoria();
           vcNoApto = dCatNoAp.FindByAll(cCondicionNoApto);
           if ( vcNoApto.size() > 0){

            cValor = vParametros.getPropEspecifica("MotReexpedicion");
            out.println("var cEtiqueta;");
            out.println("for(i=0" + ";i<form.iCve.length;i++){");
            out.println("if(form.iCveMotivo[i].value == "+ cValor +"){");
            out.println("form.iCveMotivo.selectedIndex = i;");
            out.println("cEtiqueta = form.iCveMotivo[i].text ;");
            out.println("form.iCveMotivo.length = 1;");
            out.println("form.iCveMotivo[0].text = cEtiqueta;");
            out.println("form.iCveMotivo[0].value = "+ cValor +";");
            out.println(" } ");
            out.println(" } ");
           }
           else{
              j = 0;
              Vector vcMotivo = new Vector();
              TVMotivo vMotivo = new TVMotivo();
              DAOGRLMotivo dMotivo = new DAOGRLMotivo();
              vcMotivo = dMotivo.FindByProceso(1);

              if (vcMotivo.size() > 0){
                out.println("form.iCveMotivo.length=1;");
                out.println("form.iCveMotivo[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
                out.println("form.iCveMotivo[0].value=" + "\"0\"" + ";");
                for (int i = 0; i < vcMotivo.size(); i++){
                  j = j + 1;
                  vMotivo = (TVMotivo) vcMotivo.get(i);
                  out.println("i=form.iCveMotivo.length + 1;");
                  out.println("form.iCveMotivo.length=i;");
                  out.println("form.iCveMotivo[" + j  + "].text=" + "\"" + vMotivo.getCDscMotivo()  + "\"" +";");
                  out.println("form.iCveMotivo[" + j + "].value="+ "\"" + vMotivo.getICveMotivo()  + "\"" +";");
                }
              }
              else{
               out.println("form.iCveMotivo.length=1;");
               out.println("form.iCveMotivo[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
               out.println("form.iCveMotivo[0].value=" + "\"0\"" + ";");
              }
           }

        }

   break;

  case 1:
        // Llenado de Categorias
        TDGRLCategoria dCategoria = new TDGRLCategoria();
        TVGRLCategoria vCategoria = new TVGRLCategoria();
        Vector vcCategoria = new Vector();
        String AutotransporteVal = request.getParameter("val1").toString();
        int numeroAuto=Integer.parseInt(AutotransporteVal.trim());
        if(numeroAuto == 2){
                String whereCatgoria="";
                whereCatgoria = request.getParameter("val1").toString() + " and iCveCategoria = 7";
                vcCategoria = dCategoria.FindByAll(whereCatgoria);
        }
        else{
                vcCategoria = dCategoria.FindByAll(request.getParameter("val1").toString());
        }
        out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
         if (vcCategoria.size() > 0){
            j = 0;
           out.println("form.iCveCategoria.length=1;");
           out.println("form.iCveCategoria[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form.iCveCategoria[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcCategoria.size(); i++){
              j = j + 1;
              vCategoria = (TVGRLCategoria) vcCategoria.get(i);
              out.println("i=form.iCveCategoria.length + 1;");
              out.println("form.iCveCategoria.length=i;");
              out.println("form.iCveCategoria[" + j  + "].text=" + "\"" + vCategoria.getCDscCategoria()  + "\"" +";");
              out.println("form.iCveCategoria[" + j + "].value="+ "\"" + vCategoria.getICveCategoria()  + "\"" +";");
           }
        }else{
           out.println("form.iCveCategoria.length=1;");
           out.println("form.iCveCategoria[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form.iCveCategoria[0].value=" + "\"0\"" + ";");
        }



        break;
      case 2:
           // Estado
           TVEntidadFed vEntidadFed = new TVEntidadFed();
           TDEntidadFed dEntidadFed = new TDEntidadFed();
           Vector vcEntidadFed = new Vector();
           vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("val1").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           //System.out.println(vcEntidadFed.size());
           if (vcEntidadFed.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcEntidadFed.size(); i++){
              j = j + 1;
              vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vEntidadFed.getCNombre()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vEntidadFed.getICveEntidadFed()  + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;

      case 3:
           // Municipio
           TVMunicipio vMunicipio= new TVMunicipio();
           TDMunicipio dMunicipio = new TDMunicipio();
           Vector vcMunicipio= new Vector();
           vcMunicipio = dMunicipio.FindByAll(request.getParameter("val1").toString(),request.getParameter("val2").toString());
           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcMunicipio.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcMunicipio.size(); i++){
              j = j + 1;
              vMunicipio = (TVMunicipio) vcMunicipio.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vMunicipio.getCNombre()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vMunicipio.getICveMunicipio() + "\"" +";");
           }
        }else{
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Datos no disponibles" + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
        }

        break;

       case 4:
           // Modulos
           TVGRLModulo vModulo= new TVGRLModulo();
           TDGRLModulo dModulo = new TDGRLModulo();
           Vector vcModulo= new Vector();
           vcModulo = dModulo.FindByAll(request.getParameter("val1").toString());

           out.println("form = window.parent.parent.FRMCuerpo.FRMDatos.document.forms[0];");
           if (vcModulo.size() > 0){
           out.println("form." + ObjetoDes + ".length=1;");
           out.println("form." + ObjetoDes + "[0].text=" + "\"" + "Seleccione..." + "\"" + ";");
           out.println("form." + ObjetoDes + "[0].value=" + "\"0\"" + ";");
           for (int i = 0; i < vcModulo.size(); i++){
              j = j + 1;
              vModulo = (TVGRLModulo) vcModulo.get(i);
              out.println("i=form." + ObjetoDes + ".length + 1;");
              out.println("form." + ObjetoDes + ".length=i;");
              out.println("form." + ObjetoDes + "[" + j  + "].text=" + "\"" + vModulo.getCDscModulo()  + "\"" +";");
              out.println("form." + ObjetoDes + "[" + j + "].value="+ "\"" + vModulo.getICveModulo() + "\"" +";");
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
