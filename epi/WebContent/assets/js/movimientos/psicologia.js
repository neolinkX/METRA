function guarda_evaluacion_pp(){
  var options = {type:'question',title:'¿Desea guardar Evaluación de Psiquiatría?'}
  modalConfirm(function (){
    $.ajax({
      url: 'psicologia/guarda_evaluacion_pp/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_epp").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','psicologia/evaluacionPsicologica/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red PS-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PS-01');}
    });
  },options);
}

function guarda_beta(){
  var options = {type:'question',title:'¿Desea guardar Beta III?'}
  modalConfirm(function (){
    $.ajax({
      url: 'psicologia/guarda_beta/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_beta").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','psicologia/pruebasPsicologicas');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red PS-02');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PS-02');}
    });
  },options);
}

function guarda_shipley(){
  var options = {type:'question',title:'¿Desea guardar Shipley-2?'}
  modalConfirm(function (){
    $.ajax({
      url: 'psicologia/guarda_shipley/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_shipley").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','psicologia/pruebasPsicologicas');
          swal('Se guardó correctamente la información!', '', "success");
        }else{
          alerta('Alerta!','Error de conectividad de red PS-03');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PS-03');}
    });
  },options);
}

function guarda_neuropsi(){
  var options = {type:'question',title:'¿Desea guardar Neuropsi?'}
  modalConfirm(function (){
    $.ajax({
      url: 'psicologia/guarda_neuropsi/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_neuropsi").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','psicologia/pruebasPsicologicas');
          swal('Se guardó correctamente la información!', '', "success");
        }else{
          alerta('Alerta!','Error de conectividad de red PS-04');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PS-04');}
    });
  },options);
}

function guarda_pai(){
  var options = {type:'question',title:'¿Desea guardar MMPI-2,PAI?'}
  modalConfirm(function (){
    $.ajax({
      url: 'psicologia/guarda_pai/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_pai").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','psicologia/pruebasPsicologicas');
          swal('Se guardó correctamente la información!', '', "success");
        }else{
          alerta('Alerta!','Error de conectividad de red PS-05');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PS-05');}
    });
  },options);
}
