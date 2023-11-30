$("#microalbumina_obs").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#microalbumina_otros").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#microalbumina_metodo").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_qclinica").validate({
  rules: {
    /*qclinica_glucosa:"required",
    qclinica_urea:"required",
    qclinica_creatinina:"required",
    qclinica_aurico:"required",
    qclinica_colesterol:"required",
    qclinica_trigliceridos:"required",
    qclinica_hdl:"required",
    qclinica_ldl:"required",
    qclinica_iaterogenico:"required",
    qclinica_hba1c:"required",
    qclinica_pcreactiva:"required",
    qclinica_proteina:"required",
    qclinica_albumina:"required",
    qclinica_bilirrubina:"required",
    qclinica_bdirecta:"required",
    qclinica_bindirecta:"required",
    qclinica_alcalina:"required",
    qclinica_ast:"required",
    qclinica_alt:"required",
    qclinica_ck:"required",
    qclinica_ckmb:"required",
    electrolitos_sodio:"required",
    electrolitos_potasio:"required",
    electrolitos_cloro:"required",
    tolerancia_gpostcarga:"required",
    tolerancia_orina:"required",
    microalbumina:"required",
    microalbumina_obs:"required",
    microalbumina_otros:"required",
    microalbumina_metodo:"required"*/
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_qclinica();
  }
});
