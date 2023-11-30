$("body").on("click", ".start_imagemap", function() {
  mapaImagen = $(this).attr('data-map');
  $.ajax({
		url: url_app + "imagemap/iframe/" + mapaImagen,
		type: 'POST',
		dataType: "html",
		success: function(resp_success){
      var modal =  resp_success;
      $(modal).modal().on('shown.bs.modal',function(){
        /**/
      }).on('hidden.bs.modal',function(){
        $(this).remove();
      });
		},
		error: function(){alerta('Alerta!','Error de conectividad de red IMGM-01');}
	});
});
