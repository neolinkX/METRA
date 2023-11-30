$("select").select2({ width: '100%' });

$("#frm_beta").validate({
  rules: {
    beta_subprueba1:"required",
    beta_subprueba12:"required",
    beta_subprueba2:"required",
    beta_subprueba22:"required",
    beta_subprueba3:"required",
    beta_subprueba33:"required",
    beta_subprueba4:"required",
    beta_subprueba44:"required",
    beta_subprueba5:"required",
    beta_subprueba55:"required",
    beta_suma:"required",
    beta_percentil:"required",
    beta_ci:"required",
    beta_interpretacion:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_beta();
  }
});
