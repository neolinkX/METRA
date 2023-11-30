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
  pg070104011CFG  clsConfig = new pg070104011CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104011.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101011.jsp";       // modificar
  String cDetalle    = "pg070101011.jsp";       // modificar
  String cDiagnostico    = "pg070101020.jsp";       // modificar
  String cRecomendaciones    = "pg070101030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();

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
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave1" value="">

  <input type="hidden" name="hdiCveExpediente" value="">
  <input type="hidden" name="hdiNumExamen" value="">
  <input type="hidden" name="hdICveServicio" value="">
  <input type="hidden" name="hdICveRama" value="">
  <input type="hidden" name="hdICveProceso" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
<%
                               out.print("<tr>");
                               out.print("<td colspan='4' align='right'>");
                               out.print(vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen",""));
                               out.print("</td>");
                               out.print("</tr>");
%>
      <td valign="top">

<%

                          out.print("<table border='1' class='ETablaInfo' align='center' cellspacing='0' cellpadding='3'>");

                               out.print("&nbsp;");

                               out.print("<tr>");

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

                               out.print("<tr>");
                               out.print("<td class='EEtiqueta'>Fecha de Conclusión:</td>");
                               TDEXPServicio dEXPServicio = new TDEXPServicio();
                               TVEXPServicio vEXPServicio = new TVEXPServicio();
                               Vector vcEXPServicio = new Vector();
                               TFechas dtFecha = new TFechas();
                               vcEXPServicio = dEXPServicio.FindServicio(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"), request.getParameter("hdICveServicio"));
                               if (vcEXPServicio.size() > 0){
                                  vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                                  out.print("<td class='ETabla' colspan='3'>" + dtFecha.getFechaDDMMYYYY(vEXPServicio.getDtFin(),"/")+ "</td>");
                               }
                               out.print("</tr>");


                               if (bs != null){
                                   bs.start();
                                   out.print("<tr>");
                                   out.print("<td class='ETablaT' colspan='4'>" + bs.getFieldValue("cDscServicio") + "</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print("<td class='ETablaT' colspan='4'>" + bs.getFieldValue("cDscRama") + "</td>");
                                   out.print("</tr>");
                                   while(bs.nextRow()){
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
                                           out.print("<td class='ETabla' colspan='2'>" + bs.getFieldValue("dValorIni") + "</td>");
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
    vcEXPRama = dEXPRama.getUsuAplicaVM(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"), request.getParameter("hdICveServicio"), request.getParameter("hdICveRama"));

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
