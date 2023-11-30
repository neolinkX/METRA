<%/**
 * Title: Catálogo de Causa
 * Description: JSP para mostrar el Catálogo de Causa
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
  pg070401031CFG clsConfig=new pg070401031CFG();                      // modificar
  TEntorno       vEntorno =new TEntorno();
  vEntorno.setNumModulo      (07);
  vEntorno.setNombrePagina   ("pg070401031.jsp");                     // modificar
  vEntorno.setArchTooltips   ("07_Tooltips");
  vEntorno.setOnLoad         ("fOnLoad();");
  vEntorno.setArchFuncs      ("FValida");
  vEntorno.setMetodoForm     ("POST");
  vEntorno.setActionForm     ("pg070401031.jsp\" target=\"FRMCuerpo");// modificar
  vEntorno.setUrlLogo        ("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo  ("FFiltro");
  vEntorno.setArchAyuda      (vEntorno.getNombrePagina());

  //String cCatalogo   ="pg070401031.jsp";     // no aplica
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

  BeanScroller bs     =clsConfig.getBeanScroller();
  String cPaginas     =clsConfig.getPaginas();
  String cDscPaginas  =clsConfig.getDscPaginas();
  String cUpdStatus   =clsConfig.getUpdStatus();
  String cNavStatus   =clsConfig.getNavStatus();
  String cOtroStatus  =clsConfig.getOtroStatus();
  String cCanWrite    =clsConfig.getCanWrite();
  String cSaveAction  =clsConfig.getSaveAction();
  String cDeleteAction=clsConfig.getDeleteAction();
  String cClave2      ="";
  String cPropiedad   ="";
  int    iFiltro      =0;

  TParametro vParametros=new TParametro(vEntorno.getNumModuloStr());
  //int iNumRowsPrin=Integer.parseInt(vParametros.getPropEspecifica("NumRowsPrin"));
  //int iNumRowsSec =Integer.parseInt(vParametros.getPropEspecifica("NumRowsSec" ));
  //String cRutaImg =vParametros.getPropEspecifica("RutaImg");
  //String cTipoImg =vEntorno.getTiposImg().elementAt(0).toString();
  String hdICveTpoCausa=request.getParameter("iCveTpoCausa")!=null?request.getParameter("iCveTpoCausa"):request.getParameter("hdICveTpoCausa")!=null?request.getParameter("hdICveTpoCausa"):"1";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
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
  <input type="hidden" name="hdCCondicion" value="<%=request.getParameter("hdCCondicion")!=null?request.getParameter("hdCCondicion"):""%>">
  <input type="hidden" name="hdCOrdenar" value="<%=request.getParameter("hdCOrdenar")!=null?request.getParameter("hdCOrdenar"):""%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.rowNo()):request.getParameter("hdRowNum")!=null?request.getParameter("hdRowNum"):"1"%>">
  <input type="hidden" name="hdICveTpoCausa" value="<%=hdICveTpoCausa%>">
  <input type="hidden" name="hdICveCausa" value="<%=bs!=null?(String)bs.getFieldValue("iCveCausa",""):""%>">
  <input type="hidden" name="hdCPropiedad" value="<%=bs!=null?(String)bs.getFieldValue("cPropiedad",""):""%>">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
      boolean lCaptura = clsConfig.getCaptura();
      boolean lNuevo = clsConfig.getNuevo();
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="2">Causa</td></tr>
        <tr>
          <td class="ETablaTR">Tipo:</td>
          <td class="ECampo"><select name="iCveTpoCausa" onchange="fSubmite(this,<%=lNuevo?"true":"false"%>);"<%=lCaptura&&!lNuevo?" disabled":""%>>
<%    Vector vTmp=clsConfig.getTpoCausa();
      if(vTmp!=null && vTmp.size()>0){
        for(Enumeration e=vTmp.elements();e.hasMoreElements();){
          TVINVTpoCausa vINVTpoCausa=(TVINVTpoCausa)e.nextElement();
          String cSelected=Integer.toString(vINVTpoCausa.getICveTpoCausa()).equals(hdICveTpoCausa)?" selected":"";
%>            <option value="<%=vINVTpoCausa.getICveTpoCausa()%>"<%=cSelected%>><%=vINVTpoCausa.getCDscTpoCausa()%></option>
<%      }
      }else{
%>            <option value="0">Datos no disponibles</option>
<%    }
%>          </select></td>
        </tr>
<%    if(lNuevo||bs!=null){
        String iCveCausa=lNuevo?"":(String)bs.getFieldValue("iCveCausa","");
        String cDscCausa=lNuevo?"":(String)bs.getFieldValue("cDscCausa","");
        String lActivo  =lNuevo?"1":(String)bs.getFieldValue("lActivo"  ,"");
%>        <tr><%=vEti.EtiCampo("EEtiqueta","Clave:","Ecampo","text",5,5,"iCveCausa",iCveCausa,3,"","",false,false,lNuevo||lCaptura)%></tr>
        <tr><%=vEti.EtiCampo("EEtiqueta","Descripci&oacute;n:","Ecampo","text",20,100,"cDscCausa",cDscCausa,3,"","",false,true,lNuevo||lCaptura)%></tr>
        <tr><%=vEti.EtiToggle("EEtiqueta","Activo:","ECampo","lActivo",lActivo,"",3,lNuevo||lCaptura,"ACTIVO","INACTIVO",true)%></tr>
<%    }else{
%>        <tr class="EEtiqueta"><td>Mensaje:</td><td class="ECampo">No existen datos coincidentes con el filtro proporcionado</td></tr>
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