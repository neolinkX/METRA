$(function() {
  var list = ['LA CARGA DE UNIDADES, M \u00d3DULOS, SERVICIOS Y RAMAS HA FALLADO...'
            ,'LA CARGA PARA LOS ADMINISTRADORES DEL SISTEMA HA FALLADO'
            , 'OCURRIERON ERRORES EN LA CARGA \u00BFDESEA REALIZAR NUEVAMENTE NUEVAMENTE\u003F LA CARGA...'
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