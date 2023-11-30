<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_anteflexión_dorsoflexion">
			<?=$q_expFis['exp_fis_cuello1']['label'];?>
		</label>
    <select name="exp_anteflexión_dorsoflexion" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_simetria_cuello">
			<?=$q_expFis['exp_fis_cuello2']['label'];?>
		</label>
    <select name="exp_simetria_cuello" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'simetrico_asimetrico',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_pulsovenoso_derecho">
			<?=$q_expFis['exp_fis_cuello3']['label'];?>
		</label>
    <select name="exp_pulsovenoso_derecho" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'pulso_venoso',null);?>
    </select>
	</div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_pulsocarotideo_izquierdo">
			<?=$q_expFis['exp_fis_cuello4']['label'];?>
		</label>
    <select name="exp_pulsocarotideo_izquierdo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'pulso_carotideo',null);?>
    </select>
	</div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_traquea_posicion">
			<?=$q_expFis['exp_fis_cuello5']['label'];?>
		</label>
    <select name="exp_traquea_posicion" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'traquea',null);?>
    </select>
	</div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_glandula_tiroides">
			<?=$q_expFis['exp_fis_cuello6']['label'];?>
		</label>
    <select name="exp_glandula_tiroides" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'tiroides',null);?>
    </select>
	</div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_adenopatias">
			<?=$q_expFis['exp_fis_cuello7']['label'];?>
		</label>
    <select name="exp_adenopatias" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'adenopatias_2',null);?>
    </select>
	</div>
</div>
    