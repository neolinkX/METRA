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
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<html>
<%
  pg070308020CFG  clsConfig = new pg070308020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070308020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070308020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "";    // modificar
  String cCveOrdenar  = "";  // modificar
  String cDscFiltrar  = "";    // modificar
  String cCveFiltrar  = "";  // modificar
  String cTipoFiltrar = "";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";            // modificar

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
  String cClave    = "";
  String cPosicion = "";
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
       cClave = ""+bs.getFieldValue("iAnio", "");
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <tr>
     <td valign="top">
        &nbsp;
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td class="ETablaT" colspan="8">LIBERAR LOTES</td>
          </tr>
          <tr>
            <td class="EEtiqueta">A&ntilde;o:</td>
            <td class="ETabla">
               <select name="iAnio" >
                  <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                        if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
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
                    out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,'',document.forms[0].iCveLoteCualita);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                %>
            </td>
            <td class="EEtiqueta">Lote:</td>
            <td class="ETabla">
               <select name="iCveLoteCualita" onChange="llenaSLT(2,document.forms[0].iAnio.value,document.forms[0].iCveUniMed.value,document.forms[0].iCveLoteCualita.value,document.forms[0].iCveExamCualita);">
                <%
                   if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null){
                     TDLoteCualita dLoteCualita = new TDLoteCualita();
                     TVLoteCualita vLoteCualita = new TVLoteCualita();
                     vLoteCualita.setIAnio(new Integer(request.getParameter("iAnio")).intValue());
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
               <select name="iCveExamCualita" >
                <%
                   if(request.getParameter("iAnio") != null && request.getParameter("iCveUniMed") != null && request.getParameter("iCveLoteCualita") != null){
                      TDTOXExamenCualita dExamenCualita = new TDTOXExamenCualita();
                      TVTOXExamenCualita vExamenCualita = new TVTOXExamenCualita();
                      Vector vExamCualita = new Vector();
                      String cFiltro = "";
                      cFiltro = " WHERE TOXExamenCualita.iAnio = " + request.getParameter("iAnio") +
                                "   AND TOXExamenCualita.iCveLaboratorio = " + request.getParameter("iCveUniMed") +
                                "   AND TOXExamenCualita.iCveLoteCualita = " + request.getParameter("iCveLoteCualita");
                      vExamCualita = dExamenCualita.FindMaxValue(cFiltro);
                     if (vExamCualita.size() > 0){
                        out.print("<option value = 0>Seleccione...</option>");
                        for (int i = 0; i < vExamCualita.size(); i++){
                           vExamenCualita = (TVTOXExamenCualita)vExamCualita.get(i);
                           if (request.getParameter("iCveExamCualita") != null && Integer.parseInt(request.getParameter("iCveExamCualita")) == vExamenCualita.getICveExamCualita())
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
               <td class="ETablaC" colspan="2">
               <%= vEti.clsAnclaTexto("EAnclaC","Liberar", "javascript:if(fValidaSelectores())fLiberar('pg070308020.jsp');","exp")%>                            </td>
               <td class="ETablaC" colspan="2">
               <%= vEti.clsAnclaTexto("EAnclaC","Borrar", "javascript:if(fValidaSelectores())fBorrar('pg070308020.jsp');","exp")%>                            </td>
            </tr>
          </table>
          &nbsp;
       </td>
    </tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
<%
  if( request.getParameter("hdBoton") != null ){
    if( request.getParameter("hdBoton").equals("GuardarA")){
      if(clsConfig.getILFlag() != 0)
        out.println("<script language=\"JavaScript\">alert('El Lote seleccionado fue liberado de manera correcta.');</script>");
      else
        out.println("<script language=\"JavaScript\">alert('El Lote seleccionado no pudo ser liberado.');</script>");
    }
    if( request.getParameter("hdBoton").equals("Borrar")){
      if(clsConfig.getIBFlag() != 0)
        out.println("<script language=\"JavaScript\">alert('El Lote seleccionado fue eliminado de manera correcta.');</script>");
      else
        out.println("<script language=\"JavaScript\">alert('El Lote seleccionado no pudo ser eliminado.');</script>");
    }
  }
%>

</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
