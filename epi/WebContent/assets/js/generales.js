function carga_archivo(div_contenedor,ruta,parametros){
	$('#preloader').html('<div class="progress primary-color-dark"> <div class="indeterminate"></div></div>');
	$('#'+div_contenedor).load(ruta,parametros, function(){
		$('#preloader').html('');
	});
}

function alerta(header,body){
	var modal =
	'<div class="modal fade" id="myModal" tabindex="-1" role="dialog"'+
		'aria-labelledby="myModalLabel" aria-hidden="true">'+
		'<div class="modal-dialog">'+
			'<div class="modal-content">'+
				'<div class="modal-header">'+
					'<button type="button" class="close" data-dismiss="modal"+ aria-hidden="true">×</button>'+
					'<h4 class="modal-title" id="myModalLabel">'+header+'</h4>'+
				  '</div>'+
				'<div class="modal-body">'+
					''+ body +''+
				'</div>'+
				'<div class="modal-footer">'+
					'<button data-dismiss="modal" class="btn btn-ar btn-default" type="button">Cerrar</button>'+
				'</div>'+
			'</div>'+
		'</div>'+
	'</div>';
	$(modal).modal().on('shown.bs.modal',function(){
		//console.log(modal);
	}).on('hidden.bs.modal',function(){
		$(this).remove();
	});
}
$('body').on('focus','.date-picker',function(){
	$(this).datepicker({
							 language: "es",
							 format: "yyyy-mm-dd",
							 changeMonth: true,
							 changeYear: true,
							 autoclose: true,
							 todayHighlight:!0, orientation:"bottom left", templates: {
									 leftArrow: '<i class="la la-angle-left"></i>', rightArrow: '<i class="la la-angle-right"></i>'
							 }
					 }).inputmask('yyyy-mm-dd');
});

$('body').on('focus','.date-time-picker',function(){
	$(this).datetimepicker({
						 language: "es",
						 format: "yyyy-mm-dd hh:ii",
						 autoclose: true,
					 })
});


function sweetConfirm(options,callback){
  swal({
      title: options.title,
      type:  options.type,
      html:  options.html,
      showCancelButton: true,
      showCloseButton: true,
      focusConfirm: false,
      cancelButtonText: '<i class="fa	fa-thumbs-o-down"></i>&nbsp;Cancelar',
      confirmButtonText: '<i class="fa	fa-thumbs-o-up"></i>&nbsp;Aceptar',
      confirmButtonAriaLabel: '',
      confirmButtonColor: '#4962b3',
      cancelButtonColor: '#d33',
    }).then(function (confirma) {
        if(confirma){
          callback();
        }
      });
}

function modalConfirm(callback,options){

	if(typeof options=='undefined'){
		options = {title:"¿Desea guardar el contenido?",type:"question",messageTrue:"Se guardo correctamente!",html:"",contentTrue:""}
	}

	  var options = {
	    title: (typeof options.title!='undefined')?options.title:"¿Desea guardar el contenido?",
	    type:  (typeof options.type!='undefined')?options.type:"question",
	    html:  (typeof options.html!='undefined')?options.html:"",
	    messageTrue: (typeof options.messageTrue!='undefined')?options.messageTrue:"Se guardó correctamente!",
	    contentTrue: (typeof options.contentTrue!='undefined')?options.contentTrue:""
	  }
	  sweetConfirm(options,function (){
	      if (typeof callback === "function") {
	        callback();
	      }
	  });
}
/**
 * Funcion general para limpiar combos de tipo Select2
 *
 */
function clearComboSelect2(id){
	$("#"+id).html('').select2({
		width: '100%',
		placeholder: {
			id: '-1', // the value of the option
			text: 'Seleccione una opción'
		}
	});
	 $("#"+id).append($('<option>',
		 {
				value: '-1',
				text : 'Seleccione una opcion'
		}));
}
/**
 * Funcion que Carga el contenido dentro de un tab
 * @return {[type]} [description]
 */
$('body').on('click','.load_tab_content',function(){
		var cotainer = $(this).data('container');
		var url =  $(this).data('url_content');
		carga_archivo(cotainer,url);
});


 /*
  * Mascara para validacion de numeros enteros, agregar etiqueta data-number="min|max", acepta números negativos
  *
  */
$("body").on("focus", "[data-number]", function() {
	var min_max = $(this).data('number');
	var min=0,max=1000;
	if(min_max!=''){
		obten_mm = min_max.split("|");
		min = parseInt(obten_mm[0]);
		max = parseInt(obten_mm[1]);
	}

	var allowMinus = (min<0)?true:false;

	$(this).inputmask({alias: 'numeric',
                     allowMinus: allowMinus,
                     digits: 0,
										 integerDigits:3,
										 min: -190,
                     max: max,rightAlign: false,
										 onKeyDown: function(event, buffer, caretPos, opts) {
																 var currentValue = buffer.length == 2 ? buffer[0] : "";
								        				 if (currentValue === "-" && (event.key === "Decimal" || event.key === ".")) {
								        						 $(event.currentTarget).val('-0..');
								      					 }
 							 	                  if(event.currentTarget.value > max || event.currentTarget.value < min){
 							 	                    $(event.currentTarget).val('');
 							 	                  }
 							 	   					 }
										});
});


$("body").on("focus", "[data-only_digit]", function() {
		$(this).inputmask('Regex', {regex: "[0-9]+$"});
});
/*
 * Mascara para validacion de numeros con digito, agregar etiqueta data-number_dec="min|max"
 *
 */

$("body").on("focus", "[data-number_dec]", function() {
	 var min_max = $(this).data('number_dec');
	 var num_dig_dat = $(this).data('number_dec_dig');
	 var min=0,max=1000,num_dig=1;

	 if(min_max!=''){
		 obten_mm = min_max.split("|");
		 min = parseFloat(obten_mm[0]);
		 max = parseFloat(obten_mm[1]);
	 }
	 if(num_dig_dat!=''){num_dig = num_dig_dat;}
	 var allowMinus = (min<0)?true:false;

   $(this).inputmask("decimal", { min: min, max: max, allowMinus: allowMinus, digits: num_dig, integerDigits: 3,
		 onKeyDown: function(event, buffer, caretPos, opts) {
	                  if(event.currentTarget.value > max){
	                    $(event.currentTarget).val('');
	                  }
	   					 }
	 });

});

$("body").on("focus", "[data-letter]", function() {
	$(this).inputmask('Regex', {regex: "[a-zA-ZñÑ]"});
});


function elimina_objeto_id(id){
	$("#"+id).remove();
}

function carga_modal_servicios(container,controlador){
	var id_persona = $('#id_persona_left').val();
	carga_archivo(container,controlador+id_persona);
}
function carga_servicios_episodio(container,controlador){
	var id_episodio = $('#id_episodio_left').val();
	carga_archivo(container,controlador+id_episodio);
}
function deshabilita_dependiente_check(dependiente,check){
	if($('input:checkbox[name='+check+']:checked').val()==1)
	{
		// limpia y deshabilita objeto
		$("#"+dependiente).val('');
		$("#"+dependiente).prop('disabled', true);
	}else{
		// habilita objeto
		$("#"+dependiente).prop('disabled', false);
	}
}

function habilita_dependiente_check(dependiente,check){
	if($('input:checkbox[name='+check+']:checked').val()==1)
	{
		// habilita objeto
		$("#"+dependiente).prop('disabled', false);
	}else{
		// limpia y deshabilita objeto
		$("#"+dependiente).val('');
		$("#"+dependiente).prop('disabled', true);
	}
}



/**
 *  validarCURP: Form Alta Paciente
 *  Boton: Se ejecuta como validacion antes de guardar
 *  Descripcion: Hace una validacion en base a las reglas establecidas por RENAPO para la generacion de CURP
 * @type ID-curp
 */

function validarCURP(input) {
  var curp =  input.value.toUpperCase(),
  resultado = document.getElementById("resultado"),
  valido = false;

 if (curpValida(curp)) {
  valido = true;
 }

 return valido;
}

function curpValida(curp) {
/*var re = /^([A-Z][AEIOUX][A-Z]{2}\d{2}(?:0\d|1[0-2])(?:[0-2]\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\d])(\d)$/,
  validado = curp.match(re);
 if (!validado)  //Coincide con el formato general?
  return false;

 //Validar que coincida el dígito verificador
 function digitoVerificador(curp17) {
     //Fuente https://consultas.curp.gob.mx/CurpSP/
     var diccionario  = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ",
         lngSuma      = 0.0,
         lngDigito    = 0.0;
     for(var i=0; i<17; i++)
         lngSuma= lngSuma + diccionario.indexOf(curp17.charAt(i)) * (18 - i);
     lngDigito = 10 - lngSuma % 10;
     if(lngDigito == 10)
         return 0;
     return lngDigito;
 }
 if (validado[2] != digitoVerificador(validado[1]))
  return false;
  */
  if(curp.match(/^([a-z]{4})([0-9]{6})([a-z]{6})([0-9A-Z]{1})([0-9]{1})$/i)){
	  return true; //Validado
  }else{
	  return false; //Validado	  
  }

}


/**
 *  validarRFC: Form Alta Paciente
 *  Boton: Se ejecuta como validacion antes de guardar
 *  Descripcion: Hace una validacion en base a las reglas establecidas por el SAT para la generacion de RFC
 * @type ID-rfc
 */


function validarRFC(input) {
	  var rfc =  input.value.toUpperCase(),
	  resultado = document.getElementById("resultado"),
	  valido = false;

	 if (RFCvalida(rfc)) {
	  valido = true;
	 }

	 return valido;
	}

function RFCvalida(input) {
	 if(input.match(/^([A-ZÑ&]{3,4}) ?(?:- ?)?(\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\d|3[01])) ?(?:- ?)?([A-Z\d]{2})([A\d])$/i)){
		 return true; //Validado
	  }else{
		  return false; //Validado	  
	  }
}

function mayus(e) {
    e.value = e.value.toUpperCase();
}



function fValRFC (cVRFC,iVTipo){
   switch(iVTipo){
   		case 1: iVSiglas = 4; iVMin = 10; iVTam = 13; break;
   		case 2: iVSiglas = 3; iVMin = 12; iVTam = 12; break;
   }

   if (cVRFC.length < iVMin || cVRFC.length > iVTam)
      return false;

   if (cVRFC.length > iVMin && cVRFC.length < iVTam) {
         return false;
   }

   if(fSoloLetras(cVRFC.substring(0,iVSiglas))    && 
      fSoloNumeros(cVRFC.substring(iVSiglas,iVSiglas + 6))  && 
      fSoloAlfanumericos(cVRFC.substring(iVSiglas + 6, iVTam)) && !fEspacio(cVRFC)  )
     return true;
   else
     return false;

}







function decimales(obj){
  var cadena = $('#'+obj).val();
  var patron = /([0-9]+[\.][0-9])/;

  if(patron.test(cadena)==false){
    $('#'+obj).get(0).value="";
  }
}

function resalta_seleccionado(id){
	$(".gli").css("color","#5867dd");
	$("#"+id).css("color","#06091f");
}

/**
 * Funcion que habilita o deshabilita un campo en base a la respuesta de otro (SI/NO) de tipo checked, limpia el contenido del campo habilitado
 * en caso de evaluar no.
 * @return {[type]} [description]
 */


 $("body").on("click", "[data-enable_input]", function() {
		 var input = $(this).data('enable_input');
	    if($(this).prop('checked')){
	       $('#'+input).prop('readonly', false).focus();
				 $('#'+input).prop('disabled', false).focus();
	    }else{
	      $('#'+input).prop('readonly', true).val('');
	    }
 });

 $("body").on("change", "[data-unblock_by_id]", function() {
	 var input = $(this).data('unblock_by_id');
	 var value_unblock = $(this).data('value_unblock');
		if($(this).val() == value_unblock){
			 $('#'+input).prop('disabled', false);
		}else{
			$('#'+input).prop('disabled', true).val('');
		}
 });

 $("body").on("change", "[data-block_by_id]", function() {

	 var input = $(this).data('block_by_id');
	 var value_block = $(this).data('value_block');

		if($(this).val() == value_block){
			 $('#'+input).prop('disabled', true).val('');
		}else{
				$('#'+input).prop('disabled', false);
		}
 });

$("body").on("focus", "[data-set_max_word]", function() {
		var modo_max = Number($(this).data('set_max_word'));
		$(this).maxlength({threshold: modo_max,warningClass:"m-badge m-badge--primary m-badge--rounded m-badge--wide",limitReachedClass:"m-badge m-badge--brand m-badge--rounded m-badge--wide",appendToParent:!0});
});
