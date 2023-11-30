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
<%@ page import="gob.sct.medprev.util.reglas.*" %>
<%@ page import="com.micper.sql.*" %>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="java.util.*"%>

<html>

<%

TDEXPExamCat cat2 = new TDEXPExamCat();
int trasnsporte = cat2.FindByInt("Select icvemdotrans from expexamcat where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio"));
int iCveUniMed = cat2.FindByInt("Select icveunimed from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio"));
int iCveModulo = cat2.FindByInt("Select icvemodulo from expexamaplica where icveexpediente = "+session.getAttribute("id")+" and inumexamen = "+session.getAttribute("episodio"));



System.out.println("jspD 1");
  pg070105011CFG2  clsConfig = new pg070105011CFG2();                 // modificar
  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  
  if(vUsuario != null){
		System.out.println("Usuario en Session="+vUsuario.getICveusuario());
	}else{
		System.out.println("Sacar de sistema");
	    request.getRequestDispatcher("login.jsp").forward(request,response);
	}
  
  
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
  String dictamenes = "";
  int DicNoApto = 0;
  System.out.println("jspD 2");

/**
* Listas de Resultados
*/

       Vector vcExpNMedica = new Vector();
       Vector vcExpORes = new Vector();
       Vector vcExpDicExam = new Vector();
       Vector vcExpRecomen = new Vector();
       Vector vcExpDiagnos = new Vector();
       Vector vcExpDiagnosCif = new Vector();
       //Agrego LAS
       Vector comparaCat = new Vector();
       boolean guardoDiagnostico = false;

       vcExpNMedica = clsConfig.listanMedicaDos(session.getAttribute("id")+"",session.getAttribute("episodio")+"");
       vcExpORes = clsConfig.ObserResDos(session.getAttribute("id")+"",session.getAttribute("episodio")+"");
       System.out.println("jspD 2-1");
       vcExpDicExam = clsConfig.listaDicSer(session.getAttribute("id")+"",session.getAttribute("episodio")+"","31");
       System.out.println(vcExpDicExam.size());
       vcExpRecomen = clsConfig.listaExpRecDos(session.getAttribute("id")+"",session.getAttribute("episodio")+"");
       System.out.println("jspD 2-2");
       vcExpDiagnos = clsConfig.listaExpDiaDos(session.getAttribute("id")+"",session.getAttribute("episodio")+"");
       vcExpDiagnosCif = clsConfig.listaExpDiaCif();

       System.out.println("jspD 2-3");
       TVEXPDictamenServ vExpNMedica = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpORes    = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDicExam = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpRecomen = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDiagnos = new TVEXPDictamenServ();
       TVEXPDictamenServ vExpDiagnosCif = new TVEXPDictamenServ();


       TDEXPServicio dEXPServicio = new TDEXPServicio();
       TVEXPServicio vEXPServicio = new TVEXPServicio();
       Vector vcEXPServicio = new Vector();

       TDEXPFunDictamen dEXPFunDictamen = new TDEXPFunDictamen();

       TDEXPRama dEXPRama = new TDEXPRama();
       TVEXPRama vEXPRama = new TVEXPRama();
       Vector vcEXPRama = new Vector();

       int iConcluido = 0;
       System.out.println("jspD 3");
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

  function fPrint(){
    window.print();
  }

  var cStyle = '<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">';
</script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cie.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cif.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaNas")+"cifcompleto.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selDiag.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"reco.js"%>"></script>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"selReco.js"%>"></script>
<head>
  <meta name="Autor" content="<%= vParametros.getPropEspecifica("Autor") %>">
  
  
  <!--  BOOTASTRAP -->
    <meta name="description" content="Framedev -  Framework para desarrolladores">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="http://10.33.143.52:7001/epi/assets/app/js/webfont.js"></script>
<script>
    WebFont.load({
       //google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       google: {"families":["Montserrat:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       active: function() {
           sessionStorage.fonts = true;
       }
     });
</script>
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/demo/default/base/menu_alter.css" rel="stylesheet" type="text/css" />

<!--JQuery-->
<script src="http://10.33.143.52:7001/epi/assets/plugins/jquery-3.2.1.min.js"></script>

<!--Plugins JQuery-->
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.min.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />

<!--APP-->
<link href="http://10.33.143.52:7001/epi/css/app.css" rel="stylesheet" type="text/css">

<!--calendar-->
<link href="http://10.33.143.52:7001/epi/assets/vendors/custom/jquery-ui/jquery-ui.bundle.css" rel="stylesheet" type="text/css" />
<link href="http://10.33.143.52:7001/epi/assets/vendors/custom/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css" />

<!--iconos-->
<link href="http://10.33.143.52:7001/epi/css/svg.css" rel="stylesheet" type="text/css">

<link href="http://10.33.143.52:7001/epi/css/loader.css" rel="stylesheet" type="text/css" />
<script src="http://10.33.143.52:7001/epi/assets/js/loader.js"></script>

<!--jquery steps-->
<link href="http://10.33.143.52:7001/epi/css/jquery.steps.css" rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="http://10.33.143.52:7001/epi/img/favicon.ico">
<!--  FIN BOOTASTRAP -->

  
  
  
  
  
  
  <style type="text/css">
<!--
.Estilo1 {
	color: #FF0000;
	font-size: 14px;
}
-->
  </style>
  
  
<script type="text/javascript" charset="utf-8" src="<%=vParametros.getPropEspecifica("RutaFuncs")+"jquery.min.js"%>"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncs")+"original.js"%>" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" charset="utf-8">
                    $(document).ready(function(){

<%    
System.out.println("jspD 4");
//if(clsConfig.getAccesoValido()){
       Vector vcEXPDicSer = new Vector();
       Vector vcEXPDicSer2 = new Vector();
       Vector vcEXPListaCat = new Vector();
       TVEXPDictamenServ vEXPDicSer = new TVEXPDictamenServ();
       TVEXPDictamenServ vEXPDicSer2 = new TVEXPDictamenServ();
       TVEXPDictamenServ vEXPListaCat = new TVEXPDictamenServ();
       vcEXPDicSer = clsConfig.findCatSolDos(session.getAttribute("id")+"",session.getAttribute("episodio")+"");
       vcEXPDicSer2 = clsConfig.findCatSolDos(session.getAttribute("id")+"",session.getAttribute("episodio")+"");
       System.out.println("vcEXPDicSer="+vcEXPDicSer.size());
       vcEXPListaCat = clsConfig.listaCategoria();
       TVPERDatos vPerDatos=clsConfig.findExpedienteDos(session.getAttribute("id")+"");
       System.out.println("jspD 5");
 if (vcEXPDicSer2.size() > 0){
                     //System.out.println("Generando nuevas etiquetas = "+ vcEXPDicSer2.size());
                     for (int i=0;i<vcEXPDicSer2.size();i++){
                     //System.out.println("contador = "+ i);
                     vEXPDicSer2 = (TVEXPDictamenServ) vcEXPDicSer2.get(i);                            
  %>                          
                            $('#simple-target-1<%=i%>').ezpz_tooltip({
          			    contentId: 'simple-content-1'
                            });          
          
                            $("#effects<%=i%>").ezpz_tooltip({
                                    contentId: 'simple-content-1',
                                    showContent: function(content) {
                                            content.fadeIn('slow');
                                    },
                                    hideContent: function(content) {
                                            content.stop(true, true).fadeOut('slow');
                                    }
                            });

                            $("#static-target-1<%=i%>").ezpz_tooltip({
                                    contentId: 'simple-content-1',                                
                                    contentPosition: 'rightStatic'
                            });

                            $("#stay-target-1<%=i%>").ezpz_tooltip({
                                    contentId: 'simple-content-1',
                                    contentPosition: 'belowStatic',
                                    stayOnContent: true,
                                    offset: 0
                            });

                            $("#ajax-target-1<%=i%>").ezpz_tooltip({
                                    beforeShow: function(content){
                                            if (content.html() == "") {
                                                    $.get("/ajax.txt", function(html){
                                                            content.html(html);
                                                    });
                                            }
                                    }
                            });
                            
<%
                  }}
%>                            

                            $("#fancy-target-1").ezpz_tooltip();
                    });
    </script>

    <style type="text/css" media="screen">
                    h3 { margin-top: 20px; }
                    .tooltip-target {
                            display: block;
                            padding: 10px;
                            background-color: #FFFFF;
                            text-align: center;
                    }

                    .tooltip-content {
                            display: none;      /* required */
                            position: absolute; /* required */
                            width: 250px;
                            padding: 10px;
                            border: 3px solid #088A08;
                            background-color: #37AC75;
                            text-align: center;
                    color: white;
                    }

                    .tooltip-content p {
                            margin: 0;
                    }

                    #fancy-content-1 {
                            border: none;
                            background: url('<%= vParametros.getPropEspecifica("RutaImg") %>original.png');
                            width: 277px;
                            height: 96px;
                    }
     </style>
  
</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega caracter�sticas generales de las p�ginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet" href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>" TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>" topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %>">
<form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed"
		id="frm_svs" name="frm_svs">
  <input type="hidden" name="hdLCondicion" value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
  <input type="hidden" name="hdLOrdenar" value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
  <input type="hidden" name="FILNumReng" value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
  <%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }

  System.out.println("jspD 6");
  
  %>
  <input type="hidden" name="hdICveExpediente" value="<%=session.getAttribute("id")+""!=null?session.getAttribute("id")+"":""%>">
  <input type="hidden" name="hdINumExamen" value="<%=session.getAttribute("episodio")+""!=null?session.getAttribute("episodio")+"":""%>">
  <input type="hidden" name="hdICveUniMed" value="<%=(iCveUniMed+"").toString()!=null?(iCveUniMed+"").toString():""%>">
  <input type="hidden" name="hdICveModulo" value="<%=(iCveModulo+"").toString()!=null?(iCveModulo+"").toString():""%>">
  <input type="hidden" name="hdICveProceso" value="<%="1"!=null?"1":""%>">
  <input type="hidden" name="hdDtConcluido" value="<%=request.getParameter("hdDtConcluido")!=null?request.getParameter("hdDtConcluido"):""%>">
  <input type="hidden" name="hdICveServicio" value="">
  <input type="hidden" name="iAnioSel" value="<%=request.getParameter("iAnioSel")%>">
  <input type="hidden" name="iCveMdoTransSel" value="<%=trasnsporte%>">

  <input type="hidden" name="hdRowNum" value="<%=cPosicion%>">
  <input type="hidden" name="hdCampoClave" value="<%=cClave%>">
  <input type="hidden" name="hdCPropiedad" value="">

  <input type="hidden" name="iCveProceso" value="<%=request.getParameter("iCveProceso")%>">
  <input type="hidden" name="iCveUniMed" value="<%=(iCveUniMed+"").toString()%>">
  <input type="hidden" name="iCveModulo" value="<%=(iCveModulo+"").toString()%>">
  <input type="hidden" name="iCveMdoTrans" value="<%=trasnsporte%>">
  <input type="hidden" name="tpoBusqueda" value="<%out.print(request.getParameter("tpoBusqueda"));%>">

  <%
  int iCveExpediente=0;
  int iCveServicio=0;
  int iNumExamen = 0;
  int iCvePersonal = 0;
////////////////////////////////////////DIAGNOSTICOS AUTOMATICOS///////////////////////////////////////////////////////////////////////////////////////  
///Obteniendo variables para Diabetes Mellitus
	TDEXPExamAplica dExamAplica = new TDEXPExamAplica();
	int Glucosa = 0;
	boolean DiagnosticoDiabetes = false;// Necesita diagnostico Diabetes
	//System.out.println(edad);
		try{
			Glucosa= dExamAplica.RegresaInt("select dvalorini from expresultado "+ 
										"where icveexpediente = "+session.getAttribute("id")+""+" and inumexamen = "+session.getAttribute("episodio")+""+" and "+ 
										"icveservicio = 2 and icverama = 4 and icvesintoma = 1");
		}catch(Exception e){
			System.out.println("Error al ejecutar la consulta de signos vitales");
		}
	if(Glucosa >= 200){
		DiagnosticoDiabetes = true;
	}
	//System.out.println(Glucosa);  

	System.out.println("jspD 7");
	
RDiagnosticoRecomendacionesAuto rDiag = new RDiagnosticoRecomendacionesAuto();
//Diagnostico Diabetes Mellitus
boolean DiagnosticoDiabetesMellitusAntecedentesPersPat = false;
try{
	DiagnosticoDiabetesMellitusAntecedentesPersPat = rDiag.DiagnosticoDiabetesMellitusAntecedentesPersPat(session.getAttribute("id")+"", session.getAttribute("episodio")+"");
}catch(Exception e){
	System.out.println("Error al ejecutar la consulta DiagnosticoDiabetesMellitusAntecedentesPersPat");
}

//Diagnostico Diabetes Mellitus Insulinodependiente
boolean DiagnosticoDiabetesMellitusInsulinodependienteAntecedentesPersPat = false;
try{
	DiagnosticoDiabetesMellitusInsulinodependienteAntecedentesPersPat = rDiag.DiagnosticoDiabetesMellitusAntecedentesPersPatInsulinodependiente(session.getAttribute("id")+"", session.getAttribute("episodio")+"");
}catch(Exception e){
	System.out.println("Error al ejecutar la consulta DiagnosticoDiabetesMellitusAntecedentesPersPat");
}

//Diagnostico Hipertension Arterial
boolean DiagnosticoHipertensionArterialAntecedentesPersPat = false;
try{
	DiagnosticoHipertensionArterialAntecedentesPersPat = rDiag.DiagnosticoHipertensionArterialAntecedentesPersPat(session.getAttribute("id")+"", session.getAttribute("episodio")+"");
}catch(Exception e){
	System.out.println("Error al ejecutar la consulta DiagnosticoHipertensionArterialAntecedentesPersPat");
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	System.out.println("jspD 8");
	
	%>
	<input type="hidden" name="DiagnosticoDiabetes" value="<%=DiagnosticoDiabetes%>">
  <%
  

  int iCveRama = 0;
  if (session.getAttribute("id")+""!= null && (session.getAttribute("id")+"").toString().length()>0){
     out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\""+session.getAttribute("id")+""+"\"> \n");
     iCveExpediente = Integer.parseInt(session.getAttribute("id")+"".toString());
  }else{
     out.print("<input type=\"hidden\" name=\"iCveExpediente\" value=\"1\"> \n");
     iCveExpediente = 1;
  }
  System.out.println("jspD 8-1");
  if ("31"!= null && "31".length()>0){
     out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\""+"31"+"\"> \n");
     iCveServicio = Integer.parseInt("31".toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCveServicio\" value=\"1\"> \n");
     iCveServicio = 1;
  }
  System.out.println("jspD 8-2");

  if(Integer.parseInt("31") == iEMOServ)
    out.print("<input type=\"hidden\" name=\"iEMOServ\" value=\"1\">");
  else
    out.print("<input type=\"hidden\" name=\"iEMOServ\" value=\"0\">");

  System.out.println("jspD 8-3");
  
   if (request.getParameter("iCveRama")!= null && request.getParameter("iCveRama").length()>0){
     out.print(" <input type=\"hidden\" name=\"iCveRama\" value=\""+request.getParameter("iCveRama")+"\"> \n");
     iCveRama = Integer.parseInt(request.getParameter("iCveRama").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCveRama\" value=\"1\"> \n");
     iCveRama = 1;
  }
   System.out.println("jspD 8-4");

  if (request.getParameter("iNumExamen")!= null && request.getParameter("iNumExamen").length()>0){
     out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\""+session.getAttribute("episodio")+""+"\"> \n");
     iNumExamen=Integer.parseInt(request.getParameter("iNumExamen").toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iNumExamen\" value=\""+session.getAttribute("episodio")+"\"> \n");
     iNumExamen=1;
  }
  System.out.println("jspD 8-5");
  
  if ("1"!= null && "1".length()>0){
     out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\""+"1"+"\"> \n");
     iCvePersonal=Integer.parseInt("1".toString());
  }else{
     out.print(" <input type=\"hidden\" name=\"iCvePersonal\" value=\"1\"> \n");
     iCvePersonal=1;
  }
  System.out.println("jspD 8-6");
  %>

  <table background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg" width="100%" height="100%">
  <% /*if(clsConfig.getAccesoValido()){
       Vector vcEXPDicSer = new Vector();
       Vector vcEXPListaCat = new Vector();
       TVEXPDictamenServ vEXPDicSer = new TVEXPDictamenServ();
       TVEXPDictamenServ vEXPListaCat = new TVEXPDictamenServ();
       vcEXPDicSer = clsConfig.findCatSol();
       vcEXPListaCat = clsConfig.listaCategoria();
       TVPERDatos vPerDatos=clsConfig.findExpediente();*/
  %>
  
  <input type="hidden" name="hdBoton" value="Guardar">
    
      
      <!-- 
          <table border="1" class="ETablaInfo" align="center" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="7" class="ETablaT">Datos del Personal
                </td>
             </tr>
             <%
             System.out.println("jspD 9");
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
               out.print("<td class=\"ECampo\">"+clsConfig.getEdad(vPerDatos.getDtNacimiento())+" A�os</td>");
               out.print("<td class=\"EEtiqueta\" colspan=\"3\">Sexo:</td>");
               if (vPerDatos.getCSexo().equalsIgnoreCase("M")){
                   out.print("<td class=\"ECampo\">Hombre</td>");
               }else{
                   out.print("<td class=\"ECampo\">Mujer</td>");
                }
               out.print("</tr>");
             }else{
               out.print("<tr>");
               out.print("<td class=\"EResalta\" colspan=\"4\" align=\"center\">Datos de Personal no disponibles</td>");
               out.print("</tr>");
             }

             System.out.println("jspD 10");
             %>
          </table>&nbsp;
-->
          <table border="1" class="ETablaInfo" align="left" cellspacing="0" cellpadding="3"><% // Inicio de Datos %>
             <tr>
                <td colspan="7" class="ETablaT">Categor�as Solicitadas
                </td>
             </tr>
             <%
             if(vcEXPDicSer.size()>0){
            	 System.out.println("jspD 11");
               String cModo = "";
               out.print("<tr>");
               out.print("<td class=\"ETablaT\">Modo de Transporte</td>");
               out.print("<td class=\"ETablaT\">Grupo</td>");
               out.print("<td class=\"ETablaT\">Categor�a</td>");
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
               System.out.println("jspD 12");
             }else{
                  out.print("<tr>");
                  out.print("<td class=\"EResalta\" colspan=\"7\" align=\"center\">Datos de Categorias no disponibles</td>");
                  out.print("</tr>");
             }
             System.out.println("jspD 13");
             %>
             <tr>
                <td colspan="7" class="ETablaT">Diagn�sticos
                </td>
             </tr>
             <%
               out.println("<tr>");
               out.println("<td class=\"ETablaT\" RowSpan=2>Clave CIE-10:</td>");
               out.println("<td RowSpan=2 ColSpan=2>");
               out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveDiagnosticoS\" maxlength=\"20\" size=\"20\" onBlur=\"JavaScript:fMayus(this);\" Value=\"\" >");
               out.println("</td>");
               out.println("<td>" + vEti.clsAnclaTexto("EAncla"," Buscar Frecuentes","javascript:fSelDiag(document.forms[0].iCveDiagnosticoS);", "Buscar Diagn�stico","") + "</td>");
               out.println("<td align=\"center\">");
               out.println("<Input Type=\"Button\" Value=\"Agregar Dx. >\" Name=\"Add\" onClick=\"JavaScript:fOnAdd();\">");
               out.println("</td>");
               out.println("<td RowSpan=2 ColSpan=2>");
               out.println("<select name=\"iCveDiagnostico\" size=\"5\" MULTIPLE>");
               
               /////////////////////////////////////SE AGREGAN DIAGNOSTICOS AUTOMATICOS////////////////////////////////////////////////////////
               System.out.println("jspD 14");
               if (vcExpDiagnos.size()>0){
                  for (int d=0;d<vcExpDiagnos.size();d++){
                     vExpDiagnos = (TVEXPDictamenServ)vcExpDiagnos.get(d);
                     out.println("<option value="+vExpDiagnos.getICveDiagnostico()+"_"+vExpDiagnos.getICveEspecialidad()+">"+vExpDiagnos.getCDscDiagnostico()+"</option>");
                  }
               }else{
            	   
            	   //System.out.println("DiagnosticoDiabetes ="+DiagnosticoDiabetes);
            	   //System.out.println("DiagnosticoDiabetesMellitusAntecedentesPersPat ="+DiagnosticoDiabetesMellitusAntecedentesPersPat);
            	   System.out.println("jspD 15");

                   if(DiagnosticoDiabetesMellitusInsulinodependienteAntecedentesPersPat){
                	   out.println("<option value=49_4>DIABETES MELLITUS INSULINODEPENDIENTE</option>");
                	   //out.println("<option value=50_4>DIABETES MELLITUS NO INSULINODEPENDIENTE</option>");
                	   DiagnosticoDiabetes = false;
                	   DiagnosticoDiabetesMellitusAntecedentesPersPat=false;
                   }
                               	   
            	   if(DiagnosticoDiabetes || DiagnosticoDiabetesMellitusAntecedentesPersPat){
                 	  out.println("<option value=50_4>DIABETES MELLITUS NO INSULINODEPENDIENTE</option>");
                   }

                   if(DiagnosticoHipertensionArterialAntecedentesPersPat){
                	   out.println("<option value=42_9>HIPERTENSION ESENCIAL (PRIMARIA)</option>");
                   }
               }
               
               System.out.println("jspD 16");
               
               
               
			   /////////////////////////////////////SE AGREGAN DIAGNOSTICOS AUTOMATICOS////////////////////////////////////////////////////////
               out.println("</select");
               out.println("</td>");
               out.println("</tr>");
               out.println("<tr>");
               out.println("<td>" + vEti.clsAnclaTexto("EAncla","Buscar un espec�fico","javascript:fBuscarCIE10('');","Buscar Diagn�stico en el cat�logo completo","aDiag") + "</td>");
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
               out.println("<td RowSpan=2>" + vEti.clsAnclaTexto("EAncla"," Buscar","javascript:fSelDiagCif(document.forms[0].iCveDiagnosticoS2);", "Buscar Diagn�stico","") + "</td>");
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
               
               System.out.println("jspD 17");
             %>
             
             
             <tr>
                <td colspan="7" class="ETablaT">Recomendaci�n
                </td>
             </tr>
             <%
               out.println("<tr>");
               out.println("<td class=\"ETablaT\" RowSpan=3>Recomendaci�n:</td>");
               out.println("<td RowSpan=2  ColSpan=2>");
               out.println("<Input Type\"text\" Class=\"ECampo\" Name=\"iCveRecomendacionS\" maxlength=\"20\" size=\"20\" onBlur=\"JavaScript:fMayus(this);\" Value=\"\" >");
               out.println("</td>");
               out.println("<td RowSpan=2>");
               out.println(vEti.clsAnclaTexto("EAncla","Buscar","javascript:fSelReco(document.forms[0].iCveRecomendacionS);", "Buscar Recomendaci�n",""));
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
               System.out.println("jspD 18");
             %>
             <tr>
                <td colspan="7" class="ETablaT">Dictamen por Categor�a
                </td>
             </tr>
             <%
                     if(vcExpDicExam.size()>0){
                    	 System.out.println("jspD 19");
                    	 String cModob = "";
                    	 guardoDiagnostico=true;
                        for (int b=0;b<vcExpDicExam.size();b++){

                           vExpDicExam = (TVEXPDictamenServ)vcExpDicExam.get(b);

                           System.out.println("jspD 20");
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
                        System.out.println("jspD 21");
                     }else{
                    	 System.out.println("jspD 18-1");
/*               if (vcEXPListaCat.size() > 0){
                  for (int j=0;j<vcEXPListaCat.size();j++){
                     vEXPListaCat = (TVEXPDictamenServ) vcEXPListaCat.get(j);*/
                 if (vcEXPDicSer.size() > 0){
                     //System.out.println("2");
                     System.out.println("jspD 22");
                  String cModoc = "";
                  for (int i=0;i<vcEXPDicSer.size();i++){
                     vEXPDicSer = (TVEXPDictamenServ) vcEXPDicSer.get(i);
                     // Agrego Marco A. Gonz�lez Paz
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
                     System.out.println("jspD 23");
                     //////////////////  Obtenemos informacion para conocer si hay alertas de no aptitud  ///////////////////
                           String sentencia = "";
                           TDEXPRegSint dEXPRegSint = new TDEXPRegSint();
                           int NApto = 0;
                           if("31".equals("31")){
                                ///Verifica no existan alertas de no aptitud
                                    try{
                                                sentencia = " SELECT COUNT (ICVESERVICIO) "
                                                            + "FROM EXPREGSIN "
                                                            + "WHERE "
                                                            + "ICVEEXPEDIENTE = "+session.getAttribute("id")+""+" AND "
                                                            + "INUMEXAMEN = "+session.getAttribute("episodio")+""+" AND "
                                                            + "ICVEMDOTRANS = "+vEXPDicSer.getICveMdoTrans()+" AND "
                                                            + "ICVECATEGORIA = "+vEXPDicSer.getICveCategoria()+"";
                                                NApto = dEXPRegSint.FindByCount(sentencia);                                                
                                        }catch(Exception e){
                                                                NApto = 0;
                                                                e.printStackTrace();
                                        }                  
                                        //System.out.println("NApto = "+NApto);
                           }
                           
                           System.out.println("jspD 24");
                          ///////// Validacion Insulinodependiente para invalidar regla en CDI y CENMA ///////////////
							// AG SA L 2017/04/06
							boolean InsulinoDependiente = false;
							boolean CDI = false;
							RImpedirExamenMedico rImpedirExamen = new RImpedirExamenMedico();
							try {
								CDI = rImpedirExamen.ExamenEnCDIoCenma(session.getAttribute("id")+""+"", session.getAttribute("episodio")+""+"");
								InsulinoDependiente = rImpedirExamen.DiagnosticoDeDiabetesMellitusInsulinodependiente(session.getAttribute("id")+""+"");
								if(InsulinoDependiente){
									if(CDI){
										if(NApto == 1){
											NApto = 0;
										}
									}							
								}
												
							} catch (Exception Ex) {
								System.out.println("Validacion Insulinodependiente para invalidar regla en CDI y CENMA");
							}
                                
							System.out.println("jspD 25");
							
                       //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                       
                       //////////////////////// NUEVO METODO VALIDA TODAS LAS REGLAS ///////////////////////////////////////////////
                       if(NApto==0){
                       ReglasCalculaVigencia rCalculaVigencia = new ReglasCalculaVigencia();
                       NApto = rCalculaVigencia.CalculaAptitud(vEXPDicSer.getICveMdoTrans(), vEXPDicSer.getICveCategoria(), 
                    		   Integer.parseInt(session.getAttribute("id")+""),Integer.parseInt(session.getAttribute("episodio")+""));
                       System.out.println("NApto = " +NApto);
                       }
                       
                       //////////////////////////////////////////////////////////////////////////////////////////////////////////// 
                           
                           System.out.println("jspD 26");
                     
                     if (!vEXPDicSer.getCDscMdoTrans().equalsIgnoreCase(cModoc)){
                        out.println("<tr>");
                        
                        
                        if(NApto > 0){
                                      out.println("<td class=\"ETablaT\">");
                                      out.println("<div class=\"effects-tooltip-target tooltip-target\" id=\"effects"+i+"\">"+vEXPDicSer.getCDscMdoTrans()+"</div>");
                                      out.println("<div class=\"simple-tooltip-content tooltip-content\" id=\"simple-content-1\">");
                                      out.println("<p> De acuerdo a las reglas de Medicina Preventiva y al examen Psicof�sico integral practicado a este usuario se concluyo que solo puede ser dictaminado No Apto </p>");
                                      out.println("</div> </td>");                                    
                        }else{
                                      out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscMdoTrans()+"</td>");
                        }
                        //out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscMdoTrans()+"</td>");

                        if(NApto > 0){
                                      out.println("<td class=\"ETablaT\" colspan=\"3\">");
                                      out.println("<div class=\"static-tooltip-target tooltip-target\" id=\"static-target-1"+i+"\">"+vEXPDicSer.getCDscCategoria()+"</div>");
                                      out.println("<div class=\"static-tooltip-content tooltip-content\" id=\"static-content-1\">");
                                      out.println("<p> De acuerdo a las reglas de Medicina Preventiva y al examen Psicof�sico integral practicado a este usuario se concluyo que solo puede ser dictaminado No Apto </p>");
                                      out.println("</div> </td>");                                    
                        }else{
                                      out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscCategoria()+"</td>");
                                      out.println("<td align=\"right\">");
                                      out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"1\">");
                                      out.println("</td>");
                                      out.println("<td align=\"left\" class=\"EEtiquetaL\">");
                                      out.println("Apto");
                                      out.println("</td>");
                        }
                        //out.println("<td class=\"ETablaT\">"+vEXPDicSer.getCDscCategoria()+"");
                         System.out.println("jspD 27");

                        if(NApto > 0){
                                      out.println("<td align=\"right\" class=\"EEtiquetaL\">");
                                      out.println("<div class=\"stay-tooltip-target tooltip-target\" id=\"stay-target-1"+i+"\">");
                                      out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"0\">");
                                      out.println("</div>");
                                      out.println("<div class=\"static-tooltip-content tooltip-content\" id=\"static-content-1\">");
                                      out.println("<p> De acuerdo a las reglas de Medicina Preventiva y al examen Psicof�sico integral practicado a este usuario se concluyo que solo puede ser dictaminado No Apto </p>");
                                      out.println("</div> </td>");                                    
                        }else{
                                      out.println("<td align=\"right\">");
                                      out.println("<input type=\"radio\" name=\"lDictamen"+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+"\" value=\"0\">");
                                      out.println("</td>");
                        }
                        
                        dictamenes = dictamenes +""+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+",";
                        
                        System.out.println("jspD 28");                                                    
                        if(NApto > 0){
                                      out.println("<td align=\"right\" class=\"EEtiquetaL\">");
                                      out.println("<div class=\"simple-tooltip-target tooltip-target\" id=\"simple-target-1"+i+"\">");
                                      out.println("No Apto");
                                      out.println("</div>");
                                      out.println("<div class=\"static-tooltip-content tooltip-content\" id=\"static-content-1\">");
                                      out.println("<p> De acuerdo a las reglas de Medicina Preventiva y al examen Psicof�sico integral practicado a este usuario se concluyo que solo puede ser dictaminado No Apto </p>");
                                      out.println("</div> </td>");                                    
                        }else{ 
                                      out.println("<td align=\"left\" class=\"EEtiquetaL\" ColSpan=\"3\">");
                                      out.println("No Apto");
                                      out.println("</td>");
                                      out.println("</tr>");                        
                        }                         
                         
                         cModoc=vEXPDicSer.getCDscMdoTrans();

                         System.out.println("jspD 29");
                         
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
                        dictamenes = dictamenes +""+vEXPDicSer.getICveCategoria()+"_"+vEXPDicSer.getICveMdoTrans()+",";
                     }
                  }
                 out.println(" <input type=\"hidden\" name=\"hdDictamenes\" value=\""+dictamenes+"\"> ");
                 System.out.println("jspD 30");
               }
            }
             %>
             <tr>
                <td colspan="7" class="ETablaT">Nota M�dica
                </td>
             </tr>
             <%
             int NotMedCap = 0;
             if (vcExpNMedica.size() > 0){
                for (int a=0;a<vcExpNMedica.size();a++){
                	System.out.println("jspD 31");
                     vExpNMedica = (TVEXPDictamenServ) vcExpNMedica.get(a);
                     if(vExpNMedica.getCNotaMedica() != null && !vExpNMedica.getCNotaMedica().toString().equalsIgnoreCase("null")){
                    	 System.out.println("jspD 31-1");
                       if(vExpNMedica.getCNotaMedica().length() >0){
                    	   System.out.println("jspD 31-2");
                    	   out.print(vEti.EtiAreaTextoCS("EEtiqueta","Nota M�dica:","ECampo",70,10,6,"cNotaMedicaH", vExpNMedica.getCNotaMedica(),0,"","fMayus(this);",false,false,false));
                          out.println("<input type=\"hidden\" name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\">");
                          NotMedCap = 1;

                          // - - Permite ver el examen en su totalidad
                          /*if(request.getParameter("tpoBusqueda").compareTo("unMedico")==0){
                            vcEXPServicio = dEXPServicio.getLConcluido(session.getAttribute("id")+"", request.getParameter("iNumExamen"), "31");
                            if(vcEXPServicio.size()>0){
                              vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                              iConcluido = vEXPServicio.getLConcluido();
                            }
                            if(iConcluido == 1){
                              out.println("<tr>");
                              out.println("<td colspan=7 align='center'>");
                              out.print(vEti.clsAnclaTexto("EAncla","Servicio Concluido - Ver/Imprimir Resultados del Servicio","JavaScript:fVerExamen(" + session.getAttribute("id")+"" + ", " + request.getParameter("iNumExamen") + ", " + "31" + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                              out.println("</td>");
                              out.println("</tr>");
                            }
                          }
                          else if(request.getParameter("tpoBusqueda").compareTo("variosMedicos")==0){
                           vcEXPRama = dEXPRama.getLConcluido(session.getAttribute("id")+"", request.getParameter("iNumExamen"), "31", request.getParameter("iCveRama"));
                           if(vcEXPRama.size()>0){
                             vEXPRama = (TVEXPRama) vcEXPRama.get(0);
                             iConcluido = vEXPRama.getIConcluido();
                           }
                           if(iConcluido == 1){
                             out.println("<tr>");
                             out.println("<td colspan=7 align='center'>");
                             out.print(vEti.clsAnclaTexto("EAncla","Rama Concluida. Ver/Imprimir Resultados de la Rama","JavaScript:fVerExamen(" + session.getAttribute("id")+"" + ", " + request.getParameter("iNumExamen") + ", " + "31" + ", " + request.getParameter("iCveRama") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                             out.println("</td>");
                             out.println("</tr>");
                           }
                          }*/
                          // - -

                       }else{
                          out.print(vEti.Texto("EEtiquetaC","Nota M�dica:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\">");
                          out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                       }
                     }else{
                          out.print(vEti.Texto("EEtiquetaC","Nota M�dica:"));
                          out.println("<td ColSpan=6>");
                          out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cNotaMedica\" value=\""+vExpNMedica.getCNotaMedica()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                          out.println("NINGUNA</textarea>");
                          //out.println(vExpNMedica.getCNotaMedica()+"</textarea>");
                          out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                          out.println("</td>");
                     }
                }
             }else{
                          out.print(vEti.Texto("EEtiquetaC","Nota M�dica:"));
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
             System.out.println("jspD 32");
             if (vcExpORes.size() > 0){
            	 System.out.println("jspD 33");
                for (int a=0;a<vcExpORes.size();a++){
                	System.out.println("jspD 34");
                	vExpORes = (TVEXPDictamenServ) vcExpORes.get(a);
                     if(vExpORes.getCObserRes() != null && !vExpORes.getCObserRes().toString().equalsIgnoreCase("null")){
                       if(vExpORes.getCObserRes().length() >0){
                          out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones/Restricciones:","ECampo",70,10,6,"cObserResH", vExpORes.getCObserRes(),0,"","fMayus(this);",false,false,false));
                          out.println("<input type=\"hidden\" name=\"cObserRes\" value=\""+vExpORes.getCObserRes()+"\">");

                          // - - Permite ver el examen en su totalidad
                          /*if(request.getParameter("tpoBusqueda").compareTo("unMedico")==0){
                            vcEXPServicio = dEXPServicio.getLConcluido(session.getAttribute("id")+"", request.getParameter("iNumExamen"), "31");
                            if(vcEXPServicio.size()>0){
                              vEXPServicio = (TVEXPServicio) vcEXPServicio.get(0);
                              iConcluido = vEXPServicio.getLConcluido();
                            }
                            if(iConcluido == 1){
                              out.println("<tr>");
                              out.println("<td colspan=7 align='center'>");
                              out.print(vEti.clsAnclaTexto("EAncla","Servicio Concluido - Ver/Imprimir Resultados del Servicio","JavaScript:fVerExamen(" + session.getAttribute("id")+"" + ", " + request.getParameter("iNumExamen") + ", " + "31" + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
                              out.println("</td>");
                              out.println("</tr>");
                            }
                          }
                          else if(request.getParameter("tpoBusqueda").compareTo("variosMedicos")==0){
                           vcEXPRama = dEXPRama.getLConcluido(session.getAttribute("id")+"", request.getParameter("iNumExamen"), "31", request.getParameter("iCveRama"));
                           if(vcEXPRama.size()>0){
                             vEXPRama = (TVEXPRama) vcEXPRama.get(0);
                             iConcluido = vEXPRama.getIConcluido();
                           }
                           if(iConcluido == 1){
                             out.println("<tr>");
                             out.println("<td colspan=7 align='center'>");
                             out.print(vEti.clsAnclaTexto("EAncla","Rama Concluida. Ver/Imprimir Resultados de la Rama","JavaScript:fVerExamen(" + session.getAttribute("id")+"" + ", " + request.getParameter("iNumExamen") + ", " + "31" + ", " + request.getParameter("iCveRama") + ", " + request.getParameter("iCveProceso") + ");", "Ver Examen",""));
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
                System.out.println("jspD 35");
             }else{
            			if(NotMedCap == 0){	 
            				//out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones/Restricciones:","ECampo",70,10,6,"cObserResH", "&nbsp;",0,"","fMayus(this);",false,false,false));
                            //out.println("<input type=\"hidden\" name=\"cObserRes\" value=\"\">");
            				out.print(vEti.Texto("EEtiquetaC","Observaciones/Restricciones:"));
                            out.println("<td ColSpan=6>");
                            out.println("<textarea cols=\"70\" rows=\"10\"  name=\"cObserRes\" value=\""+vExpORes.getCObserRes()+"\" onkeypress=\"fChgArea(this);\" onchange=\"fChgArea(this);\" OnBlur=\"fMayus(this);\">");
                            out.println("NINGUNA</textarea>");
                            out.println("<Input Type=\"Text\" Size=\"4\" Value=\"\" Name=\"iNoLetras\" readonly>");
                            out.println("</td>");
            			}else{
            				out.print(vEti.EtiAreaTextoCS("EEtiqueta","Observaciones/Restricciones:","ECampo",70,10,6,"cObserResH", "Este servicio no estaba configurado, favor de liberar el vale de servicios para capturar Observaciones y Restricciones para este expediente.",0,"","fMayus(this);",false,false,false));
                            out.println("<input type=\"hidden\" name=\"cObserRes\" value=\"\">");
                        }
             }
             System.out.println("jspD 36");
%>

          </table>
          
          </table>
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<%

//System.out.println("DicNoApto = "+DicNoApto);

///if(vcExpDicExam.size()>0){
//if(vExpDicExam.getLDictamen() == 0){
if(DicNoApto > 0){

	System.out.println("jspD 37");
	
String QueryF = "select cFundamentacion from EXPFunDictamen where icveexpediente = "+iCveExpediente+" and inumexamen = "+session.getAttribute("episodio")+" and icveservicio = 31";
String QFundamentacion = "";
            try{
                 QFundamentacion = dEXPFunDictamen.FindByAll(QueryF);
             }catch(Exception e){
                     //System.out.println("La consulta a fallado \n"+QFundamentacion);
                     e.printStackTrace();
             }
             int fund = 0;
             fund =  QFundamentacion.length();

             System.out.println("jspD 38");
             System.out.println("jspD 38-1"+QFundamentacion);
             System.out.println("jspD 38-2"+fund);
if(!(QFundamentacion != null && fund > 0))
  {
	//if("1".equals("1")){
		System.out.println("jspD 39");
%>

              <div align="left">
                <span class="Estilo1"> Favor de capturar la siguiente informaci�n para poder generar su constancia de no aptitud. </span></div>

<%
	//}
 }

System.out.println("jspD 40");
if (vcExpDiagnos!=null){
 if (vcExpDiagnos.size()>0){

	 System.out.println("jspD 41");
%>

     
        <table border="1" class="ETablaInfo" align="left" cellspacing="0" cellpadding="3">
      <tr>
                 <td colspan="7" class="ETablaT">Motivaci�n:</td>
      </tr>
            <script LANGUAGE="JavaScript">
                                   function ValidaMotFun(form){
                                       var form = document.forms[0];
                                       lRadioCheck = false;
				       var msg='';

 <%                 for (int d=0;d<vcExpDiagnos.size();d++){
                     vExpDiagnos = (TVEXPDictamenServ)vcExpDiagnos.get(d);
                     if(!(vExpDiagnos.getCMotivacion() != null)){
%>
                                form = document.forms[0];
                                if(form.x<%=vExpDiagnos.getICveDiagnostico()%>_<%=vExpDiagnos.getICveEspecialidad()%>.value.length == 0){
                                    msg += 'Favor de ingresar el Motivo <%=d+1%>\n';
                                }
    <%                      }
                    }
 System.out.println("jspD 42");
  %>
                               if(form.cFundamentacion.value.length == 0){
                                    msg += 'Favor de ingresar la Fundamentaci�n';
                                }

                                if(msg !=''){
                                        alert( msg);
                                }else{
                               form.action='pg070105011P.jsp';
                               //form.target='FRMCuerpo';
                               form.iCveServicio.value='<%=iCveServicio%>' ;
                               //form.iCveServicio.value='31' ;
                               form.target="FRMDatos";
                               form.method='POST';
                               form.submit();
                           }
                    }
              </script>
<%                  for (int d=0;d<vcExpDiagnos.size();d++){
	System.out.println("jspD 42");
                     vExpDiagnos = (TVEXPDictamenServ)vcExpDiagnos.get(d);
                     //int iLongM = vExpDiagnos.getCMotivacion().length();
                     //System.out.println("iLongM = "+iLongM);
                     if(vExpDiagnos.getCMotivacion() != null && vExpDiagnos.getCMotivacion().toString().length() > 0){
%>
                           <tr>
                            <td class="EEtiquetaC">Diagnostico:</td>
                            <td class="EEtiquetaC"><%=vExpDiagnos.getCDscDiagnostico()%></td>
                            <td class="EEtiquetaC">Motivaci&oacuten</td>
                            <td class="EEtiquetaC"><%=vExpDiagnos.getCMotivacion()%></td>
                           </tr>
<%
                     }
                     else{
                     %>
                           <tr>
                            <td class="EEtiquetaC">Diagnostico:</td>
                            <td class="EEtiquetaC"><%=vExpDiagnos.getCDscDiagnostico()%></td>
                            <td class="EEtiquetaC">Motivaci&oacuten</td>
                            <td><input name="x<%=vExpDiagnos.getICveDiagnostico()%>_<%=vExpDiagnos.getICveEspecialidad()%>" type="text" value="" Size="100">&nbsp;</td>
                           </tr>
<%                      }
              }
       }
}
     else{
    	 if("1".equals("1")){
System.out.println("jspD 43");
%>
			<br>
			<br>
			<div align="left">
				<span class="Estilo1"> No se podr&aacute; dictaminar como no
					Apto hasta que agregue los diagn&oacute;sticos correspondientes.<br>
					Favor de liberar el servicio Diagnostico y Recomendaciones del
					Dictaminador y volver a dictaminar.
				</span>
			</div>

			<%
     }
    }
%>
    </table>
<%
        if(QFundamentacion != null && fund > 0)
        {
        	System.out.println("Dictamen#1");
%>
		<br> <br> <br> <br>
		<table border="1" class="ETablaInfo" align="left" cellspacing="0"
			cellpadding="3">
			<tr>
				<td colspan="7" class="ETablaT">Fundamentaci�n:</td>
			</tr>
			<tr>
				<td class="EEtiquetaC">Fundamentaci&oacuten:</td>
				<td class="EEtiquetaC"><%=QFundamentacion%></td>
			</tr>
		</table>
		<!--
            <br>
            <center>
                <input name="Agregar" type="button" value="Enviar" onClick="ValidaMotFun(this.form)">
                <br>
                <a class="EAncla" name="" href="JavaScript:ValidaMotFun(this.form);"
                    onMouseOut="self.status='';return true;"
                    onMouseOver="self.status='Ver Examen';return true;">Agregar</a>
            </center>
            <br>-->
		<%}else{
	System.out.println("Dictamen#2");
%>
		<br> <br> <br> <br>
		<table border="1" class="ETablaInfo" align="left" cellspacing="0"
			cellpadding="3">
			<tr>
				<td colspan="7" class="ETablaT">Fundamentaci�n:</td>
			</tr>
			<tr>
				<td class="EEtiquetaC">Fundamentaci&oacuten:</td>
				<td><textarea cols="70" rows="4" name="cFundamentacion"
						value="" onkeypress="fChgArea(this);" onchange="fChgArea(this);"
						OnBlur="fMayus(this);"></textarea></td>
				<td>
					<div class="m-form__actions">
						<button class="btn btn-primary" onClick="ValidaMotFun(this.form);">AGREGAR</button>
					</div>
				</td>
			</tr>
		</table>

		
		<%          
    }
}
    //}
 //}
%>

 
 <br><br><br><br> <br> <br> <br><br><br><br><br><br><br>
<%
System.out.println("jspD 45");
          out.print("<table align='left'>");
          out.print("<tr><td>&nbsp;</td></tr>");
          String cNombre = "Dr(a). " + vUsuario.getCNombre() + " " + vUsuario.getCApPaterno() + " " + vUsuario.getCApMaterno();
          cNombre = clsConfig.nombreMedicoDictaminador(iCveExpediente, iNumExamen);
          int iLong = cNombre.length();
          String cLinea = "";
          for(int i=0; i<iLong; i++)
            cLinea +="_";

          System.out.println("jspD 46");          
          
          out.print("&nbsp;");
          out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cLinea + "</td></tr>");
          out.print("<tr><td class='EEtiquetaC' colspan='6'>" + cNombre + "</td></tr>");
          out.print("</table>");
/*
        if(request.getParameter("hdBoton").toString().compareTo("Generar Reporte")==0) {
           out.println(clsConfig.getActiveX());
        }*/
        System.out.println("jspD 47");
        
%>
		<br> <br> <br> <br>
		<%
		if(guardoDiagnostico){
			System.out.println("guardoDiagnostico="+guardoDiagnostico);
		%>
			<br> <br> <br> <br><br> <br> <br> <br><br> <br> <br> <br><br> <br> <br> <br><br> <br> <br> <br><br> <br> <br> <br>
		<%
		}
		 %>

		<div class="m-form__actions">
			<button class="btn btn-primary" onClick="fValidaTodo()">Guardar</button>
			<button type="reset" class="btn btn-secondary">Cancel</button>
			<button class="btn btn-secondary" onClick="fPrint()">Imprimir</button>
		</div>
		</div>
		</td>
		</tr>
		<!-- <script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script> -->  
  <%
  //}%>

 </table>

</form></body>
</html>



