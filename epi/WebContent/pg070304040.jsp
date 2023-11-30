<%/**
 * Title: Analisis Confirmatorio.
 * Description: Operatividad CG/EM
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
  pg070304040CFG  clsConfig = new pg070304040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070304040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070304040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070304040.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Laboratorio|Parámetro de Calibración|";    // modificar
  String cCveOrdenar  = "iCveLaboratorio|iCveParamCalib|";  // modificar
  String cDscFiltrar  = "Laboratorio|Parámetro de Calibración|";    // modificar
  String cCveFiltrar  = "iCveLaboratorio|iCveParamCalib|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
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
  String cAnio = "";
  String cvLoteConfirmativo = "";

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
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
</SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<head>
  <meta name="Autor" content="<%=vParametros.getPropEspecifica("Autor") %>">
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
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         &nbsp;
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda del Resultado por Equipo y Fecha</td>
           </tr><tr>
             <td class="EEtiqueta">Laboratorio:</td>
             <td class="ETabla">
             <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                 int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                 out.print(vEti.SelectOneRowSinTD("iCveUniMed","",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0"));
                 // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);
             %>
             </td>
             <td class="EEtiqueta">Equipo:</td>
             <td class="ETabla">
               <%
                  TDTOXEquipo dTOXEquipo = new TDTOXEquipo();
                  TVTOXEquipo vTOXEquipo = new TVTOXEquipo();
                  Vector vEquipo = new Vector();
                  vEquipo = dTOXEquipo.FindByAll(" WHERE lCuantCual = 1 ","");
                  out.println(vEti.SelectOneRowSinTD("SLSEquipo", "", vEquipo, "iCveEquipo", "cCveEquipo", request, "07"));
                %>
             </td>
           </tr><tr>
             <td class="EEtiqueta">Fecha:</td>
             <td class="ETabla">
               <%
                 TFechas Fecha = new TFechas();
                 if (request.getParameter("dtCalEquipo") != null)
                    out.print(vEti.CampoSinCelda("input",10,10,"dtCalEquipo" ,request.getParameter("dtCalEquipo") ,3,"","",true,true));
                 else
                    out.print(vEti.CampoSinCelda("input",10,10,"dtCalEquipo" ,Fecha.getFechaDDMMYYYY(Fecha.TodaySQL(),"/"),3,"","",true,true));

               %>
             </td>
             <td class="EEtiqueta">&nbsp</td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "EquipoFecha",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar Resultado por Equipo y Fecha","EquipoFecha", vParametros) %></td>
           </tr>
         </table>
         &nbsp;
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Aceptación de la Validación</td>
           </tr><tr>
             <td class="EEtiqueta">Validación:</td>
               <%
                Vector vTOXCalibEquipo = clsConfig.vCalibEquipo;
                if (!vTOXCalibEquipo.isEmpty())
                   out.print(vEti.ToggleMov("ECampo", "TBXValidacion","0" ,"", 59, true, "Sí", "No", true));
                else
                   out.print(vEti.ToggleMov("ECampo", "TBXValidacion","0" ,"", 59, true, "Sí", "No", false));
                %>
           </tr>
         </table>
         &nbsp;
          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
            <tr>
              <td colspan="2" class="ETablaT">Resultado de la Calibración del Equipo</td>
            </tr>
             <% // modificar según listado
               DecimalFormat dfNumber = new DecimalFormat("#######0.00");
               if (bs != null){
                   bs.start();
                   Vector vTOXCalib       = clsConfig.vCalib;
                   int m = 0;
                   while(bs.nextRow()){
                     m = m + 1;
                     if (m == 1){
                       if (!vTOXCalibEquipo.isEmpty()){
                            TVTOXCalibEquipo VTOXCalibEquipo = new TVTOXCalibEquipo();
                            VTOXCalibEquipo = (TVTOXCalibEquipo) vTOXCalibEquipo.get(0);
                         out.print("<tr>");
                         out.print(vEti.Texto("EEtiqueta", "No. de Revisión:"));
                         out.print(vEti.Texto("EEtiqueta", VTOXCalibEquipo.getiCveCalib().toString()));
                         out.print("</tr>");
                       } else {
                         out.print("<tr>");
                         out.print(vEti.Texto("EEtiqueta", "No. de Revisión:"));
                         out.print(vEti.Texto("EEtiqueta", "Nueva Revisi&oacute;n"));
                         out.print("</tr>");
                       }
                     }
                     boolean lPinto = false;
                     out.print("<tr>");
                     out.print(vEti.Texto("EEtiqueta", bs.getFieldValue("cDscParam", "&nbsp;").toString() + ":"));
                     if (request.getParameter("SLSEquipo") != null)
                       if (!vTOXCalibEquipo.isEmpty())
                          for (int i=0;i<vTOXCalibEquipo.size();i++){
                            TVTOXCalibEquipo VTOXCalibEquipo = new TVTOXCalibEquipo();
                            VTOXCalibEquipo = (TVTOXCalibEquipo) vTOXCalibEquipo.get(i);
                            if (VTOXCalibEquipo.getiCveEquipo().toString().compareToIgnoreCase(request.getParameter("SLSEquipo").toString()) == 0){
                               if (!vTOXCalib.isEmpty())
                                  for (int j=0;j<vTOXCalib.size();j++){
                                    TVTOXCalib VTOXCalib = new TVTOXCalib();
                                    VTOXCalib = (TVTOXCalib) vTOXCalib.get(j);
                                    if (VTOXCalib.getiCveEquipo().toString().compareToIgnoreCase(VTOXCalibEquipo.getiCveEquipo().toString()) == 0)
                                      if (VTOXCalib.getiCveCalib().toString().compareToIgnoreCase(VTOXCalibEquipo.getiCveCalib().toString()) == 0)
                                        if (VTOXCalib.getiCveLaboratorio().toString().compareToIgnoreCase(bs.getFieldValue("iCveLaboratorio", "&nbsp;").toString()) == 0)
                                           if (VTOXCalib.getiCveParamCalib().toString().compareToIgnoreCase(bs.getFieldValue("iCveParamCalib", "&nbsp;").toString()) == 0){
                                             if (!lPinto){
                                               out.print("<td class=\"ECampo\">");
                                               out.print(vEti.CampoSinCelda("input",6,6,"FILValor-" + bs.getFieldValue("iCveParamCalib", "&nbsp;").toString() , dfNumber.format(new Double(VTOXCalib.getdValor().toString())) ,3,"","",true,true));
                                               out.print("</td>");
                                               lPinto = true;
                                             }
                                           }
                                  }
                            }
                          }

                     if (!lPinto){
                       out.print("<td class=\"ECampo\">");
                       out.print(vEti.CampoSinCelda("input",6,6,"FILValor-" + bs.getFieldValue("iCveParamCalib", "&nbsp;").toString() , "",3,"","",true,true));
                       out.print("</td>");
                     }
                     out.print("</tr>");
                   }
                   if (m >= 1){
                     if (!vTOXCalibEquipo.isEmpty()){
                          TVTOXCalibEquipo VTOXCalibEquipo = new TVTOXCalibEquipo();
                          VTOXCalibEquipo = (TVTOXCalibEquipo) vTOXCalibEquipo.get(0);
                       out.print("<tr>");
                       out.print(vEti.Texto("EEtiqueta", "Parámetros de Calibración:"));
                       if (VTOXCalibEquipo.getlCorrecto().toString().compareToIgnoreCase("1") == 0)
                          out.print(vEti.ToggleMov("ECampo", "TBXCorrecto","1" ,"", 59, true, "Sí", "No", true));
                       else
                          out.print(vEti.ToggleMov("ECampo", "TBXCorrecto","0" ,"", 59, true, "Sí", "No", true));
                       out.print("</tr>");
                     out.println("<tr>");
                     out.print(vEti.EtiAreaTexto("EEtiqueta","Observación",
                                                             "ETabla",30,5,
                                                             "cObservacion", VTOXCalibEquipo.getcObservacion() == null ? "" : VTOXCalibEquipo.getcObservacion(),
                                                             0,"","",true, true, true));
                     out.println("</tr>");
                     } else {
                       out.print("<tr>");
                       out.print(vEti.Texto("EEtiqueta", "Parámetros de Calibración:"));
                       out.print(vEti.ToggleMov("ECampo", "TBXCorrecto","0" ,"", 59, true, "Sí", "No", true));
                       out.print("</tr>");

                     }
                   }
               }
               else{
                  out.println("<tr>");
                  out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                  out.println("</tr>");
               }
            %>
          </table>
        </td>
      </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
