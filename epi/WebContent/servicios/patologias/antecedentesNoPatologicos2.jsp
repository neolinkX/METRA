<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
   <div class="m-portlet__body">
     <div class="form-group m-form__group">
       <div class="alert m-alert m-alert--default" role="alert">
         <b>ANTECEDENTES NO PATOLÓGICOS</b>
       </div>
     </div>
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            <i class="flaticon-user-ok" aria-hidden="true"></i> Habitos Higienico/Dietéticos
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
            <i class="flaticon-pin" aria-hidden="true"></i> Habitación
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link" data-toggle="tab" href="#m_tabs_6_3" role="tab">
            <i class="flaticon-clipboard" aria-hidden="true"></i> Vacunación
            </a>
         </li>
      </ul>
      <form id="frm_no_patologicos" name="frm_no_patologicos" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
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
         <div class="tab-content">
            <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
               <div class="form-group m-form__group row">
                  <label for="nopat_alimentacion" class="col-4 col-form-label">
                  Alimentación:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_alimentacion" name="nopat_alimentacion" data-placeholder="Seleccione">
                       <?=$catalog->selectCatalog($this->help,'alimentacion',null);?>
                     </select>
                  </div>
               </div>
    
               <div class="form-group m-form__group row">
                  <label for="nopat_baña" class="col-4 col-form-label">
                  Se Baña:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_baña" name="nopat_baña" data-placeholder="Seleccione">
                       <?=$catalog->selectCatalog($this->help,'se_bagna',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_bucal" class="col-4 col-form-label">
                  Higiene Bucal:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_bucal" name="nopat_bucal" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'higiene_bucal',null);?>
                     </select>
                  </div>
               </div>
            </div>
            <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
               <div class="form-group m-form__group row">
                  <label for="nopat_cuartos" class="col-4 col-form-label">
                  No. de Cuartos:
                  </label>
                  <div class="col-4">
                     <input class="form-control m-input" id="nopat_cuartos" name="nopat_cuartos" type="number">
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_personas" class="col-4 col-form-label">
                  No. Personas:
                  </label>
                  <div class="col-4">
                     <input class="form-control m-input" name="nopat_personas" id="nopat_personas" type="text">
                  </div>
               </div>
               <div class="m-form__group form-group row">
                  <label for="nopat_agua" class="col-4 col-form-label">
                  Tiene Agua Potable en su Casa:
                  </label>
                  <div class="col-3" align="center">
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input name="nopat_agua" type="checkbox">
                     <span></span>
                     </label>
                     </span>
                  </div>
               </div>
               <div class="m-form__group form-group row">
                  <label for="nopat_luz" class="col-4 col-form-label">
                  Tiene Luz en su Domicilio:
                  </label>
                  <div class="col-3" align="center">
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input name="nopat_luz" type="checkbox">
                     <span></span>
                     </label>
                     </span>
                  </div>
               </div>
               <div class="m-form__group form-group row">
                  <label for="nopat_drenaje" class="col-4 col-form-label">
                  Cuenta con Drenaje en su Domicilio:
                  </label>
                  <div class="col-3" align="center">
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input name="nopat_drenaje" type="checkbox">
                     <span></span>
                     </label>
                     </span>
                  </div>
               </div>
               <div class="m-form__group form-group row">
                  <label for="nopat_piso" class="col-4 col-form-label">
                  Su Casa Cuenta con Piso de Cemento:
                  </label>
                  <div class="col-3" align="center">
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input name="nopat_piso" type="checkbox">
                     <span></span>
                     </label>
                     </span>
                  </div>
               </div>
               <div class="m-form__group form-group row">
                  <label for="nopat_animales" class="col-4 col-form-label">
                  Convivencia con Animales:
                  </label>
                  <div class="col-3" align="center">
                     <span class="m-switch m-switch--sm m-switch--icon">
                     <label>
                     <input name="nopat_animales" type="checkbox">
                     <span></span>
                     </label>
                     </span>
                  </div>
               </div>
            </div>
            <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
               <div class="form-group m-form__group row">
                  <label for="nopat_sr" class="col-4 col-form-label">
                  SR(Sarampión-Rubeola):
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_sr" name="nopat_sr" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'si_no_des',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_neumococo" class="col-4 col-form-label">
                  Neumococo:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_neumococo" name="nopat_neumococo" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'si_no_des',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_td" class="col-4 col-form-label">
                  TD(Tetanos-Difteria):
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_td" name="nopat_td" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'si_no_des',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_influenza" class="col-4 col-form-label">
                  Influenza:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_influenza" name="nopat_influenza" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'si_no_des',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_hepatitis" class="col-4 col-form-label">
                  Hepatitis:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_hepatitis" name="nopat_hepatitis" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'si_no_des',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_vacunas" class="col-4 col-form-label">
                  Otra (s) Vacunas:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_vacunas" name="nopat_vacunas" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'si_no_des',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_ecivil" class="col-4 col-form-label">
                  Estado Civil:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_ecivil" name="nopat_ecivil" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'estado_civil',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_religion" class="col-4 col-form-label">
                  Religión:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_religion" name="nopat_religion" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'religion',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_escolaridad" class="col-4 col-form-label">
                  Escolaridad:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_escolaridad" name="nopat_escolaridad" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'escolaridad',null);?>
                     </select>
                  </div>
               </div>
               <div class="form-group m-form__group row">
                  <label for="nopat_hijos" class="col-4 col-form-label">
                  Número de Hijos:
                  </label>
                  <div class="m-select2 m-select2--air col-3">
                     <select class="form-control m-select2 styleSelect2" id="nopat_hijos" name="nopat_hijos" data-placeholder="Seleccione">
                        <?=$catalog->selectCatalog($this->help,'numero_hijos',null);?>
                     </select>
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
         </div>
      </form>
   </div>
</div>
<script type="text/javascript">

   $(".styleSelect2").select2({
       width: '100%'
   });

   $("#prueba").change(function() {
     var id_episodio = $("#id_episodio_left").val();
       switch (this.value) {
           case '1':
               carga_archivo('contPrueba','psicologia/beta3/'+id_episodio);
           break;
           case '2':
               carga_archivo('contPrueba','psicologia/shipley/'+id_episodio);
           break;
           case '3':
               carga_archivo('contPrueba','psicologia/neuropsi/'+id_episodio);
           break;
           case '4':
               carga_archivo('contPrueba','psicologia/pai/'+id_episodio);
           break;
       }

   });
</script>
 
<script src="<?=URL_PUBLIC?>assets/js/validaciones/patologicos/valida_no_patologicos.js"></script>
    