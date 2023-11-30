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
                    <a class="nav-link active load_tab_content" data-toggle="tab" data-container="interroga_1" data-url_content="historiaclinica/interrogatorioSintomas" href="#interroga_1">1. S�ntomas generales</a>
                      
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content"  data-toggle="tab" data-container="interroga_2" data-url_content="historiaclinica/interrogatorioOjos" href="#interroga_2">2. Ojos y visi�n</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_3" data-url_content="historiaclinica/interrogatorioOidosAudicion" href="#interroga_3">3. O�dos y audici�n</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_4" data-url_content="historiaclinica/interrogatorioNariz" href="#interroga_4">4. Nariz</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_5" data-url_content="historiaclinica/interrogatorioBoca" href="#interroga_5">5. Boca y gusto</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_6" data-url_content="historiaclinica/interrogatorioCardio" href="#interroga_6">6. Cardiovascular</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_7" data-url_content="historiaclinica/interrogatorioRespiratorio" href="#interroga_7">7. Respiratorio</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_8" data-url_content="historiaclinica/interrogatorioEndocrino" href="#interroga_8">8. Endocrino</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_9" data-url_content="historiaclinica/interrogatorioUrinario" href="#interroga_9">9. Urinario</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_10" data-url_content="historiaclinica/interrogatorioVasculares" href="#interroga_10">10. Vasculares perifericos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_11" data-url_content="historiaclinica/interrogatorioGastro" href="#interroga_11">11. Gastrointestinales</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_12" data-url_content="historiaclinica/interrogatorioHema" href="#interroga_12">12. Hematopoy�tico</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_13" data-url_content="historiaclinica/interrogatorioMusculo" href="#interroga_13">13. Musculoesquel�tico</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_14" data-url_content="historiaclinica/interrogatorioNeuro" href="#interroga_14">14. Neurol�gicos</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_15" data-url_content="historiaclinica/interrogatorioEscala" href="#interroga_15">15. Escala de somnolencia de EPWORTH</a>
                 </li>
                 <li class="nav-item m-tabs__item">
                    <a class="nav-link load_tab_content" data-toggle="tab" data-container="interroga_16" data-url_content="historiaclinica/interrogatorioSaos" href="#interroga_16">16. SAOS</a>
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
                    <%@ //include file="/servicios/interrogatorio/sintomas.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_2" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/ojos.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_3" role="tabpanel">
                    <%@ //include file="/servicios/interrogatorio/oidos.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_4" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/nariz.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_5" role="tabpanel">
 					<%@ //include file="/servicios/interrogatorio/boca.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_6" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/cardio.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_7" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/respiratorio.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_8" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/endocrino.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_9" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/urinario.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_10" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/vasculares.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_11" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/gastro.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_12" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/hema.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_13" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/musculo.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_14" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/neuro.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_15" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/escala.jsp"%>
                 </div>
                 <div class="tab-pane" id="interroga_16" role="tabpanel">
					<%@ //include file="/servicios/interrogatorio/saos.jsp"%>
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
<script src="<?=URL_PUBLIC?>assets/js/validaciones/interrogatorio/valida_interrogatorio.js"></script>
