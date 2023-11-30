
<%/**
 * Title: Selección del paciente al servicio
 * Description: Selección del paciente al servicio
 * Copyright: 2004
 * Company: Micros Personales SA de CV
 * @author Romeo Sanchez
 * @version 1
 * Clase: pg070108021
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<html>
<%
  //System.out.println("Rastreo 1");
  pg070108021CFG  clsConfig = new pg070108021CFG();                 // modificar
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");
  //clsConfig.setUsuario(71);

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070108021.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070108021.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104011.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Grupo|Inicio vigencia|Fin vigencia|Vigente|";    // modificar
  String cCveOrdenar  = "cDscGrupo|dtInicio|dtFin|lVigente|";  // modificar
  String cDscFiltrar  = "Inicio vigencia|Fin vigencia|";    // modificar
  String cCveFiltrar  = "p.dtInicio|p.dtFin|";  // modificar
  String cTipoFiltrar = "5|5|";                // modificar 7/8
  boolean lFiltros    = false;                  // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  // si los sintomas que se guardaron corresponden a la ultima rama del servicio,
  // se debe direccionar a la pagina de diagnosticos (pg070104032.jsp)
//System.out.println("ultima rama: " + clsConfig.ultimaRama);
  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = "Hidden";//clsConfig.getNavStatus();
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
//System.out.println("Valor de la Rama = " + request.getParameter("iCveRama"));
int iCveUniMed =  Integer.parseInt(request.getParameter("iCveUniMed")==null?"0":request.getParameter("iCveUniMed"));
int iCveProceso = Integer.parseInt(request.getParameter("iCveProceso")==null?"0":request.getParameter("iCveProceso"));
int iCveModulo =  Integer.parseInt(request.getParameter("iCveModulo")==null?"0":request.getParameter("iCveModulo"));
int iCveServicio =  Integer.parseInt(request.getParameter("iCveServicio")==null?"0":request.getParameter("iCveServicio"));
int iCveRama =  Integer.parseInt(request.getParameter("iCveRama")==null?"0":request.getParameter("iCveRama"));
//System.out.println("Rastreo 2");
%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<!--
<SCRIPT LANGUAGE="JavaScript" SRC="C:\sct\MedprevInt\medprev\archivos\funciones\pg070108021.js"></SCRIPT>
-->
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"SelPer.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"Audio01.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"Audio02.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"Audio03.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cStyle = '<link rel="stylesheet" href="/exm/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';
  var aSel = new Array();
  var aSelMuestra = new Array();

  // Esta función recibe el vector con los valores capturados en el tipo de examen al dar click en guardar.
  function fSaveAudio(aSel,iTipoSel, oido){
    form = document.forms[0];
    //alert(aSel + '..' + iTipoSel + '..' + oido);
    if (oido == "Derecho") {
      if (iTipoSel == 1)
         form.hdOIM.value = aSel;
      else if (iTipoSel == 2)
         form.hdOIC.value = aSel;
      else if (iTipoSel == 3)
         form.hdOIT.value = aSel;
      else
         form.hdOIN.value = aSel;
    }
     if (oido == "Izquierdo") {
      if (iTipoSel == 1)
         form.hdODM.value = aSel;
      else if (iTipoSel == 2)
         form.hdODC.value = aSel;
      else if (iTipoSel == 3)
         form.hdODT.value = aSel;
      else
         form.hdODN.value = aSel;
    }
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  // Variable global para hacer referencia a la subventana
  var newWindowCholestech;

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  // Generar y llenar la nueva ventana
  function makeNewWindowCholestech() {
      // asegurarse de que no se habia abierto
      if (!newWindowCholestech || newWindowCholestech.closed) {
          newWindowCholestech = window.open("", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
          // retardar escritura hasta que la ventana exista en IE/Windows
          setTimeout("writeToWindowCholestech()", 50);
          newWindowCholestech.focus( );
      } else if (newWindowCholestech.focus) {
          // la ventana ya estaba abierta y con focus para traerla al frente
          newWindowCholestech.focus( );
      }
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006
  function writeToWindowCholestech() {
      // crear la nueva ventana
      var cPagina='<html><head><title>Cargar Archivo del Equipo Cholestech</title></head>'+
                  '<body>'+
                  '<form method="post" action="servCholestechUpXLS" name="formXLS" enctype="multipart/form-data">'+
                  'Seleccione el archivo: <input type="file" name="fileName"/>'+
                  '<br />'+
                  '<input type="submit" value="Cargar archivo"/>'+
                  '</form>'+
                  '</body>'+
                  '</html>';
      // escribir HTML al documento de la nueva ventana
      newWindowCholestech.document.write(cPagina);
      newWindowCholestech.document.close( ); // cerrar el stream
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006
  // Variable global para hacer referencia a la subventana
  var newWindowDaytona;

  // Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006
  // Generar y llenar la nueva ventana
  function makeNewWindowDaytona() {
      // asegurarse de que no se habia abierto
      if (!newWindowDaytona || newWindowDaytona.closed) {
          newWindowDaytona = window.open("", "", "width=400,height=640,status=no,resizable=yes,menubar=yes,titlebar=yes,top=0,left=500,screenY=0,screenX=500,scrollbars=yes");
          // retardar escritura hasta que la ventana exista en IE/Windows
          setTimeout("writeToWindowDaytona()", 50);
          newWindowDaytona.focus( );
      } else if (newWindowDaytona.focus) {
          // la ventana ya estaba abierta y con focus para traerla al frente
          newWindowDaytona.focus( );
      }
  }

  // Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006
  function writeToWindowDaytona() {
      form = document.forms[0];

      // crear la nueva ventana
      var cPagina='<html><head><title>Cargar Archivo del Equipo Daytona</title></head>'+
                  '<body>'+
                  '<form method="post" action="servDaytonaLabAnalisisClinicoUpXLS?icveexpediente='+form.iCveExpediente.value+
                  '&icveservicio='+form.iCveServicio.value+
                  '&icverama='+form.iCveRama.value+'" name="formXLS" enctype="multipart/form-data">'+
                  'Seleccione el archivo: <input type="file" name="fileName"/>'+
                  '<br />'+
                  '<input type="submit" value="Cargar archivo"/>'+
                  '</form>'+
                  '</body>'+
                  '</html>';

      // escribir HTML al documento de la nueva ventana
      newWindowDaytona.document.write(cPagina);
      newWindowDaytona.document.close( ); // cerrar el stream
  }
  
  //Agregado AG SA 09/07/2008
  function IMC(valor){
        form = document.forms[0];
        var peso = form.dValorI_16.value ;
        var estatura = form.dValorI_17.value;
        var estaturametros = estatura/100;
        var imc = peso / (estaturametros * estaturametros);
        imc = imc.toFixed(2); //trunca a 2 decimales
        form.cCaracter_18.value = imc;
    }
  
  

</SCRIPT>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form method="<%= vEntorno.getMetodoForm() %>" action="<%= vEntorno.getActionForm() %>">
<!--MENSAJES -->
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="500">
  <%
     //System.out.println("Rastreo 3");
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     } else {
       cPosicion = request.getParameter("hdRowNum");
     }
  %>
  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">
  <input type="hidden" name="iCvePerfil" value="<%=request.getParameter("iCvePerfil")%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="iCvePersonal" value="<%=request.getParameter("iCveExpediente")%>">
  <input type="hidden" name="iCveExpediente" value="<%=request.getParameter("iCveExpediente")%>">
  <input type="hidden" name="iCveServicio" value="<%=request.getParameter("iCveServicio")%>">
  <input type="hidden" name="iNumExamen" value="<%=request.getParameter("iNumExamen")%>">
 <input type="hidden" name="hdOIM" value="<%=request.getParameter("hdOIM")%>">
 <input type="hidden" name="hdOIC" value="<%=request.getParameter("hdOIC")%>">
 <input type="hidden" name="hdOIT" value="<%=request.getParameter("hdOIT")%>">
 <input type="hidden" name="hdOIN" value="<%=request.getParameter("hdOIN")%>">
 <input type="hidden" name="hdODM" value="<%=request.getParameter("hdODM")%>">
 <input type="hidden" name="hdODC" value="<%=request.getParameter("hdODC")%>">
 <input type="hidden" name="hdODT" value="<%=request.getParameter("hdODT")%>">
 <input type="hidden" name="hdODN" value="<%=request.getParameter("hdODN")%>">
 <input type="hidden" name="hdGOido" value="<%=request.getParameter("hdGOido")%>">
<%
//System.out.println("iNumExamen "+request.getParameter("iNumExamen"));
//System.out.println("ramaInicial "+request.getParameter("ramaInicial"));
//System.out.println(request.getParameter("iCveProceso"));
//System.out.println(vParametros.getPropEspecifica("EPIProceso"));
//System.out.println("Rastreo 4");
  if (request.getParameter("iCveProceso")!=null && request.getParameter("iCveProceso").length() >0){
%>   <input type="hidden" name="iCveProceso" value="<%=request.getParameter("iCveProceso")%>">
<%
  }else{
%>   <input type="hidden" name="iCveProceso" value="<%=vParametros.getPropEspecifica("EPIProceso")%>">
<%
 }
%>
  <input type="hidden" name="tpoBusqueda" value="<%=request.getParameter("tpoBusqueda")%>">
  <input type="hidden" name="iCveUsuario" value="<%=vUsuario.getICveusuario()%>">
  
<% 
//System.out.println("Rastreo 4-3");
//System.out.println(request.getParameter("ramaInicial"));
int ramaInicial = Integer.parseInt(request.getParameter("ramaInicial"));
   ramaInicial++;
//System.out.println("Rastreo 4-5");
%>
  <input type="hidden" name="ramaInicial" value="<%=ramaInicial%>">
  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){ %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
                          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
                            <tr><td class="ETablaT" colspan="6">Datos del Usuario</td></tr>
<% // inicializador de datos
//System.out.println("Rastreo 5");
   TVPERDatos personal = clsConfig.getPersonal();
    
   long edad = clsConfig.getEdadPersonal();
   HashMap hm = vUsuario.getVServicios(iCveUniMed, iCveProceso, iCveModulo);
   String servicio = (String)hm.get(""+iCveServicio); // descripción del servicio
TVEXPRama voRama = clsConfig.getCurrentRama();
TDEXPResultado dEXPResultado = new TDEXPResultado();
TVEXPResultado vEXPResultado;
Vector vcEXPResultado = new Vector();

vcEXPResultado = dEXPResultado.FindServAdicionales(" where EXPResultado.iCveExpediente = "
  + request.getParameter("iCveExpediente") + " and EXPResultado.iNumExamen = " + request.getParameter("iNumExamen")
  + " and EXPResultado.iCveServicio = " + vParametros.getPropEspecifica("Signos Vitales") );

String rama = "";
if (voRama!=null) {
   rama = voRama.getCDscRama();
} else {
//  pageContext.forward("pg070104031.jsp");
}

  // -- Escribe en EXPDictamenServ para los servicios que no hacen diagnósticos y/o recomendaciones

  TDMEDServicio dMEDServicio = new TDMEDServicio();
  TVMEDServicio vMEDServicio = new TVMEDServicio();
  Vector vcMEDServicio = new Vector();
////Try catch para imprimir el error
try{  
  vcMEDServicio = dMEDServicio.FindByAll(" where iCveServicio = " + request.getParameter("iCveServicio"), "");
  if (vcMEDServicio.size() > 0)
    for (int i = 0; i < vcMEDServicio.size(); i++)
      vMEDServicio = (TVMEDServicio)vcMEDServicio.get(i);

  int iDictamina = vMEDServicio.getLReqDiag();

  //System.out.println("clsConfig.ultimaRama "+clsConfig.ultimaRama);
  if (clsConfig.ultimaRama) {

      //System.out.println(iDictamina);
      
    if(iDictamina==1){
      pageContext.forward("pg070104031.jsp?hdBoton=Actual");
    }
    else if(request.getParameter("tpoBusqueda").equals("variosMedicos")){
      clsConfig.GuardarDiagRec();
      pageContext.forward("pg070104010.jsp?hdBoton=Actual");
    }
    else if(request.getParameter("tpoBusqueda").equals("unMedico")){
      clsConfig.GuardarDiagRec();
      pageContext.forward("pg070104020.jsp?hdBoton=Actual");
    }
  }
  //System.out.println("Rastreo 6");

  // -- Termina la escritura en EXPDictamenServ

  //System.out.println("iCveServicio = "+iCveServicio);
%>
  <input type="hidden" name="iCveRama" value="<%=(voRama==null?"":voRama.getICveRama()+"")%>">
  <input type="hidden" name="iCveServicio" value="<%=iCveServicio%>">
<!-- Personal -->
                            <tr>
                              <td class="EEtiqueta" colspan="3">Proceso:</td>
                              <td class="ETabla" colspan="3"><%=clsConfig.getProceso(""+iCveProceso)%></td>
                            </tr>

                            <tr>
                              <td class="EEtiqueta" colspan="1">Nombre:</td>
                              <td class="ETabla" colspan="2"><%=personal.getCApPaterno()+" "+personal.getCApMaterno()+" "+personal.getCNombre()%>
                              </td>
                              <td class="EEtiqueta" colspan="2">Expediente:</td>
                              <td class="ETabla" colspan="1"><%=personal.getICveExpediente()%>
                              </td>
                            </tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Edad:</td>
                              <td class="ETabla" colspan="2"><%=edad%>&nbsp;a&ntilde;os
                              </td>
                              <td class="EEtiqueta" colspan="2">Sexo:</td>
                              <td class="ETabla" colspan="1"><%=personal.getCSexo().equalsIgnoreCase("M")?"Masculino":"Femenino"%>
                              </td>
                            </tr>
                            <% if (vParametros.getPropEspecifica("Signos Vitales").compareTo(request.getParameter("iCveServicio")) != 0) {%>
                            <tr><td class="ETablaT" colspan="6">Signos Vitales</td></tr>
                            <tr>
                             <%
                             int y = 0;
                             if (vcEXPResultado.size() > 0){
                              out.println("<tr>");
                               for (int z = 0; z < vcEXPResultado.size(); z++){
                                  vEXPResultado = (TVEXPResultado) vcEXPResultado.get(z);
                                  out.println("<td class='EEtiqueta'>"+ vEXPResultado.getCPregunta() +"</td>");
                                  if (vEXPResultado.getICveTpoResp() == 1 ||
                                      vEXPResultado.getICveTpoResp() == 4){
                                    if (vEXPResultado.getLLogico() == 0)
                                       out.println("<td class='ECampo'>NO</td>");
                                    else
                                       out.println("<td class='ECampo'>SI</td>");
                                  }
                                  if (vEXPResultado.getICveTpoResp() == 2){
                                     if (vEXPResultado.getCCaracter() != null)
                                        out.println("<td class='ECampo'>"+ vEXPResultado.getCCaracter()+"</td>");
                                     else
                                        out.println("<td class='ECampo'>&nbsp;</td>"); 
                                  }
                                  if (vEXPResultado.getICveTpoResp() == 3){
                                    if (vEXPResultado.getDValorIni() != 0)
                                       out.println("<td class='ECampo'>"+ vEXPResultado.getDValorIni()+"</td>");
                                    else
                                       out.println("<td class='ECampo'>&nbsp;</td>");
                                  }
                                  if (vEXPResultado.getICveTpoResp() == 5){
                                    if (vEXPResultado.getDValorIni() != 0)
                                       out.println("<td class='ECampo'>"+ vEXPResultado.getDValorIni()+" - "+vEXPResultado.getDValorFin()+"</td>");
                                     else
                                       out.println("<td class='ECampo'>&nbsp;</td>");
                                  }
                                  y += 1 ;
                                  if( y == 3 ){
                                   out.println("<tr>");
                                   out.println("</tr>");
                                    y = 0;
                                 }

                               }
                               if (y > 1){
                                  for (int q = y; q<3 ; q++){
                                   out.print("<td>&nbsp</td>");
                                   out.print("<td>&nbsp</td>");
                                 }
                               }

                               out.println("</tr>");
                             }
                            }
 TDMEDConsulta dMEDConsulta = new TDMEDConsulta();
 TVMEDConsulta vMEDConsulta = new TVMEDConsulta();
//System.out.println("Rastreo 7");
 Vector vcConsulta = new Vector();
 
 ////Try catch para imprimir el error
 try{
 vcConsulta = dMEDConsulta.FindByAll(" where MEDConsulta.iCveServicio = " + request.getParameter("iCveServicio"));
 int x;
 if (vcConsulta.size() > 0){
  out.print("<tr><td class='ETablaT' colspan='6'>Servicios de Consulta</td></tr>");
  out.println("<tr>");
   x = 0;
   for (int w = 0; w < vcConsulta.size(); w++){
      vMEDConsulta = (TVMEDConsulta) vcConsulta.get(w);
      //System.out.print("Si entro al ciclo: " + vcConsulta.size() + " *** " + vMEDConsulta.getICveServicio() );
      out.print("<td class='ETablaC' colspan='2'>");
      out.print(vEti.clsAnclaTexto("EAnclaC","" + vMEDConsulta.getCDscServCons(),
                                            "javascript:fServicio("+request.getParameter("iCveExpediente") +","
                                           +request.getParameter("iNumExamen") +","+ vMEDConsulta.getICveServCons() + ",'"+"" + vMEDConsulta.getCDscServCons()  +"');","Servicio"));
      out.print("</td>");
      x += 1 ;
      if( x == 3 ){
       out.println("</tr>");
       out.println("<tr>");
        x = 0;
      }

   }
    if (x > 1){
    for (int s = x; s < 3 ; s++){
     out.print("<td>&nbsp</td>");
     out.print("<td>&nbsp</td>");
   }
 }
 }
   }catch(Exception e){
         vcConsulta = new Vector();
         e.printStackTrace();
  }   
 //System.out.println("Rastreo 8");
 
%>
<%
// NOTA DE LA INTERCONSULTA
     if (vcMEDServicio.size() > 0){
         vMEDServicio = (TVMEDServicio) vcMEDServicio.get(0);
         if (vMEDServicio.getLInterConsulta() == 1 ){
           TDEXPInterconsulta dEXPInterconsulta = new TDEXPInterconsulta();
           TVEXPInterconsulta vEXPInterconsulta = new TVEXPInterconsulta();
           Vector vcEXPInterconsulta = new Vector();
           vcEXPInterconsulta = dEXPInterconsulta.findByAll
             (" where iCveExpediente = " + request.getParameter("iCveExpediente") +
              " and iNumExamen = " + request.getParameter("iNumExamen") +
              " and iCveServicio = "+ iCveServicio);
           if (vcEXPInterconsulta.size() > 0){
              vEXPInterconsulta = (TVEXPInterconsulta) vcEXPInterconsulta.get(0);
              out.print("<tr><td class='ETablaT' colspan='6'>Motivo de Solicitud</td></tr>");
              out.println("<tr><td class='ETablaC' colspan = '6'>");
              out.println(vEXPInterconsulta.getCMtvoSolicitud());
              out.println("<td></tr>");
           }
         }
     }
  }catch(Exception e){
         vcMEDServicio = new Vector();
         e.printStackTrace();
  }   
     //System.out.println("Rastreo 9");
  //System.out.println("servicio = "+servicio);
  //System.out.println("rama = "+rama);
%>
<!-- Servicio -->
                            <tr><td class="ETablaT" colspan="6">Servicio</td></tr>
                            <tr>
                              <td class="EEtiqueta" colspan="1">Servicio:</td>
                              <td class="ETabla" colspan="2"><%=servicio%>
                              </td>
                              <td class="EEtiqueta" colspan="1">Rama:</td>
                              <td class="ETabla" colspan="2"><%=rama%>
                              </td>
                            </tr>
                            <!-- Agregado RAFAEL ALCOCER CALDERA 02/Oct/2006 -->
                            <!-- ******************************************* -->
                            <%
                                if (iCveServicio == 2) {
                            %>
                                    <tr>
                                      <td>
                                      </td>
                                      <td class="ETablaC" colspan="3">
                                      <%
                                        out.print(vEti.clsAnclaTexto("EAncla","Cargar Archivo de Cholestech","JavaScript:makeNewWindowCholestech();","Cholestech"));
                                      %>
                                      </td>
                                    </tr>
                                    <!-- ******************************************* -->
                                    <!-- Agregado RAFAEL ALCOCER CALDERA 02/Nov/2006 -->
                                    <!-- ******************************************* -->
                                    <tr>
                                      <td>
                                      </td>
                                      <td class="ETablaC" colspan="3">
                                      <%
                                        out.print(vEti.clsAnclaTexto("EAncla","Cargar Archivo de Daytona","JavaScript:makeNewWindowDaytona();","Daytona"));
                                      %>
                                      </td>
                                    </tr>
                            <%
                                }
                            %>
                            <!-- ******************************************* -->
                            <tr><td class="ETablaT" colspan="6">Preguntas / Síntomas / Resultados</td></tr>
                            <%
                                  //System.out.println("Rastreo 10");
                                  String cOdonto = "";
                               if (bs != null){
                                   bs.start();
                                   int c = 0;
                                   int d = 0;
                                   String cFlag = "";
                                   String cAux = "";
                           while(bs.nextRow()){

                            String iCveTpoResp = bs.getFieldValue("iCveTpoResp").toString();
                            String iCveSintoma = bs.getFieldValue("iCveSintoma").toString();

                            cFlag = bs.getFieldValue("iCveRama")+""+ bs.getFieldValue("iCveSintoma");

                            boolean esTexto = false;
                            boolean esCheck = false;
                            boolean esNumero = false;
                            boolean esRango = false;
                            int tpoResp = iCveTpoResp==null||iCveTpoResp.equals("null")?0:Integer.parseInt(iCveTpoResp);
                             cOdonto = cOdonto +  "<td class='EEtiquetaC' width='30%'>" + bs.getFieldValue("cPregunta","&nbsp;").toString() + "</td>";
                          if ((vParametros.getPropEspecifica("EPIServicioOdonto").compareTo(request.getParameter("iCveServicio")) != 0) ||
                             ((vParametros.getPropEspecifica("EPIServicioOdonto").compareTo(request.getParameter("iCveServicio"))) == 0) &&
                               (iCveRama > 0)){

                             out.print("<tr>");
                             if(tpoResp != 6)
                               out.print("<td class='EEtiqueta' colspan='2' width='30%'>" + bs.getFieldValue("cPregunta","&nbsp;").toString() + "</td>");
// Respuestas del tipo correspondiente
                             %>
                             <input type="hidden" name="iCveTpoResp_<%=cFlag%>" value="<%=iCveTpoResp%>">
                             <input type="hidden" name="iCveSintoma_<%=cFlag%>" value="<%=iCveSintoma%>">
                             <%
                    String ServicioMG = "12";
                    String ServicioCAR = "10";
                    String ServicioAUD = "6";
                    String ExaMax = "";
                    int max = 0;
                    String SQL = "";
                    String numeroexamen = "";
                    int countexa = 0;
                    String Contador = "";
                    int claverama = Integer.parseInt(bs.getFieldValue("iCveRama").toString());
//System.out.println("Rastreo 11");
                        TDMEDSintoma dMEDSintoma = new TDMEDSintoma();
                    try{
                        SQL = "SELECT COUNT(INUMEXAMEN) FROM EXPEXAMAPLICA WHERE ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente")+"  AND ICVEPROCESO = 1";
                        numeroexamen = dMEDSintoma.Consultas(SQL);
                    }catch(Exception e){
                        numeroexamen = "1";
                        e.printStackTrace();
                    } 
                    countexa = Integer.parseInt(numeroexamen.trim());
                    //System.out.println("Contador examenes epi= "+countexa+"");
                    String Checkbox ="";
                             
                             
                    //Validacion MEDICINA GENERAL
                             if(ServicioMG.equals(request.getParameter("iCveServicio")) || ServicioCAR.equals(request.getParameter("iCveServicio")) || ServicioAUD.equals(request.getParameter("iCveServicio")) && countexa > 1 ){
                                  //System.out.println("Dentro de condicio Mayor a 1");
                                 
                               switch (tpoResp) {
                               case 1: // si/no
                                    esCheck = true;
                                    out.println("<td class='ECampo'>");
                                   if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                        //System.out.println("Entro a la primera condicion si/no");
                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }
                                        try{
                                            SQL = "SELECT MAX(INUMEXAMEN) FROM EXPRESULTADO " +
                                                       "WHERE ICVEEXPEDIENTE  = "+request.getParameter("iCveExpediente")+" " +
                                                       " AND LLOGICO IS NOT NULL AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+"  ";
                                                        ExaMax = dMEDSintoma.Consultas(SQL);  
                                       }catch(Exception e){
                                            ExaMax = "1";
                                            e.printStackTrace();
                                        } 
                                                        if(ExaMax == null){
                                                            cCheck1 = "";
                                                            cCheck2 = "checked";
                                                            ExaMax ="1";
                                                        }else{
                                                //System.out.println("ExaMax = "+ExaMax);
                                                max = Integer.parseInt(ExaMax.trim());
                                         try{
                                            SQL = "SELECT LLOGICO FROM EXPRESULTADO WHERE ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente")+" AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+" AND INUMEXAMEN = "+max+"";
                                            Checkbox = dMEDSintoma.Consultas(SQL);
                                            //System.out.println("Checkbox = "+Checkbox);
                                       }catch(Exception e){
                                            Checkbox = "0";
                                            e.printStackTrace();
                                        }
                                                
                                            if("1".equals(Checkbox)){
                                                cCheck1 = "checked";
                                                cCheck2 = "";
                                            }else{
                                                cCheck1 = "";
                                                cCheck2 = "checked";
                                            } }
                                        
                                        //System.out.println("Rastreo 12");
                                        
                                        out.println("(+)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >  ");
                                        out.println("(-)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" >  ");
                                     }
                                     else{
                                        //System.out.println("Entro a la segunda condicion si/no");
                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }
                                        
                                         try{                
                                         SQL = "SELECT MAX(INUMEXAMEN) FROM EXPRESULTADO " +
                                                       "WHERE ICVEEXPEDIENTE  = "+request.getParameter("iCveExpediente")+" " +
                                                       " AND LLOGICO IS NOT NULL AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+"  ";
                                                        ExaMax = dMEDSintoma.Consultas(SQL);  
                                       }catch(Exception e){
                                            ExaMax = "1";
                                            e.printStackTrace();
                                        } 
                                                        if(ExaMax == null){
                                                            cCheck1 = "";
                                                            cCheck2 = "checked";
                                                            ExaMax ="1";
                                                        }else{
                                                //System.out.println("ExaMax = "+ExaMax);
                                                max = Integer.parseInt(ExaMax.trim());
                                        try{
                                            SQL = "SELECT LLOGICO FROM EXPRESULTADO WHERE ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente")+" AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+" AND INUMEXAMEN = "+max+"";
                                            Checkbox = dMEDSintoma.Consultas(SQL);
                                            //System.out.println("Checkbox = "+Checkbox);
                                       }catch(Exception e){
                                            Checkbox = "0";
                                            e.printStackTrace();
                                        }
                                            if("1".equals(Checkbox)){
                                                cCheck1 = "checked";
                                                cCheck2 = "";
                                            }else{
                                                cCheck1 = "";
                                                cCheck2 = "checked";
                                            } }
                                        
                                        out.println("Si<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >");
                                        out.println("No<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" >");
                                     }
                                     out.println("</td>");
                                     out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
                               break;
                               case 2: // letras y números
                                    esTexto = true;
                                        String cValor = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cValor = request.getParameter("cCaracter_"+cFlag);
                                        }
                                        
                                          try{
                                           SQL = "SELECT MAX(INUMEXAMEN) FROM EXPRESULTADO " +
                                                       "WHERE ICVEEXPEDIENTE  = "+request.getParameter("iCveExpediente")+" " +
                                                       " AND CCARACTER IS NOT NULL AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+"  ";
                                                        ExaMax = dMEDSintoma.Consultas(SQL);  
                                       }catch(Exception e){
                                            ExaMax = "1";
                                            e.printStackTrace();
                                        }
                                                        if(ExaMax == null){
                                                            cValor="";
                                                            ExaMax ="1";
                                                        }else{
                                                        //System.out.println("ExaMax = "+ExaMax);
                                                        max = Integer.parseInt(ExaMax.trim());
                                         try{
                                            SQL = "SELECT CCARACTER FROM EXPRESULTADO WHERE ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente")+" AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+" AND INUMEXAMEN = "+max+"";
                                            cValor = dMEDSintoma.Consultas(SQL);
                                            //cVal="Nuevo Valor";
                                       }catch(Exception e){
                                            cValor = "";
                                            e.printStackTrace();
                                        }
                                            }
                                        
                                        
                                    out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                    out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cValor+"</textarea></td>");
                               break;
                               case 3: // números
                                    esNumero = true;
                                     if(cFlag.equals("17")){
                                        out.print("<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_17\"");
                                        out.print("value=\"\"");
                                        out.print("onfocus=\"this.select();\"");
                                        out.print("onBlur=\"validaNumero(this);\"");
                                        out.print("onMouseOut=\"if (window.fOutField) window.fOutField();\"");
                                        out.print("onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
                                        out.print("onChange=\"IMC(this.value);\"></td>");
                                    }else{
                                    out.print("<td class='ECampo' colspan='1'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");}
                                    if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                        out.print("<td class='ECampo' colspan='2'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                                        out.println(vEti.Texto("ETabla", bs.getFieldValue("cValRef","&nbsp;") + ""));
                                        out.print("<input type='hidden' name='cValRef_" + c + "' value='" + bs.getFieldValue("cValRef","&nbsp;") + "'>");
                                    }
                                    else
                                    out.print("<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                               break;
                               case 4: // notas
                                    esTexto = true;
                                        String cVal = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cVal = request.getParameter("cCaracter_"+cFlag);
                                        }
                                   
                                    try{
                                        SQL = "SELECT MAX(INUMEXAMEN) FROM EXPRESULTADO " +
                                                                           "WHERE ICVEEXPEDIENTE  = "+request.getParameter("iCveExpediente")+" " +
                                                                           " AND CCARACTER IS NOT NULL AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+"  ";
                                                                            ExaMax = dMEDSintoma.Consultas(SQL);  
                                   }catch(Exception e){
                                            ExaMax = "1";
                                            e.printStackTrace();
                                   }
                                                                            if(ExaMax == null){
                                                                                ExaMax ="1";
                                                                                cVal="";
                                                                            }else{
                                                                            //System.out.println("ExaMax = "+ExaMax);
                                                                            max = Integer.parseInt(ExaMax.trim());
                                    try{
                                            SQL = "SELECT CCARACTER FROM EXPRESULTADO WHERE ICVEEXPEDIENTE = "+request.getParameter("iCveExpediente")+" AND ICVESERVICIO = "+request.getParameter("iCveServicio")+" AND ICVERAMA = "+bs.getFieldValue("iCveRama")+" AND ICVESINTOMA = "+iCveSintoma+" AND INUMEXAMEN = "+max+"";
                                            cVal = dMEDSintoma.Consultas(SQL);
                                            //cVal="Nuevo Valor";
                                   }catch(Exception e){
                                            cVal = "";
                                            e.printStackTrace();
                                   }
                                        }
                                        
                                                                            
                                    out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                    out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cVal+"</textarea></td>");
                               break;
                               case 5: // rango de números
                                    esRango = true;
                                        String cValor1 = "";
                                        String cValor2 = "";
                                        if(request.getParameter("dValorI_"+cFlag) != null && request.getParameter("dValorI_"+cFlag).trim().length() > 0){
                                          cValor1 = request.getParameter("dValorI_"+cFlag);
                                        }
                                        if(request.getParameter("dValorF_"+cFlag) != null && request.getParameter("dValorF_"+cFlag).trim().length() > 0){
                                          cValor2 = request.getParameter("dValorF_"+cFlag);
                                        }

                                    out.print("<td class='ECampo'>Entre</td>");
                                    out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,cValor1,0,"","validaNumero(this);",true, true)+"</td>");
                                    out.print("<td class='ECampo'>y</td>");
                                    out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorF_"+cFlag,cValor2,0,"","validaNumero(this);",true, true)+"</td>");
                               break;
                               case 6: // titulo
                                    out.println("<td class=\"ETablaSTC\" colspan='6'>" + bs.getFieldValue("cPregunta","&nbsp;") + "</td>");
                                    out.print("<input type='hidden' name='hdTitulo_" + d + "' value='" + bs.getFieldValue("cPregunta","&nbsp;") + "'>");
                                    d++;
                               break;
                               default:
                                    out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
                            }
                               //System.out.println("Rastreo 12");
                             
                             }
                             else{
                             
                             switch (tpoResp) {
                               case 1: // si/no
                                    esCheck = true;
                                    out.println("<td class='ECampo'>");
                                    if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }

                                        out.println("(+)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >  ");
                                        out.println("(-)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" >  ");
                                     }
                                     else{
                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }
                                        out.println("Si<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >");
                                        out.println("No<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" >");
                                     }
                                     out.println("</td>");
                                     out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
                               break;
                               case 2: // letras y números
                                    esTexto = true;
                                        String cValor = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cValor = request.getParameter("cCaracter_"+cFlag);
                                        }
                                    out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                    out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cValor+"</textarea></td>");
                               break;
                               case 3: // números
                                    esNumero = true;
                                     if(cFlag.equals("17")){
                                        out.print("<td class='ECampo' colspan='1'><input type=\"ETablaC\" size=\"10\" maxlength=\"10\" name=\"dValorI_17\"");
                                        out.print("value=\"\"");
                                        out.print("onfocus=\"this.select();\"");
                                        out.print("onBlur=\"validaNumero(this);\"");
                                        out.print("onMouseOut=\"if (window.fOutField) window.fOutField();\"");
                                        out.print("onMouseOver=\"if (window.fOverField) window.fOverField(0);\"");
                                        out.print("onChange=\"IMC(this.value);\"></td>");
                                    }else{
                                    out.print("<td class='ECampo' colspan='1'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");}
                                    if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                        out.print("<td class='ECampo' colspan='2'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                                        out.println(vEti.Texto("ETabla", bs.getFieldValue("cValRef","&nbsp;") + ""));
                                        out.print("<input type='hidden' name='cValRef_" + c + "' value='" + bs.getFieldValue("cValRef","&nbsp;") + "'>");
                                    }
                                    else
                                    out.print("<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                               break;
                               case 4: // notas
                                    esTexto = true;
                                        String cVal = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cVal = request.getParameter("cCaracter_"+cFlag);
                                        }
                                    out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                    out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cVal+"</textarea></td>");
                               break;
                               case 5: // rango de números
                                    esRango = true;
                                        String cValor1 = "";
                                        String cValor2 = "";
                                        if(request.getParameter("dValorI_"+cFlag) != null && request.getParameter("dValorI_"+cFlag).trim().length() > 0){
                                          cValor1 = request.getParameter("dValorI_"+cFlag);
                                        }
                                        if(request.getParameter("dValorF_"+cFlag) != null && request.getParameter("dValorF_"+cFlag).trim().length() > 0){
                                          cValor2 = request.getParameter("dValorF_"+cFlag);
                                        }

                                    out.print("<td class='ECampo'>Entre</td>");
                                    out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,cValor1,0,"","validaNumero(this);",true, true)+"</td>");
                                    out.print("<td class='ECampo'>y</td>");
                                    out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorF_"+cFlag,cValor2,0,"","validaNumero(this);",true, true)+"</td>");
                               break;
                               case 6: // titulo
                                    out.println("<td class=\"ETablaSTC\" colspan='6'>" + bs.getFieldValue("cPregunta","&nbsp;") + "</td>");
                                    out.print("<input type='hidden' name='hdTitulo_" + d + "' value='" + bs.getFieldValue("cPregunta","&nbsp;") + "'>");
                                    d++;
                               break;
                               default:
                                    out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
                            }
                          }// Validacion Medicina General
                            c++;
                          }// De Validación de EPIServicioOdonto
                          else{
                                //System.out.println("condicion 2");
                          //Despliegue Odontograma
                             if (c == 0)
                                out.print("<table align ='center'><tr>");
                             %>
                             <input type="hidden" name="iCveTpoResp_<%=cFlag%>" value="<%=iCveTpoResp%>">
                             <input type="hidden" name="iCveSintoma_<%=cFlag%>" value="<%=iCveSintoma%>">
                              <%
                              
                              //System.out.println("Rastreo 13");
                              
                              
                             if (c < 32){
                                //System.out.println("condicion 3");
                                switch (tpoResp) {
                                   case 1: // si/no
                                        esCheck = true;
                                        out.println("<td class='ECampo'>");
                                        if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }
                                        out.println("(+)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" > ");
                                        out.println("(-)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" > ");
                                        }
                                        else{

                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }
                                        out.println("Si<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >");
                                        out.println("No<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+">");
                                        }
                                        out.println("</td>");
                                        out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
                                   break;
                                   case 2: // letras y números
                                        esTexto = true;
                                        String cValor = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cValor = request.getParameter("cCaracter_"+cFlag);
                                        }
                                        out.println("<td class=\"ECampo\"><textarea cols=\"5\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                        out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cValor+"</textarea></td>");
                                   break;
                                   case 3: // números
                                        esNumero = true;
                                        out.print("<td class='ECampo' colspan='1'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");
                                        if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                            out.print("<td class='ECampo' colspan='2'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                                            out.println(vEti.Texto("ETabla", bs.getFieldValue("cValRef","&nbsp;") + ""));
                                            out.print("<input type='hidden' name='cValRef_" + cFlag + "' value='" + bs.getFieldValue("cValRef","&nbsp;") + "'>");
                                        }
                                        else
                                            out.print("<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                                   break;
                                   case 4: // notas
                                        esTexto = true;
                                        String cVal = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cVal = request.getParameter("cCaracter_"+cFlag);
                                        }
                                        out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                        out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cVal+"</textarea></td>");
                                   break;
                                   case 5: // rango de números
                                        esRango = true;
                                        String cValor1 = "";
                                        String cValor2 = "";
                                        if(request.getParameter("dValorI_"+cFlag) != null && request.getParameter("dValorI_"+cFlag).trim().length() > 0){
                                          cValor1 = request.getParameter("dValorI_"+cFlag);
                                        }
                                        if(request.getParameter("dValorF_"+cFlag) != null && request.getParameter("dValorF_"+cFlag).trim().length() > 0){
                                          cValor2 = request.getParameter("dValorF_"+cFlag);
                                        }
                                        out.print("<td class='ECampo'>Entre</td>");
                                        out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag, cValor1,0,"","validaNumero(this);",true, true)+"</td>");
                                        out.print("<td class='ECampo'>y</td>");
                                        out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorF_"+cFlag, cValor2,0,"","validaNumero(this);",true, true)+"</td>");
                                   break;
                                   case 6: // titulo
                                        out.println("<td class=\"ETablaSTC\" colspan='6'>" + bs.getFieldValue("cPregunta","&nbsp;") + "</td>");
                                        out.print("<input type='hidden' name='hdTitulo_" + d + "' value='" + bs.getFieldValue("cPregunta","&nbsp;") + "'>");
                                        d++;
                                   break;
                                   default:
                                         out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
                               }
                               c++;
                               if (c == 8 || c == 24){
                                    out.print("<td>&nbsp;&nbsp;&nbsp;</td>");
                                    cOdonto = cOdonto + "<td>&nbsp;&nbsp;&nbsp;</td>";
                               }
                               String cLinea = "";
                               if (c == 16 || c == 32 ){
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    out.print(cOdonto);
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    cOdonto = "";
                               }
                          } // validación < 33
                          else{ // Despliegue normal !!!!!
                                //System.out.println("condicion 4");
                             if (c == 32)
                                  out.print("<table class='ETabla' align ='center' background='"+ vParametros.getPropEspecifica("RutaImg") +"fondo01.jpg' width='100%' height='100'>");
                                  out.print("<tr>");
                             if(tpoResp != 6)
                                  out.print("<td class='EEtiqueta' colspan='2' width='30%'>" + bs.getFieldValue("cPregunta","&nbsp;").toString() + "</td>");
                             switch (tpoResp) {
                                  case 1: // si/no
                                       esCheck = true;
                                       out.println("<td class='ECampo'>");
                                       if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }

                                        out.println("(+)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >");
                                        out.println("(-)<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" >");
                                       }
                                       else{
                                        String cCheck1 = "";
                                        String cCheck2 = "checked";
                                        if(request.getParameter("lLogico_"+cFlag) != null && request.getParameter("lLogico_"+cFlag).equals("1")){
                                          cCheck1 = "checked";
                                          cCheck2 = "";
                                        }
                                        out.println("Si<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"1\" "+cCheck1+" >");
                                        out.println("No<input type=\"radio\" name=\"lLogico_"+cFlag+"\" value=\"0\" "+cCheck2+" >");
                                       }
                                       out.println("</td>");
                                       out.print("<td class='ECampo' colspan='3'>&nbsp;</td>");
                                  break;
                                  case 2: // letras y números
                                       esTexto = true;
                                        String cValor = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cValor = request.getParameter("cCaracter_"+cFlag);
                                        }
                                       out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                       out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cValor+"</textarea></td>");
                                  break;
                                  case 3: // números
                                       esNumero = true;
                                       out.print("<td class='ECampo' colspan='1'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag,"",0,"","validaNumero(this);",true, true)+"</td>");
                                       if(iCveLabC==Integer.parseInt(request.getParameter("iCveServicio"))){
                                           out.print("<td class='ECampo' colspan='2'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                                           out.println(vEti.Texto("ETabla", bs.getFieldValue("cValRef","&nbsp;") + ""));
                                           out.print("<input type='hidden' name='cValRef_" + cFlag + "' value='" + bs.getFieldValue("cValRef","&nbsp;") + "'>");
                                       }
                                       else
                                           out.print("<td class='ECampo' colspan='3'>"+bs.getFieldValue("cEtiqueta","&nbsp;")+"</td>");
                                  break;
                                  case 4: // notas
                                       esTexto = true;
                                        String cVal = "";
                                        if(request.getParameter("cCaracter_"+cFlag) != null && request.getParameter("cCaracter_"+cFlag).trim().length() > 0){
                                          cVal = request.getParameter("cCaracter_"+cFlag);
                                        }
                                       out.println("<td class=\"ECampo\" colspan='4'><textarea cols=\"50\" rows=\"2\" name=\"cCaracter_"+cFlag+"\""); //
                                       out.println(" onChange=\"javascript:truncar(this)\" onBlur=\"fMayus(this);\">"+cVal+"</textarea></td>");
                                  break;
                                  case 5: // rango de números
                                       esRango = true;
                                        String cValor1 = "";
                                        String cValor2 = "";
                                        if(request.getParameter("dValorI_"+cFlag) != null && request.getParameter("dValorI_"+cFlag).trim().length() > 0){
                                          cValor1 = request.getParameter("dValorI_"+cFlag);
                                        }
                                        if(request.getParameter("dValorF_"+cFlag) != null && request.getParameter("dValorF_"+cFlag).trim().length() > 0){
                                          cValor2 = request.getParameter("dValorF_"+cFlag);
                                        }
                                       out.print("<td class='ECampo'>Entre</td>");
                                       out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorI_"+cFlag, cValor1,0,"","validaNumero(this);",true, true)+"</td>");
                                       out.print("<td class='ECampo'>y</td>");
                                       out.print("<td class='ECampo'>"+vEti.CampoSinCelda("ETablaC",10,10,"dValorF_"+cFlag, cValor2,0,"","validaNumero(this);",true, true)+"</td>");
                                  break;
                                  case 6: // titulo
                                       out.println("<td class=\"ETablaSTC\" colspan='6'>" + bs.getFieldValue("cPregunta","&nbsp;") + "</td>");
                                       out.print("<input type='hidden' name='hdTitulo_" + d + "' value='" + bs.getFieldValue("cPregunta","&nbsp;") + "'>");
                                       d++;
                                  break;
                                  default:
                                       out.print("<td class='ECampo' colspan='4'>&nbsp;</td>");
                            }
                            c++;
                      }
                             //System.out.println("Rastreo 14");
             }
              cAux += cFlag+"|";
    }

    out.print("</tr>");
    if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("iCveServicio")) ||
        vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("iCveServicio"))) {
    out.print("</tr>");
%>
   <td colspan='6' align='center'><%= vEti.clsAnclaTexto("EAncla","Oido Derecho","JavaScript:fAudio('Derecho');", "") %>
                   <%= vEti.clsAnclaTexto("EAncla","Oido Izquierdo","JavaScript:fAudio2('Izquierdo');", "") %></td>
   </tr><tr align='center'>
   <%
   TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
   TVEXPAudiomet vEXPAudiomet;
   Vector vcAudio = new Vector();
   //System.out.println("Rastreo 15");
////Try catch para imprimir el error   
try{
//    vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + request.getParameter("iCveExpediente") + " and iNumExamen = " + request.getParameter("iNumExamen") );

String condicion = " where iCveExpediente = " + request.getParameter("iCveExpediente") + " and iNumExamen = " + request.getParameter("iNumExamen") +" and iCveServicio = " + request.getParameter("iCveServicio");
    vcAudio = dEXPAudiomet.FindByAll(condicion);
    if (vcAudio.size() > 0){
          if (request.getParameter("hdBoton").equals("AudioMuestra") && !(request.getParameter("hdGOido").equals("null"))){
          out.println("<SCRIPT LANGUAGE='JavaScript'>");
           for (int i = 0; i < vcAudio.size(); i++){
               vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
                out.println("aSel["+i+"]=["+vEXPAudiomet.getIOido()+","+vEXPAudiomet.getITipo()+
                                        ","+vEXPAudiomet.getIX()+","+vEXPAudiomet.getIY()+"];");
             }

          out.print("fGraphAudio(" + request.getParameter("hdGOido") + ",aSel);");
          out.print("</SCRIPT>");
          }
    }
 }catch(Exception e){
         vcAudio = new Vector();
         e.printStackTrace();
 }   
    //System.out.println("Rastreo 16");
    
   %>
   <td colspan='6' align='center'><%= vEti.clsAnclaTexto("EAncla","Muestra Derecho ","JavaScript:fAsigna(1);", "") %><%= vEti.clsAnclaTexto("EAncla"," Muestra Izquierdo","JavaScript:fAsigna(2);", "") %></td>
<%
     out.print("</tr>");
    }

    out.print("<table align='center'>");
    out.print("<tr><td>&nbsp;</td></tr>");

    TVGRLUsuario vGRLUsuario = new TVGRLUsuario();
    TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
    Vector vcGRLUsuario = new Vector();
////Try catch para imprimir el error
try{    
    String cSiglas = "";
    vcGRLUsuario = dGRLUsuario.getSiglas(" where GRLUsuario.iCveUsuario = " + vUsuario.getICveusuario());
    if(vcGRLUsuario.size()>0){
      vGRLUsuario = (TVGRLUsuario) vcGRLUsuario.get(0);
      cSiglas = vGRLUsuario.getCSiglas();
    }

    String cNombre = cSiglas + " " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno();
    int iLong = cNombre.length();
    String cLinea = "";
    for(int i=0; i<iLong; i++)
      cLinea +="_";

    out.print("&nbsp;");
    out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cLinea + "</td></tr>");
    out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cNombre + "</td></tr>");
    out.print("</table>");
}
  catch(Exception e){
  vcGRLUsuario = new Vector();
  e.printStackTrace();
}     
%>  <input type="hidden" name="hdTotalRows" value="<%=c%>">
    <input type="hidden" name="hdFlags" value="<%=cAux%>">
<%
                               //System.out.println(c);
                               //System.out.println(cAux);
                               //System.out.println("Rastreo 17");
                               } else {
                                   out.println("<tr>");
                                   out.print(vEti.EtiCampoCS("EEtiqueta", "Mensaje:", "ECampo", "text", 25, 50, 6, "Mensaje", "No existen datos coincidentes con el filtro proporcionado", 3, "", "", true, true, false));
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
