<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_forma">
			<?=$q_expFis['exp_fis_abdomen1']['label'];?>
		</label>
    <select name="exp_forma" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
     <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_cicatrices">
			<?=$q_expFis['exp_fis_abdomen2']['label'];?>
		</label>
    <div class="col-12">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="exp_cicatrices">
          <span></span>
        </label>
      </span>
    </div>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_peristalticos">
			<?=$q_expFis['exp_fis_abdomen3']['label'];?>
		</label>
    <select name="exp_peristalticos" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
    </select>
	</div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_hepatomegalia">
			<?=$q_expFis['exp_fis_abdomen4']['label'];?>
		</label>
    <select name="exp_hepatomegalia" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
    </select>
	</div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_hernias">
			<?=$q_expFis['exp_fis_abdomen5']['label'];?>
		</label>
    <div class="col-12">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="exp_hernias" id="exp_hernias" value="1" data-enable_input="exp_especifique_hernias">
          <span></span>
        </label>
      </span>
    </div>
	</div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_especifique_hernias">
			<?=$q_expFis['exp_fis_abdomen6']['label'];?>
		</label>
    <select name="exp_especifique_hernias" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione" disabled id="exp_especifique_hernias">
      <?=$catalog->selectCatalog($this->help,'hernia',null);?>
    </select>
	</div>
</div>
<!--<div class="row">
	<!--<div class="form-group m-form__group col-lg-4">
		<label for="exp_irritacion_peritoneal">
			<?//$q_expFis['exp_fis_abdomen7']['label'];?>
		</label>
    <select name="exp_irritacion_peritoneal" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" title="Seleccione">
      <?//$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
    </select>
	</div>-->
  <!--<div class="form-group m-form__group col-lg-4">
    <label>
			<?//=$q_expFis['exp_fis_abdomen8']['label'];?>
		</label><br>
    <div class="col-lg-12">
      <a href="javascript:;" onclick="modal_abdomen(1);" class="m-list-search__result-item">
  			<span class="m-list-search__result-item-icon">
  				<i class="flaticon-share m--font-primary"></i>
  			</span>
  			<span class="m-list-search__result-item-text">
  				Capturar Punto
  			</span>
  		</a>
    </div>

    <div class="form-group m-form__group col-lg-12">
      <a href="javascript:;" onclick="modal_resultados_abdomen(1);" class="m-list-search__result-item">
        <span class="m-list-search__result-item-icon">
          <i class="flaticon-visible m--font-primary"></i>
        </span>
        <span class="m-list-search__result-item-text">
          Mostrar Puntos
        </span>
      </a>
    </div>
	</div>
</div>-->

<div class="row">

  <div class="form-group m-form__group col-lg-4">
		<label for="exp_ureterales_dolorosos">
			<?=$q_expFis['exp_fis_abdomen10']['label'];?>
		</label>
    <select name="exp_ureterales_dolorosos" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
     <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_giordano">
			<?=$q_expFis['exp_fis_abdomen11']['label'];?>
		</label>
    <select name="exp_giordano" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'positivo_negativo',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_irritacion_peritoneal">
			<?=$q_expFis['exp_fis_abdomen7']['label'];?>
		</label>
    <select name="exp_irritacion_peritoneal" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presenta_no_presenta',null);?>
    </select>
	</div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_palpacion_abdomen">
			<?=$q_expFis['exp_fis_abdomen9']['label'];?>
		</label>
    <select name="exp_palpacion_abdomen" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
    </select>
	</div>
</div>
    