function guarda_exploracionf(){
  var id_episodio = $("#id_episodio_left").val();
  var id_persona = $("#id_persona_left").val();
  var options = {type:'question',title:'¿Desea guardar Exploración Física?'}
  modalConfirm(function (){
    $.ajax({
      url: 'exploracionfisica/guarda_exploracionf/'+id_episodio,
      type: 'POST',
      data: $("#frm_efisica").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','Historiaclinica/index/'+id_persona+'/'+id_episodio);
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red EXP-01');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-01');}
    });
  },options);
}


function modal_extremidades(id_historia_exploracion){
    //id_usuario = $(this).attr('data-function');
		$.ajax({
			url: 'exploracionfisica/modalExtremidades/'+id_historia_exploracion,
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){

              $('.zoom').magnify({
                afterLoad: function(){
                  console.log('Zoom activo');
                }
              });

              $('area').on('click', function(){
                $('#ext_sel_apm').html($(this).attr('data-iden'));
              });

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-02');}
		});
}

function modal_mano_derecha(id_historia_exploracion){
    //id_usuario = $(this).attr('data-function');
		$.ajax({
			url: 'exploracionfisica/modalManoDerecha/'+id_historia_exploracion,
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
          $(modal).modal().on('shown.bs.modal',function(){

              $('.zoom').magnify({
                afterLoad: function() {
                  console.log('Zoom activo');
                }
              });

              $('area').on('click', function() {
                $('#mander_amp').html($(this).attr('data-iden'));
              });

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-03');}
		});
}

function modal_mano_izquierda(id_historia_exploracion){
    //id_usuario = $(this).attr('data-function');
		$.ajax({
			url: 'exploracionfisica/modalManoIzquierda/'+id_historia_exploracion,
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
          $(modal).modal().on('shown.bs.modal',function(){

              $('.zoom').magnify({
                afterLoad: function() {
                  console.log('Zoom activo');
                }
              });

              $('area').on('click', function() {
                $('#manizq_amp').html($(this).attr('data-iden'));
              });

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-04');}
		});
}


function guarda_extremidades(id_historia_exploracion){
  var punto = $('#ext_sel_apm').text();
  var observaciones = $("#observaciones").val();

  if(punto==""){ alerta('Alerta!','Olvido ingresar un punto'); return false; }
  if(observaciones==""){ alerta('Alerta!','Olvido ingresar observaciones'); return false; }
  else{
    $.ajax({
      url: 'exploracionfisica/guarda_puntos_extremidades/'+id_historia_exploracion+'/'+punto+'/'+observaciones,
      dataType: 'html',
        success: function(resp_success){
          if(resp_success.resp=='true'){
            $('#myModal').modal('toggle');
            swal('Se guardó correctamente!', '', "success");
          }else{
            alerta('Alerta!','Error de conectividad de red EXP-5');
          }
        },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-5');}
    });
  }
}

function guarda_mano_derecha(id_historia_exploracion){
  var punto = $('#mander_amp').text();
  var observaciones = $("#observaciones").val();

  if(punto==""){ alerta('Alerta!','Olvido ingresar un punto'); return false; }
  if(observaciones==""){ alerta('Alerta!','Olvido ingresar observaciones'); return false; }
  else{
    $.ajax({
      url: 'exploracionfisica/guarda_puntosmano_derecha/'+id_historia_exploracion+'/'+punto+'/'+observaciones,
      dataType: 'html',
        success: function(resp_success){
          if(resp_success.resp=='true'){
            $('#myModal').modal('toggle');
            swal('Se guardó correctamente!', '', "success");
          }else{
            alerta('Alerta!','Error de conectividad de red EXP-6');
          }
        },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-6');}
    });
  }
}

function guarda_mano_izquierda(id_historia_exploracion){
  var punto = $('#manizq_amp').text();
  var observaciones = $("#observaciones").val();

  if(punto==""){ alerta('Alerta!','Olvido ingresar un punto'); return false; }
  if(observaciones==""){ alerta('Alerta!','Olvido ingresar observaciones'); return false; }
  else{
    $.ajax({
      url: 'exploracionfisica/guarda_puntosmano_izquierda/'+id_historia_exploracion+'/'+punto+'/'+observaciones,
      dataType: 'html',
        success: function(resp_success){
          if(resp_success.resp=='true'){
            $('#myModal').modal('toggle');
            swal('Se guardó correctamente!', '', "success");
          }else{
            alerta('Alerta!','Error de conectividad de red EXP-7');
          }
        },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-7');}
    });
  }
}

function modal_abdomen(id_historia_exploracion){
    $.ajax({
			url: 'exploracionfisica/modalAbdomen/'+id_historia_exploracion,
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){

              $('.zoom').magnify({
                afterLoad: function() {
                  console.log('Zoom activo');
                }
              });

              $('area').on('click', function(){
                $('#abdomen_sel').html($(this).attr('data-iden'));
              });

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-08');}
		});
}

function guarda_abdomen(id_historia_exploracion){
  var punto = $('#abdomen_sel').text();
  var observaciones = $("#observaciones").val();

  if(punto==""){ alerta('Alerta!','Olvido ingresar un punto'); return false; }
  if(observaciones==""){ alerta('Alerta!','Olvido ingresar observaciones'); return false; }
  else{
    $.ajax({
      url: 'exploracionfisica/guarda_puntos_abdomen/'+id_historia_exploracion+'/'+punto+'/'+observaciones,
      dataType: 'html',
        success: function(resp_success){
          if(resp_success.resp=='true'){
            $('#myModal').modal('toggle');
            swal('Se guardó correctamente!', '', "success");
          }else{
            alerta('Alerta!','Error de conectividad de red EXP-12');
          }
        },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-12');}
    });
  }
}


function modal_torax_anterior(id_historia_exploracion) {
  //  id_usuario = $(this).attr('data-function');
  	$.ajax({
			url: 'exploracionfisica/modalToraxAnterior/'+id_historia_exploracion,
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){

              $('.zoom').magnify({
                afterLoad: function() {
                  console.log('Zoom activo');
                }
              });

              $('area').on('click', function() {
                $('#torax_ant_sel').html($(this).attr('data-iden'));
              });

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-10');}
		});
}

function modal_torax_posterior(id_historia_exploracion){
    //id_usuario = $(this).attr('data-function');
		$.ajax({
			url: 'exploracionfisica/modalToraxPosterior/'+id_historia_exploracion,
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){

              $('.zoom').magnify({
                afterLoad: function(){
                  console.log('Zoom activo');
                }
              });

              $('area').on('click', function() {
                $('#torax_pos_sel').html($(this).attr('data-iden'));
              });

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-11');}
		});
}

function guarda_toraxpuntos_anterior(id_historia_exploracion){

      var punto = $('#torax_ant_sel').text();
      var observaciones = $("#observaciones").val();

      if(punto==""){ alerta('Alerta!','Olvido ingresar un punto'); return false; }
      if(observaciones==""){ alerta('Alerta!','Olvido ingresar observaciones'); return false; }
      else{
        $.ajax({
    			url: 'exploracionfisica/guarda_puntotorax_anterior/'+id_historia_exploracion+'/'+punto+'/'+observaciones,
    			dataType: 'html',
    				success: function(resp_success){
              if(resp_success.resp=='true'){
                $('#myModal').modal('toggle');
                swal('Se guardó correctamente!', '', "success");
          	  }else{
                alerta('Alerta!','Error de conectividad de red EXP-12');
              }
    				},
    			  error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-12');}
    		});
      }
}

function guarda_toraxpuntos_posterior(id_historia_exploracion){

      var punto = $('#torax_pos_sel').text();
      var observaciones = $("#observaciones").val();

      if(punto==""){ alerta('Alerta!','Olvido ingresar un punto'); return false; }
      if(observaciones==""){ alerta('Alerta!','Olvido ingresar observaciones'); return false; }
      else{
        $.ajax({
    			url: 'exploracionfisica/guarda_puntotorax_posterior/'+id_historia_exploracion+'/'+punto+'/'+observaciones,
    			dataType: 'html',
    				success: function(resp_success){
              if(resp_success.resp=='true'){
                $('#myModal').modal('toggle');
                swal('Se guardó correctamente!', '', "success");
          	  }else{
                alerta('Alerta!','Error de conectividad de red EXP-13');
              }
    				},
    			  error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-13');}
    		});
      }
}

function modal_resultados_torax_anterior(id_historia_exploracion){
  $.ajax({
    url: 'exploracionfisica/modalToraxAnterior_resultados/'+id_historia_exploracion,
    dataType: 'html',
      success: function(resp_success){
        var modal =  resp_success;
        $(modal).modal().on('shown.bs.modal',function(){

        }).on('hidden.bs.modal',function(){
          $(this).remove();
        });
      },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-14');}
  });
}

function modal_resultados_extremidades(id_historia_exploracion){
  $.ajax({
    url: 'exploracionfisica/modalextremidades_resultados/'+id_historia_exploracion,
    dataType: 'html',
      success: function(resp_success){
        var modal =  resp_success;
        $(modal).modal().on('shown.bs.modal',function(){

        }).on('hidden.bs.modal',function(){
          $(this).remove();
        });
      },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-17');}
  });
}

function modal_resultadosmano_derecha(id_historia_exploracion){
  $.ajax({
    url: 'exploracionfisica/modalmano_derecha_resultados/'+id_historia_exploracion,
    dataType: 'html',
      success: function(resp_success){
        var modal =  resp_success;
        $(modal).modal().on('shown.bs.modal',function(){

        }).on('hidden.bs.modal',function(){
          $(this).remove();
        });
      },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-18');}
  });
}

function modal_resultadosmano_izquierda(id_historia_exploracion){
  $.ajax({
    url: 'exploracionfisica/modalmano_izquierda_resultados/'+id_historia_exploracion,
    dataType: 'html',
      success: function(resp_success){
        var modal =  resp_success;
        $(modal).modal().on('shown.bs.modal',function(){

        }).on('hidden.bs.modal',function(){
          $(this).remove();
        });
      },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-19');}
  });
}

function modal_resultados_torax_posterior(id_historia_exploracion){
  $.ajax({
    url: 'exploracionfisica/modalToraxPosterior_resultados/'+id_historia_exploracion,
    dataType: 'html',
      success: function(resp_success){
        var modal =  resp_success;
        $(modal).modal().on('shown.bs.modal',function(){

        }).on('hidden.bs.modal',function(){
          $(this).remove();
        });
      },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-15');}
  });
}

function modal_resultados_abdomen(id_historia_exploracion){
  $.ajax({
    url: 'exploracionfisica/modalAbdomen_resultados/'+id_historia_exploracion,
    dataType: 'html',
      success: function(resp_success){
        var modal =  resp_success;
        $(modal).modal().on('shown.bs.modal',function(){

        }).on('hidden.bs.modal',function(){
          $(this).remove();
        });
      },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red EXP-16');}
  });
}

$("body").on("click", "#expf_js_fn_13", function() {
  alert('save');
});
