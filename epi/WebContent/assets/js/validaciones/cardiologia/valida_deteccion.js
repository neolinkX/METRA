$(".m_selectpicker").selectpicker();
$("#frm_prev_det_oportuna").validate({
  rules: {
    pdo_peso:"required",
    pdo_talla:"required",
    pdo_sobrepeso:"required",
    pdo_porciento_sobrepeso:"required",
    pdo_edad:"required",
    pdo_ant_fam_edad:"required",
    pdo_ant_personal:"required",
    pdo_tabaquismo:"required",
    pdo_tension_ansiedad:"required",
    pdo_obesidad:"required",
    pdo_fre_cardiaca:"required",
    pdo_hipertension:"required",
    pdo_presion_sistolica:"required",
    pdo_presion_diastolica:"required",
    pdo_reposo:"required",
    pdo_diabetes:"required",
    pdo_glucemia:"required",
    pdo_colesterol:"required",
    pdo_trigliceridos:"required",
    pdo_acido_urico:"required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_prevencion_deteccion();
  }
});
