<%/**
 *
 * Title: pg070101063.jsp
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
  pg070101063CFG  clsConfig = new pg070101063CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070101063.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070101063.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cClave    = "";
  String cClave2    = "";
  String cClave3    = "";
  String cClave4    = "";
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
 TDMEDSintoma dMEDSintoma = new TDMEDSintoma(); 

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\Archivos\funciones\pg070101063.js"></SCRIPT>-->
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
  <input type="hidden" name="hdChosen" value="<% if (request.getParameter("hdChosen") != null) out.print(request.getParameter("hdChosen"));%>">
  <%
   if (bs != null){ // Agregar tanto Hds como variables dependiendo del campo llave.
       cPosicion = Integer.toString(bs.rowNo());
       cClave  = ""+bs.getFieldValue("iCveSintoma", "");
       cClave2  = ""+bs.getFieldValue("iCveServicio", "");
       cClave3  = ""+bs.getFieldValue("iCveRama", "");
       cClave4  = ""+bs.getFieldValue("iCveTpoResp", "");
       cPropiedad = ""+bs.getFieldValue("cPropiedad", "");
     }

 %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdSintoma" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdSintoma")); else out.print(cClave);%>">
  <input type="hidden" name="hdServicio" value="<%if(cClave2.compareTo("")==0) out.print(request.getParameter("iCveServicio")); else out.print(cClave2);%>">
  <input type="hidden" name="hdServicio2" value="<%if(cClave2.compareTo("")==0) out.print(request.getParameter("iCveServicio")); else out.print(cClave2);%>">
  <input type="hidden" name="larama" value="<%if(cClave3.compareTo("")==0) out.print(request.getParameter("iCveRama")); else out.print(cClave3);%>">
  <input type="hidden" name="hdRama" value="<%if(cClave3.compareTo("")==0) out.print(request.getParameter("iCveRama")); else out.print(cClave3);%>">
  
  <input type="hidden" name="hdTpoResp" value="<%if(cClave4.compareTo("")==0) out.print(request.getParameter("hdTpoResp")); else out.print(cClave4);%>">

  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdCPropiedad" value="<%=cPropiedad%>">
  <input type="hidden" name="lLogico" value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("lLogica")); else out.print(cClave);%>">
  <input type="hidden" name="iCveTpoResp" value="<%if(cClave4.compareTo("")==0) out.print(request.getParameter("iCveTpoResp")); else out.print(cClave4);%>">

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
                                
                                //System.out.println("respuesta = "+request.getParameter("hdTpoResp"));
                                
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
                                   
                                   String Breve = "";
                                   try{
                                       Breve = dMEDSintoma.Sentencia(" SELECT CDSCBREVE FROM MEDSINTOMAS WHERE ICVESERVICIO = "+ request.getParameter("iCveServicio")
                                                             + " AND ICVERAMA = "+request.getParameter("iCveRama")
                                                             + "  AND ICVESINTOMA = "+request.getParameter("iCveSintoma"));
                                   }catch(Exception ex){
                                       Breve = "";
                                   }
                                   
                                   String Pregunta = "";
                                   try{
                                       Pregunta = dMEDSintoma.Sentencia(" SELECT CPREGUNTA FROM MEDSINTOMAS WHERE ICVESERVICIO = "+ request.getParameter("iCveServicio")
                                                             + " AND ICVERAMA = "+request.getParameter("iCveRama")
                                                             + "  AND ICVESINTOMA = "+request.getParameter("iCveSintoma"));
                                   }catch(Exception ex){
                                       Pregunta = "";
                                   }

                                   
                                    out.println("</tr>");
                                    out.println("<td class=\"EEtiqueta\">Descripción Breve:</td>");
                                    out.println("<td class=\"ECampo\" colspan=\"4\">"+Breve+"</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Pregunta:</td>");
                                    out.println("<td class=\"ECampo\" colspan=\"4\">"+Pregunta+"</td>");
                                    out.println("</tr>");

                                    
                                   if(request.getParameter("hdTpoResp").trim().equals("8") || request.getParameter("hdTpoResp").trim().equals("13") ){
                                            out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","Cargar Respuestas:"));
                                            %>
                                            <td colspan="3">
                                            <table border="0" align="left" cellspacing="0">
                                            <tr>
                                            <%
                                    }
                                    if(request.getParameter("hdTpoResp").trim().equals("9")){
                                            out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","Ingrese Imagen 1 y 2:<br>Imagen 1(Imagen en miniatura)<br>Imagen 2(Imagen a proyectar)"));
                                            %>
                                            <td colspan="3">
                                            <table border="0" align="left" cellspacing="0">
                                            <tr>
                                            <%
                                    }
                                    if(request.getParameter("hdTpoResp").trim().equals("1")){
                                        %>
                                            <input type="hidden" name="iCveSintoma" 
                                                   value="<%if(cClave.compareTo("")==0) out.print(request.getParameter("hdSintoma")); else out.print(cClave);%>">
                                        <%  out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","El valor inicial de respuesta es:"));
                                        %>
                                            <td class="ECampo"  colspan="4" >Si
                            <%
                                            String cDisable = "";
                                            String cChecked = "";
                                            out.println("<input type=\"radio\" name=\"lLogico2\" value=\"1\" " +cDisable+ ">");
                                            out.println("No");
                                            out.println("<input type=\"radio\" name=\"lLogico2\" value=\"0\" " +cDisable+ ">");
                                            out.println("</td>");
                                            out.println("</tr>");
                                            /*                                           
                                            out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","¿Se especificara la respuesta?:"));
                                            String cDisable2 = "";
                                            String cChecked2 = "";
                                            out.println("<td class=\"ECampo\" colspan=3>");
                                            out.printl n("<input type=\"checkbox\" name=\"lEspecifica\"  " + cDisable2 + " " +  cChecked2+ ">");
                                            out.println("</td>");*/

                           %>
                                       </tr>
                                       </table>
                                       </td>
                                    <%
                                    }
                                
                                    String cDisable3 = "";
                                    String cChecked3 = "";

                                    TDMEDTpoResp dTpoResp = new TDMEDTpoResp();
                                    TVMEDTpoResp vTpoResp = new TVMEDTpoResp();
                                    Vector vTipoResp = new Vector();
                                    vTipoResp = dTpoResp.FindByAll();
                                    
                                    
                                    TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
                                    TVMEDRespSint vMEDRespSint = new TVMEDRespSint();
                                    Vector vRespSint = new Vector();
                                    /*String cWhere = " icveservicio = "+ request.getParameter("iCveServicio")+ " and "+
                                                    " icverama = " + request.getParameter("iCveRama")+ " and "+
                                                    " icvesintoma = " + request.getParameter("hdSintoma");
                                    */
                                    
                                    String cWhere = " icveservicio = "+ request.getParameter("iCveServicio")+ " and "+
                                                    " icverama = " + request.getParameter("iCveRama")+ " and ";
                                                    if(request.getParameter("iCveSintoma") != null)
                                                        cWhere = cWhere +" icvesintoma = " + request.getParameter("iCveSintoma");
                                                    else{
                                                        if(request.getParameter("iCveSintoma") != null)
                                                                cWhere = cWhere +" icvesintoma = " + request.getParameter("hdSintoma");
                                                        else
                                                                cWhere = cWhere +" icvesintoma = 0";                           
                                                    }

                                    try{
                                         vRespSint = dMEDRespSint.FindByAll(cWhere);
                                    }catch(Exception ex) {
                                         vRespSint = new Vector();
                                    }
                                    

                               if(request.getParameter("hdTpoResp").trim().equals("8") || request.getParameter("hdTpoResp").trim().equals("13") ){
                                   out.println("<tr>");
                          %>
                                    <p><select name="list" size="10" multiple>
                         <%
                                     if (vRespSint.size() > 0){
                                       for (int i = 0; i < vRespSint.size(); i++){
                                          vMEDRespSint = (TVMEDRespSint)vRespSint.get(i);
                                          %>
                                          <option><%=vMEDRespSint.getCDescripcion()%></option>
                                        <%                                         
                                          }
                                       }
                                    %>
                                     </select> </p>
                                              <p><input type="button" value="Añadir" onClick="listbox_selectall('list', true);add();">
                                         </p>
                                          <%
                                   out.println("</tr>");
                                }
                                if(request.getParameter("hdTpoResp").trim().equals("9")){
                                   out.println("<tr>");
                          %>
                                    <p><select name="list" size="10" multiple>
                         <%
                                     if (vRespSint.size() > 0){
                                       for (int i = 0; i < vRespSint.size(); i++){
                                          vMEDRespSint = (TVMEDRespSint)vRespSint.get(i);
                                          %>
                                          <option><%=vMEDRespSint.getCDescripcion()%></option>
                                        <%                                         
                                          }
                                       }
                                    %>
                                     </select> </p>
                                              <p><input type="button" value="Añadir" onClick="listbox_selectall2('list', true);add();">
                                         </p>
                                          <%
                                   out.println("</tr>");
                                }
                                    

                                    %>
                                    </tr>
                                    </table>
                                    </td>
                                    <%
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
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Descripción Breve:","ECampo","text",75,150,4,"cDscBreve", bs.getFieldValue("cDscBreve","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Pregunta:","ECampo","text",75,150,4,"cPregunta", bs.getFieldValue("cPregunta","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    
                                    if(request.getParameter("hdTpoResp").trim().equals("8") || request.getParameter("hdTpoResp").trim().equals("13") ){
                                            out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","Respuestas en el Combo Box:"));
                                            %>
                                            <td colspan="3">
                                            <table border="0" align="left" cellspacing="0">
                                            <tr>
                                            <%
                                    }
                                    
                                    if(request.getParameter("hdTpoResp").trim().equals("9")){
                                            out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","Ingrese Imagen 1 y 2:<br>Imagen 1(Imagen en miniatura)<br>Imagen 2(Imagen a proyectar)"));
                                            %>
                                            <td colspan="3">
                                            <table border="0" align="left" cellspacing="0">
                                            <tr>
                                            <%
                                    }
                                    
                                    
                                    String cDisable3 = "";
                                    String cChecked3 = "";

                                    TDMEDTpoResp dTpoResp = new TDMEDTpoResp();
                                    TVMEDTpoResp vTpoResp = new TVMEDTpoResp();
                                    Vector vTipoResp = new Vector();
                                    vTipoResp = dTpoResp.FindByAll();
                                    
                                    TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
                                    TVMEDRespSint vMEDRespSint = new TVMEDRespSint();
                                    Vector vRespSint = new Vector();
                                    String cWhere = " icveservicio = "+ request.getParameter("iCveServicio")+ " and "+
                                                    " icverama = " + request.getParameter("iCveRama")+ " and ";
                                                    if(request.getParameter("hdSintoma") != null)
                                                        cWhere = cWhere +" icvesintoma = " + request.getParameter("hdSintoma");
                                                    else{
                                                        if(request.getParameter("hdSintoma") != null)
                                                                cWhere = cWhere +" icvesintoma = " + request.getParameter("hdSintoma");
                                                        else
                                                                cWhere = cWhere +" icvesintoma = 0";                           
                                                    }
                                    
                                    
                                    try{
                                           vRespSint = dMEDRespSint.FindByAll(cWhere);
                                    }catch(Exception ex) {
                                            vRespSint = new Vector();
                                    }
                                    
                                    if (!lCaptura)
                                       cDisable3 = "disabled";
                                    

                               if (request.getParameter("hdTpoResp").trim().equals("8") || request.getParameter("hdTpoResp").trim().equals("13") ) {
                                   if(request.getParameter("hdBoton").compareTo("Modificar")==0) {
                                        //out.println("<tr>");
                                        //out.println("<td class=\"ECampo\" colspan=\"4\">");
                                        //out.println("<p><select name=\"list\" size=\"10\" multiple>");
                                        out.println("<input type='hidden' name='hdSizeDatos' value='"+vRespSint.size()+"'>");
                                            if (vRespSint.size() > 0){
                                             
                                            for (int i = 0; i < vRespSint.size(); i++){
                                                vMEDRespSint = (TVMEDRespSint)vRespSint.get(i);
                                                    //out.println("<option value=\""+vMEDRespSint.getIOrden()+"\">");
                                                    //out.println(vMEDRespSint.getCDescripcion());
                                                out.println("<tr>");
                                                out.println("<input type='hidden' name='cOrdenRef" + i + "' value='"+vMEDRespSint.getIOrden()+"'>");
                                                out.println("<td colspan='3'><input type='text' name='cValRef" + i + "' size=55 maxlength=50 value='" + vMEDRespSint.getCDescripcion() + "'></td>");
                                                out.println("</tr>");

                                            }
                                            }
                                        out.println("<tr><td>Para eliminar solo deje el campo en blanco</td></tr>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                   } else {
                                        out.println("<tr>");
                                        out.println("<td class=\"ECampo\" colspan=\"4\">");
                                        out.println("<p><select  disabled=\"disabled\" name=\"list\" size=\"10\" multiple>");

                                            if (vRespSint.size() > 0){
                                            for (int i = 0; i < vRespSint.size(); i++){
                                                vMEDRespSint = (TVMEDRespSint)vRespSint.get(i);
                                                /*if (i>0){
                                                        out.println("<tr>");
                                                    }
                                                    out.println("<td class=\"ECampo\" colspan=\"4\">");
                                                    <p><select name="list" size="10" multiple>*/
                                                    out.println("<option value=\""+vMEDRespSint.getIOrden()+"\">");
                                                    out.println(vMEDRespSint.getCDescripcion());

                                            }
                                            }else{
                                                out.println("<option value=\"0\"></option>");
                                            }
                                        out.println("</select>");
                                        out.println("</td>");
                                        out.println("</tr>");
                                   }                                   
                                  
                                             
                                   }//Final de Tipo respuesta 8
                                   
                                if(request.getParameter("hdTpoResp").trim().equals("9")){
                                  out.println("<tr>");
                                  out.println("<td class=\"ECampo\" colspan=\"4\">");
                                  out.println("<p><select  disabled=\"disabled\" name=\"list\" size=\"10\" multiple>");
                                    
                                    if (vRespSint.size() > 0){
                                       for (int i = 0; i < vRespSint.size(); i++){
                                          vMEDRespSint = (TVMEDRespSint)vRespSint.get(i);
                                           /*if (i>0){
                                                out.println("<tr>");
                                             }
                                             out.println("<td class=\"ECampo\" colspan=\"4\">");
                                             <p><select name="list" size="10" multiple>*/
                                             out.println("<option value=\""+vMEDRespSint.getIOrden()+"\">");
                                             out.println(vMEDRespSint.getCDescripcion());
                                             
                                       }
                                    }else{
                                          out.println("<option value=\"0\"></option>");
                                    }
                                   out.println("</select>");
                                   out.println("</td>");
                                   out.println("</tr>");
                                             
                                   }//Final de Tipo respuesta 9
                                    
                                    
                                    
                                    
                               if(request.getParameter("hdTpoResp").trim().equals("1")){
                                   if (vRespSint.size() > 0){
                                       for (int i = 0; i < vRespSint.size(); i++){
                                          vMEDRespSint = (TVMEDRespSint)vRespSint.get(i);
                                           if (i>0){
                                                out.println("<tr>");
                                             }
                                      
                                           out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","El valor inicial de respuesta es:"));
                                            %>
                                            <td colspan="3">
                                            <table border="0" align="left" cellspacing="0">
                                            <tr>
                                            <%
                                            String cDisable = "";
                                            String cChecked = "";
                                            if (!lCaptura)
                                               cDisable = "disabled";
                                            out.println(vEti.Texto("EEtiqueta","Si"));
                                            if (vMEDRespSint.getILogica()==1){
                                               out.println("<td>");
                                               out.println("<input type=\"radio\" name=\"lLogico2\" value=\"1\" " +cDisable+ " checked>");
                                               out.println("</td>");
                                            }else{
                                               out.println("<td>");
                                               out.println("<input type=\"radio\" name=\"lLogico2\" value=\"1\" " +cDisable+ ">");
                                               out.println("</td>");
                                            }
                                            out.println(vEti.Texto("EEtiqueta","No"));
                                            if (vMEDRespSint.getILogica()==0){
                                               out.println("<td>");
                                               out.println("<input type=\"radio\" name=\"lLogico2\" value=\"0\" " +cDisable+ " checked>");
                                               out.println("</td>");
                                            }else{
                                               out.println("<td>");
                                               out.println("<input type=\"radio\" name=\"lLogico2\" value=\"0\" " +cDisable+ ">");
                                               out.println("</td>");
                                            } 
                                            %>
                                            </tr>
                                            </table>
                                            </td>
                                            <%
                                            out.println("</tr>");
                                            /*
                                            out.println("<tr>");
                                            out.println(vEti.Texto("EEtiqueta","¿Se especificara la respuesta?:"));
                                            String cDisable2 = "";
                                            String cChecked2 = "";
                                            if (!lCaptura)
                                               cDisable2 = "disabled";
                                            if (vMEDRespSint.getCDescripcion().toString().compareToIgnoreCase("0")!=0)
                                                cChecked2 = "checked";
                                            out.println("<td colspan=3>");
                                            out.println("<input type=\"checkbox\" name=\"lEspecifica\" value=\"1\" " + cDisable2 + " " +  cChecked2+ ">");
                                            out.println("</td>");
                                            out.println("</tr>");
                                            */
                                            
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
