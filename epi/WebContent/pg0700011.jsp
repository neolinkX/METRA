<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<% 	/**
	* Validacion de sesion iniciada
	* @author Ing. Andres Esteban Bernal Muñoz - 16/05/2014
	*/
      if(request.getSession(true).getAttribute("UsrID")==null){    	    
    	    response.sendRedirect("pg0700000.jsp");
    	    return;    	  
      } //Fin validacion %>
<%
    TEntorno vEntorno = new TEntorno();
    vEntorno.setNumModulo(07);
    TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
    vEntorno.setArchFuncs("FValida");
    vEntorno.setArchTooltips("07_Tooltips");
    vEntorno.clearListaImgs();
    vEntorno.setMetodoForm("POST");
    vEntorno.setActionForm("pg0700011.jsp");
    vEntorno.setOnLoad("fOnLoad();");
    vEntorno.setArchFCatalogo("FFiltro");

    String cRutaImg = vParametros.getPropEspecifica("RutaImg");
    String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
    String cRutaAyuda = vParametros.getPropEspecifica("html");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700011.js"></SCRIPT>
<script language="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cRutaFuncs = '<%=vParametros.getPropEspecifica("RutaFuncs")%>';
  var Style = '<%=vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>';
  fPag();
</script>
