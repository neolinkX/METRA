<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_oido_externo">
			<?=$q_expFis['exp_fis_oido1']['label'];?>
		</label>
    <select name="exp_oido_externo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'oido_exteno',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_oido_medio">
			<?=$q_expFis['exp_fis_oido2']['label'];?>
		</label>
    <select name="exp_oido_medio" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'oido_medio',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_pabellones">
			<?=$q_expFis['exp_fis_oido3']['label'];?>
		</label>
    <select name="exp_pabellones" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'pab_auriculares',null);?>
    </select>
	</div>
</div>
   