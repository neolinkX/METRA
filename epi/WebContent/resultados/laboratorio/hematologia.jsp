<div class="form-group m-form__group">
  <div class="alert m-alert m-alert--default" role="alert">
    <b>HEMATOLOGÍA</b>
  </div>
</div>

<form class="m-form m-form--fit m-form--group-seperator-dashed" name="frm_hematologia" id="frm_hematologia">
  <div class="tab-content">

    <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
      <div class="form-group m-form__group row" align="center">
        <label for="hem_tipo_sanguineo" class="col-2 col-form-label">
          Tipo Sanguineo:
        </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="hem_tipo_sanguineo" name="hem_tipo_sanguineo" rows="2" disabled><?php echo ucwords($datos_hematologia[0]['hem_tipo_sanguineo']); ?></textarea>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="hem_frh" class="col-2 col-form-label">
          Factor RH:
        </label>
        <div class="col-4" align="center">
          <span class="m-switch m-switch--sm m-switch--icon">
            <?php
               if($datos_hematologia[0]['hem_frh']==1){
                 $check_hem_frh="checked";
               }else{
                 $check_hem_frh="";
               }
            ?>
            <label>
              <!-- <input name="hem_frh" type="checkbox" <?php echo $check_hem_frh; ?> disabled> -->
              <input name="hem_frh" type="checkbox"  disabled>
              <span></span>
            </label>
          </span>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="hem_obs" class="col-2 col-form-label">
          Observaciones:
        </label>
        <div class="col-10">
          <textarea maxlength="500" class="form-control m-input m-input--air" id="hem_obs" name="hem_obs" rows="2" disabled><?php echo ucwords($datos_hematologia[0]['hem_obs']); ?></textarea>
        </div>
      </div>
    </div>
  </div>
</form>
