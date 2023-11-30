$(".styleSelect2").select2({
    width: '100%'
});
//$("#dterapeuticas_obs").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_no_patologicos").validate({
  rules: {
    nopat_alimentacion:"required",
    nopat_baña:"required",
    nopat_bucal:"required",
    nopat_cuartos:"required",
    nopat_personas:"required",
    nopat_sr:"required",
    nopat_neumococo:"required",
    nopat_td:"required",
    nopat_influenza:"required",
    nopat_hepatitis:"required",
    nopat_vacunas:"required",
    nopat_ecivil:"required",
    nopat_religion:"required",
    nopat_escolaridad:"required",
    nopat_hijos:"required"

  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_no_patologicos();
  }
});
