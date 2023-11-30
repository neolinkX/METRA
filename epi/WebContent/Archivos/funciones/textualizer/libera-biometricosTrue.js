$(function() {
  var list = ['LOS BIOM\u00C9TRICOS FUERON LIBERADOS EXITOSAMENTE...'
            ,'\u00BFDESEA LIBERAR LOS BIOM\u00C9TRICOS DE OTRO EXPEDIENTE\u003F'
            , 'LOS BIOM\u00C9TRICOS FUERON LIBERADOS EXITOSAMENTE...'
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