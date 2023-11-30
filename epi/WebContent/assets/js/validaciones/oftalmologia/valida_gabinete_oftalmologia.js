/*
$('.m-select2').select2({
    placeholder: "Seleccione",
    minimumResultsForSearch: -1
}).on('change', function(){
    var validator;
    validator = $("#frm_gabinete_oftalmologia").validate();
    validator.element($(this));
});
*/
$("#frm_gabinete_oftalmologia").validate({
  rules: {
    gaoftal_cercana_ojo_derecho:"required",
    gaoftal_lejana_ojo_derecho:"required",
    gaoftal_cercana_ojo_izquierdo:"required",
    gaoftal_lejana_ojo_izquierdo:"required",
    gaoftal_intermedia_ojo_derecho:"required",
    gaoftal_intermedia_ojo_izquierdo:"required",
    gaoftal_estereopsis:{
      required: true,
      min: 10,
      max: 199
    },
    gaoftal_campo_visual:"required",
    gaoftal_movimientos_oculares:"required",
    gaoftal_rp_ojo_derecho:"required",
    gaoftal_rp_ojo_izquierdo:"required",
    gaoftal_especifique_patologia:"required",
    gaoftal_exudados_duros:"required",
    gaoftal_test_evaluacion:"required",
    gaoftal_tipo_discromatopsia:"required",
    gaoftal_grado_retinopatia:"required",
    gaoftal_i1t7_17:"required",
    gaoftal_i2t7_29:"required",
    gaoftal_i1t5_e:"required",
    gaoftal_i2t5_74:"required",
    gaoftal_i1t6_58:"required",
    gaoftal_i2t6_56:"required",
    gaoftal_i1t9_12:"required",
    gaoftal_i2t9_42:"required",
    gaoftal_i1t2_2:"required",
    gaoftal_i2t2_8:"required",
    gaoftal_i1t3_5:"required",
    gaoftal_i2t3_45:"required",
    gaoftal_i1t4_6:"required",
    gaoftal_i2t4_0:"required",
    gaoftal_i1t1_8:"required",
    gaoftal_i2t1_29:"required",
    gaoftal_i1t8_18:"required",
    gaoftal_i2t8_15:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_gabinete_oftalmologia();
  }
});

function exudados_duros(){
  if($('input:checkbox[name=gaoftal_presencia_exudados_duros]:checked').val()==1){
    // habilita
    $("#gaoftal_exudados_duros").prop('disabled', false);
  }else{
    // deshabilita
    $('#gaoftal_exudados_duros').prop('disabled', true);
    $('#gaoftal_exudados_duros option[value=""]').prop('selected', true);
  }
  $('.m_selectpicker').selectpicker('refresh');
}

function retinopatia_diabetica(){
  if($('input:checkbox[name=gaoftal_retinopatia_diabetica]:checked').val()==1){
    // habilita
    $("#gaoftal_grado_retinopatia").prop('disabled', false);
  }else{
    // deshabilita
    $('#gaoftal_grado_retinopatia').prop('disabled', true);
    $('#gaoftal_grado_retinopatia option[value=""]').prop('selected', true);
  }
  $('.m_selectpicker').selectpicker('refresh');
}

function cristalino_transparente(){
  if($('input:checkbox[name=gaoftal_cristalino_transparente]:checked').val()==1){
    // habilita
    $("#gaoftal_fondo_rojo").prop('disabled', false);
    $("#gaoftal_hemorragia_vitreo").prop('disabled', false);
    $("#gaoftal_microaneurismas").prop('disabled', false);
    $("#gaoftal_presencia_exudados_duros").prop('disabled', false);

    $("#gaoftal_exudados_blandos").prop('disabled', false);
    $("#gaoftal_proliferacion_neovascular").prop('disabled', false);
    $("#gaoftal_hemorragia_intrarretinal").prop('disabled', false);
    $("#gaoftal_arrosariamiento_venoso").prop('disabled', false);
    $("#gaoftal_retinopatia_diabetica").prop('disabled', false);
  }else{
    // deshabilita
    $("input[type=checkbox]").prop('checked', false);
    $("#gaoftal_fondo_rojo").prop('disabled', true);
    $("#gaoftal_hemorragia_vitreo").prop('disabled', true);
    $("#gaoftal_microaneurismas").prop('disabled', true);
    $("#gaoftal_presencia_exudados_duros").prop('disabled', true);
    $("#gaoftal_exudados_duros").prop('disabled', true);
    $("#gaoftal_exudados_blandos").prop('disabled', true);
    $("#gaoftal_proliferacion_neovascular").prop('disabled', true);
    $("#gaoftal_hemorragia_intrarretinal").prop('disabled', true);
    $("#gaoftal_arrosariamiento_venoso").prop('disabled', true);
    $("#gaoftal_retinopatia_diabetica").prop('disabled', true);
    $("#gaoftal_grado_retinopatia").prop('disabled', true);
    $('#gaoftal_exudados_duros option[value=""]').prop('selected', true);
    $('#gaoftal_grado_retinopatia option[value=""]').prop('selected', true);
  }
  $('.m_selectpicker').selectpicker('refresh');
}

function test_evaluacion(){
  if($('#gaoftal_test_evaluacion').val()=='NORMAL'){
    $("#gaoftal_tipo_discromatopsia").prop('disabled', true);
  }else{
    $("#gaoftal_tipo_discromatopsia").prop('disabled', false);
  }
  $('.m_selectpicker').selectpicker('refresh');
}
