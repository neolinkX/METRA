function guarda_audiologia(){
  var options = {type:'question',title:'¿Desea guardar Audiológia?'}
  modalConfirm(function (){
    $.ajax({
      url: 'audiologia/guarda_audiologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_audiologia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','audiologia/index/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red AUD-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red AUD-01');}
    });
  },options);
}

function genera_grafica(oido_derecho,oido_izquierdo){
  Highcharts.chart('container2', {
       title:{ text: '' },
       subtitle: { text: '' },
       xAxis: { categories: ['125','250','500','1000','2000','4000','8000','12000'],
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
     data: oido_derecho
  }, {
     name: 'Oido Izquierdo',
     data: oido_izquierdo
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
