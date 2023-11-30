<%/**
 *
 * Title: pg070101061.jsp
 * Description: Catalogo de configuración de la rama
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version 1.0
 * Clase:   
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.micper.util.*"%>
<html>
<%
  pg070101061CFG  clsConfig = new pg070101061CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101061.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101061.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cClave2    = "";
  String cPosicion  = "";
  String cPropiedad = "";

  int    iFiltro   = 0;
  TError      vErrores      = new TError();
  StringBuffer  sbErroresAcum = new StringBuffer();
  TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave Sintoma|Pregunta|Genero(M/F/A)|";    // modificar
  String cCveOrdenar  = "iCveSintoma|cPregunta|cGenero|";  // modificar
  String cDscFiltrar  = "Pregunta|Genero(M/F/A)|";    // modificar
  String cCveFiltrar  = "cPregunta|cGenero|";  // modificar
  String cTipoFiltrar = "8|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();
  int tpresp =0;

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();

  String cNavStatus  = "";
//  if (bs != null){
//     cNavStatus = "FirstRecord";
//  }else{
     cNavStatus = clsConfig.getNavStatus();
//  }

  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
 Vector vMedSrv = new Vector();
 TDMEDServicio dMEDServicio = new TDMEDServicio();
 vMedSrv = dMEDServicio.FindByAll("Where lActivo=1");
 TVMEDServicio vMEDServicio = new TVMEDServicio();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg070101061.js"></SCRIPT>-->
<script language="JavaScript">
  // Todas las funciones salvo fOnLoad una vez probadas y terminadas deberán ser copiadas
  //  al directorio funciones de wwwrooting en un archivo con extensión js (vg. pg0702061.js)
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
       cClave  = ""+bs.getFieldValue("iCveSintoma", "");
       cClave2  = ""+bs.getFieldValue("iCveServicio", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdSintoma" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdSintoma")); else out.print(cClave);%>">
  <input type="hidden" name="hdTpoResp" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("iCveTpoResp")); else out.print(cClave);%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
  </td><td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                              <tr>
                              <td colspan="4" class="ETablaT">Configuración de la Rama
                              </td>
                            </tr>
                            <%
                                TEtiCampo vEti = new TEtiCampo();
                                boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();
                                
                                
                                if (lNuevo){
                                   out.println("<tr>");
                                   out.println(vEti.Texto("EEtiqueta","Servicio:"));
                                   TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                   TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                   out.println("<td class='ECampo' >");
                                   out.println(vEti.SelectOneRowSinTD("iCveServicio","llenaSLT(1,this.value,'','',document.forms[0].iCveRama);",vMedSrv,"iCveServicio", "cDscServicio", request, "0", true));
                                   out.println("</td>");
                                   out.println(vEti.Texto("EEtiqueta","Rama:"));

                                   if (request.getParameter("iCveRama") == null){
                                      out.println("<td class='ECampo'>");
                                      out.println("<SELECT NAME='iCveRama' SIZE='1' OnChange='fillBusca();'");
                                      out.println("</SELECT>");
                                      out.println("</td>");
                                      out.println("</tr>");
                                   }
                                   else{
                                      TDMEDRama dRama = new TDMEDRama();
                                      out.println("<td>");
                                      out.println(vEti.SelectOneRowSinTD("iCveRama","",dRama.FindByAll(" Where iCveServicio =" + request.getParameter("iCveServicio")),"iCveRama", "cDscRama", request, "0", true));
                                      out.println("</td>");
                                      out.println("</tr>");
                                   }

                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Descripción Breve:","ECampo","text",50,250,4,"cDscBreve", "",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Pregunta:","ECampo","text",50,250,4,"cPregunta", "",0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Genero:"));
                                    %>
                                    <td colspan="3">
                                    <table border="0" align="left" cellspacing="0">
                                    <tr>
                                    <%
                                    String cDisable = "";
                                    String cChecked = "";
                                    out.println("<td>");
                                    out.println("<input type=\"radio\" name=\"cGenero\" value=\"A\" " +cDisable+ ">");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Indistinto"));
                                    out.println("<td>");
                                    out.println("<input type=\"radio\" name=\"cGenero\" value=\"F\" " +cDisable+ ">");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Femenino"));
                                    out.println("<td>");
                                    out.println("<input type=\"radio\" name=\"cGenero\" value=\"M\" " +cDisable+ ">");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Masculino"));
                                    %>
                                    </tr>
                                    </table>
                                    </td>
                                    <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Resultado del Estudio:"));
                                    String cDisable2 = "";
                                    String cChecked2 = "";
                                    out.println("<td colspan=3>");
                                    out.println("<input type=\"checkbox\" name=\"lEstudio\" value=\"1\" " + cDisable2 + " " +  cChecked2+ ">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Tipo de Respuesta:"));
                                    %>
                                    <td colspan="3">
                                    <table border="0" align="left" cellspacing="0">
                                    <tr>
                                    <%
                                    String cDisable3 = "";
                                    String cChecked3 = "";

                                    TDMEDTpoResp dTpoResp = new TDMEDTpoResp();
                                    TVMEDTpoResp vTpoResp = new TVMEDTpoResp();
                                    Vector vTipoResp = new Vector();
                                    vTipoResp = dTpoResp.FindByAll();
                                    if (vTipoResp.size() > 0){
                                       for (int i = 0; i < vTipoResp.size(); i++){
                                          vTpoResp = (TVMEDTpoResp)vTipoResp.get(i);
                                          if (i>0){
                                             out.println("<tr>");
                                          }
                                          out.println("<td>");
                                          out.println("<input type=\"radio\" name=\"iCveTpoResp\" value=\""+vTpoResp.getICveTpoResp()+"\" " +cDisable+ ">");
                                          out.println("</td>");
                                          out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                                          out.println(vTpoResp.getCDscTpoResp());
                                          out.println("</td>");
                                          if (i>0){
                                             out.println("</tr>");
                                          }
                                       }
                                    }

                                    %>
                                    </tr>
                                    </table>
                                    </td>
                                    <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Etiqueta:","ECampo","text",50,50,4,"cEtiqueta", "",0,"","",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Contesta el Personal:"));
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lCPersonal\" value=\"1\">");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Respuesta Obligatoria:"));
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lObligatorio\" value=\"1\">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Evaluacion Automatica:"));
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lEvalAuto\" value=\"1\">");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Activo:"));
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" checked>");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta","Valor de Referencia:"));
                                      out.println("<td colspan='3'><input type='text' name='cValRef' size=55 maxlength=50></td>");
                                      out.println("</tr>");
                                    }

                                }else {

                                     if (bs != null){

                                     if (lCaptura){
                                     %>
                                        <input type="hidden" name="iCveServicio" value="<%=bs.getFieldValue("iCveServicio","&nbsp;").toString()%>">
                                        <input type="hidden" name="iCveRama" value="<%=bs.getFieldValue("iCveRama","").toString()%>">
                                        <input type="hidden" name="iCveSintoma" value="<%=bs.getFieldValue("iCveSintoma","").toString()%>">
                                    <%
                                        out.println("<tr>");
                                        out.print(vEti.EtiCampo("EEtiqueta","Servicio:","ECampo","text",10,10,"cDscServicio",bs.getFieldValue("cDscServicio","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.print(vEti.EtiCampo("EEtiqueta","Rama:","ECampo","text",10,10,"iCveRama", bs.getFieldValue("cDscRama","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                        out.println("</tr>");
                                     }else{
                                           out.println("<tr>");
                                           out.println(vEti.Texto("EEtiqueta","Servicio:"));
                                           TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                           TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                           out.println("<td class='ECampo' >");
                                           out.println(vEti.SelectOneRowSinTD("iCveServicio","llenaSLT(1,this.value,'','',document.forms[0].iCveRama);",vMedSrv,"iCveServicio", "cDscServicio", request, "0", true));
                                           out.println("</td>");
                                           out.println(vEti.Texto("EEtiqueta","Rama:"));

                                           if (request.getParameter("iCveRama") == null){
                                              out.println("<td class='ECampo'>");
                                              out.println("<SELECT NAME='iCveRama' SIZE='1' OnChange='fillBusca();'");
                                              out.println("</SELECT>");
                                              out.println("</td>");
                                              out.println("</tr>");
                                           }
                                           else{
                                              TDMEDRama dRama = new TDMEDRama();
                                              out.println("<td>");
                                              out.println(vEti.SelectOneRowSinTD("iCveRama","fillBusca();",dRama.FindByAll(" Where iCveServicio =" + request.getParameter("iCveServicio")),"iCveRama", "cDscRama", request, "0", true));
                                              out.println("</td>");
                                              out.println("</tr>");
                                           }
                                    }


                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Clave del Síntoma:","ECampo","text",10,10,4,"iCveSintoma", bs.getFieldValue("iCveSintoma","&nbsp;").toString(),0,"","fMayus(this);",false,true,false));
                                    %>
                                    <input type="hidden" name="iCveSintoma" value="<%=bs.getFieldValue("iCveSintoma","").toString()%>">
                                    <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Descripción Breve:","ECampo","text",75,300,4,"cDscBreve", bs.getFieldValue("cDscBreve","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Pregunta:","ECampo","text",75,300,4,"cPregunta", bs.getFieldValue("cPregunta","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Sexo:"));
                                    %>
                                    <td colspan="3">
                                    <table border="0" align="left" cellspacing="0">
                                    <tr>
                                    <%
                                    String cDisable = "";
                                    String cChecked = "";
                                    if (!lCaptura)
                                       cDisable = "disabled";
                                    if (bs.getFieldValue("cGenero","").toString().compareToIgnoreCase("A")==0){
                                       out.println("<td>");
                                       out.println("<input type=\"radio\" name=\"cGenero\" value=\"A\" " +cDisable+ " checked>");
                                       out.println("</td>");
                                    }else{
                                       out.println("<td>");
                                       out.println("<input type=\"radio\" name=\"cGenero\" value=\"A\" " +cDisable+ ">");
                                       out.println("</td>");
                                    }
                                    out.println(vEti.Texto("EEtiqueta","Indistinto"));
                                    if (bs.getFieldValue("cGenero","").toString().compareToIgnoreCase("F")==0){
                                       out.println("<td>");
                                       out.println("<input type=\"radio\" name=\"cGenero\" value=\"F\" " +cDisable+ " checked>");
                                       out.println("</td>");
                                    }else{
                                       out.println("<td>");
                                       out.println("<input type=\"radio\" name=\"cGenero\" value=\"F\" " +cDisable+ ">");
                                       out.println("</td>");
                                    }
                                    out.println(vEti.Texto("EEtiqueta","Femenino"));
                                    if (bs.getFieldValue("cGenero","").toString().compareToIgnoreCase("M")==0){
                                       out.println("<td>");
                                       out.println("<input type=\"radio\" name=\"cGenero\" value=\"M\" " +cDisable+ " checked>");
                                       out.println("</td>");
                                    }else{
                                       out.println("<td>");
                                       out.println("<input type=\"radio\" name=\"cGenero\" value=\"M\" " +cDisable+ ">");
                                       out.println("</td>");
                                    }
                                    out.println(vEti.Texto("EEtiqueta","Masculino"));
                                    %>
                                    </tr>
                                    </table>
                                    </td>
                                    <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Resultado del Estudio:"));
                                    String cDisable2 = "";
                                    String cChecked2 = "";
                                    if (!lCaptura)
                                       cDisable2 = "disabled";
                                    if (bs.getFieldValue("lEstudio","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked2 = "checked";
                                    out.println("<td colspan=3>");
                                    out.println("<input type=\"checkbox\" name=\"lEstudio\" value=\"1\" " + cDisable2 + " " +  cChecked2+ ">");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Tipo de Respuesta:"));
                                    %>
                                    <td colspan="3">
                                    <table border="0" align="left" cellspacing="0">
                                    <tr>
                                    <%
                                    String cDisable3 = "";
                                    String cChecked3 = "";
                                    

                                    TDMEDTpoResp dTpoResp = new TDMEDTpoResp();
                                    TVMEDTpoResp vTpoResp = new TVMEDTpoResp();
                                    Vector vTipoResp = new Vector();
                                    vTipoResp = dTpoResp.FindByAll();
                                    if (!lCaptura)
                                       cDisable3 = "disabled";
                                    if (vTipoResp.size() > 0){
                                       for (int i = 0; i < vTipoResp.size(); i++){
                                          vTpoResp = (TVMEDTpoResp)vTipoResp.get(i);
                                          if (Integer.parseInt(bs.getFieldValue("iCveTpoResp","0").toString()) == vTpoResp.getICveTpoResp()){
                                             if (i>0){
                                                out.println("<tr>");
                                             }
                                             out.println("<td>");
                                             out.println("<input type=\"radio\" name=\"iCveTpoResp\" value=\""+vTpoResp.getICveTpoResp()+"\" " +cDisable+ " checked>");
                                             out.println("</td>");
                                             out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                                             out.println(vTpoResp.getCDscTpoResp());
                                             out.println("</td>");
                                             tpresp = vTpoResp.getICveTpoResp();
                                             if (i>0){
                                                out.println("</tr>");
                                             }
                                          }else{
                                             if (i>0){
                                                out.println("<tr>");
                                             }
                                             out.println("<td>");
                                             out.println("<input type=\"radio\" name=\"iCveTpoResp\" value=\""+vTpoResp.getICveTpoResp()+"\" " +cDisable+ ">");
                                             out.println("</td>");
                                             out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                                             out.println(vTpoResp.getCDscTpoResp());
                                             out.println("</td>");
                                             if (i>0){
                                                out.println("</tr>");
                                             }

                                          }
                                       }
                                    }

                                    %>
                                    </tr>
                                    </table>
                                    </td>
                                    <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Etiqueta:","ECampo","text",50,50,4,"cEtiqueta", bs.getFieldValue("cEtiqueta","&nbsp;").toString(),0,"","",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");

                                    //out.print(vEti.EtiCampo("EEtiqueta","Contesta el Personal:","ECampo","text",10,10,"lCPersonal", bs.getFieldValue("lCPersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println(vEti.Texto("EEtiqueta","Contesta el Personal:"));
                                    String cDisable4 = "";
                                    String cChecked4 = "";
                                    if (!lCaptura)
                                       cDisable4 = "disabled";
                                    if (bs.getFieldValue("lCPersonal","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked4 = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lCPersonal\" value=\"1\" " + cDisable4 + " " +  cChecked4+ ">");
                                    out.println("</td>");
                                    //out.print(vEti.EtiCampo("EEtiqueta","Respuesta Obligatoria:","ECampo","text",10,10,"lObligatorio", bs.getFieldValue("lObligatorio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println(vEti.Texto("EEtiqueta","Respuesta Obligatoria:"));
                                    String cDisable5 = "";
                                    String cChecked5 = "";
                                    if (!lCaptura)
                                       cDisable5 = "disabled";
                                    if (bs.getFieldValue("lObligatorio","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked5 = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lObligatorio\" value=\"1\" " + cDisable5 + " " +  cChecked5+ ">");
                                    out.println("</td>");

                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Evaluación Automática:"));
                                    String cDisable6 = "";
                                    String cChecked6 = "";
                                    if (!lCaptura)
                                       cDisable6 = "disabled";
                                    if (bs.getFieldValue("lEvalAuto","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked6 = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lEvalAuto\" value=\"1\" " + cDisable6 + " " +  cChecked6+ ">");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Activo:"));
                                    String cDisable7 = "";
                                    String cChecked7 = "";
                                    if (!lCaptura)
                                       cDisable7 = "disabled";
                                    if (bs.getFieldValue("lActivo","0").toString().compareToIgnoreCase("0")!=0)
                                        cChecked7 = "checked";
                                    out.println("<td>");
                                    out.println("<input type=\"checkbox\" name=\"lActivo\" value=\"1\" " + cDisable7 + " " +  cChecked7+ ">");
                                    out.println("</td>");
                                    out.println("</tr>");

                                    if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                      out.println("<tr>");
                                      out.println(vEti.Texto("EEtiqueta","Valor de Referencia:"));
                                      if(request.getParameter("hdBoton").compareTo("Modificar")==0)
                                        out.println("<td colspan='3'><input type='text' name='cValRef' size=55 maxlength=50 value='" + bs.getFieldValue("cValRef", "&nbsp;") + "'></td>");
                                      else
                                        out.println("<td colspan='3' class='ETabla'>" + bs.getFieldValue("cValRef", "&nbsp;") + "</td>");
                                      out.println("</tr>");
                                    }
                                    
                                           
                                }else{
                                    %>
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
                                                if (request.getParameter("iCveServicio") != null){
                                                   if (Integer.parseInt((String)request.getParameter("iCveServicio")) == vMEDServicio.getICveServicio()){
                                                      out.print("<option value =" + vMEDServicio.getICveServicio() + " selected>" + vMEDServicio.getCDscServicio() + "</option>");
                                                   }else{
                                                      out.print("<option value =" + vMEDServicio.getICveServicio() + " >" + vMEDServicio.getCDscServicio() + "</option>");
                                                   }
                                                }else{
                                                   out.print("<option value =" + vMEDServicio.getICveServicio() + " >" + vMEDServicio.getCDscServicio() + "</option>");
                                                }
                                            }
                                    %>
                                         </select>
                                    </td>
                                    <td class="EEtiqueta">Rama:</td>
                                    <td class="ETabla">
                                    <%
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
                                        <select name="iCveRama" size="1" OnChange="fillBusca();">
                                    <%
                                        out.print("<option value=0 selected>Seleccione...</option>");
                                        if (vMEDr.size() > 0){
                                           for (int x = 0;x<vMEDr.size(); x++){
                                               vMEDRama = (TVMEDRama) vMEDr.get(x);
                                               if (request.getParameter("iCveRama")!= null){
                                                  if (Integer.parseInt((String)request.getParameter("iCveRama")) == vMEDRama.getICveRama()){
                                                     out.print("<option value =" + vMEDRama.getICveRama() + " selected>" + vMEDRama.getCDscRama() + "</option>");
                                                  }else{
                                                     out.print("<option value =" + vMEDRama.getICveRama() + " >" + vMEDRama.getCDscRama() + "</option>");
                                                  }
                                               }else{
                                                  out.print("<option value =" + vMEDRama.getICveRama() + " >" + vMEDRama.getCDscRama() + "</option>");
                                               }
                                           }
                                        }
                                    %>
                                        </select>
                                        </td>
                                        </tr>
                                    <%
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 3,"Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
                                   out.println("</tr>");
                                 }
                                }
                            %>
                          </table><% // Fin de Datos 
                          
                            if (bs != null){
                            %>
                          
                          <br>
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                              <tr>
                                <td colspan="4" class="ETablaT">Propiedades adicionales al síntoma
                                </td>
                                </tr>
                              <%
                                if (tpresp == 8 || tpresp == 13 ){
                                           out.println("<tr>");
                                           out.print("<td align=\"left\" class=\"EEtiquetaL\">");
                                           out.print(vEti.clsAnclaTexto("EEtiquetaC","Configurar respuestas del ComboBox","JavaScript:fIrCatalogo('" +bs.getFieldValue("iCveServicio","") + "','" +bs.getFieldValue("iCveRama","") + "','" +bs.getFieldValue("iCveSintoma","") + "');",""));
                                           out.print("</td>");
                                           out.println("</tr>");
                                       }

                                if (tpresp == 1){
                                           out.println("<tr>"); 
                                           out.print("<td align=\"left\" class=\"EEtiquetaL\">");
                                           out.print(vEti.clsAnclaTexto("EEtiquetaC","Configurar respuesta Lógica","JavaScript:fIrCatalogo('" +bs.getFieldValue("iCveServicio","") + "','" +bs.getFieldValue("iCveRama","") + "','" +bs.getFieldValue("iCveSintoma","") + "');",""));
                                           out.print("</td>");
                                           out.println("</tr>");
                                       }
                                
                                if (tpresp == 9){
                                           out.println("<tr>");
                                           out.print("<td align=\"left\" class=\"EEtiquetaL\">");
                                           out.print(vEti.clsAnclaTexto("EEtiquetaC","Configurar respuesta de Imagen","JavaScript:fIrCatalogo('" +bs.getFieldValue("iCveServicio","") + "','" +bs.getFieldValue("iCveRama","") + "','" +bs.getFieldValue("iCveSintoma","") + "');",""));
                                           out.print("</td>");
                                           out.println("</tr>");
                                 }else{
                                                                
                                           out.println("<tr>"); 
                                           out.print("<td align=\"left\" class=\"EEtiquetaL\">");
                                           out.print(vEti.clsAnclaTexto("EEtiquetaC","Configurar Reglas de Negocio","JavaScript:fIrReglaNegocio('" +bs.getFieldValue("iCveSintoma","") + "');",""));
                                           out.print("</td>");
                                           out.println("</tr>");
                                           
                                           if (tpresp == 3 ||tpresp == 5){
	                                           out.println("<tr>");                                  
	                                           out.print("<td align=\"left\" class=\"EEtiquetaL\">");
	                                           out.print(vEti.clsAnclaTexto("EEtiquetaC","Configurar Alertas","JavaScript:fIrConfAlert('" +bs.getFieldValue("iCveSintoma","") + "');",""));
	                                           out.print("</td>");
	                                           out.println("</tr>");
                                           }
                                           
                                           out.println("<tr>"); 
                                           out.print("<td align=\"left\" class=\"EEtiquetaL\">");
                                           out.print(vEti.clsAnclaTexto("EEtiquetaC","Configurar como pregunta dependiente","JavaScript:fIrConfDep('" +bs.getFieldValue("iCveSintoma","") + "');",""));
                                           out.print("</td>");
                                           out.println("</tr>");
                                     }    
                                                                         
%>
                          </table><%}%>
                          
                          
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
