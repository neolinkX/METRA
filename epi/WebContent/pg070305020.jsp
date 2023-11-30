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
<%@ page import="java.util.Vector"%>

<html>
<%
  pg070305020CFG  clsConfig = new pg070305020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305020.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305020.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Envío|";    // modificar
  String cCveOrdenar  = "iCveEnvio|";  // modificar
  String cDscFiltrar  = "Envío|";    // modificar
  String cCveFiltrar  = "iCveEnvio|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
  String cClave2   = "";
  String cClave3   = "";
  String cClaveA    = "";
  String cClaveB   = "";
  String cClaveC   = "";
  String cPosicion = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fIrpg070305021(cCampoClave, cCampoClave2, cCampoClave3){
    form = document.forms[0];
    form.hdCampoClave.value = cCampoClave;
    form.hdCampoClave2.value = cCampoClave2;
    form.hdCampoClave3.value = cCampoClave3;
    form.hdRowNum.value = cCampoClave;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070305021.jsp';
    form.submit();
  }
  function fIrpg070303010(cCampoClaveA, cCampoClaveB, cCampoClaveC){
    form = document.forms[0];
    form.hdCampoClaveA.value = cCampoClaveA;
    form.hdCampoClaveB.value = cCampoClaveB;
    form.hdCampoClaveC.value = cCampoClaveC;
    form.hdRowNum.value = cCampoClaveA;
    form.hdBoton.value = 'Actual';
    form.target = 'FRMDatos';
    form.action = 'pg070303010.jsp';
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
       cClave2 = ""+bs.getFieldValue("iCveUniMed", "");
       cClave3 = ""+bs.getFieldValue("iCveEnvio", "");
       cClaveA = ""+bs.getFieldValue("iAnio", "");
       cClaveB = ""+bs.getFieldValue("iCveUniMed", "");
       cClaveC = ""+bs.getFieldValue("iCveEnvio", "");

     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCampoClave2" value="<%=cClave2%>">
  <input type="hidden" name="hdCampoClave3" value="<%=cClave3%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdIAnio" value="">
  <input type="hidden" name="hdICveUniMed" value="">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="hdCampoClaveA" value="<%=cClaveA%>">
  <input type="hidden" name="hdCampoClaveB" value="<%=cClaveB%>">
  <input type="hidden" name="hdCampoClaveC" value="<%=cClaveC%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){%>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td valign="top">
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <tr>
            <td colspan="8" class="ETablaT">Búsqueda del Envío
            </td>
          </tr>
          <tr>
            <%
              out.print(vEti.Texto("EEtiqueta", "Año:"));
            %>
            <td>
               <select name="iAnio" size="1">
                  <%
                    for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                      if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                        out.print("<option value = " + i + " selected>" + i + "</option>");
                      else
                        out.print("<option value = " + i + ">" + i + "</option>");
                     }
                  %>
               </select>
            </td>
            <%
              out.println(vEti.Texto("EEtiqueta","Laboratorio:"));
              TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
              TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
              TVGRLUMUsuario vUMUsuario = new TVGRLUMUsuario();
              Vector vUMusuario = new Vector();

              vUMusuario = dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso = 3 ");
              out.println("<td class='ECampo'>");
              out.println("<SELECT NAME=\"iCveLaboratorio\" SIZE=\"1\" onChange=\"\">");
              out.println("<option value=\"-1\">Todos</option>");
              if (vUMusuario.size()>0){
                 for (int i = 0; i< vUMusuario.size();i++){
                     vUMUsuario = (TVGRLUMUsuario)vUMusuario.get(i);
                     if (request.getParameter("iCveLaboratorio")!=null&&request.getParameter("iCveLaboratorio").toString().compareTo(vUMUsuario.getICveUniMed()+"")==0)
                        out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\" selected>"+vUMUsuario.getCDscUniMed()+"</option>");
                     else
                        out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\">"+vUMUsuario.getCDscUniMed()+"</option>");
                 }
              }else
                  out.println("<option value=\"-1\">Datos no disponibles</option>");
              out.println("</SELECT>");
              out.println("</td>");
            %>
          </tr>
          <tr>
            <%
              out.println(vEti.TextoCS("EEtiqueta","Unidad Médica:",2));
             /* vUMusuario = dUMUsuario.getUniMedxUsu2(vUsuario.getICveusuario()," AND iCveProceso != 3 ");
              out.print(vEti.SelectOneRowSinTD("iCveUniMed","", vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0"));
              out.println("<td class='ECampo' colspan='3'>");
              out.println("<SELECT NAME=\"iCveUniMed\" SIZE=\"1\" onChange=\"\">");
              out.println("<option value=\"-1\">Todos</option>");
              if (vUMusuario.size()>0){
                 for (int i = 0; i< vUMusuario.size();i++){
                     vUMUsuario = (TVGRLUMUsuario)vUMusuario.get(i);
                     if (request.getParameter("iCveUniMed")!=null&&request.getParameter("iCveUniMed").toString().compareTo(vUMUsuario.getICveUniMed()+"")==0)
                        out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\" selected>"+vUMUsuario.getCDscUniMed()+"</option>");
                     else
                        out.println("<option value=\""+vUMUsuario.getICveUniMed()+"\">"+vUMUsuario.getCDscUniMed()+"</option>");
                 }
              }else
                  out.println("<option value=\"-1\">Datos no disponibles</option>");
              out.println("</SELECT>"); */
              out.println("<td class='ECampo' colspan='3'>");
                out.print(vEti.SelectOneRowSinTD("iCveUniMed","", vUsuario.getVUniMed(),"iClave","cDescripcion",request,"0"));
              out.println("</td>");
            %>
          </tr>
          </table>
      </td>
      </tr>
      <tr>
      <td valign="top">
                          &nbsp;
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="10" class="ETablaT">Envíos
                              </td>
                            </tr>
                          <% if (bs != null){ %>
                            <tr>
                              <td class="ETablaT">Unidad Médica</td>
                              <td class="ETablaT">Envío</td>
                              <td class="ETablaT">Fecha de Envío</td>
                              <td class="ETablaT">Fecha de<br>Recepción</td>
                              <td class="ETablaT">Usuario<br>Recepción</td>
<!--                              <td class="ETablaT">Laboratorio</td> -->
                              <td class="ETablaT">Enviadas</td>
                              <td class="ETablaT">Rechazadas</td>
                              <td class="ETablaT">Analizadas</td>
                              <td class="ETablaT" colspan="2">Pendientes</td>
                            </tr>
                             <% // modificar según listado
                                   bs.start();
                                   while(bs.nextRow()){
                                     out.print("<tr>");
                                     TVTOXEnvio vTVTOXEnvio = (TVTOXEnvio)bs.getCurrentBean();
                                     if (bs.getFieldValue("cDscUniMed").toString().compareTo("null")!=0)
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscUniMed", "&nbsp;").toString()));
                                     else
                                        out.print(vEti.Texto("ETabla", "&nbsp;"));

                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveEnvio", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETablaC", vTVTOXEnvio.getCdtEnvio()));

                                     if (bs.getFieldValue("dtRecepcion")!=null&&bs.getFieldValue("dtRecepcion").toString().compareTo("null")!=0)
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("dtRecepcion", "&nbsp;").toString()));
                                     else
                                        out.print(vEti.Texto("ETabla", "&nbsp;"));

                       String cUsuRec = "";
                       if (bs.getFieldValue("cNombre").toString().compareTo("")!=0    && bs.getFieldValue("cNombre").toString().compareTo("null")!=0 &&
                           bs.getFieldValue("cApPaterno").toString().compareTo("")!=0 && bs.getFieldValue("cApPaterno").toString().compareTo("null")!=0 &&
                           bs.getFieldValue("cApMaterno").toString().compareTo("")!=0 && bs.getFieldValue("cApMaterno").toString().compareTo("null")!=0){
                           cUsuRec =  bs.getFieldValue("cNombre").toString() + " " +
                                      bs.getFieldValue("cApPaterno").toString() + " " +
                                      bs.getFieldValue("cApMaterno").toString();
                           out.println(vEti.Texto("ETabla", cUsuRec));
                       }else
                           out.println("<td>&nbsp;</td>");

/*                                     if (bs.getFieldValue("cDscLaboratorio").toString().compareTo("null")!=0)
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscLaboratorio", "&nbsp;").toString()));
                                     else
                                        out.print(vEti.Texto("ETabla", "&nbsp;"));
*/

                                     if (bs.getFieldValue("iTotalEnviadas").toString().compareTo("null")!=0)
                                        out.print("<td class=\"ETablaR\">" + bs.getFieldValue("iTotalEnviadas", "&nbsp;").toString() + "</td>");
                                     else
                                        out.print("<td class=\"ETablaR\">&nbsp;</td>");


                                     if (bs.getFieldValue("iTotalRechazadas").toString().compareTo("null")!=0)
                                        out.print("<td class=\"ETablaR\">" + bs.getFieldValue("iTotalRechazadas", "&nbsp;").toString() + "</td>");
                                     else
                                        out.print("<td class=\"ETablaR\">&nbsp;</td>");


                                     if (bs.getFieldValue("iTotalRecibidas").toString().compareTo("null")!=0)
                                        out.print("<td class=\"ETablaR\">" + bs.getFieldValue("iTotalRecibidas", "&nbsp;").toString() + "</td>");
                                     else
                                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                                     if (bs.getFieldValue("iTotalPendientes").toString().compareTo("null")!=0)
                                        out.print("<td class=\"ETablaR\">" + bs.getFieldValue("iTotalPendientes", "&nbsp;").toString() + "</td>");
                                     else
                                        out.print("<td class=\"ETablaR\">&nbsp;</td>");


                                     out.print("<td class=\"ETabla\">");
                                     out.print(vEti.clsAnclaTexto("EAncla","Detalle","JavaScript:fIrpg070305021('" + bs.getFieldValue("iAnio", "") + "', '" + bs.getFieldValue("iCveUniMed", "") + "', '" + bs.getFieldValue("iCveEnvio", "") + "', 'pg070305021.jsp');", ""));
                                     out.print("</td>");
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
    <td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
