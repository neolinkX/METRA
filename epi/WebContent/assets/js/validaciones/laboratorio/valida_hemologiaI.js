$("#hem_examenes_practicados").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#hem_observaciones").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
$("#hem_metodo").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_hematologiaI").validate({
  rules: {
    /*hem_sedimentacion_globular: "required",
    hem_granulocitos: "required",
    hem_examenes_practicados: "required",
    hem_leucocitos: "required",
    hem_eritrocitos: "required",
    hem_hemoglobina: "required",
    hem_hematocrito: "required",
    hem_mvc: "required",
    hem_cmhb: "required",
    hem_mch: "required",
    hem_ade: "required",
    hem_neutrofilos: "required",
    hem_linfocitos: "required",
    hem_monocitos: "required",
    hem_eosinofilos: "required",
    hem_basofilos: "required",
    hem_neutrofilos10: "required",
    hem_linfocitos10: "required",
    hem_monocitos10: "required",
    hem_eosinofilos10: "required",
    hem_basofilos10: "required",
    hem_plaquetas: "required",
    hem_vpm: "required",
    hem_plaquetocrito: "required",
    hem_adp: "required",
    hem_observaciones: "required",
    hem_metodo: "required" */
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_hematologiaI();
  }
});
