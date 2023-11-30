<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_inspeccion">
			<?=$q_expFis['exp_fis_acardiaca1']['label'];?>
		</label>
    <select name="exp_inspeccion" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
    </select>
  </div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_palpacion_fremito">
			<?=$q_expFis['exp_fis_acardiaca2']['label'];?>
		</label>
    <select name="exp_palpacion_fremito" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_palpacion_paraesternales">
			<?=$q_expFis['exp_fis_acardiaca3']['label'];?>
		</label>
    <select name="exp_palpacion_paraesternales" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
    </select>
  </div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_percusion">
			<?=$q_expFis['exp_fis_acardiaca4']['label'];?>
		</label>
    <select name="exp_percusion" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'percusion',null);?>
    </select>
  </div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_auscultacion_primer_ruido">
			<?=$q_expFis['exp_fis_acardiaca5']['label'];?>
		</label>
    <select name="exp_auscultacion_primer_ruido" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'auscultacion_1',null);?>
    </select>
  </div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_auscultacion_segundo_ruido">
			<?=$q_expFis['exp_fis_acardiaca6']['label'];?>
		</label>
    <select name="exp_auscultacion_segundo_ruido" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'auscultacion_2',null);?>
    </select>
  </div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_auscultacion_tercer_ruido">
			<?=$q_expFis['exp_fis_acardiaca7']['label'];?>
		</label>
    <select name="exp_auscultacion_tercer_ruido" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
    </select>
  </div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_soplo">
			<?=$q_expFis['exp_fis_acardiaca8']['label'];?>
		</label>
    <select name="exp_soplo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presenta_soplos',null);?>
    </select>
  </div>
</div>
    