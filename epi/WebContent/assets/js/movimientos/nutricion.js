function guarda_nutricion(){
  var options = {type:'question',title:'¿Desea guardar Nutrición?'}
  modalConfirm(function (){
    $.ajax({
      url:  'nutricion/guarda_nutricion/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_nutricion").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','nutricion/evaluacionNutricion/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red NUT-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red NUT-01');}
    });
  },options);
}
