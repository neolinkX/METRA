<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--  
    Document   : pg070104071
    Created on : 3/05/2012, 03:19:54 PM
    Author     : AG SA
--%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.vo.*"%>
<%@ page import="com.micper.sql.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.*"%>

<%@ page import="com.micper.seguridad.dao.*"%>





<html>
<%
  pg070104071CFG  clsConfig = new pg070104071CFG();                 // modificar

  TEntorno    vEntorno      = new TEntorno();
  vEntorno.setNumModulo(07);
  vEntorno.setNombrePagina("pg070104071.jsp");                    // modificar
  vEntorno.setArchTooltips("07_Tooltips");
  vEntorno.setOnLoad("fOnLoad();");
  vEntorno.setArchFuncs("FValida");
  vEntorno.setMetodoForm("POST");
  vEntorno.setActionForm("pg070104071.jsp\" target=\"FRMCuerpo"); // modificar
  vEntorno.setUrlLogo("Acerca");
  vEntorno.setBtnPrincVisible(true);
  vEntorno.setArchFCatalogo("FFiltro");
  vEntorno.setArchAyuda(vEntorno.getNombrePagina());

  String cCatalogo    = "pg070101011.jsp";       // modificar
  String cDetalle    = "pg070101011.jsp";       // modificar
  String cDiagnostico    = "pg070101020.jsp";       // modificar
  String cRecomendaciones    = "pg070101030.jsp";       // modificar
  String cOperador    = "1";                   // modificar
  String cDscOrdenar  = "Clave|Descripción|";    // modificar
  String cCveOrdenar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cDscFiltrar  = "Clave|Descripción|";    // modificar
  String cCveFiltrar  = "iCveEspecialidad|cDscEspecialidad|";  // modificar
  String cTipoFiltrar = "7|8|";                // modificar
  boolean lFiltros    = true;                  // modificar
  boolean lIra        = false;                  // modificar
  String cEstatusIR   = "Imprimir";            // modificar

  // LLamado al Output Header
  TParametro   vParametros   = new TParametro(vEntorno.getNumModuloStr());
  int iNumRowsPrin = new Integer(vParametros.getPropEspecifica("NumRowsPrin")).intValue();
  int iNumRowsSec  = new Integer(vParametros.getPropEspecifica("NumRowsSec")).intValue();
  int iServicioCardio = new Integer(vParametros.getPropEspecifica("EPIServicioCardio")).intValue();
  int LenteDC = 0;//Utiliza lentes de correccion 0=NO 1=SI

  TError       vErrores      = new TError();
  StringBuffer sbErroresAcum = new StringBuffer();
  TEtiCampo    vEti          = new TEtiCampo();
  TEtiCampoR    vEtiR          = new TEtiCampoR();
  TFechas dtFecha = new TFechas();

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
  String cClave    = "";
  String cPosicion = "";
  String cInterCon = "";
  /////VARIABLES RIESGO CORONARIO /////////////
  int Hipertension = 0;
  int Glucemia = 0;
  int Puntaje = 0;
  int PuntajeRep = 0;
  int PuntajeIAM = 0;
  int PuntajeSaos = 0;
  int Obesidad = 0;
  int FreCar = 0;
  int FSistolica = 0;
  int FDiastolica = 0;
  int Reposo = 0;
  int Glucemia2 =0;
  //////////////IMC para SAOS////////////////////
  int IMCSAOS =0;
 /////////////Prueba de detección de alcoholismo de Michigan(MAST)////////////////
  int IMichigan = 0;
 ///////////////////////////////////       TABAQUISMO      ///////////////////////////////////////////////
  int Itabaquismo = 0;
  ///////////////////////////////////       DROGAS      ///////////////////////////////////////////////
  int idrogas = 0;
 ///////////////////////////////////       PSICOLOGIA      ///////////////////////////////////////////////
  int Psicologia1 = 0;
  int Psicologia2 = 0;
  int Psicologia3 = 0;
  int Psicologia4 = 0;
  
  
  int iRama = 0, iCol = 0, iContador = 0;;
  int ResulTorax = 0;

  TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

  TDEXPDiagnostico dEXPDiagnostico = new TDEXPDiagnostico();
  TVEXPDiagnostico vEXPDiagnostico = new TVEXPDiagnostico();
  Vector vcEXPDiagnostico = new Vector();

  TDEXPRecomendacion dEXPRecomendacion = new TDEXPRecomendacion();
  TVEXPRecomendacion vEXPRecomendacion = new TVEXPRecomendacion();
  Vector vcEXPRecomendacion = new Vector();
  
  TDMEDRespSint dMEDRespSint = new TDMEDRespSint();
  TDMEDREGSIN dMEDREGSIN = new TDMEDREGSIN();
  TDMEDAReg dMEDAReg = new TDMEDAReg();
  TDEXPResultadoDos resultaDos= new TDEXPResultadoDos(); 
  String ResultadoDos = "";
  
  ////Obtener numero de categorias y modos de transporte
  TDEXPExamCat dEXPExamCat = new TDEXPExamCat();
  int NumTramites = 0;
    try{
        String Query = " SELECT COUNT(ICVEEXPEDIENTE) FROM EXPEXAMCAT WHERE ICVEEXPEDIENTE = "+request.getParameter("hdiCveExpediente")+" AND INUMEXAMEN = "+request.getParameter("hdiNumExamen")+"";
        NumTramites = dEXPExamCat.FindByInt(Query);
    }catch(Exception e){
                        NumTramites = 0;
                        e.printStackTrace();
   }
  
    int Atendido = 0;
    try{
        String Query = " SELECT COUNT(ICVEEXPEDIENTE) FROM EXPREGSIN WHERE ICVEEXPEDIENTE = "+request.getParameter("hdiCveExpediente")+" AND INUMEXAMEN = "+request.getParameter("hdiNumExamen")+" AND ICVESERVICIO = "+request.getParameter("hdICveServicio").toString();
        Atendido = dEXPExamCat.FindByInt(Query);
    }catch(Exception e){
                        Atendido = 0;
                        e.printStackTrace();
   }

int iCveLabC = new Integer(vParametros.getPropEspecifica("CveLabClin")).intValue();
  
int iCveMdoTrans =  clsConfig.getMDOTrans(request.getParameter("hdiCveExpediente"),request.getParameter("hdiNumExamen"));
int iCvecategoria =  clsConfig.getCategoria(request.getParameter("hdiCveExpediente"),request.getParameter("hdiNumExamen"));
  
%>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+vEntorno.getNombrePagina().substring(0,vEntorno.getNombrePagina().length()-1)%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")%>pg070104021.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"calendario.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=vParametros.getPropEspecifica("RutaFuncs")+"Audio02.js"%>"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

  function Genera_Doc(){
	   form = document.forms[0];
	   form.target="_self";
	   form.hdReporte.value = 'Reporte';
	   form.submit();
   }

  function fOnLoad(){
    fOnSet(window,document.forms[0].action,'<%=cCanWrite%>','<%=cUpdStatus%>','<%=cSaveAction%>','<%=cDeleteAction%>','<%=cNavStatus%>',
          '<%=cOtroStatus%>',<%=lFiltros%>,<%=lIra%>,'<%=cDscPaginas%>','<%=cPaginas%>','<%=cOperador%>','<%=cDscFiltrar%>',
          '<%=cCveFiltrar%>','<%=cTipoFiltrar%>','<%=cDscOrdenar%>','<%=cCveOrdenar%>','<%=cEstatusIR%>',true,'<%=vEntorno.getArchAyuda()%>');
  }

  var cRutaImgServer = '<%=vParametros.getPropEspecifica("RutaImgServer")%>';
  var cRutaImgLocal  = '<%=vParametros.getPropEspecifica("RutaImgLocal")%>';
  var cStyle = '<link rel="stylesheet" href="/medprev/wwwrooting/estilos/07_estilos.css" TYPE="text/css">';

  var aSel = new Array();
  
  
  function muestra_oculta(id){
      if (document.getElementById){ //se obtiene el id
                  var el = document.getElementById(id); //se define la variable "el" igual a nuestro div
                  //el.style.display = (el.style.display == 'none') ? 'block' : 'none'; //damos un atributo display:none que oculta el div
                  el.style.display = (el.style.display == 'block') ? 'none' : 'block'; //damos un atributo display:none que oculta el div
                                  }
  }
  function div(){/*hace que se cargue la funci�n lo que predetermina que div estar� oculto hasta llamar a la funci�n nuevamente*/
      muestra_oculta('contenido_a_mostrar');/* "contenido_a_mostrar" es el nombre que le dimos al DIV */
}

  
  function Genera_Doc2() {
	  //alert("Genera_Doc2");
		form = document.forms[0];
		form.target = "_self";
		// form.target="_blank";
		form.hdBoton2.value = 'Imprime Documentacion';
		generaPDFConsentInf();
		//form.submit();
	}

  
</SCRIPT>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Autor"
	content="<%= vParametros.getPropEspecifica("Autor") %>">
<META content="100000682718088" property="fb:admins">
<LINK rel="stylesheet" type="text/css"
	href="<%= vParametros.getPropEspecifica("RutaCSS")%>css.css">
<LINK rel="stylesheet" type="text/css"
	href="<%= vParametros.getPropEspecifica("RutaCSS")%>demo.css">
<LINK rel="stylesheet" type="text/css"
	href="<%= vParametros.getPropEspecifica("RutaCSS")%>adipoli.css">
<LINK rel="shortcut icon" href="http://jobyj.in/favicon.ico">
<SCRIPT type="text/javascript"
	src="<%= vParametros.getPropEspecifica("RutaFuncs")%>jquery-1.7.1.js"></SCRIPT>
<SCRIPT type="text/javascript"
	src="<%= vParametros.getPropEspecifica("RutaFuncs")%>jquery.adipoli.min.js"></SCRIPT>
<script
	src="<%= vParametros.getPropEspecifica("RutaFuncs")%>jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
                        jQuery.noConflict()
                        jQuery.imageMagnify={
                                dsettings: {
                                        magnifyby: 4, //default increase factor of enlarged image
                                        duration: 500, //default duration of animation, in millisec
                                        imgopacity: 0.02//opacify of original image when enlarged image overlays it
                                },
                                cursorcss: 'url(magnify.cur), -moz-zoom-in', //Value for CSS's 'cursor' attribute, added to original image
                                zIndexcounter: 100,

                                refreshoffsets:function($window, $target, warpshell){
                                        var $offsets=$target.offset()
                                        var winattrs={x:$window.scrollLeft(), y:$window.scrollTop(), w:$window.width(), h:$window.height()}
                                        warpshell.attrs.x=$offsets.left //update x position of original image relative to page
                                        warpshell.attrs.y=$offsets.top
                                        warpshell.newattrs.x=winattrs.x+winattrs.w/2-warpshell.newattrs.w/2
                                        warpshell.newattrs.y=winattrs.y+winattrs.h/2-warpshell.newattrs.h/2
                                        if (warpshell.newattrs.x<winattrs.x+5){ //no space to the left?
                                                warpshell.newattrs.x=winattrs.x+5	
                                        }
                                        else if (warpshell.newattrs.x+warpshell.newattrs.w > winattrs.x+winattrs.w){//no space to the right?
                                                warpshell.newattrs.x=winattrs.x+5
                                        }
                                        if (warpshell.newattrs.y<winattrs.y+5){ //no space at the top?
                                                warpshell.newattrs.y=winattrs.y+5
                                        }
                                },

                                magnify:function($, $target, options){
                                        var setting={} //create blank object to store combined settings
                                        var setting=jQuery.extend(setting, this.dsettings, options)
                                        var attrs=(options.thumbdimensions)? {w:options.thumbdimensions[0], h:options.thumbdimensions[1]} : {w:$target.outerWidth(), h:$target.outerHeight()}
                                        var newattrs={}
                                        newattrs.w=(setting.magnifyto)? setting.magnifyto : Math.round(attrs.w*setting.magnifyby)
                                        newattrs.h=(setting.magnifyto)? Math.round(attrs.h*newattrs.w/attrs.w) : Math.round(attrs.h*setting.magnifyby)
                                        $target.css('cursor', jQuery.imageMagnify.cursorcss)
                                        if ($target.data('imgshell')){
                                                $target.data('imgshell').$clone.remove()
                                                $target.css({opacity:1}).unbind('click.magnify')
                                        }	
                                        var $clone=$target.clone().css({position:'absolute', left:0, top:0, visibility:'hidden', border:'1px solid gray', cursor:'pointer'}).appendTo(document.body)
                                        $clone.data('$relatedtarget', $target) //save $target image this enlarged image is associated with
                                        $target.data('imgshell', {$clone:$clone, attrs:attrs, newattrs:newattrs})
                                        $target.bind('click.magnify', function(e){ //action when original image is clicked on
                                                var $this=$(this).css({opacity:setting.imgopacity})
                                                var imageinfo=$this.data('imgshell')
                                                jQuery.imageMagnify.refreshoffsets($(window), $this, imageinfo) //refresh offset positions of original and warped images
                                                var $clone=imageinfo.$clone
                                                $clone.stop().css({zIndex:++jQuery.imageMagnify.zIndexcounter, left:imageinfo.attrs.x, top:imageinfo.attrs.y, width:imageinfo.attrs.w, height:imageinfo.attrs.h, opacity:0, visibility:'visible', display:'block'})
                                                .animate({opacity:1, left:imageinfo.newattrs.x, top:imageinfo.newattrs.y, width:imageinfo.newattrs.w, height:imageinfo.newattrs.h}, setting.duration,
                                                function(){ //callback function after warping is complete
                                                        //none added		
                                                }) //end animate
                                        }) //end click
                                        $clone.click(function(e){ //action when magnified image is clicked on
                                                var $this=$(this)
                                                var imageinfo=$this.data('$relatedtarget').data('imgshell')
                                                jQuery.imageMagnify.refreshoffsets($(window), $this.data('$relatedtarget'), imageinfo) //refresh offset positions of original and warped images
                                                $this.stop().animate({opacity:0, left:imageinfo.attrs.x, top:imageinfo.attrs.y, width:imageinfo.attrs.w, height:imageinfo.attrs.h},  setting.duration,
                                                function(){
                                                        $this.hide()
                                                        $this.data('$relatedtarget').css({opacity:1}) //reveal original image
                                                }) //end animate
                                        }) //end click
                                }
                        };

                        jQuery.fn.imageMagnify=function(options){
                                var $=jQuery
                                return this.each(function(){ //return jQuery obj
                                        var $imgref=$(this)
                                        if (this.tagName!="IMG")
                                                return true //skip to next matched element
                                        if (parseInt($imgref.css('width'))>0 && parseInt($imgref.css('height'))>0 || options.thumbdimensions){ //if image has explicit width/height attrs defined
                                                jQuery.imageMagnify.magnify($, $imgref, options)
                                        }
                                        else if (this.complete){ //account for IE not firing image.onload
                                                jQuery.imageMagnify.magnify($, $imgref, options)
                                        }
                                        else{
                                                $(this).bind('load', function(){
                                                        jQuery.imageMagnify.magnify($, $imgref, options)
                                                })
                                        }
                                })
                        };

                        jQuery.fn.applyMagnifier=function(options){ //dynamic version of imageMagnify() to apply magnify effect to an image dynamically
                                var $=jQuery
                                return this.each(function(){ //return jQuery obj
                                        var $imgref=$(this)
                                        if (this.tagName!="IMG")
                                                return true //skip to next matched element

                                })	

                        };

                        jQuery(document).ready(function($){
                                var $targets=$('.magnify')
                                $targets.each(function(i){
                                        var $target=$(this)
                                        var options={}
                                        if ($target.attr('data-magnifyto'))
                                                options.magnifyto=parseFloat($target.attr('data-magnifyto'))
                                        if ($target.attr('data-magnifyby'))
                                                options.magnifyby=parseFloat($target.attr('data-magnifyby'))
                                        if ($target.attr('data-magnifyduration'))
                                                options.duration=parseInt($target.attr('data-magnifyduration'))
                                        $target.imageMagnify(options)
                                })
                                var $triggers=$('a[rel^="magnify["]')
                                $triggers.each(function(i){
                                        var $trigger=$(this)
                                        var targetid=$trigger.attr('rel').match(/\[.+\]/)[0].replace(/[\[\]']/g, '') //parse 'id' from rel='magnify[id]'
                                        $trigger.data('magnifyimageid', targetid)
                                        $trigger.click(function(e){
                                                $('#'+$(this).data('magnifyimageid')).trigger('click.magnify')
                                                e.preventDefault()
                                        })
                                })
                        })

                        </script>
<script type="text/javascript">(function(){if(-1!=navigator.userAgent.indexOf("Mobile")&&-1!=navigator.userAgent.indexOf("WebKit")&&-1==navigator.userAgent.indexOf("iPad")||-1!=navigator.userAgent.indexOf("Opera Mini")){var a;a:{var b=window.location.href,c=b.split("?");switch(c.length){case 1:a=b+"?m=1";break a;case 2:a=0<=c[1].search("(^|&)m=")?null:b+"&m=1";break a;default:a=null}}a&&window.location.replace(a)};})();
                        </script>
<style type="text/css">
/* Codigo correspondente al pie de pagina */
#footer {
	background-color: Green;
	height: 100px;
}

.Estilo21 {
	color: #FFFFFF;
	font-size: small;
}
</style>

<SCRIPT LANGUAGE="JavaScript">
var newPlacaToraxFile;
function showPlacasToraxFiles() {
  if (!newPlacaToraxFile || newPlacaToraxFile.closed) {
      newPlacaToraxFile = window.open("./MostrarArchivos.jsp?"
    		<%if(request.getParameter("iCveExpediente") != null){%>
            	+"iCveExpediente="+<%=request.getParameter("iCveExpediente")%>
            <%}else{%>
            	+"iCveExpediente="+<%=request.getParameter("hdiCveExpediente")%>
            <%}%>
            +"&iNumExamen="+<%=request.getParameter("hdiNumExamen")%>
            +"&iCveServicio="+<%=request.getParameter("hdICveServicio")%>
            +"&iCveRama="+<%=request.getParameter("hdICveRama")%>
            +"&iCveUsuario="+<%=vUsuario.getICveusuario()%>, "", "height=200,width=650,resizable=yes,menubar=0,scrollbars=yes");
      newPlacaToraxFile.focus( );
  } else if (newPlacaToraxFile.focus) {
      newPlacaToraxFile.focus( );
  }
}




function generaPDFConsentInf(){
	//alert("generaPDFConsentInf");
	frm = document.forms[0];
	var cConds = "alwaysRaised=yes,dependent=yes,width=800,height=485,location=no,menubar=no,resizable=yes,scrollbars=yes,titlebar=yes,statusbar=yes,toolbar=no";
      hWinHojaAyuda = window.open("servDiabetesCFG?hdNoExpedienteRep="+frm.hdiCveExpediente.value+"&hdiNumExamenRep="+frm.hdiNumExamen.value,
                                        "FRMConsenInf", cConds);
}




</SCRIPT>


</head>
<% new TCabeceras(pageContext).TCabecerasCat(vEntorno, vParametros); /* Agrega características generales de las páginas: (estilos, funciones, Barra Estado, Ayuda, Tooltips, etc) */	%>
<link rel="stylesheet"
	href="<%= vParametros.getPropEspecifica("RutaCSS") + vParametros.getPropEspecifica("HojaCSS") %>"
	TYPE="text/css">
<body bgcolor="<%= vParametros.getPropEspecifica("ColorFondoPagina") %>"
	topmargin="0" leftmargin="0" onLoad="<%= vEntorno.getOnLoad() %> div();">
	<form method="<%= vEntorno.getMetodoForm() %>"
		action="<%= vEntorno.getActionForm() %>">
		<input type="hidden" name="hdLCondicion"
			value="<% if (request.getParameter("hdLCondicion") != null) out.print(request.getParameter("hdLCondicion"));%>">
		<input type="hidden" name="hdLOrdenar"
			value="<% if (request.getParameter("hdLOrdenar") != null) out.print(request.getParameter("hdLOrdenar"));%>">
		<input type="hidden" name="FILNumReng"
			value="<% if (request.getParameter("FILNumReng") != null)  out.print(request.getParameter("FILNumReng")); else out.print(vParametros.getPropEspecifica("NumRengTab"));%>">
		<%
     if (bs != null){
       cPosicion = Integer.toString(bs.pageNo());
     }
  %>
		<input type="hidden" name="hdRowNum" value="<%=cPosicion%>"> <input
			type="hidden" name="hdCampoClave1" value="">

		<%
  %>

		<input type="hidden" name="hdiCveExpediente"
			value="<%=request.getParameter("hdiCveExpediente")%>"> <input
			type="hidden" name="iCveExpediente"
			value="<%=request.getParameter("hdiCveExpediente")%>"> <input
			type="hidden" name="hdiNumExamen"
			value="<%=request.getParameter("hdiNumExamen")%>"> <input
			type="hidden" name="iNumExamen"
			value="<%=request.getParameter("hdiNumExamen")%>"> <input
			type="hidden" name="hdICveServicio"
			value="<%=request.getParameter("hdICveServicio")%>"> <input
			type="hidden" name="iCveServicio"
			value="<%=request.getParameter("hdICveServicio")%>"> <input
			type="hidden" name="hdICveRama"
			value="<%=request.getParameter("hdICveRama")%>"> 
			<input type="hidden" name="hdBoton2" value="">
			<%
			if(request.getParameter("hdICveServicio").equals("1")){
			%>
				<input
				type="hidden" name="hdICveProceso"
				value="2"> <input
				type="hidden" name="iCveProceso"
				value="2"> 
			<%	
			}else{%>
			<input
				type="hidden" name="hdICveProceso"
				value="<%=request.getParameter("hdICveProceso")%>"> <input
				type="hidden" name="iCveProceso"
				value="<%=request.getParameter("hdICveProceso")%>">
				
			<%} %>
			
			<input
			type="hidden" name="hdCampoClave" value="<%=cClave%>"> <input
			type="hidden" name="tpoBusqueda"
			value="<%=request.getParameter("tpoBusqueda")%>"> <input
			type="hidden" name="hdCPropiedad" value=""> <input
			type="hidden" name="iCvePerfil"
			value="<%=request.getParameter("iCvePerfil")%>"> <input
			type="hidden" name="hdLCondicion" value=""> <input
			type="hidden" name="hdLOrdenar" value="">

		<%
   if (request.getParameter("hdReporte") != null){
     if(request.getParameter("hdReporte").compareTo("Reporte") ==0)
      out.println(clsConfig.getActiveX());
   }

 %>

		<input type="hidden" name="hdReporte" value="">


		<table
			background="<%= vParametros.getPropEspecifica("RutaImg") %>fondo01.jpg"
			width="100%" height="100%">
			<% if(clsConfig.getAccesoValido()){ %>
			<tr class="EPie">
				<td>&nbsp;<input type="hidden" name="hdBoton" value=""></td>
			</tr>
			<tr>
				<td>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<tr>
							<td class="ETablaT"><img
								src="<%=vParametros.getPropEspecifica("RutaImgServer")%>lsct.jpg"
								width="173" height="115" /></td>
							<td class="ETablaT" colspan="3"><p>
									EXPEDIENTE CLÍNICO ELECTRÓNICO NO.
									<%=request.getParameter("hdiCveExpediente")%>
									DE LA DIRECCIÓN
								</p>
								<p>GENERAL DE PROTECCIÓN Y MEDICINA PREVENTIVA EN EL
									TRANSPORTE.</p></td>
						</tr>
						<tr>
							<td class="ETablaT" colspan="4">Datos del Personal</td>
						</tr>
						<% // Encabezado del documento
    TVDinRep02 vEncabezado = new TVDinRep02();
    vEncabezado = clsConfig.getVEXPDatos();
	///Calculando Edad
  	long edad = clsConfig.getEdadPersonal(vEncabezado.getDate("dtNacimiento"));
	
	///Obteniendo variables para Diabetes Mellitus
	TDEXPExamAplica dExamAplica = new TDEXPExamAplica();
	int IMC = 0;
	int Grasa = 0;
	boolean NecesitaCDTDiabetes = false;// Necesita curva de tolerancia a diabetes
	//System.out.println(edad);
	if(Integer.parseInt(request.getParameter("hdICveServicio")) == 48){
		try{
			IMC= dExamAplica.RegresaInt("select dvalorini from expresultado "+ 
										"where icveexpediente = 586135 and inumexamen = 6 and "+ 
										"icveservicio = 11 and icverama = 1 and icvesintoma = 17");
			Grasa= dExamAplica.RegresaInt("select dvalorini from expresultado "+ 
					"where icveexpediente = 586135 and inumexamen = 6 and "+ 
					"icveservicio = 11 and icverama = 1 and icvesintoma = 16");
		}catch(Exception e){
			System.out.println("Error al ejecutar la consulta de signos vitales");
		}
	}

	//System.out.println(IMC);
	//System.out.println(Grasa);
	if(edad >= 45){
		if(IMC >=25 || Grasa >= 35){
			NecesitaCDTDiabetes = true;
		}
	}
	//System.out.println(NecesitaCDTDiabetes);
    
	
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","Expediente:").toString() + vEti.Texto("ETabla",vEncabezado.getString("iCveExpediente")).toString());
    
    out.println(vEti.Texto("EEtiqueta","Edad:").append(vEti.Texto("ETabla", "" + clsConfig.getEdadPersonal(vEncabezado.getDate("dtNacimiento")) ) ));
    out.print("</tr>");
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","RFC:").append(vEti.Texto("ETabla",vEncabezado.getString("cRFC"))));
    out.println(vEti.Texto("EEtiqueta","Sexo:").append(vEti.Texto("ETabla","F".compareTo(vEncabezado.getString("cSexo")) == 0? "Mujer" : "Hombre" )));
    out.print("</tr>");
    out.println("<tr>" + vEti.Texto("EEtiqueta","Nombre:").append(vEti.TextoCS("ETabla", vEncabezado.getString("cApPaterno") + "&nbsp;" + vEncabezado.getString("cApMaterno") + "&nbsp;" + vEncabezado.getString("cNombre"),3)).toString() + "</tr>");
    out.println("<tr><td class='ETablaST' colspan='4'>Datos del Examen</td></tr>");
    out.println("<tr>" + vEti.Texto("EEtiqueta","Proceso:").toString() + vEti.TextoCS("ETabla",vEncabezado.getString("cDscProceso"),3).toString() + "</tr>");
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","Unidad M&eacute;dica:").append(vEti.Texto("ETabla",vEncabezado.getString("cDscUniMed"))));
    out.println(vEti.Texto("EEtiqueta","M&oacute;dulo:").append(vEti.Texto("ETabla", vEncabezado.getString("cDscModulo"))));
    out.print("</tr>");
    out.print("<tr>");
    out.println(vEti.Texto("EEtiqueta","Solicitado:").append(vEti.Texto("ETabla",dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtSolicitado"),"/"))));
    out.println(vEti.Texto("EEtiqueta","Realizado:").append(vEti.Texto("ETabla", dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtAplicacion"),"/"))));
    out.print("</tr>");

  %>
					</table>
				</td>
			</tr>
			<tr>
				<td class="ETablaR"><%= vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen","") %></td>
			</tr>
			<tr>
				<td valign="top">
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<%
      // Información del Servicio
      int iServicioActual  = Integer.parseInt(request.getParameter("hdICveServicio").toString());
      int iServLC  = Integer.parseInt(vParametros.getPropEspecifica("CveLabClin").toString());
      String cMedicoAplica = null;
      out.println("<tr><td class='ETablaT' colspan='6'>" + vEncabezado.getString("cDscServicio") + "</td></tr>");
      // Impresión de los Resultados
      if (clsConfig.getVSintomas() != null && clsConfig.getVSintomas().size() > 0){
        if(vEncabezado.getInt("lVariosMeds") == 0){
           out.println("<tr>");
           out.println(vEti.Texto("EEtiqueta","Servicio Efectuado:").append(vEti.TextoCS("ETabla",dtFecha.getFechaDDMMYYYY(vEncabezado.getDate("dtFin"),"/"),5)));
           out.println("<tr>");
           cMedicoAplica = vEncabezado.getString("cSiglasProf") + "&nbsp;" + vEncabezado.getString("cMedico") + "<br>" + vEncabezado.getString("cCedula");
        }
        
        
        ///Informacion Medico en operación
        if(iServicioActual == 1){
         	TDEMOExamen dEmo = new TDEMOExamen();
         	TVEMOExamen vEmo = new TVEMOExamen();
         	Vector cvEmo = new Vector();
         	String respuestas = "";
         	cvEmo = dEmo.FindByAllEMOV2(request.getParameter("hdiCveExpediente"), request.getParameter("hdiNumExamen"));
         	for(int i=0;i<cvEmo.size();i++){
         		vEmo = (TVEMOExamen) cvEmo.get(i);
        		out.println("<td class=\"EPEtiqueta\">Captura del Examen:</td>");
        		if(vEmo.getICveCapturaDelExamen() == 1){ respuestas = "EN TIEMPO REAL";	}
        		if(vEmo.getICveCapturaDelExamen() == 2){ respuestas = "POSTERIOR A LA REALIZACI&Oacute;N";	}
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+respuestas+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">Médico Dictaminador:</td>");
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+vEmo.getCNombre()+" "+vEmo.getCApPaterno()+" "+vEmo.getCApMaterno()+"<br>&nbsp;&nbsp;</td>"); 
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">Nombre del Médico Dictaminador:</td>");
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+vEmo.getCMedDic()+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">Folio:</td>");
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+vEmo.getICveFolio()+""+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">¿Licencia?:</td>");
         		if(vEmo.getLSinLicencia() == 0){ respuestas = "SI";	}
        		if(vEmo.getLSinLicencia() == 1){ respuestas = "NO";	}
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+respuestas+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">No. de Licencia:</td>");
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+vEmo.getCLicencia()+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">No. Económico o Matrícula del Vehículo:</td>");
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+vEmo.getCMatricula()+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">Origen:</td>");
         		out.println("<td  colspan=\"3\" class=\"EPTabla\">"+vEmo.getCOrigen()+"<br>&nbsp;&nbsp;</td>");
         		out.println("<td  colspan=\"2\" class=\"EPTabla\">"+vEmo.getCDscEstadoOrg()+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">Destino:</td>");
         		out.println("<td  colspan=\"3\" class=\"EPTabla\">"+vEmo.getCDestino()+"<br>&nbsp;&nbsp;</td>");
         		out.println("<td  colspan=\"2\" class=\"EPTabla\">"+vEmo.getCDscEstadoDes()+"<br>&nbsp;&nbsp;</td>");
         		out.println("</tr><tr>");
         		out.println("<td class=\"EPEtiqueta\">Fecha y hora de aplicaci&oacute;n del examen:</td>");
         		out.println("<td  colspan=\"5\" class=\"EPTabla\">"+vEmo.getTIAplicacion()+"<br>&nbsp;&nbsp;</td>");
         	}
        }
        
        
        
        
        
        
        
        
        out.println("<tr><td colspan='6' class='ETablaT'>Resultados</td></tr>");
        // Variables requeridas
        int iServOdontologia = Integer.parseInt(vParametros.getPropEspecifica("EPIServicioOdonto").toString());

        String cEtiquetas  = "";
        TVDinRep02 vSintoma = null;
        for(int i=0; i < clsConfig.getVSintomas().size(); i++){ // Despliegue de resultados
          vSintoma = new TVDinRep02();
          vSintoma = (TVDinRep02) clsConfig.getVSintomas().get(i);
          // Titulo de la Rama
          if(iRama != vSintoma.getInt("iCveRama")){
            if(iRama > 0){
            out.println("<tr class='EPie' colspan='6'>&nbsp;</tr>");
            }
            out.println("<tr>");
            if(vEncabezado.getInt("lVariosMeds") == 0)
              out.println(vEti.TextoCS("ETablaT",vSintoma.getString("cDscRama"),6));
            else{
              out.println(vEti.TextoCS("ETablaT",vSintoma.getString("cDscRama"),4));
              if(vSintoma.getInt("lConcluido") == 1){
                out.println(vEti.Texto("ETablaSTR","Servicio Efectuado:").append(vEti.Texto("ETablaST",dtFecha.getFechaDDMMYYYY(vSintoma.getDate("dtFin"),"/"))));
                // Persona que efectuó el examen
                if(iServicioActual != iServLC){
                  out.println("<tr>");
                  out.println(vEti.TextoCS("ETablaSTR","Realizado Por:",2));
                  out.println(vEti.TextoCS("ETablaST", vSintoma.getString("cSiglasProf") + "&nbsp;" + vSintoma.getString("cMedico") + "&nbsp;" +  vSintoma.getString("cCedula") ,4));
                  cMedicoAplica = vSintoma.getString("cSiglasProf") + "&nbsp;" + vSintoma.getString("cMedico") + "<br>" + vSintoma.getString("cCedula");
                  out.println("</tr>");
                }
              }    
              else
                out.println(vEti.TextoCS("EEtiquetaL","No Concluido",2));
            }
            out.println("</tr>");
            iRama = vSintoma.getInt("iCveRama");
            iContador = 0;
            // Impresión de los Títulos para Resultado y Valor de Referencia del Laboratorio
            if(vSintoma.getInt("lConcluido") == 1 &&
               iServicioActual               == iServLC){
               out.println("<tr>");
               for(int r = 0; r < 1; r++){
                  //out.println(vEti.Texto("EPTablaSTC","&nbsp;"));
                  //out.println(vEti.Texto("EPTablaSTC","Resultado"));
                  //out.println(vEti.Texto("EPTablaSTC","Valor de Referencia"));
                  %>
						<td class="EPTablaSTC" colspan="1">&nbsp;</td>
						<td class="EPTablaSTC" colspan="2">Resultado</td>
						<td class="EPTablaSTC" colspan="2">Valor de Referencia</td>
						<td class="EPTablaSTC">Alertas M&eacute;dicas</td>
						<%
               }
               out.println("</tr>");
            }
          } // Título de la Rama
          if(vSintoma.getInt("lConcluido") == 1){ // La rama está concluida por lo tanto se despliega la información
            iCol ++;
            // Mostrar el odontograma
            if(iServOdontologia == iServicioActual && iContador < 33){
              iContador++;
              if (iContador == 1)
                 out.print("<tr><td colspan ='6'><table align='center'class='ETablaInfo' border='1'><tr>");
              cEtiquetas += vEti.Texto2("ETablaT",vSintoma.getString("cPregunta"));
            } // Mostrar odontograma
            else{
              if(iCol == 3 || vSintoma.getInt("iCveTpoResp") == 4 || vSintoma.getInt("iCveTpoResp") == 6){
                out.println("</tr>");
                iCol = 1;
              }
              if(iCol == 1){
                out.println("<tr>");
              }
              // Mostrar pregunta de manera normal
              if(vSintoma.getInt("iCveTpoResp") == 6){
                out.println(vEti.TextoCS("ETablaST",vSintoma.getString("cPregunta") + ":", 6));
                iCol = 2;
              }
              else
                out.println(vEti.Texto("EPEtiqueta",vSintoma.getString("cPregunta") + ":"));
            }
            String cEtiqueta = "&nbsp;" + vSintoma.getString("cEtiqueta");
            
            ////Obteniendo Alertas////
            String AlertaR = "";
            String sentencia = "";
            String sentencia2 = "";
            String OperadorS = "";
            int operador = 0;
            

        String resultado = vSintoma.getString("cResultado");

        if(vSintoma.getInt("iCveTpoResp") == 1 || 
                    vSintoma.getInt("iCveTpoResp") == 3 || 
                    vSintoma.getInt("iCveTpoResp") == 5 || 
                    vSintoma.getInt("iCveTpoResp") == 8){
            
                if(vSintoma.getInt("iCveTpoResp") == 8 && vSintoma.getString("cResultado").equals("-1")){
                     	resultado = "0";
                     }
            
                if(vSintoma.getString("cResultado").equals("Sí")){
                		resultado = "1";
                	}
		if(vSintoma.getString("cResultado").equals("Si")){
				resultado = "1";
			}
		if(vSintoma.getString("cResultado").equals("SI")){
				resultado = "1";
			}
        if(vSintoma.getString("cResultado").equals("No")){
        		resultado = "0";
        	}
		if(vSintoma.getString("cResultado").equals("NO")){
				resultado = "0";
			}
                        try{
                        	
                        	if(vSintoma.getInt("iCveTpoResp") == 1){
                        		sentencia2 = "( M.LOGICA = "+resultado+") ";
                        		 sentencia = sentencia2 +" AND "
                                         + " C.ICVEEXPEDIENTE = "+request.getParameter("hdiCveExpediente")+" AND "
                                         + " C.INUMEXAMEN = "+request.getParameter("hdiNumExamen")+" AND "
                                         + " M.ICVESERVICIO = "+iServicioActual+" AND "
                                         + " M.ICVERAMA = "+iRama+" AND "
                                         + " M.ICVESINTOMA = "+vSintoma.getInt("iCveSintoma")+" "; 
                        		 AlertaR = dMEDREGSIN.FindByAlReg(sentencia,NumTramites,Atendido);
                        	}else{
                        	
                                 OperadorS="   R.ICVESERVICIO = "+iServicioActual+" AND "
                                                        + " R.ICVERAMA = "+iRama+" AND "
                                                        + " R.ICVESINTOMA = "+vSintoma.getInt("iCveSintoma")+" AND "
                                                        + " R.RDACCION = 1 ";
                                  operador = dMEDREGSIN.FindOp(OperadorS);
                                           
                                  if(operador > 0){
                                        if(operador == 1){ sentencia2 = "( "+resultado+" > M.IMAYORA) ";}
                                        if(operador == 10){ sentencia2 = "( M.IIGUALA = "+resultado+") ";}
                                        if(operador == 11){ sentencia2 = "( "+resultado+" > M.IMAYORA OR M.IIGUALA = "+resultado+") ";}
                                        if(operador == 100){ sentencia2 = "( "+resultado+" < M.IMENORA) ";}
                                        if(operador == 101){ sentencia2 = "( "+resultado+" > M.IMAYORA OR "+resultado+" < M.IMENORA) ";}
                                        if(operador == 110){ sentencia2 = "( "+resultado+" < M.IMENORA OR M.IIGUALA = "+resultado+") ";}
                                        if(operador == 111){ sentencia2 = "( "+resultado+" < M.IMENORA OR "+resultado+" > M.IMAYORA OR M.IIGUALA = "+resultado+") ";}
                                        
                                            //sentencia = " (  M.IMAYORA > "+resultado+" OR "
                                            //            + "  M.IIGUALA = "+resultado+"  "
                                            //            + "  OR M.IMENORA < "+resultado+" "
                                            sentencia = sentencia2 +" AND "
                                                        + " C.ICVEEXPEDIENTE = "+request.getParameter("hdiCveExpediente")+" AND "
                                                        + " C.INUMEXAMEN = "+request.getParameter("hdiNumExamen")+" AND "
                                                        + " M.ICVESERVICIO = "+iServicioActual+" AND "
                                                        + " M.ICVERAMA = "+iRama+" AND "
                                                        + " M.ICVESINTOMA = "+vSintoma.getInt("iCveSintoma")+" "; 
                                        if(LenteDC == 0 && (vSintoma.getInt("iCveSintoma")== 25 ||
                                        		             vSintoma.getInt("iCveSintoma")== 26 ||
                                        		             vSintoma.getInt("iCveSintoma")== 27 ||
                                        		             vSintoma.getInt("iCveSintoma")== 28)){
                                        			LenteDC = 0;//Debido a que no usa lentes las reglas de los siguientes sintomas no deben registarrse y tampoco mostrar alerta.                                        					
                                        	}else{
                                        		if(!sentencia2.equals(""))
                                            		AlertaR = dMEDREGSIN.FindByAlReg(sentencia,NumTramites,Atendido);
                                            }  
                                   }
                        	}
                        }catch(Exception e){
                                                AlertaR = "";
                                                e.printStackTrace();
                        }
        }
        System.out.println(vSintoma.getInt("iCveSintoma") +" = "+AlertaR);
///////////////////////////SI EXISTE ALERTA VERIFICAR INTERCONSULTA/////////////////////////////////////////////////                       
           if(!AlertaR.equals("")){
               String ServInter ="";
                       try{
                             ServInter = dMEDAReg.SenFindBy("SELECT A.SERINTERCON "
                                                          + "FROM MEDREGSIN AS S, MEDAREG AS A "
                                                          + "WHERE "
                                                          + "S.ICVESERVICIO = A.ICVESERVICIO  AND "
                                                          + "S.ICVERAMA = A.ICVERAMA AND "
                                                          + "S.ICVESINTOMA = A.ICVESINTOMA AND "
                                                          + "S.ICVEREGLA = A.ICVEREGLA AND "
                                                          + "S.RDACCION = 1  AND "
                                                          + "LACTIVO = 1 AND "
                                                          + "(S.IMAYORA > "+resultado+" OR "
                                                          + "S.IIGUALA = "+resultado+" OR "
                                                          + "S.IMENORA < "+resultado+"  ) AND "
                                                          + "S.ICVEMDOTRANS = "+iCveMdoTrans+" AND  "
                                                          + "S.ICVECATEGORIA = "+iCvecategoria+" AND "
                                                          + "S.ICVESERVICIO = "+iServicioActual+" AND "
                                                          + "S.ICVERAMA = "+iRama+" AND "
                                                          + "S.ICVESINTOMA = "+vSintoma.getInt("iCveSintoma"));
                              if(!ServInter.equals("")){
                                  if(!cInterCon.equals("")){
                                      cInterCon = cInterCon+ ","+ServInter;
                                  }else{
                                      cInterCon = ServInter;
                                  }
                              }
                        }catch(Exception e){
                            ServInter ="";
                       }
           }

           ///////////////////// R I E S G O    C O R O N A R I O //////////////////////////
            if(iServicioActual == 65){
                if(iRama == 1){
                    float Decimal = 0.0F;
                    try {
                        if(vSintoma.getInt("iCveSintoma") >= 28 ){
                                Decimal = Float.parseFloat(resultado.trim());
                        }
                    } catch (NumberFormatException ex) {
                           Decimal = 0.0F;
                    }
                    /// EDAD / ANT. FAM. IAM. EDAD / ANT. PERSONAL IAM.  /////////////
                     if(vSintoma.getInt("iCveSintoma") >= 28 && vSintoma.getInt("iCveSintoma") <= 34 ){
                        Puntaje = Puntaje + clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                    }
                    
                    /// TABAQUISMO / TENSION ARTERIAL / OBESIDAD / FREC. CARDIACA /////////////
                    if(vSintoma.getInt("iCveSintoma") >= 35 && vSintoma.getInt("iCveSintoma") <= 38 ){
                        AlertaR = clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, 0);
                        Puntaje = Puntaje + clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                        if(vSintoma.getInt("iCveSintoma") == 34){///ASIGNANDO PUNTAJE ANT. PERSONAL IAM.
                            PuntajeIAM = clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                        }
                        if(vSintoma.getInt("iCveSintoma") == 37){///ASIGNANDO VALOR OBESIDAD.
                            Obesidad = (int)Decimal;
                        }
                        if(vSintoma.getInt("iCveSintoma") == 38){///ASIGNANDO VALOR FRECUENCIA CARDIACA.
                            FreCar = (int)Decimal;
                        }
                    }
                    ///// HIPERTENCION / SISTOLICA / DISTOLICA ////////
                    if(vSintoma.getInt("iCveSintoma") == 39 || vSintoma.getInt("iCveSintoma") == 40 || vSintoma.getInt("iCveSintoma") == 41 ){
                            Hipertension = Hipertension + (int)Decimal;
                             if(vSintoma.getInt("iCveSintoma") == 41) {
                                 AlertaR = clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), Hipertension, 0);
                             }
                            Puntaje = Puntaje + clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                            if(vSintoma.getInt("iCveSintoma") == 40){///ASIGNANDO VALOR SISTOLICA
                                FSistolica = (int)Decimal;
                            }
                            if(vSintoma.getInt("iCveSintoma") == 41){///ASIGNANDO VALOR DIASTOLICA
                                FDiastolica = (int)Decimal;
                            }
                    }
                    ///// REPOSO / COLESTEROL /////
                    if(vSintoma.getInt("iCveSintoma") == 42 || vSintoma.getInt("iCveSintoma") == 45 ){
                        AlertaR = clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, 0);
                        Puntaje = Puntaje + clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                        if(vSintoma.getInt("iCveSintoma") == 42){////ASIGNANDO PUNTAJE REPOSO
                            PuntajeRep = clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                            Reposo = (int)Decimal;
                        }
                        
                    }
                    ///// DIABETES / GLUCEMIA /////
                    if(vSintoma.getInt("iCveSintoma") >= 43 && vSintoma.getInt("iCveSintoma") <= 44 ){
                        Glucemia = Glucemia + (int)Decimal;
                        if(vSintoma.getInt("iCveSintoma") == 44) {
                                AlertaR = clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, Glucemia);
                                Glucemia2 = (int)Decimal;
                        }
                        Puntaje = Puntaje + clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                        
                    }
                    /// TRIGLICERIDOS / ACIDO URICO /////////////
                     if(vSintoma.getInt("iCveSintoma") >= 46 && vSintoma.getInt("iCveSintoma") <= 47 ){
                        Puntaje = Puntaje + clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), (int)Decimal, resultado);
                    }
                    ///// DIAGNOSTICOS RIESGO CORONARIO /////
                    if(vSintoma.getInt("iCveSintoma") >= 48 || vSintoma.getInt("iCveSintoma") <= 51){
                        AlertaR = clsConfig.RiesgoCor(vSintoma.getInt("iCveSintoma"), Puntaje, 0);
                            if(vSintoma.getInt("iCveSintoma") == 49){////REQUIERE PRUEBA ESFUERZO? 
                                    if(PuntajeRep == 3){
                                        AlertaR = "SI";
                                    }
                                    if(PuntajeRep < 3 && Puntaje <= 17){
                                        AlertaR = "NO";
                                    }
                                    if(Puntaje >= 18){
                                        AlertaR = "SI";
                                    }
                            }
                            if(vSintoma.getInt("iCveSintoma") == 50){////RAZONES PARA NAP? 
                                    if(PuntajeIAM == 0){
                                        AlertaR = "NO";
                                    }
                                    if(PuntajeIAM >= 1){
                                        AlertaR = "SI";
                                    }
                            }   
                            if(vSintoma.getInt("iCveSintoma") == 51){////OTRAS RAZONES PARA NAT?
                                    if(Obesidad == 4){
                                         AlertaR = "SI";
                                    }else{
                                            if(FreCar >= 105){
                                                AlertaR = "SI";
                                            }else{
                                                    if(FSistolica >= 160){
                                                        AlertaR = "SI";
                                                    }else{
                                                        if(FDiastolica  >= 100){
                                                            AlertaR = "SI";
                                                        }else{
                                                            if(Reposo  == 3){
                                                                AlertaR = "SI";
                                                            }else{
                                                                if(Glucemia2 >= 180){
                                                                    AlertaR = "SI";
                                                                }
                                                                if(Glucemia2 < 180){
                                                                    AlertaR = "NO";
                                                                }
                                                            }
                                                        }
                                                }   
                                            }
                                   }
                            }                                              
                    }  
                }
            }
           ////////////////////////////////////////////////////////////////////////////////////////////////
           
           /////////////////////////////// REGLAS MEDICO EN OPERACION ////////////////////////////////////
           //System.out.println("######### Reglas EMO ###########");
           //System.out.println("Servicio = "+iServicioActual);
            if(iServicioActual == 1){
            	//System.out.println("######### Reglas EMO ###########  - 1");
            	//System.out.println("iRama = "+iRama);
                if(iRama == 9){
                	//System.out.println("######### Reglas EMO ###########  - 2");
                	//System.out.println("iCveSintoma = "+vSintoma.getInt("iCveSintoma"));
                	if(vSintoma.getInt("iCveSintoma") == 87){
                		//System.out.println("######### Reglas EMO ###########  - 3");
                		AlertaR =  clsConfig.EmoValidaLEntes(vSintoma.getInt("iCveSintoma"), Integer.parseInt(vEncabezado.getString("iCveExpediente")), Integer.parseInt(vEncabezado.getString("iNumExamen")),
                				iCveMdoTrans,iCvecategoria);
                    }
                }
            }
            //System.out.println("######### Reglas EMO ###########  - 4");
           ////////////////////////////////////////////////////////////////////////////////////////////////
           
           
           
        

            
            switch(vSintoma.getInt("iCveTpoResp")){
              case 1:
              case 2:
					 if(iServicioActual == 67 && iRama == 1){
						if (vSintoma.getString("cPregunta").indexOf("(IMAGEN-") > 0){
						    out.println(vEtiR.Texto3("EPTabla",vSintoma.getString("cResultado") + "<br>"+ cEtiqueta));
						    out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
						}else{
						    out.println(vEtiR.Texto("EPTabla",vSintoma.getString("cResultado") + "<br>"+ cEtiqueta));
						    out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
						}
						if(vSintoma.getInt("iCveSintoma") == 24){
							if(vSintoma.getString("cResultado").equals("Si")){LenteDC = 1;}
						}
					 }else{
					     if(iServicioActual == 7 && iRama == 1 && vSintoma.getInt("iCveSintoma") < 33){
						 out.println(vEtiR.Texto3("EPTabla",vSintoma.getString("cResultado") ));
					     }else{
					    	 if(iServicioActual == 2){
										out.println(vEtiR.Texto3("EPTabla",vSintoma.getString("cResultado")));
										out.println(vEtiR.Texto3("EPTabla",cEtiqueta));
					    	 }else{
							    if(cEtiqueta.length() > 0){
									out.println(vEtiR.Texto4("EPTabla",vSintoma.getString("cResultado") + "<br>"+ cEtiqueta));
							    }else{
									out.println(vEtiR.Texto4("EPTabla",vSintoma.getString("cResultado") + ""+ cEtiqueta));
							    }
					    	 }
						    //out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
						    if(iServicioActual == 48 && iRama == 16){/////////////////////////////CARGA PUNTAJE /////////////////////////////////
								 PuntajeSaos = PuntajeSaos + clsConfig.SAOS(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
						    }
						    if(iServicioActual == 50 && iRama == 1){////////   Prueba de detección de alcoholismo de Michigan(MAST)  ////////   
								 IMichigan = IMichigan + clsConfig.MAST(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
								 if(vSintoma.getInt("iCveSintoma") == 39){
									    AlertaR =  clsConfig.AMAST(vSintoma.getInt("iCveSintoma"), IMichigan, IMichigan);
								 }
						    }
						     if(iServicioActual == 69 && iRama == 1){////////   Prueba de detección de Transtornos Psiquiatricos  ////////   
								 Psicologia1 = Psicologia1 + clsConfig.TPsi(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
								 Psicologia2 = Psicologia2 + clsConfig.TMen(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
								 Psicologia3 = Psicologia3 + clsConfig.PETD(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
								 Psicologia4 = Psicologia4 + clsConfig.TAH(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
								 if(vSintoma.getInt("iCveSintoma") == 3){
									    AlertaR =  clsConfig.ATPsi(vSintoma.getInt("iCveSintoma"), Psicologia1, Psicologia1);
								 }
								 if(vSintoma.getInt("iCveSintoma") == 32){
									    AlertaR =  clsConfig.ATMen(vSintoma.getInt("iCveSintoma"), Psicologia2, Psicologia2);
								 }
								 if(vSintoma.getInt("iCveSintoma") == 42){
									    AlertaR =  clsConfig.APETD(vSintoma.getInt("iCveSintoma"), Psicologia3, Psicologia3);
								 }
								 if(vSintoma.getInt("iCveSintoma") == 59){
									    AlertaR =  clsConfig.APETD(vSintoma.getInt("iCveSintoma"), Psicologia4, Psicologia4);
								 }
						    }
						    out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
						}
					 }
			                iCol = 2;
                break;
              case 4:
            		 if(iServicioActual == 2){
							out.println(vEtiR.Texto3("EPTabla",vSintoma.getString("cResultado")));
							out.println(vEtiR.Texto3("EPTabla",cEtiqueta));
            		 }else{            	 
		                if(cEtiqueta.length() > 0){
		                    out.println(vEti.TextoCS("EPTabla",vSintoma.getString("cResultado") + "<br>"+ cEtiqueta, 4));                    
		                }else{
		                    out.println(vEti.TextoCS("EPTabla",vSintoma.getString("cResultado") + cEtiqueta, 4));
		                }
		             }
                out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
                iCol = 2;
                break;
              case 3:
              case 5:
            	  if(iServicioActual == 2){
            		  out.println(vEtiR.Texto3("EPTabla",vSintoma.getString("cResultado")));
						out.println(vEtiR.Texto3("EPTabla",cEtiqueta));
      		 	  }else{  
		                if(cEtiqueta.length() > 0){
		                    out.println(vEtiR.Texto4("EPTablaR",vSintoma.getString("cResultado") + "<br>"+ cEtiqueta));
		                }else{
		                    out.println(vEtiR.Texto4("EPTablaR",vSintoma.getString("cResultado") + cEtiqueta));                    
		                }
		         }
                //out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
                if(iServicioActual == 48 && iRama == 16){/////////////////////////////CARGA PUNTAJE /////////////////////////////////
                             PuntajeSaos = PuntajeSaos + clsConfig.SAOS(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
                }
                out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
                iCol = 2;
                break;
              case 7:
                   iCol = 2;
                   break;
              case 8:
                          String respuestas = "";
                          boolean esnumero = true;
                          esnumero = clsConfig.isIntNumber(vSintoma.getString("cResultado").trim());
                                        try{
                                            if(esnumero){
                                                    String condicion = " icveservicio = "+iServicioActual
                                                                    + " and icverama = "+iRama
                                                                    + " and icvesintoma = "+vSintoma.getInt("iCveSintoma")
                                                                    + " and iorden = " +vSintoma.getString("cResultado") + " ";
                                                    respuestas = dMEDRespSint.FindByResp(condicion);
                                            }
                                        }catch(Exception e){
                                                    respuestas = "";
                                                    e.printStackTrace();
                                        }
                          
                        
                    
                            if(iServicioActual == 54 && iRama == 2){/////////////////////////////ALERTAS DE RESPUESTAS A TORAX/////////////////////////////////
                                
                                out.println(vEti.Texto("EPTabla",respuestas + cEtiqueta));
                                
                                    if(iRama == 2 && vSintoma.getInt("iCveSintoma") == 2){
                                            if(vSintoma.getString("cResultado").equals("2"))
                                                out.println(vEti.Texto("EPTabla","Solicite radiografía de tórax" + cEtiqueta));
                                            ResulTorax = ResulTorax + Integer.parseInt(vSintoma.getString("cResultado"));
                                    }
                                    if(iRama == 2 && vSintoma.getInt("iCveSintoma") == 3){
                                            if(vSintoma.getString("cResultado").equals("2"))
                                                out.println(vEti.Texto("EPTabla","Solicite radiografía de tórax" + cEtiqueta));
                                            if(vSintoma.getString("cResultado").equals("3"))
                                                out.println(vEti.Texto("EPTabla","Investigue causas de disminución de la ventilación" + cEtiqueta));
                                            ResulTorax = ResulTorax + Integer.parseInt(vSintoma.getString("cResultado"));
                                   }
                                    if(iRama == 2 && vSintoma.getInt("iCveSintoma") == 4){
                                            if(vSintoma.getString("cResultado").equals("2"))
                                                out.println(vEti.Texto("EPTabla","Posible condensación" + cEtiqueta));
                                            if(vSintoma.getString("cResultado").equals("3"))
                                                out.println(vEti.Texto("EPTabla","Solicite estudio radiográfico" + cEtiqueta));
                                            ResulTorax = ResulTorax + Integer.parseInt(vSintoma.getString("cResultado"));
                                   }
                                   if(iRama == 2 && vSintoma.getInt("iCveSintoma") == 5){
                                            if(vSintoma.getString("cResultado").equals("2"))
                                                out.println(vEti.Texto("EPTabla","Posible condensación" + cEtiqueta));
                                            if(vSintoma.getString("cResultado").equals("3"))
                                                out.println(vEti.Texto("EPTabla","Solicite estudio radiográfico" + cEtiqueta));
                                            if(vSintoma.getString("cResultado").equals("4")){
                                                out.println(vEti.Texto("EPTabla","Considere hiperreactividad bronquial, recomendable realizar espirometría" + cEtiqueta));
                                                ResulTorax = ResulTorax + 4;
                                            }
                                            ResulTorax = ResulTorax + Integer.parseInt(vSintoma.getString("cResultado"));
                                   }
                            }else{
                                    if(iServicioActual == 48 && iRama == 15){/////////////////////////////CARGA PUNTAJE /////////////////////////////////
                                        Puntaje = Puntaje + clsConfig.Epworth(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
                                        out.println(vEtiR.Texto4("EPTabla",respuestas + cEtiqueta));
                                        out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
                                    }else{
                                    	if(iServicioActual == 50 && iRama == 1){/////////////////////////////Tabaquismo y Drogas/////////////////////////////////
                                    		if(vSintoma.getInt("iCveSintoma") == 61 || 
                                    				vSintoma.getInt("iCveSintoma") == 63 || 
                                    				vSintoma.getInt("iCveSintoma") == 64 || 
                                    				vSintoma.getInt("iCveSintoma") == 65 || 
                                    				vSintoma.getInt("iCveSintoma") == 66 ){
                                    			if(!(resultado.equals("&nbsp;") || resultado.length()==0)){
                                    				Itabaquismo = Itabaquismo + clsConfig.Tabaquismo(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);	
                                    			}
                                            	
                                    		}
                                    		if(vSintoma.getInt("iCveSintoma") == 8 || vSintoma.getInt("iCveSintoma") == 9 || vSintoma.getInt("iCveSintoma") == 10 || vSintoma.getInt("iCveSintoma") == 11
                                    				|| vSintoma.getInt("iCveSintoma") == 12 || vSintoma.getInt("iCveSintoma") == 13 || vSintoma.getInt("iCveSintoma") == 14
                                    				|| vSintoma.getInt("iCveSintoma") == 15 || vSintoma.getInt("iCveSintoma") == 1 || vSintoma.getInt("iCveSintoma") == 2
                                    				|| vSintoma.getInt("iCveSintoma") == 3){
                                    			if(!(resultado.equals("&nbsp;") || resultado.length()==0)){
                                    				idrogas = idrogas + clsConfig.Drogas(vSintoma.getInt("iCveSintoma"), vSintoma.getInt("cResultado"), resultado);
                                    			}                                                    		
                                    		}
                                            if(vSintoma.getInt("iCveSintoma") == 66){
                                                 AlertaR = clsConfig.ATabaquismo(vSintoma.getInt("iCveSintoma"), Itabaquismo, Itabaquismo);
                                            }
                                             if(vSintoma.getInt("iCveSintoma") == 87){
                                                 AlertaR = clsConfig.ADrogas(vSintoma.getInt("iCveSintoma"), idrogas, idrogas);
                                               //        System.out.println("idrogas = "+idrogas + "   Alerta = "+AlertaR);
                                            }
                                            out.println(vEtiR.Texto("EPTabla",respuestas + cEtiqueta));
                                            out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
                                    }else{
                                                    out.println(vEtiR.Texto4("EPTabla",respuestas + cEtiqueta));
                                                    out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
                                            }
                                    }
                            }
                    iCol = 2;
                  break;
             case 9:
		    String IMG = "";
                                   
                             try{
                                            String condicion2 = " icveservicio = "+iServicioActual
                                                             + " and icverama = "+iRama
                                                             + " and icvesintoma = "+vSintoma.getInt("iCveSintoma")+" ";
                                            IMG = dMEDRespSint.FindByAllS(condicion2);
                            }catch(Exception e){
                                                    IMG = "";
                                                    e.printStackTrace();
                            }
                                        
                                    
                            out.println(vEtiR.Texto2("EPPie", vSintoma.getString("cValRef") + "<img src=\""+vParametros.getPropEspecifica("RutaImgServer")+IMG+"\" class=\"magnify\" class=\"img-style row5\" style=\"width: 225px; height: 150px; cursor: url(http://wixusimg.blogspot.mx/magnify.cur); opacity: 1; \">"));
                   //iCol = 0;
                   break;
             case 10:
                   iCol = 2;
                   break;
             case 11:
                       if(iServicioActual == 54 && iRama == 2){/////////////////////////////ALERTAS DE RESPUESTAS A TORAX/////////////////////////////////
                            if(iRama == 2 && vSintoma.getInt("iCveSintoma") == 1){
                                    if(vSintoma.getString("cResultado").equals("0")){
                                        out.println(vEti.Texto("EPTabla","<img src=\""+vParametros.getPropEspecifica("RutaImgServer")+"torax-1.png\"/>" + cEtiqueta));
                                        out.println(vEti.Texto("EPTabla","" + cEtiqueta));
                                    }                                
                                    if(vSintoma.getString("cResultado").equals("1")){
                                        out.println(vEti.Texto("EPTabla","<img src=\""+vParametros.getPropEspecifica("RutaImgServer")+"torax-4.png\"/>" + cEtiqueta));
                                        out.println(vEti.Texto("EPTabla","Tórax en tonel, considere enfisema" + cEtiqueta));
                                    }
                                    if(vSintoma.getString("cResultado").equals("2")){
                                        out.println(vEti.Texto("EPTabla","<img src=\""+vParametros.getPropEspecifica("RutaImgServer")+"torax-5.png\"/>" + cEtiqueta));
                                        out.println(vEti.Texto("EPTabla","Investigue trastornos congénitos o adquitridos de la caja torácica" + cEtiqueta));
                                    }
                                    if(vSintoma.getString("cResultado").equals("3")){
                                        out.println(vEti.Texto("EPTabla","<img src=\""+vParametros.getPropEspecifica("RutaImgServer")+"torax-6.png\"/>" + cEtiqueta));
                                        out.println(vEti.Texto("EPTabla","Pectum excavatum, investigue grado de restricción" + cEtiqueta));
                                    }
                                    if(vSintoma.getString("cResultado").equals("4")){
                                        out.println(vEti.Texto("EPTabla","<img src=\""+vParametros.getPropEspecifica("RutaImgServer")+"torax-7.png\"/>" + cEtiqueta));
                                        out.println(vEti.Texto("EPTabla","Tórax en quilla" + cEtiqueta));
                                    }
                                    
                                    boolean esnumero2 = true;
                                    esnumero2 = clsConfig.isIntNumber(vSintoma.getString("cResultado").trim());
                                        try{
                                            if(esnumero2){
                                                   ResulTorax = ResulTorax + Integer.parseInt(vSintoma.getString("cResultado"));
                                            }
                                        }catch(Exception e){
                                                    respuestas = "";
                                                    e.printStackTrace();
                                        }
                                    
                            }
                       }else{
                           if(iServicioActual == 65){/////////////////////////////ALERTAS DE RESPUESTAS A RIESGO CORONARIO/////////////////////////////////
                               if(cEtiqueta.length() > 0){
                                            out.println(vEtiR.Texto("EPTablaR",vSintoma.getString("cResultado") + "<br>"+ cEtiqueta));
                                    }else{
                                            out.println(vEtiR.Texto("EPTablaR",vSintoma.getString("cResultado") + cEtiqueta));                    
                                    }
                                    out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
                           }else{
                               if(iServicioActual == 48){/////////////////////////////ALERTAS DE RESPUESTAS A LA ESCALA DE SOMNOLENCIA DE EPWORTH /////////////////////////////////
                                   if(iRama == 15){   AlertaR = clsConfig.EsEpworth(vSintoma.getInt("iCveSintoma"), Puntaje, 0); }
                                   if(iRama == 16){   AlertaR = clsConfig.PSAOS(vSintoma.getInt("iCveSintoma"), PuntajeSaos, 0); }
                                    if(cEtiqueta.length() > 0){
                                            out.println(vEtiR.Texto4("EPTablaR",vSintoma.getString("cResultado") + "<br>"+ cEtiqueta));
                                    }else{
                                            out.println(vEtiR.Texto("EPTablaR",vSintoma.getString("cResultado") + cEtiqueta));                    
                                    }
                                    out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
                               }else{
                                    out.println(vEtiR.Texto("EPTabla","" + cEtiqueta));
                               }
                           }
                       }
                    iCol = 2;
                  break;
                
                  case 13:
                          String respuestas2 = "";
                          boolean esnumero2 = true;
                          //esnumero2 = clsConfig.isIntNumber(vSintoma.getString("cResultado").trim());
                                        try{
                                                    String condicion = " icveservicio = "+iServicioActual
                                                                    + " and icverama = "+iRama
                                                                    + " and icvesintoma = "+vSintoma.getInt("iCveSintoma")
                                                                    + " and iorden in (" +vSintoma.getString("cResultado") + ") ";
                                                    if(vSintoma.getString("cResultado").equals("&nbsp;")){
                                                    	//respuestas2 = dMEDRespSint.FindByResp2(condicion);	
                                                    }else{
                                                    	respuestas2 = dMEDRespSint.FindByResp2(condicion);	
                                                    }
                                                    
                                        }catch(Exception e){
                                                    respuestas2 = "";
                                                    e.printStackTrace();
                                        }
                           out.println(vEtiR.Texto("EPTabla",respuestas2 ));
                           out.println(vEtiR.Texto2("EPTabla",AlertaR+""+ cEtiqueta));
                        iCol = 2;
                  break;
                  
                    
                 case 12: // Links para JSP de imagenes de puntos de referencia
                                    //out.println("<tr>");
                                     int iCveServicio = iServicioActual;
                                     int iCveRama2 = iRama;
                                     int  iCveRama = iRama;
                                    if(iServicioActual == 54 && iRama == 2){
                                        if(vSintoma.getInt("iCveSintoma") == 7){
                                                    out.println("<td class='ECampo' colspan='2' align='center'><div align=\"center\">"); 
                                                    out.print(vEti.clsAnclaTexto("EEtiqueta","","javascript:fMosRef70("+request.getParameter("iCveExpediente")+","+request.getParameter("hdiNumExamen")+","+iCveServicio+","+iCveRama2+");","Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                                    out.println("</td>");
                                        }
                                    
                                        if(vSintoma.getInt("iCveSintoma") == 215){
                                                    out.println("<td class='ECampo' colspan='2' align='center'><div align=\"center\">"); 
                                                    out.print(vEti.clsAnclaTexto("EEtiqueta","","javascript:fMosRef54t("+request.getParameter("iCveExpediente")+","+request.getParameter("hdiNumExamen")+","+iCveServicio+","+iCveRama2+");","Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                                    out.println("</td>");
                                        }
                                    
                                    }
                                    if(iServicioActual == 54 && iRama == 1){
                                         if(vSintoma.getInt("iCveSintoma") == 216){
                                                    out.println("<td class='ECampo' colspan='2' align='center'><div align=\"center\">"); 
                                                    out.print(vEti.clsAnclaTexto("EEtiqueta","","javascript:fMosRef54a("+request.getParameter("iCveExpediente")+","+request.getParameter("hdiNumExamen")+","+iCveServicio+","+iCveRama+");","Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                                    out.println("</td>");
                                        }
                                          if(vSintoma.getInt("iCveSintoma") == 217){
                                                    out.println("<td class='ECampo' colspan='2' align='center'><div align=\"center\">"); 
                                                    out.print(vEti.clsAnclaTexto("EEtiqueta","","javascript:fMosRef54AE("+request.getParameter("iCveExpediente")+","+request.getParameter("hdiNumExamen")+","+iCveServicio+","+iCveRama+");","Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                                    out.println("</td>");
                                        }
                                          if(vSintoma.getInt("iCveSintoma") == 218){
                                                    out.println("<td class='ECampo' colspan='2' align='center'><div align=\"center\">"); 
                                                    out.print(vEti.clsAnclaTexto("EEtiqueta","","javascript:fMosRef54AMD("+request.getParameter("iCveExpediente")+","+request.getParameter("hdiNumExamen")+","+iCveServicio+","+iCveRama+");","Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                                    out.println("</td>");
                                        }
                                          if(vSintoma.getInt("iCveSintoma") == 219){
                                                    out.println("<td class='ECampo' colspan='2' align='center'><div align=\"center\">"); 
                                                    out.print(vEti.clsAnclaTexto("EEtiqueta","","javascript:fMosRef54AMI("+request.getParameter("iCveExpediente")+","+request.getParameter("hdiNumExamen")+","+iCveServicio+","+iCveRama+");","Mostrar") + "&nbsp;&nbsp;&nbsp;&nbsp;");
                                                    out.println("</td>");
                                        }
                                    }
                                  iCol = 2;
                                  break;
                 case 14:
                	 ResultadoDos = resultaDos.RegresaResp14(request.getParameter("hdiCveExpediente"), 
                			 request.getParameter("hdiNumExamen"), 
                			 vSintoma.getInt("iCveServicio")+"", 
                			 vSintoma.getInt("iCveRama")+"", 
                			 vSintoma.getInt("iCveSintoma")+"");
                		 
	            	if(cEtiqueta.length() > 0){
			              out.println(vEti.TextoCS("EPTabla",ResultadoDos + "<br>"+ cEtiqueta, 4));                    
			        }else{
			              out.println(vEti.TextoCS("EPTabla",ResultadoDos + cEtiqueta, 4));
			        }
	                out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
	                iCol = 2;
                break;
                case 15:
                	 ResultadoDos = resultaDos.RegresaResp15(request.getParameter("hdiCveExpediente"), 
                			 request.getParameter("hdiNumExamen"), 
                			 vSintoma.getInt("iCveServicio")+"", 
                			 vSintoma.getInt("iCveRama")+"", 
                			 vSintoma.getInt("iCveSintoma")+"");
                		 
	            	if(cEtiqueta.length() > 0){
			              out.println(vEti.TextoCS("EPTabla",ResultadoDos + "<br>"+ cEtiqueta, 4));                    
			        }else{
			              out.println(vEti.TextoCS("EPTabla",ResultadoDos + cEtiqueta, 4));
			        }
	                out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
	                iCol = 2;
                break;
                
                 case 16:
                	 ResultadoDos = resultaDos.RegresaResp16(request.getParameter("hdiCveExpediente"), 
                			 request.getParameter("hdiNumExamen"), 
                			 vSintoma.getInt("iCveServicio")+"", 
                			 vSintoma.getInt("iCveRama")+"", 
                			 vSintoma.getInt("iCveSintoma")+"");
                		 
	            	if(cEtiqueta.length() > 0){
			              out.println(vEti.TextoCS("EPTabla",ResultadoDos + "<br>"+ cEtiqueta, 4));                    
			        }else{
			              out.println(vEti.TextoCS("EPTabla",ResultadoDos + cEtiqueta, 4));
			        }
	                out.println(vEtiR.Texto2("EPTabla",AlertaR+"&nbsp;"));
	                iCol = 2;
                break;
                                                
                  
            } // Tipo de Respuesta
            // Presentar Etiqueta y Valores de Referencia
            if((iServOdontologia != iServicioActual && vSintoma.getInt("iCveTpoResp") != 6 )
                || iContador == 33){
		if (vSintoma.getString("cPregunta").indexOf("(IMAGEN-") == 0){
			out.println(vEtiR.Texto2("EPPie", vSintoma.getString("cValRef") + "&nbsp;"));
		}
	    }
            
            if(iContador == 16){
		if (vSintoma.getString("cPregunta").indexOf("(IMAGEN-") == 0){
		    out.print("</tr><tr>" + cEtiquetas + "</tr>");
		    cEtiquetas = "";
		}
            } // iContador == 16
            if(iContador == 32){
              out.print("</tr><tr>" + cEtiquetas + "</tr></table></td></tr>");
              iContador = 33;
              iCol = 0;
            }
          } // Rama concluida
        } // Despliegue de resultados
      } // fin del ciclo para presentar los resultados

   if(iServicioActual == 54 && iRama == 2){/////////////////////////////  ALERTAS DE RESPUESTAS A TORAX  /////////////////////////////////
                        String ResultadoFin ="";
                                if(iRama == 2 ){ 
                                    ResulTorax = ResulTorax -4;//Regla debido a que los combos se inicializan con 1 y no con 0 y son 4 respuestas de combo.
                                    if(ResulTorax >= 0 && ResulTorax < 3)
                                        ResultadoFin = "Sin compromiso respiratorio relevante";
                                     if(ResulTorax == 3)
                                        ResultadoFin = "Verifique movilidad torácica";
                                     if(ResulTorax >= 4 && ResulTorax < 6)
                                        ResultadoFin = "Probable Síndrome de condensación";
                                     if(ResulTorax >= 6 && ResulTorax < 8)
                                        ResultadoFin = "Probable síndrome de sustitución";
                                     if(ResulTorax >= 8)
                                        ResultadoFin = "Considere hiperreactividad bronquial, muy frecuente";
    %>
						<tr>
							<td colspan="3" class="EPEtiqueta"><%=ResultadoFin%></td>
						</tr>
						<%  }
  }
%>

					</table>
				</td>
			</tr>
			<tr>
				<td class="EPie">&nbsp;</td>
			<tr>
			<tr>
				<td valign="top">
					<%
   
//int iCveRamaANTERIOR = iCveRama;
//iCveRama = voRama.getICveRama();
boolean puedeSubirArchivo = false;
TDMEDServicio dMEDServicio = new TDMEDServicio();
puedeSubirArchivo = dMEDServicio.puedeSubirArchivos(iServicioActual);
if (puedeSubirArchivo) {
	%>
					<table border="1" class="ETablaInfo" align="center" cellspacing="0"
						cellpadding="3">
						<% // Inicio de Datos %>
						<tr>
							<td class="ETablaT" colspan="6">Mostrar Archivos</td>
						</tr>
						<tr>
							<td class="ETablaC" colspan="6">
								<%
	                                        out.print(vEti.clsAnclaTexto("EAncla","Mostrar archivos de imagenes","JavaScript:showPlacasToraxFiles();","Ver imagenes relacionadas"));
	                                      %>
							</td>
						</tr>
					</table>
					<br> <%
 	}

     TDEXPDictamenServ dEXPDictamenServ = new TDEXPDictamenServ();
     TDEXPServicio dEXPServ = new TDEXPServicio();
     //TDMEDServicio dMEDServicio = new TDMEDServicio();
     Vector vcMEDServicio = new Vector();
     TVMEDServicio vMEDServicio = new TVMEDServicio();
    String DescSer = "  WHERE ICVESERVICIO IN ("+cInterCon+")";
    int InterDict = 0;
    
    out.print("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
    
    if(NecesitaCDTDiabetes){
  	 out.print("<tr><td class='ETablaT'>ALERTA DIABETES</td></tr>");
  	 out.print("<tr><td class='ETabla'>Se solicita curva y/o hemoglobina Glucosilada</td></tr>");
  	 out.print("  </table>  <br>");
    }
    
    out.print("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
    
    if(!cInterCon.equals("")){
    
      try{
           vcMEDServicio = dMEDServicio.FindByAll(DescSer);
      }catch(Exception e){
                      vcMEDServicio = new Vector();
                      e.printStackTrace();
      }
    
    if(vcMEDServicio.size() > 0){
 	     out.print("<tr><td class='ETablaT'>Estudios necesarios para Dictaminar el servicio</td>");
 	     out.print("<td class='ETablaT'>Dictamen</td></tr>");
 	     for (int i=0;i<vcMEDServicio.size();i++){
 	                  vMEDServicio = (TVMEDServicio) vcMEDServicio.get(i);
 	                   out.print("<tr><td class='ETabla'>" + vMEDServicio.getCDscServicio() + "</td>");
 	                   /////////VERIFICAMOS QUE EXISTA EL DICTAMEN DEL SERVICIO/////////////
 	                   String Dictamen = "";
 	                   try{
 	                        Dictamen = dEXPDictamenServ.SenFindBy("SELECT LDICTAMEN "
 	                                                             + "FROM EXPDICTAMENSERV "
 	                                                             + "WHERE ICVEEXPEDIENTE = "+request.getParameter("hdiCveExpediente")+" AND "
 	                                                             + "INUMEXAMEN = "+request.getParameter("hdiNumExamen")+" AND "
 	                                                             + "ICVEMDOTRANS = "+iCveMdoTrans+" AND "
 	                                                             + "ICVECATEGORIA = "+iCvecategoria+" AND "
 	                                                             + "ICVESERVICIO = "+vMEDServicio.getICveServicio()+"");
 	                        
 	                        //////SE MARCA CON EL VALOR DE -100 A LOS SERVICIO OBLIGATORIOS POR EL RESULTADO DEL EXAMEN
 	                       dEXPServ.updateSQL(null, "UPDATE EXPSERVICIO SET IORDEN = -100 WHERE ICVEEXPEDIENTE = "+request.getParameter("hdiCveExpediente")+
 	                    		   " AND INUMEXAMEN = "+request.getParameter("hdiNumExamen")+" AND ICVESERVICIO IN("+cInterCon+")");
 	                    }catch(Exception e){
 	                                    Dictamen = "";
 	                                    e.printStackTrace();
 	                    }
 	                   if(Dictamen.equals("")){
 	                       Dictamen = "SIN CONCLUIR";
 	                       InterDict++;
 	                    }
 	                   if(Dictamen.equals("0")){
 	                       Dictamen = "NO APTO";
 	                   }
 	                   if(Dictamen.equals("1")){
 	                       Dictamen = "APTO";
 	                   }
 	                   
 	                   out.print("<td class='ETabla'>" + Dictamen + "</td></tr>");
 	     }
    }
    }else{
         out.print("<tr><td class='ETablaT'>No es necesario estudios adicionales para concluir este servicio</td></tr>");
         
    }
     
    	if (iServicioActual == 75 && iRama == 1) {/////////////////////////////  ALERTAS DE RESPUESTAS A TORAX  /////////////////////////////////
 			out.println("<tr>");
 			out.print("<td class=\"ECampo\">");
 			out.println("<div id=\"contenido_a_mostrar\">");
 			out.print(vEti
 					.clsAnclaTexto(
 							"EAncla",
 							"Impresión de Diabetes",
 							"JavaScript:muestra_oculta('contenido_a_mostrar');Genera_Doc2();",
 							"Generar Documentacion"));
 			//out.print(vEti.clsAnclaTexto("EAncla","Impresi�n de Dictamen","JavaScript:muestra_oculta('contenido_a_mostrar');mensaje();","Generar Documentacion"));
 			out.println("</div>");
 			out.print("</td>");
 			out.println("</tr>");
 		}

 		if (InterDict == 0) {
 			String RDIC = "";
 			try {
 				RDIC = dEXPDictamenServ.SenFindBy("SELECT LREQDIAG "
 						+ "FROM MEDSERVICIO " + "WHERE  "
 						+ "ICVESERVICIO = "
 						+ request.getParameter("hdICveServicio"));
 			} catch (Exception e) {
 				RDIC = "";
 				e.printStackTrace();
 			}
 			if(request.getParameter("hdICveServicio").equals("1")){////No mostrar dictamen EMO
 				RDIC ="0";
 			}
 			if(RDIC.equals("1")) {
 				out.print("<tr><td class='ETabla'>");
 				out.print(vEti
 						.clsAnclaTexto(
 								"EAncla",
 								"Dictamen del Servicio",
 								"JavaScript:fIr(0,0,'pg070104031.jsp?hdBoton=Actual');",
 								"Dictamen"));
 				out.print("</td></tr>");
 			} else {
 				out.print("<tr><td class='ETabla'>");
 				out.print("El servicio ha quedado concluido");
 				out.print("</td></tr>");
 			}

 		}
 		/*
 		if(iServicioActual == 11){
 		 String DispositivoSVS = clsConfig.getDispositivoSVS(); 
 		 if(DispositivoSVS.length()>10){
 		   out.print("</table><br>");
 		   out.print("<table border=\"1\" class=\"ETablaInfo\" align=\"center\" cellspacing=\"0\" cellpadding=\"3\">");
 		 		out.print("<tr><td class='ETablaT'>Datos del dispositivo con el que se capturo la temperatura, el pulso, tensión arterial sistólica y diastólica</td></tr>");
 		 		out.print("<tr><td class='ETabla'>");
 		      out.print(DispositivoSVS);
 		      out.print("</td></tr>");
 		 }
 		}
 		 */
 %>
				
		</table>
		</td>
		</tr>
		<%
  /*
  if(InterDict == 0){
%>
		<tr>
			<td class="EPie">&nbsp;</td>
		<tr>
		<tr>
			<td valign="top">
				<table border="1" class="ETablaInfo" align="center" cellspacing="0"
					cellpadding="3">
					<% // Inicio de Datos %>
					<%
      // Impresión de Diagnósticos, Recomendaciones, Nota Médica y Firma del Médico que emite la Nota   
      if(vEncabezado.getInt("lReqDiag") == 1){
         out.println("<tr><td class='ETablaT'>Diagnósticos</td></tr>");
         int iEspecialidad = 0;
         for(int i=0; i< clsConfig.getVDiagnostico().size(); i++){
            vEXPDiagnostico = (TVEXPDiagnostico) clsConfig.getVDiagnostico().get(i);
            if(iEspecialidad != vEXPDiagnostico.getICveEspecialidad()){
               iEspecialidad = vEXPDiagnostico.getICveEspecialidad();
               out.println("<tr><td class='ETablaST' >" + vEXPDiagnostico.getCDscEspecialidad() + "</td></tr>");
            }
            out.println("<tr><td class='ETabla'>" + vEXPDiagnostico.getCDscDiagnostico() + "</td></tr>");
         }
         out.println("<tr><td class='ETablaT'>Recomendaciones</td></tr>");
         int iRecomendacion = 0;
         for(int i=0; i < clsConfig.getVRemendacion().size(); i++){
            vEXPRecomendacion = (TVEXPRecomendacion) clsConfig.getVRemendacion().get(i);
            if(iRecomendacion != vEXPRecomendacion.getICveEspecialidad()){
              iRecomendacion = vEXPRecomendacion.getICveEspecialidad();
              out.println("<tr><td class='ETablaST'>" + vEXPRecomendacion.getCDscEspecialidad() + "</td></tr>");
            }
            out.println("<tr><td class='ETabla'>" + vEXPRecomendacion.getCDscRecomendacion() + "</td></tr>");
         }
         out.print("<tr><td class='ETablaT'>Nota Médica</td></tr>");
         out.print("<tr><td class='ETabla'>" + vEncabezado.getString("cNotaMedica") + "</td></tr>");
      }// Se imprimen diagnósticos y Recomendaciones
    %>
				</table>
			</td>
		</tr>
		<%
			}
		  */
		  
		    if(iServicioActual != iServLC){
		    	if(!cMedicoAplica.equals("&nbsp;null<br>")){
			      	out.println("<tr><td class='ETablaC'><br><b>" + cMedicoAplica + "</b></td></tr>");
		    	}
		    }
		   if(iServicioCardio == Integer.parseInt(request.getParameter("hdICveServicio"))){
		      out.print("<tr><td class='ETablaC'>");
		      out.print(vEti.clsAnclaTexto("EAncla","Impresión en el Electrocardiograma","JavaScript:Genera_Doc();", "Ver Examen",""));
		      out.print("</td></tr>");
		   }
		   //Inserción para visualizar las gráficas de Audiometría
		   if (vParametros.getPropEspecifica("Audiometria").equals(request.getParameter("hdICveServicio")) ||
			   vParametros.getPropEspecifica("Audiologia").equals(request.getParameter("hdICveServicio")) ||
			   vParametros.getPropEspecifica("AudiometriaH").equals(request.getParameter("hdICveServicio")) ||
		       vParametros.getPropEspecifica("AudiologiaH").equals(request.getParameter("hdICveServicio"))) {
		       TDEXPAudiomet dEXPAudiomet = new TDEXPAudiomet();
		       TVEXPAudiomet vEXPAudiomet;
		       Vector vcAudio = new Vector();
		       vcAudio = dEXPAudiomet.FindByAll(" where iCveExpediente = " + vEncabezado.getString("iCveExpediente") + " and iNumExamen = " + vEncabezado.getString("iNumExamen") +" AND iCveServicio = "+request.getParameter("hdICveServicio"));
		       if(vcAudio.size() > 0){
		         out.print("<SCRIPT LANGUAGE='JavaScript'>");
		         for (int i = 0; i < vcAudio.size(); i++){
		            vEXPAudiomet = (TVEXPAudiomet) vcAudio.get(i);
		            out.print("aSel["+i+"]=["+vEXPAudiomet.getIOido()+","+vEXPAudiomet.getITipo()+
		                                   ","+vEXPAudiomet.getIX()+","+vEXPAudiomet.getIY()+"];");
		         }
		         out.print("</SCRIPT>");
		       }
		              out.print("<tr><td>&nbsp;</td></tr>");
		              out.print("<tr><td>");
		              out.print("<table table border='1' border='0' align='center' width='524'><tr><td class='ETablaT'>Oido Derecho</td></tr></table>");
		              out.print("<SCRIPT LANGUAGE='JavaScript'>");
		              out.print("fGraphAudioV(1,aSel);");
		              out.print("</SCRIPT>");
		              out.print("</table>");
		              out.print("</td></tr>");
		              out.print("<tr><td>");
		              out.print("<table table border='1' border='0' align='center' width='524'><tr><td class='ETablaT'>Oido Izquierdo</td></tr></table>");
		              out.print("<SCRIPT LANGUAGE='JavaScript'>");
		              out.print("fGraphAudioV(2,aSel);");
		              out.print("</SCRIPT>");
		              out.print("</table>");
		              out.print("</td></tr>");
		      }
		   
				
		%>
		</td>
		</tr>
		<tr>
			<td class="ETablaR"><%= vEti.clsAnclaTexto("EAncla","Imprimir","JavaScript:fPrint();", "Ver Examen","") %></td>
		</tr>
		<%}else{%>
		<script language="JavaScript">fSalir(document.forms[0], window, '<%=vEntorno.getNumModuloStr()%>');</script>
		<%}%>
		</table>
	</form>
</body>
<%=vErrores.muestraError()%><%=new TDefPrecargar(vEntorno.getListaImgs()).getResultado()%>
<% vEntorno.clearListaImgs();%>
</html>



