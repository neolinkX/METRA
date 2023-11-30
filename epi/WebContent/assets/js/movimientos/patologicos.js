function guarda_no_patologicos(){
  var id_episodio = $("#id_episodio_left").val();
  var id_persona = $("#id_persona_left").val();
  var options = {type:'question',title:'¿Desea guardar No Patologicos?'}
  modalConfirm(function (){
    $.ajax({
      url:  'historiaclinica/guarda_no_patologicos/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_no_patologicos").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','Historiaclinica/index/'+id_persona+'/'+id_episodio);
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red PAT-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PAT-01');}
    });
  },options);
}
function guarda_patologicos(){
  var id_episodio = $("#id_episodio_left").val();
  var id_persona = $("#id_persona_left").val();
  var options = {type:'question',title:'¿Desea guardar Patologicos?'}
  modalConfirm(function (){
    $.ajax({
      url:  'historiaclinica/guarda_patologicos/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_patologicos").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','Historiaclinica/index/'+id_persona+'/'+id_episodio);
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red PAT-02');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PAT-02');}
    });
  },options);
}
