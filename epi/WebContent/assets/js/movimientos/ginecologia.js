function guarda_ginecologia(){
  var id_episodio = $("#id_episodio_left").val();
  var id_persona = $("#id_persona_left").val();
  var options = {type:'question',title:'¿Desea guardar Ginecológia?'}
  modalConfirm(function (){
    $.ajax({
      url:  'historiaclinica/guarda_ginecologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_ginecologia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','historiaclinica/index/'+id_persona+'/'+id_episodio);
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red GIN-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red GIN-01');}
    });
  },options);
}
