carga_archivo('interroga_1','historiaclinica/interrogatorioSintomas');
$("#int_tipo_lentes").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#espe_aumento_peso").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#frm_interrogatorio").validate({
  rules: {
    int_leyendo_sentado:"required",
    int_television_dia:"required",
    int_sentado_inactivo:"required",
    int_viajando_transporte:"required",
    int_acostado_descansar:"required",
    int_sentado_platicando:"required",
    int_sentado_comer:"required",
    int_manejando:"required",
    circunferencia_cuello:"required",
    int_imc:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_interrogatorio();
  }
});
