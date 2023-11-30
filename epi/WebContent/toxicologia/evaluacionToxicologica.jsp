<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__head" style="background:#f7f8fa">
   <div class="m-portlet__head-caption">
    <div class="m-portlet__head-title">
       <span class="m-portlet__head-icon">
       <img src="<?=URL_APP?>img/servicios/toxicologia.svg" width="25" class="svg-inject signal-strong">
       </span>
       <h3 class="m-portlet__head-text m--font-primary">
          Servicio de Evaluación Toxicológia NOM024
       </h3>
    </div>
   </div>
   <div class="m-portlet__head-tools">
      <ul class="m-portlet__nav">
         <li class="m-portlet__nav-item">
            <a href="" data-portlet-tool="toggle" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Collapse">
            <i class="la la-plus"></i>
            </a>
         </li>
         <li class="m-portlet__nav-item">
            <a href="#" data-portlet-tool="fullscreen" class="m-portlet__nav-link btn btn-secondary m-btn m-btn--hover-primary m-btn--icon m-btn--icon-only m-btn--pill" title="" data-original-title="Pantalla Completa">
            <i class="la la-expand"></i>
            </a>
         </li>
      </ul>
   </div>
 </div>
 <div class="m-portlet__body">
   <div class="form-group m-form__group">
      <div class="alert m-alert m-alert--default" role="alert">
         Resultados Prueba Presuntiva
      </div>
   </div>
   <form class="m-form m-form--fit m-form--group-seperator-dashed" id="frm_toxicologia" name="frm_toxicologia">

    <div class="m-form__content">
     <div class="m-alert m-alert--icon alert alert-danger m--hide" role="alert" id="m_form_1_msg">
       <div class="m-alert__icon">
         <i class="la la-warning"></i>
       </div>
       <div class="m-alert__text">
         Favor de Verificar los datos.
       </div>
       <div class="m-alert__close">
         <button type="button" class="close" data-close="alert" aria-label="Close"></button>
       </div>
     </div>
    </div>

    <div class="row">
      <div class="col-md-6">
        <div class="m-portlet__body">
          <div class="m-section">
            <div class="m-form__group form-group row">
              <label for="tox_cocaina" class="col-4 col-form-label">
                Cocaína:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_cocaina">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_opiaceos" class="col-4 col-form-label">
                Opiáceos:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_opiaceos">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_anfetaminas" class="col-4 col-form-label">
                Anfetaminas:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_anfetaminas">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_cannabinoides" class="col-4 col-form-label">
                Cannabinoides:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_cannabinoides">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
            <div class="m-form__group form-group row">
              <label for="tox_alcohol" class="col-4 col-form-label">
                Alcohol:
              </label>
              <div class="col-8" align="center">
                <span class="m-switch m-switch--sm m-switch--icon">
                  <label>
                    <input type="checkbox" name="tox_alcohol">
                    <span></span>
                  </label>
                </span>
              </div>
            </div>
          </div>
         </div>
      </div>
      <div class="col-md-6">
         <div class="form-group m-form__group">
            <label for="tox_otras">
            Otras, Especifique
            </label>
            <textarea maxlength="500" class="form-control m-input" id="tox_otras" name="tox_otras" rows="8"></textarea>
         </div>
      </div>
    </div>
    <div class="m-portlet__foot m-portlet__foot--fit">
       <div class="m-form__actions">
          <button type="submit" class="btn btn-primary">
          Guardar
          </button>
          <button type="reset" class="btn btn-secondary">
          Limpiar
          </button>
       </div>
    </div>
</form>
<script src="<?=URL_PUBLIC?>assets/js/validaciones/toxicologia/valida_toxicologia.js"></script>
 