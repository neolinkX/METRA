$("#neuro_interpretacion").maxlength({threshold:2000,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#frm_neuropsi").validate({
  rules: {
    neuro_otiempo:"required",
    neuro_olugar:"required",
    neuro_opersona:"required",
    neuro_digitos:"required",
    neuro_20_3:"required",
    neuro_visual:"required",
    neuro_cpalabras:"required",
    neuro_msemicompleja:"required",
    neuro_mespontanea:"required",
    mcategorias:"required",
    neuro_mevocacion_semicompleja:"required",
    neuro_ldenominacion:"required",
    neuro_mreconocimiento:"required",
    neuro_lrepeticion:"required",
    neuro_lcomprension:"required",
    neuro_lsemantica:"required",
    neuro_lfonologia:"required",
    neuro_lectura:"required",
    neuro_dictado:"required",
    neuro_copiado:"required",
    neuro_semejanzas:"required",
    neuro_calculo:"required",
    neuro_secuenciacion:"required",
    neuro_mano_derecha:"required",
    neuro_mano_izquierda:"required",
    neuro_malternos:"required",
    neuro_ropuestas:"required",
    neuro_aestudio:"required",
    neuro_ptotal:"required",
    neuro_interpretacion:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_neuropsi();
  }
});
