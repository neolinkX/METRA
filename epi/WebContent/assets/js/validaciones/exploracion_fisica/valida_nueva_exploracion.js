$("select").select2({ width: '100%' });
carga_datepicker();
$("#hf_causa_madre").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#exp_dorsolumbar").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#exp_especifique_neurotension").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#exp_especifique_dermatomocosis").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_efisica").validate({
  rules: {
    exp_cabeza:"required",
    exp_cuero_cabelludo:"required",
    exp_edad_cronologica:"required",
    exp_piel:"required",
    exp_constitucion_corporal:"required",
    exp_marcha:"required",
    exp_romberg:"required",
    exp_punta_nariz:"required",
    exp_estrabismo:"required",
    exp_motilidad_ocular:"required",
    exp_palpebral:"required",
    exp_palpebral_especifique:"required",
    exp_conjuntivas:"required",
    exp_esclera_derecho:"required",
    exp_esclera_izquierdo:"required",
    exp_fotomotor_derecho:"required",
    exp_fotomotor_izquierdo:"required",
    exp_consencual_derecho:"required",
    exp_consencual_izquierdo:"required",
    exp_nistagmus:"required",
    exp_dolor_derecho:"required",
    exp_dolor_izquierdo:"required",
    exp_secrecion_derecho:"required",
    exp_secrecion_izquierdo:"required",
    exp_oido_externo:"required",
    exp_oido_medio:"required",
    exp_pabellones:"required",
    exp_desviacion_septal:"required",
    exp_fosales_nasales:"required",
    exp_mucosa_nasal:"required",
    exp_meatos:"required",
    exp_secrecion_nasal:"required",
    exp_anteflexión_dorsoflexion:"required",
    exp_simetria_cuello:"required",
    exp_pulsovenoso_derecho:"required",
    exp_pulsocarotideo_izquierdo:"required",
    exp_traquea_posicion:"required",
    exp_glandula_tiroides:"required",
    exp_adenopatias:"required",
    exp_inspeccion:"required",
    exp_movilidad:"required",
    exp_palpacion:"required",
    exp_percusion:"required",
    exp_auscultacion:"required",
    exp_inspeccion:"required",
    exp_palpacion_fremito:"required",
    exp_palpacion_paraesternales:"required",
    exp_percusion:"required",
    exp_auscultacion_primer_ruido:"required",
    exp_auscultacion_segundo_ruido:"required",
    exp_auscultacion_tercer_ruido:"required",
    exp_soplo:"required",
    exp_forma:"required",
    exp_peristalticos:"required",
    exp_hepatomegalia:"required",
    exp_especifique_hernias:"required",
    exp_irritacion_peritoneal:"required",
    exp_palpacion_abdomen:"required",
    exp_ureterales_dolorosos:"required",
    exp_giordano:"required",
    exp_extremidades_toracicas:"required",
    exp_extremidades_superiores:"required",
    exp_extremidades_pelvicas:"required",
    exp_asimetria:"required",
    exp_mobilidad_extremidades:"required",
    exp_tono_muscular:"required",
    exp_fuerza_muscular:"required",
    exp_bicipital_derecho_izquierdo:"required",
    exp_dorsolumbar:"required",
    exp_babinski:"required",
    exp_especifique_neurotension:"required"

  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_exploracionf();
  }
});

function valida_palpebral(){
    if($("#exp_palpebral").val()==1){
      // habilita objeto
  		$("#exp_palpebral_especifique").prop('disabled', false);
    }else{
      // limpia y deshabilita objeto
  		$("#exp_palpebral_especifique").val('');
  		$("#exp_palpebral_especifique").prop('disabled', true);
    }
}

function valida_simetria_ext_toracicas(){
    if($("#exp_extremidades_toracicas").val()==232){
      // habilita objeto
  		$("#exp_extremidades_superiores").prop('disabled', false);
    }else{
      // limpia y deshabilita objeto
  		$("#exp_extremidades_superiores").val('');
  		$("#exp_extremidades_superiores").prop('disabled', true);
    }
}
