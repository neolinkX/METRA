$(".styleSelect2").select2({
    width: '100%'
});

$("#frm_ortopedia").validate({
  rules: {
    or_motivo_solicitud:"required",
    or_diagnostico:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_ortopedia();
  }
});
