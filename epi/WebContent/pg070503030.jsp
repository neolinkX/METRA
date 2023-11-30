<%/**
 * Title:         Concentrado de Empresas
 * Description:   Concentrado de Empresas
 * Copyright:     2004
 * Company:       Microe Personales S.A. de C.V.
 * @author        Marco Antonio Hernández García
 * @version       1.0
 * Clase:         pg070503030
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
  pg070503030CFG  clsConfig = new pg070503030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070503030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070503030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070502032.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Etapa|";    // modificar
  String cCveOrdenar  = "iCveEtapa|cDscEtapa|";  // modificar
  String cDscFiltrar  = "Clave|Etapa|";    // modificar
  String cCveFiltrar  = "iCveEtapa|cDscEtapa|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  Vector vSeguimiento = new Vector();
  Vector vPersonalUltPlantilla = new Vector();
  Vector vPersonalPenUltPlantilla = new Vector();
  Vector vSegUltimaPlantilla = new Vector();
  // Para la Columna de Regularizados.
  int ivRegularizados = 0;
  int ivTotalRegularizados = 0;
  // Para la Columna de Bajas de Operadores.
  int ivBajas = 0;
  int ivTotalBajas = 0;
  // Para la Columna Total de Operadores.
  int ivOperadores = 0;
  int ivTotalOperadores = 0;
  // Para los Oficios.
  int ivOficios = 0;
  int ivTotalOficios = 0;
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
               out.print("<tr>");
               out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha Inicial:", "ECampo", "text", 10, 10,5,"dtFechaIni", "", 3, "", "", true, true, true,request));
               out.print("</tr>");
               out.print("<tr>");
               out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha Final:", "ECampo", "text", 10, 10,5,"dtFechaFin", "", 3, "", "", true, true, true,request));
               out.print("</tr>");
             %>
            </table>
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <%
                 // Valores del Vector;
                 vSeguimiento = clsConfig.vSeguimiento;
                 vSegUltimaPlantilla = clsConfig.vSegUltPlantilas;
                 if(request.getParameter("hdBoton").toString().compareTo("Enviar")==0 ) {
                    out.println(clsConfig.getActiveX());
                 }
                 if (bs != null){
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"9\">Transportistas por Etapa</td>");
                     out.println("</tr>");
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\">Clave</td>");
                     out.println("<td class=\"ETablaT\">Etapa</td>");
                     out.println("<td class=\"ETablaT\">Regularizados</td>");
                     out.println("<td class=\"ETablaT\">Bajas de Operadores</td>");
                     out.println("<td class=\"ETablaT\">Total de Operadores Activos</td>");
                     out.println("<td class=\"ETablaT\">Oficios</td>");
                     out.println("</tr>");
                     bs.start();

                     //Calculo del Número de Bajas.
                     vPersonalUltPlantilla = clsConfig.vPersonalUltEtapa;
                     vPersonalPenUltPlantilla = clsConfig.vPersonalPenUltEtapa;
                     if (!vPersonalUltPlantilla.isEmpty() && !vPersonalPenUltPlantilla.isEmpty()){
                        ivBajas = 0;
                        ivOperadores = 0;
                        ivTotalBajas = 0;
                        for(int j=0;j<vPersonalPenUltPlantilla.size();j++){
                            TVCTRPersonal VCTRPersonalPen = new TVCTRPersonal();
                            VCTRPersonalPen = (TVCTRPersonal) vPersonalPenUltPlantilla.get(j);

                          for(int i=0;i<vPersonalUltPlantilla.size();i++){
                             TVCTRPersonal VCTRPersonal = new TVCTRPersonal();
                             VCTRPersonal = (TVCTRPersonal) vPersonalUltPlantilla.get(i);

                             if (j == 0){
                               if (VCTRPersonal.getlActivo() == 0)
                                 ivBajas = ivBajas + 1;
                               if (VCTRPersonal.getlActivo() == 1)
                                 ivOperadores = ivOperadores + 1;
                             }

                             if (VCTRPersonalPen.getICveEmpresa()  == VCTRPersonal.getICveEmpresa()    &&
                                 VCTRPersonalPen.getCRFC().compareToIgnoreCase(VCTRPersonal.getCRFC()) == 0 ){



                             }
                          }
                        }
                     }


                     while(bs.nextRow()){
                         cOficio = "&nbsp";
                         TVGRLEtapa x = (TVGRLEtapa) bs.getCurrentBean();
                         out.println("<tr>");
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveEtapa", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEtapa", "&nbsp;")));
                         //Calculo de Regularizados.
                         ivRegularizados = 0;
                         if (!vSeguimiento.isEmpty()){
                           for(int i=0;i<vSeguimiento.size();i++){
                             TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
                             VCTRSeguimiento = (TVCTRSeguimiento) vSeguimiento.get(i);
                             if (VCTRSeguimiento.getiCveEtapa() == new Integer(bs.getFieldValue("iCveEtapa", "").toString()).intValue())
                               ivRegularizados = ivRegularizados + 1;
                           }
                         }
                         out.print(vEti.Texto("ETablaR",""+ ivRegularizados));
                         if (new Integer(bs.getFieldValue("iCveEtapa", "").toString()).intValue() == 2){
                           out.print(vEti.Texto("ETablaR",""+ ivBajas));
                           out.print(vEti.Texto("ETablaR",""+ ivOperadores));
                         } else {
                           out.print(vEti.Texto("ETabla",""+ "&nbsp;"));
                           out.print(vEti.Texto("ETabla",""+ "&nbsp;"));
                         }
                         ivOficios = 0;
                         if (bs.getFieldValue("cDocumento", "").toString().compareToIgnoreCase("") != 0){
                           if (!vSegUltimaPlantilla.isEmpty()){
                              for(int i=0;i<vSegUltimaPlantilla.size();i++){
                                TVCTRSeguimiento VCTRSeguimiento = new TVCTRSeguimiento();
                                VCTRSeguimiento = (TVCTRSeguimiento) vSegUltimaPlantilla.get(i);
                                if (VCTRSeguimiento.getiCveEtapa() == new Integer(bs.getFieldValue("iCveEtapa", "").toString()).intValue())
                                   ivOficios = ivOficios + 1;
                              }
                           }
                         }
                         out.print(vEti.Texto("ETablaR",""+ ivOficios));
                         out.println("</tr>");
                         ivTotalRegularizados = ivTotalRegularizados + ivRegularizados;
                         ivTotalOficios = ivTotalOficios + ivOficios;
                     }
                     out.println("<tr>");
                     out.print(vEti.Texto("ETabla",""+ "&nbsp;"));
                     out.print(vEti.Texto("ETablaR",""+ "TOTAL:"));
                     out.print(vEti.Texto("ETablaR",""+ ivTotalRegularizados));
                     out.print(vEti.Texto("ETablaR",""+  ivBajas));
                     out.print(vEti.Texto("ETablaR",""+  ivOperadores));
                     out.print(vEti.Texto("ETablaR","" + ivTotalOficios));
                     out.println("</tr>");
                     out.println("<tr>");
                     out.println("<td class=\"ETablaT\" colspan=\"9\">Envio de Información a Excel</td>");
                     out.println("</tr>");
                     cSQL = clsConfig.cValSQL;
                     request.getSession(true).setAttribute("cRepSQL",cSQL);
                     out.print("<td class=\"ECampoC\" colspan=\"9\">");
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


