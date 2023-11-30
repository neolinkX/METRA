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
        
<script LANGUAGE="JavaScript">
function enviar_parametro(valor){
var RFCR = formulario.RFC2.value;
var Paterno2R = formulario.Paterno2.value;
var Materno2R = formulario.Materno2.value;
var Nombre2R = formulario.Nombre2.value;
var SexoR = formulario.Sexo.value;
var iCvePaisNacR = formulario.iCvePaisNac.value;
var iCveEstadoNacR = formulario.iCveEstadoNac.value;
var iCvePaisDR = formulario.iCvePaisD.value;
var iCveEstadoDR = formulario.iCveEstadoD.value;

location = location.pathname + '?param=' + valor + '&RFCR=' + RFCR + '&Paterno2R=' + Paterno2R + '&Materno2R=' + Materno2R + '&Nombre2R=' + Nombre2R + '&SexoR=' + SexoR + '&iCveEstadoNacR='+iCveEstadoNacR+'&iCvePaisNacR=' + iCvePaisNacR + '&iCveEstadoDR='+iCveEstadoDR+'&iCvePaisDR=' + iCvePaisDR;
} 
</script>        
        

        
<script> 
    
function Validar(form){

var RFCC = formulario.RFC2.value ;
var año0 = RFCC.charAt(4);
var año1 = RFCC.charAt(5);
var mes0 = RFCC.charAt(6);
var mes1 = RFCC.charAt(7);
var dia0 = RFCC.charAt(8);
var dia1 = RFCC.charAt(9);
var año = año0 + año1;
var dia = dia0 + dia1;
var mes = mes0 + mes1;
año = parseInt(año);
dia = parseInt(dia);
mes = parseInt(mes);


if (formulario.RFC2.value == "")
{ alert("Por favor ingrese su RFC"); formulario.RFC2.focus(); return; }

if (formulario.RFC2.value.length < 10)
{ alert("Por favor ingrese correctamente su RFC"); formulario.RFC2.focus(); return; }

if (isNaN(año0))
 {alert("Por favor ingrese un numero en el año de su RFC"); formulario.RFC2.focus(); return; }

if (isNaN(año1))
 {alert("Por favor ingrese un numero en el año de su RFC"); formulario.RFC2.focus(); return; }

if (isNaN(mes0))
 {alert("Por favor ingrese un numero en el mes de su RFC"); formulario.RFC2.focus(); return; }
 
if (isNaN(mes1))
 {alert("Por favor ingrese un numero en el mes de su RFC"); formulario.RFC2.focus(); return; }
 
 if (isNaN(dia0))
 {alert("Por favor ingrese un numero en el dia de su RFC"); formulario.RFC2.focus(); return; }
  
 if (isNaN(dia1))
 {alert("Por favor ingrese un numero en el dia de su RFC"); formulario.RFC2.focus(); return; }
  
if (mes > "12")
{ alert("Por favor corrija el mes de la fecha de su RFC"); formulario.RFC2.focus(); return; }

if (dia > "31")
{ alert("Por favor corrija el dia de la fecha de su RFC"); formulario.RFC2.focus(); return; }

if (formulario.Paterno2.value == "")
{ alert("Por favor ingrese su Apellido Paterno"); formulario.Paterno2.focus(); return; }

if (formulario.Materno2.value == "")
{ alert("Por favor ingrese su Apellido Materno"); formulario.Materno2.focus(); return; }

if (formulario.Nombre2.value == "")
{ alert("Por favor ingrese su Nombre(s)"); formulario.Nombre2.focus(); return; }

if (formulario.Sexo.value == "S")
{ alert("Por favor seleccione su Sexo"); formulario.Sexo.focus(); return; } 

if (formulario.iCvePaisNac.selectedIndex==0)
{ alert("Por favor seleccione su País de nacimiento"); formulario.iCvePaisNac.focus(); return; } 

if (formulario.iCveEstadoNac.value == "0")
{ alert("Por favor seleccione su Lugar de nacimiento"); formulario.iCveEstadoNac.focus(); return; } 

if (formulario.iCvePaisD.selectedIndex==0)
{ alert("Por favor seleccione su País"); formulario.iCvePaisD.focus(); return; } 

if (formulario.iCveEstadoD.value == "0")
{ alert("Por favor seleccione su Estado"); formulario.iCveEstadoD.focus(); return; } 

if (formulario.iCveMunicipio.value == "0")
{ alert("Por favor seleccione su Municipio"); formulario.iCveMunicipio.focus(); return; } 

if (formulario.Calle.value == "")
{ alert("Por favor ingrese su Calle"); formulario.Calle.focus(); return; }

if (formulario.Colonia.value == "")
{ alert("Por favor ingrese su Colonia"); formulario.Colonia.focus(); return; } 

if (formulario.NoExt.value == "")
{ alert("Por favor ingrese su No. Ext."); formulario.NoExt.focus(); return; }

if (formulario.NoInt.value == "")
{ alert("Por favor ingrese su No. Int."); formulario.NoInt.focus(); return; }

if (formulario.CP.value == "")
{ alert("Por favor ingrese su C.P."); formulario.CP.focus(); return; }

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
<form name="formulario" method="post" action="SEPer022recibe.jsp">
    
    <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">

    
    <tr>
      <td class="ETablaT" colspan="2">Resultado</td>
    </tr>

<tr>
    <td colspan="2" align="center">    
    
<%
//Recibiendo dato de SEPer22recibe
String cFDI=request.getParameter("cFDI").toString();//recoge parametro y lo convierte a string
String cFDF=request.getParameter("cFDF").toString();//recoge parametro y lo convierte a string

System.out.println("La fecha de inicio |" +cFDI+ "| la fecha de fin|" + cFDF + "|");

String resultado;
TDPERDatosIn dPerDatos = new TDPERDatosIn();
//resultado = dPerDatos.actualizaDictamenes(cFDI,cFDF);
//System.out.println(resultado);
//out.write(resultado);
%>
</td></tr></table> 

</form>

<SCRIPT LANGUAGE="JavaScript">
     window.resizeTo(630,605);
</SCRIPT>        

</body>
</html>