function guarda_teletorax(){
  var options = {type:'question',title:'¿Desea guardar Tele de Torax?'}
  modalConfirm(function (){
    $.ajax({
      url: 'rayosx/guarda_teletorax/'+$('#id_episodio_left').val(),
      type: 'POST',
      data: $("#frm_teletorax").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','rayosx/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red RX-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red RX-01');}
    });
  },options);
}

function guarda_neumologia_rx(){
  var options = {type:'question',title:'¿Desea guardar Neumología?'}
  modalConfirm(function(){
    $.ajax({
      url: 'rayosx/guarda_neumologia/'+$('#id_episodio_left').val(),
      type: 'POST',
      data: $("#frm_neumologia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','rayosx/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red RX-02');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red RX-02');}
    });
  },options);
}
