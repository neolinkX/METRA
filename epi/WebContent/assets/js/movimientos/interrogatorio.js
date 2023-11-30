function guarda_interrogatorio(){
  var id_episodio = $("#id_episodio_left").val();
  var id_persona = $("#id_persona_left").val();
  var options = {type:'question',title:'¿Desea guardar Interrogatorio?'}
  modalConfirm(function (){
    $.ajax({
      url: url_app + 'historiaclinica/guarda_interrogatorio/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_interrogatorio").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','Historiaclinica/index/'+id_persona+'/'+id_episodio);
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red INT-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red INT-01');}
    });
  },options);
}
