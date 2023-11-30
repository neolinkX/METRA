<%/**
 * Title: Página que obtiene los valores de los movimientos
 * Description: Consulta de citas
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Itzia Sánchez Méndez
 * @version 1
 * Clase: ?
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="gob.sct.ingresosws.ws.ConUsrIng.*"%>

<html>
<%

  pg070103014CFG clsConfig = new pg070103014CFG();                // modificar Ok
  String cMensaje = "";

  // Obtener los parámetros de la página
  try{
    TVDinRep02 vInfoIng = new TVDinRep02();
    if(request.getParameter("hdDatosAdicionales") != null && request.getParameter("hdDatosAdicionales").toString().length() > 0)
      vInfoIng.put("cInfoCat",request.getParameter("hdDatosAdicionales").toString().trim());

    if(request.getParameter("cRefNumerica") != null && request.getParameter("cRefNumerica").toString().trim().length() > 0)
      vInfoIng.put("cRefNumerica",request.getParameter("cRefNumerica").toString().trim());

    if(request.getParameter("cRefAlfaNum") != null && request.getParameter("cRefAlfaNum").toString().trim().length() > 0)
      vInfoIng.put("cRefAlfaNum",request.getParameter("cRefAlfaNum").toString().trim());

    if(vInfoIng.containsKey("cRefNumerica".toUpperCase()) && vInfoIng.containsKey("cRefAlfaNum".toUpperCase())){
        //System.out.println("Datos = " + vInfoIng.toHashMap().toString());
	 clsConfig.GuardarA(vInfoIng);
    }else
      cMensaje = "- No se generó el movimiento";
  }catch (Exception ex) {
    ex.printStackTrace();
  }

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103014.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103014.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();


%>
<head>
</head>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0">
</body>
<SCRIPT LANGUAGE="JavaScript">
  <%
   if(cMensaje.length() > 0 )
     out.println("alert('"+ cMensaje +"');");
  %>
  top.close();
</SCRIPT>
</html>
