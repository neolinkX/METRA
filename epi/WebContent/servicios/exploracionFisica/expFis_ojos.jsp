<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_estrabismo">
      <?=$q_expFis['exp_fis_ojo1']['label'];?>
      </label>
      <select name="exp_estrabismo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'cat_estrabismo',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_motilidad_ocular">
      <?=$q_expFis['exp_fis_ojo2']['label'];?>
      </label>
      <select name="exp_motilidad_ocular" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_palpebral">
      <?=$q_expFis['exp_fis_ojo3']['label'];?>
      </label>
      <select name="exp_palpebral" id="exp_palpebral" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione" data-block_by_id="exp_palpebral_especifique" data-value_block='1200'>
         <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_palpebral_especifique">
      <?=$q_expFis['exp_fis_ojo4']['label'];?>
      </label>
      <select name="exp_palpebral_especifique" id="exp_palpebral_especifique" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'ptosis_palpebral',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_conjuntivas">
      <?=$q_expFis['exp_fis_ojo5']['label'];?>
      </label>
      <select name="exp_conjuntivas" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'conjuntivas',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_esclera_derecho">
      <?=$q_expFis['exp_fis_ojo6']['label'];?>
      </label>
      <select name="exp_esclera_derecho" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'esclera',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_esclera_izquierdo">
      <?=$q_expFis['exp_fis_ojo7']['label'];?>
      </label>
      <select name="exp_esclera_izquierdo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'esclera',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_fotomotor_derecho">
      <?=$q_expFis['exp_fis_ojo8']['label'];?>
      </label>
      <select name="exp_fotomotor_derecho" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'reflejo_fotomotor',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_fotomotor_izquierdo">
      <?=$q_expFis['exp_fis_ojo9']['label'];?>
      </label>
      <select name="exp_fotomotor_izquierdo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'reflejo_fotomotor',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_consencual_derecho">
      <?=$q_expFis['exp_fis_ojo10']['label'];?>
      </label>
      <select name="exp_consencual_derecho" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_consencual_izquierdo">
      <?=$q_expFis['exp_fis_ojo11']['label'];?>
      </label>
      <select name="exp_consencual_izquierdo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_nistagmus">
      <?=$q_expFis['exp_fis_ojo12']['label'];?>
      </label>
      <select name="exp_nistagmus" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-6">
      <label for="exp_dolor_derecho">
      <?=$q_expFis['exp_fis_ojo13']['label'];?>
      </label>
      <select name="exp_dolor_derecho" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-6">
      <label for="exp_dolor_izquierdo">
      <?=$q_expFis['exp_fis_ojo14']['label'];?>
      </label>
      <select name="exp_dolor_izquierdo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_secrecion_derecho">
      <?=$q_expFis['exp_fis_ojo15']['label'];?>
      </label>
      <select name="exp_secrecion_derecho" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_secrecion_izquierdo">
      <?=$q_expFis['exp_fis_ojo16']['label'];?>
      </label>
      <select name="exp_secrecion_izquierdo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
      </select>
   </div>
</div>
    