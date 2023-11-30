function guarda_neumologia(){
  var id_episodio = $("#id_episodio_left").val();
  var options = {type:'question',title:'¿Desea guardar el Servicio de Neumología?'}
  modalConfirm(function (){
    $.ajax({
      url:  'neumologia/guarda_neumologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_neumologia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','neumologia/evaluacion_neumologia/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red NEUMO-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red NEUMO-01');}
    });
  },options);
}
