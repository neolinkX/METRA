<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__body">
       <div class="form-group m-form__group">
         <div class="alert m-alert m-alert--default" role="alert">
           <b>EXPLORACIÓN FÍSICA</b>
         </div>
       </div>
       <form id="frm_efisica" name="frm_efisica" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
        <div class="row">
           <div class="col-3">
              <ul class="nav nav-pills flex-column m-tabs-line--2x m-tabs-line--primary" role="tablist">
                 <li class="nav-item m-tabs__item">
                    <!-- <a class="nav-link active" data-toggle="tab" href="#exp_fis_1"> 1. Inspección General
                    </a> -->
                    <a class="nav-link active load_tab_content" data-toggle="tab" data-container="interroga_1" data-url_content="servicios/exploracionFisica/expFis_inspeccionGeneral.jsp" href="exp_fis_1">1. Inspección General</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <!-- <a class="nav-link" data-toggle="tab" href="#exp_fis_2">2. Ojos</a> -->
                    <a class="nav-link" data-toggle="tab" data-container="interroga_1" data-url_content="servicios/exploracionFisica/expFis_inspeccionGeneral.jsp" href="exp_fis_2">1. Ojos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link" data-toggle="tab" href="#exp_fis_3">3. Oídos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link" data-toggle="tab" href="#exp_fis_4">4. Nariz</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link" data-toggle="tab" href="#exp_fis_5">5. Cuello</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link" data-toggle="tab" href="#exp_fis_6">6. Torax</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link" data-toggle="tab" href="#exp_fis_7">7. Área Cardiaca</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link" data-toggle="tab" href="#exp_fis_8">8. Abdomen</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link" data-toggle="tab" href="#exp_fis_9">9. Extremidades</a>
                 </li>

              </ul>
           </div>
           <div class="col-9">
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
              

              <div class="tab-content">
                 <div class="tab-pane active" id="exp_fis_1" role="tabpanel">
                   
                 </div>
                 <div class="tab-pane" id="exp_fis_2" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="exp_fis_3" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="exp_fis_4" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="exp_fis_5" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="exp_fis_6" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="exp_fis_7" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="exp_fis_8" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="exp_fis_9" role="tabpanel">
                 </div>
              </div>
            </div>
        </div>
        
        <div class="row">
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
<!-- <script src="assets/js/validaciones/exploracion_fisica/valida_nueva_exploracion.js"></script> -->
    