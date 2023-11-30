<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
      <div class="m-portlet__head-caption">
        <div class="m-portlet__head-title">
           <span class="m-portlet__head-icon">
           <img src="<?=URL_APP?>img/servicios/psicologia.svg" width="25" class="svg-inject signal-strong">
           </span>
           <h3 class="m-portlet__head-text m--font-primary">
              Evaluación Psiquiatría
           </h3>
        </div>
      </div>
      <div class="m-portlet__head-tools">
         <ul class="m-portlet__nav">
            <li class="m-portlet__nav-item">
               <a href="" data-portlet-tool="toggle" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Collapse">
               <i class="la la-plus"></i>
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
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="true">
            Motivo de la Evaluación
            </a>
         </li>
         <li class="nav-item  m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_2" role="tab" aria-expanded="true">
            Evaluación Psiquiatría
            </a>
         </li>
         <li class="nav-item  m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab" aria-expanded="true">
            Diagnósticos Psiquiatrícos
            </a>
         </li>
         <li class="nav-item  m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#psiq_digital" role="tab" aria-expanded="true">
            Digitalización
            </a>
         </li>
      </ul>

      <form class="m-form m-form--fit m-form--group-seperator-dashed" id="frm_epp" name="frm_epp">
      <div class="tab-content">

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

        <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
          <div class="form-group m-form__group row" align="center">
            <label for="epp_motivo" class="col-lg-5 col-form-label">
              Seleccione el Motivo por el que Solicita la Evaluación:
            </label>
            <div class="col-lg-5" align="left">
              <div class="m-select2 m-select2--air">
                <select class="form-control m-select2 styleSelect2" id="epp_motivo" name="epp_motivo" data-placeholder="Seleccione">
                  <?=$catalog->selectCatalog($this->help,'motivo_epp',null);?>
                </select>
              </div>
            </div>
          </div>
          <div class="form-group m-form__group row" align="center">
            <label for="epp_especifique" class="col-lg-5 col-form-label">
              Especifique:
            </label>
            <div class="col-lg-5">
               <textarea maxlength="500" class="form-control m-input m-input--air" id="epp_especifique" name="epp_especifique" rows="1"></textarea>
            </div>
          </div>
          <div class="form-group m-form__group row" align="center">
             <label for="epp_paplicadas" class="col-lg-5 col-form-label">
               Especifique las Pruebas Aplicadas para Psicología, así como Resultados:
             </label>
             <div class="col-lg-5">
                <textarea maxlength="500" class="form-control m-input m-input--air" id="epp_paplicadas" name="epp_paplicadas" rows="1"></textarea>
             </div>
          </div>
          <div class="form-group m-form__group row" align="center">
            <label for="epp_abeta" class="col-lg-5 col-form-label">
              Se Aplicó Beta III:
            </label>
            <div class="col-lg-5" align="center">
             <span class="m-switch m-switch--sm m-switch--icon">
               <label>
                 <input type="checkbox" name="epp_abeta">
                 <span></span>
               </label>
             </span>
            </div>
          </div>
          <div class="m-form__group form-group row" align="center">
            <label for="epp_rbetaIII" class="col-lg-5 col-form-label">
              Detalle Resultados Beta III:
            </label>
            <div class="col-lg-5">
              <textarea maxlength="500" class="form-control m-input m-input--air" id="epp_rbetaIII" name="epp_rbetaIII" rows="1"></textarea>
            </div>
          </div>
          <div class="m-form__group form-group row" align="center">
            <label for="epp_MMP12" class="col-lg-5 col-form-label">
              Aplicó Prueba: Inventario Multifásico de la Personalidad de Minnesota (MMP1-2)
            </label>
            <div class="col-lg-5" align="center">
              <span class="m-switch m-switch--sm m-switch--icon">
                <label>
                  <input type="checkbox" name="epp_MMP12">
                  <span></span>
                </label>
              </span>
            </div>
          </div>
          <div class="m-form__group form-group row" align="center">
            <label for="epp_minnesota" class="col-lg-5 col-form-label">
             Detalle Resultados: Aplicó Prueba: Inventario Multifásico de la Personalidad de Minnesota:
            </label>
            <div class="col-lg-5">
              <textarea maxlength="500" class="form-control m-input m-input--air" id="epp_minnesota" name="epp_minnesota" rows="2"></textarea>
            </div>
          </div>
        </div>
        <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
          <div class="m-form__group form-group row">
            <label for="epp_motivo_evaluacion" class="col-lg-4 col-form-label">
              Motivo de la Evaluación Psiquiátrica:
            </label>
            <div class="form-group m-form__group col-lg-6">
              <textarea maxlength="500" class="form-control m-input m-input--air" id="epp_motivo_evaluacion" name="epp_motivo_evaluacion" rows="3"></textarea>
            </div>
          </div>
          <div class="form-group m-form__group row">
            <label for="epp_nota_valoracion" class="col-lg-4 col-form-label">
              Nota de Valoración Psiquiátrica:
            </label>
            <div class="col-lg-6">
              <textarea maxlength="500" class="form-control m-input m-input--air" id="epp_nota_valoracion" name="epp_nota_valoracion" rows="3"></textarea>
            </div>
          </div>
         </div>
         <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
            <div class="row" align="center">
               <label for="epp_trastornos" class="col-lg-3 col-form-label">
                 Trastornos Mentales Orgánicos, Incluidos los Trastornos Sintomáticos
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input type="checkbox" name="epp_trastornos">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_CIE10" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F00-F09:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_CIE10" name="epp_CIE10" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',0,9),null);?>
                 </select>
               </div>
            </div>
            <div class="row" align="center">
               <label for="epp_esquizofrenia" class="col-lg-3 col-form-label">
                 Esquizofrenia, Trastornos Esquizotípicos y Trastornos Delirantes
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_esquizofrenia" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F20_F29" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F20-F29:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F20_F29" name="epp_F20_F29" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',20,29),null);?>
                 </select>
               </div>
            </div>
            <div class="row" align="center">
               <label for="epp_thumor" class="col-lg-3 col-form-label">
                 Trastornos del Humor (Afectivos):
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_thumor" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F30_F39" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F30-F39:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F30_F39" name="epp_F30_F39" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',30,39),null);?>
                 </select>
               </div>
            </div>


            <div class="row" align="center">
               <label for="epp_esquizofrenia" class="col-lg-3 col-form-label">
                 Trastornos neuróticos, trastornos relacionados con el estres y trastornos somatomorfos:
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_neurotico" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F40_F49" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F40-F49:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F40_F49" name="epp_F40_F49" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',40,49),null);?>
                 </select>
               </div>
            </div>

            <div class="row" align="center">
               <label for="epp_alter_fis" class="col-lg-3 col-form-label">
                 Síndrome del comportamiento asociados con alteraciones fisiológicas y factores físicos:
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_alter_fis" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F50_F59" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F50-F59:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F50_F59" name="epp_F50_F59" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',50,59),null);?>
                 </select>
               </div>
            </div>

            <div class="row" align="center">
               <label for="epp_esquizofrenia" class="col-lg-3 col-form-label">
                 Trastornos de la personalidad y de comportamiento en adultos:
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_trans_personal" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F60_F69" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F60-F69:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F60_F69" name="epp_F60_F69" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',60,69),null);?>
                 </select>
               </div>
            </div>

            <div class="row" align="center">
               <label for="epp_esquizofrenia" class="col-lg-3 col-form-label">
                 Retraso mental:
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_retraso_mental" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F70_F79" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F70-F79:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F70_F79" name="epp_F70_F79" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',70,79),null);?>
                 </select>
               </div>
            </div>

            <div class="row" align="center">
               <label for="epp_esquizofrenia" class="col-lg-3 col-form-label">
                 Trastornos del desarrollo psicológico:
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_trans_desarrollo" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F80_F89" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F80-F89:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F80_F89" name="epp_F80_F89" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',80,89),null);?>
                 </select>
               </div>
            </div>

            <div class="row" align="center">
               <label for="epp_esquizofrenia" class="col-lg-3 col-form-label">
                 Trastornos emocionales y del comportamiento que aparecen habitualmente en la niñéz o en la adolescencia:
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_trans_emocional" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F90_F98" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F90-F98:
               </label>
               <div class="col-lg-4">
                 <select class="form-control m-select2 styleSelect2" id="epp_F90_F98" name="epp_F90_F98" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',90,98),null);?>
                 </select>
               </div>
            </div>

            <div class="row" align="center">
               <label for="epp_esquizofrenia" class="col-lg-3 col-form-label">
                 Trastorno mental no especificado:
               </label>
               <div class="m-form__group form-group col-lg-3" align="center">
                  <span class="m-switch m-switch--sm m-switch--icon">
                    <label>
                      <input name="epp_tans_mental_no" type="checkbox">
                      <span></span>
                    </label>
                  </span>
               </div>
               <label for="epp_F99" class="col-lg-2 col-form-label">
                 Especifique de Acuerdo a CIE-10 F99:
               </label>
               <div class="col-lg-4">
                 <select class="form-control styleSelect2" id="epp_F99" name="epp_F99" data-placeholder="Seleccione">
                   <?=$this->help->setOption($model_cie->getDataJsonRangoCie('F',99,99),null);?>
                 </select>
               </div>
            </div>

            <div class="m-form__group form-group row" align="center">
              <label for="epp_minnesota" class="col-lg-3 col-form-label text-right">
               Indicaciones:
              </label>
              <div class="col-lg-7">
                <textarea maxlength="500" class="form-control m-input m-input--air" id="epp_indicaciones" name="epp_indicaciones" rows="3" data-set_max_word='500'></textarea>
              </div>
            </div>
         </div>

         <div class="tab-pane" id="psiq_digital">

           <div class="form-group m-form__group row">
              <div class="col-lg-6">
                 <label for="">
                    <h5>Archivo a Digitalizar</h5>
                 </label>
                 <div class="col-lg-12 col-md-12 col-sm-12 text-center">

                    <div id="div_doc_append">
                    </div>
                    <div class="m-dropzone dropzone dz-clickable up_doc_global " id="up_psiq_drop" data-routeupfile="usuarios/upload_dropzone/documentos_digitales|acta_nac|/upload_avatar">
                       <div class="m-dropzone__msg dz-message needsclick">
                          <h3 class="m-dropzone__msg-title">
                             Arrastra aquí el documento correspondiente.
                          </h3>
                          <span class="m-dropzone__msg-desc">
                          Actualmente no hay archivos
                          <strong>
                          no
                          </strong>
                          se han subido archivos.
                          </span>
                       </div>
                    </div>
                 </div>
              </div>
            </div>

         </div>

         <div class="m-portlet__foot m-portlet__foot--fit">
           <div class="m-form__actions">
              <button type="submit" class="btn btn-primary">
              Guardar
              </button>
              <button type="reset" class="btn btn-secondary">
              Cancel
              </button>
           </div>
         </div>
      </div>
    </form>
 </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
  var num_exp = $("#id_numexp_left").val();
  $("#up_psiq_drop").dropzone({
    url: "usuarios/upload_dropzone/examenes|ev_psiquiatria|"+num_exp+"/upload_avatar",
    paramName: "file",
    maxFiles: 5,
    maxFilesize: 5, // MB
    acceptedFiles: "image/*",
    accept: function(file, done) {
        //console.log(file);
        done();
    },
    init: function() {
      this.on("success", function(statics,file) {
        var img = file.split("|");
        $( "#div_doc_append" ).append("<input type='hidden' name='archivo_anexos[]' value='examenes/ev_psiquiatria/"+num_exp+"/"+img[1]+"'>");
      });
    }
   });
});
</script>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/psicologia/valida_evaluacion.js"></script>
