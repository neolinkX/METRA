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
                  out.println(vEti.SelectOneRowSinTD("SLSSustancia", "llenaSLT(1,document.forms[0].SLSAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].SLSSustancia.value,document.forms[0].SLSLoteConfirmativo);", (Vector) AppCacheManager.getColl("TOXSustancia",""), "iCveSustancia", "cDscSustancia", request, "07"));
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
      <td>
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Validación de los Controles</td>
           </tr><tr>
             <td class="EEtiqueta">Validación:</td>
               <%
                out.print(vEti.ToggleMov("ECampo", "TBXValidacion" ,"0" ,"", 59, true, "Sí", "No", true));
                %>
           </tr>
         </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="6" class="ETablaT">Equipo utilizado</td>

                             <% // modificar según listado
                               DecimalFormat dfNumber = new DecimalFormat("##,###,##0.00");

                              if (!vDespliega.isEmpty()){
                                for(int i=0;i<vDespliega.size();i++) {
                                   TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
                                   VTOXCuantAnalisis = (TVTOXCuantAnalisis) vDespliega.get(i);
                                     if (VTOXCuantAnalisis.getiCveAnalisis().toString().compareToIgnoreCase("-5") == 0)
                                       dvDilCalibrador = VTOXCuantAnalisis.getdResultadoDil().doubleValue();
                                     if (VTOXCuantAnalisis.getiCveAnalisis().toString().compareToIgnoreCase("-4") == 0)
                                       dvDilNegativo = VTOXCuantAnalisis.getdResultadoDil().doubleValue();
                                     if (VTOXCuantAnalisis.getiCveAnalisis().toString().compareToIgnoreCase("-3") == 0)
                                       dvDilControl1 = VTOXCuantAnalisis.getdResultadoDil().doubleValue();
                                     if (VTOXCuantAnalisis.getiCveAnalisis().toString().compareToIgnoreCase("-2") == 0)
                                       dvDilControl2 = VTOXCuantAnalisis.getdResultadoDil().doubleValue();
                                     if (VTOXCuantAnalisis.getiCveAnalisis().toString().compareToIgnoreCase("-1") == 0)
                                       dvDilControl3 = VTOXCuantAnalisis.getdResultadoDil().doubleValue();
                                }
                              }

                            %>



                           </tr>
                            <tr>
                              <td class="ETablaT">Equipo</td>
                              <td colspan=2 class="ECampo">
                              <select name="SLSEqControl" size="1">
                              <%
                                Vector vEQMEquipo = new Vector();
                                String cvEQMEquipo = "";
                                if (request.getParameter("SLSEqControl") != null){
                                  if (request.getParameter("SLSEqControl") != "")
                                    cvEQMEquipo = request.getParameter("SLSEqControl");
                                }

                                vEQMEquipo = clsConfig.vEQMEquipo;
                                if (vEQMEquipo != null)
                                  if (!vEQMEquipo.isEmpty())
                                    for(int i=0;i<vEQMEquipo.size();i++){
                                      TVEQMEquipo VEQMEquipo = (TVEQMEquipo) vEQMEquipo.get(i);
                                      if (cvEQMEquipo.toString().compareToIgnoreCase(new Integer(VEQMEquipo.getICveEquipo()).toString()) == 0)
                                        out.print("<option selected value=\"" + new Integer(VEQMEquipo.getICveEquipo()).toString() + "\">"  + VEQMEquipo.getCDscEquipo().toString() + "</option>");
                                      else
                                        out.print("<option value=\"" + new Integer(VEQMEquipo.getICveEquipo()).toString() + "\">"  + VEQMEquipo.getCDscEquipo().toString() + "</option>");
                                   }
                             %>
                             </select>
                              </td>
                            </tr>
                            <tr>
                              <td colspan="6" class="ETablaT">Calibradores del Lote Confirmatorio</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Sustancia</td>
                              <td class="ETablaT">Nombre</td>
                              <td class="ETablaT">Concentración</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Calibrador:</td>
                              <td class="ECampo">
                              <select name="SLSCalibrador" size="1">
                              <%
                                Vector vCalibEquipo = new Vector();
                                String cvCalibEquipo = "";
                                if (request.getParameter("SLSCalibrador") != null){
                                  if (request.getParameter("SLSCalibrador") != "")
                                    cvCalibEquipo = request.getParameter("SLSCalibrador");
                                }

                                vCalibEquipo = clsConfig.vCtrolCalibra;
                                if (vCalibEquipo != null)
                                  if (!vCalibEquipo.isEmpty())
                                    for(int i=0;i<vCalibEquipo.size();i++){
                                      TVTOXCtrolCalibra VCtrolCalibra = (TVTOXCtrolCalibra) vCalibEquipo.get(i);
                                      if (new Integer(VCtrolCalibra.getICveEmpleoCalib()).toString().compareToIgnoreCase("2") == 0){
                                         if (cvCalibEquipo.toString().compareToIgnoreCase(new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString()) == 0)
                                           out.print("<option selected value=\"" + new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString() + "\">"  + VCtrolCalibra.getCDscBreve().toString() + "</option>");
                                         else
                                           out.print("<option value=\"" + new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString() + "\">"  + VCtrolCalibra.getCDscBreve().toString() + "</option>");
                                      }
                                   }
                              %>
                              </select>
                              </td>
                              <td class="ECampo">
                              <%
                                out.print(vEti.CampoSinCelda("input",12,12,"FILCalibrador", dfNumber.format(new Double(dvDilCalibrador)),3,"","",true,true));
// new Double(dvDilCalibrador).toString()
                              %>
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Negativo:</td>
                              <td class="ECampo">
                              <select name="SLSNegativo" size="1">
                              <%
                                Vector vCalibEquipoN = new Vector();
                                String cvCalibEquipoN = "";
                                if (request.getParameter("SLSNegativo") != null){
                                  if (request.getParameter("SLSNegativo") != "")
                                    cvCalibEquipoN = request.getParameter("SLSNegativo");
                                }

                                vCalibEquipoN = clsConfig.vCtrolCalibra;
                                if (vCalibEquipoN != null)
                                  if (!vCalibEquipoN.isEmpty())
                                    for(int i=0;i<vCalibEquipoN.size();i++){
                                      TVTOXCtrolCalibra VCtrolCalibra = (TVTOXCtrolCalibra) vCalibEquipoN.get(i);
                                      if (new Integer(VCtrolCalibra.getICveEmpleoCalib()).toString().compareToIgnoreCase("1") == 0){
                                         if (cvCalibEquipoN.toString().compareToIgnoreCase(new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString()) == 0)
                                           out.print("<option selected value=\"" + new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString() + "\">"  + VCtrolCalibra.getCDscBreve().toString() + "</option>");
                                         else
                                           out.print("<option value=\"" + new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString() + "\">"  + VCtrolCalibra.getCDscBreve().toString() + "</option>");
                                      }
                                   }
                              %>
                              </select>
                              </td>
                              <td class="ECampo">
                             <%
                               out.print(vEti.CampoSinCelda("input",12,12,"FILNegativo", dfNumber.format(new Double(dvDilNegativo)) ,3,"","",true,true));
// new Double(dvDilNegativo).toString()
                             %>
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Sustancia para Controles:</td>
                              <td class="ECampo">
                              <select name="SLSControles" size="1">
                              <%
                                Vector vCalibEquipoC = new Vector();
                                String cvCalibEquipoC = "";
                                if (request.getParameter("SLSControles") != null){
                                  if (request.getParameter("SLSControles") != "")
                                    cvCalibEquipoC = request.getParameter("SLSControles");
                                }

                                vCalibEquipoC = clsConfig.vCtrolCalibra;
                                if (vCalibEquipoC != null)
                                  if (!vCalibEquipoC.isEmpty())
                                    for(int i=0;i<vCalibEquipoC.size();i++){
                                      TVTOXCtrolCalibra VCtrolCalibra = (TVTOXCtrolCalibra) vCalibEquipoC.get(i);
                                      if (new Integer(VCtrolCalibra.getICveEmpleoCalib()).toString().compareToIgnoreCase("1") == 0){
                                         if (cvCalibEquipoC.toString().compareToIgnoreCase(new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString()) == 0)
                                           out.print("<option selected value=\"" + new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString() + "\">"  + VCtrolCalibra.getCDscBreve().toString() + "</option>");
                                         else
                                           out.print("<option value=\"" + new Integer(VCtrolCalibra.getICveCtrolCalibra()).toString() + "\">"  + VCtrolCalibra.getCDscBreve().toString() + "</option>");
                                      }
                                   }
                              %>
                              </select>
                              </td>
                              <td class="ECampo">&nbsp</td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Control 40%:</td>
                              <td colspan=2 class="ECampo">
                             <%
                               out.print(vEti.CampoSinCelda("input",12,12,"FILControl1", dfNumber.format(new Double(dvDilControl1)),3,"","",true,true));
// new Double(dvDilControl1).toString()
                             %>
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Control 100%:</td>
                              <td colspan=2 class="ECampo">
                             <%
                              out.print(vEti.CampoSinCelda("input",12,12,"FILControl2", dfNumber.format(new Double(dvDilControl2)),3,"","",true,true));
// new Double(dvDilControl2).toString()
                             %>
                             </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Control 200%:</td>
                              <td colspan=2 class="ECampo">
                             <%
                               out.print(vEti.CampoSinCelda("input",12,12,"FILControl3", String.valueOf(dvDilControl3),3,"","",true,true));
// new Double(dvDilControl3).toString()
                             %>
                             </td>
                            </tr>
                          </table>
      </td></tr>
      <tr>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="6" class="ETablaT">Indicación de la Acción Correctiva</td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Acción Correctiva:</td>
                              <td colspan=2 class="ECampo">
                              <% Vector vTOXLoteCuantita = new Vector();
                                 vTOXLoteCuantita = clsConfig.vLoteCuantita;
                                 if (!vTOXLoteCuantita.isEmpty()){
                                    for(int i=0;i<vTOXLoteCuantita.size();i++){
                                      TVTOXLoteCuantita VTOXLoteCuantita = new TVTOXLoteCuantita();
                                      VTOXLoteCuantita = (TVTOXLoteCuantita) vTOXLoteCuantita.get(i);
                                      ivAcCorrectiva = VTOXLoteCuantita.getiCveAcCorrectiva().intValue();
                                      cvObservacion =  VTOXLoteCuantita.getcObservacion();
                                    }
                                 }
                                 out.println(vEti.SelectOneRowSinTD("SLSAcCorrectiva", "", (Vector) AppCacheManager.getColl("AcCorrectiva",""), "iCveAcCorrectiva", "cDscAcCorrectiva", request, "07"));
                             %>
                              </td>
                            </tr>
                            <tr>
                              <%
                                out.println(vEti.EtiAreaTexto("EEtiqueta","Observación:","ECampo",50,3,"TXTObservacion",cvObservacion,4,"","",true,true,true));
                             %>
                            </tr>



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
