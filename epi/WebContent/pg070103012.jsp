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
  pg070104021CFG  clsConfig = new pg070104021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070103012.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070103012.jsp\" target=\"FRMCuerpo"); // modificar
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
  int iServicioCardio = new Integer(vParametros.getPropEspecifica("EPIServicioCardio")).intValue();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cRutaAyuda = vParametros.getPropEspecifica("html");
  String cPrograma  = cRutaAyuda + "hlp" + vEntorno.getArchAyuda() + ".htm";

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

  function fPrint(){
    window.print();
  }
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

  <%

  %>

  <input type="hidden" name="hdiCveExpediente" value="<%=request.getParameter("hdiCveExpediente")%>">
  <input type="hidden" name="hdiNumExamen" value="<%=request.getParameter("hdiNumExamen")%>">
  <input type="hidden" name="hdICveServicio" value="<%=request.getParameter("hdICveServicio")%>">
  <input type="hidden" name="hdICveRama" value="<%=request.getParameter("hdICveRama")%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")%>">


<%
/*   if (request.getParameter("hdReporte") != null){
     if(request.getParameter("hdReporte").compareTo("Reporte") ==0)
      out.println(clsConfig.getActiveX());
   }
*/
 %>

  <input type="hidden" name="hdReporte" value="">


 <table border="0" width="100%" height="100%" cellspacing="0" cellpadding="0" class="ETablaGral" background="<%= cRutaImg %>fondoTab<%= cTipoImg %>">
    <tr>
      <td align="center" valign="top">
        <table border="2" width="<%= vParametros.getPropEspecifica("AnchoTablaPrinc") %>%"
               height="<%= vParametros.getPropEspecifica("AltoTablaPrinc") %>%"
               cellspacing="0" cellpadding="0"
               background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg">
          <tr>
            <td width="100%" valign="top"><% //if (clsConfig.getAccesoValido()){ %>
              <% new TDefSubmit(pageContext); /* Define funcion de submitir y campo oculto */ %>
              <TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" WIDTH="100%">
                <TR><% // Inicio de Filtro %>
                  <TD ALIGN="CENTER" VALIGN="TOP">
                    <table border="0" width="100%">
                      <tr>
                        <td width="100%" align="center">
                          <table border="0" width="90%" cellspacing="0" cellpadding="0">
                            <tr>
                              <td align="left">
                                <table border="0">
                                  <tr>
                                  </tr>
                                </table>
                              </td>
                              <td></td>
                              <td align="right">
                                <table border="0">
                                  <tr>
                                  </tr>
                                </table>
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr><% // Fin de Filtro %>
                <tr>
                  <td width="100%" align="center" class="EEtiquetaP">&nbsp;&nbsp;&nbsp;&nbsp;
                  </td>
                </tr>
                <tr>
                  <td width="100%" align="center" class="EEtiquetaP">
                      <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
<%
                               out.print("<tr>");
                               out.println(vEti.Texto("EEtiqueta\" colspan=\"1", "Expediente:"));
                               out.println(vEti.Texto("ECampo\" colspan=\"1",request.getParameter("hdExpediente")));
                               out.println(vEti.Texto("EEtiqueta\" colspan=\"1", "Hora:"));
                               out.println(vEti.Texto("ECampo\" colspan=\"2",request.getParameter("hdHora")));
                               out.print("<td align='right'>");
                               out.print(vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Imprimir",""));
                               out.print("</td>");
                               out.print("</tr>");
%>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td width="100%" align="center" class="EEtiquetaP">&nbsp;&nbsp;&nbsp;&nbsp;
                  </td>
                </tr>
                <tr>
                  <td width="100%" align="center">
                    <table border="1">
                      <tr>
                        <td width="100%" align="center">
                          <table border="1" class="ETablaInfo" width="100%" cellspacing="0" cellpadding="3">
                      <%   String cCampo="";
                           // Inicio de Datos
                           out.println("<tr>");
                           out.println(vEti.Texto("ETablaT\" colspan=\"2", "SECRETARÍA DE COMUNICACIONES Y TRANSPORTES"));
                           out.println(vEti.Texto("ETablaT\" colspan=\"4", "Movimientos Generados"));
                           out.println("</tr>");
                           out.println("<tr>");
                         %>
                          <td rowspan="4" class="ECampoR"><img src="<%=cRutaImg%>logoSCT2.gif"></TD>
                          <td rowspan="4" class="ECampo"><strong>R.F.C.: </strong><%=request.getParameter("hdCRFCSCT")%></TD>
                         <%
                           out.println(vEti.Texto("EEtiqueta\" colspan=\"1", "Área Rec.:"));
                           out.println(vEti.Texto("ECampo\" colspan=\"3",request.getParameter("hdCAreaRec")));
                           TFechas pFecha = new TFechas("07");
                         %>
                          </tr><tr>
                         <%
                           out.println(vEti.Texto("EEtiqueta\" colspan=\"1", "Oficina:"));
                           out.println(vEti.Texto("ECampo\" colspan=\"3", request.getParameter("hdCOficina")));
                         %>
                        </tr>
                        <tr>
                        <%
                           String cNombre = "";
                           out.println(vEti.Texto("EEtiqueta\" colspan=\"1", "Nombre:"));
                           out.println(vEti.Texto("ECampo\" colspan=\"3", request.getParameter("hdCNombre")));
                        %>
                        </tr><tr>
                        <%
                           out.println(vEti.Texto("EEtiqueta\" colspan=\"1", "RFC:"));
                           out.println(vEti.Texto("ECampo", request.getParameter("hdCRFC")));
                          out.println(vEti.Texto("EEtiqueta","Fecha:"));
                          out.println(vEti.Texto("ECampo", pFecha.getFechaDDMMMYYYY(pFecha.TodaySQL(), "/").toUpperCase()));
                        %>
                        </tr>
                     </table><br>
                     <table border="1"  class="ETablaInfo" cellspacing="0" cellpadding="3">
                       <tr>
                         <td class="ETablaT" colspan="3"><big>CONCENTRACIÓN DE FONDOS</BIG></td>
                       </tr>
                       <tr>
                         <td rowspan="2"><img src="<%=cRutaImg%>logoBanamex.gif"></TD>
                         <td class="EEtiqueta">Sucursal:</td>
                         <td class="ECampo"><%=vParametros.getPropEspecifica("WSIngSucursal")%></td>
                       </tr>
                       <tr>
                         <td class="EEtiqueta">Cuenta:</td>
                         <td class="ECampo"><%=vParametros.getPropEspecifica("WSIngCuenta")%></td>
                       </tr>
                     </table><br>
                     <table border="1" class="ETablaInfo" cellspacing="0" cellpadding="3">
                        <%
                        // Títulos de la Tabla
                        %>
                        <tr>
                          <td class="ETablaT">Depósito No.</td>
                          <td class="ETablaT">Referencia Numérica</td>
                          <td class="ETablaT">Referencia Alfanumérica</td>
                          <td class="ETablaT">Importe a Depositar</td>
                        </tr>
                        <%
                             // Generación de Renglones para captura
                               out.println("<tr>");
                               DecimalFormat df = new DecimalFormat("$    ###,###,##0.00");
                               DecimalFormat refFmt = new DecimalFormat("00000000");
                               int iNumDeposito = 0;
                               if (request.getParameter("hdcRefAlfaNumericaP")!=null && !request.getParameter("hdcRefAlfaNumericaP").equals("")) {
                                iNumDeposito += 1;
                            %>
                               <td class="ETablaC"><%=iNumDeposito%></td>
                               <td class="ETablaC"><%=request.getParameter("hdiRefNumericaP")%></td>
                               <td class="ETablaC"><%=request.getParameter("hdcRefAlfaNumericaP")%></td>
                               <td class="ETablaR"><%=request.getParameter("hdImporteP")%></td>
                            <%}
                              if (request.getParameter("hdcRefAlfaNumericaA")!=null && !request.getParameter("hdcRefAlfaNumericaA").equals("")) {
                                iNumDeposito+=1;
                              %>
                               </tr><tr>
                               <td class="ETablaC"><%=iNumDeposito%></td>
                               <td class="ETablaC"><%=request.getParameter("hdiRefNumericaA")%></td>
                               <td class="ETablaC"><%=request.getParameter("hdcRefAlfaNumericaA")%></td>
                               <td class="ETablaR"><%=request.getParameter("hdImporteA")%></td>
                            <%
                            }
                          out.println("</tr>");
                        %>
                     </table><br>
                     <table border="0">
                       <tr>
                         <td class="ECampoC"><u><big><big><big><strong>NO SELLAR</strong></big></big></big></u></td>
                       </tr>
                       <tr>
                         <td class="ECampo"><u>NOTA: ESTA FICHA NO TIENE VALIDEZ OFICIAL, SOLICITE SU COMPROBANTE DE PAGO EN SUCURSAL.</u></td>
                       </tr>
                       <tr>
                         <td class="ECampo">Solo se deberá recibir: Efectivo, Cheques Banamex y Cheques de Caja o Certificados de otros bancos.</td>
                       </tr>
                     </table><% // Fin de Datos %>
                        <%
//                            out.println(clsConfig.getActiveX()); // el ActiveX de Excel
//                            vErrores.acumulaError("Los documentos han sido enviados a impresión ", 0, "");
//                            vErrores.acumulaError("Presione el botón imprimir para imprimir esta lista de referencias", 0, "");
                        %>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            <%// } %></td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
<%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
