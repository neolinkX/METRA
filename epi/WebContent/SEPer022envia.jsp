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
           String          CCURP=request.getParameter("CCURP");
           String          SexoR=request.getParameter("SexoR");
           String          iCvePaisNacR=request.getParameter("iCvePaisNacR");
           String          iCveEstadoNacR=request.getParameter("iCveEstadoNacR");
           String          Nombre2R=request.getParameter("Nombre2R");
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
var CCURP = formulario.CCURP.value;
var iCvePaisDR = formulario.iCvePaisD.value;
var iCveEstadoDR = formulario.iCveEstadoD.value;

location = location.pathname + '?param=' + valor + '&RFCR=' + RFCR + '&Paterno2R=' + Paterno2R + '&Materno2R=' + Materno2R + '&Nombre2R=' + Nombre2R + '&SexoR=' + SexoR + '&iCveEstadoNacR='+iCveEstadoNacR+'&iCvePaisNacR=' + iCvePaisNacR + '&CCURP='+CCURP+'&iCveEstadoDR='+iCveEstadoDR+'&iCvePaisDR=' + iCvePaisDR;
} 
</script>        
        

        
<script> 
    
function Validar(form){

var RFCC = formulario.RFC2.value ;
var ano0 = RFCC.charAt(4);
var ano1 = RFCC.charAt(5);
var mes0 = RFCC.charAt(6);
var mes1 = RFCC.charAt(7);
var dia0 = RFCC.charAt(8);
var dia1 = RFCC.charAt(9);
var anio = ano0 + ano1;
var dia = dia0 + dia1;
var mes = mes0 + mes1;
anio = parseInt(anio);
dia = parseInt(dia);
mes = parseInt(mes);

cVMsg = '';

if (formulario.RFC2.value == "")
{ //alert("Por favor ingrese su RFC"); formulario.RFC2.focus(); return;
	 cVMsg = cVMsg + "- Por favor ingrese su RFC \n";
}

if (formulario.RFC2.value.length < 10)
{ //alert("Por favor ingrese correctamente su RFC"); formulario.RFC2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese correctamente su RFC \n";
}

if (isNaN(ano0))
 {//alert("Por favor ingrese un numero en el año de su RFC"); formulario.RFC2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese un numero en el año de su RFC \n";
 }

if (isNaN(ano1))
 {//alert("Por favor ingrese un numero en el año de su RFC"); formulario.RFC2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese un numero en el año de su RFC \n";
 }

if (isNaN(mes0))
 {//alert("Por favor ingrese un numero en el mes de su RFC"); formulario.RFC2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese un numero en el mes de su RFC \n";
 }
 
if (isNaN(mes1))
 {//alert("Por favor ingrese un numero en el mes de su RFC"); formulario.RFC2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese un numero en el mes de su RFC \n";
 }
 
 if (isNaN(dia0))
 {//alert("Por favor ingrese un numero en el dia de su RFC"); formulario.RFC2.focus(); return; 
	 cVMsg = cVMsg + "- Por favor ingrese un numero en el dia de su RFC \n";
 }
   
 if (isNaN(dia1))
 {//alert("Por favor ingrese un numero en el dia de su RFC"); formulario.RFC2.focus(); return;
	 cVMsg = cVMsg + "- Por favor ingrese un numero en el dia de su RFC \n";
 }
  
if (mes > "12")
{ //alert("Por favor corrija el mes de la fecha de su RFC"); formulario.RFC2.focus(); return; 
	cVMsg = cVMsg + "- Por favor corrija el mes de la fecha de su RFC \n";
}

if (dia > "31")
{ //alert("Por favor corrija el dia de la fecha de su RFC"); formulario.RFC2.focus(); return; 
	cVMsg = cVMsg + "- Por favor corrija el dia de la fecha de su RFC \n";
}

if ( fComilla(formulario.RFC2.value) == true)
           cVMsg = cVMsg + "\n - El RFC es Incorrecto. \n";
           
           
if (formulario.Paterno2.value == "")
{ //alert("Por favor ingrese su Apellido Paterno"); formulario.Paterno2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su Apellido Paterno \n";
}

if ( fComilla(formulario.Paterno2.value) == true)
           cVMsg = cVMsg + "\n - El Apellido Paterno es Incorrecto. \n";
           
           
if (formulario.Materno2.value == "")
{ //alert("Por favor ingrese su Apellido Materno"); formulario.Materno2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su Apellido Materno \n";
}

if ( fComilla(formulario.Materno2.value) == true)
           cVMsg = cVMsg + "\n - El Apellido Materno es Incorrecto. \n";

        //Valida CURP
 if ( ! formulario.CCURP.value.match(/[a-zA-Z]{4,4}[0-9]{6}[a-zA-Z]{6,6}[0-9]{2}/) ) {
         cVMsg = cVMsg + "- El formato de CURP  es incorrecto. \n";
         }


if (formulario.Nombre2.value == "")
{ //alert("Por favor ingrese su Nombre(s)"); formulario.Nombre2.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su Nombre(s) \n";
}

if ( fComilla(formulario.Nombre2.value) == true)
           cVMsg = cVMsg + "\n - El Nombre es Incorrecto. \n";
           

if (formulario.Sexo.value == "S")
{ //alert("Por favor seleccione su Genero"); formulario.Sexo.focus(); return; 
	cVMsg = cVMsg + "- Por favor seleccione su Genero \n";
} 

if (formulario.iCvePaisNac.selectedIndex==0)
{ //alert("Por favor seleccione su Pais de nacimiento"); formulario.iCvePaisNac.focus(); return; 
	cVMsg = cVMsg + "- Por favor seleccione su Pais de nacimiento \n";
} 

if (formulario.iCveEstadoNac.value == "0")
{ //alert("Por favor seleccione su Lugar de nacimiento"); formulario.iCveEstadoNac.focus(); return; 
	cVMsg = cVMsg + "- Por favor seleccione su Lugar de nacimiento \n";
} 

if (formulario.iCvePaisD.selectedIndex==0)
{ //alert("Por favor seleccione su Pais"); formulario.iCvePaisD.focus(); return; 
	cVMsg = cVMsg + "- Por favor seleccione su Pais \n";
} 

if (formulario.iCveEstadoD.value == "0")
{ //alert("Por favor seleccione su Estado"); formulario.iCveEstadoD.focus(); return; 
	cVMsg = cVMsg + "- Por favor seleccione su Estado \n";
} 

if (formulario.iCveMunicipio.value == "0")
{ //alert("Por favor seleccione su Municipio"); formulario.iCveMunicipio.focus(); return; 
	cVMsg = cVMsg + "- Por favor seleccione su Municipio \n";
} 

if (formulario.Calle.value == "")
{ //alert("Por favor ingrese su Calle"); formulario.Calle.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su Calle \n";
}

if ( fComilla(formulario.Calle.value) == true)
           cVMsg = cVMsg + "\n - La Calle es Incorrecta. \n";

           
if (formulario.Colonia.value == "")
{ //alert("Por favor ingrese su Colonia"); formulario.Colonia.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su Colonia \n";
} 

if ( fComilla(formulario.Colonia.value) == true)
           cVMsg = cVMsg + "\n - La Colonia es Incorrecta. \n";

           
if (formulario.NoExt.value == "")
{ //alert("Por favor ingrese su No. Ext."); formulario.NoExt.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su No. Ext. \n";
}

if ( fComilla(formulario.NoExt.value) == true)
           cVMsg = cVMsg + "\n - El No. Ext. es Incorrecto. \n";


if (formulario.NoInt.value == "")
{ //alert("Por favor ingrese su No. Int."); formulario.NoInt.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su No. Int. \n";
}

if ( fComilla(formulario.NoInt.value) == true)
           cVMsg = cVMsg + "\n - El  No. Int. es Incorrecto. \n";


if (formulario.CP.value == "")
{ //alert("Por favor ingrese su C.P."); formulario.CP.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su C.P. \n";
}

if ( fComilla(formulario.CP.value) == true)
           cVMsg = cVMsg + "\n - El  C.P. es Incorrecto. \n";


if (formulario.CCiudad.value == "")
{ //alert("Por favor ingrese su C.P."); formulario.CP.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su Ciudad \n";
}

if ( fComilla(formulario.CCiudad.value) == true)
    cVMsg = cVMsg + "\n - La Ciudad es Incorrecta. \n";


if (formulario.CTel.value == "")
{ //alert("Por favor ingrese su C.P."); formulario.CP.focus(); return; 
	cVMsg = cVMsg + "- Por favor ingrese su Telefono \n";
}

if ( fComilla(formulario.CTel.value) == true)
    cVMsg = cVMsg + "\n - El Telefono presenta caracteres extraños. \n";


      if (cVMsg != ''){ 
          alert("Datos no Validos: \n" + cVMsg);
          return false;
        }
           
           
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
      <td class="ETablaT" colspan="2">Alta de Personal</td>
    </tr>
<%if (request.getParameter("RFCR")==null){
                    out.println("<tr><td class=\"EEtiqueta\">RFC</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"13\" maxlength=\"13\" name=\"RFC2\" ");
                    out.println("value=\"\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }
                    else{
                    out.println("<tr><td class=\"EEtiqueta\">RFC</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"13\" maxlength=\"13\" name=\"RFC2\" ");
                    out.println("value=\"");
                    out.println(RFCR);
                    out.println("\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }
 
  if (request.getParameter("Paterno2R")==null){
                    out.println("</tr><tr><td class=\"EEtiqueta\">Paterno</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"Paterno2\"");
                    out.println("value=\"\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }
                    else{
                    out.println("</tr><tr><td class=\"EEtiqueta\">Paterno</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"Paterno2\"");
                    out.println("value=\"");
                    out.println(Paterno2R);
                    out.println("\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }
 
  if (request.getParameter("Materno2R")==null){
                    out.println("</tr><tr><td class=\"EEtiqueta\">Materno</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"Materno2\"");
                    out.println("value=\"\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }
                    else{
                    out.println("</tr><tr><td class=\"EEtiqueta\">Materno</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"Materno2\"");
                    out.println("value=\"");
                    out.println(Materno2R);
                    out.println("\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }                    
 
  if (request.getParameter("Nombre2R")==null){
                    out.println("</tr><tr><td class=\"EEtiqueta\">Nombre</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"Nombre2\"");
                    out.println("value=\"\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }
                    else{
                    out.println("</tr><tr><td class=\"EEtiqueta\">Nombre</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"Nombre2\"");
                    out.println("value=\"");
                    out.println(Nombre2R);
                    out.println("\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\"></td>");
                    }                      
 
  if (request.getParameter("SexoR")==null){
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">Sexo:</span> </div>");
                    out.println("</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<select name=\"Sexo\">");
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"F\">Mujer</option>");
                    out.println("<option value=\"M\">Hombre</option>");
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");
                    }
                    else{
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">Sexo:</span> </div>");
                    out.println("</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<select name=\"Sexo\">");
                    String SEXORES = request.getParameter("SexoR");
                    String SEXOF = "F";
                    String SEXOM = "M";
                    String SEXOS = "S";
                    
                      if (SEXORES.equals(SEXOF)){
                    out.println("<option value=\"F\">Mujer</option>");
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"M\">Hombre</option>");
                    }
                    
                      if (SEXORES.equals(SEXOM)){
                    out.println("<option value=\"M\">Hombre</option>");
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"F\">Mujer</option>");
                    }
                    
                    if (SEXORES.equals(SEXOS)){
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"F\">Mujer</option>");
                    out.println("<option value=\"M\">Hombre</option>");
                    }
                    
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");
                    }                                           
                    
 
                                    TDPais dPaisNac = new TDPais();
                                    if (request.getParameter("iCvePaisNacR")==null && request.getParameter("iCveEstadoNacR")==null ){
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais de Nacimiento:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisNac","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                    out.println("</td>");
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDONAC (Lugar de Nacimiento):</span> </div>");
                                    out.println("</td>");
                                    out.println("<td class=\"ECampo\">");
                                    out.println("<select name=\"iCveEstadoNac\">");
                                    out.println("<option value=\"valoren\">Seleccione...</option>");
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    }
                                    
                                    if (request.getParameter("iCvePaisNacR")!=null && valoren.equalsIgnoreCase(iCveEstadoNacR.trim())){
                                    
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais de Nacimiento:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisNac","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisNacR, true));
                                    out.println("</td>");
                                    out.println("</td>");               
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDONAC (Lugar de Nacimiento):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println("<select name=\"iCveEstadoNac\">");
                                    
                                    // Estado
                                   TVEntidadFed vEntidadFed = new TVEntidadFed();
                                   TDEntidadFed dEntidadFed = new TDEntidadFed();
                                   Vector vcEntidadFed = new Vector();
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisNacR").toString());
                                       if (vcEntidadFed.size() > 0){
                                       out.println("<option value=\"0\">Seleccione...</option>");
                                       for (int i = 0; i < vcEntidadFed.size(); i++){
                                           int j = 0;
                                           j = j + 1;
                                          vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
                                         if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                             out.println("");}
                                         else{
                                             out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                                       }}

                                       }else{
                                       out.println("<option value=\"0\">Seleccione...</option>");
                                       }
                                   out.println("</SELECT>");
                                   out.println("</td>");
                                   out.println("</tr>");
                                    }
                                    
   
                                   if(request.getParameter("iCvePaisNacR")!=null && iCveEstadoNacR != null){
                                        if(!(valoren.equalsIgnoreCase(iCveEstadoNacR.trim()))){
                                   
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais de Nacimiento:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisNac","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisNacR, true));
                                    out.println("</td>");
                                    out.println("</td>");                                
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDONAC (Lugar de Nacimiento):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println("<select name=\"iCveEstadoNac\">");
                                    
                                    // Estado
                                   TVEntidadFed vEntidadFed = new TVEntidadFed();
                                   TDEntidadFed dEntidadFed = new TDEntidadFed();
                                   Vector vcEntidadFed = new Vector();
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisNacR").toString()+ " AND iCveEntidadFed = " + request.getParameter("iCveEstadoNacR").toString());
                                       if (vcEntidadFed.size() > 0){
                                       for (int i = 0; i < vcEntidadFed.size(); i++){
                                           int j = 0;
                                           j = j + 1;
                                          vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
                                        if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                             out.println("");}
                                         else{
                                             out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                                       }}
                                       }
                                   TVEntidadFed vEntidadFed2 = new TVEntidadFed();
                                   TDEntidadFed dEntidadFed2 = new TDEntidadFed();
                                   Vector vcEntidadFed2 = new Vector();
                                   vcEntidadFed2 = dEntidadFed2.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisNacR").toString());
                                       if (vcEntidadFed2.size() > 0){
                                       for (int i = 0; i < vcEntidadFed2.size(); i++){
                                           int j = 0;
                                           j = j + 1;
                                          vEntidadFed2 = (TVEntidadFed) vcEntidadFed2.get(i);
                                         if(vEntidadFed2.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                             out.println("");}
                                         else{
                                             out.println("<option value=\""+vEntidadFed2.getICveEntidadFed()+"\">"+vEntidadFed2.getCNombre()+"</option>");
                                       }}

                                       }else{
                                       out.println("<option value=\"0\">Seleccione...</option>");
                                       }
                                   out.println("</SELECT>");
                                   out.println("</td>");
                                   out.println("</tr>");
                                  }}


                                     if (request.getParameter("CCURP")==null){
                                            out.println("</tr><tr><td class=\"EEtiqueta\">CURP</td>");
                                            out.println("<td class=\"ECampo\">");
                                            out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"CCURP\"");
                                            out.println("value=\"\"");
                                            out.println("onfocus=\"this.select();\"");
                                            out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                            out.println("onBlur=\"fMayus(this);\"></td>");
                                            }
                                            else{
                                            out.println("</tr><tr><td class=\"EEtiqueta\">CURP</td>");
                                            out.println("<td class=\"ECampo\">");
                                            out.println("<input type=\"text\" size=\"35\" maxlength=\"35\" name=\"CCURP\"");
                                            out.println("value=\"");
                                            out.println(CCURP);
                                            out.println("\"");
                                            out.println("onfocus=\"this.select();\"");
                                            out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                            out.println("onBlur=\"fMayus(this);\"></td>");
                                      }

                                     //out.println("<tr>");
                                     //out.println("<td colspan=\"2\" bgcolor=\"#4275AD\" class=\"EEtiquetaL\"><FONT COLOR=\"white\"><div align=\"center\" class=\"style1\">DirecciÃ³n</div></FONT></td>");
                                     //out.println("</tr>");
 %>
                                    
                                     <tr>
      									<td class="ETablaT" colspan="2">Direcci&oacute;n</td>
      							    </tr>
<%
                                    
                                    
                                    if (request.getParameter("iCvePaisDR")==null && request.getParameter("iCveEstadoDR")==null ){
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                    out.println("</td>");
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class=\"ECampo\">");
                                    out.println("<select name=\"iCveEstadoD\">");
                                    out.println("<option value=\"valoren\">Seleccione...</option>");
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
                                    out.println("<option value=\"0\">Seleccione...</option>");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    }
                                    
                                    if (request.getParameter("iCvePaisDR")!=null && valoren.equalsIgnoreCase(iCveEstadoDR.trim())){
                                    
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
                                    out.println("</td>");
                                    out.println("</td>");                                   
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro(this.value);\">");
                                    
                                    // Estado
                                   TVEntidadFed vEntidadFed = new TVEntidadFed();
                                   TDEntidadFed dEntidadFed = new TDEntidadFed();
                                   Vector vcEntidadFed = new Vector();
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
                                       if (vcEntidadFed.size() > 0){
                                       out.println("<option value=\"0\">Seleccione...</option>");
                                       for (int i = 0; i < vcEntidadFed.size(); i++){
                                           int j = 0;
                                           j = j + 1;
                                          vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
                                          if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                             out.println("");}
                                         else{
                                             out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                                       }}

                                       }else{
                                       out.println("<option value=\"0\">Seleccione...</option>");
                                       }
                                   out.println("</SELECT>");
                                   out.println("</td>");
                                   out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                    out.println("<td class='ECampo'>");
                                    out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
                                    out.println("<option value=\"0\">Seleccione...</option>");
                                    out.println("</SELECT>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    }
                                    
   
                                   if(request.getParameter("iCvePaisDR")!=null && iCveEstadoDR != null){
                                        if(!(valoren.equalsIgnoreCase(iCveEstadoDR.trim()))){
                                   
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais:"));
                                    out.println("<td>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
                                    out.println("</td>");
                                    out.println("</td>");                                
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println("<select name=\"iCveEstadoD\" onChange=\"enviar_parametro(this.value);\">");
                                    
                                    // Estado
                                   TVEntidadFed vEntidadFed = new TVEntidadFed();
                                   TDEntidadFed dEntidadFed = new TDEntidadFed();
                                   Vector vcEntidadFed = new Vector();
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString()+ " AND iCveEntidadFed = " + request.getParameter("iCveEstadoDR").toString());
                                       if (vcEntidadFed.size() > 0){
                                       for (int i = 0; i < vcEntidadFed.size(); i++){
                                           int j = 0;
                                           j = j + 1;
                                          vEntidadFed = (TVEntidadFed) vcEntidadFed.get(i);
                                        if(vEntidadFed.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                             out.println("");}
                                         else{
                                             out.println("<option value=\""+vEntidadFed.getICveEntidadFed()+"\">"+vEntidadFed.getCNombre()+"</option>");
                                       }}
                                       }
                                   TVEntidadFed vEntidadFed2 = new TVEntidadFed();
                                   TDEntidadFed dEntidadFed2 = new TDEntidadFed();
                                   Vector vcEntidadFed2 = new Vector();
                                   vcEntidadFed2 = dEntidadFed2.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisDR").toString());
                                       if (vcEntidadFed2.size() > 0){
                                       for (int i = 0; i < vcEntidadFed2.size(); i++){
                                           int j = 0;
                                           j = j + 1;
                                          vEntidadFed2 = (TVEntidadFed) vcEntidadFed2.get(i);
                                        if(vEntidadFed2.getCNombre().equalsIgnoreCase("(DESCONOCIDO)")){
                                             out.println("");}
                                         else{
                                             out.println("<option value=\""+vEntidadFed2.getICveEntidadFed()+"\">"+vEntidadFed2.getCNombre()+"</option>");
                                       }}

                                       }else{
                                       out.println("<option value=\"0\">Seleccione...</option>");
                                       }
                                   out.println("</SELECT>");
                                   out.println("</td>");
                                   out.println("</tr>");
                                   
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                   out.println("<td class='ECampo'>");
                                   out.println("<SELECT NAME='iCveMunicipio' SIZE='1' ");
                                   TVMunicipio vMunicipio= new TVMunicipio();
                                       TDMunicipio dMunicipio = new TDMunicipio();
                                       Vector vcMunicipio= new Vector();
                                       vcMunicipio = dMunicipio.FindByAll(request.getParameter("iCvePaisDR").toString(),request.getParameter("iCveEstadoDR").toString());
                                       if (vcMunicipio.size() > 0){
                                       for (int i = 0; i < vcMunicipio.size(); i++){
                                          int j = 0;
                                          j = j + 1;
                                          vMunicipio = (TVMunicipio) vcMunicipio.get(i);
                                            out.println("<option value="+ vMunicipio.getICveMunicipio()+">"+vMunicipio.getCNombre()+"</option>");
                                           }
 
                                       }else{
                                       out.println("<option value=\"0\">Seleccione...</option>");}
                                   out.println("</SELECT>");
                                   out.println("</td>");
                                   out.println("</tr>");
                                  }}
                                  
                                   
                                                                        
   
                                        out.println("<tr><tr><td class=\"EEtiqueta\">Calle:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"60\" maxlength=\"60\" name=\"Calle\"");
                                        out.println("value=\"\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
   
                                                                                
                                        out.println("</tr><tr><td class=\"EEtiqueta\">Colonia:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"60\" maxlength=\"60\" name=\"Colonia\"");
                                        out.println("value=\"\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");
   
                                        out.println("</tr><tr><td class=\"EEtiqueta\">No. Ext.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"25\" maxlength=\"25\" name=\"NoExt\"");
                                        out.println("value=\"\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");
   
                                        out.println("<tr>");
                                        out.println("<td class=\"EEtiqueta\">No. Int.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"25\" maxlength=\"25\" name=\"NoInt\"");
                                        out.println("value=\"\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
 
                                        out.println("</tr>");
                                        out.println("<td class=\"EEtiqueta\">C.P.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"20\" maxlength=\"20\" name=\"CP\"");
                                        out.println("value=\"\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");             
                                        
                                        ////Nuevos campos agregados el dia 25/04/2013
                                        out.println("<tr>");
                                        out.println("<td class=\"EEtiqueta\">Población.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"50\" maxlength=\"50\" name=\"CCiudad\"");
                                        out.println("value=\"\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");
                                        
                                        out.println("<tr>");
                                        out.println("<td class=\"EEtiqueta\">Tel.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"20\" maxlength=\"20\" name=\"CTel\"");
                                        out.println("value=\"\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");      
                                           
                                    %>

<tr>
    <td colspan="2" align="center"><input TYPE="button" name="Envio" value="Enviar" onClick="Validar(this.form)"></td></tr></table>

</form>

<SCRIPT LANGUAGE="JavaScript">
     window.resizeTo(630,650);
</SCRIPT>        

</body>
</html>