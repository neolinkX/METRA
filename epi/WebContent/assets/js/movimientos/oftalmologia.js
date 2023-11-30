function guarda_evaluacion_oftalmologia(){
  var options = {type:'question',title:'¿Desea guardar Evaluación Oftalmológia?'}
  modalConfirm(function (){
    $.ajax({
      url: 'oftalmologia/guarda_evaluacion_oftalmologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_evaluacion_oftalmo").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','oftalmologia/formEvaluacionOftalmologica/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red OFTA-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red OFTA-01');}
    });
  },options);
}

function guarda_gabinete_oftalmologia(){
  var options = {type:'question',title:'¿Desea guardar Gabinete Oftalmológia?'}
  modalConfirm(function (){
    $.ajax({
      url: 'oftalmologia/guarda_gabinete_oftalmologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_gabinete_oftalmologia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','oftalmologia/formInicioOftalmo/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red OFTA-02');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red OFTA-02');}
    });
  },options);
}

function get_evaluacion_oftalmologica(){
  var episodio = $("#id_episodio_left").val();
  carga_archivo('contenedor_movimientos','oftalmologia/formEvaluacionOftalmologica/'+episodio);
}

function get_gabinete_oftalmologia(){
  var episodio = $("#id_episodio_left").val();
  carga_archivo('contenedor_movimientos','oftalmologia/formInicioOftalmo/'+episodio);
}
