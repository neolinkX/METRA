<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <div class="m-portlet m-portlet--default m-portlet--head-solid-bg m-portlet--head-sm" data-portlet="true" id="m_portlet_tools_2">
  <div class="m-portlet__head" style="background:#f7f8fa">
     <div class="m-portlet__head-caption">
       <div class="m-portlet__head-title">
          <span class="m-portlet__head-icon">
          <img src="<?=URL_APP?>img/servicios/oftalmologia.svg" width="25" class="svg-inject signal-strong">
          </span>
          <h3 class="m-portlet__head-text m--font-primary">
             Gabinete Oftalmología
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
      <ul class="nav nav-tabs  m-tabs-line m-tabs-line--2x m-tabs-line--primary" role="tablist">
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#m_tabs_6_1" role="tab" aria-expanded="false">
            Agudeza Visual
            </a>
         </li>
         <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_2" role="tab">
            Valoración de Fondo de Ojo
            </a>
         </li>
       <!--  <li class="nav-item m-tabs__item">
            <a class="nav-link m-tabs__link " data-toggle="tab" href="#m_tabs_6_3" role="tab">
            <!--Test Visión Cromática
            </a>
         </li>-->
      </ul>
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
      <form class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed" name="frm_gabinete_oftalmologia" id="frm_gabinete_oftalmologia">
        <div class="tab-content">
         
           <div class="tab-pane active" id="m_tabs_6_1" role="tabpanel">
          <!--  <?php include "oftalmo_agudeza.php"; ?> -->

 <div class="row">
   <div class="col-md-4 text-right"> 
      <label for="gaoftal_cercana_ojo_derecho" >
     <!--  <?=$q_oftalmo['oftalmo_agudeza1']['label'];?>--> 
     <b>Agudeza Visual Cercana Ojo </b>  <br>
     <b> Derecho sin Corrección:</b> 
      </label> <br>
      <span class="m-form__help">
    <!-- <?=$q_oftalmo['oftalmo_agudeza1']['form_help'];?>-->
     (Resultado solo Decimales: 0.0) 
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_cercana_ojo_derecho" name="gaoftal_cercana_ojo_derecho" data-number_dec="0|1.9">
   </div>
   <div class="col-md-4 text-right">
      <label for="gaoftal_lejana_ojo_derecho">
      <!--  <?=$q_oftalmo['oftalmo_agudeza5']['label'];?> -->
     <b>Agudeza Visual Lejana Ojo </b>  <br>
     <b>  Derecho sin Corrección:</b>           
      </label> <br> 
      <span class="m-form__help">
      <!--<?=$q_oftalmo['oftalmo_agudeza5']['form_help'];?>-->
      (Resultado solo Decimales: 0.0) 
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_lejana_ojo_derecho" name="gaoftal_lejana_ojo_derecho" data-number_dec="0|1.9">
   </div>
</div>


<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
<div class="row">
   <div class="col-md-4 text-right">
      <label for="gaoftal_cercana_ojo_izquierdo">
      <!--<?=$q_oftalmo['oftalmo_agudeza2']['label'];?>-->
        <b>Agudeza Visual Cercana Ojo </b>  <br>
     <b>Izquierdo sin Corrección:</b> 
      </label> <br>
      <span class="m-form__help">
    <!-- <?=$q_oftalmo['oftalmo_agudeza1']['form_help'];?>-->
     (Resultado solo Decimales: 0.0) 
      </span>
   </div>
      <span class="m-form__help">
      <?=$q_oftalmo['oftalmo_agudeza2']['form_help'];?>
      </span>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_cercana_ojo_izquierdo" name="gaoftal_cercana_ojo_izquierdo" data-number_dec="0|1.9">
   </div>
  

   <div class="col-md-4 text-right">
      <label for="gaoftal_lejana_ojo_izquierdo">
     <!-- <?=$q_oftalmo['oftalmo_agudeza6']['label'];?> -->
        <b>Agudeza Visual Lejana Ojo </b>  <br>
     <b>Izquierdo sin Corrección:</b> 
      </label> <br>
      <span class="m-form__help">
     <!--  <?=$q_oftalmo['oftalmo_agudeza6']['form_help'];?>  -->
      (Resultado solo Decimales: 0.0) 
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_lejana_ojo_izquierdo" name="gaoftal_lejana_ojo_izquierdo" data-number_dec="0|1.9">
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-4 text-right">
      <label for="gaoftal_intermedia_ojo_derecho">
      <!--<?=$q_oftalmo['oftalmo_agudeza3']['label'];?>-->
      <b>Agudeza Visual Intermedia Ojo </b>  <br>
     <b>Derecho sin Corrección:</b> 
      </label><br>
      <span class="m-form__help">
     <!-- <?=$q_oftalmo['oftalmo_agudeza3']['form_help'];?>-->
     (Resultado solo Decimales: 0.0) 
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_intermedia_ojo_derecho" name="gaoftal_intermedia_ojo_derecho" data-number_dec="0|1.9">
   </div>
   <div class="col-md-4 text-right">
      <label for="gaoftal_lentes_corregir">
    <!--<?=$q_oftalmo['oftalmo_agudeza7']['label'];?> -->
   <b> ¿Utiliza Lentes para corregir su </b> <br>
      <b> agudeza visual </b>
      </label>
      <span class="m-form__help">
    <!--  <?=$q_oftalmo['oftalmo_agudeza7']['form_help'];?>-->
      </span>
   </div>
   <div class="col-lg-2 text-center">
      <span class="m-switch m-switch--sm m-switch--icon">
      <label>
      <input type="checkbox" name="gaoftal_lentes_corregir" id="gaoftal_lentes_corregir">
      <span></span>
      </label>
      </span>
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-4 text-right">
      <label for="gaoftal_intermedia_ojo_izquierdo">
    <!--  <?=$q_oftalmo['oftalmo_agudeza4']['label'];?>-->
    <b>Agudeza Visual Intermedia Ojo </b><br>
    <b>Izquierdo sin Corrección</b>
      </label><br>
      <span class="m-form__help">
      <!--<?=$q_oftalmo['oftalmo_agudeza4']['form_help'];?>-->
      (Resultado solo Decimales: 0.0)
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_intermedia_ojo_izquierdo" name="gaoftal_intermedia_ojo_izquierdo" data-number_dec="0|1.9">
   </div>
   <div class="col-md-4 text-right">
      <label for="gaoftal_estereopsis">
     <!-- <?=$q_oftalmo['oftalmo_agudeza8']['label'];?>-->
     <b>Sentido de profundidad o</b><br>
     estereopsis:
      </label>
      <span class="m-form__help">
      <!--<?=$q_oftalmo['oftalmo_agudeza8']['form_help'];?>-->
      %
      </span>
   </div>
   <div class="col-md-2 form-group">
      <input type="text" maxlength="3" class="form-control m-input" id="gaoftal_estereopsis" name="gaoftal_estereopsis" data-number>
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-6">
      <h5 class="m--font-primary text-center">Valoración de Campo Visual</h5>
      <div class="row m--margin-top-20">
         <div class="col-md-6 form-group">
            <label for="gaoftal_campo_visual">
          <!--  <?=$q_oftalmo['oftalmo_agudeza9']['label'];?>-->
          Campo Visual:
            </label>
            <input type="text" class="form-control m-input" id="gaoftal_campo_visual" name="gaoftal_campo_visual" data-number_dec="0|100">
            <span class="m-form__help">
           <!-- <?=$q_oftalmo['oftalmo_agudeza9']['form_help'];?>  --> 
           
            </span>
         </div>
         <div class="col-md-6 form-group">
            <label for="gaoftal_movimientos_oculares">
           <!-- <?=$q_oftalmo['oftalmo_agudeza10']['label'];?> -->
           Movimientos Oculares:
            </label>
            <input type="text" class="form-control m-input" id="gaoftal_movimientos_oculares" name="gaoftal_movimientos_oculares" data-number_dec="0|100">
            <span class="m-form__help">
      
            </span>
         </div>
      </div>
   </div>
   <div class="col-md-6">
      <h5 class="m--font-primary text-center">Reflejos Pupilares</h5>
      <div class="row m--margin-top-20">
         <div class="col-lg-6 form-group">
            <label for="gaoftal_rp_ojo_derecho">
         <!--   <?=$q_oftalmo['oftalmo_agudeza11']['label'];?> -->
         Ojo Derecho:
            </label>
            <select id="gaoftal_rp_ojo_derecho" name="gaoftal_rp_ojo_derecho" class="form-control m-bootstrap-select m_selectpicker">
               <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
            </select>
         </div>
         <div class="col-lg-6 form-group">
            <label for="gaoftal_rp_ojo_izquierdo">
          <!--  <?=$q_oftalmo['oftalmo_agudeza12']['label'];?>-->
          Ojo Izquierdo:
            </label>
            <select id="gaoftal_rp_ojo_izquierdo" name="gaoftal_rp_ojo_izquierdo" class="form-control m-bootstrap-select m_selectpicker">
               <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
            </select>
         </div>
      </div>
   </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
   <div class="col-md-6">
      <h5 class="m--font-primary text-center">Enfermedades del Ojo y sus Anexos</h5>
      <div class="row m--margin-top-20">
         <div class="col-md-12 form-group m-form__group">
            <label for="gaoftal_especifique_patologia">
          <!--  <?=$q_oftalmo['oftalmo_agudeza13']['label'];?>--->
          Especifique Patología:
            </label>
            <input type="text" maxlength="300" class="form-control m-input" id="gaoftal_especifique_patologia" name="gaoftal_especifique_patologia">
            <span class="m-form__help">
            <!--<?=$q_oftalmo['oftalmo_agudeza13']['form_help'];?>-->
            </span>
         </div>
      </div>
   </div>
   <div class="col-md-6">
     <label for="">
        <h5 class="m--font-primary">Archivos a Digitalizar</h5>
     </label>
     <div class="col-lg-12 col-md-12 col-sm-12 text-center">
        <div id="div_doc_append">
        </div>
        <div class="m-dropzone dropzone dz-clickable up_doc_global " id="up_oftalmo_drop" data-routeupfile="usuarios/upload_dropzone/documentos_digitales|acta_nac|/upload_avatar">
           <div class="m-dropzone__msg dz-message needsclick">
              <h3 class="m-dropzone__msg-title">
                 Arrastra aquí el documento correspondiente.
              </h3>
              <span class="m-dropzone__msg-desc">
              Actualmente no hay archivos
              <strong>
              no
              </strong>
              se han subido archivos.
              </span>
           </div>
        </div>
     </div>
   </div>
</div>





           </div>
           <div class="tab-pane" id="m_tabs_6_2" role="tabpanel">
             <!-- <?php include "oftalmo_valoracion.php"; ?> -->
             
             <div class="row">
  <div class="col-lg-3">
    <label class="">
    <!--  <?=$q_oftalmo['oftalmo_valoracion14']['label'];?>-->
    Cristalino Transparente:
    </label>
    <div class="col-12 text-center form-group">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" value="1" name="gaoftal_cristalino_transparente" id="gaoftal_cristalino_transparente" onchange="cristalino_transparente();">
          <span></span>
        </label>
      </span>
    </div>
  </div>
  <div class="col-lg-3">
    <label class="">
     <!-- <?=$q_oftalmo['oftalmo_valoracion15']['label'];?>-->
     Presencia de Fondo Rojo:
    </label>
    <div class="col-12 text-center form-group">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="gaoftal_fondo_rojo" id="gaoftal_fondo_rojo" disabled>
          <span></span>
        </label>
      </span>
    </div>
  </div>
  <div class="col-lg-3">
    <label class="">
    <!--  <?=$q_oftalmo['oftalmo_valoracion16']['label'];?> -->
    Presencia de <br>
    Hemorragia en Vítreo:
    </label>
    <div class="col-12 text-center form-group">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="gaoftal_hemorragia_vitreo" id="gaoftal_hemorragia_vitreo" disabled>
          <span></span>
        </label>
      </span>
    </div>
  </div>

  <div class="col-lg-3">
    <label class="">
     <!-- <?=$q_oftalmo['oftalmo_valoracion17']['label'];?>  -->
     Presencia de <br>
     Microaneurismas:
    </label>
    <div class="col-12 text-center form-group">
      <span class="m-switch m-switch--sm m-switch--icon">
        <label>
          <input type="checkbox" name="gaoftal_microaneurismas" id="gaoftal_microaneurismas" disabled>
          <span></span>
        </label>
      </span>
    </div>
  </div>
</div>

<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>

<div class="row">
    <div class="col-lg-3">
      <label class="">
      <!--  <?=$q_oftalmo['oftalmo_valoracion18']['label'];?> -->
      Presencia de Exudados <br>
      Duros
      </label>
      <div class="col-12 text-center form-group">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="gaoftal_presencia_exudados_duros" id="gaoftal_presencia_exudados_duros" value="1" onchange="exudados_duros();" disabled>
            <span></span>
          </label>
        </span>
      </div>
    </div>
    <div class="col-lg-3">
      <div class="col-lg-12 form-group">
         <label for="gaoftal_exudados_duros">
           <!-- <?=$q_oftalmo['oftalmo_valoracion19']['label'];?> -->
           Los exudados Duros:
         </label>
         <select id="gaoftal_exudados_duros" name="gaoftal_exudados_duros" class="form-control m-bootstrap-select m_selectpicker" data-live-search="true" title="Seleccione" disabled>
            <?=$catalog->selectCatalog($this->help,'exudados_duros',null);?>
         </select>
      </div>
    </div>
    <div class="col-lg-3">
      <label class="">
       <!-- <?=$q_oftalmo['oftalmo_valoracion20']['label'];?>-->
     Presencia de Exudados <br>
     Blandos:
      </label>
      <div class="col-12 text-center form-group">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="gaoftal_exudados_blandos" id="gaoftal_exudados_blandos" disabled>
            <span></span>
          </label>
        </span>
      </div>
    </div>
    <div class="col-lg-3">
      <label class="">
      <!--  <?=$q_oftalmo['oftalmo_valoracion21']['label'];?>-->
      Proliferación<br>
      Neovascular:
      </label>
      <div class="col-12 text-center form-group">
        <span class="m-switch m-switch--sm m-switch--icon">
          <label>
            <input type="checkbox" name="gaoftal_proliferacion_neovascular" id="gaoftal_proliferacion_neovascular" disabled>
            <span></span>
          </label>
        </span>
      </div>
    </div>
 </div>
<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
 <div class="row">
   <div class="col-lg-3">
     <label class="">
      <!-- <?=$q_oftalmo['oftalmo_valoracion22']['label'];?> -->
      Hemorragia <br>
      Intrarretinal:
     </label>
     <div class="col-12 text-center form-group">
       <span class="m-switch m-switch--sm m-switch--icon">
         <label>
           <input type="checkbox" name="gaoftal_hemorragia_intrarretinal" id="gaoftal_hemorragia_intrarretinal" disabled>
           <span></span>
         </label>
       </span>
     </div>
   </div>
   <div class="col-lg-3">
     <label class="">
      <!-- <?=$q_oftalmo['oftalmo_valoracion23']['label'];?> -->
      Arrosariamiento <br>
      Venoso:
     </label>
     <div class="col-12 text-center form-group">
       <span class="m-switch m-switch--sm m-switch--icon">
         <label>
           <input type="checkbox" name="gaoftal_arrosariamiento_venoso" id="gaoftal_arrosariamiento_venoso" disabled>
           <span></span>
         </label>
       </span>
     </div>
   </div>
   <div class="col-lg-3">
     <div class="col-lg-12 form-group">
        <label for="gaoftal_test_evaluacion">
       <!--    <?=$q_oftalmo['oftalmo_valoracion26']['label'];?> -->
       Test de Evaluación<br>
       (Visión Cromática):
        </label>
        <select id="gaoftal_test_evaluacion" name="gaoftal_test_evaluacion" class="form-control m-bootstrap-select m_selectpicker" onchange="test_evaluacion();">
           <?=$catalog->selectCatalog($this->help,'normal_anormal',null);?>
        </select>
        <span class="m-form__help">
          <?=$q_oftalmo['oftalmo_valoracion26']['form_help'];?>
        </span>
     </div>
   </div>
   <div class="col-lg-3">
     <div class="col-lg-12 form-group">
        <label for="gaoftal_tipo_discromatopsia">
     <!--      <?=$q_oftalmo['oftalmo_valoracion27']['label'];?> -->
     Retinopatía Diabética:
        </label>
         <div class="col-12 text-center form-group">
       <span class="m-switch m-switch--sm m-switch--icon">
         <label>
       <input type="checkbox" value="1" name="gaoftal_retinopatia_diabetica" id="gaoftal_retinopatia_diabetica" onchange="retinopatia_diabetica();" disabled>
           <span></span>
         </label>
       </span>
     </div>

        <!--<select id="gaoftal_tipo_discromatopsia" name="gaoftal_tipo_discromatopsia" class="form-control m-bootstrap-select m_selectpicker">--> 
         <!--  <?=$catalog->selectCatalog($this->help,'discromatopsia',null);?>-->
        <!--</select>-->
        <span class="m-form__help">
          <?=$q_oftalmo['oftalmo_valoracion27']['form_help'];?>
        </span>
     </div>
   </div>
 </div>
<div class="m-form__seperator m-form__seperator--dashed m-form__seperator--space"></div>
 <div class="row">
   <div class="col-lg-3">
     <label class="">
    <!--   <?=$q_oftalmo['oftalmo_valoracion24']['label'];?> -->
   
     </label>
     <div class="col-12 text-center form-group">
       <span class="m-switch m-switch--sm m-switch--icon">
         <label>
         Grado de Retinopatía:
        <select id="gaoftal_grado_retinopatia" name="gaoftal_grado_retinopatia" class="form-control m-bootstrap-select m_selectpicker" disabled>
           <?=$catalog->selectCatalog($this->help,'retinopatia',null);?>
        </select>
           <span></span>
         </label>
       </span>
     </div>
   </div>

   <div class="col-lg-4">
     <div class="col-lg-12 form-group">
        <label for="gaoftal_grado_retinopatia">
        <!--   <?=$q_oftalmo['oftalmo_valoracion25']['label'];?>-->
        </label>
         
     </div>
   </div>
 </div>
          
           </div>
           <div class="tab-pane" id="m_tabs_6_3" role="tabpanel">
             <!--  <?php include "oftalmo_vision_cromatica.php"; ?>  -->
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
   </div>
</div>
<script type="text/javascript">
   $('.m_selectpicker').selectpicker();
   $(document).ready(function() {
     var num_exp = $("#id_numexp_left").val();
     $("#up_oftalmo_drop").dropzone({
       url: "usuarios/upload_dropzone/examenes|gabinete_oftalmo|"+num_exp+"/upload_avatar",
       paramName: "file",
       maxFiles: 5,
       maxFilesize: 5, // MB
       acceptedFiles: "image/*",
       accept: function(file, done) {
           //console.log(file);
           done();
       },
       init: function() {
         this.on("success", function(statics,file) {
           var img = file.split("|");
           $( "#div_doc_append" ).append("<input type='hidden' name='archivo_anexos[]' value='examenes/gabinete_oftalmo/"+num_exp+"/"+img[1]+"'>");
         });
       }
      });
   });
</script>
 <script src="http://10.33.143.52:7001/epi/assets/js/validaciones/oftalmologia/valida_gabinete_oftalmologia.js"></script>
   