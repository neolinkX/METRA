<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

  <div class="form-group m-form__group">
    <div class="alert m-alert m-alert--default" role="alert">
      Anote la clasificación de las posibilidades de quedarse dormido durante el día en las siguientes situaciones:
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_leyendo_sentado" class="col-7 col-form-label">
      LEYENDO, SENTADO CÓMODAMENTE DURANTE EL DIA:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_leyendo_sentado" name="int_leyendo_sentado" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_television_dia" class="col-7 col-form-label">
      VIENDO TELEVISIÓN DURANTE EL DÍA:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_television_dia" name="int_television_dia" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_sentado_inactivo" class="col-7 col-form-label">
      SENTADO, INACTIVO, EN UN LUGAR PÚBLICO(CONFERENCIA, CLASE, EN EL CINE):
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_sentado_inactivo" name="int_sentado_inactivo" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_viajando_transporte" class="col-7 col-form-label">
      VIAJANDO EN TRANSPORTE PÚBLICO O PRIVADO DURANTE MÁS DE UNA HORA:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_viajando_transporte" name="int_viajando_transporte" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_acostado_descansar" class="col-7 col-form-label">
      ACOSTADO PARA DESCANSAR POR LA TARDE CUANDO LAS CIRCUNSTANCIAS LO PERMITEN:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_acostado_descansar" name="int_acostado_descansar" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_sentado_platicando" class="col-7 col-form-label">
      SENTADO PLATICANDO CON ALGUIEN:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_sentado_platicando" name="int_sentado_platicando" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_sentado_comer" class="col-7 col-form-label">
      SENTADO COMODAMENTE DESPUES DE COMER (SIN HABER TOMADO BEBIDAS ALCOHÓLICAS).:
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_sentado_comer" name="int_sentado_comer" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
  <div class="form-group m-form__group row">
    <label for="int_manejando" class="col-7 col-form-label">
      MANEJANDO MIENTRAS ESPERA UNOS MINUTOS EN EL TRÁFICO:</b>
    </label>
    <div class="col-5">
      <select class="form-control m-select2 styleSelect2" id="int_manejando" name="int_manejando" data-placeholder="Seleccione">
        <?=$catalog->selectCatalog($this->help,'nin_lev_mod_sev',null);?>
      </select>
    </div>
  </div>
<script>
$(".styleSelect2").select2({
    width: '100%'
});
</script>
