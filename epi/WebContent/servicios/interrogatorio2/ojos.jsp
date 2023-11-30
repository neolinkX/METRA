<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


  <div class="m-form__group form-group row">
    <label for="int_lentes" class="col-7 col-form-label">
      ¿UTILIZA LENTES DE CORRECCIÓN PARA EL DESEMPEÑO DE SUS LABORES?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" value="1" name="int_lentes" id="int_lentes" onclick="habilita_dependiente_check('int_tipo_lentes','int_lentes');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_tipo_lentes" class="col-7 col-form-label">
      ESPECIFIQUE TIPO DE LENTES: CONTACTO O AÉREO:
    </label>
    <div class="col-5">
      <textarea maxlength="200" class="form-control m-input m-input--air" id="int_tipo_lentes" name="int_tipo_lentes" rows="2" disabled></textarea>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_cirugia_oftalmologica" class="col-7 col-form-label">
      ANTECEDENTES DE CIRUGIA OFTALMOLOGICA:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" value="1" name="int_cirugia_oftalmologica" id="int_cirugia_oftalmologica" onclick="habilita_dependiente_check('int_cirugia','int_cirugia_oftalmologica');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_cirugia" class="col-7 col-form-label">
      ESPECIFIQUE CIRUGIA:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_cirugia" name="int_cirugia" data-placeholder="Seleccione" disabled>
        <?=$catalog->selectCatalog($this->help,'cirugia',null);?>
      </select>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_vista_borrosa" class="col-7 col-form-label">
      ¿PRESENTA VISION BORROSA ACTUALMENTE?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" value="1" name="int_vista_borrosa" id="int_vista_borrosa" onclick="habilita_dependiente_check('int_vision_borrosa','int_vista_borrosa');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_vision_borrosa" class="col-7 col-form-label">
      DETERMINE VISIÓN BORROSA:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_vision_borrosa" name="int_vision_borrosa" data-placeholder="Seleccione" disabled>
        <?=$catalog->selectCatalog($this->help,'vision_borrosa',null);?>
      </select>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_distinguir_colores" class="col-7 col-form-label">
      ¿DIFICULTAD PARA DISTINGUIR COLORES?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" value="1" name="int_distinguir_colores" id="int_distinguir_colores" onclick="habilita_dependiente_check('int_especifique_color','int_distinguir_colores');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_especifique_color" class="col-7 col-form-label">
      ESPECIFIQUE COLOR:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_especifique_color" name="int_especifique_color" data-placeholder="Seleccione" disabled>
          <?=$catalog->selectCatalog($this->help,'colores',null);?>
      </select>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_manchas_vista" class="col-7 col-form-label">
      ¿PERCEPCIÓN DE MANCHAS EN LA VISTA?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" value="1" name="int_manchas_vista" id="int_manchas_vista" onclick="habilita_dependiente_check('int_determine_manchas','int_manchas_vista');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_determine_manchas" class="col-7 col-form-label">
      DETERMINE MANCHAS:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_determine_manchas" name="int_determine_manchas" data-placeholder="Seleccione" disabled>
          <?=$catalog->selectCatalog($this->help,'manchas',null);?>
      </select>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_dolor_ojos" class="col-7 col-form-label">
      ¿DOLOR EN LOS OJOS, RECIENTE?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" value="1" name="int_dolor_ojos" id="int_dolor_ojos" onclick="habilita_dependiente_check('int_clasificacion_cefalea','int_dolor_ojos');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_clasificacion_cefalea" class="col-7 col-form-label">
      ESPECIFIQUE CLASIFICACIÓN DE CEFALEA:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_clasificacion_cefalea" name="int_clasificacion_cefalea" data-placeholder="Seleccione" disabled>
        <?=$catalog->selectCatalog($this->help,'cefalea',null);?>
      </select>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_trauma_ocular" class="col-7 col-form-label">
      ¿TRAUMA OCULAR RECIENTE?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_trauma_ocular">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_mareo_frecuente" class="col-7 col-form-label">
      ¿MAREO FRECUENTE?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_mareo_frecuente">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>
<script>
$(".styleSelect2").select2({
    width: '100%'
});
</script>
