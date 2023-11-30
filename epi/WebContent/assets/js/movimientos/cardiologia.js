function guarda_evaluacion_cardiologia(){

  var options = {type:'question',title:'¿Desea guardar Evaluación Cardiológica?'}
  modalConfirm(function (){
    $.ajax({
      url: 'cardiologia/guarda_evaluacion_cardiologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_ecardio").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','cardiologia/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red CAR-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red CAR-01');}
    });
  },options);
}

function guarda_prevencion_deteccion(){
  var options = {type:'question',title:'¿Desea guardar Prevención y Detección?'}
  modalConfirm(function (){
    $.ajax({
      url: 'cardiologia/guarda_prevencion_deteccion/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_prev_det_oportuna").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','cardiologia/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red CAR-02');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red CAR-02');}
    });
  },options);
}
