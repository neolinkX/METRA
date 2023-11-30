$(".styleSelect2").select2({
    width: '100%'
});
$('.js-example-basic-multiple').select2();
$("#frm_patologicos").validate({
  rules: {
    pat_alergias:"required",
    pat_tmemoria:"required",pat_copas_dia:"required",
    pat_copas_ocasion:"required",
    pat_parar_beber:"required",
    pat_dejar_hacer:"required",
    pat_frecuente_bebio:"required",
    pat_remordimiento_bebio:"required",
    pat_olvido_bebio:"required",
    pat_lesionado_bebio:"required",
    pat_preocupado_forma_bebe:"required",
    pat_epulmunar:"required",
    pat_atransfusionales:"required",
    pat_atransfusionales_tratamiento:"required",
    pat_diabetesm_tratamiento:"required",
    pat_insulinodependiente_tratamiento:"required",
    pat_crisis:"required",
    pat_oncologicos:"required",
    pat_oncologicos_tratamiento:"required",
    pat_cardiopatia_tratamiento:"required",
    pat_bebidas_alcoholicas:"required",
    pat_copas_dia:"required",
    pat_copas_ocasion:"required",
    pat_parar_beber:"required",
    pat_dejar_hacer:"required",
    pat_frecuente_bebio:"required",
    pat_tmemoria_especifique:"required",
    pat_remordimiento_bebio:"required",
    pat_olvido_bebio:"required",
    pat_lesionado_bebio:"required",
    pat_preocupado_forma_bebe:"required",
    pat_cigarrillos_dia:"required",
    pat_especifique_consumo_drogas:"required",
    pat_cigarrillos_temprano:"required",
    pat_cigarrillos_despierta:"required",
    pat_cigarrillos_omitir:"required",
    pat_fumar_prohibido:"required",
    pat_fumar_enfermo:"required",
    pat_consumo_drogas:"required",
    pat_esp_cons_drogas:"required",
    pat_tipo_droga:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_patologicos();
  }
});

function valida_bebidas_alcoholicas(){
  if($('#pat_bebidas_alcoholicas').val()==151){
    $("#pat_copas_dia").prop('disabled', true);
    $("#pat_copas_ocasion").prop('disabled', true);
    $("#pat_parar_beber").prop('disabled', true);
    $("#pat_dejar_hacer").prop('disabled', true);
    $("#pat_frecuente_bebio").prop('disabled', true);
    $("#pat_remordimiento_bebio").prop('disabled', true);
    $("#pat_olvido_bebio").prop('disabled', true);
    $("#pat_lesionado_bebio").prop('disabled', true);
    $("#pat_preocupado_forma_bebe").prop('disabled', true);
  }else{
    $("#pat_copas_dia").prop('disabled', false);
    $("#pat_copas_ocasion").prop('disabled', false);
    $("#pat_parar_beber").prop('disabled', false);
    $("#pat_dejar_hacer").prop('disabled', false);
    $("#pat_frecuente_bebio").prop('disabled', false);
    $("#pat_remordimiento_bebio").prop('disabled', false);
    $("#pat_olvido_bebio").prop('disabled', false);
    $("#pat_lesionado_bebio").prop('disabled', false);
    $("#pat_preocupado_forma_bebe").prop('disabled', false);
  }
}

function validaciones_fuma(){
  if($('input:checkbox[name=pat_fuma]:checked').val()==1){
    // habilita
    $("#pat_cigarrillos_dia").prop('disabled', false);
    $("#pat_cigarrillos_temprano").prop('disabled', false);
    $("#pat_cigarrillos_despierta").prop('disabled', false);
    $("#pat_cigarrillos_omitir").prop('disabled', false);
    $("#pat_fumar_prohibido").prop('disabled', false);
    $("#pat_fumar_enfermo").prop('disabled', false);
  }else{
    // deshabilita
    $("#pat_cigarrillos_dia").prop('disabled', true);
    $("#pat_cigarrillos_temprano").prop('disabled', true);
    $("#pat_cigarrillos_despierta").prop('disabled', true);
    $("#pat_cigarrillos_omitir").prop('disabled', true);
    $("#pat_fumar_prohibido").prop('disabled', true);
    $("#pat_fumar_enfermo").prop('disabled', true);
  }
}

function valida_consumo_drogas(){
  if($('#pat_consumo_drogas').val()==213){
    $("#pat_especifique_consumo_drogas").show();
    $("#pat_esp_cons_drogas").prop('disabled', false);
  }else{
    $("#pat_especifique_consumo_drogas").hide();
    $("#pat_esp_cons_drogas").prop('disabled', true);
  }
}

function validacion_droga(){
  if($('input:checkbox[name=pat_sigue_consumiendo]:checked').val()==1){
    // habilita
    $("#pat_tipo_droga").prop('disabled', false);
    $("#pat_12meses").prop('disabled', false);
    $("#pat_uso_estimulacion").prop('disabled', false);
    $("#pat_menos_efecto").prop('disabled', false);
    $("#pat_mas_cantidad").prop('disabled', false);
    $("#pat_necesidad").prop('disabled', false);
    $("#pat_consumir_desesperadamente").prop('disabled', false);
    $("#pat_suspender_consumo").prop('disabled', false);
    $("#pat_dificultad_consumo").prop('disabled', false);
  }else{
    // deshabilita
    $("#pat_tipo_droga").prop('disabled', true);
    $("#pat_12meses").prop('disabled', true);
    $("#pat_uso_estimulacion").prop('disabled', true);
    $("#pat_menos_efecto").prop('disabled', true);
    $("#pat_mas_cantidad").prop('disabled', true);
    $("#pat_necesidad").prop('disabled', true);
    $("#pat_consumir_desesperadamente").prop('disabled', true);
    $("#pat_suspender_consumo").prop('disabled', true);
    $("#pat_dificultad_consumo").prop('disabled', true);
  }
}

function historia_alergica(){
  if($('#pat_alergias').val()==107){
    // deshabilita
    $("#pat_alergias_des").prop('disabled', true);
  }else{
    // habilita
    $("#pat_alergias_des").prop('disabled', false);
  }
}

function tmemoria(){
  if($('#pat_tmemoria').val()==118){
    // deshabilita
    $("#pat_tmemoria_especifique").prop('disabled', true);
  }else{
    // habilita
    $("#pat_tmemoria_especifique").prop('disabled', false);
  }
}
