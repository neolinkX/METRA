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
  pg070304070CFG  clsConfig = new pg070304070CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070304070.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070304070.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070304070.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "No.Análisis|Posición|";    // modificar
  String cCveOrdenar  = "iCveAnalisis|iPosicion|";  // modificar
  String cDscFiltrar  = "No.Análisis|Posición|";    // modificar
  String cCveFiltrar  = "iCveAnalisis|iPosicion|";  // modificar
  String cTipoFiltrar = "7|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
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
  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070304070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070304070P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
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
         &nbsp;
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
         &nbsp;
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Valores de la Calibración</td>
           </tr>
           <tr>
             <td colspan="6" class="ETablaT">Sustancia</td>
             <td colspan="3" class="ETablaT">Estándar Interno</td>
           </tr>
           <tr>
             <td class="ETablaT">&nbsp;</td>
             <td class="ETablaT">Concentración</td>
             <td class="ETablaT">Tiempo de Retención</td>
             <td class="ETablaT">Ion 01</td>
             <td class="ETablaT">Ion 02</td>
             <td class="ETablaT">Ion 03</td>
             <td class="ETablaT">Tiempo de Retención</td>
             <td class="ETablaT">Ion 01</td>
             <td class="ETablaT">Ion 02</td>
           </tr>
               <%
                DecimalFormat dfNumber = new DecimalFormat("##,###,##0.00");
                Vector vCuantAnalisis = new Vector();
                vCuantAnalisis = clsConfig.vCuantAnalisis;

                for (int i=0;i<vCuantAnalisis.size();i++){
                   TVTOXCuantAnalisis VTOXCuantAnalisis = new TVTOXCuantAnalisis();
                   VTOXCuantAnalisis = (TVTOXCuantAnalisis)vCuantAnalisis.get(i);

                   out.print("<tr>");
                   if (VTOXCuantAnalisis.getiCveAnalisis().intValue() == -5)
                     out.print(vEti.Texto("EEtiqueta","Calibrador:" ));
                   if (VTOXCuantAnalisis.getiCveAnalisis().intValue() == -4)
                     out.print(vEti.Texto("EEtiqueta","Negativo:" ));
                   if (VTOXCuantAnalisis.getiCveAnalisis().intValue() == -3)
                     out.print(vEti.Texto("EEtiqueta","Control 1:" ));
                   if (VTOXCuantAnalisis.getiCveAnalisis().intValue() == -2)
                     out.print(vEti.Texto("EEtiqueta","Control 2:" ));
                   if (VTOXCuantAnalisis.getiCveAnalisis().intValue() == -1)
                     out.print(vEti.Texto("EEtiqueta","Control 3:" ));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getdResultado().toString()))));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getDTmpRetenc()))));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getDIon01()))));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getDIon02()))));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getDIon03()))));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getDTmpRetencD()))));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getDIon04()))));
                   out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(VTOXCuantAnalisis.getDIon05()))));
// VTOXCuantAnalisis.getdConcentracion().toString()
                   if (VTOXCuantAnalisis.getiCveAnalisis().intValue() == -1){
                     out.print("</tr>");
                     out.print("<tr>");
                     out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observacion:","ETabla",50,2,8,"TXTObservación", "",4,"","",true,false,true));
                   }
                   out.print("</tr>");
                }
                %>
         </table>
         &nbsp;
          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
            <tr>
              <td colspan="14" class="ETablaT">An&aacute;lisis de las Muestras</td>
            </tr>
            <tr>
              <td class="ETablaT" colspan="7">Sustancia</td>
              <td class="ETablaT" colspan="3">Estandar Interno</td>
              <td class="ETablaT" colspan="4">Autorizaci&oacute;n</td>
            </tr>
            <tr>
              <td class="ETablaT">An&aacute;lisis</td>
              <% if(clsConfig.getISustUnifica() > 0) {%>
              <td class="ETablaT">Sustancia</td>
              <% }%>
              <td class="ETablaT">Concentraci&oacute;n</td>
              <td class="ETablaT">Resultado</td>
              <td class="ETablaT">Tiempo de Retenci&oacute;n</td>
              <td class="ETablaT">Ion 01</td>
              <td class="ETablaT">Ion 02</td>
              <td class="ETablaT">Ion 03</td>
              <td class="ETablaT">Tiempo de Retenci&oacute;n</td>
              <td class="ETablaT">Ion 01</td>
              <td class="ETablaT">Ion 02</td>
              <td class="ETablaT">Situaci&oacute;n</td>
              <td class="ETablaT">Rean&aacute;lisis</td>
              <td class="ETablaT">Diluci&oacute;n</td>
              <td class="ETablaT">Observaci&oacute;n</td>
            </tr>
             <% // modificar según listado
               if (bs != null){
                  Vector vCalibCuantita = new Vector();
                  vCalibCuantita = clsConfig.vCalibCuantita;
                   bs.start();
                   while(bs.nextRow()){
                     boolean lPinto = false;
                     out.print("<tr class=\"" + (bs.getFieldValue("lCorrecto").toString().compareToIgnoreCase("1")==0?"ETabla":"ETablaST") + "\">");

                     if (new Integer(bs.getFieldValue("iCveAnalisis", "&nbsp;").toString()).intValue() < 0){
                        if (!vCalibCuantita.isEmpty())
                           for(int i=0;i<vCalibCuantita.size();i++){
                              TVTOXCalibCuantita VTOXCalibCuantita = new TVTOXCalibCuantita();
                              VTOXCalibCuantita = (TVTOXCalibCuantita) vCalibCuantita.get(i);
                              if (bs.getFieldValue("iCveLaboratorio", "&nbsp;").toString().compareToIgnoreCase(VTOXCalibCuantita.getiCveLaboratorio().toString()) == 0 &&
                                  bs.getFieldValue("iCveAnalisis", "&nbsp;").toString().compareToIgnoreCase(VTOXCalibCuantita.getiPosicion().toString()) == 0   ){
                                  out.print(vEti.Texto("ETablaR", VTOXCalibCuantita.getcDscCalib()));
                                  lPinto = true;
                              }
                           }
                        if (!lPinto)
                           out.print(vEti.Texto("ETablaR", "&nbsp;"));
                     }
                     else
                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveAnalisis", "&nbsp;").toString()));
                     if(clsConfig.getISustUnifica() > 0){
                       out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscCalib", "&nbsp;").toString()));
                     }


                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dResultado", "&nbsp;").toString()))));

                     if (bs.getFieldValue("lResultado", "&nbsp;").toString().compareToIgnoreCase("1") == 0)
                        out.print(vEti.Texto("EPositivos", "Positivo"));
                     else
                        out.print(vEti.Texto("ETabla", "Negativo"));

                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dTmpRetenc", "&nbsp;").toString()))));
                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dIon01", "&nbsp;").toString()))));
                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dIon02", "&nbsp;").toString()))));
                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dIon03", "&nbsp;").toString()))));
                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dTmpRetencD", "&nbsp;").toString()))));
                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dIon04", "&nbsp;").toString()))));
                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dIon05", "&nbsp;").toString()))));
                     if (bs.getFieldValue("lCorrecto").toString().compareToIgnoreCase("1") == 0)
                       out.print(vEti.Texto("ETabla", "Correcto"));
                     else
                       out.print(vEti.Texto("EPositivos", "Incorrecto"));
                     out.print(vEti.ToggleMov("ETablaC", "TBXSel-" + bs.getFieldValue("iCveAnalisis", "&nbsp;").toString(),"0" ,"", 59, true, "Sí", "No", true));
                     if (bs.getFieldValue("iDilusion", "&nbsp;").toString().compareToIgnoreCase("1") == 0){
                       out.print("<td class=\"ECampo\">1:");
                       out.print(vEti.CampoSinCelda("input",6,6,"FILDilucion-" + bs.getFieldValue("iCveAnalisis", "&nbsp;").toString(), bs.getFieldValue("iDilusion", "&nbsp;").toString(),3,"","",true,true));
                       out.print("</td>");
                        //out.print(vEti.Texto("ETablaR", "D"));
                     }
                     else {
                       out.print("<td class=\"ECampo\">1:");
                       out.print(vEti.CampoSinCelda("input",6,6,"FILDilucion-" + bs.getFieldValue("iCveAnalisis", "&nbsp;").toString(), bs.getFieldValue("iDilusion", "&nbsp;").toString(),3,"","",true,true));
                       out.print("</td>");
                        //out.print(vEti.Texto("ETablaR", "1:" + bs.getFieldValue("iDilusion", "&nbsp;").toString()));
                     }

                     out.print(vEti.EtiAreaTextoSINEtiqueta("ETabla",30,2,"TXTObservacion-" + bs.getFieldValue("iCveAnalisis", "").toString(), "", 10, "", "fMayus(this);", true, true, true));
                     out.print("</tr>");
                   }
               }
               else{
                  out.println("<tr>");
                  out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 5, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
