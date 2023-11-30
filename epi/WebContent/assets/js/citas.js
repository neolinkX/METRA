
function guarda_inicio_proceso_epi(id_episodio){
  var options = {type:'question',title:'\u00BFSe iniciar\u00E1 el Proceso EPI, Desea continuar?'}
  modalConfirm(function (){
    $.ajax({
      //url: 'epi/inicioProcesoEpi/'+id_episodio,
      url: 'epi/inicioProcesoEpi.jsp',
      type: 'POST',
      dataType: 'json',
      success: function(resp_success){
          if(resp_success.resp==true){
        	swal('Se inicio correctamente el proceso EPI!', '', "success");
        	$("#btn_inicio_epi").remove();          	
          }

      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
    });
  },options);
}


function guarda_inicio_proceso_epi(id_episodio){
	  var options = {type:'question',title:'\u00BFSe iniciar\u00E1 el Proceso EPI, Desea continuar?'}
	  modalConfirm(function (){
	    $.ajax({
	      //url: 'epi/inicioProcesoEpi/'+id_episodio,
	      url: 'epi/inicioProcesoEpi.jsp',
	      type: 'POST',
	      dataType: 'json',
	      success: function(resp_success){
	    	  //alert(resp_success[0].resp);
	          //if(resp_success[0].resp==true){
	    	  if(resp_success[0].resp=='success'){
	        	swal('Se inicio correctamente el proceso EPI!', '', "success");
	        	$("#btn_inicio_epi").remove();          	
	          }else{
	        	  swal('Se presentaron errores al generar el proceso EPI: '+resp_success[0].resp, '', "error");
	          }

	      },
	      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
	    });
	  },options);
	}


function guarda_tarmite_cita555555555555555(id_paciente){
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

    var options = {type:'question',title:'\u00BFDesea guardar el Tr\u00E1mite?'}
    modalConfirm(function (){
    	//alert("tramite");
      $.ajax({
        //url: url_app + 'citas/saveTramiteCita/'+id_paciente,
    	url: 'citas/saveTramiteCita.jsp?hdICvePersonal='+id_paciente,
        type: 'POST',
        data: $("#form_tramites_cita").serialize(),
        dataType: 'json',
        success: function(resp_success){
        	alert(resp_success[0].resp);
            //if(resp_success.resp==true){
        	if(resp_success[0].resp=='success'){
                swal('Se guardó correctamente!', '', "success");
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





function VerResultadosReglas(id_episodio){
	  var options = {type:'question',title:'\u00BFSe iniciar\u00E1 el Proceso EPI, Desea continuar?'}
	  modalConfirm(function (){
	    $.ajax({
	      //url: 'epi/inicioProcesoEpi/'+id_episodio,
	      url: 'pg070104071.jsp',
	      type: 'POST',
	      dataType: 'json',
	      success: function(resp_success){
	          if(resp_success.resp==true){
	        	swal('Se inicio correctamente el proceso EPI!', '', "success");
	        	$("#btn_inicio_epi").remove();          	
	          }

	      },
	      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red PER-06');}
	    });
	  },options);
	}
