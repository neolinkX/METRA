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
  pg070106035CFG  clsConfig = new pg070106035CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070106035.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070106035.jsp\" target=\"FRMCuerpo"); // modificar
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
  boolean lFiltros    = true;                 // modificar
  boolean lIra        = false;                 // modificar
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
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden"; //clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  String dFechaActual=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):new TFechas().getFechaDDMMYYYY(new java.sql.Date(new java.util.Date().getTime()),"/");
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
  <!--input type="hidden" name="FILNumReng" value="< % if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));% >"-->
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.pageNo()):""%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdICveExpediente" value="">
  <input type="hidden" name="hdINumExamen" value="">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("dtConcluido")!=null?request.getParameter("dtConcluido"):request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("iCveModulo")!=null?request.getParameter("iCveModulo"):request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("iCveProceso")!=null?request.getParameter("iCveProceso"):request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("dtConcluido")!=null?request.getParameter("dtConcluido"):request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
      String cTmp = request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):"0";
      Vector vGModulo = new TDGRLModulo().FindByAll2("where iCveUniMed = " + cTmp);
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td colspan="4" class="ETablaT">Dictamenes Concluidos</td></tr>
        <tr>
          <td class="EEtiqueta">Unidad M&eacute;dica:</td>
          <td class="ETabla">
            <%=vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",vUsuario.getVUniNoDup(),"iCveUniMed","cDscUniMed",request,cTmp,true)%>
          </td>
          <td class="EEtiqueta">M&oacute;dulo:</td>
          <td class="ETabla"><select name="iCveModulo" size="1">
            <option value="0"><%=vGModulo.size()>0?"Seleccione...":"No Existen Datos Relacionados"%></option>
<%    cTmp=request.getParameter("iCveModulo")!=null?request.getParameter("iCveModulo"):request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):"0";
      for(int x=0;x<vGModulo.size();x++){
        TVGRLModulo vGRLModulo=(TVGRLModulo)vGModulo.get(x);
%>            <option value="<%=vGRLModulo.getICveModulo()%>"<%=cTmp.equals(Integer.toString(vGRLModulo.getICveModulo()))?" selected":""%>><%=vGRLModulo.getCDscModulo()%></option>
<%    }
%>          </select></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Proceso:</td>
          <td class="ETabla" colspan = "3"><select name="iCveProceso" size="1">
<%    cTmp=request.getParameter("iCveUniMed")!=null?request.getParameter("iCveUniMed"):request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):"0";
      int iCveUniMed=cTmp!=null?Integer.parseInt(cTmp):Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
      Vector vProceso=vUsuario.getVUnidades();
%>            <option value="0"><%=vProceso.size()>0?"Seleccione...":"No Existen Datos Relacionados"%></option>
<%    cTmp=request.getParameter("iCveProceso")!=null?request.getParameter("iCveProceso"):request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):"0";
      for(int w=0;w<vProceso.size();w++){
        TVGRLUMUsuario vGRLUMUsu = (TVGRLUMUsuario) vProceso.get(w);
        if(vGRLUMUsu.getICveUniMed()==iCveUniMed){
%>            <option value="<%=vGRLUMUsu.getICveProceso()%>"<%=cTmp.equals(Integer.toString((vGRLUMUsu.getICveProceso())))?" selected":""%>><%=vGRLUMUsu.getCDscProceso()%></option>
<%      }
      }
%>          </select></td>
        </tr>
<!--    <tr></%= vEti.EtiCampoCS("EEtiqueta", "Fecha: ", "ECampo", "text", 10, 10,3,"dtConcluido", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request)%/></tr> -->
        <tr><%=vEti.EtiCampoCS("EEtiqueta", "Fecha desde: ", "ECampo", "text", 10, 10,1,"dtDesde", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request)%>
            <%=vEti.EtiCampoCS("EEtiqueta", "Hasta: ", "ECampo", "text", 10, 10,1,"dtHasta", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request)%></tr>
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
  if(clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() > 0){
     out.println(clsConfig.getSbReporte());
  }
  else if(request.getParameter("hdBoton") != null && request.getParameter("hdBoton").equals("Generar Reporte") &&
    clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() == 0){
%><script language="JavaScript">alert("No Hay Registros Coincidentes.");</script>
<%
  }
%>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>