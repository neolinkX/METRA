function busqueda_persona(){
  var ap_paterno = $.trim($("#ap_paterno").val());
  var ap_materno = $.trim($("#ap_materno").val());
  var nombre     = $.trim($("#nombre").val());
  var rfc     = $.trim($("#crfc").val());
  var curp     = $.trim($("#ccurp").val());
  var id     = $.trim($("#id").val());

  
  
  /////////////////Nuevas Reglas ///////////////////////
  form = document.forms[0];
  lAcceso = true;
  cCondicion = '';

  if(id != ''){
      cCondicion = " PERDatos.iCvePersonal = " + id + " and";
  }

  if(crfc != '' && lAcceso && crfc.length > 1)
    cCondicion = cCondicion + " UCASE(PERDatos.cRFC) like '" + crfc + "%' and";

  if(ccurp != ''  && lAcceso && ccurp.length > 1)
    cCondicion = cCondicion + " UCASE(PERDatos.cCURP) like '" + ccurp + "%' and";

  if(ap_paterno != ''  && lAcceso && ap_paterno.length > 1)
    cCondicion = cCondicion + " UCASE(PERDatos.cApPaterno) like '" + ap_paterno + "%' and";

  if(ap_materno != ''  && lAcceso && ap_materno.length > 1)
    cCondicion = cCondicion + " UCASE(PERDatos.cApMaterno) like '" + ap_materno + "%' and";

  if(nombre != ''  && lAcceso && nombre.length > 1)
    cCondicion = cCondicion + " UCASE(PERDatos.cNombre) like '" + nombre + "%' and";

  if(cCondicion != ''){
    cCondicion = ' where ' + cCondicion.substring(0,cCondicion.length - 3);
  }

  if(lAcceso && cCondicion != ''){
    //form.hdBuscar.value = 1;
    //form.action = "SEPer01.jsp";
    //form.hdCondicion.value = cCondicion;
    //form.submit();
	  $("#filtro").val(cCondicion);
  }else{
	  $("#alert_busqueda").show();
	    return false;
	  alert('Debe introducir alguna condición de bùsqueda vàlida (mayor a 2 caracteres)');  
  }
    
  
  
  
  
  
  /*
  if(nombre=='' || (nombre=='' &&  ap_paterno=='' && ap_materno=='') || (nombre!='' && ap_paterno=='' && ap_materno=='')){
    $("#alert_busqueda").show();
    return false;
  }
  */
  
  
  
  $("#alert_busqueda").hide();

  $.ajax({
    url: 'busqueda/busca_persona.jsp',
    dataType: 'json',
    type: 'Post',
    data: $("#form_busqueda_persona").serialize(),
      success: function(resp_success){
        //carga_archivo('contenedor_principal','http://lalo.expediente.tk/persona/altaPersona');

        $('#busqueda_pac').DataTable( {
            destroy: true,
            searching: true,
            data: resp_success.data
            } );

          $('#busqueda_pac tbody').on('click', 'tr', function () {
      			var id = $('td', this).eq(0).text();
            carga_ficha_persona(id);
      		} );
      },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-01');}
  });
}

function buscaCurpPersona(id){
  $.ajax({
	  url: 'persona/BuscaCurp.jsp?ccurp=' + id.value,
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


function buscaCurpPersona2(id){
	  $.ajax({
		  url: 'persona/BuscaCurp.jsp?ccurp=' + id.value,
		  type: 'POST',
		  dataType: 'json',
	     success: function(resp_success){
	       	//alert(resp_success[0].resp);
	       	if(resp_success[0].resp=='success'){
	       		var msg = id.value;
	           	swal('El Curp '+msg +' pertenece a otro expediente!', '', "error");
	           	id.value='';
	         	}
	     },   
	     error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
	   });
	}



function carga_ficha_persona(id){
	//carga_archivo('contenedor_principal','persona/perfilPersona/'+id);
	carga_archivo('contenedor_principal','persona/perfil_persona.jsp?idPersona='+id);
  carga_foco_paciente(id);

  $('body').removeClass('m-brand--minimize');
  $('body').removeClass('m-aside-left--minimize');
}

/*
function guardaNuevaPersona(){
    var options = {type:'question',title:'\u00BFDesea guardar la Ficha de Identificaci\u00F3n?'}
  modalConfirm(function (){

      $.ajax({
        url: 'persona/guardaPersona.jsp',
        dataType: 'json',
        type: 'Post',
        data: $("#form_alta_persona").serialize()+'&'+$("#form_domicilio_persona").serialize(),
          success: function(resp_success){
            if(resp_success.resp==true){
              swal(resp_success.mensaje, '', "success");
              carga_archivo('contenedor_principal','citas/');carga_foco_vacio();
            }else{
              swal(resp_success.mensaje, '', "error");
            }
          },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-01');}
      });
   },options);
}*/


function guardaNuevaPersona(){
    var options = {type:'question',title:'\u00BFDesea guardar la Ficha de Identificaci\u00F3n?'}
  modalConfirm(function (){
      $.ajax({
        url: 'persona/guardaPersona.jsp',
        dataType: 'json',
        type: 'Post',
        data: $("#form_alta_persona").serialize()+'&'+$("#form_domicilio_persona").serialize(),
          /*success: function(resp_success){
            if(resp_success.resp==true){
              swal(resp_success.mensaje, '', "success");
              carga_archivo('contenedor_principal','citas/');carga_foco_vacio();
            }else{
              swal(resp_success.mensaje, '', "error");
            }
          },*/
 	     success: function(resp_success){
 	       	//alert(resp_success[0].resp);
 	       	if(resp_success[0].resp=='success'){
 	       	swal('El expediente se creo satisfactoriamente, el expediente asociado es el '+resp_success[1].exp, '', "success");
            carga_archivo('contenedor_principal','citas/');carga_foco_vacio();
            carga_ficha_persona(resp_success[1].exp);
 	         }else{
 	              swal('No fue posible crear el expediente, int\u00E9ntalo nuevamente', '', "error");
             }
 	     },   
          error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-01');}
      });
   },options);
}





$("body").on("click", "#gen_exp_fn_01", function() {
		var id_paciente = $(this).attr('data-paciente');
		alert("bueno2");
			$.ajax({
				//url: 'persona/perfilPersona/' + id_paciente+'/2',
				url: 'persona/perfil_persona.jsp?idPersona='+id_paciente+'',
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


function get_signosVitales(){
  var episodio = $("#id_episodio_left").val();
  //carga_archivo('contenedor_movimientos','persona/signosVitales/'+episodio);
  //console.log("signosvitalescap");
  //alert("aqui en signos");
  carga_archivo('contenedor_movimientos','http://10.33.143.52:7001/epi/persona/signosVitales.jsp');
}

function get_Digitaliza(){
	  var episodio = $("#id_episodio_left").val();
	  //carga_archivo('contenedor_movimientos','persona/signosVitales/'+episodio);
	  //console.log("signosvitalescap");
	  //alert("aqui en signos");
	  carga_archivo('contenedor_movimientos','http://app.sct.gob.mx/elic/CDPagNva.jsp?cPagina=CD/frmmi.js&cPagNva=pg13032100000.js');
}


//Boton generar Expediente
$("body").on("click", "#gen_exp_fn_02", function() {
		var id_paciente = $(this).attr('data-paciente');
		alert("bueno3");
    var id_cita = $(this).attr('data-cita');
			$.ajax({
				//url: 'persona/perfilPersona/' + id_paciente+'/3/'+id_cita,
				url: 'persona/perfil_persona.jsp?idPersona='+id_paciente+'',
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
});

/**
 *  Menu: Consulta de Citas
 *  Boton de Tabla: Puesto
 *  Descripcion: Genera la Modal para seleccionar el Tramite a realizar(Selección de Modo de Transporte, Categoria y Puesto)
 * @type {[type]}
 */

$("body").on("click", "#gen_exp_fn_03", function() {
		var id_paciente = $(this).attr('data-paciente');
			$.ajax({
				//url: 'persona/modalTramitePersona/' + id_paciente,
				url: 'persona/tramites.jsp',
				dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){
             $("select").select2({ width: '100%' });
					}).on('hidden.bs.modal',function(){
            $("select").select2('destroy');
						$(this).remove();
					});
				},
				error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
			});
});


function generaExpediente(id){
  var options = {type:'question',title:'¿Desea dar de Alta la persona correspondiente?'}
  modalConfirm(function (){

    $.ajax({
      url: 'persona/generaExpediente/'+id,
      dataType: 'json',
      type: 'Post',
        success: function(resp_success){

            if(resp_success.resp==true){
              swal('Se generó correctamente el expediente!', 'A partir de este momento tiene un máximo de 3 horas para generar el vale de servicio, en caso de no hacerlo este expediente será eliminado', "success");
              $('#consulta_citas').DataTable().ajax.reload();
              $('#modalPerfil').modal('hide');
            }else{
              swal('Error al generar expediente!', '', "error");
            }

        },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-01');}
    });

  },options);
}

/**
 * [$ description]
 * @param  {[type]} form_alta_persona [description]
 * @return {[type]}                   [description]
 */
$("body").on("click", "#btn_save_alta_pac", function() {

    $("#form_alta_persona").validate({ debug: true,
    rules: {
           apaterno: { required: true }, nombre: { required: true }, fecha_nacimiento:{ required: true },
           rfc:{ required: true , checkRFC: true },
           curp: {
           required: true,
           checkCURP: true,
           //checkUniqueCurp: true
         },
           sexo:{ required: true }, id_nacionalidad:{ required: true },
           estado_nacimiento:{ required: true }, dom_calle:{ required: true }, dom_numext:{ required: true },
           dom_codpos:{ required: true }, dom_id_pais:{ required: true }, dom_id_estado:{ required: true },telefono:{ required: true },
           dom_id_municipio:{ required: true }, dom_id_localidad:{ required: true }, dom_colonia:{ required: true }
        },
        invalidHandler:function(e,r){
          var i=$("#m_form_1_msg");
          i.removeClass("m--hide").show(),
          mApp.scrollTo(i,-200);
        },
        submitHandler: function(form) {
          guardaNuevaPersona();
        }
    });

    $.validator.addMethod('checkCURP', function(value, element) {
        return validarCURP(element);
    }, "El Formato del CURP no es válido");

    /*$.validator.addMethod('checkRFC', function(value, element) {
        return validarRFC(element);
    }, "El Formato del RFC no es válido");
     */
    $.validator.addMethod('checkRFC', function(value, element) {
    	return validarRFC(element);
	}, "El Formato del RFC no es válido");
});


function guarda_tarmite_cita(id_paciente){
  $(".alert").hide();
  var id_modo_transporte = $("#id_modo_transporte").val();
  var categoria_tramite = $("#categoria_tramite").val();
  var puesto_tramite = $("#puesto_tramite").val();
  var id_motivo_tramite = $("#id_motivo_tramite").val();
  
 // $("#id_modo_transporte").jCombo({ url: "ComoTransporte.jsp", selected_value : '15' } );

  if(id_modo_transporte =='-1' || id_modo_transporte =='' || categoria_tramite =='null' || puesto_tramite =='null' || id_motivo_tramite ==''
     || categoria_tramite < 0 || puesto_tramite < 0){
      $(".alert").show();
      return false;
    }

    var options = {type:'question',title:'\u00BFDesea guardar el Tr\u00E1mite\u003F'}
    modalConfirm(function (){
    	//alert("tramite");
      $.ajax({
        //url: url_app + 'citas/saveTramiteCita/'+id_paciente,
    	url: 'citas/saveTramiteCita.jsp?hdICvePersonal='+id_paciente,
        type: 'POST',
        data: $("#form_tramites_cita").serialize(),
        dataType: 'json',
        success: function(resp_success){
        	//alert(resp_success[0].resp);
            //if(resp_success.resp==true){
        	if(resp_success[0].resp=='success'){
                swal('Se guard\u00F3 correctamente\u0021', '', "success");
                $("#btn_guarda_tramite").remove();
                $('#consulta_citas').DataTable().ajax.reload();
            }else{
                //swal('Hubo un error al guardar el trámite', '', "error");
                swal(''+resp_success[0].resp, '', "error");
            }
        },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
      });
    },options);
}


$("body").on("click", "#btn_inicio_procesoEPI", function() {
		var id_paciente = $(this).attr('data-paciente');
			$.ajax({
				//url: 'persona/modalInicioProceso/' + id_paciente,
				url: 'persona/modalInicioProceso.jsp',
				dataType: 'html',
				success: function(resp_success){
					var modal =  resp_success;
					$(modal).modal().on('shown.bs.modal',function(){
             $("select").select2({ width: '100%' });
					}).on('hidden.bs.modal',function(){
            $("select").select2('destroy');
						$(this).remove();
					});
				},
				error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
			});
});



//Funcion para Atender Persona
$('body').on('click', '#btn_atender_persona', function () {
  var id = $(this).data('paciente');
  var id_episodio = $(this).data('episodio');
  //carga_archivo('contenedor_principal','persona/perfilPersona/'+id);
  carga_archivo('contenedor_principal','persona/perfilPersona.jsp');
  carga_foco_paciente(id,id_episodio);
  $("#menu_lat_flot").show();
  $('body').removeClass('m-brand--minimize');
  $('body').removeClass('m-aside-left--minimize');

});


function calcula_imc(){
  var peso = $('#svs_peso').val();
  var estatura = $('#svs_estatura').val();

  if(peso!="" && estatura!=""){
    // calcula imc
    // pasa centimetros a metros
    var metros = estatura/100;
    var cuadrado = Math.pow(metros, 2);
    var imc = (peso/cuadrado).toFixed(2);

    $('#svs_imc').val(imc);

  }else{
    $('#svs_imc').val("");
  }
}


/**
 * Funciones para cargar los selects del Formulario de ALta Persona (Direccion)
 * Estados, Municipios y Localidades
 *
 */


function busca_estado(id_estado){
   //var id_estado = $('#dom_id_estado').val();
   $("#dom_id_municipio").html('');
   $("#dom_id_localidad").html('<option value="">Seleccione</option>');
   $("#dom_codpos").val('');

 $.ajax({
   url:  'direccion/getSelectMunicipios/'+id_estado,
   type: 'POST',
   dataType: 'html',
   success: function(resp_success){
     $("#dom_id_municipio").html(resp_success);
   },
   error: function(respuesta){ alerta('Alerta!','Error de conectividad de red AP-DIR-EDO');}
 });
}

function busca_asentamiento(){
	  //console.log("busca_asentamiento");
  var id_estado = $('#dom_id_estado').val();
  var id_municipio = $('#dom_id_municipio').val();

  $.ajax({
    url:  'direccion/getSelectAsentamientos/'+id_estado+'/'+id_municipio,
    type: 'POST',
    dataType: 'html',
    success: function(resp_success){
      $("#dom_id_localidad").html(resp_success);
    },
    error: function(respuesta){ alerta('Alerta!','Error de conectividad de red AP-DIR-EDO');}
  });
}

function obtiene_cp(){
   var id_asentamiento = $('#dom_id_localidad').val();

   $.ajax({
     url:  'direccion/getCodposByAsentamiento/'+id_asentamiento,
     type: 'POST',
     dataType: 'json',
     success: function(resp_success){
       //console.log(resp_success.codigo_postal);
       $("#dom_codpos").val(resp_success.codigo_postal);
     },
     error: function(respuesta){ alerta('Alerta!','Error de conectividad de red AP-DIR-CP-AS');}
   });
 }
 
 function carga_codpos(){
   var cod_pos = $('#dom_codpos').val();
   $.ajax({
     url: 'direccion/busca_codigo_postal/'+cod_pos,
     type: 'POST',
     dataType: 'json',
     success: function(resp_success){

         if(resp_success.id_entidad!=''){
           $("#dom_id_pais option[value='1309']").prop('selected', true).trigger('change.select2');
           busca_estado(resp_success.id_entidad);
           $("#dom_id_estado option[value='"+resp_success.id_entidad+"']").prop('selected', true).trigger('change.select2');
           setTimeout(function(){ $("#dom_id_municipio option[value='"+resp_success.cve_municipio+"']").prop('selected', true).trigger('change.select2'); }, 500);

           if(resp_success.cve_asentamiento!=0){
             setTimeout(function(){$("#dom_id_localidad option[value='"+resp_success.cve_asentamiento+"']").prop('selected', true).trigger('change.select2'); }, 800);
           }

           $("#dom_codpos").val(cod_pos);
         }
     },
     error: function(respuesta){ alerta('Alerta!','Error de conectividad de red AP-01');}
   });
 }


/*
 *  Funciones para Dictamen
 *
 */

 function modal_addclaves_dictamen(obj){
  var tabla = $(obj).data('nametable');
  var name_tabla  = $(obj).data('nameidtr');

  $.ajax({
   url: 'dictamen/modal_addclaves_dictamen/'+name_tabla,
   dataType: 'html',
   success: function(resp_success){

     var modal =  resp_success;
     $(modal).modal().on('shown.bs.modal',function(){

        $("#"+tabla+"_modal").dataTable( {
           "fnDrawCallback": function( oSettings ) {
           },
           "searching": true,
           "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
           "processing": true,
           "serverSide": true,
           "ajax": {
               "url": "dictamen/data_table_claves/"+name_tabla,
               "type": "POST"
           }
       } );

       $("#"+tabla+"_modal").on("click", "#btn_agrega_tr", function() {
         var id = $(this).data('id');
         var clave = $(this).data('clave');
         var descripcion = $(this).data('descripcion');

         if($("#tr_"+name_tabla+id).length > 0){return false;}
            $("#"+tabla).append("<tr id='tr_"+name_tabla+id+"'><td>"+id+"</td><td>"+clave+"</td><td>"+descripcion+"</td><td><input type='hidden' name='diagnosticos_"+name_tabla+"[]' value='"+id+"'><button type='button' class='btn btn-sm btn-danger' onclick='elimina_objeto_id(\"tr_"+name_tabla+id+"\");'><i class='fa fa-trash-o'></i></button></td></tr>")
            toastr.success("Se agregó la clave correctamente!")
         });

     }).on('hidden.bs.modal',function(){
       $(this).remove();
     });
   },
   error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
 });
 }

 function guarda_dictamen_servicio(servicio){
   if($.trim($("#observaciones_dictamen").val())==''){
     alerta("Alerta","El campo de Observaciones es requerido");
     return false;
   }
  var id_episodio = $("#id_episodio_left").val();
  $.ajax({
   url: 'dictamen/guarda_dictamen_servicio/'+id_episodio,
   dataType: 'json',
   type: 'POST',
   data: $('#frm_dictamen_servicio').serialize(),
   success: function(resp_success){
     if(resp_success.resp=='true'){
       swal('Se guardó correctamente el dictamen!', '', "success");
       carga_archivo('contenedor_movimientos','dictamen/nuevo_dictamen/'+servicio+'/'+id_episodio);
     }

   },
   error: function(respuesta){ alerta('Alerta!','Error de conectividad de red ');}
  });

 }

 function carga_servicio_dictamen(servicio){
   var episodio = $('#id_episodio_left').val();
   carga_archivo('contenedor_movimientos','dictamen/nuevo_dictamen/'+servicio+'/'+episodio);
 }

 function modal_addclaves_dictamen_cie(obj){
  var idtextarea = $(obj).data('idtextarea');
  var name_tabla  = $(obj).data('nameidtr');
  var tabla = 'append_cie';

  $.ajax({
   url: 'dictamen/modal_addclaves_dictamen/'+name_tabla,
   dataType: 'html',
   success: function(resp_success){

     var modal =  resp_success;
     $(modal).modal().on('shown.bs.modal',function(){

        $("#"+tabla+"_modal").dataTable( {
           "fnDrawCallback": function( oSettings ) {
           },
           "searching": true,
           "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
           "processing": true,
           "serverSide": true,
           //'scrollY': 400,
           "ajax": {
               "url": "dictamen/data_table_claves/"+name_tabla,
               "type": "POST"
           }
       } );

       $("#"+tabla+"_modal").on("click", "#btn_agrega_tr", function() {
           var id = $(this).data('id');
           var clave = $(this).data('clave');
           var descripcion = $(this).data('descripcion');
           $("#"+idtextarea+"_textarea").val(descripcion);
           $("#"+idtextarea).val(id);
           toastr.success("Se agregó la clave CIE-10!");
         });

     }).on('hidden.bs.modal',function(){
       $(this).remove();
     });
   },
   error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-03');}
 });
 }
