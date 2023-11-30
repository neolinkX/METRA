<%/**
 * Title:        Administración y Seguridad
 * Description:  Módulo de Administración y Seguridad
 * Copyright:    Copyright (c) 2003
 * Company:      Micros Personales S.A. de C.V.
 * @author       LSC. Jaime Enrique Suárez Romero
 * @version      1.0
 * Clase:        JSP del Encabezado y control del menú, frames y páginas del Sistema
 */ %>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="gob.sct.medprev.CaracteresEspeciales" %>
<html>
<%
  CaracteresEspeciales cE= new CaracteresEspeciales();
  pg0700001CFG  clsConfig = new pg0700001CFG();
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg0700001.jsp");
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  Vector vBtnPrin = new Vector();
  Vector vUrlPrin = new Vector();
  Vector vEstPrin = new Vector();
  int    iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  clsConfig.runConfig(vEntorno, vBtnPrin, vUrlPrin, vEstPrin, vParametros);
  clsConfig.outputHeader(vEntorno, vErrores, pageContext, request, response);
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cValorSubmit = "";
  String cValorAyuda = "";
  String cValorBoton = "";
  int acceso = 0;
  if (request.getParameter("hdSubmitido") != null)
    if (request.getParameter("hdSubmitido").compareTo("Si") == 0)
      cValorSubmit = "Si";
  if (request.getParameter("hdAyuda") != null){	  	  
	  cValorAyuda = cE.scanCompleto(request.getParameter("hdAyuda")); //Scan no XSS	  
    }
  

  if(request.getParameter("hdBoton")!=null){
      if (request.getParameter("hdBoton").compareTo("Bloqueado") == 0){
          acceso = 1;
          cValorBoton = request.getParameter("hdBoton");
      }
  }
      
%>

<SCRIPT LANGUAGE="JavaScript">
  var cPagina        = '<%=vEntorno.getNumModuloStr()%>';
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cRutaAyuda     = '<%= vParametros.getPropEspecifica("html")%>';
  var cExtAyuda      = '<%= vParametros.getPropEspecifica("TipoAyuda")%>';
  window.defaultStatus = "<%= vParametros.getPropEspecifica("BarraEdo") %>";
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs") + "Ayuda.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="<%=vParametros.getPropEspecifica("RutaFuncs")%>t07_Tooltips.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdAyuda" value="<%= cValorAyuda %>">
  <input type="hidden" name="hdSubmitido" value="<%= cValorSubmit %>">
  <input type="hidden" name="hdBoton" value="<%= cValorBoton %>">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" class="ETablaGral" background=" <%= cRutaImg %>fondoTab<%= cTipoImg %>">
    <tr>
      <td width="100%" height="100%" valign="middle">
         <% 
        if (acceso == 1)
            new TTitulo2(pageContext, vEntorno, vBtnPrin, iNumRowsPrin, vUrlPrin, vEstPrin, vEntorno.getBtnPrincVisible(), vParametros); /* Se encarga de desplegar el logo de empresa y botones de acción generales */ 
        else
            new TTitulo(pageContext, vEntorno, vBtnPrin, iNumRowsPrin, vUrlPrin, vEstPrin, vEntorno.getBtnPrincVisible(), vParametros); /* Se encarga de desplegar el logo de empresa y botones de acción generales */ 
        %>
      </td>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs(); %>
</html>
 