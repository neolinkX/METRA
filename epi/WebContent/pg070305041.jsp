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
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>

<html>
<%
  pg070305041CFG  clsConfig = new pg070305041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305041.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305041.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Posición|Análisis|NIM|Unidad Médica|";    // modificar
  String cCveOrdenar  = "CA.iPosicion|CA.iCveAnalisis|M.iCveMuestra|M.iCveUniMed|";  // modificar
  String cDscFiltrar  = "Análisis|NIM|";    // modificar
  String cCveFiltrar  = "CA.iCveAnalisis|M.iCveMuestra|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cPosicion = "";
  String cClave    = "";
  String cClave2   = "";
  String cClave3   = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fAccion(cAccion){
    form = document.forms[0];
    form.target="_self";
    form.hdBoton.value = cAccion;
    form.submit();
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
  <input type="hidden" name="hdRowNum" value="<%  if (request.getParameter("hdRowNum")!=null)   out.print(request.getParameter("hdRowNum")); else out.print(cPosicion);%>">
  <input type="hidden" name="hdCampoClave" value="<%  if (request.getParameter("hdCampoClave")!=null)   out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave2" value="<% if (request.getParameter("hdCampoClave2")!=null) out.print(request.getParameter("hdCampoClave2")); else out.print(cClave2);%>">
  <input type="hidden" name="hdCampoClave3" value="<% if (request.getParameter("hdCampoClave3")!=null) out.print(request.getParameter("hdCampoClave3")); else out.print(cClave3);%>">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="iAnio" value="<%=request.getParameter("iAnio")%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveLaboratorio" value="<%=request.getParameter("iCveLaboratorio")%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
        if(request.getParameter("hdBoton").toString().compareTo("Generar Reporte")==0) {
           out.println(clsConfig.getActiveX());
        }
        if(request.getParameter("hdBoton").toString().compareTo("Reporte")==0) {
           out.println(clsConfig.getActiveY());
        }
        String iCveLoteCuantita = "";
        
  %>
    <tr>
       <td valign="top">          &nbsp;
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                <tr><td colspan="13" class="ETablaT">Información del Lote Confirmatorio</td></tr>
                <% if (bs != null){
                   TVTOXLoteCuantDetalle Lote = new TVTOXLoteCuantDetalle ();
                   TFechas Fecha = new TFechas();
                   Lote = (TVTOXLoteCuantDetalle) clsConfig.VLote.get(0);
                   out.println("<tr>");
                   out.print(vEti.Texto("EEtiqueta", "Año:"));
                   out.print(vEti.Texto("ETabla",  Lote.VLote.getiAnio().toString()));
                   out.println("</tr><tr>");
                   out.print(vEti.Texto("EEtiqueta", "Sustancia:"));
                   out.print(vEti.Texto("ETabla",  Lote.VLote.VSustancia.getCPrefLoteConf()));
                   out.print(vEti.Texto("EEtiqueta", "Lote:"));
                   out.print(vEti.Texto("ETabla",  Lote.VLote.getiCveLoteCuantita().toString()));
                   iCveLoteCuantita = Lote.VLote.getiCveLoteCuantita().toString();
                   out.println("</tr><tr>");
                   out.print(vEti.Texto("EEtiqueta", "Generado:"));
                   out.print(vEti.Texto("ETablaC",  Lote.VLote.getdtGeneracion()   != null? Fecha.getFechaDDMMYYYY(Lote.VLote.getdtGeneracion(),"/") : "-" ));
                   out.print(vEti.Texto("EEtiqueta", "Por:"));
                   out.print(vEti.Texto("ETabla",  Lote.getCUsuGenera() != null ? Lote.getCUsuGenera() : "-"));
                   out.println("</tr><tr>");
                   out.print(vEti.Texto("EEtiqueta", "Calibración:"));
                   out.print(vEti.Texto("ETablaC",  Lote.VLote.getdtCalibracion()  != null? Fecha.getFechaDDMMYYYY(Lote.VLote.getdtCalibracion(),"/") : "-" ));
                   out.print(vEti.Texto("EEtiqueta", "Resultado:"));
                   out.print(vEti.Texto("ETabla",  Lote.VLote.getdtCalibracion()  != null? Lote.VLote.getlValidaCalib().intValue() == 1?"Correcta":"Incorrecta" : "-" ));
                   out.println("</tr><tr>");
                   out.print(vEti.Texto("EEtiqueta", "Analizado:"));
                   out.print(vEti.Texto("ETablaC",  Lote.VLote.getdtAnalisis()     != null? Fecha.getFechaDDMMYYYY(Lote.VLote.getdtAnalisis(),"/") : "-" ));
                   out.print(vEti.Texto("EEtiqueta", "Por:"));
                   out.print(vEti.Texto("ETabla",  Lote.getCUsuAnalista() != null ? Lote.getCUsuAnalista() : "-"));
                   out.println("</tr><tr>");
                   out.print(vEti.Texto("EEtiqueta", "Autorizado:"));
                   out.print(vEti.Texto("ETablaC",  Lote.VLote.getdtAutorizacion() != null? Fecha.getFechaDDMMYYYY(Lote.VLote.getdtAutorizacion(),"/") : "-" ));
                   out.print(vEti.Texto("EEtiqueta", "Por:"));
                   out.print(vEti.Texto("ETabla",  Lote.getCUsuAutoriza() != null ? Lote.getCUsuAutoriza() : "-"));
                   out.println("</tr>");
                }
                %>
              </table>
       </td>
    </tr>
    <tr><td colspan="13" class="EPie">* Conc. = Concentraci&oacute;n</td>
    </tr>
    <tr>
       <td valign="top">          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="13" class="ETablaT">Detalle del Lote
                              </td>
                            </tr>
                          <% if (bs != null){ %>
                            <tr>
                              <td class="ETablaT">Análisis</td>
                              <td class="ETablaT" width="75">NIM</td>
                              <td class="ETablaT">Unidad Médica</td>
                              <td class="ETablaT">Envío</td>
                              <td class="ETablaT">Módulo</td>
                              <td class="ETablaT">Lote<br>Presuntivo</td>
                              <td class="ETablaT">Autorizado</td>
                              <td class="ETablaT">Conc.<br>ng/ml</td>
                              <td class="ETablaT">Resultado</td>
                              <td class="ETablaT">Otras Sustancias</td>
                              <td class="ETablaT">Observaciones</td>
                            </tr>
                             <% // modificar según listado
                             TVTOXAnalisisCuant VAnalisis;
                             TFechas Fecha = new TFechas();
                             for(int i=0; i < clsConfig.VRegistros.size(); i++){
                                VAnalisis = new TVTOXAnalisisCuant ();
                                VAnalisis = (TVTOXAnalisisCuant) clsConfig.VRegistros.get(i);
                                out.print("<tr class=\"" + (VAnalisis.VCAnal.getlAutorizado().intValue() == 1 ? "ETabla " : "ETablaST") + "\">");
                                out.print(vEti.Texto("ETablaR", VAnalisis.VMuestra.getCAnalisis()));
                                out.print(vEti.Texto("ETablaR", String.valueOf(VAnalisis.VMuestra.getCMuestra())));
                                out.print(vEti.Texto("ETabla",  VAnalisis.VMuestra.getCDscUM()));
                                out.print(vEti.Texto("ETablaR", String.valueOf(VAnalisis.VMuestra.getICveEnvio())));
                                out.print(vEti.Texto("ETabla",  VAnalisis.VMuestra.getCDscModulo()));
                                out.print(vEti.Texto("ETablaR", VAnalisis.VAnalisis.getiCveLoteCualita().toString()));
                                out.print(vEti.Texto("ETablaC", VAnalisis.VCAnal.getlAutorizado().intValue() == 1? "S&Iacute":"NO / Rean&aacute;lisis"));
                                out.print(vEti.Texto("ETablaR", VAnalisis.VCAnal.getdResultado().toString()));
                                out.print(vEti.Texto(VAnalisis.VCAnal.getlResultado().intValue() == 1 ? "EPositivosR": "ETablaR",
                                                     VAnalisis.VCAnal.getlAutorizado().intValue() == 1
                                                     ? VAnalisis.VCAnal.getlResultado().intValue() == 1 ? "POSITIVO": "NEGATIVO"
                                                     : "&nbsp;"));
                                //if(!VAnalisis.VAnalisis.getISustPost().equals(new Integer(1))){
                                if(!VAnalisis.VAnalisis.getISustPost().equals(new Integer(0))){
                                  // Enviar información de otras sustancias
                                  //System.out.println("Entro a la sentencia "+ VAnalisis.VAnalisis.getISustPost());
                                  out.print(vEti.Texto("ETablaC",clsConfig.getOtrasSust(VAnalisis.VCAnal, iCveLoteCuantita)));
                                }
                                else
                                  out.print(vEti.Texto("ETabla", "&nbsp;"));
                                out.print(vEti.Texto("ETabla",  VAnalisis.VCAnal.getcObservacion() != null ? VAnalisis.VCAnal.getcObservacion() : "&nbsp;"));
                                out.print("</tr>");
                             }
                            %>
                          </table>
                          <% }
                               else{
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                 out.println("</tr>");
                               }

                          %>
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
