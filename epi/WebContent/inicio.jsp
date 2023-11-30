<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="com.micper.seguridad.vo.TVUsuario"%>
<%@ page import="gob.sct.medprev.ControlEliminaExp"%>
<%@ page import="gob.sct.medprev.dao.*" %>
<%@ page import="gob.sct.medprev.dwr.vo.*"%>
<%@ page import="gob.sct.medprev.dwr.MedPredDwr"%>

<%@ page import="gob.sct.medprev.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="com.micper.ingsw.*"%>
<%@ page import="com.micper.util.caching.*"%>
<%@ page import="com.micper.util.*"%>
<%@ page import="com.micper.seguridad.vo.*"%>
<%@ page import="gob.sct.cis.dao.*"%>
<%@ page import="gob.sct.medprev.dao.*"%>
<%@ page import="gob.sct.medprev.dwr.vo.ClaseDatosInicio"%>
<%@ page import="gob.sct.medprev.dwr.MedPredDwr"%>
<%@ page import="java.io.*"%>


<!DOCTYPE html>
<%  
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());

System.out.println("-id==="+request.getParameter("id"));
System.out.println("-episodio==="+request.getParameter("episodio"));

//Session de paciente en turo
session.setAttribute("id", "0");
session.setAttribute("episodio", "0");

//session.setMaxInactiveInterval(400);




long totalSum = 0;
long startTime = System.currentTimeMillis();
totalSum+= (System.currentTimeMillis()-startTime);
System.out.println("Inicio="+startTime);





vEntorno.setNumModulo(07);
vEntorno.setArchFuncs("FValida");
vEntorno.setArchTooltips("07_Tooltips");
vEntorno.clearListaImgs();
vEntorno.setMetodoForm("POST");
vEntorno.setActionForm("pg0700009.jsp");
//vEntorno.setNombrePagina("pg0700009.jsp");
vEntorno.setNombrePagina("pg0700009.jsp");   
vEntorno.setOnLoad("fOnLoad();");
vEntorno.setArchFCatalogo("FFiltro");
vEntorno.setArchAyuda(vEntorno.getNombrePagina());

String cRutaImg = vParametros.getPropEspecifica("RutaImg");
String cTipoImg = vEntorno.getTiposImg().elementAt(0).toString();
String cRutaAyuda = vParametros.getPropEspecifica("html");
ClaseDatosInicio datosUsuario = new ClaseDatosInicio();
MedPredDwr medPred = new MedPredDwr();


TVUsuario vUsuario = (TVUsuario) request.getSession(true).getAttribute("UsrID");

System.out.println("Session Usr="+vUsuario);

if(vUsuario != null){
	System.out.println("Usuario en Session="+vUsuario.getICveusuario());
}else{
	System.out.println("Sacar de sistema");
    request.getRequestDispatcher("login.jsp").forward(request,response);
}
	





/////otras////
  /*SEDetPerCFG  clsConfig = new SEDetPerCFG();         
  if(clsConfig.getAccesoValido()){ 
	System.out.println("NO sacar");
	  //request.getRequestDispatcher("login.jsp").forward(request,response);
  }else{
	  System.out.println("Sacar de sistema000");  
	  
  }*/
//////////////////////

 session.setAttribute("Sicveusuario", ""+vUsuario.getICveusuario());
 session.setAttribute("Scusuario", ""+vUsuario.getCUsuario());
 session.setAttribute("SNombre", ""+vUsuario.getCNombre());

 





%>
<html lang="es" >

	<head>
		<meta charset="utf-8" />
		<title>
			M E D P R E V		</title>
		<meta name="description" content="Framedev -  Framework para desarrolladores">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/app/js/webfont.js"></script>
<script>
    WebFont.load({
       //google: {"families":["Poppins:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       google: {"families":["Montserrat:300,400,500,600,700","Roboto:300,400,500,600,700"]},
       active: function() {
           sessionStorage.fonts = true;
       }
     });
</script>
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
<!--<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/demo3/base/style.bundle.css" rel="stylesheet" type="text/css" />-->
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/base/style.bundle.css" rel="stylesheet" type="text/css" />
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/base/menu_alter.css" rel="stylesheet" type="text/css" />

<!--JQuery-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery-3.2.1.min.js"></script>

<!--Plugins JQuery-->
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-touchspin/bootstrap.touchspin.min.css" rel="stylesheet" type="text/css" />
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />

<!--APP-->
<link href="<%=vParametros.getPropEspecifica("RutaCssExp")%>app.css" rel="stylesheet" type="text/css">

<!--calendar-->
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/vendors/custom/jquery-ui/jquery-ui.bundle.css" rel="stylesheet" type="text/css" />
<link href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/vendors/custom/fullcalendar/fullcalendar.bundle.css" rel="stylesheet" type="text/css" />

<!--iconos-->
<link href="<%=vParametros.getPropEspecifica("RutaCssExp")%>svg.css" rel="stylesheet" type="text/css">

<!--Loader-->
<!-- <link href="http://10.33.143.52:7001/epi/css/loader.css" rel="stylesheet" type="text/css" /> -->
<link href="<%=vParametros.getPropEspecifica("RutaCssExp")%>loader.css" rel="stylesheet" type="text/css" />
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/loader.js"></script>










<!--jquery steps-->
<link href="<%=vParametros.getPropEspecifica("RutaCssExp")%>jquery.steps.css" rel="stylesheet" type="text/css" />

<link rel="shortcut icon" href="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>favicon.ico">
	</head>

  <body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default m-brand--minimize m-aside-left--minimize">
  <div id="initpreloader">;
  	   <div id="initloader"></div>
  </div>

  <div class="m-grid m-grid--hor m-grid--root m-page">
    <header class="m-grid__item    m-header "  data-minimize-offset="200" data-minimize-mobile-offset="200" >
   <div class="m-container m-container--fluid m-container--full-height">
      <div class="m-stack m-stack--ver m-stack--desktop">
         <!-- BEGIN: Brand -->
<div class="m-stack__item m-brand  m-brand--skin-dark ">
  <div class="m-stack m-stack--ver m-stack--general">

    <div class="m-stack__item m-stack__item--middle m-stack__item--center m-brand__logo">
      <a href="javascript:;" class="m-brand__logo-wrapper">
        <img width="100px"  alt="" src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>medprev.png"/>

      </a>
    </div>
    <div class="m-stack__item m-stack__item--middle m-brand__tools">
      <!-- BEGIN: Left Aside Minimize Toggle -->
      <a href="javascript:;" id="m_aside_left_minimize_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-desktop-inline-block">
        <span></span>
      </a>
      <!-- END -->
  <!-- BEGIN: Responsive Aside Left Menu Toggler -->
      <a href="javascript:;" id="m_aside_left_offcanvas_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-tablet-and-mobile-inline-block">
        <span></span>
      </a>
      <!-- END -->
  <!-- BEGIN: Responsive Header Menu Toggler -->
      <a id="m_aside_header_menu_mobile_toggle" href="javascript:;" class="m-brand__icon m-brand__toggler m--visible-tablet-and-mobile-inline-block">
        <span></span>
      </a>
      <!-- END -->
<!-- BEGIN: Topbar Toggler -->
      <a id="m_aside_header_topbar_mobile_toggle" href="javascript:;" class="m-brand__icon m--visible-tablet-and-mobile-inline-block">
        <i class="flaticon-more"></i>
      </a>
      <!-- BEGIN: Topbar Toggler -->
    </div>
  </div>
</div>
<!-- END: Brand -->
         <div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">
            <!-- BEGIN: Horizontal Menu -->
            <button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id="m_aside_header_menu_mobile_close_btn">
            <i class="la la-close"></i>
            </button>

            <!-- Corresponde a los breadcrumbs cuando estan en el lugar del menu, se modificaron para dar lugar al menu

            <div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark "  >
               <h5 class="m-subheader__title m-subheader__title--separator" id="breadcrumb-title" style="top: 33px; position: relative;">
                  M E D P R E V               </h5>
            </div>-->
            
<div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark "  >
   <ul class="m-menu__nav  m-menu__nav--submenu-arrow ">
            <li class="m-menu__item  m-menu__item--active m-menu__item--submenu m-menu__item--rel"  data-menu-submenu-toggle="click" aria-haspopup="true">
         <a  href="#" class="m-menu__link m-menu__toggle">
         <span class="m-menu__link-text">
         Framework
         </span>
         <i class="m-menu__hor-arrow la la-angle-down"></i>
         <i class="m-menu__ver-arrow la la-angle-right"></i>
         </a>
         <div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left">
            <span class="m-menu__arrow m-menu__arrow--adjust"></span>
            <ul class="m-menu__subnav">
                              <li class="m-menu__item  m-menu__item--active" aria-haspopup="true" >
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>perfil.jsp');" class="m-menu__link ">
                  <i class="menu_top_left flaticon-user"></i>
                  <span class="m-menu__link-text">
                  Mi perfil
                  </span>
                  </a>
               </li>
              
               <li class="m-menu__item" aria-haspopup="true"  data-redirect="true">
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>listaUnidades.jsp');" class="m-menu__link ">
                  <!-- <i class="menu_top_left flaticon-users"></i> -->
                  <i class="menu_top_left flaticon-list"></i>
                  <span class="m-menu__link-text">
                  Cat&aacute;logo
                  </span>
                  </a>
               </li>
                <!-- 
               <li class="m-menu__item sbmnufocus" aria-haspopup="true"  data-redirect="true">
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>controllers.jsp');" class="m-menu__link ">
                  <i class="menu_top_left flaticon-network"></i>
                  <span class="m-menu__link-text">
                  Controladores
                  </span>
                  </a>
               </li>
                              <li class="m-menu__item sbmnufocus" aria-haspopup="true"  data-redirect="true">
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>usuarios/logueados.jsp');"  class="m-menu__link ">
                  <i class="menu_top_left flaticon-logout"></i>
                  <span class="m-menu__link-text">
                  Control de logins
                  </span>
                  </a>
               </li>
                              <li class="m-menu__item sbmnufocus" aria-haspopup="true"  data-redirect="true">
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>login/loginlogger.jsp');"  class="m-menu__link ">
                  <i class="menu_top_left flaticon-visible"></i>
                  <span class="m-menu__link-text">
                  Registro de accesos
                  </span>
                  </a>
               </li>
                              <li class="m-menu__item sbmnufocus" aria-haspopup="true"  data-redirect="true">
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>catalogo.jsp');" class="m-menu__link ">
                  <i class="menu_top_left flaticon-list"></i>
                  <span class="m-menu__link-text">
                  Catálogo
                  </span>
                  </a>
               </li>
                -->
                           </ul>
         </div>
      </li>
            <li class="m-menu__item  m-menu__item--active m-menu__item--submenu m-menu__item--rel"  data-menu-submenu-toggle="click" aria-haspopup="true">
         <a  href="#" class="m-menu__link m-menu__toggle">
         <span class="m-menu__link-text">
         Admisión
         </span>
         <i class="m-menu__hor-arrow la la-angle-down"></i>
         <i class="m-menu__ver-arrow la la-angle-right"></i>
         </a>
         <div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left">
            <span class="m-menu__arrow m-menu__arrow--adjust"></span>
            <ul class="m-menu__subnav">


                 
                     <li class="m-menu__item  sbmnufocus" aria-haspopup="true" >
                      <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>busqueda/busqueda_persona.jsp');carga_foco_vacio();" class="m-menu__link "> 
                      <!-- <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','busqueda/');carga_foco_vacio();" class="m-menu__link ">-->
                      <i class="menu_top_left fal fa-search"></i>
                      <span class="m-menu__link-text">
                      Búsqueda de Persona
                      </span>
                      </a>
                   </li>
                      <li class="m-menu__item  sbmnufocus" aria-haspopup="true" >
                      <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','busqueda/busca_citas.jsp');carga_foco_vacio();" class="m-menu__link ">
                      <i class="menu_top_left fal fa-search"></i>
                      <span class="m-menu__link-text">
                      Búsqueda de Citas
                      </span>
                      </a>
                   </li>
                  <!--  <li class="m-menu__item  sbmnufocus" aria-haspopup="true" >
                      <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>citas.jsp');" class="m-menu__link ">
                      <i class="menu_top_left fal fa-calendar"></i>
                      <span class="m-menu__link-text">
                      Consulta de Citas Expediente
                      </span>
                      </a>
                   </li>
                        -->               
                       <li class="m-menu__item  sbmnufocus" aria-haspopup="true" >
                      <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>listaTrabajo.jsp');carga_foco_vacio();" class="m-menu__link ">
                      <i class="menu_top_left fal fa-calendar"></i>
                      <span class="m-menu__link-text">
                      Lista de Trabajo EPI
                      </span>
                      </a>
                   </li>
                              </ul>
         </div>
      </li>


<!-- 
      <li class="m-menu__item  m-menu__item--active m-menu__item--submenu m-menu__item--rel"  data-menu-submenu-toggle="click" aria-haspopup="true">
         <a  href="#" class="m-menu__link m-menu__toggle">
         <span class="m-menu__link-text">
         Configuración
         </span>
         <i class="m-menu__hor-arrow la la-angle-down"></i>
         <i class="m-menu__ver-arrow la la-angle-right"></i>
         </a>
         <div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left">
            <span class="m-menu__arrow m-menu__arrow--adjust"></span>
            <ul class="m-menu__subnav">
     
                <li class="m-menu__item  sbmnufocus" aria-haspopup="true" >
                      <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','medprev/reglas_validacion');" class="m-menu__link ">
                      <i class="menu_top_left fal fa-cog"></i>
                      <span class="m-menu__link-text">
                      Validaciónes EPI
                      </span>
                      </a>
                   </li>
                   


                             </ul>
         </div>
      </li> -->

   </ul>
</div>
            <div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general">
  <div class="m-stack__item m-topbar__nav-wrapper">
    <ul class="m-topbar__nav m-nav m-nav--inline">
                        <li class="m-nav__item m-topbar__user-profile m-topbar__user-profile--img m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light" data-dropdown-toggle="click">
  <a href="#" class="m-nav__link m-dropdown__toggle">
    <span class="m-topbar__userpic">
      <img id="avatar_top1" src="<%=vParametros.getPropEspecifica("RutaNASAvatarURL")%><%=vUsuario.getICveusuario()%>.jpg" alt=""/>
    </span>
  </a>
  <div class="m-dropdown__wrapper">
  <span style="color: #696969 !important;" class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
  <div class="m-dropdown__inner">
    <div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/user_profile_bg.jpg); background-size: cover;">
      <div class="m-card-user m-card-user--skin-dark">
        <div class="m-card-user__pic">
          <img id="avatar_top2" src="<%=vParametros.getPropEspecifica("RutaNASAvatarURL")%><%=vUsuario.getICveusuario()%>.jpg" alt=""/>
        </div>
        <div class="m-card-user__details">
          <span class="m-card-user__name m--font-weight-500">
            <%=vUsuario.getCNombre()+" "+ vUsuario.getCApPaterno() +" "+ vUsuario.getCApMaterno() %>          </span>
          <a href="" class="m-card-user__email m--font-weight-300 m-link">
            <%=vUsuario.getCUsuario()%>          </a>
        </div>
      </div>
    </div>
    <div class="m-dropdown__body">
      <div class="m-dropdown__content">
        <ul class="m-nav m-nav--skin-light">
          <li class="m-nav__section m--hide">
            <span class="m-nav__section-text">
              Section
            </span>
          </li>

                    <li class="m-nav__item">
            <a href="javascript:void(0)" onclick="carga_archivo('contenedor_principal','http://10.33.143.52:7001/epi/usuarios/perfil');" class="m-nav__link">
              <i class="m-nav__link-icon flaticon-profile-1"></i>
              <span class="m-nav__link-title">
                <span class="m-nav__link-wrap">
                  <span class="m-nav__link-text">
                    Mi perfil
                  </span>
                </span>
              </span>
            </a>
          </li>
          
              <!--       <li class="m-nav__item">
            <a href="javascript:void(0)" onclick="carga_archivo('contenedor_principal','http://10.33.143.52:7001/epi/usuarios');" class="m-nav__link">
              <i class="m-nav__link-icon flaticon-users"></i>
              <span class="m-nav__link-text">
                Control de usuarios
              </span>
            </a>
          </li> -->
          
            <!--         <li class="m-nav__item">
            <a href="javascript:void(0)" onclick="carga_archivo('contenedor_principal','http://10.33.143.52:7001/epi/controllers');" class="m-nav__link">
              <i class="m-nav__link-icon flaticon-interface-3"></i>
              <span class="m-nav__link-text">
                Controladores
              </span>
            </a>
          </li> -->
          <!-- 
          <li class="m-nav__separator m-nav__separator--fit"></li>

                    <li class="m-nav__item">
            <a href="http://10.33.143.52:7001/epi/site" target="_blank" class="m-nav__link">
              <i class="m-nav__link-icon flaticon-browser"></i>
              <span class="m-nav__link-text">
                Site
              </span>
            </a>
          </li>  -->
          
          <li class="m-nav__item">
            <!-- <a href="<%=vParametros.getPropEspecifica("URL_APP")%>login/lockSession.jsp" class="m-nav__link"> -->
            <a href="<%=vParametros.getPropEspecifica("URL_APP")%>login.jsp" class="m-nav__link">
              <i class="m-nav__link-icon flaticon-lock"></i>
              <span class="m-nav__link-text">
                Bloquear
              </span>
            </a>
          </li>
          <li class="m-nav__separator m-nav__separator--fit"></li>
          <li class="m-nav__item">
            <span id="comm_js_fn_01" class="btn m-btn--pill    btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">

              Salir
              &nbsp;&nbsp;
              <i class="m-nav__link-icon flaticon-logout"></i>

            </span>
          </li>
                  </ul>
      </div>
    </div>
  </div>
</div>
</li>
          </ul>
  </div>
</div>
         </div>
      </div>
   </div>
</header>
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
  <button class="m-aside-left-close m-aside-left-close--skin-dark" id="m_aside_left_close_btn">
    <i class="la la-close"></i>
  </button>

        <div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-dark ">
      					<!-- BEGIN: Aside Menu -->
      	<div id="m_ver_menu" class="m-aside-menu  m-aside-menu--skin-dark m-aside-menu--submenu-skin-dark " data-menu-vertical="true" data-menu-scrollable="false" data-menu-dropdown-timeout="500">
          <span class="m-menu__nav">
              <span class="m-menu__item">
                  <span class="m-menu__link">
                      <span class="m-menu__link-text">
                            
                      </span>
                  </span>
              </span>
          </span>
				</div>
				<!-- END: Aside Menu -->
			</div>
    <div class="m-grid__item m-grid__item--fluid m-wrapper">
            <div class="m-content" id="contenedor_principal">
        <!-- BEGIN PAGE TITLE-->
<!-- END PAGE TITLE-->
<br>
<div align="center">
  <img width="400px" alt="escudo_nacional" src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>escudo2.png"/>
</div>
	
      </div>
    </div>
</div>
    <!-- begin::Footer -->
    <footer class="m-grid__item		m-footer ">
      <div class="m-container m-container--fluid m-container--full-height m-page__container">
        <div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
          <div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last">
            <span class="m-footer__copyright">
              2017 &copy; M E D P R E V              <a href="#" class="m-link">
                Sistema Integral de Protección y Medicina Preventiva en el Transporte BRANCH::MEDPREV
              </a>
            </span>
          </div>
          <!--
          <div class="m-stack__item m-stack__item--right m-stack__item--middle m-stack__item--first">
            <ul class="m-footer__nav m-nav m-nav--inline m--pull-right">
              <li class="m-nav__item">
                <a href="#" class="m-nav__link">
                  <span class="m-nav__link-text">
                    About
                  </span>
                </a>
              </li>
              <li class="m-nav__item">
                <a href="#"  class="m-nav__link">
                  <span class="m-nav__link-text">
                    Privacy
                  </span>
                </a>
              </li>
              <li class="m-nav__item">
                <a href="#" class="m-nav__link">
                  <span class="m-nav__link-text">
                    T&C
                  </span>
                </a>
              </li>
              <li class="m-nav__item">
                <a href="#" class="m-nav__link">
                  <span class="m-nav__link-text">
                    Purchase
                  </span>
                </a>
              </li>
              <li class="m-nav__item m-nav__item">
                <a href="#" class="m-nav__link" data-toggle="m-tooltip" title="Support Center" data-placement="left">
                  <i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
                </a>
              </li>
            </ul>
          </div>
        -->
        </div>
      </div>
    </footer>
    <!-- end::Footer -->
  </div>

  
  <div class="m-scroll-top m-scroll-top--skin-top" data-toggle="m-scroll-top" data-scroll-offset="500" data-scroll-speed="300">
    <i class="la la-arrow-up"></i>
  </div>

  <ul class="m-nav-sticky" style="margin-top: 30px;display:none;" id="menu_lat_flot">

    <li class="m-nav-sticky__item" data-toggle="" title="Servicios" data-placement="left">
    <div style="text-decoration: none;">
      <div class="m-dropdown m-dropdown--left" data-dropdown-toggle="click" aria-expanded="true"><br>
        <a href="#" class="m-dropdown__toggle">
          <i class="fa fa-medkit" style="font-size:1.3em; color:#C1BFD0;"></i>
        </a>
        <ul class="m-menu__nav" style="list-style:none;">
   <li class="m-nav__item m-topbar__quick-actions m-topbar__quick-actions--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push m-dropdown--mobile-full-width m-dropdown--skin-light"  data-dropdown-toggle="click">
      <div class="m-dropdown__wrapper">
         <div class="m-dropdown__inner">
            <div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/quick_actions_bg.jpg); background-size: cover;">
               <span class="m-dropdown__header-title">
                 Carrusel
               </span>
               <span class="m-dropdown__header-subtitle">
               Servicios
               </span>
            </div>
            <div class="m-dropdown__body m-dropdown__body--paddingless">
               <div class="m-dropdown__content">
                  <div class="m-nav-grid m-nav-grid--skin-light">
                     <div class="m-nav-grid__row">
                                              <!-- <a href="javascript:;" onclick="get_signosVitales();" class="m-nav-grid__item"> -->
                                              <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>persona/signosVitales.jsp');" class="m-nav-grid__item">
                       <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/signos_vitales.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                           Signos Vitales
                         </span>
                       </a>
                                               <!--<a href="javascript:;" onclick="get_laboratorio();" class="m-nav-grid__item">  -->
                                               <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>laboratorio/analisisClinicos.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/laboratorio.svg" width="40" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Laboratorio
                          </span>
                        </a>
                                                <!-- <a href="javascript:;" onclick="carga_archivo('contenedor_movimientos','rayosx/');" class="m-nav-grid__item"> -->
                                                <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>rayosx/rayosx.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/rayosx.svg" width="40" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Rayos x
                          </span>
                        </a>
                                                <!--  <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','cardiologia/gabineteCardiologia/');" class="m-nav-grid__item">-->
                                                <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>cardiologia/cardiologia.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/cardiologia.svg" width="40" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Cardiología
                          </span>
                        </a>
                                             </div>
                     <div class="m-nav-grid__row">
                                              <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','toxicologia/evaluacionToxicologia/');" class="m-nav-grid__item"> -->
                                              <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>toxicologia/evaluacionToxicologica.jsp');" class="m-nav-grid__item">
                         <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/toxicologia.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                           Toxicología
                         </span>
                       </a>
                                              <!-- <a href="javascript:;" onclick="carga_archivo('contenedor_movimientos','Psicologia/pruebasPsicologicas');" class="m-nav-grid__item"> -->
                                              <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>psicologia/pruebas_psicologicas.jsp');" class="m-nav-grid__item">
                         <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/ppsicologicas.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                           Pruebas Psicológicas
                         </span>
                       </a>
                                               <!-- <a href="javascript:;" onclick="get_gabinete_oftalmologia();" class="m-nav-grid__item"> -->
                                              <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','<%=vParametros.getPropEspecifica("URL_APP")%>oftalmologia/nuevoEstudioOftalmo.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/oftalmologia.svg" width="40" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Oftalmología
                          </span>
                        </a>
                                                <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','odontologia/odontograma/');" class="m-nav-grid__item"> -->
                                              <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','odontologia/odontograma.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/odontologia.svg" width="40" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Odontología
                          </span>
                        </a>
                                             </div>
                     <div class="m-nav-grid__row">
                                              <!-- <a href="javascript:;" onclick="get_historia_clinica();" class="m-nav-grid__item"> -->
                                              <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','servicios/historia_clinica.jsp');" class="m-nav-grid__item">
                         <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/historia_clinica.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                           Historia clínica
                         </span>
                       </a>
                                               <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','audiologia/index/');" class="m-nav-grid__item"> -->
                                               <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','audiologia/audiologia.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/audiologia.svg" width="50" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Audiología
                          </span>
                        </a>
                        
                       <!-- Nueva-->
                                               <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','diagnostico/diagnostico.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/report.svg" width="50" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Dignósticos
                          </span>
                        </a>
                       <!-- Nueva--> 
                        
                        
                        <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','dictamen/dictamen.jsp');" class="m-nav-grid__item">
                          <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>servicios/dictamen.svg" width="40" class="svg-inject signal-strong">
                          <span class="m-nav-grid__text">
                            Dictamen
                          </span>
                        </a>
                        
                     </div>
                     <!-- 
                     <div class="m-nav-grid__row">
                       <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','entrevistas/entrevista_autotransporte/');" class="m-nav-grid__item" title="Entrevista Autotransporte">
                         <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/ppsicologicas.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                           Autotransporte
                         </span>
                       </a>
                       <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','entrevistas/entrevista_ferroviario/');" class="m-nav-grid__item" title="Entrevista Ferroviario">
                         <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/ppsicologicas.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                           Ferroviario
                         </span>
                       </a>
                       <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','entrevistas/entrevista_aereo/');" class="m-nav-grid__item" title="Entrevista Aéreo Grupo 1 y 2">
                         <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/ppsicologicas.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                            Aéreo Grupo 1 y 2
                         </span>
                       </a>
                       <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','entrevistas/entrevista_cta/');" class="m-nav-grid__item" title="Entrevista Controlador de Tránsito Aéreo">
                         <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/ppsicologicas.svg" width="40" class="svg-inject signal-strong">
                         <span class="m-nav-grid__text">
                           Controlador de Tránsito Aéreo
                         </span>
                       </a>
                     </div>
                      -->


                  </div>
               </div>
            </div>
         </div>
      </div>
   </li>
</ul>
      </div>
    </div>
  </li>
    <li class="m-nav-sticky__item" data-toggle="" title="Interconsultas" data-placement="left">
    <div style="text-decoration: none;">
      <div class="m-dropdown m-dropdown--left" data-dropdown-toggle="click" aria-expanded="true"><br>
        <a href="#" class="m-dropdown__toggle">
          <i class="fa fa-retweet" style="font-size:1.3em; color:#C1BFD0;"></i>
        </a>
        <ul class="m-menu__nav" style="list-style:none;">
<li class="m-nav__item m-topbar__quick-actions m-topbar__quick-actions--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push m-dropdown--mobile-full-width m-dropdown--skin-light"  data-dropdown-toggle="click">
  <div class="m-dropdown__wrapper">
    <div class="m-dropdown__inner">
      <div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/quick_actions_bg.jpg); background-size: cover;">
        <span class="m-dropdown__header-title">
          Carrusel
        </span>
        <span class="m-dropdown__header-subtitle">
          Interconsultas
        </span>
      </div>
      <div class="m-dropdown__body m-dropdown__body--paddingless">
        <div class="m-dropdown__content">

            <div class="m-nav-grid m-nav-grid--skin-light">
              <div class="m-nav-grid__row">
                              
                  <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','cardiologia/index/');" class="m-nav-grid__item">-->
                    <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','cardiologia_ic/cardiologia_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/cardiologia.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Cardiología
                  </span>
                </a>
     
                              <!--  <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','nutricion/evaluacionNutricion/');" class="m-nav-grid__item">-->
                              <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','nutricion_ic/nutricion_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/nutricion.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Nutrición
                  </span>
                </a>
                             <!--   <a href="javascript:;" onclick="get_evaluacion_oftalmologica();" class="m-nav-grid__item"> -->
                             <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','oftalmologia_ic/oftalmologia_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/oftalmologia.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Oftalmología
                  </span>
                </a>
                               <!--<a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','minterna/evaluacionMinterna/');" class="m-nav-grid__item">-->
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','medicinaInterna_ic/medicinaInterna_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/minterna.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Medicina Interna
                  </span>
                </a>
                              </div>
              <div class="m-nav-grid__row">
                  <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','psicologia/evaluacionPsicologica/');" class="m-nav-grid__item">-->
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','evalPsiquiatria_ic/evalPsiquiatria_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/ppsicologicas.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Evaluacion de Psiquiatría
                  </span>
                </a>
                               <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','otorrino/evaluacion_otorrino/');" class="m-nav-grid__item">-->
                   <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','otorrino_ic/otorrino_Inter.jsp');" class="m-nav-grid__item">            
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/otorrino.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Otorrino
                  </span>
                </a>
                              <!--  <a href="javascript:;" onclick="carga_archivo('contenedor_movimientos','Estudios/');" class="m-nav-grid__item"> -->
                            <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','estudiosAdicionales_ic/estudiosAdicionales_Inter.jsp');" class="m-nav-grid__item">  
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/oestudios.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Estudios Adicionales
                  </span>
                </a>
                
               <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','neurologia/evaluacion_neurologia/');" class="m-nav-grid__item"> -->
                  <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','neurologia_ic/neurologia_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/neuro2.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Neurología
                  </span>
                </a>
                              </div>
              <div class="m-nav-grid__row">
                   <!--<a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','ortopedia/evaluacion_ortopedia/');" class="m-nav-grid__item">-->
                   <!-- <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','ortopedia_ic/ortopedia_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/ortopedia2.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Ortopedia
                  </span>
                </a> -->

                               <!-- <a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','neumologia/evaluacion_neumologia/');" class="m-nav-grid__item">-->
                  <!-- <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','neumologia_ic/.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/neumologia2.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Neumología
                  </span>
                </a> -->
                
                <!--<a href="javascript:;" onclick="carga_servicios_episodio('contenedor_movimientos','dictamen/dictamen_final/');" class="m-nav-grid__item">-->
                 <!-- <a  href="javascript:;" onclick="carga_archivo('contenedor_principal','dictamenFinal_ic/dictamenFinal_Inter.jsp');" class="m-nav-grid__item">
                  <img src="<%=vParametros.getPropEspecifica("RutaImgServerExp")%>interconsultas/oestudios.svg" width="40" class="svg-inject signal-strong">
                  <span class="m-nav-grid__text">
                    Dictamen Final
                  </span>
                </a> -->
                              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </li>
</ul>
      </div>
    </div>
  </li>
  </ul>
  
<!--Inicio::Base Scripts -->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/vendors/base/vendors.bundle.js" type="text/javascript"></script>

<!--Slim Scroll-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>


<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/base/scripts.bundle.js" type="text/javascript"></script>
<!--Fin::Base Scripts -->



<!--Inicio::Page Snippets -->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/app/js/dashboard.js" type="text/javascript"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/custom/header/actions.js" type="text/javascript"></script>
<!--Fin::Page Snippets -->

<!--Calendario-->

<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/vendors/custom/fullcalendar/fullcalendar.bundle.js" type="text/javascript"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/custom/components/calendar/external-events.js" type="text/javascript"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/vendors/custom/jquery-ui/jquery-ui.bundle.js" type="text/javascript"></script>


<!-- JQuery Plugins-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-sessiontimeout/bootstrap-session-timeout.js" type="text/javascript"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-touchspin/bootstrap.touchspin.min.js" type="text/javascript"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/buzz/buzz.min.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>


<!--Dropzone-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/dropzone.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/custom/components/forms/widgets/dropzone.js" type="text/javascript"></script>

<!--Sweet Alert-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/bootstrap-sweetalert/sweetalert.min.js"></script>

<!--Datatables-->
<link rel="stylesheet" type="text/css" href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/datatables/datatables.css"/>
<link rel="stylesheet" type="text/css" href="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/datatables/dataTables.bootstrap4.min.css"/>
<script type="text/javascript" src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/datatables/datatables.min.js"></script>

<!-- graficas -->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/highcharts.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/series-label.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/exporting.js"></script>

<!--Internacionalizacion  para JS (datepicker,dataTable)-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/vendors/base/spanish_ui.js" type="text/javascript"></script>


<!--Portlets-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/custom/components/portlets/tools.js" type="text/javascript"></script>

<!--Select Boostrap-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/demo/default/custom/components/forms/widgets/bootstrap-select.js" type="text/javascript"></script>


<!--fontawesome-pro-5.0.0 load everything!!-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/fontawesome-pro-5.0.0/svg-with-js/js/fontawesome-all.js" type="text/javascript"></script>
<script defer src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/fontawesome-pro-5.0.0/svg-with-js/js/fa-v4-shims.js"></script>

<!--iconos-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/jquery.svginject.js"></script>

<!--Zoom-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/jquery.magnify.js"></script>

<script>var url_app = 'http://10.33.143.52:7001/epi/';</script>
<!--mis Librerias-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/jquery.autocomplete.js"></script>
<script src="https://js.pusher.com/4.1/pusher.min.js"></script>


<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/controllers.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/generales.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/inicio.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/roles.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/usuario.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/cardiologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/exploracion_fisica.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/heredofamiliares.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/laboratorio.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/nutricion.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/rayosx.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/toxicologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/psicologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/patologicos.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/audiologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/odontologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/oftalmologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/otorrino.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/minterna.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/ortopedia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/neurologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/neumologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/interrogatorio.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/movimientos/ginecologia.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/common.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/catalogo.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/left_menu.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/imagemap.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/wow.min.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/persona.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/citas.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/pdf.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/combos.js"></script>
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/medprev.js"></script>

<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/unidades.js"></script>

<!-- <script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/validaciones/biometricos/validaBiometrico.js"></script> -->

<script src="<%=vParametros.getPropEspecifica("RutaFuncs")%>valida_nt.js"></script>

<!--Input Mask-->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js"></script>

<!-- Jquery steps -->
<script src="<%=vParametros.getPropEspecifica("RutaFuncsExt")%>assets/js/jquery.steps.js"></script>

</body>

</html>

<% 

////Valida el tiempo del metodo/////
if(vParametros.getPropEspecifica("Bloquear").equals("false")){
if(vParametros.getPropEspecifica("NumRowsSec").equals("1")){
	boolean enejecucion = false;
  if( new ControlEliminaExp().comprobar() )
    {
        System.out.println("Ejecuto aplicación");
        enejecucion = true;
    }        
    else
    {
    	System.out.println("Aplicacion en ejecución");
    	enejecucion = false;
    }
	}
	System.out.println("produccion");
}else{
	System.out.println("Desarrollo");
}

%>
