<%/**
 * @Tipo:          Interfaz
 * @Clasificaci�n: Acerca de ...
 * @Autor:         Tecnolog�a InRed S.A. de C.V. - LSC. Marco Antonio Hern�ndez Garc�a
 * @FechaFin:      01/11/2003
 * @Descripci�n:   Acerca de ...
 * @Imagen:        pgAcerca.jpg
 */%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.logging.*"%>
<%@ page import="com.micper.excepciones.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<html>
<%  // Unica Parte del C�digo que debe modificarse
  try{
%>
<script language="JavaScript">
  function fValidaTodo(){
    var form = document.forms[0];
    if(top.fConfirmaBorrar)
      if (!top.fConfirmaBorrar(document))
        return false;
    return true;
  }
  function fCargarPag(TheUrl, theTarget){
    document.forms[0].action  = TheUrl;
    document.forms[0].target  = theTarget;
    document.forms[0].submit();
  }
</script>
<%
  String           vCriterio     = "";
  TError           vErrores      = new TError();
  StringBuffer     sbErroresAcum = new StringBuffer();
  Vector           vBtnSec       = new Vector();
  Vector           vUrlSec       = new Vector();
  Vector           vEstSec       = new Vector();
  TEntorno         vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  TParametro  vParametros  = new TParametro(vEntorno.getNumModuloStr());

  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setTituloVentana("Acerca de ...");
  vEntorno.setTituloPagina("Direcci�n General de Medicina Preventiva en el Transporte");
  vEntorno.setNombrePagina("pgAcerca.jsp");
  vEntorno.setArchAyuda("Acerca");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.clearListaImgs();
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pgAcerca.jsp\" target=\"FRMCuerpo");
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(false);
  vEntorno.setArchFCatalogo("FFiltro");
  vBtnSec.addElement("bSalir2");
  vUrlSec.addElement("JavaScript: window.close();");
  vEstSec.addElement("Salir del Acerca de ...");

  String cRutaImg      = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg      = vEntorno.getTiposImg().elementAt(0).toString();
  String cRutaAyuda    = vParametros.getPropEspecifica("html");
  String cPrograma     = cRutaAyuda + "hlp" + vEntorno.getArchAyuda() + ".htm";
%>
<script language="JavaScript">
  top.document.title = '<%= vEntorno.getTituloVentana() %>';
  function fAbrir(cNombre,cVentana) {
    wExp = open(cNombre,cVentana,"dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=yes,scrollbars=yes,status=yes,titlebar=yes,toolbar=no,width=630,height=530,screenX=20,screenY=10");
    wExp.focus();
  }
  function fAcerca(){}
</script>
<head>
  <title><%= vEntorno.getTituloVentana() /* Obtiene el t�tulo de la ventana de los par�metros y lo muestra */ %></title>
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" class="ETablaGral" background=" <%= cRutaImg %>fondoInicio.gif">
    <tr>
      <td width="100%" height="1%" valign="top">
       <%=new TCreaBoton().clsCreaBoton(vEntorno, 3, "Acerca", "Logo", 0, "Acerca de...","", vParametros)%>
      </td>
    </tr>
    <tr>
      <td align="center" valign="top">
        <table border="2" width="<%= vParametros.getPropEspecifica("AnchoTablaPrinc") %>%"
               height="<%= vParametros.getPropEspecifica("AltoTablaPrinc") %>%"
               cellspacing="0" cellpadding="0"
               background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg">
          <tr>
            <td width="100%" valign="top">
              <% new TDefSubmit(pageContext); /* Define funcion de sumbitir y campo oculto */ %>
              <% new TSubTitulo(pageContext, vEntorno, vBtnSec, 1, vUrlSec, vEstSec, vParametros); /* Se encarga de desplegar el logo de empresa y botones de acci�n generales */ %>
              <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%">
                <tr>
                  <td width="100%" align="center" class="EEtiquetaP">
                    <p>&nbsp;</p>
                  </td>
                </tr>
                <tr>
                  <td width="100%" align="center">
                    <table border="0">
                      <tr>
                        <td width="100%" align="center">
                          <table border="1" class="ETablaInfo" width="100%" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                               <tr>
                                 <td colspan="2" class="ETablaT">
                                   Administraci�n y Seguridad - DGMPT
                                 </td>
                               </tr>
                            <%
                               TEtiCampo vEti = new TEtiCampo();
                               out.print("<tr>");
                               out.print(vEti.Texto("EEtiquetaL", "Versi�n"));
                               out.print(vEti.Texto("ECampo", "3.0"));
                               out.print("</tr>");
                               out.print("<tr>");
                               out.print(vEti.Texto("EEtiquetaL", "Soporte T�cnico"));
                               out.print(vEti.Texto("ECampo", "Ing. Soporte T�cnico"));
                               out.print("</tr>");
                               out.print("<tr>");
                               out.print(vEti.Texto("EEtiquetaL", "&nbsp;"));
                               out.print(vEti.Texto("ECampo", "SoTecING@sct.gob.mx"));
                               out.print("</tr>");
                               out.print("<tr>");
                               out.print(vEti.Texto("EEtiquetaL", "&nbsp;"));
                               out.print(vEti.Texto("ECampo", "(0155) 55 55 55 55 ext. 123456"));
                               out.print("</tr>");
                               try{
                                 TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                 if(vUsuario != null){
                                 out.print("<tr>");
                                 out.print(vEti.Texto("EEtiquetaL", "Usuario Actual"));
                                 out.print(vEti.Texto("ECampo", ""+vUsuario.getICveusuario()));
                                 out.print("</tr>");
                                 out.print("<tr>");
                                 out.print(vEti.Texto("EEtiquetaL", "&nbsp;"));
                                 out.print(vEti.Texto("ECampo", vUsuario.getCNombre()+" "+vUsuario.getCApPaterno()+" "+vUsuario.getCApMaterno()));
                                 out.print("</tr>");

                                 %>
                               <tr><td colspan="2" class="ETablaT">
                                 Temas de Ayuda para la Operaci�n del Sistema
                               </td></tr>
                               <tr><td colspan="2" class="EEtiquetaL">
                                 Es importante resaltar que en aplicaciones que trabajan con navegador de internet, solo debe dar 'UN CLICK' sobre botones, ligas y cualquier otro elemento, la acci�n 'DOBLE CLICK' podr�a generar resultados inesperados.
                               </td></tr>
                               <tr><td colspan="2" class="EEtiquetaL">
                                 <a href="JavaScript:fAbrir('<%=vParametros.getPropEspecifica("html")%>pgConfigNav.htm','Config');" onMouseOver="self.status='Instrucciones de configuraci�n...';return true;" onMouseOut="self.status='';return true;">Como configurar el navegador para un correcto funcionamiento.</a>
                               </td></tr>
                               <tr><td colspan="2" class="EEtiquetaL">
                                 <a href="JavaScript:fAbrir('<%=vParametros.getPropEspecifica("html")%>pgConfigAX.htm','ActiveX');" onMouseOver="self.status='Instrucciones de configuraci�n para reportes...';return true;" onMouseOut="self.status='';return true;">Como configurar el navegador para el funcionamiento de reportes.</a>
                               </td></tr>
                               <tr><td colspan="2" class="EEtiquetaL">
                                 <a href="<%=vParametros.getPropEspecifica("RutaPlantilla")%>" onMouseOver="self.status='Obtenci�n de plantillas para reportes...';return true;" onMouseOut="self.status='';return true;">Plantillas para Reportes del Sistema</a>
                               </td></tr>
                                 <%
                                 }
                               }catch(Exception e){}
                            %>
                          </table>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
           </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
<%= vErrores.muestraError() /* Al final de la p�gina se despliegan errores si existen */ %>
<%=	new TDefPrecargar(vEntorno.getListaImgs()).getResultado() /* Define funciones y llamado de precarga de imagenes */ %>
<% vEntorno.clearListaImgs(); %>
</html>
<%
}catch(Exception ex){
  ex.printStackTrace();
}%>
