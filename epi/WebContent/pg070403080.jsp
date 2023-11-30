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
<%@ page import="com.micper.util.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070403080CFG  clsConfig = new pg070403080CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070403080.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070403080.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  //String cCatalogo    = "pg070501071.jsp";       // modificar
  //String cDetalle    = "pg070501071.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Descripción|";    // modificar
  String cCveOrdenar  = "cDscUniMed|";  // modificar
  String cDscFiltrar  = "Descripción|";    // modificar
  String cCveFiltrar  = "cDscUniMed|";  // modificar
  String cTipoFiltrar = "8|";                // modificar
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
  String cClave2    = "";
  String cPosicion = "";
  int iv18=0,iv19=0,iv20=0,iv21=0,iv22=0,iv23=0,iv24=0,iv25=0,iv26=0,iv27=0,iv28=0,iv29=0,iv30=0;
  int iv31=0,iv32=0,iv33=0,iv34=0,iv35=0,iv36=0,iv37=0,iv38=0,iv39=0,iv40=0;
  int iv41=0,iv42=0,iv43=0,iv44=0,iv45=0,iv46=0,iv47=0,iv48=0,iv49=0,iv50=0;
  int iv51=0,iv52=0,iv53=0,iv54=0,iv55=0,iv56=0,iv57=0,iv58=0,iv59=0,iv60=0,ivMayor60=0;
  int ivSeIgnora=0;
  int ivTot18=0,ivTot19=0,ivTot20=0,ivTot21=0,ivTot22=0,ivTot23=0,ivTot24=0,ivTot25=0,ivTot26=0,ivTot27=0,ivTot28=0,ivTot29=0,ivTot30=0;
  int ivTot31=0,ivTot32=0,ivTot33=0,ivTot34=0,ivTot35=0,ivTot36=0,ivTot37=0,ivTot38=0,ivTot39=0,ivTot40=0;
  int ivTot41=0,ivTot42=0,ivTot43=0,ivTot44=0,ivTot45=0,ivTot46=0,ivTot47=0,ivTot48=0,ivTot49=0,ivTot50=0;
  int ivTot51=0,ivTot52=0,ivTot53=0,ivTot54=0,ivTot55=0,ivTot56=0,ivTot57=0,ivTot58=0,ivTot59=0,ivTot60=0,ivTotMayor60=0;
  int ivTotSeIgnora=0;
  int ivTotUniMed = 0,ivTotGeneral = 0;
  Vector vAccidentes = new Vector();
  Vector vMdoTransp = new Vector();

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
  <input type="hidden" name="hdCampoClave1" value="">
  <input type="hidden" name="hdCampoClave2" value="">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <tr>
                              <td colspan="5" class="ETablaT">Filtros para el Reporte</td>
                            </tr>
                               <% out.println("<tr>");
                                  out.println("<td class=\"ETablaTR\">Año:</td>");
                                  out.println("<td class=\"ETabla\">");
                                  out.println("<select name=\"iAnio\">");
                                  out.println("<option value = \"-1\" selected>Seleccione...</option>");
                                  for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                    if (request.getParameter("iAnio")!=null&&request.getParameter("iAnio").compareToIgnoreCase(new Integer(i).toString())==0)
                                      out.println("<option value = " + i + " selected>" + i + "</option>");
                                    else
                                      out.println("<option value = " + i + ">" + i + "</option>");
                                  }
                                  out.println("</select>");
                                  out.println("</td>");
                                  out.println("</tr>");
                               %>
                          </table >
      </td>
      </tr>
      <tr><td>&nbsp;</td></tr>
      <tr>
      <td valign="top">&nbsp;</td>
      <td>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="47" class="ETablaT">Accidentes Registrados</td>
                            </tr>
                               <%
                               if(request.getParameter("hdBoton").toString().compareTo("Enviar")==0 ) {
                                 out.println(clsConfig.getActiveX());
                               }
                              // modificar según listado
                               if (bs != null){
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\"></td>");
                                   out.print("<td class=\"ETablaT\" colspan=\"46\">Edad del Personal</td>");
                                   out.print("</tr>");
                                   out.print("<tr>");
                                   out.print("<td class=\"ETablaT\">Modo de Transporte</td>");
                                   out.print("<td class=\"ETablaT\">18</td>");
                                   out.print("<td class=\"ETablaT\">19</td>");
                                   out.print("<td class=\"ETablaT\">20</td>");
                                   out.print("<td class=\"ETablaT\">21</td>");
                                   out.print("<td class=\"ETablaT\">22</td>");
                                   out.print("<td class=\"ETablaT\">23</td>");
                                   out.print("<td class=\"ETablaT\">24</td>");
                                   out.print("<td class=\"ETablaT\">25</td>");
                                   out.print("<td class=\"ETablaT\">26</td>");
                                   out.print("<td class=\"ETablaT\">27</td>");
                                   out.print("<td class=\"ETablaT\">28</td>");
                                   out.print("<td class=\"ETablaT\">29</td>");
                                   out.print("<td class=\"ETablaT\">30</td>");
                                   out.print("<td class=\"ETablaT\">31</td>");
                                   out.print("<td class=\"ETablaT\">32</td>");
                                   out.print("<td class=\"ETablaT\">33</td>");
                                   out.print("<td class=\"ETablaT\">34</td>");
                                   out.print("<td class=\"ETablaT\">35</td>");
                                   out.print("<td class=\"ETablaT\">36</td>");
                                   out.print("<td class=\"ETablaT\">37</td>");
                                   out.print("<td class=\"ETablaT\">38</td>");
                                   out.print("<td class=\"ETablaT\">39</td>");
                                   out.print("<td class=\"ETablaT\">40</td>");
                                   out.print("<td class=\"ETablaT\">41</td>");
                                   out.print("<td class=\"ETablaT\">42</td>");
                                   out.print("<td class=\"ETablaT\">43</td>");
                                   out.print("<td class=\"ETablaT\">44</td>");
                                   out.print("<td class=\"ETablaT\">45</td>");
                                   out.print("<td class=\"ETablaT\">46</td>");
                                   out.print("<td class=\"ETablaT\">47</td>");
                                   out.print("<td class=\"ETablaT\">48</td>");
                                   out.print("<td class=\"ETablaT\">49</td>");
                                   out.print("<td class=\"ETablaT\">50</td>");
                                   out.print("<td class=\"ETablaT\">51</td>");
                                   out.print("<td class=\"ETablaT\">52</td>");
                                   out.print("<td class=\"ETablaT\">53</td>");
                                   out.print("<td class=\"ETablaT\">54</td>");
                                   out.print("<td class=\"ETablaT\">55</td>");
                                   out.print("<td class=\"ETablaT\">56</td>");
                                   out.print("<td class=\"ETablaT\">57</td>");
                                   out.print("<td class=\"ETablaT\">58</td>");
                                   out.print("<td class=\"ETablaT\">59</td>");
                                   out.print("<td class=\"ETablaT\">60</td>");
                                   out.print("<td class=\"ETablaT\">Mayor 60</td>");
                                   out.print("<td class=\"ETablaT\">Se Ignora</td>");
                                   out.print("<td class=\"ETablaT\">Total</td>");
                                   out.print("</tr>");
                                   //Obtención de los Valores de la Base de Datos
                                   if (request.getParameter("iAnio") != null)
                                       vAccidentes = clsConfig.getValores(new Integer(request.getParameter("iAnio").toString()).intValue());

                                   bs.start();
                                   while(bs.nextRow()){
                                     iv18 = 0;iv19 = 0;
                                     iv20 = 0;iv21 = 0;iv22 = 0;iv23 = 0;iv24 = 0;iv25 = 0;iv26 = 0;iv27 = 0;iv28 = 0;iv29 = 0;
                                     iv30 = 0;iv31 = 0;iv32 = 0;iv33 = 0;iv34 = 0;iv35 = 0;iv36 = 0;iv37 = 0;iv38 = 0;iv39 = 0;
                                     iv40 = 0;iv41 = 0;iv42 = 0;iv43 = 0;iv44 = 0;iv45 = 0;iv46 = 0;iv47 = 0;iv48 = 0;iv49 = 0;
                                     iv50 = 0;iv51 = 0;iv52 = 0;iv53 = 0;iv54 = 0;iv55 = 0;iv56 = 0;iv57 = 0;iv58 = 0;iv59 = 0;
                                     iv60 = 0;
                                     if(!vAccidentes.isEmpty()){
                                       for(int i=0;i<vAccidentes.size();i++){
                                         TVINVRegistro VINVRegistro = new TVINVRegistro();
                                         VINVRegistro = (TVINVRegistro) vAccidentes.get(i);
                                         if (VINVRegistro.getICveMdoTrans() == new Integer(bs.getFieldValue("iCveMdoTrans", "").toString()).intValue()){
                                            if(VINVRegistro.getIIDDGPMPT() == 18)
                                              iv18 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 19)
                                              iv19 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 20)
                                              iv20 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 21)
                                              iv21 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 22)
                                              iv22 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 23)
                                              iv23 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 24)
                                              iv24 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 25)
                                              iv25 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 26)
                                              iv26 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 27)
                                              iv27 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 28)
                                              iv28 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 29)
                                              iv29 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 30)
                                              iv30 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 31)
                                              iv31 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 32)
                                              iv32 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 33)
                                              iv33 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 34)
                                              iv34 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 35)
                                              iv35 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 36)
                                              iv36 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 37)
                                              iv37 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 38)
                                              iv38 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 39)
                                              iv39 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 40)
                                              iv40 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 41)
                                              iv41 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 42)
                                              iv42 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 43)
                                              iv43 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 44)
                                              iv44 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 45)
                                              iv45 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 46)
                                              iv46 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 47)
                                              iv47= VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 48)
                                              iv48 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 49)
                                              iv49 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 50)
                                              iv50 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 51)
                                              iv51 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 52)
                                              iv52 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 53)
                                              iv53 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 54)
                                              iv54 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 55)
                                              iv55 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 56)
                                              iv56 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 57)
                                              iv57 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 58)
                                              iv58 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 59)
                                              iv59 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() == 60)
                                              iv60 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.
                                            if(VINVRegistro.getIIDDGPMPT() > 60)
                                              ivMayor60 = VINVRegistro.getICveMotivo(); // Valor count que envia la BD.

                                         }
                                       }
                                     }
                                     out.print("<tr>");
                                     out.print(vEti.Texto("ETabla", bs.getFieldValue("cDscMdoTrans", "&nbsp;").toString()));
                                     out.print(vEti.Texto("ECampoR", ""+ iv18)); // Enero
                                     out.print(vEti.Texto("ECampoR", ""+ iv19)); // Febrero
                                     out.print(vEti.Texto("ECampoR", ""+ iv20)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv21)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv22)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv23)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv24)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv25)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv26)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv27)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv28)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv29)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv30)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv31)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv32)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv33)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv34)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv35)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv36)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv37)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv38)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv39)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv40)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv41)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv42)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv43)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv44)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv45)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv46)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv47)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv48)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv49)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv50)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv51)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv52)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv53)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv54)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv55)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ iv56)); // Abril
                                     out.print(vEti.Texto("ECampoR", ""+ iv57)); // Mayo
                                     out.print(vEti.Texto("ECampoR", ""+ iv58)); // Junio
                                     out.print(vEti.Texto("ECampoR", ""+ iv59)); // Julio
                                     out.print(vEti.Texto("ECampoR", ""+ iv60)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ ivMayor60)); // Marzo
                                     out.print(vEti.Texto("ECampoR", ""+ ivSeIgnora)); // Marzo
                                     ivTotUniMed = iv18 + iv19 +
                                                   iv20 + iv21 + iv22 + iv23 + iv24 + iv25 + iv26 + iv27 + iv28 + iv29 +
                                                   iv30 + iv31 + iv32 + iv33 + iv34 + iv35 + iv36 + iv37 + iv38 + iv39 +
                                                   iv40 + iv41 + iv42 + iv43 + iv44 + iv45 + iv46 + iv47 + iv48 + iv49 +
                                                   iv50 + iv51 + iv52 + iv53 + iv54 + iv55 + iv56 + iv57 + iv58 + iv59 +
                                                   iv60 + ivMayor60   + ivSeIgnora;
                                     out.print(vEti.Texto("ECampoR", ""+ ivTotUniMed)); // Total
                                     out.print("</tr>");
                                     ivTot18   = ivTot18 + iv18;
                                     ivTot19 = ivTot19 + iv19;
                                     ivTot20   = ivTot20 + iv20;
                                     ivTot21   = ivTot21 + iv21;
                                     ivTot22    = ivTot22  + iv22;
                                     ivTot23   = ivTot23 + iv23;
                                     ivTot24   = ivTot24 + iv24;
                                     ivTot25   = ivTot25 + iv25;
                                     ivTot26   = ivTot26 + iv26;
                                     ivTot27   = ivTot27 + iv27;
                                     ivTot28   = ivTot28 + iv28;
                                     ivTot29   = ivTot29 + iv29;
                                     ivTot30   = ivTot30 + iv30;
                                     ivTot31   = ivTot31 + iv31;
                                     ivTot32   = ivTot32 + iv32;
                                     ivTot33   = ivTot33 + iv33;
                                     ivTot34   = ivTot34 + iv34;
                                     ivTot35   = ivTot35 + iv35;
                                     ivTot36   = ivTot36 + iv36;
                                     ivTot37   = ivTot37 + iv37;
                                     ivTot38   = ivTot38 + iv38;
                                     ivTot39   = ivTot39 + iv39;
                                     ivTot40   = ivTot40 + iv40;
                                     ivTot41   = ivTot41 + iv41;
                                     ivTot42   = ivTot42 + iv42;
                                     ivTot43   = ivTot43 + iv43;
                                     ivTot44   = ivTot44 + iv44;
                                     ivTot45   = ivTot45 + iv45;
                                     ivTot46   = ivTot46 + iv46;
                                     ivTot47   = ivTot47 + iv47;
                                     ivTot48   = ivTot48 + iv48;
                                     ivTot49   = ivTot49 + iv49;
                                     ivTot50   = ivTot50 + iv50;
                                     ivTot51   = ivTot51 + iv51;
                                     ivTot52   = ivTot52 + iv52;
                                     ivTot53   = ivTot53 + iv53;
                                     ivTot54   = ivTot54 + iv54;
                                     ivTot55   = ivTot55 + iv55;
                                     ivTot56   = ivTot56 + iv56;
                                     ivTot57   = ivTot57 + iv57;
                                     ivTot58   = ivTot58 + iv58;
                                     ivTot59   = ivTot59 + iv59;
                                     ivTot60   = ivTot60 + iv60;
                                     ivTotMayor60  = ivTotMayor60  + ivMayor60;
                                     ivTotSeIgnora = ivTotSeIgnora + ivSeIgnora;
                                   }
                                   out.print("<tr>");
                                   out.print(vEti.Texto("ETablaR", "T O T A L"));
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot18)); // Enero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot19)); // Febrero
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot20)); // Marzo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot21)); // Abril
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot22)); // Mayo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot23)); // Junio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot24)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot25)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot26)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot27)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot28)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot29)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot30)); // Marzo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot31)); // Abril
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot32)); // Mayo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot33)); // Junio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot34)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot35)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot36)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot37)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot38)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot39)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot40)); // Marzo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot41)); // Abril
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot42)); // Mayo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot43)); // Junio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot44)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot45)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot46)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot47)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot48)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot49)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot50)); // Marzo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot51)); // Abril
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot52)); // Mayo
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot53)); // Junio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot54)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot55)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot56)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot57)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot58)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot59)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTot60)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotMayor60)); // Julio
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotSeIgnora)); // Julio
                                   ivTotGeneral = ivTot18 + ivTot19 + ivTot20 + ivTot21 + ivTot22 + ivTot23 + ivTot24 + ivTot25 + ivTot26 + ivTot27 + ivTot28 + ivTot29 + ivTot30
                                                                              + ivTot31 + ivTot32 + ivTot33 + ivTot34 + ivTot35 + ivTot36 + ivTot37 + ivTot38 + ivTot39 + ivTot40
                                                                              + ivTot41 + ivTot42 + ivTot43 + ivTot44 + ivTot45 + ivTot46 + ivTot47 + ivTot48 + ivTot49 + ivTot50
                                                                              + ivTot51 + ivTot52 + ivTot53 + ivTot54 + ivTot55 + ivTot56 + ivTot57 + ivTot58 + ivTot59 + ivTot60
                                                                              + ivTotMayor60 + ivTotSeIgnora;
                                   out.print(vEti.Texto("ECampoR", ""+ ivTotGeneral )); // Total
                                   out.print("</tr>");
                                   out.println("<tr>");
                                   out.println("<td class=\"ETablaT\" colspan=\"47\">Envio de Información a Excel</td>");
                                   out.println("</tr>");
                                   //cSQL = clsConfig.cValSQL;
                                   //request.getSession(true).setAttribute("cRepSQL",cSQL);
                                   out.print("<td class=\"ECampoC\" colspan=\"47\">");
                                   out.print(vEti.clsAnclaTexto("EAncla","Enviar a Excel","JavaScript:fEnviarExcel('','','','','');",""));
                                   out.print("</td>");
                                   out.println("</tr>");
                               }
                               else{
                                 out.println("<tr>");
                                 out.print("<td class='ETablaC' colspan='47'>No existen datos coincidentes con el filtro proporcionado.</td>");
                                 out.println("</tr>");
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
