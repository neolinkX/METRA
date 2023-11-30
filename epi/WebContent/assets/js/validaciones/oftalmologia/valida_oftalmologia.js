var e = $("#m_portlet_tools_2").mPortlet();
e.on("beforeCollapse", function(e) {
    setTimeout(function() {
        //toastr.info("Before collapse event fired!")
    }, 100)
}), e.on("afterCollapse", function(e) {
    setTimeout(function() {
        //toastr.warning("Before collapse event fired!")
    }, 2e3)
}), e.on("beforeExpand", function(e) {
    setTimeout(function() {
        //toastr.info("Before expand event fired!")
    }, 100)
}), e.on("afterExpand", function(e) {
    setTimeout(function() {
        //toastr.warning("After expand event fired!")
    }, 2e3)
}), e.on("beforeRemove", function(e) {
    return toastr.info("Before remove event fired!"), confirm("Are you sure to remove this portlet ?")
}), e.on("afterRemove", function(e) {
    setTimeout(function() {
        //toastr.warning("After remove event fired!")
    }, 2e3)
}), e.on("reload", function(e) {
    toastr.info("Leload event fired!"), mApp.block(e.getSelf(), {
        overlayColor: "#000000",
        type: "spinner",
        state: "brand",
        opacity: .05,
        size: "lg"
    }), setTimeout(function() {
        mApp.unblock(e.getSelf())
    }, 2e3)
})
$(".m_selectpicker").selectpicker();
carga_datepicker();

$("#oftal_especifique_otra").maxlength({threshold:2000,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#oftal_determinacion").maxlength({threshold:2000,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#oftal_diagnostico_determinaciones").maxlength({threshold:2000,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_evaluacion_oftalmo").validate({
  rules: {
    oftal_agudeza_visual_derecho_cercana:"required",
    oftal_agudeza_visual_izquierda_cercana:"required",
    oftal_agudeza_visual_derecho_intermedia:"required",
    oftal_agudeza_visual_izquierda_corregida:"required",
    oftal_agudeza_visual_derecho_lejana:"required",
    oftal_agudeza_visual_izquierda_lejana:"required",
    oftal_campo_visual:"required",
    oftal_especifique_otra:"required",
    oftal_determinacion:"required",
    oftal_diagnostico_determinaciones:"required",
    oftal_vision_estereopsis:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_evaluacion_oftalmologia();
  }
});
