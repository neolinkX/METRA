$("body").on("click", "#mprv_js_fn_01", function() {

		$.ajax({
			url: 'medprev/nuevaregla',
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){
						//console.log(modal);
					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red MPRV-01');}
		});

});

$("body").on("click", "#mprv_js_fn_02", function() {

  var msj_error="";
	if( $('#nombre').get(0).value == "" )	msj_error+='Olvidó ingresar Nombre.<br />';

	if( !msj_error == "" ){
		alerta('Faltan datos', msj_error);
		return false;
	}

		$.ajax({
			url: url_app + 'medprev/nuevaregla_do',
			type: 'POST',
			data: $("#nueva_regla").serialize(),
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
          $('#myModal').modal('hide');
					$('#reglas').DataTable().ajax.reload();
				}else{
					alerta('Alerta!', resp_success['mensaje']);
				}
			},
			error: function(respuesta){
				alerta('Alerta!','Error de conectividad de red MPRV-02');
				$('#nombre').get(0).value = "";
			}
		});

});

$("body").on("click", ".mprv_js_fn_03", function() {

		$.ajax({
			url: 'medprev/nuevavalidacion/' +  $(this).data('function'),
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){
						$( "#add" ).click(function() {
								$("#add_field").css({ display: "" });
								$("#add").css({ display: "none" });
						});

						$( "#ocultar" ).click(function() {
								$("#add_field").css({ display: "none" });
								$("#add").css({ display: "" });
						});

						$("#interconsulta option").each(function(){
								if($(this).text().indexOf("Servicio_") > -1){
									$(this).remove();
								}
						});

						$( ".editar_v" ).click(function() {
								$("#edit_field").css({ display: "" });
								$("#add_field").css({ display: "none" });
								$("#add").css({ display: "none" });
						});

						$( "#e_ocultar" ).click(function() {
								$("#edit_field").css({ display: "none" });
								$("#add").css({ display: "" });
						});

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red MPRV-03');}
		});

});


$("body").on("click", "#mprv_js_fn_04", function() {

  var msj_error="";
	if( $('#nombre').get(0).value == "" )	msj_error+='Olvidó ingresar Nombre.<br />';
	if( $('#operando').get(0).value == "" )	msj_error+='Olvidó ingresar el operando.<br />';

	if( $('#evento').get(0).value == "" )	msj_error+='Olvidó seleccionar el evento.<br />';

	if( $('#evento').get(0).value == "1862" ){
		if( $('#interconsulta').get(0).value == "" )	msj_error+='Si el evento es interconsulta debe seleccionar el tipo de interconsulta.<br />';
	}

	if( $('#operador').get(0).value == "" )	msj_error+='Olvidó seleccionar el operador.<br />';
	if( !msj_error == "" ){
		alerta('Faltan datos', msj_error);
		return false;
	}

		$.ajax({
			url: url_app + 'medprev/nuevavalidacion_do',
			type: 'POST',
			data: $("#nueva_validacion").serialize(),
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					alerta('Agregado', 'Abra nuevamente la modal para ver los cambios y opciones');
				}else{
					alerta('Alerta!', resp_success['mensaje']);
				}
			},
			error: function(respuesta){
				alerta('Alerta!','Error de conectividad de red MPRV-04');
			}
		});

});


$("body").on("click", ".mprv_js_fn_05", function() {

		$.ajax({
			url: url_app + 'medprev/get_validacion/' +  $(this).data('iden'),
			type: 'POST',
			dataType: 'json',
			success: function(resp_success){

				$('#e_id').get(0).value = resp_success[0]['id'];
				$('#e_nombre').get(0).value = resp_success[0]['nombre'];
				$('#e_operando').get(0).value = resp_success[0]['operando'];
				$('#e_orden').get(0).value = resp_success[0]['orden'];

				$('#e_operador').html(resp_success[0]['operador'][0]);
				$('#e_interconsulta').html(resp_success[0]['interconsulta'][0]);
				$('#e_evento').html(resp_success[0]['evento'][0]);

				if(resp_success[0]['bloqueo'] == '1'){
					$('#e_bloquear').prop("checked", true);
				}else{
					$('#e_bloquear').prop("checked", false);
				}

				$("#e_interconsulta option").each(function(){
						if($(this).text().indexOf("Servicio_") > -1){
							$(this).remove();
						}
				});


			},
			error: function(respuesta){
				alerta('Alerta!','Error de conectividad de red MPRV-05');
			}
		});

});


$("body").on("click", "#mprv_js_fn_06", function() {

	var msj_error="";
	if( $('#e_nombre').get(0).value == "" )	msj_error+='Olvidó ingresar Nombre.<br />';
	if( $('#e_operando').get(0).value == "" )	msj_error+='Olvidó ingresar el operando.<br />';
	if( $('#e_evento').get(0).value == "" )	msj_error+='Olvidó seleccionar el evento.<br />';
	if( $('#e_evento').get(0).value == "1862" ){
		if( $('#e_interconsulta').get(0).value == "" )	msj_error+='Si el evento es interconsulta debe seleccionar el tipo de interconsulta.<br />';
	}
	if( $('#e_operador').get(0).value == "" )	msj_error+='Olvidó seleccionar el operador.<br />';
	if( !msj_error == "" ){
		alerta('Faltan datos', msj_error);
		return false;
	}

		$.ajax({
			url: url_app + 'medprev/edit_validacion',
			type: 'POST',
			data: $("#edit_validacion").serialize(),
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					alerta('Editado', 'Abra nuevamente la modal para ver los cambios y opciones');
				}else{
					alerta('Alerta!', resp_success['mensaje']);
				}
			},
			error: function(respuesta){
				alerta('Alerta!','Error de conectividad de red MPRV-06');
			}
		});
});


$("body").on("click", ".mprv_js_fn_07", function() {
	var options = {
		title: "¿Desea eliminar la regla?",
		type:  "question",
		html:  "",
		messageTrue: "Se guardó correctamente!",
		contentTrue: ""
	}
	var e = $(this).data('iden');
	sweetConfirm(options,function (){
		$.ajax({
			url: url_app + 'medprev/del_validacion/' +  e,
			type: 'POST',
			dataType: 'json',
			success: function(resp_success){
				$('#myModal').modal('hide');
			},
			error: function(respuesta){
				alerta('Alerta!','Error de conectividad de red MPRV-07');
			}
		});
	});
});

$("body").on("click", ".mprv_js_fn_08", function() {

		var options = {
			title: "¿Desea eliminar la regla?",
			type:  "question",
			html:  "",
			messageTrue: "Se guardó correctamente!",
			contentTrue: ""
		}
		var e = $(this).data('function');
		sweetConfirm(options,function (){
			$.ajax({
				url: url_app + 'medprev/del_rule/' +  e,
				type: 'POST',
				dataType: 'json',
				success: function(resp_success){
					$('#reglas').DataTable().ajax.reload();
				},
				error: function(respuesta){
					alerta('Alerta!','Error de conectividad de red MPRV-08');
				}
			});
		});
});
