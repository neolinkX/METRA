<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<% //Variables
           
           String          param=request.getParameter("param");
           String          RFCR=request.getParameter("RFCR");
           String          Paterno2R=request.getParameter("Paterno2R");
           String          Materno2R=request.getParameter("Materno2R");
           String          Nombre2R=request.getParameter("Nombre2R");
           String          SexoR=request.getParameter("SexoR");
           String          iCvePaisNacR=request.getParameter("iCvePaisNacR");
           String          iCveEstadoNacR=request.getParameter("iCveEstadoNacR");           
           String          iCvePaisDR=request.getParameter("iCvePaisDR");
           String          iCveEstadoDR=request.getParameter("iCveEstadoDR");
           String          valoren="valoren";
%>

<html>
    <head>
        
        
<script> 
    
function Validar(form){

var cFDI = formulario.cFDI.value ;

var cFDF = formulario.cFDF.value ;


if (formulario.cFDI.value == "")
{ alert("Por favor ingrese una fecha"); formulario.cFDI.focus(); return; }

if (formulario.cFDF.value == "")
{ alert("Por favor ingrese una fecha"); formulario.cFDF.focus(); return; }

formulario.submit();
}
</script>



<title>Alta Personal</title>  

<%
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  vEntorno.setNombrePagina("SEPer01.jsp");
  TEtiCampo vEti = new TEtiCampo();
  TSimpleCampo vSCampo = new TSimpleCampo();
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">

    </head>

<body bgcolor="" topmargin="0" leftmargin="0" onLoad="fOnLoad();">
    
    
    


<%
           String cPreDet = "";
           if((""+request.getParameter("hdPreDet")).compareTo("1")==0){
             cPreDet = "" + request.getParameter("iCveUniMed") + "|" + request.getParameter("iCveModulo") + "|";
             request.getSession(true).setAttribute("SelPer",cPreDet);
           }

           String cOmision1 = "", cOmision2="";
           if(request.getSession(true).getAttribute("SelPer")!=null){
             StringTokenizer stSelPer = new StringTokenizer(""+request.getSession(true).getAttribute("SelPer"),"|");
             cOmision1=stSelPer.nextToken();
             cOmision2=stSelPer.nextToken();
           }

           
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
         %>

<br>
<form name="formulario" method="post" action="SeperActualiza2.jsp">
    
    <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">

    
    <tr>
      <td class="ETablaT" colspan="2">Actualizar datos</td>
    </tr>

<tr><td class="EEtiqueta">Fecha de Dictamen</td>
<td class="ECampo">
<input type="text" size="13" maxlength="13" name="cFDI" 
value=""></td>
</tr>
<tr><td class="EEtiqueta">Fecha de Dictamen</td>
<td class="ECampo">
<input type="text" size="13" maxlength="13" name="cFDF" 
value=""></td>
</tr>



<tr>
    <td colspan="2" align="center"><input TYPE="button" name="Envio" value="Enviar" onClick="Validar(this.form)"></td></tr></table> 

</form>

<SCRIPT LANGUAGE="JavaScript">
     window.resizeTo(630,605);
</SCRIPT>        

</body>
</html>