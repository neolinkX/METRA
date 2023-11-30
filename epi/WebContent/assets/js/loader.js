$(window).on('load', function(){
  $('#initpreloader').fadeOut('slow');
  $('body').css({'overflow':'visible'});
  var intro;
  $(function() {
    intro = new buzz.sound( "assets/audio/intro", {
      formats: ['mp3']
    }).setVolume(100).play();
  });
})
