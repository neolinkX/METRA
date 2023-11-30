function guarda_estudios_adicionales(){
  var options = {type:'question',title:'¿Desea guardar Estudios Adicionales?'}
  modalConfirm(function (){
    $.ajax({
      url: 'estudios/guarda_estudios_adicionales/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_estudios_adicionales").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','estudios/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red EST-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EST-01');}
    });
  },options);
}
