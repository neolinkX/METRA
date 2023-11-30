<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <div class="m-portlet__body">
      <div class="form-group m-form__group row">
         <label for="int_fiebre" class="col-3 col-form-label">
         Fiebre:
         </label>
         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="checkbox" name="int_fiebre" value="1" id="int_fiebre" onclick="habilita_dependiente_check('int_fiebre_esp','int_fiebre');">
               <span></span>
               </label>
               </span>
            </div>
         </div>
         <label for="int_fiebre_esp" class="col-3 col-form-label">
         Especifique fiebre:
         </label>
         <div class="col-4">
            <select class="form-control m-select2 styleSelect2" id="int_fiebre_esp" name="int_fiebre_esp" data-placeholder="Seleccione" disabled>
            <?=$catalog->selectCatalog($this->help,'fiebre',null);?>
            </select>
         </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="int_astenia" class="col-3 col-form-label">
        Astenia:
        </label>
        <div class="col-2">
           <div class="col-3">
              <span class="m-switch m-switch--sm m-switch--icon">
              <label>
              <input type="checkbox" name="int_astenia">
              <span></span>
              </label>
              </span>
           </div>
        </div>

        <label for="int_adinamia" class="col-3 col-form-label">
        Adinamia:
        </label>
        <div class="col-4">
           <div class="col-3">
              <span class="m-switch m-switch--sm m-switch--icon">
              <label>
              <input type="checkbox" name="int_adinamia">
              <span></span>
              </label>
              </span>
           </div>
        </div>
      </div>

      <div class="form-group m-form__group row">
        <label for="int_aumento_peso" class="col-3 col-form-label">
        Aumento de peso:
        </label>
        <div class="col-2">
           <div class="col-3">
              <span class="m-switch m-switch--sm m-switch--icon">
              <label>
              <input type="checkbox" value="1" name="int_aumento_peso" id="int_aumento_peso" onclick="habilita_dependiente_check('espe_aumento_peso','int_aumento_peso');">
              <span></span>
              </label>
              </span>
           </div>
        </div>
        <label for="int_fiebre_esp" class="col-3 col-form-label">
        Especifique:
        </label>
        <div class="col-4">
           <textarea maxlength="200" class="form-control m-input m-input--air" id="espe_aumento_peso" name="espe_aumento_peso" rows="2" disabled></textarea>
        </div>
      </div>
      <div class="form-group m-form__group row">
        <label for="int_disminucion_peso" class="col-3 col-form-label">
        Disminución de peso:
        </label>
        <div class="col-2">
           <div class="col-3">
              <span class="m-switch m-switch--sm m-switch--icon">
              <label>
              <input type="checkbox" value="1" name="int_disminucion_peso" id="int_disminucion_peso" id="" onclick="habilita_dependiente_check('int_disminucion_peso_des','int_disminucion_peso');">
              <span></span>
              </label>
              </span>
           </div>
        </div>

        <label for="int_disminucion_peso_des" class="col-3 col-form-label">
        Especifique Disminución:
        </label>
        <div class="col-4">
           <select class="form-control m-select2 styleSelect2" id="int_disminucion_peso_des" name="int_disminucion_peso_des" data-placeholder="Seleccione" disabled>
             <?=$catalog->selectCatalog($this->help,'aumento_peso',null);?>
           </select>
        </div>
      </div>

      <div class="form-group m-form__group row">
         <label for="int_hiporexia" class="col-3 col-form-label">
         Hiporexia:
         </label>
         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="checkbox" name="int_hiporexia">
               <span></span>
               </label>
               </span>
            </div>
         </div>

         <label for="int_hiperorexia" class="col-3 col-form-label">
         Hiperorexia:
         </label>
         <div class="col-4">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="checkbox" name="int_hiperorexia">
               <span></span>
               </label>
               </span>
            </div>
         </div>
      </div>

      <div class="form-group m-form__group row">
         <label for="int_diaforesis" class="col-3 col-form-label">
         Diaforesis:
         </label>
         <div class="col-2">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="checkbox" name="int_diaforesis">
               <span></span>
               </label>
               </span>
            </div>
         </div>

         <label for="int_anticoagulantes" class="col-3 col-form-label">
         Uso de anticoagulantes:
         </label>
         <div class="col-4">
            <div class="col-3">
               <span class="m-switch m-switch--sm m-switch--icon">
               <label>
               <input type="checkbox" value="1" name="int_anticoagulantes" id="int_anticoagulantes" onclick="habilita_dependiente_check('espe_uso_anticoagulantes','int_anticoagulantes');">
               <span></span>
               </label>
               </span>
            </div>
         </div>
      </div>
      <div class="form-group m-form__group row">
        <label for="int_disminucion_peso_des" class="col-3 col-form-label">
        Especifique Uso de anticoagulantes:
        </label>
        <div class="col-4">
           <textarea maxlength="200" class="form-control m-input m-input--air" id="espe_uso_anticoagulantes" name="espe_uso_anticoagulantes" rows="2" disabled></textarea>
        </div>
      </div>

   </div>
<script>
$(".styleSelect2").select2({
    width: '100%'
});
</script>
