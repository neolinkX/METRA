<%/**
 * Title: Listado de Investigaciones por Unidad Médica
 * Description: JSP para mostrar el Listado de Investigaciones por Unidad Médica
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
  pg070402010CFG clsConfig=new pg070402010CFG();                      // modificar
  TEntorno       vEntorno =new TEntorno();
  vEntorno.setNumModulo      (07);
  vEntorno.setNombrePagina   ("pg070402010.jsp");                     // modificar
  vEntorno.setArchTooltips   ("07_Tooltips");
  vEntorno.setOnLoad         ("fOnLoad();");
  vEntorno.setArchFuncs      ("FValida");
  vEntorno.setMetodoForm     ("POST");
  vEntorno.setActionForm     ("pg070402010.jsp\" target=\"FRMCuerpo");// modificar
  vEntorno.setUrlLogo        ("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo  ("FFiltro");
  vEntorno.setArchAyuda      (vEntorno.getNombrePagina());

  String cCatalogo   ="pg070402011.jsp";                                       // modificar
  String cOperador   ="1";                                                     // modificar
  String cDscOrdenar ="ID DGPMPT|ID Mdo Transporte|Fecha Accidente|Lugar|";  // modificar
  String cCveOrdenar ="iIDDGPMPT|iIDMdoTrans|dtAccidente|cLugar|";                    // modificar
  String cDscFiltrar ="ID DGPMPT|ID Mdo Transporte|Fecha Accidente|Lugar|";  // modificar
  String cCveFiltrar ="iIDDGPMPT|iIDMdoTrans|dtAccidente|cLugar|";                    // modificar
  String cTipoFiltrar="7|7|5|8";                                                 // modificar
  boolean lFiltros   =true;                                                    // modificar
  boolean lIra       =true;                                                    // modificar
  String cEstatusIR  ="Imprimir";                                              // modificar

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
  String cCanWrite    =clsConfig.getCanWrite();
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
  <input type="hidden" name="FILNumReng" value="<%=request.getParameter("FILNumReng")!=null?request.getParameter("FILNumReng"):vParametros.getPropEspecifica("NumRengTab")%>">
  <input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")!=null?request.getParameter("hdLCondicion"):""%>">
  <input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")!=null?request.getParameter("hdLOrdenar"):""%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():1%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iAnio" value="">
  <input type="hidden" name="iCveMdoTrans" value="">
  <input type="hidden" name="iIDDGPMPT" value="">
  <input type="hidden" name="iCveModulo" value="">


<%-- inicio de los campos para saltar a las paginas pg070402012 y pg070402014--%>
  <input type="hidden" name="hdiAnio" value="">
  <input type="hidden" name="hdiCveMdoTrans" value="">
  <input type="hidden" name="hdiIDDGPMPT" value="">
  <input type="hidden" name="hdiIdefMedPrev" value="">
<%-- fin de los campos para saltar a las paginas pg070402012 y pg070402014--%>
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="12">Investigaciones por Unidad M&eacute;dica</td></tr>
        <tr>
          <td class="ETablaTR">Año:</td>
          <td class="ECampo"><select name="iAnioSel">
            <option value="0">Seleccione...</option>
<%    int iAnioFin=java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
      int iAnioIni=iAnioFin-Integer.parseInt(vParametros.getPropEspecifica("Anios"));
      do{
        String selected=Integer.toString(iAnioFin).equals(request.getParameter("iAnioSel"))?" selected":"";
%>            <option value="<%=iAnioFin%>"<%=selected%>><%=iAnioFin%></option>
<%    }while(iAnioFin-->iAnioIni);
%>          </select></td>
          <td class="ETablaTR" colspan="2">Modo de Transporte:</td>
          <td class="ECampo"><select name="iCveMdoTransSel">
<%    Vector vTmp=clsConfig.getMdoTrans();
%>            <option value="0"><%=(vTmp!=null && vTmp.size()!=0)?"Seleccione...":"Sin datos disponibles"%></option>
<%    if(vTmp!=null && vTmp.size()!=0){
        for(Enumeration e=vTmp.elements();e.hasMoreElements();){
          TVGRLMdoTrans vGRLMdoTrans=(TVGRLMdoTrans)e.nextElement();
          String selected=Integer.toString(vGRLMdoTrans.getICveMdoTrans()).equals(request.getParameter("iCveMdoTransSel"))?" selected":"";
%>            <option value="<%=vGRLMdoTrans.getICveMdoTrans()%>"<%=selected%>><%=vGRLMdoTrans.getCDscMdoTrans()%></option>
<%      }
      }
%>          </select></td>
          <td class="ETablaTR">Unidad M&eacute;dica:</td>
          <td class="ECampo" colspan="6"><select name="iCveUniMedSel">
<%    vTmp=clsConfig.getUniMeds();
%>            <option value="0"><%=(vTmp!=null && vTmp.size()!=0)?"Sin Asignar...":"Sin datos disponibles"%></option>
<%    if(vTmp!=null && vTmp.size()!=0){
        for(Enumeration e=vTmp.elements();e.hasMoreElements();){
          TVGRLUniMed vGRLUniMed=(TVGRLUniMed)e.nextElement();
          String selected=Integer.toString(vGRLUniMed.getICveUniMed()).equals(request.getParameter("iCveUniMedSel"))?" selected":"";
%>            <option value="<%=vGRLUniMed.getICveUniMed()%>"<%=selected%>><%=vGRLUniMed.getCDscUniMed()%></option>
<%      }
      }
%>          </select></td>
        </tr>
        <tr class="ETablaT">
          <td>ID DGPMPT</td>
          <td>Módulo</td>
          <td>ID Mdo Transporte</td>
          <td>Fecha Accidente</td>
          <td>Fecha Notificaci&oacute;n</td>
          <td>Descripci&oacute;n Breve</td>
          <td>Lugar</td>
          <td colspan="5">Investigado</td>
        </tr>
<%
	if(bs!=null){
        bs.start();
        com.micper.util.TFechas tFecha=new com.micper.util.TFechas();
        TVINVRegistro vINVRegistro=null;
        String cPostFijo=null;
        while(bs.nextRow()){
          vINVRegistro=(TVINVRegistro)bs.getCurrentBean();
          cPostFijo=vINVRegistro.getIAnio()+"|"+vINVRegistro.getICveMdoTrans()+"|"+vINVRegistro.getIIDDGPMPT();
%>        <tr class="ETablaC" valign="top">
          <td><%=bs.getFieldValue("iIDDGPMPT","&nbsp;")%></td>
          <td><%=bs.getFieldValue("cDscModulo","&nbsp;")%></td>
          <td><%=bs.getFieldValue("iIDMdoTrans","&nbsp;")%></td>
          <td><%=vINVRegistro.getDtAccidente()!=null?tFecha.getFechaDDMMYYYY(vINVRegistro.getDtAccidente(),""):"&nbsp;"%></td>
          <td><%=vINVRegistro.getDtNotifica ()!=null?tFecha.getFechaDDMMYYYY(vINVRegistro.getDtNotifica (),""):"&nbsp;"%></td>
          <td align="left"><%=bs.getFieldValue("cDscBreve","&nbsp;")%></td>
          <td align="left"><%=bs.getFieldValue("cLugar","&nbsp;")%></td>
<%        if(vINVRegistro.getLInvestigado()==1 && vINVRegistro.getLCancelado()==0){
%>            <td align="center">INVESTIGADO</td>
<%        }else if(vINVRegistro.getLInvestigado()==1 && vINVRegistro.getLCancelado()==1){
%>            <td align="center">CANCELADO</td>
<%        }else{
%>          <td align="left">
            <input type="radio" name="rbStatus|<%=cPostFijo%>" value=0 checked>Ninguno<br>
            <input type="radio" name="rbStatus|<%=cPostFijo%>" value=1>Investigado<br>
            <input type="radio" name="rbStatus|<%=cPostFijo%>" value=2>Cancelado
          </td>
<%        }
          if (clsConfig.getLPagina(cCatalogo)){
            String cTmp=vINVRegistro.getPK().getFields().toString();
            cTmp=cTmp.substring(1,cTmp.length()-1);
%>        <td><a class="EAncla" href="javascript:fIrCatalogo(<%=cTmp%>,11)">Detalle</a></td>
          <td><a class="EAncla" href="javascript:fIrCatalogo(<%=cTmp%>,20)">Asignar</a></td>
          <td><a class="EAncla" href="javascript:fIrCatalogo(<%=cTmp%>,12)">Veh&iacute;culos</a></td>
<%          if(vINVRegistro.getICveModulo() > 0){%>
          <td><a class="EAncla" href="javascript:fIrCatalogo(<%=cTmp%>,14,<%=""+vINVRegistro.getICveModulo()%>)">Personal</a></td>
<%          }else{
               out.print("<td class=\"ECampo\">Personal</td>");
            }
%>

<%
          }
%>        </tr>
<%      }
%>        <tr class="ETablaC"><td colspan="12"><a class="EAncla" href="javascript:fGuardar()">Guardar</a></td></tr>
<%    }else{
%>        <tr class="EEtiqueta"><td>Mensaje:</td><td class="ECampo" colspan="11">No existen datos coincidentes con el filtro proporcionado</td></tr>
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