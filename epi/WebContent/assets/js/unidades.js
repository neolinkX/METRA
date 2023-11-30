
/**
 * [$ description]
 * @param  {[type]} form_alta_persona [description]
 * @return {[type]}                   [description]
 */
$("body").on("click", "#btn_save_unidad", function() {

    $("#form_modifica_unidad").validate({ debug: true,
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
        	actualiza_Unidad();
        }
    });


});



/**
 * [$ description]
 * @param  {[type]} form_alta_persona [description]
 * @return {[type]}                   [description]
 */
$("body").on("click", "#btn_save_modifica_unidad", function() {

    $("#form_modifica_unidad").validate({ debug: true,
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
        	actualiza_Unidad();
        }
    });


});




//Funcion para Unidades Medicas
$('body').on('click', '#btn_unimed', function () {
var id = $(this).data('paciente');
var id_unimed = $(this).data('unimed');
//alert(id_unimed);
//carga_archivo('contenedor_principal','persona/perfilPersona/'+id);
carga_archivo('contenedor_principal','unidades/modifica_unidad.jsp?icveunimed='+id_unimed);
//carga_foco_paciente(id,id_episodio);
//$("#menu_lat_flot").show();
//$('body').removeClass('m-brand--minimize');
//$('body').removeClass('m-aside-left--minimize');
});


//Funcion para Unidades Medicas
$('body').on('click', '#btn_alta_unimed', function () {
var id = $(this).data('paciente');
var id_unimed = $(this).data('unimed');
//alert(id_unimed);
//carga_archivo('contenedor_principal','persona/perfilPersona/'+id);
carga_archivo('contenedor_principal','unidades/alta_unidad.jsp?icveunimed='+id_unimed);
//carga_foco_paciente(id,id_episodio);
//$("#menu_lat_flot").show();
//$('body').removeClass('m-brand--minimize');
//$('body').removeClass('m-aside-left--minimize');
});


function actualiza_Unidad(){
    var options = {type:'question',title:'\u00BFDesea actualizar la Unidad M\u00E9dica?'}
  modalConfirm(function (){
      $.ajax({
        url: 'unidades/guardaUnidad.jsp',
        dataType: 'json',
        type: 'Post',
        data: $("#form_modifica_unidad").serialize(),
 	     success: function(resp_success){
 	       	//alert(resp_success[0].resp);
 	       	if(resp_success[0].resp=='success'){
 	       	//swal('El expediente se creo satisfactoriamente, el expediente asociado es el '+resp_success[1].exp, '', "success");
 	       	swal('La Unidad M\u00E9dica se actualizo satisfactoriamente', '', "success");
            //carga_archivo('contenedor_principal','citas/');
            //carga_foco_vacio();
            //carga_ficha_persona(resp_success[1].exp);
 	         }else{
 	              swal('No fue posible crear el expediente, int\u00E9ntalo nuevamente', '', "error");
             }
 	     },   
          error: function(respuesta){ alerta('Alerta!','Error de conectividad de red USR-01');}
      });
   },options);
}
