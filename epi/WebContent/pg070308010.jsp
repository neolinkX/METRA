<%/**
 * Title: Listado de Exámenes Concluidos
 * Description: JSP para listar los exámenes concluidos
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<html>
<%
  pg070308010CFG  clsConfig = new pg070308010CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070308010.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070308010.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";     // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                 // modificar
  boolean lIra        = false;                 // modificar
  String cEstatusIR   = "";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  int iAniosAtras = Integer.parseInt(vParametros.getPropEspecifica("Anios"));
  TVUsuario vUsuario=(TVUsuario)request.getSession(true).getAttribute("UsrID");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  </SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.pageNo()):""%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdINumExamen" value="">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td colspan="4" class="ETablaT">LIBERAR MUESTRAS</td></tr>
        <tr>
          <td class="EEtiqueta">A&ntilde;o:</td>
          <td class="ETabla">
             <select name="iAnio" >
                  <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                        if (request.getParameter("iAnio") != null && Integer.parseInt(request.getParameter("iAnio")) == i)
                           out.print("<option value = " + i + " selected>" + i + "</option>");
                        else
                           out.print("<option value = " + i + ">" + i + "</option>");
                     }
                  %>
             </select>
          </td>
          <%=vEti.EtiCampoCS("EEtiqueta", "Muestra: ", "ECampo", "text", 19, 19,1,"iCveMuestra", "", 0, "", "", false, true,true, request)%>
          </tr>
          <tr>
                              <td class="ETablaC" colspan="2">
<%=                                vEti.clsAnclaTexto("EAnclaC","Liberar", "javascript:if(fValidaSelectores())fLiberar('pg070308010.jsp');","exp")
%>                            </td>
                              <td class="ETablaC" colspan="2">
<%=                               vEti.clsAnclaTexto("EAnclaC","Borrar", "javascript:if(fValidaSelectores())fBorrar('pg070308010.jsp');","exp")
%>                            </td>

         </tr>
          <!--tr>
            </%= vEti.EtiCampoCS("EEtiqueta", "Env&iacute;o: ", "ECampo", "text", 19, 19,1,"iCveEnvio", "", 0, "", "", false, true,true, request) %/>
             <td class="ETablaC" colspan="2">
</%=           vEti.clsAnclaTexto("EAnclaC","Env&iacute;pg070303040o", "javascript:if(fValidaSelectores())fEnvio('pg070308010.jsp');","exp")%/>
             </td>
            </tr-->
      </table>

     </table>
    </td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
<%
  if( request.getParameter("hdBoton") != null ){
    if( request.getParameter("hdBoton").equals("GuardarA")){
      if(clsConfig.getILFlag() != 0)
        out.println("<script language=\"JavaScript\">alert('Se Ha Liberado La Muestra.');</script>");
      else
        out.println("<script language=\"JavaScript\">alert('No Se Pudo Liberar La Muestra.');</script>");
    }
    if( request.getParameter("hdBoton").equals("Borrar")){
      if(clsConfig.getIBFlag() != 0)
        out.println("<script language=\"JavaScript\">alert('Se Ha Borrado La Muestra.');</script>");
      else
        out.println("<script language=\"JavaScript\">alert('No Se Pudo Borrar La Muestra.');</script>");
    }
  }
%>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>