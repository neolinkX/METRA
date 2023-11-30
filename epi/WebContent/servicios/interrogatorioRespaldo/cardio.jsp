<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  <div class="m-form__group form-group row">
    <label for="int_dolor_precordial" class="col-7 col-form-label">
      DOLOR PRECORDIAL:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_dolor_precordial">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_taquicardia" class="col-7 col-form-label">
      EPISODIOS DE TAQUICARDIA:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_taquicardia">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_edema_extremidades" class="col-7 col-form-label">
      EDEMA EN EXTREMIDADES INFERIORES:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_edema_extremidades">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_sincope" class="col-7 col-form-label">
      SINCOPE:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_sincope">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_desmayos" class="col-7 col-form-label">
      LIPOTIMIA O DESMAYOS:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_desmayos">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_cianosis" class="col-7 col-form-label">
      PRESENTA CIANOSIS:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_cianosis" id="int_cianosis" value="1" onclick="habilita_dependiente_check('int_tipo_cianosis','int_cianosis');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_tipo_cianosis" class="col-7 col-form-label">
      CIANOSIS TIPO:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_tipo_cianosis" name="int_tipo_cianosis" data-placeholder="Seleccione" disabled>
        <?=$catalog->selectCatalog($this->help,'cianosis',null);?>
      </select>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_antecedente_cardiovascular" class="col-7 col-form-label">
      ANTECEDENTE DE ENFERMEDAD CARDIOVASCULAR?
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_antecedente_cardiovascular" name="int_antecedente_cardiovascular" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'ant_cardiovascular',null);?>
      </select>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_antecedentes" class="col-7 col-form-label">
      ANTECEDENTES DE CIRUGÍA CARDIACA:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_antecedentes" name="int_antecedentes" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'ant_cirugia_cardio',null);?>
      </select>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_invasivo_cardiaco" class="col-7 col-form-label">
      HISTORIA DE PROCEDIMIENTO INVASIVO CARDIACO:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_invasivo_cardiaco" name="int_invasivo_cardiaco" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'hist_inv_cardio',null);?>
      </select>
    </div>
  </div>
<script>
$(".styleSelect2").select2({
    width: '100%'
});
</script>
