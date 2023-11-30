$(function() {
  var list = ['LA CARGA DE UNIDADES, M\u00d3DULOS, SERVICIOS Y RAMAS FUE REALIZADA EXITOSAMENTE...'
            ,'LA CARGA SE HA REALIZADO EXITOSAMENTE PARA LOS ADMINISTRADORES DEL SISTEMA...'
            , 'LA CARGA SE HA REALIZADA EXITOSAMENTE...'
            , 'SISTEMA INSTITUCIONAL DE PROTECCI\u00d3N Y MEDICINA PREVENTIVA EN EL TRANSPORTE.'];

  var txt = $('#txtlzr');

  var options = {
  duration: 1000,          // Time (ms) each blurb will remain on screen
  rearrangeDuration: 1000, // Time (ms) a character takes to reach its position
  effect: 'random',        // Animation effect the characters use to appear
  centered: true           // Centers the text relative to its container
}

  txt.textualizer(list,options);
  txt.textualizer('start');
});