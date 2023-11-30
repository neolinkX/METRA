<%/**
 * Title: pg070104001A.jsp
 * Description:
 * Copyright:
 * Company:
 * @author Ivan Santiago Méndez
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  pg070104001ACFG  clsConfig = new pg070104001ACFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104001A.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104001A.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104001A.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar

  String cEstatusIR = "";            // modificar
  if(request.getParameter("hdBoton").compareTo("Guardar")==0)
    cEstatusIR   = "Imprimir";            // modificar
  else
    cEstatusIR   = "Reporte";            // modificar

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
  String cUpdStatus  = "SaveCancelOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  /*
   * Calcula Fecha Actual
   */
  java.util.Date today = new java.util.Date();
  java.sql.Date defFecha = new java.sql.Date(today.getTime());
  java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
  String dFechaActual = "";
  TFechas dtFecha = new TFechas();
  dFechaActual = dtFecha.getFechaDDMMYYYY(defaultFecha,"/");
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070104001.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
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
  <input type="hidden" name="hdType" value="">
  <input type="hidden" name="lIniciado" value="1">
  <input type='hidden' name='hdBuscado' value='0'>

<%  if (request.getParameter("hdICvePersonal") != null){
%>
       <input type="hidden" name="hdICvePersonal" value="<%=request.getParameter("hdICvePersonal").toString()%>">
<%
    }else{
%>
        <input type="hidden" name="hdICvePersonal" value="">
<%
    }
%>
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
       TVPERDatos vPerDatos = null;
       if (request.getParameter("hdType") != null){
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
             vPerDatos=clsConfig.findUser();
          }else if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
             vPerDatos=clsConfig.findExpediente();
          }
       }


       if (request.getParameter("hdType") != null){
          if (request.getParameter("hdType").toString().equalsIgnoreCase("P")){
%>
             <input type="hidden" name="iNumExamen" value="<%=request.getParameter("iNumExamen").toString()%>">
<%
          }else if (request.getParameter("hdType").toString().equalsIgnoreCase("E")){
             if(vPerDatos!=null){
%>
                <input type="hidden" name="iNumExamen" value="<%=vPerDatos.getINumExamen()%>">
<%
             }else{
%>
                <input type="hidden" name="iNumExamen" value="">
<%
             }
          }else{
%>
                <input type="hidden" name="iNumExamen" value="">
<%
          }
       }else{
%>
         <input type="hidden" name="iNumExamen" value="">
<%
       }
%>

  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
        <tr><td align="center" colspan="4" class="EResalta">Vale de Servicios</td></tr>
                            <tr>
                              <td colspan="5" class="ETablaT">Inicio del Examen
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Unidad Médica:</td>
                              <td class="ETabla">
                                  <%  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                      Vector vProceso = new Vector();
                                      vProceso = vUsuario.getVUnidades();
                                      TVGRLUMUsuario vGRLUMUsu;
                                      if (request.getParameter("iCveUniMed") != null){
                                         out.print(vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",vUsuario.getVUniNoDup(),"iCveUniMed","cDscUniMed",request,request.getParameter("iCveUniMed").toString(), true)) ;
                                      }else{
                                         out.print(vEti.SelectOneRowSinTD("iCveUniMed","fillNext();",vUsuario.getVUniNoDup(),"iCveUniMed","cDscUniMed",request,"0", true));
                                      }
                                  %>
                              </td>
                              <td class="EEtiqueta">M&oacute;dulo:</td>
                              <td class="ETabla"><%
                                        TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();
                                        TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                                        Vector vGModulo = new Vector();
                                        String cFiltro = "";
                                        if (request.getParameter("iCveUniMed") != null){
                                          cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                                    " and GRLUsuMedicos.iCveUniMed =  " + request.getParameter("iCveUniMed") +
                                                    " and GRLUsuMedicos.iCveProceso = " + vParametros.getPropEspecifica("EPIProceso")  ;
                                        }else{
                                          cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                                    " and GRLUsuMedicos.iCveUniMed =  0 " +
                                                    " and GRLUsuMedicos.iCveProceso = " + vParametros.getPropEspecifica("EPIProceso")  ;
                                        }
                                        vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                                     %>
                                    <select name="iCveModulo" size="1" onChange="">
                                     <%
                                    if (vGModulo.size() > 0){
                                     out.print("<option value=0>Seleccione...</option>");
                                     for (int x = 0;x<vGModulo.size(); x++){
                                          vGRLUSUMedicos = (TVGRLUSUMedicos) vGModulo.get(x);
                                          if (request.getParameter("iCveModulo")!=null&&request.getParameter("iCveModulo").compareToIgnoreCase(new Integer(vGRLUSUMedicos.getICveModulo()).toString())==0){
                                             out.print("<option value =" + vGRLUSUMedicos.getICveModulo() + " Selected>" + vGRLUSUMedicos.getCDscModulo() + "</option>");
                                          }else{
                                             out.print("<option value =" + vGRLUSUMedicos.getICveModulo() + ">" + vGRLUSUMedicos.getCDscModulo() + "</option>");
                                          }
                                       }
                                    }else{
                                       out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                    }
                                    %>
                                 </select>
                              </td>
                              </tr>
                              <tr>
                              <td class="EEtiqueta">Proceso:</td>
                              <td class="ETabla" colspan = "3">
                                 <select name="iCveProceso" size="1" onChange="">
                                    <%
                                      int iCveUniMed = 0;
                                      if (request.getParameter("iCveUniMed") != null){
                                         iCveUniMed = Integer.parseInt(request.getParameter("iCveUniMed").toString());
                                      }else{
                                         iCveUniMed = Integer.parseInt(vParametros.getPropEspecifica("EPIProceso"));
                                      }
                                      if(vProceso.size() >0 ){
                                        out.print("<option value=0>Seleccione...</option>");
                                        for (int w = 0;w<vProceso.size(); w++){
                                           vGRLUMUsu = (TVGRLUMUsuario) vProceso.get(w);
                                           if (vGRLUMUsu.getICveUniMed() == iCveUniMed){
                                              if (request.getParameter("iCveProceso")!=null&&request.getParameter("iCveProceso").compareToIgnoreCase(new Integer(vGRLUMUsu.getICveProceso()).toString())==0){
                                                 out.print("<option value =" + vGRLUMUsu.getICveProceso() + " Selected>" + vGRLUMUsu.getCDscProceso() + "</option>");
                                             }else{
                                                 out.print("<option value =" + vGRLUMUsu.getICveProceso() + ">" + vGRLUMUsu.getCDscProceso() + "</option>");
                                             }
                                           }
                                         }
                                      }else{
                                         out.print("<option value=0>No Existen Datos Relacionados...</option>");
                                      }
                                    %>
                                 </select>
                              </td>
                              </tr>
                              <%
                                 out.print("<tr>");
                                 out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha:", "ECampo", "text", 10, 10,3,"dtSolicitado", dFechaActual, 0, "", "fValFecha(this.value);", false, true,true, request));
                                 out.print("</tr>");
                                 if(vPerDatos!=null){
                                    out.print("<tr>");
                                    out.print("<td class=\"EEtiqueta\">Expediente:</td>");
                                    out.print("<td>");
                                    out.print("<Input Type=\"Text\" Value=\""+vPerDatos.getICveExpediente()+"\" Name=\"iCveExpediente\" Size=\"10\" MaxLength=\"10\">");
                                    out.print("</td>");
                                 }else{
                                    out.print("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Expediente:", "ECampo", "text", 10, 10,"iCveExpediente", "", 0, "", "", false, true,true, request));
                                 }
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Expediente","JavaScript:fSelExp();", "Buscar Expediente",""));
                                 out.print("</td>");
                                 out.print("<td>");
                                 out.print(vEti.clsAnclaTexto("EAncla","Buscar Personal","JavaScript:fSelPer(1);", "Buscar Personal",""));
                                 out.print("</td>");
                                 out.print("</tr>");
                              %>
                          </table>&nbsp;
                            <%
                        if(vPerDatos!=null){
                          if(vPerDatos.getCNombre() !=null){
                        %>
                        <script language>
                          form = document.forms[0];
                          form.hdBuscado.value = 1;
                        </script>

                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr>
                              <td colspan="2" class="ETablaT">Personal Registrado para el Examen
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Expediente:</td>
                              <td class="ECampo"><%=vPerDatos.getICveExpediente()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">R.F.C.:</td>
                              <td class="ECampo"><%=vPerDatos.getCRFC()%></td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta">Nombre:</td>
                              <td class="ECampo"><%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%></td>
                              <input type="hidden" name="cGenero" value="<%=vPerDatos.getCSexo()%>">
                            </tr>
                            <tr>

                            <input type="hidden" name="hdNombre" value="<%=vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()%>">
                            <input type="hidden" name="hdNoExpediente" value="<%=vPerDatos.getICveExpediente()%>">
                            <input type="hidden" name="hdRFC" value="<%=vPerDatos.getCRFC()%>">

                            <%
                              out.print(vEti.EtiCampoCS("EEtiqueta","Encuesta de Salida:","ECampo","text",10,10,3,"iFolioEs","",0,"","fMayus(this);",false,true,true));
                            %>
                            </tr>
                          </table>
                        <%
                          }else{
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
                        }else{
//******************************************************************************
                          if(request.getParameter("hdBoton").compareTo("Guardar")==0){
                            %>
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                   out.print("<tr>");
                                     out.print("<td colspan=\"3\" class=\"ETablaT\">Datos del Personal</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Expediente:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdNoExpediente") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>Nombre:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdNombre") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='EEtiqueta'>R.F.C.:</td>");
                                     out.print("<td colspan='2' class='ECampo'>" + request.getParameter("hdRFC") + "</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='ETablaT'>Modo Trans.</td>");
                                     out.print("<td class='ETablaT'>Categoría</td>");
                                     out.print("<td class='ETablaT'>Motivo</td>");
                                   out.print("</tr>");

                                   TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
                                   TVEXPExamCat vEXPExamCat = new TVEXPExamCat();
                                   Vector vcEXPExamCat = new Vector();

                                   vcEXPExamCat = dEXPExamCat.FindBy(request.getParameter("hdNoExpediente"), request.getParameter("iNumExamen"));
                                   if (vcEXPExamCat.size() > 0)
                                     for (int i = 0; i < vcEXPExamCat.size(); i++){
                                       vEXPExamCat = (TVEXPExamCat)vcEXPExamCat.get(i);
                                       out.print("<tr>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscMdoTrans() + "</td>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscCategoria() + "</td>");
                                         out.print("<td class='ECampo'>" + vEXPExamCat.getCDscMotivo() + "</td>");
                                       out.print("</tr>");
                                     }

                            %>
                                </table>
                            <%

                            %>
                                &nbsp;
                                <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                            <%
                                   out.print("<tr>");
                                     out.print("<td colspan=\"5\" class=\"ETablaT\">Servicios</td>");
                                   out.print("</tr>");

                                   out.print("<tr>");
                                     out.print("<td class='ETablaT'>Aplicado</td>");
                                     out.print("<td class='ETablaT'>Servicio</td>");
                                     out.print("<td class='ETablaT'>Hora de Entrada</td>");
                                     out.print("<td class='ETablaT'>Hora de Salida</td>");
                                     out.print("<td width='200'class='ETablaT'>Firma</td>");
                                   out.print("</tr>");

                                   TDEXPServicio dEXPServicio = new TDEXPServicio();
                                   TVEXPServicio vEXPServicio = new TVEXPServicio();
                                   Vector vcEXPServicio = new Vector();

                                   vcEXPServicio = dEXPServicio.FindBy(request.getParameter("hdNoExpediente"), request.getParameter("iNumExamen"));
                                   if (vcEXPServicio.size() > 0)
                                     for (int i = 0; i < vcEXPServicio.size(); i++){
                                       vEXPServicio = (TVEXPServicio)vcEXPServicio.get(i);
                                       out.print("<tr>");
                                         out.print("<td class='ETablaC'><input type='checkbox'></td>");
                                         out.print("<td class='ECampo'>" + vEXPServicio.getCDscServicio() + "</td>");
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                         out.print("<td class='ECampo'>&nbsp;</td>");
                                       out.print("</tr>");
                                     }


                            %>
                                </table>
                            <%
                          }
//******************************************************************************
                          if (request.getParameter("hdType") != null && request.getParameter("hdType").length()>0){
                             // System.out.println("hdType2 ==>>"+request.getParameter("hdType"));
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
