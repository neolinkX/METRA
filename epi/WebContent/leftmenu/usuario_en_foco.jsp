<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<% 
TEntorno    vEntorno      = new TEntorno();
vEntorno.setNumModulo(07);
TParametro  vParametros = new TParametro(vEntorno.getNumModuloStr());


///Actualizar la session
//Session de paciente en turo
session.setAttribute("id", ""+request.getParameter("id"));
session.setAttribute("episodio", ""+request.getParameter("episodio"));
session.setAttribute("inumExamen", ""+request.getParameter("episodio"));


System.out.println("foco id="+request.getParameter("id"));
System.out.println("foco episodio="+request.getParameter("episodio"));



System.out.println("foco sid="+session.getAttribute("id"));
System.out.println("foco sepisodio="+session.getAttribute("episodio"));


//session.setAttribute("id", request.getParameter("id"));
//session.setAttribute("episodio",request.getParameter("episodio"));

boolean hombre = true;
boolean mujer = false;
String avatar = "";

%>
<%@ include file="/busqueda/busca_persona_foco.jsp"%> 

<%
session.setAttribute("nombreExp", ""+NombreCompleto);
session.setAttribute("rfcExp", ""+CRFC);
session.setAttribute("sexoExp", ""+CSEXO);
session.setAttribute("edadExp", ""+CEDAD);
%>
    
<style>
#m_ver_menu>ul>li>a svg{
  font-size:1.4em;
  color:#ffffff4f;
}
#m_ver_menu>ul>li>a span{
  padding-left: 35px;
}
</style>
<ul class="m-menu__foco  m-menu__foco--dropdown-submenu-arrow">
  <li class="m-menu__item " aria-haspopup="true">
    <a href="javascript:void(0)" class="m-menu__link ">
      <div class="m-widget3__user-img">
      <%if(hombre){
    	avatar=vParametros.getPropEspecifica("URL_APP")+"svg/images/hombre_avatar.svg";  
      }else{
    	  if(mujer){
    		  avatar=vParametros.getPropEspecifica("URL_APP")+"svg/images/mujer_avatar.svg";
    	  }else{
    		  avatar=vParametros.getPropEspecifica("URL_APP")+"svg/images/hombre_avatar.svg";  
    	  }
      }
    	  %>
        <img class="m-widget3__img" src="<%=avatar %>" alt="" width="80px">

      </div>
      <datalist class="m-menu__link-title">
        <datalist class="m-menu__link-wrap">
          <datalist class="m-menu__link-text">
            <!-- <?=ucwords($nombre_completo);?> -->
            <%=NombreCompleto %>
          </datalist>
        </datalist>
      </datalist>
    </a>
    <span class="m-menu__link">
        <span class="m-menu__link-text">
          <input type="hidden" id="id_persona_left" value="<%=request.getParameter("id")%>">
          <input type="hidden" id="id_episodio_left" value="<%=request.getParameter("episodio")%>">
          <input type="hidden" id="id_numexp_left" value="<?=$num_expediente?>">
          <h4 class="m-widget5__title text-center" style="font-size:1.3em;">
            <!-- <?=$num_expediente;?> -->
            <%=request.getParameter("id") %>
          </h4>
          <span class="m-widget5__desc">
            <!-- RFC: <?=strtoupper($datos_p->rfc);?> -->
            RFC: <%=CRFC %>
          </span>
          <div class="m-widget5__info">

            <span class="m-widget5__info-label">
              Sexo: <%=CSEXO %>
            </span>
            <span class="m-widget5__info-date m--font-info">
              <!-- <?=$sexo_persona;?> -->
              
            </span>
            <span class="m-widget5__info-label">
              Edad: <%=CEDAD %>
            </span>
            <span class="m-widget5__info-date m--font-info">
              <!-- <?=$edad_persona;?> -->
            </span><br>

          </div>
        </span>
    </span>
  </li>

  <li class="m-menu__section">
		<h4 class="m-menu__section-text">
			Mis ...
		</h4>
		<i class="m-menu__section-icon flaticon-more-v3"></i>
	</li>

<!-- 
  <?php
  if($this->help->tiene_permiso('Persona|perfilPersona')){
  ?>
   -->
  <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" data-menu-submenu-toggle="hover">
    <a href="javascript:;" class="m-menu__link m-menu__toggle" onclick="carga_archivo('contenedor_principal','persona/perfil_persona.jsp?idPersona=<%=request.getParameter("id")%>');">
    
      <i class="fa fa-address-book"></i>
      <span class="m-menu__link-text">
      Ficha de persona
      </span>
      <i class="m-menu__ver-arrow la la-angle-right"></i>
    </a>
  </li>
 <!-- 
  <?php
  }
  if($this->help->tiene_permiso('Somatometria|resultadosPanelLeftSignos')){
  ?>
   -->
  <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" data-menu-submenu-toggle="hover">
      <a href="javascript:void(0)" class="m-menu__link m-menu__toggle" onclick="carga_archivo_modal('contenedor_movimientos','somatometria/resultadosPanelLeftSignos/<%=request.getParameter("episodio")%>/<%=request.getParameter("id")%>');">

      <i class="fa fa-thermometer-full"></i>
      <span class="m-menu__link-text">
      Mis Signos Vitales
      </span>
      <i class="m-menu__ver-arrow la la-angle-right"></i>
    </a>
  </li>
  <!-- <?php
  }
  if($this->help->tiene_permiso('XXXXXX|YYYYYY')){
  ?> -->
  <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" data-menu-submenu-toggle="hover">
    <!-- <a href="javascript:;" class="m-menu__link m-menu__toggle"> --> 
    <a  href="javascript:;" class="m-menu__link m-menu__toggle"  onclick="carga_archivo('contenedor_principal','biometricos/biometria.jsp?idPersona=<%=request.getParameter("id")%>');">
      <i class="fa fa-thumbs-up"></i>
      <span class="m-menu__link-text">
      Mis huellas
      </span>
      <i class="m-menu__ver-arrow la la-angle-right"></i>
    </a>
  </li>
 <!--  <?php
  }
  if(
    ($this->help->tiene_permiso('Pdf|constancia')) OR
    ($this->help->tiene_permiso('Pdf|acta_biometricos')) OR
    ($this->help->tiene_permiso('Pdf|acta_omision')) OR
    ($this->help->tiene_permiso('Pdf|escrito_solicitud')) OR
    ($this->help->tiene_permiso('Pdf|solicitud_ayuda'))
  ){
  ?> -->
  <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true"  data-menu-submenu-toggle="hover">
    <a  href="#" class="m-menu__link m-menu__toggle">
      <i class="fal fa-file-pdf"></i>
      <span class="m-menu__link-text">
        Documentos
      </span>
      <i class="m-menu__ver-arrow la la-angle-right"></i>
    </a>
    <div class="m-menu__submenu">
      <span class="m-menu__arrow"></span>
      <ul class="m-menu__subnav">
        <!-- <?php
        if($this->help->tiene_permiso('Pdf|constancia')){
        ?> -->
        <li class="m-menu__item " aria-haspopup="true" >
          <a  href="javascript:;" id="pdf_js_fn_05" class="m-menu__link ">
            <i class="m-menu__link-bullet m-menu__link-bullet--dot">
              <span></span>
            </i>
            <span class="m-menu__link-text">
              Constancia
            </span>
          </a>
        </li>
        <!-- <?php
        }
        if($this->help->tiene_permiso('Pdf|acta_biometricos')){
        ?> -->
        <li class="m-menu__item " aria-haspopup="true" >
          <a  href="javascript:;" id="pdf_js_fn_06" class="m-menu__link ">
            <i class="m-menu__link-bullet m-menu__link-bullet--dot">
              <span></span>
            </i>
            <span class="m-menu__link-text">
              Acta biometricos
            </span>
          </a>
        </li>
        <!-- <?php
        }
        if($this->help->tiene_permiso('Pdf|acta_omision')){
        ?> -->
        <li class="m-menu__item " aria-haspopup="true" >
          <a  href="javascript:;" id="pdf_js_fn_07" class="m-menu__link ">
            <i class="m-menu__link-bullet m-menu__link-bullet--dot">
              <span></span>
            </i>
            <span class="m-menu__link-text">
              Acta omisión
            </span>
          </a>
        </li>
        <!-- <?php
        }
        if($this->help->tiene_permiso('Pdf|escrito_solicitud')){
        ?> -->
        <li class="m-menu__item " aria-haspopup="true" >
          <a  href="javascript:;" id="pdf_js_fn_08" class="m-menu__link ">
            <i class="m-menu__link-bullet m-menu__link-bullet--dot">
              <span></span>
            </i>
            <span class="m-menu__link-text">
              Escrito salud
            </span>
          </a>
        </li>
        <!-- <?php
        }
        if($this->help->tiene_permiso('Pdf|solicitud_ayuda')){
        ?> -->
        <li class="m-menu__item " aria-haspopup="true" >
          <a  href="javascript:;" id="pdf_js_fn_09" class="m-menu__link ">
            <i class="m-menu__link-bullet m-menu__link-bullet--dot">
              <span></span>
            </i>
            <span class="m-menu__link-text">
              Solicitud ayuda
            </span>
          </a>
        </li>
       <!--  <?php
        }
        ?> -->
      </ul>
    </div>
  </li>
  <!-- <?php
  }
  if($this->help->tiene_permiso('Persona|digitalizarDocumentos')){
  ?> -->
  <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" data-menu-submenu-toggle="hover">
    <!-- <a href="javascript:;" class="m-menu__link m-menu__toggle" id="inicio_digitalizacion" onclick="carga_archivo('contenedor_movimientos','persona/digitalizarDocumentos/<%=request.getParameter("id")%>/<?=$num_expediente?>');"> -->
    <!-- <a href="javascript:;" class="m-menu__link m-menu__toggle" onclick="window.open("http://app.sct.gob.mx/elic/CDPagNva.jsp?cPagina=CD/frmmi.js&cPagNva=pg13032100000.js");"> -->
    <!-- <a href="javascript:;" class="m-menu__link m-menu__toggle" onclick="carga_archivo_modalDigitaliza('contenedor_movimientos','persona/perfil_persona.jsp?idPersona=<%=request.getParameter("id")%>');"> -->
     <a  href="javascript:;" class="m-menu__link m-menu__toggle"  id="digitaliza">
      <i class="fa fa-cloud-upload"></i>
      <span class="m-menu__link-text">
      Digitalización
      </span>
      <i class="m-menu__ver-arrow la la-angle-right"></i>
    </a>
  </li>
 <!--  <?php
}
  if($this->help->tiene_permiso('Persona|episodiosPersona')){
  ?> -->
  <li class="m-menu__item  m-menu__item--submenu" aria-haspopup="true" data-menu-submenu-toggle="hover">
    <!-- <a href="javascript:;" class="m-menu__link m-menu__toggle" onclick="carga_archivo('contenedor_movimientos','persona/episodiosPersona/<%=request.getParameter("episodio")%>/<%=request.getParameter("id")%>');">
        <a href="javascript:;" class="m-menu__link m-menu__toggle" onclick="carga_archivo_modalDigitaliza('contenedor_movimientos','persona/episodiosPersona.jsp?idPersona=<%=request.getParameter("id")%>');">   -->
    <!-- <a href="javascript:void(0)" class="m-menu__link m-menu__toggle" onclick="carga_archivo_modalResultados('contenedor_movimientos','resultados/resultadoServicios.jsp?idPersona=<%=request.getParameter("id")%>');"> -->
    <a  href="javascript:;" class="m-menu__link m-menu__toggle"  onclick="carga_archivo('contenedor_principal','resultados/resultadoServicios.jsp?idPersona=<%=request.getParameter("id")%>');">  
       
      <i class="fa 	fa-id-card-o"></i>
      <span class="m-menu__link-text">
      Mis Episodios 
      </span>
      <i class="m-menu__ver-arrow la la-angle-right"></i>
    </a>
  </li>
  <!-- <?php
  }
  ?> -->
</ul>
<!--Inicio::Base Scripts -->
<script>
var menu = $('.m-menu__foco').mMenu({
  submenu: {
    desktop: {
      default: 'accordion',
      state: {
        body: 'm-aside-left--minimize',
        mode: 'dropdown'
      }
    },
    tablet: 'accordion',
    mobile: 'accordion'
  },
  accordion: {
    autoScroll: true,
    expandAll: false
  }
});
</script>
