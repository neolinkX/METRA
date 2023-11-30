<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__body">
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            Exploración Clínica de Mamas
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
            Exploración Física Ginecológica
            </a>
         </li>
      </ul>
      <div class="m-form__content">
        <div class="m-alert m-alert--icon alert alert-danger m--hide" role="alert" id="m_form_1_msg">
          <div class="m-alert__icon">
            <i class="la la-warning"></i>
          </div>
          <div class="m-alert__text">
            Favor de Verificar los datos.
          </div>
          <div class="m-alert__close">
            <button type="button" class="close" data-close="alert" aria-label="Close"></button>
          </div>
        </div>
      </div>
      <form id="frm_ginecologia" name="frm_ginecologia" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">

        <div class="tab-content">
           <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
            <!--  <?php include "exploracion_mamas.php"; ?> -->
            <%@ //include file="/servicios/ginecologia/exploracion_mamas.jsp"%>
           </div>
           <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
              <!--<?php include "exploracion_fisicaGineco.php"; ?> -->
           <%@ //include file="/servicios/ginecologia/exploracion_fisicaGineco.jsp"%>
           </div>
           <div class="m-portlet__foot m-portlet__foot--fit">
   	        <div class="m-form__actions">
   	          <button type="submit" class="btn btn-primary">
   	            Guardar
   	          </button>
   	          <button type="reset" class="btn btn-secondary">
   	            Limpiar
   	          </button>
   	        </div>
   	      </div>

        </div>
      </form>
   </div>
</div>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/ginecologia/valida_ginecologia.js"></script>
<script>
   $("select").select2({widt:'100%'});
   carga_datepicker();
</script>
