<%/**
 * Title: pg070104031
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
  pg070104031CFG  clsConfig = new pg070104031CFG();                 // modificar
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104031.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104031.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070104031.jsp";     // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Propiedad|Valor|";    // modificar
  String cCveOrdenar  = "cPropiedad|cValor|";  // modificar
  String cDscFiltrar  = "Propiedad|Valor|";    // modificar
  String cCveFiltrar  = "cPropiedad|cValor|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  
  boolean lFiltros    = false;                 // modificar
  boolean lIra        = true;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

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
       Vector vcExpDicExam = new Vector();
       Vector vcExpRecomen = new Vector();
       Vector vcExpDiagnos = new Vector();
       Vector vcExpDiagnosCif = new Vector();
       //Agrego LAS
       Vector comparaCat = new Vector();

       vcExpNMedica = clsConfig.listanMedica();
       vcExpDicExam = clsConfig.listaDicSer();
       vcExpRecomen = clsConfig.listaExpRec();
       vcExpDiagnos = clsConfig.listaExpDia();
       vcExpDiagnosCif = clsConfig.listaExpDiaCif();

       TVEXPDictamenServ vExpNMedica = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDicExam = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpRecomen = new TVEXPDictamenServ();   
       TVEXPDictamenServ vExpDiagnos = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDiagnosCif = new TVEXPDictamenServ();

       TDEXPServicio dEXPServicio = new TDEXPServicio();
       TVEXPServicio vEXPServicio = new TVEXPServicio();
       Vector vcEXPServicio = new Vector();

       TDEXPRama dEXPRama = new TDEXPRama();
       TVEXPRama vEXPRama = new TVEXPRama();
       Vector vcEXPRama = new Vector();


       int iConcluido = 0;
              
%>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<script LANGUAGE="JavaScript">
////////////////////////////////
  var wExp;
  /*function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveRama,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
      cInicio += "&hdICveRama=" + iCveRama;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

       wExp = open("pg070104011.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=600,height=300,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }*/
/*
  function fVerExamen(iCveExpediente,iNumExamen,iCveServicio,iCveProceso){
      cInicio = "";
      form = document.forms[0];

      cInicio = "?hdiCveExpediente=" + iCveExpediente;
      cInicio += "&hdiNumExamen=" + iNumExamen;
      cInicio += "&hdICveServicio=" + iCveServicio;
      cInicio += "&hdICveProceso=" + iCveProceso;

      if((wExp != null) && (!wExp.closed))
        wExp.focus();

       wExp = open("pg070104021.jsp"+cInicio, "Selector",'dependent=yes,hotKeys=no,location=no,menubar=no,personalbar=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,width=600,height=300,screenX=800,screenY=600');
       wExp.moveTo(50,50);
       window.onclick=HandleFocus
       window.onfocus=HandleFocus
       fSetModal();
   }*/

////////////////////////////////
  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cStyle = '<link rel="stylesheet" href="/exm/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';


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
  

  function fOnMinCif(){
    form = document.forms[0];
    if (form.iCveDiagnostico2.value.length > 0 && form.iCveDiagnostico2.value != null){
       if(form.iCveDiagnostico2.selectedIndex >= <%=vcExpDiagnos.size()%>){
          form.iCveDiagnostico2.options [form.iCveDiagnostico2.selectedIndex] = null;
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
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cie.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cif.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cifcompleto.js"%>"></script>
<% //SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"ciecompleto.js""> </script> %>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selDiag.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"reco.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selReco.js"%>"></script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracterï¿½sticas generales de las pï¿½ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
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
  <input type="hidden" name="hdiCveExpediente" value="">
  <input type="hidden" name="hdiNumExamen" value="">
  <input type="hidden" name="hdICveServicio" value="">
  <input type="hidden" name="hdICveProceso" value="">

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
  int iCveRama = 0;
%>
<input type="hidden" name="iCveUsuario" value="<%=vUsuario.getICveusuario()%>">
<%

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

     String cBtn = ""+request.getParameter("hdBoton");
     if(cBtn.equals("Imprime Documentacion")) {
        out.println(clsConfig.getActiveX());
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
             <%
             
              if(request.getParameter("iCveProceso").compareTo(vParametros.getPropEspecifica("EMOProceso").toString())== 0){
             %>
             <tr><td class="ETablaT" colspan="7">La impresi&oacute;n del diagn&oacute;stico no apto es solo a solicitud por escrito del operador.</td></tr>
             <%
                }
             %>
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
               out.print("<td class=\"ECampo\">"+clsConfig.getEdad(vPerDatos.getDtNacimiento())+" A&ntilde;os</td>");
               out.print("<td class=\"EEtiqueta\" colspan=\"3\">Sexo:</td>");
               if (vPerDatos.getCSexo().equalsIgnoreCase("M")){
                   out.print("<td class=\"ECampo\">Hombre</td>");
               }else{
                   out.print("<td class=\"ECampo\">Mujer</td>");
                }
               out.print("</tr>");


              if(request.getParameter("iCveProceso").compareTo(vParametros.getPropEspecifica("EMOProceso").toString())== 0){

               TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
               TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
               TFechas dtFecha = new TFechas();
               Vector vcHInicio = new Vector();
               vcHInicio = dEXPExamAplica.FindByAll(" where iCveExpediente = " + request.getParameter("iCveExpediente") + " and iNumExamen = " + request.getParameter("iNumExamen"));
               if(vcHInicio.size() > 0){
                  vEXPExamAplica = (TVEXPExamAplica) vcHInicio.get(0);
               }
               TDGRLEmpresas dGRLEmpresas = new TDGRLEmpresas();
               TVGRLEmpresas vGRLEmpresas = new TVGRLEmpresas();
               Vector vcEmpresas = new Vector();

               vcEmpresas = dGRLEmpresas.FindByAll(" where iCveEmpresa = " + vEXPExamAplica.getICveNumEmpresa());
               if (vcEmpresas.size() > 0){
                    vGRLEmpresas = (TVGRLEmpresas) vcEmpresas.get(0);
                    out.print("<tr>");
                    out.print("<td class='EEtiqueta' colspan='1'>Cve.:</td>");
                    out.print("<td class='ETabla' colspan='1'>"+ vGRLEmpresas.getcIDDGPMPT() + "</td>");
                    out.print("<td class='EEtiqueta' colspan='1'>Empresa:</td>");
                    out.print("<td class='ETabla' colspan='5'>"+ vGRLEmpresas.getCNombreRS()+ "</td>");
                    out.print("</tr>");
               }
               out.print("<tr>");
               out.print("<td class='EEtiqueta' colspan='1'>Fecha:</td>");
               out.print("<td class='ETabla' colspan='1'>"+dtFecha.getFechaDDMMYYYY(dtFecha.TodaySQL(),"/")+"</td>");
               out.print("<td class='EEtiqueta' colspan='1'>Hora de Inicio:</td>");
               out.print("<td class='ETabla' colspan='1'>"+ vEXPExamAplica.getTInicio().toString()+"</td>");
               out.print("<td class='EEtiqueta' colspan='1'>Hora de T&eacute;rmino:</td>");
               out.print("<td class='ETabla' colspan='2'>");
               if(vEXPExamAplica.getTConcluido() == null)
                  out.print("&nbsp;");
               else
                  out.print(vEXPExamAplica.getTConcluido().toString());
               out.print("</td>");
               out.print("</tr>");

             }

             }else{
               out.print("<tr>");
               out.print("<td class=\"EResalta\" colspan=\"4\" align=\"center\">Datos de Personal no disponibles</td>");
               out.print("</tr>");
             }

             if(request.getParameter("iCveProceso").compareTo(vParametros.getPropEspecifica("EMOProceso").toString())== 0){

            out.print("<tr><td class='ETablaC' colspan='7'>");
            /*out.print(vEti.clsAnclaTexto("EAnclaC","**Ver Examen**",
                                           "javascript:fServicio("+request.getParameter("iCveExpediente") +","
                                           +request.getParameter("iNumExamen") +","+ vParametros.getPropEspecifica("EMOServicio") + ",'"+"" + "EMO"  +"');","Servicio"));*/
            out.print(vEti.clsAnclaTexto("EAncla", "Ver/Imprimir Resultados del Servicio", "JavaScript:fIrVerExamen(0,0,'pg070104071.jsp'," + request.getParameter("iNumExamen").toString() + ");", "Ver Examen", ""));                                           
            out.print("</td></tr>");
             }
            else{
            // Revisiï¿½n de Examen MGonzï¿½lez 14/02/2005 2:43 P.M.
             if(clsConfig.getAccion().compareToIgnoreCase("Guardar")!=0) {
                out.println("<tr>");
                out.println("<td colspan=7 align='center'>");
                out.print(vEti.clsAnclaTexto("EAncla","Revisi&oacute;n de Examen","JavaScript:fVerExamen(" + request.getParameter("iCveExpediente") + ", " + request.getParameter("iNumExamen") + ", " + request.getParameter("iCveServicio") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                out.println("</td>");
                out.println("</tr>");
             }
            }

             %>
          </table>&nbsp;

          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="7" class="ETablaT">Categor&iacute;as Solicitadas
                </td>
             </tr>
             <%
             if(vcEXPDicSer.size()>0){
               String cModo = "";
               out.print("<tr>");
               out.print("<td class=\"ETablaT\">Modo de Transporte</td>");
               out.print("<td class=\"ETablaT\">Grupo</td>");
               out.print("<td class=\"ETablaT\">Categor&iacute;a</td>");
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
                <td colspan="7" class="ETablaT">Diagn&oacute;sticos
                </td>
             </tr>
             <%
               out.println("<tr>");
               out.println("<td class=\"ETablaT\" RowSpan=2>Clave CIE-10:</td>");
               out.println("<td RowSpan=2 ColSpan=2>");
               out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveDiagnosticoS\" maxlength=\"20\" size=\"20\" onBlur=\"JavaScript:fMayus(this);\" Value=\"\" >");
               out.println("</td>");
 /*cambio*/              out.println("<td>");
  //             out.println("<td RowSpan=2>");
 /*Cambio*/              out.println(vEti.clsAnclaTexto("EAncla"," Buscar Frecuente","javascript:fSelDiag(document.forms[0].iCveDiagnosticoS);", "Buscar Diagn&oacute;stico","") + "</td>");
              //original out.println(vEti.clsAnclaTexto("EAncla","Buscar","javascript:fSelDiag(document.forms[0].iCveDiagnosticoS);", "Buscar Diagnï¿½stico",""));
               out.println("</td>");
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
              
 /*Cambio aki*/              out.println("<td>" + vEti.clsAnclaTexto("EAncla","Buscar un espec&iacute;fico","javascript:fBuscarCIE10('');","Buscar Diagn&oacute;stico en el cat&aacute;logo completo","aDiag") + "</td>");
               out.println("<td align=\"center\">");
               out.println("<Input Type=\"Button\" Value=\"< Quitar Dx.\" Name=\"Min\" onClick=\"JavaScript:fOnMin();\">");
               out.println("</td>");
               out.println("</tr>");

               %>
               <tr>
                  <td colspan="7" class="ETablaT">Clasificaci&oacute; Internacional del Funcionamiento, de la Discapacidad y de la salud en el primer nivel de atenci&oacute;n.
                  </td>
               </tr>
               <%
                 out.println("<tr>");
                 out.println("<td class=\"ETablaT\" RowSpan=2>Clave CIF:</td>");
                 out.println("<td RowSpan=2 ColSpan=2>");
                 out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveDiagnosticoS2\" maxlength=\"20\" size=\"20\" onBlur=\"JavaScript:fMayus(this);\" Value=\"\" >");
                 out.println("</td>");
                 out.println("<td RowSpan=2>" + vEti.clsAnclaTexto("EAncla"," Buscar","javascript:fSelDiagCif(document.forms[0].iCveDiagnosticoS2);", "Buscar Diagnóstico","") + "</td>");
                 out.println("<td align=\"center\">");
                 out.println("<Input Type=\"Button\" Value=\"Agregar Dx. CIF. >\" Name=\"Add\" onClick=\"JavaScript:fOnAddCif();\">");
                 out.println("</td>");
                 out.println("<td RowSpan=2 ColSpan=2>");
                 out.println("<select name=\"iCveDiagnostico2\" size=\"5\" MULTIPLE>");
                 if (vcExpDiagnosCif.size()>0){
                    for (int d=0;d<vcExpDiagnosCif.size();d++){
                       vExpDiagnosCif = (TVEXPDictamenServ)vcExpDiagnosCif.get(d);
                       out.println("<option value="+vExpDiagnosCif.getICveDiagnostico()+"_"+vExpDiagnosCif.getICveEspecialidad()+">"+vExpDiagnosCif.getCDscDiagnostico()+"</option>");
                    }
                 }
                 out.println("</select");
                 out.println("</td>");
                 out.println("</tr>");
                 out.println("<tr>");
                 //out.println("<td> &nbsp</td>");
                 out.println("<td align=\"center\">");
                 out.println("<Input Type=\"Button\" Value=\"< Quitar Dx. CIF.\" Name=\"Min\" onClick=\"JavaScript:fOnMinCif();\">");
                 out.println("</td>");
                 out.println("</tr>");
             %>
             <tr>
                <td colspan="7" class="ETablaT">Recomendaci&oacute;n
                </td>
             </tr>
             <%
               out.println("<tr>");
               out.println("<td class=\"ETablaT\" RowSpan=3>Recomendaci&oacute;n:</td>");
               out.println("<td RowSpan=2  ColSpan=2>");
               out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveRecomendacionS\" maxlength=\"20\" size=\"20\" onBlur=\"JavaScript:fMayus(this);\" Value=\"\" >");
               out.println("</td>");
               out.println("<td RowSpan=2>");
               out.println(vEti.clsAnclaTexto("EAncla","Buscar","javascript:fSelReco(document.forms[0].iCveRecomendacionS);", "Buscar Recomendaci&oacute;n",""));
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
                <td colspan="7" class="ETablaT">Dictamen por Categor&iacute;a
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

                     // Agrego Marco A. Gonzï¿½lez Paz
                     // Aplica solo para el EMO !!!!
                     out.println("<input type=\"hidden\" name=\"iCveCategoria\" value='"+vEXPDicSer.getICveCategoria()+"'>");
                     //
/*
                     out.println("<tr>");
                     if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(cModoc)){
                        out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscMdoTrans()+"</td>");
                     }else{
                        out.println("<td class=\"ETablaT\">&nbsp;</td>");
                     }
                     out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscCategoria()+"");
                     out.println("<td align=\"right\">");
                     out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"1\">");
                     out.println("</td>");
                     out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                     out.println("Apto2");
                     out.println("</td>");
                     out.println("<td align=\"right\">");
                     out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"0\">");
                     out.println("</td>");
                     out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                     out.println("No Apto");
                     out.println("</td>");
                     out.println("</tr>");
                     cModoc=vEXPDicSer.getCDscMdoTrans();*/
                     
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
                <td colspan="7" class="ETablaT">Observaciones
                </td>
             </tr>
             <%
             if (vcExpNMedica.size() > 0){
                for (int a=0;a<vcExpNMedica.size();a++){
                     vExpNMedica = (TVEXPDictamenServ) vcExpNMedica.get(a);
                     if(vExpNMedica.getCNotaMedica() != null && !vExpNMedica.getCNotaMedica().toString().equalsIgnoreCase("null")){
                       if(vExpNMedica.getCNotaMedica().length() >0){
                          out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones:","ECampo",70,10,6,"cNotaMedicaH", vExpNMedica.getCNotaMedica(),0,"","fMayus(this);",false,false,false));
                          out.println("<input type=\"hidden\" name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\">");

                          // - - Permite ver el examen en su totalidad
                          if(request.getParameter("tpoBusqueda").compareTo("unMedico")==0){
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
                          }
                          // - -

                       }else{
                          out.print(vEti.Texto("EEtiquetaC","Observaciones:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                          out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                       }
                     }else{
                          out.print(vEti.Texto("EEtiquetaC","Observaciones:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                          out.println("NINGUNA</textarea>");
                          //out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                     }
                }
             }else{
                          out.print(vEti.Texto("EEtiquetaC","Observaciones:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                          out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
             }
               
             %>
          </table>
<%
if(vcExpDicExam.size()>0){
if(vExpDicExam.getLDictamen() == 0 && vExpDicExam.getICveServicio() == 45){

          TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
               TVEXPExamAplica vEXPExamAplica = new TVEXPExamAplica();
               TFechas dtFecha = new TFechas();
               Vector vcHInicio = new Vector();
               vcHInicio = dEXPExamAplica.FindByAll(" where iCveExpediente = " + request.getParameter("iCveExpediente") + " and iNumExamen = " + request.getParameter("iNumExamen"));
               if(vcHInicio.size() > 0){
                  vEXPExamAplica = (TVEXPExamAplica) vcHInicio.get(0);
               }
    if(vEXPExamAplica.getICveProceso() == 1){
      out.print("<br><br><br>");
      out.print("<table align='center'>");
          out.print("  <tr class=\"ETabla\"><td></td><td><a class=\"EAncla\" ");
          out.print("  href=\"javascript:fIrCatalogo('pg070105011.jsp','iCveServicio','31')\">");
          out.print("  DIAGN&Oacute;STICO Y RECOMENDACI&Oacute;N DEL DICTAMINADOR</a></td></tr>");
      out.print("</table>");
      out.print("<input type=\"hidden\" name=\"hdICveExpediente\" value=\""+vEXPExamAplica.getICveExpediente() +"\">");
      out.print("<input type=\"hidden\" name=\"hdINumExamen\" value=\""+vEXPExamAplica.getINumExamen() +"\">");
      out.print("<input type=\"hidden\" name=\"hdICveUniMed\" value=\""+vEXPExamAplica.getICveUniMed() +"\">");
      out.print("<input type=\"hidden\" name=\"hdICveModulo\" value=\""+vEXPExamAplica.getICveModulo() +"\">");
      out.print("<input type=\"hidden\" name=\"hdICveProceso\" value=\""+vEXPExamAplica.getICveProceso() +"\">");
      String dFechaActual = "";
      dFechaActual = dtFecha.getFechaDDMMYYYY(vEXPExamAplica.getDtSolicitado(), "/");
      out.print("<input type=\"hidden\" name=\"hdDtConcluido\" value=\""+ dFechaActual +"\">");
      }
    }
}
          out.print("<table align='center'>");
          out.print("<tr><td>&nbsp;</td></tr>");

          TVGRLUsuario vGRLUsuario = new TVGRLUsuario();
          TDGRLUsuario dGRLUsuario = new TDGRLUsuario();
          Vector vcGRLUsuario = new Vector();

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

           if(clsConfig.getAccion().compareToIgnoreCase("Imprime Documentacion")==0) {
             out.println(clsConfig.getActiveX());
           }

           //SE COMENTO LA SIGUIENTE PARTE, QUE GENERABA LA LIGA DE DCOCUMENTACION
           
//          if( (vcExpDicExam.size()>0) && (request.getParameter("iCveProceso").compareTo(vParametros.getPropEspecifica("EMOProceso").toString())== 0))  {
%>
<!--    <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
          <%
/*             out.println("<tr>");
             out.print("<td class=\"ECampo\">");
             out.print(vEti.clsAnclaTexto("EAncla","Documentaciï¿½n","JavaScript:Genera_Doc();","Generar Documentacion"));
             out.print("</td>");
             out.println("</tr>");  */
          %>
          </table>   -->
          <%
//           }               FIN COMENTARIO DOCUMENTACION   LAS 26/06/2008
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

