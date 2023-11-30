<%@ page import="gob.sct.medprev.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.ingsw.*"%>
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
    TEntorno    vEntorno      = new TEntorno();
    vEntorno.setNumModulo(07);
    TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
    vEntorno.setArchFuncs("FValida");
    vEntorno.setArchTooltips("07_Tooltips");
    vEntorno.clearListaImgs();
    vEntorno.setMetodoForm("POST");
    vEntorno.setActionForm("pg0700008.jsp");
    vEntorno.setOnLoad("fOnLoad();");
    vEntorno.setArchFCatalogo("FFiltro");

    String cRutaImg = vParametros.getPropEspecifica("RutaImg");
    String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
    String cRutaAyuda = vParametros.getPropEspecifica("html");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>toolbars.js"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg0700008.js"></SCRIPT>-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg0700008.js"></SCRIPT>
<script language="JavaScript">
  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var vpNMR = '<%= vParametros.getPropEspecifica("NumMaxReng")%>';
  var vpNRT = '<%= vParametros.getPropEspecifica("NumRengTab")%>';
</script>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdTipos" value="">
  <input type="hidden" name="hdOperador" value="">
  <input type="hidden" name="hdNumRengTab" value="">
  <input type="hidden" name="hdNewAction" value="New">
  <input type="hidden" name="hdOldAction" value="Old">
  <input type="hidden" name="hdOrdenar" value="">
  <input type="hidden" name="hdNRworks" value="">
  <table border="0" width="100%" cellspacing="0" cellpadding="0" background=" <%= cRutaImg %>fondoFiltro.jpg">
    <tr>
      <td>
        <table border="0" width="100%" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td>
              <table align="center">
                <td class="EEtiquetaF" valign="middle" >
                  <img SRC="<%=cRutaImg%>bOrdenar01.gif">
                </td>
                <td class="EEtiquetaF" valign="middle" >
                  <select name="SLSOrden" size="1">
                  </select>
                </td>
              </table>
            </td>
            <td>
              <table align="center">
                 <td class="EEtiquetaF" valign="middle">
                   <img SRC="<%=cRutaImg%>bRegistros01.gif">
                </td>
                <td class="EEtiquetaF" valign="middle">
                   <input type="text" name="FILNumReng" value="" size="2" maxlength="3">
                </td>
              </table>
            </td>
            <td>
              <table align="center">
                <td class="EEtiquetaF" valign="middle">
                  <img SRC="<%=cRutaImg%>bFiltrar01.gif">
                </td>
                <td class="EEtiquetaF" valign="middle" >
                  <select name="SLSFiltro"  onChange="fVerOperador()" size="1">
                  </select>
                  <select name="SLSOperador" onChange="fVerTodos()" size="1">
                  </select>
                  <input type="text" name="FILBuscar" value="" size="8" OnMouseOver="fOverField(34);" OnMouseOut="fOutField();">
                </td>
                <td class="EEtiquetaF" valign="middle" >
                  <%= new TCreaBoton().clsCreaBoton(vEntorno,3,"Buscar","bBuscar",vEntorno.getTipoImg(),"Buscar","javascript:fSubmite();",vParametros) %>
                </td>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
<%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
</html>
