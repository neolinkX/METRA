<%/**
 * Title: pg070101060
 * Description: Consulta de Configuracion de la rama
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070101060CFG  clsConfig = new pg070101060CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070101060.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101060.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101060.jsp";       // modificar
  String cReordenar   = "pg070101062.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Orden|Pregunta|Sexo(M/F/A)|";    // modificar
  String cCveOrdenar  = "iOrden|cPregunta|cGenero|";  // modificar
  String cDscFiltrar  = "Orden|Pregunta|Sexo(M/F/A)|";    // modificar
  String cCveFiltrar  = "iOrden|cPregunta|cGenero|";  // modificar
  String cTipoFiltrar = "7|8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
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

  
  TDMEDREGSIN dREGSIN = new TDMEDREGSIN();
  String cWhere ="";
  
/**
* Este segmento de codigo inicializa los vectores
* para el llenado de los combos
*/
 Vector vMedSrv = new Vector();
 TDMEDServicio dMEDServicio = new TDMEDServicio();
 vMedSrv = dMEDServicio.FindByAll("Where lActivo=1");
 TVMEDServicio vMEDServicio = new TVMEDServicio();
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
  <input type="hidden" name="hdCCondicion" value="<% if (request.getParameter("hdCCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdCOrdenar" value="<% if (request.getParameter("hdCOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
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
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="iCveSintoma" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td class="EEtiqueta">Servicio:</td>
                              <td class="ETabla">
                               <select name="iCveServicio" size="1" onChange="fillNext();">
                                 <%
                                 if (request.getParameter("iCveServicio")!=null){
                                 %>
                                  <option value=0 Selected>Seleccione...</option>
                                 <%
                                 }else{
                                 %>
                                  <option value=0>Seleccione...</option>
                                 <%
                                 }
                                     for (int i = 0;i<vMedSrv.size(); i++){
                                          vMEDServicio = (TVMEDServicio) vMedSrv.get(i);
                                          if (request.getParameter("iCveServicio")!=null&&request.getParameter("iCveServicio").compareToIgnoreCase(new Integer( vMEDServicio.getICveServicio()).toString())==0){
                                             out.print("<option value =" + vMEDServicio.getICveServicio() + " Selected>" + vMEDServicio.getCDscServicio() + "</option>");
                                          }else{
                                             out.print("<option value =" + vMEDServicio.getICveServicio() + ">" + vMEDServicio.getCDscServicio() + "</option>");
                                          }
                                       }
                                    %>
                                 </select>
                              </td>
                              <td class="EEtiqueta">Rama:</td>
                              <td class="ETabla"><%
                                        TDMEDRama dMEDRama = new TDMEDRama();
                                        TVMEDRama vMEDRama = new TVMEDRama();
                                        Vector vMEDr = new Vector();
                                        String cFiltro = "";
                                        if (request.getParameter("iCveServicio") != null){
                                            cFiltro = " WHERE iCveServicio = " + request.getParameter("iCveServicio");
                                        }else{
                                            cFiltro = " WHERE iCveServicio = " + "0";
                                        }
                                        vMEDr = dMEDRama.FindByAll(cFiltro);
                                     %>
                                    <select name="iCveRama" size="1" onChange="fillBusca();">
                                     <%
                                    out.print("<option value=0>Seleccione...</option>");
                                    if (vMEDr.size() > 0){
                                     for (int x = 0;x<vMEDr.size(); x++){
                                          vMEDRama = (TVMEDRama) vMEDr.get(x);
                                          if (request.getParameter("iCveRama")!=null&&request.getParameter("iCveRama").compareToIgnoreCase(new Integer(vMEDRama.getICveRama()).toString())==0){
                                             out.print("<option value =" + vMEDRama.getICveRama() + " Selected>" + vMEDRama.getCDscRama() + "</option>");
                                          }else{
                                             out.print("<option value =" + vMEDRama.getICveRama() + ">" + vMEDRama.getCDscRama() + "</option>");
                                          }
                                       }
                                    }
                                    %>
                                 </select>
                              </td>
                              <%
                                boolean lReordenar = clsConfig.getLPagina(cReordenar);
                                if(lReordenar){
                                  out.print("<td class=\"ECampo\">");
                                  out.print(vEti.clsAnclaTexto("EAncla","Reordenar","JavaScript:fReordenar('" + request.getParameter("iCveServicio") + "', '" + request.getParameter("iCveRama") + "');", ""));
                                  out.print("</td>");
                                }
                              %>

                            </tr>
                          </table>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="11" class="ETablaT">Consulta de la Configuración de la Rama
                              </td>
                            </tr>
                            <tr>
                              <td class="ETablaT">Clave</td>
                              <td class="ETablaT">Pregunta / Síntoma / Resultado</td>
                              <td class="ETablaT">Sexo</td>
                              <td class="ETablaT">Estudio</td>
                              <td class="ETablaT">Tipo de Respuesta</td>
                              <td class="ETablaT">Etiqueta</td>
                              <td class="ETablaT">Eval. Automática</td>
                              <td class="ETablaT">Reglas</td>
                              <td class="ETablaT">Dependiente</td>
                              <td class="ETablaT" colspan="2">Contestación Personal</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                   String cGenero ="";
                                   bs.start();
                                   while(bs.nextRow()){
                                       out.println("<tr>");
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("iCveSintoma", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cPregunta", "&nbsp;")));
                                       if(bs.getFieldValue("cGenero", "").toString().equalsIgnoreCase("m")){
                                          cGenero = "MASCULINO";
                                       }
                                       if(bs.getFieldValue("cGenero", "").toString().equalsIgnoreCase("f")){
                                          cGenero = "FEMENINO";
                                       }
                                       if(bs.getFieldValue("cGenero", "").toString().equalsIgnoreCase("a")){
                                          cGenero = "AMBOS";
                                       }
                                       out.print(vEti.Texto("ETabla",""+ cGenero));
                                       if(bs.getFieldValue("lEstudio", "").toString().equalsIgnoreCase("1")){
                                          out.print(vEti.Texto("ETablaC","SI"));
                                       }else{
                                          out.print(vEti.Texto("ETablaC","NO"));
                                       }
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscTpoResp", "&nbsp;")));
                                       out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cEtiqueta", "&nbsp;")));

                                       if(bs.getFieldValue("lEvalAuto", "").toString().equalsIgnoreCase("1")){
                                          out.print(vEti.Texto("ETablaC","SI"));
                                       }else{
                                          out.print(vEti.Texto("ETablaC","NO"));
                                       }

                                       ////Obtener si existen Reglas////
                                       cWhere = "D.ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND "
                           					+ " D.ICVERAMA = "+request.getParameter("iCveRama")+"  AND	"
                        					+ " D.ICVESINTOMA = "+bs.getFieldValue("iCveSintoma")+" ";
                                       out.print(vEti.Texto("ETabla",""+ dREGSIN.PreguntaConReglas(cWhere)+"&nbsp;"));
                                       
                                       ////Obtener si es dependiente////
                                       cWhere = "D.ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND "
                           					+ " D.ICVERAMA = "+request.getParameter("iCveRama")+"  AND	"
                        					+ " D.ICVESINTOMA = "+bs.getFieldValue("iCveSintoma")+" ";
                                       out.print(vEti.Texto("ETabla",""+ dREGSIN.PreguntasDeLasQueDepende(cWhere)+"&nbsp;"));
                                       
                                       
                                       if(bs.getFieldValue("lCPersonal", "").toString().equalsIgnoreCase("1")){
                                          out.print(vEti.Texto("ETablaC","SI"));
                                       }else{
                                          out.print(vEti.Texto("ETablaC","NO"));
                                       }

                                       if (clsConfig.getLPagina(cCatalogo)){
                                           out.print("<td class=\"EEtiquetaC\">");
                                           out.print(vEti.clsAnclaTexto("EEtiquetaC","Detalle","JavaScript:fIrCatalogo('" +bs.getFieldValue("iCveSintoma","") + "');",""));
                                           out.print("</td>");
                                       }
                                       out.println("</tr>");
                                   }
                               }  
                            %> 
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

