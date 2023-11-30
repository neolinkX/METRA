<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__body">
       <div class="form-group m-form__group">
         <div class="alert m-alert m-alert--default" role="alert">
           <b>EXPLORACIÓN FÍSICA</b>
         </div>
       </div>
       <form id="frm_interrogatorio" name="frm_interrogatorio" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
        <div class="row">
           <div class="col-3">
              <ul class="nav nav-pills flex-column m-tabs-line--2x m-tabs-line--primary" role="tablist">
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link active load_tab_content" data-toggle="tab" data-container="interroga_1" data-url_content="servicios/exploracionFisica/inspeccionGeneral.jsp" id="I1" href="#interroga_1">1. Inspección General</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content"  data-toggle="tab" data-container="interroga_2" data-url_content="servicios/exploracionFisica/ojos.jsp" id="I2"  href="#interroga_2">2. Ojos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_3" data-url_content="servicios/exploracionFisica/oidos.jsp" href="#interroga_3">3. Oídos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_4" data-url_content="servicios/exploracionFisica/nariz.jsp" href="#interroga_4">4. Nariz</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_5" data-url_content="servicios/exploracionFisica/cuello.jsp" href="#interroga_5">5. Cuello</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_6" data-url_content="servicios/exploracionFisica/torax.jsp" href="#interroga_6">6. Torax</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_7" data-url_content="servicios/exploracionFisica/areaCardiaca.jsp" href="#interroga_7">7. &Aacute;rea Cardiaca</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_8" data-url_content="servicios/exploracionFisica/abdomen.jsp" href="#interroga_8">8. Abdomen</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_9" data-url_content="servicios/exploracionFisica/extremidades.jsp" href="#interroga_9">9. Extremidades</a>
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
	    			<%@ include file="/servicios/exploracionFisica/inspeccionGeneral.jsp"%>
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