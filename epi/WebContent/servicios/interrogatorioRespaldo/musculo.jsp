<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  <div class="m-form__group form-group row">
    <label for="int_artritis" class="col-7 col-form-label">
      HISTORIA DE ARTRITIS:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_artritis">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_amputacion" class="col-7 col-form-label">
      AMPUTACIÓN DE ALGÚN MIEMBRO:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_amputacion" data-enable_input="int_uso_protesis">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_uso_protesis" class="col-7 col-form-label">
      USO DE PRÓTESIS:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_uso_protesis" name="int_uso_protesis" data-placeholder="Seleccione" disabled>
        <?=$catalog->selectCatalog($this->help,'uso_protesis',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_artropatias" class="col-7 col-form-label">
       ANTECEDENTES DE ARTROPATIAS:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_artropatias" name="int_artropatias" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'ant_artropatias',null);?>
      </select>
    </div>
  </div>
<script>
$(".styleSelect2").select2({
    width: '100%'
});
</script>
