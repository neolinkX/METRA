function guarda_toxicologia(){
  var options = {type:'question',title:'¿Desea guardar Toxicológia?'}
  modalConfirm(function (){
    $.ajax({
      url:  'toxicologia/guarda_toxicologia/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_toxicologia").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','toxicologia/evaluacionToxicologia/'+$("#id_episodio_left").val());
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red TOX-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red TOX-01');}
    });
  },options);
}
