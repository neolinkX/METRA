<%/**
 * Title: Formato Federal de Control y Cadena de Custodia para Análisis de Drogas EPI
 * Description: Formato Federal de Control y Cadena de Custodia para Análisis de Drogas EPI
 * Copyright: 2004
 * Company: Micros Personales S.A. de C.V.
 * @author Marco A. González Paz
 * @version 1.0
 * Clase:pg070104050CFG
 */%>


<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.seguridad.dao.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.micper.util.*"%>

<html>
<%
  pg070104050CFG  clsConfig = new pg070104050CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setNombrePagina("pg070104050.jsp");                    // modificar
  vEntorno.setArchFuncs("FValida");
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104050.jsp\" target=\"FRMCuerpo"); // modificar
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
  String cDscOrdenar  = "Muestra|";    // modificar
  String cCveOrdenar  = "iCveMuestra|";  // modificar
  String cDscFiltrar  = "Muestra|";    // modificar
  String cCveFiltrar  = "iCveMuestra|";  // modificar
  String cTipoFiltrar = "7|";                // modificar
  boolean lFiltros    = true;                 // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";             // modificar

  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

      String cCampo = "";
      String cPanel = "";

    if ( (request.getParameter("hdOPPbaRapida") == null) ||
         (request.getParameter("hdBoton").compareTo("Guardar") == 0) ||
          (request.getParameter("hdBoton").compareTo("Cancelar") == 0)){
         cCampo = "0";
    }
    else{
         cCampo = "" + request.getParameter("hdOPPbaRapida");
     }



  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  BeanScroller bs = clsConfig.getBeanScroller();
  String cRutaImg = vParametros.getPropEspecifica("RutaImg");
  String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
  String cUpdStatus  = clsConfig.getUpdStatus();
  String cNavStatus  = clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = clsConfig.getSaveAction();
  String cDeleteAction = clsConfig.getDeleteAction();
  int iAniosAtras = new Integer(vParametros.getPropEspecifica("Anios")).intValue();
  TFechas dtFecha = new TFechas();
  int iAnio = dtFecha.getIntYear(dtFecha.TodaySQL());
  TEtiCampo vEti = new TEtiCampo();
  TDGRLUSUMedicos dGRLUSUMedicos = new TDGRLUSUMedicos();

%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"empr.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selEmp.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script language="JavaScript">

 function fSelectedPer(iCvePersonal){
    var form = document.forms[0];
    form.hdiCvePersonal.value = iCvePersonal;

   //if (form.iCveMdoTrans){
      form.hdBoton.value = "Nuevo";
   //}

   /*else{
       form.hdBoton.value = "Cancelar";
   }*/

    form.target = "_self";
    form.submit();

  }


 function llenaSLT(opc,val1,val2,val3,objDes){
     if(objDes!=''){
        if(objDes.name!=''){
           objDes.length=1;
           objDes[0].text="Cargando Datos...";
           window.parent.FRMOtroPanel.location="pg070104050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name +  "&hdAccion=" + document.forms[0].action;
        } else {
           alert("Debe seleccionar un dato valido!");
           objDes.length=0;
           objDes.length=1;
           return false;
        }
    } else {
       window.parent.FRMOtroPanel.location="pg070104050P.jsp?opc=" + opc + "&val1=" + val1 + "&val2=" + val2 + "&val3=" + val3 + "&objDes=" + objDes.name +  "&hdAccion=" + document.forms[0].action;
    }
  }

function fChgArea(fArea){
  form = document.forms[0];
  cText = fArea.value;
  if(cText.length > 1999)
    fArea.value = cText = cText.substring(0,1999);
  form.iNoLetras.value = 1999 - cText.length;
}


  // Esta función no debe modificarse
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',false,'<%=vEntorno.getArchAyuda()%>');
  }

  function fEmpSelected(iCveEmpresa, cNombreRS, cApPaterno, cApMaterno, cRFC, cCURP, cTpoPersona){
      var form = document.forms[0];
     //alert("Valores Empresa: " + iCveEmpresa + cNombreRS );
     form.hdCveEmpresa.value = iCveEmpresa;
     form.hdDscEmpresa.value = cNombreRS;

    // if (form.iCveMdoTrans){
      form.hdBoton.value = "Nuevo";
    // }

    form.target = "_self";
    form.submit();
  }


  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
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
       cClave2  = ""+bs.getFieldValue("iCveMuestra", "");
        }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave2%>">
  <input type="hidden" name="hdAnio" value="<%=iAnio%>">
  <input type="hidden" name="hdPbaRapida" value="<%=request.getParameter("hdPbaRapida")%>">
  <input type="hidden" name="hdiCvePersonal" value="<%=request.getParameter("hdiCvePersonal")%>">
  <input type="hidden" name="hdCveEmpresa" value="<%=request.getParameter("hdCveEmpresa")%>">
  <input type="hidden" name="hdDscEmpresa" value="<%=request.getParameter("hdDscEmpresa")%>">


  <input type="hidden" name="hdiCveUniMed" value="<%=request.getParameter("hdiCveUniMed")%>">

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td>
  <input type="hidden" name="hdBoton" value="">
  </td><td valign="top">

                              <input type="hidden" name="hdOPPbaRapida" value="<%=cCampo%>">

                               <%
                                 boolean lCaptura = clsConfig.getCaptura();
                                boolean lNuevo = clsConfig.getNuevo();


                                if (lNuevo || cCampo.compareTo("0") != 0){
                                %>
                                 <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="7" class="ETablaT">Generar Formato Federal de Control y Cadena de Custodia de Muestras para Análisis de Drogas EPI
                                 </td>
                                 </tr>
                                 <tr>
                                 <%

                                out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                out.println("<td class='ECampo'>");
                                String User = "";
                                String cDef1 = request.getParameter("xCveUniMed");
                                String cDef2 = request.getParameter("iCveProceso");
                                if (cDef1 != null){
                                   if (cDef1.compareTo("null") == 0 && cDef1.compareTo("") == 0) {
                                      cDef1 = "0";
                                   }
                                }else{
                                   cDef1 = "0";
                                }
                                if (cDef2 != null){
                                   if (cDef2.compareTo("null") == 0 && cDef2.compareTo("") == 0) {
                                      cDef2 = "0";
                                   }
                                }else{
                                   cDef2 = "0";
                                }

                                TDGRLUMUsuario dUMUsuario = new TDGRLUMUsuario();
                                TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                User = "" + vUsuario.getICveusuario();
                                %>
                                <input type="hidden" name="iCveUsuCaptura" value="<%=User%>">
                                <%

                                out.println(vEti.SelectOneRowSinTD("iCveUniMed", "llenaSLT(1,"+ User + ",this.value,'',document.forms[0].iCveProceso);",dUMUsuario.getUniMedxUsu(vUsuario.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                out.println("</td>");


                                if (request.getParameter("hdiCveUniMed") != null){
                                 %>
                                 <script language="JavaScript">
                                    //llenaSLT(1,<%=User%>,<%=request.getParameter("hdiCveUniMed")%>,'',document.forms[0].iCveProceso);
                                 </script>
                                 <%
                                }


                                //Módulo
                               out.print(vEti.Texto("EEtiqueta", "MóduloT:"));
                                //System.out.println("request 1: " + request.getParameter("iCveModulo"));
                               //System.out.println("vals jsp: hd:" + request.getParameter("hdiCveUniMed") + " ** fill:" + request.getParameter("iCveUniMed"));
                               if (request.getParameter("iCveModulo") == null){
                                 out.println("<td class='ECampo' colspan='4'>");
                                 out.println("<SELECT NAME='iCveModulo' SIZE='1' onChange=\"llenaSLT(3,document.forms[0].iCveUniMed.value,this.value,'',document.forms[0].iCveModTrans);\">");
                                 out.println("</SELECT>");
                               }
                               else{
                                  //System.out.println("request 2: " + request.getParameter("iCveModulo"));
                                  TVGRLUSUMedicos vGRLUSUMedicos = new TVGRLUSUMedicos();
                                  Vector vGModulo = new Vector();
                                  String cFiltro = "where GRLUsuMedicos.iCveUsuario = " + vUsuario.getICveusuario() +
                                                   " and GRLUsuMedicos.iCveUniMed =  " + request.getParameter("iCveUniMed") ;
                                  vGModulo = dGRLUSUMedicos.FindModulos(cFiltro);
                                  out.print(vEti.SelectOneRow("ECampo","iCveModulo","",vGModulo,"iCveModulo","cDscModulo",request,""));
                               }

                                out.println("</tr>");
                                out.println("</tr>");
                                out.println(vEti.Texto("EEtiqueta","Proceso:"));


                                 if (request.getParameter("iCveProceso") == null){
                                  out.println("<td class='ECampo'>");
                                  out.println("<SELECT NAME='iCveProceso' SIZE='1' onChange=\"llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);\">");
                                  out.println("</SELECT>");
                                }
                                else{
                                  TDGRLUMUsuario dGRLUMUsuario = new TDGRLUMUsuario();
                                  out.println("<td>");
                                  out.println(vEti.SelectOneRowSinTD("iCveProceso","llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);",dGRLUMUsuario.getProcesos(Integer.parseInt(User,10),Integer.parseInt(request.getParameter("iCveUniMed"),10)),"iCveProceso", "cDscProceso", request, "0", true));
                                  out.println("</td>");
                                }
                                /*
                                out.println("<td class='ECampo'>");
                                out.println("<SELECT NAME='iCveProceso' SIZE='1' onChange=\"llenaSLT(2,this.value,'','',document.forms[0].iCveMotivo);\">");
                                out.println("</SELECT>");
                                out.println("</td>");
                                */


                                out.println(vEti.Texto("EEtiqueta","Motivo:"));
                                if (request.getParameter("iCveMotivo") == null){
                                out.println("<td class='ECampo' colspan='4'>");
                                out.println("<SELECT NAME='iCveMotivo' SIZE='1' onChange=''>");
                                out.println("</SELECT>");
                                out.println("</td>");
                                }
                                else{
                                 TVMotivo vMotivo = new TVMotivo();
                                 out.println("<td colspan='4'>");
                                 out.println(vEti.SelectOneRowSinTD("iCveMotivo","",(Vector) AppCacheManager.getColl("GRLMotivo",request.getParameter("iCveProceso").toString()),"iCveMotivo", "cDscMotivo", request, "0", true));
                                 out.println("</td>");
                                }


                                /*out.println("<td class='ECampo'>");
                                out.println("<SELECT NAME='iCveMotivo' SIZE='1' onChange=''>");
                                out.println("</SELECT>");
                                out.println("</td>");*/
                                out.println("</tr>");
                                out.println("<tr>");

                               //Modos de transporte

                               //System.out.println("mdo trans 50 : " + request.getParameter("iCveModTrans"));
                                out.print(vEti.Texto("EEtiqueta", "Modo de Transporte:"));
                                if (request.getParameter("iCveModTrans") == null){
                                   out.println("<td class='ECampo' colspan='8'>");
                                   out.println("<SELECT NAME='iCveModTrans' SIZE='1'>");
                                   out.println("</SELECT>");
                                }
                                else{

                                   TDGRLProcesoMDOT dGRLProcesoMDOT = new TDGRLProcesoMDOT();
                                   TVGRLProcesoMDOT vGRLProcesoMDOT = new TVGRLProcesoMDOT();

                                   Vector vcGRLProcesoMDOT = new Vector();

                                   vcGRLProcesoMDOT = dGRLProcesoMDOT.FindMTXMod(" where iCveUniMed = " +  request.getParameter("iCveUniMed") + " and iCveModulo = " +  request.getParameter("iCveModulo") );
                                   out.print(vEti.SelectOneRow("ECampo","iCveModTrans","",vcGRLProcesoMDOT,"iCveMdoTrans","cDscMdoTrans",request,""));
                                }

                                out.println("</tr>");
                                out.println("<tr>");

                                out.println("<tr>");
                                out.print(vEti.EtiCampoCS("EEtiqueta","No. de Identificación de la Muestra:","ECampo","text",15,15,5,"iCveMuestra","",0,"","fMayus(this);",false,true,true,request));
                                out.println("</tr>");

                                out.println("<tr>");

                                if (request.getParameter("hdiCvePersonal") != null &&
                                    request.getParameter("hdiCvePersonal").toString().compareTo("") != 0 &&
                                    request.getParameter("hdiCvePersonal").toString().compareTo("null") != 0
                                    ){
                                  out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", request.getParameter("hdiCvePersonal"), 3, "", "", true, true, false,request));
                                }
                                else{
                                  out.print(vEti.EtiCampo("EEtiqueta", "Cve. Personal:", "ECampo", "text", 25, 50, "Personal", "&nbsp;", 3, "", "", true, true, false,request));
                                }
                                //Aqui agregue lo del Selector de Personas
                                 if (request.getParameter("hdiCvePersonal") != null && request.getParameter("hdiCvePersonal").compareTo("") != 0 && request.getParameter("hdiCvePersonal").toString().compareTo("null") != 0) {
                                    out.println("<input type='hidden' name='iCvePersonal' value='" + request.getParameter("hdiCvePersonal")  + "'>" );
                                    int i = 0;
                                    Object obElemento;
                                    TDPERDatos dPerDatos = new TDPERDatos();
                                    Vector vcPersonal = new Vector();
                                    vcPersonal = dPerDatos.FindByAll(request.getParameter("hdiCvePersonal"));
                                    obElemento =  vcPersonal.get(i);
                                    TVPERDatos vPERDatos = (TVPERDatos) obElemento;
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text", 50, 50,3, "cDscPersonal",vPERDatos.getCNombreCompleto(), 4, "", "", true, true, false));
                                 }
                                 else{
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Nombre del Personal:", "ECampo", "text",50, 50,3, "cDscPersonal","&nbsp;", 4, "", "", true, true, false));
                                 }
                                 out.println("</tr>");

                                 out.println("</tr>");
                                 out.println("<tr>");
                                 out.println("<td align='center' colspan='6'>");
                                 out.print(vEti.clsAnclaTexto("EEtiqueta","Buscar Persona","javascript:fSelPer();","Buscar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                 out.println("</td>");
                                 out.println("</tr>");
                                 out.println("<tr>");
                                  out.print(vEti.Texto("EEtiqueta", "Prueba Rápida:"));

                                    if (request.getParameter("hdPbaRapida") == null || cCampo.compareTo("0") == 0) {
                                         out.print(vEti.TextoCS("EEtiquetaL", "0",5));
                                    }
                                    else{
                                         out.print(vEti.TextoCS("EEtiquetaL", request.getParameter("hdPbaRapida"),5));
                                     }
                                     out.println("</tr>");


                                    TDEmpresa dEmpresa = new TDEmpresa();
                                    out.println("<tr>");

                                    if (request.getParameter("hdCveEmpresa") != null &&
                                    request.getParameter("hdCveEmpresa").toString().compareTo("") != 0 &&
                                    request.getParameter("hdCveEmpresa").toString().compareTo("null") != 0
                                    ){
                                     out.println("<input type='hidden' name='iCveEmpresa' value='" + request.getParameter("hdCveEmpresa")  + "'>" );
                                      out.print(vEti.EtiCampo("EEtiqueta", "Cve. Empresa:", "ECampo", "text", 25, 50, "CveEmpresa", request.getParameter("hdCveEmpresa"), 3, "", "", true, true, false,request));
                                   }
                                   else{
                                     out.print(vEti.EtiCampo("EEtiqueta", "Cve. Empresa:", "ECampo", "text", 25, 50, "CveEmpresa", "&nbsp;", 3, "", "", true, true, false,request));
                                   }

                                   if (request.getParameter("hdDscEmpresa") != null &&
                                    request.getParameter("hdDscEmpresa").toString().compareTo("") != 0 &&
                                    request.getParameter("hdDscEmpresa").toString().compareTo("null") != 0
                                    ){
                                     out.print(vEti.EtiCampo("EEtiqueta", "Empresa:", "ECampo", "text", 25, 50, "DscEmpresa", request.getParameter("hdDscEmpresa"), 3, "", "", true, true, false,request));
                                   }
                                   else{
                                     out.print(vEti.EtiCampo("EEtiqueta", "Empresa:", "ECampo", "text", 25, 50, "DscEmpresa", "&nbsp;", 3, "", "", true, true, false,request));
                                   }
                                    out.println("<tr>");
                                    out.println("<td align='center' colspan='6'>");
                                    out.println(vEti.clsAnclaTexto("EAncla","Buscar Empresa","JavaScript:fSelEmp();", "Buscar Empresa",""));
                                    out.println("</td>");
                                    //out.println("<td class='ECampo' colspan='5'>");
                                    //out.println(vEti.SelectOneRowSinTD("iCveEmpresa", "", dEmpresa.FindByAll(), "iCveEmpresa", "cDscEmpresa", request, "0",true));
                                    //out.println("</td>");
                                    out.println("</tr>");

                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Fecha Recolección:", "ECampo", "text",10, 10,5, "dtRecoleccion","", 3, "", "", false, true, true));
                                   %>
                                   <input type="hidden" name="hdHoy" value="<%=dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")%>" >
                                   <%
                                   out.println("</tr>");

                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Médico Recolector:"));


                                if (request.getParameter("iCveUsuRecolec") == null){
                                  out.println("<td>");
                                  out.println("<SELECT NAME='iCveUsuRecolec' SIZE='1' onChange=''>");
                                  out.println("</SELECT>");
                                  out.println("</td>");
                                }
                                  else{
                                TVUsuario vUsuarioAplica = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                Vector vcPersonal;
                                int iCveProceso = Integer.parseInt(vParametros.getPropEspecifica("ToxProcesoLab"));
                                int iUniMed = 0;

                                if(request.getParameter("iCveUniMed") == null){
                                   vcPersonal = vUsuario.getVUniFiltro(iCveProceso);
                                   if(vcPersonal.size() != 0){
                                       iUniMed = ((TVGRLUMUsuario) vcPersonal.get(0)).getICveUniMed();
                                   }
                                }else{
                                      iUniMed = Integer.parseInt(request.getParameter("iCveUniMed"),10);
                                }
                                vcPersonal = new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);

                                 new TDGRLUMUsuario().getUniMed(iCveProceso,iUniMed);
                                 out.println("<td>");
                                 out.print(vEti.SelectOneRowSinTD("iCveUsuRecolec", "", vcPersonal,"iCveUsuario", "cNomCompleto", request, "", true));
                                 out.println("</td>");
                                 }


                                /*    out.println("<td>");
                                    out.println("<SELECT NAME='iCveUsuRecolec' SIZE='1' onChange=''>");
                                    out.println("</SELECT>");
                                    out.println("</td>");*/

                                    TDGRLMdoTrans dMdoTrans = new TDGRLMdoTrans();
                                    out.println(vEti.Texto("EEtiqueta","Situación:"));
                                    out.println(vEti.TextoCS("EEtiquetaL","Recolección",3));

                                   /* out.println(vEti.Texto("EEtiqueta","Modo de Transporte:"));
                                    out.println("<td class='ECampo'>");
                                    out.println(vEti.SelectOneRowSinTD("iCveMdoTrans", "", dMdoTrans.findByAll(""), "iCveMdoTrans", "cDscMdoTrans", request, "0",true));
                                    out.println("</td>");*/
                                    out.println("</tr>");
                                    out.println("<tr>");
                                   %>
                                    <td colspan="7" class='ETablaT'>Características de la Muestra</td>
                                  <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Temperatura correcta:"));
                                    out.println("<td>");
                                    out.println("<input type='checkbox' name='lTemperaC' value='ON'>");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Presenta alguna alteración:"));
                                    out.println("<td colspan='3'>");
                                    out.println("<input type='checkbox' name='lAlteracion' value='ON'>");
                                     out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Se tomó bajo observación:"));
                                    out.println("<td colspan='5'>");
                                    out.println("<input type='checkbox' name='lBajoObserva' value='ON'>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println("<td class=\"EEtiqueta\">Observaciones:<br><input type=\"text\" size=\"5\" name=\"iNoLetras\" value=\"\" disabled></td>");
                                    out.print("<td colspan='4'><textarea cols=\"80\" rows=\"5\" name=\"cObsMuestra\" value=\"\" onkeypress=\"fChgArea(this);\" onChange=\"fChgArea(this);\"></textarea></td>");
                                    //out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",50,5,5,"cObsMuestra","",0,"","",false,true,lCaptura));
                                    out.println("</tr>");
                                %>
                                   </table>
                               <%
                                   }
                                else {
                                 %>

                                   <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
                                   <tr>
                                   <td colspan="7" class="ETablaT">Búsqueda
                                   </td>
                                   </tr>
                                   <tr>
                                   <%
                                       out.println(vEti.Texto("EEtiqueta","Año:"));
                                     %>
                                    <td class="ETabla">
                                    <select name="iAnioB" size="1">
                                    <% for (int i = iAnio; i >= (iAnio - iAniosAtras); i--){
                                          out.print("<option value = " + i + ">" + i + "</option>");
                                       }
                                    %>
                                   </select>
                                   </tr>
                                <%
                                   out.println(vEti.Texto("EEtiqueta","Unidad Médica:"));
                                   out.println("<td class='ECampo'>");
                                   String UserB = "";
                                   TDGRLUMUsuario dUMUsuarioB = new TDGRLUMUsuario();
                                   Vector vcUMInicial = new Vector();
                                   TVUsuario vUsuarioB = (TVUsuario) request.getSession(true).getAttribute("UsrID");
                                   UserB = "" + vUsuarioB.getICveusuario();
                                   out.println(vEti.SelectOneRowSinTD("iCveUniMedB", "",dUMUsuarioB.getUniMedxUsu(vUsuarioB.getICveusuario()),"iCveUniMed", "cDscUniMed", request, "0", true));
                                   out.println("</td>");
                                %>
                                </table>
                               <p>&nbsp;<p>
                                   <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                                 <tr>
                                 <td colspan="7" class="ETablaT">Generar Formato Federal de Control y Cadena de Custodia de Muestras para Análisis de Drogas EPI
                                 </td>
                                 </tr>
                                 <tr>

                                 <%
                                    if (bs != null){
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Unidad Médica:", "ECampo", "text", 25, 50, "iCveUniMed", bs.getFieldValue("cDscUM","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Módulo:", "ECampo", "text", 25, 50,4, "iCveModulo", bs.getFieldValue("cDscModulo","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta", "Proceso:", "ECampo", "text", 25, 50, "iCveProceso", bs.getFieldValue("cDscProceso","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Motivo:", "ECampo", "text", 25, 50,4, "iCveMotivo", bs.getFieldValue("cDscMotivo","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta", "Modo de Transporte:", "ECampo", "text", 25, 50,6, "iCveMdoTrans", bs.getFieldValue("cDscMdoTrans","&nbsp;").toString(), 3, "", "", true, false, lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","No. de Identificación de la Muestra (NIM):","ECampo","text",10,10,5,"iCveMuestra", bs.getFieldValue("iCveMuestra","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    //out.print(vEti.EtiCampoCS("EEtiqueta","Año:","ECampo","text",10,10,3,"iAnio",bs.getFieldValue("iAnio","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Personal:","ECampo","text",10,10,"iCvePersonal", bs.getFieldValue("iCvePersonal","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Personal:","ECampo","text",10,10,3,"cDscPersonal", bs.getFieldValue("cNombreCompleto","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Prueba Rápida:","ECampo","text",10,10,6,"iCvePbaRápida", bs.getFieldValue("iCvePbaRapida","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Empresa:","ECampo","text",10,10,"iCveEmpresa" ,bs.getFieldValue("iCveEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Empresa:","ECampo","text",10,10,3,"cDscEmpresa" ,bs.getFieldValue("cDscEmpresa","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Cve. Modo de Transporte:","ECampo","text",10,10,"iCveMdoTrans" ,bs.getFieldValue("iCveMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Modo de Transporte:","ECampo","text",10,10,3,"cDscMdoTrans" ,bs.getFieldValue("cDscMdoTrans","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.print(vEti.EtiCampo("EEtiqueta","Fecha Captura:","ECampo","text",10,10,"dtCaptura",bs.getFieldValue("cDtCaptura","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.print(vEti.EtiCampoCS("EEtiqueta","Fecha Recolección:","ECampo","text",10,10,3,"dtRecoleccion",bs.getFieldValue("cDtRecoleccion","&nbsp;").toString(),0,"","fMayus(this);",false,true,lCaptura));
                                    out.println("</tr>");
                                    out.println("<tr>");
                                   %>
                                    <td colspan="7" class='ETablaT'>Características de la Muestra</td>
                                  <%
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Temperatura correcta:"));
                                    out.println("<td>");
                                    if ( bs.getFieldValue("lTemperaC","&nbsp;").toString().compareTo("1") == 0)
                                        out.println("<input type='checkbox' name='lTemperaC' value='ON' disabled = 'true' checked>");
                                    else
                                        out.println("<input type='checkbox' name='lTemperaC' value='ON' disabled = 'true'>");
                                    out.println("</td>");
                                    out.println(vEti.Texto("EEtiqueta","Presenta alguna alteración:"));
                                    out.println("<td colspan = '4'>");
                                    if ( bs.getFieldValue("lAlteracion","&nbsp;").toString().compareTo("1") == 0)
                                        out.println("<input type='checkbox' name='lAlteracion' value='ON' disabled = 'true' checked>");
                                    else
                                        out.println("<input type='checkbox' name='lAlteracion' value='ON' disabled = 'true'>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.Texto("EEtiqueta","Se tomó bajo observación:"));
                                    out.println("<td colspan = '5'>");
                                    if ( bs.getFieldValue("lBajoObserva","&nbsp;").toString().compareTo("1") == 0)
                                        out.println("<input type='checkbox' name='lBajoObserva' value='ON' disabled = 'true' checked>");
                                    else
                                        out.println("<input type='checkbox' name='lBajoObserva' value='ON' disabled = 'true'>");
                                    out.println("</td>");
                                    out.println("</tr>");
                                    out.println("<tr>");
                                    out.println(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones","ECampo",10,5,5,"cObsMuestra",bs.getFieldValue("cObsMuestra","").toString(),0,"","",false,true,lCaptura));
                                    out.println("</tr>");

                                    }
                                }
                            %>
                           <tr>
                           </tr>
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