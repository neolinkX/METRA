$(".styleSelect2").select2({
    width: '100%'
});
$("#audio_interpretacion_graficas").maxlength({threshold:500,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});

$("#frm_audiologia").validate({
  rules: {
    audio_audiometria_tonal:"required",
    audio_interpretacion_graficas:"required",
  /*  audiologia_derecho_125:"required",
    audiologia_derecho_250:"required",
    audiologia_derecho_500:"required",
    audiologia_derecho_1000:"required",
    audiologia_derecho_2000:"required",
    audiologia_derecho_4000:"required",
    audiologia_derecho_8000:"required",
    audiologia_derecho_12000:"required",
    audiologia_izquierdo_125:"required",
    audiologia_izquierdo_250:"required",
    audiologia_izquierdo_500:"required",
    audiologia_izquierdo_1000:"required",
    audiologia_izquierdo_2000:"required",
    audiologia_izquierdo_4000:"required",
    audiologia_izquierdo_8000:"required",
    audiologia_izquierdo_12000:"required",*/
    cat_audio_via_derecho: "required",
    cat_audio_via_izquierdo: "required"
  },
  invalidHandler:function(e,r){
    var i=$("#m_form_1_msg");
    i.removeClass("m--hide").show(),
    mApp.scrollTo(i,-200);
  },
  submitHandler: function(form) {
    cont =0;
    $.each($("input[data-au_required=true]:checked"),function (){
      cont++;
    });
    if(cont < 16){
        alerta("Alerta","Debes marcar todos los DB del Oído Derecho e Izquierdo");return false;
    }
    guarda_audiologia();
  }
});

function prueba(){
  Highcharts.chart('container2', {

       title: {
           text: ''
       },

       subtitle: {
           text: ''
       },

       xAxis: {

       categories: [
          '125',
          '250',
          '500',
          '1000',
          '2000',
          '4000',
          '8000',
          '12000'
       ],
       crosshair: true
  },

  yAxis: {
  min: -10,
  max: 130,
  tickInterval:+5,
  title: {
      text: 'dB'
  }
},

       legend: {
           layout: 'vertical',
           align: 'right',
           verticalAlign: 'middle'
       },

       plotOptions: {
           series: {
               label: {
                   connectorAllowed: true
               }
           }
       },

       series: [{
           name: 'Oido Derecho',
           data: [15, 36, 44, 84, 72, 83, 85, 120]
       }, {
           name: 'Oido Izquierdo',
           data: [10, 26, 34, 48, 59, 68, 75, 90]
       }],

       responsive: {
           rules: [{
               condition: {
                   maxWidth: 500
               },
               chartOptions: {
                   legend: {
                       layout: 'horizontal',
                       align: 'center',
                       verticalAlign: 'bottom'
                   }
               }
           }]
       }

   });
}
