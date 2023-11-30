<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <div class="form-group m-form__group row">
   <div class="col-lg-12">
      <h2>Inspección</h2>
      <div class="m-widget5">
         <div class="m-widget5__item">
            <div class="m-widget5__pic text-center">
               <img class="m-widget7__img" src="<?=URL_PUBLIC.'/img/torax/torax_estenico.png';?>" alt="">
               <div class="text-center">
                  <label class="m-radio m-radio--state-success">
                  <input type="radio" name="exp_inspeccion_torax" value="1">
                  <span></span>
                  </label>
               </div>
            </div>
            <div class="m-widget5__pic text-center">
               <img class="m-widget7__img" src="<?=URL_PUBLIC.'/img/torax/torax_hipostenico.png';?>" alt="">
               <div class="text-center">
                  <label class="m-radio m-radio--state-success">
                  <input type="radio" name="exp_inspeccion_torax" value="2">
                  <span></span>
                  </label>
               </div>
            </div>
            <div class="m-widget5__pic text-center">
               <img class="m-widget7__img" src="<?=URL_PUBLIC.'/img/torax/torax_hiperestenico.png';?>" alt="">
               <div class="text-center">
                  <label class="m-radio m-radio--state-success">
                  <input type="radio" name="exp_inspeccion_torax" value="3">
                  <span></span>
                  </label>
               </div>
            </div>
            <div class="m-widget5__pic text-center">
               <img class="m-widget7__img" src="<?=URL_PUBLIC.'/img/torax/torax_enfisematoso.png';?>" alt="">
               <div class="text-center">
                  <label class="m-radio m-radio--state-success">
                  <input type="radio" name="exp_inspeccion_torax" value="4">
                  <span></span>
                  </label>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
<div class="form-group m-form__group row">
   <div class="col-lg-12">
      <div class="m-widget5">
         <div class="m-widget5__item">

            <div class="m-widget5__pic text-center">
               <img class="m-widget7__img" src="<?=URL_PUBLIC.'/img/torax/torax_aplanamiento.png';?>" alt="">
               <div class="text-center">
                  <label class="m-radio m-radio--state-success">
                  <input type="radio" name="exp_inspeccion_torax" value="5">
                  <span></span>
                  </label>
               </div>
            </div>
            <div class="m-widget5__pic text-center">
               <img class="m-widget7__img" src="<?=URL_PUBLIC.'/img/torax/torax_acanalado.png';?>" alt="">
               <div class="text-center">
                  <label class="m-radio m-radio--state-success">
                  <input type="radio" name="exp_inspeccion_torax" value="6">
                  <span></span>
                  </label>
               </div>
            </div>
            <div class="m-widget5__pic text-center">
               <img class="m-widget7__img" src="<?=URL_PUBLIC.'/img/torax/torax_quilla.png';?>" alt="">
               <div class="text-center">
                  <label class="m-radio m-radio--state-success">
                  <input type="radio" name="exp_inspeccion_torax" value="7">
                  <span></span>
                  </label>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>

<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_movilidad">
      <?=$q_expFis['exp_fis_torax3']['label'];?>
      </label>
      <select name="exp_movilidad" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         	<?=$catalog->selectCatalog($this->help,'simetrico_asimetrico',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_palpacion">
      <?=$q_expFis['exp_fis_torax4']['label'];?>
      </label>
      <select name="exp_palpacion" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
       		<?=$catalog->selectCatalog($this->help,'palpacion',null);?>
      </select>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_percusion_torax">
      <?=$q_expFis['exp_fis_torax5']['label'];?>
      </label>
      <select name="exp_percusion_torax" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         	<?=$catalog->selectCatalog($this->help,'percusion_torax',null);?>
      </select>
   </div>
</div>
<div class="row">
   <div class="form-group m-form__group col-lg-4">
      <label for="exp_auscultacion">
      <?=$q_expFis['exp_fis_torax6']['label'];?>
      </label>
      <select name="exp_auscultacion" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" data-placeholder="Seleccione">
         <?=$catalog->selectCatalog($this->help,'auscultacion_torax',null);?>
      </select>
   </div>
   <!--<div class="form-group m-form__group col-lg-4">
      <label>
      REFERENCIA TORAX ANTERIOR
      </label><br>
      <div class="palpacioncol-lg-12">
         <a href="javascript:;" onclick="modal_torax_anterior(1);" class="m-list-search__result-item">
         <span class="m-list-search__result-item-icon">
         <i class="flaticon-share m--font-primary"></i>
         </span>
         <span class="m-list-search__result-item-text">
         Capturar Punto
         </span>
         </a>
      </div>
      <div class="form-group m-form__group col-lg-12">
         <a href="#" class="m-list-search__result-item" onclick="modal_resultados_torax_anterior(1);">
         <span class="m-list-search__result-item-icon">
         <i class="flaticon-visible m--font-primary"></i>
         </span>
         <span class="m-list-search__result-item-text">
         Mostrar Puntos
         </span>
         </a>
      </div>
   </div>
   <div class="form-group m-form__group col-lg-4">
      <label>
      REFERENCIA TORAX POSTERIOR
      </label><br>
      <div class="col-lg-12">
         <a href="javascript:;" onclick="modal_torax_posterior(1);" class="m-list-search__result-item">
         <span class="m-list-search__result-item-icon">
         <i class="flaticon-share m--font-primary"></i>
         </span>
         <span class="m-list-search__result-item-text">
         Capturar Punto
         </span>
         </a>
      </div>
      <div class="form-group m-form__group col-lg-12">
         <a href="javascript:;" onclick="modal_resultados_torax_posterior(1);" class="m-list-search__result-item">
         <span class="m-list-search__result-item-icon">
         <i class="flaticon-visible m--font-primary"></i>
         </span>
         <span class="m-list-search__result-item-text">
         Mostrar Puntos
         </span>
         </a>
      </div>
   </div> -->
</div>
    