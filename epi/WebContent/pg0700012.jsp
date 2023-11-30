<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="com.micper.seguridad.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>


<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.util.reglas.ReglasExpedienteVirtual" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>   
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<% 	/**
	* Validacion de sesion iniciada
	* @author Ing. Andres Esteban Bernal Muñoz - 16/05/2014
	*/
      if(request.getSession(true).getAttribute("UsrID")==null){    	    
    	    response.sendRedirect("pg0700000.jsp");
    	    return;    	  
      } //Fin validacion %>
<html>
<%

  pg0700012CFG clsConfig = new pg0700012CFG();

  TError vErrores = new TError();
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg0700012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg0700012.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "|";    // modificar
  String cCveOrdenar  = "|";  // modificar
  String cDscFiltrar  = "|";    // modificar
  String cCveFiltrar  = "|";  // modificar
  String cTipoFiltrar = "|";                // modificar 
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";            // modificar 
  
  
  

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();
 
  String cPaginas = "";
  String cDscPaginas = "";
  String cUpdStatus  = "SaveOnly";
  //String cNavStatus  = "Hidden";
  //String cOtroStatus = "";
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "";
  String cClave    = "";
  String cPosicion = "";
  PageBeanScroller bs = clsConfig.getBeanScroller();
  
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
%>
<%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<!--<script type='text/javascript' src='/medprev/dwr/engine.js'></script>
<script type='text/javascript' src='/medprev/dwr/util.js'></script>
<script type='text/javascript' src='/medprev/dwr/interface/MedPredDwr.js'></script>

<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700002DB.js"></script>
<SCRIPT LANGUAGE="JavaScript">
    var  macAddress = "";
    var ipAddress = "";
    var computerName = "";

if (BrowserDetect.browser == 'Explorer' && BrowserDetect.version == '9') {
        MedPredDwr.insertAccess(<%=vUsuario.getICveusuario()%>,"",BrowserDetect.browser,BrowserDetect.version,"","",{
            callback : function(data) {
                   if(data == 1){
                       //alert("Prueba Superada");
                   }
            },
            scope : this
    });
    } else {
        var wmi = GetObject("winmgmts:{impersonationLevel=impersonate}");
        e = new Enumerator(wmi.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration WHERE IPEnabled = True"));
        for(; !e.atEnd(); e.moveNext()) {
            var s = e.item();
            macAddress = s.MACAddress;
            ipAddress = s.IPAddress(0);
            computerName = s.DNSHostName;
        }
        //alert("macAd: " + macAddress + " ipAdrr: " + ipAddress + " computerName: " + computerName);
        MedPredDwr.insertAccess(<%=vUsuario.getICveusuario()%>,ipAddress,BrowserDetect.browser,BrowserDetect.version,macAddress,computerName,{
            callback : function(data) {
                    if(data == 1){
                      // alert("Prueba Superada");
                   }
            },
            scope : this
        });
    }
     -->
 <script language="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  
  

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  
  
  
  function cuenta(){
	  document.forms[0].caracteres.value=document.forms[0].cNvaContrasenia.value.length
	  if(document.forms[0].cNvaContrasenia.value.length > 15){
		  	document.forms[0].cNvaContrasenia.value = document.forms[0].cNvaContrasenia.value.substring(0,15);
	  		alert("No puede ingresar mas de 15 caracters para su contraseña");
	  }
  }

</SCRIPT>
<script language="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cPagina = '<%=vEntorno.getNumModuloStr()%>';
  var cAyuda = '<%=vEntorno.getArchAyuda()%>';
</script>

<script language="JavaScript">fOnLoad2();</script>

<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value=""><input type="hidden" name="hdLCondicion" value=""><input type="hidden" name="hdLOrdenar" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="2" class="ETablaT">Cambio de Contraseña
                              </td>
                            </tr>
                             <%
                               try{
                                 //TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                 if(vUsuario != null){
                                    out.print("<tr>");
                                    out.print(vEti.Texto("EEtiquetaL", "Clave:"));
                                    out.print(vEti.Texto("ECampo", ""+vUsuario.getICveusuario()));
                                    out.print("<input type=\"hidden\" name=\"hdUsuario\" value=\""+vUsuario.getCUsuario()+"\">");
                                    out.print("<input type=\"hidden\" name=\"hdPwd\" value=\""+vUsuario.getCPassword()+"\">");
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    out.print(vEti.Texto("EEtiquetaL", "Usuario:"));
                                    out.print(vEti.Texto("ECampo", vUsuario.getCUsuario()));
                                    out.print("</tr>"); 
                                    out.print("<tr>");
                                    out.print(vEti.Texto("EEtiquetaL", "Nombre:"));
                                    out.print(vEti.Texto("ECampo", vUsuario.getCNombre()+" "+vUsuario.getCApPaterno()+" "+vUsuario.getCApMaterno()));
                                    out.print("</tr>");
                                    if(cCanWrite.equals("yes")){
                                      out.print("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","Contraseña:","ECampo","password", 15, 15, "cContrasenia", "", 0, "", "", true, true, true));
                                      out.print("</tr>");
                                      out.print("<tr>");
                                      //out.print(vEti.EtiCampo("EEtiqueta","Nueva Contraseña:","ECampo","password", 12, 10, "cNvaContrasenia", "", 0, "", "", true, true, true));
                                      out.print(" <td class=\"EEtiqueta\">Nueva Contraseña:</td><td class=\"ECampo\"> ");
                                      out.print(" <input type=\"password\" size=\"16\" maxlength=\"16\" name=\"cNvaContrasenia\" ");
									  out.print(" value=\"\" onfocus=\"this.select();\" ");
									  out.print(" onMouseOut=\"if (window.fOutField) window.fOutField();\"");
									  out.print(" onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
									  out.print(" onKeyDown=\"cuenta()\" onKeyUp=\"cuenta()\" ></td>");
                                      out.print("<input type=\"hidden\" size=\"16\" maxlength=\"16\" name=\"caracteres\" value=\"\">");
                                      out.print("</tr>");
                                      out.print("<tr>");
                                      out.print(vEti.EtiCampo("EEtiqueta","Reescriba Nueva Contraseña:","ECampo","password", 16, 16, "cReNvaContrasenia", "", 0, "", "", true, true, true));
                                      out.print("</tr>");
                                    }
                                 }else{
                                 %>
                                    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
                                 <%}
                               }catch(Exception e){}
                            %>
                          </table>
  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%>

<% 
if(clsConfig.getAccesoValido2()){
}else{%> 
      <script language="JavaScript">fSalir2(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
 <%}%>


</html>
 