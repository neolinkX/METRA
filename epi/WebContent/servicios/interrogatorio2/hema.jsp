<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  <div class="m-form__group form-group row">
    <label for="int_sudores_nocturnos" class="col-7 col-form-label">
      SUDORES NOCTURNOS:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_sudores_nocturnos">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_adenopatias" class="col-7 col-form-label">
      ADENOPATÍAS:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_adenopatias" data-placeholder="Seleccione" name="int_adenopatias">
        <?=$catalog->selectCatalog($this->help,'adenopatias',null);?>
      </select>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_manchas_piel" class="col-7 col-form-label">
      MANCHAS ROJAS EN PIEL Y/O MUCOSAS:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_manchas_piel">
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
