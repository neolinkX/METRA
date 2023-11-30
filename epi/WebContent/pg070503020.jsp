<%/**
 * Title:         Concentrado de Empresas
 * Description:   Concentrado de Empresas
 * Copyright:     2004
 * Company:       Microe Personales S.A. de C.V.
 * @author        Marco Antonio Hernández García
 * @version       1.0
 * Clase:         pg070503020
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070503020CFG  clsConfig = new pg070503020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070503020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070503020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "RFC|Razon Social|ID DGPMPT|";    // modificar
  String cCveOrdenar  = "GRLEmpresas.cRFC|GRLEmpresas.cDscEmpresa|GRLEmpresas.cIDDGPMPT|";  // modificar
  String cDscFiltrar  = "RFC|Razon Social|ID DGPMPT|";    // modificar
  String cCveFiltrar  = "GRLEmpresas.cRFC|GRLEmpresas.cDscEmpresa|GRLEmpresas.cIDDGPMPT|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                 // modificar
  String cEstatusIR   = "Imprimir";            // modificar
  TFechas Fecha = new TFechas();
  String dtToday = Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(),"/");


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
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  String cOficio   = "";
  String cSQL = "";
  DecimalFormat df = new DecimalFormat("##,###,##0.00");
  String cValor = "";
  TFechas dtFecha = new TFechas();
  Vector vEtapas = new Vector();
  Vector vPlantilla = new Vector();
  Vector vSeguimiento = new Vector();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
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
  <input type="hidden" name="hdIni" value="<%  /*EMPRESA*/   if (request.getParameter("hdIni") != null) out.print(request.getParameter("hdIni"));%>">
  <input type="hidden" name="hdIni2" value="<% /*PLANTILLA*/ if (request.getParameter("hdIni2") != null) out.print(request.getParameter("hdIni2"));%>">

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="<%if (request.getParameter("hdBoton") != null)  out.print(request.getParameter("hdBoton"));%>">
  <input type="hidden" name="hdToday" value="<%=dtToday%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
               <td colspan="6" class="ETablaT">Filtro de la Búsqueda</td>
             </tr>
                  <%
                    vEtapas = clsConfig.getEtapas();
                    out.print("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta", "RFC:", "ECampo", "text", 15, 15,5,"cRFC", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta", "Razón Social:", "ECampo", "text", 20, 20,5,"cRazonSocial", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td class=\"EEtiqueta\">Etapa</td>");
                    out.print("<td class=\"ECampo\">");
                    out.print("<select name=\"SLSEtapa\">");
                    if (request.getParameter("SLSEtapa") != null){
                       if (request.getParameter("SLSEtapa").toString().compareToIgnoreCase("0") == 0)
                          out.print("<option value=\"-1\" selected>Todas las Etapas</option>");
                       else
                           out.print("<option value=\"-1\">Todas las Etapas</option>");
                    } else{
                       out.print("<option value=\"-1\">Todas las Etapas</option>");
                    }
                    if (!vEtapas.isEmpty()){
                        for(int i=0;i<vEtapas.size();i++){
                           TVGRLEtapa VGRLEtapa = new TVGRLEtapa();
                           VGRLEtapa = (TVGRLEtapa) vEtapas.get(i);
                           if (request.getParameter("SLSEtapa") != null){
                             if (request.getParameter("SLSEtapa").toString().compareToIgnoreCase(new Integer(VGRLEtapa.getiCveEtapa()).toString()) == 0)
                               out.print("<option value=\"" + VGRLEtapa.getiCveEtapa() + "\" selected>" + VGRLEtapa.getcDscEtapa() + "</option>");
                             else
                               out.print("<option value=\"" + VGRLEtapa.getiCveEtapa() + "\" >" + VGRLEtapa.getcDscEtapa() + "</option>");
                           } else
                             out.print("<option value=\"" + VGRLEtapa.getiCveEtapa() + "\" >" + VGRLEtapa.getcDscEtapa() + "</option>");
                        }
                    }
                    out.print("</select>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta", "ID DGPMPT:", "ECampo", "text", 12, 12,5,"cIDDGPMPT", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de Solicitud:", "ECampo", "text", 10, 10,5,"dtSolicitud", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de Vencimiento:", "ECampo", "text", 10, 10,5,"dtVencimiento", "", 3, "", "", true, true, true,request));
                    out.print("</tr>");



                  %>
            </table>
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <%
                 float fDiferencia = 0;
                 String cPersona = "";
                 if (bs != null){
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"10\">Situación de la Empresa</td>");
                     out.println("</tr>");
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\">ID DGPMPT</td>");
                     out.println("<td class=\"ETablaT\">RFC</td>");
                     out.println("<td class=\"ETablaT\">Razón Social</td>");
                     out.println("<td class=\"ETablaT\">Domicilio</td>");
                     out.println("<td class=\"ETablaT\">Ult. Plantilla</td>");
                     out.println("<td class=\"ETablaT\">Etapa</td>");
                     out.println("<td class=\"ETablaT\">Fecha Solicitud</td>");
                     out.println("<td class=\"ETablaT\">Oficio</td>");
                     out.println("<td class=\"ETablaT\">Fecha Vencimiento</td>");
                     out.println("<td class=\"ETablaT\">Fecha Recepción</td>");
                     out.println("</tr>");
                     bs.start();
                     TDCTRDomicilio DCTRDomicilio = new TDCTRDomicilio();
                     Vector vDomicilio = new Vector();
                     while(bs.nextRow()){
                         cOficio = "&nbsp";
                         TVGRLEmpresas x = (TVGRLEmpresas) bs.getCurrentBean();
                         out.println("<tr>");
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cIDDGPMPT", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cRFC", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEmpresa", "&nbsp;")));
                         if (new Integer(bs.getFieldValue("iCveDomicilio", "").toString()).intValue() != 0){
                            try{
                              vDomicilio = DCTRDomicilio.FindByCompleto(" where iCveEmpresa   = " + new Integer(bs.getFieldValue("iCveEmpresa", "").toString()).intValue()   +
                                                                        "   and iCveDomicilio = " + new Integer(bs.getFieldValue("iCveDomicilio", "").toString()).intValue() +
                                                                        "   and lActivo       = 1 "
                                                                        ,"");
                            } catch (Exception ex){
                            }

                            if (!vDomicilio.isEmpty()){
                              String cDomicilio = "";
                              for(int i=0;i<vDomicilio.size();i++){
                                 TVCTRDomicilio VCTRDomicilio = new TVCTRDomicilio();
                                 VCTRDomicilio = (TVCTRDomicilio) vDomicilio.get(i);
                                 cDomicilio = VCTRDomicilio.getCCalle()   + ",\n" +
                                              VCTRDomicilio.getCColonia() + ",\n" +
                                              VCTRDomicilio.getCCiudad()  + "," +
                                              VCTRDomicilio.getICP()      + ",\n" +
                                              VCTRDomicilio.getcNombreMunicipio() + ",\n" +
                                              VCTRDomicilio.getcNombreEntidad() + ",\n" +
                                              VCTRDomicilio.getcNombrePais()  + "\n";
                              }
                              out.print(vEti.Texto("ETabla",""+ cDomicilio));
                            } else
                             out.print(vEti.Texto("ETabla",""+ "No tiene"));
                         } else
                            out.print(vEti.Texto("ETabla",""+ "No tiene"));
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCvePlantilla", "&nbsp;")));
                         vPlantilla = clsConfig.getPlantilla(new Integer(bs.getFieldValue("iCveEmpresa", "&nbsp;").toString()).intValue(),
                                                             new Integer(bs.getFieldValue("iCvePlantilla", "&nbsp;").toString()).intValue());

                         vSeguimiento = clsConfig.getSeguimiento(new Integer(bs.getFieldValue("iCveEmpresa", "&nbsp;").toString()).intValue(),
                                                                 new Integer(bs.getFieldValue("iCvePlantilla", "&nbsp;").toString()).intValue(),
                                                                 new Integer(bs.getFieldValue("iCveMovimiento", "&nbsp;").toString()).intValue());

                         boolean lPinto = false;
                         if (!vSeguimiento.isEmpty()){
                            for(int i=0;i<vSeguimiento.size();i++){
                              TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
                              VCTRSeguimiento = (TVCTRSeguimiento) vSeguimiento.get(i);
                              cOficio = VCTRSeguimiento.getcNoOficio();
                              for (int j=0;j<vEtapas.size();j++){
                                 TVGRLEtapa VGRLEtapa = new TVGRLEtapa();
                                 VGRLEtapa = (TVGRLEtapa) vEtapas.get(j);
                                 if (new Integer(VCTRSeguimiento.getiCveEtapa()).toString().compareToIgnoreCase(new Integer(VGRLEtapa.getiCveEtapa()).toString()) == 0){
                                   out.print(vEti.Texto("ETabla",""+ VGRLEtapa.getcDscEtapa()));
                                   lPinto = true;
                                 }
                              }
                            }
                         }
                         if (!lPinto)
                            out.print(vEti.Texto("ETabla","&nbsp"));

                         boolean lPinto2 = false;
                         if (!vPlantilla.isEmpty()){
                            for(int i=0;i<vPlantilla.size();i++){
                              TVCTRPlantilla VCTRPlantilla = new TVCTRPlantilla();
                              VCTRPlantilla = (TVCTRPlantilla) vPlantilla.get(i);
                              if (VCTRPlantilla.getdtSolicitud() != null)
                                out.print(vEti.Texto("ETablaC",""+ Fecha.getFechaDDMMYYYY(VCTRPlantilla.getdtSolicitud(),"/")));
                              else
                                out.print(vEti.Texto("ETablaC","&nbsp"));
                              // No de Oficio.
                              if (cOficio != null){
                                 if (cOficio.compareToIgnoreCase("") != 0)
                                   out.print(vEti.Texto("ETabla",""+ cOficio));
                                 else
                                   out.print(vEti.Texto("ETabla","&nbsp"));
                              } else
                                out.print(vEti.Texto("ETabla","&nbsp"));
                              if (VCTRPlantilla.getdtVencimiento() != null)
                                out.print(vEti.Texto("ETablaC",""+ Fecha.getFechaDDMMYYYY(VCTRPlantilla.getdtVencimiento(),"/")));
                              else
                                out.print(vEti.Texto("ETablaC","&nbsp"));
                              if (VCTRPlantilla.getdtRecepcion() != null)
                                out.print(vEti.Texto("ETablaC",""+ Fecha.getFechaDDMMYYYY(VCTRPlantilla.getdtRecepcion(),"/")));
                              else
                                out.print(vEti.Texto("ETablaC","&nbsp"));
                            }

                         }
                         out.println("</tr>");
                     }
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"10\">Envio de Información a Excel</td>");
                     out.println("</tr>");
                     cSQL = clsConfig.cValSQL;
                     request.getSession(true).setAttribute("cRepSQL",cSQL);
                     out.print("<td class=\"ECampoC\" colspan=\"10\">");
                     out.print(vEti.clsAnclaTexto("EAncla","Enviar a Excel","JavaScript:fEnviarExcel('pgReporteBase.jsp','1','Situación Actual de la Empresa','No. Plantilla|Clave Empresa|Movimiento|Grupo|ID DGMPT|ID Modo de Transporte|RFC|CURP|Unidad Médica|Modo de Transporte|Tipo Persona|Apellido Paterno|Apellido Materno|Nombre|Razón Social|Fecha de Registro|Clave RPA|Clave Origen de la Información|Descripción|','');",""));
                     out.print("</td>");
                 } else {
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"11\">Mensaje</td>");
                     out.println("</tr>");
                     out.println("<tr>");
                     out.println("<td class=\"ECampo\">No Existen datos con el Filtro Proporcionado</td>");
                     out.println("</tr>");
                 }
              %>
            </table>
      </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');
      </script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>


