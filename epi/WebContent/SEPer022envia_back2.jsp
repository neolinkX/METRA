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
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="java.lang.*" %>



<% //Variables
           
           String          param=request.getParameter("param");
           String          RFCR=request.getParameter("RFC2");
           String          Paterno2R=request.getParameter("Paterno2");
           String          Materno2R=request.getParameter("Materno2");
           String          Nombre2R=request.getParameter("Nombre2");
           String          SexoR=request.getParameter("SexoR");
           String          iCvePaisNacR=request.getParameter("iCvePaisNac");
           String          iCveEstadoNacR=request.getParameter("iCveEstadoNac");           
           String          iCvePaisDR=request.getParameter("iCvePaisD");
           String          iCveEstadoDR=request.getParameter("iCveEstadoD");
           String          valoren="valoren";
           String          icvejunimed=request.getParameter("icvejunimed");
%>

<html>
    <head>
        
<script LANGUAGE="JavaScript">


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

if (isNaN(formulario.NoExt.value ))
{ alert("Por favor ingrese solo números  en su No. Ext."); formulario.NoExt.focus(); return; }

if (formulario.NoInt.value == "")
{ alert("Por favor ingrese su No. Int."); formulario.NoInt.focus(); return; }

if (isNaN(formulario.NoInt.value ))
{ alert("Por favor ingrese solo números en su No. Int."); formulario.NoInt.focus(); return; }

if (formulario.CP.value == "")
{ alert("Por favor ingrese su C.P."); formulario.CP.focus(); return; }

if (isNaN(formulario.CP.value))
{ alert("Por favor ingrese solo números en su C.P."); formulario.CP.focus(); return; }

formulario.action = "SEPer022recibe.jsp";
formulario.method = "Post";
//formulario.submit();

formulario.submit();
}




function enviar_parametro(valor){
formulario.action = "SEPer022envia.jsp";
formulario.method = "Post";
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
<!--
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></SCRIPT>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">

    </head>

<body bgcolor="" topmargin="0" leftmargin="0" >
    
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

    <input type="hidden" name="icvejunimed" value="<%=icvejunimed%>">

    <tr>
      <td class="ETablaT" colspan="2">Alta de Personal</td>
    </tr>
<%if (request.getParameter("RFC2")==null){
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
 
  if (request.getParameter("Paterno2")==null){
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
 
  if (request.getParameter("Materno2")==null){
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
 
  if (request.getParameter("Nombre2")==null){
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
 
  if (request.getParameter("Sexo")==null){
                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">Sexo:</span> </div>");
                    out.println("</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<select name=\"Sexo\">");
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"F\">F</option>");
                    out.println("<option value=\"M\">M</option>");
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
                    String SEXORES = request.getParameter("Sexo");
                    String SEXOF = "F";
                    String SEXOM = "M";
                    String SEXOS = "S";
                    
                      if (SEXORES.equals(SEXOF)){
                    out.println("<option value=\"F\">F</option>");
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"M\">M</option>");
                    }
                    
                      if (SEXORES.equals(SEXOM)){
                    out.println("<option value=\"M\">M</option>");
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"F\">F</option>");
                    }
                    
                    if (SEXORES.equals(SEXOS)){
                    out.println("<option value=\"S\">Seleccione...</option>");
                    out.println("<option value=\"F\">F</option>");
                    out.println("<option value=\"M\">M</option>");
                    }
                    
                    out.println("</select>");
                    out.println("</td>");
                    out.println("</tr>");
                    }                                           
                    
 
                                    TDPais dPaisNac = new TDPais();
                                    if (request.getParameter("iCvePaisNac")==null && request.getParameter("iCveEstadoNac")==null ){
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
                               
                                    if (request.getParameter("iCvePaisNac")!=null && valoren.equalsIgnoreCase(iCveEstadoNacR.trim())){
                                    
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
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisNac").toString());
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
                                    
   
                                   if(request.getParameter("iCvePaisNac")!=null && iCveEstadoNacR != null){
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
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisNac").toString()+ " AND iCveEntidadFed = " + request.getParameter("iCveEstadoNac").toString());
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
                                   vcEntidadFed2 = dEntidadFed2.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisNac").toString());
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
 
 
                                    
                                    out.println("<tr>");
                                    out.println("<td colspan=\"2\" bgcolor=\"#4275AD\" class=\"EEtiquetaL\"><FONT COLOR=\"white\"><div align=\"center\" class=\"style1\">Dirección</div></FONT></td>");
                                    out.println("</tr>");

                                    
                                    
                                    if (request.getParameter("iCvePaisD")==null && request.getParameter("iCveEstadoD")==null ){
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
                                    
                                    if (request.getParameter("iCvePaisD")!=null && valoren.equalsIgnoreCase(iCveEstadoDR.trim())){
                                    
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
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisD").toString());
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
                                    
   
                                   if(request.getParameter("iCvePaisD")!=null && iCveEstadoDR != null){
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
                                   vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisD").toString()+ " AND iCveEntidadFed = " + request.getParameter("iCveEstadoD").toString());
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
                                   vcEntidadFed2 = dEntidadFed2.FindByAll(" where iCvePais = " + request.getParameter("iCvePaisD").toString());
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
                                       vcMunicipio = dMunicipio.FindByAll(request.getParameter("iCvePaisD").toString(),request.getParameter("iCveEstadoD").toString());
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
                                    %>

<tr>
    <td colspan="2" align="center"><input TYPE="button" name="Envio" value="Enviar" onClick="Validar(this.form)"></td></tr></table> 

</form>

<SCRIPT LANGUAGE="JavaScript">
     window.resizeTo(630,605);
</SCRIPT>        

</body>
</html>