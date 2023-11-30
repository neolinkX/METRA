<%/**
 * Title: pg070306081
 * Description:Listado Caputra de datos de calibracion
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.util.logging.*"%>
<%@ page import="java.sql.*"%>

<html>
<%
  pg070306081CFG  clsConfig = new pg070306081CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070306081.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070306081.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070306081.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripcion|Activo|";    // modificar
  String cCveOrdenar  = "iCveMarcaSust|cDscMarcaSust|lActivo|";  // modificar
  String cDscFiltrar  = "Clave|Descripcion|Activo|";    // modificar
  String cCveFiltrar  = "iCveMarcaSust|cDscMarcaSust|lActivo|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
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
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  //int    iRegsSol = 0;
  //int    iAnio = 0;
  //int    iCveAbsorvancia = 0;
  //String cRegsSol = "";
  //String cFecha = "";
  //String cAnio = "";
  //String cCveAbsorvancia = "";
  //String cBoton = "";
  //String cEquipo = "";
  //String cObservacion = "";
  //String cUser = "";


  //cCveAbsorvancia = (String)request.getAttribute("cClave");
  //cRegsSol = request.getParameter("nOMuestras");

  //if (request.getParameter("iAnio")!=null && request.getParameter("iAnio").toString().compareTo("")!=0){
     //cAnio = request.getParameter("iAnio");
     //iAnio = Integer.parseInt(cAnio);
  //}
  //cCveAbsorvancia = request.getParameter("iCveAbsorvancia");
  //cBoton = request.getParameter("hdBotonAlt");
  //cEquipo = request.getParameter("iCveEquipo");
  //cObservacion = request.getParameter("cObservacion");
  //cUser = request.getParameter("iCveUsuResp");

  //if (cRegsSol != null && cRegsSol.length()>0){
     //iRegsSol = Integer.parseInt(cRegsSol);
  //}
//int cSpan1 = 2;
//int iCont = 0;
//Vector vTOXListaSust = new Vector();
//Vector vTOXARes = new Vector();
//DAOTOXSustancia dSustancia = new DAOTOXSustancia();
//TDTOXAbsorMuestra dAbsRes = new TDTOXAbsorMuestra();
//TVTOXSustancia vTOXMuestraItera = new TVTOXSustancia();
//TVTOXAbsorMuestra  vTOXAbsRes = new TVTOXAbsorMuestra();
//vTOXListaSust = dSustancia.FindByAll();

//cSpan1 = 6+vTOXListaSust.size();
//cFecha = request.getParameter("dtEstudio");
String cSinEspacio = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
function fIrCatalogo(cCampoClave, cPropiedad){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCPropiedad.value = cPropiedad;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070301111.jsp';
    form.submit();
  }
  function fValidaTodo(){
    var form = document.forms[0];
       if (form.hdBoton.value == "Guardar" || form.hdBoton.value == "GuardarA"){
           if (confirm("Desea Salvar?")){
              return true;
           }else{
              return false;
           }
      }else{
         return true;
      }
}

  // Agregado por Rafael Alcocer Caldera 01/Nov/2006
  // ***********************************************
  function openXLS(tipo) {
    form = document.forms[0];
    form.target = "_self";

    if (tipo == 1) {
        form.hdReporte.value = 'VPAT1';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 2) {
        form.hdReporte.value = 'VPAT2';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 3) {
        form.hdReporte.value = 'VPAT3';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 4) {
        form.hdReporte.value = 'VPAT4';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 5) {
        form.hdReporte.value = 'VPAT5';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 6) {
        form.hdReporte.value = 'VPAT6';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 7) {
        form.hdReporte.value = 'VPAT7';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 8) {
        form.hdReporte.value = 'VPAF1';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 9) {
        form.hdReporte.value = 'VPAF2';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 10) {
        form.hdReporte.value = 'VPAF3';
        form.hdBoton.value = 'Reporte';
    } else if (tipo == 11) {
        form.hdReporte.value = 'VPAF4';
        form.hdBoton.value = 'Reporte';
    }

    form.submit();
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
  <input type="hidden" name="iAnio" value="<%=request.getParameter("iAnio")%>">
  <input type="hidden" name="iCveAbsorvancia" value="<%=request.getParameter("iCveAbsorvancia")%>">
  <input type="hidden" name="cBoton" value="<%=request.getParameter("hdBotonAlt")%>">
  <input type="hidden" name="iCveEquipo" value="<%=request.getParameter("iCveEquipo")%>">
  <input type="hidden" name="dtEstudio" value="<%=request.getParameter("dtEstudio")%>">
  <input type="hidden" name="cObservacion" value="<%=request.getParameter("cObservacion")%>">
  <input type="hidden" name="iCveUsuResp" value="<%=request.getParameter("iCveUsuResp")%>">
  <input type="hidden" name="hdTotRows" value="<%=request.getParameter("nOMuestras")%>">
  <input type="hidden" name="nOMuestras" value="<%=request.getParameter("nOMuestras")%>">
  <input type="hidden" name="hdReporte">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
       if("Reporte".compareToIgnoreCase(clsConfig.getAccion()) ==0) {
           out.println(clsConfig.getActiveX());
       }
  %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
  </tr>
  <!-- Agregado por Rafael Alcocer Caldera 01/Nov/2006 -->
  <!-- *********************************************** -->
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAT1", "JavaScript:openXLS(1);", "VPAT1"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAT2", "JavaScript:openXLS(2);", "VPAT2"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAT3", "JavaScript:openXLS(3);", "VPAT3"));%>
      </td>
  </tr>
  <tr>
      <td  class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAT4", "JavaScript:openXLS(4);", "VPAT4"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAT5", "JavaScript:openXLS(5);", "VPAT5"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAT6", "JavaScript:openXLS(6);", "VPAT6"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAT7", "JavaScript:openXLS(7);", "VPAT7"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAF1", "JavaScript:openXLS(8);", "VPAF1"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAF2", "JavaScript:openXLS(9);", "VPAF2"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAF3", "JavaScript:openXLS(10);", "VPAF3"));%>
      </td>
  </tr>
  <tr>
      <td class="ECampoC">
        <% out.println(vEti.clsAnclaTexto("EAncla", "VPAF4", "JavaScript:openXLS(11);", "VPAF4"));%>
      </td>
  </tr>
  <!-- *********************************************** -->
  <% } else { %>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%= vEntorno.getNumModuloStr()%>');</script>
  <% } %>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
