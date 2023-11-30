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
  pg070302041CFG  clsConfig = new pg070302041CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070302041.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070302041.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "NIM|Fecha de Recolección|Situación|";    // modificar
  String cCveOrdenar  = "TOXMuestra.iCveMuestra|TOXMuestra.dtRecoleccion|TOXMuestra.iCveSituacion|";  // modificar
  String cDscFiltrar  = "NIM|";    // modificar
  String cCveFiltrar  = "TOXMuestra.iCveMuestra|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
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
     int iEnvio = 0;
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
       cClave  = ""+bs.getFieldValue("iAnio", "");
       cClave2 = ""+bs.getFieldValue("iCveUniMed", "");
       cClave3 = ""+bs.getFieldValue("iCveEnvio", "");
       iEnvio  = Integer.parseInt(bs.getFieldValue("iCveEnvio", "0").toString());
     }
  %>

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%if (request.getParameter("hdCampoClave")!=null) out.print(request.getParameter("hdCampoClave")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave2" value="<%if (request.getParameter("hdCampoClave2")!=null) out.print(request.getParameter("hdCampoClave2")); else out.print(cClave2);%>">
  <input type="hidden" name="hdCampoClave3" value="<%if (request.getParameter("hdCampoClave3")!=null) out.print(request.getParameter("hdCampoClave3")); else out.print(cClave3);%>">
  <input type="hidden" name="hdBoton" value="">
  <input type="hidden" name="iAnio" value="<%=request.getParameter("iAnio")%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveLaboratorio" value="<%=request.getParameter("iCveLaboratorio")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr>
     <td valign="top">
     <tr>
          <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="10" class="ETablaT">Envío <% if (iEnvio > 0) out.println(iEnvio); %></td>
                            </tr>
                            <tr>
                              <td colspan="10" class="ETablaT">Listado de Muestras</td>
                            </tr>
                             <% // modificar según listado
                               if (bs != null){
                                 %>
                                 <tr>
                                   <td class="ETablaT">NIM</td>
                                   <td class="ETablaT">Proceso</td>
                                   <td class="ETablaT">Motivo</td>
                                   <td class="ETablaT">Recolecci&oacute;n</td>
                                   <td class="ETablaT">Situaci&oacute;n</td>
                                   <td class="ETablaT">Motivo de la recepci&oacute;n</td>
                                   <td class="ETablaT">Resultado</td>
                                   <td class="ETablaT">Autorizado</td>
                                   <!-- <td class="ETablaT">Hielera</td> -->
                                   <td class="ETablaT">Mensajería</td>
                                </tr>
                                   <% 
                                   bs.start();  
                                   while(bs.nextRow()){ 
                                     out.print("<tr ");
                                     if((bs.getFieldValue("cResultado", "&nbsp;").toString().equalsIgnoreCase("POSITIVO") ||
                                         bs.getFieldValue("cResultado", "&nbsp;").toString().equalsIgnoreCase("EN PROCESO")))
                                        out.print("class=\"ETablaST\"");
                                     out.print(">");
                                     out.print(vEti.Texto("ETablaR", bs.getFieldValue("cCveMuestra", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscProceso", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMotivo", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETablaC", bs.getFieldValue("dtRecoleccion", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscSituacion", "&nbsp;").toString()));
                                     if(!bs.getFieldValue("cObsMuestra", "&nbsp;").toString().equalsIgnoreCase("null"))
                                      out.print(vEti.Texto("ETabla", bs.getFieldValue("cObsMuestra", "&nbsp;").toString()));
                                     else
                                      out.print(vEti.Texto("ETabla", "&nbsp;"));
                                     if(!bs.getFieldValue("cResultado", "&nbsp;").toString().equalsIgnoreCase("POSITIVO"))
                                        out.print(vEti.Texto("ETabla", bs.getFieldValue("cResultado", "&nbsp;").toString()));
                                     else {
                                        // IMPRESIÓN DE SUSTANCIAS
                                        TVTOXCuantAnalisis vCAnal = new TVTOXCuantAnalisis();
                                        vCAnal.setiAnio(new Integer(bs.getFieldValue("iAnio","0").toString()));
                                        vCAnal.setiCveAnalisis(new Integer(bs.getFieldValue("iCveMuestra","0").toString()));
                                        vCAnal.setiCveSustancia(new Integer(0));
                                        out.println(vEti.Texto("EPositivosR",bs.getFieldValue("cResultado", "&nbsp;").toString() + "<br>" + clsConfig.getOtrasSust(vCAnal)));                                     
                                     }

                                     //Obteniendo informacion de Autorizacion del Lote Confirmatorio
                                     if(!bs.getFieldValue("cResultado", "&nbsp;").toString().equalsIgnoreCase("POSITIVO")){
                                         out.print(vEti.Texto("ETabla", "&nbsp;"));
                                     }else{
                                     TVTOXCuantAnalisis vCAnal2 = new TVTOXCuantAnalisis();
                                        vCAnal2.setiAnio(new Integer(bs.getFieldValue("iAnio","0").toString()));
                                        vCAnal2.setiCveAnalisis(new Integer(bs.getFieldValue("iCveMuestra","0").toString()));
                                        vCAnal2.setiCveSustancia(new Integer(0));
                                        out.println(vEti.Texto("EPositivosR",clsConfig.getAutorizada(vCAnal2)));
                                     }
                                     String Muestra = bs.getFieldValue("cCveMuestra", "&nbsp;").toString();
                                     String[] parts = Muestra.split(" ");
                                     Muestra = parts[1]+"";
                                     TDMuestra dMuestra =  new TDMuestra();
                                     //String Hielera = dMuestra.FindMuestraHielera(Integer.parseInt(cClave) ,Integer.parseInt(request.getParameter("iCveUniMed")) ,Integer.parseInt(cClave3),Integer.parseInt(Muestra))+"";
                                     String Mensajeria = dMuestra.FindMuestraMensajeria(Integer.parseInt(cClave) ,Integer.parseInt(request.getParameter("iCveUniMed")) ,Integer.parseInt(cClave3),Integer.parseInt(Muestra))+"";
                                     //out.print(vEti.Texto("ETabla", Hielera+"&nbsp;"));
                                     out.print(vEti.Texto("ETabla", Mensajeria+"&nbsp;"));
                                     out.print("</tr>");
                                   } 
                               }
                               else{
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                 out.println("</tr>");
                               }
                            %>
                          </table>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>
