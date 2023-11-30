<%/**
 * Title: Listado de diagnosticos y recomendaciones
 * Description: JSP para mostrar el detalle de los diagnosticos y las recomendaciones por servicios
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
  pg070105050CFG  clsConfig = new pg070105050CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070105050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070105050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070105020.jsp";     // modificar
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
  <!--input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null) out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>"-->
  <input type="hidden" name="FILNumReng" value="200">
  <input type="hidden" name="hdRowNum" value="<%=bs!=null?Integer.toString(bs.pageNo()):""%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveServicio" value="<%=request.getParameter("hdICveServicio")!=null?request.getParameter("hdICveServicio"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdINumExamTmp" value="">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%  if(clsConfig.getAccesoValido()){
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr><td class="ETablaT" colspan="4">Datos del Personal</td></tr>
<%    TVPERDatos vPERDatos=clsConfig.getUser(request.getParameter("hdICveExpediente"));
      if(vPERDatos!=null){
%>        <tr>
          <td class="EEtiqueta">Nombre:</td>
          <td class="ETabla"><%=vPERDatos.getCNombre()+" "+vPERDatos.getCApPaterno()+" "+vPERDatos.getCApMaterno()%></td>
          <td class="EEtiqueta">Expediente:</td>
          <td class="ETabla"><%=vPERDatos.getICveExpediente()%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Edad:</td>
          <td class="ETabla"><%=clsConfig.getEdad(vPERDatos.getDtNacimiento())%></td>
          <td class="EEtiqueta">Sexo:</td>
          <td class="ETabla"><%=vPERDatos.getCSexo()%></td>
        </tr>
<%    }else{
%>        <tr><td class="EResalta" colspan="4">Datos no disponibles</td></tr>
<%    }
%>      </table>
    </td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT">
          <td>N&uacute;m. Examen</td>
          <td>Unidad M&eacute;dica</td>
          <td>Proceso</td>
          <td>Aplicado</td>
          <td>Concluido</td>
          <td>Mdo. Transp.</td>
          <td>Categor&iacute;a</td>
          <td>Dictamen</td>
        </tr>
<%    if(bs!=null){
        TFechas tFecha=new TFechas();
        bs.start();
        while(bs.nextRow()){
          TVEXPExamAplica vEXPExamAplica=(TVEXPExamAplica)bs.getCurrentBean();
          Vector vcTmp=vEXPExamAplica.getVcEXPExamCat();
          TVEXPExamCat vEXPExamCat=(TVEXPExamCat)vcTmp.get(0);
          int iRowSpan=vcTmp.size();
%>        <tr class="ECampo" valign="top">
<%        if(clsConfig.getLPagina(cCatalogo)){
%>          <td rowspan="<%=iRowSpan%>" align="center"><a class="EAncla" href="javascript:fIrCatalogo('<%=bs.getFieldValue("iNumExamen","0")%>')"><%=bs.getFieldValue("iNumExamen","&nbsp;")%></a></td>
<%        }else{
%>          <td rowspan="<%=iRowSpan%>" align="center"><%=bs.getFieldValue("iNumExamen","&nbsp;")%></td>
<%        }
%>          <td rowspan="<%=iRowSpan%>"><%=bs.getFieldValue("cDscUniMed","&nbsp;")%></td>
          <td rowspan="<%=iRowSpan%>"><%=bs.getFieldValue("cDscProceso","&nbsp;")%></td>
          <td rowspan="<%=iRowSpan%>"><%=vEXPExamAplica.getDtAplicacion()!=null?tFecha.getFechaDDMMYYYY(vEXPExamAplica.getDtAplicacion(),null):"&nbsp;"%></td>
          <td rowspan="<%=iRowSpan%>"><%=vEXPExamAplica.getDtConcluido()!=null?tFecha.getFechaDDMMYYYY(vEXPExamAplica.getDtConcluido(),null):"&nbsp;"%></td>
          <td><%=vEXPExamCat.getCDscMdoTrans()%></td>
          <td><%=vEXPExamCat.getCDscCategoria()%></td>
          <td><%=vEXPExamCat.getLDictamen()==1?"Apto":"No Apto"%></td>
        </tr>
<%        for(int i=1;i<iRowSpan;i++){
            vEXPExamCat=(TVEXPExamCat)vcTmp.get(i);
%>        <tr class="ECampo" valign="top">
          <td><%=vEXPExamCat.getCDscMdoTrans()%></td>
          <td><%=vEXPExamCat.getCDscCategoria()%></td>
          <td><%=vEXPExamCat.getLDictamen()==1?"Apto":"No Apto"%></td>
        </tr>
<%        }
        }
      }else{
%>        <tr class="EResalta"><td colspan="8">Datos no Disponibles</td></tr>
<%    }
    }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>      </table>
    </td></tr>
  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>


