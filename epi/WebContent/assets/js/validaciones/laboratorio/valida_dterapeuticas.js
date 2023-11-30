$("#dterapeuticas_obs").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#dterapeuticas_metodo").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_dterapeuticas").validate({
  rules: {
    dterapeuticas_avalproico:"required",
    dterapeuticas_fenitoina:"required",
    dterapeuticas_carbamacepina:"required",
    dterapeuticas_obs:"required",
    dterapeuticas_metodo:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_dterapeuticas();
  }
});
