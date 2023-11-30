<%/**
 * Title: Listado de Causa
 * Description: JSP para mostrar el Listado de Causa
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="java.util.*"%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<html>
<%
  pg070401030CFG clsConfig=new pg070401030CFG();                      // modificar
  TEntorno       vEntorno =new TEntorno();
  vEntorno.setNumModulo      (07);
  vEntorno.setNombrePagina   ("pg070401030.jsp");                     // modificar
  vEntorno.setArchTooltips   ("07_Tooltips");
  vEntorno.setOnLoad         ("fOnLoad();");
  vEntorno.setArchFuncs      ("FValida");
  vEntorno.setMetodoForm     ("POST");
  vEntorno.setActionForm     ("pg070401030.jsp\" target=\"FRMCuerpo");// modificar
  vEntorno.setUrlLogo        ("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo  ("FFiltro");
  vEntorno.setArchAyuda      (vEntorno.getNombrePagina());

  String cCatalogo   ="pg070401031.jsp";       // modificar
  String cOperador   ="1";                     // modificar
  String cDscOrdenar ="Clave|Descripción|";    // modificar
  String cCveOrdenar ="iCveCausa|cDscCausa|";  // modificar
  String cDscFiltrar ="Clave|Descripción|";    // modificar
  String cCveFiltrar ="iCveCausa|cDscCausa|";  // modificar
  String cTipoFiltrar="7|8|";                  // modificar
  boolean lFiltros   =true;                    // modificar
  boolean lIra       =true;                    // modificar
  String cEstatusIR  ="Imprimir";              // modificar

  // LLamado al Output Header
  //StringBuffer sbErroresAcum=new StringBuffer();
  TError    vErrores=new TError();
  TEtiCampo vEti    =new TEtiCampo();

  clsConfig.outputHeader(vErrores,pageContext,vEntorno,request);

  PageBeanScroller bs =clsConfig.getBeanScroller();
  String cPaginas     =clsConfig.getPaginas();
  String cDscPaginas  =clsConfig.getDscPaginas();
  String cUpdStatus   ="Hidden";
  String cNavStatus   =clsConfig.getNavStatus();
  String cOtroStatus  =clsConfig.getOtroStatus();
  String cCanWrite    ="No";
  String cSaveAction  ="Guardar";
  String cDeleteAction="BorrarB";
  String cClave       ="";

  TParametro vParametros=new TParametro(vEntorno.getNumModuloStr());
  //int iNumRowsPrin=Integer.parseInt(vParametros.getPropEspecifica("NumRowsPrin"));
  //int iNumRowsSec =Integer.parseInt(vParametros.getPropEspecifica("NumRowsSec" ));
  //String cRutaImg =vParametros.getPropEspecifica("RutaImg");
  //String cTipoImg =vEntorno.getTiposImg().elementAt(0).toString();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
  function fIrCatalogo(iCveCausa){
    var form=document.forms[0];
    form.hdRowNum.value=iCveCausa;
    form.hdICveCausa.value=iCveCausa;
    form.hdBoton.value='Actual';
    form.target='FRMDatos';
    form.action='pg070401031.jsp';
    form.submit();
  }
  function fSubmite(){
    var form=document.forms[0];
    form.target='FRMDatos';
    form.submit();
  }
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<%   /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
    new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros);
%><link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="FILNumReng" value="<%=request.getParameter("FILNumReng")!=null?request.getParameter("FILNumReng"):vParametros.getPropEspecifica("NumRengTab")%>">
  <input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")!=null?request.getParameter("hdLCondicion"):""%>">
  <input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")!=null?request.getParameter("hdLOrdenar"):""%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():0%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdICveTpoCausa" value="<%=request.getParameter("iCveTpoCausa")!=null?request.getParameter("iCveTpoCausa"):"1"%>">
  <input type="hidden" name="hdICveCausa" value="1">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="4">Causa</td></tr>
        <tr>
          <td class="ETablaTR">Tipo:</td>
          <td class="ECampo" colspan="3"><select name="iCveTpoCausa" onchange="fSubmite();">
<%    Vector vTmp=clsConfig.getTpoCausa();
      if(vTmp!=null && vTmp.size()>0){
        for(Enumeration e=vTmp.elements();e.hasMoreElements();){
          TVINVTpoCausa vINVTpoCausa=(TVINVTpoCausa)e.nextElement();
          String cSelected=Integer.toString(vINVTpoCausa.getICveTpoCausa()).equals(request.getParameter("iCveTpoCausa"))?" selected":"";
%>            <option value="<%=vINVTpoCausa.getICveTpoCausa()%>"<%=cSelected%>><%=vINVTpoCausa.getCDscTpoCausa()%></option>
<%      }
      }else{
%>            <option value="0">Datos no disponibles</option>
<%    }
%>          </select></td>
        </tr>
        <tr class="ETablaT">
          <td>Clave</td>
          <td>Descripci&oacute;n</td>
          <td colspan="2">Activo</td>
        </tr>
<%    if(bs!=null){
        bs.start();
        while(bs.nextRow()){
%>        <tr class="ECampo">
          <td><%=bs.getFieldValue("iCveCausa","&nbsp;")%></td>
          <td><%=bs.getFieldValue("cDscCausa","&nbsp;")%></td>
          <td><%="1".equals((String)bs.getFieldValue("lActivo","0"))?"ACTIVO":"INACTIVO"%></td>
<%        if (clsConfig.getLPagina(cCatalogo)){
%>          <td><a class="EAncla" href="javascript:fIrCatalogo('<%=bs.getFieldValue("iCveCausa","0")%>')">Detalle</a></td>
<%        }
%>        </tr>
<%      }
      }else{
%>        <tr class="EEtiqueta"><td>Mensaje:</td><td class="ECampo" colspan="3">No existen datos coincidentes con el filtro proporcionado</td></tr>
<%    }
%>      </table>
    </td></tr>
<%  }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<%  vEntorno.clearListaImgs();
%></html>