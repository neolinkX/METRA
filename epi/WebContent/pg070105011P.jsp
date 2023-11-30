<%/** 
 * Title: pg070105011
 * Description:
 * Copyright:
 * Company:
 * @author Javier Mendoza
 * @version
 * Clase:
 */%>
<%@ page import="gob.sct.medprev.*" %>
<%@ page import="gob.sct.medprev.vo.*" %>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>

<%

  pg070105011CFG  clsConfig = new pg070105011CFG();                 // modificar
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070105011.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070105011.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070105011.jsp";     // modificar
  String cOperador    = "3";                   // modificar
  String cDscOrdenar  = "No Disponible|";      // modificar
  String cCveOrdenar  = "0|";                  // modificar
  String cDscFiltrar  = "No Disponible|";      // modificar
  String cCveFiltrar  = "0|";                  // modificar
  String cTipoFiltrar = "8|";                  // modificar

  boolean lFiltros    = true;                 // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Reporte";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iEMOServ = new Integer(vParametros.getPropEspecifica("EMOServicio")).intValue();

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();

  clsConfig.outputHeader(vErrores, pageContext, vEntorno, request);

  String cPaginas = clsConfig.getPaginas();
  String cDscPaginas = clsConfig.getDscPaginas();
  PageBeanScroller bs = clsConfig.getBeanScroller();
  String cUpdStatus  = "SaveOnly";
  String cNavStatus  = "Hidden";
  String cOtroStatus = clsConfig.getOtroStatus();
  String cCanWrite   = clsConfig.getCanWrite();
  String cSaveAction = "Guardar";
  String cDeleteAction = "BorrarB";
  String cClave    = "";
  String cPosicion = "";
  //Agrego LAS
  String Categoria ="";


/**
* Listas de Resultados
*/

       Vector vcExpNMedica = new Vector();
       Vector vcExpORes = new Vector();
       Vector vcExpDicExam = new Vector();
       Vector vcExpRecomen = new Vector();
       Vector vcExpDiagnos = new Vector();
       //Agrego LAS
       Vector comparaCat = new Vector();

       vcExpNMedica = clsConfig.listanMedica();
       vcExpORes = clsConfig.ObserRes();
       vcExpDicExam = clsConfig.listaDicSer();
       vcExpRecomen = clsConfig.listaExpRec();
       vcExpDiagnos = clsConfig.listaExpDia();

       TVEXPDictamenServ vExpNMedica = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpORes    = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDicExam = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpRecomen = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDiagnos = new TVEXPDictamenServ();

       TDEXPServicio dEXPServicio = new TDEXPServicio();
       TVEXPServicio vEXPServicio = new TVEXPServicio();
       Vector vcEXPServicio = new Vector();

       TDEXPRama dEXPRama = new TDEXPRama();
       TVEXPRama vEXPRama = new TVEXPRama();
       Vector vcEXPRama = new Vector();

       //Agregado por AG SA 2010-09-25
       TDEXPDiagnostico dEXPDiagnostico = new TDEXPDiagnostico();
       TDEXPFunDictamen dEXPFunDictamen = new TDEXPFunDictamen();

       int iConcluido = 0;
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script LANGUAGE="JavaScript">
////////////////////////////////

////////////////////////////////
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';


  function fOnMin(){
    form = document.forms[0];
    if (form.iCveDiagnostico.value.length > 0 && form.iCveDiagnostico.value != null){
       if(form.iCveDiagnostico.selectedIndex >= <%=vcExpDiagnos.size()%>){
          form.iCveDiagnostico.options [form.iCveDiagnostico.selectedIndex] = null;
       }else{
          alert("Los Diagnosticos YA Grabados NO Pueden ser Eliminados");
       }
    }
    //else{
    //   alert("Debe Seleccionar Primero un Diagnostico");
    //}
  }

  function fOnMinR(){
    form = document.forms[0];
    if (form.iCveRecomendacion.value.length > 0 && form.iCveRecomendacion.value != null){
       if(form.iCveRecomendacion.selectedIndex >= <%=vcExpRecomen.size()%>){
          form.iCveRecomendacion.options [form.iCveRecomendacion.selectedIndex] = null;
       }else{
          alert("Las Recomendaciones YA Grabadas NO Pueden ser Eliminadas");
       }
    }
    //else{
    //   alert("Debe Seleccionar Primero una Recomendacion");
    //}
  }

  function Genera_Doc(){
    form = document.forms[0];
    form.target="_self";
    form.hdBoton.value = 'Imprime Documentacion';
    form.submit();
  }

  function fPrint(){
    window.print();
  }


function fIrCatalogo(page,fieldName,value,fieldName2,value2){
    var form=document.forms[0];
    form.action=page;
    if(value ) form[fieldName ].value=value ;
    if(value2) form[fieldName2].value=value2;
    form.target="FRMDatos";
    form.submit();
  }


  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
</script>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"pg070105011.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"valida_nt.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"buscar_nt.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"t07_Tooltips.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"valida_nt.js"%>"></SCRIPT>



<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cie.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selDiag.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"reco.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selReco.js"%>"></script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
  <style type="text/css">
<!--
.Estilo1 {
	color: #FF0000;
	font-size: 14px;
}
-->
  </style>
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

   <input type="hidden" name="hdICveExpediente" value="<%=request.getParameter("hdICveExpediente")!=null?request.getParameter("hdICveExpediente"):""%>">
  <input type="hidden" name="hdINumExamen" value="<%=request.getParameter("hdINumExamen")!=null?request.getParameter("hdINumExamen"):""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=request.getParameter("hdICveUniMed")!=null?request.getParameter("hdICveUniMed"):""%>">
  <input type="hidden" name="hdICveModulo" value="<%=request.getParameter("hdICveModulo")!=null?request.getParameter("hdICveModulo"):""%>">
  <input type="hidden" name="hdICveProceso" value="<%=request.getParameter("hdICveProceso")!=null?request.getParameter("hdICveProceso"):""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdICveServicio" value="">
  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=request.getParameter("iCveMdoTransSel")%>">


  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iCveProceso" value="<%=request.getParameter("iCveProceso")%>">
  <input type="hidden" name="iCveUniMed" value="<%=request.getParameter("iCveUniMed")%>">
  <input type="hidden" name="iCveModulo" value="<%=request.getParameter("iCveModulo")%>">
  <input type="hidden" name="iCveMdoTrans" value="<%=request.getParameter("iCveMdoTrans")%>">
  <input type="hidden" name="tpoBusqueda" value="<%out.print(request.getParameter("tpoBusqueda"));%>">

  <%

  int iCveExpediente=0;
  int iCveServicio=0;
  int iNumExamen = 0;
  int iCvePersonal = 0;
  int DicNoApto = 0;

  int iCveRama = 0;
  if (request.getParameter("iCveExpediente")!= null && request.getParameter("iCveExpediente").length()>0){
     out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\""+request.getParameter("iCveExpediente")+"\"> \n");
     iCveExpediente = Integer.parseInt(request.getParameter("iCveExpediente").toString());
  }else{
     out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\"1\"> \n");
     iCveExpediente = 1;
  }
  if (request.getParameter("iCveServicio")!= null && request.getParameter("iCveServicio").length()>0){
     out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\""+request.getParameter("iCveServicio")+"\"> \n");
     iCveServicio = Integer.parseInt(request.getParameter("iCveServicio").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\"1\"> \n");
     iCveServicio = 1;
  }

  if(Integer.parseInt(request.getParameter("iCveServicio")) == iEMOServ)
    out.print("<input type=\"hidden\" name=\"iEMOServ\" value=\"1\">");
  else
    out.print("<input type=\"hidden\" name=\"iEMOServ\" value=\"0\">");


   if (request.getParameter("iCveRama")!= null && request.getParameter("iCveRama").length()>0){
     out.print(" <input type=\"hidden\" name=\"iCveRama\" value=\""+request.getParameter("iCveRama")+"\"> \n");
     iCveRama = Integer.parseInt(request.getParameter("iCveRama").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCveRama\" value=\"1\"> \n");
     iCveRama = 1;
  }


  if (request.getParameter("iNumExamen")!= null && request.getParameter("iNumExamen").length()>0){
     out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\""+request.getParameter("iNumExamen")+"\"> \n");
     iNumExamen=Integer.parseInt(request.getParameter("iNumExamen").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\"1\"> \n");
     iNumExamen=1;
  }
  if (request.getParameter("iCvePersonal")!= null && request.getParameter("iCvePersonal").length()>0){
     out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\""+request.getParameter("iCvePersonal")+"\"> \n");
     iCvePersonal=Integer.parseInt(request.getParameter("iCvePersonal").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\"1\"> \n");
     iCvePersonal=1;
  }

  %>

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% if(clsConfig.getAccesoValido()){
       Vector vcEXPDicSer = new Vector();
       Vector vcEXPListaCat = new Vector();
       TVEXPDictamenServ vEXPDicSer = new TVEXPDictamenServ();
       TVEXPDictamenServ vEXPListaCat = new TVEXPDictamenServ();
       vcEXPDicSer = clsConfig.findCatSol();
       vcEXPListaCat = clsConfig.listaCategoria();
       TVPERDatos vPerDatos=clsConfig.findExpediente();
  %>
  <tr><td>&nbsp;</td></tr>
  <tr><td><input type="hidden" name="hdBoton" value="">
      </td>
      <td valign="top">
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="7" class="ETablaT">Datos del Personal
                </td>
             </tr>
             <%
             if(vPerDatos!=null){
               out.print("<tr>");
               out.print("<td class=\"EEtiqueta\">Servicio:</td>");
               out.print("<td class=\"ECampo\" ColSpan=\"5\">"+vPerDatos.getCPersonaAviso()+"</td>");
                  /*Se usa getCPersonaAviso para guardar la descripcion del servicio*/
               out.print("</tr>");
               out.print("<tr>");
               out.print("<td class=\"EEtiqueta\">Nombre:</td>");
               out.print("<td class=\"ECampo\">"+vPerDatos.getCNombre()+" "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()+"</td>");
               out.print("<input type=\"hidden\" name=\"pName\" value=\"" +vPerDatos.getCNombre() + " "+vPerDatos.getCApPaterno()+" "+vPerDatos.getCApMaterno()+"\"'> \n");
               out.print("<td class=\"EEtiqueta\" colspan=\"3\">Expediente:</td>");
               out.print("<td class=\"ECampo\">"+vPerDatos.getICveExpediente()+"</td>");
               out.print("</tr>");
               out.print("<tr>");
               out.print("<td class=\"EEtiqueta\">Edad:</td>");
               out.print("<td class=\"ECampo\">"+clsConfig.getEdad(vPerDatos.getDtNacimiento())+" Años</td>");
               out.print("<td class=\"EEtiqueta\" colspan=\"3\">Sexo:</td>");
               if (vPerDatos.getCSexo().equalsIgnoreCase("M")){
                   out.print("<td class=\"ECampo\">Masculino</td>");
               }else{
                   out.print("<td class=\"ECampo\">Femenino</td>");
                }
               out.print("</tr>");
             }else{
               out.print("<tr>");
               out.print("<td class=\"EResalta\" colspan=\"4\" align=\"center\">Datos de Personal no disponibles</td>");
               out.print("</tr>");
             }

             %>
          </table>&nbsp;

          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="7" class="ETablaT">Categorías Solicitadas
                </td>
             </tr>
             <%
             if(vcEXPDicSer.size()>0){
               String cModo = "";
               out.print("<tr>");
               out.print("<td class=\"ETablaT\">Modo de Transporte</td>");
               out.print("<td class=\"ETablaT\">Grupo</td>");
               out.print("<td class=\"ETablaT\">Categoría</td>");
               out.print("<td class=\"ETablaT\" ColSpan=\"2\">Motivo</td>");
               out.print("<td class=\"ETablaT\" ColSpan=\"2\">Puesto</td>");
               for (int i=0;i<vcEXPDicSer.size();i++){
                  vEXPDicSer = (TVEXPDictamenServ) vcEXPDicSer.get(i);
                  out.print("<tr>");
                  if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(cModo)){
                     out.print("<td class=\"ECampo\">"+vEXPDicSer.getCDscMdoTrans()+"</td>");
                  }else{
                      out.println("<td>&nbsp;</td>");
                  }
                  out.print("<td class=\"ECampo\">"+vEXPDicSer.getCDscGrupo()+"</td>");
                  out.print("<td class=\"ECampo\">"+vEXPDicSer.getCDscCategoria()+"</td>");
                  out.print("<td class=\"ECampo\" ColSpan=\"2\">"+vEXPDicSer.getCDscMotivo()+"</td>");
                  out.print("<input type='hidden' name='hdiCveMotivo' value='" + vEXPDicSer.getICveMotivo() + "'>");
                  out.print("<td class=\"ECampo\" ColSpan=\"2\">"+vEXPDicSer.getCDscPuesto()+"</td>");
                  out.print("</tr>");
                  cModo=vEXPDicSer.getCDscMdoTrans();
               }
             }else{
                  out.print("<tr>");
                  out.print("<td class=\"EResalta\" colspan=\"7\" align=\"center\">Datos de Categorias no disponibles</td>");
                  out.print("</tr>");
             }
             %>
             <tr>
                <td colspan="7" class="ETablaT">Diagnósticos
                </td>
             </tr>
             <%
               out.println("<tr>");
               out.println("<td class=\"ETablaT\" RowSpan=2>Clave CIE-10:</td>");
               out.println("<td RowSpan=2 ColSpan=2>");
               out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveDiagnosticoS\" maxlength=\"20\" size=\"20\" onBlur=\"JavaScript:fMayus(this);\" Value=\"\" >");
               out.println("</td>");
               out.println("<td>" + vEti.clsAnclaTexto("EAncla"," Buscar Frecuentes","javascript:fSelDiag(document.forms[0].iCveDiagnosticoS);", "Buscar Diagnóstico","") + "</td>");
               out.println("<td align=\"center\">");
               out.println("<Input Type=\"Button\" Value=\"Agregar Dx. >\" Name=\"Add\" onClick=\"JavaScript:fOnAdd();\">");
               out.println("</td>");
               out.println("<td RowSpan=2 ColSpan=2>");
               out.println("<select name=\"iCveDiagnostico\" size=\"5\" MULTIPLE>");
               if (vcExpDiagnos.size()>0){
                  for (int d=0;d<vcExpDiagnos.size();d++){
                     vExpDiagnos = (TVEXPDictamenServ)vcExpDiagnos.get(d);
                     out.println("<option value="+vExpDiagnos.getICveDiagnostico()+"_"+vExpDiagnos.getICveEspecialidad()+">"+vExpDiagnos.getCDscDiagnostico()+"</option>");
                  }
               }
               out.println("</select");
               out.println("</td>");
               out.println("</tr>");
               out.println("<tr>");
               out.println("<td>" + vEti.clsAnclaTexto("EAncla","Buscar un específico","javascript:fBuscarCIE10('');","Buscar Diagnóstico en el catálogo completo","aDiag") + "</td>");
               out.println("<td align=\"center\">");
               out.println("<Input Type=\"Button\" Value=\"< Quitar Dx.\" Name=\"Min\" onClick=\"JavaScript:fOnMin();\">");
               out.println("</td>");
               out.println("</tr>");
             %>
             <tr>
                <td colspan="7" class="ETablaT">Recomendación
                </td>
             </tr>
             <%
               out.println("<tr>");
               out.println("<td class=\"ETablaT\" RowSpan=3>Recomendación:</td>");
               out.println("<td RowSpan=2  ColSpan=2>");
               out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveRecomendacionS\" maxlength=\"20\" size=\"20\" onBlur=\"JavaScript:fMayus(this);\" Value=\"\" >");
               out.println("</td>");
               out.println("<td RowSpan=2>");
               out.println(vEti.clsAnclaTexto("EAncla","Buscar","javascript:fSelReco(document.forms[0].iCveRecomendacionS);", "Buscar Recomendación",""));
               out.println("</td>");
               out.println("<td align=\"center\">");
               out.println("<Input Type=\"Button\" Value=\"Agregar Rec. >\" Name=\"Add\" onClick=\"JavaScript:fOnAddR();\">");
               out.println("</td>");
               out.println("<td RowSpan=2 ColSpan=2>");
               out.println("<select name=\"iCveRecomendacion\" size=\"5\" MULTIPLE>");
               if (vcExpRecomen.size()>0){
                  for (int c=0;c<vcExpRecomen.size();c++){
                     vExpRecomen = (TVEXPDictamenServ)vcExpRecomen.get(c);
                     out.println("<option value="+vExpRecomen.getICveRecomendacion()+"_"+vExpRecomen.getICveEspecialidad()+">"+vExpRecomen.getCDscRecomendacion()+"</option>");
                  }
               }
               out.println("</td>");
               out.println("</tr>");
               out.println("<tr>");
               out.println("<td align=\"center\">");
               out.println("<Input Type=\"Button\" Value=\"< Quitar Rec.\" Name=\"Min\" onClick=\"JavaScript:fOnMinR();\">");
               out.println("</td>");
               out.println("</tr>");
             %>
             <tr>
                <td colspan="7" class="ETablaT">Dictamen por Categoría
                </td>
             </tr>
             <%
                     if(vcExpDicExam.size()>0){
                        String cModob = "";
                        for (int b=0;b<vcExpDicExam.size();b++){

                           vExpDicExam = (TVEXPDictamenServ)vcExpDicExam.get(b);

                           out.println("<tr>");
                           if (!vExpDicExam.getCDscMdoTrans().equalsIgnoreCase(cModob)){
                              out.println("<td class=\"ETablaT\">"+vExpDicExam.getCDscMdoTrans()+"</td>");
                           }else{
                              out.println("<td class=\"ETablaT\">&nbsp;</td>");
                           }
                           out.println("<td class=\"ETablaT\">"+vExpDicExam.getCDscCategoria()+"");
                           out.println("<td align=\"right\">");
                           if(vExpDicExam.getLDictamen() == 1){
                              out.println("<input type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\" Checked Disabled>");
                              out.println("<input type=\"hidden\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\">");
                           }else{
                              out.println("<input type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"1\"  Disabled>");
                           }
                           out.println("</td>");
                           out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                           out.println("Apto");
                           out.println("</td>");
                           out.println("<td align=\"right\">");
                           if(vExpDicExam.getLDictamen() == 0){
                              out.println("<input type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\" Checked  Disabled>");
                              out.println("<input type=\"hidden\" name=\"lDictamen"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\">");
                              DicNoApto++;
                           }else{
                              out.println("<input align=\"right\" type=\"radio\" name=\"lDictamen_X"+vExpDicExam.getICveCategoria()+"_"+vExpDicExam.getICveMdoTrans()+"\" value=\"0\"  Disabled>");
                           }
                           out.println("</td>");
                           out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                           out.println("No Apto");
                           out.println("</td>");
                           out.println("</tr>");
                           cModob=vExpDicExam.getCDscMdoTrans();
                        }
                     }else{
/*               if (vcEXPListaCat.size() > 0){
                  for (int j=0;j<vcEXPListaCat.size();j++){
                     vEXPListaCat = (TVEXPDictamenServ) vcEXPListaCat.get(j);*/
                 if (vcEXPDicSer.size() > 0){
                  String cModoc = "";
                  for (int i=0;i<vcEXPDicSer.size();i++){
                     vEXPDicSer = (TVEXPDictamenServ) vcEXPDicSer.get(i);
                     // Agrego Marco A. González Paz
                     // Aplica solo para el EMO !!!!
                     out.println("<input type=\"hidden\" name=\"iCveCategoria\" value='"+vEXPDicSer.getICveCategoria()+"'>");
                     //

                     //Agrego LAS
                     Categoria = vEXPDicSer.getCDscCategoria();
                     comparaCat.insertElementAt(Categoria, i);
                     int f = i-1;
                     if(i>0){
                     Categoria = (String)comparaCat.elementAt(f);
                     }
                     if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(cModoc)){
                        out.println("<tr>");
                        out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscMdoTrans()+"</td>");

                         out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscCategoria()+"");
                         out.println("<td align=\"right\">");
                         out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"1\">");
                         out.println("</td>");
                         out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                         out.println("Apto");
                         out.println("</td>");
                         out.println("<td align=\"right\">");
                         out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"0\">");
                         out.println("</td>");
                         out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                         out.println("No Apto");
                         out.println("</td>");
                         out.println("</tr>");
                         cModoc=vEXPDicSer.getCDscMdoTrans();


                     }else{
                        if(!vEXPDicSer.getCDscCategoria().equalsIgnoreCase(Categoria)){
                             out.println("<tr>");
                             out.println("<td class=\"ETablaT\">&nbsp;</td>");

                             out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscCategoria()+"");
                             out.println("<td align=\"right\">");
                             out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"1\">");
                             out.println("</td>");
                             out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                             out.println("Apto");
                             out.println("</td>");
                             out.println("<td align=\"right\">");
                             out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"0\">");
                             out.println("</td>");
                             out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                             out.println("No Apto");
                             out.println("</td>");
                             out.println("</tr>");
                             cModoc=vEXPDicSer.getCDscMdoTrans();
                     }

                     }


                  }
               }
            }
             %>
             <tr>
                <td colspan="7" class="ETablaT">Nota Médica
                </td>
             </tr>
             <%
             if (vcExpNMedica.size() > 0){
                for (int a=0;a<vcExpNMedica.size();a++){
                     vExpNMedica = (TVEXPDictamenServ) vcExpNMedica.get(a);
                     if(vExpNMedica.getCNotaMedica() != null && !vExpNMedica.getCNotaMedica().toString().equalsIgnoreCase("null")){
                       if(vExpNMedica.getCNotaMedica().length() >0){
                          out.print(vEti.EtiAreaTextoCS("EEtiqueta","Nota Médica:","ECampo",70,10,6,"cNotaMedicaH", vExpNMedica.getCNotaMedica(),0,"","fMayus(this);",false,false,false));
                          out.println("<input type=\"hidden\" name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\">");

                          // - - Permite ver el examen en su totalidad
                          /*if(request.getParameter("tpoBusqueda").compareTo("unMedico")==0){
                            vcEXPServicio = dEXPServicio.getLConcluido(request.getParameter("iCveExpediente"), request.getParameter("iNumExamen"), request.getParameter("iCveServicio"));
                            if(vcEXPServicio.size()>0){
                              vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                              iConcluido = vEXPServicio.getLConcluido();
                            }
                            if(iConcluido == 1){
                              out.println("<tr>");
                              out.println("<td colspan=7 align='center'>");
                              out.print(vEti.clsAnclaTexto("EAncla","Servicio Concluido - Ver/Imprimir Resultados del Servicio","JavaScript:fVerExamen(" + request.getParameter("iCveExpediente") + ", " + request.getParameter("iNumExamen") + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                              out.println("</td>");
                              out.println("</tr>");
                            }
                          }
                          else if(request.getParameter("tpoBusqueda").compareTo("variosMedicos")==0){
                           vcEXPRama = dEXPRama.getLConcluido(request.getParameter("iCveExpediente"), request.getParameter("iNumExamen"), request.getParameter("iCveServicio"), request.getParameter("iCveRama"));
                           if(vcEXPRama.size()>0){
                             vEXPRama = (TVEXPRama) vcEXPRama.get(0);
                             iConcluido = vEXPRama.getIConcluido();
                           }
                           if(iConcluido == 1){
                             out.println("<tr>");
                             out.println("<td colspan=7 align='center'>");
                             out.print(vEti.clsAnclaTexto("EAncla","Rama Concluida. Ver/Imprimir Resultados de la Rama","JavaScript:fVerExamen(" + request.getParameter("iCveExpediente") + ", " + request.getParameter("iNumExamen") + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveRama") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                             out.println("</td>");
                             out.println("</tr>");
                           }
                          }*/
                          // - -

                       }else{
                          out.print(vEti.Texto("EEtiquetaC","Nota Médica:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                          out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                       }
                     }else{
                          out.print(vEti.Texto("EEtiquetaC","Nota Médica:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                          out.println("NINGUNA</textarea>");
                          //out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                     }
                }
             }else{
                          out.print(vEti.Texto("EEtiquetaC","Nota Médica:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                          out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
             }

               ///////// Agregando nuevo campo de observaciones
             %>


             <tr>
                <td colspan="7" class="ETablaT">Observaciones/Restricciones
                </td>
             </tr>
             <%
             if (vcExpORes.size() > 0){
                for (int a=0;a<vcExpORes.size();a++){
                     vExpORes = (TVEXPDictamenServ) vcExpORes.get(a);
                     if(vExpORes.getCObserRes() != null && !vExpORes.getCObserRes().toString().equalsIgnoreCase("null")){
                       if(vExpORes.getCObserRes().length() >0){
                          out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones/Restricciones:","ECampo",70,10,6,"cObserResH", vExpORes.getCObserRes(),0,"","fMayus(this);",false,false,false));
                          out.println("<input type=\"hidden\" name=\"cObserRes\" value=\""+vExpORes.getCObserRes()+"\">");

                          // - - Permite ver el examen en su totalidad
                          /*if(request.getParameter("tpoBusqueda").compareTo("unMedico")==0){
                            vcEXPServicio = dEXPServicio.getLConcluido(request.getParameter("iCveExpediente"), request.getParameter("iNumExamen"), request.getParameter("iCveServicio"));
                            if(vcEXPServicio.size()>0){
                              vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                              iConcluido = vEXPServicio.getLConcluido();
                            }
                            if(iConcluido == 1){
                              out.println("<tr>");
                              out.println("<td colspan=7 align='center'>");
                              out.print(vEti.clsAnclaTexto("EAncla","Servicio Concluido - Ver/Imprimir Resultados del Servicio","JavaScript:fVerExamen(" + request.getParameter("iCveExpediente") + ", " + request.getParameter("iNumExamen") + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                              out.println("</td>");
                              out.println("</tr>");
                            }
                          }
                          else if(request.getParameter("tpoBusqueda").compareTo("variosMedicos")==0){
                           vcEXPRama = dEXPRama.getLConcluido(request.getParameter("iCveExpediente"), request.getParameter("iNumExamen"), request.getParameter("iCveServicio"), request.getParameter("iCveRama"));
                           if(vcEXPRama.size()>0){
                             vEXPRama = (TVEXPRama) vcEXPRama.get(0);
                             iConcluido = vEXPRama.getIConcluido();
                           }
                           if(iConcluido == 1){
                             out.println("<tr>");
                             out.println("<td colspan=7 align='center'>");
                             out.print(vEti.clsAnclaTexto("EAncla","Rama Concluida. Ver/Imprimir Resultados de la Rama","JavaScript:fVerExamen(" + request.getParameter("iCveExpediente") + ", " + request.getParameter("iNumExamen") + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveRama") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                             out.println("</td>");
                             out.println("</tr>");
                           }
                          }*/
                          // - -

                       }else{
                          out.print(vEti.Texto("EEtiquetaC","Observaciones/Restricciones:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cObserRes\" value=\""+vExpORes.getCObserRes()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                          out.println(vExpORes.getCObserRes()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                       }
                     }else{
                          out.print(vEti.Texto("EEtiquetaC","Observaciones/Restricciones:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cObserRes\" value=\""+vExpORes.getCObserRes()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                          out.println("NINGUNA</textarea>");
                          //out.println(vExpORes.getCObserRes()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                     }
                }
             }else{
                          out.print(vEti.Texto("EEtiquetaC","Observaciones/Restricciones:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cObserRes\" value=\""+vExpORes.getCObserRes()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                          out.println(vExpORes.getCObserRes()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
             }

%>

          </table>

<%
/*Vector motivos = new Vector();
Vector diagnos = new Vector();
Vector especial = new Vector();*/
String motivacion ="";
String fundamentacion ="";
String Query ="";

//if(vcExpDicExam.size()>0){
//if(vExpDicExam.getLDictamen() == 0){
if(DicNoApto > 0){
%> 

    <br><br>
              <div align="center">
                <span class="Estilo1"> Favor de capturar la siguiente información para poder generar su constancia de no aptitud. </span></span></div>
     <br>
        <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr>
                 <td colspan="7" class="ETablaT">Motivación:</td>
      </tr> 
<%
 if (vcExpDiagnos.size()>0){


%>                         <script LANGUAGE="JavaScript">
                                   function ValidaMotFun(form){
                                       form = document.forms[0];
				       var msg='';

 <%                 for (int d=0;d<vcExpDiagnos.size();d++){
                     vExpDiagnos = (TVEXPDictamenServ)vcExpDiagnos.get(d);
                     //int iLongM = vExpDiagnos.getCMotivacion().length();
                     if(!(vExpDiagnos.getCMotivacion() != null)){
                                if(request.getParameter("cFundamentacion")!=null){
//                                        String campo = request.getParameter("x"+vExpDiagnos.getICveDiagnostico()+"_"+vExpDiagnos.getICveEspecialidad()+"");
                                        motivacion = request.getParameter("x"+vExpDiagnos.getICveDiagnostico()+"_"+vExpDiagnos.getICveEspecialidad()+"");  

                                                 Query = "update expdiagnostico "+
                                                         "   set cmotivacion = '"+motivacion+"'"+
                                                         "   where "+
                                                         "           icveexpediente =   "+iCveExpediente+"	and"+
                                                         "           inumexamen =       "+iNumExamen+"	and"+
                                                         "           icveservicio =     "+iCveServicio+"	and"+
                                                         //"           icveservicio =   31	and"+
                                                         "           icveespecialidad = "+vExpDiagnos.getICveEspecialidad()+"	and"+
                                                         "           icvediagnostico = 	"+vExpDiagnos.getICveDiagnostico()+"";

                                                // System.out.println(Query);
                                                 //Conexion al DAO para agregar Motivacion por diagnostico
                                                 try{
                                                    dEXPDiagnostico.updateQuery(null, Query);
                                                 }catch(Exception e){
                                                   //System.out.println("La insercion de la Motivacion a fallado \n"+Query);
                                                   e.printStackTrace();
                                                 }


                                    }
%>
                                form = document.forms[0];
                                if(form.x<%=vExpDiagnos.getICveDiagnostico()%>_<%=vExpDiagnos.getICveEspecialidad()%>.value.length == 0){
                                    msg += 'Favor de ingresar el Motivo <%=d+1%>\n';
                                }
    <%                      }
                    }

                                            //Conexion al DAO para agregar Motivacion por diagnostico
                                                 try{
                                                     int serivicio = 31;
                                                     int dictamen = 0;
                                                      dEXPFunDictamen.insert(null, iCveExpediente, iNumExamen, serivicio, dictamen, request.getParameter("cFundamentacion"));
                                                 }catch(Exception e){
                                                   //System.out.println("La insercion de la Fundamentacion a fallado \n"+Query);
                                                   e.printStackTrace();
                                                 }


  %>
                               if(form.cFundamentacion.value.length == 0){
                                    msg += 'Favor de ingresar la Fundamentación';
                                }

                                if(msg !=''){
                                        alert( msg);
                                }else{
                               form.submit();
                           }
                    }
              </script>
<% 
                for (int d=0;d<vcExpDiagnos.size();d++){
                     vExpDiagnos = (TVEXPDictamenServ)vcExpDiagnos.get(d);
                     if(vExpDiagnos.getCMotivacion() != null){
%>
                           <tr>
                            <td class="EEtiquetaC">Diagnostico:</td>
                            <td class="EEtiquetaC"><%=vExpDiagnos.getCDscDiagnostico()%></td>
                            <td class="EEtiquetaC">Motivacion</td>
                            <td class="EEtiquetaC"><%=vExpDiagnos.getCMotivacion()%></td>
                           </tr>
<%
                     }
                     else{
                     %>
                           <tr>
                            <td class="EEtiquetaC">Diagnostico:</td>
                            <td class="EEtiquetaC"><%=vExpDiagnos.getCDscDiagnostico()%></td>
                            <td class="EEtiquetaC">Motivacion</td>
                            <td><input name="x<%=vExpDiagnos.getICveDiagnostico()%>_<%=vExpDiagnos.getICveEspecialidad()%>" type="text" value="" Size="100">&nbsp;</td>
                           </tr>
<%                      }
              }
       }

%>
    </table>

    <br>
    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3">
      <tr>
                 <td colspan="7" class="ETablaT">Fundamentación:</td>
      </tr>
      <tr>
        <td class="EEtiquetaC">Fundamentacion:</td>
        <td>
            <textarea cols="70" rows="4"  name="cFundamentacion" value="" onkeypress="fChgArea(this);"
                      onchange="fChgArea(this);" OnBlur="fMayus(this);"></textarea>	</td>
      </tr>
</table>

<br>
<center>
    <input name="Agregar" type="button" value="Enviar" onClick="ValidaMotFun(this.form)">
    <br>
    <a class="EAncla" name="" href="JavaScript:ValidaMotFun(this.form);"
        onMouseOut="self.status='';return true;"
        onMouseOver="self.status='Ver Examen';return true;">enviar</a>
</center>
<br>
<%
//    }
//}
}

%>


<%

          out.print("<table align='center'>");
          out.print("<tr><td>&nbsp;</td></tr>");
          String cNombre = "Dr(a). " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno();
          cNombre = clsConfig.nombreMedicoDictaminador(iCveExpediente, iNumExamen);
          int iLong = cNombre.length();
          String cLinea = "";
          for(int i=0; i<iLong; i++)
            cLinea +="_";

          out.print("&nbsp;");
          out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cLinea + "</td></tr>");
          out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cNombre + "</td></tr>");
          out.print("</table>");

        if(request.getParameter("hdBoton").toString().compareTo("Generar Reporte")==0) {
           out.println(clsConfig.getActiveX());
        }
          %>


  </td>
</tr>

  <%
          out.print("<tr><td align='left'>");
          out.print(vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen",""));
          out.print("</td></tr>");


}else{%>
      <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
  <%}%>
 </table>
</form>
</body>
<script language="JavaScript">
//fIrCatalogo('pg070105011.jsp','iCveServicio','31');
fIrCatalogo('pg070105011.jsp','iCveServicio','<%=iCveServicio%>');
</script>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>

