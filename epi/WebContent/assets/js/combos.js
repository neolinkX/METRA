function generaCombosSecundario(objeto,nombre_cat,other){
      var value = objeto.value, combo_secundario = $("#"+objeto.id).data('load_datacombo');
      //var url = 'catalogo/getCatalogoSecundario/' + value+'/'+nombre_cat;
      var url = 'catalogo/obtener_catalogo.jsp'; 
      if(value==''){return false;}
      if(other!==undefined){
         url = 'catalogo/getCatalogoSecundario/' + value+'/'+nombre_cat + '/' + other;
      }
     $.ajax({
       url: url,
       data:{"valor":value,"cat":nombre_cat},
       dataType: 'json',
       type: 'Post',
       success: function(resp_success){
           genInputSelect(resp_success,combo_secundario);
           if(objeto.id=='id_modo_transporte'){
             clearComboSelect2('puesto_tramite');
           }
           //clearComboSelect2('puesto_tramite');
       },
       error: function(respuesta){ alerta('Alerta!','Error de conectividad de red CATGL-02');}
     });
}

function genInputSelect(data,id_select){
  var obj_combo = $("#"+id_select);
  clearComboSelect2(id_select);
  $.each(data, function(index, item) {
    obj_combo.append($('<option>',
       {
          value: item.id,
          text : item.value
      }));
  });
}
