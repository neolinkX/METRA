<%/**
 * Title: pg070104003.jsp
 * Description:
 * Copyright:
 * Company:
 * @author AG SA
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {

            String paramName = (String) en.nextElement();
            //System.out.println(paramName + " = " + request.getParameter(paramName) + " ");

        }

        pg071003034CFG  clsConfig = new pg071003034CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg071003034.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg071003034.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg071003034.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar

  String cEstatusIR = "";            // modificar
  if(request.getParameter("hdBoton").compareTo("Guardar")==0)
    cEstatusIR   = "Imprimir";            // modificar
  else
    cEstatusIR   = "Reporte";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  TFechas dtFecha = new TFechas();
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
  
  String RutaFunc = ""+vParametros.getPropEspecifica("RutaFuncs");
  
%>
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070104001.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }


function fCargaM(){
    form = document.forms[0];
    form.target =  "FRMDatos";
    var txt;
    var img;
    if(confirm("¿Esta seguro que deseas ejecutar la carga?")){
    	txt = "Favor de ser paciente el proceso de carga se está realizando";
    	 var a="<%= vParametros.getPropEspecifica("RutaImg") %>loading2";
    	  var c=a+".gif";
    	  c="\""+c+"\"";
    	  img= "<img src="+c+"\/>";
    	form.hdType.value = "B";
        form.hdBoton.value ="Primero";
        form.hdRowNum.value = 0;
        form.submit();
    }else{
    	txt = "";
    }
    document.getElementById("boton").innerHTML = txt;
    document.getElementById("demo").innerHTML = img;
}




</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracterï¿½sticas generales de las pï¿½ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">

<!-- Efecto texto -->
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS")%>textualizer/project.css" type="text/css">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS")%>textualizer/pygments.css" type="text/css">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS")%>textualizer/textualizer.css" type="text/css">

  
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdType" value="">
  <input type="hidden" name="iCveServicio" value="2">
  <input type="hidden" name="lIniciado" value="1">
  <input type='hidden' name='hdBuscado' value='0'>
  <input type='hidden' name='hdUsuario' value='71'>
  <input type='hidden' name='iCveModulo' value='-1'>
  <input type='hidden' name='iCveUniMed' value='-1'>

<%  if (request.getParameter("hdICvePersonal") != null){
%>
       <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal").toString()%>">
<%
    }else{
%>
        <input type="hidden" name="hdICvePersonal" value="">
<%
    } 
%>
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <%
  String RESULTADOBORRADO ="";
  boolean respuesta = false;
  if(clsConfig.getAccesoValido()){
       TVPERDatos vPerDatos = null;
       if (request.getParameter("hdType") != null){
    	   //System.out.println("Op 1");
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
        	  //System.out.println("Op 2");
             //vPerDatos=clsConfig.findUser();
          }else{
              if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
            	  //System.out.println("Op 3");
                 //vPerDatos=clsConfig.findExpToEliminaBiometricoNAS();
              }
               if (request.getParameter("hdType").toString().equalsIgnoreCase("B")){
            	   respuesta = clsConfig.CargaModulosYServiciosAdmin();
                 if(respuesta==false){
                          RESULTADOBORRADO = "NO SE PUDO LIBERERAR LOS BIOMÉTRICOS";
                     }else{
                        RESULTADOBORRADO = "LOS BIOMÉTRICOS FUERON LIBERADOS";
                     }
              }
          }
       }


%>

  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
        <tr><td align="center" colspan="4" class="EResalta"></td></tr>
                            <tr>
                              <td colspan="5" class="ETablaT">CARGA MÓDULOS, SERVICIOS Y RAMAS A ADMINISTRADORES
                              </td>
                            </tr>

                              <%
                              out.print("<tr>");
                              out.print("<td colspan=\"5\"><center>");
                              out.print("<div id=\"boton\" align=\"center\">");
                              out.print(vEti.clsAnclaTexto("EAncla","Ejecutar proceso de carga","JavaScript: fCargaM();", "Buscar Expediente",""));
                              out.print("</div>");
                              out.print("</td>");
                              out.print("</tr>");
                              out.print("<tr>");
                              out.print("<td colspan=\"5\">");
                              out.print("<p style=\"color:#F00\" align=\"center\"> <b id=\"demo\"></b></p>");
                              out.print("</td>");
                              out.print("</tr>");
                              %>
                          </table>&nbsp;
                            <%
                      //  if(vPerDatos!=null){
                      //  }else{
//******************************************************************************
                          
//******************************************************************************
                          if (request.getParameter("hdType") != null && request.getParameter("hdType").length()>0){
                             //System.out.println("hdType2 ==>>"+request.getParameter("hdType"));
                          %>
                          <h3><%
                          if(respuesta){%>
                        	  <jsp:include  page = "textualizer.jsp" > 
    							<jsp:param  name = "nombreTextualizer"  value = "CargaModulosTrue" />
    							<jsp:param  name = "ruta"  value = "<%=RutaFunc%>" />     							
   							  </jsp:include>
                          <%}else{%>
                        	 <jsp:include  page = "textualizer.jsp" > 
    							<jsp:param  name = "nombreTextualizer"  value = "CargaModulosFalse" />
    							<jsp:param  name = "ruta"  value = "<%=RutaFunc%>" />     							
   							  </jsp:include>
                        	  <%}
                          //RESULTADOBORRADO%> </h3>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                          <%
                                out.println("<tr>");
                                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                out.println("</tr>");
                          %>
                                </table>
                          <%
                          }
                        //}
                        %>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
