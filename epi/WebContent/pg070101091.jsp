<%/**
 * Title: Catalogo de Tipos de Respuesta
 * Description: Consulta de Tipos de Respuesta
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<html>
<%
  pg070101091CFG  clsConfig = new pg070101091CFG();                // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101091.jsp");                     // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101091.jsp\" target=\"FRMCuerpo");  // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                         // modificar
  String cDscOrdenar  = "Tipo|Descripción|";         // modificar
  String cCveOrdenar  = "iCveTpoResp|cDscTpoResp|";  // modificar
  String cDscFiltrar  = "Tipo|Descripción|";         // modificar
  String cCveFiltrar  = "iCveTpoResp|cDscTpoResp|";  // modificar
  String cTipoFiltrar = "7|8|";                      // modificar
  boolean lFiltros    = true;                        // modificar
  boolean lIra        = true;                        // modificar
  String cEstatusIR   = "Imprimir";                  // modificar

  // LLamado al Output Header
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">
  function fValidaTodo(){
    var form = document.forms[0];
    if(form.hdBoton.value != 'Cancelar') {
      cVMsg = '';
//      if (form.iCveTpoResp)
//        cVMsg = cVMsg + fSinValor(form.iCveTpoResp,3,'Tipo', true);
      if (form.cDscTpoResp){
        cVMsg = cVMsg + fSinValor(form.cDscTpoResp,2,'Descripción', true);
        fMayus(form.cDscTpoResp);
      }
      if (form.cCampo){
        cVMsg = cVMsg + fSinValor(form.cCampo,2,'Campo', true);
        //fMayus(form.cCampo);
      }
      if (form.lActivo)
        cVMsg = cVMsg + fSinValor(form.lActivo,3,'Activo', true);
        if (cVMsg != ''){
          alert("Datos no Válidos: \n" + cVMsg);
          return false;
        }
    }
    if (form.hdBoton.value == 'Borrar' || form.hdBoton.value == 'BorrarB') {
      if(!confirm("¿Está Seguro de Eliminar los Registro seleccionados?"))
        return false;
    }
    if (form.hdBoton.value == 'Guardar' || form.hdBoton.value == 'GuardarA') {
      if(!confirm("¿Está Seguro de Guardar la Información?"))
        return false;
    }
     return true;
   }
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
<%  if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
      cPosicion = Integer.toString(bs.rowNo());
      cClave2  = ""+bs.getFieldValue("iCveTpoResp", "");
      cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
    }
%>  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td colspan="2" class="ETablaT">Tipo de Respuesta</td></tr>
<%    boolean lCaptura = clsConfig.getCaptura();
      if(clsConfig.getNuevo()){
%>        <tr><%=vEti.EtiCampo("EEtiqueta","Tipo:","ECampo","text",5,5,"iCveTpoResp","",3,"","",false,false,true)%></tr>
        <tr><%=vEti.EtiCampo("EEtiqueta","Descripci&oacute;n:","ECampo","text",25,25,"cDscTpoResp","",3,"","",false,true,true)%></tr>
        <tr><%=vEti.EtiCampo("EEtiqueta","Campo:","ECampo","text",20,20,"cCampo","",3,"","",false,true,true)%></tr>
        <tr><%=vEti.EtiToggle("EEtiqueta","Activo:","Ecampo","lActivo","0","",0,true,"Activo","Inactivo",true)%></tr>
<%    }else if (bs != null){
%>        <tr><%=vEti.EtiCampo("EEtiqueta","Tipo:","ECampo","text",5,5,"iCveTpoResp",bs.getFieldValue("iCveTpoResp","&nbsp;").toString(),3,"","",false,false,lCaptura)%></tr>
        <tr><%=vEti.EtiCampo("EEtiqueta","Descripci&oacute;n:","ECampo","text",25,25,"cDscTpoResp",bs.getFieldValue("cDscTpoResp","&nbsp;").toString(),3,"","",false,true,lCaptura)%></tr>
        <tr><%=vEti.EtiCampo("EEtiqueta","Campo:","ECampo","text",20,20,"cCampo",bs.getFieldValue("cCampo","&nbsp;").toString(),3,"","",false,true,lCaptura)%></tr>
        <tr><%=vEti.EtiToggle("EEtiqueta","Activo:","Ecampo","lActivo",bs.getFieldValue("lActivo","0").toString(),"",0,lCaptura,"Activo","Inactivo",true)%></tr>
<%    }else{
%>        <tr><td colspan="2">Datos no disponibles</td></tr>
<%    }
%>      </table>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>