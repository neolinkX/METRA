<%/**
 * Title:       Seguimiento de Mantenimiento
 * Description: Listado de los Equipos
 * Copyright:   2004
 * Company:     Macros Personales, S.A. de C.V.
 * @author      Marco Antonio Hernández García
 * @version     1.0
 * Clase:       pg070603030
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>

<html>
<%
  pg070603030CFG  clsConfig = new pg070603030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070603030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070603030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070603031.jsp";       // modificar
  String cOperador    = "1";                   // modificar

  String cDscOrdenar  = "Mantenimiento|Equipo|";    // modificar
  String cCveOrdenar  = "EQMMantenimiento.iCveMantenimiento|EQMMantenimiento.iCveEquipo|";  // modificar
  String cDscFiltrar  = "Mantenimiento|Equipo|";    // modificar
  String cCveFiltrar  = "EQMMantenimiento.iCveMantenimiento|EQMMantenimiento.iCveEquipo|";  // modificar


  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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

  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  String cFiltro = "";
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070603030.js"%>"></SCRIPT>
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
  <input type="hidden" name="iCveEquipo" value="<% if (request.getParameter("iCveEquipo") != null)  out.print(request.getParameter("iCveEquipo"));%>">
  <input type="hidden" name="iCveMantenimiento" value="<% if (request.getParameter("iCveMantenimiento") != null)  out.print(request.getParameter("iCveMantenimiento"));%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIni" value="">
  <input type="hidden" name="hdIni2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
        <input type="hidden" name="hdBoton" value="">
        &nbsp;
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td class="ETablaT" colspan="10">Seguimiento del Mantenimiento</td>
          </tr>
          <%
             String cTemp = "";
             out.println("<tr>");
             if (request.getParameter("lSolicitados")!=null && request.getParameter("lSolicitados").toString().compareTo("1")==0)
                out.println("<td class=\"EEtiquetaL\"><input type=\"checkbox\" name=\"lSolicitados\" value=\"1\" onclick=\"Activar1(!this.checked);\" checked>Solicitados de:</td>");
             else
                out.println("<td class=\"EEtiquetaL\"><input type=\"checkbox\" name=\"lSolicitados\" value=\"1\" onclick=\"Activar1(!this.checked);\">Solicitados de:</td>");
             out.println("<td class=\"ECampo\">");

             if (request.getParameter("dtDeSol")!=null && request.getParameter("dtDeSol").compareTo("null")!=0)
                cTemp = request.getParameter("dtDeSol");
             else
                cTemp = "";

             out.println("<input type=\"text\" size=\"10\" maxlength=\"10\" name=\"dtDeSol\" value=\""+cTemp+"\">");
             out.println("<a class=\"EAncla\" name=\"calendario\" href=\"JavaScript:fLoadCal(9,8,2004,document.forms[0].dtDeSol);\">(dd/mm/aaaa)</a>");
             out.println("</td>");
                out.print(vEti.EtiCampoCS("EEtiqueta","a:","ECampo","text",10,10,3,"dtASol","",0,"","fMayus(this);",false,true,true,request));
             out.println("</tr>");

             out.println("<tr>");
             if (request.getParameter("lProgramados")!=null && request.getParameter("lProgramados").toString().compareTo("1")==0)
                out.println("<td class=\"EEtiquetaL\"><input type=\"checkbox\" name=\"lProgramados\" value=\"1\" onclick=\"Activar2(!this.checked);\" checked>Programados de:</td>");
             else
                out.println("<td class=\"EEtiquetaL\"><input type=\"checkbox\" name=\"lProgramados\" value=\"1\" onclick=\"Activar2(!this.checked);\">Programados de:</td>");
             out.println("<td class=\"ECampo\">");
             if (request.getParameter("dtDePro")!=null && request.getParameter("dtDePro").compareTo("null")!=0)
                cTemp = request.getParameter("dtDePro");
             else
                cTemp = "";
             out.println("<input type=\"text\" size=\"10\" maxlength=\"10\" name=\"dtDePro\" value=\""+cTemp+"\">");
             out.println("<a class=\"EAncla\" name=\"calendario\" href=\"JavaScript:fLoadCal(9,8,2004,document.forms[0].dtDePro);\">(dd/mm/aaaa)</a>");
             out.println("</td>");
                out.print(vEti.EtiCampoCS("EEtiqueta","a:","ECampo","text",10,10,3,"dtAPro","",0,"","fMayus(this);",false,true,true,request));
             out.println("</tr>");
          %>
          <tr>
            <td class="ETablaT" colspan="10">Ubicación</td>
          </tr>
          <%

              out.println("<tr>");
              out.println("<td class=\"EEtiqueta\">Unidad:</td>");
              out.println("<td colspan=\"5\">");
              out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
              out.println("</td>");
              out.println("</tr>");
              out.println("<tr>");
              out.println("<td class=\"EEtiqueta\">Modulo:</td>");
              out.println("<td colspan=\"5\">");
              if (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveModulo")!=null){
                 TVGRLModulo vGRLModulo = new TVGRLModulo();
                 Vector vModulo = new Vector();
                 int iUniMed = new Integer(request.getParameter("iCveUniMed")).intValue();
                 vModulo = (Vector) AppCacheManager.getColl("GRLModulo",iUniMed + "|");
                 if (vModulo.size()>0){
                    out.print(vEti.SelectOneRowSinTD("iCveModulo","llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);",vModulo,"iCveModulo","cDscModulo",request,request.getParameter("iCveModulo"),true));
                 }else{
                    out.println("<select name=\"iCveModulo\" size=\"1\" onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\">");
                    out.println("</select>");
                 }
              }else{
                 out.println("<select name=\"iCveModulo\" size=\"1\" onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\">");
                 out.println("</select>");
              }
              out.println("</td>");
              out.println("</tr>");
              out.println("<tr>");

              out.println("<td class=\"EEtiqueta\">Área:</td>");
              out.println("<td colspan=\"5\">");
              if (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveModulo")!=null && request.getParameter("iCveArea")!=null){
                 DAOGRLAreaModulo dGRLAreaModulo = new DAOGRLAreaModulo();
                 TVGRLAreaModulo  vGRLAreaModulo = new TVGRLAreaModulo();
                 Vector vAreaModulo = new Vector();
                 cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + request.getParameter("iCveUniMed").toString() +
                           "  AND GRLAreaModulo.iCveModulo = " + request.getParameter("iCveModulo").toString() + " ";
                 vAreaModulo = dGRLAreaModulo.FindByAll(cFiltro);
                 if (vAreaModulo.size()>0)
                    out.print(vEti.SelectOneRowSinTD("iCveArea","",vAreaModulo,"iCveArea","cDscArea",request,request.getParameter("iCveArea"),true));
                 else{
                    out.println("<select name=\"iCveArea\" size=\"1\" >");
                    out.println("</select>");
                 }
              }else{
                 out.println("<select name=\"iCveArea\" size=\"1\" >");
                 out.println("</select>");
              }
              out.println("</td>");

              out.println("</tr>");

          %>
        </table>
        &nbsp;
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td class="ETablaT" colspan="12">Mantenimientos</td>
          </tr>
          <tr>
            <td class="ETablaT">No.</td>
            <td class="ETablaT">Fecha de <br> Programado</td>
            <td class="ETablaT">Tipo de <br> Equipo</td>
            <td class="ETablaT">Clave</td>
            <td class="ETablaT">Equipo</td>
            <td class="ETablaT">Num. Serie</td>
            <td class="ETablaT">Inventario</td>
            <td class="ETablaT">Unidad<br>Médica</td>
            <td class="ETablaT">Módulo</td>
            <td class="ETablaT" colspan="3">Área</td>
          </tr>
          <%
             if (bs != null){
                 bs.start();
                 while(bs.nextRow()){
                     out.println("<tr>");
                     out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveMantenimiento", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("dtProgramado", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoEquipo", "&nbsp;")));
                     out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveEquipo", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEquipo", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumSerie", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cInventario", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscUniMed", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscModulo", "&nbsp;")));
                     out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscArea", "&nbsp;")));
                     out.print("<td class=\"ECampo\">");
                     out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrCatalogo('" +
                                                       bs.getFieldValue("iCveEquipo","") + "','" +
                                                       bs.getFieldValue("iCveMantenimiento","") +
                                                             "'," + "'pg070603031');","Ir a..."));
                     out.print("</td>");
                     out.print("<td class=\"ECampo\">");
                     out.print(vEti.clsAnclaTexto("EAncla","Seguimiento","JavaScript:fIrSeguimiento('" +
                                                       bs.getFieldValue("iCveEquipo","") + "','" +
                                                       bs.getFieldValue("iCveMantenimiento","") +
                                                             "');","Ir a..."));
                     out.print("</td>");


                 }
             }
          %>
        </table>
      <script language="JavaScript">
         form = document.forms[0];
         if (form.dtDeSol)
            form.dtDeSol.disabled = !form.lSolicitados.checked;
         if (form.dtASol)
            form.dtASol.disabled = !form.lSolicitados.checked;
         if (form.dtDePro)
            form.dtDePro.disabled = !form.lProgramados.checked;
         if (form.dtAPro)
            form.dtAPro.disabled = !form.lProgramados.checked;
      </script>
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