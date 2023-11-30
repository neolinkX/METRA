<%/**
 * Title: 
 * Description:
 * Copyright:
 * Company: 
 * @author
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
<%@ page import="java.text.DecimalFormat"%>

<html>
<%
  pg070304050CFG  clsConfig = new pg070304050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070304050.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070304050.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070304050.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Lote Presuntivo|No.Análisis|";    // modificar
  String cCveOrdenar  = "iCveLoteCualita|iCveAnalisis|";  // modificar
  String cDscFiltrar  = "Lote Presuntivo|No.Análisis|";    // modificar
  String cCveFiltrar  = "iCveLoteCualita|iCveAnalisis|";  // modificar
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
  Vector vDespliega = clsConfig.vDespliega2;
  double dvDilCalibrador = 0;
  double dvDilNegativo = 0;
  double dvDilControl1 = 0;
  double dvDilControl2 = 0;
  double dvDilControl3 = 0;
  int ivAcCorrectiva = 0;
  String cvObservacion = "";


  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070304050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070304050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
    }
  }
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fAccion(cAccion){
    form = document.forms[0];
    if(confirm("¿Desea validar la calibración?")){
      form.target="_self";
      form.hdBoton.value = cAccion;
      form.submit();
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
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top"><input type="hidden" name="hdBoton" value="">
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Búsqueda del Lote Confirmatorio</td>
           </tr><tr>
             <td class="EEtiqueta">Año:</td>
             <td class="ECampo">
                <select name="SLSAnio" size="1" onChange="llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].SLSSustancia.value,document.forms[0].SLSLoteConfirmativo);">
                <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                    if (request.getParameter("SLSAnio")!=null&&request.getParameter("SLSAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                       out.print("<option value = " + i + " selected>" + i + "</option>");
                    else
                       out.print("<option value = " + i + ">" + i + "</option>");
                   }
                %>
                </select>
             </td>
             <td class="EEtiqueta">Laboratorio:</td>
             <td class="ETabla">
             <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                 int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                 out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].SLSSustancia.value,document.forms[0].SLSLoteConfirmativo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0"));
                 // llenaSLT(1,document.forms[0].iAnio.value,this.value,'',document.forms[0].iCveLoteCualita);
             %>
             </td>
             <td class="EEtiqueta">&nbsp</td>
           </tr><tr>
             <td class="EEtiqueta">Sustancia:</td>
             <td class="ECampo">
               <%
                  out.println(vEti.SelectOneRowSinTD("SLSSustancia", "llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].SLSSustancia.value,document.forms[0].SLSLoteConfirmativo);", (Vector) AppCacheManager.getColl("TOXSustancia",""), "iCveSustancia", "cDscSustancia", true, request, "0", true));
               %>
             </td>
             <td class="EEtiqueta">Lote Confirmatorio:</td>
             <td class="ECampo">
                <select name="SLSLoteConfirmativo" size="1">
                <% if (request.getParameter("SLSLoteConfirmativo") != null){
                      if (request.getParameter("SLSLoteConfirmativo") != "")
                          cvLoteConfirmativo = request.getParameter("SLSLoteConfirmativo");
                    }

                    if (cvLoteConfirmativo.compareToIgnoreCase("0") == 0)
                        out.print("<option selected value=\"" + "0" + "\">"  + "Seleccione el Lote" + "</option>");
                    else
                        out.print("<option value=\"" + "0" + "\">"  + "Seleccione el Lote" + "</option>");

                    Vector vLoteCuantita = new Vector();
                    vLoteCuantita = clsConfig.vLoteCuantita;
                    if (vLoteCuantita != null)
                      if (!vLoteCuantita.isEmpty())
                        for(int i=0;i<vLoteCuantita.size();i++){
                          TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) vLoteCuantita.get(i);
                          if (cvLoteConfirmativo.compareToIgnoreCase(VTOXLoteCuantita.getiCveLoteCuantita().toString()) == 0)
                             out.print("<option selected value=\"" + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "\">"  + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "</option>");
                          else
                             out.print("<option value=\"" + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "\">"  + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "</option>");
                        }
                %>
                </select>
             </td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "LoteConfirmativo",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar el Lote Confirmatorio","LoteConfirmativo", vParametros) %></td>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center">
                       <%
                           Vector VCalibracion = new Vector();
                           VCalibracion = clsConfig.vDespliega2;
                           if(clsConfig.lIntegraCalib){
                           if(VCalibracion.size() > 0){
                        %>
                            <tr>
                              <td colspan="11" class="ETablaT">Calibradores del Lote Confirmatorio</td>
                            </tr>
                            <tr>
                              <td colspan="8" class="ETablaT">Sustancia</td>
                              <td colspan="3" class="ETablaT">Estándar Interno</td>
                            </tr>
                            <tr>
                              <td <%= clsConfig.VInfoCalibrador != null ? "rowspan=\"2\"":"\"\""%> class="ETablaT">Calibrador</td>
                              <td <%= clsConfig.VInfoCalibrador != null ? "rowspan=\"2\"":"\"\""%> class="ETablaT" colspan="3" >Concentraci&oacute;n</td>
                              <td class="ETablaT">Tiempo de<br>Retenci&oacute;n</td>
                              <td <%= clsConfig.VInfoCalibrador != null ? "rowspan=\"2\"":"\"\""%> class="ETablaT">Ion 01</td>
                              <td class="ETablaT">Ion 02</td>
                              <td class="ETablaT">Ion 03</td>
                              <td class="ETablaT">Tiempo de<br>Retenci&oacute;n</td>
                              <td <%= clsConfig.VInfoCalibrador != null ? "rowspan=\"2\"":"\"\""%> class="ETablaT">Ion 01</td>
                              <td class="ETablaT">Ion 02</td>
                            </tr>
                            <% // Valores del calibrador
                               DecimalFormat Formato = new DecimalFormat("#0.00");
                                  out.println("<tr>");
                               //if(clsConfig.VInfoCalibrador != null && clsConfig.VInfoCalibrador.VAnalisis.getLRegistrado() == new Integer(1)){
                               if(clsConfig.VInfoCalibrador != null && clsConfig.VInfoCalibrador.VAnalisis.getLRegistrado().intValue() == 1){ 
                                  // Presentar valores del calibrador

                                  out.println(vEti.Texto("EPositivosC", Formato.format(clsConfig.VInfoCalibrador.getDTmpRetencInf()) + " - " + Formato.format(clsConfig.VInfoCalibrador.getDTmpRetencSup()) ));
                                  out.println(vEti.Texto("EPositivosC", Formato.format(clsConfig.VInfoCalibrador.getDIon02Inf()) + " - " + Formato.format(clsConfig.VInfoCalibrador.getDIon02Sup()) ));
                                  out.println(vEti.Texto("EPositivosC", Formato.format(clsConfig.VInfoCalibrador.getDIon03Inf()) + " - " + Formato.format(clsConfig.VInfoCalibrador.getDIon03Sup()) ));
                                  out.println(vEti.Texto("EPositivosC", Formato.format(clsConfig.VInfoCalibrador.getDTmpRetencDInf()) + " - " + Formato.format(clsConfig.VInfoCalibrador.getDTmpRetencDSup()) ));
                                  out.println(vEti.Texto("EPositivosC", Formato.format(clsConfig.VInfoCalibrador.getDIon05Inf()) + " - " + Formato.format(clsConfig.VInfoCalibrador.getDIon05Sup()) ));

                               }                     
                               out.println("</tr>");
                               TVTOXCuantAnalisis VAnalisis = new TVTOXCuantAnalisis();
                               TDTOXCtrolCalibra  DControl  = new TDTOXCtrolCalibra();
                               TFechas Fecha = new TFechas();
                               StringBuffer cFiltro;
                               VCalibracion = clsConfig.vDespliega2;
                                 for(int i=0; i< VCalibracion.size(); i++){
                                   VAnalisis = new TVTOXCuantAnalisis();
                                   VAnalisis = (TVTOXCuantAnalisis) VCalibracion.get(i);
                                   double dResultado   = 0;
                                   if(VAnalisis.getdResultado() != null)
                                     dResultado = VAnalisis.getdResultado().doubleValue();
                                   cFiltro = new StringBuffer();
                                   out.println("<tr class=\"" + (VAnalisis.getLRegistrado().intValue() == 1 && VAnalisis.getLCorrecto().intValue() == 0?"ETablaST":"ETablaR") + "\">");
                                   out.println(vEti.Texto("EEtiqueta", VAnalisis.getCDscCalib()));
                                   if(!clsConfig.lModifica) {
                                      if(VAnalisis.getlControl().equals(new Integer(0)))
                                         out.println(vEti.Texto("EPositivosR", Formato.format(clsConfig.VInfoCalibrador.getDLimInferior())));
                                      else
                                         out.println(vEti.Texto("EPositivosR", Formato.format(VAnalisis.getdConcentracion().doubleValue() - (VAnalisis.getdConcentracion().doubleValue() * VAnalisis.getDMargenError().doubleValue()/100)  )));
                                   }

                                   out.println(vEti.CeldaCampo("ETablaR" + (clsConfig.lModifica?"\" colspan=\"3":" "), "input", 10, 10, "FILConc" + VAnalisis.getiCveAnalisis(), String.valueOf(dResultado), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   if(!clsConfig.lModifica) {
                                      if(VAnalisis.getlControl().equals(new Integer(0)))
                                         out.println(vEti.Texto("EPositivosR", Formato.format(clsConfig.VInfoCalibrador.getDLimSuperior())));
                                      else 
                                         out.println(vEti.Texto("EPositivosR", Formato.format(VAnalisis.getdConcentracion().doubleValue() + (VAnalisis.getdConcentracion().doubleValue() * VAnalisis.getDMargenError().doubleValue()/100)  )));
                                   }
                                   out.println(vEti.CeldaCampo("ETablaR", "input", 10, 10, "FILTmpRetenc" + VAnalisis.getiCveAnalisis(), Formato.format(VAnalisis.getDTmpRetenc()), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   out.println(vEti.CeldaCampo("ETablaR", "input", 10, 10, "FILIon01" + VAnalisis.getiCveAnalisis(), Formato.format(VAnalisis.getDIon01()), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   out.println(vEti.CeldaCampo("ETablaR", "input", 10, 10, "FILIon02" + VAnalisis.getiCveAnalisis(), Formato.format(VAnalisis.getDIon02()), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   out.println(vEti.CeldaCampo("ETablaR", "input", 10, 10, "FILIon03" + VAnalisis.getiCveAnalisis(), Formato.format(VAnalisis.getDIon03()), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   out.println(vEti.CeldaCampo("ETablaR", "input", 10, 10, "FILTmpRetencD" + VAnalisis.getiCveAnalisis(), Formato.format(VAnalisis.getDTmpRetencD()), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   out.println(vEti.CeldaCampo("ETablaR", "input", 10, 10, "FILIon04" + VAnalisis.getiCveAnalisis(), Formato.format(VAnalisis.getDIon04()), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   out.println(vEti.CeldaCampo("ETablaR", "input", 10, 10, "FILIon05" + VAnalisis.getiCveAnalisis(), Formato.format(VAnalisis.getDIon05()), 0, "", "", true, clsConfig.lModifica, clsConfig.lModifica ));
                                   out.println("</tr>");
                                 }
                                   //poner liga para validar
                              if(clsConfig.lModifica){
                                out.print("<tr><td colspan=\"11\" align=\"center\">");
                                out.print(vEti.clsAnclaTexto("EAncla","Cargar Información Archivo ZIP","JavaScript:fGetFile('upInfoCroma01');", "Cargar Información...",""));
                                out.print("</td></tr>");
                              /*  out.print("<tr><td colspan=\"11\" align=\"center\">");
                                out.print(vEti.clsAnclaTexto("EAncla","Cargar Información Archivo TXT","JavaScript:fGetFile('servUpCroma');", "Cargar Información...",""));
                                out.print("</td></tr>"); */
                                out.println("<tr><td colspan =\"11\" class=\"ETablaC\">");
                                out.print(vEti.clsAnclaTexto("EAncla","Validar","JavaScript:fAccion('Validar');", "Validar"));
                                out.println("</td></tr>");
                              }
                             // Si no fue valida la calibración, se va a poner la información de la acción correctiva y la observación
                             if(VAnalisis.getlAutorizado() != null){
                               TDTOXAcCorrectiva DAccCorrec = new TDTOXAcCorrectiva();
                               out.println("<tr>");
                               out.println(vEti.Texto("EEtiqueta", "Calibración:"));
                               out.println(vEti.Texto("EResalta \" colspan=\"10", (VAnalisis.getlAutorizado().intValue() == 0? "INCORRECTA":"CORRECTA") ));
                               out.println("</tr>");
                              // Calibración incorrecta
                               if(VAnalisis.getlAutorizado().equals(new Integer(0))){
                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta", "Acción Correctiva:"));
                                 out.print("<td>" + vEti.SelectOneRowSinTD("iCveAccCorrectiva","",
                                                                            DAccCorrec.FindByAll(),
                                                                            "iCveAcCorrectiva",
                                                                            "cDscAcCorrectiva",request,
                                                                            clsConfig.VLote.getiCveAcCorrectiva() == null ? "0" : clsConfig.VLote.getiCveAcCorrectiva().toString(),
                                                                            true,clsConfig.lModificaA)
                                               + "</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiAreaTexto("EEtiqueta","Observación",
                                                                      "ETabla",30,5,
                                                                      "cObservacion", clsConfig.VLote.getcObservacion() == null?"":clsConfig.VLote.getcObservacion(),
                                                                      0,"","",true, clsConfig.lModificaA, true));
                                 out.println("</tr>");

                               }
                             }


                           } else {
                         %>
                            <tr><td colspan="3" class="EResalta">No existe información con el filtro proporcionado.</td>
                            </tr>
                        <% }
                           }else{
                         %>
                            <tr><td colspan="3" class="EResalta">No se ha integrado la información del calibrador y los controles a utilizar.</td>
                            </tr>
                        <%
                           } %>
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
