<%/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 * @author Mauricio Delgadillo
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
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<html>
<%
  pg070303040CFG  clsConfig = new pg070303040CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070303040.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070303040.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Análisis|NIM|Disco|Posición|";    // modificar
  String cCveOrdenar  = "ToxAnalisis.iCveAnalisis|iCveMuestra|iCarrusel|iPosicion|";  // modificar
  String cDscFiltrar  = "Análisis|Disco|Posición|";    // modificar
  String cCveFiltrar  = "ToxAnalisis.iCveAnalisis|iCarrusel|iPosicion|";  // modificar
  String cTipoFiltrar = "7|7|7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
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
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "No";
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  //////////////////////////////////////////////////////
  String anIo = ""; 
  ////////////////////////////////////////////////
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070303040P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070303040P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name;
    }
  }

  function fAccion(cAccion){
    form = document.forms[0];
    form.target="_self";
    form.hdBoton.value = cAccion;
    form.submit();
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
       cClave = ""+bs.getFieldValue("iAnio", "");
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
 
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <% if(clsConfig.getAccion().compareToIgnoreCase("Generar Reporte")==0) {
        out.println(clsConfig.getActiveX());
     }else
       if(clsConfig.getAccion().compareToIgnoreCase("Controles")==0) {
          out.println(clsConfig.getControles());
     }
   %>
  <tr>
     <td valign="top">
        &nbsp;
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td class="ETablaT" colspan="8">B&uacute;squeda de Lote Autorizado para An&aacute;lisis Presuntivo</td>
          </tr>
          <tr>
            <td class="EEtiqueta">Año:</td>
            <td class="ETabla">
               <select name="iAnio" size="1" onChange="">
                  <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                        if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                        {
                           out.print("<option value = " + i + " selected>" + i + "</option>");
                           anIo = String.valueOf(i);
                        }
                        else
                        {
                           out.print("<option value = " + i + ">" + i + "</option>");
                           anIo = String.valueOf(i);
                        }
                     }
                  %>
               </select>
            </td>
            <td class="EEtiqueta">Laboratorio:</td>
            <td class="ETabla">
                <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                    int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                    out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveLoteCualita);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                %>
            </td>
            <td class="EEtiqueta">Lote:</td>
            <td class="ETabla">
               <select name="iCveLoteCualita" size="1" onChange="llenaSLT(2,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].iCveLoteCualita.value,document.forms[0].iCveExamCualita);">
                <%
                   if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null){
                     TDLoteCualita dLoteCualita = new TDLoteCualita();
                     TVLoteCualita vLoteCualita = new TVLoteCualita();
                     vLoteCualita.setIAnio(new Integer(request.getParameter("iAnio")).intValue());
                     /////////////////////////////////////////
                     //anIo = (vLoteCualita.getIAnio()).toString();
                     anIo = String.valueOf(vLoteCualita.getIAnio());
                     ///////////////////////////////////////////
                     vLoteCualita.setICveLaboratorio(new Integer(request.getParameter("iCveUniMed")).intValue());
                     Vector vExCuali = new Vector();
                     vExCuali = dLoteCualita.FindAllLoteCualita2(vLoteCualita);
                     if (vExCuali.size() > 0){
                        out.print("<option value = 0>Seleccione...</option>");
                        for (int i = 0; i < vExCuali.size(); i++){
                           vLoteCualita = (TVLoteCualita)vExCuali.get(i);
                           if (request.getParameter("iCveLoteCualita")!=null&&request.getParameter("iCveLoteCualita").compareToIgnoreCase(new Integer(vLoteCualita.getICveLoteCualita()).toString())==0)
                              out.print("<option value = " + vLoteCualita.getICveLoteCualita() + " selected>" + vLoteCualita.getICveLoteCualita() + "</option>");
                           else
                              out.print("<option value = " + vLoteCualita.getICveLoteCualita() + ">" + vLoteCualita.getICveLoteCualita() + "</option>");
                        }
                     }else{
                        out.print("<option value = 0>Datos no disponibles...</option>");
                     }
                   }
                %>
               </select>
            </td>
            <td class="EEtiqueta">Exámenes:</td>
            <td class="ETabla">
               <select name="iCveExamCualita" size="1">
                <%
                   if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null && request.getParameter("iCveLoteCualita") != null){
                      TDTOXExamenCualita dExamenCualita = new TDTOXExamenCualita();
                      TVTOXExamenCualita vExamenCualita = new TVTOXExamenCualita();
                      Vector vExamCualita = new Vector();
                      String cFiltro = "";
                      String cOrden  = "";
                      cFiltro = " WHERE TOXExamenCualita.iAnio = " + request.getParameter("iAnio") +
                                "   AND TOXExamenCualita.iCveLaboratorio = " + request.getParameter("iCveUniMed") +
                                "   AND TOXExamenCualita.iCveLoteCualita = " + request.getParameter("iCveLoteCualita");
                      cOrden = " ORDER BY TOXExamenCualita.iCveExamCualita " ;
                      vExamCualita = dExamenCualita.FindByAll(cFiltro,cOrden);
                     if (vExamCualita.size() > 0){
                        out.print("<option value = 0>Seleccione...</option>");
                        for (int i = 0; i < vExamCualita.size(); i++){
                           vExamenCualita = (TVTOXExamenCualita)vExamCualita.get(i);
                           if (request.getParameter("iCveExamCualita")!=null&&request.getParameter("iCveExamCualita").compareToIgnoreCase(new Integer(vExamenCualita.getICveExamCualita()).toString())==0)
                              out.print("<option value = " + vExamenCualita.getICveExamCualita() + " selected>" + vExamenCualita.getICveExamCualita() + "</option>");
                           else
                              out.print("<option value = " + vExamenCualita.getICveExamCualita() + ">" + vExamenCualita.getICveExamCualita() + "</option>");
                        }
                     }else{
                        out.print("<option value = 0>Datos no disponibles...</option>");
                     }
                   }
                %>
               </select>
            </td>
          </tr>
        </table>
        &nbsp;
          <% if (bs != null){%>
          <table align="center">
            <tr>
              <td align="Center">
                 <%  out.print(vEti.clsAnclaTexto("EAncla","Papelería del Lote","JavaScript:fAccion('Controles');", "Controles")); %>
              </td>
            </tr>
          </table>
          <% } %>
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td colspan="8" class="ETablaT">Análisis
              </td>
            </tr>
          <% if (bs != null){%>
             <tr>
              <td class="ETablaT">Análisis</td>
              <td class="ETablaT">NIM</td>
              <td class="ETablaT">Disco</td>
              <td class="ETablaT">Posición</td>
            </tr>
             <%  // modificar según listado
                   bs.start();
                   while(bs.nextRow()){
                     if (bs.getFieldValue("iCveMuestra", "0").toString().compareToIgnoreCase("0")==0)
                       out.print("<tr class=\"ETablaST\">");
                     else
                        out.print("<tr class=\"ETabla\">");
                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveAnalisis", "&nbsp;").toString()));
                     if (bs.getFieldValue("iCveMuestra", "0").toString().compareToIgnoreCase("0")==0)
                        out.print(vEti.Texto("ETablaR", "Control"));
                     else
                     {
                        //////////////////////////////////////////////////// 
                      //        System.out.println("Valor de anIo :" + anIo);
                        String paso = "";
                        paso = "" + anIo.substring(2);
                     //        System.out.println("Valor de año corto :" + paso);
                        paso = paso + bs.getFieldValue("iCveMuestra","&nbsp;").toString();
                      //        System.out.println("Valor total de la muuestra :" + paso);
                        //////////////////////////////////////////////////////////
                        out.print(vEti.Texto("ETablaR",paso ));
                     }
                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCarrusel", "&nbsp;").toString()));
                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iPosicion", "&nbsp;").toString()));
                     out.print("</tr>");
                   }
          }
          else{
            out.print("<tr>");
            out.print(vEti.EtiCampo("EResalta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
            out.print("</tr>");
          }
            %>
          </table>
          &nbsp;
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
