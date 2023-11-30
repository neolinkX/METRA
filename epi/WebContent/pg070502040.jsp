<%/**
 * Title: Generación de Plantillas.
 * Description: Generación de Plantillas.
 * Copyright: Micros Personales, S.A. de C.V.
 * Company: Micros Personales, S.A. de C.V.
 * @author LCI. Oscar Castrejón Adame.
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.text.*" %>


<html>
<%
  pg070502040CFG  clsConfig = new pg070502040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070502040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070502040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070802021.jsp";       // modificar
  String cDetalle     = "pg070802021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Denominacion|";    // modificar
  String cCveOrdenar  = "iCveEmpresa|cDenominacion|";  // modificar
  String cDscFiltrar  = "Clave|Denominación|";    // modificar
  String cCveFiltrar  = "GRLEmpresas.iCveEmpresa|GRLEmpresas.cDenominacion|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar

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
  String cUpdStatus  = clsConfig.getUpdStatus(); //"SaveCancelOnly";
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cEstatusIR   = clsConfig.cImprimir;            // modificar
  String cCanWrite   = "yes";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  TFechas dtFecha = new TFechas();
  Vector vMdoTrans  = new Vector();
  Vector vPais      = new Vector();
  Vector vEstado    = new Vector();
  Vector vMunicipio = new Vector();
  Vector vUnidades = new Vector();
  Vector vPeriodPla = new Vector();
  String cvEmpresas = "";
  String cvEstado = "";
  String cvMunicipio = "";
  String cvUnidades = "";
  TFechas Fecha = new TFechas();
  java.sql.Date dtToday = Fecha.TodaySQL();

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());


%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fSubmitForm1(theButton){
    var form = document.forms[0];
    form.hdBoton.value = theButton;
    if(form.hdBoton.value == 'Imprimir')
      fImprimir();
    else{
      form.target="_self";
      if (window.fValidaTodo)
        lSubmitir = fValidaTodo();
      else
        lSubmitir = true;
      if (lSubmitir){
        window.parent.parent.FRMCuerpo.FRMFiltro.FRMBusqueda.fSubmiteInt();
      }else
        form.hdBoton.value = "";
    }
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
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIni" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Datos para la Plantilla</td>
           </tr><tr>
                <%
                 vPeriodPla = clsConfig.vPeriodPla;
                 TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                 Vector vcPersonal = new Vector();
                 int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CtrlTransProceso"));
                 int iUniMed = 0;

                 vUnidades = vUsuario.getVUniFiltro(iCveProceso);
                 vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                 if(vcPersonal.size() != 0){
                    iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                 }
                 vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                 out.print(vEti.Texto("EEtiqueta", "Unidad Médica que Solicita:"));
                 out.print("<td class=\"ECampo\">");
                 out.print("<select name=\"SLSUniMed\">");
                 if (request.getParameter("SLSUniMed") != null){
                   if (request.getParameter("SLSUniMed") != "")
                      cvUnidades = request.getParameter("SLSUniMed");
                 }

                 if (!vUnidades.isEmpty()){
                   for (int i=0;i<vUnidades.size();i++){
                     TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                     VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                     if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                        out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                     else
                        out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                   }
                 }
                 out.print("</select>");
                 out.print("</td>");
                 out.print(vEti.Texto("EEtiqueta", "Solicitud:"));
                 out.print("<td class=\"ECampo\">");
                 out.print(vEti.CampoSinCelda("input",10,10,"dtSolicitud", Fecha.getFechaDDMMYYYY(dtToday,"/"),3,"","",true,true));
                 out.print("</td>");
                 out.print("</tr>");

                 out.print("<tr>");
                 out.print(vEti.EtiToggle("EEtiqueta","Programadas:","ECampo","lProgramada", "","",1,true,"1","0",true));
                 out.print(vEti.Texto("EEtiqueta", "Vencimiento:"));
                 out.print("<td class=\"ECampo\">");
                 out.print(vEti.CampoSinCelda("input",10,10,"dtVencimiento", Fecha.getFechaDDMMYYYY(dtToday,"/"),3,"","",true,true));
                 out.print("</td>");
                 out.print("</tr>");

                 out.print("<tr>");
                 out.print(vEti.Texto("EEtiqueta", "Año:"));
                 out.print("<td class=\"ETabla\">");
                 out.print("<select name=\"SLSAnio\" onChange=\"llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].SLSPeriodo);\">");
                 for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                    if (request.getParameter("SLSAnio")!=null&&request.getParameter("SLSAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                       out.print("<option value = " + i + " selected>" + i + "</option>");
                    else
                       out.print("<option value = " + i + ">" + i + "</option>");
                 }
                 out.print("</select>");
                 out.print("</td>");
                 out.print(vEti.Texto("EEtiqueta", "Periodo:"));
                 out.print("<td class=\"ETabla\">");
                 out.print("<select name=\"SLSPeriodo\" onChange=\"llenaFecha(2,document.forms[0].SLSAnio.value,document.forms[0].SLSPeriodo.value,document.forms[0].dtVencimiento);\">");
                 if (!vPeriodPla.isEmpty()){
                    for(int i=0;i<vPeriodPla.size();i++){
                      TVCTRPeriodPla VCTRPeriodPla = new TVCTRPeriodPla();
                      VCTRPeriodPla = (TVCTRPeriodPla) vPeriodPla.get(i);
                      if (request.getParameter("SLSPeriodo")!=null&&request.getParameter("SLSPeriodo").compareToIgnoreCase((new Integer(VCTRPeriodPla.getiCvePeriodPla())).toString())==0 &&
                         request.getParameter("SLSAnio")!=null&&request.getParameter("SLSAnio").compareToIgnoreCase((new Integer(VCTRPeriodPla.getiAnio())).toString())==0 )
                          out.print("<option value = " + VCTRPeriodPla.getiCvePeriodPla() + " selected>" + VCTRPeriodPla.getiCvePeriodPla() + " - " + VCTRPeriodPla.getcDscPeriodPla() + "</option>");
                      else
                          out.print("<option value = " + VCTRPeriodPla.getiCvePeriodPla() + ">" + VCTRPeriodPla.getiCvePeriodPla() + " - " + VCTRPeriodPla.getcDscPeriodPla() + "</option>");
                    }
                 }
                 out.print("</select>");
                 out.print("</td>");
                 out.print("</tr>");

                 out.print("<tr>");
                 out.print("<td class=\"ETablaT\">Usuario:</td>");
                 out.println("<td class=\"ETablaR\">");
                 out.print(vEti.SelectOneRowSinTD("SLSUsuario", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, ""));
                 out.println("</td>");
                 out.print("<td>&nbsp</td>");
                 out.print("<td>&nbsp</td>");
                 out.print("</tr>");



                 out.print("<tr>");
                 out.print("<td colspan=\"9\" class=\"ETablaT\">Datos del Transportista</td>");
                 out.print("</tr>");

                 out.print("<tr>");
                 out.print(vEti.Texto("EEtiqueta", "Unidad Médica que Controla:"));
                 out.print("<td class=\"ECampo\">");
                 out.print("<select name=\"SLSUniMedCtr\">");
                 if (request.getParameter("SLSUniMedCtr") != null){
                   if (request.getParameter("SLSUniMedCtr") != "")
                      cvUnidades = request.getParameter("SLSUniMedCtr");
                 }
                 if (!vUnidades.isEmpty()){
                   for (int i=0;i<vUnidades.size();i++){
                     TVGRLUMUsuario VGRLUMUsuario = new TVGRLUMUsuario();
                     VGRLUMUsuario = (TVGRLUMUsuario) vUnidades.get(i);
                     if (cvUnidades.compareToIgnoreCase(new Integer(VGRLUMUsuario.getICveUniMed()).toString()) == 0)
                        out.print("<option selected value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                     else
                        out.print("<option value=\"" + VGRLUMUsuario.getICveUniMed()  + "\">" + VGRLUMUsuario.getCDscUniMed() + "</option>");
                   }
                 }
                 out.print("</select>");
                 out.print("</td>");
                 vMdoTrans = clsConfig.vMdoTrans;
                 out.print(vEti.Texto("EEtiqueta", "Modos de Transporte:"));
                 out.print("<td class=\"ECampo\">");
                 out.println(vEti.SelectOneRowSinTD("SLSMdoTransporte", "", vMdoTrans, "iCveMdoTrans", "cDscMdoTrans", request, "0"));
                 out.print("</td>");
                 out.print("</tr>");

                 out.print("<tr>");
                 out.print("<td class=\"ECampo\" colspan=\"4\">");
                 out.print("<div align=\"center\">");
                 out.print(vEti.clsAnclaTexto("EAncla","Generar","JavaScript:fListado();",""));
                 out.print("</div>");
                 out.print("</td>");

                %>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                             <% // modificar según listado
                               if (bs != null){
                                   bs.start();
                               } else {
                                  out.print("<tr>");
                                  out.print("<td class=\"ETablaT\" colspan=\"7\">Mensaje</td>");
                                  out.print("</tr>");
                                  out.print("<td class=\"ETabla\" colspan=\"7\">El Número de Plantillas Solicitadas es de:"+ clsConfig.getCantidad() + " </td>");
                                  out.print("</tr>");
                               }
                            %>
                          </table>
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
