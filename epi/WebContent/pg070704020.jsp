<%/**
 * Title:       Programación de Mantenimientos
 * Description: Programación de Mantenimientos
 * Copyright:   2004
 * Company:     Micros Personales
 * @author      Marco Antonio Hernández García
 * @version     1.0
 * Clase:       pg070704020.jsp
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070704020CFG  clsConfig = new pg070704020CFG();

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070704020.jsp");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070704020.jsp\" target=\"FRMCuerpo");
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";
  String cOperador    = "1";
  String cDscOrdenar  = "Vehículo|";
  String cCveOrdenar  = "VEHVehiculo.iCveVehiculo|";
  String cDscFiltrar  = "Vehículo|";
  String cCveFiltrar  = "VEHVehiculo.iCveVehiculo|";
  String cTipoFiltrar = "7|";
  boolean lFiltros    = false;
  boolean lIra        = false;
  String cEstatusIR   = "IR";

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
  String cNavStatus  = "Hidden";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";

  Vector vMantProg = new Vector();
  Vector vMantNProg = new Vector();

  TDVEHTpoMantto dVEHTpoMantto = new TDVEHTpoMantto();
  Vector vTemp = new Vector();
/*
  int iAnioIni = new Integer(vParametros.getPropEspecifica("iAniosIni")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnioFin = dtFecha.getIntYear(dtFecha.TodaySQL())+1;
  String cMeses = vParametros.getPropEspecifica("NombresMes");
  StringTokenizer st = new StringTokenizer(cMeses,",");
  int i = 0;*/
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  int iCveProceso     = Integer.parseInt(vParametros.getPropEspecifica("CtrlVeh")); // 7
  int iCveEtapa       = Integer.parseInt(vParametros.getPropEspecifica("CTREtapaInicial")); // 1
  int iCveSolicitante = Integer.parseInt(vParametros.getPropEspecifica("CTRSolicitanteIni")); // 1
  DecimalFormat df = new DecimalFormat("#,##0");
  String dFechaActual = new TFechas().getFechaDDMMYYYY(new java.sql.Date(new java.util.Date().getTime()),"/");

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
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCveUsuSesion" value="<%=vUsuario!=null?vUsuario.getICveusuario():0%>">
  <input type="hidden" name="iCveProceso" value="<%=iCveProceso%>">
  <input type="hidden" name="iCveEtapa" value="<%=iCveEtapa%>">
  <input type="hidden" name="iCveSolicitante" value="<%=iCveSolicitante%>">
  <input type="hidden" name="cDscTpoMantto" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
          <input type="hidden" name="hdBoton" value="">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="6">Programar Mantenimiento</td>
            </tr>
               <%
                out.println("<tr>");
                out.println("<td class=\"EEtiqueta\">Vehiculo:</td>");
                out.println("<td>");
                out.println("<select name=\"iCveVehiculo\">");
                out.println("<option value=\"0\">Seleccione... </option>");
                Vector vVehiculo = new TDVEHVehiculo().FindByAll("","");
                if(vVehiculo != null && vVehiculo.size() > 0){
                   for(int k = 0; k < vVehiculo.size(); k++){
                      TVVEHVehiculo tvVehiculo = (TVVEHVehiculo)vVehiculo.get(k);
                      if(request.getParameter("iCveVehiculo") != null && Integer.parseInt(request.getParameter("iCveVehiculo")) == tvVehiculo.getICveVehiculo() )
                         out.println("<option value=\""+tvVehiculo.getICveVehiculo()+"\" selected >"+tvVehiculo.getCPlacas()+"</option>");
                      else
                         out.println("<option value=\""+tvVehiculo.getICveVehiculo()+"\" >"+tvVehiculo.getCPlacas()+"</option>");
                   }
                }
                out.println("</select>");
                out.println("</td>");

                out.println("<td class=\"EEtiqueta\">Empresa de <br> Mantenimiento:</td>");
                out.println("<td>");
                out.println("<select name=\"iCveEmpMantto\">");
                out.println("<option value=\"0\">Seleccione... </option>");
                Vector vEmpMantto = new TDVEHEmpMantto().FindByAll();
                if(vEmpMantto != null && vEmpMantto.size() > 0){
                   for(int k = 0; k < vEmpMantto.size(); k++){
                      TVVEHEmpMantto tvEmpMantto = (TVVEHEmpMantto)vEmpMantto.get(k);
                      if(request.getParameter("iCveEmpMantto") != null && Integer.parseInt(request.getParameter("iCveEmpMantto")) == tvEmpMantto.getICveEmpMantto() )
                         out.println("<option value=\""+tvEmpMantto.getICveEmpMantto()+"\" selected >"+tvEmpMantto.getCDscBreve()+"</option>");
                      else
                         out.println("<option value=\""+tvEmpMantto.getICveEmpMantto()+"\" >"+tvEmpMantto.getCDscBreve()+"</option>");
                   }
                }
                out.println("</select>");
                out.println("</td>");
                out.println("<td class=\"EEtiqueta\">Tipo de <br> Mantenimiento:</td>");
                out.println("<td>");
                vTemp = dVEHTpoMantto.FindByAll("","");
                out.print(vEti.SelectOneRowSinTD("iCveTpoMantto","",vTemp,"iCveTpoMantto","cDscBreve",request,"0",true));
                out.println("</td>");
                out.println("</tr>");
                out.println("<tr>");

                out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha desde: ", "ECampo", "text\" readonly=\"yes", 10, 10,1,"dtDesde", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                out.print(vEti.EtiCampoCS("EEtiqueta", "Hasta: ", "ECampo", "text\" readonly=\"yes", 10, 10,1,"dtHasta", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                out.println("<td colspan=\"1\" ><td>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td class=\"EEtiqueta\">Filtrar por:</td>");
                out.println("<td>");
                out.println("<select name=\"iFiltro\">");

                String cSelect1 = "selected";
                String cSelect2 = "";
                String cSelect3 = "";
                String cSelect4 = "";
                String cSelect5= "";
                if(request.getParameter("iFiltro") != null){
                  if(Integer.parseInt(request.getParameter("iFiltro")) == 2 ){
                    cSelect1 = "";
                    cSelect2 = "selected";
                  }
                  else if(Integer.parseInt(request.getParameter("iFiltro")) == 3 ){
                    cSelect1 = "";
                    cSelect3 = "selected";
                  }
                  else if(Integer.parseInt(request.getParameter("iFiltro")) == 4 ){
                    cSelect1 = "";
                    cSelect4 = "selected";
                  }
                  else if(Integer.parseInt(request.getParameter("iFiltro")) == 5 ){
                    cSelect1 = "";
                    cSelect5 = "selected";
                  }
                }

                out.println("<option value=\"1\" "+cSelect1+" >Solicitud</option>");
                out.println("<option value=\"2\" "+cSelect2+" >Programado</option>");
                out.println("<option value=\"3\" "+cSelect3+" >Inicio</option>");
                out.println("<option value=\"4\" "+cSelect4+" >Recepción</option>");
                out.println("<option value=\"5\" "+cSelect5+" >Cancelación</option>");
                out.println("</select>");
                out.println("</td>");
                out.println("<td>");
                out.print(vEti.clsAnclaTexto("EAnclaC","Buscar","javascript:fBuscar();","Buscar"));
                out.println("</td>");
                out.println("</tr>");
               %>
          </table>
          &nbsp;

          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="15">Vehículos Encontrados</td>
            </tr>
            <tr>
              <td class="ETablaT">Empresa.</td>
              <td class="ETablaT">Fecha Solicitud.</td>
              <td class="ETablaT">Fecha Programado.</td>
              <td class="ETablaT">Fecha Inicia.</td>
              <td class="ETablaT">Fecha Recepci&oacute;n.</td>
              <td class="ETablaT">Vehiculo.</td>
              <td class="ETablaT">Marca.</td>
              <td class="ETablaT">Modelo.</td>
              <td class="ETablaT">Placas.</td>
              <td class="ETablaT">Kilometraje.</td>
              <td class="ETablaT">Concluido.</td>
              <td class="ETablaT">Tipo de Mantto.</td>
              <td class="ETablaT">Observaciones.</td>
              <td class="ETablaT">Cancelado.</td>
              <td class="ETablaT">Fecha Cancelaci&oacute;n.</td>
            </tr>
            <%
               if (bs != null){
                   bs.start();
                   while(bs.nextRow()){
                          out.println("<tr>");
                          out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("cDscEmpMantto", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtSolicitud", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtProgramado", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtInicia", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtRecepcion", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoVehiculo", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMarca", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscModelo", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cPlacas", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iKilometraje", "&nbsp;")));
                          if(bs.getFieldValue("lConcluido") != null && bs.getFieldValue("lConcluido").equals("1"))
                            out.print(vEti.Texto("ETabla","SI"));
                          else if(bs.getFieldValue("lConcluido") != null && bs.getFieldValue("lConcluido").equals("0"))
                            out.print(vEti.Texto("ETabla","NO"));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoMantto", "&nbsp;")));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cObservaciones", "&nbsp;")));
                          if(bs.getFieldValue("lCancelado") != null && bs.getFieldValue("lCancelado").equals("1"))
                            out.print(vEti.Texto("ETabla","SI"));
                          else if(bs.getFieldValue("lCancelado") != null && bs.getFieldValue("lCancelado").equals("0"))
                            out.print(vEti.Texto("ETabla","NO"));
                          out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtCancelacion", "&nbsp;")));
                          out.print("</tr>");
                       }

               }else{
                 out.println("<tr>");
                 out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 10,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                 out.println("</tr>");
               }
            %>
          </table>
          &nbsp;
          </table>
       </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}

  if(clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() > 0){
     out.println(clsConfig.getSbReporte());
  }
  else if(request.getParameter("hdBoton") != null && request.getParameter("hdBoton").equals("Generar Reporte") &&
    clsConfig.getSbReporte() != null && clsConfig.getSbReporte().length() == 0){
%><script language="JavaScript">alert("No Hay Registros Coincidentes.");</script>
<%
  }
%>


 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

