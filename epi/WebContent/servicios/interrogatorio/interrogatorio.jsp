<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__body">
       <div class="form-group m-form__group">
         <div class="alert m-alert m-alert--default" role="alert">
           <b>INTERROGATORIO</b>
         </div>
       </div>
       <form id="frm_interrogatorio" name="frm_interrogatorio" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
        <div class="row">
           <div class="col-3">
              <ul class="nav nav-pills flex-column m-tabs-line--2x m-tabs-line--primary" role="tablist">
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link active load_tab_content" data-toggle="tab" data-container="interroga_1" data-url_content="servicios/interrogatorio/sintomas.jsp" id="I1" href="#interroga_1">1. Síntomas generales</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content"  data-toggle="tab" data-container="interroga_2" data-url_content="servicios/interrogatorio/ojos.jsp" id="I2"  href="#interroga_2">2. Ojos y visión</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_3" data-url_content="servicios/interrogatorio/oidos.jsp" href="#interroga_3">3. Oídos y audición</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_4" data-url_content="servicios/interrogatorio/nariz.jsp" href="#interroga_4">4. Nariz</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_5" data-url_content="servicios/interrogatorio/boca.jsp" href="#interroga_5">5. Boca y gusto</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_6" data-url_content="servicios/interrogatorio/cardio.jsp" href="#interroga_6">6. Cardiovascular</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_7" data-url_content="servicios/interrogatorio/respiratorio.jsp" href="#interroga_7">7. Respiratorio</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_8" data-url_content="servicios/interrogatorio/endocrino.jsp" href="#interroga_8">8. Endocrino</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_9" data-url_content="servicios/interrogatorio/urinario.jsp" href="#interroga_9">9. Urinario</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_10" data-url_content="servicios/interrogatorio/vasculares.jsp" href="#interroga_10">10. Vasculares perifericos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_11" data-url_content="servicios/interrogatorio/gastro.jsp" href="#interroga_11">11. Gastrointestinales</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_12" data-url_content="servicios/interrogatorio/hema.jsp" href="#interroga_12">12. Hematopoyético</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_13" data-url_content="servicios/interrogatorio/musculo.jsp" href="#interroga_13">13. Musculoesquelético</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_14" data-url_content="servicios/interrogatorio/neuro.jsp" href="#interroga_14">14. Neurológicos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_15" data-url_content="servicios/interrogatorio/escala.jsp" href="#interroga_15">15. Escala de somnolencia de EPWORTH</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_16" data-url_content="servicios/interrogatorio/saos.jsp" href="#interroga_16">16. SAOS</a>
                 </li>

              </ul>
           </div>
           <div class="col-9">
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

              <div class="tab-content">
                  <div class="tab-pane active" id="interroga_1" role="tabpanel">
	    			<%@ include file="/servicios/interrogatorio/sintomas.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_2" role="tabpanel">
                 </div>
                 <div class="tab-pane" id="interroga_3" role="tabpanel">
                    
                 </div> 
                 <div class="tab-pane" id="interroga_4" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_5" role="tabpanel">
 					
                 </div>
                 <div class="tab-pane" id="interroga_6" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_7" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_8" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_9" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_10" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_11" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_12" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_13" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_14" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_15" role="tabpanel">
					
                 </div>
                 <div class="tab-pane" id="interroga_16" role="tabpanel">

                 </div>

              </div>

           </div>
        </div>
       
        </form>
     </div>

</div>
<!-- <script src="<?=URL_PUBLIC?>assets/js/validaciones/interrogatorio/valida_interrogatorio.js"></script> -->
