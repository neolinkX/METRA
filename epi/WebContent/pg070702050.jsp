<%/**
 * Title:       Solicitud de Mantenimiento
 * Description: Se solicita el Mantenimiento de un Equipo determinado
 * Copyright:   2004
 * Company:     Micros Personales S.A. de C.V.
 * @author      Marco A. Hernández García
 * @version     1.0
 * Clase:       pg070702050CFG
 */%>


<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>
<html>
<%
  pg070702050CFG  clsConfig = new pg070702050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070702050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070702050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070702051.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Núm. Serie|";    // modificar
  String cCveOrdenar  = "VEHUbicacion.iCveVehiculo|VEHVehiculo.cNumSerie|";  // modificar
  String cDscFiltrar  = "Clave|Núm. Serie|";    // modificar
  String cCveFiltrar  = "VEHUbicacion.iCveVehiculo|VEHVehiculo.cNumSerie|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7
  String cFiltro = "";
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
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
          &nbsp;
          <input type="hidden" name="hdBoton" value="">
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
           <tr><td colspan="5" class="ETablaT">Filtro de Vehículos</td></tr>
            <%
              out.println("<tr>");
              out.println("<td class=\"EEtiqueta\">Unidad Médica:</td>");
              out.println("<td>");
              if (vUsuario!=null)
                 out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
              out.println("</td>");
              out.println("</tr>");

              out.println("<tr>");
              out.print(vEti.EtiCampo("EEtiqueta","Placas:","ECampo","text",10,10,"cPlacas", "",0,"","fMayus(this);",false,true,true,request));
              out.println("</tr>");

            %>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="8">Solicitud de Mantenimiento</td>
            </tr>
            <tr>
              <td class="ETablaT">Clave</td>
              <td class="ETablaT">Marca</td>
              <td class="ETablaT">Modelo</td>
              <td class="ETablaT">Núm. Serie</td>
              <td class="ETablaT">Placas</td>
              <td class="ETablaT">Inventario</td>
              <td class="ETablaT">Estado</td>
              <td class="ETablaT" colspan="2">Solicitar</td>
            </tr>
            <%
               boolean pg070702051  = clsConfig.getLPagina("pg070702051.jsp");
               if (bs != null){
                   bs.start();
                   while(bs.nextRow()){
                       out.println("<tr>");
                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveVehiculo", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMarca", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscModelo", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumSerie", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cPlacas", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cInventario", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEstadoVeh", "&nbsp;")));
                       if (pg070702051){
                          out.print("<td class=\"ECampo\">");
                          out.print(vEti.clsAnclaTexto("EAncla","Solicitar","JavaScript:fIrCatalogo('" +
                                                        bs.getFieldValue("iCveVehiculo","") + "','pg070702051.jsp');","Ir a..."));
                          out.print("</td>");
                       }
                   }
               }
            %>
          </table>
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

