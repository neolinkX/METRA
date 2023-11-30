<%/**
 * Title: Investigación de Accidentes
 * Description: Asignación de Unidad Médica al Accidente
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1.0
 * Clase:pg070402020CFG
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
  pg070402020CFG  clsConfig = new pg070402020CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070402020.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070402020.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "No Disponible|";    // modificar
  String cCveOrdenar  = "No Disponible|";  // modificar
  String cDscFiltrar  = "No Disponible|";    // modificar
  String cCveFiltrar  = "No Disponible|";  // modificar
  String cTipoFiltrar = "8|";                // modificar

  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus = "";
  if (clsConfig.getUpdStatus().compareTo("AddOnly") == 0)
     cUpdStatus  = "SaveOnly";
  else
     cUpdStatus  = clsConfig.getUpdStatus();


  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">

  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
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
       //cClave2  = "" + bs.getFieldValue("iCveEnvio", "");
       //cPropiedad = "" + bs.getFieldValue(" ", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">



  <input type="hidden" name="hdLCondicion" value="<%=request.getParameter("hdLCondicion")%>">
  <input type="hidden" name="hdLOrdenar" value="<%=request.getParameter("hdLOrdenar")%>">
  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=request.getParameter("iCveMdoTransSel")%>">
  <input type="hidden" name="iCveUniMedSel" value="<%=request.getParameter("iCveUniMedSel")%>">


  <input type="hidden" name="hdiAnio" value="<%=request.getParameter("hdiAnio")%>">
  <input type="hidden" name="hdiCveMdoTrans" value="<%=request.getParameter("hdiCveMdoTrans")%>">
  <input type="hidden" name="hdiIdefMedPrev" value="<%=request.getParameter("hdiIdefMedPrev")%>">



  <!--input type="hidden" name="hdOPPbaRapida" value="<%=cPropiedad%>" -->
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr><%
                                   TCreaBoton bBtn = new TCreaBoton();
                                   TEtiCampo vEti = new TEtiCampo();
                                   boolean lCaptura = clsConfig.getCaptura();
                                   boolean lNuevo = clsConfig.getNuevo();
                                   TFechas dtFechas = new TFechas();
                                   String cBoton = "";
                                   int iPAnio = 0;
                                   int iPUniMed = 1;
                                   int iEnvio = 0;

                                    TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                    out.println("<input type='hidden' name='hdUsuario' value='"+ vUsuario.getICveusuario()+ "'>");

                                   int iProcINV = Integer.parseInt(vParametros.getPropEspecifica("INVProceso"));
                                   out.println("<input type='hidden' name='hdINVProceso' value='"+ iProcINV +"'>");
                                   int iProcTOX = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                   out.println("<input type='hidden' name='hdTOXProceso' value='"+ iProcTOX +"'>");
                                   int iProcEPI = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
                                   out.println("<input type='hidden' name='hdEPIProceso' value='"+ iProcEPI +"'>");
                                   int iProcFallecido = Integer.parseInt(vParametros.getPropEspecifica("INVFallecido"));
                                   out.println("<input type='hidden' name='hdFallecido' value='"+ iProcFallecido +"'>");
                               %>
                              <td colspan="9" class="ETablaT">Asignar Investigación a la Unidad Médica
                              </td>
                              </tr>
                              <tr>
                              </tr>
                            <%
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampo("EEtiqueta","Año:","ECampo","text",10,10,"iAnio",request.getParameter("hdiAnio"),0,"","fMayus(this);",false,true,false));
                                     TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
                                     TVGRLMdoTrans vMdoTrans;
                                     Vector vcMdoTrans = new Vector();
                                     vcMdoTrans = dMdoTrans.findByAll("where iCveMdoTrans = " + request.getParameter("hdiCveMdoTrans") + " ");
                                     if (vcMdoTrans.size() > 0){
                                        vMdoTrans = (TVGRLMdoTrans) vcMdoTrans.get(0);
                                        out.print(vEti.EtiCampoCS("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,3,"iCveMdoTrans",vMdoTrans.getCDscMdoTrans(),0,"","fMayus(this);",false,true,false));
                                     }
                                     out.println("</td>");
                                     out.println("</tr>");
                                     out.println("<tr>");
                                     out.print(vEti.EtiCampoCS("EEtiqueta","Identificador de Medicina Preventiva:","ECampo","text",10,10,5,"identificador",request.getParameter("hdiIdefMedPrev"),0,"","fMayus(this);",false,true,false));
                                     out.println("</tr>");

                            // Tabla de Captura

                             if (bs != null){ // Modificar de acuerdo al catálogo específico
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampo("EEtiqueta","Unidad Médica:","ECampo","text",10,10,"iCveUniMed", bs.getFieldValue("cDscUniMed","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                 out.println("<input type='hidden' name='hdiCveUniMed' value='"+ bs.getFieldValue("iCveUniMed","&nbsp;").toString() +"'>");
                                 out.println("<input type='hidden' name='hdcDscUniMed' value='"+ bs.getFieldValue("cDscUniMed","&nbsp;").toString() +"'>");
                                 out.print(vEti.EtiCampo("EEtiqueta","Módulo:","ECampo","text",10,10,"iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                 out.print(vEti.EtiCampo("EEtiqueta","Fecha de Asignación:","ECampo","text",10,10,"dtAsigna", bs.getFieldValue("cDscDtAsigna","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print("<td colspan='6' align='center'>");
                                 out.print(vEti.clsAnclaTexto("EEtiqueta","Equipo de Investigación","javascript:fIrPagina();","Buscar"));
                                 out.print("</td>");
                                 out.println("</tr>");

                               }
                               else{
                                 TDGRLUniMed dUniMed = new TDGRLUniMed();
                                 TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();

                                 out.println("<tr>");
                                 out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                 out.println("<td class='ECampo' >");
                                 out.println(vEti.SelectOneRowSinTD("iCveUniMed","llenaSLT(4,this.value,'','',document.forms[0].iCveModulo);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                 out.println("</td>");
                                 out.println(vEti.Texto("EEtiqueta","Módulo:"));
                                 out.println("<td class='ECampo'>");
                                 out.println("<SELECT NAME='iCveModulo' SIZE='1' ");
                                 out.println("</SELECT>");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta","Fecha de Asignación:","ECampo","text",10,10,3,"dtAsigna", "",0,"","fMayus(this);",false,true,true));
                                 out.println("</tr>");

                             }
                            %>

                          </table><% // Fin de Datos %>

  </td></tr>
  <%}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<%= vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>