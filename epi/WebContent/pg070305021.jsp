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

<html>
<%
  pg070305021CFG  clsConfig = new pg070305021CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070305021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070305021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";       // modificar
  String cDscOrdenar  = "NIM|";    // modificar
  String cCveOrdenar  = "iCveMuestra|";  // modificar
  String cDscFiltrar  = "NIM|";    // modificar
  String cCveFiltrar  = "iCveMuestra|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "IR";            // modificar

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
  String cPosicion = "";
  String cClave    = "";
  String cClave2   = "";
  String cClave3   = "";
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  function fAccion(cAccion){
    form = document.forms[0];
    form.target="_self";
    form.hdBoton.value = cAccion;
    form.submit();
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
     int iEnvio =  0;
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
       cClave  = ""+bs.getFieldValue("iAnio", "0");
       cClave2 = ""+bs.getFieldValue("iCveLaboratorio", "0");
       cClave3 = ""+bs.getFieldValue("iCveEnvio", "0");
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%  if (request.getParameter("hdCampoClave")!=null)   out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave2" value="<% if (request.getParameter("hdCampoClave2")!=null) out.print(request.getParameter("hdCampoClave2")); else out.print(cClave2);%>">
  <input type="hidden" name="hdCampoClave3" value="<% if (request.getParameter("hdCampoClave3")!=null) out.print(request.getParameter("hdCampoClave3")); else out.print(cClave3);%>">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="iAnio" value="<%=request.getParameter("iAnio")%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveLaboratorio" value="<%=request.getParameter("iCveLaboratorio")%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
        if(request.getParameter("hdBoton").toString().compareTo("Generar Reporte")==0) {
           out.println(clsConfig.getActiveX());
        }
        if(request.getParameter("hdBoton").toString().compareTo("Reporte")==0) {
           out.println(clsConfig.getActiveY());
        }

  %>
    <tr>
       <td valign="top">
          &nbsp;
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
            <tr>
              <td colspan="10" class="ETablaT">Muestras del Envío
               <%
                  if (request.getParameter("hdCampoClave3")!=null){
                     out.print(request.getParameter("hdCampoClave3"));
                  }
               %>
              </td>
            </tr>
             <% // modificar según listado
               if (bs != null){
                 %>
                 <tr>
                   <td class="ETablaT">NIM</td>
                   <td class="ETablaT">Tipo de <br> Recepci&oacute;n</td>
                   <td class="ETablaT">Motivo</td>
                   <td class="ETablaT">Lote<br>Cualitativo</td>
                   <td class="ETablaT">An&aacute;lisis</td>
                   <td class="ETablaT">Presunto <br> Positivo</td>
                   <td class="ETablaT">Sustancias <br> Presuntas</td>
                   <td class="ETablaT">Sustancias <br> Confirmadas</td>
                   <td class="ETablaT">Resultado</td>
                   <td class="ETablaT">Sustancia</td>

                </tr>
                   <%
                   bs.start();
                   String cCveMuestra = "";

                   while(bs.nextRow()){
                     out.print("<tr>");
                     if (bs.getFieldValue("cCveMuestra")!=null && (bs.getFieldValue("cCveMuestra").toString().compareTo(cCveMuestra)!=0)){
                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("cCveMuestra", "&nbsp;").toString()));
                     if (bs.getFieldValue("cDscTipoRecep").toString().compareTo("null")!=0)
                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscTipoRecep", "&nbsp;").toString()));
                     else
                        out.print("<td class=\"ETabla\">&nbsp;</td>");

                     if (bs.getFieldValue("cDscMotivo").toString().compareTo("null")!=0)
                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMotivo", "&nbsp;").toString()));
                     else
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                     if (bs.getFieldValue("iCveSituacion").toString().compareTo(vParametros.getPropEspecifica("TOXRechazo").toString())==0){
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                     }else{
                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("iCveLoteCualita", "&nbsp;").toString()));
                        out.print(vEti.Texto("ETablaR", bs.getFieldValue("cCveAnalisis", "&nbsp;").toString()));
                        if (bs.getFieldValue("lAutorizado").toString().compareTo("1") !=0 ){
                          out.print("<td class=\"ETablaR\">Analizando</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                          out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        }
                        else {
                        if (bs.getFieldValue("lPresuntoPost").toString().compareTo("null")!=0){
                            if (bs.getFieldValue("lPresuntoPost").toString().compareTo("1")==0)
                               out.print(vEti.Texto("EResaltaR", "SI"));
                            else
                               out.print(vEti.Texto("ETablaR", "NO"));
                        }
                        else
                            out.print(vEti.Texto("ETablaR", "&nbsp;"));


                        if (bs.getFieldValue("iSustPost").toString().compareTo("null")!=0)
                           out.print(vEti.Texto("ETablaR", bs.getFieldValue("iSustPost", "&nbsp;").toString()));
                        else
                            out.print(vEti.Texto("ETablaR", "&nbsp;"));

                        if (bs.getFieldValue("iSustConf").toString().compareTo("null")!=0)
                            out.print(vEti.Texto("ETablaR", bs.getFieldValue("iSustConf", "&nbsp;").toString()));
                        else
                            out.print(vEti.Texto("ETablaR", "&nbsp;"));

                       if (bs.getFieldValue("iSustPost").toString().compareTo("null")!=0 &&
                           bs.getFieldValue("iSustConf").toString().compareTo("null")!=0 ){
                           if(bs.getFieldValue("iSustPost").toString().compareTo(bs.getFieldValue("iSustConf").toString())!=0){
                             if(bs.getFieldValue("lResultado").toString().compareTo("1")==0)
                                    out.print(vEti.Texto("EPositivosR", "POSITIVO"));
                             else
                                out.print(vEti.Texto("ETabla", "RESULTADO PENDIENTE"));
                            }
                           else{
                               if (bs.getFieldValue("lResultado").toString().compareTo("1")==0)
                                  out.print(vEti.Texto("EPositivosR", "POSITIVO"));
                               else
                                  out.print(vEti.Texto("ETabla", "NEGATIVO"));
                               }
                           // IMPRESIÓN DE SUSTANCIAS
                           TVTOXCuantAnalisis vCAnal = new TVTOXCuantAnalisis(); 
                           vCAnal.setiAnio(new Integer(bs.getFieldValue("iAnio","0").toString()));
                           vCAnal.setiCveLaboratorio(new Integer(bs.getFieldValue("iCveLaboratorio","0").toString()));
                           vCAnal.setiCveAnalisis(new Integer(bs.getFieldValue("iCveAnalisis","0").toString()));
                           vCAnal.setiCveSustancia(new Integer(0));
                           out.println(vEti.Texto("ETablaC",clsConfig.getOtrasSust(vCAnal)));

                       }else{
                           out.print(vEti.Texto("ETabla", "EN PROCESO"));
                           out.print(vEti.Texto("ETabla", "EN PROCESO"));
                       }
                     }
                    }

                   }
                   else{
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        out.print("<td class=\"ETablaR\">&nbsp;</td>");
                        if (bs.getFieldValue("cDscSustancia").toString().compareTo("null")!=0)
                          out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscSustancia", "&nbsp;").toString()));
                        else
                           out.print("<td class=\"ETablaR\">&nbsp;</td>");
                   }
                   out.print("</tr>");
                   if (bs.getFieldValue("iCveMuestra")!=null)
                      cCveMuestra = bs.getFieldValue("iCveMuestra").toString();
               }
               }else{
                 out.println("<tr>");
                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
