<%/**
 * Title: pg070402030.jsp
 * Description: Investigaciones por unidad medica
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Javier Mendoza
 * @version 1.0
 * Clase:pg070402030CFG
 */%>
<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>
<html>
<%
  pg070402030CFG  clsConfig = new pg070402030CFG();                 // modificar
  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070402030.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar


  boolean lFiltros    = false;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  /*String cUpdStatus  = clsConfig.getUpdStatus();*/
  String cUpdStatus  = "Hidden";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = "Hidden";
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  String dFechaActual = "";
  dFechaActual = dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/");

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<script language="JavaScript">

  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); %>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdCCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdCOrdenar"));%>">
  <%
     if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave2  = "" + bs.getFieldValue("iCveEnvio", "");
       cPropiedad = "" + bs.getFieldValue(" ", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <!--input type="hidden" name="hdOPPbaRapida" value="<%=cPropiedad%>" -->
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
  <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
     <tr>
     <%
       TCreaBoton bBtn = new TCreaBoton();
       TEtiCampo vEti = new TEtiCampo();
       boolean lCaptura = clsConfig.getCaptura();
       boolean lNuevo = clsConfig.getNuevo();
       TFechas dtFechas = new TFechas();
       String cBoton = "";
       int iPAnio = 0;
       int iPUniMed = 1;
       int iEnvio = 0;
     %>
        <td colspan="13" class="ETablaT">
            Investigaciones por Unidad Médica
        </td>
     </tr>
     <%
       out.println("<tr>");
       out.println(vEti.Texto("EEtiqueta","Año:"));
     %>
        <td class="ETabla">
           <select name="iAnio" size="1">
     <%
             for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                String cAnio = "0";
                if (request.getParameter("iAnio") == null)
                   cAnio = "0";
                else
                   cAnio = request.getParameter("iAnio");
                if (cAnio.compareTo("" + i) == 0){
                   out.print("<option value = '" + i + "' selected>" + i + "</option>");
                   iPAnio = i;
                }else
                   out.print("<option value = '" + i + "' >" + i + "</option>");
                }
     %>
           </select>
     <%
           if (request.getParameter("iAnio") == null)
              iPAnio = iAnio;

           out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
           out.println("<td class='ECampo' ColSpan=5>");
           TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
           out.println("<input type=\"hidden\" name=\"iCveUsuario\" value=\""+vUsuario.getICveusuario()+"\">");
           if(request.getParameter("iCveUniMed") !=null && request.getParameter("iCveUniMed").length()>0){
              out.println(vEti.SelectOneRowSinTD("iCveUniMed", "",dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso != 3 "),"iCveUniMed", "cDscUniMed", request, request.getParameter("iCveUniMed").toString(), true));
           }else{
              out.println(vEti.SelectOneRowSinTD("iCveUniMed", "",dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso != 3 "),"iCveUniMed", "cDscUniMed", request, "0", true));
           }

           out.println("</td>");

           if (request.getParameter("iCveUniMed") != null && request.getParameter("iCveUniMed").compareTo("") != 0){
              iPUniMed = Integer.parseInt(request.getParameter("iCveUniMed"));
           }
     %>
       </tr>
       <tr>
           <td class="EEtiqueta">Modo de Transporte:</td>
           <td class="ETabla">
              <select name="iCveMdoTrans" size="1" onChange="">
     <%
              Vector vcMdoTrans       = new Vector();
              TVGRLMdoTrans vMdoTrans = new TVGRLMdoTrans();
              TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
              vcMdoTrans = dMdoTrans.findByAll("");
              int iCveMdoTrans = 0;
              if (request.getParameter("iCveMdoTrans") != null && request.getParameter("iCveMdoTrans").length()>0){
                 iCveMdoTrans = Integer.parseInt(request.getParameter("iCveMdoTrans").toString());
              }else{
                 iCveMdoTrans = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
              }
              if(vcMdoTrans.size() >0 ){
                out.print("<option value=0>Seleccione...</option>");
                for (int w = 0;w<vcMdoTrans.size(); w++){
                    vMdoTrans = (TVGRLMdoTrans) vcMdoTrans.get(w);
                    if (request.getParameter("iCveMdoTrans")!= null  && request.getParameter("iCveMdoTrans").length()>0 ){
                       if (Integer.parseInt(request.getParameter("iCveMdoTrans").toString()) == vMdoTrans.getICveMdoTrans()){
                          out.print("<option value =" + vMdoTrans.getICveMdoTrans() + " Selected>" + vMdoTrans.getCDscMdoTrans() + "</option>");
                       }else{
                          out.print("<option value =" + vMdoTrans.getICveMdoTrans() + ">" + vMdoTrans.getCDscMdoTrans() + "</option>");
                       }
                    }else{
                       out.print("<option value =" + vMdoTrans.getICveMdoTrans() + ">" + vMdoTrans.getCDscMdoTrans() + "</option>");
                    }
                }
             }else{
                out.print("<option value=0>No Existen Datos Relacionados...</option>");
             }
     %>
             </select>
         </td>
    <%
         out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha de Asignación:", "ECampo", "text", 10, 10,4,"dtAsigna", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
         out.print("<td>");
         out.print(vEti.clsAnclaTexto("EAncla","Buscar","JavaScript:fBusca();", "Buscar",""));
         out.print("</td>");
    %>
     </tr>
    <%
        TVINVRegistro vInvRegistro = new TVINVRegistro();
        TDINVRegistro dInvRegistro = new TDINVRegistro();
        Vector vcInvRegistro       = new Vector();
        String cWhere = "";
        String cOrder = "";
        String cDate  = "";
        out.println("<input type=\"hidden\" name=\"iIDDGPMPT\" value=\"\">");
        out.println("<input type=\"hidden\" name=\"iIDMdoTrans\" value=\"\">");
        out.println("<input type=\"hidden\" name=\"dtAccidente\" value=\"\">");
        out.println("<input type=\"hidden\" name=\"cDscBreve\" value=\"\">");
        out.println("<input type=\"hidden\" name=\"iCveMotivo\" value=\"\">");
        out.println("<input type=\"hidden\" name=\"cDscMdoTrans\" value=\"\">");
        out.println("<input type=\"hidden\" name=\"iCveProceso\" value=\""+vParametros.getPropEspecifica("INVProceso")+"\">");
        if(request.getParameter("iCveMdoTrans")!=null &&
           request.getParameter("iCveUniMed")!=null   &&
           request.getParameter("iAnio")!=null        &&
           request.getParameter("dtAsigna")!= null){
           String cFecha = ""+new TFechas().getDateSQL(request.getParameter("dtAsigna"));
           cWhere = " a.iCveMdoTrans = "+request.getParameter("iCveMdoTrans")+
                           " And   a.iCveUniMed   = "+request.getParameter("iCveUniMed")+
                           " And   a.iAnio        = "+request.getParameter("iAnio")+
                           " And   a.dtAsigna     = '"+ cFecha +"'";
           cOrder = " Order by a.iAnio, a.iCveMdoTrans, a.iIDDGPMPT ";
        }else{
           cWhere = " a.iCveMdoTrans = 0"+
                           " And   a.iCveUniMed   = 0"+
                           " And   a.iAnio        = 0";
           cOrder = " Order by a.iAnio, a.iCveMdoTrans, a.iIDDGPMPT ";
        }
        vcInvRegistro = dInvRegistro.FindByAll(cWhere,cOrder);
        if(vcInvRegistro.size() > 0){
             out.println("<tr>");
             out.println("<td class='ETablaT'>");
             out.println("ID Medicina Preventiva");
             out.println("</td>");
             out.println("<td class='ETablaT'>");
             out.println("ID Modo de Transporte");
             out.println("</td>");
             out.println("<td class='ETablaT'>");
             out.println("Fecha de Accidente");
             out.println("</td>");
             out.println("<td class='ETablaT'>");
             out.println("Fecha de Notificación");
             out.println("</td>");
             out.println("<td class='ETablaT'>");
             out.println("Descripción Breve");
             out.println("</td>");
             out.println("<td class='ETablaT' ColSpan='3'>");
             out.println("Lugar");
             out.println("</td>");
             out.println("</tr>");
          for (int x=0;x<vcInvRegistro.size();x++){
             vInvRegistro = (TVINVRegistro) vcInvRegistro.get(x);
             out.println("<tr>");
             out.println("<td class='ECampo'>");
             out.println(vInvRegistro.getIIDDGPMPT());
             out.println("</td>");
             out.println("<td class='ECampo'>");
             out.println(vInvRegistro.getIIDMdoTrans());
             out.println("</td>");
             out.println("<td class='ECampo'>");
             out.println(dtFecha.getFechaDDMMYYYY(vInvRegistro.getDtAccidente(),"/"));
             out.println("</td>");
             out.println("<td class='ECampo'>");
             out.println(dtFecha.getFechaDDMMYYYY(vInvRegistro.getDtNotifica(),"/"));
             out.println("</td>");
             out.println("<td class='ECampo'>");
             out.println(vInvRegistro.getCDscBreve());
             out.println("</td>");
             out.println("<td class='ECampo'>");
             out.println(vInvRegistro.getCLugar());
             out.println("</td>");
             out.println("<td>");
             out.println(vEti.clsAnclaTexto("EAncla","Investigación","JavaScript:fInvestigacion('"+vInvRegistro.getIIDDGPMPT()+"','"+vInvRegistro.getIIDMdoTrans()+"','"+dtFecha.getFechaDDMMYYYY(vInvRegistro.getDtAccidente(),"/")+"','"+vInvRegistro.getCDscBreve()+"','"+vInvRegistro.getICveMotivo()+"');", "Investigación",""));
             out.print("</td>");
             out.println("<td>");
             out.println(vEti.clsAnclaTexto("EAncla","Recomendación","JavaScript:fRecomendacion('"+vInvRegistro.getIIDDGPMPT()+"','"+vInvRegistro.getIIDMdoTrans()+"','"+dtFecha.getFechaDDMMYYYY(vInvRegistro.getDtAccidente(),"/")+"','"+vInvRegistro.getCDscBreve()+"','"+vInvRegistro.getICveMotivo()+"');", "Recomendación",""));
             out.print("</td>");
             out.println("</tr>");
          }
        }else{
           if(request.getParameter("iCveMdoTrans")!= null){
%>
               <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
<%
                out.println("<tr>");
                out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                out.println("</tr>");
%>
               </table>
<%
           }
        }
%>
</table>
<%
}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>