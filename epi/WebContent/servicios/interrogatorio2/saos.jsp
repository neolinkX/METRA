<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  <div class="m-form__group form-group row">
    <label for="int_ronca" class="col-7 col-form-label">
      ¿LE HAN DICHO QUE RONCA TODAS O CASI TODAS LAS NOCHES?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_ronca">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_respiracion_ahogado" class="col-7 col-form-label">
      ¿LE HAN DICHO QUE CUANDO RONCA, RESPIRA COMO SI SE ESTUVIERA AHOGANDO?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_respiracion_ahogado">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="m-form__group form-group row">
    <label for="int_deja_respirar" class="col-7 col-form-label">
      ¿LE HAN DICHO QUE CUANDO RONCA, CON FRECUENCIA DEJA DE RESPIRAR POR MOMENTOS?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_deja_respirar">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_no_descansar" class="col-7 col-form-label">
      ¿DESPIERTA UD SINTIÉNDOSE COMO SI NO HUBIERA DESCANSADO, POR LO MENOS TRES DIAS A LA SEMANA?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_no_descansar">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>
  <div class="m-form__group form-group row">
    <label for="int_trabajo_despierto" class="col-7 col-form-label">
      ¿LE CUESTA TRABAJO MANTENERSE DESPIERTO DURANTE EL DIA, POR LO MENOS TRES DIAS A LA SEMANA?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_trabajo_despierto">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_presion_alta" class="col-7 col-form-label">
      ¿PADECE DE PRESIÓN ALTA (HIPERTENSIÓN ARTERIAL SISTÉMICA)?
    </label>
    <div class="col-5">
      <div class="col-3">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="int_presion_alta">
            <span></span>
          </label>
        </span>
      </div>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="circunferencia_cuello" class="col-7 col-form-label">
      CIRCUNFERENCIA DE CUELLO:
    </label>
    <div class="col-5">
        <input type="number" step="0.01" class="form-control m-input" id="circunferencia_cuello" name="circunferencia_cuello">
        <span>cm</span>
    </div>
  </div>

  <div class="form-group m-form__group row">
    <label for="int_imc" class="col-7 col-form-label">
      INDICE DE MASA CORPORAL:
    </label>
    <div class="col-5">
        <input type="number" step="0.01" class="form-control m-input" id="int_imc" name="int_imc">
    </div>
  </div>
