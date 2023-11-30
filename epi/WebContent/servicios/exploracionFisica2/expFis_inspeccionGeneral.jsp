<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_cabeza">
      <?=$q_expFis['exp_fis_insg1']['label'];?>
      </label>
      <select  name="exp_cabeza" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'cabeza',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_cuero_cabelludo">
      <?=$q_expFis['exp_fis_insg2']['label'];?>
      </label>
      <select name="exp_cuero_cabelludo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'cuero_cabelludo',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_edad_cronologica">
      <?=$q_expFis['exp_fis_insg3']['label'];?>
      </label>
      <select name="exp_edad_cronologica" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'edad_cronologico',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_piel">
      <?=$q_expFis['exp_fis_insg4']['label'];?>
      </label>
      <select name="exp_piel" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_constitucion_corporal" class="">
      <?=$q_expFis['exp_fis_insg5']['label'];?>
      </label>
      <select name="exp_constitucion_corporal" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'constitucion_corporal',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_marcha">
      <?=$q_expFis['exp_fis_insg6']['label'];?>
      </label>
      <select name="exp_marcha" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'marcha',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_romberg">
      <?=$q_expFis['exp_fis_insg7']['label'];?>
      </label>
      <select name="exp_romberg" id="exp_romberg" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'cat_romberg',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_punta_nariz">
      <?=$q_expFis['exp_fis_insg8']['label'];?>
      </label>
      <select name="exp_punta_nariz" id="exp_punta_nariz" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'cat_punta_nariz',null);?>
      </select>
   </div>
</div>

    