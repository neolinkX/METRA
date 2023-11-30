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
  pg070105030CFG  clsConfig = new pg070105030CFG();               // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070105030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070105030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070105030.jsp";     // modificar
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
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
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
          <td class="ETabla"><%=("F".compareTo(vPERDatos.getCSexo()) == 0? "Mujer" : "Hombre" )%></td>
        </tr>
        <tr>
          <td class="EEtiqueta">Proceso:</td>
          <td class="ETabla" colspan="3"><%=clsConfig.getProceso(request.getParameter("hdICveProceso")).getCDscProceso()%></td>
        </tr>
<%    }else{
%>        <tr><td class="EResalta" colspan="4">Datos no disponibles</td></tr>
<%    }
%>      </table>
    </td></tr>
<%    if(bs!=null){
        bs.start();
        while(bs.nextRow()){
          TDMEDServicio dMEDServicio = new TDMEDServicio();
          TVMEDServicio vMEDServicio = new TVMEDServicio();
          Vector vcMEDServicio = new Vector();
          vcMEDServicio = dMEDServicio.FindByAll(" where lReqDiag = 1 and iCveServicio = " + bs.getFieldValue("iCveServicio"), "");
          if (vcMEDServicio.size() > 0)
            for (int j = 0; j < vcMEDServicio.size(); j++)
              vMEDServicio = (TVMEDServicio)vcMEDServicio.get(j);

        if(vMEDServicio.getLReqDiag()==1){ // Muestra los resultados solo si el servicio diagnostica o hace recomendaciones

%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="ETablaT"><td colspan="3"><%=bs.getFieldValue("cDscServicio","&nbsp;")%></td></tr>
<%        String cCveServicio=(String)bs.getFieldValue("iCveServicio","0");
          Vector vTmp=clsConfig.getDiagnosticos(cCveServicio);
          if(vTmp!=null && vTmp.size()!=0){
%>        <tr class="ETablaT">
          <td>Clave</td>
          <td>Diagn&oacute;stico</td>
          <td>Grupo</td>
        </tr>
<%          for(Enumeration e=vTmp.elements();e.hasMoreElements();){
              TVEXPDiagnostico vEXPDiagnostico=(TVEXPDiagnostico)e.nextElement();
              int lAlarma=vEXPDiagnostico.getLAlarma();
%>        <tr class=<%=lAlarma==1?"EAlarma":lAlarma==0?"ENoAptitud":"Ecampo"%>>
          <td><%=vEXPDiagnostico.getCCIE()!=null?vEXPDiagnostico.getCCIE():"&nbsp;"%></td>
          <td><%=vEXPDiagnostico.getCDscDiagnostico()!=null?vEXPDiagnostico.getCDscDiagnostico():"&nbsp;"%></td>
          <td><%=vEXPDiagnostico.getCDscGrupo()!=null?vEXPDiagnostico.getCDscGrupo():"&nbsp;"%></td>
        </tr>
<%          }
          }
          vTmp=clsConfig.getRecomendaciones(cCveServicio);
          if(vTmp!=null && vTmp.size()!=0){
%>        <tr class="ETablaT">
          <td>Clave</td>
          <td colspan="2">Recomendaciones</td>
        </tr>
<%          for(Enumeration e=vTmp.elements();e.hasMoreElements();){
              TVEXPRecomendacion vEXPRecomendacion=(TVEXPRecomendacion)e.nextElement();
%>        <tr class="ECampo">
          <td><%=vEXPRecomendacion.getCIdentificador()!=null?vEXPRecomendacion.getCIdentificador():"&nbsp;"%></td>
          <td colspan="2"><%=vEXPRecomendacion.getCDscBreve()!=null?vEXPRecomendacion.getCDscBreve():"&nbsp;"%></td>
        </tr>
<%          }
          }
%>      </table>
    </td></tr>
<%
        } // if
        } // while
      }else{
%>    <tr><td>&nbsp;</td></tr>
    <tr><td valign="top">
      <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
        <tr class="EResalta"><td align="center">Datos no disponibles</td></tr>
      </table>
    </td></tr>
<%    }
    }else{
%>    <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
<%  }
%>  </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>