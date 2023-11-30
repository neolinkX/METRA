/*function guarda_odontologia(){
  var options = {type:'question',title:'¿Desea guardar Odontología?'}
  modalConfirm(function (){
    $.ajax({
      //url: 'odontologia/guarda_odontologia/'+$("#id_episodio_left").val(),
      url: 'grdservicios/guardaServicios.jsp',
      type: 'POST',
      data: $("#frm_odontograma").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_servicios_episodio('contenedor_movimientos','odontologia/odontograma/');
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red ODO-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red ODO-01');}
    });
  },options);
}
*/

function guarda_odontologia(){
	var options = {type:'question',title:'\u00BFDesea guardar Odontolog\u00EDa\u003F'}
  modalConfirm(function (){
    $.ajax({
      url: 'grdservicios/guardaServicios.jsp',
      type: 'POST',
      data: $("#frm_odontograma").serialize(),
      dataType: 'json',
      success: function(resp_success){
        swal('Se guard\u00F3 correctamente la informaci\u00F3n!', '', "success");
        get_signosVitales();
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
    });
  },options);
}