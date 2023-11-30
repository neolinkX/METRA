<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <div class="row">
   <div class="col-md-4 text-right">
      <label for="gaoftal_cercana_ojo_derecho">
      <?=$q_oftalmo['oftalmo_agudeza1']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza1']['form_help'];?>
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_cercana_ojo_derecho" name="gaoftal_cercana_ojo_derecho" data-number_dec="0|1.9">
   </div>
   <div class="col-md-4 text-right">
      <label for="gaoftal_lejana_ojo_derecho">
      <?=$q_oftalmo['oftalmo_agudeza5']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza5']['form_help'];?>
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_lejana_ojo_derecho" name="gaoftal_lejana_ojo_derecho" data-number_dec="0|1.9">
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-4 text-right">
      <label for="gaoftal_cercana_ojo_izquierdo">
      <?=$q_oftalmo['oftalmo_agudeza2']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza2']['form_help'];?>
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_cercana_ojo_izquierdo" name="gaoftal_cercana_ojo_izquierdo" data-number_dec="0|1.9">
   </div>
   <div class="col-md-4 text-right">
      <label for="gaoftal_lejana_ojo_izquierdo">
      <?=$q_oftalmo['oftalmo_agudeza6']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza6']['form_help'];?>
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_lejana_ojo_izquierdo" name="gaoftal_lejana_ojo_izquierdo" data-number_dec="0|1.9">
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-4 text-right">
      <label for="gaoftal_intermedia_ojo_derecho">
      <?=$q_oftalmo['oftalmo_agudeza3']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza3']['form_help'];?>
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_intermedia_ojo_derecho" name="gaoftal_intermedia_ojo_derecho" data-number_dec="0|1.9">
   </div>
   <div class="col-md-4 text-right">
      <label for="gaoftal_lentes_corregir">
      <?=$q_oftalmo['oftalmo_agudeza7']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza7']['form_help'];?>
      </span>
   </div>
   <div class="col-lg-2 text-center">
      <span class="m-switch m-switch--sm m-switch--icon">
      <label>
      <input type="checkbox" name="gaoftal_lentes_corregir" id="gaoftal_lentes_corregir">
      <span></span>
      </label>
      </span>
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-4 text-right">
      <label for="gaoftal_intermedia_ojo_izquierdo">
      <?=$q_oftalmo['oftalmo_agudeza4']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza4']['form_help'];?>
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_intermedia_ojo_izquierdo" name="gaoftal_intermedia_ojo_izquierdo" data-number_dec="0|1.9">
   </div>
   <div class="col-md-4 text-right">
      <label for="gaoftal_estereopsis">
      <?=$q_oftalmo['oftalmo_agudeza8']['label'];?>
      </label>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza8']['form_help'];?>
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_estereopsis" name="gaoftal_estereopsis" data-number>
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-6">
      <h5 class="m--font-primary text-center">Valoración de Campo Visual</h5>
      <div class="row m--margin-top-20">
         <div class="col-md-6 form-group">
            <label for="gaoftal_campo_visual">
            <?=$q_oftalmo['oftalmo_agudeza9']['label'];?>
            </label>
            <input type="text" class="form-control m-input" id="gaoftal_campo_visual" name="gaoftal_campo_visual" data-number_dec="0|100">
            <span class="m-form__help">
            <?=$q_oftalmo['oftalmo_agudeza9']['form_help'];?>
            </span>
         </div>
         <div class="col-md-6 form-group">
            <label for="gaoftal_movimientos_oculares">
            <?=$q_oftalmo['oftalmo_agudeza10']['label'];?>
            </label>
            <input type="text" class="form-control m-input" id="gaoftal_movimientos_oculares" name="gaoftal_movimientos_oculares" data-number_dec="0|100">
            <span class="m-form__help">
            <?=$q_oftalmo['oftalmo_agudeza10']['form_help'];?>
            </span>
         </div>
      </div>
   </div>
   <div class="col-md-6">
      <h5 class="m--font-primary text-center">Reflejos Pupilares</h5>
      <div class="row m--margin-top-20">
         <div class="col-lg-6 form-group">
            <label for="gaoftal_rp_ojo_derecho">
            <?=$q_oftalmo['oftalmo_agudeza11']['label'];?>
            </label>
            <select id="gaoftal_rp_ojo_derecho" name="gaoftal_rp_ojo_derecho" class="form-control m-bootstrap-select m_selectpicker">
               <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
            </select>
         </div>
         <div class="col-lg-6 form-group">
            <label for="gaoftal_rp_ojo_izquierdo">
            <?=$q_oftalmo['oftalmo_agudeza12']['label'];?>
            </label>
            <select id="gaoftal_rp_ojo_izquierdo" name="gaoftal_rp_ojo_izquierdo" class="form-control m-bootstrap-select m_selectpicker">
               <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
            </select>
         </div>
      </div>
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-6">
      <h5 class="m--font-primary text-center">Enfermedades del Ojo y sus Anexos</h5>
      <div class="row m--margin-top-20">
         <div class="col-md-12 form-group m-form__group">
            <label for="gaoftal_especifique_patologia">
            <?=$q_oftalmo['oftalmo_agudeza13']['label'];?>
            </label>
            <input type="text" maxlength="300" class="form-control m-input" id="gaoftal_especifique_patologia" name="gaoftal_especifique_patologia">
            <span class="m-form__help">
            <?=$q_oftalmo['oftalmo_agudeza13']['form_help'];?>
            </span>
         </div>
      </div>
   </div>
   <div class="col-md-6">
     <label for="">
        <h5 class="m--font-primary">Archivos a Digitalizar</h5>
     </label>
     <div class="col-lg-12 col-md-12 col-sm-12 text-center">
        <div id="div_doc_append">
        </div>
        <div class="m-dropzone dropzone dz-clickable up_doc_global " id="up_oftalmo_drop" data-routeupfile="usuarios/upload_dropzone/documentos_digitales|acta_nac|/upload_avatar">
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
</div><br>
    