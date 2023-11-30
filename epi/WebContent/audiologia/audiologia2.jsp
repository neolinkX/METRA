<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__head" style="background:#f7f8fa">
      <div class="m-portlet__head-caption">
         <div class="m-portlet__head-title">
            <span class="m-portlet__head-icon">
            <img src="img/servicios/audiologia.svg" width="25" class="svg-inject signal-strong">
            </span>
            <h3 class="m-portlet__head-text m--font-primary">
               Gabinete Audiología
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
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            Datos del Personal
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
            Oido Derecho
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab">
            Oido Izquierdo
            </a>
         </li>
      </ul>
      <form id="frm_audiologia" name="frm_audiologia">
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
            <div class="tab-content">
               <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
                  <br>
                  <div class="form-group m-form__group row">
                     <div class="col-lg-3">
                        <label for="audio_audiometria_tonal">
                        Audiometría Tonal Vía Aérea
                        </label>
                     </div>
                     <div class="col-lg-5">
                        <select name="audio_audiometria_tonal" class="styleSelect2" data-live-search="true">
                        <?=$catalog->selectCatalog($this->help,'audiometria_tonal',null);?>
                        </select>
                     </div>
                  </div>                
                  <div class="form-group m-form__group row">
                     <div class="col-lg-3">
                        <label for="audio_interpretacion_graficas">
                        Interpretación de las Gráficas
                        </label>
                     </div>
                     <div class="col-lg-6">
                        <textarea name="audio_interpretacion_graficas" id="audio_interpretacion_graficas" maxlength="500" class="form-control m-input m-input--air" id="exampleTextarea" rows="2"></textarea>
                     </div>
                  </div>
                  <div class="form-group m-form__group row m--margin-top-50">
                     <div class="col-lg-12 text-center">
                        <label for="">
                           <h5>Archivos a Digitalizar</h5>
                        </label>
                        <div class="col-lg-12 col-md-12 col-sm-12 text-center">
                           <div id="div_doc_append">
                           </div>
                           <div class="m-dropzone dropzone dz-clickable up_doc_global " id="up_audio_drop" data-routeupfile="usuarios/upload_dropzone/documentos_digitales|acta_nac|/upload_avatar">
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
               <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
                  <div class="m-form__group form-group row text-center">
                     <div class="col-12">
                        <div class="m-radio-inline">
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_derecho" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','via_aerea'); ?>">
                           Vía Aérea Derecho
                           <span></span>
                           </label>
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_derecho" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','via_osea'); ?>">
                           Vía Osea Derecho
                           <span></span>
                           </label>
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_derecho" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','aux_auditivo'); ?>">
                           Auxiliar Auditivo
                           <span></span>
                           </label>
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_derecho" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','sin_respuesta'); ?>">
                           Sin Respuesta
                           <span></span>
                           </label>
                        </div>
                     </div>
                  </div>
                  <table class="table table-bordered table-responsive">
                     <thead>
                        <tr>
                           <th>dB</th>
                           <?php
                              $cabeza=125;
                              for($i=0;$i<=6;$i++){
                              ?>
                           <th><?= $cabeza; ?></th>
                           <?php
                              $cabeza=$cabeza+$cabeza;
                              }
                              ?>
                           <th>12000</th>
                        </tr>
                     </thead>
                     <tbody>
                        <?php
                           $cuerpo=-10;
                           for($i=0;$i<=28;$i++){
                           ?>
                        <tr>
                           <th scope="row"><?= $cuerpo; ?></th>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_125" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_250" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_500" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_1000" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_2000" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_4000" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_8000" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_derecho_12000" data-au_required='true' value="<?= $cuerpo; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                        </tr>
                        <?php
                           $cuerpo=$cuerpo+5;
                           }
                           ?>
                     </tbody>
                  </table>
               </div>
               <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
                  <div class="m-form__group form-group row text-center">
                     <div class="col-12">
                        <div class="m-radio-inline">
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_izquierdo" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','via_aerea'); ?>">
                           Vía Aérea Izquierdo
                           <span></span>
                           </label>
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_izquierdo" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','via_osea'); ?>">
                           Vía Osea Izquierdo
                           <span></span>
                           </label>
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_izquierdo" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','aux_auditivo'); ?>">
                           Auxiliar Auditivo
                           <span></span>
                           </label>
                           <label class="m-radio">
                           <input type="radio" name="cat_audio_via_izquierdo" value="<?= $catalog->getIdByName('framework.cm_catalogo','id_cat','valor','sin_respuesta'); ?>">
                           Sin Respuesta
                           <span></span>
                           </label>
                        </div>
                     </div>
                  </div>
                  <table class="table table-bordered table-responsive">
                     <thead>
                        <tr>
                           <th>dB</th>
                           <?php
                              $cabeza2=125;
                              for($i2=0;$i2<=6;$i2++){
                              ?>
                           <th><?= $cabeza2; ?></th>
                           <?php
                              $cabeza2=$cabeza2+$cabeza2;
                              }
                              ?>
                           <th>12000</th>
                        </tr>
                     </thead>
                     <tbody>
                        <?php
                           $cuerpo2=-10;
                           for($i2=0;$i2<=28;$i2++){
                           ?>
                        <tr>
                           <th scope="row"><?= $cuerpo2; ?></th>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_125" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_250" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_500" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_1000" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_2000" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_4000" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_8000" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                           <td align="center">
                              <label class="m-radio m-radio--primary">
                              <input name="audiologia_izquierdo_12000" data-au_required='true' value="<?= $cuerpo2; ?>" type="radio">
                              <span></span>
                              </label>
                           </td>
                        </tr>
                        <?php
                           $cuerpo2=$cuerpo2+5;
                           }
                           ?>
                     </tbody>
                  </table>
               </div>
               <div class="tab-pane" id="m_tabs_6_4" role="tabpanel">
                  <div id="container2"></div>
               </div>
               <div class="m-portlet__foot m-portlet__foot--fit m--margin-top-50">
                  <div class="m-form__actions">
                     <button type="submit" class="btn btn-primary">
                     Guardar
                     </button>
                     <button type="button" class="btn btn-secondary" onclick="prueba_fea();">
                     Limpiar
                     </button>
                  </div>
               </div>
            </div>
         </div>
      </form>
   </div>
</div>
<script type="text/javascript">
   $(document).ready(function() {
     var num_exp = $("#id_numexp_left").val();
     $("#up_audio_drop").dropzone({
       url: "usuarios/upload_dropzone/examenes|audiologia|"+num_exp+"/upload_avatar",
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
           $( "#div_doc_append" ).append("<input type='hidden' name='archivo_anexos[]' value='examenes/audiologia/"+num_exp+"/"+img[1]+"'>");
         });
       }
      });
   });
</script>
<script src="assets/js/validaciones/audiologia/valida_audiologia.js"></script>
