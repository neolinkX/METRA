<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  <div class="m-form__group form-group row">
    <label for="int_problemas_audicion" class="col-7 col-form-label">
      HA TENIDO PROBLEMAS DE AUDICION RECIENTE:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_problemas_audicion">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_cirugias_opticas" class="col-7 col-form-label">
      CIRUGIAS OTICAS RECIENTES
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_cirugias_opticas">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_vertigo" class="col-7 col-form-label">
      VERTIGO RECIENTE:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_vertigo">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_otitis_cronica" class="col-7 col-form-label">
      OTITIS CRÓNICA:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_otitis_cronica">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_acufenos_tinitus" class="col-7 col-form-label">
      ¿REFIERE ACUFENOS O TINITUS?:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_acufenos_tinitus">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_oidos_nariz_garganta" class="col-7 col-form-label">
      ¿PRESENTA ALGUNA ALTERACIÓN DE OÍDOS, NARIZ O GARAGANTA?:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_oidos_nariz_garganta">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_auxiliar_auditivo" class="col-7 col-form-label">
      ¿UTILIZA ALGÚN AUXILIAR AUDITIVO?:
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_auxiliar_auditivo" id="int_auxiliar_auditivo" value="1" onclick="habilita_dependiente_check('int_supervisa_auditivo','int_auxiliar_auditivo');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_supervisa_auditivo" class="col-7 col-form-label">
      CADA CUANDO LO SUPERVISA SU AUXILIAR AUDITIVO:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_supervisa_auditivo" name="int_supervisa_auditivo" data-placeholder="Seleccione" disabled>
        <?=$catalog->selectCatalog($this->help,'supervision_auditiva',null);?>
      </select>
    </div>
  </div>
<script>
$(".styleSelect2").select2({
    width: '100%'
});
</script>
