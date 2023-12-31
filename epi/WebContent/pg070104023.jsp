<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Leonel Popoca G.
 * @version
 * Clase:
 */%>

<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070104023CFG  clsConfig = new pg070104023CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104023.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104023.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101011.jsp";       // modificar
  String cDetalle    = "pg070101011.jsp";       // modificar
  String cDiagnostico    = "pg070101020.jsp";       // modificar
  String cRecomendaciones    = "pg070101030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripci�n|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cDscFiltrar  = "Clave|Descripci�n|";    // modificar
  String cCveFiltrar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iServicioCardio = new Integer(vParametros.getPropEspecifica("EPIServicioCardio")).intValue();

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

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  TDEXPDiagnostico dEXPDiagnostico = new TDEXPDiagnostico();
  TVEXPDiagnostico vEXPDiagnostico = new TVEXPDiagnostico();
  Vector vcEXPDiagnostico = new Vector();

  TDEXPRecomendacion dEXPRecomendacion = new TDEXPRecomendacion();
  TVEXPRecomendacion vEXPRecomendacion = new TVEXPRecomendacion();
  Vector vcEXPRecomendacion = new Vector();

  TDEXPServicio dEXPServicio = new TDEXPServicio();
  TVEXPServicio vEXPServicio = new TVEXPServicio();
  Vector vcEXPServicio = new Vector();
  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"Audio02.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function Genera_Doc(){

   form = document.forms[0];
   form.target="_self";
   form.hdReporte.value = 'Reporte';
   form.submit();
}

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cStyle = '<link rel="stylesheet" href="/medprev/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';

  var aSel = new Array();
</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave1" value="">

  <%

  %>

  <input type="hidden" name="hdiCveExpediente" value="<%=request.getParameter("hdiCveExpediente")%>">
  <input type="hidden" name="hdiNumExamen" value="<%=request.getParameter("hdiNumExamen")%>">
  <input type="hidden" name="hdICveServicio" value="<%=request.getParameter("hdICveServicio")%>">
  <input type="hidden" name="hdICveRama" value="<%=request.getParameter("hdICveRama")%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")%>">


<%
   if (request.getParameter("hdReporte") != null){
     if(request.getParameter("hdReporte").compareTo("Reporte") ==0)
      out.println(clsConfig.getActiveX());
   }
 %>

  <input type="hidden" name="hdReporte" value="">


  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>

<%

                               out.print("<tr>");
                               out.print("<td align='right' colspan='4'>");
                               out.print(vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen",""));
                               out.print("</td>");
                               out.print("</tr>");

%>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                             <%

                               out.print("&nbsp;");

                               out.print("<tr>");
                               out.print("<td class='ETablaT' colspan='4'>Datos Personales</td>");
                               out.print("</tr>");

                               TVGRLProceso vGRLProceso = new TVGRLProceso();
                               TDGRLProceso dGRLProceso = new TDGRLProceso();
                               Vector vcGRLProceso = new Vector();
                               vcGRLProceso = dGRLProceso.FindByAll(" where iCveProceso = " + request.getParameter("hdICveProceso"), "");
                               vGRLProceso = (TVGRLProceso) vcGRLProceso.get(0);

                               TVPERDatos vPERDatos = new TVPERDatos();
                               TDPERDatos dPERDatos = new TDPERDatos();
                               Vector vcPERDatos = new Vector();
                               vcPERDatos = dPERDatos.FindByAll(request.getParameter("hdiCveExpediente"));
                               vPERDatos = (TVPERDatos) vcPERDatos.get(0);

                               out.print("<tr>");
                               out.print("<td class='EEtiqueta'>Proceso:</td>");
                               out.print("<td class='ETabla'>" + vGRLProceso.getCDscProceso() + "</td>");
                               out.print("<td class='EEtiqueta'>No. Expediente:</td>");
                               out.print("<td class='ETabla'>" + vPERDatos.getICveExpediente() + "</td>");
                               out.print("</tr>");

                               out.print("<tr>");
                               out.print("<td class='EEtiqueta'>R.F.C.:</td>");
                               out.print("<td class='ETabla'>" + vPERDatos.getCRFC() + "</td>");
                               out.print("<td class='EEtiqueta'>Sexo:</td>");
                               String cGenero = vPERDatos.getCSexo();
                               if(cGenero.compareTo("M")==0)
                                 out.print("<td class='ETabla'>MASCULINO</td>");
                               else
                                 out.print("<td class='ETabla'>FEMENINO</td>");
                               out.print("</tr>");

                               out.print("<tr>");
                               out.print("<td class='EEtiqueta'>Nombre:</td>");
                               out.print("<td class='ETabla' colspan='3'>" + vPERDatos.getCApPaterno() + " " + vPERDatos.getCApMaterno() + " " + vPERDatos.getCNombre() + "</td>");
                               out.print("</tr>");


                               TDEXPServicio dEXPServiciom = new TDEXPServicio();
                               TVEXPServicio vEXPServiciom = new TVEXPServicio();
                               Vector vcEXPServiciom = new Vector();
                               TFechas dtFecha = new TFechas();
                               vcEXPServiciom = dEXPServicio.FindServicio(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"), request.getParameter("hdICveServicio"));
                               if (vcEXPServiciom.size() > 0){
                                  out.print("<tr>");
                                  out.print("<td class='EEtiqueta'>Fecha:</td>");
                                  vEXPServiciom = (TVEXPServicio) vcEXPServiciom.get(0);
                                  out.print("<td class='ETabla' colspan='3'>" + dtFecha.getFechaDDMMYYYY(vEXPServiciom.getDtFin(),"/")+ "</td>");
                                  out.print("</tr>");
                               }

                               if (bs != null){
                                   bs.start();
                                   out.print("<tr>");
                                   out.print("<td class='ETablaT' colspan='4'>" + bs.getFieldValue("cDscServicio") + "</td>");
                                   out.print("</tr>");
                                   String cDscRama = "";
                                   int iCveRama = 0;
                                   int iContador = 0;
                                   String cEtiquetas = "";
// Inicio de Despliegue
                                   while(bs.nextRow()){
                                         iCveRama = Integer.parseInt("" + bs.getFieldValue("iCveRama"));
                                  if ((vParametros.getPropEspecifica("EPIServicioOdonto").compareTo(request.getParameter("hdICveServicio")) != 0) ||
                             ((vParametros.getPropEspecifica("EPIServicioOdonto").compareTo(request.getParameter("hdICveServicio"))) == 0) && (iCveRama > 1)) {

                                     if(cDscRama.compareTo(bs.getFieldValue("cDscRama").toString())!=0){
                                       cDscRama = bs.getFieldValue("cDscRama").toString();
                                       out.print("<tr>");
                                       out.print("<td class='ETablaT' colspan='4'>" + cDscRama + "</td>");
                                       out.print("</tr>");
                                     }
                                     out.print("<tr>");
                                     switch(Integer.parseInt(bs.getFieldValue("iCveTpoResp").toString())){
                                       case 1:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(Integer.parseInt(bs.getFieldValue("lLogico", "&nbsp;").toString())==1){
                                           if(iCveLabC == Integer.parseInt(request.getParameter("hdICveServicio")))
                                             out.print("<td class='ETabla' colspan='2'>(+)</td>");
                                           else
                                             out.print("<td class='ETabla' colspan='2'>Si</td>");
                                         }
                                         else if(Integer.parseInt(bs.getFieldValue("lLogico", "&nbsp;").toString())==-1)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else{
                                           if(iCveLabC == Integer.parseInt(request.getParameter("hdICveServicio")))
                                             out.print("<td class='ETabla' colspan='2'>(-)</td>");
                                           else
                                             out.print("<td class='ETabla' colspan='2'>No</td>");
                                         }
                                       break;
                                       case 2:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("cCaracter", "&nbsp;") + "</td>");
                                       break;
                                       case 3:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorIni").toString().compareTo("")==0 || bs.getFieldValue("dValorIni").toString().compareTo("null")==0 || bs.getFieldValue("dValorIni").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("dValorIni", "&nbsp;") + "</td>");
                                       break;
                                       case 4:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("cCaracter", "&nbsp;") + "</td>");
                                       break;
                                       case 5:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorIni").toString().compareTo("")==0 || bs.getFieldValue("dValorIni").toString().compareTo("null")==0 || bs.getFieldValue("dValorIni").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla'>" + bs.getFieldValue("dValorIni", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorFin").toString().compareTo("")==0 || bs.getFieldValue("dValorFin").toString().compareTo("null")==0 || bs.getFieldValue("dValorFin").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla'>" + bs.getFieldValue("dValorFin", "&nbsp;") + "</td>");
                                       break;
                                       case 6:
                                         out.print("<td class='ETablaSTC' colspan='4'>" + bs.getFieldValue("cPregunta") + "</td>");
                                       break;
                                       default:
                                       break;
                                     }
                                     out.print("</tr>");
                                   }
                                  else{
                                     // Muestra Odontograma
                                     iContador++;

                                   if (iContador < 33){
                                     if(cDscRama.compareTo(bs.getFieldValue("cDscRama").toString())!=0){
                                       cDscRama = bs.getFieldValue("cDscRama").toString();
                                       out.print("<tr>");
                                       out.print("<td class='ETablaT' colspan='4'>" + cDscRama + "</td>");
                                       out.print("</tr>");

                                       }
                                     if (iContador == 1)
                                      out.print("<td colspan ='4'><table align='center'class='ETablaInfo' border='1'><tr>");
                                     switch(Integer.parseInt(bs.getFieldValue("iCveTpoResp").toString())){
                                       case 1:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(Integer.parseInt(bs.getFieldValue("lLogico", "&nbsp;").toString())==1)
                                           out.print("<td class='ETabla' colspan='2'>Si</td>");
                                         else if(Integer.parseInt(bs.getFieldValue("lLogico", "&nbsp;").toString())==-1)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla' colspan='2'>No</td>");
                                       break;
                                       case 2:
                                         //out.print("<td class='ETabla'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         cEtiquetas = cEtiquetas + "<td class='ETablaT'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>";
                                         out.print("<td class='ETabla'>" + bs.getFieldValue("cCaracter", "&nbsp;") + "</td>");
                                       break;
                                       case 3:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorIni").toString().compareTo("")==0 || bs.getFieldValue("dValorIni").toString().compareTo("null")==0 || bs.getFieldValue("dValorIni").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("dValorIni", "&nbsp;") + "</td>");
                                       break;
                                       case 4:
                                         out.print("<td class='ETablaR'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         out.print("<td class='ETabla'>" + bs.getFieldValue("cCaracter", "&nbsp;") + "</td>");
                                       break;
                                       case 5:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorIni").toString().compareTo("")==0 || bs.getFieldValue("dValorIni").toString().compareTo("null")==0 || bs.getFieldValue("dValorIni").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla'>" + bs.getFieldValue("dValorIni", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorFin").toString().compareTo("")==0 || bs.getFieldValue("dValorFin").toString().compareTo("null")==0 || bs.getFieldValue("dValorFin").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla'>" + bs.getFieldValue("dValorFin", "&nbsp;") + "</td>");
                                       break;
                                       case 6:
                                         out.print("<td class='ETablaSTC' colspan='4'>" + bs.getFieldValue("cPregunta") + "</td>");
                                       break;
                                       default:
                                       break;
                                     }
                                     if (iContador == 16){
                                        out.print("</tr>");
                                        out.print("<tr>" + cEtiquetas + "</tr>");
                                        cEtiquetas = "";
                                     }
                                     if (iContador == 32)
                                       out.print("</tr><tr>" + cEtiquetas + "</tr></table></td>");
                                   }
                                  else{ // Despu�s de odontograma !!!
                                    out.print("<tr>");
                                     switch(Integer.parseInt(bs.getFieldValue("iCveTpoResp").toString())){
                                       case 1:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(Integer.parseInt(bs.getFieldValue("lLogico", "&nbsp;").toString())==1)
                                           out.print("<td class='ETabla' colspan='2'>Si</td>");
                                         else if(Integer.parseInt(bs.getFieldValue("lLogico", "&nbsp;").toString())==-1)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla' colspan='2'>No</td>");
                                       break;
                                       case 2:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("cCaracter", "&nbsp;") + "</td>");
                                       break;
                                       case 3:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorIni").toString().compareTo("")==0 || bs.getFieldValue("dValorIni").toString().compareTo("null")==0 || bs.getFieldValue("dValorIni").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("dValorIni", "&nbsp;") + "</td>");
                                       break;
                                       case 4:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("cCaracter", "&nbsp;") + "</td>");
                                       break;
                                       case 5:
                                         out.print("<td class='ETablaR' colspan='2'>" + bs.getFieldValue("cPregunta", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorIni").toString().compareTo("")==0 || bs.getFieldValue("dValorIni").toString().compareTo("null")==0 || bs.getFieldValue("dValorIni").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla'>" + bs.getFieldValue("dValorIni", "&nbsp;") + "</td>");
                                         if(bs.getFieldValue("dValorFin").toString().compareTo("")==0 || bs.getFieldValue("dValorFin").toString().compareTo("null")==0 || bs.getFieldValue("dValorFin").toString().compareTo("0.0")==0)
                                           out.print("<td class='ETabla' colspan='2'>&nbsp;</td>");
                                         else
                                           out.print("<td class='ETabla'>" + bs.getFieldValue("dValorFin", "&nbsp;") + "</td>");
                                       break;
                                       case 6:
                                         out.print("<td class='ETablaSTC' colspan='4'>" + bs.getFieldValue("cPregunta") + "</td>");
                                       break;
                                       default:
                                       break;
                                     }
                                   }

                                  }

                                   }
// Fin de Despliegue
                                   out.print("<tr>");
                                   out.print("<td class='ETablaT' colspan='4'>Diagn�sticos</td>");
                                   out.print("</tr>");

                                   vcEXPDiagnostico = dEXPDiagnostico.getDiagEspXServ(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"), request.getParameter("hdICveServicio"));
                                   int iEspecialidad = 0;
                                   for(int i=0; i<vcEXPDiagnostico.size(); i++){
                                     vEXPDiagnostico = (TVEXPDiagnostico) vcEXPDiagnostico.get(i);
                                     if(iEspecialidad != vEXPDiagnostico.getICveEspecialidad()){
                                       iEspecialidad = vEXPDiagnostico.getICveEspecialidad();
                                       out.print("<tr>");
                                       out.print("<td class='ETablaSTC' colspan='4'>" + vEXPDiagnostico.getCDscEspecialidad() + "</td>");
                                       out.print("</tr>");
                                     }
                                     out.print("<tr>");
                                     out.print("<td class='ETabla' colspan='4'>" + vEXPDiagnostico.getCDscDiagnostico() + "</td>");
                                     out.print("</tr>");
                                   }

                                   out.print("<tr>");
                                   out.print("<td class='ETablaT' colspan='4'>Recomendaciones</td>");
                                   out.print("</tr>");

                                   vcEXPRecomendacion = dEXPRecomendacion.getRecEspXServ(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"), request.getParameter("hdICveServicio"));
                                   int iRecomendacion = 0;
                                   for(int i=0; i<vcEXPRecomendacion.size(); i++){
                                     vEXPRecomendacion = (TVEXPRecomendacion) vcEXPRecomendacion.get(i);
                                     if(iRecomendacion != vEXPRecomendacion.getICveEspecialidad()){
                                       iRecomendacion = vEXPRecomendacion.getICveEspecialidad();
                                       out.print("<tr>");
                                       out.print("<td class='ETablaSTC' colspan='4'>" + vEXPRecomendacion.getCDscEspecialidad() + "</td>");
                                       out.print("</tr>");
                                     }
                                     out.print("<tr>");
                                     out.print("<td class='ETabla' colspan='4'>" + vEXPRecomendacion.getCDscRecomendacion() + "</td>");
                                     out.print("</tr>");
                                   }


                                   out.print("<tr>");
                                   out.print("<td class='ETablaT' colspan='4'>Nota M�dica</td>");
                                   out.print("</tr>");

                                   vcEXPServicio = dEXPServicio.getNotaMed(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"), request.getParameter("hdICveServicio"));
                                   if(vcEXPServicio.size()>0){
                                     vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                                     out.print("<tr>");
                                     out.print("<td class='ETabla' colspan='4'>" + vEXPServicio.getCNotaMedica() + "</td>");
                                     out.print("</tr>");
                                   }

                                   if(iServicioCardio == Integer.parseInt(request.getParameter("hdICveServicio"))){
                                     out.print("<tr>");
                                     out.print("<td align='center' colspan='4'>");
                                     out.print(vEti.clsAnclaTexto("EAncla","Impresi�n en el Electrocardiograma","JavaScript:Genera_Doc();", "Ver Examen",""));
                                     out.print("</td>");
                                     out.print("</tr>");
                                   }

                                   //Inserci�n para visualizar las gr�ficas de Audiometr�a
                                   if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
                                       vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio"))) {
                                       TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
                                       TVEXPAudiomet vEXPAudiomet;
                                       Vector vcAudio = new Vector();

//                                       vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + request.getParameter("hdiCveExpediente") + " and iNumExamen = " + request.getParameter("hdiNumExamen") +" ");
// System.out.println("where iCveExpediente = " + request.getParameter("hdiCveExpediente") + " and iNumExamen = " + request.getParameter("hdiNumExamen") +" AND iCveServicio = "+request.getParameter("hdICveServicio"));
                                       vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + request.getParameter("hdiCveExpediente") + " and iNumExamen = " + request.getParameter("hdiNumExamen") +" AND iCveServicio = "+request.getParameter("hdICveServicio"));
                                       if (vcAudio.size() > 0){
                                          out.print("<SCRIPT LANGUAGE='JavaScript'>");
                                          for (int i = 0; i < vcAudio.size(); i++){
                                             vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
                                             out.print("aSel["+i+"]=["+vEXPAudiomet.getIOido()+","+vEXPAudiomet.getITipo()+
                                             ","+vEXPAudiomet.getIX()+","+vEXPAudiomet.getIY()+"];");
                                           }
                                          out.print("</SCRIPT>");
                                        }

                                       out.println("</table>");
                                       out.print("<table table border='1' border='0' align='center' width='524'><tr><td class='ETablaT'>Oido Derecho</td></tr></table>");
                                       out.print("<SCRIPT LANGUAGE='JavaScript'>");
                                       out.print("fGraphAudioV(1,aSel);");
                                       out.print("</SCRIPT>");
                                       out.print("<table table border='1' border='0' align='center' width='524'><tr><td class='ETablaT'>Oido Izquierdo</td></tr></table>");
                                       out.print("<SCRIPT LANGUAGE='JavaScript'>");
                                       out.print("fGraphAudioV(2,aSel);");
                                       out.print("</SCRIPT>");
                                    }
                               }
                               else{
                                   out.print("<tr>");
                                   out.print("<td class='ETablaC' colspan='4'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                   out.print("</tr>");
                               }

                            %>
                          </table>
<%


    out.print("<table align='center'>");
    out.print("<tr><td>&nbsp;</td></tr>");

    String cNombre = "";
    String cLinea = "";
    String cSiglas = "";

    TVEXPRama vEXPRama = new TVEXPRama();
    TDEXPRama dEXPRama = new TDEXPRama();
    Vector vcEXPRama = new Vector();
    vcEXPRama = dEXPRama.getUsuAplicaUM(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"), request.getParameter("hdICveServicio"));

    TVGRLUsuario vGRLUsuario = new TVGRLUsuario();
    TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
    Vector vcGRLUsuario = new Vector();

    if(vcEXPRama.size()>0){
      vEXPRama = (TVEXPRama) vcEXPRama.get(0);

      vcGRLUsuario = dGRLUsuario.getSiglas(" where GRLUsuario.iCveUsuario = " + vEXPRama.getICveUsuAplica());
      if(vcGRLUsuario.size()>0){
        vGRLUsuario = (TVGRLUsuario) vcGRLUsuario.get(0);
        cSiglas = vGRLUsuario.getCSiglas();
      }

      cNombre += cSiglas + " " + vEXPRama.getCNombre() + " " + vEXPRama.getCApPaterno() + " " + vEXPRama.getCApMaterno();
    }

    int iLong = cNombre.length();
    for(int i=0; i<iLong; i++)
      cLinea +="_";
    out.print("&nbsp;");
    out.print("<input type='hidden' name='hdCNombre' value='" + cNombre  + "'>");
    out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cLinea + "</td></tr>");
    out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cNombre + "</td></tr>");
    out.print("<tr><td>&nbsp;</td></tr>");
    out.print("</table>");

    out.print("<table align='right'>");
    out.print("<tr>");
    out.print("<td align='right'>");
    out.print(vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen",""));
    out.print("</td>");
    out.print("</tr>");
    out.print("</table>");
%>

  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
