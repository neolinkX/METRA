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
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.Vector"%>
<%@ page import="java.text.*"%>

<html>
<%
  pg070803030CFG  clsConfig = new pg070803030CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070803030.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070803030.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg0702061.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Articulo|";    // modificar
  String cCveOrdenar  = "almsumart.icvearticulo|";  // modificar
  String cDscFiltrar  = "Articulo|";    // modificar
  String cCveFiltrar  = "almsumart.icvearticulo|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                 // modificar
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
  DecimalFormat df = new DecimalFormat("##,###,##0.00");
  String cValor = "";
  TFechas dtFecha = new TFechas();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
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
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="hdBoton" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr>
      <td valign="top">
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr><td colspan="5" class="ETablaT">Concentrado</td></tr>
<%
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Año:</td>");
                                    out.println("<td class=\"ETabla\">");
                                    out.println("<select name=\"iAnio\" size=\"1\" onchange=\"llenaSLT(2,this.value,'','',document.forms[0].iCvePeriodo);\">");
                                    out.print("<option value = \"-1\" selected>Seleccione...</option>");
                                     for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                             out.print("<option value = " + i + " selected>" + i + "</option>");
                                          else
                                             out.print("<option value = " + i + ">" + i + "</option>");
                                       }

                                    out.println("</select>");
                                    out.println("</td>");
                                    out.println("<td class=\"EEtiqueta\">Periodo:</td>");
                                    out.println("<td>");
                                    TDALMPeriodo dALMPeriodo = new TDALMPeriodo();
                                    TVALMPeriodo vALMPeriodo = new TVALMPeriodo();
                                    Vector vPeriodo = new Vector();
                                    if (request.getParameter("iAnio")!=null)
                                       vPeriodo = dALMPeriodo.FindByAll(" WHERE iAnio = " + request.getParameter("iAnio").toString() + " AND lActivo = 1 "," ORDER BY iCvePeriodo ");
                                    if (vPeriodo.size()>0)
                                       out.print(vEti.SelectOneRowSinTD("iCvePeriodo","",vPeriodo,"iCvePeriodo","cDscPeriodo",request,"0",true));
                                    else{
                                       out.println("<SELECT NAME=\"iCvePeriodo\" SIZE=\"1\">");
                                       out.println("</SELECT>");
                                    }
                                    out.println("</td>");
                                    out.println("</tr>");
%>

               <tr>
                 <td class="EEtiqueta">Tipo de Artículo:</td>
                 <td class="ETabla" colspan="3">
                    <%
                        TDALMTpoArticulo dALMTpoArticulo = new TDALMTpoArticulo();
                        TVALMTpoArticulo vALMTpoArticulo = new TVALMTpoArticulo();
                        Vector vTipoArticulo = new Vector();
                        vTipoArticulo = dALMTpoArticulo.FindByAll("","");
                        out.print(vEti.SelectOneRowSinTD("iCveTpoArticulo","llenaSLT(1,this.value,'','',document.forms[0].iCveFamilia);",vTipoArticulo,"iCveTpoArticulo","cDscBreve",request,"0",true));
                    %>
                 </td>
                </tr>
                <tr>
                 <td class="EEtiqueta">Familia:</td>
                 <td class="ETabla">
                   <%
                      if (request.getParameter("iCveFamilia")!=null){
                         TDALMFamilia dALMFamilia = new TDALMFamilia();
                         TVALMFamilia vALMFamilia = new TVALMFamilia();
                         Vector vFamilia = new Vector();
                         int iTpoArticulo = 0;
                         if (request.getParameter("iCveTpoArticulo")!=null && request.getParameter("iCveTpoArticulo").toString().compareTo("")!=0)
                            iTpoArticulo = new Integer(request.getParameter("iCveTpoArticulo")).intValue();
                         vFamilia = dALMFamilia.FindByAll("  where F.iCveTpoArticulo = " + iTpoArticulo," order by F.iCveTpoArticulo");
                         if (vFamilia.size()>0)
                            out.print(vEti.SelectOneRowSinTD("iCveFamilia","",vFamilia,"iCveFamilia","cDscBreve",request,request.getParameter("iCveFamilia"),true));
                         else{
                            out.print("<select name=\"iCveFamilia\" size=\"1\">");
                            out.print("</select>");
                         }
                      }else{
                          out.print("<select name=\"iCveFamilia\" size=\"1\">");
                          out.print("</select>");
                      }
                   %>
                 </td>
                 <td class="EEtiqueta">Programada:</td>
                 <td class="ETabla">
                   <% if (request.getParameter("lProgramada")!=null && request.getParameter("lProgramada").toString().compareTo("1")==0)
                         out.print("<input type=\"checkbox\" name=\"lProgramada\" value=\"1\" checked>");
                      else
                         out.print("<input type=\"checkbox\" name=\"lProgramada\" value=\"1\">");
                   %>
                 </td>
               </tr>
                <tr>
                 <td class="EEtiqueta">Núm. Solicitud:</td>
                 <td class="ETabla" colspan="3">
                   <% TDALMSolicSumin DSolic = new TDALMSolicSumin();
                      Vector vSolic = DSolic.FindByAllCustom(" WHERE S.lRevisada = 0 AND S.lAutorizada = 1 ", " ORDER BY S.iCveSolicSum");
                      if (vSolic==null)
                        vSolic = new Vector();
                      out.println("<select name=\"iCveSolicitud\" size=\"1\">");
                      if (request.getParameter("iCveSolicitud")==null || (request.getParameter("iCveSolicitud")!=null&&request.getParameter("iCveSolicitud").equals("0")))
                        out.println("<option selected value\"0\">Seleccione...</option>");
                      else
                        out.println("<option value\"0\">Seleccione...</option>");
                      if (vSolic != null && vSolic.size() > 0){
                        for (int i=0; i<vSolic.size();i++){
                          TVALMSolicSumin VSolic = (TVALMSolicSumin)vSolic.get(i);
                          if (request.getParameter("iCveSolicitud")!=null && request.getParameter("iCveSolicitud").equals(""+VSolic.getICveSolicSum()))
                            out.println("<option selected value\"" + VSolic.getICveSolicSum() + "\">" + VSolic.getICveSolicSum() + "</option>");
                          else
                            out.println("<option value\"" + VSolic.getICveSolicSum() + "\">" + VSolic.getICveSolicSum() + "</option>");
                        }
                      }
                      out.print("</select><small>(Solo aparecen solicitudes autorizadas no analizadas)</small>");
                   %>
                 </td>
               </tr>
            </table>
            &nbsp;
            <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
              <tr>
                <td class="ETablaT" colspan="2">Articulo</td>
                <td class="ETablaT">Existencias</td>
                <td class="ETablaT">Solicitadas</td>
                <td class="ETablaT">Analizadas</td>
                <td class="ETablaT" colspan="2">Diferencia</td>
              </tr>
              <%
                 float fDiferencia = 0;
                 if (bs != null){
                     bs.start();
                     while(bs.nextRow()){
                         out.println("<tr>");
                         out.print(vEti.Texto("ETablaR",""+ bs.getFieldValue("iCveArticulo", "&nbsp;")));
                         out.print(vEti.Texto("ETabla",""+ bs.getFieldValue("cDscArticulo", "&nbsp;")));

                         if (bs.getFieldValue("dExistencia")!=null && bs.getFieldValue("dExistencia").toString().compareTo("")!=0)
                            cValor = df.format(Double.parseDouble(bs.getFieldValue("dExistencia","0").toString()));
                         else
                            cValor = "&nbsp;";

                         out.print(vEti.Texto("ETablaR",cValor));

                         if (bs.getFieldValue("dUnidSolicita")!=null && bs.getFieldValue("dUnidSolicita").toString().compareTo("")!=0)
                            cValor = df.format(Double.parseDouble(bs.getFieldValue("dUnidSolicita","0").toString()));
                         else
                            cValor = "&nbsp;";

                         out.print(vEti.Texto("ETablaR",cValor));

                         if (bs.getFieldValue("dUnidAutor")!=null && bs.getFieldValue("dUnidAutor").toString().compareTo("")!=0)
                           if (bs.getFieldValue("lAnalizada") != null && bs.getFieldValue("lAnalizada").toString().equals("0"))
                             cValor = "&nbsp;";
                           else
                             cValor = df.format(Double.parseDouble(bs.getFieldValue("dUnidAutor","0").toString()));
                         else
                            cValor = "&nbsp;";

                         out.print(vEti.Texto("ETablaR",cValor));

                         fDiferencia = new Double(bs.getFieldValue("dExistencia").toString()).floatValue() -
                                       new Double(bs.getFieldValue("dUnidSolicita").toString()).floatValue();

                         cValor = df.format(Double.parseDouble(Double.toString(fDiferencia)));

                         out.print(vEti.Texto("ETablaR",cValor));
                         out.print("<td class=\"ECampo\">");
                         out.print(vEti.clsAnclaTexto("EAncla","Analizar","JavaScript:fIrCatalogo('" + bs.getFieldValue("iCveArticulo", "0") + "'," + "'pg070803031');","Autorizar"));
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


