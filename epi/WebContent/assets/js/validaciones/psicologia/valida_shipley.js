$("select").select2({ width: '100%' });
$("#shi_vocabulario_des").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#shi_abstraccion_des").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#shi_bloques_des").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#shi_ca_des").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#shi_cb_des").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_shipley").validate({
  rules: {
    shi_vocabulario_natural:"required",
    shi_vocabulario_estandar:"required",
    shi_vocabulario_percentil:"required",
    shi_vocabulario_des:"required",
    shi_abstraccion_natural:"required",
    shi_abstraccion_estandar:"required",
    shi_abstraccion_percentil:"required",
    shi_abstraccion_des:"required",
    shi_bloques_natural:"required",
    shi_bloques_estandar:"required",
    shi_bloques_percentil:"required",
    shi_bloques_des:"required",
    shi_ca_natural:"required",
    shi_ca_estandar:"required",
    shi_ca_percentil:"required",
    shi_ca_des:"required",
    shi_cb_natural:"required",
    shi_cb_estandar:"required",
    shi_cb_percentil:"required",
    shi_cb_des:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_shipley();
  }
});
