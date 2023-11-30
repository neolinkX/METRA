<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.micper.ingsw.TParametro"%>
<%@ page import="com.micper.ingsw.TEntorno"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamAplica"%>
<%@ page import="gob.sct.medprev.dao.TDEXPExamCat"%>    
<%
TDEXPExamCat cat = new TDEXPExamCat();
TEntorno    vEntorno4      = new TEntorno();
vEntorno4.setNumModulo(07);
TParametro  vParametros4 = new TParametro(vEntorno4.getNumModuloStr());

String inumExamen = cat.SentenciaSQL("Select max(inumexamen) from expexamcat where icveexpediente = "+session.getAttribute("id"));
session.setAttribute("inumExamen", inumExamen);

%>
<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm"  data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
      <div class="m-portlet__head-caption">
         <div class="m-portlet__head-title">
            <span class="m-portlet__head-icon">
            <i class="flaticon-notes"></i>
            </span>
            <h3 class="m-portlet__head-text m--font-primary">
               Mi Episodio Actual
            </h3>
         </div>
      </div>
      <div class="m-portlet__head-tools">
         <ul class="m-portlet__nav">
            <li class="m-portlet__nav-item"  id="btn_regresa_movs" style="display:none;">
               <a href="#" data-portlet-tool="" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Regresar" onclick="oculta_div('movs_container_episodio');">
               <i class="la la-angle-double-left"></i>
               </a>
            </li>
            <li class="m-portlet__nav-item">
               <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
               <i class="la la-expand"></i>
               </a>
            </li>
         </ul>
      </div>
   </div>
   <div class="m-portlet__body">
      <!--
         <div class="m-alert m-alert--outline alert alert-success alert-dismissible fade show" role="alert">
         <button type="button" class="close" data-dismiss="" aria-label="Close"></button>
         <strong>
         Atento aviso!
         </strong>
         La persona cuenta con un Episodio Abierto en este momento.
         </div>

         -->
      <div id="movs_container_episodio">
         <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
            <li class="nav-item m-tabs__item">
               <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
               <i class="fa fa-check-circle" aria-hidden="true"></i>
               Servicios
               </a>
            </li>
         </ul>
         <div class="m-nav-grid m-nav-grid--skin-light">
            <div class="m-nav-grid__row">
               <!-- <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','somatometria/resultadosSomatometria/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item"> -->
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','resultados/somatometria/res_somatometria.jsp');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/signos_vitales.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Signos Vitales
                   </span>
               </a>

               <!-- <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','laboratorio/resultadosLab/<?=$episodio?>');" class="m-nav-grid__item"> -->
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','resultados/laboratorio/panel_container.jsp');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/laboratorio.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Laboratorio
                   </span>
               </a>
               <!-- <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','rayosx/resultadosRayosX/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item"> -->
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','resultados/rayos_x/res_rayosx.jsp');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/rayosx.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Rayos x
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','cardiologia/resultadosGabineteCardiologia/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/cardiologia.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Cardiolog&iacute;a
                   </span>
               </a>
            </div>
            <div class="m-nav-grid__row">
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','toxicologia/resultadosToxicologia/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/toxicologia.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Toxicolog&iacute;a
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','psicologia/resultadosPruebasPsicologia/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="img/interconsultas/ppsicologicas.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Pruebas Psicol&oacute;gicas
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','oftalmologia/resultadosGabineteOftalmo/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/oftalmologia.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Oftalmolog&iacute;a
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','odontologia/resultadosGabineteOdonto/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/odontologia.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Odontolog&iacute;a
                   </span>
               </a>
            </div>
            <div class="m-nav-grid__row">
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','historiaclinica/resultados/<?=$id_persona.'/'.$episodio?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/historia_clinica.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Historia cl&iacute;nica
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','audiologia/resultadosAudiologia/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/audiologia.svg" width="50" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Audiolog&iacute;a
                   </span>
               </a>
            </div>
         </div>
         <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary m--margin-top-20" role="tablist">
            <li class="nav-item m-tabs__item">
               <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
               <i class="fa fa-times-circle" aria-hidden="true"></i>
              Interconsultas
               </a>
            </li>
         </ul>
         <div class="m-nav-grid m-nav-grid--skin-light">
            <div class="m-nav-grid__row">
               <a href="javascript:;" class="m-nav-grid__item" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','cardiologia/resultadosInterCardiologia/<?=$episodio.'/'.$id_persona?>');">
                   <img src="img/interconsultas/cardiologia.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Cardiolog&iacute;a
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','nutricion/resultadosNutricion/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="img/interconsultas/nutricion.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Nutrici&oacute;n
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','minterna/resultadosMedInterna/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="img/interconsultas/minterna.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Medicina Interna
                   </span>
               </a>

               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','oftalmologia/resultadosEvaOftalmo/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="img/interconsultas/oftalmologia.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Oftalmolog&iacute;a
                   </span>
               </a>
            </div>
            <div class="m-nav-grid__row">

               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','psiquiatria/resultadosPsiaquiatria/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/servicios/psicologia.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Evaluacion de Psiquiatr&iacute;a
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','otorrino/resultadosOtorrino/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                   <img src="<%=vParametros4.getPropEspecifica("URL_APP")%>img/interconsultas/otorrino.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Otorrino
                   </span>
               </a>
               <a href="javascript:;"  class="m-nav-grid__item">
                   <img src="img/interconsultas/oestudios.svg" width="40" class="svg-inject signal-strong">
                   <span class="m-nav-grid__text">
                   Estudios Adicionales
                   </span>
               </a>
               <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','neurologia/resultadosNeurologia/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                 <img src="img/interconsultas/neuro2.svg" width="40" class="svg-inject signal-strong">
                 <span class="m-nav-grid__text">
                   Neurolog&iacute;a
                 </span>
               </a>
            </div>
            <div class="m-nav-grid__row">
              <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','ortopedia/resultadosOrtopedia/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                <img src="img/interconsultas/ortopedia2.svg" width="40" class="svg-inject signal-strong">
                <span class="m-nav-grid__text">
                  Ortopedia
                </span>
              </a>
              <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','neumologia/resultadosNeumologia/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                <img src="img/interconsultas/neumologia2.svg" width="40" class="svg-inject signal-strong">
                <span class="m-nav-grid__text">
                  Neumolog&iacute;a
                </span>
              </a>
              <a href="javascript:;" onclick="oculta_div('movs_container_episodio');carga_archivo('container_episodio','dictamen/resultadosDictamen/<?=$episodio.'/'.$id_persona?>');" class="m-nav-grid__item">
                <img src="img/interconsultas/oestudios.svg" width="40" class="svg-inject signal-strong">
                <span class="m-nav-grid__text">
                  Dictamen Final
                </span>
              </a>
            </div>
         </div>
      </div>
      <div id="container_episodio"></div>
   </div>
</div>
<script>
   function oculta_div(div){
     $('#'+div).toggle();
     $('#btn_regresa_movs').toggle();
     $("#container_episodio").html('');
   }
</script>
