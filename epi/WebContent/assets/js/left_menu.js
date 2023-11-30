$("body").on("click", "#lfm_js_fn_01", function() {
	$.ajax({
		url: 'leftmenu/usuario_en_foco.jsp',
		type: 'POST',
		dataType: "html",
    beforeSend: function() {
        $('#m_ver_menu').html('<div class="spinner_menu"><div class="m-spinner m-spinner--brand m-spinner--lg"></div></div>');
    },
		success: function(respuesta){
			  $('#m_ver_menu').html(respuesta);
		},
		error: function(){alerta('Alerta!','Error de conectividad de red LFM-01');}
	});
});
$("body").on("click", "#lfm_js_fn_02", function() {
	$.ajax({
		url: 'leftmenu/unload_foco.jsp',
		type: 'POST',
		dataType: "html",
    beforeSend: function() {
        $('#m_ver_menu').html('<div class="spinner_menu"><div class="m-spinner m-spinner--brand m-spinner--lg"></div></div>');
    },
		success: function(respuesta){
			  $('#m_ver_menu').html(respuesta);
		},
		error: function(){alerta('Alerta!','Error de conectividad de red LFM-02');}
	});
});

function carga_foco_vacio(){
	$.ajax({
		url: 'leftmenu/unload_foco.jsp',
		type: 'POST',
		dataType: "html",
    beforeSend: function() {
        $('#m_ver_menu').html('<div class="spinner_menu"><div class="m-spinner m-spinner--brand m-spinner--lg"></div></div>');
    },
		success: function(respuesta){
			  $('#m_ver_menu').html(respuesta);
		},
		error: function(){alerta('Alerta!','Error de conectividad de red LFM-02');}
	});
}

function carga_foco_paciente(id,episodio){
	$.ajax({
		//url: 'leftmenu/usuario_en_foco.jsp/'+id+'/'+episodio,
		url: 'leftmenu/usuario_en_foco.jsp',
		type: 'POST',
		data: 'id='+id+"&episodio="+episodio,
		dataType: "html",
		beforeSend: function() {
				$('#m_ver_menu').html('<div class="spinner_menu"><div class="m-spinner m-spinner--brand m-spinner--lg"></div></div>');
		},
		success: function(respuesta){
				$('#m_ver_menu').html(respuesta);
		},
		error: function(){alerta('Alerta!','Error de conectividad de red LFM-01');}
	});
}
$("body").on("click", ".m-menu__submenu", function() {
		$("#menu_lat_flot").hide();
		$('body').addClass('m-brand--minimize');
		$('body').addClass('m-aside-left--minimize');
});


function carga_archivo_modalPersona(){
  var episodio = $("#id_episodio_left").val();
  var id = $("#id_persona_left").val();
  $.ajax({
    //url: 'persona/cargaModalPanel/'+episodio,
	url: 'persona/perfil_persona.jsp?idPersona='+id,
	type: 'POST',
	data: 'episodio='+episodio+'&id='+id,
    dataType: 'html',
    success: function(resp_success){
      var modal =  resp_success;
      $(modal).modal().on('shown.bs.modal',function(){
      }).on('hidden.bs.modal',function(){
        $(this).remove();
      });
    },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
  });
}

function carga_archivo_modalDigitaliza(){
	  var episodio = $("#id_episodio_left").val();
	  var id = $("#id_persona_left").val();
	  $.ajax({
	    //url: 'persona/cargaModalPanel/'+episodio,
		//url: 'http://app.sct.gob.mx/elic/CDPagNva.jsp?cPagina=CD/frmmi.js&cPagNva=pg13032100000.js',
		url: 'digitalizacion/digitaliza.jsp',
		//url: 'digitalizacion/Requisitos/CDPagNva.jsp',
		type: 'POST',
		data: 'episodio='+episodio+'&id='+id,
	    dataType: 'html',
	    success: function(resp_success){
	      var modal =  resp_success;
	      $(modal).modal().on('shown.bs.modal',function(){
	      }).on('hidden.bs.modal',function(){
	        $(this).remove();
	      });
	    },
	    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
	  });
	}


function carga_archivo_modal(){
  var episodio = $("#id_episodio_left").val();
  var id = $("#id_persona_left").val();
  $.ajax({
    //url: 'persona/cargaModalPanel/'+episodio,
	url: 'persona/cargaModalPanel.jsp',
	type: 'POST',
	data: 'episodio='+episodio+'&id='+id,
    dataType: 'html',
    success: function(resp_success){
      var modal =  resp_success;
      $(modal).modal().on('shown.bs.modal',function(){
      }).on('hidden.bs.modal',function(){
        $(this).remove();
      });
    },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
  });
}


function carga_archivo_modalResultados(){
  var episodio = $("#id_episodio_left").val();
  var id = $("#id_persona_left").val();
  $.ajax({
    //url: 'persona/cargaModalPanel/'+episodio,
	url: 'resultados/resultadoServicios.jsp',
	type: 'POST',
	data: 'episodio='+episodio+'&id='+id,
    dataType: 'html',
    success: function(resp_success){
      var modal =  resp_success;
      $(modal).modal().on('shown.bs.modal',function(){
      }).on('hidden.bs.modal',function(){
        $(this).remove();
      });
    },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
  });
}



function carga_archivo_modal_Vitales(){
	  var episodio = $("#id_episodio_left").val();
	  var id = $("#id_persona_left").val();
	  $.ajax({
	    //url: 'persona/cargaModalPanel/'+episodio,
		url: 'persona/cargaModalPanelVitales.jsp',
		type: 'POST',
		data: 'episodio='+episodio+'&id='+id,
	    dataType: 'html',
	    success: function(resp_success){
	      var modal =  resp_success;
	      $(modal).modal().on('shown.bs.modal',function(){
	      }).on('hidden.bs.modal',function(){
	        $(this).remove();
	      });
	    },
	    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
	  });
	}

