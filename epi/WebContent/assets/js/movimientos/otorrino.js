function guarda_otorrino(){
  var options = {type:'question',title:'¿Desea guardar Otorrinolaringologo?'}
  modalConfirm(function (){
    $.ajax({
      url:  'otorrino/guarda_otorrino/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_otorrino").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','otorrino/evaluacion_otorrino/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red OTO-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red OTO-01');}
    });
  },options);
}
