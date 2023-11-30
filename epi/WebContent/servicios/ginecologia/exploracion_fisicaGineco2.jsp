<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

      <div class="row">

      <div class="form-group m-form__group col-lg-3">
         <label for="gi_fecha_ur">
         <?=$q_gineco['exp_gineco13']['label'];?>
         </label>
         <input name="gi_fecha_ur" type="text" class="form-control m-input" placeholder="">
      </div>
      <!--<div class="form-group m-form__group col-lg-3">
         <label for="gi_grupo_sanguineo">
         <?//=$q_gineco['exp_gineco14']['label'];?>
         </label>
         <select name="gi_grupo_sanguineo" class="form-control" data-live-search="true" title="Seleccione" style="width: 100%;">
            <?//=$catalog->selectCatalog($this->help,'',null);?>
         </select>
      </div>-->

      <div class="form-group m-form__group col-lg-3">
         <label for="gi_nembarazos">
         <?=$q_gineco['exp_gineco15']['label'];?>
         </label>
         <input name="gi_nembarazos" type="number" class="form-control m-input" placeholder="">
      </div>

      <div class="form-group m-form__group col-lg-3">
         <label for="gi_nabortos">
         <?=$q_gineco['exp_gineco25']['label'];?>
         </label>
         <input name="gi_nabortos" type="number" class="form-control m-input" placeholder="">
      </div>

      <div class="form-group m-form__group col-lg-3">
         <label for="gi_npartos">
         <?=$q_gineco['exp_gineco16']['label'];?>
         </label>
         <input name="gi_npartos" type="number" class="form-control m-input" placeholder="">
      </div>

   </div>
   <div class="row">
      <div class="form-group m-form__group col-lg-3">
         <label for="gi_ncesareas">
         <?=$q_gineco['exp_gineco17']['label'];?>
         </label>
         <input name="gi_ncesareas" type="number" class="form-control m-input" placeholder="">
      </div>
      <div class="form-group m-form__group col-lg-3">
         <label for="gi_embarazo_actual">
         <?=$q_gineco['exp_gineco18']['label'];?>
         </label>
         <div class="col-12">
            <span class="m-switch m-switch--sm m-switch--icon">
            <label>
            <input type="checkbox" name="gi_embarazo_actual" id="gi_embarazo_actual" value="1" onclick="habilita_dependiente_check('gi_edad_gestional','gi_embarazo_actual')">
            <span></span>
            </label>
            </span>
         </div>
      </div>
      <div class="form-group m-form__group col-lg-3">
         <label for="gi_edad_gestional">
         <?=$q_gineco['exp_gineco19']['label'];?>
         </label>
         <input type="number" name="gi_edad_gestional" id="gi_edad_gestional" class="form-control m-input" placeholder="" disabled>
      </div>

      <div class="form-group m-form__group col-lg-3">
         <label for="gi_factores_riesgo">
         <?=$q_gineco['exp_gineco20']['label'];?>
         </label>
         <select name="gi_factores_riesgo" class="form-control" data-live-search="true" title="Seleccione" style="width: 100%;">
          	<?=$catalog->selectCatalog($this->help,'cat_factor_riesgo',null);?>
         </select>
      </div>
   </div>
   <div class="row">
      <div class="form-group m-form__group col-lg-6">
        <label for="gi_signos_alarma">
        <?=$q_gineco['exp_gineco21']['label'];?>
        </label>
        <select class="js-example-basic-multiple" name="gi_signos_alarma[]" multiple="multiple" style="width: 100%;">
        	<?=$catalog->selectCatalog($this->help,'cat_signos_alarma',null);?>
        </select>
      </div>
      <div class="form-group m-form__group col-lg-6">
         <label for="gi_emergencia_obstetrica">
         <?=$q_gineco['exp_gineco22']['label'];?>
         </label>
         <select name="gi_emergencia_obstetrica[]" multiple="multiple" class="js-example-basic-multiple" style="width: 100%;">
           	<?=$catalog->selectCatalog($this->help,'cat_emergencia_obstetrica',null);?>
         </select>
      </div>
    </div>
    <div class="row">
      <div class="form-group m-form__group col-lg-6">
         <label for="gi_planificacion_familiar">
         <?=$q_gineco['exp_gineco23']['label'];?>
         </label>
         <select name="gi_planificacion_familiar[]" multiple="multiple" class="js-example-basic-multiple" style="width: 100%;">
         		<?=$catalog->selectCatalog($this->help,'cat_planificacion_familiar',null);?>
         </select>
      </div>

      <div class="form-group m-form__group col-lg-6">
         <label for="gi_inicio_vida_sexual">
         <?=$q_gineco['exp_gineco24']['label'];?>
         </label>
         <input type="number" name="gi_inicio_vida_sexual" class="form-control m-input" placeholder="">
      </div>
   </div>
    