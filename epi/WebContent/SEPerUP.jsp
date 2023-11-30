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
           String          iCveExpediente=request.getParameter("iCveExpediente");
       //  //        System.out.println("Clave expediente es igual a: "+iCveExpediente);
%>

<html>
    <head>
        
<script LANGUAGE="JavaScript">
function enviar_parametro(valor){
var iCveExpediente = formulario.iCveExpediente.value;
var RFCR = formulario.RFC2.value;
var Paterno2R = formulario.Paterno2.value;
var Materno2R = formulario.Materno2.value;
var Nombre2R = formulario.Nombre2.value;
var SexoR = formulario.Sexo.value;
var iCvePaisNacR = formulario.iCvePaisNac.value;
var iCveEstadoNacR = formulario.iCveEstadoNac.value;
var iCvePaisDR = formulario.iCvePaisD.value;
var iCveEstadoDR = formulario.iCveEstadoD.value;

location = location.pathname + '?param=' + valor + '&iCveExpediente=' + iCveExpediente + '&RFCR=' + RFCR + '&Paterno2R=' + Paterno2R + '&Materno2R=' + Materno2R + '&Nombre2R=' + Nombre2R + '&SexoR=' + SexoR + '&iCveEstadoNacR='+iCveEstadoNacR+'&iCvePaisNacR=' + iCvePaisNacR + '&iCveEstadoDR='+iCveEstadoDR+'&iCvePaisDR=' + iCvePaisDR;
} 
</script>        
        

        
<script> 
    
function Validar(form){


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

CP2 = formulario.CP.value
CP2 = validarEntero(CP2)

if (CP2 =="")
{ alert("Tiene que introducir un número entero en su CP."); formulario.CP.focus(); return; } 


formulario.submit();
}

function validarEntero(valor){ 
      //intento convertir a entero. 
     //si era un entero no le afecta, si no lo era lo intenta convertir 
     valor = parseInt(valor) 
 
      //Compruebo si es un valor numérico 
      if (isNaN(valor)) { 
            //entonces (no es numero) devuelvo el valor cadena vacia 
            return "" 
      }else{ 
            //En caso contrario (Si era un número) devuelvo el valor 
            return valor 
      } 
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
     
           
           
      TDPERDatos dPerDatos = new TDPERDatos();
       String cBuscar = "where icvepersonal="+iCveExpediente+" ";
       
       Vector vcPersonal = new Vector();
       int TamVec=0;

       try{        
                  String ClaveExpediente = "";
                   StringTokenizer solDatos = new StringTokenizer(iCveExpediente, "'");
                             ClaveExpediente = solDatos.nextToken();
                   
                  vcPersonal = dPerDatos.FindByAll(ClaveExpediente);
                   if(vcPersonal.size() == 0){
                   vcPersonal = dPerDatos.FindBySelector(cBuscar);
                   //vcPersonal = dPerDatos.FindByPersona(ClaveExpediente);
                   }
                   else{
                   TamVec = vcPersonal.size() -1;
                   }
         }catch(Exception e){
         vcPersonal = new Vector();
         e.printStackTrace();
       }
              
       TVPERDatos vPerDatos;
       if(vcPersonal.size() > 0){
            for(int x=TamVec;x<vcPersonal.size();x++){
              vPerDatos = (TVPERDatos) vcPersonal.get(x);
     //         out.print(vEti.Texto("ETabla",vPerDatos.getCRFC()+(vPerDatos.getCHomoclave()==null ? "&nbsp;" : vPerDatos.getCHomoclave())));
         %>

<br>
<form name="formulario" method="post" action="SEPerUPR.jsp">
    
    <table class="ETablaInfo" background="" border="1" align="center" cellspacing="0" cellpadding="3">

    
    <tr>
      <td class="ETablaT" colspan="2">Alta de Personal</td>
    </tr>
<%                    
                    out.println("<tr><td class=\"EEtiqueta\">Clave Expediente</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"hidden\" size=\"13\" maxlength=\"13\" name=\"iCveExpediente\" ");
                    out.println("value=\"");
                    out.println(vPerDatos.getICveExpediente());
                    out.println("\"");
                    out.println("onBlur=\"fMayus(this);\">"+vPerDatos.getICveExpediente()+"</td>");

                    
                    out.println("<tr><td class=\"EEtiqueta\">RFC</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"hidden\" size=\"13\" maxlength=\"13\" name=\"RFC2\" ");
                    out.println("value=\"");
                    out.println(vPerDatos.getCRFC());
                    out.println("\"");
                    out.println("onBlur=\"fMayus(this);\">"+vPerDatos.getCRFC()+"</td>");

 

                    out.println("</tr><tr><td class=\"EEtiqueta\">Paterno</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"hidden\" size=\"35\" maxlength=\"35\" name=\"Paterno2\"");
                    out.println("value=\"");
                    out.println(vPerDatos.getCApPaterno());
                    out.println("\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\">"+vPerDatos.getCApPaterno()+"</td>");
                    
 
                    out.println("</tr><tr><td class=\"EEtiqueta\">Materno</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"hidden\" size=\"35\" maxlength=\"35\" name=\"Materno2\"");
                    out.println("value=\"");
                    out.println(vPerDatos.getCApMaterno());
                    out.println("\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\">"+vPerDatos.getCApMaterno()+"</td>");

                    
                    out.println("</tr><tr><td class=\"EEtiqueta\">Nombre</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"hidden\" size=\"35\" maxlength=\"35\" name=\"Nombre2\"");
                    out.println("value=\"");
                    out.println(vPerDatos.getCNombre());
                    out.println("\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\">"+vPerDatos.getCNombre()+"</td>");

 
                    out.println("</tr><tr><td class=\"EEtiqueta\">Sexo:</td>");
                    out.println("<td class=\"ECampo\">");
                    out.println("<input type=\"hidden\" size=\"35\" maxlength=\"35\" name=\"Sexo\"");
                    out.println("value=\"");
                    out.println(vPerDatos.getCSexo());
                    out.println("\"");
                    out.println("onfocus=\"this.select();\"");
                    out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                    out.println("onBlur=\"fMayus(this);\">"+vPerDatos.getCSexo()+"</td>");
/*                        
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
                    out.println("</tr>");*/
                 //   }                                           
                    
 
                                    TDPais dPaisNac = new TDPais();
                                    if (request.getParameter("iCvePaisNacR")==null && request.getParameter("iCveEstadoNacR")==null ){
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais de Nacimiento:"));
                                    out.println("<td>");
                                    out.println("<span class=\"EEtiqueta\">");                                     
                                    out.println(vPerDatos.getCDscPaisNac());                                     
                                    out.println("</span>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisNac","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                    out.println("</td>");
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDONAC (Lugar de Nacimiento):</span> </div>");
                                    out.println("</td>");
                                    out.println("<td class=\"ECampo\">");
                                    out.println(vPerDatos.getCDscEstadoNac());
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
                                    out.println("<span class=\"EEtiqueta\">");                                     
                                    out.println(vPerDatos.getCDscPaisNac());
                                    out.println("</span>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisNac","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisNacR, true));
                                    out.println("</td>");
                                    out.println("</td>");               
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDONAC (Lugar de Nacimiento):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println(vPerDatos.getCDscEstadoNac());
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
                                    out.println("<span class=\"EEtiqueta\">");                                     
                                    out.println(vPerDatos.getCDscPaisNac());                                     
                                    out.println("</span>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisNac","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisNacR, true));
                                    out.println("</td>");
                                    out.println("</td>");                                
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDONAC (Lugar de Nacimiento):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println(vPerDatos.getCDscEstadoNac());
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
 
 
                                    
                                    out.println("<tr>");
                                    out.println("<td colspan=\"2\" bgcolor=\"#4275AD\" class=\"EEtiquetaL\"><FONT COLOR=\"white\"><div align=\"center\" class=\"style1\">Dirección</div></FONT></td>");
                                    out.println("</tr>");

                                    
                                    
                                    if (request.getParameter("iCvePaisDR")==null && request.getParameter("iCveEstadoDR")==null ){
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Pais:"));
                                    out.println("<td>");
                                    out.println("<span class=\"EEtiqueta\">");                                     
                                    out.println(vPerDatos.getCDscPais());                                     
                                    out.println("</span>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, "0", true));
                                    out.println("</td>");
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class=\"ECampo\">");
                                    out.println(vPerDatos.getCDscEstado());
                                    out.println("<select name=\"iCveEstadoD\">");
                                    out.println("<option value=\"valoren\">Seleccione...</option>");
                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","MUN (Municipio):"));
                                    out.println("<td class='ECampo'>");
                                    out.println(vPerDatos.getCDscMunicipio());
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
                                    out.println("<span class=\"EEtiqueta\">");                                     
                                    out.println(vPerDatos.getCDscPais());
                                    out.println("</span>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
                                    out.println("</td>");
                                    out.println("</td>");                                   
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println(vPerDatos.getCDscEstado());
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
                                    out.println(vPerDatos.getCDscMunicipio());
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
                                    out.println("<span class=\"EEtiqueta\">");                                     
                                    out.println(vPerDatos.getCDscPais());
                                    out.println("</span>");
                                    out.println(vEti.SelectOneRowSinTD("iCvePaisD","enviar_parametro(this.value);", dPaisNac.FindByAll(), "iCvePais", "cNombre", request, iCvePaisDR, true));
                                    out.println("</td>");
                                    out.println("</td>");                                
                                    out.println("</tr>");
                                    
                                    out.println("<tr>");
                                    out.println("<td class=\"ECampo\"><div align=\"right\"><span class=\"EEtiqueta\">EDO (Estado):</span> </div>");
                                    out.println("</td>");  
                                    out.println("<td class='ECampo'>");
                                    out.println(vPerDatos.getCDscEstado());
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
                                   out.println(vPerDatos.getCDscMunicipio());
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
                                        out.println("value=\"");
                                        out.println(vPerDatos.getCCalle());
                                        out.println("\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
   
                                                                                
                                        out.println("</tr><tr><td class=\"EEtiqueta\">Colonia:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"60\" maxlength=\"60\" name=\"Colonia\"");
                                        out.println("value=\"");
                                        out.println(vPerDatos.getCColonia());
                                        out.println("\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");
   
                                        out.println("</tr><tr><td class=\"EEtiqueta\">No. Ext.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"25\" maxlength=\"25\" name=\"NoExt\"");
                                        out.println("value=\"");
                                        out.println(vPerDatos.getCNumExt());
                                        out.println("\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");
   
                                        out.println("<tr>");
                                        out.println("<td class=\"EEtiqueta\">No. Int.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"25\" maxlength=\"25\" name=\"NoInt\"");
                                        out.println("value=\"");
                                        out.println(vPerDatos.getCNumInt());
                                        out.println("\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
 
                                        out.println("</tr>");
                                        out.println("<td class=\"EEtiqueta\">C.P.:</td>");
                                        out.println("<td class=\"ECampo\">");
                                        out.println("<input type=\"text\" size=\"20\" maxlength=\"20\" name=\"CP\"");
                                        out.println("value=\"");
                                        out.println(vPerDatos.getICP());
                                        out.println("\"");
                                        out.println("onfocus=\"this.select();\"");
                                        out.println("onChange=\"\" onkeyup=\"fVer(event);\"");
                                        out.println("onBlur=\"fMayus(this);\"></td>");
                                        out.println("</tr>");                                
                             //  }
                         }}
                               %>

<tr>
    <td colspan="2" align="center"><input TYPE="button" name="Envio" value="Enviar" onClick="Validar(this.form)"></td></tr></table> 

</form>

<SCRIPT LANGUAGE="JavaScript">
     window.resizeTo(630,605);
</SCRIPT>        

</body>
</html>