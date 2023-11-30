$("#oto_alteracion_otorrinolaringologica").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#oto_determinacion_evaluacion").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#oto_oido_derecho_audiometria").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#oto_oido_izquierdo_audiometria").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#oto_desviacion_septal_especifique").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_otorrino").validate({
  rules:{
    oto_alteracion_otorrinolaringologica:"required",
    oto_determinacion_evaluacion:"required",
    oto_oido_derecho_audiometria:"required",
    oto_oido_izquierdo_audiometria:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_otorrino();
  }
});
