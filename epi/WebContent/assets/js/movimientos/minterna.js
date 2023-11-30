function guarda_medicina_interna(){
  var id_episodio = $("#id_episodio_left").val();
  var options = {type:'question',title:'¿Desea guardar Medicina Interna?'}
  modalConfirm(function (){
    $.ajax({
      url:  'minterna/guarda_medicina_interna/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_minterna").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','minterna/evaluacionMinterna/'+id_episodio);
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red MI-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red MI-01');}
    });
  },options);
}
