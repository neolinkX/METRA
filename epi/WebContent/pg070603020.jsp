<%/**
 * Title:       Solicitud de Mantenimiento
 * Description: Se solicita el Mantenimiento de un Equipo determinado
 * Copyright:   2004
 * Company:     Micros Personales S.A. de C.V.
 * @author      Marco A. Hernández García
 * @version     1.0
 * Clase:       pg070603020CFG
 */%>


<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="com.micper.util.caching.AppCacheManager"%>
<html>
<%
  pg070603020CFG  clsConfig = new pg070603020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070603020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070603020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070603021.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Equipo|Num.Serie|";    // modificar
  String cCveOrdenar  = "EQMEquipo.iCveEquipo|EQMEquipo.cNumSerie|";  // modificar
  String cDscFiltrar  = "Equipo|Num.Serie|";    // modificar
  String cCveFiltrar  = "EQMEquipo.iCveEquipo|EQMEquipo.cNumSerie|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
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
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("CalEqProceso"));
  String cFiltro = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070603020.js"%>"></SCRIPT>
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
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
          &nbsp;
          <input type="hidden" name="hdBoton" value="">
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
           <tr><td colspan="5" class="ETablaT">Solicitud</td></tr>
            <%
              /*out.println("<tr>");
              out.println("<td class=\"EEtiqueta\">Unidad:</td>");
              out.println("<td>");
              out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
              out.println("</td>");
              out.println("<td class=\"EEtiqueta\">Modulo:</td>");
              out.println("<td>");
              if (request.getParameter("iCveUniMed")!=null && request.getParameter("iCveModulo")!=null){
                 TVGRLModulo vGRLModulo = new TVGRLModulo();
                 Vector vModulo = new Vector();
                 int iUniMed = new Integer(request.getParameter("iCveUniMed")).intValue();
                 vModulo = (Vector) AppCacheManager.getColl("GRLModulo",iUniMed + "|");
                 if (vModulo.size()>0)
                    out.print(vEti.SelectOneRowSinTD("iCveModulo","llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);",vModulo,"iCveModulo","cDscModulo",request,request.getParameter("iCveModulo"),true));
                 else{
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
              out.println("<td colspan=\"3\">");
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
              }*/

             //Inserción MGonzalez
                      String User = "" + vUsuario.getICveusuario();
                      out.println("<tr>");
                      out.println("<td class=\"EEtiqueta\">Unidad Médica:</td>");
                      out.println("<td>");
                      if (vUsuario!=null)
                         out.print(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(1,"+User+",this.value,'','',document.forms[0].iCveModulo);",vUsuario.getVUniFiltro(iCveProceso),"iCveUniMed","cDscUniMed",request,"0",true));
                      out.println("</td>");

                      //Módulo
                      TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                      out.print(vEti.Texto("EEtiqueta", "Módulo:"));
                      if (request.getParameter("iCveModulo") == null){
                         out.println("<td class='ECampo'>");
                         out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);\">");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                         Vector vGModulo = new Vector();
                         cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                   " and GRLUsuMedicos.iCveUniMed =  " + request.getParameter("iCveUniMed") ;
                         vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveModulo","llenaSLT(2,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveArea);",vGModulo,"iCveModulo","cDscModulo",request,""));
                       }

                      out.println("</td>");
                      out.println("</tr>");
                      out.println("<tr>");

                      //Área
                      DAOGRLAreaModulo dAreaModuloC = new DAOGRLAreaModulo();
                      out.print(vEti.Texto("EEtiqueta", "Área:"));
                      if (request.getParameter("iCveArea") == null){
                         out.println("<td class='ECampo'>");
                         out.println("<SELECT NAME='iCveArea' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,this.value,document.forms[0].iCveUsuSolic);\">");
                         out.println("</SELECT>");
                       }
                       else{
                         TVGRLAreaModulo vAreaModulo = new  TVGRLAreaModulo();
                         Vector vcAreaModulo = new Vector();
                         cFiltro = " WHERE GRLAreaModulo.iCveUniMed = " + request.getParameter("iCveUniMed") +
                                   "   AND GRLAreaModulo.iCveModulo = " + request.getParameter("iCveModulo");
                         vcAreaModulo = dAreaModuloC.FindByAll(cFiltro);
                         out.print(vEti.SelectOneRow("ECampo","iCveArea","llenaSLT(3,document.forms[0].iCveUniMed.value,document.forms[0].iCveModulo.value,this.value,document.forms[0].iCveUsuSolic);",vcAreaModulo,"iCveArea","cDscArea",request,""));
                        }


            // fin de Inserción
              out.println("</td>");
              out.println("</tr>");
            %>
          </table>
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td class="ETablaT" colspan="8">Equipos</td>
            </tr>
            <tr>
              <td class="ETablaT">Clave</td>
              <td class="ETablaT">Descripción</td>
              <td class="ETablaT">Marca</td>
              <td class="ETablaT">Modelo</td>
              <td class="ETablaT">Num. Serie</td>
              <td class="ETablaT">Inventario</td>
              <td class="ETablaT">Estado</td>
              <td class="ETablaT" colspan="2">Solicitar</td>
            </tr>
            <%
               if (bs != null){
                   bs.start();
                   while(bs.nextRow()){
                       out.println("<tr>");
                       out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveEquipo", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEquipo", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscMarca", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cModelo", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cNumSerie", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cInventario", "&nbsp;")));
                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscEstadoEQM", "&nbsp;")));
                       out.print("<td class=\"ECampo\">");
                       out.print(vEti.clsAnclaTexto("EAncla","Solicitar","JavaScript:fIrCatalogo('" +
                                                     bs.getFieldValue("iCveEquipo","") + "','pg070603021');","Ir a..."));
                       out.print("</td>");
                   }
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

