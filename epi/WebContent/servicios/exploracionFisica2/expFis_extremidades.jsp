<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_extremidades_toracicas">
			<?=$q_expFis['exp_fis_extremidades1']['label'];?>
		</label>
    <select name="exp_extremidades_toracicas" id="exp_extremidades_toracicas" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione" onchange="valida_simetria_ext_toracicas()">
      <?=$catalog->selectCatalog($this->help,'simetrico_asimetrico',null);?>
    </select>
	</div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_extremidades_superiores">
			<?=$q_expFis['exp_fis_extremidades2']['label'];?>
		</label>
    <select name="exp_extremidades_superiores" id="exp_extremidades_superiores" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione" disabled>
      <?=$catalog->selectCatalog($this->help,'asimetria_ext_sup',null);?>
    </select>
	</div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_extremidades_pelvicas">
			<?=$q_expFis['exp_fis_extremidades3']['label'];?>
		</label>
    <select name="exp_extremidades_pelvicas" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'simetrico_asimetrico',null);?>
    </select>
	</div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_asimetria">
			<?=$q_expFis['exp_fis_extremidades4']['label'];?>
		</label>
    <select name="exp_asimetria" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
     <?=$catalog->selectCatalog($this->help,'asimetria_ext_pelv',null);?>
    </select>
	</div>
  <div class="form-group m-form__group col-lg-4">
    <label for="exp_mobilidad_extremidades">
      <?=$q_expFis['exp_fis_extremidades5']['label'];?>
    </label>
    <select name="exp_mobilidad_extremidades" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
    </select>
  </div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_tono_muscular">
			<?=$q_expFis['exp_fis_extremidades6']['label'];?>
		</label>
    <select name="exp_tono_muscular" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'tono_musc',null);?>
    </select>
	</div>
</div>
<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_fuerza_muscular">
			<?=$q_expFis['exp_fis_extremidades7']['label'];?>
		</label>
    <select name="exp_fuerza_muscular" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'fuerza_musc',null);?>
    </select>
	</div>

  <div class="form-group m-form__group col-lg-4">
    <label for="exp_bicipital_derecho_izquierdo">
      <?=$q_expFis['exp_fis_extremidades8']['label'];?>
    </label>
    <select name="exp_bicipital_derecho_izquierdo" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
    </select>
  </div>
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_dorsolumbar">
			<?=$q_expFis['exp_fis_extremidades9']['label'];?>
		</label>
    <textarea maxlength="200" name="exp_dorsolumbar" id="exp_dorsolumbar" class="col-lg-12 form-control m-input m-input--air" id="exampleTextarea" rows="2"></textarea>
	</div>

</div>

<div class="row">
  <div class="form-group m-form__group col-lg-4">
		<label for="exp_babinski">
			<?=$q_expFis['exp_fis_extremidades10']['label'];?>
		</label>
    <select name="exp_babinski" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
      <?=$catalog->selectCatalog($this->help,'presente_ausente',null);?>
    </select>
	</div>

  <div class="form-group m-form__group col-lg-4">
    <label for="exp_amputaciones_extremidades">
      <?=$q_expFis['exp_fis_extremidades11']['label'];?>
    </label>
    <div class="col-12">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="exp_amputaciones_extremidades">
          <span></span>
        </label>
      </span>
    </div>
  </div>
  <!--<div class="form-group m-form__group col-lg-4">
    <label>
			<?//=$q_expFis['exp_fis_extremidades12']['label'];?>
		</label><br>
    <div class="col-lg-12">
      <a href="javascript:;" onclick="modal_extremidades('1');" class="m-list-search__result-item">
  			<span class="m-list-search__result-item-icon">
  				<i class="flaticon-share m--font-primary"></i>
  			</span>
  			<span class="m-list-search__result-item-text">
  				Capturar Punto
  			</span>
  		</a>
    </div>

    <div class="form-group m-form__group col-lg-12">
      <a href="#" class="m-list-search__result-item">
        <span class="m-list-search__result-item-icon">
          <i class="flaticon-visible m--font-primary"></i>
        </span>
        <span href="javascript:;" onclick="modal_resultados_extremidades(1);" class="m-list-search__result-item-text">
          Mostrar Puntos
        </span>
      </a>
    </div>
	</div> -->
	<div class="form-group m-form__group col-lg-4">
    <label for="exp_usa_protesis">
      <?=$q_expFis['exp_fis_extremidades15']['label'];?>
    </label>
    <div class="col-12">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="exp_usa_protesis">
          <span></span>
        </label>
      </span>
    </div>
  </div>
</div>

<!--<div class="row">

  <div class="form-group m-form__group col-lg-4">
    <label>
			<?//=$q_expFis['exp_fis_extremidades13']['label'];?>
		</label><br>
    <div class="col-lg-12">
      <a href="javascript:;" onclick="modal_mano_derecha('1');" class="m-list-search__result-item">
  			<span class="m-list-search__result-item-icon">
  				<i class="flaticon-share m--font-primary"></i>
  			</span>
  			<span class="m-list-search__result-item-text">
  				Capturar Punto
  			</span>
  		</a>
    </div>

    <div class="form-group m-form__group col-lg-12">
      <a href="javascript:;" onclick="modal_resultadosmano_derecha(1);" class="m-list-search__result-item">
        <span class="m-list-search__result-item-icon">
          <i class="flaticon-visible m--font-primary"></i>
        </span>
        <span class="m-list-search__result-item-text">
          Mostrar Puntos
        </span>
      </a>
    </div>
	</div>
  <div class="form-group m-form__group col-lg-4">
    <label>
			<?//=$q_expFis['exp_fis_extremidades14']['label'];?>
		</label><br>
    <div class="col-lg-12">
      <a href="javascript:;" onclick="modal_mano_izquierda('1');" class="m-list-search__result-item">
  			<span class="m-list-search__result-item-icon">
  				<i class="flaticon-share m--font-primary"></i>
  			</span>
  			<span class="m-list-search__result-item-text">
  				Capturar Punto
  			</span>
  		</a>
    </div>

    <div class="form-group m-form__group col-lg-12">
      <a href="javascript:;" onclick="modal_resultadosmano_izquierda(1);" class="m-list-search__result-item">
        <span class="m-list-search__result-item-icon">
          <i class="flaticon-visible m--font-primary"></i>
        </span>
        <span class="m-list-search__result-item-text">
          Mostrar Puntos
        </span>
      </a>
    </div>
	</div>

</div> -->

<div class="row">
  <div class="form-group m-form__group col-lg-4">
    <label for="exp_neurotension">
      <?=$q_expFis['exp_fis_extremidades16']['label'];?>
    </label>
    <div class="col-12">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="exp_neurotension" id="exp_neurotension" value="1" onclick="habilita_dependiente_check('exp_especifique_neurotension','exp_neurotension')">
          <span></span>
        </label>
      </span>
    </div>
  </div>
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_especifique_neurotension">
			<?=$q_expFis['exp_fis_extremidades17']['label'];?>
		</label>
    <textarea name="exp_especifique_neurotension" id="exp_especifique_neurotension" maxlength="200" class="col-lg-12 form-control m-input m-input--air" rows="2" disabled></textarea>
	</div>
  <div class="form-group m-form__group col-lg-4">
    <label for="exp_dermatomocosis">
      <?=$q_expFis['exp_fis_extremidades18']['label'];?>
    </label>
    <div class="col-12">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="exp_dermatomocosis" id="exp_dermatomocosis" value="1" onclick="habilita_dependiente_check('exp_especifique_dermatomocosis','exp_dermatomocosis')">
          <span></span>
        </label>
      </span>
    </div>
  </div>
</div>

<div class="row">
	<div class="form-group m-form__group col-lg-4">
		<label for="exp_especifique_dermatomocosis ">
			<?=$q_expFis['exp_fis_extremidades19']['label'];?>
		</label>
    <textarea name="exp_especifique_dermatomocosis" id="exp_especifique_dermatomocosis" maxlength="200" class="col-lg-12 form-control m-input m-input--air" rows="2" disabled></textarea>
	</div>
</div>
    