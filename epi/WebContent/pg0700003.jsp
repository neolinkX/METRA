<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>  
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%> 
<%
    TVMenu vMenu;
    TEntorno    vEntorno      = new TEntorno();
    vEntorno.setNumModulo(07);
    TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
    vEntorno.setArchFuncs("FValida");
    vEntorno.setArchTooltips("07_Tooltips");
    vEntorno.clearListaImgs();
    vEntorno.setActionForm("pg0700003.jsp"); 
    vEntorno.setNombrePagina("pg0700003.jsp"); 
    vEntorno.setArchFCatalogo("FFiltro");
    Vector vcMenuUsuario = new Vector();
    if(request.getSession(true).getAttribute("MenuUsuario") != null){
      vcMenuUsuario = (Vector) request.getSession(true).getAttribute("MenuUsuario");
    }else{ %>
      <script language="JavaScript">
        alert("El Sistema ya no se encuentra disponible, este módulo cerrará.");
        if(window.opener.fSalir)
           window.opener.fSalir();
        window.close();
      </script>
    <%}
    String cRutaImg = vParametros.getPropEspecifica("RutaImg");
    String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
    String cRutaAyuda = vParametros.getPropEspecifica("html");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700003.js"></SCRIPT>
<script language="JavaScript">
  var cRutaImg = '';
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cRutaFuncs = '<%=vParametros.getPropEspecifica("RutaFuncs")%>';
  var Style = '<%=vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS")%>';
  var bkFondo = '<%=cRutaImg%>';
<%  NumberFormat nOpc = NumberFormat.getNumberInstance();
    nOpc.setMinimumIntegerDigits(10);
    nOpc.setMaximumIntegerDigits(10);
    nOpc.setGroupingUsed(false);
    for(int j = 0; j < vcMenuUsuario.size(); j++){
       vMenu = (TVMenu) vcMenuUsuario.get(j);
       out.print("fM("+vMenu.getIOpcPadre()+",'"+vMenu.getIOrden()+"','"+vMenu.getCReferencia()+"','"+vMenu.getCDscMenu()+"','"+vMenu.getINivel()+"');");
    }%>fPag();
</script>