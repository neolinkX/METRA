function guarda_hematologiaI(){

  var pase = 0;
  $("#frm_hematologiaI").find(':input').each(function(){
      if(this.value == ""){
          pase = 0;
      }else{
          pase = 1;
          return false;
      }
  });

  if(pase>=1){

  var options = {type:'question',title:'¿Desea guardar Hematología I?'}
  modalConfirm(function (){
      $.ajax({
        url:  'laboratorio/guarda_hematologiaI/'+$("#id_episodio_left").val(),
        type: 'POST',
        data: $("#frm_hematologiaI").serialize(),
        dataType: 'json',
        success: function(resp_success){
          if(resp_success.resp=='true'){
            carga_archivo('contenedor_movimientos','laboratorio/analisisClinicos/'+$("#id_episodio_left").val());
            swal('Se guardó correctamente la información!', '', "success");
      	  }else{
            alerta('Alerta!','Error de conectividad de red LAB-01');
          }
        },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red LAB-01');}
      });
    },options);

  }else{
    alerta('Alerta!','Debe llenarse por lo menos un campo');
  }

}

function get_laboratorio(){
  var episodio = $("#id_episodio_left").val();
  carga_archivo('contenedor_movimientos','laboratorio/analisisClinicos/'+episodio);
}

function guarda_qclinica(){

  var pase = 0;
  $("#frm_qclinica").find(':input').each(function(){
      if(this.value == ""){
          pase = 0;
      }else{
          pase = 1;
          return false;
      }
  });

  if(pase>=1){

    var options = {type:'question',title:'¿Desea guardar Química Clínica?'}
    modalConfirm(function (){
      $.ajax({
        url:  'laboratorio/guarda_qclinica/'+$("#id_episodio_left").val(),
        type: 'POST',
        data: $("#frm_qclinica").serialize(),
        dataType: 'json',
        success: function(resp_success){
          if(resp_success.resp=='true'){
            carga_archivo('contenedor_movimientos','laboratorio/analisisClinicos/'+$("#id_episodio_left").val());
            swal('Se guardó correctamente la información!', '', "success");
      	  }else{
            alerta('Alerta!','Error de conectividad de red LAB-02');
          }
        },
        error: function(respuesta){ alerta('Alerta!','Error de conectividad de red LAB-02');}
      });
    },options);

  }else{
    alerta('Alerta!','Debe llenarse por lo menos un campo');
  }

}

function guarda_ego(){

  var pase = 0;
  $("#frm_ego").find(':input').each(function(){
      if(this.value == "" || this.value == "on"){
          pase = 0;
      }else{
          pase = 1;
          return false;
      }
  });

  if(pase>=1){

      var options = {type:'question',title:'¿Desea guardar Examen General de Orina?'}
      modalConfirm(function (){
        $.ajax({
          url:  'laboratorio/guarda_ego/'+$("#id_episodio_left").val(),
          type: 'POST',
          data: $("#frm_ego").serialize(),
          dataType: 'json',
          success: function(resp_success){
            if(resp_success.resp=='true'){
              carga_archivo('contenedor_movimientos','laboratorio/analisisClinicos/'+$("#id_episodio_left").val());
              swal('Se guardó correctamente la información!', '', "success");
        	  }else{
              alerta('Alerta!','Error de conectividad de red LAB-03');
            }
          },
          error: function(respuesta){ alerta('Alerta!','Error de conectividad de red LAB-03');}
        });
      },options);

  }else{
    alerta('Alerta!','Debe llenarse por lo menos un campo');
  }
}

function guarda_hematologia(){

  var pase = 0;
  $("#frm_hematologia").find(':input').each(function(){
      if(this.value == "" || this.value == "on"){
          pase = 0;
      }else{
          pase = 1;
          return false;
      }
  });

  if(pase>=1){

      var options = {type:'question',title:'¿Desea guardar Hematología?'}
      modalConfirm(function (){
        $.ajax({
          url:  'laboratorio/guarda_hematologia/'+$("#id_episodio_left").val(),
          type: 'POST',
          data: $("#frm_hematologia").serialize(),
          dataType: 'json',
          success: function(resp_success){
            if(resp_success.resp=='true'){
              carga_archivo('contenedor_movimientos','laboratorio/analisisClinicos/'+$("#id_episodio_left").val());
              swal('Se guardó correctamente la información!', '', "success");
        	  }else{
              alerta('Alerta!','Error de conectividad de red LAB-04');
            }
          },
          error: function(respuesta){ alerta('Alerta!','Error de conectividad de red LAB-04');}
        });
      },options);

    }else{
      alerta('Alerta!','Debe llenarse por lo menos un campo');
    }

}

function guarda_dterapeuticas(){
  var options = {type:'question',title:'¿Desea guardar Drogas Terapeúticas?'}
  modalConfirm(function(){
    $.ajax({
      url:  'laboratorio/guarda_dterapeuticas/'+$("#id_episodio_left").val(),
      type: 'POST',
      data: $("#frm_dterapeuticas").serialize(),
      dataType: 'json',
      success: function(resp_success){
        if(resp_success.resp=='true'){
          carga_archivo('contenedor_movimientos','laboratorio/analisisClinicos/'+$("#id_episodio_left").val());
          swal('Se guardó correctamente la información!', '', "success");
    	  }else{
          alerta('Alerta!','Error de conectividad de red LAB-05');
        }
      },
      error: function(respuesta){ alerta('Alerta!','Error de conectividad de red LAB-05');}
    });
  },options);
}
