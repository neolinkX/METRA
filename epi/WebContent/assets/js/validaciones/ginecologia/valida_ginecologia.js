$("#gi_especifique_hallazgos_clinicos").maxlength({threshold:200,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("select").select2({ width: '70%' });
$('.js-example-basic-multiple').select2();

$("#frm_ginecologia").validate({
  rules: {
    gi_bordes:"required",
    gi_mastitis:"required",
    gi_mastalgia:"required",
    gi_fecha_ur:"required",
    gi_nembarazos:"required",
    gi_nabortos:"required",
    gi_npartos:"required",
    gi_ncesareas:"required",
    gi_edad_gestional:"required",
    gi_factores_riesgo:"required",
    //gi_signos_alarma[]:"required",
    //gi_emergencia_obstetrica[]:"required",
    //gi_planificacion_familiar[]:"required",
    //gi_especifique_hallazgos_clinicos:"required",
    gi_inicio_vida_sexual:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_ginecologia();
  }
});
