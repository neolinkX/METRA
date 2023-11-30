<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
       <div class="row">
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_protrusion_cutanea">
         <?=$q_gineco['exp_gineco1']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input name="gi_protrusion_cutanea" type="checkbox">
             <span></span>
           </label>
         </span>
       </div>
     </div>
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_direccion_pezon">
         <?=$q_gineco['exp_gineco2']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_direccion_pezon">
             <span></span>
           </label>
         </span>
       </div>
     </div>
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_retraccion_piel">
         <?=$q_gineco['exp_gineco3']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_retraccion_piel">
             <span></span>
           </label>
         </span>
       </div>
     </div>
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_piel_naranja">
         <?=$q_gineco['exp_gineco4']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_piel_naranja">
             <span></span>
           </label>
         </span>
       </div>
     </div>
   </div>
   <div class="row">
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_hipertemia_local">
         <?=$q_gineco['exp_gineco5']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_hipertemia_local">
             <span></span>
           </label>
         </span>
       </div>
     </div>
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_ulceracion_cutanea">
         <?=$q_gineco['exp_gineco6']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_ulceracion_cutanea">
             <span></span>
           </label>
         </span>
       </div>
     </div>
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_secrecion_pezon">
         <?=$q_gineco['exp_gineco7']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_secrecion_pezon">
             <span></span>
           </label>
         </span>
       </div>
     </div>
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_aumento_red">
         <?=$q_gineco['exp_gineco8']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_aumento_red">
             <span></span>
           </label>
         </span>
       </div>
     </div>
   </div>
   <div class="row">
     <div class="form-group m-form__group col-lg-3">
       <label for="gi_hallazgos_clinicos">
         <?=$q_gineco['exp_gineco9']['label'];?>
       </label>
       <div class="col-12 text-center">
         <span class="m-switch m-switch--sm m-switch--icon">
           <label>
             <input type="checkbox" name="gi_hallazgos_clinicos" id="gi_hallazgos_clinicos" value="1" onclick="habilita_dependiente_check('gi_especifique_hallazgos_clinicos','gi_hallazgos_clinicos')">
             <span></span>
           </label>
         </span>
       </div>
     </div>
     <div class="form-group m-form__group col-lg-6">
   		<label for="gi_especifique_hallazgos_clinicos">
   			<?=$q_gineco['exp_gineco26']['label'];?>
   		</label>
       <textarea name="gi_especifique_hallazgos_clinicos" id="gi_especifique_hallazgos_clinicos" maxlength="200" class="col-lg-12 form-control m-input m-input--air" rows="2" disabled></textarea>
   	 </div>
     <div class="form-group m-form__group col-lg-3">
   		<label for="gi_bordes">
   			<?=$q_gineco['exp_gineco10']['label'];?>
   		</label>
       <select name="gi_bordes" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" title="Seleccione">
         <?=$catalog->selectCatalog($this->help,'cat_glandula_mamaria',null);?>
       </select>
   	</div>
    <div class="form-group m-form__group col-lg-3">
  		<label for="gi_mastitis">
  			<?=$q_gineco['exp_gineco11']['label'];?>
  		</label>
      <select name="gi_mastitis" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" title="Seleccione">
        <?=$catalog->selectCatalog($this->help,'cat_mastitis',null);?>
      </select>
  	</div>
    <div class="form-group m-form__group col-lg-3">
  		<label for="gi_mastalgia">
  			<?=$q_gineco['exp_gineco12']['label'];?>
  		</label>
      <select name="gi_mastalgia" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" title="Seleccione">
        <?=$catalog->selectCatalog($this->help,'cat_mastalgia',null);?>
      </select>
  	</div>
   </div>
    