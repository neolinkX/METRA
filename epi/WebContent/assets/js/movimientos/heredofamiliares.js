function guarda_heredofamiliares(){
  var id_episodio = $("#id_episodio_left").val();
  var id_persona = $("#id_persona_left").val();
  var options = {type:'question',title:'¿Desea guardar Heredofamiliares?'}
  modalConfirm(function (){
    $.ajax({
      url: url_app + 'historiaclinica/guarda_heredofamiliares/'+id_episodio,
      type: 'POST',
      data: $("#frm_heredofamiliares").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','Historiaclinica/index/'+id_persona+'/'+id_episodio);
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red HF-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red HF-01');}
    });
  },options);
}

function get_historia_clinica(){
  var id_episodio = $("#id_episodio_left").val();
  var id_persona = $("#id_persona_left").val();
  carga_archivo('contenedor_movimientos','Historiaclinica/index/'+id_persona+'/'+id_episodio);
}
