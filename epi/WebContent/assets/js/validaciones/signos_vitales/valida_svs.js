$("#frm_svs").validate({
  rules: {
    svs_presion_sistolica:{
      required: true,
      min: 0,
      max: 299
    },
    svs_presion_diastolica:{
      required: true,
      min: 0,
      max: 199
    },
    svs_frec_cardiaca:{
      required: true,
      min: 10,
      max: 299
    },
    svs_frec_respiratoria:{
      required: true,
      min: 10,
      max: 39
    },
    svs_peso:{
      required: true,
      min: 20,
      max: 300
    },
    svs_estatura:{
      required: true,
      min: 80,
      max: 299
    },
    svs_temperatura:{
      required: true,
      min: 30,
      max: 39
    },
    svs_imc:{
      required: true
    },
    svs_grasa:{
      required: true,
      min: 0,
      max: 100
    },
    svs_cintura:{
      required: true,
      min: 50,
      max: 199
    },
    svs_cuello:{
      required: true,
      min: 10,
      max: 99
    }
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    guarda_signos_vitales();
  }
});
