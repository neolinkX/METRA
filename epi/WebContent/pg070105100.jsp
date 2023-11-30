<%/**
 * Title: Listado del detalle de Exámenes Concluidos
 * Description: JSP para mostrar el detalle de los exámenes concluidos
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
  pg070105100CFG clsConfig=new pg070105100CFG();                      // modificar
  TEntorno       vEntorno =new TEntorno();
  vEntorno.setNumModulo      (07);
  vEntorno.setNombrePagina   ("pg070105100.jsp");                     // modificar
  vEntorno.setArchTooltips   ("07_Tooltips");
  vEntorno.setOnLoad         ("fOnLoad();");
  vEntorno.setArchFuncs      ("FValida");
  vEntorno.setMetodoForm     ("POST");
  vEntorno.setActionForm     ("pg070105100.jsp\" target=\"FRMCuerpo");// modificar
  vEntorno.setUrlLogo        ("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo  ("FFiltro");
  vEntorno.setArchAyuda      (vEntorno.getNombrePagina());

  String cCatalogo    ="pg070105010.jsp";      // modificar
  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";      // modificar
  String cCveOrdenar  = "0|";                  // modificar
  String cDscFiltrar  = "No Disponible|";      // modificar
  String cCveFiltrar  = "0|";                  // modificar
  String cTipoFiltrar = "8|";                  // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<%   /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */
    new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros);
%><link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")!=null?request.getParameter("hdLCondicion"):""%>">
  <input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")!=null?request.getParameter("hdLOrdenar"):""%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():0%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdICvePerfil" value="<%=request.getParameter("hdICvePerfil")!=null?request.getParameter("hdICvePerfil"):""%>">
  <input type="hidden" name="hdCDscGrupo" value="<%=request.getParameter("hdCDscGrupo")!=null?request.getParameter("hdCDscGrupo"):""%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="2">Consulta de Perfiles</td></tr>
        <tr><td class="EEtiqueta">Perfil:</td><td class="ETabla"><%=request.getParameter("hdICvePerfil")!=null?request.getParameter("hdICvePerfil"):"0"%></td></tr>
        <tr><td class="EEtiqueta">Grupo:</td><td class="ETabla"><%=request.getParameter("hdCDscGrupo")!=null?request.getParameter("hdCDscGrupo"):""%></td></tr>
        <tr>
          <td class="EEtiqueta">Especialidad:</td>
          <td class="ETabla"><select name="iCveEspecialidad">
<%    Vector vcEspec=clsConfig.getEspecialidades();
      if(vcEspec!=null && vcEspec.size()!=0){
%>            <option value="0">Seleccione</option>
<%      String cTmp=request.getParameter("iCveEspecialidad");
        if(cTmp==null) cTmp="";
        for(Enumeration e=vcEspec.elements();e.hasMoreElements();){
          TVMEDEspecialidad vEspec=(TVMEDEspecialidad)e.nextElement();
          String cSelected=cTmp.equals(Integer.toString(vEspec.getICveEspecialidad()))?" selected":"";
%>            <option value="<%=vEspec.getICveEspecialidad()%>"<%=cSelected%>><%=vEspec.getCDscEspecialidad()%></option>
<%      }
      }else{
%>            <option value="0">Datos no disponibles</option>
<%    }
%>          </select></td>
        </tr>
        <tr class="ETablaC"><td colspan="2"><a class="EAncla" href="javascript:fSubmite()">Buscar</a></td></tr>
      </table>
    </td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%    if(bs!=null){
        bs.start();
        while(bs.nextRow()){
%>        <tr class="ETablaT"><td colspan="4">Especificaci&oacute;n</td></tr>
        <tr class="ETablaC"><td colspan="4"><textarea readonly cols="120" rows="6"><%=bs.getFieldValue("cEspecificacion","")%></textarea></td></tr>
        <tr class="ETablaT"><td colspan="4">Diagn&oacute;sticos</td></tr>
        <tr class="ETablaSTC">
          <td>CIE 10</td>
          <td>Descripci&oacute;n</td>
          <td>Diagn&oacute;stico</td>
          <td>Alarma</td>
        </tr>
<%        Vector vcTmp=(Vector)bs.getFieldValue("vcMEDPerfilDiag");
          if(vcTmp!=null){
            for(Enumeration e=vcTmp.elements();e.hasMoreElements();){//vcMEDPerfilDiag
              TVMEDPerfilDiag vMEDPerfilDiag=(TVMEDPerfilDiag)e.nextElement();
%>        <tr class="ETabla">
          <td><%=vMEDPerfilDiag.getCCIE()%></td>
          <td><%=vMEDPerfilDiag.getCDscBreve()%></td>
          <td><%=vMEDPerfilDiag.getCDscDiagnostico()%></td>
          <td align="center"><%=vMEDPerfilDiag.getLAlarma()!=0?"Si":"&nbsp;"%></td>
        </tr>
<%          }
          }else{
%>        <tr class="EResalta"><td colspan="4" align="center">Datos no disponibles</td></tr>
<%        }
        }
      }else{
%>        <tr class="EResalta"><td colspan="4" align="center">Datos no disponibles</td></tr>
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