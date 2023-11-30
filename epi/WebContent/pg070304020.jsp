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
  pg070304020CFG  clsConfig = new pg070304020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070304020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070304020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070304020.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Lote Presuntivo|No.Análisis|";    // modificar
  String cCveOrdenar  = "iCveLoteCualita|iCveAnalisis|";  // modificar
  String cDscFiltrar  = "Lote Presuntivo|No.Análisis|";    // modificar
  String cCveFiltrar  = "TOXExamResult.iCveLoteCualita|TOXExamResult.iCveAnalisis|";  // modificar
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
           window.parent.FRMOtroPanel.location="pg070304020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070304020P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name + '&hdAccion=' + document.forms[0].action;
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
  <table border="1" background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
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
                      if (!vLoteCuantita.isEmpty()){
                        for(int i=0;i<vLoteCuantita.size();i++){
                          TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) vLoteCuantita.get(i);
                          if (cvLoteConfirmativo.compareToIgnoreCase(VTOXLoteCuantita.getiCveLoteCuantita().toString()) == 0)
                             out.print("<option selected value=\"" + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "\">"  + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "</option>");
                          else
                             out.print("<option value=\"" + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "\">"  + VTOXLoteCuantita.getiCveLoteCuantita().toString() + "</option>");
                        }
                      }
                %>
                </select>
             </td>
             <td class="ECampo"><%=  new TCreaBoton().clsCreaBoton(vEntorno, 1, "Buscar",
                              "bBuscar", vEntorno.getTipoImg(),
                              "Buscar el Lote Confirmatorio","Buscar", vParametros) %></td>
           </tr>
         </table>
         &nbsp;
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Selección del Equipo</td>
           </tr><tr>
             <td class="EEtiqueta">Equipo:</td>
             <td class="ECampo">
               <%
                  TDTOXEquipo dTOXEquipo = new TDTOXEquipo();
                  TVTOXEquipo vTOXEquipo = new TVTOXEquipo();
                  Vector vEquipo = new Vector();
                  vEquipo = dTOXEquipo.FindByAll(" WHERE lCuantCual = 1 ","");
                  out.println(vEti.SelectOneRowSinTD("SLSEquipo", "", vEquipo, "iCveEquipo", "cDscEquipo", request, "07"));
                %>
             </td>
           </tr>
         </table>
         &nbsp;
         <table border="1" class="ETablaInfo" align="center">
           <tr>
             <td colspan="9" class="ETablaT">Autorización del Lote Confirmatorio</td>
           </tr><tr>
             <td class="EEtiqueta">Autorización:</td>
               <%
                out.print(vEti.ToggleMov("ECampo", "TBXAutorizacion" ,"0" ,"", 59, true, "Sí", "No", true));
                %>
           </tr>
         </table>
         &nbsp;
          <table border="1" class="ETablaInfo" align="center"><% // Inicio de Datos %>
            <tr>
              <td colspan="6" class="ETablaT">Análisis Confirmatorio de Presuntos Positivos</td>
            </tr>
            <tr>
              <td class="ETablaT">Consecutivo</td>
              <td class="ETablaT">Lote Presuntivo</td>
              <td class="ETablaT">No. Análisis</td>
              <td class="ETablaT">Examen</td>
              <td class="ETablaT">Absorbancia</td>
              <td class="ETablaT">Eliminar</td>
            </tr>
             <% // modificar según listado
               DecimalFormat dfNumber = new DecimalFormat("##,###,##0.00");
               int iContador = 0;
               if (bs != null){
                   bs.start();
                   while(bs.nextRow()){
                     out.print("<tr>");
                     out.println(vEti.Texto("ETablaR", String.valueOf(++iContador)));
                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveLoteCualita", "&nbsp;").toString()));
                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveAnalisis", "&nbsp;").toString()));
                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveExamCualita", "&nbsp;").toString()));
                     //out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveSustancia", "&nbsp;").toString()));
                     out.print(vEti.Texto("ETablaR", dfNumber.format(new Double(bs.getFieldValue("dResultado", "&nbsp;").toString()))));
                     out.print(vEti.ToggleMov("ECampo", "TBXElim-" + bs.getFieldValue("iAnio", "&nbsp;").toString()           + "-"
                                                                   + bs.getFieldValue("iCveLaboratorio", "&nbsp;").toString() + "-"
                                                                   + bs.getFieldValue("iCveLoteCualita", "&nbsp;").toString() + "-"
                                                                   + bs.getFieldValue("iCveExamCualita", "&nbsp;").toString() + "-"
                                                                   + bs.getFieldValue("iCveAnalisis", "&nbsp;").toString()    + "-"
                                                                   + bs.getFieldValue("iCveSustancia", "&nbsp;").toString()
                                 ,"0" ,"", 59, true, "Sí", "No", true));
                     out.print("</tr>");
                   }
               }
               else{
                  out.println("<tr>");
                  out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 4,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
