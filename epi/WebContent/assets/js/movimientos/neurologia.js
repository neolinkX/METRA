function guarda_neurologia(){
  var id_episodio = $("#id_episodio_left").val();
  var options = {type:'question',title:'¿Desea guardar el Servicio de Neurología?'}
  modalConfirm(function (){
    $.ajax({
      url:  'neurologia/guarda_neurologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_neurologia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','neurologia/evaluacion_neurologia/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red NEURO-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red NEURO-01');}
    });
  },options);
}
