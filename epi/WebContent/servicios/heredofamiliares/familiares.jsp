<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <div id="content_familiares" class="pantallas">
  <div class="m-form__group form-group row">
    <label class="col-4 col-form-label">
      Madre vive:
    </label>
    <div class="col-4">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon" align="center">
          <label>
            <input type="checkbox" name="hf_madre_vive" value="1" onclick="deshabilita_dependiente_check('hf_causa_madre','hf_madre_vive');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="hf_causa_madre" class="col-4 col-form-label">
      Causa de Muerte:
    </label>
    <div class="col-6">
      <textarea maxlength="200" class="form-control" name="hf_causa_muerte" id="hf_causa_madre" rows="2"></textarea>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label class="col-4 col-form-label">
      Padre vive:
    </label>
    <div class="col-7">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="hf_padre_vive" value="1" onclick="deshabilita_dependiente_check('hf_causa_padre','hf_padre_vive');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="hf_causa_padre" class="col-4 col-form-label">
      Causa de Muerte:
    </label>
    <div class="col-8">
      <textarea maxlength="200" class="form-control" id="hf_causa_padre" name="hf_causa_padre" rows="2"></textarea>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="hf_hermanos_viven" class="col-4 col-form-label">
      Hermanos viven:
    </label>
    <div class="col-7">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="hf_hermanos_viven" value="1" onclick="deshabilita_dependiente_check('hf_causa_hermanos','hf_hermanos_viven');">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="hf_causa_hermanos" class="col-4 col-form-label">
      Causa de Muerte:
    </label>
    <div class="col-8">
      <textarea maxlength="200" class="form-control" id="hf_causa_hermanos" name="hf_causa_hermanos" rows="2"></textarea>
    </div>
  </div>

</div>
    