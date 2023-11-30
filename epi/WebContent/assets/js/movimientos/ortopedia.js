function guarda_ortopedia(){
  var options = {type:'question',title:'¿Desea guardar el Servicio de Ortopedia?'}
  modalConfirm(function (){
    $.ajax({
      url:  'ortopedia/guarda_ortopedia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_ortopedia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','ortopedia/evaluacion_ortopedia/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red ORTO-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red ORTO-01');}
    });
  },options);
}
