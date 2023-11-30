<div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    <b>DROGAS TERAPEÚTICAS</b>
  </div>
</div>
<form class="m-form m-form--fit m-form--group-seperator-dashed" name="frm_dterapeuticas" id="frm_dterapeuticas">
	<div class="tab-content">

    <div class="form-group m-form__group row" align="center">
      <label for="dterapeuticas_avalproico" class="col-2 col-form-label">
        Acido Valproico:
      </label>
      <div class="col-4">
        <input type="number" class="form-control m-input" name="dterapeuticas_avalproico" disabled value="<?=$datos_drogas[0]['dterapeuticas_avalproico']?>">
      </div>
    </div>

    <div class="form-group m-form__group row">
      <label for="dterapeuticas_fenitoina" class="col-2 col-form-label">
        Fenitoina:
      </label>
      <div class="col-4">
        <input type="number" class="form-control" name="dterapeuticas_fenitoina" disabled value="<?=$datos_drogas[0]['dterapeuticas_fenitoina']?>">
      </div>
      <div class="col-6">
        <div class="alert m-alert m-alert--default" role="alert">
          microgramos /ml &nbsp;&nbsp;&nbsp; Rango: &nbsp; 0 mg/dL
        </div>
      </div>
    </div>

    <div class="form-group m-form__group row">
      <label for="dterapeuticas_carbamacepina" class="col-2 col-form-label">
        Carbamacepina:
      </label>
      <div class="col-4">
        <input type="number" class="form-control" name="dterapeuticas_carbamacepina" disabled value="<?=$datos_drogas[0]['dterapeuticas_carbamacepina']?>">
      </div>
      <div class="col-6">
        <div class="alert m-alert m-alert--default" role="alert">
          microgramos /ml &nbsp;&nbsp;&nbsp; Rango: &nbsp; 10 - 20 mg/dL
        </div>
      </div>
    </div>
    <div class="form-group m-form__group row">
      <label for="dterapeuticas_obs" class="col-2 col-form-label">
        Observaciones:
      </label>
      <div class="col-4">
        <textarea maxlength="500" class="form-control m-input m-input--air" id="dterapeuticas_obs" name="dterapeuticas_obs" rows="2" disabled><?php echo ucwords($datos_drogas[0]['dterapeuticas_obs']); ?></textarea>
      </div>
    </div>
    <div class="form-group m-form__group row">
      <label for="dterapeuticas_metodo" class="col-2 col-form-label">
        Método:
      </label>
      <div class="col-4">
        <textarea maxlength="500" class="form-control m-input m-input--air" id="dterapeuticas_metodo" name="dterapeuticas_metodo" rows="2" disabled><?php echo ucwords($datos_drogas[0]['dterapeuticas_metodo']); ?></textarea>
      </div>
    </div>
  </div>
</form>
