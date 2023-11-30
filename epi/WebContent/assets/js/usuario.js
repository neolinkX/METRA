$("body").on("click", "#usr_js_fn_01", function() {
		$.ajax({
			url: 'usuarios/modal_add_usr',
			dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){
						/**/
						carga_datepicker();

					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-01');}
		});
});

$("body").on("click", "#usr_js_fn_02", function(){
	var msj_error="";
	if( $('#nombres').get(0).value == "" )	msj_error+='Olvid\u00F3 ingresar Nombre.<br />';
	if( $('#apellido_paterno').get(0).value == "")	msj_error+='Olvid\u00F3 ingresar el apellido paterno.<br />';
	if( $('#apellido_materno').get(0).value == "")	msj_error+='Olvid\u00F3 ingresar el apellido materno.<br />';

	if( $('#correo').get(0).value == "" )	msj_error+='Olvid\u00F3 ingresar Correo.<br />';
    var regex = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
    if (!regex.test($('#correo').val().trim())) msj_error+='El correo tiene un formato inv\u00E1lido.<br />';

	if( $('#password').get(0).value == "" )	msj_error+='Olvid\u00F3 ingresar Contrase\u00F1a.<br />';
	if( $('#password2').get(0).value == "") msj_error+='Olvid\u00F3 ingresar Confimación de contrase\u00F1a.<br />';
	if( $('#password').get(0).value != $('#password2').get(0).value) msj_error+='Las contrase\u00F1as no empatan.<br />';

	if( !msj_error == "" ){
		alerta('Faltan datos', msj_error);
		return false;
	}

		$.ajax({
			//url: url_app + 'usuarios/editar_perfil',
			url: url_app + 'persona/editar_perfil.jsp',
			type: 'POST',
			data: $("#editar_perfil").serialize(),
			dataType: 'json',
			success: function(resp_success){
				//if (resp_success['resp'] == true) {
				if(resp_success[0].resp=="correcto"){
					//$('#name_top').html(resp_success['new_name']);
					alerta('Anuncio!', 'Password modificado correctamente!');
				}else{
					alerta('Alerta!', 'Se produjeron errores al modificar los datos del usuario!');
				}
			},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-02');}
		});

});

$("body").on("click", ".usr_js_fn_03", function() {
		id_usuario = $(this).attr('data-function');
			$.ajax({
				url: 'usuarios/datos_usuario/' + id_usuario,
				dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){
						carga_datepicker();
					}).on('hidden.bs.modal',function(){
						$(this).remove();
					});
				},
				error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
			});
});


$("body").on("click", "#usr_js_fn_04", function() {
	var msj_error="";
	if( $('#usuario').get(0).value == "" ){ msj_error+='Olvidó ingresar Usuario.<br />'; /*$('#usuario').css({background:'#F4CECD'}); */ }
	if( $('#nombres').get(0).value == "" )	msj_error+='Olvidó ingresar Nombre.<br />';
	if( $('#apellido_paterno').get(0).value == "")	msj_error+='Olvidó ingresar el apellido paterno.<br />';
	if( $('#apellido_materno').get(0).value == "")	msj_error+='Olvidó ingresar el apellifo materno.<br />';
	if( $('#correo').get(0).value == "" )	msj_error+='Olvidó ingresar Correo.<br />';
	if( $('#password').get(0).value == "" )	msj_error+='Olvidó ingresar Contraseña.<br />';
	if( $('#password2').get(0).value == "") msj_error+='Olvidó ingresar Confimación de contraseña.<br />';
	if( $('#id_rol').get(0).value == "" )	msj_error+='Olvidó seleccionar Rol de usuario.<br />';
	if( $('#id_ubicacion').get(0).value == "" )	msj_error+='Olvidó seleccionar la ubicacion del usuario.<br />';

	if( !msj_error == "" ){
		alerta_div('error_alerta','Error en la captura de datos.',msj_error);
		return false;
	}


		$.ajax({
			url: 'usuarios/agregar_usuario',
			type: 'POST',
			data: $("#nuevo_usuario").serialize(),
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					$('#myModal').modal('hide');
					$('#usuarios').DataTable().ajax.reload();
				}else{
					alerta_div('error_alerta',resp_success['mensaje'],resp_success['error']);
					$('#password').get(0).value = "";
					$('#password2').get(0).value = "";
				}
			},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-04');}
		});


});

function alerta_div(id_div,error_head,error_content){
	var div_error = '<div class="alert alert-danger" id="error_new_user">';
	div_error += '<button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>';
	div_error += '<strong><i class="fa fa-comments-o"></i>'+error_head+'</strong>';
	div_error += '<br />'+error_content;
	div_error += '</div>';
	$('#'+id_div).html(div_error);
}

$("body").on("click", "#usr_js_fn_05", function() {
	var msj_error="";
	if( $('#nombres').get(0).value == "" )	msj_error+='Olvidó ingresar Nombre.<br />';
	if( $('#apellido_paterno').get(0).value == "")	msj_error+='Olvidó ingresar el apellido paterno.<br />';
	if( $('#apellido_materno').get(0).value == "")	msj_error+='Olvidó ingresar el apellido materno.<br />';
	if( $('#correo').get(0).value == "" )	msj_error+='Olvidó ingresar Correo.<br />';
	if( $('#id_rol').get(0).value == "" )	msj_error+='Olvidó seleccionar Rol de usuario.<br />';
	if( $('#password').get(0).value != $('#password2').get(0).value) msj_error+='Las contraseñas no empatan.<br />';
	if( $('#id_ubicacion').get(0).value == "" )	msj_error+='Olvidó Seleccionar la Ubicación.<br />';


	if( !msj_error == "" ){
		alerta('Faltan datos', msj_error);
		return false;
	}

		$.ajax({
			url: 'usuarios/editar_usuario',
			type: 'POST',
			data: $("#edita_usuario").serialize(),
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					$('#myModal').modal('hide');
					$('#usuarios').DataTable().ajax.reload();
				}else{
					alerta_div('error_alerta',resp_success['mensaje'],resp_success['error']);
				}
			},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-05');}
		});

});


$("body").on("click", "#chk_change_pass", function() {
	if($("#chk_change_pass").is(':checked')) {
		$('#change_pass').get(0).value = "132";
	} else {
		$('#change_pass').get(0).value = "133";
	}
});


$("body").on("click", "#chk_cat_status", function() {
	if($("#chk_cat_status").is(':checked')) {
		$('#cat_status').get(0).value = "3";
	} else {
		$('#cat_status').get(0).value = "4";
	}
});


$("body").on("click", "#usr_js_fn_06", function() {

		var id = $('#id_usuario').get(0).value;
		$.ajax({
			url: 'usuarios/baja_usuario/' + id,
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					$('#myModal').modal('hide');
					$('#usuarios').DataTable().ajax.reload();
				}else{
					alerta_div('error_alerta',resp_success['mensaje'],resp_success['error']);
				}
			},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-06');}
		});

});

$("body").on("click", "#usr_js_fn_07", function() {
		id_usuario = $(this).attr('data-function');
		$.ajax({
			url: 'usuarios/desbloquea_usuario/' + id_usuario,
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					$('#myModal').modal('hide');
					$('#usuarios').DataTable().ajax.reload();
				}else{
					alerta_div('error_alerta',resp_success['mensaje'],resp_success['error']);
				}
			},
			error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-07');}
		});
});

function tyc(stat) {
	if(stat == 'SI'){
		$.ajax({
			url: 'usuarios/tyc/' + stat,
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					console.log(resp_success['resp']);
					if(resp_success['dispositivo']=='celular'){
						window.location ="mobile";
					}else{
						window.location ="inicio";
					}
				}else{
					console.log(resp_success['resp']);
					window.location ="inicio";
				}
			},
			error: function(respuesta){ console.log('Error en el proceso TYC');}
		});
	}else{
		salirAlternativo();
	}
}
function cambiar_pass(){
		$.ajax({
			url: 'usuarios/cambiar_password',
			type: 'POST',
			data: $("#chge_pass").serialize(),
			dataType: 'json',
			success: function(resp_success){
				if (resp_success['resp'] == true) {
					console.log(resp_success['resp']);
					if(resp_success['dispositivo']=='celular'){
						window.location ="mobile";
					}else{
						window.location ="inicio";
					}
				}else{
					alerta('Alerta!','Error en el proceso PASS_CHGE 01');
				}
			},
			error: function(respuesta){ alerta('Alerta!','Error en el proceso PASS_CHGE 02');}
		});
}
