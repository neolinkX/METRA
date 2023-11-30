<%/**
 * Title: Listado de Recomendación
 * Description: JSP para mostrar el Listado de Recomendación
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
  pg070401050CFG clsConfig=new pg070401050CFG();                      // modificar
  TEntorno       vEntorno =new TEntorno();
  vEntorno.setNumModulo      (07);
  vEntorno.setNombrePagina   ("pg070401050.jsp");                     // modificar
  vEntorno.setArchTooltips   ("07_Tooltips");
  vEntorno.setOnLoad         ("fOnLoad();");
  vEntorno.setArchFuncs      ("FValida");
  vEntorno.setMetodoForm     ("POST");
  vEntorno.setActionForm     ("pg070401050.jsp\" target=\"FRMCuerpo");// modificar
  vEntorno.setUrlLogo        ("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo  ("FFiltro");
  vEntorno.setArchAyuda      (vEntorno.getNombrePagina());

  String cCatalogo   ="pg070401051.jsp";                       // modificar
  String cOperador   ="1";                                     // modificar
  String cDscOrdenar ="Clave|Descripción|";                    // modificar
  String cCveOrdenar ="iCveRecomendacion|cDscRecomendacion|";  // modificar
  String cDscFiltrar ="Clave|Descripción|";                    // modificar
  String cCveFiltrar ="iCveRecomendacion|cDscRecomendacion|";  // modificar
  String cTipoFiltrar="7|8|";                                  // modificar
  boolean lFiltros   =true;                                    // modificar
  boolean lIra       =true;                                    // modificar
  String cEstatusIR  ="Imprimir";                              // modificar

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
</SCRIPT>
<%   /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
    new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros);
%><link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="FILNumReng" value="<%=request.getParameter("FILNumReng")!=null?request.getParameter("FILNumReng"):vParametros.getPropEspecifica("NumRengTab")%>">
  <input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")!=null?request.getParameter("hdLCondicion"):""%>">
  <input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")!=null?request.getParameter("hdLOrdenar"):""%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():1%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){%>
  <tr><td align="center" width="100%">
     <%=vEti.clsAnclaTexto("EAncla","Generar Archivo JS","JavaScript:fGenArchivo();", "Generar Archivo JS","")%>
     <%
        if(request.getParameter("hdBoton").compareTo("GenJS") == 0 ){
           if(new TDINVRecomendacion().genJS()){
             vErrores.acumulaError("",0,"El archivo JS se ha generado exitosamente!");
           }else{
             vErrores.acumulaError("",0,"El archivo JS no ha sido generado. Consulte a su área de Sistemas!");
           }
        }
     %>
  </td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="6">Recomendaci&oacute;n</td></tr>
        <tr class="ETablaT">
          <td>Clave</td>
          <td>Descripci&oacute;n</td>
          <td>Clave Interna</td>
          <td colspan="2">Activo</td>
        </tr>
<%    if(bs!=null){
        bs.start();
        while(bs.nextRow()){
%>        <tr class="ECampo">
          <td><%=bs.getFieldValue("iCveRecomendacion","&nbsp;")%></td>
          <td><%=bs.getFieldValue("cDscRecomendacion","&nbsp;")%></td>
          <td><%=bs.getFieldValue("cCveInterna","&nbsp;")%></td>
          <td><%="1".equals((String)bs.getFieldValue("lActivo","0"))?"ACTIVO":"INACTIVO"%></td>
<%        if (clsConfig.getLPagina(cCatalogo)){
%>          <td><a class="EAncla" href="javascript:fIrCatalogo('<%=bs.getFieldValue("iCveRecomendacion","0")%>')">Detalle</a></td>
<%        }
%>        </tr>
<%      }
      }else{
%>        <tr class="EEtiqueta"><td>Mensaje:</td><td class="ECampo" colspan="4">No existen datos coincidentes con el filtro proporcionado</td></tr>
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