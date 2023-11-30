<%/**
 * Title: Listado de Turnos de Validación
 * Description: Consulta de citas
 * Copyright: ?
 * Company: Micros Personales S.A. de C.V.
 * @author Esteban Viveros
 * @version 1
 * Clase: ?
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.util.caching.*"%>
<html>
<%
  pg070102030CFG clsConfig = new pg070102030CFG();                // modificar Ok

  TEntorno       vEntorno  = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070102030.jsp");                    // modificar Ok
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070102030.jsp\" target=\"FRMCuerpo"); // modificar Ok
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  String cCatalogo    = "pg070102031.jsp";           // modificar Ok
  String cOperador    = "1";                         // modificar ?
  String cDscOrdenar  = "Sustancia|";                // modificar Ok
  String cCveOrdenar  = "cDscSustancia|";            // modificar Ok
  String cDscFiltrar  = "Sustancia|";                // modificar Ok
  String cCveFiltrar  = "cDscSustancia|";            // modificar Ok
  String cTipoFiltrar = "8|";                        // modificar Ok
  boolean lFiltros    = false;                       // modificar Ok
  boolean lIra        = false;                       // modificar Ok
  String cEstatusIR   = "Imprimir";                  // modificar ?

  // LLamado al Output Header
  TParametro vParametros = new TParametro(vEntorno.getNumModuloStr());
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
    String iCveProceso = vParametros.getPropEspecifica("EPIProceso");
%>
<head>
<meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070103040.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"PermCombos.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }
</SCRIPT>
</head>
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>" onSubmit="return fOnSubmit();">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?bs.pageNo():0%>">
  <input type="hidden" name="hdCveUniMed" value="">
  <input type="hidden" name="hdCveModulo" value="">
  <input type="hidden" name="hdFecha" value="">
  <input type="hidden" name="hdCveCita" value="">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="Primero">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%

  TFechas oFecha = new TFechas();
   String cHoy = oFecha.getFechaDDMMYYYY(oFecha.TodaySQL(),"/");

   if(clsConfig.getAccesoValido()){
      java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd,MM,yyyy");
      String cDate=sdf.format(new java.util.Date());
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr><td class="ETablaT" colspan="5">Consulta de Citas</td></tr>
      <tr>
        <td class="EEtiqueta">Unidad M&eacute;dica:</td>
        <td class="ETabla">
          <%= vEti.SelectOneRowSinTD("iCveUniMed","{forma = document.forms[0];fActualizaCombo('3',forma,this,forma.iCveModulo,this.value,0," + iCveProceso + " );}",  vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0",true)%>
          <% int iCveUniMed = (request.getParameter("iCveUniMed") != null && request.getParameter("iCveUniMed").toString().length() > 0) ? Integer.parseInt(request.getParameter("iCveUniMed").toString()) : 0;
          %>
        </td>
        <td class="EEtiqueta">M&oacute;dulo:</td>
        <td class="ETabla"><%=vEti.SelectOneRowSinTD("iCveModulo","fActualizaHora(this.form);",  vUsuario.getModuloFUP(iCveUniMed,Integer.parseInt(iCveProceso)),"iClave","cDescripcion",request,"0",true)%>
        </td>
        </td>
        <td class="EEtiquetaF" valign="middle" rowspan="2">
          <%
          out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar","javascript:fSubmite();","Buscar"));
          %>
          <%//=new TCreaBoton().clsCreaBoton(vEntorno,3,"Buscar","bBuscar",vEntorno.getTipoImg(),"Buscar","javascript:fSubmite();",vParametros)%>
        </td>
      </tr>
      <tr>
        <td class="EEtiqueta">Fecha:</td>
        <td class="Etabla">
           <input type="text" size="12" maxlength="10" name="dtFecha" value="<%=request.getParameter("dtFecha")!=null?request.getParameter("dtFecha"):cHoy%>" onBlur="fActualizaHora(this.form)">
          <a class="EAncla" name="calendario" href="JavaScript:fLoadCal(<%=cDate%>,document.forms[0].dtFecha);document.forms[0].dtFecha.focus();">(dd/mm/aaaa)</a>
        </td>
        <td class="EEtiqueta">Hora:</td>
        <td class="ETabla"><select name="cHora">
<%    Vector vcCombo=clsConfig.getHorasDeCitas();
      if(vcCombo.size()>0){
        String cSelected="";
        String cNoSelected=" selected";
        for(Enumeration e=vcCombo.elements();e.hasMoreElements();){
          TVDinamico dCombo=(TVDinamico)e.nextElement();
          cSelected=dCombo.get("cIndice").equals(request.getParameter("cHora"))?" selected":"";
          if(cSelected.length()!=0) cNoSelected="";
%>          <option value="<%=dCombo.get("cIndice")%>"<%=cSelected%>><%=dCombo.get("cDescripcion")%></option>
<%      }
%>          <option value=""<%=cNoSelected%>>TODOS</option>
<%    }else{
%>          <option value="">Datos no disponibles...</option>
<%    }
%>        </select></td>
      </tr></table>
    </td></tr>
    <tr><td valign="top"><table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr class="ETablaT">
        <td>Cita</td>
        <td>RFC</td>
        <td>Nombre</td>
        <td>Hora</td>
        <td>Motivo</td>
        <td>Situaci&oacute;n</td>
      </tr>
<%    if(bs!=null){
        bs.start();
        sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
        while(bs.nextRow()){
          String cTmp=bs.getFieldValue("iCveCita","&nbsp;").toString();
          if(clsConfig.getLPagina(cCatalogo)){
            java.sql.Date dtTmp=((TVEPICisCita)bs.getCurrentBean()).getDtFecha();
            if(dtTmp==null) dtTmp=new java.sql.Date(new Date().getTime());
            String cLink=bs.getFieldValue("iCveUniMed","")+"','"+bs.getFieldValue("iCveModulo","")+"','"+sdf.format(dtTmp)+"','"+bs.getFieldValue("iCveCita","");
            cTmp=vEti.clsAnclaTexto("EAncla",cTmp,"JavaScript:fIrCatalogo('"+cLink+"','pg070102031');","").toString();
          }
%>      <tr class="ETabla">
        <td><%=cTmp%></td>
        <td><%=bs.getFieldValue("cRFC"         ,"&nbsp;")%></td>
        <td><%=bs.getFieldValue("cNombre"      ,"&nbsp;")+" "+bs.getFieldValue("cApPaterno","&nbsp;")+" "+bs.getFieldValue("cApMaterno","&nbsp;")%></td>
        <td><%=bs.getFieldValue("cHora"        ,"&nbsp;")%></td>
        <td><%=bs.getFieldValue("cDscMotivo"   ,"&nbsp;")%></td>
        <td><%=bs.getFieldValue("cDscSituacion","&nbsp;")%></td>
      </tr>
<%      }
      }else{
%>      <tr class="EEtiquetaC" align="center"><td colspan="6">Datos no Disponibles.</td></tr>
<%    }
%>    </table></td></tr>
<%  }else{
%>  <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();
%></html>
