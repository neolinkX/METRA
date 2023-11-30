<%/**  
 * Title: Cat�logo del Personal
 * Description: Cat�logo del Personal
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 * Clase:SEDetPerCFG
 *  */%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>  
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html onMouseOver="mostrardiv();">
<%
  pg070103041CFG  clsConfig = new pg070103041CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070103041.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103041.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "|";                // modificar
  boolean lFiltros    = true;                 // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

      String cCampo = "";
      String cPanel = "";

    if ( (request.getParameter("hdOPPbaRapida") == null) ||
         (request.getParameter("hdBoton").compareTo("Guardar") == 0) ||
          (request.getParameter("hdBoton").compareTo("Cancelar") == 0)){
         cCampo = "0";
    }
    else{
         cCampo = "" + request.getParameter("hdOPPbaRapida");
     }



  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  TDPERDatos dPERDatos = new TDPERDatos();

  String TEMPORAL = clsConfig.Temporal();


	///Validar si es tercero para bloquear el registro de cita
	TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
	boolean bloqueaGeneraCita = false;
	bloqueaGeneraCita = clsConfig.getBloqueaGeneraCita(vUsuario.getCUsuario(), vUsuario.getICveusuario());
	//System.out.println("bloqueaGeneraCita="+bloqueaGeneraCita);
	if(bloqueaGeneraCita){
		cUpdStatus  = "";
	}else{
		cUpdStatus  = clsConfig.getUpdStatus();		
	}
  
  
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">
  // Esta funci�n no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';

  function fEmpSelected(iCveEmpresa, cNombreRS, cApPaterno, cApMaterno, cRFC, cCURP, cTpoPersona){
    var form = document.forms[0];
    form.iCveNumEmpresa.value = iCveEmpresa;
    form.cDscEmpresa.value = cNombreRS;

  }

function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1999)
    fArea.value = cText = cText.substring(0,1999);
  form.iNoLetras.value = 1999 - cText.length;
}


function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1999)
    fArea.value = cText = cText.substring(0,1999);
  form.iNoLetras.value = 1999 - cText.length;
  fArea.value = revisaCaracteresEspeciales(cText);
}

//Valida Apostrofo y Enter

function revisaCaracteresEspeciales(texto){
      if(fEncCaract(texto, "'")){
            alert("No se permite la comilla simple");
            texto = texto.replace("'","");
      }
      if(fEncCaract(texto, String.fromCharCode(10))||fEncCaract(texto, String.fromCharCode(13))){
          alert("No se permite ejecutar un ENTER en este campo");
          texto = texto.replace(String.fromCharCode(13),"");
          texto = texto.replace(String.fromCharCode(10),"");
      }
      return texto;
  }

  /* Funci�n para localizar un caracter espec�fico dentro de una cadena
   Par�metros: cVCadena -- Cadena en la cual se va a localizar el caracter
               cVCaract -- Caracter a buscar
   Valor que regresa:   true  -- Si lo localiza
                        false -- Si no lo encuentra */
function fEncCaract(cVCadena, cVCaract){
   if (cVCadena.indexOf(cVCaract) != -1){        
        return true;
   }else{
       return false;       
   }
}



function mostrardiv() {
var x = form.cDscEmpresa.value;
//alert(x);
		if(form.cDscEmpresa.value == '<%=TEMPORAL%>' || form.cDscEmpresa.value == ' <%=TEMPORAL%>'){
			div = document.getElementById('flotante');
			div.style.display = '';
			div = document.getElementById('flotante2');
			div.style.display = '';
		}else{
                     div = document.getElementById('flotante');
                     div.style.display='none';
                     div = document.getElementById('flotante2');
                     div.style.display='none';
                }
}

function cerrar() {
    if(form.cDscEmpresa.value != '<%=TEMPORAL%>'){
        div = document.getElementById('flotante');
        div.style.display='none';
        div = document.getElementById('flotante2');
        div.style.display='none';
    }
}



</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"empr.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selEmp.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SEDetPer.js"%>"></SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>;mostrardiv();">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = ""+bs.getFieldValue("iCvePersonal", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" >
  <input type="hidden" name="hdPersonal">
  <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal")%>">

  <input type="hidden" name="iCveNumEmpresa">

  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="dtFecha" value="<%=request.getParameter("dtFecha")%>">
  <input type="hidden" name="cHora" value="<%=request.getParameter("cHora")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  <input type="hidden" name="hdBoton">
  </td><td valign="top">

                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="4" class="ETablaT">Detalle del Personal
                                 </td>
                                 </tr>
                                 <tr>

                               <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                //Datos de catalogos
                                if (lNuevo){

                                }
                                else {
                                  if (bs != null){
                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Datos Personal",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Personal:","ECampo","text",10,10,"cApPaterno", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"fMayus(this);","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Expediente:","ECampo","text",10,10,"cApMaterno", bs.getFieldValue("iCveExpediente","&nbsp;").toString(),0,"fMayus(this);","fMayus(this);",false,true,false));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Primer Apellido:","ECampo","text",25,25,"cApPaterno", bs.getFieldValue("cApPaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("  <input type=\"hidden\" name=\"cApPaterno\" value=\""+bs.getFieldValue("cApPaterno","&nbsp;").toString()+"\">");
                                    out.print(vEti.EtiCampo("EEtiqueta","Segundo Apellido:","ECampo","text",25,25,"cApMaterno", bs.getFieldValue("cApMaterno","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("  <input type=\"hidden\" name=\"cApMaterno\" value=\""+bs.getFieldValue("cApMaterno","&nbsp;").toString()+"\">");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Nombre(s):","ECampo","text",30,30,"cNombre", bs.getFieldValue("cNombre","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("  <input type=\"hidden\" name=\"cNombre\" value=\""+bs.getFieldValue("cNombre","&nbsp;").toString()+"\">");
                                    if (request.getParameter("hdBoton").compareTo("Modificar") != 0 ){
                                    	//out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cSexo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    	out.print(vEti.EtiCampo("EEtiqueta","Sexo:","ECampo","text",10,10,"cGenero", bs.getFieldValue("cSexo_DGIS","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    	out.println("  <input type=\"hidden\" name=\"cGenero\" value=\""+bs.getFieldValue("cGenero","&nbsp;").toString()+"\">");
                                    }
                                    else{
                                     out.println(vEti.Texto("EEtiqueta","Sexo:"));
                                     if (bs.getFieldValue("cSexo","&nbsp;").toString().compareTo("M") == 0){
                                    %>
                                    <td class="EEtiquetaL">
                                    Hombre<input type="radio" name="cSexo" value="M" checked>
                                    Mujer<input type="radio" name="cSexo" value="F">
                                    </td>
                                    <%
                                     }
                                    else{
                                    %>
                                    <td class="EEtiquetaL">
                                    Hombre<input type="radio" name="cSexo" value="M" checked>
                                    Mujer<input type="radio" name="cSexo" value="F" checked>
                                    </td>
                                    <% 
                                     }
                                    }
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","FECNAC (Fecha de Nacimiento):","ECampo","text",10,10,4,"dtNacimiento", bs.getFieldValue("cDscFecNacimiento","").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("  <input type=\"hidden\" name=\"dtNacimiento\" value=\""+bs.getFieldValue("cDscFecNacimiento","&nbsp;").toString()+"\">");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","RFC:","ECampo","text",13,13,"cRFC", bs.getFieldValue("cRFC","").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("  <input type=\"hidden\" name=\"cRFC\" value=\""+bs.getFieldValue("cRFC","&nbsp;").toString()+"\">");
                                    out.print(vEti.EtiCampo("EEtiqueta","Homoclave:","ECampo","text",3,3,"cHomoclave", bs.getFieldValue("cHomoclave","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("  <input type=\"hidden\" name=\"cHomoclave\" value=\""+bs.getFieldValue("cHomoclave","&nbsp;").toString()+"\">");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","CURP:","ECampo","text",18,18,4,"cCURP", bs.getFieldValue("cCURP","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.println("  <input type=\"hidden\" name=\"cCURP\" value=\""+bs.getFieldValue("cCURP","&nbsp;").toString()+"\">");
                                    //adicion de campos AERM
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia Nacional:","ECampo","text",12,12,"cLicencia", bs.getFieldValue("cLicencia","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Licencia Internacional:","ECampo","text",10,10,"cLicenciaInt", bs.getFieldValue("cLicenciaInt","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    //fin de adicion
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    if (request.getParameter("hdBoton").compareTo("Modificar") != 0 ){
                                    out.print(vEti.EtiCampo("EEtiqueta","Pa&iacute;s de Nacimiento:","ECampo","text",10,10,"iCvePaisNac", bs.getFieldValue("cDscPaisNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","EDONAC (Lugar de Nacimiento):","ECampo","text",10,10,"iCveEstadoNac", bs.getFieldValue("cDscEstadoNac","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    }
                                    else{
	                                    TDPais dPaisNac = new TDPais();
	                                    out.println("<tr>");
	                                    out.println(vEti.Texto("EEtiqueta","Pa&iacute;s de Nacimiento:"));
	                                    //out.println("<td>");
	                                    //out.println(vEti.SelectOneRowSinTD("iCvePaisNac","llenaSLT(2,this.value,'','',document.forms[0].iCveEstadoNac);", dPaisNac.FindByAll(), "iCvePais", "cNombre",request, bs.getFieldValue("iCvePaisNac","&nbsp;").toString() , true));
	                                    out.println("<td class=\"ECampo\">"+bs.getFieldValue("cDscPaisNac","&nbsp;").toString());
	                                    out.println("<input type=\"hidden\" name=\"iCvePaisNac\" value=\""+bs.getFieldValue("iCvePaisNac","").toString()+"\">");
	                                    out.println("</td>");
	                                    out.println("</td>");
	                                    out.println(vEti.Texto("EEtiqueta","EDONAC (Lugar de Nacimiento):"));
	                                    out.println("<td class='ECampo'>");
	
	                                    TVEntidadFed vEntidadFed = new TVEntidadFed();
	                                    TDEntidadFed dEntidadFed = new TDEntidadFed();
	                                    Vector vcEntidadFed = new Vector();
	                                    vcEntidadFed = dEntidadFed.FindByAll(" where iCvePais = " + bs.getFieldValue("iCvePaisNac","&nbsp;").toString());
	
	                                    out.println(vEti.SelectOneRowSinTD("iCveEstadoNac","", vcEntidadFed, "iCveEntidadFed", "cNombre",request, bs.getFieldValue("iCveEstadoNac","&nbsp;").toString() , true));
	
	                                    out.println("</td>");
	                                    out.println("</tr>");
                                    }
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Expediente:","ECampo","text",40,50,4,"cExpediente", bs.getFieldValue("cExpediente","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Correo Electr&oacute;nico:","ECampo","text",50,50,"cCorreoElec", bs.getFieldValue("cCorreoElec","&nbsp;").toString(),0,"","",false,true,lCaptura));
	
                                    /*
                                    if (request.getParameter("hdBoton").compareTo("Modificar") != 0 ){
                                       if (bs.getFieldValue("lDonadorOrg","&nbsp;").toString().compareTo("1") == 0)
                                          out.print(vEti.EtiCampo("EEtiqueta","Donador de �rganos:","ECampo","text",10,10,"lDonadorOrg","SI",0,"","fMayus(this);",false,true,lCaptura));
                                       else
                                          out.print(vEti.EtiCampo("EEtiqueta","Donador de �rganos:","ECampo","text",10,10,"lDonadorOrg","NO",0,"","fMayus(this);",false,true,lCaptura));
                                    }
                                    else{
                                      out.println(vEti.Texto("EEtiqueta","Donador de �rganos:"));
                                      out.println("<td>");
                                      if (bs.getFieldValue("lDonadorOrg","&nbsp;").toString().compareTo("1") == 0)
                                         out.println("<input type='checkbox' name='lDonadorOrg' value='ON' checked>");
                                      else
                                         out.println("<input type='checkbox' name='lDonadorOrg' value='ON'>");
                                      out.println("</td>");
                                    }*/

                                    out.println("</tr>");
                                    out.println("<tr>");
                                    if (lCaptura) {
                                       out.println("<td class=\"EEtiqueta\">Se&ntilde;as Particulares:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
                                       out.println("<td colspan=\"3\"><textarea cols=\"80\" rows=\"5\"  name=\"cSenasPersonal\" value=\"" + bs.getFieldValue("cSenasPersonal","&nbsp;").toString() + "\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">" + bs.getFieldValue("cSenasPersonal","&nbsp;").toString() + "</textarea></td>");
                                    }
                                    else
                                    out.println(vEti.EtiAreaTextoCS("EEtiqueta","Se&ntilde;as Particulares:","ECampo",50,5,4,"cSenasPersonal",bs.getFieldValue("cSenasPersonal","&nbsp;").toString(),0,"","",false,true,lCaptura));
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.TextoCS("ETablaSTC","Datos Aviso",4));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Persona Aviso:","ECampo","text",80,150,4,"cPersonaAviso", bs.getFieldValue("cPersonaAviso","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Direcci&oacute;n Aviso:","ECampo","text",80,150,4,"cDirecAviso", bs.getFieldValue("cDirecAviso","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Tel. Aviso:","ECampo","text",20,20,"cTelAviso", bs.getFieldValue("cTelAviso","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampo("EEtiqueta","Correo Aviso:","ECampo","text",25,25,"cCorreoAviso", bs.getFieldValue("cCorreoAviso","&nbsp;").toString(),0,"","",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    if (request.getParameter("hdBoton").toString().compareTo("Modificar") == 0){
                                     out.println(vEti.Texto("EEtiqueta","Empresa:"));
                                     out.println("<td>");
                                     out.println("<input type='text' name='cDscEmpresa' value='" + bs.getFieldValue("cDscEmpresa","&nbsp;").toString()  + "' size='50' disabled = 1>");
                                     out.println("</td>");
                                     out.println("<td>");
                                     out.println(vEti.clsAnclaTexto("EAncla","Buscar Empresa","JavaScript:fSelEmp();","Buscar Empresa",""));
                                     out.println("</td>");
                                    }
                                    else{
                                    //out.print(vEti.EtiCampo("EEtiqueta","Cve.:","ECampo","text",80,150,"iCveEmpresa", bs.getFieldValue("iCveEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Empresa:","ECampo","text",80,150,4,"cDscEmpresa", bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    }
                                    out.println("</tr>");

                                //Nuevo Campo Empresa Actual 17 de junio 2011 AG SA.
                                    out.println("<tr>");
                                    if (request.getParameter("hdBoton").toString().compareTo("Modificar") == 0){
                                    out.println(" <td class=\"EEtiqueta\">");
                                    out.println("<div id=\"flotante\" style=\"display:none;\">");
                                    out.println("Empresa Actual:  ");
                                    out.println("</div>");
                                    out.println("</td>");
                                    out.println(" <td class=\"EEtiqueta\">");
                                    out.println("<div id=\"flotante2\" style=\"display:none;\">");
                                     out.println("<input type='text' name='cDscEmpresa2' value='" + clsConfig.NombreTemp(bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),bs.getFieldValue("iCvePersonal","&nbsp;").toString())  + "' size='50' >");
                                     out.println("</div>");
                                     out.println("</td>");
                                     out.println("<td>");
                                     //out.println(vEti.clsAnclaTexto("EAncla","Buscar Empresa temporal","JavaScript:fSelEmp();","Buscar Empresa",""));
                                     out.println("</td>");
                                    }
                                    else{
                                    String empresaA = "";
                                    empresaA = clsConfig.NombreTemp(bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                                    //System.out.println(empresaA +" -- "+ empresaA.length());
                                        if(empresaA.length()!=0){
                                        //out.print(vEti.EtiCampo("EEtiqueta","Cve.:","ECampo","text",80,150,"iCveEmpresa", bs.getFieldValue("iCveEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.print(vEti.EtiCampoCS("EEtiqueta","Empresa Actual:","ECampo","text",80,150,4,"cDscEmpresa2", clsConfig.NombreTemp(bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),bs.getFieldValue("iCvePersonal","&nbsp;").toString()),0,"","fMayus(this);",false,true,false));
                                        }
                                        else{
                                            empresaA = "";
                                        }
                                    }
                                    out.println("</tr>");
                                // Final de nuevo campo
								
                                    out.println("<tr>");
                                    out.println("<td colspan='4' align='center'>");
                                    out.print(vEti.clsAnclaTexto("EEtiquetaC","Direcciones",
                                    "javascript:fDireccion("+ bs.getFieldValue("iCvePersonal","&nbsp;") +  ");","Buscar")
                                    + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                     out.println("</td>");
                                    out.println("</tr>");
%>
                        </table>
                        <p>&nbsp;</p>
<%

                                    //Actualizando y Validando Catalogos
                                    Vector Correctos = new Vector();
                                    Correctos = dPERDatos.FindByValida2(bs.getFieldValue("iCvePersonal","&nbsp;").toString());
                                    //System.out.println(Correctos.elementAt(0));
                                    //System.out.println(Correctos.elementAt(1));
                                    //System.out.println(Correctos.elementAt(2));
                                    //System.out.println(Correctos.elementAt(3));
                                    //System.out.println(Correctos.elementAt(4));
                                    //System.out.println(Correctos.elementAt(5));
                                    //System.out.println(Correctos.elementAt(6));
                                    if(Correctos.elementAt(0).toString().compareTo("0") == 0 ||
                                       Correctos.elementAt(1).toString().compareTo("0") == 0 ||
                                       Correctos.elementAt(2).toString().compareTo("0") == 0 ||
                                       Correctos.elementAt(3).toString().compareTo("0") == 0 ||
                                       Correctos.elementAt(4).toString().compareTo("0") == 0 ||
                                       Correctos.elementAt(5).toString().compareTo("0") == 0 ||
                                       Correctos.elementAt(6).toString().compareTo("0") == 0
                                       ){
                                        out.println("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
                                        out.println("<tr>");
                                        out.println("<td width=\"316\" class=\"ETablaT\">Favor de corregir los siguientes datos:</td>");
                                        out.println("</tr>");
                                    int contador = 0;
                                    if(Correctos.elementAt(0).toString().compareTo("0") == 0){
                                       out.println("<tr><td class=\"EEtiqueta\"><div align=\"left\">");
                                       out.println("Pa&iacute;s de Nacimiento<br>");
                                       out.println("</tr>");
                                       contador ++;
                                    }
                                    if(Correctos.elementAt(1).toString().compareTo("0") == 0){
                                       out.println("<tr><td class=\"EEtiqueta\"><div align=\"left\">");
                                       out.println("Lugar de Nacimiento<br>");
                                       out.println("</tr>");
                                       contador ++;
                                    }
                                    if(Correctos.elementAt(3).toString().compareTo("0") == 0){
                                       out.println("<tr><td class=\"EEtiqueta\"><div align=\"left\">");
                                       out.println("Pa&iacute;s de la direcci&oacute;n<br>");
                                       out.println("</tr>");
                                       contador ++; 

                                    }
                                   if(Correctos.elementAt(4).toString().compareTo("0") == 0){
                                       out.println("<tr><td class=\"EEtiqueta\"><div align=\"left\">");
                                       out.println("Estado de la direcci&oacute;n<br>");
                                       out.println("</tr>");
                                       contador ++;
                                    }
                                    if(Correctos.elementAt(5).toString().compareTo("0") == 0){
                                       out.println("<tr><td class=\"EEtiqueta\"><div align=\"left\">");
                                       out.println("Municipio de la direcci&oacute;n<br>");
                                       out.println("</tr>");
                                       contador ++;
                                    }
                                    if(Correctos.elementAt(6).toString().compareTo("0") == 0 || Correctos.elementAt(2).toString().compareTo("0") == 0){
                                          try {
                                                  dPERDatos.Sentencia("UPDATE PERDATOS SET ICVEDIRECCION = 1 WHERE ICVEEXPEDIENTE = "+ bs.getFieldValue("iCveExpediente","&nbsp;").toString()+" AND ICVEDIRECCION = 0");
                                                  dPERDatos.Sentencia("UPDATE PERDIRECCION SET ICVEDIRECCION = 1 WHERE ICVEPERSONAL = "+ bs.getFieldValue("iCveExpediente","&nbsp;").toString()+" AND ICVEDIRECCION = 0");
                                            }
                                            catch (Exception ex) {
                                            //        System.out.println("Se produjo un error en la actualizacion");
                                            }

                                        out.println("<tr><td class=\"EEtiqueta\"><div align=\"left\">");
                                        out.println("Se actualizaron de forma autom&aacute;tica los datos err&oacute;neos<br>");
                                        out.println("</tr>");

                                    }
                                       out.println("</table>");
                                    }
                                   }
                                }
                            %>
                          <% // Fin de Datos %>

  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
