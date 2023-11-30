<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.CaracteresEspeciales" %>
<% 	/**
	* Validacion de sesion iniciada
	* @author Ing. Andres Esteban Bernal Muñoz - 16/05/2014
	*/
      if(request.getSession(true).getAttribute("UsrID")==null){    	    
    	    response.sendRedirect("pg0700000.jsp");
    	    return;    	  
      } //Fin validacion %>
     
<%
  CaracteresEspeciales cE= new CaracteresEspeciales();
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setActionForm("pg0700007.jsp");

  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(1).toString();
  String cRutaAyuda = vParametros.getPropEspecifica("html");
  String cTitulo = "" + request.getParameter("hdTitulo");  
  cTitulo=cE.scanCompleto(cTitulo.toUpperCase());  
  if(cTitulo.compareTo("null") == 0) 
     cTitulo = "";
  else
     cTitulo = vParametros.getPropEspecifica("NomModulo") + " " + cTitulo;

  String cAccion = ""+request.getParameter("hdAccion");
  
  if(cAccion.compareTo("") != 0){
    try{
       HashMap hmPermisos = (HashMap) request.getSession(true).getAttribute("PermisosUsuario");
       if(hmPermisos != null && hmPermisos.containsKey(cAccion)){
          StringTokenizer stTitulo = new StringTokenizer(""+hmPermisos.get(cAccion),"|");
          cTitulo = stTitulo.nextToken();
       }
    }catch(Exception e){
    }
  }
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700007.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  var RutaFuncs = "<%=vParametros.getPropEspecifica("RutaFuncs")%>";
  var Style = '<%=vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>';
  var cAccion = '<%= request.getParameter("hdAccion")!=null ? request.getParameter("hdAccion") : vEntorno.getActionForm().substring(0,vEntorno.getActionForm().length()-5) + ".jsp" %>';
  var bkFondo = '<%=cRutaImg%>FondoStitulo<%=cTipoImg%>';
  var cTitulo = '<%=cTitulo.toUpperCase()%>';
  fPag();
</SCRIPT> 

