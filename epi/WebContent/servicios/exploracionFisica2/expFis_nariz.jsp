<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_desviacion_septal">
			<?=$q_expFis['exp_fis_nariz1']['label'];?>
		</label>
    <select name="exp_desviacion_septal" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
     <?=$catalog->selectCatalog($this->help,'desviacion_septal',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_fosales_nasales">
			<?=$q_expFis['exp_fis_nariz2']['label'];?>
		</label>
    <select name="exp_fosales_nasales" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
     <?=$catalog->selectCatalog($this->help,'perm_fosas_nasales',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_mucosa_nasal">
			<?=$q_expFis['exp_fis_nariz3']['label'];?>
		</label>
    <select name="exp_mucosa_nasal" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'mucosa_nasal',null);?>
    </select>
	</div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_meatos">
			<?=$q_expFis['exp_fis_nariz4']['label'];?>
		</label>
    <select name="exp_meatos" class="form-control m-bootstrap-select m_selectpicker" data-placeholder="Seleccione" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'meatos',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_secrecion_nasal">
			<?=$q_expFis['exp_fis_nariz5']['label'];?>
		</label>
    <select name="exp_secrecion_nasal" id="exp_secrecion_nasal" class="form-control m-bootstrap-select m_selectpicker" data-placeholder="Seleccione" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'cat_secrecion_nasal',null);?>
    </select>
	</div>
</div>
    