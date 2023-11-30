<%/**
 * Title:       Programas de Mantenimiento o Calibración
 * Description: Pantalla para realizar filtrados para reporte
 * Copyright:   2004
 * Company:     Micros Personales
 * @author      Rafael Miranda Blumenkron
 * @version     1.0
 * Clase:       pg070604020.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070604020CFG  clsConfig = new pg070604020CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070604020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070604020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "";    // modificar
  String cCveOrdenar  = "";  // modificar
  String cDscFiltrar  = "";    // modificar
  String cCveFiltrar  = "";  // modificar
  String cTipoFiltrar = "";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Reporte";            // modificar
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
  String cUpdStatus  = "Hidden";

  String cCondicion = "";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
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
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
        if(request.getParameter("hdBoton").toString().compareTo("Generar Reporte")==0) {
           out.println(clsConfig.getActiveX());
        }
 %>
  <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="2">Programa a Desplegar</td>
            </tr>
             <tr>
              <td class="EEtiqueta">Ejercicio:</td>
              <td class="ECampo"><select name="iEjercicio"><%
                int iEjercicioIni;
                int iEjercicioAct = clsConfig.iEjercicioAct;
                try{
                  iEjercicioIni = Integer.parseInt(vParametros.getPropEspecifica("EQMAniosIniPrograma"));
                }catch(Exception e){
                  iEjercicioIni = clsConfig.iEjercicio;
                }
                for (int i=iEjercicioAct;i>=iEjercicioIni;i--){
                  if (clsConfig.iEjercicio == i)
                    out.println("<option selected value=\"" + i + "\">" + i + "</option>");
                  else
                    out.println("<option value=\"" + i + "\">" + i + "</option>");
                }
              %></select></td>
             </tr>
             <tr>
              <td class="EEtiqueta">Tipo de Mantenimiento:</td>
              <td class="ECampo"><select name="iCveTpoMantto"><%
                Vector vTipos = clsConfig.getVTipoMantto();
                TVEQMTpoMantto VTpoMantto;
                for (int i=0; i<vTipos.size(); i++){
                  VTpoMantto = (TVEQMTpoMantto)vTipos.get(i);
                  if (clsConfig.iTipoMantto == VTpoMantto.getICveTpoMantto())
                    out.println("<option selected value=\"" + VTpoMantto.getICveTpoMantto() + "\">" + VTpoMantto.getCDscTpoMantto() + "</option>");
                  else
                    out.println("<option value=\"" + VTpoMantto.getICveTpoMantto() + "\">" + VTpoMantto.getCDscTpoMantto() + "</option>");
                }
              %></select></td>
             </tr>
            <tr>
              <td class="EEtiqueta">Ordernar Resultado por:</td>
              <td class="ECampo">
                <table border="0" cellspacing="0" cellpadding="2">
                  <% int iOrden = clsConfig.iValorOrden; %>
                  <tr><td class="ECampo"><input type="radio" name="RSTOrdenar" value="1"<%=iOrden==1?" checked ":""%>>&nbsp; Equipo</td></tr>
                  <tr><td class="ECampo"><input type="radio" name="RSTOrdenar" value="2"<%=iOrden==2?" checked ":""%>>&nbsp; Marca</td></tr>
                  <tr><td class="ECampo"><input type="radio" name="RSTOrdenar" value="3"<%=iOrden==3?" checked ":""%>>&nbsp; Modelo</td></tr>
                  <tr><td class="ECampo"><input type="radio" name="RSTOrdenar" value="4"<%=iOrden==4?" checked ":""%>>&nbsp; Localización</td></tr>
                  <tr><td class="ECampo"><input type="radio" name="RSTOrdenar" value="5"<%=iOrden==5?" checked ":""%>>&nbsp; Periodo</td></tr>
               </table>
             </tr>
          </table>
          &nbsp;
      </td>
  </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

